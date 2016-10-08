package com.itgrids.partyanalyst.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.IWebServiceHandlerServiceForCadre;

@Path("/cadre")
public class WebServiceHandlerForCadre {
	
	private final static Logger LOG = Logger.getLogger(WebServiceHandlerForCadre.class);
	
	private IWebServiceHandlerServiceForCadre webServiceHandlerServiceForCadre;
	
	
	public void setWebServiceHandlerServiceForCadre( IWebServiceHandlerServiceForCadre webServiceHandlerServiceForCadre) {
		this.webServiceHandlerServiceForCadre = webServiceHandlerServiceForCadre;
	}


    //LOCATIONS RELATED WEB SERVICES
	@GET
	@Path("/getStateWiseDistrict/{stateId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IdAndNameVO> getStateWiseDistrict(@PathParam("stateId") Long stateId){
		
		try{
			  return webServiceHandlerServiceForCadre.getStateWiseDistrict(stateId);
			  
		}catch(Exception e){
			LOG.error("Exception Occured in getStateWiseDistrict() Method, Exception is ",e);
		}
	    return null;
	}
	
	@GET
	@Path("/getDistrictWiseConstituency/{districtId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IdAndNameVO> getDistrictWiseConstituency(@PathParam("districtId") Long districtId){
		
		try{
			  return webServiceHandlerServiceForCadre.getDistrictWiseConstituency(districtId);
			  
		}catch(Exception e){
			LOG.error("Exception Occured in getDistrictWiseConstituency() Method, Exception is ",e);
		}
	    return null;
	}
	
	@GET
	@Path("/getConstitencyWiseTehsil/{consistuencyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IdAndNameVO> getConstitencyWiseTehsil(@PathParam("consistuencyId") Long consistuencyId) {

		try {
			
			 return webServiceHandlerServiceForCadre.getConstitencyWiseTehsil(consistuencyId);

		} catch (Exception e) {
			LOG.error("Exception raised in getConstitencyWiseTehsil method in CadreRegistrationAction Action",e);
		}
		return null;
	}
	
	@GET
	@Path("/getAllPanchayatsForMandal/{mandalOrLocalElectionBodyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IdAndNameVO> getAllPanchayatsForMandal(@PathParam("mandalOrLocalElectionBodyId") Long mandalOrLocalElectionBodyId) {

		try {
			
			 return webServiceHandlerServiceForCadre.getAllPanchayatsForMandal(mandalOrLocalElectionBodyId);

		} catch (Exception e) {
			LOG.error("Exception raised in getAllPanchayatsForMandal method in CadreRegistrationAction Action",e);
		}
		return null;
	}
	
	@GET
	@Path("/getAllBoothsForPanchayat/{panchayatId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IdAndNameVO> getAllBoothsForPanchayat(@PathParam("panchayatId") Long panchayatId) {

		try {
			
			 return webServiceHandlerServiceForCadre.getAllBoothsForPanchayat(panchayatId);

		} catch (Exception e) {
			LOG.error("Exception raised in getAllBoothsForPanchayat method in CadreRegistrationAction Action",e);
		}
		return null;
	}
	
	
}
