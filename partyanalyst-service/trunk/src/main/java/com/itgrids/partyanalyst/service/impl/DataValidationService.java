package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.DataValidationVO;
import com.itgrids.partyanalyst.service.IDataValidationService;

public class DataValidationService implements IDataValidationService{
	private static final Logger LOG = Logger.getLogger(DataValidationService.class);
	
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IPanchayatHamletDAO panchayatHamletDAO;
	
	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	public static Comparator<DataValidationVO> sortData = new Comparator<DataValidationVO>()
    {
   
        public int compare(DataValidationVO resultList1, DataValidationVO resultList2)
        {
            return (resultList1.getTehsilName()).compareTo(resultList2.getTehsilName());
        	
        }
    };
    
    public List<DataValidationVO> getHamletsAssignedValidation(Long constituencyId,Long publicationDateId,Long userId)
	{
		List<DataValidationVO> result = new ArrayList<DataValidationVO>();
		List<Long> panchayatsList = new ArrayList<Long>(0);
		try{
			List<Object[]> list = voterInfoDAO.getPanchayatWiseVotersCount(constituencyId, publicationDateId);
			if(list != null && list.size() > 0)
			{
				DataValidationVO dataValidationVO = null;
				for(Object[] params : list)
				{
					dataValidationVO = new DataValidationVO();
					dataValidationVO.setTehsilId((Long)params[0]);
					dataValidationVO.setTehsilName(params[1].toString());
					dataValidationVO.setPanchayatId((Long)params[2]);
					dataValidationVO.setPanchayatName(params[3].toString());
					dataValidationVO.setTotalVoters((Long)params[4]);
					panchayatsList.add(dataValidationVO.getPanchayatId());
					result.add(dataValidationVO);
				}
				
				List<Object[]> panchayatsHamletsList = panchayatHamletDAO.getHamletsByPanchayatsList(panchayatsList);
				
				for(Object[] params : panchayatsHamletsList)
				{
					DataValidationVO dataValidationVO2 = getDataValidationVOFromList(result,(Long)params[0]);
					List<SelectOptionVO> hamletsList = dataValidationVO2.getHamletsList();
					hamletsList.add(new SelectOptionVO((Long)params[2],params[3].toString()));
					dataValidationVO2.setHamletsList(hamletsList);
				}
				
				List<Object[]> villageResult = userVoterDetailsDAO.getPanchayatWiseHamletsAssignedDetails(constituencyId,publicationDateId,userId);
				SelectOptionVO selectOptionVO = null;
				
				for(Object[] params : villageResult)
				{
					DataValidationVO validationVO = getDataValidationVOFromList(result,(Long)params[0]);
					if(validationVO == null)
						continue;
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[2]);
					selectOptionVO.setName(params[3].toString());
					selectOptionVO.setPopulateId((Long)params[4]);
					
					List<SelectOptionVO> assignedHamletsList = validationVO.getAssignedHamletsList();
					assignedHamletsList.add(selectOptionVO);
					validationVO.setAssignedHamletsList(assignedHamletsList);
					
					List<Long> assignedHamletsIdsList = validationVO.getAssignedHamletsIdsList();
					assignedHamletsIdsList.add((Long)params[2]);
					validationVO.setAssignedHamletsIdsList(assignedHamletsIdsList);
					
					validationVO.setHamletAssignedVoters(validationVO.getHamletAssignedVoters()+(Long)params[4]);
				}
				for(DataValidationVO validationVO2 : result)
				{
					validationVO2.setHamletsNotAssignedVoters(validationVO2.getTotalVoters()-validationVO2.getHamletAssignedVoters());
					for(SelectOptionVO optionVO : validationVO2.getHamletsList())
						if(!validationVO2.getAssignedHamletsIdsList().contains(optionVO.getId()))
						{
							List<SelectOptionVO> notAssignedHamletsList = validationVO2.getNotAssignedHamletsList();
							notAssignedHamletsList.add(optionVO);
							validationVO2.setNotAssignedHamletsList(notAssignedHamletsList);
						}
							
				}
			}
			return result;
		}catch (Exception e) {
			LOG.error("Exception occured in getHamletsAssignedValidation() method ",e);
			return result;
		}
	}
	
    public DataValidationVO getDataValidationVOFromList(List<DataValidationVO> result,Long panchayatId)
    {
    	try{
    		for(DataValidationVO dataValidationVO : result)
    			if(dataValidationVO.getPanchayatId().equals(panchayatId))
    				return dataValidationVO;
    		return null;
    	}catch (Exception e) {
    		LOG.error("Exception Ocuured in getDataValidationVOFromList()");
    		return null;
    	}
    }
		
}
