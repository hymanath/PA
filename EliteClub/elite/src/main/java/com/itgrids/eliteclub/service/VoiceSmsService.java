package com.itgrids.eliteclub.service;

import java.util.List;

public interface VoiceSmsService
{
	public void sendVoiceSmsThread(Integer audioFileId,List<String> mobileNos,Thread threadName, List<?> voiceIds);
}
