package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itgrids.partyanalyst.dto.PdfVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAdderdOrDeletedRengesInfoVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IVoterModifiationPdfsGenerations;
/**
 * 
 * This Class is used for generating pdfs for voter modification report in constituency level
 * @author Prasad Thiragabathina
 * 
 */
public class VoterModifiationPdfsGenerations implements IVoterModifiationPdfsGenerations{

	 private static Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 private static Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN, 8,Font.NORMAL);
	 
	 private static Font BIGFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 6,Font.BOLD);
	 private static Font SMALLFONT1 = new Font(Font.FontFamily.TIMES_ROMAN, 5,Font.NORMAL);
	 private static Logger LOG = Logger.getLogger(VoterModifiationPdfsGenerations.class);
	 private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	 
	 private static Font style1 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 private static Font style2 = new Font(Font.FontFamily.TIMES_ROMAN, 8,Font.NORMAL);
	 /**
	  * Range Wise Voter Additions In Panchayats
	  * @param document
	  * @param returnList
	  * @param constituenyName
	  * @Date 10-12-2013
	  */
	  public void createTableForAddedVoters(Document document,List<VoterAdderdOrDeletedRengesInfoVO> returnList,String constituenyName)
	  {
	    try
	    {
	    	LOG.info("Enterd into createTableForAddedVoters() method in VoterModifiationPdfsGenerations Class");
	    	
	    	
	    	java.util.List<VoterAdderdOrDeletedRengesInfoVO> voterAdderdList = returnList.get(0).getAddedVoeterDetails();
	    	if(voterAdderdList != null)
	    	{
	    		PdfPTable table = new PdfPTable(8);
		    	document.add( new Paragraph(" ") );
		    	document.add( new Paragraph(" ") );
	    		PdfPCell cell;
		 	  	cell = new PdfPCell(new Phrase("Range Wise Voter Additions In Panchayats",BIGFONT));
		 	  	cell.setColspan(8);
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);
		 	  	
		 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("1 - 50",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("51-100",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("101 - 150",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	c1 = new PdfPCell(new Phrase("151 - 200",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("201 - 300",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Above 300",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("No Changes",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	table.setHeaderRows(1);
	    		 	  	
	 	  	for (VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO : returnList) {
	 	  		java.util.List<VoterAdderdOrDeletedRengesInfoVO> voterAdderdRengesInfoVOList = voterAdderdOrDeletedRengesInfoVO.getAddedVoeterDetails();
	 	  		if(voterAdderdRengesInfoVOList != null && voterAdderdRengesInfoVOList.size() > 0)
	 	  		{
	 	  			for (VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO2 : voterAdderdRengesInfoVOList) {
		 	  			PdfPCell c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getName(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt1To50AddedCount().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt51To100AddedCount().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt101To150AddedCount().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt151To200Addedcount().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt201To300AddedCount().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getAbove300AddedCount().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getNoChangesAddedCount().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			/*table.addCell(voterAdderdOrDeletedRengesInfoVO2.getName());
		 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt1To50AddedCount().toString());
		 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt51To100AddedCount().toString());
		 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt101To150AddedCount().toString());
		 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt151To200Addedcount().toString());
		 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt201To300AddedCount().toString());
		 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getAbove300AddedCount().toString());
		 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getNoChangesAddedCount().toString());*/
		 	  		}
	 	  		}
	 	  		
	 	  		java.util.List<VoterAdderdOrDeletedRengesInfoVO> voterAdderRengesInfoVOListForRuralUrban = voterAdderdOrDeletedRengesInfoVO.getRuralUrbanAddedVoterDetails();
	 	  		if(voterAdderRengesInfoVOListForRuralUrban != null)
	 	  		{
	 	  			for (VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO2 : voterAdderRengesInfoVOListForRuralUrban) {
	 	  				PdfPCell c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getName(),SMALLFONT));
	 	  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt1To50AddedCount().toString(),SMALLFONT));
	 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt51To100AddedCount().toString(),SMALLFONT));
	 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt101To150AddedCount().toString(),SMALLFONT));
	 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt151To200Addedcount().toString(),SMALLFONT));
	 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt201To300AddedCount().toString(),SMALLFONT));
	 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getAbove300AddedCount().toString(),SMALLFONT));
	 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getNoChangesAddedCount().toString(),SMALLFONT));
	 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	 		  			table.addCell(c2);
	 	  				/*table.addCell(voterAdderdOrDeletedRengesInfoVO2.getName());
	 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt1To50AddedCount().toString());
	 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt51To100AddedCount().toString());
	 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt101To150AddedCount().toString());
	 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt151To200Addedcount().toString());
	 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt201To300AddedCount().toString());
	 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getAbove300AddedCount().toString());
	 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getNoChangesAddedCount().toString());*/
	 	  			}
	 	  		}
	 	  	 }
	 	  	float[] widths = new float[] {1f, 1f ,1f,1f,1f, 1f ,1f,1f};
	 	  	table.setWidths(widths);
	 	  	document.add(table);
	    	}
	 	  	
		} 
	    catch (Exception e)
		{
			LOG.debug("Exception raised in createTableForAddedVoters() method in VoterModifiationPdfsGenerations Class",e);
		}
	  	
	  }
	  /**
	   * Range Wise Voter Deletions In Panchayats
	   * @param document
	   * @param returnList 
	   * @param constituenyName
	   * @date 10-12-2013
	   */
	  public void deletedVotersDetails(Document document,List<VoterAdderdOrDeletedRengesInfoVO> returnList,String constituenyName)
	  {
	     
		    try
		    {
		    	LOG.info("Enterd into deletedVotersDetails() method in VoterModifiationPdfsGenerations Class");
		    	java.util.List<VoterAdderdOrDeletedRengesInfoVO> voterAdderd = returnList.get(0).getDeletedVoterDetails();
		    	if(voterAdderd != null)
		    	{
		    		PdfPTable table = new PdfPTable(8);
			 	  	document.add( new Paragraph(" ") );
			 	  	document.add( new Paragraph(" ") );
			 	  	
			 	  	PdfPCell cell;
			 	  	cell = new PdfPCell(new Phrase("Range Wise Voter Deletions In Panchayats",BIGFONT));
			 	  	cell.setColspan(8);
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);
			 	  									

			 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);

			 	  	c1 = new PdfPCell(new Phrase("1 - 50",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);

			 	  	c1 = new PdfPCell(new Phrase("51-100",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("101 - 150",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	c1 = new PdfPCell(new Phrase("151 - 200",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("201 - 300",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Above 300",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("No Changes",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
			 	  	table.addCell(c1);
			 	  	table.setHeaderRows(1);
			 	  	//table.setWidthPercentage(100);
			 	  	for (VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO : returnList) {
			 	  		java.util.List<VoterAdderdOrDeletedRengesInfoVO> voterAdderdRengesInfoVOList = voterAdderdOrDeletedRengesInfoVO.getDeletedVoterDetails();
			 	  		for (VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO2 : voterAdderdRengesInfoVOList) {
			 	  			PdfPCell c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getName(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt1To50DeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt51To100DeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt101To150DeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt151To200DeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt201To300DeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getAbove300DeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getNoChangesDeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			
			 	  			/*table.addCell(voterAdderdOrDeletedRengesInfoVO2.getName());
			 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt1To50DeletedCount().toString());
			 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt51To100DeletedCount().toString());
			 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt101To150DeletedCount().toString());
			 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt151To200DeletedCount().toString());
			 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt201To300DeletedCount().toString());
			 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getAbove300DeletedCount().toString());
			 	  			table.addCell(voterAdderdOrDeletedRengesInfoVO2.getNoChangesDeletedCount().toString());*/
			 	  		}
			 	  		java.util.List<VoterAdderdOrDeletedRengesInfoVO> voterAdderRengesInfoVOListForRuralUrban = voterAdderdOrDeletedRengesInfoVO.getRuralUrbanDeletedVoterDetails();
			 	  		if(voterAdderRengesInfoVOListForRuralUrban != null)
			 	  		{
			 	  			for (VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO2 : voterAdderRengesInfoVOListForRuralUrban) {
			 	  				PdfPCell c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getName(),SMALLFONT));
			 	  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt1To50DeletedCount().toString(),SMALLFONT));
			 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt51To100DeletedCount().toString(),SMALLFONT));
			 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt101To150DeletedCount().toString(),SMALLFONT));
			 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt151To200DeletedCount().toString(),SMALLFONT));
			 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getBt201To300DeletedCount().toString(),SMALLFONT));
			 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getAbove300DeletedCount().toString(),SMALLFONT));
			 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 		  			c2 = new PdfPCell(new Phrase(voterAdderdOrDeletedRengesInfoVO2.getNoChangesDeletedCount().toString(),SMALLFONT));
			 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 		  			table.addCell(c2);
			 	  				/*table.addCell(voterAdderdOrDeletedRengesInfoVO2.getName());
			 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt1To50DeletedCount().toString());
			 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt51To100DeletedCount().toString());
			 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt101To150DeletedCount().toString());
			 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt151To200DeletedCount().toString());
			 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getBt201To300DeletedCount().toString());
			 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getAbove300DeletedCount().toString());
			 	  				table.addCell(voterAdderdOrDeletedRengesInfoVO2.getNoChangesDeletedCount().toString());*/
			 	  			}
			 	  		}
			 	  	}		    
			 	  	float[] widths = new float[] {1f, 1f ,1f,1f,1f, 1f ,1f,1f};
			 	  	table.setWidths(widths);
			 	  	document.add(table);
				}
		    }
		    	
		    catch (Exception e)
			{
				LOG.debug("Exception raised in deletedVotersDetails() method in VoterModifiationPdfsGenerations Class");
			}
	  	
	  }
	  
	  /**
	   * Panchayat Wise Added/Deleted Voters Count In Constituency
	   * @param document
	   * @param pdfVO
	   * @param constituenyName
	   * @Date 10-12-2013
	   */
	  public void panchayatWiseAddedDeletedVoterDetails(Document document,PdfVO pdfVO,String constituenyName)
	  { 
		    try 
		    {
		    	LOG.info("Enterd into panchayatWiseAddedDeletedVoterDetails() method in VoterModifiationPdfsGenerations Class");
		    	PdfPTable table = new PdfPTable(7);
		    	document.add( new Paragraph(" ") );
		    	document.add( new Paragraph(" ") );
		 	  	PdfPCell cell;
		 	  	cell = new PdfPCell(new Phrase("Panchayat Wise Added/Deleted Voters Count In "+constituenyName+" Constituency",BIGFONT));
		 	  	cell.setColspan(7);
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);
		 	  									

		 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Total Voters",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Added %",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Deleted %",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	/*c1 = new PdfPCell(new Phrase("Grand Total",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);*/
		 	  	
		 	  	
		 	  	List<VotersDetailsVO> voterDetails = pdfVO.getCompleteVoterDetailsVoList();
		 	  	if(voterDetails != null && voterDetails.size() > 0)
		 	  	{
		 	  		for (VotersDetailsVO votersDetailsVO : voterDetails) {
		 	  			PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getTehsilName().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getPanchayatname().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalVoters().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getMaleVotersCountBetween18To25().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			if(votersDetailsVO.getVotersPercentFor18To25() != null)
		 		  	    {
		 	  				c2 = new PdfPCell(new Phrase(votersDetailsVO.getVotersPercentFor18To25().toString(),SMALLFONT));
		 	  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  			table.addCell(c2);
		 		  		}
		 	  			else
		 	  			{
		 	  				c2 = new PdfPCell(new Phrase("0.00",SMALLFONT));
		 	  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  			table.addCell(c2);
		 		  			votersDetailsVO.setVotersPercentFor18To25("0.00");
		 	  			}
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotesFor18To25().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			 if(votersDetailsVO.getVotersPercentFor26To35() != null)
		 	  			  {
		 	  				c2 = new PdfPCell(new Phrase(votersDetailsVO.getVotersPercentFor26To35().toString(),SMALLFONT));
		 	  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  			table.addCell(c2);
		 	  			  }
		 	  			 else
		 	  			 {
		 	  				c2 = new PdfPCell(new Phrase("0.00",SMALLFONT));
		 	  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  			table.addCell(c2);
		 		  			votersDetailsVO.setVotersPercentFor26To35("0.00");
		 	  			 }
		 	  			/*c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor26To35().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);*/
		 	  			
		 	  			 /*table.addCell(votersDetailsVO.getTehsilName().toString());
		 	  			 table.addCell(votersDetailsVO.getPanchayatname().toString());
		 	  			 table.addCell(votersDetailsVO.getMaleVotersCountBetween18To25().toString());
		 		  		 if(votersDetailsVO.getVotersPercentFor18To25() != null)
		 		  		 {
		 		  			table.addCell(votersDetailsVO.getVotersPercentFor18To25().toString());
		 		  		 }else
		 		  		 {
		 	  				 table.addCell("0.00");
		 	  				 votersDetailsVO.setVotersPercentFor18To25("0.00");
		 	  			 }
		 	  			
		 	  			 table.addCell(votersDetailsVO.getTotalMaleVotesFor18To25().toString());
		 	  			 
		 	  			  if(votersDetailsVO.getVotersPercentFor26To35() != null)
		 	  			  {
		 	  				table.addCell(votersDetailsVO.getVotersPercentFor26To35().toString());
		 	  			  }else
		 	  			  {
		 	  				table.addCell("0.00");
		 	  				votersDetailsVO.setVotersPercentFor26To35("0.00");
		 	  			  }
		 	  			 table.addCell(votersDetailsVO.getTotalMaleVotersFor26To35().toString());*/
		 	  		}
		 	  	}
		 	  	float[] widths = new float[] {1f ,1f,1f,1f, 1f ,1f,1f};
		 	  	table.setWidths(widths);
		 	  	table.setHeaderRows(2);
		 	  	document.add(table);
			}
		    catch (Exception e)
			{
				LOG.debug("Exception raised in panchayatWiseAddedDeletedVoterDetails() method in VoterModifiationPdfsGenerations Class");
			}
	  	

	  }

	  /**
	   * Top 10 Voters Added Panchayats(By Added %) In Constituency
	   * @param document
	   * @param voterDetails
	   * @param constituencyName
	   * @param type
	   * @Date 10-12-2013
	   */
	  public void panchayatWiseAddedDeletedVoterDetailsByList(Document document,List<VotersDetailsVO> voterDetails,String constituenyName,String type)
	  { 
		    try 
		    {
		    	LOG.info("Enterd into panchayatWiseAddedDeletedVoterDetailsByList() method in VoterModifiationPdfsGenerations Class");
		    	PdfPTable table = new PdfPTable(4);
		    	document.add( new Paragraph(" ") );
		    	document.add( new Paragraph(" ") );
		 	  	PdfPCell cell;
		 	  	if(type.equalsIgnoreCase("add"))
		 	  	{
		 	  		cell = new PdfPCell(new Phrase("Top 10 Voters Added Panchayats(By Added %) In "+constituenyName+" constituency",BIGFONT));
		 	  	}
		 	  	else
		 	  	{
		 	  		cell = new PdfPCell(new Phrase("Top 10 Voters Deleted Panchayats(By Deleted %) In "+constituenyName+" constituency",BIGFONT));
		 	  	}
		 	  	cell.setColspan(4);
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);
		 	  									

		 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	if(type.equalsIgnoreCase("add"))
		 	  	{
		 	  		c1 = new PdfPCell(new Phrase("Added",BIGFONT));
		 		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  	c1.setBackgroundColor(BaseColor.YELLOW);
		 		  	table.addCell(c1);
		 		  	
		 		  	c1 = new PdfPCell(new Phrase("Added %",BIGFONT));
		 		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  	c1.setBackgroundColor(BaseColor.YELLOW);
		 		  	table.addCell(c1);
		 	  	}
		 	  	else
		 	  	{
		 	  		c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
		 		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  	c1.setBackgroundColor(BaseColor.YELLOW);
		 		  	table.addCell(c1);
		 		  	
		 		  	c1 = new PdfPCell(new Phrase("Deleted %",BIGFONT));
		 		  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  	c1.setBackgroundColor(BaseColor.YELLOW);
		 		  	table.addCell(c1);
		 	  	}
		 	  	
		 	  	
		 	  	/*c1 = new PdfPCell(new Phrase("Grand Total",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);*/
		 	  	
		 	  	table.setHeaderRows(1);
		 	  	
		 	  	if(voterDetails != null && voterDetails.size() > 0)
		 	  	{
		 	  		Long count = 0l;
		 	  		for (VotersDetailsVO votersDetailsVO : voterDetails) {
		 	  			 count++;
		 	  			 if(count == 10)
		 	  			 {
		 	  				break;
		 	  			 }
		 	  			PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getTehsilName().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getPanchayatname().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			if(type.equalsIgnoreCase("add"))
		 	  		  	{
		 	  				c2 = new PdfPCell(new Phrase(votersDetailsVO.getMaleVotersCountBetween18To25().toString(),SMALLFONT));
		 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  			table.addCell(c2);
		 		  			if(votersDetailsVO.getVotersPercentFor18To25() != null)
		 			  	    {
		 		  				c2 = new PdfPCell(new Phrase(votersDetailsVO.getVotersPercentFor18To25().toString(),SMALLFONT));
		 		  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 			  			table.addCell(c2);
		 			  		}
		 		  			else
		 		  			{
		 		  				c2 = new PdfPCell(new Phrase("0.00",SMALLFONT));
		 		  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 			  			table.addCell(c2);
		 			  			votersDetailsVO.setVotersPercentFor18To25("0.00");
		 		  			}
		 	  		  	}
		 	  			else
		 	  			{
		 	  				c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotesFor18To25().toString(),SMALLFONT));
		 		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		  			table.addCell(c2);
		 		  			 if(votersDetailsVO.getVotersPercentFor26To35() != null)
		 		  			  {
		 		  				c2 = new PdfPCell(new Phrase(votersDetailsVO.getVotersPercentFor26To35().toString(),SMALLFONT));
		 		  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 			  			table.addCell(c2);
		 		  			  }
		 		  			 else
		 		  			 {
		 		  				c2 = new PdfPCell(new Phrase("0.00",SMALLFONT));
		 		  				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 			  			table.addCell(c2);
		 			  			votersDetailsVO.setVotersPercentFor26To35("0.00");
		 		  			 }
		 	  			}
		 	  			
		 	  			/*c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor26To35().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);*/
		 	  			 /*table.addCell(votersDetailsVO.getTehsilName().toString());
		 	  			 table.addCell(votersDetailsVO.getPanchayatname().toString());
		 	  			 table.addCell(votersDetailsVO.getMaleVotersCountBetween18To25().toString());
		 		  		 if(votersDetailsVO.getVotersPercentFor18To25() != null)
		 		  		 {
		 		  			table.addCell(votersDetailsVO.getVotersPercentFor18To25().toString());
		 		  		 }else
		 		  		 {
		 	  				 table.addCell("0.00");
		 	  			 }
		 	  			
		 	  			 table.addCell(votersDetailsVO.getTotalMaleVotesFor18To25().toString());
		 	  			 
		 	  			  if(votersDetailsVO.getVotersPercentFor26To35() != null)
		 	  			  {
		 	  				table.addCell(votersDetailsVO.getVotersPercentFor26To35().toString());
		 	  			  }else
		 	  			  {
		 	  				table.addCell("0.00");
		 	  			  }
		 	  			 table.addCell(votersDetailsVO.getTotalMaleVotersFor26To35().toString());*/
		 	  		}
		 	  	}
		 	  	float[] widths = new float[] {1f,1f,1f,1f};
		 	  	table.setWidths(widths);
		 	  	document.add(table);
			}
		    catch (Exception e)
			{
				LOG.debug("Exception raised in panchayatWiseAddedDeletedVoterDetailsByList() method in VoterModifiationPdfsGenerations Class");
			}
	  	
	  }

	  /**
	   * 2014 Draft Electoral Roll Added Voters or Deleted Voters
	   * @param Document
	   * @param PdfVO
	   * @param constituenyName
	   * @param type
	   * @Date 10-12-2013
	   */
	  public void totalAddedOrDeletedVoterDetails(String type,Document document,PdfVO pdfVO,String constituenyName)
	  {	 
		    try 
		    {
		    	LOG.info("Enterd into totalAddedOrDeletedVoterDetails() method in VoterModifiationPdfsGenerations Class");
		    	PdfPTable table = new PdfPTable(10);
		    	document.add( new Paragraph(" ") );
		    	document.add( new Paragraph(" ") );
		 	  	PdfPCell cell;
		 	  	if(type.equalsIgnoreCase("add"))
		 	  	{
		 	  		cell = new PdfPCell(new Phrase("2014 Draft Electoral Roll Added Voters",BIGFONT));
		 	  	
		 	  	}
		 	  	else
		 	  	{
		 	  		cell = new PdfPCell(new Phrase("2014 Draft Electoral Roll Deleted Voters",BIGFONT));
		 	  	}
		 	    cell.setColspan(10);
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);
		 	  	
		 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("Part No",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Voter Id",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	c1 = new PdfPCell(new Phrase("Name",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("H NO",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Gender",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Age",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Relative Name",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Relationship",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	
		 	  	List<VoterVO> voters = null;
		 	  	if(type.equalsIgnoreCase("add"))
		 	  	{
		 	  		voters  = pdfVO.getAddedVoterDetails();
		 	  	}
		 	  	else
		 	  	{
		 	  		voters  = pdfVO.getDeletedVoterDetils();
		 	  	}
		 	  	for(VoterVO voter:voters){
		 	  		PdfPCell c2 = new PdfPCell(new Phrase(checkForNull(voter.getTownShip()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getPanchayatName()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getBoothName()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getVoterIDCardNo()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getFirstName()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getHouseNo()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getGender()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getAge().toString()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getRelativeFirstName()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  		c2 = new PdfPCell(new Phrase(checkForNull(voter.getRelationshipType()),SMALLFONT));
		 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		   			table.addCell(c2);
		 	  			/*table.addCell(checkForNull(voter.getTownShip()));
		 	  			table.addCell(checkForNull(voter.getPanchayatName()));
		 	  			table.addCell(checkForNull(voter.getBoothName()));
		 	  			table.addCell(checkForNull(voter.getVoterIDCardNo()));
		 	  			table.addCell(checkForNull(voter.getFirstName()));
		 	  			table.addCell(checkForNull(voter.getHouseNo()));
		 	  			table.addCell(checkForNull(voter.getGender()));
		 	  			table.addCell(checkForNull(voter.getAge().toString()));
		 	  			table.addCell(checkForNull(voter.getRelativeFirstName()));
		 	  			table.addCell(checkForNull(voter.getRelationshipType()));*/
		 	  			//table.addCell(c2);
		 	     
		 	  		}

		 	  	float[] widths = new float[] { 1.2f, 1.5f ,1f,1.5f,1.5f,1f,1f,0.7f,1.5f,1.8f};
		 	  	table.setWidths(widths);
		 	  	table.setHeaderRows(2);
		 	  	document.add(table);
		 	  	document.newPage();
			}
		    catch (Exception e) {
				LOG.debug("Exception raised in totalAddedOrDeletedVoterDetails() method in VoterModifiationPdfsGenerations Class",e);
			}
	  	
	  }

	  /**
	   * Age Wise Voters Added Info or Deleted Info
	   * @param type
	   * @param document
	   * @param pdfVO
	   * @param constituenctName
	   * @Date 10-12-2013
	   */
	  public void agewiseAddedDeletedVoterDetails(String type,Document document,PdfVO pdfVO,String constituenyName)
	  {	
		    try 
		    {
		    	LOG.info("Enterd into agewiseAddedDeletedVoterDetails() method in VoterModifiationPdfsGenerations Class");
		    	PdfPTable table = new PdfPTable(9);
		    	document.add( new Paragraph(" ") );
		    	document.add( new Paragraph(" ") );	 	  	
		 	  	PdfPCell cell;
		 	  	if(type.equalsIgnoreCase("add"))
		 	  	{
		 	  		cell = new PdfPCell(new Phrase("Age Wise Voters Added Info",BIGFONT));
		 	  	}
		 	  	else
		 	  	{
		 	  		cell = new PdfPCell(new Phrase("Age Wise Voters Deletion Info",BIGFONT));
		 	  	}
		 	  	
		 	  	cell.setColspan(9);
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);
		 	  	
		 	  	
		 	  	PdfPCell c1 = new PdfPCell(new Phrase("Mandal",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Panchayat",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	 c1 = new PdfPCell(new Phrase("First Time Voters(18-22)",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("23 - 25",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("26 - 35",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	c1 = new PdfPCell(new Phrase("36 - 45",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("46 - 60",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("60-Above",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	
		 	  	c1 = new PdfPCell(new Phrase("Total",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
		 	  	table.addCell(c1);
		 	  	//table.setHeaderRows(1);

		 	  	List<VotersDetailsVO> voters = null;
		 	  	if(type.equalsIgnoreCase("add"))
		 	  	{
		 	  		voters =  pdfVO.getAddedVoterDetailsVoList();
		 	  	}
		 	  	else
		 	  	{
		 	  		voters =  pdfVO.getDeletedVoterDetailsVoList();
		 	  	}
		 	  	
		 	  	if(voters != null && voters.size() > 0);
		 	  	{
		 	  		for (VotersDetailsVO votersDetailsVO : voters) {
		 	  			PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getTehsilName(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getPanchayatname(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersForYoungerVoters().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotesFor18To25().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor26To35().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor36To45().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor46To60().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersForAbove60().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getMaleVotersCountBetween18To25().toString(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			
		 	  			/*table.addCell(votersDetailsVO.getTehsilName());
		 	  			table.addCell(votersDetailsVO.getPanchayatname());
		 	  			table.addCell(votersDetailsVO.getTotalMaleVotersForYoungerVoters().toString());
		 	  			table.addCell(votersDetailsVO.getTotalMaleVotesFor18To25().toString());
		 	  			table.addCell(votersDetailsVO.getTotalMaleVotersFor26To35().toString());
		 	  			table.addCell(votersDetailsVO.getTotalMaleVotersFor36To45().toString());
		 	  			table.addCell(votersDetailsVO.getTotalMaleVotersFor46To60().toString());
		 	  			table.addCell(votersDetailsVO.getTotalMaleVotersForAbove60().toString());
		 	  			table.addCell(votersDetailsVO.getMaleVotersCountBetween18To25().toString());*/
		 	  		}
		 	  	}
		 	  	float[] widths = new float[] {1f, 1f ,1.5f,1f,1f, 1f ,1f,1f,1f};
		 	  	table.setWidths(widths);
		 	  	table.setHeaderRows(2);
		 	  	document.add(table);
			}
		    catch (Exception e)
			{
				LOG.debug("Exception raised in agewiseAddedDeletedVoterDetails() method in VoterModifiationPdfsGenerations Class");
			}
	  	
	  	
	  }
	  
	  /**
	   * Mandal/Muncipality Wise Newly Added/Deleted Voters Info
	   * @param list
	   * @param document
	   * @param constituenyName 
	   * @Date 10-12-2013
	   */
	  public void buildAddedDeletedVotesByMundal(List<VoterModificationVO> list,Document document,String constituenyName)
	  {
		  if(list != null && list.size() > 0)
		  {
			  List<SelectOptionVO> selectOptionVOList = list.get(0).getSelectOptionVOsList();
		    	Long size = 7L + selectOptionVOList.size();
			    try
			    {
			    	LOG.info("Enterd into buildAddedDeletedVotesByMundal() method in VoterModifiationPdfsGenerations Class");
			    	
			    	PdfPTable table = new PdfPTable(size.intValue());
			    	document.add( new Paragraph(" ") );
			    	document.add( new Paragraph(" ") );
			    	
			 	  	PdfPCell cellHeading = new PdfPCell(new Phrase("Mandal/Muncipality Wise Newly Added/Deleted Voters Info",BIGFONT));;
			 	  	cellHeading.setColspan(size.intValue());
			 	  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cellHeading);
			 	  	PdfPCell cell;
			 	  	cell = new PdfPCell(new Phrase("Mandal",BIGFONT));
			 	  	cell.setRowspan(2);
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell); 
			 	  	
			 	  	cell = new PdfPCell(new Phrase("Voters",BIGFONT));
			 	  	cell.setColspan(selectOptionVOList.size());
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);
			 	  	
			 	  	cell = new PdfPCell(new Phrase("Total Voters ",BIGFONT));
			 	  	cell.setColspan(2);
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);
			 	  	
			 	  	cell = new PdfPCell(new Phrase("Male Voters",BIGFONT));
			 	  	cell.setColspan(2);
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);
			 	  	
			 	  	cell = new PdfPCell(new Phrase("Female Voters",BIGFONT));
			 	  	cell.setColspan(2);
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);

			 	  	
			 	  	PdfPCell c1 ;
			 	  	if(list != null && list.size() > 0);
			 	  	{
			 	  			if(selectOptionVOList != null && selectOptionVOList.size() > 0)
			 	  			{
			 	  				Long count = 0l;
			 	  				for (SelectOptionVO selectOptionVO : selectOptionVOList) {
			 	  					count ++;
			 	  					if(count == 1)
			 	  					{
			 	  						//table.addCell(selectOptionVO.getId().toString());
			 	  						c1 = new PdfPCell(new Phrase(selectOptionVO.getName().toString(),BIGFONT));
			 	  						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  						c1.setBackgroundColor(BaseColor.YELLOW);
			 	  			  			table.addCell(c1);
			 	  					}
			 	  					
			 	  					else
			 	  					{
			 	  						c1 = new PdfPCell(new Phrase(selectOptionVO.getName().toString(),BIGFONT));
			 	  						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  						c1.setBackgroundColor(BaseColor.YELLOW);
			 	  			  			table.addCell(c1);
			 	  			  		    //table.addCell(selectOptionVO.getId().toString());
			 	  					}
			 	  					
			 					}
			 	  			}
			 	  		
			 	  		/*c1 = new PdfPCell(new Phrase("Electoral Roll 2014 - Draft",BIGFONT));
				 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 	  	c1.setBackgroundColor(BaseColor.YELLOW);
				 	  	table.addCell(c1);

				 	  	c1 = new PdfPCell(new Phrase("Electoral Roll 2013 - Final",BIGFONT));
				 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 	  	c1.setBackgroundColor(BaseColor.YELLOW);
				 	  	table.addCell(c1);*/
			 	  	}
			 	  	
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
			 	  	table.addCell(c1);
			 	  	
			 	  
			 	  		  	
			 	  	if(list != null && list.size() > 0);
			 	  	{
			 	  		for (VoterModificationVO votersDetailsVO : list) {
			 	  			List<SelectOptionVO> selectOptionVOList1 = votersDetailsVO.getSelectOptionVOsList();
			 	  			PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getName(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			
			 	  			//table.addCell(votersDetailsVO.getName());
			 	  			if(selectOptionVOList1 != null && selectOptionVOList1.size() > 0)
			 	  			{
			 	  				Long count = 0l;
			 	  				for (SelectOptionVO selectOptionVO : selectOptionVOList1) {
			 	  					count ++;
			 	  					if(count == 1)
			 	  					{
			 	  						//table.addCell(selectOptionVO.getId().toString());
			 	  						c2 = new PdfPCell(new Phrase(selectOptionVO.getId().toString(),SMALLFONT));
			 	  						c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			  			table.addCell(c2);
			 	  					}
			 	  					
			 	  					else
			 	  					{
			 	  						c2 = new PdfPCell(new Phrase(selectOptionVO.getId().toString(),SMALLFONT));
			 	  						c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			  			table.addCell(c2);
			 	  			  		    //table.addCell(selectOptionVO.getId().toString());
			 	  					}
			 	  					
			 					}
			 	  			}
			 	  			
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getAddedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getDeletedCount().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getMaleVotersAdded().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getMaleVotersDeleted().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getFemaleVotersAdded().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getFemaleVotersDeleted().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			/*table.addCell(votersDetailsVO.getAddedCount().toString());
			 	  			table.addCell(votersDetailsVO.getDeletedCount().toString());
			 	  			table.addCell(votersDetailsVO.getMaleVotersAdded().toString());
			 	  			table.addCell(votersDetailsVO.getMaleVotersDeleted().toString());
			 	  			table.addCell(votersDetailsVO.getFemaleVotersAdded().toString());
			 	  			table.addCell(votersDetailsVO.getFemaleVotersDeleted().toString());*/
			 	  		}
			 	  	}
			 	  	float[] widths = null;
			 	  	if(size == 8)
			 	  	{
			 	  		 widths = new float[] { 1f, 1.5f ,1f,1.2f, 1f ,1.2f,1f,1.2f};
			 	  	}else
			 	  	{
			 	  		 widths = new float[] { 1f, 1.5f ,1.5f,1f,1.2f, 1f ,1.2f,1f,1.2f};
			 	  	}
			 	  	
			 	  	table.setWidths(widths);
			 	  	document.add(table);	 	  	
			 	  	
				}
			    catch (Exception e)
				{
					LOG.debug("Exception raised in buildAddedDeletedVotesByMundal() method in VoterModifiationPdfsGenerations Class",e);
				}
		  }
		    
	  	
	  }
	  
	  /**
	   * Gender Wise Added and Deleted voters list
	   * @param list
	   * @param document
	   * @param constituencyName
	   * @Date 10-12-2013
	   */
	  public void buildAddedDeletedVotesByGender(List<VoterModificationGenderInfoVO> list,Document document,String constituenyName)
	  {
		    try
		    {
		    	if(list != null && list.size() > 0)
		    	{
		    		LOG.info("Enterd into buildAddedDeletedVotesByGender() method in VoterModifiationPdfsGenerations Class");
			    	PdfPTable table = new PdfPTable(8);
			    	document.add( new Paragraph(" ") );
			    	document.add( new Paragraph(" ") );
			 	  			 		
			 	  	PdfPCell cell;
			 	  			 	  	
			 	  	cell = new PdfPCell(new Phrase("Added",BIGFONT));
			 	  	cell.setColspan(3);
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);
			 	  	
			 	  	cell = new PdfPCell(new Phrase("Deleted",BIGFONT));
			 	  	cell.setColspan(3);
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);

			 	  	
			 	    

			 	  	PdfPCell c1 = new PdfPCell(new Phrase("Publication Name",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);

			 	  	c1 = new PdfPCell(new Phrase("Previous Publication Name",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Total",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	c1 = new PdfPCell(new Phrase("Male",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Female",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Total",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Male",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
			 	  	table.addCell(c1);
			 	  	
			 	  	c1 = new PdfPCell(new Phrase("Female",BIGFONT));
			 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	c1.setBackgroundColor(BaseColor.YELLOW);	    
			 	  	table.addCell(c1);
			 	  	
			 	  
			 	  		  	
			 	  	if(list != null && list.size() > 0);
			 	  	{
			 	  		for (VoterModificationGenderInfoVO votersDetailsVO : list) {
			 	  			PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getPublicationName(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getPreviousPublicationName().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getAddedTotal().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getAddedMale().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getAddedFemale().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getDeletedTotal().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getDeletedMale().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getDeletedFemale().toString(),SMALLFONT));
			 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  			table.addCell(c2);
			 	  			
			 	  			/*table.addCell(votersDetailsVO.getPublicationName());
			 	  			table.addCell(votersDetailsVO.getPreviousPublicationName().toString());
			 	  			table.addCell(votersDetailsVO.getAddedTotal().toString());
			 	  			table.addCell(votersDetailsVO.getAddedMale().toString());
			 	  			table.addCell(votersDetailsVO.getAddedFemale().toString());
			 	  			table.addCell(votersDetailsVO.getDeletedTotal().toString());
			 	  			table.addCell(votersDetailsVO.getDeletedMale().toString());
			 	  			table.addCell(votersDetailsVO.getDeletedFemale().toString());*/
			 	  		}
			 	  	}
			 	  	//table.setWidthPercentage(100);
			 	  	document.add(table);
		    	}
		    	
		 	  	
			}
		    catch (Exception e)
			{
				LOG.debug("Exception raised in buildAddedDeletedVotesByGender() method in VoterModifiationPdfsGenerations Class");
			}
	  	
	  	
	  }
	  public static String checkForNull(String value)
	  {
		  return value == null ? "" : value;
		  
	  }
	  /**
	   * Newly Added/Deleted Voters Info
	   * @param voterModificationGenderInfoVO
	   * @param document
	   * @param constituencyName
	   * @Date 10-12-2013
	   */
	  public void buildGenderWiseVoterModifivationReport(VoterModificationGenderInfoVO voterModificationGenderInfoVO,Document document,String constituenyName)
	  {
		    try
		    {
		    	LOG.info("Enterd into buildGenderWiseVoterModifivationReport() method in VoterModifiationPdfsGenerations Class");
		    	PdfPTable table = new PdfPTable(4);
		    	document.add( new Paragraph(" ") );
		    	document.add( new Paragraph(" ") );
		 	  	
		 	  	PdfPCell cellHeading = new PdfPCell(new Phrase("Newly Added/Deleted Voters Info ",BIGFONT));;
		 	  	cellHeading.setColspan(4);
		 	  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cellHeading);
		 	  	PdfPCell cell;
		 	  	
		 	  	
		 	  	
		 	  	cell = new PdfPCell(new Phrase("Status",BIGFONT));
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);
		 	  	
		 	  	
		 	  	
		 	  	cell = new PdfPCell(new Phrase("Total",BIGFONT));
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);

		 	  	
		 	    

		 	  	cell = new PdfPCell(new Phrase("Male",BIGFONT));
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);

		 	  	cell = new PdfPCell(new Phrase("Female",BIGFONT));
		 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	cell.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(cell);
		 	  	
		 	  	PdfPCell c2 = new PdfPCell(new Phrase("Added",SMALLFONT));
		 	  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  	c2 = new PdfPCell(new Phrase(voterModificationGenderInfoVO.getAddedTotal().toString(),SMALLFONT));
		 	  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  	c2 = new PdfPCell(new Phrase(voterModificationGenderInfoVO.getAddedMale().toString(),SMALLFONT));
		 	  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  	c2 = new PdfPCell(new Phrase(voterModificationGenderInfoVO.getAddedFemale().toString(),SMALLFONT));
		 		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  	c2 = new PdfPCell(new Phrase("Deleted",SMALLFONT));
		 	  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  	c2 = new PdfPCell(new Phrase(voterModificationGenderInfoVO.getDeletedTotal().toString(),SMALLFONT));
		 		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  	c2 = new PdfPCell(new Phrase(voterModificationGenderInfoVO.getDeletedMale().toString(),SMALLFONT));
		 	  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  	c2 = new PdfPCell(new Phrase(voterModificationGenderInfoVO.getDeletedFemale().toString(),SMALLFONT));
		 	  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 		table.addCell(c2);
		 	  			/*table.addCell("Added");
		 	  			table.addCell(voterModificationGenderInfoVO.getAddedTotal().toString());
		 	  			table.addCell(voterModificationGenderInfoVO.getAddedMale().toString());
		 	  			table.addCell(voterModificationGenderInfoVO.getAddedFemale().toString());
		 	  			
		 	  			table.addCell("Deleted");
		 	  			table.addCell(voterModificationGenderInfoVO.getDeletedTotal().toString());
		 	  			table.addCell(voterModificationGenderInfoVO.getDeletedMale().toString());
		 	  			table.addCell(voterModificationGenderInfoVO.getDeletedFemale().toString());*/
		 	  	
		 	  	float[] widths = new float[] { 1f, 1f ,1f,1f};
		 	  	table.setWidths(widths);
		 	  	document.add(table);	 	
		 	  
			}
		    catch (Exception e) 
			{
				LOG.debug("Exception raised in buildGenderWiseVoterModifivationReport() method in VoterModifiationPdfsGenerations Class");
			}
	  	
	  	
	  }
	  
	  /**
	   * Voters Basic Info In Constituency
	   * @param voterAgeRangeVOList
	   * @param document
	   * @param constituenyName
	   * @Date 10-12-2013
	   */
	  public void buildVoterModifivationReport(List<VoterAgeRangeVO> voterAgeRangeVOList,Document document,String constituenyName)
	  {
		    try
		    {
		    	if(voterAgeRangeVOList != null && voterAgeRangeVOList.size() > 0)
		    	{
		    		LOG.info("Enterd into buildVoterModifivationReport() method in VoterModifiationPdfsGenerations Class");
			    	PdfPTable table = new PdfPTable(4);
			 	  	document.add( new Paragraph(" ") );
			 	  	document.add( new Paragraph(" ") );
			 	  	
			 	  	PdfPCell cellHeading = new PdfPCell(new Phrase("Voters Basic Info In "+constituenyName+" Constituency ",BIGFONT));;
			 	  	cellHeading.setColspan(4);
			 	  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cellHeading);
			 	  	
			 	  	PdfPCell cell;
			 	  	
			 	  	
			 	  	
			 	  	cell = new PdfPCell(new Phrase("Publication Date",BIGFONT));
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);
			 	  	
			 	  	
			 	  	
			 	  	cell = new PdfPCell(new Phrase("Total Voters",BIGFONT));
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);

			 	  	
			 	    

			 	  	cell = new PdfPCell(new Phrase("Male Voters",BIGFONT));
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);

			 	  	cell = new PdfPCell(new Phrase("Female Voters",BIGFONT));
			 	  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	  	cell.setBackgroundColor(BaseColor.YELLOW);
			 	  	table.addCell(cell);
			 	  	for (VoterAgeRangeVO voterAgeRangeVO : voterAgeRangeVOList) {
			 	  		
			 	  		PdfPCell c2 = new PdfPCell(new Phrase(voterAgeRangeVO.getPublicationDate().toString(),SMALLFONT));
			 	  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			table.addCell(c2);
			 		  	c2 = new PdfPCell(new Phrase(voterAgeRangeVO.getTotalVoters().toString(),SMALLFONT));
			 		  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			table.addCell(c2);
			 		  	c2 = new PdfPCell(new Phrase(voterAgeRangeVO.getMaleVoters().toString(),SMALLFONT));
			 		  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			table.addCell(c2);
			 		  	c2 = new PdfPCell(new Phrase(voterAgeRangeVO.getFemaleVoters().toString(),SMALLFONT));
			 		  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			table.addCell(c2);
			 	  		/*table.addCell(voterAgeRangeVO.getPublicationDate().toString());
			 			table.addCell(voterAgeRangeVO.getTotalVoters().toString());
			 			table.addCell(voterAgeRangeVO.getMaleVoters().toString());
			 			table.addCell(voterAgeRangeVO.getFemaleVoters().toString());*/
			 			
			 		}
			 	  	
			 	  	float[] widths = new float[] { 1.2f, 1f ,1f,1f};
			 	  	table.setWidths(widths);
			 	  	document.add(table);
		    	}
		    	
		 	  	
			}
		    catch (Exception e) {
				LOG.debug("Exception raised in buildVoterModifivationReport() method in VoterModifiationPdfsGenerations Class");
			}
	  }
	  
	  /**
	   * Age Wise Newly Added/Deleted Voters Info
	   * @param voterModificationAgeRangeVOList
	   * @param document
	   * @param constituenyName
	   * @Date 10-12-2013
	   */
	  public void buildVoterModifivationReportByAgeRange(List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList,Document document,String constituenyName)
	  {
		  try 
		  {
			  if(voterModificationAgeRangeVOList != null && voterModificationAgeRangeVOList.size() > 0)
			  {
				  LOG.info("Enterd into buildVoterModifivationReportByAgeRange() method in VoterModifiationPdfsGenerations Class");
				    PdfPTable table = new PdfPTable(12);
				    document.add( new Paragraph(" ") );
				    document.add( new Paragraph(" ") );	  	
				  	PdfPCell cellHeading = new PdfPCell(new Phrase("Age Wise Newly Added/Deleted Voters Info ",BIGFONT));;
				  	cellHeading.setColspan(12);
				  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cellHeading);
				  	
				  	PdfPCell cell;
				  	cell = new PdfPCell(new Phrase("Young Voters",BIGFONT));
				  	cell.setColspan(2);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("23-25",BIGFONT));
				  	cell.setColspan(2);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("26-35",BIGFONT));
				  	cell.setColspan(2);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("36-45",BIGFONT));
				  	cell.setColspan(2);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("46-60",BIGFONT));
				  	cell.setColspan(2);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("60-Above",BIGFONT));
				  	cell.setColspan(2);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	PdfPCell c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  
				  	for (VoterModificationAgeRangeVO voterModificationAgeRangeVO : voterModificationAgeRangeVOList) {
				  		
				  		PdfPCell c2 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getAddedCount().toString(),SMALLFONT));
			  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			  			table.addCell(c2);
					  	c2 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getDeletedCount().toString(),SMALLFONT));
					  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(c2);
				  		/*Long addedCount = 0l;
				  		Long deletedCount = 0l;
				  		if(voterModificationAgeRangeVO.getRange().equalsIgnoreCase("Young Voters"))
				  		{
				  			addedCount   = voterModificationAgeRangeVO.getAddedCount();
				  			deletedCount = voterModificationAgeRangeVO.getDeletedCount();
				  		}
				  		if(voterModificationAgeRangeVO.getRange().equalsIgnoreCase("18-25"))
				  		{
				  			Long addedCount1 = voterModificationAgeRangeVO.getAddedCount();
				  			Long deletedCount1 = voterModificationAgeRangeVO.getDeletedCount();
				  			PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf((addedCount1-addedCount)),SMALLFONT));
				  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  			table.addCell(c2);
						  	c2 = new PdfPCell(new Phrase(String.valueOf((deletedCount1-deletedCount)),SMALLFONT));
						  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
							table.addCell(c2);
				  			table.addCell(String.valueOf((addedCount1-addedCount)));
				  			table.addCell(String.valueOf((deletedCount1-deletedCount)));
				  		}
				  		else
				  		{
				  			PdfPCell c2 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getAddedCount().toString(),SMALLFONT));
				  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  			table.addCell(c2);
						  	c2 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getDeletedCount().toString(),SMALLFONT));
						  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
							table.addCell(c2);
				  			table.addCell(voterModificationAgeRangeVO.getAddedCount().toString());
				  			table.addCell(voterModificationAgeRangeVO.getDeletedCount().toString());
				  		}*/
				  		
						
					}
				  	float[] widths = new float[] { 1.1f, 1.2f ,1.1f,1.2f,1.1f, 1.2f ,1.1f,1.2f,1.1f, 1.2f ,1.1f,1.2f};
				  	table.setWidths(widths);
				  	document.add(table);
			  }
			    

		  }
		  catch (Exception e)
		  {
			 LOG.debug("Exception raised in buildVoterModifivationReportByAgeRange() method in VoterModifiationPdfsGenerations Class",e);
		  }
	  	
	  	
	  }
	  
	  
	  /**
	   * Age Wise Newly Added/Deleted Voters Info
	   * @param voterModificationAgeRangeVOList
	   * @param document
	   * @param constituenyName
	   * @Date 10-12-2013
	   */
	  public void buildVoterModifivationReportByAgeRangeByGender(List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList,Document document,String constituenyName)
	  {
		  try 
		  {
			  if(voterModificationAgeRangeVOList != null && voterModificationAgeRangeVOList.size() > 0)
			  {
				  LOG.info("Enterd into buildVoterModifivationReportByAgeRange() method in VoterModifiationPdfsGenerations Class");
				    PdfPTable table = new PdfPTable(24);
				    document.add( new Paragraph(" ") );
				    document.add( new Paragraph(" ") );	  	
				  	PdfPCell cellHeading = new PdfPCell(new Phrase("Age Wise Newly Added/Deleted Voters Info ",BIGFONT));;
				  	cellHeading.setColspan(24);
				  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cellHeading);
				  	
				  	PdfPCell cell;
				  	cell = new PdfPCell(new Phrase("Young Voters",BIGFONT));
				  	cell.setColspan(4);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("23-25",BIGFONT));
				  	cell.setColspan(4);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("26-35",BIGFONT));
				  	cell.setColspan(4);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("36-45",BIGFONT));
				  	cell.setColspan(4);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("46-60",BIGFONT));
				  	cell.setColspan(4);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	cell = new PdfPCell(new Phrase("60-Above",BIGFONT));
				  	cell.setColspan(4);
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				  	
				  	PdfPCell c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	c1 = new PdfPCell(new Phrase("Added",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	c1 = new PdfPCell(new Phrase("Deleted",BIGFONT));
				  	c1.setColspan(2);
				  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c1.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c1);
				  	
				  	 
				  	PdfPCell c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("M",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(c2);
				  	c2 = new PdfPCell(new Phrase("F",BIGFONT));
				  	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	c2.setBackgroundColor(BaseColor.YELLOW); 
				  	table.addCell(c2);
				  	
				  	for (VoterModificationAgeRangeVO voterModificationAgeRangeVO : voterModificationAgeRangeVOList) {
				  		
				  		PdfPCell c3 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getAddedMale().toString(),SMALLFONT));
				  		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			  			table.addCell(c3);
			  			c3 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getAddedFemale().toString(),SMALLFONT));
			  			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(c3);
						c3 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getDeletedMale().toString(),SMALLFONT));
						c3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(c3);
						c3 = new PdfPCell(new Phrase(voterModificationAgeRangeVO.getDeletedFemale().toString(),SMALLFONT));
						c3.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(c3);
				  		
				  		
						
					}
				  	float[] widths = new float[] { 1.1f, 1.2f ,1.1f,1.2f,1.1f, 1.2f ,1.1f,1.2f,1.1f, 1.2f ,1.1f,1.2f, 1.1f, 1.2f ,1.1f,1.2f,1.1f, 1.2f ,1.1f,1.2f,1.1f, 1.2f ,1.1f,1.2f};
				  	table.setWidths(widths);
				  	document.add(table);
			  }
			    

		  }
		  catch (Exception e)
		  {
			 LOG.debug("Exception raised in buildVoterModifivationReportByAgeRange() method in VoterModifiationPdfsGenerations Class",e);
		  }
	  	
	  	
	  }
	  /**
	   * Percentage Wise Voters Additions In Panchayats
	   * @param type
	   * @param document
	   * @param pdfVO
	   * @param constituencyName
	   * @Date 10-12-2013
	   */
	  public void buildAddedOrDeletedVotersbyPrecReport(String type,Document document,PdfVO pdfVO, String constituenyName)
	  {
		 
		  try
		  {
		        LOG.info("Enterd into buildAddedOrDeletedVotersbyPrecReport() method in VoterModifiationPdfsGenerations Class");
		        PdfPTable table = new PdfPTable(8);
		        document.add( new Paragraph(" ") );
		        document.add( new Paragraph(" ") );
			  	if(type.equalsIgnoreCase("add"))
			  	{
			  		PdfPCell cellHeading = new PdfPCell(new Phrase("Percentage Wise Voters Additions In Panchayats",BIGFONT));;
				  	cellHeading.setColspan(8);
				  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cellHeading);
			  	}
			  	else
			  	{
			  		PdfPCell cellHeading = new PdfPCell(new Phrase("Percentage Wise Voters Deletions In Panchayats",BIGFONT));;
				  	cellHeading.setColspan(8);
				  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cellHeading);
			  	}
			  	
			  	
			  	PdfPCell cell;
			  	
			  	
			  	
			  	cell = new PdfPCell(new Phrase("Mandal",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	
			  	
			  	
			  	cell = new PdfPCell(new Phrase("No Changes",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);

			  	
			    

			  	cell = new PdfPCell(new Phrase("0-3 %",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);

			  	cell = new PdfPCell(new Phrase("3-5 %",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	
			  	cell = new PdfPCell(new Phrase("5-10 %",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	
			  	cell = new PdfPCell(new Phrase("10-20 %",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	
			  	cell = new PdfPCell(new Phrase("20-40 %",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	
			  	cell = new PdfPCell(new Phrase("40-Above %",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	List<VotersDetailsVO> votersDetailsVOList = null;
			  	if(type.equalsIgnoreCase("add"))
			  	{
			  		votersDetailsVOList = pdfVO.getAddedDetaildByPerc();
			  	}
			  	else
			  	{
			  		votersDetailsVOList = pdfVO.getDeletedDetaildByPerc();
			  	}
			  	for (VotersDetailsVO votersDetailsVO : votersDetailsVOList) {
			  		
			  		PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getName().toString(),SMALLFONT));
			  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotesFor18To25().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor26To35().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalFemaleVotersForAbove60().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalVotersForYoungerVoters().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor36To45().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersFor46To60().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalMaleVotersForAbove60().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
			  		/*table.addCell(votersDetailsVO.getName().toString());
					table.addCell(votersDetailsVO.getTotalMaleVotesFor18To25().toString());
					table.addCell(votersDetailsVO.getTotalMaleVotersFor26To35().toString());
					table.addCell(votersDetailsVO.getTotalFemaleVotersForAbove60().toString());
					table.addCell(votersDetailsVO.getTotalVotersForYoungerVoters().toString());
					table.addCell(votersDetailsVO.getTotalMaleVotersFor36To45().toString());
					table.addCell(votersDetailsVO.getTotalMaleVotersFor46To60().toString());
					table.addCell(votersDetailsVO.getTotalMaleVotersForAbove60().toString());*/
				}
			  	//table.setWidthPercentage(100);		
			  	float[] widths = new float[] { 1f, 1f ,1f,1f,1f, 1f ,1f,1f};
			  	table.setWidths(widths);
			  	document.add(table);
			  	
		  }
		  catch (Exception e)
		  {
			LOG.debug("Exception raised in buildAddedOrDeletedVotersbyPrecReport() method in VoterModifiationPdfsGenerations Class",e);
		  }
	  	
	  }
	  
	  /**
	   * Percentage Wise Voters Additions In Panchayats
	   * @param type
	   * @param document
	   * @param pdfVO
	   * @param constituencyName
	   * @Date 10-12-2013
	   */
	  public void buildAddedOrDeletedVotersbyBoothWiseReport(String type,Document document,PdfVO pdfVO, String constituenyName,String constituencyType)
	  {
		 
		  try
		  {
		        LOG.info("Enterd into buildAddedOrDeletedVotersbyBoothWiseReport() method in VoterModifiationPdfsGenerations Class");
		        PdfPTable table = new PdfPTable(2);
		        document.add( new Paragraph(" ") );
		        document.add( new Paragraph(" ") );
		        String name = "";
		         
			  	if(type.equalsIgnoreCase("add"))
			  	{
			  		
			  		PdfPCell cellHeading = null;
			  		name = pdfVO.getBoothWiseAddedList().get(0).getCastName();
			  		if(constituencyType.equalsIgnoreCase("URBAN"))
			  		{
			  			 cellHeading = new PdfPCell(new Phrase("Booth Wise Added Voters in "+name.toUpperCase()+"",BIGFONT));;
			  		}
			  		else
			  		{
			  			cellHeading = new PdfPCell(new Phrase("Booth Wise Added Voters in  "+name.toUpperCase()+" ",BIGFONT));;
			  		}
			  		cellHeading.setColspan(2);
				  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cellHeading);
			  	}
			  	else
			  	{
			  		PdfPCell cellHeading = null;
			  		name = pdfVO.getBoothWiseDeletedList().get(0).getCastName();
			  		if(constituencyType.equalsIgnoreCase("URBAN"))
			  		{
			  			 cellHeading = new PdfPCell(new Phrase("Booth Wise Deleted Voters in "+name.toUpperCase()+"",BIGFONT));;
			  		}
			  		else
			  		{
			  			cellHeading = new PdfPCell(new Phrase("Booth Wise Deleted Voters in  "+name.toUpperCase()+" ",BIGFONT));;
			  		}
			  		
				  	cellHeading.setColspan(2);
				  	cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cellHeading.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cellHeading);
			  	}
			  	
			  	
			  	PdfPCell cell;
			  	
			  	
			  	
			  	cell = new PdfPCell(new Phrase("Booth",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	
			  	
			  	
			  	cell = new PdfPCell(new Phrase("Voters",BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);

			  	List<VotersDetailsVO> votersDetailsVOList = null;
			  	if(type.equalsIgnoreCase("add"))
			  	{
			  		votersDetailsVOList = pdfVO.getBoothWiseAddedList();
			  	}
			  	else
			  	{
			  		votersDetailsVOList = pdfVO.getBoothWiseDeletedList();
			  	}
			  	for (VotersDetailsVO votersDetailsVO : votersDetailsVOList) {
			  		
			  		PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getName().toString(),SMALLFONT));
			  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c2);
		  			c2 = new PdfPCell(new Phrase(votersDetailsVO.getTotalVoters().toString(),SMALLFONT));
		  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		  			table.addCell(c2);
				}
			  	//table.setWidthPercentage(100);		
			  	float[] widths = new float[] { 1f, 1f };
			  	table.setWidths(widths);
			  	table.setHeaderRows(2);
			  	document.add(table);
			  	
		  }
		  catch (Exception e)
		  {
			LOG.debug("Exception raised in buildAddedOrDeletedVotersbyBoothWiseReport() method in VoterModifiationPdfsGenerations Class",e);
		  }
	  	
	  }
	  
	  
  
	  public void buildAddressTable(Document document,List<Object[]> list)
	  {
		 
		  try
		  {
			  if(list != null && list.size() > 0 )
			  {
					StringBuilder sb  = new StringBuilder();
					PdfPTable table = new PdfPTable(3);
					/*document.add( new Paragraph(" ") );
					document.add( new Paragraph(" ") );*/
					for (Object[] parms : list) {
						
						sb.append("<p>To,<p>");
						sb.append("<p style='margin-left: 10px;'>"+replaceSpecialChars(parms[7].toString())+"<p>");
						String name = "";
						if(parms[12].toString().equalsIgnoreCase("Father") || parms[12].toString().equalsIgnoreCase("Mother"))
						{
							if(parms[8].toString().trim().equalsIgnoreCase("M"))
							{
								name = "S/O , " + replaceSpecialChars(parms[11].toString());
							}
							else
							{
								name = "D/O , " + replaceSpecialChars(parms[11].toString());
							}
						}
						
						else if(parms[12].toString().equalsIgnoreCase("Husband"))
						{
							name = "W/O , "  + replaceSpecialChars(parms[11].toString());
						}
						else
						{
							name = "C/O , "  + replaceSpecialChars(parms[11].toString());
						}
						sb.append("<p style='margin-left: 10px;'>"+name+"<p>");
						sb.append("<p style='margin-left: 10px;'>H.NO : "+parms[10].toString()+"</p>");
						if(parms[2] != null)
						{
							sb.append("<p style='margin-left: 10px;'>Village : "+parms[2].toString()+"<p>");
						}
						sb.append("<p style='margin-left: 10px;'>Panchayat : "+parms[1].toString()+"<p>");
						sb.append("<p style='margin-left: 10px;'>Mandal : "+parms[0].toString()+"<p>");
						sb.append("<p style='margin-left: 10px;'>District : "+parms[14].toString()+"<p>");
						sb.append("<p style='margin-left: 10px;'>Andhra Pradesh<p>");
						
						PdfPCell c2 = new PdfPCell(new Phrase(sb.toString(),SMALLFONT));
				  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(c2);
					}
					document.add(table);
				}
		  }
		  catch (Exception e)
		  {
			LOG.debug("Exception raised in buildAddressTable() method in VoterModifiationPdfsGenerations Class",e);
		  }
	  	
	  }
	  
	  
	  public String replaceSpecialChars(String str)
	  {
	  		try{
	  			String newStr = "";
	  			
	  			char[] charArray = str.toCharArray();
	  			for(Character C : charArray)
				{
					if(Character.isLetter(C) || C.toString().equals(" "))
						newStr = newStr+C.toString();
				}
	  			return newStr.trim();
	  		}catch(Exception e)
	  		{
	  			return str;
	  		}
	  }
	  
	  
	  /**
	   * This service is used for building the pdf for important familes in a constituency
	   * @param document
	   * @param List<VoterHouseInfoVO> list
	   * @param constituencyName
	   * @Date 13-03-2014
	   */
	  public void generatePdfsForImpFamiles(Document document,List<VoterHouseInfoVO> list ,String constituencyName)
	  {
		  try {
			  
			  LOG.info("Enterd into generatePdfsForImpFamiles() method in VoterModifiationPdfsGenerations Class");
			  if(list != null && list.size() > 0)
			  {
				  
				  Map<Long,List<VoterHouseInfoVO>> map = new HashMap<Long, List<VoterHouseInfoVO>>();
				  for (VoterHouseInfoVO voterHouseInfoVO : list) {
					  List<VoterHouseInfoVO> values =  map.get(Long.valueOf(voterHouseInfoVO.getPartNo()));
					  if(values == null)
					  {
						  values = new ArrayList<VoterHouseInfoVO>();
						 
					  }
					  values.add(voterHouseInfoVO);
					  map.put(Long.valueOf(voterHouseInfoVO.getPartNo()), values);
					  
				  }
				  Set<Long> boothIds = map.keySet();
				  if(boothIds != null && boothIds.size() > 0)
				  {
					  int count1 = 0;
					  for (Long boothId : boothIds)
					  {
						
						  PdfPTable table = new PdfPTable(15);
						 // document.add( new Paragraph(" ") );
					      //document.add( new Paragraph(" ") );
						  Paragraph preface = new Paragraph();
					      preface.setAlignment(Element.ALIGN_CENTER);
				    	  preface.add(new Paragraph("Booth -" +boothId+ "  Importent Familes Report" , catFont));
				    	  preface.add(" ");
				    	  preface.add(" ");
				    	  document.add(preface);
					      
					      PdfPCell cellHeading;
					      cellHeading = new PdfPCell(new Phrase("",BIGFONT1));
					      cellHeading.setColspan(7);
						  cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
						  cellHeading.setBackgroundColor(BaseColor.YELLOW);
						  table.addCell(cellHeading);
						  
						  cellHeading = new PdfPCell(new Phrase("Elder Person",BIGFONT1));
					      cellHeading.setColspan(4);
						  cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
						  cellHeading.setBackgroundColor(BaseColor.YELLOW);
						  table.addCell(cellHeading);
						  
						  cellHeading = new PdfPCell(new Phrase("Younger Person",BIGFONT1));
					      cellHeading.setColspan(4);
						  cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
						  cellHeading.setBackgroundColor(BaseColor.YELLOW);
						  table.addCell(cellHeading);
						  
					      PdfPCell cell;

					  	  /*cell = new PdfPCell(new Phrase("SNO",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);*/
					  	  cell = new PdfPCell(new Phrase("Mandal/Muncipality",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Panchayat",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Hamlet",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Booth",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Caste",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("House No",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Count",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Voter Id",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Name",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Gender",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Age",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);

					  	  cell = new PdfPCell(new Phrase("Voter Id",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Name",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Gender",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
					  	  cell = new PdfPCell(new Phrase("Age",BIGFONT1));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  cell.setBackgroundColor(BaseColor.YELLOW);
					  	  table.addCell(cell);
						  //Long count = 1l;
						    
						 
						  List<VoterHouseInfoVO>  list1 = map.get(boothId);
						  Collections.sort(list1,sort);
						  for (VoterHouseInfoVO voterHouseInfoVO : list1)
						  {
							  	
							    /*PdfPCell c2 = new PdfPCell(new Phrase(count.toString(),SMALLFONT1));
							    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
							    table.addCell(c2);*/
							  
							    PdfPCell c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getTehsilName() != null ? voterHouseInfoVO.getTehsilName().toString() : "" ,SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getPanchayatName() != null ? voterHouseInfoVO.getPanchayatName().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getHamletName() != null ? voterHouseInfoVO.getHamletName().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getPartNo() != null ? voterHouseInfoVO.getPartNo().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getElderCaste() != null ? voterHouseInfoVO.getElderCaste().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getHouseNo() != null ? voterHouseInfoVO.getHouseNo().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getCount() != null ? voterHouseInfoVO.getCount().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getVoterIdCardNo() != null ? voterHouseInfoVO.getVoterIdCardNo().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getElder() != null ? voterHouseInfoVO.getElder().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getElderGender() != null ? voterHouseInfoVO.getElderGender().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getElderAge() != null ? voterHouseInfoVO.getElderAge().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getVoterGroup() != null ? voterHouseInfoVO.getVoterGroup().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getYounger() != null ? voterHouseInfoVO.getYounger().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getYoungerGender() != null ? voterHouseInfoVO.getYoungerGender().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			
					  			c2 = new PdfPCell(new Phrase(voterHouseInfoVO.getYoungerAge() != null ? voterHouseInfoVO.getYoungerAge().toString() : "",SMALLFONT1));
					  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					  			table.addCell(c2);
					  			//count++;
						  }
						    /*System.out.println(boothId);
							System.out.println(count1);
							if(count1 > 0)
							{
								System.out.println(count);
								System.out.println("Yes");
								
								
							}*/
							
							count1 ++;
							float[] widths = new float[] { 1f ,1f,1f,1f, 1f ,1f,0.5f,1f, 1f ,1f,0.5f,1f, 1f ,1f,0.5f};
							 table.setWidths(widths);
							 table.setHeaderRows(2);
							 document.add(table);
							 document.newPage();
					  }
					  
				  }
				
				  
			  }
		} catch (Exception e) {
			LOG.debug("Exception raised in generatePdfsForImpFamiles() method in VoterModifiationPdfsGenerations Class",e);
		}
	  }
	  
	 public static Comparator<VoterHouseInfoVO> sort = new Comparator<VoterHouseInfoVO>()
	 {
			  
		  public int compare(VoterHouseInfoVO loc1, VoterHouseInfoVO loc2)
			{
			   return (loc2.getCount().compareTo(loc1.getCount()));
			}
	  };
	  
	  
	 /* public void panchayatWiseTargetVotesTable(Document document,List<PanchayatVO> totalCastesList)
	  {
		  try {
			  	
			  LOG.info("Enterd into panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class");
			  PdfPTable table = new PdfPTable(6);
			  Paragraph preface = new Paragraph();
			  preface.setAlignment(Element.PTABLE);
			  preface.add( new Paragraph("               Panchayath Wise :"));
			  preface.add( new Paragraph(" ") );
			  document.add(preface); 
			  DecimalFormat df = new DecimalFormat("##.##");
			  PdfPCell cell ;
			  	  
		        cell = new PdfPCell(new Phrase("Panchayath",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase("2014 Voters",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("Total Targeted",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("Targeted Percentage",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("2009 TDP Voting Percentage",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("Opportunity %",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  int count = 0;
			  	  for (PanchayatVO panchayatVO : totalCastesList)
			  	  {
			  		  if(count == 14)
			  		  {
			  			  break;
			  		  }
			  		  cell = new PdfPCell(new Phrase(panchayatVO.getPanchayatName(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getTotalVoters()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getOtherVotes()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(df.format(panchayatVO.getTargetPerc()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(df.format(panchayatVO.getPartyPerc()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(df.format(panchayatVO.getOpportunity()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  count ++;
				}
			  	table.setHeaderRows(2);
				 document.add(table);
				 document.newPage();
		} catch (Exception e) {
			LOG.debug("Exception raised in panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class",e);
		}
	  }
	  
	  public void panchayatWiseTargetYoungVotesTable(Document document,List<PanchayatVO> totalCastesList,String type)
	  {
		  try {
			  	
			  LOG.info("Enterd into panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class");
			    PdfPTable table = new PdfPTable(5);
			    DecimalFormat df = new DecimalFormat("##.##"); 
			    Paragraph preface = new Paragraph();
			    preface.setAlignment(Element.PTABLE);
			    preface.add( new Paragraph("               Panchayath Wise :"));
			    preface.add( new Paragraph(" ") );
			    document.add(preface);
			    
		        PdfPCell cell ;
			  	  
		        cell = new PdfPCell(new Phrase("Panchayath",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase("Total Voters",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase(type,style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("Targeted",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("Targeted %",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	 int count = 0;
			  	  for (PanchayatVO panchayatVO : totalCastesList)
			  	  {
			  		  if(count == 14)
			  		  {
			  			  break;
			  		  }
			  		  cell = new PdfPCell(new Phrase(panchayatVO.getPanchayatName(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getTotalPanchayatVoters()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getTotalVoters()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(Long.valueOf(panchayatVO.getOthrExpctdVotes()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  cell = new PdfPCell(new Phrase(df.format(panchayatVO.getTargetPerc()).toString(),style2));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  table.addCell(cell);
				  	  
				  	  count ++;
				  	  
				  	
				}
			  	table.setHeaderRows(2);
				document.add(table);
				document.newPage();
		} catch (Exception e) {
			LOG.debug("Exception raised in panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class",e);
		}
	  }
	  
	  public void prpEffectTableTable(Document document,List<PartyEffectVO> list)
	  {
		  try {
			  	
			  LOG.info("Enterd into panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class");
			  
			  DecimalFormat df = new DecimalFormat("##.##"); 
			  
			  PdfPTable table = new PdfPTable(4);
			    
			    Paragraph preface = new Paragraph();
			    preface.setAlignment(Element.PTABLE);
			    preface.add( new Paragraph("               PRP Votes to Regain"));
			    preface.add( new Paragraph(" ") );
			    document.add(preface);
			    
			    
		        
		        PdfPCell cell ;
			  	  
		        cell = new PdfPCell(new Phrase("Panchayath",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase("PRP Gain %",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  
			  	  
			  	  cell = new PdfPCell(new Phrase("PRP Effect on TDP Party",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("Major Castes %",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	 
			  	  for (PartyEffectVO partyEffectVO : list)
			  	  {
			  		  if( partyEffectVO.getDifference() > 0.0)
			  		  {
			  			  cell = new PdfPCell(new Phrase(partyEffectVO.getName(),style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(df.format(partyEffectVO.getPrpCurrentPerc()).toString(),style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(df.format(partyEffectVO.getDifference()).toString(),style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  table.addCell(cell);
					  	  
					  	  cell = new PdfPCell(new Phrase(partyEffectVO.getCastes(),style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  table.addCell(cell);
					  	  
			  		  }

				}
			  	  table.setHeaderRows(2);
				  document.add(table);
				  document.newPage();
		} catch (Exception e) {
			LOG.debug("Exception raised in panchayatWiseTargetVotesTable() method in VoterModifiationPdfsGenerations Class",e);
		}
	  }
	  public void generatePdfForMatrixReport(Document document,List<PartyPositionVO>  previousTrends)
	  {
		  try {
			  LOG.info("Enterd into generatePdfsForImpFamiles() method in VoterModifiationPdfsGenerations Class");
			    
			    PdfPTable table = new PdfPTable(7);
			    
			    Paragraph preface = new Paragraph();
			    preface.setAlignment(Element.PTABLE);
			   
			    preface.add( new Paragraph("               Previous Trends"));
			    preface.add( new Paragraph(" ") );
			    document.add(preface);
			    
		          PdfPCell cellHeading;
			      cellHeading = new PdfPCell(new Phrase("Previous Trends",style1));
			      cellHeading.setColspan(7);
				  cellHeading.setHorizontalAlignment(Element.ALIGN_CENTER);
				  cellHeading.setBackgroundColor(BaseColor.YELLOW);
				  table.addCell(cellHeading);
				  
				  PdfPCell cell ;
			  	  cell = new PdfPCell(new Phrase(previousTrends.get(0).getName()+ "/" + previousTrends.get(1).getName(),style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
				  
			  	  
			  	  cell = new PdfPCell(new Phrase("WORST",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.RED);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("VERY POOR",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.ORANGE);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("POOR",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.CYAN);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("OK",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.YELLOW);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("STRONG",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.BLUE);
			  	  table.addCell(cell);
			  	  
			  	  cell = new PdfPCell(new Phrase("VERY STRONG",style1));
			  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	  cell.setBackgroundColor(BaseColor.GREEN);
			  	  table.addCell(cell);
			  	  
			  	List<PartyPositionVO> previousElectionList = previousTrends.get(0).getPartyPositionVOList();
			  	List<PartyPositionVO> presentElectionList  = previousTrends.get(1).getPartyPositionVOList();
			  	Collections.reverse(previousElectionList);
			  	Collections.reverse(presentElectionList);
			  	int i = 0;
			  	  for (PartyPositionVO partyPositionVO : previousElectionList)
			  	  {
			  		  cell = new PdfPCell(new Phrase(partyPositionVO.getName(),style1));
				  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	  if(i == 0)
				  	  {
				  		 cell.setBackgroundColor(BaseColor.RED);
				  	  }  
				  	  else if(i == 1)
				  	  {
				  		 cell.setBackgroundColor(BaseColor.ORANGE);
				  	  }
				  	  else if(i == 2)
				  	  {
				  		 cell.setBackgroundColor(BaseColor.CYAN);
				  	  }
				  	  else if(i == 3)
				  	  {
				  		 cell.setBackgroundColor(BaseColor.YELLOW); 
				  	  }
				  	  else if(i == 4)
				  	  {
				  		 cell.setBackgroundColor(BaseColor.BLUE); 
				  	  }
				  	  else if(i == 5)
				  	  {
				  		 cell.setBackgroundColor(BaseColor.GREEN);
				  	  }
				  	  
				  	  table.addCell(cell);
				  	  
				  	 
				  	  
				  	  
			  		  for (PartyPositionVO partyPositionVO1 : presentElectionList)
			  		  {
				  			StringBuffer sb = new StringBuffer("");
				  			if(partyPositionVO.getPartyPositionVOList() != null && partyPositionVO.getPartyPositionVOList().size() > 0)
				  			{
				  				for(PartyPositionVO namesPositionVO : partyPositionVO.getPartyPositionVOList())
							  	  {
						  			
								  		  for(PartyPositionVO namesPositionVO1 : partyPositionVO1.getPartyPositionVOList())
									  	  {
								  			  if(namesPositionVO.getId().equals(namesPositionVO1.getId()))
								  				  sb.append(namesPositionVO.getName()+ '\n');
									  	  }
	
							  	  }
				  			}
			  			  cell = new PdfPCell(new Phrase(sb.toString(),style2));
					  	  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	  table.addCell(cell);
			  		  }
			  		  i++;
			  	  }
			  	table.setHeaderRows(2);
			  	document.add(table);
			  	document.newPage();
			  	
		} catch (Exception e) {
			LOG.debug("Exception raised in generatePdfForMatrixReport() method in VoterModifiationPdfsGenerations Class",e);
		}
	  }*/
}
