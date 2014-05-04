package com.itgrids.eliteclub.service.impl;

import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.eliteclub.dao.IBoothPollingUpdatesDAO;
import com.itgrids.eliteclub.dto.BoothPollingUpdatesVO;
import com.itgrids.eliteclub.model.BoothPollingUpdates;
import com.itgrids.eliteclub.service.ISmsWebServiceHandler;



/**
 * @author Anilkumar Ravula
 *
 */
@Service("smsWebServiceHandlerImpl")
public class SmsWebServiceHandlerImpl implements  ISmsWebServiceHandler{
	
	@Autowired
	public IBoothPollingUpdatesDAO  boothPollingUpdatesDAO;

	private static final Logger LOG = LogManager.getLogger();

	public Object saveBoothPollingData(BoothPollingUpdatesVO inputVo)
	
	{  try {
		BoothPollingUpdates  vo =new BoothPollingUpdates();
		vo.setMessage(inputVo.getMessage());
		vo.setMobileNumber(inputVo.getMobileNumber());
		vo.setInsertedDate(inputVo.getInsertedDate());
		vo.setRecievedDate(inputVo.getRecievedDate());
		
		BoothPollingUpdates persistVo=boothPollingUpdatesDAO.save(vo);
		boothPollingUpdatesDAO.flushSession();
		LOG.debug("saving data sucessfull for booth polling updates"+persistVo.getBoothPollingUpdatesId());
	 }catch (Exception e) {
		 return "104";
	}
		
		return "100";
	}
	

   

}
