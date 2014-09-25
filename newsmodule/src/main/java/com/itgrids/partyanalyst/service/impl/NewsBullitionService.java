package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.INewsBullitionDAO;
import com.itgrids.partyanalyst.dao.INewsBullitionNewsTypeDAO;
import com.itgrids.partyanalyst.dao.INewsBullitionTypeDAO;
import com.itgrids.partyanalyst.dao.INewsBullitionValuesDAO;
import com.itgrids.partyanalyst.dto.NewsBullitionVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.NewsBullition;
import com.itgrids.partyanalyst.model.NewsBullitionValues;
import com.itgrids.partyanalyst.service.INewsBullitionService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class NewsBullitionService implements INewsBullitionService{
	
	private static final Logger LOG       = Logger.getLogger(NewsBullitionService.class);
	
	
	private INewsBullitionDAO			  newsBullitionDAO ;
	private INewsBullitionTypeDAO		  newsBullitionTypeDAO ;
	private INewsBullitionNewsTypeDAO     newsBullitionNewsTypeDAO;
	private INewsBullitionValuesDAO       newsBullitionValuesDAO ;
	private TransactionTemplate 		  transactionTemplate;
	
	
	public void setNewsBullitionDAO(INewsBullitionDAO newsBullitionDAO) {
		this.newsBullitionDAO = newsBullitionDAO;
	}
	
	public void setNewsBullitionTypeDAO(INewsBullitionTypeDAO newsBullitionTypeDAO) {
		this.newsBullitionTypeDAO = newsBullitionTypeDAO;
	}
	
	public void setNewsBullitionNewsTypeDAO(
			INewsBullitionNewsTypeDAO newsBullitionNewsTypeDAO) {
		this.newsBullitionNewsTypeDAO = newsBullitionNewsTypeDAO;
	}
	
	public void setNewsBullitionValuesDAO(
			INewsBullitionValuesDAO newsBullitionValuesDAO) {
		this.newsBullitionValuesDAO = newsBullitionValuesDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public ResultStatus saveNewsBullitionDetails(final List<NewsBullitionVO> newsBullitionVOList){
		
		final ResultStatus resultStatus = new ResultStatus();
		try {
			
			 transactionTemplate.execute(new TransactionCallback() {
				  public Object doInTransaction(TransactionStatus status) {
					  for (NewsBullitionVO newsBullitionVO : newsBullitionVOList) 
					  {
						  NewsBullition newsBullition = new NewsBullition();
						  
						  newsBullition.setChannelId(newsBullitionVO.getChannelId());
						  newsBullition.setNewsBullitionTime(newsBullitionVO.getNewsBullitionTime());
						  newsBullition.setMinutes(newsBullitionVO.getMinutes());
						  newsBullition.setSeconds(newsBullitionVO.getSeconds());
						  newsBullition.setNewsBullitionDescription(newsBullitionVO.getNewsBullitionDescription());
						  if(newsBullitionVO.getIsHeadLine().equalsIgnoreCase("YES"))
						  {
							  newsBullition.setIsHeadLine("Y");
						  }
						  else
						  {
							  newsBullition.setIsHeadLine("N");
						  }
						  
						  DateUtilService currentDate = new DateUtilService();
						  newsBullition.setInsertedTime(currentDate.getCurrentDateAndTime());
						  newsBullition.setUpdateTime(currentDate.getCurrentDateAndTime());
						  
						  newsBullition.setIsDeleted("N");
						  
						  newsBullitionDAO.save(newsBullition);
						  
						  NewsBullitionValues newsBullitionValues = new NewsBullitionValues();
						  
						  newsBullitionValues.setNewsBullitionTypeId(newsBullitionVO.getNewsBullitionTypeId());
						  newsBullitionValues.setNewsBullitionNewsTypeId(newsBullitionVO.getNewsBullitionNewsTypeId());
						  if(newsBullitionVO.getDistrictId() != null && newsBullitionVO.getDistrictId() > 0)
						  {
							  newsBullitionValues.setDistrictId(newsBullitionVO.getDistrictId());
						  }
						 
						  if(newsBullitionVO.getMandalId() != null && newsBullitionVO.getMandalId() > 0)
						  {
							  newsBullitionValues.setMandalId(newsBullitionVO.getMandalId());
						  }
						 
						  if(newsBullitionVO.getVillageId() != null && newsBullitionVO.getVillageId() > 0)
						  {
							  newsBullitionValues.setVillageId(newsBullitionVO.getVillageId());
						  }
						 
						  newsBullitionValues.setInsertedTime(currentDate.getCurrentDateAndTime());
						  newsBullitionValues.setUpdateTime(currentDate.getCurrentDateAndTime());
						  
						  newsBullitionValues.setNewsBullition(newsBullition);
						  
						  NewsBullitionValues result = newsBullitionValuesDAO.save(newsBullitionValues);
						  
						  if(result != null)
						  {
							  resultStatus.setResultCode(0);
							  resultStatus.setMessage("Succesfully Saved");
						  }
						  else
						  {
							  resultStatus.setResultCode(1);
							  resultStatus.setMessage("Error Occured While Saving");
						  }
					  }
					  
					  return resultStatus;
				  }
			 });
		} catch (Exception e) {
			LOG.error("Exceptiopn raised in saveNewsBullitionDetails in NewsBullitionService Service", e);
			resultStatus.setResultCode(2);
			resultStatus.setMessage("Exception Occured While Saving");
			return resultStatus;
		}
		return resultStatus;
		
	}
	
	
	
}
