package com.iemr.flw.service.impl;

import com.iemr.flw.domain.iemr.DiseaseControl;
import com.iemr.flw.dto.iemr.DiseaseControlDTO;
import com.iemr.flw.repo.iemr.DiseaseControlRepo;
import com.iemr.flw.service.DiseaseControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiseaseControlServiceImpl implements DiseaseControlService {
    @Autowired
    private DiseaseControlRepo diseaseControlRepo;

    @Override
    public String save(DiseaseControlDTO diseaseControlDTO) {
        for(DiseaseControl diseaseControlData: diseaseControlDTO.getDiseaseControlList()){
            if(diseaseControlRepo.findByBenId(diseaseControlData.getBenId()).isPresent()){
                return   update(diseaseControlData);
            }else {
                diseaseControlRepo.save(saveData(diseaseControlData));
                return   "Data add successfully";

            }
        }
        return "Fail";


    }

    @Override
    public List<DiseaseControl> getAll() {
        return diseaseControlRepo.findAll();
    }

    private DiseaseControl  saveData(DiseaseControl diseaseControlDTO){
        DiseaseControl diseaseControl = new DiseaseControl();
        diseaseControl.setBenId(diseaseControlDTO.getBenId());
        diseaseControl.setCaseDate(diseaseControlDTO.getCaseDate()); // Added
        diseaseControl.setCaseStatus(diseaseControlDTO.getCaseStatus());
        diseaseControl.setSymptoms(diseaseControlDTO.getSymptoms());
        diseaseControl.setReferredTo(diseaseControlDTO.getReferredTo()); // Added
        diseaseControl.setOtherReferredTo(diseaseControlDTO.getOtherReferredTo());
        diseaseControl.setRemarks(diseaseControlDTO.getRemarks());
        diseaseControl.setStatus(diseaseControlDTO.getStatus());
        diseaseControl.setBodyPart(diseaseControlDTO.getBodyPart());
        diseaseControl.setSufferingFromFilariasis(diseaseControlDTO.getSufferingFromFilariasis());
        diseaseControl.setOtherStatus(diseaseControlDTO.getOtherStatus());
        diseaseControl.setHomeVisitDate(diseaseControlDTO.getHomeVisitDate());
        diseaseControl.setMedicineSideEffect(diseaseControlDTO.getMedicineSideEffect());
        diseaseControl.setDiseaseTypeId(diseaseControlDTO.getDiseaseTypeId());

        Map<String, Object> diseaseRelatedData = new HashMap<>();
        diseaseRelatedData.put("malariaCaseCount", diseaseControlDTO.getMalariaCaseCount());
        diseaseRelatedData.put("malariaCaseStatusDate", diseaseControlDTO.getMalariaCaseStatusDate());

        diseaseControl.setFollowUpPoint(diseaseControlDTO.getFollowUpPoint());
        diseaseControl.setFollowUpDate(diseaseControlDTO.getFollowUpDate());
        diseaseControl.setLeprosyStatusDate(diseaseControlDTO.getLeprosyStatusDate());

        diseaseControl.setOtherFields(diseaseRelatedData.toString());


        return diseaseControl;

    }
    private String  update(DiseaseControl diseaseControlDTO){
        return  diseaseControlRepo.findByBenId(diseaseControlDTO.getBenId()).map(diseaseControl -> {
            diseaseControl.setCaseDate(diseaseControlDTO.getCaseDate()); // Added
            diseaseControl.setCaseStatus(diseaseControlDTO.getCaseStatus());
            diseaseControl.setSymptoms(diseaseControlDTO.getSymptoms());
            diseaseControl.setMalariaCaseCount(diseaseControlDTO.getMalariaCaseCount());
            diseaseControl.setReferredTo(diseaseControlDTO.getReferredTo()); // Added
            diseaseControl.setOtherReferredTo(diseaseControlDTO.getOtherReferredTo());
            diseaseControl.setMalariaCaseStatusDate(diseaseControlDTO.getMalariaCaseStatusDate()); // Added
            diseaseControl.setRemarks(diseaseControlDTO.getRemarks());
            diseaseControl.setFollowUpPoint(diseaseControlDTO.getFollowUpPoint());
            diseaseControl.setFollowUpDate(diseaseControlDTO.getFollowUpDate());
            diseaseControl.setFollowUpDate(diseaseControlDTO.getFollowUpDate());
            diseaseControl.setStatus(diseaseControlDTO.getStatus());
            diseaseControl.setBodyPart(diseaseControlDTO.getBodyPart());
            diseaseControl.setSufferingFromFilariasis(diseaseControlDTO.getSufferingFromFilariasis());
            diseaseControl.setOtherStatus(diseaseControlDTO.getOtherStatus());
            diseaseControl.setHomeVisitDate(diseaseControlDTO.getHomeVisitDate());
            diseaseControl.setLeprosyStatusDate(diseaseControlDTO.getLeprosyStatusDate());
            diseaseControl.setMedicineSideEffect(diseaseControlDTO.getMedicineSideEffect());
            diseaseControl.setDiseaseTypeId(diseaseControlDTO.getDiseaseTypeId());
            diseaseControlRepo.save(diseaseControl);
            
            return "Data update successfully";

        }).orElseThrow(()->new RuntimeException("Data not found"));

    }
}
