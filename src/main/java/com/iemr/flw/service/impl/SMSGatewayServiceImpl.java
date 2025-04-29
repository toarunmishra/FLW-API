/*
* AMRIT – Accessible Medical Records via Integrated Technology 
* Integrated EHR (Electronic Health Records) Solution 
*
* Copyright (C) "Piramal Swasthya Management and Research Institute" 
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.flw.service.impl;
import com.google.gson.Gson;
import com.iemr.flw.service.smsService.SMSGatewayService;
import com.iemr.flw.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@PropertySource("classpath:application.properties")
public class SMSGatewayServiceImpl implements SMSGatewayService {
	@Value("${sendSMSUrl}")
	private String sendSMSUrl;
	@Value("${welcome}")
	private String welcome;
	@Value("${namaste}")
	private String namaste;



	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private CookieUtil cookieUtil;
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());



	@Override
	public int sendNamasteSMS(Long benRegID, String createdBy, String Authorization) {

		return 0;
	}

	@Override
	public int sendWelcomeSMS(Long benRegID, String createdBy, String Authorization) {
		return 0;
	}

	@Override
	public String createSMSRequest(String smsType, Long benRegID) {
//		SmsRequestOBJ obj = null;
//		ArrayList<SmsRequestOBJ> objList = new ArrayList<>();
//
//		int smsTypeID;
//
//		switch (smsType) {
//			case "schedule":
//				smsTypeID = tCRequestModelRepo.getSMSTypeID(schedule);
//				break;
//			case "cancel":
//				smsTypeID = tCRequestModelRepo.getSMSTypeID(cancel);
//				break;
//			case "reSchedule":
//				smsTypeID = tCRequestModelRepo.getSMSTypeID(reSchedule);
//				break;
//			default:
//				smsTypeID = 0;
//		}
//
//		if (smsTypeID != 0) {
//			obj = new SmsRequestOBJ();
//			ArrayList<Integer> smsTemplateID = tCRequestModelRepo.getSMSTemplateID(smsTypeID);
//			if (smsTemplateID != null && smsTemplateID.size() == 1)
//				obj.setSmsTemplateID(smsTemplateID.get(0));
//			else {
//				obj.setSmsTemplateID(null);
//				logger.info("Multiple SMS template created for same sms type");
//			}
//			obj.setBeneficiaryRegID(benRegID);
//			obj.setSpecializationID(specializationID);
//			obj.setSmsType(smsType);
//			obj.setCreatedBy(createdBy);
//			obj.setTcDate(tcDate);
//			obj.setTcPreviousDate(tcPreviousDate);
//
//			objList.add(obj);
//		}
//		if (obj != null && obj.getSmsTemplateID() != null)
//			return new Gson().toJson(objList);
//		else
			return null;
	}
}
