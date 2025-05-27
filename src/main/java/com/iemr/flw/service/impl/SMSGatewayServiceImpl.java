
package com.iemr.flw.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import com.iemr.flw.dto.iemr.SmsRequestOBJ;
import com.iemr.flw.service.SMSGatewayService;
import com.iemr.flw.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.http.HttpServletRequest;

@Service
@PropertySource("classpath:application.properties")
public class SMSGatewayServiceImpl implements SMSGatewayService {
	@Value("${sendSMSUrl}")
	private String sendSMSUrl;


	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CookieUtil cookieUtil;

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Override
	public int smsSenderGateway(String smsType, Long benRegID,  String createdBy, String tcDate, String tcPreviousDate, String Authorization) {

		int returnOBJ = 0;
		try {
			String requestOBJ = createSMSRequest(smsType, benRegID,
					createdBy, tcDate, tcPreviousDate);

			if (requestOBJ != null) {
				String smsStatus = sendSMS(requestOBJ, Authorization);
				if (smsStatus != null) {
					JsonObject jsnOBJ = new JsonObject();
					JsonParser jsnParser = new JsonParser();
					JsonElement jsnElmnt = jsnParser.parse(smsStatus);
					jsnOBJ = jsnElmnt.getAsJsonObject();
					if (jsnOBJ != null && jsnOBJ.get("statusCode").getAsInt() == 200)
						returnOBJ = 1;
				}
				// System.out.println("hello");
			}
		} catch (Exception e) {
			logger.info("Exception during sending " + smsType + " SMS " + e.getMessage());
		}
		return returnOBJ;

	}



	@Override
	public String createSMSRequest(String smsType, Long benRegID, String createdBy, String tcDate, String tcPreviousDate) {

		SmsRequestOBJ obj = null;
		ArrayList<SmsRequestOBJ> objList = new ArrayList<>();

		int smsTypeID;

		switch (smsType) {
			case "ANC":
			//	smsTypeID = tCRequestModelRepo.getSMSTypeID(schedule);
				break;

			default:
				smsTypeID = 0;
		}
		return  "";



	}

	@Override
	public String sendSMS(String request, String Authorization) {
		HttpServletRequest requestHeader = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String jwtTokenFromCookie = cookieUtil.getJwtTokenFromCookie(requestHeader);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("AUTHORIZATION", Authorization);
		headers.add("Cookie", "Jwttoken=" + jwtTokenFromCookie);

		HttpEntity<Object> requestOBJ = new HttpEntity<Object>(request, headers);

		return restTemplate.exchange(sendSMSUrl, HttpMethod.POST, requestOBJ, String.class).getBody();
	}
}
