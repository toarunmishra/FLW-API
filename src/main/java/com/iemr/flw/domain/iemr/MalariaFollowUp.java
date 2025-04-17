package com.iemr.flw.domain.iemr;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "malaria_follow_up", schema = "db_iemr")
@Data
public class MalariaFollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ben_id", nullable = false)
    private Long benId;

    @Column(name = "houseHoldDetailsId", nullable = false)
    private Long houseHoldDetailsId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "diseaseId", nullable = false)
    private Long diseaseId;

    @Column(name = "date_of_diagnosis", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfDiagnosis;

    @Column(name = "treatment_start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date treatmentStartDate;

    @Column(name = "treatment_given", nullable = false)
    private String treatmentGiven;

    @Column(name = "treatment_days")
    private Boolean day;

    @Column(name = "treatment_completion_date")
    @Temporal(TemporalType.DATE)
    private Date treatmentCompletionDate;

    @Column(name = "referral_date")
    @Temporal(TemporalType.DATE)
    private Date referralDate;
}
