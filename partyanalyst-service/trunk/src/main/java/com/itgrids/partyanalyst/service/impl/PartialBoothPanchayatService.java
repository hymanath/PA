package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.dto.PartialBoothPanchayatVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;
import com.itgrids.partyanalyst.service.IPartialBoothPanchayatService;

public class PartialBoothPanchayatService implements IPartialBoothPanchayatService{

	private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;
	private IPanchayatDAO panchayatDAO;
	private IBoothDAO boothDAO;
	private TransactionTemplate transactionTemplate = null;
	private static final Logger LOG = Logger.getLogger(PartialBoothPanchayatService.class);
	
	
	public IPartialBoothPanchayatDAO getPartialBoothPanchayatDAO() {
		return partialBoothPanchayatDAO;
	}

	public void setPartialBoothPanchayatDAO(
			IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
		this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public ResultStatus savePartialBoothPanchayaDetails(final Long panchayaId,
			final Long boothId,final Long partialPanchayatId,final String description,final String partialDescription) {
			final ResultStatus resultStatus =new ResultStatus();
		try {
			LOG.debug("enterd into savePartialBoothPanchayaDetails() method in PartialBoothPanchayatService Class");
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				PartialBoothPanchayat partialBoothPanchayat = null;
				PartialBoothPanchayat boothPartialPanchayat = null;
				if(panchayaId != null)
				{
					Long particlPanchayatDetailsId = getPanchayatBoothDetals(panchayaId,boothId);
					if(particlPanchayatDetailsId == null)
					{
						partialBoothPanchayat = new PartialBoothPanchayat();
						partialBoothPanchayat.setDescription(description);
						if(boothDAO.get(boothId) != null)
						{
							partialBoothPanchayat.setBooth(boothDAO.get(boothId));
						}
						if(panchayatDAO.get(panchayaId) != null)
						{
							partialBoothPanchayat.setPanchayat(panchayatDAO.get(panchayaId));
						}
						partialBoothPanchayat = partialBoothPanchayatDAO.save(partialBoothPanchayat);	
					}
					Long boothPanchayatDetailsId = getPanchayatBoothDetals(partialPanchayatId,boothId);
					if(boothPanchayatDetailsId == null)
					{
						boothPartialPanchayat = new PartialBoothPanchayat();
						boothPartialPanchayat.setDescription(partialDescription);
						if(boothDAO.get(boothId) != null)
						{
							boothPartialPanchayat.setBooth(boothDAO.get(boothId));
						}
						if(panchayatDAO.get(partialPanchayatId) != null)
						{
							boothPartialPanchayat.setPanchayat(panchayatDAO.get(partialPanchayatId));
						}
						boothPartialPanchayat = partialBoothPanchayatDAO.save(boothPartialPanchayat);
					}
					/*else
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						resultStatus.setExceptionMsg("Already Mapped");
					}*/
					if(boothPartialPanchayat != null || partialBoothPanchayat != null)
					{
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						resultStatus.setExceptionMsg("Successfully Mapped");
						Booth booth = boothDAO.get(boothId);
						booth.setIsPartial("Y");
						booth = boothDAO.save(booth);
					}
					else
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						resultStatus.setExceptionMsg("Already Mapped");
					}
				}	
			}
			});
		} catch (Exception e) {
			LOG.error("Exception raised in savePartialBoothPanchayaDetails() method in PartialBoothPanchayatService Class",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		
		return resultStatus;
	}

	public Long getPanchayatBoothDetals(Long panchayatId, Long boothId)
	{
		
		Long particlPanchayatDetails = 0l;
		try {
			LOG.debug("enterd into getPanchayatBoothDetals() method in PartialBoothPanchayatService Class");
			particlPanchayatDetails = partialBoothPanchayatDAO.getBoothPanchayatDetails(panchayatId,boothId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPanchayatBoothDetals() method in PartialBoothPanchayatService Class",e);
		}		
		return particlPanchayatDetails;
	}

	
	public List<PartialBoothPanchayatVO> getAllPartialBoothsInASelectedMandal(Long mandalId,Long publicationId)
	{
		List<PartialBoothPanchayatVO> returnList = null;
		try {
			LOG.debug("enterd into getAllPartialBoothsInASelectedMandal() method in PartialBoothPanchayatService Class");
			List<Object[]> partialBooths = partialBoothPanchayatDAO.getPartialBoothsAndPanchayats(mandalId,publicationId);
			if(partialBooths != null && partialBooths.size() > 0)
			{
				returnList = new ArrayList<PartialBoothPanchayatVO>();
				for (Object[] parms : partialBooths) {
					PartialBoothPanchayatVO partialBoothPanchayatVO = new PartialBoothPanchayatVO();
					partialBoothPanchayatVO.setPartialBoothPanchayatId((Long)parms[0]);
					partialBoothPanchayatVO.setPanchayatId((Long)parms[1]);
					partialBoothPanchayatVO.setPanchayatName(parms[2].toString());
					partialBoothPanchayatVO.setBoothId((Long)parms[3]);
					partialBoothPanchayatVO.setBoothName("Booth - " +parms[4]+ "" .toString() );
					partialBoothPanchayatVO.setDescription(parms[5] != null ? parms[5].toString() : "");
					returnList.add(partialBoothPanchayatVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getAllPartialBoothsInASelectedMandal() method in PartialBoothPanchayatService Class",e);
		}
		
		return returnList;
	}
	
	public List<PartialBoothPanchayatVO> getSelectedPartialPanchayatDetails(Long partialBoothPanchayatid)
	{
		List<PartialBoothPanchayatVO> returnList = null;
		try {
			LOG.debug("enterd into getSelectedPartialPanchayatDetails() method in PartialBoothPanchayatService Class");
			List<Object[]> partialPanchayatList = partialBoothPanchayatDAO.getSelectdPartialPanchayatDetails(partialBoothPanchayatid);
			if(partialPanchayatList != null && partialPanchayatList.size() > 0)
			{
				returnList = new ArrayList<PartialBoothPanchayatVO>();
				for (Object[] parms : partialPanchayatList) {
					PartialBoothPanchayatVO partialBoothPanchayatVO = new PartialBoothPanchayatVO();
					partialBoothPanchayatVO.setPartialBoothPanchayatId(partialBoothPanchayatid);
					partialBoothPanchayatVO.setPanchayatId((Long)parms[0]);
					partialBoothPanchayatVO.setPanchayatName(parms[1].toString());
					partialBoothPanchayatVO.setBoothId((Long)parms[2]);
					partialBoothPanchayatVO.setBoothName("Booth - " +parms[3]+ "" .toString());
					partialBoothPanchayatVO.setPartialpanchayatId((Long)parms[4]);
					partialBoothPanchayatVO.setPartialPanchayatName(parms[5].toString());
					partialBoothPanchayatVO.setDescription(parms[6] != null ? parms[6].toString() : "");
					partialBoothPanchayatVO.setPartialDescription(partialBoothPanchayatDAO.getDescriptionForSelectedPartalPanchay(partialBoothPanchayatVO.getPartialpanchayatId(),partialBoothPanchayatVO.getBoothId()).get(0) != null ? partialBoothPanchayatDAO.getDescriptionForSelectedPartalPanchay(partialBoothPanchayatVO.getPartialpanchayatId(),partialBoothPanchayatVO.getBoothId()).get(0) : "");
					returnList.add(partialBoothPanchayatVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getSelectedPartialPanchayatDetails() method in PartialBoothPanchayatService Class",e);
		}
		return returnList;
	}
	
	public ResultStatus deleteSelectedPartialPanchayat(Long partialBoothPanchayatid,Long boothId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try {
			LOG.debug("enterd into deleteSelectedPartialPanchayat() method in PartialBoothPanchayatService Class");
			int deleteStatus = 0;
			Long count = partialBoothPanchayatDAO.getCountForPartianBooths(boothId);
			if(count == 2)
			{
				 deleteStatus = partialBoothPanchayatDAO.deleteSelectedMultiplePartialBoothPanchayat(boothId);
			}
			else
			{
				deleteStatus = partialBoothPanchayatDAO.deleteSelectedPartialBoothPanchayat(partialBoothPanchayatid);
			}
			if(deleteStatus > 0)
			{
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				resultStatus.setMessage("Deleted Successfully");
				Booth booth = new Booth();
				booth = boothDAO.get(boothId);
				if(booth != null)
				{
					booth.setIsPartial("N");
					boothDAO.save(booth);
				}
			}
			else
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setMessage("Not Deleted Successfully");
			}
		} catch (Exception e) {
			LOG.error("Exception raised in deleteSelectedPartialPanchayat() method in PartialBoothPanchayatService Class",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setMessage("Exception occured while deleteing");
		}
		
		return resultStatus;
	}
	
	public ResultStatus updatePartialBoothPanchayaDetails(final Long id ,final Long panchayaId,
			final Long boothId,final Long partialPanchayatId,final String description,final String partialDescription) {
			final ResultStatus resultStatus =new ResultStatus();
		try {
			LOG.debug("enterd into savePartialBoothPanchayaDetails() method in PartialBoothPanchayatService Class");
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				
				PartialBoothPanchayat partialBoothPanchayat = partialBoothPanchayatDAO.get(id);
				if(partialBoothPanchayat != null)
				{
					partialBoothPanchayat.setBooth(boothDAO.get(boothId));
					partialBoothPanchayat.setDescription(partialDescription);
					partialBoothPanchayat.setPanchayat(panchayatDAO.get(partialPanchayatId));
					partialBoothPanchayat = partialBoothPanchayatDAO.save(partialBoothPanchayat);
					if(partialBoothPanchayat != null)
					{
						int result = partialBoothPanchayatDAO.updateDescriptionForPartialPanchayat(panchayaId, boothId, description);
						if(result > 0)
						{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
							resultStatus.setMessage("updated successfully");
						}
					}
					
				}
				else
				{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setMessage("Not updated successfully");
				}
			}	
			});
		} catch (Exception e) {
			LOG.error("Exception raised in savePartialBoothPanchayaDetails() method in PartialBoothPanchayatService Class",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setMessage("Exception Occured while updating tha details");
		}
		
		return resultStatus;
	}
}


