package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ISmsGatewayService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.utils.IConstants;


public class SmsSenderService implements ISmsSenderService{
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private static final Logger LOG = Logger.getLogger(SmsSenderService.class);
	private TaskExecutor taskExecutor;
	private ICadreRegistrationService cadreRegistrationService;
	private ISmsGatewayService smsGatewayService;
	
	
	
	
	

	public ISmsGatewayService getSmsGatewayService() {
		return smsGatewayService;
	}

	public void setSmsGatewayService(ISmsGatewayService smsGatewayService) {
		this.smsGatewayService = smsGatewayService;
	}

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
								 vo.setLatitude(params[10] != null ? params[10].toString() : "");
								 vo.setLongitude(params[11] != null ? params[11].toString() : "");
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
					 result.setMessage("success");
				}
		catch (Exception e) {
			result.setMessage("fail");
			LOG.error("Exception Occured in sendSmsToCadre() method"+e);
			
		}
		return result;
		
	}
	
	
	public ResultStatus sendSmsToVoter(MobileAppUserVoterVO inputVO)	{
		
		LOG.info("Entered in to SendSmsToVoter() method ");
		ResultStatus result = new ResultStatus();
		try{
			Integer startIndex =0;
			Integer maxIndex = 5000;
				List<CadreVoterVO> resultList = new ArrayList<CadreVoterVO>();
				List<Object[]> list = boothPublicationVoterDAO.getVoterInfo(inputVO.getMobileNums());
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
					 result.setMessage("success");
				}
		catch (Exception e) {
			result.setMessage("fail");
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
			/*String relation = "";
			if(vo.getRelationShipType().equalsIgnoreCase("Father") && vo.getGender().equalsIgnoreCase("M"))
				relation = "s/o";
			else if(vo.getRelationShipType().equalsIgnoreCase("Mother")&& vo.getGender().equalsIgnoreCase("F"))
				relation = "d/o";
			
			else if(vo.getRelationShipType().equalsIgnoreCase("Husband"))
				relation = "w/o";
			else
				relation = "c/o";
			
			String msg = vo.getName() +"\n" +relation +" "+vo.getRelativeName() +"\n" +"AIO"+vo.getVoterIdCardNo()+"\n" +
			 "Polling Booth No:"+vo.getPartNo()+"\n" +"Voter Serial No:"+vo.getSerialNo()+"\n" +"Polling Booth Location:"+vo.getLocation()+"\n" +"Ward No:"+vo.getWardNo();*/
			 StringBuilder sb = new StringBuilder();
			   
			   String voterName =vo.getName() != null ? vo.getName().toString() : "";
			   String voterIdCardNo = vo.getVoterIdCardNo() != null ? vo.getVoterIdCardNo().toString() : null;
			   String relativeName =vo.getRelativeName() != null ? vo.getRelativeName().toString() : null;
			   String relation = vo.getRelationShipType() != null ? vo.getRelationShipType().toString() : "";
			   String gender = vo.getGender();
			   String serialNo =vo.getSerialNo() != null ? vo.getSerialNo().toString() : null;
			   String latitude = vo.getLatitude() != null ?vo.getLatitude() .toString() : null;
			   String longitude = vo.getLongitude() != null ?vo.getLongitude().toString() : null;
			   String location =vo.getLocation()!= null ? vo.getLocation().toString() : null;
			   String relationStr = "C/O";
					   
			   sb.append("Name : "+voterName+"\n");
			   
			   if(relation.equalsIgnoreCase("Father") || relation.equalsIgnoreCase("Mother"))
			   {
				   if(gender.equalsIgnoreCase("M"))
					   relationStr = "S/O";
				   else
					   relationStr = "D/O";
			   }
			   else if(relation.equalsIgnoreCase("Husband") && gender.equalsIgnoreCase("F"))
				   relationStr = "W/O";
			   
			   if(relativeName != null)
				   sb.append(relationStr+" : "+relativeName+"\n");
			   
			   if(voterIdCardNo != null)
				   sb.append("EPIC ID : "+voterIdCardNo+"\n");
			   
			   if(serialNo != null)
				   sb.append("Serial No : "+serialNo+"\n");
			   
			   sb.append("Booth No : "+vo.getPartNo().toString()+"\n");
			   
			   if(location != null)
			   sb.append("Location : "+location+"\n");
			   sb.append("Vote on 02-FEB-2016 07:00 AM - 05:00 PM.\n");
			   
			   if(latitude != null && longitude != null)
			   {
				   String url = "http://maps.google.com/maps?saddr=Current+Location&daddr="+latitude+","+longitude;
				   sb.append("Route to Polling Station\n");
				   sb.append(url);
			   }
			taskExecutor.execute(sendSMSForCader(sb.toString(),"9032411640"));
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
			//cadreRegistrationService.sendSMS(phoneNumbers,message);
			smsGatewayService.sendSMS(phoneNumbers,message,IConstants.ITGRIDS_USERNAME_FOR_SMS,IConstants.ITGRIDS_PASSWORD_FOR_SMS);		 
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
