package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itextpdf.text.Document;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;

public interface IPdfReportsService 
{

	public void infectedBoothsReport(Document document,EffectedBoothsResponse output);
	
	public void criticalPanchayatsReport(Document document,List<Object> output);
	
	public void pollingPercentageReport(Document document,List<PartyPositionVO> list,String heading,Double perc);
	
	public void pollingPercentageReportForHighPolling(Document document,List<PartyPositionVO> list,String heading,Double perc);
	
	public void voterAvgAgeReport(Document document,SelectOptionVO result);
	
	public void buildPanchaytWiseReport(Document document,List<PartyPositionVO> result);
	
	public void generatepdfForBoothResult(Document document , List<BoothResultVO> result);
	
}
