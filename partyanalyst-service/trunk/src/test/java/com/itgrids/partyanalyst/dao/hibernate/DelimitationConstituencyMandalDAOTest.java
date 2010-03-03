package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyMandalDAOTest extends BaseDaoTestCase{

	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	
	public void testFindMandalsForConstituency(){
		List mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(232l);
		List<VotersWithDelimitationInfoVO> votersWithDelimitationInfoVOList = new ArrayList<VotersWithDelimitationInfoVO>();
		List<VotersInfoForMandalVO> votersInfoForMandalList = new ArrayList<VotersInfoForMandalVO>();
				
		for(int j = 0; j < mandalsList.size(); j++)
		{			
			Object[] obj = (Object[]) mandalsList.get(j);
			String parialData = obj[3].toString();
			System.out.println(parialData+" j value = "+ j);
		}
		
		System.out.println("-------");
		Map<String, String> mandalsIdsYear = new HashMap<String, String>();
		for (int i = 0; i < mandalsList.size(); i++) 
		{
			Object[] obj = (Object[]) mandalsList.get(i);			
			String year = obj[2].toString();
			String value = mandalsIdsYear.get(year);
			StringBuilder ids = new StringBuilder();
			if(value==null){
				ids .append(obj[0].toString());
			}else{
				ids.append(value).append(IConstants.COMMA).append(obj[0].toString());
			}
			mandalsIdsYear.put(year, ids.toString());
			
		}	
		
		System.out.println(mandalsIdsYear);
		
	}
	
}
