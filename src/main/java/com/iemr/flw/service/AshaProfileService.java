package com.iemr.flw.service;

import com.iemr.flw.domain.iemr.AshaWorker;
import com.iemr.flw.dto.iemr.AshaWorkerDTO;
import com.iemr.flw.utils.exception.IEMRException;
import org.springframework.stereotype.Service;

@Service
public interface AshaProfileService {
    AshaWorker saveEditData(AshaWorkerDTO ashaWorkerRequest);
    AshaWorker getProfileData(String jwtToken) throws IEMRException;

}
