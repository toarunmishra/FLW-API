package com.iemr.flw.dto.iemr;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public  class DiseaseScreeningDTO {
    private BigInteger id;
    private BigInteger benId;
    private BigInteger houseHoldDetailsId;
    private Date screeningDate;
    private String beneficiaryStatus;
    private Integer beneficiaryStatusId;
    private Date dateOfDeath;
    private String placeOfDeath;
    private String otherPlaceOfDeath;
    private String reasonForDeath;
    private String otherReasonForDeath;
    private String caseStatus;
    private String rapidDiagnosticTest;
    private Date dateOfRdt;
    private String slideTestPf;
    private String slideTestPv;
    private Date dateOfSlideTest;
    private String slideNo;
    private Integer referredTo;
    private String otherReferredFacility;
    private String remarks;
    private Date dateOfVisitBySupervisor;
    private boolean feverMoreThanTwoWeeks;
    private boolean fluLikeIllness;
    private boolean shakingChills;
    private boolean headache;
    private boolean muscleAches;
    private boolean tiredness;
    private boolean nausea;
    private boolean vomiting;
    private boolean diarrhea;
    private Integer userID;
    private Integer diseaseTypeID;
    private String createdBy;
    private String referToName;
}
