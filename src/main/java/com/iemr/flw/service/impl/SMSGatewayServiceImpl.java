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
import com.iemr.flw.service.smsService.SMSGatewayService;
import com.iemr.flw.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
