package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAffiliatedMemberDAO;
import com.itgrids.partyanalyst.dto.AffiliatedMemberVO;
import com.itgrids.partyanalyst.service.IAffiliatedMember;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class AffiliatedMemberImpl implements IAffiliatedMember {
	private static final Logger   LOG = Logger.getLogger(AffiliatedMemberImpl.class);
	
	private IAffiliatedMemberDAO  iAffiliatedMemberDAO;
	
	private CommonMethodsUtilService commonMethodsUtilService;

	public IAffiliatedMemberDAO getiAffiliatedMemberDAO() {
		return iAffiliatedMemberDAO;
	}

	public void setiAffiliatedMemberDAO(IAffiliatedMemberDAO iAffiliatedMemberDAO) {
		this.iAffiliatedMemberDAO = iAffiliatedMemberDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	@Override
	public AffiliatedMemberVO saveAffiliatedMemberDetails(
			AffiliatedMemberVO affiliatedMemberVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AffiliatedMemberVO> searchAffiliatedMemberDetails(String searchType, String searchValue,String locationType, Long locationValues) {
		 List<AffiliatedMemberVO> returnList = new ArrayList<AffiliatedMemberVO>();
		try{  		
			List<Object[]> affiliatedDtls = iAffiliatedMemberDAO.searchAffiliatedMemberDetails(searchType, searchValue, locationType,  locationValues);
			if(affiliatedDtls!= null && affiliatedDtls.size() >0){
				for (Object[] objects : affiliatedDtls) {
					AffiliatedMemberVO vo=new AffiliatedMemberVO();
					vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setVoterId(commonMethodsUtilService.getLongValueForObject(objects[4]));
					vo.setVoterIdCardNo(commonMethodsUtilService.getStringValueForObject(objects[5]));
					vo.setMembershipNo(commonMethodsUtilService.getStringValueForObject(objects[3]));
					//vo.setAffiliatedMemberId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setRelativeName(commonMethodsUtilService.getStringValueForObject(objects[6]));
					vo.setCasteStateId(commonMethodsUtilService.getLongValueForObject(objects[9]));
					vo.setHouseNo(commonMethodsUtilService.getStringValueForObject(objects[7]));
					returnList.add(vo);
				}
				}
	 	        }catch(Exception e){
		        LOG.error("Exception rised in searchAffiliatedMemberDetails",e);
	          }
		return returnList;
	
	

	}
}

