package com.iemr.flw.repo.iemr;

import com.iemr.flw.domain.iemr.M_UserDemographics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMasterUserDemographicRepo extends JpaRepository<M_UserDemographics, Integer> {
    M_UserDemographics findByUserID(Integer userID);

}
