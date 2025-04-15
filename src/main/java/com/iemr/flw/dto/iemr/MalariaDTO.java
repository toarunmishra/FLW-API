package com.iemr.flw.dto.iemr;


import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
public class MalariaDTO {
    Integer userId;
    List<DiseaseMalariaDTO> malariaLists;



}


