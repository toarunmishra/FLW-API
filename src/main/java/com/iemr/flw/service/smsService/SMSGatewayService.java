
package com.iemr.flw.service.smsService;

import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface SMSGatewayService {
	public int sendNamasteSMS(Long benRegID, String createdBy, String Authorization);
	public int sendWelcomeSMS(Long benRegID, String createdBy, String Authorization);

	public String createSMSRequest(String smsType, Long benRegID);


}
