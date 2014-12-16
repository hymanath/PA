package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerConstituencyDAO;
import com.itgrids.partyanalyst.dto.TdpCadreVolunteerVO;

public class TdpCadreVolunteerConstituencyDAOHibernateTest extends BaseDaoTestCase{

	private ITdpCadreVolunteerConstituencyDAO tdpCadreVolunteerConstituencyDAO;

	public void setTdpCadreVolunteerConstituencyDAO(
			ITdpCadreVolunteerConstituencyDAO tdpCadreVolunteerConstituencyDAO) {
		this.tdpCadreVolunteerConstituencyDAO = tdpCadreVolunteerConstituencyDAO;
	}
	
	
	public void testDetailsForVolunteer()
	{
		try {
			List<Long> vaolunteersList = new ArrayList<Long>(0);
			vaolunteersList.add(11L);
			vaolunteersList.add(12L);
			List<Object[]> volunteerInfoList = tdpCadreVolunteerConstituencyDAO.getconsituencyListById(vaolunteersList);
			
			System.out.println(volunteerInfoList);
			/*
			
			List<Object[]> volunteerInfoList = tdpCadreVolunteerConstituencyDAO.getVolunteerInfoByLocation(315L,"Available");
			Map<Long,TdpCadreVolunteerVO> volunteersMap = new HashMap<Long,TdpCadreVolunteerVO>();
			
			List<TdpCadreVolunteerVO> returnList = null;
			if(volunteerInfoList != null && volunteerInfoList.size()>0)
			{
				for (Object[] volunteer: volunteerInfoList) 
				{
					TdpCadreVolunteerVO volunteerVO = new TdpCadreVolunteerVO();					
					Long volunteerId = volunteer[0] != null ? Long.valueOf(volunteer[0].toString().trim()):0L;
					if(volunteersMap.get(volunteerId) != null)
					{
						volunteerVO = volunteersMap.get(volunteerId);
					}
					else
					{
						volunteerVO.setId(volunteerId);
						volunteerVO.setName(volunteer[1] != null ? volunteer[1].toString().trim():"");
						volunteerVO.setMobileNo(volunteer[2] != null ? volunteer[2].toString().trim():"");
						volunteerVO.setAddress(volunteer[3] != null ? volunteer[3].toString().trim():"");
						volunteerVO.setInternet(volunteer[5] != null ? volunteer[5].toString().trim():"");
						volunteerVO.setLapTop(volunteer[6] != null ? volunteer[6].toString().trim():"");
						volunteerVO.setSmartPhone3G(volunteer[7] != null ? volunteer[7].toString().trim():"");
						volunteerVO.setSmartPhone2G(volunteer[8] != null ? volunteer[8].toString().trim():"");
						volunteerVO.setConstituencyId(volunteer[9] != null ? volunteer[9].toString().trim():"");
					}
					
					volunteersMap.put(volunteerId, volunteerVO);
				}
			}
			
			if(volunteersMap != null && volunteersMap.size()>0)
			{
				Set<Long> volunteersIds = volunteersMap.keySet();
				List<Long> constituencyIds = new ArrayList<Long>();
				constituencyIds.addAll(volunteersIds);
				List<Object[]> constituencyList = tdpCadreVolunteerConstituencyDAO.getconsituencyListById(constituencyIds);
				Map<Long, List<TdpCadreVolunteerVO>> consituencyMap = new HashMap<Long, List<TdpCadreVolunteerVO>>(0);
				if(constituencyList != null && constituencyList.size()>0)
				{
					for (Object[] volenteer : constituencyList) 
					{
						List<TdpCadreVolunteerVO>  valenteerConstituencyList = new ArrayList<TdpCadreVolunteerVO>();
						Long volunteerId = volenteer[0] != null ? Long.valueOf(volenteer[0].toString().trim()):0L;
						
						if(consituencyMap.get(volunteerId) != null)
						{
							valenteerConstituencyList = consituencyMap.get(volunteerId);
						}
						
						TdpCadreVolunteerVO constituencyVO = new TdpCadreVolunteerVO();
						constituencyVO.setId( volenteer[1] != null ? Long.valueOf(volenteer[1].toString().trim()):0L);
						constituencyVO.setName(volenteer[2] != null ? volenteer[2].toString().trim():"");
						valenteerConstituencyList.add(constituencyVO);
						
						consituencyMap.put(volunteerId, valenteerConstituencyList);
					}
				}
				
				returnList = new ArrayList<TdpCadreVolunteerVO>(0);
				for (Long volunteerId : volunteersMap.keySet()) 
				{
					TdpCadreVolunteerVO finalVO = volunteersMap.get(volunteerId);
					if(finalVO != null)
					{
						finalVO.setTdpCadreVolunteerVOList(consituencyMap.get(volunteerId));
						
						returnList.add(finalVO);
					}
				}
				
				System.out.println(returnList);
			}
		*/} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
