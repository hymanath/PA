package com.itgrids.partyanalyst.dao.hibernate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IPRPWeightegesDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dto.StrategyVO;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;

public class StrategyModelTargetingServiceTest  extends BaseDaoTestCase{
	IStrategyModelTargetingService strategyModelTargetingService;
	IPartyTrendsDAO partyTrendsDAO;
	IPRPWeightegesDAO prpWeightegesDAO;

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

	public IPRPWeightegesDAO getPrpWeightegesDAO() {
		return prpWeightegesDAO;
	}

	public void setPrpWeightegesDAO(IPRPWeightegesDAO prpWeightegesDAO) {
		this.prpWeightegesDAO = prpWeightegesDAO;
	}

	public void setPartyTrendsDAO(IPartyTrendsDAO partyTrendsDAO) {
		this.partyTrendsDAO = partyTrendsDAO;
	}

	public void testBuild(){
		StrategyVO strategyVO = getRang();
		Document document = new Document();
		   String filePath = "D:"+"/1.pdf";

		   File file = new File(filePath);
		   PdfWriter writer = null;
		   try {
			   file.createNewFile();
			   writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
		   document.open();
		  /* StratagicReportsServicePdf stratagicReportsServicePdf = new StratagicReportsServicePdf();
		   stratagicReportsServicePdf.strategyModelTargetingService = strategyModelTargetingService;*/
		try{
			//stratagicReportsServicePdf.buildPdfDelegator(strategyVO);
			strategyModelTargetingService.getTopPanchayats(strategyVO, document, writer);
		}catch (Exception e) {
			   e.printStackTrace();
		   }
		 document.close();
	}

	public StrategyVO getRang(){
		StrategyVO strategyVO = new StrategyVO();
		strategyVO.setConstituencyId(282l);//
		strategyVO.setPartyId(872l);
		List<Long> electionIds = new ArrayList<Long>();
		electionIds.add(38l);
		electionIds.add(3l);
		strategyVO.setPublicationId(10l);
		strategyVO.setConsiderRange(true);
		strategyVO.setElectionIds(electionIds);
		Map<Long,Float> castePercents = new HashMap<Long,Float>();
		castePercents.put(86l,0.60f);	//gouda
		castePercents.put(110l,0.60f);	//settibalaja
		castePercents.put(211l,0.15f);  //Mala
		castePercents.put(288l,0.70f);//kapu
		castePercents.put(285l,0.15f);//reddy
		castePercents.put(290l,0.80f);//kamma
		castePercents.put(189l,0.70f);//madiga
		castePercents.put(289l,0.80f);//Kshatriya
		castePercents.put(159l,0.70f);//Thurpu Kapu
		castePercents.put(61l ,0.60f);//Chakali/Rajaka
		castePercents.put(103l,0.60f);//Padmashali
		castePercents.put(38l ,0.60f);//Mangali
		castePercents.put(287l,0.50f);//Velama
		castePercents.put(286l,0.65f);//Vysya
		castePercents.put(0l,0.50f);//others
		Double regainVotrsWeigthPerc = prpWeightegesDAO.getPRPWeightageByConstiId(strategyVO.getConstituencyId());
		  if(regainVotrsWeigthPerc == null || regainVotrsWeigthPerc == 0d)
			  regainVotrsWeigthPerc = 0d;
		  Double prevTrendWeigthPerc = 80d-regainVotrsWeigthPerc;
		  
		strategyVO.setCastePercents(null);
		strategyVO.setPrevTrnzWt(prevTrendWeigthPerc);
		strategyVO.setYoungWt(10d);
		strategyVO.setPrpWt(regainVotrsWeigthPerc);
		strategyVO.setAgedWt(10d);
		strategyVO.setTotalCastWt(0d);
		strategyVO.setAutoCalculate(true);
		/*strategyVO.setBase(jObj.getLong("base"));
		strategyVO.setAssured(jObj.getLong("assured"));
		strategyVO.setTdpPerc(jObj.getLong("partyPerc"));*/
		strategyVO.setEffectPartyId(662l);
		strategyVO.setEffectElectionId(38l);
	
			strategyVO.setWorstMin(0d);
			strategyVO.setWorstMax(21.12d);
			strategyVO.setVeryPoorMin(21.13);
			strategyVO.setVeryPoorMax(29d);
			strategyVO.setPoorMin(29.01d);
			strategyVO.setPoorMax(36.87d);
			strategyVO.setOkMin(36.88d);
			strategyVO.setOkMax(41.88);
			strategyVO.setStrongMin(41.89);
			strategyVO.setStrongMax(49.76);
			strategyVO.setVeryStrongMin(49.77);
			strategyVO.setVeryStrongMax(100d);
		
		return strategyVO;
	}
}
