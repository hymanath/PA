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

import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.dto.CardPrintValidationUserVO;
import com.itgrids.partyanalyst.dto.CardPrintValidationVO;
import com.itgrids.partyanalyst.dto.CardPrintingDispatchVO;
import com.itgrids.partyanalyst.dto.CardsValidateVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.PrintStatusUpdateVO;
import com.itgrids.partyanalyst.dto.PrintUpdateDetailsStatusVO;
import com.itgrids.partyanalyst.dto.PrintVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadrePrintDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.dto.WebServiceCadreVO;
import com.itgrids.partyanalyst.service.ICardPrintService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerServiceForCadre;

@Path("/cadre")
public class WebServiceHandlerForCadre {
	
	private final static Logger LOG = Logger.getLogger(WebServiceHandlerForCadre.class);
	
	private IWebServiceHandlerServiceForCadre webServiceHandlerServiceForCadre;
	private ICardPrintService cardPrintService;
	
	public void setWebServiceHandlerServiceForCadre( IWebServiceHandlerServiceForCadre webServiceHandlerServiceForCadre){
		this.webServiceHandlerServiceForCadre = webServiceHandlerServiceForCadre;
	}

    //LOCATIONS RELATED
	
	public ICardPrintService getCardPrintService() {
		return cardPrintService;
	}


	public void setCardPrintService(ICardPrintService cardPrintService) {
		this.cardPrintService = cardPrintService;
	}


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
	
	
	//QA APP CALLS
	
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
	public TdpCadrePrintDetailsVO getTdpCadrePrintDetailsByMemberShipId(@PathParam("memberShipId") String memberShipId){
		TdpCadrePrintDetailsVO tdpCadrePrintDetailsVO = null;
		try{
			tdpCadrePrintDetailsVO = webServiceHandlerServiceForCadre.getTdpCadrePrintDetailsByMemberShipId(memberShipId);
			
		}catch(Exception e){
			LOG.error("Exception raised in tdpCadrePrintDetails() in WebServiceHandlerForCadre class",e);
		}
		return tdpCadrePrintDetailsVO;
	}
	
	@POST
	@Path("/updateCardPrintValidStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultStatus updateCardPrintValidStatus(CardPrintValidationVO inputVO){
		ResultStatus rs = null;
		try{
			rs  =  webServiceHandlerServiceForCadre.updateCardPrintValidStatus(inputVO);
			
		}catch(Exception e){
			LOG.error("Exception raised in updateCardPrintValidStatus() in WebServiceHandlerForCadre class",e);
		}
		return rs;
	}
	
	
	//PRINT DETAILS SYNCHING To TdpCadreCardPrint
	@POST
	@Path("/updatePrintDetailsToTdpCadreCardPrint")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PrintUpdateDetailsStatusVO  updatePrintDetailsToTdpCadreCardPrint(List<PrintVO> printList){
		PrintUpdateDetailsStatusVO finalVO = null;
		try{
			
			finalVO = cardPrintService.updatePrintDetailsToTdpCadreCardPrint(printList);
			
		}catch(Exception e){
			LOG.error("Exception raised in updatePrintDetailsToTdpCadreCardPrint() in WebServiceHandlerForCadre class",e);
		}
		return finalVO;
	}
	
	@POST
	@Path("/syncConstituencyPrintStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	public PrintUpdateDetailsStatusVO syncConstituencyPrintStatus(PrintStatusUpdateVO inputVO){
		PrintUpdateDetailsStatusVO finalVO = null;
        try{
			
        	ResultStatus status = cardPrintService.saveConstituencyPrintStatus(inputVO);
			if(status != null){
				finalVO = new PrintUpdateDetailsStatusVO();
				finalVO.setResultCode(status.getResultCode());
				finalVO.setMessage(status.getExceptionMsg());
			}
		}catch(Exception e){
			finalVO = new PrintUpdateDetailsStatusVO();
			finalVO.setResultCode(0);
			finalVO.setMessage("Exception Occurred At Server Side.Try Later..");
			LOG.error("Exception raised in syncConstituencyPrintStatus() in WebServiceHandlerForCadre class",e);
		}
		return finalVO;
	}
	
	//QA Verification dashboard ws for local project start.
	
	@GET
	@Path("/getDistrictList/{vendorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CardPrintVO> getDistrictList(@PathParam("vendorId") Long vendorId){
		List<CardPrintVO> distList = null;
		try{
			 distList = cardPrintService.getDstrListByVendor(vendorId);
		}catch(Exception e){
			LOG.error("Exception raised in getDistrictList() in WebServiceHandlerForCadre ",e);
		}
		return distList;
	}
	
	@GET
	@Path("/getConstencyList/{vendorId}/{districtId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CardPrintVO> getConstencyList(@PathParam("vendorId") Long vendorId , @PathParam("districtId") Long districtId){
		List<CardPrintVO> constList = null;
		try{
			constList = cardPrintService.getConstListByVendor(vendorId,districtId);
		}catch(Exception e){
			LOG.error("Exception raised in getConstencyList() in WebServiceHandlerForCadre ",e);
		}
		return constList;
	}
	
	@GET
	@Path("/getPrintingDispatchDetails/{vendorId}/{districtId}/{constituencyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CardPrintingDispatchVO getPrintingDispatchDetails(@PathParam("vendorId") Long vendorId , @PathParam("districtId") Long districtId , @PathParam("constituencyId") Long constituencyId){
		CardPrintingDispatchVO finaVO = null;
		try{
			finaVO = cardPrintService.getPrintingDispatchDetails(vendorId, districtId, constituencyId);
		}catch (Exception e) {
			LOG.error("Exception raised in getPrintingDispatchDetails() in WebServiceHandlerForCadre ",e);
		}
		return finaVO;
	}
	
	//QA Verification dashboard ws for local project end.
	
	//QA APP Android dashboard ws
	@GET
	@Path("/constWiseValidatedCadreByUser/{userId}/{fromDate}/{toDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public CardsValidateVO constWiseValidatedCadreByUser(@PathParam("userId") Long userId, @PathParam("fromDate") String fromDate , @PathParam("toDate") String toDate ){
		CardsValidateVO cardsValidateVO = new CardsValidateVO();
		try{
			
			if(userId != null && userId.longValue() > 0l){
				cardsValidateVO= cardPrintService.constWiseValidatedCadreByUser(userId , fromDate , toDate);
			}else{
				cardsValidateVO.setStatus("Failure");
				cardsValidateVO.setMessage("userId does not exist");
			}
			 
		}catch(Exception e){
			cardsValidateVO.setStatus("Failure");
			cardsValidateVO.setMessage("Exception Occurred..");
			LOG.error("Exception raised in constWiseValidatedCadreByUser() in WebServiceHandlerForCadre ",e);
		}
		return cardsValidateVO;
	}
	
	@GET
	@Path("/boxWiseValidatedCadreByUser/{userId}/{fromDate}/{toDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public CardsValidateVO boxWiseValidatedCadreByUser(@PathParam("userId") Long userId, @PathParam("fromDate") String fromDate , @PathParam("toDate") String toDate ){
		CardsValidateVO cardsValidateVO = new CardsValidateVO();
		try{
				if(userId != null && userId.longValue() > 0l){
					cardsValidateVO= cardPrintService.boxWiseValidatedCadreByUser(userId , fromDate , toDate);
				}else{
					cardsValidateVO.setStatus("Failure");
					cardsValidateVO.setMessage("userId does not exist");
				}
		}catch(Exception e){
			cardsValidateVO.setStatus("Failure");
			cardsValidateVO.setMessage("Exception Occurred..");
			LOG.error("Exception raised in boxWiseValidatedCadreByUser() in WebServiceHandlerForCadre ",e);
		}
		return cardsValidateVO;
	}
	
	
}
