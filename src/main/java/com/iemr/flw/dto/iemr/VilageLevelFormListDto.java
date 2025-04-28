package com.iemr.flw.dto.iemr;

import lombok.Data;

import java.sql.Date;

@Data
public class VilageLevelFormListDto {
    private String formType;
    private Integer userId;
    private Date date;
    private String place;
    private int participantCount;
    private String imageUrl1; // Comma separated URLs or Base64 if needed
    private String imageUrl2; // Comma separated URLs or Base64 if needed
    private String  createdBy;
    private boolean dewormingRound;
    private Integer age;


}

