package com.iemr.flw.domain.iemr;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "screening_screening",schema = "db_iemr")
public class DiseaseScreening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "ben_id")
    private BigInteger benId;

    @Column(name = "houseHoldDetailsId")  // Ensure case matches exactly
    private BigInteger houseHoldDetailsId;

    @Column(name = "screening_date")
    private Date screeningDate;

    @Column(name = "beneficiary_status")
    private String beneficiaryStatus;

    @Column(name = "date_of_death")
    private Date dateOfDeath;

    @Column(name = "place_of_death")
    private String placeOfDeath;

    @Column(name = "other_place_of_death")
    private String otherPlaceOfDeath;

    @Column(name = "reason_for_death")
    private String reasonForDeath;

    @Column(name = "other_reason_for_death")
    private String otherReasonForDeath;

    @Column(name = "symptoms", columnDefinition = "TEXT")
    private String symptoms;

    @Column(name = "case_status")
    private String caseStatus;

    @Column(name = "rapid_diagnostic_test")
    private String rapidDiagnosticTest;

    @Column(name = "date_of_rdt")
    private Date dateOfRdt;

    @Column(name = "slide_test_pf")
    private String slideTestPf;

    @Column(name = "slide_test_pv")
    private String slideTestPv;

    @Column(name = "date_of_slide_test")
    private Date dateOfSlideTest;

    @Column(name = "slide_no")
    private String slideNo;

    @Column(name = "referred_to")  // Change to String (matches VARCHAR)
    private Integer referredTo;

    @Column(name = "other_referred_facility")
    private String otherReferredFacility;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "date_of_visit_by_supervisor")
    private Date dateOfVisitBySupervisor;

    @Column(name = "diseaseTypeID")  // Ensure exact match
    private Integer diseaseTypeID;

    @Column(name = "userID")  // Ensure exact match
    private Integer userID;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "created_by")
    private String createdBy;


}
