package com.iemr.flw.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.primitives.Ints;
import com.iemr.flw.domain.iemr.OtpBeneficiary;
import com.iemr.flw.dto.iemr.OTPRequestParsor;
import com.iemr.flw.dto.iemr.OtpRequestDTO;
import com.iemr.flw.repo.iemr.OtpBeneficiaryRepository;
import com.iemr.flw.service.OTPHandler;
import com.iemr.flw.utils.CookieUtil;
import com.iemr.flw.utils.JwtUtil;
import com.iemr.flw.utils.config.ConfigProperties;
import com.iemr.flw.utils.http.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class OTPHandlerServiceImpl implements OTPHandler {
    @Autowired
    HttpUtils httpUtils;
    @Value("${sendSMSUrl}")
    private String sendSMSUrl;

    @Value("${airtel.api.url}")
    private String apiUrl;

    @Value("${airtel.api.customerId}")
    private String customerId;

    @Value("${airtel.api.sourceAddress}")
    private String sourceAddress;

    @Value("${airtel.api.dltTemplateId}")
    private String dltTemplateId;

    @Value("${airtel.api.entityId}")
    private String entityId;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    OtpBeneficiaryRepository otpBeneficiaryRepository;
    final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private LoadingCache<String, String> otpCache;

    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private JwtUtil jwtUtil;


    private static final Integer EXPIRE_MIN = 15;

    private static final String SMS_GATEWAY_URL = ConfigProperties.getPropertyByName("sms-gateway-url");


    // Constructor for new object creation
    public OTPHandlerServiceImpl() {
        otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    public String load(String key) {
                        return "0";
                    }
                });
    }

    /***
     * @param
     * @return success if OTP sent successfully
     */
    @Override
    public String sendOTP(String mobNo, String auth) throws Exception {
        int otp = generateOTP(mobNo);
        saveOtp(mobNo, otp);

        return  sendSms(mobNo,"OTP-123",String.valueOf(otp));
//        restTemplate = new RestTemplate();
//        String url = OTP_SERVICE_URL + "/sendOTP";
//        logger.info(url);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", auth);
//
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("mobNo", mobNo);
//
//        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

//        return response.getBody();
    }

    /***
     * @param obj
     * @return OTP verification success or failure
     *
     */
    @Override
    public String validateOTP(OTPRequestParsor obj, String auth) throws Exception {
        String cachedOTP = otpCache.get(obj.getMobNo());
        String inputOTPEncrypted = getEncryptedOTP(obj.getOtp());

        if (cachedOTP.equalsIgnoreCase(inputOTPEncrypted)) {
            JSONObject responseObj = new JSONObject();
            responseObj.put("userName", obj.getMobNo());
            responseObj.put("userID", obj.getMobNo());
            saveBeneficiaryId(obj.getMobNo(), obj.getOtp());
            return responseObj.toString();
        } else {
            throw new Exception("Please enter valid OTP");
        }
//        restTemplate = new RestTemplate();
//
//        String url = OTP_SERVICE_URL + "/validateOTP";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", auth);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("mobNo", obj.getMobNo());
//        requestBody.put("otp", obj.getOtp());
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
//
//        return response.getBody();
    }
    public String saveBeneficiaryId(String phoneNumber, Integer otp) {
        Optional<OtpBeneficiary> otpEntry = otpBeneficiaryRepository.findByPhoneNumberAndOtp(phoneNumber, otp);

        if (otpEntry.isPresent()) {
            OtpBeneficiary otpBeneficiary = otpEntry.get();
            otpBeneficiary.setIsOtpVerify(true);
            return "Beneficiary Id created.";
        } else {
            return "Invalid Beneficiary";
        }
    }
    /***
     * @param
     * @return success if OTP re-sent successfully
     */
    @Override
    public String resendOTP(String mobNo, String auth) throws Exception {
        int otp = generateOTP(mobNo);
        saveOtp(mobNo, otp);

        return  sendSms(mobNo,"OTP-123",String.valueOf(otp));
//        restTemplate = new RestTemplate();
//
//        String url = OTP_SERVICE_URL + "/resendOTP";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", auth);
//
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("mobNo", mobNo);
//
//        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
//
//        return response.getBody();
    }
    @Override
    public JSONObject saveBenficiary(OtpRequestDTO requestOBJ) {
        JSONObject jsonObject = new JSONObject();

        saveBeneficiaryId(requestOBJ.getPhoneNumber(), requestOBJ.getOtp());
        jsonObject.put("data", requestOBJ);

        return jsonObject;
    }
    @Override
    public String sendSMS(String request, String Authorization) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("AUTHORIZATION", Authorization);

        HttpEntity<Object> requestOBJ = new HttpEntity<Object>(request, headers);

        return restTemplate.exchange(sendSMSUrl, HttpMethod.POST, requestOBJ, String.class).getBody();
    }
    private void saveOtp(String phoneNo, Integer otp) {
        OtpBeneficiary otpEntry = new OtpBeneficiary();
        otpEntry.setPhoneNumber(phoneNo);
        otpEntry.setOtp(otp);
        otpEntry.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        otpBeneficiaryRepository.save(otpEntry);

    }
    // generate 6 digit random no #
    public int generateOTP(String authKey) throws Exception {
        String generatedPassword = null;
//		Random random = new Random();
        Random random = SecureRandom.getInstanceStrong();
        int otp = 100000 + random.nextInt(900000);

        generatedPassword = getEncryptedOTP(otp);

        if (otpCache != null)
            otpCache.put(authKey, generatedPassword);
        else {
            OTPHandlerServiceImpl obj = new OTPHandlerServiceImpl();
            obj.otpCache.put(authKey, generatedPassword);
        }
        return otp;
    }
    // SHA-256 encoding logic implemented
    private String getEncryptedOTP(int otp) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(Ints.toByteArray(otp));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    // send SMS to user
    public String sendSms(String phoneNumber, String applicationId,String opt) {
//        HttpServletRequest requestHeader = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
//        String jwtTokenFromCookie = cookieUtil.getJwtTokenFromCookie(requestHeader);
//        logger.info("Token:"+jwtTokenFromCookie);
//        String userName = jwtUtil.getUsernameFromToken(jwtTokenFromCookie);
//        logger.info("UserName:"+userName);
        try {
            String message = "Dear Citizen, your OTP for login is " +opt+". Use it within 15 minutes. Do not share this code. Regards PSMRIAM.";
//            String message = "Hello! Your OTP for providing consent for registration on AMRIT is {#OTP#}. This OTP is valid for 10 minutes. Kindly share it only with {#User Name, Designation#} to complete the process. PSMRI";
            // Build payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("customerId", customerId);
            payload.put("destinationAddress", phoneNumber);
            payload.put("message", message);
            payload.put("sourceAddress", sourceAddress);
            payload.put("messageType", "SERVICE_IMPLICIT");
            payload.put("dltTemplateId", dltTemplateId);
            payload.put("entityId", entityId);
            payload.put("otp", true);

            Map<String, Object> metaData = new HashMap<>();
            metaData.put("OTP", opt);
            payload.put("metaData", metaData);
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            String auth = customerId + ":" + "]Kt9GAp8}$S*@";
            headers.add("Authorization",
                    "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            // Call API
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

            // Return response
            return "otp"+opt;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending SMS: " + e.getMessage();
        }
    }
}
