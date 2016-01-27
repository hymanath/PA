package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.utils.IConstants;


public class SmsSenderService implements ISmsSenderService{
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private static final Logger LOG = Logger.getLogger(SmsSenderService.class);
	private TaskExecutor taskExecutor;
	private ICadreRegistrationService cadreRegistrationService;
	
	
	
	

	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public ResultStatus sendSmsToCadre(Integer startIndex,Integer maxIndex)	{
		
		LOG.info("Entered in to sendSmsToCadre() method ");
		ResultStatus result = new ResultStatus();
		try{
				List<CadreVoterVO> resultList = new ArrayList<CadreVoterVO>();
				List<Object[]> list = boothPublicationVoterDAO.getCadreVoterInfo();
					 if(list != null && list.size() > 0)
					 {
							 for(Object[] params : list)
							 {
								 CadreVoterVO vo = new CadreVoterVO();
								 vo.setPartNo(params[0] != null ? params[0].toString() : "");
								 vo.setSerialNo(params[1] != null ? params[1].toString() : "");
								 vo.setLocation(params[2] != null ? params[2].toString() : "");
								 vo.setName(params[3] != null ? params[3].toString() : "");
								 vo.setRelativeName(params[4] != null ? params[4].toString() : "");
								 vo.setVoterIdCardNo(params[5] != null ? params[5].toString() : "");
								 vo.setWardNo(params[6] != null ? params[6].toString() : "");
								 vo.setMobileNum(params[7] != null ? params[7].toString() : "");
								 vo.setRelationShipType(params[8] != null ? params[8].toString() : "");
								 vo.setGender(params[9] != null ? params[9].toString() : "");
								 resultList.add(vo);
							 }
					 }
					 for(;;)
						{
							 if(startIndex >= resultList.size()-1)
								 break;
							 if(maxIndex >= resultList.size())
								 maxIndex = resultList.size() - 1;
							 startConsumeMessages(resultList.subList(startIndex, maxIndex));
							 startIndex = maxIndex;
							 maxIndex = maxIndex + maxIndex;
						}
				}
		catch (Exception e) {
			LOG.error("Exception Occured in sendSmsToCadre() method"+e);
		}
		return result;
		
	}
	
	public void startConsumeMessages(List<CadreVoterVO> resultList)
	{
		int i=0;
		for(CadreVoterVO vo : resultList){
		try{
			LOG.error(i+1+") Thread is Creating...............");
			String relation = "";
			if(vo.getRelationShipType().equalsIgnoreCase("Father") && vo.getGender().equalsIgnoreCase("M"))
				relation = "s/o";
			else if(vo.getRelationShipType().equalsIgnoreCase("Mother")&& vo.getGender().equalsIgnoreCase("F"))
				relation = "d/o";
			
			else if(vo.getRelationShipType().equalsIgnoreCase("Husband"))
				relation = "w/o";
			else
				relation = "c/o";
			
			String msg = vo.getName() +"\n" +relation +" "+vo.getRelativeName() +"\n" +"AIO"+vo.getVoterIdCardNo()+"\n" +
			 "Polling Booth No:"+vo.getPartNo()+"\n" +"Voter Serial No:"+vo.getSerialNo()+"\n" +"Polling Booth Location:"+vo.getLocation()+"\n" +"Ward No:"+vo.getWardNo();
			taskExecutor.execute(sendSMSForCader(msg,"9032411640"));
			}catch(Exception e)
			{
				LOG.error("Exception in Creating a thread");
				LOG.error(e);
			}
		}
	}
	public Runnable sendSMSForCader(String message,String phoneNumbers)
	{
		try{
			cadreRegistrationService.sendSMS(phoneNumbers,message);
			return new Thread();
			
		}catch (Exception e) {
			e.printStackTrace();
			return new Thread();
		}
	}
	
	/*private class ConsumeMessage implements Runnable 
	{

		
		public void run() {
			LOG.info("Entered in run()");
			try{
				
				sendSmsFromAdmin();
				
			}
			catch (Exception e) {
				LOG.error(e);
			}
			
		}
		
	}*/

}
