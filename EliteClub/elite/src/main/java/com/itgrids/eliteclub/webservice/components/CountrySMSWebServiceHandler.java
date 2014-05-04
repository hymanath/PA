package com.itgrids.eliteclub.webservice.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itgrids.eliteclub.dto.BoothPollingUpdatesVO;
import com.itgrids.eliteclub.service.ISmsWebServiceHandler;


@RestController
public class CountrySMSWebServiceHandler {

	 
	public static final	Logger LOG=LogManager.getLogger(WebServiceHandler.class);
		@Autowired
		private ISmsWebServiceHandler smsWebServiceHandlerImpl;
	    
	   /* @RequestMapping(value="/getBoothPercentages",method=RequestMethod.GET)
	    public String sendMsgToContatcts(@RequestParam(value="mobileNumber") String mobileNumber,@RequestParam(value="message") String message, @RequestParam(value="receivedon") String receivedDate) {
	        //model.addAttribute("name", name);
	    	StringBuilder string1 =new  StringBuilder();
	    	string1.append("rest to mobileNumber ");
	    	string1.append(mobileNumber);
	    	string1.append("message  ");
	    	string1.append(message);
	    	string1.append(" and receivedDate " );
	    	string1.append(receivedDate);	    	
	    	log.debug(string1);
	    	
	    	
	    	
	        return "sucess";
	    }*/
		
		
	    @RequestMapping(value="/getBoothPercentages",method=RequestMethod.GET)

	    public String sendMsgToContatcts(@RequestParam("mobilenumber") String mobileNumber,@RequestParam("message") String message, @RequestParam("receivedon") String receivedDate) {
	        //model.addAttribute("name", name);
			String responseCode="100";
	    	StringBuilder string1 =new  StringBuilder();
	    	string1.append("rest to mobileNumber ");
	    	string1.append(mobileNumber);
	    	string1.append("message  ");
	    	string1.append(message);
	    	string1.append(" and receivedDate " );
	    	string1.append(receivedDate);	    	
	    	LOG.debug(string1);
	    	try {
	    	BoothPollingUpdatesVO inputVo = new BoothPollingUpdatesVO();
	    	inputVo.setMessage(message);
	    	inputVo.setMobileNumber(mobileNumber);
	    	inputVo.setRecievedDate(getDateFromString(receivedDate));
	    	
	    	
	    	 responseCode=(String)smsWebServiceHandlerImpl.saveBoothPollingData(inputVo);
	    	}catch (ParseException e) {
	    		LOG.error("Date Format Exception "+e.getMessage());
				return "105";
			}
	    	catch (Exception e) {
				LOG.error("error Occured at booth Pooling Updates "+e);
				return "104";
			} 
	        return responseCode;
	    }
	public  Date getDateFromString(String date) throws ParseException  {
		//String date="05/23/89 12:12:12 AM";
		Date dt=null;
	
		String dateFormat="MM/dd/yy HH:mm:ss a";
		  SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		 dt= format.parse(date);
		 System.out.println(dt);
		 LOG.debug(dt);
		
		return dt;
	}

}
