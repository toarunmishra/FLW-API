package com.iemr.flw.dto.iemr;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
public class AshaWorkerDTO {

    private Long id;

    private String name;

    private String village;

    private Integer employeeId;  // Changed from Integer to String

    private LocalDate dob; // Changed from String to LocalDate


    private String mobileNumber;

    private String alternateMobileNumber;

    private String fatherOrSpouseName;

    private LocalDate dateOfJoining; // Changed from String to LocalDate


    private String bankAccount;

    private String ifsc;


    private Integer populationCovered;


    private String choName;


    private String choMobile;


    private String awwName;


    private String awwMobile;


    private String anm1Name;


    private String anm1Mobile;


    private String anm2Name;


    private String anm2Mobile;


    private String abhaNumber;

    private String ashaHouseholdRegistration;

    private String ashaFamilyMember;

    private Integer ProviderServiceMapID;

    private String profileImage;

    private Boolean isFatherOrSpouse;
    private String supervisorName;
    private String supervisorMobile;

}
