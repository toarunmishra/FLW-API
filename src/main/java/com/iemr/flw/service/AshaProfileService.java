package com.iemr.flw.service;

import com.iemr.flw.domain.iemr.AshaWorker;
import com.iemr.flw.dto.iemr.AshaWorkerDTO;
import org.springframework.stereotype.Service;

@Service
public interface AshaProfileService {
    AshaWorker saveEditData(AshaWorkerDTO ashaWorkerRequest);
    AshaWorker getProfileData(Integer employeeId);

}
