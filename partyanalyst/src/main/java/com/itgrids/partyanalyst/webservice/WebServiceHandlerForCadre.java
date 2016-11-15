package com.itgrids.partyanalyst.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.CardPrintValidationUserVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.dto.WebServiceCadreVO;
import com.itgrids.partyanalyst.service.IWebServiceHandlerServiceForCadre;

@Path("/cadre")
public class WebServiceHandlerForCadre {
	
	private final static Logger LOG = Logger.getLogger(WebServiceHandlerForCadre.class);
	
	private IWebServiceHandlerServiceForCadre webServiceHandlerServiceForCadre;
	
	
	public void setWebServiceHandlerServiceForCadre( IWebServiceHandlerServiceForCadre webServiceHandlerServiceForCadre) {
		this.webServiceHandlerServiceForCadre = webServiceHandlerServiceForCadre;
	}


    //LOCATIONS RELATED
	
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
	
	//NEW RELATED
	@POST
	@Path("/getVotersBySearch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<VoterSearchVO> getVotersBySearch(WebServiceCadreVO inputVO){      
		try {
			 
			 List<VoterSearchVO> votersList =  webServiceHandlerServiceForCadre.getVotersBySearch(inputVO);
             return votersList;
		} catch (Exception e) {
			LOG.error("Exception raised in getVotersBySearch() in WebServiceHandlerForCadre class",e);
		}
		return null;
	}
	
	
	@POST
	@Path("/getRegistrationPersonDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public NewCadreRegistrationVO getRegistrationPersonDetails(WebServiceCadreVO inputVO){ 
		NewCadreRegistrationVO newCadreRegistrationVO = null;
		try {
			 
			newCadreRegistrationVO = webServiceHandlerServiceForCadre.getRegistrationPersonDetails(inputVO);
              return newCadreRegistrationVO;
		} catch (Exception e) {
			LOG.error("Exception raised in getRegistrationPersonDetails() in WebServiceHandlerForCadre class",e);
		}
		return null;
	}
	
	//RENEWAL RELATED
	
	@POST
	@Path("/getTdpCadresBySearch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<TdpCadreVO> getTdpCadresBySearch(WebServiceCadreVO inputVO){ 
		List<TdpCadreVO> cadreList = null;
		try {
			 
			  cadreList = webServiceHandlerServiceForCadre.getTdpCadresBySearch(inputVO);
              return cadreList;
		} catch (Exception e) {
			LOG.error("Exception raised in getTdpCadresBySearch() in WebServiceHandlerForCadre class",e);
		}
		return null;
	}
	
	
	//CADRE IMAGE RELATED
	@POST
	@Path("/saveCadreImage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ImageCadreVO saveCadreImage(ImageCadreVO inputVO){ 
		try {
			 
			   webServiceHandlerServiceForCadre.saveCadreImage(inputVO);
              return inputVO;
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreImage() in WebServiceHandlerForCadre class",e);
		}
		return inputVO;
	}
	
	
	//PRINTING APP CALLS
	@GET
	@Path("/validateCardPrintUserLogin/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public CardPrintValidationUserVO validateCardPrintUserLogin(@PathParam("username") String username,@PathParam("password") String password){
		CardPrintValidationUserVO cardPrintValidationUserVO  = null;
		try{
			 
			cardPrintValidationUserVO = webServiceHandlerServiceForCadre.validateCardPrintUserLogin(username,password);
			
		}catch(Exception e){
			LOG.error("Exception raised in checkCardPrintValidationUserLogin() in WebServiceHandlerForCadre class",e);
		}
		return cardPrintValidationUserVO;
	}
	
	@GET
	@Path("/tdpCadrePrintDetails/{memberShipId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void tdpCadrePrintDetails(@PathParam("memberShipId") String memberShipId){
		//TdpCadrePrintDetailsVO
		try{
			
			
		}catch(Exception e){
			LOG.error("Exception raised in tdpCadrePrintDetails() in WebServiceHandlerForCadre class",e);
		}
	}
}
