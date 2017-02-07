package com.itgrids.partyanalyst.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDynamicKeysDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.service.ICadreDetailsService;

public class CommonUtilsService {

	private static final Logger LOG = Logger.getLogger(CommonUtilsService.class);
	private IDynamicKeysDAO dynamicKeysDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private ICadreDetailsService cadreDetailsService;
	
	
	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}


	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public void setDynamicKeysDAO(IDynamicKeysDAO dynamicKeysDAO) {
		this.dynamicKeysDAO = dynamicKeysDAO;
	}
	
	public String getDynamicValueOfAKey(String key)
	{
		String value = null;
		try{
			value = dynamicKeysDAO.getValueByKey(key);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getDynamicValueOfAKey() Method");
		}
		return value;
	}
	
	public Boolean checkValidMember(String memberShipNo)
	{
		
		Boolean valid = false;
		try{
			/*
			if(memberShipNo == null || memberShipNo.isEmpty() || memberShipNo.toString().trim().length() <= 7)
				return false;
		    Long tdpCadreId = tdpCadreDAO.checkMemberExists(memberShipNo);
			if(tdpCadreId != null)
				valid  = true;
			else
				valid = false;	
			 */
			if(memberShipNo == null || memberShipNo.trim().isEmpty() || memberShipNo.toString().trim().length() <= 7)
				return false; 
			
			TdpCadreVO tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(0L, 0L, "", memberShipNo, "", "", "", 0L, "",null,null,null,null,0,0,false,3l,null);
			if(tdpCadreVO != null)
			{
				if(tdpCadreVO.getTdpCadreDetailsList() != null && tdpCadreVO.getTdpCadreDetailsList().size()>0)
				{
					valid  = true;
				}
				else
				{
					valid = false;	
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valid;
	}

	public List<CadreAddressVO> checkForValidMember(List<String> memberShipNos)
	{
		
		List<CadreAddressVO> cadreAddressVOList = new ArrayList<CadreAddressVO>();
		try{
			StringBuilder queryStr = new StringBuilder();
			if(memberShipNos != null &&  memberShipNos.size() > 0)
			{
				/*for(String memberShipNo :memberShipNos){
				String memberShipNumber = "AP14"+memberShipNo;
				String memberShipNumber1 = "TS14"+memberShipNo;
				String temp =  memberShipNos.get(memberShipNos.size() - 1);
				if(temp.equalsIgnoreCase(memberShipNo))
					queryStr.append("  (model.memberShipNo ='"+memberShipNo.trim()+"' OR model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");
				else
					queryStr.append(" (model.memberShipNo ='"+memberShipNo.trim()+"' OR model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"')  OR ");
				}*/
				
				String memberShipNumber="";
				String memberShipNumber1 ="";
				String memberShipNo ="";
				int i=memberShipNos.size();
				do{
					if(i==memberShipNos.size())
					{
						memberShipNumber = "\'AP14"+memberShipNos.get(i-1)+"\'";
						memberShipNumber1 = "\'TS14"+memberShipNos.get(i-1)+"\'";
						memberShipNo = "\'"+memberShipNos.get(i-1)+"\'";
					}
					else
					{
						memberShipNumber = memberShipNumber+",\'AP14"+memberShipNos.get(i-1)+"\'";
						memberShipNumber1 = memberShipNumber1+",\'TS14"+memberShipNos.get(i-1)+"\'";
						memberShipNo = memberShipNo+",\'"+memberShipNos.get(i-1)+"\'";
					}
					
					i=--i;
				}while(i>0);
				queryStr.append(" model.memberShipNo in ("+memberShipNo.trim()+") OR model.memberShipNo in ("+memberShipNumber.trim()+") OR model.memberShipNo in  ("+memberShipNumber1.trim()+")   ");
			}
		
		    List<String> details = tdpCadreDAO.checkForMemberExists(queryStr.toString());
		    List<String> validNos = new ArrayList<String>();
			if(details != null && details.size() >0)
			{
				for(String params :details){
					CadreAddressVO vo = new CadreAddressVO();
					if(params.trim().length() > 8){
					  vo.setMembershipNo(params.substring(4));
					}else{
						vo.setMembershipNo(params);
					}
					vo.setValue("true");
					validNos.add(vo.getMembershipNo());
					cadreAddressVOList.add(vo);
				}
			}	
			for(String membershipNo :memberShipNos){
				if(!validNos.contains(membershipNo)){
					CadreAddressVO vo1 = new CadreAddressVO();					
					vo1.setMembershipNo(membershipNo);
					vo1.setValue("false");
					cadreAddressVOList.add(vo1);
				}
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cadreAddressVOList;
	}
}
