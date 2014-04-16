package com.itgrids.eliteclub.service.tests;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.itgrids.eliteclub.service.WebserviceHandlerSevice;


@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/app-config.xml"
@ContextConfiguration("/applicationContext.xml")
@ActiveProfiles("dev")
public class SampleServiceTest {
	
	@Autowired
	private WebserviceHandlerSevice webserviceHandlerSeviceImpl;

	@Test
	public void test()
	{
		/*
		System.out.println(webserviceHandlerSeviceImpl);
		webserviceHandlerSeviceImpl.loadObject(2);*/
		String calluid=UUID.randomUUID().toString();
	    String phoneno="9505485043";	    
	    String voiceid="101";
		
		 RestTemplate restTemplate = new RestTemplate();
		 String url="http://103.241.182.18/ConVoxBCT/VoiceApp.php?calluid="+calluid+"&phoneno="+phoneno+"&voiceid="+voiceid;
		
		 String page = restTemplate.getForObject(url, String.class);
		 
		 System.out.println(page);
	}
	
}
