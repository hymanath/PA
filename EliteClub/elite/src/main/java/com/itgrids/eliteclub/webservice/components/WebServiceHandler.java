package com.itgrids.eliteclub.webservice.components;


import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerAdapter;

import com.itgrids.eliteclub.dto.BaseObject;
import com.itgrids.eliteclub.dto.CategoryResponseVo;
import com.itgrids.eliteclub.dto.UserContactsInputVO;
import com.itgrids.eliteclub.service.WebserviceHandlerSevice;
import com.itgrids.eliteclub.service.impl.SmsThreadClass;

@RestController
public class WebServiceHandler { 
	Logger log=LogManager.getLogger(WebServiceHandler.class);
	
	@Autowired
	private WebserviceHandlerSevice webserviceHandlerSevice;
	
	@Autowired
	SmsThreadClass smsThreadClass;
    @RequestMapping("/welcome")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        smsThreadClass.activeteThread("123456",1,1);
        return "helloworld";
    }
/*    
    @RequestMapping(value="/sendMsgToContacts/{imeiNo}/{fileId}/{userId}",method=RequestMethod.GET,produces="application/json")
    public String sendMsgToContatcts(@PathVariable(value="imeiNo") String imeiNo,@PathVariable(value="fileId") String fileId,@PathVariable(value="userId") int userId,  Model model) {
        //model.addAttribute("name", name);
    	StringBuilder string1 =new  StringBuilder();
    	string1.append("request to send msg and voice sms to imei no ");
    	string1.append(imeiNo);
    	string1.append("and user id is  ");
    	string1.append(userId);
    	string1.append(" and file ID is   " );
    	string1.append(fileId);
    	
    	log.debug(string1);
        smsThreadClass.activeteThread(imeiNo,userId,Integer.valueOf(fileId));
        return "sucess";
    }*/
    @RequestMapping(value="/sendMsgToContacts",method=RequestMethod.POST,produces="application/json",consumes="application/json")
    public String sendMsgToContatcts( @RequestBody UserContactsInputVO  inputVo,  Model model) {
        //model.addAttribute("name", name);
    	StringBuilder string1 =new  StringBuilder();
    	string1.append("request to send msg and voice sms to imei no ");
    	string1.append(inputVo.getImeiNo());
    	string1.append("and user id is  ");
    	string1.append(inputVo.getUserId());
    	string1.append(" and file ID is   " );
    	string1.append(inputVo.getFileIds());
    	
    	log.debug(string1);
        smsThreadClass.activeteThread(inputVo);
        return "sucess";
    }

	


	@RequestMapping(value="/getBase",method=RequestMethod.GET,produces={"application/json"})
    public @ResponseBody BaseObject hello1(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        
        BaseObject bobj= new BaseObject();
        bobj.setId(1);
        bobj.setName(name);
        HandlerAdapter bu =null;
        return bobj;
    }
    
    //get phonenumbers and save into database
    
    
    
    @RequestMapping(value="/getCategories/{imeiNo}",method=RequestMethod.GET,produces={"application/json"})
    public @ResponseBody CategoryResponseVo saveContactsForUser(@PathVariable(value="imeiNo") String imeiNo, Model model) {
    	log.debug("inside returning json data for tdp user");

    	
        return webserviceHandlerSevice.getAudioFilesBasedOnCategories(imeiNo);
    }
    
    @RequestMapping(value="/getCategoriesForUser",method=RequestMethod.GET,produces={"application/json"})
    public @ResponseBody CategoryResponseVo getCategories( UserContactsInputVO userContacts, Model model) {
      log.debug("hello inside");
    	 UserContactsInputVO user =new UserContactsInputVO();
         
         user.setEmailId("rak");
 		user.setImeiNo("jhsdjksdfh");
 		user.setMobileno("anilkumar");
 		user.setUserName("ravula");
 		HashMap map=	new HashMap<String, String>();
 		map.put("99592325", "1212");
 		map.put("995953232", "1212");
         user.setContacts(map);
        return (CategoryResponseVo) webserviceHandlerSevice.loadCategories(user);
    }
    
    
    @RequestMapping(value="/getCategoriesForUser",method=RequestMethod.POST,produces="application/json",consumes="application/json")
    public @ResponseBody CategoryResponseVo getCategoriesOfFile( @RequestBody UserContactsInputVO userContacts, Model model) {
    	log.debug("inside saving contacts and returning json data for tdp user");
   try{
       return (CategoryResponseVo) webserviceHandlerSevice.loadCategories(userContacts);

     }catch (Exception e) {
	log.error("exception Occured "+e.getMessage());
     }
        return new CategoryResponseVo();
    }
    
    
    @RequestMapping(value="/getCategoriesForUserTest",method=RequestMethod.POST,produces="application/json")
    public @ResponseBody CategoryResponseVo getCategoriesOfFileTest( ) {
      
    	 UserContactsInputVO user =new UserContactsInputVO();
         
         user.setEmailId("rak");
 		user.setImeiNo("jhsdjksdfh");
 		user.setMobileno("anilkumar");
 		user.setUserName("ravula");
 		HashMap map=	new HashMap<String, String>();
 		map.put("99592325", "1212");
 		map.put("995953232", "1212");
         user.setContacts(map);
        return (CategoryResponseVo) webserviceHandlerSevice.loadCategories(user);
    }

}
