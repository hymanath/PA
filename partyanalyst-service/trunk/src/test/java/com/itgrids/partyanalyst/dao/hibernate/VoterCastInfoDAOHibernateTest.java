package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.model.VoterCastInfo;

public class VoterCastInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterCastInfoDAO voterCastInfoDAO;

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}
	
	/*public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(844l);
		List<Object[]> values = voterCastInfoDAO.getCastAndPartyForSelectedLevel(1l,2l,ids,8l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
	}*/
	/*public void test()
	{
		
		List<Object[]> values = voterCastInfoDAO.getAllCasteInfoByUserId(1l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
		}
		
	}*/
	
	/*public void test()
	{
		List<Long> ids = new ArrayList<Long>();
		Set<Long> casteIds = new HashSet<Long>();
		ids.add(11l);
		ids.add(12l);
		ids.add(1l);
		ids.add(7l);
		ids.add(4l);
		ids.add(13l);
		ids.add(10l);
		ids.add(6l);
		ids.add(9l);
		ids.add(8l);
		ids.add(3l);
		ids.add(2l);
		ids.add(5l);
		casteIds.add(296l);
		casteIds.add(290l);
		casteIds.add(211l);
		casteIds.add(189l);
		casteIds.add(244l);
		List<Object[]> values = voterCastInfoDAO.getTopCasteFoeSelctedLevel(ids,3l,8l,1l,casteIds);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1] +":"+ parms[2]);
		}
	}*/
	/*public void test()
	{
		List ids = new ArrayList<Long>();
		ids.add(835l);

		List<VoterCastInfo> values = voterCastInfoDAO.getVotersCastInfo(5l,83l,232l,8l,1l);
		System.out.println(values.size());
		for(VoterCastInfo voterCastInfo:values)
		{
			System.out.println(voterCastInfo.getCasteState().getCaste().getCasteName());
			System.out.println(voterCastInfo.getCasteVoters());
			System.out.println(voterCastInfo.getCastePercentage().toString());
		}
	}*/
	
	/*public void testgetTopThreeCasteForPanchayat(){
		List<Long> ids = new ArrayList<Long>();
		ids.add(11l);
		
		List<Object[]> values = voterCastInfoDAO.getTopThreeCasteForPanchayat(ids,3l,8l,1l);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1] +":"+ parms[2] );
		}
	}*/
	
/*	public void testgetVoterCasteInfoList()
	{
		List<VoterCastInfo> list = voterCastInfoDAO.getVoterCasteInfoList(232l,8l,1l);
		System.out.println(list.size());
		StringBuilder str = new StringBuilder();
		for(VoterCastInfo vCastInfo:list)
		{
			if(vCastInfo.getSubLeveCastePercentage() != null)
			str.append(""+vCastInfo.getSubLeveCastePercentage()+"");
		}
		System.out.println(str);
	}*/
	
	/*public void testgetCasteWiseCountDetails()
	{
		List<Object[]> values = voterCastInfoDAO.getCasteWiseCountDetails(5l,83l,8l,232l,296l,1l);
		for (Object[] parms : values) {
			System.out.println(parms[0]  +":"+ parms[1]  );
		}
	}*/
}
