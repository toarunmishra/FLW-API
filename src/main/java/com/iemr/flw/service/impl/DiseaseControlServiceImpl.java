package com.iemr.flw.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iemr.flw.domain.iemr.*;
import com.iemr.flw.dto.iemr.*;
import com.iemr.flw.repo.iemr.*;
import com.iemr.flw.service.DiseaseControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiseaseControlServiceImpl implements DiseaseControlService {
    @Autowired
    private DiseaseScreeningRepo diseaseScreeningRepo;
    @Autowired
    private IncentiveRecordRepo recordRepo;
    @Autowired
    private UserServiceRoleRepo userRepo;

    @Autowired
    private IncentivesRepo incentivesRepo;



    @Override
    public String save(DiseaseControlDTO diseaseControlDTO) {
        for (DiseaseScreening diseaseScreeningData : diseaseControlDTO.getDiseaseScreeningList()) {
            if (diseaseScreeningRepo.findByBenId(diseaseScreeningData.getBenId()).isPresent()) {
                return update(diseaseScreeningData);
            } else {

                diseaseScreeningRepo.save(saveData(diseaseScreeningData));
                return "Data add successfully";

            }
        }
        return "Fail";


    }

    @Override
    public String saveMalaria(MalariaDTO diseaseControlDTO) {
        for (DiseaseScreeningDTO diseaseControlData : diseaseControlDTO.getMalariaLists()) {
            if (diseaseScreeningRepo.findByBenId(diseaseControlData.getBenId()).isPresent()) {
                return updateDisease(diseaseControlData);
            } else {
                if(diseaseControlDTO.getUserId()!=null){
                    diseaseControlData.setUserID(diseaseControlData.getUserID());
                }
                diseaseScreeningRepo.save(saveDisease(diseaseControlData));
                return "Data add successfully";

            }
        }
        return "Fail";
    }


    @Override
    public String saveKalaAzar(KalaAzarDTO diseaseControlDTO) {
        for (DiseaseScreeningDTO diseaseControlData : diseaseControlDTO.getKalaAzarLists()) {
            if (diseaseScreeningRepo.findByBenId(diseaseControlData.getBenId()).isPresent()) {
                return updateDisease(diseaseControlData);
            } else {
                if(diseaseControlDTO.getUserId()!=null){
                    diseaseControlData.setUserID(diseaseControlData.getUserID());
                }
                diseaseScreeningRepo.save(saveDisease(diseaseControlData));
                return "Data add successfully";

            }
        }
        return "Fail";

    }

    @Override
    public String saveAES(AesJeDTO diseaseControlDTO) {
        for (DiseaseScreeningDTO diseaseControlData : diseaseControlDTO.getAesJeLists()) {
            if (diseaseScreeningRepo.findByBenId(diseaseControlData.getBenId()).isPresent()) {
                return updateDisease(diseaseControlData);
            } else {
                if(diseaseControlDTO.getUserId()!=null){
                    diseaseControlData.setUserID(diseaseControlData.getUserID());
                }
                diseaseScreeningRepo.save(saveDisease(diseaseControlData));
                return "Data add successfully";

            }
        }
        return "Fail";
    }

    @Override
    public String saveFilaria(FilariaDTO diseaseControlDTO) {
        for (DiseaseScreeningDTO diseaseControlData : diseaseControlDTO.getFilariaLists()) {
            if (diseaseScreeningRepo.findByBenId(diseaseControlData.getBenId()).isPresent()) {
                return updateDisease(diseaseControlData);
            } else {
                if(diseaseControlDTO.getUserId()!=null){
                    diseaseControlData.setUserID(diseaseControlData.getUserID());
                }
                diseaseScreeningRepo.save(saveDisease(diseaseControlData));
                return "Data add successfully";

            }
        }
        return "Fail";
    }

    @Override
    public String saveLeprosy(LeprosyDTO diseaseControlDTO) {
        for (DiseaseScreeningDTO diseaseControlData : diseaseControlDTO.getLeprosyLists()) {
            if (diseaseScreeningRepo.findByBenId(diseaseControlData.getBenId()).isPresent()) {
                return updateDisease(diseaseControlData);
            } else {
                if(diseaseControlDTO.getUserId()!=null){
                    diseaseControlData.setUserID(diseaseControlData.getUserID());
                }
                diseaseScreeningRepo.save(saveDisease(diseaseControlData));
                return "Data add successfully";

            }
        }
        return "Fail";
    }

    @Override
    public Object getAll(GetDiseaseRequestHandler getDiseaseRequestHandler) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Fetch and filter records
        List<DiseaseScreening> filteredList = diseaseScreeningRepo.findAll().stream()
                .filter(diseaseScreening -> diseaseScreening.getDiseaseTypeID() == getDiseaseRequestHandler.getDiseaseTypeID())
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            return Collections.singletonMap("message", "Data not found");
        }

        // Convert to DTO list
        List<DiseaseScreeningDTO> dtoList = filteredList.stream().map(diseaseScreening -> {
            DiseaseScreeningDTO dto = new DiseaseScreeningDTO();

            // Map direct fields
            dto.setId(diseaseScreening.getId());
            dto.setBenId(diseaseScreening.getBenId());
            dto.setHouseHoldDetailsId(diseaseScreening.getHouseHoldDetailsId());
            dto.setScreeningDate(diseaseScreening.getScreeningDate());
            dto.setBeneficiaryStatus(diseaseScreening.getBeneficiaryStatus());
            dto.setDateOfDeath(diseaseScreening.getDateOfDeath());
            dto.setPlaceOfDeath(diseaseScreening.getPlaceOfDeath());
            dto.setOtherPlaceOfDeath(diseaseScreening.getOtherPlaceOfDeath());
            dto.setReasonForDeath(diseaseScreening.getReasonForDeath());
            dto.setOtherReasonForDeath(diseaseScreening.getOtherReasonForDeath());
            dto.setCaseStatus(diseaseScreening.getCaseStatus());
            dto.setRapidDiagnosticTest(diseaseScreening.getRapidDiagnosticTest());
            dto.setDateOfRdt(diseaseScreening.getDateOfRdt());
            dto.setSlideTestPf(diseaseScreening.getSlideTestPf());
            dto.setSlideTestPv(diseaseScreening.getSlideTestPv());
            dto.setDateOfSlideTest(diseaseScreening.getDateOfSlideTest());
            dto.setSlideNo(diseaseScreening.getSlideNo());
            dto.setReferredTo(diseaseScreening.getReferredTo());
            dto.setOtherReferredFacility(diseaseScreening.getOtherReferredFacility());
            dto.setRemarks(diseaseScreening.getRemarks());
            dto.setDateOfVisitBySupervisor(diseaseScreening.getDateOfVisitBySupervisor());
            dto.setUserID(diseaseScreening.getUserID());
            dto.setDiseaseTypeID(diseaseScreening.getDiseaseTypeID());

            // Parse JSON symptoms
            try {
                if (diseaseScreening.getSymptoms() != null && !diseaseScreening.getSymptoms().isEmpty()) {
                    MalariaSymptomsDTO symptomsDTO = objectMapper.readValue(diseaseScreening.getSymptoms(), MalariaSymptomsDTO.class);

                    // Map symptoms
                    dto.setFeverMoreThanTwoWeeks(symptomsDTO.isFeverMoreThanTwoWeeks());
                    dto.setFluLikeIllness(symptomsDTO.isFluLikeIllness());
                    dto.setShakingChills(symptomsDTO.isShakingChills());
                    dto.setHeadache(symptomsDTO.isHeadache());
                    dto.setMuscleAches(symptomsDTO.isMuscleAches());
                    dto.setTiredness(symptomsDTO.isTiredness());
                    dto.setNausea(symptomsDTO.isNausea());
                    dto.setVomiting(symptomsDTO.isVomiting());
                    dto.setDiarrhea(symptomsDTO.isDiarrhea());
                }
            } catch (Exception e) {
                throw new RuntimeException("Error parsing symptoms JSON for ID: " + diseaseScreening.getId(), e);
            }

            return dto;
        }).collect(Collectors.toList());

        // Return response in required format


        return dtoList;
    }



    private DiseaseScreening saveData(DiseaseScreening diseaseScreeningDTO) {
        DiseaseScreening diseaseScreening = new DiseaseScreening();

        diseaseScreening.setBenId(diseaseScreeningDTO.getBenId());
        diseaseScreening.setScreeningDate(diseaseScreeningDTO.getScreeningDate()); // Mapping caseDate to screeningDate
        diseaseScreening.setCaseStatus(diseaseScreeningDTO.getCaseStatus());
        diseaseScreening.setSymptoms(diseaseScreeningDTO.getSymptoms());
        diseaseScreening.setReferredTo(diseaseScreeningDTO.getReferredTo());
        diseaseScreening.setOtherReferredFacility(diseaseScreeningDTO.getOtherReferredFacility()); // Fixed field name
        diseaseScreening.setRemarks(diseaseScreeningDTO.getRemarks());
        return diseaseScreening;

    }

    private String updateDisease(DiseaseScreeningDTO requestData) {
        return diseaseScreeningRepo.findByBenId(requestData.getBenId()).map(diseaseScreening -> {
            diseaseScreening.setBenId(requestData.getBenId());
            diseaseScreening.setHouseHoldDetailsId(requestData.getHouseHoldDetailsId());
            diseaseScreening.setScreeningDate(requestData.getScreeningDate());
            diseaseScreening.setBeneficiaryStatus(requestData.getBeneficiaryStatus());
            diseaseScreening.setDateOfDeath(requestData.getDateOfDeath());
            diseaseScreening.setPlaceOfDeath(requestData.getPlaceOfDeath());
            diseaseScreening.setOtherPlaceOfDeath(requestData.getOtherPlaceOfDeath());
            diseaseScreening.setReasonForDeath(requestData.getReasonForDeath());
            diseaseScreening.setOtherReasonForDeath(requestData.getOtherReasonForDeath());
            diseaseScreening.setDiseaseTypeID(requestData.getDiseaseTypeID());
            if(requestData.getDiseaseTypeID()==1){
                diseaseScreening.setSymptoms(convertSelecteddiseaseScreeningToJson(requestData)); // Convert specific fields to JSON

            }
            diseaseScreening.setCaseStatus(requestData.getCaseStatus());
            diseaseScreening.setRapidDiagnosticTest(requestData.getRapidDiagnosticTest());
            diseaseScreening.setDateOfRdt(requestData.getDateOfRdt());
            diseaseScreening.setSlideTestPf(requestData.getSlideTestPf());
            diseaseScreening.setSlideTestPv(requestData.getSlideTestPv());
            diseaseScreening.setDateOfSlideTest(requestData.getDateOfSlideTest());
            diseaseScreening.setSlideNo(requestData.getSlideNo());
            diseaseScreening.setReferredTo(requestData.getReferredTo());
            diseaseScreening.setOtherReferredFacility(requestData.getOtherReferredFacility());
            diseaseScreening.setRemarks(requestData.getRemarks());
            diseaseScreening.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            diseaseScreening.setDateOfVisitBySupervisor(requestData.getDateOfVisitBySupervisor());
            diseaseScreeningRepo.save(diseaseScreening);
            return "Data update successfully";

        }).orElseThrow(() -> new RuntimeException("Data not found"));
    }


    private DiseaseScreening saveDisease(DiseaseScreeningDTO requestData) {
        DiseaseScreening diseaseScreening = new DiseaseScreening();

        diseaseScreening.setBenId(requestData.getBenId());
        diseaseScreening.setHouseHoldDetailsId(requestData.getHouseHoldDetailsId());
        diseaseScreening.setScreeningDate(requestData.getScreeningDate());
        diseaseScreening.setBeneficiaryStatus(requestData.getBeneficiaryStatus());
        diseaseScreening.setDateOfDeath(requestData.getDateOfDeath());
        diseaseScreening.setPlaceOfDeath(requestData.getPlaceOfDeath());
        diseaseScreening.setOtherPlaceOfDeath(requestData.getOtherPlaceOfDeath());
        diseaseScreening.setReasonForDeath(requestData.getReasonForDeath());
        diseaseScreening.setOtherReasonForDeath(requestData.getOtherReasonForDeath());
        diseaseScreening.setDiseaseTypeID(requestData.getDiseaseTypeID());
        if(requestData.getDiseaseTypeID()==1){
            diseaseScreening.setSymptoms(convertSelecteddiseaseScreeningToJson(requestData)); // Convert specific fields to JSON

        }        
        diseaseScreening.setCaseStatus(requestData.getCaseStatus());
        diseaseScreening.setRapidDiagnosticTest(requestData.getRapidDiagnosticTest());
        diseaseScreening.setDateOfRdt(requestData.getDateOfRdt());
        diseaseScreening.setSlideTestPf(requestData.getSlideTestPf());
        diseaseScreening.setSlideTestPv(requestData.getSlideTestPv());
        diseaseScreening.setDateOfSlideTest(requestData.getDateOfSlideTest());
        diseaseScreening.setSlideNo(requestData.getSlideNo());
        diseaseScreening.setReferredTo(requestData.getReferredTo());
        diseaseScreening.setOtherReferredFacility(requestData.getOtherReferredFacility());
        diseaseScreening.setRemarks(requestData.getRemarks());
        diseaseScreening.setDateOfVisitBySupervisor(requestData.getDateOfVisitBySupervisor());
        diseaseScreening.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        checkAndAddIncentives(diseaseScreening);

        return diseaseScreeningRepo.save(diseaseScreening);
    }




    private String convertSelecteddiseaseScreeningToJson(DiseaseScreeningDTO requestData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> diseaseScreeningMap = new HashMap<>();
            diseaseScreeningMap.put("nausea", requestData.isNausea());
            diseaseScreeningMap.put("diarrhea", requestData.isDiarrhea());
            diseaseScreeningMap.put("tiredness", requestData.isTiredness());
            diseaseScreeningMap.put("vomiting", requestData.isVomiting());
            diseaseScreeningMap.put("headache", requestData.isHeadache());
            diseaseScreeningMap.put("feverMoreThanTwoWeeks", requestData.isFeverMoreThanTwoWeeks());
            diseaseScreeningMap.put("fluLikeIllness", requestData.isFluLikeIllness());
            diseaseScreeningMap.put("shakingChills", requestData.isShakingChills());

            return objectMapper.writeValueAsString(diseaseScreeningMap);
        } catch (Exception e) {
            throw new RuntimeException("Error converting selected diseaseScreening fields to JSON", e);
        }
    }

//    private String updateKalaAzar(KalaAzarData updatedData) {
//        return kalazarControlRepo.findByBenId(updatedData.getBenId()).map(kalaAzarData -> {
//            kalaAzarData.setId(updatedData.getId());
//            kalaAzarData.setBenId(updatedData.getBenId());
//            kalaAzarData.setCaseDate(updatedData.getCaseDate());
//            kalaAzarData.setCaseStatus(updatedData.getCaseStatus());
//            kalaAzarData.setSymptoms(updatedData.getSymptoms());
//            kalaAzarData.setMalariaCaseCount(updatedData.getMalariaCaseCount());
//            kalaAzarData.setReferredTo(updatedData.getReferredTo());
//            kalaAzarData.setKalaAzarCaseStatusDate(updatedData.getKalaAzarCaseStatusDate());
//            kalaAzarData.setRemarks(updatedData.getRemarks());
//            kalaAzarData.setFollowUpPoint(updatedData.getFollowUpPoint());
//            kalaAzarData.setFollowUpDate(updatedData.getFollowUpDate());
//            kalaAzarData.setStatus(updatedData.getStatus());
//            kalaAzarData.setHouseholdId(updatedData.getHouseholdId());
//
//            kalazarControlRepo.save(kalaAzarData);
//
//            return "Data updated successfully";
//        }).orElseThrow(() -> new RuntimeException("Data not found"));
//    }
//    private String updateLeprosy(LeprosyData updateData) {
//        return leprosyControlRepo.findByBenId(updateData.getBenId()).map(leprosyData -> {
//            leprosyData.setId(updateData.getId());
//            leprosyData.setDateOfHomeVisit(updateData.getDateOfHomeVisit());
//            leprosyData.setLeprosyStatus(updateData.getLeprosyStatus());
//            leprosyData.setReferredTo(updateData.getReferredTo());
//            leprosyData.setOther(updateData.getOther());
//            leprosyData.setLeprosyStatusDate(updateData.getLeprosyStatusDate());
//            leprosyData.setTypeOfLeprosy(updateData.getTypeOfLeprosy());
//            leprosyData.setFollowUpDate(updateData.getFollowUpDate());
//            leprosyData.setStatus(updateData.getStatus());
//            leprosyData.setRemark(updateData.getRemark());
//            leprosyData.setDiseaseTypeID(updateData.getDiseaseTypeID());
//            leprosyData.setBenId(updateData.getBenId());
//            leprosyData.setHouseholdId(updateData.getHouseholdId());
//
//
//            leprosyControlRepo.save(leprosyData);
//
//            return "Data updated successfully";
//        }).orElseThrow(() -> new RuntimeException("Data not found"));
//    }
//
//    private String updateAesJe(AesJeData updateData) {
//        return aesJeControlRepo.findByBenId(updateData.getBenId()).map(aesJeData -> {
//            aesJeData.setId(updateData.getId());
//            aesJeData.setCaseDate(updateData.getCaseDate());
//            aesJeData.setAesjeCaseStatus(updateData.getAesjeCaseStatus());
//            aesJeData.setReferredTo(updateData.getReferredTo());
//            aesJeData.setDiseaseTypeID(updateData.getDiseaseTypeID());
//            aesJeData.setBenId(updateData.getBenId());
//            aesJeData.setHouseholdId(updateData.getHouseholdId());
//
//            // Save the updated data (if required, depending on your repo)
//            aesJeControlRepo.save(aesJeData);
//
//            return "Data updated successfully";
//        }).orElseThrow(() -> new RuntimeException("Data not found"));
//    }
//
//    private String updateFilaria(FilariaData updateData) {
//        return filariaControlRepo.findByBenId(updateData.getBenId()).map(filariaData -> {
//            filariaData.setId(updateData.getId());
//            filariaData.setSufferingFromFilariasis(updateData.getSufferingFromFilariasis());
//            filariaData.setWhichPartOfBody(updateData.getWhichPartOfBody());
//            filariaData.setHomeVisitDate(updateData.getHomeVisitDate());
//            filariaData.setDecAndAlbendazoleDoseStatus(updateData.getDecAndAlbendazoleDoseStatus());
//            filariaData.setMedicineSideEffect(updateData.getMedicineSideEffect());
//            filariaData.setOther(updateData.getOther());
//            filariaData.setDiseaseTypeID(updateData.getDiseaseTypeID());
//            filariaData.setBenId(updateData.getBenId());
//            filariaData.setHouseholdId(updateData.getHouseholdId());
//
//            // Save the updated data (if required, depending on your repo)
//            filariaControlRepo.save(filariaData);
//
//            return "Data updated successfully";
//        }).orElseThrow(() -> new RuntimeException("Data not found"));
//    }



    private String update(DiseaseScreening diseaseScreeningDTO) {
        return diseaseScreeningRepo.findByBenId(diseaseScreeningDTO.getBenId()).map(diseaseControl -> {
            diseaseControl.setScreeningDate(diseaseScreeningDTO.getScreeningDate()); // Fixed caseDate -> screeningDate
            diseaseControl.setCaseStatus(diseaseScreeningDTO.getCaseStatus());
            diseaseControl.setSymptoms(diseaseScreeningDTO.getSymptoms());
            diseaseControl.setReferredTo(diseaseScreeningDTO.getReferredTo());
            diseaseControl.setOtherReferredFacility(diseaseScreeningDTO.getOtherReferredFacility()); // Fixed field name
            diseaseControl.setRemarks(diseaseScreeningDTO.getRemarks());

            // Save updated entity
            diseaseScreeningRepo.save(diseaseControl);

            return "Data updated successfully";

        }).orElseThrow(() -> new RuntimeException("Data not found"));
    }


    private void checkAndAddIncentives(DiseaseScreening diseaseScreening) {
        IncentiveActivity diseaseScreeningActivity =
                incentivesRepo.findIncentiveMasterByNameAndGroup("MALARIA_1", "DISEASECONTROL");

        IncentiveActivity diseaseScreeningActivit2 =
                incentivesRepo.findIncentiveMasterByNameAndGroup("MALARIA_2", "DISEASECONTROL");



        if (diseaseScreeningActivity != null) {
            if (diseaseScreening.getDiseaseTypeID() == 1) {
                IncentiveActivityRecord record = recordRepo
                        .findRecordByActivityIdCreatedDateBenId(diseaseScreeningActivity.getId(), diseaseScreening.getCreatedDate(), diseaseScreening.getBenId().longValue());
                if (record == null) {
                    if(Objects.equals(diseaseScreening.getCaseStatus(), "Confirmed Case")){
                        record = new IncentiveActivityRecord();
                        record.setActivityId(diseaseScreeningActivity.getId());
                        record.setCreatedDate(diseaseScreening.getCreatedDate());
                        record.setCreatedBy(diseaseScreening.getCreatedBy());
                        record.setStartDate(diseaseScreening.getCreatedDate());
                        record.setEndDate(diseaseScreening.getCreatedDate());
                        record.setUpdatedDate(diseaseScreening.getCreatedDate());
                        record.setUpdatedBy(diseaseScreening.getCreatedBy());
                        record.setBenId(diseaseScreening.getBenId().longValue());
                        record.setAshaId(diseaseScreening.getUserID());
                        record.setAmount(Long.valueOf(diseaseScreeningActivity.getRate()));
                        recordRepo.save(record);
                    }else {
                        record = new IncentiveActivityRecord();
                        record.setActivityId(diseaseScreeningActivit2.getId());
                        record.setCreatedDate(diseaseScreening.getCreatedDate());
                        record.setCreatedBy(diseaseScreening.getCreatedBy());
                        record.setStartDate(diseaseScreening.getCreatedDate());
                        record.setEndDate(diseaseScreening.getCreatedDate());
                        record.setUpdatedDate(diseaseScreening.getCreatedDate());
                        record.setUpdatedBy(diseaseScreening.getCreatedBy());
                        record.setBenId(diseaseScreening.getBenId().longValue());
                        record.setAshaId(diseaseScreening.getUserID());
                        record.setAmount(Long.valueOf(diseaseScreeningActivit2.getRate()));
                        recordRepo.save(record);
                    }

                }
            }
        }
    }
}
