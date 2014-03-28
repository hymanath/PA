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
	public List<Object> getPrioritiesToTarget(StrategyVO strategyVO,boolean partyPerformanceReq);
	
	public void generateImpFamilesTable(Document document , List<ImpFamilesVO> list,String reqType);
	
	public void generateCasteWiseTable(Document document,Map<String,Float> casteNamePercMap);
	
	public void panchayatwisePartyPerformanceTable(Document document,List<PartyPositionVO> panchayatList,Long rank,String heading);
	
	public void panchayatWiseTargetVotesTable(Document document,List<PanchayatVO> totalCastesList);
	 
	public void panchayatWiseTargetYoungVotesTable(Document document,List<PanchayatVO> totalCastesList,String type);
	 
	public void prpEffectTableTable(Document document,List<PartyEffectVO> list);
	
	public void generatePdfForMatrixReport(Document document,List<PartyPositionVO>  previousTrends);
	
	public void orderOFPriorityTable(Document document,List<OrderOfPriorityVO> list,int count);
	
	public void buildPanchayatsClassificationBlock(Document document, List<OrderOfPriorityVO>  panchayatsClassific);
	
	public void buildChart(Document doc);
	
	public void buildPiChart(Document document,List<OrderOfPriorityVO> panchayatsClassification,PdfWriter writer);
	
	public void getImpFamilesList(Long panchayatId,Long publicationDateId,List<ImpFamilesVO> impfamilesList,String type,String reqFor);
	
	public void getTopPanchayats(StrategyVO strategyVO,Document document,PdfWriter writer);
	
	 public void buildChartForPartyPerformanceReort(Document document,List<PartyPositionVO> list,PdfWriter writer,String heading);
	 
	 public List<Object> getCriticalPanchayats(Long constituencyId);
}
