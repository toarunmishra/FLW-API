package com.iemr.flw.service.impl;

import com.iemr.flw.domain.iemr.*;
import com.iemr.flw.dto.iemr.*;
import com.iemr.flw.repo.iemr.*;
import com.iemr.flw.service.VhndFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VhndFormServiceImpl implements VhndFormService {

    @Autowired
    private VillageFormRepository repository;
    @Autowired
    private IncentiveRecordRepo recordRepo;
    @Autowired
    private UserServiceRoleRepo userRepo;

    @Autowired
    private IncentivesRepo incentivesRepo;

    @Autowired
    private PHCReviewFormRepo phcReviewFormRepo;

    @Autowired
    private DewormingFormRepo dewormingFormRepo;

    @Autowired
    private VhncFormRepo vhndFormRepo;

    @Autowired
    private VhndRepo vhndRepo;

    @Autowired
    private VhncFormRepo vhncFormRepo;

    @Autowired
    private AHDFormRepo ahdFormRepo;


    public String submitForm(VilageLevelFormDto vilageLevelFormDto) {
        for (VilageLevelFormListDto dto : vilageLevelFormDto.getVilageLevelFormList()) {
            if (dto.getUserId() == null || dto.getDate() == null || dto.getPlace() == null || dto.getParticipantCount() <= 0) {
                return "Missing or invalid fields";
            }


            VillageFormEntry entry = new VillageFormEntry();
            entry.setUserId(dto.getUserId());
            entry.setFormType(dto.getFormType());
            entry.setDate(dto.getDate());
            entry.setPlace(dto.getPlace());
            entry.setParticipantCount(dto.getParticipantCount());
          //  entry.setImageUrls(dto.getImageUrls());
            entry.setSubmittedAt(LocalDateTime.now());
            entry.setCreatedDate(Date.valueOf(LocalDate.now()));
            entry.setCreatedBy(dto.getCreatedBy());

            repository.save(entry);
            //checkAndAddIncentives(entry);
            return "Form submitted successfully";


        }
        return "Fail";


    }

    @Override
    public String submitForm(VhndDto dto) {
        for (VHNDFormDTO vhndFormDTO : dto.getEntries()) {
            saveVhndFormData(vhndFormDTO,dto.getUserId());

        }
        return "Fail" ;
    }

    @Override
    public String submitVhncForm(VhncDto dto) {
        for(VhncFormDTO  vhncFormDTO : dto.getEntries()){
            saveVhncFormData(vhncFormDTO,dto.getUserId());
        }
        return "Fail";
    }
    private String saveVhncFormData(VhncFormDTO vhncFormDTO, Integer userID) {
        VhncForm vhncForm = new VhncForm();
        vhncForm.setUserId(Long.valueOf(userID));
        vhncForm.setVhncDate(vhncFormDTO.getVhncDate());
        vhncForm.setImage2(vhncFormDTO.getImage2());
        vhncForm.setImage1(vhncFormDTO.getImage1());
        vhncForm.setPlace(vhncFormDTO.getPlace());
        vhncForm.setNoOfBeneficiariesAttended(vhncFormDTO.getNoOfBeneficiariesAttended());
        vhncForm.setFormType("VHNC");
        vhncFormRepo.save(vhncForm);  // Data ko save karte hain
        checkAndAddIncentivesForVhnc(vhncForm); // Incentives ko check karte hain
        return "Save Vhnd Form successfully";
    }



    @Override
    public String submitPhcForm(PhcReviewMeetingDTO dto) {
        for(PhcReviewMeetingFormDTO phcReviewMeetingDTO: dto.getEntries()){
            submitPhcForm(phcReviewMeetingDTO,dto.getUserId());
        }
        return "Fail";
    }

    @Override
    public String submitAdhForm(AhdMeetingDto dto) {
        for(AhDMeetingFormDTO ahDMeetingFormDTO: dto.getEntries()){
            submitAhdForm(ahDMeetingFormDTO,dto.getUserId());
        }
        return "Fail";
    }

    @Override
    public String submitDewormingForm(DewormingDto dto) {
        for(DewormingFormDTO dewormingFormDTO: dto.getEntries()){
            submitDewormingForm(dewormingFormDTO,dto.getUserId());
        }
        return "Fail";
    }



    private String submitPhcForm(PhcReviewMeetingFormDTO dto, Integer userID) {
        PHCReviewForm phcReviewForm = new PHCReviewForm();
        phcReviewForm.setUserId(Long.valueOf(userID));
        phcReviewForm.setPhcReviewDate(dto.getPhcReviewDate());
        phcReviewForm.setPlace(dto.getPlace());
        phcReviewForm.setNoOfBeneficiariesAttended(dto.getNoOfBeneficiariesAttended());
        phcReviewForm.setImage1(dto.getImage1());
        phcReviewForm.setImage2(dto.getImage2());
        phcReviewForm.setFormType("PHC Review");
        phcReviewFormRepo.save(phcReviewForm);
        checkAndAddIncentivesForPhcReview(phcReviewForm);
        return "Save PHC Review Form successfully";
    }

    private String submitAhdForm(AhDMeetingFormDTO dto, Integer userID) {
        AHDForm ahdForm = new AHDForm();
        ahdForm.setUserId(Long.valueOf(userID));
        ahdForm.setMobilizedForAHD(dto.getMobilizedForAHD());
        ahdForm.setAhdPlace(dto.getAhdPlace());
        ahdForm.setAhdDate(dto.getAhdDate());
        ahdForm.setImage1(dto.getImage1());
        ahdForm.setImage2(dto.getImage2());
        ahdForm.setFormType("AHD Meeting");
        ahdFormRepo.save(ahdForm);
        checkAndAddIncentivesForAhd(ahdForm);
        return "Save AHD Form successfully";
    }

    private String submitDewormingForm(DewormingFormDTO dto, Integer userID) {
        DewormingForm dewormingForm = new DewormingForm();
        dewormingForm.setUserId(Long.valueOf(userID));
        dewormingForm.setDewormingDone(dto.getDewormingDone());
        dewormingForm.setDewormingDate(dto.getDewormingDate());
        dewormingForm.setDewormingLocation(dto.getDewormingLocation());
        dewormingForm.setAgeGroup(dto.getAgeGroup());
        dewormingForm.setImage1(dto.getImage1());
        dewormingForm.setImage2(dto.getImage2());
        dewormingForm.setFormType("Deworming");
        dewormingFormRepo.save(dewormingForm);
        checkAndAddIncentivesForDeworming(dewormingForm);
        return "Save Deworming Form successfully";
    }



    private String saveVhndFormData(VHNDFormDTO vhndFormDTO,Integer userID) {
        VHNDForm vhndForm = new VHNDForm();
        vhndForm.setUserId(userID);
        vhndForm.setVhndDate(vhndFormDTO.getVhndDate());
        vhndForm.setImage2(vhndFormDTO.getImage2());
        vhndForm.setImage1(vhndFormDTO.getImage1());
        vhndForm.setPlace(vhndFormDTO.getPlace());
        vhndForm.setNoOfBeneficiariesAttended(vhndFormDTO.getNoOfBeneficiariesAttended());
        vhndForm.setFormType("VHND");
        vhndRepo.save(vhndForm);
        checkAndAddIncentivesForVhnd(vhndForm);
        return "Save Vhnd Form successfully";


    }

    @Override
    public Object getAll(GetVillageLevelRequestHandler getVillageLevelRequestHandler) {
        if(Objects.equals(getVillageLevelRequestHandler.getFormType(), "VHND")){
            return vhndRepo.findAll().stream().filter(vhndForm -> Objects.equals(vhndForm.getUserId(),getVillageLevelRequestHandler.getUserId())).collect(Collectors.toList());
        }
        return null;
    }


    private void checkAndAddIncentivesForVhnd(VHNDForm villageFormEntry) {
        IncentiveActivity villageFormEntryActivity;
        villageFormEntryActivity = incentivesRepo.findIncentiveMasterByNameAndGroup(villageFormEntry.getFormType(), "VILLAGELEVEL");

        if (villageFormEntryActivity != null) {
            IncentiveActivityRecord record = recordRepo
                    .findRecordByActivityIdCreatedDateBenId(villageFormEntryActivity.getId(), Timestamp.valueOf(villageFormEntry.getVhndDate().toString()),null);
            if (record == null) {
                record = new IncentiveActivityRecord();
                record.setActivityId(villageFormEntryActivity.getId());
                record.setCreatedDate(Timestamp.valueOf(villageFormEntry.getVhndDate().toString()));
                record.setCreatedBy(villageFormEntry.getCreatedBy());
                record.setStartDate(Timestamp.valueOf(villageFormEntry.getVhndDate().toString()));
                record.setEndDate(Timestamp.valueOf(villageFormEntry.getVhndDate().toString()));
                record.setUpdatedDate(Timestamp.valueOf(villageFormEntry.getVhndDate().toString()));
                record.setUpdatedBy(villageFormEntry.getCreatedBy());
                record.setAshaId(villageFormEntry.getUserId());
                record.setName(villageFormEntryActivity.getName());
                record.setAmount(Long.valueOf(villageFormEntryActivity.getRate()));
                recordRepo.save(record);

            }
        }
    }

    private void checkAndAddIncentivesForVhnc(VhncForm villageFormEntry) {
        IncentiveActivity villageFormEntryActivity;
        villageFormEntryActivity = incentivesRepo.findIncentiveMasterByNameAndGroup(villageFormEntry.getFormType(), "VILLAGELEVEL");

        if (villageFormEntryActivity != null) {
            IncentiveActivityRecord record = recordRepo
                    .findRecordByActivityIdCreatedDateBenId(villageFormEntryActivity.getId(), Timestamp.valueOf(villageFormEntry.getVhncDate().toString()),null);
            if (record == null) {
                record = new IncentiveActivityRecord();
                record.setActivityId(villageFormEntryActivity.getId());
                record.setCreatedDate(Timestamp.valueOf(villageFormEntry.getVhncDate().toString()));
                record.setCreatedBy(villageFormEntry.getCreatedBy());
                record.setStartDate(Timestamp.valueOf(villageFormEntry.getVhncDate().toString()));
                record.setEndDate(Timestamp.valueOf(villageFormEntry.getVhncDate().toString()));
                record.setUpdatedDate(Timestamp.valueOf(villageFormEntry.getVhncDate().toString()));
                record.setUpdatedBy(villageFormEntry.getCreatedBy());
                record.setAshaId(Math.toIntExact(villageFormEntry.getUserId()));
                record.setName(villageFormEntryActivity.getName());
                record.setAmount(Long.valueOf(villageFormEntryActivity.getRate()));
                recordRepo.save(record);

            }
        }
    }

    private void checkAndAddIncentivesForPhcReview(PHCReviewForm phcReviewFormEntry) {
        IncentiveActivity phcReviewFormActivity = incentivesRepo.findIncentiveMasterByNameAndGroup(phcReviewFormEntry.getFormType(), "MEETINGLEVEL");

        if (phcReviewFormActivity != null) {
            IncentiveActivityRecord record = recordRepo
                    .findRecordByActivityIdCreatedDateBenId(phcReviewFormActivity.getId(), Timestamp.valueOf(phcReviewFormEntry.getPhcReviewDate().toString()), null);
            if (record == null) {
                record = new IncentiveActivityRecord();
                record.setActivityId(phcReviewFormActivity.getId());
                record.setCreatedDate(Timestamp.valueOf(phcReviewFormEntry.getPhcReviewDate().toString()));
                record.setCreatedBy(phcReviewFormEntry.getCreatedBy());
                record.setStartDate(Timestamp.valueOf(phcReviewFormEntry.getPhcReviewDate().toString()));
                record.setEndDate(Timestamp.valueOf(phcReviewFormEntry.getPhcReviewDate().toString()));
                record.setUpdatedDate(Timestamp.valueOf(phcReviewFormEntry.getPhcReviewDate().toString()));
                record.setUpdatedBy(phcReviewFormEntry.getCreatedBy());
                record.setAshaId(Math.toIntExact(phcReviewFormEntry.getUserId()));
                record.setName(phcReviewFormActivity.getName());
                record.setAmount(Long.valueOf(phcReviewFormActivity.getRate()));
                recordRepo.save(record);
            }
        }
    }

    private void checkAndAddIncentivesForDeworming(DewormingForm dewormingFormEntry) {
        IncentiveActivity dewormingFormActivity = incentivesRepo.findIncentiveMasterByNameAndGroup(dewormingFormEntry.getFormType(), "VILLAGELEVEL");

        if (dewormingFormActivity != null) {
            IncentiveActivityRecord record = recordRepo
                    .findRecordByActivityIdCreatedDateBenId(dewormingFormActivity.getId(), Timestamp.valueOf(dewormingFormEntry.getDewormingDate().toString()), null);
            if (record == null) {
                record = new IncentiveActivityRecord();
                record.setActivityId(dewormingFormActivity.getId());
                record.setCreatedDate(Timestamp.valueOf(dewormingFormEntry.getDewormingDate().toString()));
                record.setCreatedBy(dewormingFormEntry.getCreatedBy());
                record.setStartDate(Timestamp.valueOf(dewormingFormEntry.getDewormingDate().toString()));
                record.setEndDate(Timestamp.valueOf(dewormingFormEntry.getDewormingDate().toString()));
                record.setUpdatedDate(Timestamp.valueOf(dewormingFormEntry.getDewormingDate().toString()));
                record.setUpdatedBy(dewormingFormEntry.getCreatedBy());
                record.setAshaId(Math.toIntExact(dewormingFormEntry.getUserId()));
                record.setName(dewormingFormActivity.getName());
                record.setAmount(Long.valueOf(dewormingFormActivity.getRate()));
                recordRepo.save(record);
            }
        }
    }

    private void checkAndAddIncentivesForAhd(AHDForm ahdFormEntry) {
        IncentiveActivity ahdFormActivity = incentivesRepo.findIncentiveMasterByNameAndGroup(ahdFormEntry.getFormType(), "MEETINGLEVEL");

        if (ahdFormActivity != null) {
            IncentiveActivityRecord record = recordRepo
                    .findRecordByActivityIdCreatedDateBenId(ahdFormActivity.getId(), Timestamp.valueOf(ahdFormEntry.getAhdDate().toString()), null);
            if (record == null) {
                record = new IncentiveActivityRecord();
                record.setActivityId(ahdFormActivity.getId());
                record.setCreatedDate(Timestamp.valueOf(ahdFormEntry.getAhdDate().toString()));
                record.setCreatedBy(ahdFormEntry.getCreatedBy());
                record.setStartDate(Timestamp.valueOf(ahdFormEntry.getAhdDate().toString()));
                record.setEndDate(Timestamp.valueOf(ahdFormEntry.getAhdDate().toString()));
                record.setUpdatedDate(Timestamp.valueOf(ahdFormEntry.getAhdDate().toString()));
                record.setUpdatedBy(ahdFormEntry.getCreatedBy());
                record.setAshaId(Math.toIntExact(ahdFormEntry.getUserId()));
                record.setName(ahdFormActivity.getName());
                record.setAmount(Long.valueOf(ahdFormActivity.getRate()));
                recordRepo.save(record);
            }
        }
    }

}
