package com.itgrids.partyanalyst.webservice;

import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.webservice.service.IVoterDataService;
import com.itgrids.partyanalyst.webservice.utils.VoterInputVo;

/*import com.itgrids.partyanalyst.webservice.service.CadreService;
import com.itgrids.partyanalyst.webservice.utils.CadreUtilVo;*/

/**
 * @author Anilkumar Ravula
 *
 */
@Component
@Path("/VoterData")
//@RolesAllowed("ITG@ANDROID@SMS")
public class VoterDataSync  {
	
	@Autowired
	private IVoterDataService voterDataService;
	
	@POST
    @Path("/Secure/GetVotersByBoothAndConstituency")
	@RolesAllowed("ITG@ANDROID@SMS")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public <K,V>Map<K,V> getVotersByBoothAndConstituency(VoterInputVo voterInputs)
	
    {	
		return voterDataService.getVotersByBoothIdsandConstituencyIds(voterInputs);
    }
	@GET
    @Path("/test")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//@RolesAllowed("ITG@ANDROID@SMS")
	public <K,V>Map<K,V> getTest()
	
    {	
		VoterInputVo voterInputs = new VoterInputVo();
		voterInputs.setBoothId(308399L);
		voterInputs.setConstituencyId(221l);
		voterInputs.setType("byBooth");
		 return voterDataService.getVotersByBoothIdsandConstituencyIds(voterInputs);
    }

	public IVoterDataService getVoterDataService() {
		return voterDataService;
	}

	public void setVoterDataService(IVoterDataService voterDataService) {
		this.voterDataService = voterDataService;
	}

}
