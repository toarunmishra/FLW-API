package com.iemr.flw.dto.iemr;

import com.iemr.flw.domain.iemr.DiseaseControl;
import lombok.Data;
import org.json.JSONObject;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Data
public class DiseaseControlDTO {
    Integer userId;
    List<DiseaseControl> diseaseControlList;




}

