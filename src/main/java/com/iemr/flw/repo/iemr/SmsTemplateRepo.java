package com.iemr.flw.repo.iemr;

import com.iemr.flw.dto.iemr.SmsRequestOBJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SmsTemplateRepo  extends JpaRepository<SmsRequestOBJ,Integer> {
    @Query(value = " SELECT SMSTemplateID FROM db_iemr.m_smstemplate "
            + " WHERE SMSTypeID = :smsTypeID AND deleted<>true ", nativeQuery = true)
    public ArrayList<Integer> getSMSTemplateID(@Param("smsTypeID") Integer smsTypeID);

    @Query(value = " SELECT SMSTypeID FROM db_iemr.m_smstype " + " WHERE SMSType = :smsType ", nativeQuery = true)
    public Integer getSMSTypeID(@Param("smsType") String smsType);
}
