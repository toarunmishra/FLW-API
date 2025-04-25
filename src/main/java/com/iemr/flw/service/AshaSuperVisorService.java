package com.iemr.flw.service;

import com.iemr.flw.domain.iemr.M_User;
import com.iemr.flw.domain.iemr.V_Userservicerolemapping;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AshaSuperVisorService {
    public List<V_Userservicerolemapping> getAllAsha(Integer serviceProviderID,String vilageName);
}
