package com.iemr.flw.service.impl;

import com.iemr.flw.dto.iemr.UserServiceRoleDTO;
import com.iemr.flw.repo.iemr.UserServiceRoleRepo;
import com.iemr.flw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserServiceRoleRepo userServiceRoleRepo;

    public UserServiceRoleDTO getUserDetail(Integer userId) {
        logger.info("calling getUserRole for userId: " + userId);
        UserServiceRoleDTO userRole = userServiceRoleRepo.getUserRole(userId).get(0);
        return userRole;
    }

    @Override
    public Object getAllUser(Integer villageId) {
        return userServiceRoleRepo.findAll().stream()
                .filter(userServiceRole -> {
                    String villageIdsStr = userServiceRole.getVillageid(); // e.g., "1,2,3"
                    if (villageIdsStr == null || villageIdsStr.isEmpty()) {
                        return false;
                    }
                    return Arrays.asList(villageIdsStr.split(","))
                            .contains(String.valueOf(villageId));
                });
    }
}
