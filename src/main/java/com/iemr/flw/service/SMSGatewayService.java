package com.iemr.flw.service;

public interface SMSGatewayService {
    public int smsSenderGateway(String smsType, Long benRegID, String createdBy, String tcDate, String tcPreviousDate, String Authorization);

    public String createSMSRequest(String smsType, Long benRegID, String createdBy, String tcDate, String tcPreviousDate);

    public String sendSMS(String request, String Authorization);
}
