package com.iemr.flw.service.impl;

import com.iemr.flw.domain.iemr.M_User;
import com.iemr.flw.domain.iemr.V_Userservicerolemapping;
import com.iemr.flw.repo.iemr.AshaByVilageRepo;
import com.iemr.flw.repo.iemr.EmployeeMasterRepo;
import com.iemr.flw.service.AshaSuperVisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AshaSuperVisorImpl implements AshaSuperVisorService {
    @Autowired
    private AshaByVilageRepo ashaByVilageRepo;
    @Override
    public List<V_Userservicerolemapping> getAllAsha(Integer serviceProviderID,String vilageName) {
        ArrayList<V_Userservicerolemapping> getData = (ArrayList<V_Userservicerolemapping>) ashaByVilageRepo
                .getAllRoleOfProvider(serviceProviderID);
        ArrayList<V_Userservicerolemapping> mappedRoles = new ArrayList<>();

        if (getData != null) {
            for (V_Userservicerolemapping mapping : getData) {
                if (mapping.getVillageidDb() != null) {
                    mapping.setVillageID(mapping.getVillageidDb().split(","));
                } else {
                    mapping.setVillageID(new String[0]);
                }

                if (mapping.getVillageNameDb() != null) {
                    mapping.setVillageName(mapping.getVillageNameDb().split(","));
                } else {
                    mapping.setVillageName(new String[0]);
                }
                if (mapping.getServiceID()!=null) {
                    mapping.setBlockID(mapping.getBlockID());
                    mapping.setBlockName(mapping.getBlockName());
                    mapping.setVillageID(mapping.getVillageID());
                    mapping.setVillageName(mapping.getVillageName());
                    if(null != mapping.getIsSanjeevani())
                        mapping.setIsSanjeevani(mapping.getIsSanjeevani());
                } else {
                    mapping.setBlockID(null);
                    mapping.setBlockName(null);
                    mapping.setVillageID(null);
                    mapping.setVillageName(null);
                    mapping.setVillageidDb(null);
                    mapping.setVillageNameDb(null);
                    mapping.setIsSanjeevani(false);

                }
                mappedRoles.add(mapping);
            }
        }
        return mappedRoles.stream().filter(v_userservicerolemapping -> v_userservicerolemapping.getWorkingLocationID().equals(vilageName)).collect(Collectors.toList());
    }



}
