package com.iemr.flw.repo.iemr;

import com.iemr.flw.domain.iemr.M_User;
import com.iemr.flw.domain.iemr.V_Userservicerolemapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AshaByVilageRepo extends JpaRepository<V_Userservicerolemapping,Integer> {
    @Query("SELECT u FROM V_Userservicerolemapping u where u.serviceProviderID = :serviceProviderID ORDER By u.userName")
    ArrayList<V_Userservicerolemapping> getAllRoleOfProvider(@Param("serviceProviderID") Integer serviceProviderID);
}