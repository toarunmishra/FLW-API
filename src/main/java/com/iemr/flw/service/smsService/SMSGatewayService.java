
package com.iemr.flw.service.smsService;

import java.util.List;


public interface SMSGatewayService {
	public int sendNamasteSMS(Long benRegID, String createdBy, String Authorization);
	public int sendWelcomeSMS(Long benRegID, String createdBy, String Authorization);


}
