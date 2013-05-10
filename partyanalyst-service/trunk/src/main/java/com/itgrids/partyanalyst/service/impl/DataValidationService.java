package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.excel.booth.DataValidationVO;
import com.itgrids.partyanalyst.service.IDataValidationService;

public class DataValidationService implements IDataValidationService{
	private static final Logger LOG = Logger.getLogger(DataValidationService.class);
	
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	
	private IPanchayatHamletDAO panchayatHamletDAO;
	
	
	
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



	public List<DataValidationVO> getHamletsAssignedValidation(Long constituencyId,Long publicationDateId)
	{
		List<DataValidationVO> result = new ArrayList<DataValidationVO>();
		DataValidationVO dataValidationVO = null;
		List<Object[]> list = null;
		
		
		try{
			
			list = userVoterDetailsDAO.getPanchayatWiseHamletsAssignedDetailsInAConstituency(constituencyId,publicationDateId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					Long totalHamlets = 0l;
					dataValidationVO = new DataValidationVO();
					dataValidationVO.setTehsilId((Long)params[0]);
					dataValidationVO.setTehsilName(params[1].toString());
					dataValidationVO.setPanchayatId((Long)params[2]);
					dataValidationVO.setPanchayatName(params[3].toString());
					dataValidationVO.setTotalVoters((Long)params[4]);
					dataValidationVO.setHamletAssignedVoters((Long)params[5]);
					dataValidationVO.setHamletsNotAssignedVoters(dataValidationVO.getTotalVoters() - dataValidationVO.getHamletAssignedVoters());
					
					totalHamlets = (Long)panchayatHamletDAO.getHamletsCountOfAPanchayat(dataValidationVO.getPanchayatId()).get(0);
					dataValidationVO.setTotalHamlets(totalHamlets);
					result.add(dataValidationVO);
				}
			}
			Collections.sort(result,sortData);
			return result;
		}catch (Exception e) {
			LOG.error("Exception occured in getHamletsAssignedValidation() method ",e);
			return result;
		}
	}
	public static Comparator<DataValidationVO> sortData = new Comparator<DataValidationVO>()
		    {
		   
		        public int compare(DataValidationVO resultList1, DataValidationVO resultList2)
		        {
		            return (resultList1.getTehsilName()).compareTo(resultList2.getTehsilName());
		        	
		        }
		    };
		    
		
}
