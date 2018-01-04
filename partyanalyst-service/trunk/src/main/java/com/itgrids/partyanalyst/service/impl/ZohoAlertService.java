package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.service.IZohoAlertService;

public class ZohoAlertService implements IZohoAlertService {
	private static final Logger LOG = Logger.getLogger(ZohoAlertService.class);

	private ITdpCadreDAO tdpCadreDAO;

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}


	@Override
	public String getMobileNoByMemberShip(String membershipId) {
		String mobileNo = null;
		try{
			List<Object[]> mobileNoList = tdpCadreDAO.getMobileNoOfMembership(membershipId);
			if(mobileNoList!=null && mobileNoList.size()>0){
				mobileNo=mobileNoList.get(0)[1]!=null?mobileNoList.get(0)[1].toString():null;
			}
		}catch (Exception e) {
			LOG.error("Exception raised at getMobileNoByMemberShip in ZohoAlertService Class ", e);
		}
		return mobileNo;
	}

}
