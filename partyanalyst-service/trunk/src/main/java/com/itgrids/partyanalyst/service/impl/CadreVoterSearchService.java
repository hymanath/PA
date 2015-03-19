package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.service.ICadreVoterSearchService;
import com.itgrids.partyanalyst.utils.IConstants;



public class CadreVoterSearchService implements ICadreVoterSearchService{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	
	
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;


	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}


	public List<CadreAddressVO> getAllDistrictsAndConstis(String type,Long id){	
		 
		List<CadreAddressVO> returnList = new ArrayList<CadreAddressVO>();
		try {
			List<Object[]> list = new ArrayList<Object[]>();
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
				 list = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1l, id);
			else
				 list = constituencyDAO.getDistrictConstituencies(id);	
			
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					CadreAddressVO vo = new CadreAddressVO();
					vo.setId(Long.valueOf(obj[0].toString()));
					vo.setName(obj[1].toString());				
					returnList.add(vo);
				}
			}
		  }catch(Exception e){
			  LOG.error("Exception Occured in getAllDistrictsAndConstis()", e);
		  }
		 return  returnList;
	  } 
}
