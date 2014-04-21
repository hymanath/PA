package com.itgrids.partyanalyst.dao.hibernate;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.CasteStratagicReportVO;
import com.itgrids.partyanalyst.dto.HouseHoldsVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.VoterStratagicReportVo;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.impl.StratagicReportServiceForMLASuccess;
import com.itgrids.partyanalyst.service.impl.StratagicReportsServicePdf;

public class StrategyModelTargetingServiceTest  extends BaseDaoTestCase{
	IStrategyModelTargetingService strategyModelTargetingService;
	IPartyTrendsDAO partyTrendsDAO;
	IBoothDAO boothDAO;
	ICensusDAO censusDAO;
	IVoterInfoDAO voterInfoDAO;
	IVoterAgeInfoDAO voterAgeInfoDAO;
	IVoterCastInfoDAO voterCastInfoDAO;
	
	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	IVoterFamilyInfoDAO voterFamilyInfoDAO;
	
	

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public ICensusDAO getCensusDAO() {
		return censusDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO) {
		this.censusDAO = censusDAO;
	}

	public IVoterFamilyInfoDAO getVoterFamilyInfoDAO() {
		return voterFamilyInfoDAO;
	}

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	public IStrategyModelTargetingService getStrategyModelTargetingService() {
		return strategyModelTargetingService;
	}

	public void setStrategyModelTargetingService(
			IStrategyModelTargetingService strategyModelTargetingService) {
		this.strategyModelTargetingService = strategyModelTargetingService;
	}
	
	/*public void testGetReport(){
		StrategyVO strategyVO = new StrategyVO();
		strategyVO.setConstituencyId(232l);
		strategyVO.setPartyId(872l);
		List<Long> electionIds = new ArrayList<Long>();
		electionIds.add(38l);
		electionIds.add(3l);
		strategyVO.setElectionIds(electionIds);
		strategyVO.setEffectPartyId(662l);
		strategyVO.setEffectElectionId(38l);
		strategyVO.setPrevTrnzWt(0.2);
		strategyVO.setYoungWt(0.2);
		strategyVO.setPrpWt(0.2);
		strategyVO.setAgedWt(0.2);
		strategyVO.setTotalCastWt(0.2);
		strategyVO.setPublicationId(8l);
		Map<Long,Float> castePercents = new HashMap<Long,Float>();
		castePercents.put(211l,0.4f);
		castePercents.put(161l,0.6f);
		castePercents.put(285l,0.3f);
		castePercents.put(189l,0.8f);
		castePercents.put(0l,0.4f);
		strategyVO.setCastePercents(castePercents);
		strategyModelTargetingService.getPrioritiesToTarget(strategyVO,"");
	}*/
	
/*	public void testbuildPiChart()
	  {
		Document document = new Document();
		   String filePath = "D:"+"/1.pdf";

		   File file = new File(filePath);
		   PdfWriter writer = null;
		   try {
			   file.createNewFile();
			   writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		   } catch (Exception e) {
			   
		   }
		   document.open();
	  try{
		  DefaultPieDataset  dataSet = new DefaultPieDataset ();
		  List<OrderOfPriorityVO>  panchayatsClassification = new ArrayList<OrderOfPriorityVO>();
		  for(int i=0;i<=5;i++){
			   switch(i){
			    case 0: OrderOfPriorityVO easyPrior = new OrderOfPriorityVO();
			    easyPrior.setTotalVoters(5l);
			    panchayatsClassification.add(easyPrior);
			    easyPrior.setName("Highly Critical Panchayaths");
			           
			    	   break;
			    case 1: OrderOfPriorityVO easyPrior1 = new OrderOfPriorityVO();
			    easyPrior1.setTotalVoters(4l);
			    panchayatsClassification.add(easyPrior1);
			    easyPrior1.setName("Critical Panchayaths");
			    	   break;
			    case 2:OrderOfPriorityVO easyPrior2 = new OrderOfPriorityVO();
			    easyPrior2.setTotalVoters(3l);
			    panchayatsClassification.add(easyPrior2);
			    easyPrior2.setName("Medium Panchayaths");
			    	   break;
			    case 3:OrderOfPriorityVO easyPrior3 = new OrderOfPriorityVO();
			    easyPrior3.setTotalVoters(4l);
			    panchayatsClassification.add(easyPrior3);
			    easyPrior3.setName("Easy Panchayaths");
			    	   break;
			    case 4:OrderOfPriorityVO easyPrior4 = new OrderOfPriorityVO();
			    easyPrior4.setTotalVoters(2l);
			    panchayatsClassification.add(easyPrior4);
			    easyPrior4.setName("Good Panchayaths");
			    	   break;

            }
		  }
		  for(OrderOfPriorityVO order:panchayatsClassification){
			  dataSet.setValue(order.getName(),order.getTotalVoters());
		  }

		  JFreeChart chart = ChartFactory.createPieChart("Strategy Suggestive Model", dataSet, true, true, false);
		  chart.setBackgroundPaint(Color.white);
		  PiePlot plot = (PiePlot)chart.getPlot();
		  for(int i=0;i<panchayatsClassification.size();i++){
			   switch(i){
			    case 0:plot.setSectionPaint("Highly Critical Panchayaths",new Color(255, 0, 0));
			           plot.setExplodePercent("Highly Critical Panchayaths", 0.08);
			    	   break;
			    case 1: plot.setSectionPaint("Critical Panchayaths",new Color(204, 102, 0));
			    	   break;
			    case 2:plot.setSectionPaint("Medium Panchayaths",new Color(255, 153, 102));
			    	   break;
			    case 3:plot.setSectionPaint("Easy Panchayaths", new Color(51, 153, 255));
			    	   break;
			    case 4:plot.setSectionPaint("Good Panchayaths", new Color(0, 153, 0));
			    	   break;

             }
		  }
		
	      plot.setSimpleLabels(true);
	      
	      PdfContentByte cb = writer.getDirectContent();
		  PdfTemplate bar = cb.createTemplate(600, 800);
		  Graphics2D g2d2 = bar.createGraphics(600,800);
		  Rectangle2D rectangle2d = new Rectangle2D.Double(40,40, 300,400);

		  chart.draw(g2d2, rectangle2d);
		  g2d2.dispose();
		  cb.addTemplate(bar,0.0f,0.0f);
	     
	        
		  document.close();
	   }catch(Exception e){
		  e.printStackTrace();
		}
	  }*/
	
	public IPartyTrendsDAO getPartyTrendsDAO() {
		return partyTrendsDAO;
	}

	public void setPartyTrendsDAO(IPartyTrendsDAO partyTrendsDAO) {
		this.partyTrendsDAO = partyTrendsDAO;
	}

	/*public void testBuild(){
		Document document = new Document();
		   String filePath = "D:"+"/1.pdf";

		   File file = new File(filePath);
		   PdfWriter writer = null;
		   try {
			 file.createNewFile();
			   //writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			     
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
		   document.open();
		StratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess = new StratagicReportServiceForMLASuccess();
		stratagicReportServiceForMLASuccess.partyTrendsDAO = partyTrendsDAO;
		List<PartyElectionTrendsReportVO> resForPrevTrends = stratagicReportServiceForMLASuccess.getPreviousTrendsReport(181l);
		StratagicReportsServicePdf val = new StratagicReportsServicePdf();
		try{
		val.buildPdfForPrevTrends("", resForPrevTrends, document, writer, "Previous Election Results in ");
		}catch (Exception e) {
			   e.printStackTrace();
		   }
		 document.close();
	}*/
	public void testBuild(){
		if(true)
			return;
        //1 
Document document = new Document();
String filePath = "C:/pdfs"+"/1.pdf";
try {
File file = new File(filePath);
PdfWriter writer = null;

file.createNewFile();
//2
writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

//TableHeader event = new TableHeader();
//writer.setPageEvent(event);
    
document.open();

StratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess = new StratagicReportServiceForMLASuccess();
stratagicReportServiceForMLASuccess.partyTrendsDAO = partyTrendsDAO;
stratagicReportServiceForMLASuccess.boothDAO=boothDAO;
stratagicReportServiceForMLASuccess.censusDAO=censusDAO;
stratagicReportServiceForMLASuccess.voterFamilyInfoDAO=voterFamilyInfoDAO;
/*List<PartyElectionTrendsReportVO> resForPrevTrends = stratagicReportServiceForMLASuccess.getPreviousTrendsReportParliament(181L);
StratagicReportsServicePdf val = new StratagicReportsServicePdf();*/
StratagicReportsServicePdf val = new StratagicReportsServicePdf();
HouseHoldsVO hvo=stratagicReportServiceForMLASuccess.getHouseHoldInfoByConstituency(null, 181L, 9L);
document.newPage();
//try{
/*val.buildPdfForPrevTrends("", resForPrevTrends, document, writer, "Previous Election Results in ");*/
val.buildPdfForHouseHolds( hvo, document, writer, hvo.getMessage());
document.newPage();
document.add(new Paragraph("fhdhdh"));

}catch (Exception e) {
e.printStackTrace();
}
document.close();
}



public void testBuild4(){
	if(true)
		return;
    //1
Document document = new Document();
String filePath = "C:/pdfs"+"/1.pdf";
try {
File file = new File(filePath);
PdfWriter writer = null;

file.createNewFile();
//2
writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

//TableHeader event = new TableHeader();
//writer.setPageEvent(event);

document.open();

StratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess = new StratagicReportServiceForMLASuccess();
stratagicReportServiceForMLASuccess.partyTrendsDAO = partyTrendsDAO;
stratagicReportServiceForMLASuccess.voterInfoDAO=voterInfoDAO;

/*List<PartyElectionTrendsReportVO> resForPrevTrends = stratagicReportServiceForMLASuccess.getPreviousTrendsReportParliament(181L);
StratagicReportsServicePdf val = new StratagicReportsServicePdf();*/
StratagicReportsServicePdf val = new StratagicReportsServicePdf();
VoterStratagicReportVo vinfo=stratagicReportServiceForMLASuccess.getVotersInfoByConstituency(1L, 181L, 9L);
document.newPage();
//try{
/*val.buildPdfForPrevTrends("", resForPrevTrends, document, writer, "Previous Election Results in ");*/
val. buildPdfForVotersInfo(vinfo, document, writer, vinfo.getMessage());
document.newPage();
document.add(new Paragraph("fhdhdh"));

}catch (Exception e) {
e.printStackTrace();
}
document.close();
}
	
public void testBuild1(){
	
    //1
Document document = new Document();
String filePath = "C:/pdfs"+"/1.pdf";
try {
File file = new File(filePath);
PdfWriter writer = null;

file.createNewFile();
//2
writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

//TableHeader event = new TableHeader();
//writer.setPageEvent(event);

document.open();

StratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess = new StratagicReportServiceForMLASuccess();
stratagicReportServiceForMLASuccess.partyTrendsDAO = partyTrendsDAO;
stratagicReportServiceForMLASuccess.voterAgeInfoDAO=voterAgeInfoDAO;

List<PartyElectionTrendsReportVO> resForPrevTrends = stratagicReportServiceForMLASuccess.getPreviousTrendsReportParliament(232L);
StratagicReportsServicePdf val = new StratagicReportsServicePdf();
//StratagicReportsServicePdf val = new StratagicReportsServicePdf();
//VoterStratagicReportVo vinfo=stratagicReportServiceForMLASuccess.getFirstTimeVotersInfoByConstituency(1L, 232L, 10L);
document.newPage();
//try{
val.buildPdfForPrevTrends("", resForPrevTrends, document, writer, "Previous Election Results in ");
//val.buildPdfForFirstTimeVotersAndVotersByAgeGroup(vinfo, document, writer, "");
document.newPage();
document.add(new Paragraph("fhdhdh"));

}catch (Exception e) {
e.printStackTrace();
}
document.close();
}


public void testBuild2(){
	if(true)
		return ;
	
    //1
Document document = new Document();
String filePath = "C:/pdfs"+"/1.pdf";
try {
File file = new File(filePath);
PdfWriter writer = null;

file.createNewFile();
//2
writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

//TableHeader event = new TableHeader();
//writer.setPageEvent(event);

document.open();

StratagicReportServiceForMLASuccess stratagicReportServiceForMLASuccess = new StratagicReportServiceForMLASuccess();
stratagicReportServiceForMLASuccess.partyTrendsDAO = partyTrendsDAO;
stratagicReportServiceForMLASuccess.voterAgeInfoDAO=voterAgeInfoDAO;

/*List<PartyElectionTrendsReportVO> resForPrevTrends = stratagicReportServiceForMLASuccess.getPreviousTrendsReportParliament(181L);
StratagicReportsServicePdf val = new StratagicReportsServicePdf();*/
StratagicReportsServicePdf val = new StratagicReportsServicePdf();
VoterStratagicReportVo vinfo=stratagicReportServiceForMLASuccess.getAgeWiseVotersInfoByConstituency(1L, 232L, 9L);
document.newPage();
//try{
/*val.buildPdfForPrevTrends("", resForPrevTrends, document, writer, "Previous Election Results in ");*/
val. buildPdfForFirstTimeVotersAndVotersByAgeGroup(vinfo, document, writer, "");
document.newPage();
document.add(new Paragraph("fhdhdh"));

}catch (Exception e) {
e.printStackTrace();
}
document.close();
}

}