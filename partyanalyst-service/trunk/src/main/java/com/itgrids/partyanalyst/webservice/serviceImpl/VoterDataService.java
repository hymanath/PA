package com.itgrids.partyanalyst.webservice.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.webservice.service.IVoterDataService;
import com.itgrids.partyanalyst.webservice.utils.VoterInputVo;

/**
 * @author Anilkumar Ravula
 *
 */
@Service
public class VoterDataService implements  IVoterDataService{
	
	@Autowired
	private IBoothPublicationVoterDAO  boothPublicationVoterDAo;

	
	
	public  <K,V>Map<K,V> getVotersByBoothIdsandConstituencyIds(VoterInputVo voterInputs)
	{  List<?> voters =null;
		if(voterInputs.getType().equalsIgnoreCase("byBooths"))
		{
	 voters=	boothPublicationVoterDAo.getVoterDetailsByBoothAndConstituency(voterInputs.getBoothIds(), voterInputs.getConstituencyId());
	
		}else
			if(voterInputs.getType().equalsIgnoreCase("byBooth"))
		{
			 voters=	boothPublicationVoterDAo.getVoterDetailsByBoothAndConstituency(voterInputs.getBoothId(), voterInputs.getConstituencyId());
		}
	Map<K,V> votersList=IVoterDataService.BuildVoterVo.buildVoterVo(voters);
		
		return votersList;
	}
	
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAo() {
		return boothPublicationVoterDAo;
	}

	public void setBoothPublicationVoterDAo(
			IBoothPublicationVoterDAO boothPublicationVoterDAo) {
		this.boothPublicationVoterDAo = boothPublicationVoterDAo;
	}
  


}
