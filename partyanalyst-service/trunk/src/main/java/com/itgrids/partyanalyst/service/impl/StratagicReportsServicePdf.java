package com.itgrids.partyanalyst.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IStrategyAssumptionsDAO;
import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.AssumptionsVO;
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.ImpFamilesVO;
import com.itgrids.partyanalyst.dto.OrderOfPriorityVO;
import com.itgrids.partyanalyst.dto.PDFHeadingAndReturnVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.StrategicCensusVO;
import com.itgrids.partyanalyst.dto.StrategyVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.model.StrategyAssumptions;
import com.itgrids.partyanalyst.service.IBoothwisePollingStratagicService;
import com.itgrids.partyanalyst.service.IStratagicReportServiceForMLASuccess;
import com.itgrids.partyanalyst.service.IStratagicReportsService;
import com.itgrids.partyanalyst.service.IStratagicReportsServicePdf;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author Anilkumar Mar 20, 2014
 *
 */
public class StratagicReportsServicePdf implements IStratagicReportsServicePdf{
	private static final Logger LOG = Logger.getLogger(StratagicReportsServicePdf.class);
	// Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
	 Font  TITLE = FontFactory.getFont("Calibri",9,Font.BOLD);
	  BaseColor bcolor=BaseColor.YELLOW;
	  BaseColor subHeading= new BaseColor(69,109,142);
	  Font SMALLFONT = FontFactory.getFont("Calibri",9,Font.NORMAL);
	  Font param = FontFactory.getFont("Calibri",12,Font.BOLD);
	
	@Autowired 
	public IStratagicReportsService stratagicReportsService;
	
	@Autowired 	
	private IStratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess;
	
	@Autowired
	
	private IBoothwisePollingStratagicService  boothwisePollingStratagicService;
	
	@Autowired
	public ISuggestiveModelService suggestiveModelService;
	
	
	@Autowired
	private IStrategyModelTargetingService strategyModelTargetingService;
	
	@Autowired
	
	private IConstituencyDAO  constituencyDAO;

	@Autowired
	IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
	@Autowired
	IStrategyAssumptionsDAO strategyAssumptionsDAO;
	
	
	private enum PdfPages{
		prevConst,prevPar,prevMptcZptc,census,hoseHolds,voterInfo,firstTimeVoters, votersAgeGroup, votersCaste,panchayatVoterDensity, voterAdditionAndDeletion, genderWiseVoterModification, ageGroup,pollingPercent, boothWiseAddedDelList, delimitationEffect, assumptions, localityAddedDeleted 
		,selectedCastes,totalCastesOrder,partyPerformance,previousTrends,youngCastes,agedCastes,otherPartyEffect,panchayatsClassification,impfamilesList,finalOrderOfOriority,constId
	}
	
	
	//serialization utility class
	public class Serialize<E> 
	{
     public  String  serialize(String fileName,E v) throws IOException 
	
	{
    	 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
    	 String path = IConstants.STATIC_CONTENT_FOLDER_URL+"Strategy"+pathSeperator+"temp"+pathSeperator+fileName+".ser";
		FileOutputStream fileOut =
		         new FileOutputStream(path);
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(v);
		         out.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in /tmp/employee.ser");
		         return  fileName;
		
	}
	}
	 public static  String  serialize(String fileName,Object obj,boolean autoStrategy) throws IOException 
		
		{
		 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			FileOutputStream fileOut =null;
			try{
				String path = "";
				if(autoStrategy){
					 path =  IConstants.STATIC_CONTENT_FOLDER_URL+"DynamicReports"+pathSeperator+"temp"+pathSeperator+fileName+".ser";
				 }else{
					 path =  IConstants.STATIC_CONTENT_FOLDER_URL+"Strategy"+pathSeperator+"temp"+pathSeperator+fileName+".ser";
				 }
			fileOut =new FileOutputStream(path);
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(obj);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in"+fileName);
			         return  fileName;
			}catch (Exception e) {
				LOG.error("exception rised in serialize ",e);
				fileOut=null;
			}finally{
				if(fileOut!=null)
					fileOut.close();
			}
			return  "";
		}
	// desrialization utility calss
	public class DeSerialize<E> 
	{
		  public   E deSerialize(String fileName,boolean autoStrategy) throws IOException, ClassNotFoundException 
			
			{
			
			  String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			  String path="";
			  if(autoStrategy){
				  path =  IConstants.STATIC_CONTENT_FOLDER_URL+"DynamicReports"+pathSeperator+"temp"+pathSeperator+fileName+".ser";
				 }else{
		    	  path = IConstants.STATIC_CONTENT_FOLDER_URL+"Strategy"+pathSeperator+"temp"+pathSeperator+fileName+".ser";
				 }
			 FileInputStream fileIn = new FileInputStream(path);
		     ObjectInputStream in = new ObjectInputStream(fileIn);
		     E vos   =  (E) in.readObject();
		    // System.out.println(vos);
		    // System.out.println(vos.getSubVo().size());
		     in.close();
		     fileIn.close();
				return vos;
			}
	}

	public Object buildPdf(StrategyVO inputVo) {
		
		return null;
	}
	
	public void buildAutoStrategy(Long constituencyId){
		StrategyVO strategyVO = strategyModelTargetingService.getStrategyArguments(constituencyId);
		strategyVO.setAutoStrategy(true);
		List<StrategyAssumptions> assumptionsList =  strategyAssumptionsDAO.getDataByConstituencyId(constituencyId);
		if(assumptionsList != null && assumptionsList.size() > 0 && assumptionsList.get(0) != null){
			StrategyAssumptions model = assumptionsList.get(0);
			strategyVO.setGoalDataPresent(true);
			strategyVO.setBase(model.getExceptedPollingPerc());
			strategyVO.setAssured(model.getVoterBasePerc());
			strategyVO.setTdpPerc(model.getTargetedVotesPerc());
		}
		buildPdfDelegator(strategyVO);
	}
	
	public Object buildPdfDelegator(StrategyVO strategyVO) {
		
		int noOfPages=29;
		
		//serailization of data 
		
		 Map<PdfPages,String> maps =new HashMap<PdfPages, String>();
		
		serializationOfObjects(strategyVO,maps);
		//deserilization of data
		
		Object url = DeserializationOfObjects(maps,strategyVO.getConstituencyId(),strategyVO.isAutoStrategy(),strategyVO.isGoalDataPresent());
		//build pdf 
		
		return url;
	}
	
	//
	public Object serializationOfObjects(StrategyVO strategyVO,Map<PdfPages,String> maps)
	{
		
		 Long constId=strategyVO.getConstituencyId();
		 Long publicationDateId=strategyVO.getPublicationId();
		 Long userId=1L;
		 Long partyId=strategyVO.getPartyId();
		 Long electionId1=strategyVO.getElectionIds().get(1);
		 Long electionId=strategyVO.getElectionIds().get(0);
		 String locationType=IConstants.CONSTITUENCY;
		 Long locationValue=constId,constituencyId=constId,fromPublicationDateId=publicationDateId-1,toPublicationDateId=publicationDateId;
		
		//asumptions
		 Long base=strategyVO.getBase();
		 Long assured=strategyVO.getAssured();
		 Long tdpPerc=strategyVO.getTdpPerc();
		//maps.put(PdfPages.prevConst, "prev1");
		//maps.put(PdfPages.prevPar, "prev1");
        String defaultFileName;
		try{
	   //page-4
		    //previous trends 
		 
			String uidentifier = new Random().nextInt(100000000)+"";
		     //previuos ternds in constituency
		
		maps.put(PdfPages.constId, constId.toString());
		defaultFileName="prev1"+uidentifier;
		List<PartyElectionTrendsReportVO> resForPrevTrends=stratagicReportServiceForMLASuccess.getPreviousTrendsReport(constId);	                    
		defaultFileName=serialize(defaultFileName, resForPrevTrends,strategyVO.isAutoStrategy());
		maps.put(PdfPages.prevConst, defaultFileName);
		
		resForPrevTrends=null;
		//previous trends in parliament
		
		defaultFileName="prev2"+uidentifier;
		List<PartyElectionTrendsReportVO> resForPrevTrendsForPaliament=stratagicReportServiceForMLASuccess.getPreviousTrendsReportParliament(constId);
		defaultFileName =   serialize(defaultFileName, resForPrevTrendsForPaliament,strategyVO.isAutoStrategy());
		maps.put(PdfPages.prevPar, defaultFileName);

		resForPrevTrendsForPaliament=null;
		
		
	//page-5
		
		  //previous trends in mptc and zptc
		 
		defaultFileName="mptcZptc"+uidentifier;
		PartyResultsVerVO mptcZptcResults=resultsForMptcZptcElections(constId);
		defaultFileName =   serialize(defaultFileName, mptcZptcResults,strategyVO.isAutoStrategy());
		maps.put(PdfPages.prevMptcZptc, defaultFileName);
		
		
   //apge-6 
		
		//census
	   defaultFileName="census"+uidentifier;
		StrategicCensusVO cVo=	stratagicReportServiceForMLASuccess.getCensusDetailsForAConstituency(constId);
		List<StrategicCensusVO> objs=cVo.getCensusDetailsList();
		   if(objs.size()>2)
				objs.remove(2);
		defaultFileName =   serialize(defaultFileName, cVo,strategyVO.isAutoStrategy());
		maps.put(PdfPages.census, defaultFileName);
		cVo=null;
  //page-7
		
		//households
		defaultFileName="houses"+uidentifier;
		HouseHoldsVO hvo=stratagicReportServiceForMLASuccess.getHouseHoldInfoByConstituency(null, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, hvo,strategyVO.isAutoStrategy());
		maps.put(PdfPages.hoseHolds, defaultFileName);
		hvo=null;
		
		//voters
		defaultFileName="vinfo"+uidentifier;
		VoterStratagicReportVo vinfo=stratagicReportServiceForMLASuccess.getVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, vinfo,strategyVO.isAutoStrategy());
		maps.put(PdfPages.voterInfo, defaultFileName);
		vinfo=null;
		
		//firsttime voters
		defaultFileName="firstTimeVoters"+uidentifier;
		vinfo=stratagicReportServiceForMLASuccess.getFirstTimeVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, vinfo,strategyVO.isAutoStrategy());
		maps.put(PdfPages.firstTimeVoters, defaultFileName);
		vinfo=null;
		
		//voters by age group
		defaultFileName="votersAgeGroup"+uidentifier;
		vinfo=stratagicReportServiceForMLASuccess.getAgeWiseVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, vinfo,strategyVO.isAutoStrategy());
		maps.put(PdfPages.votersAgeGroup, defaultFileName);
		vinfo=null;
	//page-8
		
		//voters by caste
		defaultFileName="votersCaste"+uidentifier;
		CasteStratagicReportVO cvo=stratagicReportServiceForMLASuccess.getCasteWiseVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, cvo,strategyVO.isAutoStrategy());
		maps.put(PdfPages.votersCaste, defaultFileName);
		cvo=null;
		
		//voter-density vs panchayats
		defaultFileName="panchayatVoterDensity"+uidentifier;
		VoterDensityWithPartyVO voterDensityWithPartyVO = stratagicReportsService.getVotersCountInPanchayatsForDensity(constId,publicationDateId);
		defaultFileName =   serialize(defaultFileName, voterDensityWithPartyVO,strategyVO.isAutoStrategy());
		maps.put(PdfPages.panchayatVoterDensity, defaultFileName);
		voterDensityWithPartyVO=null;
		
		//page-9
		
		//delimitationeffect
		defaultFileName="delimitationEffect"+uidentifier;
		DelimitationEffectVO delimitationEffectVO=stratagicReportsService.getDelimationEffectOnConstituency(constId,partyId);
		defaultFileName =   serialize(defaultFileName, delimitationEffectVO,strategyVO.isAutoStrategy());
		maps.put(PdfPages.delimitationEffect, defaultFileName);
		
		//assumptions
		if(!strategyVO.isAutoStrategy() || strategyVO.isGoalDataPresent()){
			defaultFileName="assumptions"+uidentifier;
			AssumptionsVO assumptionsVO=stratagicReportsService.votersAssumptionsService( constId,base,assured,publicationDateId,tdpPerc);
			defaultFileName =   serialize(defaultFileName, assumptionsVO,strategyVO.isAutoStrategy());
			maps.put(PdfPages.assumptions, defaultFileName);
		}
		
		
		
		//page-10 
		//targetting key factors
		
		//18111s
		
		//page 13 to 23
		try{
		List<Object> targetingAreas = strategyModelTargetingService.getPrioritiesToTarget(strategyVO, true);
		Map<String,Float> casteNamePercMap =  (Map<String,Float>)targetingAreas.get(0);//1
		List<PanchayatVO> totalCastesList = (List<PanchayatVO>)targetingAreas.get(1);//2
		List<PartyPositionVO> partyPerformance = (List<PartyPositionVO>)targetingAreas.get(2);//3
		List<PartyPositionVO> previousTrends = (List<PartyPositionVO>)targetingAreas.get(3);//4
		List<PanchayatVO> youngCastesList = (List<PanchayatVO>)targetingAreas.get(4);//5
		List<PanchayatVO> agedCastesList = (List<PanchayatVO>)targetingAreas.get(5);//6
		List<PartyEffectVO> otherPartyEffect = (List<PartyEffectVO>)targetingAreas.get(6);//7
		List<OrderOfPriorityVO> panchayatsClassification = (List<OrderOfPriorityVO>)targetingAreas.get(7);//8
		List<ImpFamilesVO> impfamilesList = (List<ImpFamilesVO>)targetingAreas.get(8);//9
		List<OrderOfPriorityVO> finalOrderOfOriority = (List<OrderOfPriorityVO>)targetingAreas.get(9);//10
		targetingAreas = null;
		//page-21
		
		    defaultFileName="selectedCastes"+uidentifier;		 
			defaultFileName =   serialize(defaultFileName, casteNamePercMap,strategyVO.isAutoStrategy());
			maps.put(PdfPages.selectedCastes, defaultFileName);
			casteNamePercMap=null;
		
			defaultFileName="totalCastesOrder"+uidentifier;
			defaultFileName =   serialize(defaultFileName, totalCastesList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.totalCastesOrder, defaultFileName);
			totalCastesList=null;
				
			defaultFileName="partyPerformance"+uidentifier;
			defaultFileName =   serialize(defaultFileName,partyPerformance,strategyVO.isAutoStrategy());
			maps.put(PdfPages.partyPerformance, defaultFileName);
			partyPerformance=null;
			
		    defaultFileName="previousTrends"+uidentifier;		 
			defaultFileName =   serialize(defaultFileName, previousTrends,strategyVO.isAutoStrategy());
			maps.put(PdfPages.previousTrends, defaultFileName);
			previousTrends=null;
		
			defaultFileName="youngCastes"+uidentifier;
			defaultFileName =   serialize(defaultFileName, youngCastesList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.youngCastes, defaultFileName);
			youngCastesList=null;
				
			defaultFileName="agedCastes"+uidentifier;
			defaultFileName =   serialize(defaultFileName,agedCastesList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.agedCastes, defaultFileName);
			agedCastesList=null;
			
			defaultFileName="otherPartyEffect"+uidentifier;
			defaultFileName =   serialize(defaultFileName,otherPartyEffect,strategyVO.isAutoStrategy());
			maps.put(PdfPages.otherPartyEffect, defaultFileName);
			otherPartyEffect=null;
			
		    defaultFileName="panchayatsClassification"+uidentifier;		 
			defaultFileName =   serialize(defaultFileName, panchayatsClassification,strategyVO.isAutoStrategy());
			maps.put(PdfPages.panchayatsClassification, defaultFileName);
			panchayatsClassification=null;
		
			defaultFileName="impfamilesList"+uidentifier;
			defaultFileName =   serialize(defaultFileName, impfamilesList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.impfamilesList, defaultFileName);
			impfamilesList=null;
				
			defaultFileName="finalOrderOfOriority"+uidentifier;
			defaultFileName =   serialize(defaultFileName,finalOrderOfOriority,strategyVO.isAutoStrategy());
			maps.put(PdfPages.finalOrderOfOriority, defaultFileName);
			finalOrderOfOriority=null;		
			//targetting key factors ends
		}catch (Exception e) {
			LOG.error("Exception rised in ",e);
		}
		//-->Voters Additions & Deletions
		 
		
		   defaultFileName="voterAdditionAndDeletion"+uidentifier;
		   PDFHeadingAndReturnVO voterAgeRangeVOList = stratagicReportsService.getVoterInfoByPublicationDateList(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
			defaultFileName =   serialize(defaultFileName, voterAgeRangeVOList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.voterAdditionAndDeletion, defaultFileName);
			voterAgeRangeVOList=null;
		 //stratagicReportsService.generatePDFForVoterInfo(voterAgeRangeVOList,"voterInfo");
		
		//-->Gender Wise Voter Modifications between Electoral Roll 2013 - Draft To Electoral Roll 2013 – Final
		 defaultFileName="genderWiseVoterModification"+uidentifier;
		 PDFHeadingAndReturnVO voterModificationAgeRangeVOList = stratagicReportsService.getGenderWiseVoterModificationsForEachPublication(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			defaultFileName =   serialize(defaultFileName, voterModificationAgeRangeVOList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.genderWiseVoterModification, defaultFileName);
			voterModificationAgeRangeVOList=null;
		 //stratagicReportsService.generatePDFForVoterInfo(voterModificationAgeRangeVOList,"addedDeleted");
		
		//--->Age Group
		 defaultFileName="ageGroup"+uidentifier;
		 PDFHeadingAndReturnVO voterModificationGenderInfoVOList = stratagicReportsService.getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			defaultFileName =   serialize(defaultFileName, voterModificationGenderInfoVOList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.ageGroup, defaultFileName);
			voterModificationGenderInfoVOList=null;
		 //stratagicReportsService.generatePDFForVoterInfo(voterModificationGenderInfoVOList,"genderWise");
			
			
			/*DeSerialize<List<AgeRangeVO>>  dboothWiseAddedDelList=new DeSerialize<List<AgeRangeVO>>();
			  List<AgeRangeVO> boothWiseAddedDelList = dboothWiseAddedDelList.deSerialize( maps.get(PdfPages.boothWiseAddedDelList) );

			  stratagicReportsService.generateBoothWiseAddedDeletedVoters(boothWiseAddedDelList,document);*/
			defaultFileName="boothWiseAddedDelList"+uidentifier;
			List<AgeRangeVO> boothWiseAddedDelList =stratagicReportsService.getBoothWiseAddedAndDeletedVoters(constituencyId,publicationDateId);
		
			defaultFileName =   serialize(defaultFileName, boothWiseAddedDelList,strategyVO.isAutoStrategy());
			maps.put(PdfPages.boothWiseAddedDelList, defaultFileName);
			
		//-->locality wise added deleted 
			
			defaultFileName="localityAddedDeleted"+uidentifier;
			VoterModificationVO voterModificationVO=stratagicReportsService.getSubLevelsVoterModificationDetailsByLocationValue(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
			defaultFileName =   serialize(defaultFileName, voterModificationVO,strategyVO.isAutoStrategy());
			maps.put(PdfPages.localityAddedDeleted, defaultFileName);
		
	//page-23
		//Polling Stations – Increase Polling %
		  defaultFileName="boothPolling"+uidentifier;
		 List<PartyPositionVO> polling= suggestiveModelService.getPollingPercentagesByParty(constId, partyId, electionId, electionId1,"","");
		  defaultFileName =   serialize(defaultFileName, polling,strategyVO.isAutoStrategy());
			maps.put(PdfPages.pollingPercent, defaultFileName);
		}catch (Exception e) {
			LOG.error("exception rised in serializationOfObjects ",e);
			//logic delete all created files
		}
		//logic delete all created files when exception raised
		
		return null;
	}
	
	public Object DeserializationOfObjects(Map<PdfPages,String> maps,Long constituencyId,boolean autoStrategy,boolean goalDataPresent) 
	{
		 //page-4
	    //previous trends 
	/*	Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
		  subHeading.setColor(new BaseColor(69,109,142)); */
		   Document document=null;
		   String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			 String uidentifier = new Random().nextInt(100000000)+"";
			 String path ="";
			 if(autoStrategy){
				 path = IConstants.STATIC_CONTENT_FOLDER_URL+"DynamicReports"+pathSeperator+"pdfs";
			 }else{
				 path = IConstants.STATIC_CONTENT_FOLDER_URL+"Strategy"+pathSeperator+"pdfs";
			 }
			 Long constiNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId, 2009l);
			  String filePath = path+pathSeperator+constiNo+"_"+constituencyDAO.get(constituencyId).getName()+"_"+uidentifier+".pdf";
			   String url = "Strategy"+pathSeperator+"pdfs"+pathSeperator+constiNo+"_"+constituencyDAO.get(constituencyId).getName()+"_"+uidentifier+".pdf";
		try{
		
		 
		//basic pdf blocks
		   File f =new File(path);
		   

		    if( !f.exists())
			f.mkdirs();
		     document = new Document();
		    // 6 Step MLA Success Strategy
		    // C o n f i d e n t i a l
		    // © Copyright 2014. All rights reserved | TELUGU DESAM PARTY
		   /*  HeaderFooter header = new HeaderFooter(new Phrase("Add Header Part Here"), false);  
		     HeaderFooter footer = new HeaderFooter(new Phrase("Add Footer Here"), new Phrase(".")); */
		     
		    PdfWriter writer=	PdfWriter.getInstance(document, new FileOutputStream(filePath));
			 document.open();
			 
			 int indentation = 0;
				
			   Image image = Image.getInstance(path+pathSeperator+"indeximage.jpg");
			 float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
			                - document.rightMargin() - indentation) / image.getWidth()) * 100;

			 image.scalePercent(scaler);
			    
		        document.add(image);
		        
		        document.newPage();
			 buildPreviousTrendsHeadings(document);
			 String name=constituencyDAO.getConstituencyNameByConstituencyId(Long.valueOf(maps.get(PdfPages.constId)));
			 
			    String constituencyName=name+" Constituency";
			  
				String heading="Previous Election Results in "+constituencyName;
				 
	
		DeSerialize<List<PartyElectionTrendsReportVO>> des =new DeSerialize<List<PartyElectionTrendsReportVO>>();
	   List<PartyElectionTrendsReportVO> resForPrevTrends=des.deSerialize( maps.get(PdfPages.prevConst),autoStrategy );	                    
	   buildPdfForPrevTrends( maps.get(PdfPages.prevConst), resForPrevTrends, document, writer, heading);
	   resForPrevTrends=null;
	   des=null;
	 //previous trends in parliament
	
	   DeSerialize<List<PartyElectionTrendsReportVO>> des1 =new DeSerialize<List<PartyElectionTrendsReportVO>>();
	   List<PartyElectionTrendsReportVO> resForPrevTrendsForPaliament=des1.deSerialize( maps.get(PdfPages.prevPar),autoStrategy );	
	   String parliamentName=name+" Segment";
	   String heading2="Parliament Results In "+parliamentName;
	   buildPdfForPrevTrends( maps.get(PdfPages.prevPar), resForPrevTrendsForPaliament, document, writer, heading2);
	   resForPrevTrendsForPaliament=null;
	   des1=null;
	   
	//maps.put(PdfPages.prevPar, defaultFileName);
  
	
//page-5
	   document.newPage();
	  //previous trends in mptc and zptc
	   //buildSubHeading(document, "Zilla and Mandal Parishad Elections Results"); 
	   DeSerialize<PartyResultsVerVO> dmptcZptcResults =new DeSerialize<PartyResultsVerVO>();
	   PartyResultsVerVO mptcZptcResults =dmptcZptcResults.deSerialize( maps.get(PdfPages.prevMptcZptc),autoStrategy );
	   
	   stratagicReportsService.generatePdfForLocalElectionResults(mptcZptcResults,document);
	   mptcZptcResults=null;
	   dmptcZptcResults=null;
	   
//apge-6 
	//census
	   document.newPage();
	   buildSubHeading(document, "Census “A Snapshot”");
	   DeSerialize<StrategicCensusVO> cdes =new DeSerialize<StrategicCensusVO>();
	  StrategicCensusVO cVo =cdes.deSerialize( maps.get(PdfPages.census),autoStrategy );
	  try{
	  buildPdfForCensusData(cVo, document, writer, cVo.getMessage());
	  if(cVo != null && cVo.getConclusion() != null){
	  String[] conclusions= cVo.getConclusion().split(",");
	 
	
		 
	  document.add(Chunk.NEWLINE);
	  com.itextpdf.text.ZapfDingbatsList orderedList = new com.itextpdf.text.ZapfDingbatsList(108, 10);
      
	  for (String string : conclusions) {
		
		  orderedList.add(new ListItem(new Paragraph("  "+string.trim(),SMALLFONT)));
	}
	  orderedList.getIndentationRight();
	 Paragraph p =new Paragraph("");
	 p.add(orderedList);
	 p.setAlignment(Paragraph.ALIGN_RIGHT);
     document.add(p);
	  }
	  }catch (Exception e) {
		  LOG.error("Exception rised in ",e);
	}
	   cdes=null;
	   cVo=null;
	   
		
//page-7
	
	//households
	   document.newPage();
		 buildSubHeading(document, "Households");
	 DeSerialize<HouseHoldsVO> dhvo =new DeSerialize<HouseHoldsVO>();
	 HouseHoldsVO hvo=dhvo.deSerialize( maps.get(PdfPages.hoseHolds),autoStrategy );
     buildPdfForHouseHolds( hvo, document, writer, hvo.getMessage());

	 dhvo=null;hvo=null;
	
	//voters
	 buildSubHeading(document, "Voters");
	 DeSerialize<VoterStratagicReportVo> dvinfo =new DeSerialize<VoterStratagicReportVo>();
	 VoterStratagicReportVo vinfo =dvinfo.deSerialize( maps.get(PdfPages.voterInfo),autoStrategy );
	 buildPdfForVotersInfo(vinfo, document, writer, vinfo.getMessage());
	
	//firsttime voters
	  buildSubHeading(document, "First Time Voters");
	  vinfo =dvinfo.deSerialize( maps.get(PdfPages.firstTimeVoters),autoStrategy );
	  if(vinfo !=null)
	 buildPdfForFirstTimeVotersAndVotersByAgeGroup(vinfo, document, writer, "");
      
	  vinfo=null;
	
	//voters by age group
	
	 buildSubHeading(document, "Voters by Age Group");
     vinfo =dvinfo.deSerialize( maps.get(PdfPages.votersAgeGroup),autoStrategy );
	 buildPdfForFirstTimeVotersAndVotersByAgeGroup(vinfo, document, writer, "");

	 vinfo=null;
	
//page-8
	 document.newPage();
	//voters by caste  
	// buildSubHeading(document, "Voters by Caste");
	 
	 DeSerialize<CasteStratagicReportVO>  dcvo=new DeSerialize<CasteStratagicReportVO>();
	 CasteStratagicReportVO cvo =dcvo.deSerialize( maps.get(PdfPages.votersCaste),autoStrategy );
	 if(cvo!=null)
	 {
		 List<String> columnNames = new ArrayList<String>();
	     columnNames.add("Caste");
	     columnNames.add("Caste Category");
	     columnNames.add("Voters");
	     columnNames.add("Male Voters");
	     columnNames.add("Female Voters ");
	     columnNames.add("Caste Percentage ");
	     
	 buildPdfForCasteVoters(cvo, document, writer, "Voters by Caste", columnNames);
	
	 
	 }
	 cvo=null;
	 dcvo=null;
	 
	 //panchayat voters density
	 buildSubHeading(document, "Voter Density Vs Panchayaths – Along with 2013 Panchayath Results"); 
	   // VoterDensityWithPartyVO voterDensityWithPartyVO = stratagicReportsService.getVotersCountInPanchayatsForDensity(constituencyId,publicationId);
	  DeSerialize<VoterDensityWithPartyVO>  dvoterDensityWithPartyVO=new DeSerialize<VoterDensityWithPartyVO>();
	  VoterDensityWithPartyVO voterDensityWithPartyVO =dvoterDensityWithPartyVO.deSerialize( maps.get(PdfPages.panchayatVoterDensity),autoStrategy );
	  stratagicReportsService.generatePDFForDensity(voterDensityWithPartyVO,document);
	  voterDensityWithPartyVO=null;
	 
	  /* //page 9
		 document.newPage();
		  buildSubHeading(document, "Our Candidate");
		 
	      Paragraph str =new Paragraph("We consolidate the inputs procured from the field operations to provide a snapshot of the strengths, 	 weakness of the contestants available in the constituency. This is a key ingredient in helping us to create  a  strategy  based  on  the  covering  up  our  weakness  and  fortify  our  strengths  &  opportunities  as  well 	 exploit the opponent’s weakness.",SMALLFONT);
	      document.add(str);
		 document.add(Chunk.NEWLINE);
		 document.add(Chunk.NEWLINE);
		 buildPdfForText(document);*/
 	 
      //page-10

       //delimitation
    document.newPage();
     buildSubHeading(document, " Step 2 – Goal");
     document.add(Chunk.NEWLINE);
     Paragraph  subText =new Paragraph("The key aspect of any successful strategy is to have a right goal, and we have gathered all the required information in setting up the goal for winning elections in this constituency.",SMALLFONT);
     document.add(subText);
     document.add(Chunk.NEWLINE);
     
	 buildSubHeading(document, "Delimitation Effect");

     DeSerialize<DelimitationEffectVO> desdelimitationEffectVO =new DeSerialize<DelimitationEffectVO>();
     DelimitationEffectVO delimitationEffectVO=desdelimitationEffectVO.deSerialize( maps.get(PdfPages.delimitationEffect),autoStrategy );
     //call to pdf generation 	   
    stratagicReportsService.generatePDFForDelimitationEffect(delimitationEffectVO,document);
     
    //assumptions
    
    if(!autoStrategy  || goalDataPresent){
        DeSerialize<AssumptionsVO> desassumptionsVO =new DeSerialize<AssumptionsVO>();
        AssumptionsVO assumptionsVO =desassumptionsVO.deSerialize( maps.get(PdfPages.assumptions),autoStrategy );
        //call to pdf generation 	   
        stratagicReportsService.generatePDFForAssuredTargetVotersBlock(assumptionsVO,document);
       }
        
    
     //page-11
     
    //18111
     //page-21
  //targetting key factors starts
    try{
    	document.newPage();
    	Paragraph preface = new Paragraph();
	    preface.setAlignment(Element.PTABLE);
    	Font topHeading = new Font(Font.FontFamily.TIMES_ROMAN, 15,Font.BOLD);
		topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
		preface.add( new Paragraph("Step 3 – Targeting",topHeading));
		preface.add( new Paragraph(" ") );
	    document.add(preface);
        DeSerialize<Map<String,Float>>  dcasteNamePercMap=new DeSerialize<Map<String,Float>>();
	    Map<String,Float> casteNamePercMap =dcasteNamePercMap.deSerialize( maps.get(PdfPages.selectedCastes),autoStrategy );
	    if(casteNamePercMap != null && casteNamePercMap.size() > 0){
		  
		  strategyModelTargetingService.generateCasteWiseTable(document,casteNamePercMap);//1
		  casteNamePercMap=null;
	 
	  	  DeSerialize<List<PanchayatVO>>  dtotalCastesList=new DeSerialize<List<PanchayatVO>>();
	      List<PanchayatVO> totalCastesList =dtotalCastesList.deSerialize( maps.get(PdfPages.totalCastesOrder),autoStrategy );
	      strategyModelTargetingService.panchayatWiseTargetVotesTable(document,totalCastesList);//2
	      totalCastesList=null;
	   }
	  

      DeSerialize<List<PartyPositionVO>>  dpreviousTrends=new DeSerialize<List<PartyPositionVO>>();
	  List<PartyPositionVO> previousTrends =dpreviousTrends.deSerialize( maps.get(PdfPages.previousTrends),autoStrategy );
	  strategyModelTargetingService.generatePdfForMatrixReport(document,previousTrends);//4
	  previousTrends=null;

	  buildSubHeading(document, "First Time (18-22) Voters");
	  Font calibriNormal = FontFactory.getFont("Calibri",9,Font.NORMAL);
	   preface = new Paragraph();
	  preface.setAlignment(Element.PTABLE);
	  preface.add( new Paragraph("Constituency Wise :",calibriNormal));
	  //preface.add( new Paragraph(" ") );
	  document.add(preface);
	  vinfo =dvinfo.deSerialize( maps.get(PdfPages.firstTimeVoters),autoStrategy );
	  if(vinfo !=null)
	  buildPdfForFirstTimeVotersAndVotersByAgeGroup(vinfo, document, writer, "");
	  vinfo=null;
	  
     DeSerialize<List<PanchayatVO>>  dyoungCastesList=new DeSerialize<List<PanchayatVO>>();
	  List<PanchayatVO> youngCastesList =dyoungCastesList.deSerialize( maps.get(PdfPages.youngCastes),autoStrategy );
	  strategyModelTargetingService.panchayatWiseTargetYoungVotesTable(document,youngCastesList,"18-22");//5
	  youngCastesList=null;
	  
	  DeSerialize<List<PanchayatVO>>  dagedCastesList=new DeSerialize<List<PanchayatVO>>();
	  List<PanchayatVO> agedCastesList =dagedCastesList.deSerialize( maps.get(PdfPages.agedCastes),autoStrategy );
	   if(agedCastesList != null && agedCastesList.size() > 0){
		  buildSubHeading(document, "Aged (Above 60) Voters");
		  Paragraph preface1 = new Paragraph();
		  preface1.setAlignment(Element.PTABLE);
		  preface1.add( new Paragraph("Constituency Wise :",calibriNormal));
		  //preface1.add( new Paragraph(" ") );
		  document.add(preface1);
		  vinfo =dvinfo.deSerialize( maps.get(PdfPages.votersAgeGroup),autoStrategy );
		  if(vinfo.getVoterStategicReportVOList() != null && vinfo.getVoterStategicReportVOList().size() > 0)
		  {
			  int size = vinfo.getVoterStategicReportVOList().size() ;
			  buildPdfForFirstTimeVotersAndVotersByAgeGroupForConstituency(vinfo.getVoterStategicReportVOList().get(size-1), document, writer, "");
		  }
		  
		  vinfo=null;
			 
	     
		  strategyModelTargetingService.panchayatWiseTargetYoungVotesTable(document,agedCastesList,"Above 60");//6
		  agedCastesList=null;
	  }
     DeSerialize<List<PartyEffectVO>>  dotherPartyEffect=new DeSerialize<List<PartyEffectVO>>();
	  List<PartyEffectVO> otherPartyEffect =dotherPartyEffect.deSerialize( maps.get(PdfPages.otherPartyEffect),autoStrategy );
	  strategyModelTargetingService.prpEffectTableTable(document,otherPartyEffect);//7
	  otherPartyEffect=null;
	  
	  DeSerialize<List<ImpFamilesVO>>  dimpfamilesList=new DeSerialize<List<ImpFamilesVO>>();
	  List<ImpFamilesVO> impfamilesList =dimpfamilesList.deSerialize( maps.get(PdfPages.impfamilesList),autoStrategy );
	  strategyModelTargetingService.generateImpFamilesTable(document,impfamilesList,null);//9
	  impfamilesList=null;

     DeSerialize<List<OrderOfPriorityVO>>  dpanchayatsClassification=new DeSerialize<List<OrderOfPriorityVO>>();
	  List<OrderOfPriorityVO> panchayatsClassification =dpanchayatsClassification.deSerialize( maps.get(PdfPages.panchayatsClassification),autoStrategy );
	  strategyModelTargetingService.buildPiChart(document,panchayatsClassification,writer);//8
	  strategyModelTargetingService.buildPanchayatsClassificationBlock(document,panchayatsClassification);//8
	  panchayatsClassification=null;

  

     DeSerialize<List<OrderOfPriorityVO>>  dfinalOrderOfOriority=new DeSerialize<List<OrderOfPriorityVO>>();
	  List<OrderOfPriorityVO> finalOrderOfOriority =dfinalOrderOfOriority.deSerialize( maps.get(PdfPages.finalOrderOfOriority),autoStrategy );
	  strategyModelTargetingService.orderOFPriorityTable(document,finalOrderOfOriority,15);//10
	  finalOrderOfOriority=null;	  
	  
	  try {
			DeSerialize<List<PartyPositionVO>>  dpartyPerformance=new DeSerialize<List<PartyPositionVO>>();
			  String Eleheading="Election Results Comparision Table b/w 2009(TDP @ Top Position) Assembly & 2013 Panchayat";
			  List<PartyPositionVO> partyPerformance =dpartyPerformance.deSerialize( maps.get(PdfPages.partyPerformance),autoStrategy ); 
			  if(partyPerformance != null && partyPerformance.size() > 0){
				  List<Long> wonPanchayats = getData(partyPerformance,1l);
				  List<Long> regainedPanchayats = getData(partyPerformance,2l);
				  buildTableAndGraph(document,wonPanchayats,regainedPanchayats,writer);
				  document.newPage();
				  strategyModelTargetingService.panchayatwisePartyPerformanceTable(document,partyPerformance,1l,Eleheading);//3
				  Eleheading="Election Results Comparision Table b/w 2009(TDP @ Lower Positions) Assembly & 2013 Panchayat";
				  strategyModelTargetingService.panchayatwisePartyPerformanceTable(document,partyPerformance,2l,Eleheading);//3
				  
				  
			  }
			  //String EleChartheading="Election Results Comparision Chart b/w 2009 Assembly & 2013  Panchayat";
			  //strategyModelTargetingService.buildChartForPartyPerformanceReort(document,partyPerformance,writer,EleChartheading);//3
			  partyPerformance=null;
		
		} catch (Exception e) {
			// TODO: handle exception
		} 
   }catch (Exception e) {
	   LOG.error("Exception rised in serializationOfObjects",e);
	}
	//targetting key factors ends
    
   //-->Voters Additions & Deletions
     document.newPage();
		
	  DeSerialize<PDFHeadingAndReturnVO>  dvoterAgeRangeVOList=new DeSerialize<PDFHeadingAndReturnVO>();
	  PDFHeadingAndReturnVO voterAgeRangeVOList =dvoterAgeRangeVOList.deSerialize( maps.get(PdfPages.voterAdditionAndDeletion),autoStrategy );
	  stratagicReportsService.generatePDFForVoterInfo(voterAgeRangeVOList,"voterInfo",document);	 
		voterAgeRangeVOList=null;
	 //stratagicReportsService.generatePDFForVoterInfo(voterAgeRangeVOList,"voterInfo");
	
	//-->Gender Wise Voter Modifications between Electoral Roll 2013 - Draft To Electoral Roll 2013 – Final
	 PDFHeadingAndReturnVO voterModificationGenderInfoVOList = dvoterAgeRangeVOList.deSerialize( maps.get(PdfPages.genderWiseVoterModification),autoStrategy );
	    stratagicReportsService.generatePDFForVoterInfo(voterModificationGenderInfoVOList,"genderWise",document);

	    voterModificationGenderInfoVOList=null;
		//stratagicReportsService.generatePDFForVoterInfo(voterModificationAgeRangeVOList,"addedDeleted");
	
	//--->Age Group
	    
	    PDFHeadingAndReturnVO ageGroup = dvoterAgeRangeVOList.deSerialize( maps.get(PdfPages.ageGroup),autoStrategy );
		stratagicReportsService.generatePDFForVoterInfo(ageGroup,"addedDeleted",document);

		voterModificationGenderInfoVOList=null;
		
     //Age Group –Booth wise Additions information
		
		 //List<AgeRangeVO> boothWiseAddedDelList=stratagicReportsService.getBoothWiseAddedAndDeletedVoters(constituencyId,publicationDateId);
		
		  DeSerialize<List<AgeRangeVO>>  dboothWiseAddedDelList=new DeSerialize<List<AgeRangeVO>>();
		  List<AgeRangeVO> boothWiseAddedDelList = dboothWiseAddedDelList.deSerialize( maps.get(PdfPages.boothWiseAddedDelList) ,autoStrategy);

		  stratagicReportsService.generateBoothWiseAddedDeletedVoters(boothWiseAddedDelList,document);
	      
		  //locality wise added and deleted
		  
		   DeSerialize<VoterModificationVO> desvoterModificationVO =new DeSerialize<VoterModificationVO>();
		    VoterModificationVO voterModificationVO =desvoterModificationVO.deSerialize( maps.get(PdfPages.localityAddedDeleted) ,autoStrategy);
	         //call to pdf generation 	   
			 stratagicReportsService.getPDFForSubLevelAddedDeleted(voterModificationVO,document);
		
		
   //page-23$24
     document.newPage();
   		//Polling Stations – Increase Polling %
     DeSerialize<List<PartyPositionVO>>  dpolling=new DeSerialize<List<PartyPositionVO>>();
     List<PartyPositionVO> polling =dpolling.deSerialize( maps.get(PdfPages.pollingPercent),autoStrategy );
     
     List<String> columnNames = new ArrayList<String>();
     columnNames.add("P.S#");
     columnNames.add("Panchayat");
     columnNames.add("Location");
     //columnNames.add("Villages Covered");
     columnNames.add("Polling %");
     columnNames.add("TDP Votes 2009");
     columnNames.add("TDP %");
    
     buildPdfForPollingStations(polling.get(0), document, writer, columnNames);
     polling=null;
     dpolling=null;

	 
	 document.close();
	}catch (Exception e) {
		LOG.error("Exception rised in DeserializationOfObjects",e);
	 document.close();

	}
		return url;
		
		
	}

	public void buildPreviousTrendsHeadings(Document document) throws DocumentException {
	
		/*
		          public static String[][] FONTS = {
		        
		        {"resources/fonts/cmr10.afm", BaseFont.WINANSI},
		BaseFont.createFont(FONTS[0][0], FONTS[0][1], BaseFont.EMBEDDED);
	System.out.println(FontFactory.contains("CALIBRI.TTF"));
	System.out.println(FontFactory.contains("Calibri"));*/
		 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD);
		 Font topHeading = FontFactory.getFont("CALIBRI", 12, Font.BOLD);
		 topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
		
		 Font subHeading1 = TITLE;
		 TITLE.setColor(subHeading);
		 
		Paragraph pageParagraph =   new Paragraph( "Step 1 – The Big Picture ",topHeading);
		document.add(pageParagraph);
		
		document.add( new Paragraph(" ") );
	
		Paragraph p =   new Paragraph( "Previous Results “A Snapshot” ",TITLE);		
	
				Paragraph p1 =   new Paragraph( "How did we fare in the Past?",SMALLFONT);
		
		document.add(p);
		
		document.add(p1);
		
		TITLE.setColor(BaseColor.BLACK);

	}
	
	public void buildPdfForPrevTrends(String name,List<PartyElectionTrendsReportVO> finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
	{
		
		  Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
		  BaseColor winner=new BaseColor(146, 208, 80);
		  BaseColor runner= new BaseColor(141,180,226);
		  Font calibriBold1 = FontFactory.getFont("Calibri",21,Font.BOLD);
		  Font calibriBold2 = FontFactory.getFont("Calibri",25,Font.BOLD);
		  Font calibriBold3 = FontFactory.getFont("Calibri",18,Font.BOLD);
		 int padding = 6;
		 /*Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		*/
		 Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
		 subHeading.setColor(BaseColor.MAGENTA); 
		  
		
		
		Paragraph p =   new Paragraph(heading ,SMALLFONT);
		//p.setFont(subHeading);
		
		document.add(p );
		
		PdfPCell c1;
		document.add( new Paragraph(" ") );

		PdfPTable table = new PdfPTable(12);
		 table.setWidthPercentage(100);
		  	c1 = new PdfPCell(new Phrase("Year",calibriBold));
		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	c1.setBackgroundColor(BaseColor.YELLOW);
		  	c1.setPadding(padding); 
		  	table.addCell(c1);
		  	// public void addCellToTable(PdfPTable table, )

		  	c1 = new PdfPCell(new Phrase("Total Voters",calibriBold));
		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	c1.setBackgroundColor(BaseColor.YELLOW);
		  	c1.setPadding(padding); 
		  	table.addCell(c1);

		  	c1 = new PdfPCell(new Phrase("Votes Polled",calibriBold));
		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	c1.setBackgroundColor(BaseColor.YELLOW);
		  	c1.setPadding(padding); 
		  	table.addCell(c1);
		  	
		 
		  	
		  	table.setHeaderRows(1);
		  	int count=0;
		  	
		  	for (PartyElectionTrendsReportVO prev : finalRes) {

		  		
			 c1 = new PdfPCell(new Phrase("TDP",calibriBold));
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 c1.setPadding(padding); 
				 table.addCell(c1);
				
				 c1 = new PdfPCell(new Phrase("  %   Votes    (TDP)",calibriBold));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 	c1.setPadding(padding); 
	         table.addCell(c1);
				
				 c1 = new PdfPCell(new Phrase("  Margin Votes     (%)",calibriBold));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 	 c1.setPadding(padding); 
				table.addCell(c1);
				
				//inc
				c1 = new PdfPCell(new Phrase("INC",calibriBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 	c1.setPadding(padding); 
				table.addCell(c1);
				
				c1 = new PdfPCell(new Phrase("  %   Votes    (INC)",calibriBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 	c1.setPadding(padding); 
				table.addCell(c1);
				
				//prp
				String textName="PRP";
				if(prev.getElectionYear().equals(2012))
				{
					textName="YSRCP/PRP";
				}
				if(prev.getDistrictId()>10){
			    c1 = new PdfPCell(new Phrase(textName,calibriBold));
			    c1.setHorizontalAlignment(Element.ALIGN_CENTER);	
			    c1.setBackgroundColor(BaseColor.YELLOW);
			    c1.setPadding(padding); 
				table.addCell(c1);
				
				
				
				c1 = new PdfPCell(new Phrase("  %   Votes    ("+textName+")",calibriBold));
			    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    c1.setBackgroundColor(BaseColor.YELLOW);
			    c1.setPadding(padding); 
				table.addCell(c1);
				}else{
					 c1 = new PdfPCell(new Phrase("TRS",calibriBold));
					 c1.setHorizontalAlignment(Element.ALIGN_CENTER);	
					 c1.setBackgroundColor(BaseColor.YELLOW);
					 c1.setPadding(padding); 
						table.addCell(c1);
						
					c1 = new PdfPCell(new Phrase("  %   Votes    (TRS)",calibriBold));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.YELLOW);
					c1.setPadding(padding); 
					table.addCell(c1);
				}
				//others
				c1 = new PdfPCell(new Phrase("OTHERS",calibriBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.YELLOW);
			c1.setPadding(padding); 
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("  %   Votes    (OTHERS)",calibriBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.YELLOW);
			c1.setPadding(padding); 
				table.addCell(c1);
				
				break;
		  	
		  	}
		  
        //0,156,88
			for (PartyElectionTrendsReportVO prev : finalRes) {
				
				if(prev.getTdpVo()==null )
					continue;
			   boolean isOthersFirst=false;
			   boolean isOtherSecond=false;
			   BaseColor color=null;
			   
				//tdp
			 c1 = new PdfPCell(new Phrase(prev.getElectionYear().toString(),calibriBold));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 c1.setPadding(padding); 
			 table.addCell(c1);
				 c1 = new PdfPCell(new Phrase(prev.getTotalVoters().toString(),calibriBold));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPadding(padding); 
				table.addCell(c1);
				
				 c1 = new PdfPCell(new Phrase(prev.getTotalVotesPolled().toString(),calibriBold));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setPadding(padding); 
				table.addCell(c1);
				
				
			/*	if(prev.getTdpVo()==null )
					continue;*/
				
			     Long rank=	prev.getTdpVo().getRank();
			     
			     if(rank.equals(1L))
			     {
			    	color=winner;
			    	 isOthersFirst=true;
			     }
			     else
			    	 if(rank.equals(2L)){
			    		 color=runner;
			    		 isOtherSecond=true;
			     }
				
				 addCellTotableWithPadding(prev.getTdpVo().getVotesEarned(), prev.getTdpVo().getPercentage(), table, c1, calibriBold,padding,color);

				if(isOthersFirst)
				 c1 = new PdfPCell(new Phrase(prev.getTdpVo().getMarginVotes()+"   ("+prev.getTdpVo().getMarginVotesPercentage()+")",calibriBold));
				else 
					c1 = new PdfPCell(new Phrase(" -"+prev.getTdpVo().getMarginVotes()+"   ( -" +prev.getTdpVo().getMarginVotesPercentage()+")",calibriBold));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setPadding(padding); 
				table.addCell(c1);
				
				//inc
				rank=0l;
				color=null;
				rank=	prev.getIncVo().getRank();
			     
			     if(rank.equals(1L))
			     {
			    	color=winner;
			    	 isOthersFirst=true;
			     }
			     else
			    	 if(rank.equals(2L)){
			    		 color=runner;
			    		 isOtherSecond=true;
			     }
				addCellTotableWithPadding(prev.getIncVo().getVotesEarned(), prev.getIncVo().getPercentage(), table, c1, calibriBold,padding,color);

			
				//prp or ysrcp
				if(prev.getDistrictId()>10){
					rank=0l;
					color=null;
					rank=	prev.getPrpVo().getRank();
				     
				     if(rank.equals(1L))
				     {
				    	color=winner;
				    	 isOthersFirst=true;
				     }
				     else
				    	
				    	 if(rank.equals(2L)){
				    		 color=runner;
				    		 isOtherSecond=true;
				     }
					addCellTotableWithPadding(prev.getPrpVo().getVotesEarned(), prev.getPrpVo().getPercentage(), table, c1, calibriBold,padding,color);
				} else{
					rank=0l;
					color=null;
					rank=	prev.getTrsVo().getRank();
				     
				     if(rank.equals(1L))
				     {
				    	color=winner;
				    	 isOthersFirst=true;
				     }
				     else				    	
				    	 if(rank.equals(2L)){
				    		 color=runner;
				    		 isOtherSecond=true;
				     }
					addCellTotableWithPadding(prev.getTrsVo().getVotesEarned(), prev.getTrsVo().getPercentage(), table, c1, calibriBold,padding,color);
				}
				
		
				
				//others
				if(!isOthersFirst)
					color=winner;
				else
					if(!isOtherSecond)
						color=runner;
					else
						color=null;
				
				c1 = new PdfPCell(new Phrase(prev.getOthersVo().getVotesEarned().toString(),calibriBold));
			   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			   if(color!=null)
				   c1.setBackgroundColor(color) ;
			   c1.setPadding(padding); 
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(prev.getOthersVo().getPercentage()+"",calibriBold));
			   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			   c1.setPadding(padding); 
				table.addCell(c1);
				
		
		  		
		  	}
			float[] widths = new float[] {1.1f, 1.5f ,1.5f,1.2f,1.2f, 1.7f ,1.2f,1.2f,1.4f, 1.5f ,1.6f,1.8f};
			table.setWidths(widths);
		  	document.add(table);
			
		  	Chunk id = new Chunk("                                                    ",calibriBold2);
	         
		     
		     Chunk id1 = new Chunk("Winner", SMALLFONT);
		  
		     Chunk id2 = new Chunk("-", calibriBold);
		     Chunk id7 = new Chunk(" ", calibriBold);
		     
		     Chunk id3 = new Chunk("   ", calibriBold3);
		     id3.setBackground(winner);
		     Chunk id9 = new Chunk(" ", calibriBold);
		     Chunk id4 = new Chunk("Runner", SMALLFONT);
			  
			     Chunk id5 = new Chunk("-", calibriBold);
			     Chunk id8 = new Chunk(" ", calibriBold);
			    
			     Chunk id6 = new Chunk("   ", calibriBold3);				     
			     id6.setBackground(runner);
	        // id3.setHorizontalScaling(2);
	         
	        // Image img = Image.getInstance(IConstants.IMAGE);
		       
		 // Chunk id1= new Chunk(img, 5, 5, false);
	   //  id1.setBackground(BaseColor.RED);
	    // id1.setHorizontalScaling(2);
		
	
	     document.add(id);
	     document.add(id1);
	     document.add(id2);
	     document.add(id7);
	     document.add(id3);
	     document.add(id9);
	     document.add(id4);	    
	     document.add(id5);
	     document.add(id8);
	     document.add(id6);
		  	
	}
	public byte[] extractBytes (String ImageName) throws IOException {
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );
		}

   public void addCellTotable(Long votersEarned,Double getPercentage,PdfPTable table,PdfPCell c1,Font font){
      if(votersEarned.intValue() == 0)
		 c1 = new PdfPCell(new Phrase("",font));
	else 
    c1 = new PdfPCell(new Phrase(votersEarned.toString(),font));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
	if(getPercentage == 0.0f)
		 c1 = new PdfPCell(new Phrase("",font));
	else 
	c1 = new PdfPCell(new Phrase(getPercentage+"",font));
   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);
}
   
   public void addCellTotableWithPadding(Long votersEarned,Double getPercentage,PdfPTable table,PdfPCell c1,Font font,int padding,BaseColor color){
	      
	   
	   
	   if(votersEarned.intValue() == 0)
			 c1 = new PdfPCell(new Phrase("--",font));
		else 
	    c1 = new PdfPCell(new Phrase(votersEarned.toString(),font));
	   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	   if(color!=null)
	   c1.setBackgroundColor(color);
	   c1.setPadding(padding); 
		table.addCell(c1);
		if(getPercentage == 0.0f)
			 c1 = new PdfPCell(new Phrase("--",font));
		else 
		c1 = new PdfPCell(new Phrase(getPercentage+"",font));
	   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	   c1.setPadding(padding); 
		table.addCell(c1);
	}
	
public void buildSubHeading(Document document,String sub) 
{
	  Font subHeading = FontFactory.getFont("Calibri",11,Font.BOLD);
	  subHeading.setColor(new BaseColor(69,109,142)); 
	
	  Paragraph p =   new Paragraph( sub,subHeading);
	try {
		document.add(p);
	} catch (DocumentException e) {
		
		 LOG.error("Exception rised in buildSubHeading",e);
	}
	
}

public void buildSubHeading1(Document document,String sub) 
{
	  Font subHeading = FontFactory.getFont("Calibri",11,Font.BOLD);
	  subHeading.setColor(BaseColor.BLACK); 
	
	  Paragraph p =   new Paragraph( sub,subHeading);
	try {
		document.add(p);
	} catch (DocumentException e) {
		
		 LOG.error("Exception rised in buildSubHeading1",e);
	}
	
}
public void buildSubHeading2(Document document,String sub) 
{
	  Font subHeading = FontFactory.getFont("Calibri",10,Font.BOLD);
	  subHeading.setColor(BaseColor.BLACK); 
	
	  Paragraph p =   new Paragraph( sub,subHeading);
	try {
		document.add(p);
	} catch (DocumentException e) {
		
		LOG.error("Exception rised in buildSubHeading2",e);
	}
	
}
public PdfPCell getHeaderCell(String columText ,Font font){
	PdfPCell c1 = new PdfPCell(new Phrase(columText,font));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	c1.setBackgroundColor(BaseColor.YELLOW);
	c1.setPaddingTop(13);

  	return c1;
 	
}

public PdfPCell getHeaderCell1(String columText ,Font font){
	PdfPCell c1 = new PdfPCell(new Phrase(columText,font));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	c1.setBackgroundColor(BaseColor.YELLOW);
	c1.setPadding(5);

	
  	return c1;
 	
}

public PdfPCell getColumnCell(String columText ,Font font){
	PdfPCell c1 = new PdfPCell(new Phrase(columText,font));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	return c1;
 	
}

public PdfPCell getColumnCell4(String columText ,Font font){
	PdfPCell c1 = new PdfPCell(new Phrase(columText,font));
	 c1.setHorizontalAlignment(Element.ALIGN_LEFT);
  	return c1;
 	
}

public String buildNullsAsEmptyString(Object obj)
{
	if(obj==null)
		return "";
	else
	
		return obj.toString();
	
		
}
//pdf census

public void buildPdfForCensusData(StrategicCensusVO finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
{

	/* Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); 
	  */
	//Font calibribold = FontFactory.getFont("Calibri",9,Font.BOLD);
	
	 int padding =4;
	Paragraph p =   new Paragraph(heading ,SMALLFONT);
	//p.setFont(subHeading);
	
	document.add(p );
	

	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	//PdfPTable table=null;
    PdfPTable table = new PdfPTable(9);
	//table.setWidthPercentage(100);
	
	
	

	int count=finalRes.getCount();
	if(finalRes.getCount()>1)
		 table = new PdfPTable(9);
	else
		table = new PdfPTable(3);
	table.setWidthPercentage(100);
	table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
	
	
	c1 = new PdfPCell(new Phrase("Parameter",param));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	c1.setPaddingTop(25);
	c1.setRowspan(3);
//	c1.set
  	table.addCell(c1);    	
 	
  if(count==1)
  {
  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  		
  		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getYear()),SMALLFONT));
  	 	 c1.setBackgroundColor(BaseColor.YELLOW);
  	 	 c1.setColspan(2);
  		 
  		 table.addCell(c1);
  		
  		table.addCell(getHeaderCell(finalRes.getConstituencyName(), TITLE));
  		
  		table.addCell(getHeaderCell(finalRes.getConstituencyName(), TITLE));
  		
  		table.addCell(getHeaderCell1("Values", BIGFONT));
  		table.addCell(getHeaderCell1("%", BIGFONT));
  		
  		table.addCell(getColumnCell4("Population", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulation()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulationPercentage()), SMALLFONT));


  		
  		table.addCell(getColumnCell4("Male", SMALLFONT));
  		
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulation()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulationPercentage()), SMALLFONT));
  		
  		
  		table.addCell(getColumnCell4("Female", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulation()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulationPercentage()), SMALLFONT));
  		
  		
  		table.addCell(getColumnCell4("Households", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHolds()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHoldsPercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("SC", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSC()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSCPercent()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("ST", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationST()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSTPercent()), SMALLFONT));
  		
  		
  		table.addCell(getColumnCell4("Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeople()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeoplePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("Male Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingMale()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingMalePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("Female Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingFemale()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingFemalePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("Non Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeople()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeoplePercent()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("Population(Age<6)", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSix()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSixPercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("Literates", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiteratesPercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("Male Literates", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiteraturePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell4("Female Literates", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiteraturePercentage()), SMALLFONT));
  		
  	}
  	
  }else
  	{
  		
  		for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		
  	  		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getYear()),TITLE));
  	  	 	 c1.setBackgroundColor(BaseColor.YELLOW);
  	  	 	 c1.setColspan(2);
  	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	  		 table.addCell(c1);
  	  		 
  		}
  		String titl ="     2001 to 2011 Changes in Percentages";
  		     c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(titl),TITLE));
	  	 	 c1.setBackgroundColor(BaseColor.YELLOW);
	  	 	 c1.setColspan(4);
	  	    
	  		 table.addCell(c1);
	  		
	  		    for(int i=0;i<6;i++)
	  		    {
	  		    	 
	  			table.addCell(getHeaderCell(finalRes.getConstituencyName(), TITLE));
	  	  	
	  		    }
	  	  		
	  		
	  		table.addCell(getHeaderCell(finalRes.getDistrictName(), TITLE));
	  		
	  		
	  		if(finalRes.getStateName().equalsIgnoreCase("Andhra Pradesh"))
	  			
	  	 	 {
	  			table.addCell(getHeaderCell("A.P. State", TITLE));	  		   	 
	  	 	 }
	  		
	  	  for(int i=0;i<3;i++)
		    { 
	  		table.addCell(getHeaderCell1("Values", TITLE));
	  		
  	  		table.addCell(getHeaderCell1("%", TITLE));
	  	
		    }
	  	
	  	table.addCell(getHeaderCell1("%", TITLE));
	  	
	  	table.addCell(getHeaderCell1("%", TITLE));

	  		
  	  		table.addCell(getColumnCell("Population", SMALLFONT));
  	  		
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulation()), SMALLFONT));
  	  	
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulationPercentage()), SMALLFONT));

  	  	}
  	       
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferencePopulation()), SMALLFONT));
  	  
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferencePopulationPercent()), SMALLFONT));
  		
  		
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferencePopulationPercent()), SMALLFONT));
  	
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferencePopulationPercent()), SMALLFONT));

       //male
  	  		
  	  		table.addCell(getColumnCell("Male", SMALLFONT));
  	   	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	   	
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulation()), SMALLFONT));
  	  	
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulationPercentage()), SMALLFONT));
  	   	}
  	
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMalePopulation()), SMALLFONT));
  	 	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMalePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceMalePercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceMalePercent()), SMALLFONT));
		
		
		//female
		 
  	  		table.addCell(getColumnCell("Female", SMALLFONT));
  	  		
  	   	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulation()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulationPercentage()), SMALLFONT));
  	   	}
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemalePopulation()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemalePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceFemalePercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceFemalePercent()), SMALLFONT));
		
  	  		
  	  		table.addCell(getColumnCell("Households", SMALLFONT));
  	   	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHolds()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHoldsPercentage()), SMALLFONT));
  	   	}
  	   	
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceHouseHolds()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceHouseHoldsPercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceHouseHoldsPercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceHouseHoldsPercent()), SMALLFONT));
  	   	
  	  		
  	  		table.addCell(getColumnCell("SC", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSC()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSCPercent()), SMALLFONT));
  	  	}
  	        table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceSC()), SMALLFONT));
  			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceSCPercent()), SMALLFONT));
  			
  			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceSCPercent()), SMALLFONT));
  			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceSCPercent()), SMALLFONT));
  			
  			
  	  		table.addCell(getColumnCell("ST", SMALLFONT));
  	  		for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationST()), SMALLFONT));
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSTPercent()), SMALLFONT));
  			}
  	  		
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceST()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceSTPercent()), SMALLFONT));
			
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceSTPercent()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceSTPercent()), SMALLFONT));
			
			
  			
	  	  	table.addCell(getColumnCell("Working People", SMALLFONT));
	  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeople()), SMALLFONT));
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeoplePercentage()), SMALLFONT));
  			}
	  	  	
	  	  	
	  	  table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceWorkingPeople()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceWorkingPeoplePercent()), SMALLFONT));
			
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceWorkingPeoplePercent()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceWorkingPeoplePercent()), SMALLFONT));
			
			
  	  		table.addCell(getColumnCell("Male Working People", SMALLFONT));
  	  		for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingMale()), SMALLFONT));
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingMalePercentage()), SMALLFONT));
  			}
  	  		
  	  	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleWorkingPeoplePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceMaleWorkingPeoplePercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceMaleWorkingPeoplePercent()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Female Working People", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingFemale()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingFemalePercentage()), SMALLFONT));
  	  	}
  	  	
  	  	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleWorkingPeoplePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceFemaleWorkingPeoplePercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceFemaleWorkingPeoplePercent()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Non Working People", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeople()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeoplePercent()), SMALLFONT));
  	  	}
  	  	
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceNonWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceNonWorkingPeoplePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceNonWorkingPeoplePercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceNonWorkingPeoplePercent()), SMALLFONT));
		
		
		
  	  		table.addCell(getColumnCell("Population(Age<6)", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSix()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSixPercentage()), SMALLFONT));
  	  	}
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLessthan6Population()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLessthan6Percent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceLessthan6Percent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceLessthan6Percent()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Literates", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiterates()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiteratesPercentage()), SMALLFONT));
  	  	}
  	  	
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLiterates()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLiteratesPercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceLiteratesPercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceLiteratesPercent()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Male Literates", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiterates()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiteraturePercentage()), SMALLFONT));
  	  		
  	  	}
    	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleLiteratesPercent()), SMALLFONT));
  		
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceMaleLiteratesPercent()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceMaleLiteratesPercent()), SMALLFONT));
  		
  	  		table.addCell(getColumnCell("Female Literates", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiterates()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiteraturePercentage()), SMALLFONT));
  	  	}
  	  	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleLiterates()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleLiteratesPercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceFemaleLiteratesPercent()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceFemaleLiteratesPercent()), SMALLFONT));
		
  		 

  }
	float[] widths = null;
	
	if(finalRes.getCount()>1)
		widths=new float[] {2.3f, 1.9f ,1.9f,1.9f,1.9f, 1.9f ,1.9f,1.4f,1.2f};
	else
		widths=new float[] {1.2f, 1.2f ,1.2f};
	table.setWidths(widths);
	  	document.add(table);
		
}
public void buildPdfForHouseHolds(HouseHoldsVO finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
{
	
	 Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.BOLD);
	    subHeading.setColor(BaseColor.BLACK);
	    
	   /* Font title = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
		  title.setColor(new BaseColor(141,180,226)); 
	   
	   
	  
	   Paragraph p1 =   new Paragraph(finalRes.getHeading() ,title);
		p1.setFont(title);
		
		document.add(p1 );*/
	
	Paragraph p =   new Paragraph(heading ,SMALLFONT);
	//p.setFont(subHeading);
	
	document.add(p );


	
	Paragraph p2 =   new Paragraph(finalRes.getCalcMessage() ,subHeading);
	p2.setFont(subHeading);
	
	document.add(p2 );
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(5);
	table.setWidthPercentage(60);
	table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);


	  	c1 = new PdfPCell(new Phrase("",BIGFONT));
	  c1.setBorder(Rectangle.NO_BORDER);
	  	
	  	table.addCell(c1);
	    	
	 
	  	
	  	table.setHeaderRows(1);
	  	int count=0;
	  	
	  	for (HouseHoldsVO prev : finalRes.getHouseHoldsVOList()) {
	  		if(prev.getFamiliRange().equalsIgnoreCase("0-3"))
		  		
       	 {
       	 c1 = new PdfPCell(new Phrase("Voters             Below 3",BIGFONT));
       	 c1.setBackgroundColor(BaseColor.YELLOW);
  	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 	 table.addCell(c1);     	 
       	 }
	  	else if(prev.getFamiliRange().equalsIgnoreCase("4-6"))
		  		
	       	 {
	       	 c1 = new PdfPCell(new Phrase("Voters         Between 4-6",BIGFONT));
	       	 c1.setBackgroundColor(BaseColor.YELLOW);
	  	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 	 table.addCell(c1);     	 
	       	 }
	  	else if(prev.getFamiliRange().equalsIgnoreCase("7-10"))
		  		
	       	 {
	       	 c1 = new PdfPCell(new Phrase("Voters           Between 7-10",BIGFONT));
	       	 c1.setBackgroundColor(BaseColor.YELLOW);
	  	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 	 table.addCell(c1);     	 
	       	 }
	  	else if(prev.getFamiliRange().equalsIgnoreCase("10-Above"))
	  		
      	 {
      	 c1 = new PdfPCell(new Phrase("Voters          Above 10",BIGFONT));
      	 c1.setBackgroundColor(BaseColor.YELLOW);
 	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 	 table.addCell(c1);     	 
      	 }
        else{
        	c1 = new PdfPCell(new Phrase(prev.getFamiliRange(),BIGFONT));
   	 	 c1.setBackgroundColor(BaseColor.YELLOW);
   		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
   		 table.addCell(c1);        
   		 }
	  	
		
		
	  	}
	  	 c1 = new PdfPCell(new Phrase("No of           Families",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
          
		for (HouseHoldsVO prev :  finalRes.getHouseHoldsVOList()) {

			 c1 = new PdfPCell(new Phrase(prev.getFamilyCount(),SMALLFONT));
		 	// c1.setBackgroundColor(BaseColor.YELLOW);
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(c1);
	  	}
		
		 c1 = new PdfPCell(new Phrase(" Families %",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		
      for (HouseHoldsVO prev :  finalRes.getHouseHoldsVOList()) {

    		 c1 = new PdfPCell(new Phrase(prev.getFamilyPercentage()+"%",SMALLFONT));
		 
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(c1);
	  	}
		float[] widths = new float[] {1.0f, 1.0f ,1.0f,1.0f,1.0f};
		table.setWidths(widths);
	  	document.add(table);
		
}
public void buildPdfForCasteVoters(CasteStratagicReportVO finalRes,Document document,PdfWriter writer,String heading,List<String> headings) throws DocumentException, IOException
{

	 Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	/* Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);*/
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); 
	  
	
	   /* if(finalRes.getStrategicVOList()!=null && finalRes.getStrategicVOList().size()>0)
	    	buildSubHeading(document, heading);*/
	
	/*Paragraph p =   new Paragraph(heading ,subHeading);
	//p.setFont(subHeading);
	
	document.add(p );*/
	  
	    if(finalRes.getStrategicVOList()!=null && finalRes.getStrategicVOList().size()>0)
            buildSubHeading(document, heading);
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(6);
	table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
	
 
  	
	
	for(String msg:headings)
	{
		 table.addCell(getHeaderCell(msg, TITLE));
	}

	 
		
		 
	  	for (CasteStratagicReportVO prev : finalRes.getStrategicVOList()) {
	  		 if(prev.getCaste().equalsIgnoreCase(""))
	 	  		
        	 {
        	 c1 = new PdfPCell(new Phrase("Others",SMALLFONT));
        	 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 	 table.addCell(c1);     	 
        	 }
         else{
        	 c1 = new PdfPCell(new Phrase(prev.getCaste(),SMALLFONT));
 	  		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 	 table.addCell(c1);
         }
	  		 /*c1 = new PdfPCell(new Phrase(prev.getCaste(),SMALLFONT));
	  		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 	 table.addCell(c1);*/
	  		
		
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getCasteCategory()),SMALLFONT));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	table.addCell(c1);
		 
		
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getTotalCasteVoters()),SMALLFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1);
	 	 
		 
	 	 
	 	 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleCasteVoters()),SMALLFONT));
	 	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1);
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleCasteVoters()),SMALLFONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1);
		 
	 	 
		
		 
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getCastePercentage()),SMALLFONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		
		 
		 	
	  	}
		float[] widths = new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.5f };
		table.setWidths(widths);
	  	document.add(table);
		
}	
public void buildPdfForVotersInfo(VoterStratagicReportVo finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
{

	/* Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);*/
	
	    /*Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); */
	  
	   /* Font title = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
		  title.setColor(new BaseColor(141,180,226)); */
	   
	   /*
	     
	  TITLE.setColor(subHeading);
	  
	   Paragraph p1 =   new Paragraph(finalRes.getHeading() ,TITLE);
		
	    
		document.add(p1 );
		TITLE.setColor(BaseColor.BLACK);*/
	Paragraph p =   new Paragraph(heading ,SMALLFONT);
	//p.setFont(subHeading);
	
	document.add(p );
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(3);
	table.setWidthPercentage(30);
	table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);

	  	c1 = new PdfPCell(new Phrase("Total Voters",SMALLFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);

		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	  	table.addCell(c1);    	
	 	table.setHeaderRows(1);
	  	
	  	for (VoterStratagicReportVo prev : finalRes.getVoterStategicReportVOList()) {

	  		
		 c1 = new PdfPCell(new Phrase(prev.getTotalVoters().toString(),SMALLFONT));
	 	// c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		
		 c1 = new PdfPCell(new Phrase("",BIGFONT));
	 	 c1.setBorder(Rectangle.NO_BORDER);
		 table.addCell(c1);
		 
		 
		 c1 = new PdfPCell(new Phrase("Male",SMALLFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(prev.getMaleVotersCount().toString(),SMALLFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleTotalPercentage()),BIGFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1);
		 
	 	 
		 c1 = new PdfPCell(new Phrase("Female",SMALLFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
	 	 
		 c1 = new PdfPCell(new Phrase(prev.getFemaleVotersCount().toString(),SMALLFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleTotalPercentage()),BIGFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		 	
	  	}
	  	float[] widths = new float[] {1f, 1f ,1f};
		table.setWidths(widths);
	  	document.add(table);
		
}	  
public void buildPdfForFirstTimeVotersAndVotersByAgeGroup(VoterStratagicReportVo finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
{

	/* Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); */
	  
	/* TITLE.setColor(subHeading);
	  
	   Paragraph p1 =   new Paragraph(finalRes.getHeading() ,TITLE);
		
	    
		document.add(p1 );
		TITLE.setColor(BaseColor.BLACK);*/
	
	/*Paragraph p =   new Paragraph(heading ,subHeading);
	//p.setFont(subHeading);
	
	document.add(p );*/
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );
	
	
	PdfPTable table = new PdfPTable(7);
	 table.setWidthPercentage(100);
	table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
	
	  	c1 = new PdfPCell(new Phrase("Age Range",BIGFONT));
	  	c1.setRowspan(2);
	  	
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	c1.setBackgroundColor(BaseColor.YELLOW);
	  	table.addCell(c1);    	
	 	
		 c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setColspan(2);
	  	 table.addCell(c1);    	
	 	
	 	
	 	
	 	 c1 = new PdfPCell(new Phrase("Male",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
	 	 c1.setColspan(2);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase("Female",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
	 	 c1.setColspan(2);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		 table.addCell(getHeaderCell("Total Voters", BIGFONT));
		 table.addCell(getHeaderCell("Total Percentage", BIGFONT));
		 
		 table.addCell(getHeaderCell(" Voters", BIGFONT));
		 table.addCell(getHeaderCell("Percentage", BIGFONT));
		 
		 table.addCell(getHeaderCell(" Voters", BIGFONT));
		 table.addCell(getHeaderCell("Percentage", BIGFONT));
		 
		
		 
			for (VoterStratagicReportVo prev : finalRes.getVoterStategicReportVOList()) {
	             if(prev.getVoterAgeRange().equalsIgnoreCase("Young Voters"))
		  		
	            	 {
	            	 c1 = new PdfPCell(new Phrase("First Time Voters (18-22)",SMALLFONT));
	       	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	 	 	 table.addCell(c1);     	 
	            	 }
	             else{
		  		 c1 = new PdfPCell(new Phrase(prev.getVoterAgeRange(),SMALLFONT));
		  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 	 table.addCell(c1);
	             }
			
			 
			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getTotalVoters()),SMALLFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	table.addCell(c1);
			 
			
			 
			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getTotalPercentage()),SMALLFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 table.addCell(c1);
		 	 
			 
		 	 
		 	 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleVotersCount()),SMALLFONT));
		 	 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 table.addCell(c1);
		 	 
			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleTotalPercentage()),SMALLFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 table.addCell(c1);
			 
		 	 
			
			 
		 	 
			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleVotersCount()),SMALLFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(c1);
			 
			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleTotalPercentage()),SMALLFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(c1);
			 
			 	
		  	}
			float[] widths = new float[] {1.6f, 1.2f ,1.2f,1.2f,1.2f, 1.5f ,1.2f};
			table.setWidths(widths);
		  	document.add(table);
			
	}


public void buildPdfForPollingStations(PartyPositionVO finalRes,Document document,PdfWriter writer,List<String> headings) throws DocumentException, IOException
{
	 //BaseColor subHeading= new BaseColor(69,109,142);
	// Font SMALLFONT = FontFactory.getFont("Calibri",9,Font.NORMAL);
	
	/*Paragraph p =   new Paragraph(heading ,subHeading);
	//p.setFont(subHeading);
	
	document.add(p );*/
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(7);
	table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
	PdfPTable table1 = new PdfPTable(7);
	table1.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
	
	document.add(new Paragraph(""));
    
    Paragraph p =   new Paragraph("Polling Stations – Reduce Polling %" ,TITLE);
	
	TITLE.setColor(subHeading);
    document.add(p);
    Paragraph p1 =   new Paragraph("We need to try & decrease the polling percentages where we are OK and there "+
    		"has been abnormal polling percentage. The below are the polling booths where we had highest polling"+
    		"percentages and our party’s performances was low.",SMALLFONT);
    
   
    document.add(p1);
    
	pollingStationHelper(finalRes, table, headings,"Strong","Polling Stations – Increase Polling %");
	
	document.add( new Paragraph(" ") );
	
	float[] widths = new float[] {1.0f,2.8f ,2.8f,1.2f,1.2f,1.2f, 1.5f };
	table.setWidths(widths);
  	document.add(table);
  	
  	document.newPage();
  	Paragraph p2 =   new Paragraph("Polling Stations – Increase Polling %" ,TITLE);
	TITLE.setColor(subHeading);
    document.add(p2);
    
    Paragraph p3 =   new Paragraph("We need to try & improve the polling percentages where we are very strong."+
    		"The following are the polling booths where we are strong and had low polling percentages." ,SMALLFONT);
    document.add(p3);
    
	pollingStationHelper(finalRes, table1, headings,"Weak","Polling Stations – Reduce Polling %");

	document.add( new Paragraph(" ") );
	
	table1.setWidths(widths);
  	document.add(table1);
	

		
}	


public void pollingStationHelper(PartyPositionVO finalRes,PdfPTable table ,List<String> headings ,String input,String heading)
{
	Font calibriBold1 = FontFactory.getFont("Calibri",9,Font.BOLDITALIC);
	//Font SMALLFONT = FontFactory.getFont("Calibri",9,Font.NORMAL);
	
	for(String msg:headings)
	{
		 table.addCell(getHeaderCell(msg, calibriBold1));
	}

	 List<PartyPositionVO> objs=null;
		 
		if(input.equalsIgnoreCase("Strong"))
		{
			objs=finalRes.getStrongPollingPercentVOList();
			
		  
			
		}else
			objs=finalRes.getWeakPollingPercentVOList();
		 
		int count=0;
	  	for (PartyPositionVO prev : objs) {
	  		
	  		PdfPCell	 c1=null;
	  		if(count==0){
	  		if(input.equalsIgnoreCase("Strong"))
			{
				
	  		    c1 = new PdfPCell(new Phrase("Improve                                                                (Avg Poll% "+roundTo2DigitsDoubleValue(finalRes.getPollingPercentage())+")",calibriBold1));
	  			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			c1.setBackgroundColor(BaseColor.YELLOW);
	  			table.addCell(c1);
			}else
				
			{ 
				
				   c1 = new PdfPCell(new Phrase("To Decrease     (Avg Poll% "+roundTo2DigitsDoubleValue(finalRes.getPollingPercentage())+")",calibriBold1));
		  			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			c1.setBackgroundColor(BaseColor.YELLOW);
		  			table.addCell(c1);
			}
	  		}
	  		count++;
         
	  		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getName()),SMALLFONT));
	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 	 table.addCell(c1);
	  		
	 	 	 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getLocalbodyName().trim().length() > 0 ? prev.getLocalbodyName() : "-"),SMALLFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		 	 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getLocation() != null && prev.getLocation().trim().length()>0?prev.getLocation():"-" ),SMALLFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	 	 table.addCell(c1);
		 
	 	/* c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getVillagesCovered() != null && prev.getVillagesCovered().trim().length()>0?prev.getVillagesCovered():"-" ),SMALLFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	 	 table.addCell(c1);*/
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getPollingPercentage())),SMALLFONT));
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1);
		
		 
	 	if(input.equalsIgnoreCase("Strong"))
	 		c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getWeakPollingPercentVOList().get(0).getPartyTotalvotes()),SMALLFONT));
	 	else
	 		c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getStrongPollingPercentVOList().get(0).getPartyTotalvotes()),SMALLFONT));
	 		
	 	 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1); 
	 	
	 	 
	 	
	 	if(input.equalsIgnoreCase("Strong"))
	 		c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getWeakPollingPercentVOList().get(0).getPartyPercentage())),SMALLFONT));
	 	else
	 		c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getStrongPollingPercentVOList().get(0).getPartyPercentage())),SMALLFONT));
	 	
	 	 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 table.addCell(c1);
		 
		 
	 	if(input.equalsIgnoreCase("Strong"))
		
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getMinValue())),SMALLFONT));
	 	else
			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getMaxValue())),SMALLFONT));

	 	 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		
		 
		 	
	  	}
}
public String roundTo2DigitsDoubleValue(Double number){
	  
	LOG.debug("Entered into the roundTo2DigitsFloatValue service method");
	  
	  String result = "";
	  try
	  {
		  
		
		NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
		f.setMaximumFractionDigits(2);  
		f.setMinimumFractionDigits(2);
		
		result =  f.format(number);
	  }catch(Exception e)
	  {
		  LOG.error("Exception raised in roundTo2DigitsFloatValue service method",e);

	  }
	  return result;
  }

//mptczptc Results
  public PartyResultsVerVO  resultsForMptcZptcElections(Long constituencyId)
  {   PartyResultsVerVO prevResults=null;
	  String areaType=suggestiveModelService.getConstituencyType(constituencyId);
		prevResults=new PartyResultsVerVO();
			
		if(!areaType.equalsIgnoreCase("urban")){
			prevResults=stratagicReportsService.getZptcMptcResultsOfConstituency(constituencyId);
			prevResults.setZptcMptcTitle("Zilla and Mandal Parishad Elections Results");
		}

		PartyResultsVerVO pv=stratagicReportsService.getMuncipalCorpPrevResults(constituencyId);
		if(pv.getPartyResultsVOList()!=null){
			prevResults.setMuncipalCorpResults(pv.getPartyResultsVOList());
			prevResults.setPartyStrengths(pv.getPartyStrengths());
			prevResults.setParticipated(pv.getParticipated());
			prevResults.setWon(pv.getWon());
			prevResults.setOtherVotes(pv.getOtherVotes());
			prevResults.setOtherVotesPercent(pv.getOtherVotesPercent());
			prevResults.setDistrictId(pv.getDistrictId());
			prevResults.setMuncipalOrCorpOrGmc(pv.getMuncipalOrCorpOrGmc());
			prevResults.setTotalNoOfWardsTitle("Total No of Wards -"+pv.getPartyResultsVOList().size());
			prevResults.setWardTitle("Others indicates Inclusive of All Other Parties and Independents, Independents are participated for more than one seat in some of the Wards");
			prevResults.setInformation("A categorization that provides you with insight of "+pv.getElectionBodyType()+" Election results of Wards in your constituency helping in creating a common strategy:");
		}
			
		PartyResultsVerVO pv_gmc=stratagicReportsService.getMuncipalCorpPrevResultsInGHMC(constituencyId);
		if(pv_gmc.getPartyResultsVOList()!=null){
			prevResults.setGmcResults(pv_gmc.getPartyResultsVOList());
			prevResults.setPartyStrengths(pv_gmc.getPartyStrengths());
			prevResults.setParticipated(pv_gmc.getParticipated());
			prevResults.setWon(pv_gmc.getWon());
			prevResults.setOtherVotes(pv_gmc.getOtherVotes());
			prevResults.setOtherVotesPercent(pv_gmc.getOtherVotesPercent());
			prevResults.setMuncipalOrCorpOrGmc(pv.getMuncipalOrCorpOrGmc());
			prevResults.setDistrictId(pv_gmc.getDistrictId());
			prevResults.setTotalNoOfWardsTitle("Total No of Wards -"+pv_gmc.getPartyResultsVOList().size());
			prevResults.setWardTitle("Others indicates Inclusive of All Other Parties and Independents, Independents are participated for more than one seat in some of the Wards");
			prevResults.setInformation("A categorization that provides you with insight of "+pv_gmc.getElectionBodyType()+" Election results of Wards in your constituency helping in creating a common strategy:");
		}
		return prevResults;
		//stratagicReportsService.generatePdfForLocalElectionResults(prevResults);
  }

  public void buildPdfForFirstTimeVotersAndVotersByAgeGroupForConstituency(VoterStratagicReportVo finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
  {

  	PdfPCell c1;
  	document.add( new Paragraph(" ") );
  	
  	
  	    PdfPTable table = new PdfPTable(7);
  	    table.setWidthPercentage(100);
  	  	
  	
  	    c1 = new PdfPCell(new Phrase("Age Range",BIGFONT));
  	  	c1.setRowspan(2);
  	  	
  		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	 	c1.setBackgroundColor(BaseColor.YELLOW);
  	  	table.addCell(c1);    
  	 	
  		c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
  		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	 	c1.setBackgroundColor(BaseColor.YELLOW);
  		c1.setColspan(2);
  	  	table.addCell(c1);    	
  	 	
  	 	
  	 	c1 = new PdfPCell(new Phrase("Male",BIGFONT));
  	 	c1.setBackgroundColor(BaseColor.YELLOW);
  	 	c1.setColspan(2);
  		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  		table.addCell(c1);
  		 
  		 c1 = new PdfPCell(new Phrase("Female",BIGFONT));
  	 	 c1.setBackgroundColor(BaseColor.YELLOW);
  	 	 c1.setColspan(2);
  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  		 table.addCell(c1);
  		
  		table.addCell(getHeaderCell("Total Voters", BIGFONT));
  		 table.addCell(getHeaderCell("Total Percentage", BIGFONT));
  		 
  		 table.addCell(getHeaderCell(" Voters", BIGFONT));
  		 table.addCell(getHeaderCell("Percentage", BIGFONT));
  		 
  		 table.addCell(getHeaderCell(" Voters", BIGFONT));
  		 table.addCell(getHeaderCell("Percentage", BIGFONT));

	  		 c1 = new PdfPCell(new Phrase(finalRes.getVoterAgeRange(),SMALLFONT));
	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 	 table.addCell(c1);

  			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(finalRes.getTotalVoters()),SMALLFONT));
  			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  		 	table.addCell(c1);
  			 
  			
  			 
  			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(finalRes.getTotalPercentage()),SMALLFONT));
  			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  		 	 table.addCell(c1);
  		 	 
  			 
  		 	 
  		 	 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(finalRes.getMaleVotersCount()),SMALLFONT));
  		 	 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  		 	 table.addCell(c1);
  		 	 
  			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(finalRes.getMaleTotalPercentage()),SMALLFONT));
  			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  		 	 table.addCell(c1);
  			 
  			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(finalRes.getFemaleVotersCount()),SMALLFONT));
  			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  			 table.addCell(c1);
  			 
  			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(finalRes.getFemaleTotalPercentage()),SMALLFONT));
  			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  			 table.addCell(c1);

  			float[] widths = new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.5f ,1.2f};
  			table.setWidths(widths);
  		  	document.add(table);
  			
  	}

  public void buildPdfForText(Document document)  throws DocumentException, IOException
	 {
		 
	  String candidate="Mr. Gobbula Tammaiah (TDP)";
	
	  buildSubHeading1(document, candidate);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading(document, "Strength & Weakness");
	  buildSubHeading2(document, "Strengths");
	
	 
	  Paragraph str2 =new Paragraph("     Having Good Following in Student Community as a Principal of a College.",SMALLFONT);
	  //document.add(str2);
	  Paragraph str3 =new Paragraph("     Good Relationship in his Settibalija Community which is the Major Community in Constituency.",SMALLFONT);
	  //document.add(str3);
	  Paragraph str4 =new Paragraph("     Good Financial background.",SMALLFONT);
	  // document.add(str4);
	
	 
	  document.add(new Paragraph(" ")); 
	  List<String> columnNames1 = new ArrayList<String>();
      columnNames1.add(new ListItem(str2).toString());
      columnNames1.add(new ListItem(str3).toString());
      columnNames1.add(new ListItem(str4).toString());

      getPdfContent(document,columnNames1);   	 
	 
	  document.add(new Paragraph(" ")); 
      buildSubHeading2(document, "Weakness");
	  Paragraph str5 =new Paragraph("  Some of the Cadre are Silent.",SMALLFONT);
	  //document.add(str5);
	  Paragraph str6 =new Paragraph("  No Good Relationship in Cadre.",SMALLFONT);
	  //document.add(str6);
	 
	  document.add(new Paragraph(" ")); 
	 
      List<String> columnNames2 = new ArrayList<String>();
      columnNames2.add(new ListItem(str5).toString());
      columnNames2.add(new ListItem(str6).toString());

      getPdfContent(document,columnNames2); 
	  document.add(Chunk.NEWLINE); 
	  buildSubHeading2(document, "Opportunity");
	  Paragraph str7 =new Paragraph("  With the Solid Support of Settibalija Community, can win Easily .",SMALLFONT);
	  //document.add(str7);
	  Paragraph str8 =new Paragraph("  By Maintaining Good Relationship with Neutral Cadre may help to win Easily .",SMALLFONT);
	  // document.add(str8);
	  Paragraph str9 =new Paragraph("  Capture the PRP Voting with lost by the party in 2009 General Elections .",SMALLFONT);
	  //document.add(str9);
	 
	  document.add(Chunk.NEWLINE);
	 
	  
	  List<String> columnNames3 = new ArrayList<String>();
      columnNames3.add(new ListItem(str7).toString());
      columnNames3.add(new ListItem(str8).toString());
      columnNames3.add(new ListItem(str9).toString());
      getPdfContent(document,columnNames3); 
	  document.add(Chunk.NEWLINE); 
	  buildSubHeading2(document, "Threat");
	  Paragraph str10 =new Paragraph("   All the Parties may field their candidates from Settibalija Community .",SMALLFONT);
	  //document.add(str10);
	  Paragraph str11 =new Paragraph("   Ex Minister Pitani Satyanarayana near relative may contest from Jai Samaikya Andhra Party .",SMALLFONT);
	  //document.add(str11);
	  Paragraph str12 =new Paragraph("   Kapu Community Votes May Split, if Pawan Kalyan Party Contests.",SMALLFONT);
	  //document.add(str12); 
	  Paragraph str13 =new Paragraph("   Polling % in Nelamuru and Ramannapalem Panchayaths was above 90% in 2009 Election, and Our Party is losing much so, Preventive measures have to be taken in Maintain the Election process .",SMALLFONT);
	  //document.add(str13); 
	 
	  document.add(Chunk.NEWLINE);
	 
	  
	  List<String> columnNames4 = new ArrayList<String>();
      columnNames4.add(new ListItem(str10).toString());
      columnNames4.add(new ListItem(str11).toString());
      columnNames4.add(new ListItem(str12).toString());
      columnNames4.add(new ListItem(str13).toString());
      getPdfContent(document,columnNames4);
	  buildSubHeading(document, "Viable Opponents");
	  Paragraph str1 =new Paragraph("   After required amount of survey & opinions collected from distinguished politicians, journalists as well  as the local public we have created a list of candidates who can pose threat ", SMALLFONT);
	  document.add(str1);
	  document.add(Chunk.NEWLINE);
	 
	 
	  String candidate1="Mr. Kaadiboina Srinivas (YSRCP)";
      buildSubHeading1(document, candidate1);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading(document, "Strength & Weakness");
	  document.add(Chunk.NEWLINE);
	  buildSubHeading2(document, "Strengths:");
	 
	  Paragraph str21 =new Paragraph("   Good Support from MALA Community.",SMALLFONT);
	  // document.add(str21);
	  Paragraph str22 =new Paragraph("   Can invest huge amounts.",SMALLFONT);
	  // document.add(str22);
	  Paragraph str23 =new Paragraph("   Good Support from Kudupudi Srinivas, MP Contesting Candidate.",SMALLFONT);
	  //document.add(str23);
	  Paragraph str33 =new Paragraph("   Settibalija Caste Voting percentage may gain.",SMALLFONT);
	  //document.add(str33);
	 
	  document.add(Chunk.NEWLINE);
	
	  List<String> columnNames5 = new ArrayList<String>();
      columnNames5.add(new ListItem(str21).toString());
      columnNames5.add(new ListItem(str22).toString());
      columnNames5.add(new ListItem(str23).toString());
      columnNames5.add(new ListItem(str33).toString());
      getPdfContent(document,columnNames5);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading2(document, "Weakness:");
	  Paragraph str24 =new Paragraph("   Low Strength of Mala Community.",SMALLFONT);
	  //document.add(str24);
	  Paragraph str25 =new Paragraph("   Can lose Support from the Cadre, in case not getting the Chance of their Choice.",SMALLFONT);
	  //document.add(str25);
	  document.add(Chunk.NEWLINE);
	 
	  List<String> columnNames6 = new ArrayList<String>();
      columnNames6.add(new ListItem(str24).toString());
      columnNames6.add(new ListItem(str25).toString());
      getPdfContent(document,columnNames6);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading2(document, "Opportunity");
	  Paragraph str26 =new Paragraph("   Winning Chances are there, if Kapu and Settibalija Castes Work together .",SMALLFONT);
	  //document.add(str26);
	 
	  document.add(Chunk.NEWLINE);
	 
	  List<String> columnNames7 = new ArrayList<String>();
      columnNames7.add(new ListItem(str26).toString());
      getPdfContent(document,columnNames7);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading2(document, "Threat:");
	  Paragraph str29 =new Paragraph("   Dissatisfied Aspirants non Cooperation .",SMALLFONT);
	  // document.add(str29);
	  Paragraph str30 =new Paragraph("   Ex Minister Pitani Satyanarayana May also in the Fray.",SMALLFONT);
	  //document.add(str30);
	  document.add(Chunk.NEWLINE);
	
	  
	  List<String> columnNames8 = new ArrayList<String>();
      columnNames8.add(new ListItem(str29).toString());
      columnNames8.add(new ListItem(str30).toString());
      getPdfContent(document,columnNames8);
	  document.add(Chunk.NEWLINE);
	  
	  String candidate2="Mr.Pitani Satyanarayana (JSAP)";
	  buildSubHeading1(document, candidate2);
	 
	  document.add(Chunk.NEWLINE);
	 // buildSubHeading(document, "Strength & Weakness");
	  buildSubHeading2(document, "Strengths");
	 
	  Paragraph str41 =new Paragraph("   He was Ex Minister.",SMALLFONT);
	  //document.add(str21);
	  Paragraph str42 =new Paragraph("   Good Hold on Constituency.",SMALLFONT);
	  // document.add(str22);
	  document.add(Chunk.NEWLINE);
	  List<String> columnNames9 = new ArrayList<String>();
      columnNames9.add(new ListItem(str41).toString());
      columnNames9.add(new ListItem(str42).toString());
      getPdfContent(document,columnNames9);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading2(document, "Weakness");
	  Paragraph str43 =new Paragraph("   OC Category May against his Candidature.",SMALLFONT);
	  //document.add(str43);
	  Paragraph str44 =new Paragraph("   Lost Confidence in Samaikya Andhra Movement.",SMALLFONT);
	  //document.add(str44);
	 
	  document.add(Chunk.NEWLINE);
	 
	  List<String> columnNames10 = new ArrayList<String>();
      columnNames10.add(new ListItem(str43).toString());
      columnNames10.add(new ListItem(str44).toString());
      getPdfContent(document,columnNames10);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading2(document, "Opportunity");
	  Paragraph str45 =new Paragraph("   May get support from NGO’s to Jai Samaikya Andhra Party .",SMALLFONT);
	  // document.add(str45);
	 
	  document.add(Chunk.NEWLINE);
	  List<String> columnNames11 = new ArrayList<String>();
      columnNames11.add(new ListItem(str45).toString());
      getPdfContent(document,columnNames11);
	  document.add(Chunk.NEWLINE);
	  buildSubHeading2(document, "Threat");
	  Paragraph str46 =new Paragraph("   Less Support from Own Caste .",SMALLFONT);
	  //document.add(str46);
	  Paragraph str47 =new Paragraph("   Nil Support from OC’s.",SMALLFONT);
	  //document.add(str47);
	  
	  document.add(Chunk.NEWLINE);
	 
	  List<String> columnNames12 = new ArrayList<String>();
      columnNames12.add(new ListItem(str46).toString());
      columnNames12.add(new ListItem(str47).toString());
      getPdfContent(document,columnNames12);
	 }
	 public void getPdfContent(Document document,List<String> strengths)throws DocumentException,IOException
	 {
		 com.itextpdf.text.ZapfDingbatsList orderedList = new com.itextpdf.text.ZapfDingbatsList(108, 10);
	 
		 for (String string : strengths) {
			  orderedList.add(new ListItem(string));
    	}
		 document.add(orderedList);   
	  
	 }

	 public Long getConstituencyNo(Long constituencyId){
			return delimitationConstituencyDAO.getConstituencyNo(constituencyId, 2009l);
		 }
	 
	 public  List<Long> getData(List<PartyPositionVO> result,long rank){
		
		    List<Long> returnList = new ArrayList<Long>();
			 Long topCount = 0l;
			 Long regained =0l;
			 Long lost = 0l;
			 Long regaintotalvotes =0l;
			 Long losttotalvotes = 0l;
			 Long count = 0l;
			 Long reg = 0l;
			 
			 for(int j = 0 ; j< result.size() ; j++){
				 PartyPositionVO vo = result.get(j);
				if(vo.getRank().longValue() == rank){
						  if(vo.getWinPartyName().equalsIgnoreCase("TDP"))
						  {
							
							count ++;
							topCount =  count;
							regaintotalvotes = vo.getTotalValidVotes() + regaintotalvotes;
						  }
				  
						  else
						 {
							   reg++;
							   regained = reg;
							  losttotalvotes =  vo.getTotalValidVotes() + losttotalvotes;
							  
						 }
				}
			 }
			Long totalPanchayats = topCount + regained;
			 lost = totalPanchayats - topCount;

			
			 returnList.add(totalPanchayats);
			 returnList.add(topCount);
			 returnList.add(lost);
			 returnList.add(regaintotalvotes);
			 returnList.add(losttotalvotes);
			return returnList;
	 }
	 
	 public void buildTableAndGraph(Document document,List<Long> wonPanchayats,List<Long> regainedPanchayats,PdfWriter writer){
		 try{
			 
			 Paragraph p =new Paragraph("   ");
			 document.add(p);
			  p =new Paragraph("   ");
			  document.add(p);
			  Font subHeading = FontFactory.getFont("Calibri",11,Font.BOLD);
	    	    subHeading.setColor(new BaseColor(69,109,142)); 
	    	      p =   new Paragraph("Summary Report",subHeading);
	     	     document.add(p );
	     	     document.add(new Paragraph(" "));
			     PdfPTable table = new PdfPTable(2);
			  int padding = 4;
		         PdfPCell cell ;
			  	  
		        cell = new PdfPCell(new Phrase("In 2009 TDP @ top position Panchayats"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	  
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(wonPanchayats.get(0).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	 
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
			  	  
			  	  
			  	cell = new PdfPCell(new Phrase("Win Panchayats in 2013"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(wonPanchayats.get(1).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
			  	  
			  	cell = new PdfPCell(new Phrase("Lost in 2013"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(wonPanchayats.get(2).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
			  	  
			  	cell = new PdfPCell(new Phrase("Total votes in Win Panchayats"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(wonPanchayats.get(3).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
			  	  
			  	cell = new PdfPCell(new Phrase("Total votes in lost Panchayats"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(wonPanchayats.get(4).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	  table.addCell(cell);
			  
			  	float[] widths = new float[] {2.0f, 0.8f};
				table.setWidths(widths);
			  	 PdfPTable table1 = new PdfPTable(2);
				  
		       
		        cell = new PdfPCell(new Phrase("In 2009 TDP @ second or lower position Panchayats "));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(regainedPanchayats.get(0).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
			  	  
			  	  
			  	cell = new PdfPCell(new Phrase("Regained Panchayats in 2013"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(regainedPanchayats.get(1).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
			  	  
			  	cell = new PdfPCell(new Phrase("Lost in 2013"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(regainedPanchayats.get(2).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
			  	  
			  	cell = new PdfPCell(new Phrase("Total votes in Regained Panchayats"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(regainedPanchayats.get(3).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
			  	  
			  	cell = new PdfPCell(new Phrase("Total votes in lost Panchayats"));
			  	  cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase(regainedPanchayats.get(4).toString()));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setMinimumHeight(45);
			  	cell.setPadding(padding);
			  	table1.addCell(cell);
			  	
				table1.setWidths(widths);
			  	 PdfPTable table2 = new PdfPTable(2);
			  	table2.setWidthPercentage(100);
			        PdfPCell cell2 = new PdfPCell(table);
			        cell2.setPaddingRight(7);
			        cell2.setBorder(PdfPCell.NO_BORDER);   
			        table2.addCell(cell2);
			       
			        PdfPCell cell3 = new PdfPCell(table1);
			        cell3.setBorder(PdfPCell.NO_BORDER);
			        cell3.setPaddingLeft(7);
			        table2.addCell(cell3);
			       
			        document.add(table2);
			  	 DefaultPieDataset  dataSet = new DefaultPieDataset();
				    dataSet.setValue("Win Panchayats In 2013",wonPanchayats.get(1));
				    dataSet.setValue("Lost Panchayats In 2013",wonPanchayats.get(2));
				    JFreeChart chart = ChartFactory.createPieChart("", dataSet, true, true, false);
				    chart.setBackgroundPaint(Color.white);
				    PiePlot plot = (PiePlot)chart.getPlot();
				    
				    plot.setSectionPaint("Win Panchayats In 2013",new Color(51, 102, 204));
				    plot.setSectionPaint("Lost Panchayats In 2013",new Color(255, 0, 0));
				    plot.setLabelGenerator(null);
				    PdfContentByte cb = writer.getDirectContent();
				    PdfTemplate bar = cb.createTemplate(380, 480);
				    Graphics2D g2d2 = bar.createGraphics(380,480);
				    Rectangle2D rectangle2d = new Rectangle2D.Double(27,0, 270,300);
				  
				    chart.draw(g2d2, rectangle2d);
				    g2d2.dispose();
				    cb.addTemplate(bar,0.0f,0.0f);
				    
			    
    			DefaultPieDataset  dataSet1 = new DefaultPieDataset();
    			dataSet1.setValue("Regained Panchayats In 2013",regainedPanchayats.get(1));
    			dataSet1.setValue("Lost Panchayats In 2013",regainedPanchayats.get(2));
    			JFreeChart chart1 = ChartFactory.createPieChart("", dataSet1, true, true, false);
  			    chart1.setBackgroundPaint(Color.white);
  			    PiePlot plot1 = (PiePlot)chart1.getPlot();
  			 
			    plot1.setSectionPaint("Regained Panchayats In 2013",new Color(51, 102, 204));
			    plot1.setSectionPaint("Lost Panchayats In 2013",new Color(255, 0, 0));
			    plot1.setLabelGenerator(null);
			    PdfContentByte cb1 = writer.getDirectContent();
			    PdfTemplate bar1 = cb1.createTemplate(700, 800);
			    Graphics2D g2d21 = bar1.createGraphics(700,800);
			    Rectangle2D rectangle2d1 = new Rectangle2D.Double(298,320, 270,300);
			  
			    chart1.draw(g2d21, rectangle2d1);
			    g2d21.dispose();
			    cb1.addTemplate(bar1,0.0f,0.0f);
    			  
		 }catch(Exception e){
			 LOG.error("Exception rised in buildTableAndGraph ",e);
		 }
	 }
}

