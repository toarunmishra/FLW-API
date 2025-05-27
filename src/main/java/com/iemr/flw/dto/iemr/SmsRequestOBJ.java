
package com.iemr.flw.dto.iemr;

import lombok.Data;

@Data
public class SmsRequestOBJ {
	private Long beneficiaryRegID;
	private Integer smsTemplateID;
	private Integer specializationID;
	private String smsTypeTM;
	private String createdBy;
	private String tcDate;
	private String tcPreviousDate;

}
