package com.itgrids.partyanalyst.service.impl;

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
import com.itgrids.partyanalyst.dto.PdfVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAdderdOrDeletedRengesInfoVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
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
 * @
 *
 */
public class VoterModifiationPdfsGenerations implements IVoterModifiationPdfsGenerations{

	 private static Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	 private static Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN, 8,Font.NORMAL);
	 private static Logger LOG = Logger.getLogger(VoterModifiationPdfsGenerations.class);
	 
	 
	 
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
		 	  	table.setHeaderRows(3);
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
		 	  	table.setHeaderRows(10);
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

		 	  	 c1 = new PdfPCell(new Phrase("First Time Voters",BIGFONT));
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
		 	  	table.setHeaderRows(5);
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
		    try
		    {
		    	LOG.info("Enterd into buildAddedDeletedVotesByMundal() method in VoterModifiationPdfsGenerations Class");
		    	PdfPTable table = new PdfPTable(9);
		    	document.add( new Paragraph(" ") );
		    	document.add( new Paragraph(" ") );
		 	  	
		 	  	PdfPCell cellHeading = new PdfPCell(new Phrase("Mandal/Muncipality Wise Newly Added/Deleted Voters Info",BIGFONT));;
		 	  	cellHeading.setColspan(9);
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
		 	  	cell.setColspan(2);
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

		 	  	
		 	    

		 	  	PdfPCell c1 = new PdfPCell(new Phrase("Electoral Roll 2014 - Draft",BIGFONT));
		 	  	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  	c1.setBackgroundColor(BaseColor.YELLOW);
		 	  	table.addCell(c1);

		 	  	c1 = new PdfPCell(new Phrase("Electoral Roll 2013 - Final",BIGFONT));
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
		 	  	
		 	  
		 	  		  	
		 	  	if(list != null && list.size() > 0);
		 	  	{
		 	  		for (VoterModificationVO votersDetailsVO : list) {
		 	  			List<SelectOptionVO> selectOptionVOList = votersDetailsVO.getSelectOptionVOsList();
		 	  			PdfPCell c2 = new PdfPCell(new Phrase(votersDetailsVO.getName(),SMALLFONT));
		 	  			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		 	  			table.addCell(c2);
		 	  			
		 	  			//table.addCell(votersDetailsVO.getName());
		 	  			if(selectOptionVOList != null && selectOptionVOList.size() > 0)
		 	  			{
		 	  				Long count = 0l;
		 	  				for (SelectOptionVO selectOptionVO : selectOptionVOList) {
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
		 	  	float[] widths = new float[] { 1f, 1.5f ,1.5f,1f,1.2f, 1f ,1.2f,1f,1.2f};
		 	  	table.setWidths(widths);
		 	  	document.add(table);	 	  	
		 	  	
			}
		    catch (Exception e)
			{
				LOG.debug("Exception raised in buildAddedDeletedVotesByMundal() method in VoterModifiationPdfsGenerations Class");
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
  
}
