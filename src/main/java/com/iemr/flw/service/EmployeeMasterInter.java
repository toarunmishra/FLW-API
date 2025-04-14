package com.iemr.flw.service;

import com.iemr.flw.domain.iemr.M_User;
import com.iemr.flw.domain.iemr.M_UserDemographics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeMasterInter {
    public M_User getUserDetails(Integer userID);
    public M_UserDemographics getUserDemographicsDetails(Integer userId);

    List<M_User> getAllUsers();


}
