package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itextpdf.text.Document;
import com.itgrids.partyanalyst.dto.PdfVO;
import com.itgrids.partyanalyst.dto.VoterAdderdOrDeletedRengesInfoVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;

public interface IVoterModifiationPdfsGenerations {

	 public void createTableForAddedVoters(Document document,List<VoterAdderdOrDeletedRengesInfoVO> returnList,String constituenyName);
	
	 public void deletedVotersDetails(Document document,List<VoterAdderdOrDeletedRengesInfoVO> returnList,String constituenyName);
	 
	 public void panchayatWiseAddedDeletedVoterDetails(Document document,PdfVO pdfVO,String constituenyName);
	 
	 public void totalAddedOrDeletedVoterDetails(String type,Document document,PdfVO pdfVO,String constituenyName);
	 
	 public void agewiseAddedDeletedVoterDetails(String type,Document document,PdfVO pdfVO,String constituenyName);
	 
	 public void buildAddedDeletedVotesByMundal(List<VoterModificationVO> list,Document document,String constituenyName);
	 
	 public void buildAddedDeletedVotesByGender(List<VoterModificationGenderInfoVO> list,Document document,String constituenyName);
	 
	 public void buildGenderWiseVoterModifivationReport(VoterModificationGenderInfoVO voterModificationGenderInfoVO,Document document,String constituenyName);
	 
	 public void buildVoterModifivationReport(List<VoterAgeRangeVO> voterAgeRangeVOList,Document document,String constituenyName);
	 
	 public void buildVoterModifivationReportByAgeRange(List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList,Document document,String constituenyName);
	 
	 public void buildAddedOrDeletedVotersbyPrecReport(String type,Document document,PdfVO pdfVO, String constituenyName);
	 
	 public void panchayatWiseAddedDeletedVoterDetailsByList(Document document,List<VotersDetailsVO> voterDetails,String constituenyName,String type);
	 
	 public void buildAddedOrDeletedVotersbyBoothWiseReport(String type,Document document,PdfVO pdfVO, String constituenyName,String constituencyType);
}
