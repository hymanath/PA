package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import jxl.format.RGB;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatResultDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.AssumptionsVO;
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.PDFHeadingAndReturnVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportHelperVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionResultsVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StratagicReportInputVO;
import com.itgrids.partyanalyst.dto.StrategicCensusVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.service.IBoothwisePollingStratagicService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IStratagicReportServiceForMLASuccess;
import com.itgrids.partyanalyst.service.IStratagicReportsService;
import com.itgrids.partyanalyst.service.IStratagicReportsServicePdf;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author Anilkumar Mar 20, 2014
 *
 */
public class StratagicReportsServicePdf implements IStratagicReportsServicePdf{
	private static final Logger LOG = Logger.getLogger(StratagicReportsServicePdf.class);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);


	@Autowired 
	private IStratagicReportsService stratagicReportsService;
	
	@Autowired 	
	private IStratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess;
	
	@Autowired
	
	private IBoothwisePollingStratagicService  boothwisePollingStratagicService;
	
	@Autowired
	private ISuggestiveModelService suggestiveModelService;
	
	
	private Long constId=232L;
	private Long publicationDateId=9L;
	private Long userId=0L;
	private Long partyId=IConstants.TDP_PARTY_ID;
	private Long electionId1=3L;
	private Long electionId=38L;
	private String locationType=IConstants.CONSTITUENCY;
	private Long locationValue=constId,constituencyId=constId,fromPublicationDateId=8L,toPublicationDateId=9L;
	
	//asumptions
	private Long base=232L;
	private Long assured=232L;
	private Long tdpPerc=232L;
	
	private enum PdfPages{
		prevConst,prevPar,prevMptcZptc,census,hoseHolds,voterInfo,firstTimeVoters, votersAgeGroup, votersCaste,panchayatVoterDensity, voterAdditionAndDeletion, genderWiseVoterModification, ageGroup,pollingPercent, boothWiseAddedDelList, delimitationEffect, assumptions, localityAddedDeleted 
	}
	private Map<PdfPages,String> maps =new HashMap<PdfPages, String>();
	
	
	
	//serialization utility class
	public class Serialize<E> 
	{
     public  String  serialize(String fileName,E v) throws IOException 
	
	{
		FileOutputStream fileOut =
		         new FileOutputStream("c:\\temp1\\"+fileName+".ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(v);
		         out.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in /tmp/employee.ser");
		         return  fileName;
		
	}
	}
	 public static  String  serialize(String fileName,Object obj) throws IOException 
		
		{
			FileOutputStream fileOut =null;
			try{
			fileOut =new FileOutputStream("c:\\temp1\\"+fileName+".ser");
			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(obj);
			         out.close();
			         fileOut.close();
			         System.out.printf("Serialized data is saved in"+fileName);
			         return  fileName;
			}catch (Exception e) {
				
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
		  public   E deSerialize(String fileName) throws IOException, ClassNotFoundException 
			
			{
			
			 FileInputStream fileIn = new FileInputStream("c:\\temp1\\"+fileName+".ser");
		     ObjectInputStream in = new ObjectInputStream(fileIn);
		     E vos   =  (E) in.readObject();
		    // System.out.println(vos);
		    // System.out.println(vos.getSubVo().size());
		     in.close();
		     fileIn.close();
				return vos;
			}
	}



	
	
	@Override
	public Object buildPdf(StratagicReportInputVO inputVo) {
		
		return null;
	}
    
	
	
	
	@Override
	public Object buildPdfDelegator(StratagicReportInputVO inputVo) {
		
		int noOfPages=29;
		
		
		//serailization of data 
		
		
		serializationOfObjects();
		//deserilization of data
		
		DeserializationOfObjects();
		//build pdf 
		
		return null;
	}
	
	//
	public Object serializationOfObjects()
	{
		//maps.put(PdfPages.prevConst, "prev1");
		//maps.put(PdfPages.prevPar, "prev1");
        String defaultFileName;
		try{
	   //page-4
		    //previous trends 
		 
			String uidentifier = new Random().nextInt(100000000)+"";
		     //previuos ternds in constituency
		
		
		defaultFileName="prev1"+uidentifier;
		List<PartyElectionTrendsReportVO> resForPrevTrends=stratagicReportServiceForMLASuccess.getPreviousTrendsReport(constId);	                    
		defaultFileName=serialize(defaultFileName, resForPrevTrends);
		maps.put(PdfPages.prevConst, defaultFileName);
		resForPrevTrends=null;
		//previous trends in parliament
		
		defaultFileName="prev2"+uidentifier;
		List<PartyElectionTrendsReportVO> resForPrevTrendsForPaliament=stratagicReportServiceForMLASuccess.getPreviousTrendsReportParliament(constId);
		defaultFileName =   serialize(defaultFileName, resForPrevTrendsForPaliament);
		maps.put(PdfPages.prevPar, defaultFileName);

		resForPrevTrendsForPaliament=null;
		
		
	//page-5
		
		  //previous trends in mptc and zptc
		 
		defaultFileName="mptcZptc"+uidentifier;
		PartyResultsVerVO mptcZptcResults=resultsForMptcZptcElections(constId);
		defaultFileName =   serialize(defaultFileName, mptcZptcResults);
		maps.put(PdfPages.prevMptcZptc, defaultFileName);
		
		
   //apge-6 
		
		//census
		defaultFileName="census"+uidentifier;
		StrategicCensusVO cVo=	stratagicReportServiceForMLASuccess.getCensusDetailsForAConstituency(constId);
		List<StrategicCensusVO> objs=cVo.getCensusDetailsList();
		   if(objs.size()>2)
				objs.remove(2);
		defaultFileName =   serialize(defaultFileName, cVo);
		maps.put(PdfPages.census, defaultFileName);
		cVo=null;
  //page-7
		
		//households
		defaultFileName="houses"+uidentifier;
		HouseHoldsVO hvo=stratagicReportServiceForMLASuccess.getHouseHoldInfoByConstituency(null, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, hvo);
		maps.put(PdfPages.hoseHolds, defaultFileName);
		hvo=null;
		
		//voters
		defaultFileName="vinfo"+uidentifier;
		VoterStratagicReportVo vinfo=stratagicReportServiceForMLASuccess.getVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, vinfo);
		maps.put(PdfPages.voterInfo, defaultFileName);
		vinfo=null;
		
		//firsttime voters
		defaultFileName="firstTimeVoters"+uidentifier;
		vinfo=stratagicReportServiceForMLASuccess.getFirstTimeVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, vinfo);
		maps.put(PdfPages.firstTimeVoters, defaultFileName);
		vinfo=null;
		
		//voters by age group
		defaultFileName="votersAgeGroup"+uidentifier;
		vinfo=stratagicReportServiceForMLASuccess.getAgeWiseVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, vinfo);
		maps.put(PdfPages.votersAgeGroup, defaultFileName);
		vinfo=null;
	//page-8
		
		//voters by caste
		defaultFileName="votersCaste"+uidentifier;
		CasteStratagicReportVO cvo=stratagicReportServiceForMLASuccess.getCasteWiseVotersInfoByConstituency(userId, constId, publicationDateId);
		defaultFileName =   serialize(defaultFileName, cvo);
		maps.put(PdfPages.votersCaste, defaultFileName);
		cvo=null;
		
		//voter-density vs panchayats
		defaultFileName="panchayatVoterDensity"+uidentifier;
		VoterDensityWithPartyVO voterDensityWithPartyVO = stratagicReportsService.getVotersCountInPanchayatsForDensity(constId,publicationDateId);
		defaultFileName =   serialize(defaultFileName, voterDensityWithPartyVO);
		maps.put(PdfPages.panchayatVoterDensity, defaultFileName);
		voterDensityWithPartyVO=null;
		
		//page-9
		//delimitationeffect
		defaultFileName="delimitationEffect"+uidentifier;
		DelimitationEffectVO delimitationEffectVO=stratagicReportsService.getDelimationEffectOnConstituency(constId,partyId);
		defaultFileName =   serialize(defaultFileName, delimitationEffectVO);
		maps.put(PdfPages.delimitationEffect, defaultFileName);
		
		//assumptions
		defaultFileName="assumptions"+uidentifier;
		AssumptionsVO assumptionsVO=stratagicReportsService.votersAssumptionsService( constId,base,assured,publicationDateId,tdpPerc);
		defaultFileName =   serialize(defaultFileName, assumptionsVO);
		maps.put(PdfPages.assumptions, defaultFileName);
		
		
		
		
		//page-10 
		//targetting key factors
		
		//18111s
		//page-21
		
		
		
		//-->Voters Additions & Deletions
		 
		
		 defaultFileName="voterAdditionAndDeletion"+uidentifier;
		 PDFHeadingAndReturnVO voterAgeRangeVOList = stratagicReportsService.getVoterInfoByPublicationDateList(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
			defaultFileName =   serialize(defaultFileName, voterAgeRangeVOList);
			maps.put(PdfPages.voterAdditionAndDeletion, defaultFileName);
			voterAgeRangeVOList=null;
		 //stratagicReportsService.generatePDFForVoterInfo(voterAgeRangeVOList,"voterInfo");
		
		//-->Gender Wise Voter Modifications between Electoral Roll 2013 - Draft To Electoral Roll 2013 – Final
		 defaultFileName="genderWiseVoterModification"+uidentifier;
		 PDFHeadingAndReturnVO voterModificationAgeRangeVOList = stratagicReportsService.getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			defaultFileName =   serialize(defaultFileName, voterModificationAgeRangeVOList);
			maps.put(PdfPages.genderWiseVoterModification, defaultFileName);
			voterModificationAgeRangeVOList=null;
		 //stratagicReportsService.generatePDFForVoterInfo(voterModificationAgeRangeVOList,"addedDeleted");
		
		//--->Age Group
		 defaultFileName="ageGroup"+uidentifier;
		 PDFHeadingAndReturnVO voterModificationGenderInfoVOList = stratagicReportsService.getGenderWiseVoterModificationsForEachPublication(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId,"intermediate");
			defaultFileName =   serialize(defaultFileName, voterModificationGenderInfoVOList);
			maps.put(PdfPages.ageGroup, defaultFileName);
			voterModificationGenderInfoVOList=null;
		 //stratagicReportsService.generatePDFForVoterInfo(voterModificationGenderInfoVOList,"genderWise");
			
			
			/*DeSerialize<List<AgeRangeVO>>  dboothWiseAddedDelList=new DeSerialize<List<AgeRangeVO>>();
			  List<AgeRangeVO> boothWiseAddedDelList = dboothWiseAddedDelList.deSerialize( maps.get(PdfPages.boothWiseAddedDelList) );

			  stratagicReportsService.generateBoothWiseAddedDeletedVoters(boothWiseAddedDelList,document);*/
			defaultFileName="boothWiseAddedDelList"+uidentifier;
			List<AgeRangeVO> boothWiseAddedDelList =stratagicReportsService.getBoothWiseAddedAndDeletedVoters(constituencyId,publicationDateId);
			defaultFileName =   serialize(defaultFileName, boothWiseAddedDelList);
			maps.put(PdfPages.boothWiseAddedDelList, defaultFileName);
			
		//-->locality wise added deleted 
			
			defaultFileName="localityAddedDeleted"+uidentifier;
			VoterModificationVO voterModificationVO=stratagicReportsService.getSubLevelsVoterModificationDetailsByLocationValue(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
			defaultFileName =   serialize(defaultFileName, voterModificationVO);
			maps.put(PdfPages.localityAddedDeleted, defaultFileName);
		
	//page-23
		//Polling Stations – Increase Polling %
		  defaultFileName="boothPolling"+uidentifier;
		 List<PartyPositionVO> polling= boothwisePollingStratagicService.getPollingPercentagesByParty(constId, partyId, electionId, electionId1);
		  defaultFileName =   serialize(defaultFileName, polling);
			maps.put(PdfPages.pollingPercent, defaultFileName);
		}catch (Exception e) {
			e.printStackTrace();
			//logic delete all created files
		}
		//logic delete all created files when exception raised
		
		return null;
	}
	
	public Object DeserializationOfObjects() 
	{
		 //page-4
	    //previous trends 
		Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
		  subHeading.setColor(new BaseColor(69,109,142)); 
		   Document document=null;
	try{
		
		//basic pdf blocks
		   File f =new File("c:\\pdfs\\const");
		    String filePath = "c:\\pdfs\\const\\aniltest.pdf";

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
			 
			    Image image1 = Image.getInstance(IConstants.IMAGE);
		        document.add(image1);
		        
		        document.newPage();
			 buildPreviousTrendsHeadings(document);
			    String constituencyName="Kavali Assembly Constituency";
			  
				String heading="Previous Election Results in "+constituencyName;
				 
	
		DeSerialize<List<PartyElectionTrendsReportVO>> des =new DeSerialize<List<PartyElectionTrendsReportVO>>();
	   List<PartyElectionTrendsReportVO> resForPrevTrends=des.deSerialize( maps.get(PdfPages.prevConst) );	                    
	   buildPdfForPrevTrends( maps.get(PdfPages.prevConst), resForPrevTrends, document, writer, heading);
	   resForPrevTrends=null;
	   des=null;
	 //previous trends in parliament
	
	   DeSerialize<List<PartyElectionTrendsReportVO>> des1 =new DeSerialize<List<PartyElectionTrendsReportVO>>();
	   List<PartyElectionTrendsReportVO> resForPrevTrendsForPaliament=des1.deSerialize( maps.get(PdfPages.prevPar) );	
	   String parliamentName="Kavali Assembly Segment";
	   String heading2="Parliament Results In "+parliamentName;
	   buildPdfForPrevTrends( maps.get(PdfPages.prevPar), resForPrevTrendsForPaliament, document, writer, heading);
	   resForPrevTrendsForPaliament=null;
	   des1=null;
	   
	//maps.put(PdfPages.prevPar, defaultFileName);
  
	
//page-5
	   document.newPage();
	  //previous trends in mptc and zptc
	   buildSubHeading(document, "Zilla and Mandal Parishad Elections Results"); 
	   DeSerialize<PartyResultsVerVO> dmptcZptcResults =new DeSerialize<PartyResultsVerVO>();
	   PartyResultsVerVO mptcZptcResults =dmptcZptcResults.deSerialize( maps.get(PdfPages.prevMptcZptc) );
	   
	   stratagicReportsService.generatePdfForLocalElectionResults(mptcZptcResults,document);
	   mptcZptcResults=null;
	   dmptcZptcResults=null;
	   
//apge-6 
	//census
	   document.newPage();
	   buildSubHeading(document, "Census “A Snapshot”");
	   DeSerialize<StrategicCensusVO> cdes =new DeSerialize<StrategicCensusVO>();
	  StrategicCensusVO cVo =cdes.deSerialize( maps.get(PdfPages.census) );
	  buildPdfForCensusData(cVo, document, writer, cVo.getMessage());
	  String[] conclusions= cVo.getConclusion().split("'");
	 
	  com.itextpdf.text.List orderedList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
      
	  for (String string : conclusions) {
		  orderedList.add(new ListItem(string));
	}
	  
     document.add(orderedList);


	  
	   cdes=null;
	   cVo=null;
	   
		
//page-7
	
	//households
	   document.newPage();
		 buildSubHeading(document, "Households");
	 DeSerialize<HouseHoldsVO> dhvo =new DeSerialize<HouseHoldsVO>();
	 HouseHoldsVO hvo=dhvo.deSerialize( maps.get(PdfPages.hoseHolds) );
     buildPdfForHouseHolds( hvo, document, writer, hvo.getMessage());

	 dhvo=null;hvo=null;
	
	//voters
	 buildSubHeading(document, "Voters");
	 DeSerialize<VoterStratagicReportVo> dvinfo =new DeSerialize<VoterStratagicReportVo>();
	 VoterStratagicReportVo vinfo =dvinfo.deSerialize( maps.get(PdfPages.voterInfo) );
	 buildPdfForVotersInfo(vinfo, document, writer, vinfo.getMessage());
	
	//firsttime voters
	  buildSubHeading(document, "First Time Voters");
	  vinfo =dvinfo.deSerialize( maps.get(PdfPages.firstTimeVoters) );
	 buildPdfForFirstTimeVotersAndVotersByAgeGroup(vinfo, document, writer, "");
      
	  vinfo=null;
	
	//voters by age group
	
	 buildSubHeading(document, "Voters by Age Group");
     vinfo =dvinfo.deSerialize( maps.get(PdfPages.votersAgeGroup) );
	 buildPdfForFirstTimeVotersAndVotersByAgeGroup(vinfo, document, writer, "");

	 vinfo=null;
	
//page-8
	 document.newPage();
	//voters by caste  
	 buildSubHeading(document, "Voters by Caste");
	 DeSerialize<CasteStratagicReportVO>  dcvo=new DeSerialize<CasteStratagicReportVO>();
	 CasteStratagicReportVO cvo =dcvo.deSerialize( maps.get(PdfPages.votersCaste) );
	 if(cvo!=null)
	 buildPdfForCasteVoters(cvo, document, writer, "", null);
	 cvo=null;
	 dcvo=null;
	 
	 //panchayat voters density
	 
	   // VoterDensityWithPartyVO voterDensityWithPartyVO = stratagicReportsService.getVotersCountInPanchayatsForDensity(constituencyId,publicationId);
	  DeSerialize<VoterDensityWithPartyVO>  dvoterDensityWithPartyVO=new DeSerialize<VoterDensityWithPartyVO>();
	  VoterDensityWithPartyVO voterDensityWithPartyVO =dvoterDensityWithPartyVO.deSerialize( maps.get(PdfPages.panchayatVoterDensity) );
	  stratagicReportsService.generatePDFForDensity(voterDensityWithPartyVO,document);
	  voterDensityWithPartyVO=null;
	 
	 //page 9
	 document.newPage();
	  buildSubHeading(document, "Our Candidate");
	 
      Paragraph str =new Paragraph("We consolidate the inputs procured from the field operations to provide a snapshot of the strengths, 	 weakness of the contestants available in the constituency. This is a key ingredient in helping us to create  a  strategy  based  on  the  covering  up  our  weakness  and  fortify  our  strengths  &  opportunities  as  well 	 exploit the opponent’s weakness.");
      document.add(str);
	 document.add(new Paragraph(""));
	  
	 buildSubHeading(document, "Viable Opponents");
	  
	 Paragraph str1 =new Paragraph("After required amount of survey & opinions collected from distinguished politicians, journalists as well  as the local public we have created a list of candidates who can pose threat ");
	 
     document.add(str1);
     
     //page-10
       //delimitation
     DeSerialize<DelimitationEffectVO> desdelimitationEffectVO =new DeSerialize<DelimitationEffectVO>();
     DelimitationEffectVO delimitationEffectVO=desdelimitationEffectVO.deSerialize( maps.get(PdfPages.delimitationEffect) );
     //call to pdf generation 	   
    stratagicReportsService.generatePDFForDelimitationEffect(delimitationEffectVO,document);
     
    //assumptions
    
    DeSerialize<AssumptionsVO> desassumptionsVO =new DeSerialize<AssumptionsVO>();
    AssumptionsVO assumptionsVO =desassumptionsVO.deSerialize( maps.get(PdfPages.assumptions) );
    //call to pdf generation 	   
    stratagicReportsService.generatePDFForAssuredTargetVotersBlock(assumptionsVO,document);
    
    
     //page-11
     
    //18111
     //page-21
   //-->Voters Additions & Deletions
     document.newPage();
		
	  DeSerialize<PDFHeadingAndReturnVO>  dvoterAgeRangeVOList=new DeSerialize<PDFHeadingAndReturnVO>();
	  PDFHeadingAndReturnVO voterAgeRangeVOList =dvoterAgeRangeVOList.deSerialize( maps.get(PdfPages.voterAdditionAndDeletion) );
	  stratagicReportsService.generatePDFForVoterInfo(voterAgeRangeVOList,"voterInfo",document);	 
		voterAgeRangeVOList=null;
	 //stratagicReportsService.generatePDFForVoterInfo(voterAgeRangeVOList,"voterInfo");
	
	//-->Gender Wise Voter Modifications between Electoral Roll 2013 - Draft To Electoral Roll 2013 – Final
	 PDFHeadingAndReturnVO voterModificationAgeRangeVOList = dvoterAgeRangeVOList.deSerialize( maps.get(PdfPages.genderWiseVoterModification) );
		
		stratagicReportsService.generatePDFForVoterInfo(voterModificationAgeRangeVOList,"addedDeleted",document);
		voterModificationAgeRangeVOList=null;
		//stratagicReportsService.generatePDFForVoterInfo(voterModificationAgeRangeVOList,"addedDeleted");
	
	//--->Age Group
	    
	    PDFHeadingAndReturnVO voterModificationGenderInfoVOList = dvoterAgeRangeVOList.deSerialize( maps.get(PdfPages.ageGroup) );
	    stratagicReportsService.generatePDFForVoterInfo(voterModificationGenderInfoVOList,"genderWise",document);
		
		voterModificationGenderInfoVOList=null;
		
     //Age Group –Booth wise Additions information
		
		 //List<AgeRangeVO> boothWiseAddedDelList=stratagicReportsService.getBoothWiseAddedAndDeletedVoters(constituencyId,publicationDateId);
		
		  DeSerialize<List<AgeRangeVO>>  dboothWiseAddedDelList=new DeSerialize<List<AgeRangeVO>>();
		  List<AgeRangeVO> boothWiseAddedDelList = dboothWiseAddedDelList.deSerialize( maps.get(PdfPages.boothWiseAddedDelList) );

		  stratagicReportsService.generateBoothWiseAddedDeletedVoters(boothWiseAddedDelList,document);
	      
		  //locality wise added and deleted
		  
		   DeSerialize<VoterModificationVO> desvoterModificationVO =new DeSerialize<VoterModificationVO>();
		    VoterModificationVO voterModificationVO =desvoterModificationVO.deSerialize( maps.get(PdfPages.localityAddedDeleted) );
	         //call to pdf generation 	   
			 stratagicReportsService.getPDFForSubLevelAddedDeleted(voterModificationVO,document);
		
		
   //page-23$24
     document.newPage();
   		//Polling Stations – Increase Polling %
     DeSerialize<List<PartyPositionVO>>  dpolling=new DeSerialize<List<PartyPositionVO>>();
     List<PartyPositionVO> polling =dpolling.deSerialize( maps.get(PdfPages.pollingPercent) );
     
     List<String> columnNames = new ArrayList<String>();
     columnNames.add("P.S#");
     columnNames.add("Panchayat Covered");
     columnNames.add("Polling %");
     columnNames.add("TDP Votes 2009");
     columnNames.add("TDP %");
    
     buildPdfForPollingStations(polling.get(0), document, writer, columnNames);
     polling=null;
     dpolling=null;

	 
	 document.close();
	}catch (Exception e) {
	e.printStackTrace();
	 document.close();

	}
		return null;
		
		
	}




	private void buildPreviousTrendsHeadings(Document document) throws DocumentException {
	
		 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 13,Font.BOLD);
		 Font topHeading = new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
		 topHeading.setColor( new BaseColor(111,165,235));//69,109,142,
		 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		
		 Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
		  subHeading.setColor(new BaseColor(69,109,142)); 
		
		Paragraph pageParagraph =   new Paragraph( "Step 1 – The Big Picture ",topHeading);
		document.add( new Paragraph(" ") );
	
		Paragraph p =   new Paragraph( "Previous Results “A Snapshot” ",subHeading);
		
		document.add( new Paragraph(" ") );
		Paragraph p1 =   new Paragraph( "How did we fare in the Past?",SMALLFONT);
		
		document.add(pageParagraph);
		document.add(p);
		document.add(p1);
		
	}
	
	public void buildPdfForPrevTrends(String name,List<PartyElectionTrendsReportVO> finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
	{
		
		
		 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		
		 Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
		 subHeading.setColor(BaseColor.MAGENTA); 
		  
		
		
		Paragraph p =   new Paragraph(heading ,SMALLFONT);
		//p.setFont(subHeading);
		
		document.add(p );
		
		PdfPCell c1;
		document.add( new Paragraph(" ") );

		PdfPTable table = new PdfPTable(12);

		  	c1 = new PdfPCell(new Phrase("Year",BIGFONT));
		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	c1.setBackgroundColor(BaseColor.YELLOW);
		  	table.addCell(c1);
		  	// public void addCellToTable(PdfPTable table, )

		  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	c1.setBackgroundColor(BaseColor.YELLOW);
		  	table.addCell(c1);

		  	c1 = new PdfPCell(new Phrase("Votes Polled",BIGFONT));
		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  	c1.setBackgroundColor(BaseColor.YELLOW);
		  	table.addCell(c1);
		  	
		 
		  	
		  	table.setHeaderRows(1);
		  	int count=0;
		  	
		  	for (PartyElectionTrendsReportVO prev : finalRes) {

		  		
			 c1 = new PdfPCell(new Phrase(prev.getTdpVo().getName(),BIGFONT));
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 table.addCell(c1);
				
				 c1 = new PdfPCell(new Phrase("  %   Votes    ("+prev.getTdpVo().getName()+")",BIGFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
	         table.addCell(c1);
				
				 c1 = new PdfPCell(new Phrase("  Margin Votes(%)",BIGFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);

				table.addCell(c1);
				
				//inc
				c1 = new PdfPCell(new Phrase(prev.getIncVo().getName(),BIGFONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);

				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("  %   Votes    ("+prev.getIncVo().getName()+")",BIGFONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	 c1.setBackgroundColor(BaseColor.YELLOW);

				table.addCell(c1);
				
				//prp
				if(prev.getDistrictId()>10){
			     c1 = new PdfPCell(new Phrase(prev.getPrpVo().getName(),BIGFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);	
			c1.setBackgroundColor(BaseColor.YELLOW);

				table.addCell(c1);
				
				c1 = new PdfPCell(new Phrase("  %   Votes    ("+prev.getPrpVo().getName()+")",BIGFONT));
			    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    c1.setBackgroundColor(BaseColor.YELLOW);
				table.addCell(c1);
				}else{
					 c1 = new PdfPCell(new Phrase(prev.getTrsVo().getName(),BIGFONT));
					 c1.setHorizontalAlignment(Element.ALIGN_CENTER);	
					 c1.setBackgroundColor(BaseColor.YELLOW);

						table.addCell(c1);
						
					c1 = new PdfPCell(new Phrase("  %   Votes    ("+prev.getTrsVo().getName()+")",BIGFONT));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setBackgroundColor(BaseColor.YELLOW);
					table.addCell(c1);
				}
				//others
				c1 = new PdfPCell(new Phrase("OTHERS",BIGFONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.YELLOW);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("  %   Votes    (OTHERS)",BIGFONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.YELLOW);
				table.addCell(c1);
				
				break;
		  	
		  	}

			for (PartyElectionTrendsReportVO prev : finalRes) {

				//tdp
			 c1 = new PdfPCell(new Phrase(prev.getElectionYear().toString(),SMALLFONT));
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(c1);
				 c1 = new PdfPCell(new Phrase(prev.getTotalVoters().toString(),SMALLFONT));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
				
				 c1 = new PdfPCell(new Phrase(prev.getTotalVotesPolled().toString(),SMALLFONT));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
				
				
				if(prev.getTdpVo()==null )
					continue;
		  		
				 addCellTotable(prev.getTdpVo().getVotesEarned(), prev.getTdpVo().getPercentage(), table, c1, SMALLFONT);

				
				 c1 = new PdfPCell(new Phrase(prev.getTdpVo().getMarginVotesPercentage()+"",SMALLFONT));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
				
				//inc
			 addCellTotable(prev.getIncVo().getVotesEarned(), prev.getIncVo().getPercentage(), table, c1, SMALLFONT);

			
				//prp or ysrcp
				if(prev.getDistrictId()>10){
					addCellTotable(prev.getPrpVo().getVotesEarned(), prev.getPrpVo().getPercentage(), table, c1, SMALLFONT);
				} else{
				addCellTotable(prev.getTrsVo().getVotesEarned(), prev.getTrsVo().getPercentage(), table, c1, SMALLFONT);
				}
				
		
				
				//others
				c1 = new PdfPCell(new Phrase(prev.getOthersVo().getVotesEarned().toString(),SMALLFONT));
			   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(prev.getOthersVo().getPercentage()+"",SMALLFONT));
			  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(c1);
				
		
		  		
		  	}
			float[] widths = new float[] {1.2f, 1.5f ,1.5f,1.2f,1.2f, 1.5f ,1.2f,1.2f,1.2f, 1.2f ,1.5f,1.7f};
			table.setWidths(widths);
		  	document.add(table);
			
		        Chunk id3 = new Chunk("                                                  ", BIGFONT);
	           id3.setHorizontalScaling(2);
	     
			     Chunk id = new Chunk("winner", BIGFONT);
			     id.setBackground(BaseColor.CYAN);
			     id.setHorizontalScaling(2);
			     Chunk id2 = new Chunk("            ", BIGFONT);
		     id3.setHorizontalScaling(2);
			 
			     Chunk id1= new Chunk("Runner", BIGFONT);
		     id1.setBackground(BaseColor.RED);
		     id1.setHorizontalScaling(2);
			
		
		     document.add(id3);
		     document.add(id);
		     document.add(id2);
		     document.add(id1);
		  	
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
	
public void buildSubHeading(Document document,String sub) 
{
	 Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	  subHeading.setColor(new BaseColor(69,109,142)); 
	Paragraph p =   new Paragraph( sub,subHeading);
	try {
		document.add(p);
	} catch (DocumentException e) {
		
		e.printStackTrace();
	}
	
}
public PdfPCell getHeaderCell(String columText ,Font font){
	PdfPCell c1 = new PdfPCell(new Phrase(columText,font));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	c1.setBackgroundColor(BaseColor.YELLOW);
  	return c1;
 	
}

public PdfPCell getColumnCell(String columText ,Font font){
	PdfPCell c1 = new PdfPCell(new Phrase(columText,font));
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

	 Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); 
	  
	
	
	Paragraph p =   new Paragraph(heading ,SMALLFONT);
	//p.setFont(subHeading);
	
	document.add(p );
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = null;
	int count=finalRes.getCount();
	if(finalRes.getCount()>1)
		table = new PdfPTable(9);
	else
		table = new PdfPTable(3);
	
 
	
	
	c1 = new PdfPCell(new Phrase("Parameter",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	c1.setRowspan(3);
//	c1.set
  	table.addCell(c1);    	
 	
  if(count==1)
  {
  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  		
  		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getYear()),SMALLFONT));
  	 	 c1.setBackgroundColor(BaseColor.YELLOW);
  	 	 c1.setColspan(2);
  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  		 table.addCell(c1);
  		 
  		table.addCell(getHeaderCell(finalRes.getConstituencyName(), BIGFONT));
  		table.addCell(getHeaderCell(finalRes.getConstituencyName(), BIGFONT));
  		table.addCell(getHeaderCell("Values", BIGFONT));
  		table.addCell(getHeaderCell("%", BIGFONT));
  		
  		table.addCell(getColumnCell("Population", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulation()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulationPercentage()), SMALLFONT));


  		
  		table.addCell(getColumnCell("Male", SMALLFONT));
  		
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulation()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulationPercentage()), SMALLFONT));
  		
  		
  		table.addCell(getColumnCell("Female", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulation()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulationPercentage()), SMALLFONT));
  		
  		
  		table.addCell(getColumnCell("Households", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHolds()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHoldsPercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell("SC", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSC()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSCPercent()), SMALLFONT));
  		
  		table.addCell(getColumnCell("ST", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationST()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSTPercent()), SMALLFONT));
  		
  		
  		table.addCell(getColumnCell("Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeople()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeoplePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell("Male Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingMale()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingMalePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell("Female Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingFemale()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingFemalePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell("Non Working People", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeople()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeoplePercent()), SMALLFONT));
  		
  		table.addCell(getColumnCell("Population(Age<6)", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSix()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSixPercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell("Literates", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiteratesPercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell("Male Literates", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiteraturePercentage()), SMALLFONT));
  		
  		table.addCell(getColumnCell("Female Literates", SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiteraturePercentage()), SMALLFONT));
  		
  	}
  	
  }else
  	{
  		
  		for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		
  	  		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getYear()),SMALLFONT));
  	  	 	 c1.setBackgroundColor(BaseColor.YELLOW);
  	  	 	 c1.setColspan(2);
  	  		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	  		 table.addCell(c1);
  	  		 
  		}
  		String titl ="2001 to 2011 Changes in Percentages";
  		     c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(titl),SMALLFONT));
	  	 	 c1.setBackgroundColor(BaseColor.YELLOW);
	  	 	 c1.setColspan(4);	  		
	  		 table.addCell(c1);
	  		
	  		    for(int i=0;i<6;i++)
	  		    {
	  			table.addCell(getHeaderCell(finalRes.getConstituencyName(), BIGFONT));
	  	  	
	  		    }
	  	  		
	  		
	  		table.addCell(getHeaderCell(finalRes.getDistrictName(), BIGFONT));
	  	
	  		table.addCell(getHeaderCell(finalRes.getStateName(), BIGFONT));
	  	  for(int i=0;i<3;i++)
		    {
	  		table.addCell(getHeaderCell("Values", BIGFONT));
  	  		table.addCell(getHeaderCell("%", BIGFONT));
	  	
		    }
	  		
	  	table.addCell(getHeaderCell("%", BIGFONT));

	  	table.addCell(getHeaderCell("%", BIGFONT));

	  		
	  		 
  	  		 
  	  		
  	  		
  	  		table.addCell(getColumnCell("Population", SMALLFONT));
  	  		
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulation()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalPopulationPercentage()), SMALLFONT));

  	  	}
  	  	
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferencePopulation()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferencePopulationPercent()), SMALLFONT));
  		
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getTotalPopulationPercentage()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getTotalPopulationPercentage()), SMALLFONT));

       //male
  	  		
  	  		table.addCell(getColumnCell("Male", SMALLFONT));
  	   	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulation()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMalePopulationPercentage()), SMALLFONT));
  	   	}
  	  		
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMalePopulation()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMalePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceMalePopulation()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceMalePercent()), SMALLFONT));
		
		
		
		//female
		
  	  		table.addCell(getColumnCell("Female", SMALLFONT));
  	  		
  	   	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulation()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemalePopulationPercentage()), SMALLFONT));
  	   	}
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemalePopulation()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemalePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceFemalePopulation()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceFemalePercent()), SMALLFONT));
		
  	  		
  	  		table.addCell(getColumnCell("Households", SMALLFONT));
  	   	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHolds()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getHouseHoldsPercentage()), SMALLFONT));
  	   	}
  	   	
  	    table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceHouseHolds()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceHouseHoldsPercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceHouseHolds()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceHouseHoldsPercent()), SMALLFONT));
  	   	
  	  		
  	  		table.addCell(getColumnCell("SC", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSC()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSCPercent()), SMALLFONT));
  	  	}
  	        table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceSC()), SMALLFONT));
  			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceSCPercent()), SMALLFONT));
  			
  			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceSC()), SMALLFONT));
  			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceSCPercent()), SMALLFONT));
  			
  			
  			
  	  		table.addCell(getColumnCell("ST", SMALLFONT));
  	  		for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationST()), SMALLFONT));
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationSTPercent()), SMALLFONT));
  			}
  	  		
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferencePopulation()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferencePopulationPercent()), SMALLFONT));
			
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferencePopulation()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferencePopulation()), SMALLFONT));
			
			
  			
	  	  	table.addCell(getColumnCell("Working People", SMALLFONT));
	  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeople()), SMALLFONT));
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingPeoplePercentage()), SMALLFONT));
  			}
	  	  	
	  	  	
	  	  table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceWorkingPeople()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceWorkingPeoplePercent()), SMALLFONT));
			
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceWorkingPeople()), SMALLFONT));
			table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceWorkingPeople()), SMALLFONT));
			
			
  	  		table.addCell(getColumnCell("Male Working People", SMALLFONT));
  	  		for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingMale()), SMALLFONT));
	  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingMalePercentage()), SMALLFONT));
  			}
  	  		
  	  	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleWorkingPeoplePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceMaleWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceMaleWorkingPeople()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Female Working People", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getWorkingFemale()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getTotalWorkingFemalePercentage()), SMALLFONT));
  	  	}
  	  	
  	  	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleWorkingPeoplePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceFemaleWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceFemaleWorkingPeople()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Non Working People", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeople()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getNonWorkingPeoplePercent()), SMALLFONT));
  	  	}
  	  	
  	  table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceNonWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceNonWorkingPeoplePercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceNonWorkingPeople()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceNonWorkingPeople()), SMALLFONT));
		
		
		
  	  		table.addCell(getColumnCell("Population(Age<6)", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSix()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getPopulationUnderSixPercentage()), SMALLFONT));
  	  	}
  	  table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLessthan6Population()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLessthan6Percent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceLessthan6Population()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceLessthan6Population()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Literates", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiterates()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getLiteratesPercentage()), SMALLFONT));
  	  	}
  	  	
  	  table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLiterates()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceLiteratesPercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceLiterates()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceLiterates()), SMALLFONT));
		
		
  	  		table.addCell(getColumnCell("Male Literates", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiterates()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getMaleLiteraturePercentage()), SMALLFONT));
  	  		
  	  	}
    	  table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceMaleLiteratesPercent()), SMALLFONT));
  		
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceMaleLiterates()), SMALLFONT));
  		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceMaleLiterates()), SMALLFONT));
  		
  	  		table.addCell(getColumnCell("Female Literates", SMALLFONT));
  	  	for (StrategicCensusVO prev : finalRes.getCensusDetailsList()) {
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiterates()), SMALLFONT));
  	  		table.addCell(getColumnCell(buildNullsAsEmptyString(prev.getFemaleLiteraturePercentage()), SMALLFONT));
  	  	}
  	  	table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleLiterates()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDifferenceFemaleLiteratesPercent()), SMALLFONT));
		
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getDistrictDetails().getDifferenceFemaleLiterates()), SMALLFONT));
		table.addCell(getColumnCell(buildNullsAsEmptyString(finalRes.getStateDetails().getDifferenceFemaleLiterates()), SMALLFONT));
		
  		
  	
  	
		 

  }
	float[] widths = null;
	
	if(finalRes.getCount()>1)
		widths=new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.5f ,1.2f,1.2f,1.7f};
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
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); 
	  
	
	
	Paragraph p =   new Paragraph(heading ,SMALLFONT);
	//p.setFont(subHeading);
	
	document.add(p );
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(5);

	  	c1 = new PdfPCell(new Phrase("",BIGFONT));
	  c1.setBorder(Rectangle.NO_BORDER);
	  	
	  	table.addCell(c1);
	    	
	 
	  	
	  	table.setHeaderRows(1);
	  	int count=0;
	  	
	  	for (HouseHoldsVO prev : finalRes.getHouseHoldsVOList()) {

	  		
		 c1 = new PdfPCell(new Phrase(prev.getFamiliRange(),BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		
	  	}
	  	 c1 = new PdfPCell(new Phrase("No Of Families",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
          
		for (HouseHoldsVO prev :  finalRes.getHouseHoldsVOList()) {

			 c1 = new PdfPCell(new Phrase(prev.getFamilyCount(),BIGFONT));
		 	// c1.setBackgroundColor(BaseColor.YELLOW);
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(c1);
	  	}
		
		 c1 = new PdfPCell(new Phrase(" Families %",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		
      for (HouseHoldsVO prev :  finalRes.getHouseHoldsVOList()) {

    		 c1 = new PdfPCell(new Phrase(prev.getFamilyPercentage(),BIGFONT));
		 
			 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 table.addCell(c1);
	  	}
		float[] widths = new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f};
		table.setWidths(widths);
	  	document.add(table);
		
}
public void buildPdfForCasteVoters(CasteStratagicReportVO finalRes,Document document,PdfWriter writer,String heading,List<String> headings) throws DocumentException, IOException
{

	 Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); 
	  
	
	
	/*Paragraph p =   new Paragraph(heading ,subHeading);
	//p.setFont(subHeading);
	
	document.add(p );*/
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(6);
	
	for(String msg:headings)
	{
		 table.addCell(getHeaderCell(msg, BIGFONT));
	}

	 
		 
		
		 
	  	for (CasteStratagicReportVO prev : finalRes.getStrategicVOList()) {
         
	  		 c1 = new PdfPCell(new Phrase(prev.getCaste(),SMALLFONT));
	 	 	 table.addCell(c1);
	  		
		
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getCasteCategory()),SMALLFONT));
	 	table.addCell(c1);
		 
		
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getTotalCasteVoters()),SMALLFONT));
	 	 table.addCell(c1);
	 	 
		 
	 	 
	 	 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleCasteVoters()),SMALLFONT));
	 	 table.addCell(c1);
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleCasteVoters()),BIGFONT));
	 	 table.addCell(c1);
		 
	 	 
		
		 
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getCastePercentage()),BIGFONT));
	 
		 table.addCell(c1);
		 
		
		 
		 	
	  	}
		float[] widths = new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.5f };
		table.setWidths(widths);
	  	document.add(table);
		
}	
public void buildPdfForVotersInfo(VoterStratagicReportVo finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
{

	 Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); 
	  
	
	
	Paragraph p =   new Paragraph(heading ,SMALLFONT);
	//p.setFont(subHeading);
	
	document.add(p );
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(3);

	  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);

		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	  	table.addCell(c1);    	
	 	table.setHeaderRows(1);
	  	
	  	for (VoterStratagicReportVo prev : finalRes.getVoterStategicReportVOList()) {

	  		
		 c1 = new PdfPCell(new Phrase(prev.getTotalVoters().toString(),BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase("",BIGFONT));
	 	 c1.setBorder(Rectangle.NO_BORDER);
		 table.addCell(c1);
		 
		 
		 c1 = new PdfPCell(new Phrase("Male",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(prev.getMaleVotersCount().toString(),BIGFONT));
	 	 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleTotalPercentage()),BIGFONT));
	 	 table.addCell(c1);
		 
	 	 
		 c1 = new PdfPCell(new Phrase("Female",BIGFONT));
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		 c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell(c1);
		 
	 	 
		 c1 = new PdfPCell(new Phrase(prev.getFemaleVotersCount().toString(),BIGFONT));
	 
		 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleTotalPercentage()),BIGFONT));
		 
		 table.addCell(c1);
		 
		 	
	  	}	float[] widths = new float[] {1.2f, 1.2f ,1.2f};
		table.setWidths(widths);
	  	
	  	document.add(table);
		
}	  
public void buildPdfForFirstTimeVotersAndVotersByAgeGroup(VoterStratagicReportVo finalRes,Document document,PdfWriter writer,String heading) throws DocumentException, IOException
{

	 Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	
	    Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	    subHeading.setColor(BaseColor.MAGENTA); 
	  
	
	
	/*Paragraph p =   new Paragraph(heading ,subHeading);
	//p.setFont(subHeading);
	
	document.add(p );*/
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(7);

	  	c1 = new PdfPCell(new Phrase("Age Range",BIGFONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 c1.setBackgroundColor(BaseColor.YELLOW);

		//c1.setColspan(2);
		c1.setRowspan(2);
	//	c1.set
	  	table.addCell(c1);    	
	 	table.setHeaderRows(1);
	 	
		c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 c1.setBackgroundColor(BaseColor.YELLOW);
		c1.setColspan(2);
	  	table.addCell(c1);    	
	 	table.setHeaderRows(1);
	 	
	 	
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
         
	  		 c1 = new PdfPCell(new Phrase(prev.getVoterAgeRange(),BIGFONT));
	 	 	 table.addCell(c1);
	  		
		
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getTotalVoters()),BIGFONT));
	 	table.addCell(c1);
		 
		
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getTotalPercentage()),BIGFONT));
	 	 table.addCell(c1);
	 	 
		 
	 	 
	 	 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleVotersCount()),BIGFONT));
	 	 table.addCell(c1);
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getMaleTotalPercentage()),BIGFONT));
	 	 table.addCell(c1);
		 
	 	 
		
		 
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleVotersCount()),BIGFONT));
	 
		 table.addCell(c1);
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getFemaleTotalPercentage()),BIGFONT));
		 
		 table.addCell(c1);
		 
		 	
	  	}
		float[] widths = new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.5f ,1.2f};
		table.setWidths(widths);
	  	document.add(table);
		
}


public void buildPdfForPollingStations(PartyPositionVO finalRes,Document document,PdfWriter writer,List<String> headings) throws DocumentException, IOException
{

	 Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	
	 Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD);
	  subHeading.setColor(new BaseColor(69,109,142)); 
	  
	
	
	/*Paragraph p =   new Paragraph(heading ,subHeading);
	//p.setFont(subHeading);
	
	document.add(p );*/
	
	PdfPCell c1;
	document.add( new Paragraph(" ") );

	PdfPTable table = new PdfPTable(6);
	PdfPTable table1 = new PdfPTable(6);
	
	
	Paragraph p =   new Paragraph("Polling Stations – Increase Polling %" ,subHeading);
    p.setFont(subHeading);
    document.add(p);
	pollingStationHelper(finalRes, table, headings,"Strong","Polling Stations – Increase Polling %");
	
	document.add( new Paragraph(" ") );
	
	float[] widths = new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.5f };
	table.setWidths(widths);
  	document.add(table);
  	
  	document.add( new Paragraph(" ") );
  	
	Paragraph p1 =   new Paragraph("Polling Stations – Reduce Polling %" ,subHeading);
    p1.setFont(subHeading);
    document.add(p1);
	pollingStationHelper(finalRes, table1, headings,"Weak","Polling Stations – Reduce Polling %");

	document.add( new Paragraph(" ") );
	
	table1.setWidths(widths);
  	document.add(table1);
	

		
}	


public void pollingStationHelper(PartyPositionVO finalRes,PdfPTable table ,List<String> headings ,String input,String heading)
{
	
	
	
	
	for(String msg:headings)
	{
		 table.addCell(getHeaderCell(msg, BIGFONT));
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
				
	  		    c1 = new PdfPCell(new Phrase("Improve (Avg Poll% "+prev.getPartyPercentage()+")",BIGFONT));
	  			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			c1.setBackgroundColor(BaseColor.YELLOW);
	  			table.addCell(c1);
			}else
				
			{ 
				
				   c1 = new PdfPCell(new Phrase("To Decrease(((Avg Poll% "+prev.getPartyPercentage()+")",BIGFONT));
		  			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			c1.setBackgroundColor(BaseColor.YELLOW);
		  			table.addCell(c1);
			}
	  		}
	  		count++;
         
	  		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getId()),SMALLFONT));
	 	 	 table.addCell(c1);
	  		
		
		 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getLocalbodyName()),SMALLFONT));
	 	table.addCell(c1);
		 
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getPollingPercentage())),SMALLFONT));
	 	 table.addCell(c1);
		
		 
	 	 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getSelectedPartyTotalVoters()),SMALLFONT));
	 	 table.addCell(c1); 
	 	
	 	 
		 
	 	 
	 	
	 	 
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(prev.getPartyPercentage()),BIGFONT));
	 	 table.addCell(c1);
		 
	 	 
		
		 
	 	if(input.equalsIgnoreCase("Strong"))
		
		 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getMinValue())),BIGFONT));
	 	else
			 c1 = new PdfPCell(new Phrase(buildNullsAsEmptyString(roundTo2DigitsDoubleValue(prev.getMaxValue())),BIGFONT));

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
		  LOG.error("Exception raised in roundTo2DigitsFloatValue service method");
		  e.printStackTrace();
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
}
