package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dto.ImpFamilesVO;
import com.itgrids.partyanalyst.dto.OrderOfPriorityVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.StrategyVO;

public interface IStrategyModelTargetingService {
	public List<Object> getPrioritiesToTarget(StrategyVO strategyVO,String path);
	
	public void generateImpFamilesTable(Document document , List<ImpFamilesVO> list);
	
	public void generateCasteWiseTable(Document document,Map<String,Float> casteNamePercMap);
	
	public void panchayatwisePartyPerformanceTable(Document document,List<PartyPositionVO> panchayatList,Long rank);
	
	public void panchayatWiseTargetVotesTable(Document document,List<PanchayatVO> totalCastesList);
	 
	public void panchayatWiseTargetYoungVotesTable(Document document,List<PanchayatVO> totalCastesList,String type);
	 
	public void prpEffectTableTable(Document document,List<PartyEffectVO> list);
	
	public void generatePdfForMatrixReport(Document document,List<PartyPositionVO>  previousTrends);
	
	public void orderOFPriorityTable(Document document,List<OrderOfPriorityVO> list,int count);
	
	public void buildPanchayatsClassificationBlock(Document document, List<OrderOfPriorityVO>  panchayatsClassific);
	
	public void buildChart(Document doc);
	
	public void buildChartForPartyPerformanceReort(Document doc,List<PartyPositionVO> list);
	
	public void buildPiChart(Document document,List<OrderOfPriorityVO> panchayatsClassification,PdfWriter writer);
	
	
}
