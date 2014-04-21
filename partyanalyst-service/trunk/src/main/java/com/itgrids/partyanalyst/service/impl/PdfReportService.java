package com.itgrids.partyanalyst.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.OrderOfPriorityVO;
import com.itgrids.partyanalyst.dto.PanchayatCountVo;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.service.IPdfReportsService;

public class PdfReportService implements IPdfReportsService
{

	private static final Logger LOG = Logger.getLogger(PdfReportService.class);
	
	private static Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	private static Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN, 8,Font.NORMAL);
	
	public void infectedBoothsReport(Document document,EffectedBoothsResponse output)
	{
		try
		{
			LOG.info("Entered into infectedBoothsReport in PdfReportService Service");
			
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(50);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Infected Booths",BIGFONT));
	 	  	cell.setColspan(3);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Polling Stations",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	/*c1 = new PdfPCell(new Phrase("Effected Count",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);*/
			
			
			
			
			
				for (PanchayatCountVo panchayatCountVo : output.getPanchayats())
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(panchayatCountVo.getPanchayatName(),SMALLFONT));
  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			StringBuffer sb = new StringBuffer();
	  			Integer count = 0;
	  			Integer size = panchayatCountVo.getBooths().size();
	  			for(Long boothId : panchayatCountVo.getBooths())
	  			{
	  				count ++;
	  				sb.append(boothId);
	  				if(count != size)
	  				sb.append(",");
	  			}
	  			c2 = new PdfPCell(new Phrase(sb.toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			//c2 = new PdfPCell(new Phrase(Long.valueOf(panchayatCountVo.getEffectedCount()).toString(),SMALLFONT));
	  			//c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			//table.addCell(c2);
			}
				float[] widths = new float[] {0.5f,0.5f};
				table.setWidths(widths);
	 	  	document.add(table);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in infectedBoothsReport in PdfReportService Service",e);
		}
	}

	public void criticalPanchayatsReport(Document document, List<Object> output)
	{
		try
		{
			LOG.info("Entered into criticalPanchayatsReport in PdfReportService Service");
			DecimalFormat f = new DecimalFormat("##.##");
			PdfPTable table = new PdfPTable(4);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Strategy Suggestive Model Table",BIGFONT));
	 	  	cell.setColspan(4);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Status",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("No of Panchayats",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Gainable Votes %",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Gainable Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	@SuppressWarnings("unchecked")
			List<OrderOfPriorityVO>	list1 =  (List<OrderOfPriorityVO>)output.get(0);
	 	  	if(list1 != null)
    		for (OrderOfPriorityVO orderOfPriorityVO : list1)
    		{
    			PdfPCell c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getName(),SMALLFONT));
  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getTotalVoters().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(f.format(orderOfPriorityVO.getOpportunityPerc()),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getOpportunity().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
			}
	 	  	
	 	  	
	 	  	document.add(table);
	 	  	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
	 	  	@SuppressWarnings("unchecked")
			List<OrderOfPriorityVO>	list2 =  (List<OrderOfPriorityVO>)output.get(1);
	 	  	criticalPanchaytsReport2(document,list2);
	 	  	
	 	  	
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in criticalPanchayatsReport in PdfReportService Service",e);
		}
	}
	
	public void criticalPanchaytsReport2(Document document, List<OrderOfPriorityVO>	list)
	{
		try
		{
			LOG.info("Entered into criticalPanchaytsReport2 in PdfReportService Service");
			 
			PdfPTable table = new PdfPTable(6);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Panchayats - Top Priority & Opportunity",BIGFONT));
	 	  	cell.setColspan(6);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
	 	  	c1.setRowspan(2);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
	 	  	c1.setRowspan(2);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Targeted 2014 vs 2009",BIGFONT));
	 	  	c1.setColspan(2);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Difference",BIGFONT));
	 	  	c1.setColspan(2);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	

	 	  	c1 = new PdfPCell(new Phrase("Targeted Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("2009 TDP Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("%",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	
	 	  	for (OrderOfPriorityVO orderOfPriorityVO : list)
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getName(),SMALLFONT));
  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getTotalVoters().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getTotalVoters().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getPreviousVoters().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getOpportunity().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(orderOfPriorityVO.getOpportunityPerc().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);

			}
	 	  	table.setHeaderRows(1);
	 	  	document.add(table);
			
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in criticalPanchaytsReport2 in PdfReportService Service",e);
		}
	}

	public void pollingPercentageReport(Document document,List<PartyPositionVO> list,String heading,Double pollingPerc)
	{
		try
		{
			LOG.info("Entered into pollingPercentageReport in PdfReportService Service");
			
			PdfPTable table = new PdfPTable(12);
			 table.setWidthPercentage(100);

			// int padding =4;
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase(heading,BIGFONT));
	 	  	cell.setColspan(12);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Booth",BIGFONT));
	 	  	c1.setPaddingTop(10);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
	 	  	c1.setPaddingTop(10);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Location",BIGFONT));
	 	  	c1.setPaddingTop(10);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Villages Covered",BIGFONT));
	 	  	c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Total Votes 2009",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polled Votes 2009",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polling %",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("TDP Votes 2009",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("TDP %",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("To Decrease",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polling @"+pollingPerc+"",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Lost Votes",BIGFONT));
	 		c1.setPaddingTop(5);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	for (PartyPositionVO partyPositionVO : list)
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(partyPositionVO.getName(),SMALLFONT));
	 	  		c2.setPaddingTop(10);
  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getLocation(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getLocalbodyName(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getVillagesCovered(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalVoters().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalValidVotes().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			double per=partyPositionVO.getPollingPercentage();
	  			//c2 = new PdfPCell(new Phrase(partyPositionVO.getPollingPercentage().toString(),SMALLFONT));
	  			c2 =  new PdfPCell(new Phrase(new DecimalFormat("##.##").format(per),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getWeakPollingPercentVOList().get(0).getPartyTotalvotes().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			double per1=partyPositionVO.getWeakPollingPercentVOList().get(0).getPartyPercentage();
	  			//c2 = new PdfPCell(new Phrase(partyPositionVO.getWeakPollingPercentVOList().get(0).getPartyPercentage().toString(),SMALLFONT));
	  			c2 =  new PdfPCell(new Phrase(new DecimalFormat("##.##").format(per1),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			double per2=partyPositionVO.getMinValue();
	  			//c2 = new PdfPCell(new Phrase(partyPositionVO.getMinValue().toString(),SMALLFONT));
	  			c2 =  new PdfPCell(new Phrase(new DecimalFormat("##.##").format(per2),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(Double.valueOf(partyPositionVO.getRangePercentage()).toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getLostSeats().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
			}
	 	  	table.setHeaderRows(1);
	 	  	float[] widths = new float[] {1.1f, 1.7f ,1.7f,1.7f,1.2f, 1.7f,1.2f,1.1f,1.1f,1.3f,1.1f,1.1f};
			table.setWidths(widths);
	 	  	document.add(table);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in pollingPercentageReport in PdfReportService Service",e);
		}
	}
	
	public void pollingPercentageReportForHighPolling(Document document,List<PartyPositionVO> list,String heading,Double pollingPerc)
	{
		try
		{
			LOG.info("Entered into pollingPercentageReport in PdfReportService Service");
			document.newPage();
			PdfPTable table = new PdfPTable(13);
			 table.setWidthPercentage(100);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase(heading,BIGFONT));
	 	  	cell.setColspan(13);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Booth",BIGFONT));
	 	  	c1.setPaddingTop(25);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
	 	  	c1.setPaddingTop(25);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Location",BIGFONT));
	 	  	c1.setPaddingTop(25);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Villages Covered",BIGFONT));
	 	  	c1.setPaddingTop(25);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Total Votes 2009",BIGFONT));
	 	  	c1.setPaddingTop(15);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polled Votes 2009",BIGFONT));
	 	  	c1.setPaddingTop(20);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polling %",BIGFONT));
	 	  	c1.setPaddingTop(20);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("TDP Votes 2009",BIGFONT));
	 	  	c1.setPaddingTop(20);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("TDP %",BIGFONT));
	 	  	c1.setPaddingTop(20);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Scope To Improve(Avg Poll%("+pollingPerc+"))",BIGFONT));
	 	  	c1.setPaddingTop(10);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polling @"+pollingPerc+"",BIGFONT));
	 	  	c1.setPaddingTop(20);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("To Target",BIGFONT));
	 	  	c1.setPaddingTop(20);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("TDP Lost Votes",BIGFONT));
	 	  	c1.setPaddingTop(20);
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	for (PartyPositionVO partyPositionVO : list)
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(partyPositionVO.getName(),SMALLFONT));
	 	  		c2.setPaddingTop(10);
  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getLocation(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getLocalbodyName(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getVillagesCovered(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalVoters().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalValidVotes().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			double per3=partyPositionVO.getPollingPercentage();
	  			//c2 = new PdfPCell(new Phrase(new DecimalFormat("##.##").format(partyPositionVO.getPollingPercentage()).toString(),SMALLFONT));
	  			c2 =  new PdfPCell(new Phrase(new DecimalFormat("##.##").format(per3),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getStrongPollingPercentVOList().get(0).getPartyTotalvotes().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			double per4=partyPositionVO.getStrongPollingPercentVOList().get(0).getPartyPercentage();
	  			//c2 = new PdfPCell(new Phrase(new DecimalFormat("##.##").format(partyPositionVO.getStrongPollingPercentVOList().get(0).getPartyPercentage()).toString(),SMALLFONT));
	  			c2 =  new PdfPCell(new Phrase(new DecimalFormat("##.##").format(per4),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			double per5=partyPositionVO.getMaxValue();
	  			//c2 = new PdfPCell(new Phrase(partyPositionVO.getMaxValue().toString(),SMALLFONT));
	  			c2 =  new PdfPCell(new Phrase(new DecimalFormat("##.##").format(per5),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(Double.valueOf(partyPositionVO.getRangePercentage()).toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getToTarget().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(partyPositionVO.getToImprove().toString(),SMALLFONT));
	  			c2.setPaddingTop(10);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
			}
	 	  	table.setHeaderRows(1);
	 	  	float[] widths = new float[] {1.1f, 1.7f ,1.7f,1.7f,1.2f, 1.7f,1.2f,1.1f,1.1f,1.4f,1.3f,1.1f,1.1f};
			table.setWidths(widths);
			
	 	  	document.add(table);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in pollingPercentageReport in PdfReportService Service",e);
		}
	}

	public void voterAvgAgeReport(Document document, SelectOptionVO result)
	{
		try
		{
			LOG.info("Entered into voterAvgAgeReport in PdfReportService Service");
			
			PdfPTable table = new PdfPTable(3);
			 table.setWidthPercentage(60);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Constituency Wise Voter Average Age Report",BIGFONT));
	 	  	cell.setColspan(3);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Constituency",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Average Voters Age",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
 	  		
	 	  	PdfPCell c2 = new PdfPCell(new Phrase(result.getName(),SMALLFONT));
	 	  	c2.setPaddingTop(5);
	 	  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
  			table.addCell(c2);
  			c2 = new PdfPCell(new Phrase(result.getTotalCount().toString(),SMALLFONT));
  			c2.setPaddingTop(5);
  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
  			table.addCell(c2);
  			c2 = new PdfPCell(new Phrase(result.getPerc().toString(),SMALLFONT));
  			c2.setPaddingTop(5);
  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
  			table.addCell(c2);
  			float[] widths = new float[] {1.2f, 1.2f ,1.2f};
			table.setWidths(widths);
	 	  	document.add(table);
	 	  	
	 	  	buildMandalWiseReport(document,result.getSelectOptionsList());
	 	  	buildPanchayatWiseReport(document,result.getSelectOptionsList1());
	 	  	buildBoothWiseWiseReport(document,result.getSelectOptionsList2());
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in voterAvgAgeReport in PdfReportService Service",e);
		}
	}
	
	public void buildMandalWiseReport(Document document,List<SelectOptionVO> list)
	{
		try
		{
			LOG.info("Entered into buildMandalWiseReport in PdfReportService Service");
			
			PdfPTable table = new PdfPTable(3);
			 table.setWidthPercentage(60);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Mandal Wise Voter Average Age Report",BIGFONT));
	 	  	cell.setColspan(3);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal/Muncipality",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Average Voters Age",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	for (SelectOptionVO selectOptionVO : list)
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(selectOptionVO.getName(),SMALLFONT));
	 	  		c2.setPaddingTop(5);
	 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			c2 = new PdfPCell(new Phrase(selectOptionVO.getTotalCount().toString(),SMALLFONT));
	  			c2.setPaddingTop(5);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			c2 = new PdfPCell(new Phrase(selectOptionVO.getPerc().toString(),SMALLFONT));
	  			c2.setPaddingTop(5);
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
			}
	 	   float[] widths = new float[] {1.3f, 1.3f ,1.3f};
			table.setWidths(widths);
	 	  	document.add(table);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in buildMandalWiseReport in PdfReportService Service",e);
		}
	}
	
	public void buildPanchayatWiseReport(Document document,List<SelectOptionVO> list)
	{
		try
		{
			LOG.info("Entered into buildMandalWiseReport in PdfReportService Service");
			document.newPage();
			PdfPTable table = new PdfPTable(4);
			 table.setWidthPercentage(70);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Panchayat Wise Voter Average Age Report",BIGFONT));
	 	  	cell.setColspan(4);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal/Muncipality",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Pancayat",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Average Voters Age",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	
	 	  	
	 	  	for (SelectOptionVO selectOptionVO : list)
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(selectOptionVO.getName(),SMALLFONT));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setRowspan(selectOptionVO.getSelectOptionsList().size());
				 c2.setPaddingTop(75);
				table.addCell(c2);
	 	  		for (SelectOptionVO selectOptionVO1 : selectOptionVO.getSelectOptionsList())
	 	  		{
	 	  			c2 = new PdfPCell(new Phrase(selectOptionVO1.getName(),SMALLFONT));
	 	  			c2.setPaddingTop(5);
	 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(selectOptionVO1.getTotalCount().toString(),SMALLFONT));
		  			c2.setPaddingTop(5);
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(selectOptionVO1.getPerc().toString(),SMALLFONT));
		  			c2.setPaddingTop(5);
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
				}
	 	  		
			}
	 	  	table.setHeaderRows(1);
	 	  	float[] widths = new float[] {1.2f, 1.3f ,1.0f,1.0f};
			table.setWidths(widths);
	 	  	document.add(table);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in buildMandalWiseReport in PdfReportService Service",e);
		}
	}
	
	public void buildBoothWiseWiseReport(Document document,List<SelectOptionVO> list)
	{
		try
		{
			LOG.info("Entered into buildMandalWiseReport in PdfReportService Service");
			document.newPage();
			PdfPTable table = new PdfPTable(4);
			 table.setWidthPercentage(60);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Booth Wise Voter Average Age Report",BIGFONT));
	 	  	cell.setColspan(4);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Booth",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Average Voters Age",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	
	 	  	
	 	  	for (SelectOptionVO selectOptionVO : list)
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(selectOptionVO.getName(),SMALLFONT));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setRowspan(selectOptionVO.getSelectOptionsList().size());
			    //c2.setPaddingTop(150);
	  			table.addCell(c2);
	 	  		for (SelectOptionVO selectOptionVO1 : selectOptionVO.getSelectOptionsList())
	 	  		{
	 	  			c2 = new PdfPCell(new Phrase(selectOptionVO1.getName(),SMALLFONT));
	 	  			//c2.setPaddingTop(5);
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(selectOptionVO1.getTotalCount().toString(),SMALLFONT));
		  			//c2.setPaddingTop(5);
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(selectOptionVO1.getPerc().toString(),SMALLFONT));
		  			//c2.setPaddingTop(5);
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
				}
	 	  		
			}
	 	  	table.setHeaderRows(1);
	 	  	float[] widths = new float[] {1.3f, 1.0f ,1.0f,1.0f};
			table.setWidths(widths);
	 	  	document.add(table);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in buildMandalWiseReport in PdfReportService Service",e);
		}
	}

	public void buildPanchaytWiseReport(Document document,List<PartyPositionVO> result)
	{
		try
		{
			LOG.info("Entered into buildPanchaytWiseReport in PdfReportService Service");
			
			PdfPTable table = new PdfPTable(7);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Election Results Comparision Table b/w 2009(TDP @ Top Position) Assembly & 2013 Panchayat",BIGFONT));
	 	  	cell.setColspan(7);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Total Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Votes Polled",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("TDP Gained",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Margin",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("2013 Total Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Win Party Name",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	for (PartyPositionVO partyPositionVO : result)
	 	  	{
	 	  		if(partyPositionVO.getRank().equals(1l))
	 	  		{
	 	  			PdfPCell c2 = new PdfPCell(new Phrase(partyPositionVO.getName(),SMALLFONT));
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalVoters().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalValidVotes().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getSelectedPartyTotalVoters().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getPartyPercentage().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getWinPartyTotal().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getWinPartyName().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
	 	  		}
	 	  		
			}
	 	  	
	 	  	table.setHeaderRows(1);
	 	  	document.add(table);
	 	  	
	 	  	buildPanchaytWiseReportForRank2(document,result);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in buildPanchaytWiseReport in PdfReportService Service",e);
		}
	}
	
	
	public void buildPanchaytWiseReportForRank2(Document document,List<PartyPositionVO> result)
	{
		try
		{
			LOG.info("Entered into buildPanchaytWiseReport in PdfReportService Service");
			
			PdfPTable table = new PdfPTable(7);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Election Results Comparision Table b/w 2009(TDP @ Lower Positions) Assembly & 2013 Panchayat",BIGFONT));
	 	  	cell.setColspan(7);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Total Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Votes Polled",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("TDP Gained",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Margin",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("2013 Total Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Win Party Name",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	for (PartyPositionVO partyPositionVO : result)
	 	  	{
	 	  		if(partyPositionVO.getRank().equals(2l))
	 	  		{
	 	  			PdfPCell c2 = new PdfPCell(new Phrase(partyPositionVO.getName(),SMALLFONT));
					c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalVoters().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getTotalValidVotes().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getSelectedPartyTotalVoters().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getPartyPercentage().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getWinPartyTotal().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			
		  			c2 = new PdfPCell(new Phrase(partyPositionVO.getWinPartyName().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
	 	  		}
	 	  		
			}
	 	  	
	 	  	table.setHeaderRows(1);
	 	  	document.add(table);
		}
		catch (Exception e)
		{
			LOG.error("Exception raised in buildPanchaytWiseReport in PdfReportService Service",e);
		}
	}
	
	public void generatepdfForBoothResult(Document document , List<BoothResultVO> result)
	{
		try
		{
			LOG.info("Entered into generatepdfForBoothResult in PdfReportService Service");
			
			PdfPTable table = new PdfPTable(8);
	    	document.add( new Paragraph(" ") );
	    	document.add( new Paragraph(" ") );
    		PdfPCell cell;
	 	  	cell = new PdfPCell(new Phrase("Booth Wise Performance ",BIGFONT));
	 	  	cell.setColspan(8);
	 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	cell.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(cell);
	 	  	
	 	  	PdfPCell c1 = new PdfPCell(new Phrase("Booth No",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Location",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Villages Covered",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);

	 	  	c1 = new PdfPCell(new Phrase("Mandal",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Votes Earned",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polled Votes",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Polling %",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	c1 = new PdfPCell(new Phrase("Votes %",BIGFONT));
	 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	  	c1.setBackgroundColor(BaseColor.YELLOW);
	 	  	table.addCell(c1);
	 	  	
	 	  	for (BoothResultVO boothResultVO : result)
	 	  	{
	 	  		PdfPCell c2 = new PdfPCell(new Phrase(boothResultVO.getPartNo(),SMALLFONT));
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(boothResultVO.getLocation(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(boothResultVO.getVillagesCovered(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(boothResultVO.getMandal(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(Long.valueOf(boothResultVO.getVotesEarned()).toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(Long.valueOf(boothResultVO.getVotesEarned()).toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(boothResultVO.getPollingPercentage().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
	  			
	  			c2 = new PdfPCell(new Phrase(boothResultVO.getPercentage().toString(),SMALLFONT));
	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	  			table.addCell(c2);
			}
	 	  	
	 	  	table.setHeaderRows(1);
	 	  	document.add(table);
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised in generatepdfForBoothResult in PdfReportService Service",e);
		}
	}
	
}
