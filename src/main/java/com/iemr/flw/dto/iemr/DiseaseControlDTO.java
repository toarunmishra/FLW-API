package com.iemr.flw.dto.iemr;

import com.iemr.flw.domain.iemr.DiseaseScreening;
import lombok.Data;

import java.util.List;

@Data
public class DiseaseControlDTO {
    Integer userId;
    List<DiseaseScreening> diseaseScreeningList;




}

