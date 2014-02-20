package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.itgrids.partyanalyst.dto.AnalysisBasicInfoVO;
import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsAnalysisVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface INewsAnalysisService {
	
	
	public ResultStatus saveDesignationDetails(final String designationString);
	
	public ResultStatus savePartyDetails(final String partyShortName,final String PartyLongName);
	
	public NewsAnalysisVO analyseNewsWithSelectedParameters(AnalysisVO analysisVO);
	
	public List<FileVO> getSelectedNews(NewsAnalysisVO vo,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex);
	
	public List<SelectOptionVO> getConstituencyesList(List<Long> districtIds);
	
	public List<SelectOptionVO> getProgramsWiseNews(List<Long> categIds, List<Long> constituencyIds,String fromDateStr , String toDateStr,Long startIndex , Long maxIndex,Long partyid,Long userId,String url,String requestType);
	
	public List<SelectOptionVO> getCategoeryWiseCountDetails(List<Long> categIds, List<Long> constituencyIds,String fromDateStr , String toDateStr,String type,List<Long> districtIds,Long partyId );
	
	public List<SelectOptionVO> generatePdfOrExcel(List<Long> catgIds,List<Long> constiIds,List<Long> districtIds,String fromDateStr,String toDateStr,String type,String Path,Long partyId);
	
	public SelectOptionVO getPartyWiseNewsCountForGraph(AnalysisVO analysisVO);
	
	public AnalysisBasicInfoVO getAnalysedNewsCount(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds);
	
	public List<FileVO> getAnalysedNews(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long onPartyId,String type,String benifit,Integer startIndex,Integer maxIndex,Long categoryId,Long sourceId,Long otherPartyId,Long keywordId);
	
	public List<NewsAnalysisVO> getSourceCategoryCount(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,String type,String benifit,Integer startIndex,Integer maxIndex,Long otherPartyId);
	
	public TreeMap<Long,String> getPartyNames(String partyIds);
	
	public SelectOptionVO generateExcelForAnalysis(Date startDate,Date endDate,String ids,String[] partyIds,Long locationLevelId,String locationLevelValue,Long candidatateId);
}
