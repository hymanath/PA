package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IActivityReportDAO;
import com.itgrids.partyanalyst.dao.IActivityReportFilesDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.hibernate.DesignationDAO;
import com.itgrids.partyanalyst.dao.hibernate.PartyDAO;
import com.itgrids.partyanalyst.dto.AnalysisBasicInfoVO;
import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsAnalysisVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ActivityReport;
import com.itgrids.partyanalyst.model.ActivityReportFiles;
import com.itgrids.partyanalyst.model.Designation;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsAnalysisService;
import com.itgrids.partyanalyst.utils.CommonStringUtils;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IWebConstants;

public class NewsAnalysisService implements INewsAnalysisService {
   
	private static final Logger LOG = Logger.getLogger(NewsAnalysisService.class);
    private IFileDAO fileDAO;
    private ICandidateDetailsService candidateDetailsService;
	private TransactionTemplate transactionTemplate;	
	private DesignationDAO designationDAO;
	private PartyDAO partyDAO;
	private IConstituencyDAO constituencyDAO ;
	private ICandidatePartyCategoryDAO candidatePartyCategoryDAO;
	private IGallaryDAO gallaryDAO;
	private static Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
	private static Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN, 8,Font.NORMAL);
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font catFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 11,  Font.BOLD);
	private IActivityReportDAO activityReportDAO;
	private IUserDAO userDAO;
	private IActivityReportFilesDAO activityReportFilesDAO;
	private ICandidatePartyFileDAO candidatePartyFileDAO;
	private ICandidateDAO candidateDAO;
	
	
	
	/**
	 * @return the candidatePartyFileDAO
	 */
	public ICandidatePartyFileDAO getCandidatePartyFileDAO() {
		return candidatePartyFileDAO;
	}
	/**
	 * @param candidatePartyFileDAO the candidatePartyFileDAO to set
	 */
	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}
	/**
	 * @return the gallaryDAO
	 */
	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}
	/**
	 * @param gallaryDAO the gallaryDAO to set
	 */
	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}
	/**
	 * @return the candidatePartyCategoryDAO
	 */
	public ICandidatePartyCategoryDAO getCandidatePartyCategoryDAO() {
		return candidatePartyCategoryDAO;
	}
	/**
	 * @param candidatePartyCategoryDAO the candidatePartyCategoryDAO to set
	 */
	public void setCandidatePartyCategoryDAO(
			ICandidatePartyCategoryDAO candidatePartyCategoryDAO) {
		this.candidatePartyCategoryDAO = candidatePartyCategoryDAO;
	}
	/**
	 * @return the constituencyDAO
	 */
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	/**
	 * @param constituencyDAO the constituencyDAO to set
	 */
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IFileDAO getFileDAO() {
		return fileDAO;
	}
	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	
	public void setPartyDAO(PartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	/**
	 * @param designationDAO the designationDAO to set
	 */
	public void setDesignationDAO(DesignationDAO designationDAO) {
		this.designationDAO = designationDAO;
	}

	public DesignationDAO getDesignationDAO() {
		return designationDAO;
	}
	public PartyDAO getPartyDAO() {
		return partyDAO;
	}
	
	public IActivityReportFilesDAO getActivityReportFilesDAO() {
		return activityReportFilesDAO;
	}
	public void setActivityReportFilesDAO(
			IActivityReportFilesDAO activityReportFilesDAO) {
		this.activityReportFilesDAO = activityReportFilesDAO;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public IActivityReportDAO getActivityReportDAO() {
		return activityReportDAO;
	}
	public void setActivityReportDAO(IActivityReportDAO activityReportDAO) {
		this.activityReportDAO = activityReportDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	
	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}
	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	public NewsAnalysisVO analyseNewsWithSelectedParameters(AnalysisVO analysisVO){
		if(LOG.isInfoEnabled()){
			LOG.info("Enter in to analyseNewsWithSelectedParameters method ");
		}
		try{
			if(analysisVO.isByCategory()){
				if((analysisVO.isBySourceCand() || analysisVO.isByDestiCand()) && (analysisVO.isSourceCand() || analysisVO.isSourceParty() || analysisVO.isDestiCand() || analysisVO.isDestiParty())){
				 return getResultsNew(analysisVO,"Category");
				}else{
				 return getSourceDestinationCategoryPresentQuery(analysisVO);
				}				 
			}else if(analysisVO.isByKeyword()){
				if((analysisVO.isBySourceCand() || analysisVO.isByDestiCand()) && (analysisVO.isSourceCand() || analysisVO.isSourceParty() || analysisVO.isDestiCand() || analysisVO.isDestiParty())){
				  return getResultsNew(analysisVO,"Keyword");
				}else{
				  return getSourceDestinationKeywordPresentQuery(analysisVO);
				}
			}
			else if(( ((analysisVO.isDestiCand() || analysisVO.isDestiParty()) && !analysisVO.isSourceCand() && !analysisVO.isSourceParty()) || ((analysisVO.isSourceCand() ||  analysisVO.isSourceParty()) && !analysisVO.isDestiCand() && !analysisVO.isDestiParty())) && !analysisVO.isByKeyword() && !analysisVO.isByCategory() && !analysisVO.isSourcePresent() && !analysisVO.isLocationPresent() && !analysisVO.isByDestiCand() && !analysisVO.isBySourceCand()){
				return getSourceDestinationKeywordPresentQueryByParty(analysisVO);
			}
			else if(!analysisVO.isByDestiCand() && !analysisVO.isBySourceCand() && !analysisVO.isByCategory() && !analysisVO.isByKeyword() && !analysisVO.isSourceCand() && !analysisVO.isSourceParty() && !analysisVO.isDestiCand() && !analysisVO.isDestiParty() && (analysisVO.isSourcePresent() || analysisVO.isLocationPresent() || analysisVO.getFromDate() != null || analysisVO.getToDate() != null )){
				 return getOnlySourceDestinationPresent(analysisVO); 
			}else{
				if((analysisVO.isBySourceCand() || analysisVO.isByDestiCand()) && (analysisVO.isSourceCand() || analysisVO.isSourceParty() || analysisVO.isDestiCand() || analysisVO.isDestiParty())){
					return getResultsNew(analysisVO,"Others");
				}else{
				  return getSourceDestinationPresentIncludeCandidateParty(analysisVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception rised in analyseNewsWithSelectedParameters ",e);
			return new NewsAnalysisVO();
		}
	}
	
	
	public NewsAnalysisVO getSourceDestinationKeywordPresentQueryByParty(AnalysisVO vo)
	{
		List<Object[]> list1 = null;
		List<Object[]> list2 = null;
		List<Object[]> list3 = null;
		List<Object[]> list4 = null;
		List<Object[]> list5 = null;
		List<Object[]> list6 = null;
		if(vo.isSourceCand())
		{
		     list1 = candidatePartyFileDAO.getSourcePartyCommentsOnly(vo.getSourcePartyId(),vo.getSourceCandidateId(),vo.getFromDate(),vo.getToDate());
			 list2 = candidatePartyFileDAO.getSourcePartyCandidateComments(vo.getSourcePartyId(),vo.getSourceCandidateId(),vo.getFromDate(),vo.getToDate());
			 list3 = candidatePartyFileDAO.getSourcePartyComments(vo.getSourcePartyId(),vo.getSourceCandidateId(),vo.getFromDate(),vo.getToDate());
		}
		else if(vo.isSourceParty())
		{
			 list1 = candidatePartyFileDAO.getSourcePartyCommentsOnly(vo.getSourcePartyId(),null,vo.getFromDate(),vo.getToDate());
			 list2 = candidatePartyFileDAO.getSourcePartyCandidateComments(vo.getSourcePartyId(),null,vo.getFromDate(),vo.getToDate());
			 list3 = candidatePartyFileDAO.getSourcePartyComments(vo.getSourcePartyId(),null,vo.getFromDate(),vo.getToDate());
			 list4 = candidatePartyFileDAO.getSourcePartyCommentsOnly(vo.getSourcePartyId(),0l,vo.getFromDate(),vo.getToDate());
			 list5 = candidatePartyFileDAO.getSourcePartyCandidateComments(vo.getSourcePartyId(),0l,vo.getFromDate(),vo.getToDate());
			 list6 = candidatePartyFileDAO.getSourcePartyComments(vo.getSourcePartyId(),0l,vo.getFromDate(),vo.getToDate());
		}
		else if(vo.isDestiCand())
		{
			 list1 = candidatePartyFileDAO.getDestinationPartyCommentsOnly(vo.getDestiPartyId(),vo.getDestiCandidateId(),vo.getFromDate(),vo.getToDate());
			 list2 = candidatePartyFileDAO.getDestinationPartyComments(vo.getDestiPartyId(),vo.getDestiCandidateId(),vo.getFromDate(),vo.getToDate());
			 list3 = candidatePartyFileDAO.getDestinationPartyCandidateComments(vo.getDestiPartyId(),vo.getDestiCandidateId(),vo.getFromDate(),vo.getToDate());
		}
		else
		{
			 list1 = candidatePartyFileDAO.getDestinationPartyCommentsOnly(vo.getDestiPartyId(),null,vo.getFromDate(),vo.getToDate());
			 list2 = candidatePartyFileDAO.getDestinationPartyComments(vo.getDestiPartyId(),null,vo.getFromDate(),vo.getToDate());
			 list3 = candidatePartyFileDAO.getDestinationPartyCandidateComments(vo.getDestiPartyId(),null,vo.getFromDate(),vo.getToDate());
			 list4 = candidatePartyFileDAO.getDestinationPartyCommentsOnly(vo.getDestiPartyId(),0l,vo.getFromDate(),vo.getToDate());
			 list5 = candidatePartyFileDAO.getDestinationPartyComments(vo.getDestiPartyId(),0l,vo.getFromDate(),vo.getToDate());
			 list6 = candidatePartyFileDAO.getDestinationPartyCandidateComments(vo.getDestiPartyId(),0l,vo.getFromDate(),vo.getToDate());
		}
		
		return fillNewsAnalysisVOForDisplay(list1,list2,list3,list4,list5,list6);
	}
	
	public NewsAnalysisVO fillNewsAnalysisVOForDisplay(List<Object[]> list1,List<Object[]> list2,List<Object[]> list3,List<Object[]> list4,List<Object[]> list5,List<Object[]> list6)
	{
		NewsAnalysisVO newsAnalysisVO = null;
		if(list1 != null || list2 != null || list3 != null || list4 != null || list5 != null || list6 != null)
		{
			newsAnalysisVO = new NewsAnalysisVO();
			newsAnalysisVO.setBuildMethod("six");
			List<NewsAnalysisVO> newsAnalysisVOList = new ArrayList<NewsAnalysisVO>();
			if(list1 != null && list1.size() > 0)
			{
				fillVO(list1,newsAnalysisVOList);
			}
			if(list2 != null && list2.size() > 0)
			{
				fillVO(list2,newsAnalysisVOList);
			}
			if(list3 != null && list3.size() > 0)
			{
				fillVO(list3,newsAnalysisVOList);
			}
			if(list4 != null && list4.size() > 0)
			{
				fillVO(list4,newsAnalysisVOList);
			}
			if(list5 != null && list5.size() > 0)
			{
				fillVO(list5,newsAnalysisVOList);
			}
			if(list6 != null && list6.size() > 0)
			{
				fillVO(list6,newsAnalysisVOList);
			}
			newsAnalysisVO.setSubList(newsAnalysisVOList);
		}
		return newsAnalysisVO;
	}
	public void fillVO(List<Object[]> list,List<NewsAnalysisVO> newsAnalysisVOList)
	{
		for (Object[] parms : list) {
			NewsAnalysisVO newsAnalysisVO1 = new NewsAnalysisVO();
			newsAnalysisVO1.setSourceCandId((Long)parms[7]);
			newsAnalysisVO1.setSorceCandName(parms[1] != null? parms[1].toString():"");
			newsAnalysisVO1.setTotal((Long)parms[2]);
			newsAnalysisVO1.setDestiCandId((Long)parms[8]);
			newsAnalysisVO1.setDestCandName(parms[4] != null ? parms[4].toString():"");
			newsAnalysisVO1.setSourcePartyId((Long)parms[5]);
			newsAnalysisVO1.setDestiPartyId((Long)parms[6]);
			newsAnalysisVOList.add(newsAnalysisVO1);
		}
	}
	public NewsAnalysisVO getSourceDestinationCategoryPresentQuery(AnalysisVO vo){
		StringBuilder query = new StringBuilder();
		// bySourceCand analyse by who candidate
		// byDestiCand analyse by whome candidate
		// sourcePresent ex: eendau,sakshi,andhra jyothi
		// locationPresent ex: state ap,district ranga reddy,ac ,pc
		// sourceCand if source candidate is selected
		// sourceParty if source party is selected
		// destiCand if destination candidate is selected
		// destiParty if destination party is selected
		query.append("select ");
		query.append("  count(distinct cpc.candidatePartyFile.file.fileId) ,");
		if(vo.isBySourceCand()){
			query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId,cpc.candidatePartyFile.sourceCandidate.lastname, ");
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isByDestiCand()){
			query.append(" cpc.candidatePartyFile.destinationCandidate.candidateId,cpc.candidatePartyFile.destinationCandidate.lastname, ");
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isLocationPresent()){
			if(vo.getLocationLvl().longValue() == 1){
				 query.append(" cpc.candidatePartyFile.file.userAddress.district.districtId,cpc.candidatePartyFile.file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId,cpc.candidatePartyFile.file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" cpc.candidatePartyFile.file.userAddress.constituency.constituencyId,cpc.candidatePartyFile.file.userAddress.constituency.name,");
			} 
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(!vo.isSourcePresent()){
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
				
				
			   query.append(" fsl.source.sourceId,fsl.source.source,cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc,FileSourceLanguage fsl " +
			   		" where cpc.candidatePartyFile.file.isDeleted !='Y' and cpc.candidatePartyFile.file.fileId = fsl.file.fileId ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				if(vo.isSourceCand()){
				    query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" and fsl.source.sourceId in ("+vo.getSourceIds()+") and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+") group by ");
			   
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   
			   if(vo.isBySourceCand()){
					query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId, ");
				}
			   if(vo.isByDestiCand()){
					query.append(" cpc.candidatePartyFile.destinationCandidate.candidateId, ");
				}
			   query.append(" fsl.source.sourceId ,cpc.gallary.gallaryId ");
			}else{
				query.append("  fsl.source.sourceId,fsl.source.source,cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc" +
				   		" ,FileSourceLanguage fsl where cpc.candidatePartyFile.file.isDeleted != 'Y' and cpc.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
						if(vo.isSourceCand()){
							query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
						}else if(vo.isSourceParty()){
						   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
						}
						if(vo.isDestiCand()){
						  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
						}else if(vo.isDestiParty()){
						   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
						}
						if(vo.getFromDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
						}
						if(vo.getToDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
						}
						if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
							query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
						}
		                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
						}
				query.append("  and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+") group by  ");
				if(vo.isBySourceCand()){
					query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId, ");
				}
				if(vo.isByDestiCand()){
					query.append(" cpc.candidatePartyFile.destinationCandidate.candidateId, ");
				}
				query.append(" fsl.source.sourceId ,cpc.gallary.gallaryId");
			}
		}else{
			query.append(" cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc where cpc.candidatePartyFile.file.isDeleted !='Y' ");
			query.append("  and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+")  ");
			          if(vo.isSourceCand()){
							query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
						}else if(vo.isSourceParty()){
						   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
						}
						if(vo.isDestiCand()){
						  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
						}else if(vo.isDestiParty()){
						   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+"  ");
						}
						if(vo.isLocationPresent()){	
							if(vo.getLocationLvl().longValue() == 1){
								 query.append( " and cpc.candidatePartyFile.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
							}else if(vo.getLocationLvl().longValue() == 2){
								query.append(" and cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
							}else if(vo.getLocationLvl().longValue() == 3){
								query.append(" and cpc.candidatePartyFile.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
							}
						}
						if(vo.getFromDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
						}
						if(vo.getToDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
						}
						if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
							query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
						}
		                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
						}
						query.append(" group by ");
						if(vo.isLocationPresent()){
							if(vo.getLocationLvl().longValue() == 1){
								 query.append( "  cpc.candidatePartyFile.file.userAddress.district.districtId ,");
							}else if(vo.getLocationLvl().longValue() == 2){
								query.append(" cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId ,");
							}else if(vo.getLocationLvl().longValue() == 3){
								query.append(" cpc.candidatePartyFile.file.userAddress.constituency.constituencyId , ");
							}
						}
						if(vo.isBySourceCand()){
							query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId, ");
						}
						if(vo.isByDestiCand()){
							query.append(" cpc.candidatePartyFile.destinationCandidate.candidateId, ");
						}
						query.append("  cpc.gallary.gallaryId");
		}
		 return getDataForCategoryOrKeywordPresent( query.toString(),vo,null,null,false,"Category");
	}
	
	public NewsAnalysisVO getSourceDestinationKeywordPresentQuery(AnalysisVO vo){
		StringBuilder query = new StringBuilder();
		query.append("select  ");
		query.append("  count(distinct cpk.candidatePartyFile.file.fileId) ,");
		if(vo.isBySourceCand()){
			query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId,cpk.candidatePartyFile.sourceCandidate.lastname, ");
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		
		if(vo.isByDestiCand()){
			query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId,cpk.candidatePartyFile.destinationCandidate.lastname, ");
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isLocationPresent()){
			if(vo.getLocationLvl().longValue() == 1){
				 query.append("cpk.candidatePartyFile.file.userAddress.district.districtId,cpk.candidatePartyFile.file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId,cpk.candidatePartyFile.file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" cpk.candidatePartyFile.file.userAddress.constituency.constituencyId,cpk.candidatePartyFile.file.userAddress.constituency.name,");
			} 
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(!vo.isSourcePresent()){
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
			   query.append(" fsl.source.sourceId,fsl.source.source,cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk,FileSourceLanguage fsl " +
			   		" where cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.candidatePartyFile.file.fileId = fsl.file.fileId ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
		      if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" and fsl.source.sourceId in ("+vo.getSourceIds()+") and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+") group by ");
			   
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   
			   if(vo.isBySourceCand()){
					query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
				}
               if(vo.isByDestiCand()){
					query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
				}
			   query.append(" fsl.source.sourceId ,cpk.keyword.keywordId ");
			}else{
				query.append("   fsl.source.sourceId,fsl.source.source,cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk " +
				   		" ,FileSourceLanguage fsl where cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				 if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append("  and cpk.keyword.keywordId in ( "+vo.getGallaryKeyWordIds()+") group by  ");
				 if(vo.isBySourceCand()){
						query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
					}
				 if(vo.isByDestiCand()){
						query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
					}
				 query.append(" fsl.source.sourceId ,cpk.keyword.keywordId ");
			}
		}else{
			if(vo.isLocationPresent()){
				
				
			   query.append(" cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk " +
			   		" where  cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+") ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and cpk.candidatePartyFile.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and cpk.candidatePartyFile.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				 if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append("  group by ");
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  cpk.candidatePartyFile.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" cpk.candidatePartyFile.file.userAddress.constituency.constituencyId , ");
				}
			   if(vo.isBySourceCand()){
					query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
				}
			   if(vo.isByDestiCand()){
					query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
				}
			   query.append(" cpk.keyword.keywordId ");
			}else{
			  query.append(" cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk where cpk.candidatePartyFile.file.isDeleted !='Y' ");
			  query.append("  and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+")  ");
			   if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append(" group by ");
				
				    if(vo.isBySourceCand()){
						query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
					}
				    if(vo.isByDestiCand()){
						query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
					}
				    
				    query.append(" cpk.keyword.keywordId");
			}
		}
		return getDataForCategoryOrKeywordPresent(query.toString(),vo,null,null,false,"Keyword");
	}
	
	public NewsAnalysisVO getOnlySourceDestinationPresent(AnalysisVO vo){
		StringBuilder query = new StringBuilder();
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
				query.append("select  count(distinct fsl.file.fileId) ,");
				if(vo.getLocationLvl().longValue() == 1){
					 query.append(" fsl.file.userAddress.district.districtId,fsl.file.userAddress.district.districtName,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId,fsl.file.userAddress.parliamentConstituency.name,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId,fsl.file.userAddress.constituency.name,");
				} 
			   query.append(" fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl where fsl.file.isDeleted !='Y' and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
			   if(vo.getFromDate() != null){
					query.append(" and date(fsl.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(fsl.file.fileDate) <= :toDate ");
				}
			   query.append(" group by ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   query.append(" fsl.source.sourceId  ");
			}else{
				query.append(" select  count(distinct fsl.file.fileId) ,cast(null as char),cast(null as char), fsl.source.sourceId,fsl.source.source from  " +
				   		" FileSourceLanguage fsl where fsl.file.isDeleted !='Y' and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				if(vo.getFromDate() != null){
					query.append(" and date(fsl.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(fsl.file.fileDate) <= :toDate ");
				}
				query.append("   group by fsl.source.sourceId ");
				
			}
		}else if(vo.isLocationPresent()){
			query.append("select  count(distinct file.fileId) ,");
			if(vo.getLocationLvl().longValue() == 1){
				 query.append(" file.userAddress.district.districtId,file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId,file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId,file.userAddress.constituency.name,");
			} 
		   query.append("  cast(null as char),cast(null as char) from File file where file.isDeleted != 'Y' ");
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( " and file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" and file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" and file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
			}
		   if(vo.getFromDate() != null){
				query.append(" and date(file.fileDate) >= :fromDate ");
			}
			if(vo.getToDate() != null){
				query.append(" and date(file.fileDate) <= :toDate ");
			}
		   query.append(" group by ");
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( "  file.userAddress.district.districtId");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId ");
			}
		}else {
			query.append("select count(distinct file.fileId),cast(null as char),cast(null as char),cast(null as char),cast(null as char) from File file where file.isDeleted !='Y' ");
			if(vo.getFromDate() != null && vo.getToDate() != null){
				query.append(" and file.fileDate >= :fromDate and file.fileDate <= :toDate");
			}else if(vo.getFromDate() != null){
				query.append(" and file.fileDate >= :fromDate ");
			}else if(vo.getToDate() != null){
				query.append(" and file.fileDate <= :toDate ");
			}
		}
		
		return getDataForOnlySourceDestinationPresent(query.toString(),vo);
	}
	
	public NewsAnalysisVO getSourceDestinationPresentIncludeCandidateParty(AnalysisVO vo){
		StringBuilder query = new StringBuilder();
		query.append("select  count(distinct cpf.file.fileId),");
		if(vo.isBySourceCand()){
			query.append(" cpf.sourceCandidate.candidateId,cpf.sourceCandidate.lastname, ");
		}else{
			query.append("cast(null as char),cast(null as char),");
		} 
		if(vo.isByDestiCand()){
			query.append(" cpf.destinationCandidate.candidateId,cpf.destinationCandidate.lastname, ");
		}else{
			query.append("cast(null as char),cast(null as char),");
		}
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
				
				if(vo.getLocationLvl().longValue() == 1){
					 query.append(" fsl.file.userAddress.district.districtId,fsl.file.userAddress.district.districtName,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId,fsl.file.userAddress.parliamentConstituency.name,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId,fsl.file.userAddress.constituency.name,");
				} 
			   query.append(" fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf where fsl.file.isDeleted != 'Y' and fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" group by ");
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   if(vo.isBySourceCand()){
					query.append(" cpf.sourceCandidate.candidateId, ");
				}
			   if(vo.isByDestiCand()){
					query.append(" cpf.destinationCandidate.candidateId, ");
				}
			   query.append(" fsl.source.sourceId  ");
			}else{
				query.append("   cast(null as char),cast(null as char),fsl.source.sourceId,fsl.source.source from  " +
				   		" FileSourceLanguage fsl,CandidatePartyFile cpf where fsl.file.isDeleted != 'Y' and fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append("   group by  ");
				 if(vo.isBySourceCand()){
						query.append(" cpf.sourceCandidate.candidateId, ");
					}
				 if(vo.isByDestiCand()){
						query.append(" cpf.destinationCandidate.candidateId, ");
					}
				query.append(" fsl.source.sourceId ");
				
			}
		}else if(vo.isLocationPresent()){
			
			if(vo.getLocationLvl().longValue() == 1){
				 query.append(" file.userAddress.district.districtId,file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId,file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId,file.userAddress.constituency.name,");
			} 
		   query.append(" cast(null as char),cast(null as char) from File file,CandidatePartyFile cpf  where file.isDeleted != 'Y' and file.fileId = cpf.file.fileId ");
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( " and file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" and file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" and file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
			}
		   if(vo.isSourceCand()){
				query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
			}else if(vo.isSourceParty()){
			   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
			}
			if(vo.isDestiCand()){
			  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
			}else if(vo.isDestiParty()){
			   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
			}
			if(vo.getFromDate() != null){
				query.append(" and date(file.fileDate) >= :fromDate ");
			}
			if(vo.getToDate() != null){
				query.append(" and date(file.fileDate) <= :toDate ");
			}
			if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
				query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
			}
             if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
            	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
			}
		   query.append(" group by ");
		  
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( "  file.userAddress.district.districtId");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId ");
			}
		   if(vo.isBySourceCand()){
				query.append(" ,cpf.sourceCandidate.candidateId ");
			}
		   if(vo.isByDestiCand()){
				query.append(" ,cpf.destinationCandidate.candidateId ");
			}
		}else{
			query = new StringBuilder();
			if(vo.isSourceCand() || vo.isSourceParty() || vo.isDestiCand() || vo.isDestiParty() || vo.isBySourceCand() || vo.isByDestiCand()){
				query.append("select count(distinct cpf.file.fileId) ");
				if(vo.isBySourceCand()){
					query.append(" ,cpf.sourceCandidate.candidateId,cpf.sourceCandidate.lastname ");
				}else{
					query.append(" ,cast(null as char),cast(null as char) ");
				}
				if(vo.isByDestiCand()){
					query.append(" ,cpf.destinationCandidate.candidateId,cpf.destinationCandidate.lastname ");
				}else{
					query.append(" ,cast(null as char),cast(null as char) ");
				}
				query.append(" ,cast(null as char),cast(null as char),cast(null as char),cast(null as char) from  CandidatePartyFile cpf where cpf.file.isDeleted != 'Y' ");
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
                 if(vo.isBySourceCand() && vo.isByDestiCand()){
                	 query.append(" group by cpf.sourceCandidate.candidateId,cpf.destinationCandidate.candidateId ");
                 }
                 else if(vo.isBySourceCand()){
					query.append(" group by cpf.sourceCandidate.candidateId ");
				}
                 else if(vo.isByDestiCand()){
					query.append(" group by cpf.destinationCandidate.candidateId ");
				}
			}else{
				query.append("select count(distinct file.fileId),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char) from File file where file.isDeleted != 'Y' ");
				if(vo.getFromDate() != null && vo.getToDate() != null){
					query.append(" and file.fileDate >= :fromDate and file.fileDate <= :toDate");
				}else if(vo.getFromDate() != null){
					query.append(" and file.fileDate >= :fromDate ");
				}else if(vo.getToDate() != null){
					query.append(" and file.fileDate <= :toDate ");
				}
			}
		}
		return getDataForSourceDestinationPresentIncludeCandidateParty(query.toString(),vo,null,null,false);
	}
	
	public NewsAnalysisVO getDataForCategoryOrKeywordPresent(String query,AnalysisVO vo,String sourceType,String destiType,boolean considerParty,String cagetOrKey){
		NewsAnalysisVO returnVal = new NewsAnalysisVO();
		List<String> levels = new ArrayList<String>();
		returnVal.setLevels(levels);
		returnVal.setBuildMethod("first");
		Map<Long,Map<Long,Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>>>> newsCountsMap = new HashMap<Long,Map<Long,Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>>>>();
		//  loc    soucand   destcand  sour    cat/keyword
		Map<Long,Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>>> sourceCandidateMap = null;
		Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>> candidateMap = null;
		Map<Long,Map<Long,NewsAnalysisVO>> sourceMap = null;
		Map<Long,NewsAnalysisVO> categKeywordMap = null;
		NewsAnalysisVO countVO = null;
		Map<Long,String> locationNamesMap = new HashMap<Long,String>();
		Map<Long,String> candidateNamesMap = new HashMap<Long,String>();
		Map<Long,String> sourceNamesMap = new HashMap<Long,String>();
		
		boolean soucandidateLvl = true;
		boolean candidateLvl = true;
		boolean sourceLvl = true;
		boolean categKeyLvl = true;
		boolean soucandidateSubLvl = false;
		boolean candidateSubLvl = false;
		boolean sourceSubLvl = false;
		
	    List<Object[]>	newsList = fileDAO.getNewsBySearchCriteria(query,vo);
		// 0 count 1 soucandidateId 2 soucandidateName 3 desticandidateId 4 desticandidateName 5 locationId 6 locationName 7 sourceId 8 sourceName 9 gallaryId 10 gallaryName 11 benifits
	    
	    for(Object[] news:newsList){
	    	sourceCandidateMap = newsCountsMap.get((Long)news[5]);
			if(sourceCandidateMap == null){
				sourceCandidateMap = new HashMap<Long,Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>>>();
				newsCountsMap.put((Long)news[5],sourceCandidateMap);
				if(news[6] != null)
				 locationNamesMap.put((Long)news[5],news[6].toString());
			}
	    	candidateMap = sourceCandidateMap.get((Long)news[1]);
			if(candidateMap == null){
			    candidateMap = new HashMap<Long,Map<Long,Map<Long,NewsAnalysisVO>>>();
			    sourceCandidateMap.put((Long)news[1],candidateMap);
				if(news[2] != null)
				 candidateNamesMap.put((Long)news[1],news[2].toString());
			}
			sourceMap = candidateMap.get((Long)news[3]);
			if(sourceMap == null){
			    sourceMap = new HashMap<Long,Map<Long,NewsAnalysisVO>>();
				candidateMap.put((Long)news[3],sourceMap);
				if(news[4] != null)
				candidateNamesMap.put((Long)news[3],news[4].toString());
			}
			categKeywordMap = sourceMap.get((Long)news[7]);
			if(categKeywordMap == null){
			    categKeywordMap = new HashMap<Long,NewsAnalysisVO>();
				sourceMap.put((Long)news[7],categKeywordMap);
				if(news[8] != null)
				sourceNamesMap.put((Long)news[7],news[8].toString());
			}
			countVO = categKeywordMap.get((Long)news[9]);
			if(countVO == null){
			    countVO = new NewsAnalysisVO();
			    countVO.setLocationId((Long)news[5]);
			    countVO.setSourceId((Long)news[7]);
			    countVO.setLocationLvl(vo.getLocationLvl());
			    countVO.setSourceType(sourceType);
			    countVO.setDestiType(destiType);
			    if(considerParty){
			     countVO.setConsiderParty("true");
			    }else{
			    	countVO.setConsiderParty("false");
			    }
			    	
			    if(vo.isByCategory()){
			      countVO.setCategoryId((Long)news[9]);
			    }
			    if(vo.isByKeyword()){
			      countVO.setKeywordId((Long)news[9]);
			    }
			    if(vo.isBySourceCand()){
			    	countVO.setSourceCandId((Long)news[1]);
			    }
			    if(vo.isByDestiCand()){
			    	countVO.setDestiCandId((Long)news[3]);
			    }
			    if(vo.isSourceCand()){
			    	countVO.setSourceCandId(vo.getSourceCandidateId());
				}else if(vo.isSourceParty()){
					countVO.setSourcePartyId(vo.getSourcePartyId());
				}
				if(vo.isDestiCand()){
					countVO.setDestiCandId(vo.getDestiCandidateId());
				}else if(vo.isDestiParty()){
					countVO.setDestiPartyId(vo.getDestiPartyId());
				}
				countVO.setSourceBenifitId(vo.getSourceBenifitId());
				countVO.setDestiBenifitId(vo.getDestiBenifitId());
			    countVO.setName(news[10] != null?news[10].toString():"");
				categKeywordMap.put((Long)news[9],countVO);
			}
			/*if(((Long)news[9]).intValue() == 1){
				countVO.setPositiveCount((Long)news[0]);
			}else if(((Long)news[9]).intValue() == 2){
				countVO.setNegativeCount((Long)news[0]);
			}else{
				countVO.setNeutralCount((Long)news[0]);
			}*/
			countVO.setTotal(countVO.getTotal()+(Long)news[0]);
	    }
	    
	    Set<Long> locationKeys = newsCountsMap.keySet();
	    if(locationKeys.size() == 1 && locationKeys.contains(null)){
	    	
	    }else{
	    	levels.add("Location");
	    	returnVal.setSubListPresent(true);
	    }
	    List<NewsAnalysisVO> locationList = new ArrayList<NewsAnalysisVO>();
	    returnVal.setSubList(locationList);
	    for(Long key:locationKeys){
	    	NewsAnalysisVO location = new NewsAnalysisVO();	    	
	    	location.setName(locationNamesMap.get(key));
	    	locationList.add(location);
	    	List<NewsAnalysisVO> sourceCandidateList = new ArrayList<NewsAnalysisVO>();
	    	location.setSubList(sourceCandidateList);
	    	sourceCandidateMap = newsCountsMap.get(key);
	    	Set<Long> sourcandidateKeys = sourceCandidateMap.keySet();
	    	if(soucandidateLvl){
	    		if(!(sourcandidateKeys.size() == 1 && sourcandidateKeys.contains(null))){
	    			levels.add("Who");
	    			soucandidateSubLvl = true;
	    	    }
	    		soucandidateLvl = false;
	    	}
	    	location.setSubListPresent(soucandidateSubLvl);
	      for(Long soucandidateKey:sourcandidateKeys){	
	    		NewsAnalysisVO sourCandidate = new NewsAnalysisVO();	    	
	    		sourCandidate.setName(candidateNamesMap.get(soucandidateKey));
	    		sourceCandidateList.add(sourCandidate);
		    	List<NewsAnalysisVO> candidateList = new ArrayList<NewsAnalysisVO>();
		    	sourCandidate.setSubList(candidateList);
		    	candidateMap = sourceCandidateMap.get(soucandidateKey);
		    	Set<Long> candidateKeys = candidateMap.keySet();
		    	if(candidateLvl){
		    		if(!(candidateKeys.size() == 1 && candidateKeys.contains(null))){
		    			levels.add("Whome");
		    			candidateSubLvl = true;
		    	    }
		    		candidateLvl = false;
		    	}
		    	sourCandidate.setSubListPresent(candidateSubLvl);  
	    	for(Long candidateKey:candidateKeys){
	    		
	    		NewsAnalysisVO candidate = new NewsAnalysisVO();
	    		candidate.setName(candidateNamesMap.get(candidateKey));
	    		candidateList.add(candidate);
		    	List<NewsAnalysisVO> sourceList = new ArrayList<NewsAnalysisVO>();
		    	candidate.setSubList(sourceList);
	    		sourceMap = candidateMap.get(candidateKey);
		    	Set<Long> sourceKeys = sourceMap.keySet();
		    	if(sourceLvl){
		    		if(!(sourceKeys.size() == 1 && sourceKeys.contains(null))){
		    			levels.add("Source");
		    			sourceSubLvl = true;
		    	    }
		    		sourceLvl = false;
		    	}
		    	candidate.setSubListPresent(sourceSubLvl);
		    	for(Long sourceKey:sourceKeys){
		    		NewsAnalysisVO source = new NewsAnalysisVO();
		    		source.setName(sourceNamesMap.get(sourceKey));
		    		source.setSubListPresent(true);
		    		sourceList.add(source);
		    		
			    	
		    		categKeywordMap = sourceMap.get(sourceKey);
		    		source.setRowSpan(categKeywordMap.size());
			    	Set<Long> categKeywordKeys = categKeywordMap.keySet();
			    	location.setRowSpan(location.getRowSpan()+categKeywordMap.size());
			    	candidate.setRowSpan(candidate.getRowSpan()+categKeywordMap.size());
			    	sourCandidate.setRowSpan(sourCandidate.getRowSpan()+categKeywordMap.size());
			    	if(categKeyLvl){
			    		if(!(categKeywordKeys.size() == 1 && categKeywordKeys.contains(null))){
			    			levels.add(cagetOrKey);
			    	    }
			    		categKeyLvl = false;
			    	}
			    		source.setSubList(new ArrayList<NewsAnalysisVO>(categKeywordMap.values()));
			    	
			    	
			    }
		    }
	      }
	    }
	    
		return returnVal;
	}
	
	public NewsAnalysisVO getDataForOnlySourceDestinationPresent(String query,AnalysisVO vo){
		NewsAnalysisVO returnVal = new NewsAnalysisVO();
		List<String> levels = new ArrayList<String>();
		returnVal.setLevels(levels);
		returnVal.setBuildMethod("second");
		Map<Long,Map<Long,NewsAnalysisVO>> newsCountsMap = new HashMap<Long,Map<Long,NewsAnalysisVO>>();
		//  loc      sour          
		Map<Long,NewsAnalysisVO> sourceMap = null;
		NewsAnalysisVO countVO = null;
		Map<Long,String> locationNamesMap = new HashMap<Long,String>();
		boolean sourceSubLvl = false;
		boolean sourceLvl = true;
	    List<Object[]>	newsList = fileDAO.getNewsBySearchCriteria(query,vo);
		// 0 count  1 locationId 2 locationName 3 sourceId 4 sourceName 
	    
	    for(Object[] news:newsList){
	    	sourceMap = newsCountsMap.get((Long)news[1]);
			if(sourceMap == null){
				sourceMap = new HashMap<Long,NewsAnalysisVO>();
				newsCountsMap.put((Long)news[1],sourceMap);
				if(news[2] != null)
				locationNamesMap.put((Long)news[1],news[2].toString());
			}
			countVO = sourceMap.get((Long)news[3]);
			if(countVO == null){
			    countVO = new NewsAnalysisVO();
			    countVO.setLocationId((Long)news[1]);
			    countVO.setLocationLvl(vo.getLocationLvl());
			    countVO.setSourceId((Long)news[3]);
			    countVO.setName(news[4] != null?news[4].toString():"");
			    sourceMap.put((Long)news[3],countVO);
			}
			/*if(((Long)news[9]).intValue() == 1){
				countVO.setPositiveCount((Long)news[0]);
			}else if(((Long)news[9]).intValue() == 2){
				countVO.setNegativeCount((Long)news[0]);
			}else{
				countVO.setNeutralCount((Long)news[0]);
			}*/
			countVO.setTotal(countVO.getTotal()+(Long)news[0]);
	    }
	    Set<Long> locationKeys = newsCountsMap.keySet();
	    if(locationKeys.size() == 1 && locationKeys.contains(null)){
	    	
	    }else{
	    	levels.add("Location");
	    	returnVal.setSubListPresent(true);
	    }
	    List<NewsAnalysisVO> locationList = new ArrayList<NewsAnalysisVO>();
	    returnVal.setSubList(locationList);
	    for(Long key:locationKeys){
	    	NewsAnalysisVO location = new NewsAnalysisVO();
	    	location.setName(locationNamesMap.get(key));
	    	locationList.add(location);
	    	sourceMap = newsCountsMap.get(key);
	    	Set<Long> sourceKeys = sourceMap.keySet();
	    	if(sourceLvl){
	    		if(!(sourceKeys.size() == 1 && sourceKeys.contains(null))){
	    			levels.add("Source");
	    			sourceSubLvl = true;
	    	    }
	    		sourceLvl = false;
	    	}
	    	location.setSubListPresent(sourceSubLvl);
	    	location.setRowSpan(sourceMap.size());
	    	location.setSubList(new ArrayList<NewsAnalysisVO>(sourceMap.values()));	    	

	    }
	    
		return returnVal;
	}
	
	/*public NewsAnalysisVO getDataForSourceDestinationPresentIncludeCandidateParty(String query,AnalysisVO vo){
		NewsAnalysisVO returnVal = new NewsAnalysisVO();
		List<String> levels = new ArrayList<String>();
		returnVal.setLevels(levels);
		returnVal.setBuildMethod("third");
		Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>> newsCountsMap = new HashMap<Long,Map<Long,Map<Long,NewsAnalysisVO>>>();
		//  loc      cand     sour    
		
		Map<Long,Map<Long,NewsAnalysisVO>> candidateMap = null;
		Map<Long,NewsAnalysisVO> sourceMap = null;
		NewsAnalysisVO countVO = null;
		Map<Long,String> locationNamesMap = new HashMap<Long,String>();
		Map<Long,String> candidateNamesMap = new HashMap<Long,String>();
		boolean candidateLvl = true;
		boolean sourceLvl = true;
		boolean candidateSubLvl = false;
		boolean sourceSubLvl = false;
		
	    List<Object[]>	newsList = fileDAO.getNewsBySearchCriteria(query,vo);
		// 0 count 1 candidateId 2 candidateName 3 locationId 4 locationName 5 sourceId 6 sourceName  7 benifits
	    
	    for(Object[] news:newsList){
	    	candidateMap = newsCountsMap.get((Long)news[3]);
			if(candidateMap == null){
			    candidateMap = new HashMap<Long,Map<Long,NewsAnalysisVO>>();
				newsCountsMap.put((Long)news[3],candidateMap);
				if(news[4] != null)
				locationNamesMap.put((Long)news[3],news[4].toString());
			}
			
			sourceMap = candidateMap.get((Long)news[1]);
			if(sourceMap == null){
				sourceMap = new HashMap<Long,NewsAnalysisVO>();
				candidateMap.put((Long)news[1],sourceMap);
				if(news[2] != null)
				candidateNamesMap.put((Long)news[1],news[2].toString());
			}
			countVO = sourceMap.get((Long)news[5]);
			if(countVO == null){
			    countVO = new NewsAnalysisVO();
			    countVO.setLocationId((Long)news[3]);
			    countVO.setLocationLvl(vo.getLocationLvl());
			    countVO.setSourceId((Long)news[5]);
			    if(vo.isBySourceCand()){
			    	countVO.setSourceCandId((Long)news[1]);
			    }
			    if(vo.isByDestiCand()){
			    	countVO.setDestiCandId((Long)news[1]);
			    }
			    if(vo.isSourceCand()){
			    	countVO.setSourceCandId(vo.getSourceCandidateId());
				}else if(vo.isSourceParty()){
					countVO.setSourcePartyId(vo.getSourcePartyId());
				}
				if(vo.isDestiCand()){
					countVO.setDestiCandId(vo.getDestiCandidateId());
				}else if(vo.isDestiParty()){
					countVO.setDestiPartyId(vo.getDestiPartyId());
				}
			    countVO.setName(news[6] != null?news[6].toString():"");
			    sourceMap.put((Long)news[5],countVO);
			}
			if(((Long)news[9]).intValue() == 1){
				countVO.setPositiveCount((Long)news[0]);
			}else if(((Long)news[9]).intValue() == 2){
				countVO.setNegativeCount((Long)news[0]);
			}else{
				countVO.setNeutralCount((Long)news[0]);
			}
			countVO.setTotal(countVO.getTotal()+(Long)news[0]);
	    }
	   
	    Set<Long> locationKeys = newsCountsMap.keySet();
	    if(locationKeys.size() == 1 && locationKeys.contains(null)){
	    	
	    }else{
	    	levels.add("Location");
	    	returnVal.setSubListPresent(true);
	    }
	    List<NewsAnalysisVO> locationList = new ArrayList<NewsAnalysisVO>();
	    returnVal.setSubList(locationList);
	    for(Long key:locationKeys){
	    	NewsAnalysisVO location = new NewsAnalysisVO();
	    	location.setName(locationNamesMap.get(key));
	    	locationList.add(location);
	    	List<NewsAnalysisVO> candidateList = new ArrayList<NewsAnalysisVO>();
	    	location.setSubList(candidateList);
	    	candidateMap = newsCountsMap.get(key);
	    	Set<Long> candidateKeys = candidateMap.keySet();
	    	if(candidateLvl){
	    		if(!(candidateKeys.size() == 1 && candidateKeys.contains(null))){
	    			levels.add("Candidate");
	    			candidateSubLvl = true;	
	    	    }
	    		candidateLvl = false;
	    	}
	    	location.setSubListPresent(candidateSubLvl);
	    	for(Long candidateKey:candidateKeys){
	    		NewsAnalysisVO candidate = new NewsAnalysisVO();
	    		
	    		candidate.setName(candidateNamesMap.get(candidateKey));
	    		candidateList.add(candidate);
		    	
		    	
	    		sourceMap = candidateMap.get(candidateKey);
		    	Set<Long> sourceKeys = sourceMap.keySet();
		    	if(sourceLvl){
		    		if(!(sourceKeys.size() == 1 && sourceKeys.contains(null))){
		    			levels.add("Source");
		    			sourceSubLvl = true;
		    	    }
		    		sourceLvl = false;
		    	}
		    	candidate.setSubListPresent(sourceSubLvl);
		    	candidate.setRowSpan(candidate.getRowSpan()+sourceMap.size());
		    	location.setRowSpan(location.getRowSpan()+sourceMap.size());
		    	candidate.setSubList(new ArrayList<NewsAnalysisVO>(sourceMap.values()));
		    }
	    	
	    }
	    
		return returnVal;
	}*/
	public NewsAnalysisVO getDataForSourceDestinationPresentIncludeCandidateParty(String query,AnalysisVO vo,String sourceType,String destiType,boolean considerParty){
		NewsAnalysisVO returnVal = new NewsAnalysisVO();
		List<String> levels = new ArrayList<String>();
		returnVal.setLevels(levels);
		returnVal.setBuildMethod("third");
		Map<Long,Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>>> newsCountsMap = new HashMap<Long,Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>>>();
		//  loc   soucand   destcand   sour
		Map<Long,Map<Long,Map<Long,NewsAnalysisVO>>> souCandidateMap = null;
		Map<Long,Map<Long,NewsAnalysisVO>> candidateMap = null;
		Map<Long,NewsAnalysisVO> sourceMap = null;
		NewsAnalysisVO countVO = null;
		Map<Long,String> locationNamesMap = new HashMap<Long,String>();
		Map<Long,String> candidateNamesMap = new HashMap<Long,String>();
		//Map<Long,String> sourceNamesMap = new HashMap<Long,String>();
		
		boolean souCandidateLvl = true;
		boolean candidateLvl = true;
		boolean sourceLvl = true;
		boolean souCandidateSubLvl = false;
		boolean candidateSubLvl = false;
		boolean sourceSubLvl = false;
		
	    List<Object[]>	newsList = fileDAO.getNewsBySearchCriteria(query,vo);
	 // 0 count  1 source candidateId 2 source candidateName  3 candidateId 4 candidateName 5 locationId 6 locationName 7 sourceId 8 sourceName  9 benifits
	    if(newsList == null || newsList.size() == 0)
	    	return new NewsAnalysisVO();
	    for(Object[] news:newsList){
	    	souCandidateMap = newsCountsMap.get((Long)news[5]);
			if(souCandidateMap == null){
				souCandidateMap = new HashMap<Long,Map<Long,Map<Long,NewsAnalysisVO>>>();
				newsCountsMap.put((Long)news[5],souCandidateMap);
				if(news[6] != null)
				 locationNamesMap.put((Long)news[5],news[6].toString());
			}
			candidateMap = souCandidateMap.get((Long)news[1]);
			if(candidateMap == null){
				candidateMap = new HashMap<Long,Map<Long,NewsAnalysisVO>>();
				souCandidateMap.put((Long)news[1],candidateMap);
				if(news[2] != null)
				candidateNamesMap.put((Long)news[1],news[2].toString());
			}
			sourceMap = candidateMap.get((Long)news[3]);
			if(sourceMap == null){
				sourceMap = new HashMap<Long,NewsAnalysisVO>();
				candidateMap.put((Long)news[3],sourceMap);
				if(news[4] != null)
				 candidateNamesMap.put((Long)news[3],news[4].toString());
			}
			countVO = sourceMap.get((Long)news[7]);
			if(countVO == null){
			    countVO = new NewsAnalysisVO();
			    countVO.setLocationId((Long)news[5]);
			    countVO.setLocationLvl(vo.getLocationLvl());
			    countVO.setSourceId((Long)news[7]);
			    countVO.setSourceType(sourceType);
			    countVO.setDestiType(destiType);
			    if(considerParty){
				     countVO.setConsiderParty("true");
				    }else{
				    	countVO.setConsiderParty("false");
				    }
			    if(vo.isBySourceCand()){
			    	countVO.setSourceCandId((Long)news[1]);
			    }
			    if(vo.isByDestiCand()){
			    	countVO.setDestiCandId((Long)news[3]);
			    }
			    if(vo.isSourceCand()){
			    	countVO.setSourceCandId(vo.getSourceCandidateId());
				}else if(vo.isSourceParty()){
					countVO.setSourcePartyId(vo.getSourcePartyId());
				}
				if(vo.isDestiCand()){
					countVO.setDestiCandId(vo.getDestiCandidateId());
				}else if(vo.isDestiParty()){
					countVO.setDestiPartyId(vo.getDestiPartyId());
				}
				countVO.setSourceBenifitId(vo.getSourceBenifitId());
				countVO.setDestiBenifitId(vo.getDestiBenifitId());
			    countVO.setName(news[8] != null?news[8].toString():"");
			    sourceMap.put((Long)news[7],countVO);
			}
			/*if(((Long)news[9]).intValue() == 1){
				countVO.setPositiveCount((Long)news[0]);
			}else if(((Long)news[9]).intValue() == 2){
				countVO.setNegativeCount((Long)news[0]);
			}else{
				countVO.setNeutralCount((Long)news[0]);
			}*/
			countVO.setTotal(countVO.getTotal()+(Long)news[0]);
	    }
	    
	    Set<Long> locationKeys = newsCountsMap.keySet();
	    if(locationKeys.size() == 1 && locationKeys.contains(null)){
	    	
	    }else{
	    	levels.add("Location");
	    	returnVal.setSubListPresent(true);
	    }
	    List<NewsAnalysisVO> locationList = new ArrayList<NewsAnalysisVO>();
	    returnVal.setSubList(locationList);
	    for(Long key:locationKeys){
	    	NewsAnalysisVO location = new NewsAnalysisVO();	    	
	    	location.setName(locationNamesMap.get(key));
	    	locationList.add(location);
	    	List<NewsAnalysisVO> souCandidateList = new ArrayList<NewsAnalysisVO>();
	    	location.setSubList(souCandidateList);
	    	souCandidateMap = newsCountsMap.get(key);
	    	Set<Long> souCandidateKeys = souCandidateMap.keySet();
	    	if(souCandidateLvl){
	    		if(!(souCandidateKeys.size() == 1 && souCandidateKeys.contains(null))){
	    			levels.add("Who");
	    			souCandidateSubLvl = true;
	    	    }
	    		souCandidateLvl = false;
	    	}
	    	location.setSubListPresent(souCandidateSubLvl);
	    	for(Long souCandidateKey:souCandidateKeys){
	    		
	    		NewsAnalysisVO souCandidate = new NewsAnalysisVO();
	    		souCandidate.setName(candidateNamesMap.get(souCandidateKey));
	    		souCandidateList.add(souCandidate);
		    	List<NewsAnalysisVO> candidateList = new ArrayList<NewsAnalysisVO>();
		    	souCandidate.setSubList(candidateList);
		    	candidateMap = souCandidateMap.get(souCandidateKey);
		    	Set<Long> candidateKeys = candidateMap.keySet();
		    	if(candidateLvl){
		    		if(!(candidateKeys.size() == 1 && candidateKeys.contains(null))){
		    			levels.add("Whome");
		    			candidateSubLvl = true;	
		    	    }
		    		candidateLvl = false;
		    	}
		    	souCandidate.setSubListPresent(candidateSubLvl);
		    	for(Long candidateKey:candidateKeys){
		    		NewsAnalysisVO candidate = new NewsAnalysisVO();
		    		
		    		candidate.setName(candidateNamesMap.get(candidateKey));
		    		candidateList.add(candidate);
			    	
			    	
		    		sourceMap = candidateMap.get(candidateKey);
			    	Set<Long> sourceKeys = sourceMap.keySet();
			    	if(sourceLvl){
			    		if(!(sourceKeys.size() == 1 && sourceKeys.contains(null))){
			    			levels.add("Source");
			    			sourceSubLvl = true;
			    	    }
			    		sourceLvl = false;
			    	}
			    	candidate.setSubListPresent(sourceSubLvl);
			    	candidate.setRowSpan(candidate.getRowSpan()+sourceMap.size());
			    	location.setRowSpan(location.getRowSpan()+sourceMap.size());
			    	souCandidate.setRowSpan(souCandidate.getRowSpan()+sourceMap.size());
			    	candidate.setSubList(new ArrayList<NewsAnalysisVO>(sourceMap.values()));
			    }
		    }
	    	
	    }
	    
		return returnVal;
	}
	public List<FileVO> getSelectedNews(NewsAnalysisVO vo,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex){
	  try{
		  LinkedHashMap<Long,FileVO> fileMap = null;
		 if(vo.getName().equalsIgnoreCase("2"))
		 {
			 StringBuilder queryCount = new StringBuilder();
			 StringBuilder queryData  = new StringBuilder();
			 queryData.append("select distinct  model.file.fileTitle ,model.file.fileDescription ," +
						" model.file.fileDate ,model.file.filePath ,model.file.fileId ,model.file.font.fontId,model.file.descFont.fontId  " +
						" from  CandidatePartyFile model  where model.file.isDeleted != 'Y' and model.sourceCandidate.candidateId = "+vo.getSourceCandId()+" " +
								" and model.destinationCandidate.candidateId = "+vo.getDestiCandId()+" and " +
								" model.sourceParty.partyId = "+vo.getSourcePartyId()+" and " +
								" model.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
			 if(fromDate != null){
				 queryData.append(" and model.file.fileDate >=:fromDate  ");
				}
				if(toDate != null){
					queryData.append(" and model.file.fileDate <=:toDate  ");
				}
			 System.out.println(queryData);
			 queryCount.append("select  count(distinct model.file.fileId)   " +
						" from  CandidatePartyFile model  where model.file.isDeleted != 'Y' and model.sourceCandidate.candidateId = "+vo.getSourceCandId()+" " +
								" and model.destinationCandidate.candidateId = "+vo.getDestiCandId()+" and " +
								" model.sourceParty.partyId = "+vo.getSourcePartyId()+" and " +
								" model.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
			 if(fromDate != null){
				 queryCount.append(" and model.file.fileDate >=:fromDate  ");
				}
				if(toDate != null){
					queryCount.append(" and model.file.fileDate <=:toDate  ");
				}
			System.out.println(queryCount);
			List<Long> fileIds = new ArrayList<Long>();
			fileMap = new LinkedHashMap<Long, FileVO>();
			List<Object[]> files = candidatePartyFileDAO.getSelectedNewsBySearchCriteria(queryData.toString(),fromDate,toDate, startIndex, maxIndex);
			Long count = candidatePartyFileDAO.getSelectedNewsCountBySearchCriteria(queryCount.toString(),fromDate,toDate);
			candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, count);
		 }
		 else
		 {
			 StringBuilder query = new StringBuilder();
				StringBuilder queryCount = new StringBuilder();
				StringBuilder queryData  = new StringBuilder();
				StringBuilder cpfQuery   = new StringBuilder();
				StringBuilder fslQuery   = new StringBuilder();
				StringBuilder cpcQuery   = new StringBuilder();
				StringBuilder cpkQuery   = new StringBuilder();
				queryData.append("select distinct  model.fileTitle ,model.fileDescription ," +
						" model.fileDate ,model.filePath ,model.fileId ,model.font.fontId,model.descFont.fontId  " +
						" from File model  ");		
				queryCount.append("select  count(distinct model.fileId) from File model ");
				if((vo.getSourceCandId()!= null && vo.getSourceCandId() > 0) || (vo.getDestiCandId() != null && vo.getDestiCandId() > 0) || (vo.getSourcePartyId() != null && vo.getSourcePartyId() > 0 ) || (vo.getDestiPartyId() != null && vo.getDestiPartyId() >0 )){
					query.append(",CandidatePartyFile cpf ");
					cpfQuery.append(" and model.fileId = cpf.file.fileId  ");
					if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
						cpfQuery.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
					}
		             if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		            	 cpfQuery.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
					}
				}
				if(vo.getSourceId() != null && vo.getSourceId() > 0){
					query.append(",FileSourceLanguage fsl ");
					fslQuery.append(" and model.fileId = fsl.file.fileId  ");
				}
				if(vo.getCategoryId() != null && vo.getCategoryId() > 0){
					query.append(",CandidatePartyCategory cpc ");
					cpcQuery.append(" and model.fileId = cpc.candidatePartyFile.file.fileId  ");
					if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
						cpcQuery.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
					}
		             if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		            	 cpcQuery.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
					}
				}
				if(vo.getKeywordId() != null && vo.getKeywordId() > 0){
					query.append(",CandidatePartyKeyword cpk ");
					cpkQuery.append(" and model.fileId = cpk.candidatePartyFile.file.fileId  ");
					if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
						cpkQuery.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
					}
		             if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		            	 cpkQuery.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
					}
				}
				query.append(" where model.isDeleted != 'Y'  ");
				query.append(cpfQuery);
				query.append(fslQuery);
				query.append(cpcQuery);
				query.append(cpkQuery);
				if(vo.getConsiderParty() != null && vo.getConsiderParty().equalsIgnoreCase("true")){
				  if(vo.getSourceType() == null || vo.getSourceType().equalsIgnoreCase("null")){
					if(vo.getSourceCandId()!= null && vo.getSourceCandId() > 0){
						query.append(" and cpf.sourceCandidate.candidateId ="+vo.getSourceCandId()+" ");
					}
					if(vo.getSourcePartyId()!= null && vo.getSourcePartyId() > 0){
						query.append(" and cpf.sourceParty.partyId ="+vo.getSourcePartyId()+" ");
					}
				  }else if(vo.getSourceType().equalsIgnoreCase("party")){
					  query.append(" and cpf.sourceCandidate.candidateId is null ");
					  query.append(" and cpf.sourceParty.partyId ="+vo.getSourceCandId()+" ");
				  }else if(vo.getSourceType().equalsIgnoreCase("no")){
					  query.append(" and cpf.sourceCandidate.candidateId is null ");
					  query.append(" and cpf.sourceParty.partyId is null  ");
				  }else if(vo.getSourceType().equalsIgnoreCase("noSource")){
					  if(vo.getSourceCandId() != null && vo.getSourceCandId() > 0){
						  query.append(" and cpf.sourceCandidate.candidateId ="+vo.getSourceCandId()+" ");
					  }
					  if(vo.getSourcePartyId()!= null && vo.getSourcePartyId() > 0){
							query.append(" and cpf.sourceParty.partyId ="+vo.getSourcePartyId()+" ");
					  }
				  }
				  if(vo.getDestiType() == null || vo.getDestiType().equalsIgnoreCase("null")){
						if(vo.getDestiCandId()!= null && vo.getDestiCandId() > 0){
							query.append(" and cpf.destinationCandidate.candidateId ="+vo.getDestiCandId()+" ");
						}
						if(vo.getDestiPartyId()!= null && vo.getDestiPartyId() > 0){
							query.append(" and cpf.destinationParty.partyId ="+vo.getDestiPartyId()+" ");
						}
				  }else if(vo.getDestiType().equalsIgnoreCase("party")){
					  query.append(" and cpf.destinationCandidate.candidateId is null ");
					  query.append(" and cpf.destinationParty.partyId ="+vo.getDestiCandId()+" ");
				  }else if(vo.getDestiType().equalsIgnoreCase("no")){
					  query.append(" and cpf.destinationCandidate.candidateId is null ");
					  query.append(" and cpf.destinationParty.partyId is null  ");
				  }else if(vo.getDestiType().equalsIgnoreCase("noDesti")){
				    if(vo.getDestiCandId()!= null && vo.getDestiCandId() > 0){
						query.append(" and cpf.destinationCandidate.candidateId ="+vo.getDestiCandId()+" ");
					}
					if(vo.getDestiPartyId()!= null && vo.getDestiPartyId() > 0){
						query.append(" and cpf.destinationParty.partyId ="+vo.getDestiPartyId()+" ");
					}
				  }
				}else{
					if(vo.getSourceCandId()!= null && vo.getSourceCandId() > 0){
						query.append(" and cpf.sourceCandidate.candidateId ="+vo.getSourceCandId()+" ");
					}
					if(vo.getDestiCandId()!= null && vo.getDestiCandId() > 0){
						query.append(" and cpf.destinationCandidate.candidateId ="+vo.getDestiCandId()+" ");
					}
					if(vo.getSourcePartyId()!= null && vo.getSourcePartyId() > 0){
						query.append(" and cpf.sourceParty.partyId ="+vo.getSourcePartyId()+" ");
					}
					if(vo.getDestiPartyId()!= null && vo.getDestiPartyId() > 0){
						query.append(" and cpf.destinationParty.partyId ="+vo.getDestiPartyId()+" ");
					}
				}
				if(vo.getLocationLvl()!= null && vo.getLocationLvl() > 0 && vo.getLocationId()!= null && vo.getLocationId() > 0){
					if(vo.getLocationLvl() == 1){
						query.append(" and model.userAddress.district.districtId ="+vo.getLocationId()+" ");
					}else if(vo.getLocationLvl() == 2){
						query.append(" and model.userAddress.parliamentConstituency.constituencyId ="+vo.getLocationId()+" ");
					}else if(vo.getLocationLvl() == 3){
						query.append(" and model.userAddress.constituency.constituencyId ="+vo.getLocationId()+" ");
					}
				}
				if(vo.getSourceId() != null && vo.getSourceId() > 0){
					query.append(" and fsl.source.sourceId ="+vo.getSourceId()+" ");
					
				}
				if(vo.getCategoryId() != null && vo.getCategoryId() > 0){
					query.append(" and cpc.gallary.gallaryId ="+vo.getCategoryId()+"  ");
					
				}
				if(vo.getKeywordId() != null && vo.getKeywordId() > 0){
					query.append(" and cpk.keyword.keywordId ="+vo.getKeywordId()+"  ");
					
				}
				if(fromDate != null){
					query.append(" and model.fileDate >=:fromDate  ");
				}
				if(toDate != null){
					query.append(" and model.fileDate <=:toDate  ");
				}
				query.append(" order by model.fileDate desc,model.updatedDate desc");
				queryData.append(query);
				List<Long> fileIds = new ArrayList<Long>();
				fileMap = new LinkedHashMap<Long, FileVO>();
				List<Object[]> files = fileDAO.getSelectedNewsBySearchCriteria(queryData.toString(), fromDate, toDate, startIndex, maxIndex);
				Long count = fileDAO.getSelectedNewsCountBySearchCriteria(queryCount.append(query).toString(), fromDate, toDate);
				candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, count);
		 }
		
		
		return new ArrayList<FileVO>(fileMap.values());
	  }catch(Exception e){
		  LOG.error("exception rised in getSelectedNews ",e);
		  return new ArrayList<FileVO>();
	  }
	}
	
	public ResultStatus saveDesignationDetails(final String designationString)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try {
			LOG.debug("Entered into saveDesignationDetails method in NewsAnalysisService service");
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					Long designationId = designationDAO.getDesignation(designationString);
					if(designationId == null)
					{
						Designation designation = new Designation();
						designation.setDesignation(designationString);
						designation = designationDAO.save(designation);
						if(designation != null)
						{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
						
					}
					else
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
					
					
				}
			});
		} catch (Exception e) {
			LOG.error("exception raised in saveDesignationDetails method in NewsAnalysisService service");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		
		return resultStatus;
	}
	
	public ResultStatus savePartyDetails(final String partyShortName,final String PartyLongName)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try {
			LOG.debug("Entered into savePartyDetails method in NewsAnalysisService service");
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@SuppressWarnings("unused")
				public void doInTransactionWithoutResult(TransactionStatus status) {
					Long partyId = partyDAO.getPartyShortName(partyShortName);
					if(partyId == null)
					{
						Party party = new Party();
						party.setLongName(PartyLongName);
						party.setShortName(partyShortName);
						party.setIsNewsPortal("Y");
						partyDAO.save(party);
						if(party != null)
						{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
						
					}
					else
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
					
				}
			});
		} catch (Exception e) {
			LOG.error("exception raised in savePartyDetails method in NewsAnalysisService service");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		
		return resultStatus;
	}
	/**
	 * Tis Service is used for building the ategoery wise news details in constituency level
	 * @param categIds
	 * @param constituencyIds
	 * @param fromDateStr
	 * @param toDateStr
	 * @param startIndex
	 * @param maxIndex
	 * @return List<SelectOptionVO>
	 * @Date 18-12-2013
	 */
	public List<SelectOptionVO> getProgramsWiseNews(List<Long> categIds, List<Long> constituencyIds,String fromDateStr , String toDateStr ,Long startIndex,Long maxIndex,final Long partyid,final Long userId,String url,String requestType){
		List<SelectOptionVO> returnList = null;
		try {
			LOG.info("Entered into getProgramsWiseNews method in NewsAnalysisService service");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate   = null; 
			if(fromDateStr != null && fromDateStr.trim().length() > 0)
			{
				 fromDate = format.parse(fromDateStr);
			}
			if(toDateStr != null && toDateStr.trim().length() > 0)
			{
				toDate   = format.parse(toDateStr);
			}

			List<Object[]> count = candidatePartyCategoryDAO.getCategoeryAndConsttituencyWiseTotalCount(categIds,constituencyIds,fromDate,toDate,partyid);
			List<Object[]> catgList = candidatePartyCategoryDAO.getCategoeryAndConsttituencyWiseNews(categIds,constituencyIds,fromDate,toDate,startIndex.intValue(),maxIndex.intValue(),partyid);
			if(catgList != null && catgList.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : catgList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setValue(parms[0] != null ? parms[0].toString() :"");
					selectOptionVO.setName(parms[1] != null ? StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(parms[1].toString())) :"");
					selectOptionVO.setType(parms[2] != null ? parms[2].toString() :"");
					selectOptionVO.setLocation(parms[3] != null ? parms[3].toString() :"");
					selectOptionVO.setId(parms[4] != null ? new Long(parms[4].toString()) :0l);
					if(count != null)
					selectOptionVO.setOrderId(Long.valueOf(count.size()));
					else
					 selectOptionVO.setOrderId(0l);
					returnList.add(selectOptionVO);
					
					
				}
				final List<Long> fileIdsList =  candidatePartyCategoryDAO.getCategoeryAndConsttituencyWiseNewsIds(categIds, constituencyIds, fromDate, toDate, partyid);
				if(fileIdsList.size() > 0 && requestType.equalsIgnoreCase("initial")){
					String categoryIds = "";
					for(Long id:categIds){
						categoryIds = categoryIds+","+id;
					}
					if(categoryIds.length() > 0){
						categoryIds = categoryIds.substring(1);
					}
					final String categoryString = categoryIds;
					final String key = UUID.randomUUID().toString();
					url = url+"key="+key;
					returnList.get(0).setPartno(url);
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						public void doInTransactionWithoutResult(TransactionStatus status) {
							DateUtilService dateService = new DateUtilService();
							ActivityReport activityReport = new ActivityReport();
							activityReport.setUser(userDAO.get(userId));
							activityReport.setCreatedDate(dateService.getCurrentDateAndTime());
							activityReport.setReportKey(key);
							activityReport.setParty(partyDAO.get(partyid));
							activityReport.setCategories(categoryString);
							activityReport = activityReportDAO.save(activityReport);
							for(Long fileId:fileIdsList){
								ActivityReportFiles activityReportFiles = new ActivityReportFiles();
								activityReportFiles.setActivityReport(activityReport);
								activityReportFiles.setFile(fileDAO.get(fileId));
								activityReportFilesDAO.save(activityReportFiles);
							}
						}
					});
				}
			}
		} catch (Exception e) {
			LOG.error("exception raised in getProgramsWiseNews method in NewsAnalysisService service",e);
		}
		return returnList;
	}
	/**
	 * This service is used for building the categoery wise count details for selectd constituency and district in News Activites
	 * @param categIds
	 * @param constituencyIds
	 * @param fromDateStr
	 * @param toDateStr
	 * @param type
	 * @param districtIds
	 * @return List<SelectOptionVO> returnList
	 * @Date 18-12-2013
	 */
	public List<SelectOptionVO> getCategoeryWiseCountDetails(List<Long> categIds, List<Long> constituencyIds,String fromDateStr , String toDateStr,String type ,List<Long> districtIds,Long partyId)
	{
		List<SelectOptionVO> returnList = null;
		try {
			LOG.info("Entered into getCategoeryWiseCountDetails method in NewsAnalysisService service");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate   = null; 
			if(fromDateStr != null && fromDateStr.trim().length() > 0)
			{
				 fromDate = format.parse(fromDateStr);
			}
			if(toDateStr != null && toDateStr.trim().length() > 0)
			{
				toDate   = format.parse(toDateStr);
			}
			Map<Long,Map<Long,Long>> constituencyWiseCountMap = null;//Map<constituencyId,Map<galleryId,count>
			Map<Long,Long> countMap = null;//Map<galleryId,count>
			Map<Long,String> constiMap = null;//Map<constituencyId,constituencyName>
			Map<Long,String> catgeMap = null;//Map<galleyId,galleryName>
			Map<Long,Long> categCountMap  = null ;//Map<categoeryId,totalCount>
			List<Object[]> categCountList = null;
			List<SelectOptionVO> countList = null;
			SelectOptionVO selectOptionVO1 = null;
			if(type.equalsIgnoreCase("district"))
			{
				categCountList = candidatePartyCategoryDAO.getCategoeryAndDisrictWiseCount(categIds,districtIds,fromDate,toDate,partyId);
			}
			else
			{
				categCountList = candidatePartyCategoryDAO.getCategoeryAndConsttituencyWiseCount(categIds,constituencyIds,fromDate,toDate,partyId);
			}
			
			if(categCountList != null && categCountList.size() > 0)
			{
				constituencyWiseCountMap = new HashMap<Long, Map<Long,Long>>();
				constiMap = new HashMap<Long, String>();
				catgeMap = new HashMap<Long, String>();
				List<Object[]> galleryList = gallaryDAO.getGalleriesForIds(categIds);
				if(galleryList != null && galleryList.size() > 0)
				{
					for (Object[] parms : galleryList) {
						String catgName = catgeMap.get((Long)parms[0]);
						if(catgName == null)
						{
							catgeMap.put((Long)parms[0], parms[1].toString());
						}
					}
					
				}
				for (Object[] parms : categCountList) {
					countMap = constituencyWiseCountMap.get((Long)parms[3]);
					if(countMap == null)
					{
						countMap = new HashMap<Long, Long>();
						constituencyWiseCountMap.put((Long)parms[3], countMap);
						constiMap.put((Long)parms[3], parms[4].toString());
					}
					countMap.put((Long)parms[2], (Long)parms[1]);
					
				}
				
				Set<Long> constiIds = constituencyWiseCountMap.keySet();
				Set<Long> cateoeryIds = new HashSet<Long>(categIds);
				if(constiIds != null && constiIds.size() > 0)
				{
					returnList = new ArrayList<SelectOptionVO>();
					categCountMap = new HashMap<Long, Long>();
					for (Long constituencyId : constiIds) {
						selectOptionVO1 = new SelectOptionVO();
						selectOptionVO1.setLocation(constiMap.get(constituencyId));
						List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
						Map<Long,Long> catgWistCountMap = constituencyWiseCountMap.get(constituencyId);
						for (Long catgId : cateoeryIds) {
							SelectOptionVO selectOptionVO = new SelectOptionVO();
							Long count = catgWistCountMap.get(catgId);
							Long count1 = categCountMap.get(catgId);
							if(count == null)
							{
								count = 0l;
								selectOptionVO.setId(count);
							}
							else
							{
								selectOptionVO.setId(count);
							}
							if(count1 == null)
							{
								categCountMap.put(catgId, count);
							}
							else
							{
								categCountMap.put(catgId, categCountMap.get(catgId)+count);
							}
							selectOptionVO.setName(catgeMap.get(catgId));
							list.add(selectOptionVO);
						}
						selectOptionVO1.setSelectOptionsList(list);
						returnList.add(selectOptionVO1);
					}
					if(type.equalsIgnoreCase("constituency"))
					{
						Set<Long> catgSet = categCountMap.keySet();
						if(catgSet != null && catgSet.size() > 0)
						{
							selectOptionVO1 = new SelectOptionVO();
							selectOptionVO1.setLocation("Total");
							countList = new ArrayList<SelectOptionVO>();
							for (Long catgId : cateoeryIds)
							{
								SelectOptionVO selectOptionVO = new SelectOptionVO();
								selectOptionVO.setName(catgeMap.get(catgId));
								selectOptionVO.setId(categCountMap.get(catgId));
								countList.add(selectOptionVO);
								selectOptionVO1.setSelectOptionsList(countList);
							}
						}
						returnList.add(selectOptionVO1);
					}
					
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("exception raised in getCategoeryWiseCountDetails method in NewsAnalysisService service",e);
		}
		return returnList;
	}
	/**
	 * This service is used for build the pdf or excel generation logic
	 * @param catgIds
	 * @param constiIds
	 * @param districtIds
	 * @param fromDateStr
	 * @param toDateStr
	 * @param type
	 * @param path
	 * @return List<SelectOptionVO> returnList
	 * @Date 18-12-2013
	 */
	public List<SelectOptionVO> generatePdfOrExcel(List<Long> catgIds,List<Long> constiIds,List<Long> districtIds,String fromDateStr,String toDateStr,String type,String path,Long partyId)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOptionVO = new SelectOptionVO();
		FileOutputStream fileOut = null;
		Random randomNum = new Random();

		try {
			LOG.info("Entered into generatePdfOrExcel method in NewsAnalysisService service");
			if(type.equalsIgnoreCase("pdf"))
			{
				 Document document = new Document();
				 String filePath = "Reports"+"/"+"report"+randomNum.nextInt(100000)+".pdf";
				 		
				    String FILE = path+filePath;
				    File file  = new File(FILE);
				    file.createNewFile();
				    selectOptionVO.setUrl(filePath);
				  	try {
				  		PdfWriter.getInstance(document, new FileOutputStream(FILE));
				  	} catch (FileNotFoundException e) {
				  		e.printStackTrace();
				  	} catch (DocumentException e) {
				  		e.printStackTrace();
				  	}
				  	document.open();
				  	addTitlePage(document);
				List<SelectOptionVO> list1 = getCategoeryWiseCountDetails( catgIds,  constiIds, fromDateStr ,  toDateStr, "district" , districtIds,partyId);
				if(list1 != null && list1.size() > 0)
				{
					pdfGeneration(list1,document,"District");
				}
				List<SelectOptionVO> list2 = getCategoeryWiseCountDetails( catgIds,  constiIds, fromDateStr ,  toDateStr, "constituency" , districtIds,partyId);
				if(list2 != null && list2.size() > 0)
				{
					pdfGeneration(list2,document,"Constituency");
				}
				document.close();
				selectOptionVO.setName("success");
			}
			/*else
			{
				String filename= "Reports"+"/"+"report.xls";
				String FILE = path+filename;
				File file  = new File(FILE);
				file.createNewFile();
				HSSFWorkbook workbook=new HSSFWorkbook();
				HSSFSheet sheet =  workbook.createSheet("District"); 
				fileOut =  new FileOutputStream(FILE);
				selectOptionVO.setUrl(filename);
				List<SelectOptionVO> list1 =  getCategoeryWiseCountDetails( catgIds,  constiIds, fromDateStr ,  toDateStr, "district" , districtIds);
				List<SelectOptionVO> list2 = getCategoeryWiseCountDetails( catgIds,  constiIds, fromDateStr ,  toDateStr, "constituency" , districtIds);
				if(list1 != null && list1.size() > 0 || list2 != null && list2.size() > 0)
				{
					generateXl(list1,"District",FILE,workbook,sheet,fileOut,list2);
				}
				workbook.write(fileOut);
				selectOptionVO.setName("success");
			}*/
			else
			{
				
				String filename= "Reports"+"/"+"report"+randomNum.nextInt(100000)+".xls";
				String FILE = path+filename;
				File file  = new File(FILE);
				file.createNewFile();
				HSSFWorkbook workbook=new HSSFWorkbook();
				selectOptionVO.setUrl(filename);
				List<SelectOptionVO> list1 =  getCategoeryWiseCountDetails( catgIds,  constiIds, fromDateStr ,  toDateStr, "district" , districtIds,partyId);
				if(list1 != null && list1.size() > 0)
				{
					
			        HSSFSheet sheet =  workbook.createSheet("District"); 
			        fileOut =  new FileOutputStream(FILE);
					generateXl(list1,"District",FILE,workbook,sheet,fileOut);
					workbook.write(fileOut);
				}
				List<SelectOptionVO> list2 = getCategoeryWiseCountDetails( catgIds,  constiIds, fromDateStr ,  toDateStr, "constituency" , districtIds,partyId);
				if(list2 != null && list2.size() > 0)
				{
			        HSSFSheet sheet =  workbook.createSheet("Constituency"); 
			        fileOut =  new FileOutputStream(FILE);
					generateXl(list2,"Constituency",FILE,workbook,sheet,fileOut);
					workbook.write(fileOut);
				}
				selectOptionVO.setName("success");
			}
			
			
		} catch (Exception e) {
			selectOptionVO.setName("fail");
			LOG.error("exception raised in generatePdfOrExcel method in NewsAnalysisService service",e);
		}
		finally
		{
			if(fileOut != null)
			{
				try {
					fileOut.close();
				} catch (IOException e) {
				}
			}
			
		}
		returnList.add(selectOptionVO);
		return returnList;
	}
	/**
	 * This service is used for building the pdf
	 * @param list
	 * @param document
	 * @param type
	 * @Date 18-12-2013
	 */
	public void pdfGeneration(List<SelectOptionVO> list,Document document,String type)
	{		
		  try
		  {
		        LOG.info("Enterd into pdfGeneration() method in NewsAnalysisService Class");
		        List<SelectOptionVO> catgList = list.get(0).getSelectOptionsList();
		        int length = catgList.size();
		        PdfPTable table = new PdfPTable(length+1);
		        document.add( new Paragraph(" ") );
		        document.add( new Paragraph(" ") );
			  	
			  	PdfPCell cell;
			  			  	
			  	cell = new PdfPCell(new Phrase(type,BIGFONT));
			  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  	cell.setBackgroundColor(BaseColor.YELLOW);
			  	table.addCell(cell);
			  	for (SelectOptionVO selectOptionVO : catgList)
			  	{
			  		cell = new PdfPCell(new Phrase(selectOptionVO.getName(),BIGFONT));
				  	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	cell.setBackgroundColor(BaseColor.YELLOW);
				  	table.addCell(cell);
				}
			 
			  	for (SelectOptionVO selectOptionVO : list) {
			  		
			  		PdfPCell c2 = new PdfPCell(new Phrase(selectOptionVO.getLocation().toString(),SMALLFONT));
			  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c2);
					List<SelectOptionVO> catgsList = selectOptionVO.getSelectOptionsList();
					for (SelectOptionVO selectOptionVO2 : catgsList) {
						c2 = new PdfPCell(new Phrase(selectOptionVO2.getId().toString(),SMALLFONT));
				  		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(c2);
					}
		  			
			  		
				}	
			  	/*List<Float> widthsList = new ArrayList<Float>();
			  	for (int i = 0; i < length+1 ; i++) {
			  		widthsList.add(1.0f);
				}
			  	float[] array = new float[widthsList.size()];
		 	  	table.setWidths(array);*/
			  	document.add(table);
			  	
		  }
		  catch (Exception e)
		  {
			LOG.debug("Exception raised in pdfGeneration() method in NewsAnalysisService Class",e);
		  }

	}
	
	@SuppressWarnings("deprecation")
	/*public void generateXl(List<SelectOptionVO> list,String type,String filename,HSSFWorkbook workbook,HSSFSheet sheet,FileOutputStream fileOut,List<SelectOptionVO> list1)
	{
		try {
			LOG.info("Enterd into generateXl() method in NewsAnalysisService Class"); 
	        List<SelectOptionVO> catgList = list.get(0).getSelectOptionsList();
	        HSSFRow rowhead=   sheet.createRow((short)0);
	        rowhead.createCell((short) 0).setCellValue(type);
	        int count = 0;
	        for (SelectOptionVO selectOptionVO : catgList) {
	        	count ++;
	        	rowhead.createCell((short)count).setCellValue(selectOptionVO.getName());
			}
	        
	        int count2 = 0;
	        HSSFRow row;
	        for (SelectOptionVO selectOptionVO : list) {
	        	count2++;
	        	int count1 = 0;
	        	row =   sheet.createRow((short)count2);
	        	row.createCell((short) 0).setCellValue(selectOptionVO.getLocation());
	        	List<SelectOptionVO> list2 = selectOptionVO.getSelectOptionsList();
	        	for (SelectOptionVO selectOptionVO2 : list2)
	        	{
	        		count1++;
	        		row.createCell((short) count1).setCellValue(selectOptionVO2.getId());
				}
			}
	        
	        List<SelectOptionVO> catgList1 = list1.get(0).getSelectOptionsList();
	        HSSFRow rowhead1=   sheet.createRow((short)0);
	        rowhead1.createCell((short) 0).setCellValue(type);
	        int count12 = 0;
	        for (SelectOptionVO selectOptionVO : catgList1) {
	        	count12++;
	        	rowhead.createCell((short)count12).setCellValue(selectOptionVO.getName());
			}
	        
	        int count21 = 0;
	        HSSFRow row1;
	        for (SelectOptionVO selectOptionVO : list1) {
	        	count21++;
	        	int count13 = 0;
	        	row1 =   sheet.createRow((short)count21);
	        	row1.createCell((short) 0).setCellValue(selectOptionVO.getLocation());
	        	List<SelectOptionVO> list2 = selectOptionVO.getSelectOptionsList();
	        	for (SelectOptionVO selectOptionVO2 : list2)
	        	{
	        		count13++;
	        		row1.createCell((short) count13).setCellValue(selectOptionVO2.getId());
				}
			}
	        
	        
	        
		} catch (Exception e) {
			LOG.debug("Exception raised in generateXl() method in NewsAnalysisService Class",e);
		}
	}*/
	/**
	 * This service is used for generating xl
	 * @param list
	 * @param type
	 * @param filename
	 * @param workbook
	 * @param sheet
	 * @param fileOut
	 * @Date 18-12-2013
	 */
	public void generateXl(List<SelectOptionVO> list,String type,String filename,HSSFWorkbook workbook,HSSFSheet sheet,FileOutputStream fileOut)
	{
		try {
			LOG.info("Enterd into generateXl() method in NewsAnalysisService Class"); 
	        List<SelectOptionVO> catgList = list.get(0).getSelectOptionsList();
	        HSSFRow rowhead=   sheet.createRow((short)0);
	        rowhead.createCell((short) 0).setCellValue(type);
	        int count = 0;
	        for (SelectOptionVO selectOptionVO : catgList) {
	        	count ++;
	        	rowhead.createCell((short)count).setCellValue(selectOptionVO.getName());
			}
	        
	        int count2 = 0;
	        HSSFRow row;
	        for (SelectOptionVO selectOptionVO : list) {
	        	count2++;
	        	int count1 = 0;
	        	row =   sheet.createRow((short)count2);
	        	row.createCell((short) 0).setCellValue(selectOptionVO.getLocation());
	        	List<SelectOptionVO> list2 = selectOptionVO.getSelectOptionsList();
	        	for (SelectOptionVO selectOptionVO2 : list2)
	        	{
	        		count1++;
	        		row.createCell((short) count1).setCellValue(selectOptionVO2.getId());
				}
			}      
	        
		} catch (Exception e) {
			LOG.debug("Exception raised in generateXl() method in NewsAnalysisService Class",e);
		}
	}
	private  void addTitlePage(Document document) throws DocumentException
	{
	    Paragraph preface = new Paragraph();
	    preface.setAlignment(Element.ALIGN_CENTER);
	    preface.add(new Paragraph("Abstract" , catFont));
	    document.add(preface); 
    }
      
	public List<SelectOptionVO> getConstituencyesList(List<Long> districtIds)
	{
		List<SelectOptionVO> returnList = null;
		try {
			LOG.info("Entered into getConstituencyesList method in NewsAnalysisService service");
			List<Object[]> constituenccyesList = constituencyDAO.getConstituencyes(districtIds);
			if(constituenccyesList != null && constituenccyesList.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				for (Object[] parms : constituenccyesList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1] != null ? parms[1].toString():"");
					returnList.add(selectOptionVO);
				}
			}
		} catch (Exception e) {
			LOG.error("exception raised in getConstituencyesList method in NewsAnalysisService service",e);
		}
		return returnList;
	}
	
	public NewsAnalysisVO getResults(AnalysisVO vo)
	{
		NewsAnalysisVO returnVal = new NewsAnalysisVO();
		if(vo.getSourceCandidateId() != null || vo.getDestiCandidateId() != null)
		{
			NewsAnalysisVO returnVal1 = getSourceDestinationCategoeryPresentQueryForParty( vo,null,0l,1l);
			NewsAnalysisVO returnVal2 = getSourceDestinationCategoeryPresentQueryForParty( vo,null,0l,2l);
			NewsAnalysisVO returnVal3 = getSourceDestinationCategoeryPresentQueryForParty( vo,null,0l,3l);
			populateToVO(returnVal1,returnVal);
			populateToVO(returnVal2,returnVal);
			populateToVO(returnVal3,returnVal);
		}
		else
		{
			NewsAnalysisVO returnVal1 = getSourceDestinationCategoeryPresentQueryForParty( vo,0l,null,1l);
			NewsAnalysisVO returnVal2 = getSourceDestinationCategoeryPresentQueryForParty( vo,0l,null,2l);
			NewsAnalysisVO returnVal3 = getSourceDestinationCategoeryPresentQueryForParty( vo,0l,null,3l);
			NewsAnalysisVO returnVal4 = getSourceDestinationCategoeryPresentQueryForParty( vo,null,0l,1l);
			NewsAnalysisVO returnVal5 = getSourceDestinationCategoeryPresentQueryForParty( vo,null,0l,2l);
			NewsAnalysisVO returnVal6 = getSourceDestinationCategoeryPresentQueryForParty( vo,null,0l,3l);
			populateToVO(returnVal1,returnVal);
			populateToVO(returnVal2,returnVal);
			populateToVO(returnVal3,returnVal);
			populateToVO(returnVal4,returnVal);
			populateToVO(returnVal5,returnVal);
			populateToVO(returnVal6,returnVal);
		}
		return returnVal;
	}
	
	
	public NewsAnalysisVO getResultsForTwo(AnalysisVO vo)
	{
		NewsAnalysisVO returnVal = new NewsAnalysisVO();
		if(vo.getSourceCandidateId() != null || vo.getDestiCandidateId() != null)
		{
			NewsAnalysisVO returnVal1 = getSourceDestinationKeywordPresentQueryForParty( vo,null,0l,1l);
			NewsAnalysisVO returnVal2 = getSourceDestinationKeywordPresentQueryForParty( vo,null,0l,2l);
			NewsAnalysisVO returnVal3 = getSourceDestinationKeywordPresentQueryForParty( vo,null,0l,3l);
			populateToVO(returnVal1,returnVal);
			populateToVO(returnVal2,returnVal);
			populateToVO(returnVal3,returnVal);
		}
		else
		{
			NewsAnalysisVO returnVal1 = getSourceDestinationKeywordPresentQueryForParty( vo,0l,null,1l);
			NewsAnalysisVO returnVal2 = getSourceDestinationKeywordPresentQueryForParty( vo,0l,null,2l);
			NewsAnalysisVO returnVal3 = getSourceDestinationKeywordPresentQueryForParty( vo,0l,null,3l);
			NewsAnalysisVO returnVal4 = getSourceDestinationKeywordPresentQueryForParty( vo,null,0l,1l);
			NewsAnalysisVO returnVal5 = getSourceDestinationKeywordPresentQueryForParty( vo,null,0l,2l);
			NewsAnalysisVO returnVal6 = getSourceDestinationKeywordPresentQueryForParty( vo,null,0l,3l);
			populateToVO(returnVal1,returnVal);
			populateToVO(returnVal2,returnVal);
			populateToVO(returnVal3,returnVal);
			populateToVO(returnVal4,returnVal);
			populateToVO(returnVal5,returnVal);
			populateToVO(returnVal6,returnVal);
		}
		return returnVal;
	}
	
	public NewsAnalysisVO getResultsForThree(AnalysisVO vo)
	{
		NewsAnalysisVO returnVal = new NewsAnalysisVO();
		if(vo.getSourceCandidateId() != null || vo.getDestiCandidateId() != null)
		{
			NewsAnalysisVO returnVal1 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,null,0l,1l);
			NewsAnalysisVO returnVal2 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,null,0l,2l);
			NewsAnalysisVO returnVal3 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,null,0l,3l);
			populateToVO(returnVal1,returnVal);
			populateToVO(returnVal2,returnVal);
			populateToVO(returnVal3,returnVal);
		}
		else
		{
			NewsAnalysisVO returnVal1 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,0l,null,1l);
			NewsAnalysisVO returnVal2 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,0l,null,2l);
			NewsAnalysisVO returnVal3 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,0l,null,3l);
			NewsAnalysisVO returnVal4 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,null,0l,1l);
			NewsAnalysisVO returnVal5 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,null,0l,2l);
			NewsAnalysisVO returnVal6 = getSourceDestinationPresentIncludeCandidatePartyForParty( vo,null,0l,3l);
			populateToVO(returnVal1,returnVal);
			populateToVO(returnVal2,returnVal);
			populateToVO(returnVal3,returnVal);
			populateToVO(returnVal4,returnVal);
			populateToVO(returnVal5,returnVal);
			populateToVO(returnVal6,returnVal);
		}
		return returnVal;
	}
	
	
	public String arrangeQueryForFirstCondition(Object candidate , Object party , StringBuilder query,AnalysisVO vo,Long type)
	{
		if(type == 1)
		{
			if(candidate != null)
			{
				if(vo.isBySourceCand()){
					query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId,cpk.candidatePartyFile.sourceCandidate.lastname, ");
				}else{
					query.append(" cast(null as char),cast(null as char),");
				}
				query.append(" cast(null as char),cast(null as char),");
			}
			else
			{
				if(vo.isBySourceCand()){
					query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId,cpk.candidatePartyFile.sourceCandidate.lastname, ");
				}else{
					query.append(" cast(null as char),cast(null as char),");
				}
				query.append(" cast(null as char),cast(null as char),");
			}
		}
		if(type == 2)
		{
			if(candidate != null)
			{
				if(vo.isBySourceCand()){
					query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId,cpc.candidatePartyFile.sourceCandidate.lastname, ");
				}else {
					query.append(" cast(null as char),cast(null as char),");
				}
				query.append(" cast(null as char),cast(null as char),");
			}
			else
			{
				if(vo.isBySourceCand()){
					query.append(" cpc.candidatePartyFile.sourceParty.partyId,cpc.candidatePartyFile.sourceParty.shortName, ");
				}else {
					query.append(" cast(null as char),cast(null as char),");
				}
				if(vo.isByDestiCand()){
					query.append(" cpc.candidatePartyFile.destinationParty.partyId,cpc.candidatePartyFile.destinationParty.shortName, ");
				}else{
					query.append(" cast(null as char),cast(null as char),");
				}
			}
		}
		else
		{
			if(candidate != null)
			{
				if(vo.isBySourceCand()){
					query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId,cpc.candidatePartyFile.sourceCandidate.lastname, ");
				}else {
					query.append(" cast(null as char),cast(null as char),");
				}
				if(vo.isByDestiCand()){
					query.append(" cpc.candidatePartyFile.destinationCandidate.candidateId,cpc.candidatePartyFile.destinationCandidate.lastname, ");
				}else{
					query.append(" cast(null as char),cast(null as char),");
				}
			}
			else
			{
				if(vo.isBySourceCand()){
					query.append(" cpc.candidatePartyFile.sourceParty.partyId,cpc.candidatePartyFile.sourceParty.shortName, ");
				}else {
					query.append(" cast(null as char),cast(null as char),");
				}
				if(vo.isByDestiCand()){
					query.append(" cpc.candidatePartyFile.destinationParty.partyId,cpc.candidatePartyFile.destinationParty.shortName, ");
				}else{
					query.append(" cast(null as char),cast(null as char),");
				}
			}
		}
		  return query.toString();
	}
	
	public String arrangeQueryForSecondCondition(Object candidate , Object party , StringBuilder query,AnalysisVO vo,Long type)
	{
		if(type == 1)
		{
			   if(candidate == null)
			   {
				   query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId is null and cpc.candidatePartyFile.destinationCandidate.candidateId is null " +
				   		" and cpc.candidatePartyFile.destinationParty.partyId is null and cpc.candidatePartyFile.sourceParty.partyId is not null ");
			   }
			   else
			   {
				   query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId is not null and cpc.candidatePartyFile.destinationCandidate.candidateId is null " +
					   		" and cpc.candidatePartyFile.destinationParty.partyId is null and cpc.candidatePartyFile.sourceParty.partyId is not null ");
			   }
		}
		if(type == 2)
		{
			   if(candidate == null)
			   {
				   query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId is null and cpc.candidatePartyFile.destinationCandidate.candidateId is null " +
				   		" and cpc.candidatePartyFile.destinationParty.partyId is not null ");
			   }
			   else
			   {
				   query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId is not null and cpc.candidatePartyFile.destinationCandidate.candidateId is null " +
					   		" and cpc.candidatePartyFile.destinationParty.partyId is not null ");
			   }
		}
		else
		{
			   if(candidate == null)
			   {
				   query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId is null and cpc.candidatePartyFile.destinationCandidate.candidateId is not null " +
				   		" and cpc.candidatePartyFile.destinationParty.partyId is not null ");
			   }
			   else
			   {
				   query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId is not null and cpc.candidatePartyFile.destinationCandidate.candidateId is not null " +
					   		" and cpc.candidatePartyFile.destinationParty.partyId is not null ");
			   }
		}
		  return query.toString();
	}
	
	public String arrangeQueryForThirdCondition(Object candidate , Object party , StringBuilder query,AnalysisVO vo,Long type)
	{
		if(type == 1)
		{
			   if(candidate == null)
			   {
				   query.append(" cpc.candidatePartyFile.sourceParty.partyId , ");
			   }
			   else
			   {
				   query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId, ");
			   }
		}
		if(type == 2)
		{
			if(candidate == null)
			  {
				   query.append(" cpc.candidatePartyFile.sourceParty.partyId,cpc.candidatePartyFile.destinationParty.partyId , ");
			  }
			  else
			  {
				   query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId,cpc.candidatePartyFile.destinationCandidate.candidateId, ");
			  }
		}
		else
		{
			 if(candidate == null)
			  {
				   query.append(" cpc.candidatePartyFile.sourceParty.partyId,cpc.candidatePartyFile.destinationCandidate.candidateId , ");
			  }
			  else
			  {
				   query.append(" cpc.candidatePartyFile.sourceCandidate.candidateId,cpc.candidatePartyFile.destinationCandidate.candidateId, ");
			  }
		}
		  return query.toString();
	}
	
	
	
	public NewsAnalysisVO getSourceDestinationCategoeryPresentQueryForParty(AnalysisVO vo,Object candidate,Object party ,Long type)
	{
		StringBuilder query = new StringBuilder();
		
		query.append("select ");
		query.append("  count(distinct cpc.candidatePartyFile.file.fileId) ,");
		
		arrangeQueryForFirstCondition(candidate,party,query,vo,type);
		
		if(vo.isLocationPresent()){
			if(vo.getLocationLvl().longValue() == 1){
				 query.append(" cpc.candidatePartyFile.file.userAddress.district.districtId,cpc.candidatePartyFile.file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId,cpc.candidatePartyFile.file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" cpc.candidatePartyFile.file.userAddress.constituency.constituencyId,cpc.candidatePartyFile.file.userAddress.constituency.name,");
			} 
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(!vo.isSourcePresent()){
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
				
				
			   query.append(" fsl.source.sourceId,fsl.source.source,cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc,FileSourceLanguage fsl " +
			   		" where cpc.candidatePartyFile.file.isDeleted != 'Y' and cpc.candidatePartyFile.file.fileId = fsl.file.fileId ");
			   
			   arrangeQueryForSecondCondition(candidate,party,query,vo,type);
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				if(vo.isSourceCand()){
				    query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" and fsl.source.sourceId in ("+vo.getSourceIds()+") and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+") group by ");
			   
			   arrangeQueryForThirdCondition(candidate,party,query,vo,type);
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   
			   query.append(" fsl.source.sourceId ,cpc.gallary.gallaryId ");
			}
			else{
				query.append("  fsl.source.sourceId,fsl.source.source,cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc" +
				   		" ,FileSourceLanguage fsl where cpc.candidatePartyFile.file.isDeleted !='Y' and cpc.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				
				arrangeQueryForSecondCondition(candidate,party,query,vo,type);
				
						if(vo.isSourceCand()){
							query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
						}else if(vo.isSourceParty()){
						   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
						}
						if(vo.isDestiCand()){
						  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
						}else if(vo.isDestiParty()){
						   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
						}
						if(vo.getFromDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
						}
						if(vo.getToDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
						}
						if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
							query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
						}
		                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
						}
				query.append("  and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+") group by  ");
				
				arrangeQueryForThirdCondition(candidate,party,query,vo,type);
				
				query.append(" fsl.source.sourceId ,cpc.gallary.gallaryId");
			}
		}
		else{
			  query.append(" cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc where cpc.candidatePartyFile.file.isDeleted !='Y' ");
			  query.append("  and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+")  ");
			 
			  arrangeQueryForSecondCondition(candidate,party,query,vo,type);
			  
			          if(vo.isSourceCand()){
							query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
						}else if(vo.isSourceParty()){
						   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
						}
						if(vo.isDestiCand()){
						  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
						}else if(vo.isDestiParty()){
						   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+"  ");
						}
						if(vo.getFromDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
						}
						if(vo.getToDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
						}
						if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
							query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
						}
		                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
						}
						query.append(" group by ");
						
						arrangeQueryForThirdCondition(candidate,party,query,vo,type);
						 
						if(vo.isLocationPresent()){
							if(vo.getLocationLvl().longValue() == 1){
								 query.append( "  cpc.candidatePartyFile.file.userAddress.district.districtId ,");
							}else if(vo.getLocationLvl().longValue() == 2){
								query.append(" cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId ,");
							}else if(vo.getLocationLvl().longValue() == 3){
								query.append(" cpc.candidatePartyFile.file.userAddress.constituency.constituencyId , ");
							}
						}
						query.append("  cpc.gallary.gallaryId");
		}
		 return getDataForCategoryOrKeywordPresent( query.toString(),vo,null,null,false,"Category");
	}
	
	
	
	public NewsAnalysisVO getSourceDestinationKeywordPresentQueryForParty(AnalysisVO vo,Object candidate,Object party,Long type)
	{
		StringBuilder query = new StringBuilder();
		query.append("select  ");
		query.append("  count(distinct cpk.candidatePartyFile.file.fileId) ,");
		
		arrangeQueryForFirstCondition(candidate,party,query,vo,type);
		
		if(vo.isLocationPresent()){
			if(vo.getLocationLvl().longValue() == 1){
				 query.append("cpk.candidatePartyFile.file.userAddress.district.districtId,cpk.candidatePartyFile.file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId,cpk.candidatePartyFile.file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" cpk.candidatePartyFile.file.userAddress.constituency.constituencyId,cpk.candidatePartyFile.file.userAddress.constituency.name,");
			} 
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(!vo.isSourcePresent()){
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
			   query.append(" fsl.source.sourceId,fsl.source.source,cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk,FileSourceLanguage fsl " +
			   		" where cpk.candidatePartyFile.file.isDeleted != 'Y' and cpk.candidatePartyFile.file.fileId = fsl.file.fileId ");
			   
			   arrangeQueryForSecondCondition(candidate,party,query,vo,type);
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
		      if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" and fsl.source.sourceId in ("+vo.getSourceIds()+") and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+") group by ");
			   
			   
			   arrangeQueryForThirdCondition(candidate,party,query,vo,type);
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   
			  /* if(vo.isBySourceCand()){
					query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
				}
               if(vo.isByDestiCand()){
					query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
				}*/
			   query.append(" fsl.source.sourceId ,cpk.keyword.keywordId ");
			}else{
				query.append("   fsl.source.sourceId,fsl.source.source,cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk " +
				   		" ,FileSourceLanguage fsl where cpk.candidatePartyFile.file.isDeleted != 'Y' and cpk.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				
				arrangeQueryForSecondCondition(candidate,party,query,vo,type);
				
				 if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append("  and cpk.keyword.keywordId in ( "+vo.getGallaryKeyWordIds()+") group by  ");
				
				arrangeQueryForThirdCondition(candidate,party,query,vo,type);
				
				/* if(vo.isBySourceCand()){
						query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
					}
				 if(vo.isByDestiCand()){
						query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
					}*/
				 query.append(" fsl.source.sourceId ,cpk.keyword.keywordId ");
			}
		}else{
			if(vo.isLocationPresent()){
				
				
			   query.append(" cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk " +
			   		" where  cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+") ");
			  
			   arrangeQueryForSecondCondition(candidate,party,query,vo,type);
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and cpk.candidatePartyFile.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and cpk.candidatePartyFile.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				 if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append("  group by ");
			   
			   arrangeQueryForThirdCondition(candidate,party,query,vo,type);
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  cpk.candidatePartyFile.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" cpk.candidatePartyFile.file.userAddress.constituency.constituencyId , ");
				}
			   /*if(vo.isBySourceCand()){
					query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
				}
			   if(vo.isByDestiCand()){
					query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
				}*/
			   query.append(" cpk.keyword.keywordId ");
			}else{
			  query.append(" cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk where cpk.candidatePartyFile.file.isDeleted !='Y' ");
			  query.append("  and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+")  ");
			 
			  arrangeQueryForSecondCondition(candidate,party,query,vo,type);
			  
			   if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append(" group by ");
				
				arrangeQueryForThirdCondition(candidate,party,query,vo,type);
				   /* if(vo.isBySourceCand()){
						query.append(" cpk.candidatePartyFile.sourceCandidate.candidateId, ");
					}
				    if(vo.isByDestiCand()){
						query.append(" cpk.candidatePartyFile.destinationCandidate.candidateId, ");
					}*/
				    
				    query.append(" cpk.keyword.keywordId");
			}
		}
		return getDataForCategoryOrKeywordPresent(query.toString(),vo,null,null,false,"Keyword");
	}
	
	public NewsAnalysisVO getSourceDestinationPresentIncludeCandidatePartyForParty(AnalysisVO vo,Object candidate,Object party,Long type){
		StringBuilder query = new StringBuilder();
		query.append("select  count(distinct cpf.file.fileId),");
		
		arrangeQueryForFirstCondition(candidate,party,query,vo,type);
		
		
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
				
				if(vo.getLocationLvl().longValue() == 1){
					 query.append(" fsl.file.userAddress.district.districtId,fsl.file.userAddress.district.districtName,");
					
					 arrangeQueryForSecondCondition(candidate,party,query,vo,type);
					 
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId,fsl.file.userAddress.parliamentConstituency.name,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId,fsl.file.userAddress.constituency.name,");
				} 
			   query.append(" fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf where fsl.file.isDeleted !='Y' and fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" group by ");
			   
			   arrangeQueryForThirdCondition(candidate,party,query,vo,type);
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			  /* if(vo.isBySourceCand()){
					query.append(" cpf.sourceCandidate.candidateId, ");
				}
			   if(vo.isByDestiCand()){
					query.append(" cpf.destinationCandidate.candidateId, ");
				}*/
			   query.append(" fsl.source.sourceId  ");
			}else{
				query.append("   cast(null as char),cast(null as char),fsl.source.sourceId,fsl.source.source from  " +
				   		" FileSourceLanguage fsl,CandidatePartyFile cpf where  fsl.file.isDeleted !='Y' and fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				
				arrangeQueryForSecondCondition(candidate,party,query,vo,type);
				
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append("   group by  ");
				
				arrangeQueryForThirdCondition(candidate,party,query,vo,type);
				
				 /*if(vo.isBySourceCand()){
						query.append(" cpf.sourceCandidate.candidateId, ");
					}
				 if(vo.isByDestiCand()){
						query.append(" cpf.destinationCandidate.candidateId, ");
					}*/
				query.append(" fsl.source.sourceId ");
				
			}
		}else if(vo.isLocationPresent()){
			
			if(vo.getLocationLvl().longValue() == 1){
				 query.append(" file.userAddress.district.districtId,file.userAddress.district.districtName,");
			
			arrangeQueryForSecondCondition(candidate,party,query,vo,type);
			
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId,file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId,file.userAddress.constituency.name,");
			} 
		   query.append(" cast(null as char),cast(null as char) from File file,CandidatePartyFile cpf  where file.isDeleted !='Y' and file.fileId = cpf.file.fileId ");
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( " and file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" and file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" and file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
			}
		   if(vo.isSourceCand()){
				query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
			}else if(vo.isSourceParty()){
			   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
			}
			if(vo.isDestiCand()){
			  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
			}else if(vo.isDestiParty()){
			   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
			}
			if(vo.getFromDate() != null){
				query.append(" and date(file.fileDate) >= :fromDate ");
			}
			if(vo.getToDate() != null){
				query.append(" and date(file.fileDate) <= :toDate ");
			}
			if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
				query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
			}
             if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
            	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
			}
		   query.append(" group by ");
		   arrangeQueryForThirdCondition(candidate,party,query,vo,type);
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( "  file.userAddress.district.districtId");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId ");
			}
		  /* if(vo.isBySourceCand()){
				query.append(" ,cpf.sourceCandidate.candidateId ");
			}
		   if(vo.isByDestiCand()){
				query.append(" ,cpf.destinationCandidate.candidateId ");
			}*/
		}else{
			query = new StringBuilder();
			if(vo.isSourceCand() || vo.isSourceParty() || vo.isDestiCand() || vo.isDestiParty() || vo.isBySourceCand() || vo.isByDestiCand()){
				query.append("select count(distinct cpf.file.fileId) ");
				
				arrangeQueryForFirstCondition(candidate,party,query,vo,type);
				
				
				query.append(" ,cast(null as char),cast(null as char),cast(null as char),cast(null as char) from  CandidatePartyFile cpf where cpf.file.isDeleted != 'Y' ");
				
				arrangeQueryForSecondCondition(candidate,party,query,vo,type);
				
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
                 if(vo.isBySourceCand() && vo.isByDestiCand()){
                	 query.append(" group by cpf.sourceCandidate.candidateId,cpf.destinationCandidate.candidateId ");
                 }
                 else if(vo.isBySourceCand()){
					query.append(" group by cpf.sourceCandidate.candidateId ");
				}
                 else if(vo.isByDestiCand()){
					query.append(" group by cpf.destinationCandidate.candidateId ");
				}
			}else{
				query.append("select count(distinct file.fileId),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char) from File file ");
				if(vo.getFromDate() != null && vo.getToDate() != null){
					query.append(" where file.isDeleted != 'Y' and file.fileDate >= :fromDate and file.fileDate <= :toDate");
				}else if(vo.getFromDate() != null){
					query.append(" where file.isDeleted != 'Y' and file.fileDate >= :fromDate ");
				}else if(vo.getToDate() != null){
					query.append(" where file.isDeleted != 'Y' and file.fileDate <= :toDate ");
				}
			}
		}
		return getDataForSourceDestinationPresentIncludeCandidateParty(query.toString(),vo,null,null,false);
	}
	
	public void populateToVO(NewsAnalysisVO from,NewsAnalysisVO to){
	 if(from != null){
		 if(from.isSubListPresent()){
			 to.setSubListPresent(true);
		 }
		if(from.getBuildMethod() != null){
			to.setBuildMethod(from.getBuildMethod());
		}
		if(from.getLevels() != null){
			if(to.getLevels() != null){
				for(String level:from.getLevels()){
					if(!to.getLevels().contains(level)){
						to.getLevels().add(level);
					}
				}
			}else{
				to.setLevels(from.getLevels());
			}
		}
		if(from.getSubList() != null){
			if(to.getSubList() != null){
				to.getSubList().addAll(from.getSubList());
			}else{
				to.setSubList(from.getSubList());
			}
		}
	 }
	}

	public NewsAnalysisVO getResultsNew(AnalysisVO vo,String type){
	    NewsAnalysisVO returnVal = new NewsAnalysisVO();
		//if who and whome analysis required
		if(vo.isBySourceCand() && vo.isByDestiCand()){
			//in who candidaie or party and whome candidate or party selected
			if((vo.isSourceCand() || vo.isSourceParty()) && (vo.isDestiCand() || vo.isDestiParty())){
				if(vo.isSourceCand() && vo.isDestiCand()){
				   if(type.equalsIgnoreCase("Category")){              
					returnVal =  getSourceDestinationCategoryPresentQuery(vo);
				   }else if(type.equalsIgnoreCase("Keyword")){
				    returnVal =  getSourceDestinationKeywordPresentQuery(vo);
				   }else if(type.equalsIgnoreCase("Others")){
				    returnVal =  getSourceDestinationPresentIncludeCandidateParty(vo);
				   }
				}else if(vo.isSourceCand() && vo.isDestiParty()){
					//getting news from who candidate on only whome party not candidate
					NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,true,false,false,false,true,false,type,null,"party");
					//getting news from who candidate on candidates in whome party
					NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,true,false,false,true,false,false,type,null,null);//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)	
					 populateToVO(returnVal1,returnVal);
			         populateToVO(returnVal2,returnVal);
				}else if(vo.isSourceParty() && vo.isDestiCand()){
					NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,true,false,true,false,false,type,"party",null);
					NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,true,false,false,true,false,false,type,null,null);//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)	
                     populateToVO(returnVal1,returnVal);
			         populateToVO(returnVal2,returnVal);					
				}else if(vo.isSourceParty() && vo.isDestiParty()){
					NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,true,false,false,true,false,type,"party","party");
					NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,false,true,false,true,false,false,type,"party",null);
					NewsAnalysisVO returnVal3 = getAllNewsCountDetails(vo,true,false,false,false,true,false,type,null,"party");
					NewsAnalysisVO returnVal4 = getAllNewsCountDetails(vo,true,false,false,true,false,false,type,null,null);//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)
                     populateToVO(returnVal1,returnVal);
			         populateToVO(returnVal2,returnVal);
			         populateToVO(returnVal3,returnVal);
			         populateToVO(returnVal4,returnVal);					
				}
			}else if(vo.isSourceCand() || vo.isSourceParty()){
                if(vo.isSourceCand()){
                	NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,true,false,false,false,true,false,type,null,"party");
                	NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,true,false,false,true,false,false,type,null,null);//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)
					NewsAnalysisVO returnVal3 = getAllNewsCountDetails(vo,true,false,false,false,false,true,type,null,"no");
					 populateToVO(returnVal1,returnVal);
					 populateToVO(returnVal2,returnVal);
					 populateToVO(returnVal3,returnVal);
				}else if(vo.isSourceParty()){
					NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,true,false,false,true,false,type,"party","party");
					NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,false,true,false,true,false,false,type,"party",null);
					NewsAnalysisVO returnVal3 = getAllNewsCountDetails(vo,false,true,false,false,false,true,type,"party","no");				
					NewsAnalysisVO returnVal4 = getAllNewsCountDetails(vo,true,false,false,false,true,false,type,null,"party");
					NewsAnalysisVO returnVal5 = getAllNewsCountDetails(vo,true,false,false,true,false,false,type,null,null);//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)
					NewsAnalysisVO returnVal6 = getAllNewsCountDetails(vo,true,false,false,false,false,true,type,null,"no");
					populateToVO(returnVal1,returnVal);
			        populateToVO(returnVal2,returnVal);
			        populateToVO(returnVal3,returnVal);
			        populateToVO(returnVal4,returnVal);
			        populateToVO(returnVal5,returnVal);
                    populateToVO(returnVal6,returnVal);
				}
			}else if(vo.isDestiCand() || vo.isDestiParty()){
				if(vo.isDestiCand()){
					NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,true,false,true,false,false,type,"party",null);
					NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,true,false,false,true,false,false,type,null,null);//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)
					NewsAnalysisVO returnVal3 = getAllNewsCountDetails(vo,false,false,true,true,false,false,type,"no",null);
					populateToVO(returnVal1,returnVal);
					populateToVO(returnVal2,returnVal);
					populateToVO(returnVal3,returnVal);
				}else if(vo.isDestiParty()){
					NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,true,false,false,true,false,type,"party","party");					
					NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,true,false,false,false,true,false,type,null,"party");//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)
					NewsAnalysisVO returnVal3 = getAllNewsCountDetails(vo,false,false,true,false,true,false,type,"no","party");
					NewsAnalysisVO returnVal4 = getAllNewsCountDetails(vo,false,true,false,true,false,false,type,"party",null);
					NewsAnalysisVO returnVal5 = getAllNewsCountDetails(vo,true,false,false,true,false,false,type,null,null);//(sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination)
					NewsAnalysisVO returnVal6 = getAllNewsCountDetails(vo,false,false,true,true,false,false,type,"no",null);
                     populateToVO(returnVal1,returnVal);
					 populateToVO(returnVal2,returnVal);
					 populateToVO(returnVal3,returnVal);
					 populateToVO(returnVal4,returnVal);
					 populateToVO(returnVal5,returnVal);
					 populateToVO(returnVal6,returnVal);					
				}
			}
		}else if(vo.isBySourceCand()){
			if(vo.isSourceCand()){
				returnVal = getAllNewsCountDetails(vo,true,false,false,false,false,false,type,null,"noDesti");
			}
			else if(vo.isSourceParty()){
				NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,true,false,false,false,false,type,"party","noDesti");
				NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,true,false,false,false,false,false,type,null,"noDesti");	
				populateToVO(returnVal1,returnVal);
				populateToVO(returnVal2,returnVal);
			}else{
				NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,true,false,false,false,false,type,"party","noDesti");
				NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,true,false,false,false,false,false,type,null,"noDesti");			
				NewsAnalysisVO returnVal3 = getAllNewsCountDetails(vo,false,false,true,false,false,false,type,"no","noDesti");
				populateToVO(returnVal1,returnVal);
				populateToVO(returnVal2,returnVal);
				populateToVO(returnVal3,returnVal);
			}
		}else if(vo.isByDestiCand()){
			if(vo.isDestiCand()){
				returnVal = getAllNewsCountDetails(vo,false,false,false,true,false,false,type,"noSource",null);
			}
			else if(vo.isDestiParty()){
				NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,false,false,false,true,false,type,"noSource","party");
				NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,false,false,false,true,false,false,type,"noSource",null);	
                populateToVO(returnVal1,returnVal);
				populateToVO(returnVal2,returnVal);
			}else{
				NewsAnalysisVO returnVal1 = getAllNewsCountDetails(vo,false,false,false,false,true,false,type,"noSource","party");
				NewsAnalysisVO returnVal2 = getAllNewsCountDetails(vo,false,false,false,true,false,false,type,"noSource",null);		
                NewsAnalysisVO returnVal3 = getAllNewsCountDetails(vo,false,false,false,false,false,true,type,"noSource","no");
				populateToVO(returnVal1,returnVal);
				populateToVO(returnVal2,returnVal);
				populateToVO(returnVal3,returnVal);
			}
		}
       return returnVal;
	}
	public NewsAnalysisVO getAllNewsCountDetails(AnalysisVO vo,boolean sourceCandidate,boolean sourceParty,boolean noSource,boolean destinationCandidate,boolean destinationParty,boolean noDestination,String type,String sourceType,String destiType){
	  if(type.equalsIgnoreCase("Category")){
	    return getNewsByCategoryWise(vo,sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination,sourceType,destiType);
	  }else if(type.equalsIgnoreCase("Keyword")){
	    return getSourceDestinationKeywordPresentQueryNew(vo,vo.isSourceCand(),vo.isDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination,sourceType,destiType);
	  }else if(type.equalsIgnoreCase("Others")){
	    return getSourceDestinationPresentIncludeCandidateParty(vo,sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination,sourceType,destiType);
	  }
	  else return null;
	}
	public NewsAnalysisVO getNewsByCategoryWise(AnalysisVO vo,boolean sourceCandidate,boolean sourceParty,boolean noSource,boolean destinationCandidate,boolean destinationParty,boolean noDestination,String sourceType,String destiType){
		StringBuilder query = new StringBuilder();
		// bySourceCand analyse by who candidate
		// byDestiCand analyse by whome candidate
		// sourcePresent ex: eendau,sakshi,andhra jyothi
		// locationPresent ex: state ap,district ranga reddy,ac ,pc
		// sourceCand if source candidate is selected
		// sourceParty if source party is selected
		// destiCand if destination candidate is selected
		// destiParty if destination party is selected
		query.append("select ");
		query.append("  count(distinct cpc.candidatePartyFile.file.fileId) ,");
        query.append(getCandidatePartyQueryForKeywordSource("cpc.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
		if(vo.isLocationPresent()){
			if(vo.getLocationLvl().longValue() == 1){
				 query.append(" cpc.candidatePartyFile.file.userAddress.district.districtId,cpc.candidatePartyFile.file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId,cpc.candidatePartyFile.file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" cpc.candidatePartyFile.file.userAddress.constituency.constituencyId,cpc.candidatePartyFile.file.userAddress.constituency.name,");
			} 
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(!vo.isSourcePresent()){
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
				
				
			   query.append(" fsl.source.sourceId,fsl.source.source,cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc,FileSourceLanguage fsl " +
			   		" where cpc.candidatePartyFile.file.isDeleted !='Y' and cpc.candidatePartyFile.file.fileId = fsl.file.fileId ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				if(vo.isSourceCand()){
				    query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpc.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
				if(vo.getFromDate() != null){
					query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" and fsl.source.sourceId in ("+vo.getSourceIds()+") and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+") group by ");
			   			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}			   
			   query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpc.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
			   query.append(" fsl.source.sourceId ,cpc.gallary.gallaryId ");
			}else{
				query.append("  fsl.source.sourceId,fsl.source.source,cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc" +
				   		" ,FileSourceLanguage fsl where cpc.candidatePartyFile.file.isDeleted != 'Y' and cpc.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
						if(vo.isSourceCand()){
							query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
						}else if(vo.isSourceParty()){
						   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
						}
						if(vo.isDestiCand()){
						  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
						}else if(vo.isDestiParty()){
						   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
						}
						query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpc.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
				
						if(vo.getFromDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
						}
						if(vo.getToDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
						}
						if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
							query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
						}
		                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
						}
				query.append("  and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+") group by  ");
				query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpc.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
			  
				query.append(" fsl.source.sourceId ,cpc.gallary.gallaryId");
			}
		}else{
			query.append(" cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc where cpc.candidatePartyFile.file.isDeleted !='Y' ");
			query.append("  and cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+")  ");
			          if(vo.isSourceCand()){
							query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
						}else if(vo.isSourceParty()){
						   query.append(" and cpc.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
						}
						if(vo.isDestiCand()){
						  query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
						}else if(vo.isDestiParty()){
						   query.append(" and cpc.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+"  ");
						}
                        query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpc.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
						if(vo.isLocationPresent()){	
							if(vo.getLocationLvl().longValue() == 1){
								 query.append( " and cpc.candidatePartyFile.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
							}else if(vo.getLocationLvl().longValue() == 2){
								query.append(" and cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
							}else if(vo.getLocationLvl().longValue() == 3){
								query.append(" and cpc.candidatePartyFile.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
							}
						}
						if(vo.getFromDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
						}
						if(vo.getToDate() != null){
							query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
						}
						if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
							query.append(" and cpc.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
						}
		                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
		                	 query.append(" and cpc.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
						}
						query.append(" group by ");
						if(vo.isLocationPresent()){
							if(vo.getLocationLvl().longValue() == 1){
								 query.append( "  cpc.candidatePartyFile.file.userAddress.district.districtId ,");
							}else if(vo.getLocationLvl().longValue() == 2){
								query.append(" cpc.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId ,");
							}else if(vo.getLocationLvl().longValue() == 3){
								query.append(" cpc.candidatePartyFile.file.userAddress.constituency.constituencyId , ");
							}
						}
						query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpc.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
			  
						query.append("  cpc.gallary.gallaryId");
		}
		 return getDataForCategoryOrKeywordPresent( query.toString(),vo,sourceType,destiType,true,"Category");
	}	
	
	public StringBuilder getCandidatePartyQueryForKeywordSource(String table,boolean byWho,boolean byWhome,boolean sourceCandidate,boolean sourceParty,boolean noSource,boolean destinationCandidate,boolean destinationParty,boolean noDestination){
		StringBuilder returnQuery = new StringBuilder();
		if(byWho){
			if(sourceCandidate){
				returnQuery.append(" "+table+".sourceCandidate.candidateId,"+table+".sourceCandidate.lastname, ");
			}else if(sourceParty){
				returnQuery.append(" "+table+".sourceParty.partyId,"+table+".sourceParty.shortName, ");
			}else if(noSource){
				returnQuery.append(" 0l,'', ");
			}
		}else{
			returnQuery.append(" cast(null as char),cast(null as char),");
		}
		if(byWhome){
			if(destinationCandidate){
				returnQuery.append(" "+table+".destinationCandidate.candidateId,"+table+".destinationCandidate.lastname, ");
			}else if(destinationParty){
				returnQuery.append(" "+table+".destinationParty.partyId,"+table+".destinationParty.shortName, ");
			}else if(noDestination){
				returnQuery.append(" 0l,'', ");
			}
		}else{
			returnQuery.append(" cast(null as char),cast(null as char),");
		}
		return returnQuery;
	}
	public StringBuilder getCandidatePartyQueryGroupByConditionForKeywordSource(String table,boolean byWho,boolean byWhome,boolean sourceCandidate,boolean sourceParty,boolean noSource,boolean destinationCandidate,boolean destinationParty,boolean noDestination){
		StringBuilder returnQuery = new StringBuilder();
		if(byWho){
			if(sourceCandidate){
				returnQuery.append(" "+table+".sourceCandidate.candidateId, ");
			}else if(sourceParty){
				returnQuery.append(" "+table+".sourceParty.partyId, ");
			}
		}
		if(byWhome){
			if(destinationCandidate){
				returnQuery.append(" "+table+".destinationCandidate.candidateId, ");
			}else if(destinationParty){
				returnQuery.append(" "+table+".destinationParty.partyId, ");
			}
		}
		return returnQuery;
	}
	
	public StringBuilder getCandidatePartyQueryAndConditionForKeywordSource(String table,boolean byWho,boolean byWhome,boolean sourceCandidate,boolean sourceParty,boolean noSource,boolean destinationCandidate,boolean destinationParty,boolean noDestination){
		StringBuilder returnQuery = new StringBuilder();
		if(byWho){
			if(sourceCandidate){
				returnQuery.append(" and "+table+".sourceCandidate.candidateId is not null ");
			}else if(sourceParty){
				returnQuery.append(" and "+table+".sourceParty.partyId is not null and "+table+".sourceCandidate.candidateId is null ");
			}else if(noSource){
				returnQuery.append(" and "+table+".sourceParty.partyId is null and "+table+".sourceCandidate.candidateId is null ");
			}
		}
		if(byWhome){
			if(destinationCandidate){
				returnQuery.append(" and "+table+".destinationCandidate.candidateId is not null ");
			}else if(destinationParty){
				returnQuery.append(" and "+table+".destinationParty.partyId is not null and "+table+".destinationCandidate.candidateId is null ");
			}else if(noDestination){
				returnQuery.append(" and "+table+".destinationParty.partyId is null and "+table+".destinationCandidate.candidateId is null ");
			}
		}
		return returnQuery;
	}
	public NewsAnalysisVO getSourceDestinationKeywordPresentQueryNew(AnalysisVO vo,boolean byWho,boolean byWhome,boolean sourceCandidate,boolean sourceParty,boolean noSource,boolean destinationCandidate,boolean destinationParty,boolean noDestination,String sourceType,String destiType){
		StringBuilder query = new StringBuilder();
		query.append("select  ");
		query.append("  count(distinct cpk.candidatePartyFile.file.fileId) ,");
		query.append(getCandidatePartyQueryForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

		if(vo.isLocationPresent()){
			if(vo.getLocationLvl().longValue() == 1){
				 query.append("cpk.candidatePartyFile.file.userAddress.district.districtId,cpk.candidatePartyFile.file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId,cpk.candidatePartyFile.file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" cpk.candidatePartyFile.file.userAddress.constituency.constituencyId,cpk.candidatePartyFile.file.userAddress.constituency.name,");
			} 
		}else{
			query.append(" cast(null as char),cast(null as char),");
		}
		if(!vo.isSourcePresent()){
			query.append(" cast(null as char),cast(null as char),");
		}
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
			   query.append(" fsl.source.sourceId,fsl.source.source,cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk,FileSourceLanguage fsl " +
			   		" where cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.candidatePartyFile.file.fileId = fsl.file.fileId ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
		      if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" and fsl.source.sourceId in ("+vo.getSourceIds()+") and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+") group by ");
			   
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   
			   query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

			   query.append(" fsl.source.sourceId ,cpk.keyword.keywordId ");
			}else{
				query.append("   fsl.source.sourceId,fsl.source.source,cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk " +
				   		" ,FileSourceLanguage fsl where cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				 if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append("  and cpk.keyword.keywordId in ( "+vo.getGallaryKeyWordIds()+") group by  ");
				 query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				 query.append(" fsl.source.sourceId ,cpk.keyword.keywordId ");
			}
		}else{
			if(vo.isLocationPresent()){
				
				
			   query.append(" cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk " +
			   		" where  cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+") ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and cpk.candidatePartyFile.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and cpk.candidatePartyFile.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				 if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append("  group by ");
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  cpk.candidatePartyFile.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" cpk.candidatePartyFile.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" cpk.candidatePartyFile.file.userAddress.constituency.constituencyId , ");
				}
			   query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

			   query.append(" cpk.keyword.keywordId ");
			}else{
			  query.append(" cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk where cpk.candidatePartyFile.file.isDeleted !='Y' ");
			  query.append("  and cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+")  ");
			   if(vo.isSourceCand()){
					query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpk.candidatePartyFile.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpk.candidatePartyFile.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				if(vo.getFromDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpk.candidatePartyFile.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpk.candidatePartyFile.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append(" group by ");
				
				    query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpk.candidatePartyFile",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				    
				    query.append(" cpk.keyword.keywordId");
			}
		}
		return getDataForCategoryOrKeywordPresent(query.toString(),vo,sourceType,destiType,true,"Keyword");
	}
    public NewsAnalysisVO getSourceDestinationPresentIncludeCandidateParty(AnalysisVO vo,boolean sourceCandidate,boolean sourceParty,boolean noSource,boolean destinationCandidate,boolean destinationParty,boolean noDestination,String sourceType,String destiType){
		StringBuilder query = new StringBuilder();
		query.append("select  count(distinct cpf.file.fileId),");
		query.append(getCandidatePartyQueryForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
		if(vo.isSourcePresent()){
			if(vo.isLocationPresent()){
				
				if(vo.getLocationLvl().longValue() == 1){
					 query.append(" fsl.file.userAddress.district.districtId,fsl.file.userAddress.district.districtName,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId,fsl.file.userAddress.parliamentConstituency.name,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId,fsl.file.userAddress.constituency.name,");
				} 
			   query.append(" fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf where fsl.file.isDeleted != 'Y' and fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and fsl.file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append("and fsl.file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append("and fsl.file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
				
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));
				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
			   query.append(" group by ");
			   
			   if(vo.getLocationLvl().longValue() == 1){
					 query.append( "  fsl.file.userAddress.district.districtId ,");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" fsl.file.userAddress.parliamentConstituency.constituencyId ,");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" fsl.file.userAddress.constituency.constituencyId , ");
				}
			   query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

			   query.append(" fsl.source.sourceId  ");
			}else{
				query.append("   cast(null as char),cast(null as char),fsl.source.sourceId,fsl.source.source from  " +
				   		" FileSourceLanguage fsl,CandidatePartyFile cpf where fsl.file.isDeleted != 'Y' and fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
				}
				query.append("   group by  ");
				 query.append(getCandidatePartyQueryGroupByConditionForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				query.append(" fsl.source.sourceId ");
				
			}
		}else if(vo.isLocationPresent()){
			
			if(vo.getLocationLvl().longValue() == 1){
				 query.append(" file.userAddress.district.districtId,file.userAddress.district.districtName,");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId,file.userAddress.parliamentConstituency.name,");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId,file.userAddress.constituency.name,");
			} 
		   query.append(" cast(null as char),cast(null as char) from File file,CandidatePartyFile cpf  where file.isDeleted != 'Y' and file.fileId = cpf.file.fileId ");
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( " and file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" and file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" and file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
			}
		   if(vo.isSourceCand()){
				query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
			}else if(vo.isSourceParty()){
			   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
			}
			if(vo.isDestiCand()){
			  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
			}else if(vo.isDestiParty()){
			   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
			}
			query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

			if(vo.getFromDate() != null){
				query.append(" and date(file.fileDate) >= :fromDate ");
			}
			if(vo.getToDate() != null){
				query.append(" and date(file.fileDate) <= :toDate ");
			}
			if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
				query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
			}
             if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
            	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
			}
		   query.append(" group by ");
		  
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( "  file.userAddress.district.districtId");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId ");
			}
		    if(vo.isSourceCand()){
				if(sourceCandidate){
					query.append(" ,cpf.sourceCandidate.candidateId ");
				}else if(sourceParty){
					query.append(" ,cpf.sourceParty.partyId ");
				}
			}
			if(vo.isDestiCand()){
				if(destinationCandidate){
					query.append(" ,cpf.destinationCandidate.candidateId ");
				}else if(destinationParty){
					query.append(" ,cpf.destinationParty.partyId ");
				}
			}
		}else{
			query = new StringBuilder();
			if(vo.isSourceCand() || vo.isSourceParty() || vo.isDestiCand() || vo.isDestiParty() || vo.isBySourceCand() || vo.isByDestiCand()){
				query.append("select count(distinct cpf.file.fileId), ");
				query.append(getCandidatePartyQueryForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				query.append(" cast(null as char),cast(null as char),cast(null as char),cast(null as char) from  CandidatePartyFile cpf where cpf.file.isDeleted != 'Y' ");
				if(vo.isSourceCand()){
					query.append(" and cpf.sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
				}else if(vo.isSourceParty()){
				   query.append(" and cpf.sourceParty.partyId = "+vo.getSourcePartyId()+" ");
				}
				if(vo.isDestiCand()){
				  query.append(" and cpf.destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
				}else if(vo.isDestiParty()){
				   query.append(" and cpf.destinationParty.partyId = "+vo.getDestiPartyId()+" ");
				}
				query.append(getCandidatePartyQueryAndConditionForKeywordSource("cpf",vo.isBySourceCand(),vo.isByDestiCand(),sourceCandidate,sourceParty,noSource,destinationCandidate,destinationParty,noDestination));

				if(vo.getFromDate() != null){
					query.append(" and date(cpf.file.fileDate) >= :fromDate ");
				}
				if(vo.getToDate() != null){
					query.append(" and date(cpf.file.fileDate) <= :toDate ");
				}
				if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
					query.append(" and cpf.sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
				}
                 if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
                	 query.append(" and cpf.destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
					 
				}
                 query.append(" ");
                 int count = 0;
				if(vo.isBySourceCand() && vo.isByDestiCand()){
				        if(sourceCandidate){
							query.append(" group by  cpf.sourceCandidate.candidateId ");
							count=count+1;
						}else if(sourceParty){
							if(count == 0)
							    query.append(" group by cpf.sourceParty.partyId ");
							else
								query.append(", cpf.sourceParty.partyId ");	
							count=count+1;
						}
						if(destinationCandidate){
							if(count == 0)
							  query.append(" group by cpf.destinationCandidate.candidateId ");
							else
							  query.append(", cpf.destinationCandidate.candidateId ");
							count=count+1;
						}else if(destinationParty){
							if(count == 0)
							  query.append(" group by cpf.destinationParty.partyId ");
							else
								query.append(" ,cpf.destinationParty.partyId ");
							count=count+1;
						}
				}else{
					if(vo.isBySourceCand()){
						if(sourceCandidate){
							query.append(" group by cpf.sourceCandidate.candidateId ");
						}else if(sourceParty){
							query.append(" group by cpf.sourceParty.partyId ");
						}
					}
					else if(vo.isByDestiCand()){
						if(destinationCandidate){
							query.append(" group by cpf.destinationCandidate.candidateId ");
						}else if(destinationParty){
							query.append(" group by cpf.destinationParty.partyId ");
						}
					}
				}
			}else{
				query.append("select count(distinct file.fileId),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char) from File file where file.isDeleted != 'Y' ");
				if(vo.getFromDate() != null && vo.getToDate() != null){
					query.append(" and file.fileDate >= :fromDate and file.fileDate <= :toDate");
				}else if(vo.getFromDate() != null){
					query.append(" and file.fileDate >= :fromDate ");
				}else if(vo.getToDate() != null){
					query.append(" and file.fileDate <= :toDate ");
				}
			}
		}
		return getDataForSourceDestinationPresentIncludeCandidateParty(query.toString(),vo,sourceType,destiType,true);
	}
    
    public SelectOptionVO getPartyWiseNewsCountForGraph(AnalysisVO analysisVO){
    	List<String> partyNames = new ArrayList<String>();
    	Map<String,Long> totalCountMap = new HashMap<String,Long>();
    	Map<String,Long> positiveCountMap = new HashMap<String,Long>();
    	Map<String,Long> negativeCountMap = new HashMap<String,Long>();
    	
    	StringBuilder totalSourceCountquery = getQueryForGraph(analysisVO,"0","cpf0.sourceParty.partyId","select count(distinct cpf0.file.fileId) ,cpf0.sourceParty.partyId ,cpf0.sourceParty.shortName "," and cpf0.sourceParty.partyId is not null ");
    	//StringBuilder inQuery = getQueryForGraph(analysisVO,"1",null," select cpf1.file.fileId  "," and cpf0.sourceParty.partyId is not null ");
    	StringBuilder totalDestiCountquery = getQueryForGraph(analysisVO,"0","cpf0.destinationParty.partyId","select count(distinct cpf0.file.fileId) ,cpf0.destinationParty.partyId ,cpf0.destinationParty.shortName "," and cpf0.destinationParty.partyId is not null and (cpf0.sourceParty.partyId is null or (cpf0.sourceParty.partyId != cpf0.destinationParty.partyId ) )");
    	StringBuilder sourcePosNegCountquery = getQueryForGraph(analysisVO,"0","cpf0.sourceParty.partyId,cpf0.sourceBenefit.benefitId","select count(distinct cpf0.file.fileId) ,cpf0.sourceParty.partyId ,cpf0.sourceParty.shortName,cpf0.sourceBenefit.benefitId,cpf0.sourceBenefit.name "," and cpf0.sourceParty.partyId is not null and cpf0.sourceBenefit.benefitId is not null and cpf0.sourceBenefit.benefitId in(1,2) ");
    	//StringBuilder inQueryForPostivNeg = getQueryForGraph(analysisVO,"1",null," select cpf1.file.fileId  "," and cpf0.sourceParty.partyId is not null and cpf0.sourceBenefit.benefitId is not null and cpf0.sourceBenefit.benefitId in(1,2) ");
    	StringBuilder destiPosNegCountquery = getQueryForGraph(analysisVO,"0","cpf0.destinationParty.partyId,cpf0.destinationBenefit.benefitId","select count(distinct cpf0.file.fileId) ,cpf0.destinationParty.partyId ,cpf0.destinationParty.shortName,cpf0.destinationBenefit.benefitId,cpf0.destinationBenefit.name "," and cpf0.destinationParty.partyId is not null and cpf0.destinationBenefit.benefitId is not null and cpf0.destinationBenefit.benefitId in(1,2)  and (cpf0.sourceParty.partyId is null or (cpf0.sourceParty.partyId != cpf0.destinationParty.partyId ) )");
    	
    	List<Object[]> totalSourceCountList = fileDAO.getNewsBySearchCriteria(totalSourceCountquery.toString(),analysisVO);
    	List<Object[]> totalDestiCountList = fileDAO.getNewsBySearchCriteria(totalDestiCountquery.toString(),analysisVO);
    	
    	List<Object[]> benifitsSourceCountList = fileDAO.getNewsBySearchCriteria(sourcePosNegCountquery.toString(),analysisVO);
    	List<Object[]> benifitsDestiCountList = fileDAO.getNewsBySearchCriteria(destiPosNegCountquery.toString(),analysisVO);
    	
    	populateTotalCount(totalSourceCountList,totalCountMap,partyNames);
    	populateTotalCount(totalDestiCountList,totalCountMap,partyNames);
    	
    	populateBenifitCount(benifitsSourceCountList,positiveCountMap,negativeCountMap);
    	populateBenifitCount(benifitsDestiCountList,positiveCountMap,negativeCountMap);
    	 Collections.sort(partyNames);
    	 SelectOptionVO vo = new SelectOptionVO();
    	 vo.setValues(partyNames);
    	 List<Long> positiveCount = new ArrayList<Long>();
    	 List<Long> negativeCount = new ArrayList<Long>();
    	 List<Long> totalCount    = new ArrayList<Long>();
    	 for(String party:partyNames){
    		 if(totalCountMap.get(party) != null){
    			 totalCount.add(totalCountMap.get(party));
    		 }else{
    			 totalCount.add(0l);
    		 }
    		 if(positiveCountMap.get(party) != null){
    			 positiveCount.add(positiveCountMap.get(party));
    		 }else{
    			 positiveCount.add(0l);
    		 }
    		 if(negativeCountMap.get(party) != null){
    			 negativeCount.add(negativeCountMap.get(party));
    		 }else{
    			 negativeCount.add(0l);
    		 }
    	 }
    	 vo.setTotalCount(totalCount);
    	 vo.setPositiveCount(positiveCount);
    	 vo.setLocationValuesList(negativeCount);
    	 
    	 return vo;
    }
    
    public void populateTotalCount(List<Object[]> totalCountList,Map<String,Long> totalCountMap,List<String> partyNames){
    	if(totalCountList != null && totalCountList.size() > 0){
    		for(Object[] totalCount:totalCountList){
    			if(!partyNames.contains(totalCount[2].toString()))
    			  partyNames.add(totalCount[2].toString());
    			Long count = totalCountMap.get(totalCount[2].toString());
    			if(count == null){
    				totalCountMap.put(totalCount[2].toString(),(Long)totalCount[0]);
    			}else{
    				totalCountMap.put(totalCount[2].toString(),count+(Long)totalCount[0]);
    			}
    		}
    	}
    }
    
    public void populateBenifitCount(List<Object[]> benifitsCountList,Map<String,Long> positiveCountMap,Map<String,Long> negativeCountMap){
    	if(benifitsCountList != null && benifitsCountList.size() > 0){
    		for(Object[] benifitCount:benifitsCountList){
    			Long count = null;
    			if(((Long)benifitCount[3]).longValue() == 1){
	    			 count = positiveCountMap.get(benifitCount[2].toString());
	    			if(count == null){
	    				positiveCountMap.put(benifitCount[2].toString(),(Long)benifitCount[0]);
	    			}else{
	    				positiveCountMap.put(benifitCount[2].toString(),count+(Long)benifitCount[0]);
	    			}
    			}else{
    				 count = negativeCountMap.get(benifitCount[2].toString());
 	    			if(count == null){
 	    				negativeCountMap.put(benifitCount[2].toString(),(Long)benifitCount[0]);
 	    			}else{
 	    				negativeCountMap.put(benifitCount[2].toString(),count+(Long)benifitCount[0]);
 	    			}
    			}
    		}
    	}
    }
    
    public StringBuilder getQueryForGraph(AnalysisVO vo,String id,String groupBy,String selectPart,String conditionsReq){
    	// bySourceCand analyse by who candidate
		// byDestiCand analyse by whome candidate
		// sourcePresent ex: eendau,sakshi,andhra jyothi
		// locationPresent ex: state ap,district ranga reddy,ac ,pc
		// sourceCand if source candidate is selected
		// sourceParty if source party is selected
		// destiCand if destination candidate is selected
		// destiParty if destination party is selected
    	// byCategory if category present
		 StringBuilder query = new StringBuilder();
		     query.append(selectPart +" from CandidatePartyFile cpf"+id+"  ");
		    //query.append("select count(distinct cpf"+id+".file.fileId) ,cpf"+id+".sourceParty.partyId ,cpf"+id+".sourceParty.shortName ");
		    if(vo.isByCategory()){
		    	query.append(" ,CandidatePartyCategory cpc"+id+" ");
		    }
		    if(vo.isByKeyword()){
				query.append(",CandidatePartyKeyword cpk"+id+" ");
		    }
		    if(vo.isSourcePresent()){
		    	query.append(",FileSourceLanguage fsl"+id+" ");
		    }
		    query.append(" where cpf"+id+".file.isDeleted != 'Y' and cpf"+id+".sourceParty.partyId is not null ");
		    if(vo.isByCategory()){
		    	query.append(" and cpf"+id+".candidatePartyFileId = cpc"+id+".candidatePartyFile.candidatePartyFileId ");
		    }
		    if(vo.isByKeyword()){
				query.append(" and cpf"+id+".candidatePartyFileId = cpk"+id+".candidatePartyFile.candidatePartyFileId ");
		    }
		    if(vo.isSourcePresent()){
		    	query.append(" and cpf"+id+".file.fileId = fsl"+id+".file.fileId ");
		    }
		    if(vo.getFromDate() != null){
				query.append(" and cpf"+id+".file.fileDate >=:fromDate  ");
			}
			if(vo.getToDate() != null){
				query.append(" and cpf"+id+".file.fileDate <=:toDate  ");
			}
			if(vo.isSourceCand()){
			    query.append(" and cpf"+id+".sourceCandidate.candidateId = "+vo.getSourceCandidateId()+" ");
			}else if(vo.isSourceParty()){
			   query.append(" and cpf"+id+".sourceParty.partyId = "+vo.getSourcePartyId()+" ");
			}
			if(vo.isDestiCand()){
			  query.append(" and cpf"+id+".destinationCandidate.candidateId = "+vo.getDestiCandidateId()+" ");
			}else if(vo.isDestiParty()){
			   query.append(" and cpf"+id+".destinationParty.partyId = "+vo.getDestiPartyId()+" ");
			}
			if(vo.getSourceBenifitId() != null && vo.getSourceBenifitId() > 0){
				query.append(" and cpf"+id+".sourceBenefit.benefitId = "+vo.getSourceBenifitId()+" ");
			}
             if(vo.getDestiBenifitId() != null && vo.getDestiBenifitId() > 0){
            	 query.append(" and cpf"+id+".destinationBenefit.benefitId = "+vo.getDestiBenifitId()+" ");
			}
             if(vo.isByCategory()){
            	 query.append(" and cpc"+id+".gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+")");
             }
             if(vo.isByKeyword()){
            	 query.append(" and cpk"+id+".keyword.keywordId in ("+vo.getGallaryKeyWordIds()+")");
 		    }
             if(vo.isSourcePresent()){
 		    	query.append(" and fsl"+id+".source.sourceId in ("+vo.getSourceIds()+") ");
 		    }
             if(vo.isLocationPresent()){
	             if(vo.getLocationLvl().longValue() == 1){
					 query.append( " and cpf"+id+".file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
				}else if(vo.getLocationLvl().longValue() == 2){
					query.append(" and cpf"+id+".file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
				}else if(vo.getLocationLvl().longValue() == 3){
					query.append(" and cpf"+id+".file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
				}
             }
             if(conditionsReq != null){
            	 query.append(conditionsReq);
             }
             if(groupBy != null)
               query.append(" group by "+groupBy);
             
             return query;
    }
    
    public AnalysisBasicInfoVO getAnalysedNewsCount(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds){
    	AnalysisBasicInfoVO returnVo = new AnalysisBasicInfoVO();
    	
    	//getting total count info
    	StringBuilder totalCountQuery = getTotalNewsCountQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,null,null,null);
    	StringBuilder totalPositiveCountQuery = getPostivNegivNewsCountQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1L,null,null,null);
    	StringBuilder totalNegativeCountQuery = getPostivNegivNewsCountQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2L,null,null,null);
    	Long totalNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    	Long totalPositiveCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalPositiveCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    	Long totalNegativeCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalNegativeCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    	NewsAnalysisVO totalCount = new NewsAnalysisVO();
    	totalCount.setTotal(totalNewsCount);
    	totalCount.setPositiveCount(totalPositiveCount);
    	totalCount.setNegativeCount(totalNegativeCount);
    	returnVo.setTotalCount(totalCount);
    	
    	//getting tdp own news count info
    	if(candidateId == null || candidateId.longValue() == 0){
    	  StringBuilder totalOwnNewsCountQuery = getPartyOwnNewsCountQuery(fromDate,toDate,partyId,locationLvl,locationIds,null,null,null);
    	  StringBuilder totalOwnNegNewsCountQuery = getPartyOwnNegativeNewsCountQuery(fromDate,toDate,partyId,locationLvl,locationIds,null,null,null);
    	  Long totalOwnNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalOwnNewsCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    	  Long totalNegOwnNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalOwnNegNewsCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    	  NewsAnalysisVO ownNews = new NewsAnalysisVO();
    	  ownNews.setTotal(totalOwnNewsCount);
    	  ownNews.setNegativeCount(totalNegOwnNewsCount);
    	  returnVo.setOwnNews(ownNews);
    	  
    	}
    	
    	//getting media news on tdp count info
    	StringBuilder totalMediaCountQuery = getTotalNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,null,null,null);
    	StringBuilder totalPositiveNegMediaCountQuery = getPostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds);
    	Long totalMediaCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalMediaCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    	List<Object[]> totalPositiveNegMediaList = candidatePartyFileDAO.getNewsCount(totalPositiveNegMediaCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    	NewsAnalysisVO inMedia = new NewsAnalysisVO();
    	inMedia.setTotal(totalMediaCount);
    	returnVo.setInMedia(inMedia);
    	for(Object[] positivNeg:totalPositiveNegMediaList){
    		if(positivNeg != null && positivNeg.length > 0){
    			if(((Long)positivNeg[1]).longValue() == 1){
    				inMedia.setPositiveCount((Long)positivNeg[0]);
    			}else if(((Long)positivNeg[1]).longValue() == 2){
    				inMedia.setNegativeCount((Long)positivNeg[0]);
    			}
    		}
    	}
    	FileVO result = getAnalysisDetails(partyId,candidateId,locationLvl,locationIds,fromDate,toDate);
    	if(result != null && result.getOtherPartyEffect() != null && result.getOtherPartyEffect().getSubList() != null && result.getOtherPartyEffect().getSubList().size() > 0){
    		Collections.sort(result.getOtherPartyEffect().getSubList(),countCompare);
    	}
        if(result != null && result.getTdpPartyEffect() != null && result.getTdpPartyEffect().getSubList() != null && result.getTdpPartyEffect().getSubList().size() > 0){
        	Collections.sort(result.getTdpPartyEffect().getSubList(),countCompare);
    	}
    	returnVo.setOnMe(result.getOtherPartyEffect());
    	returnVo.setOnOtherParty(result.getTdpPartyEffect());
    	return returnVo;
    }
    public StringBuilder getTotalNewsCountQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	query.append("select count(distinct cpf.file.fileId) from CandidatePartyFile cpf " );
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	if(candidateId != null && candidateId.longValue() > 0){
    	   
    		query.append(" (cpf.sourceCandidate.candidateId =:candidateId or cpf.destinationCandidate.candidateId =:candidateId )"); 
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" ( cpf.sourceParty.partyId =:partyId or cpf.destinationParty.partyId =:partyId )");
    	}
    	if(fromDate != null){
 		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
 	    }
 	    if(toDate != null){
 		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
 	    }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	return query;
    }
    public StringBuilder getPostivNegivNewsCountQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long positiveNegivId,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	 query.append("select count(distinct cpf.file.fileId) from CandidatePartyFile cpf   " );
     	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" ((cpf.sourceCandidate.candidateId =:candidateId and cpf.sourceBenefit.benefitId = "+positiveNegivId+" ) or (cpf.destinationCandidate.candidateId =:candidateId  and cpf.destinationBenefit.benefitId = "+positiveNegivId+" ))");
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" ( (cpf.sourceParty.partyId =:partyId  and cpf.sourceBenefit.benefitId = "+positiveNegivId+" ) or (cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId = "+positiveNegivId+" ))");
    	}
    	   if(fromDate != null){
 		       query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	 	   }
	 	   if(toDate != null){
	 		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	 	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	return query;
    }
    
    public StringBuilder addLocationString(Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder(); 
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		if(locationLvl.longValue() == 1){
				 query.append( " and ua.district.districtId in ("+locationIds+") ");
			}else if(locationLvl.longValue() == 2){
				query.append(" and ua.parliamentConstituency.constituencyId in ("+locationIds+")");
			}else if(locationLvl.longValue() == 3){
				query.append(" and ua.constituency.constituencyId in ("+locationIds+") ");
			}
    	}
    	return query;
    }
    
    public StringBuilder getPartyOwnNewsCountQuery(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder(); 
    	query.append(" select count(distinct cpf.file.fileId) from CandidatePartyFile cpf   " );
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	query.append("  cpf.sourceParty.partyId =:partyId and cpf.destinationParty.partyId =:partyId ");
	    if(fromDate != null){
		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	    }
	    if(toDate != null){
		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	    }
	    query.append(""+addLocationString(locationLvl,locationIds)+"");
       	return query;
    }
    
    public StringBuilder getPartyOwnNegativeNewsCountQuery(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder(); 
    	query.append(" select count(distinct cpf.file.fileId) from CandidatePartyFile cpf   " );
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	query.append("  cpf.sourceParty.partyId =:partyId and cpf.destinationParty.partyId =:partyId and " +
    			" cpf.destinationBenefit.benefitId = 2 ");
	    if(fromDate != null){
		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	    }
	    if(toDate != null){
		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	    }
	    query.append(""+addLocationString(locationLvl,locationIds)+"");
       	return query;
    }
    
    public StringBuilder getPostivNegivNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder();
    	query.append("select count(distinct cpf.file.fileId),cpf.destinationBenefit.benefitId from CandidatePartyFile cpf ");
    	query.append(" "+getCategorySourceQuery(null, null,null,locationLvl,locationIds)+"  " );
    	query.append(" cpf.sourceCandidate.candidateId is null and cpf.sourceParty.partyId is null ");
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpf.destinationCandidate.candidateId =:candidateId and cpf.destinationBenefit.benefitId is not null  and cpf.destinationBenefit.benefitId in(1,2) "); 	   
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId is not null and cpf.destinationBenefit.benefitId in(1,2) ");	    	  
    	}
       if(fromDate != null){
  		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
  	   }
  	   if(toDate != null){
  		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
  	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	
    	query.append("group by cpf.destinationBenefit.benefitId ");
    	return query;
    }
    
    public StringBuilder getPostivNegivNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long positiveNegivId,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	query.append(" select count(distinct cpf.file.fileId) from CandidatePartyFile cpf  " );
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    		query.append(" cpf.sourceCandidate.candidateId is null and cpf.sourceParty.partyId is null  " );
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpf.destinationCandidate.candidateId =:candidateId and cpf.destinationBenefit.benefitId is not null  and cpf.destinationBenefit.benefitId = "+positiveNegivId+" "); 	   
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId is not null and cpf.destinationBenefit.benefitId = "+positiveNegivId+" ");  
    	}
       if(fromDate != null){
  		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
  	   }
  	   if(toDate != null){
  		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
  	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	return query;
    }
    
    public StringBuilder getTotalNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	 query.append(" select count(distinct cpf.file.fileId) from CandidatePartyFile cpf " );
    	 query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	 query.append(" cpf.sourceCandidate.candidateId is null and cpf.sourceParty.partyId is null  "); 
    	 if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpf.destinationCandidate.candidateId =:candidateId ");
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpf.destinationParty.partyId =:partyId ");
    	}
    	   if(fromDate != null){
 		       query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	 	   }
	 	   if(toDate != null){
	 		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	 	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	return query;
    }
    
    public List<FileVO> getAnalysedNews(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long onPartyId,String type,String benifit,Integer startIndex,Integer maxIndex,Long categoryId,Long sourceId,Long otherPartyId,Long keywordId){
    	List<Long> fileIds = new ArrayList<Long>();
    	LinkedHashMap<Long,FileVO> fileMap = new LinkedHashMap<Long, FileVO>();
    	if(type.equalsIgnoreCase("total")){
    		if(benifit.equalsIgnoreCase("total")){
    		  StringBuilder totalCountQuery = getTotalNewsCountQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,categoryId,sourceId,keywordId);
	    	  StringBuilder totalNewsQuery = getTotalNewsQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,categoryId,sourceId,keywordId);
    		  Long totalNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    		  List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
    		  candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalNewsCount);
    	    }else if(benifit.equalsIgnoreCase("positive")){
    	      StringBuilder totalPositiveCountQuery = getPostivNegivNewsCountQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1L,categoryId,sourceId,keywordId);
    		  StringBuilder totalPositiveNewsQuery = getPostivNegivNewsQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1L,categoryId,sourceId,keywordId);
    		  Long totalPositiveCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalPositiveCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    		  List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalPositiveNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
    		  candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalPositiveCount);
    	    }else if(benifit.equalsIgnoreCase("negative")){
    	      StringBuilder totalNegativeCountQuery = getPostivNegivNewsCountQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2L,categoryId,sourceId,keywordId);
    	      StringBuilder  totalNegativeNewsQuery = getPostivNegivNewsQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2L,categoryId,sourceId,keywordId);
    		  Long totalNegativeCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalNegativeCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    		  List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalNegativeNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
    		  candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalNegativeCount);
    	    }
    	}else if(type.equalsIgnoreCase("own")){
	    	if(candidateId == null || candidateId.longValue() == 0){
	    	 if(benifit.equalsIgnoreCase("total")){	
	    	    StringBuilder totalOwnNewsCountQuery = getPartyOwnNewsCountQuery(fromDate,toDate,partyId,locationLvl,locationIds,categoryId,sourceId,keywordId);
	    	    StringBuilder totalOwnNewsQuery = getPartyOwnNewsQuery(fromDate,toDate,partyId,locationLvl,locationIds,categoryId,sourceId,keywordId);
	    	    Long totalOwnNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalOwnNewsCountQuery.toString(), fromDate, toDate, partyId, candidateId);
	    	    List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalOwnNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
    		    candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalOwnNewsCount);
	    	 }else if(benifit.equalsIgnoreCase("negative")){
	    		 StringBuilder totalOwnNegNewsCountQuery = getPartyOwnNegativeNewsCountQuery(fromDate,toDate,partyId,locationLvl,locationIds,categoryId,sourceId,keywordId);
	    	     StringBuilder totalOwnNegNewsQuery = getPartyOwnNegativeNewsQuery(fromDate,toDate,partyId,locationLvl,locationIds,categoryId,sourceId,keywordId);
	    	     Long totalOwnNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalOwnNegNewsCountQuery.toString(), fromDate, toDate, partyId, candidateId);
	    	     List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalOwnNegNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
    		     candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalOwnNewsCount); 
	    	 }
	    	}
    	}else if(type.equalsIgnoreCase("other")){
    		if(benifit.equalsIgnoreCase("total")){
    			StringBuilder totalCountQuery = null;
    			StringBuilder totalNewsQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCountQuery = tdpEffectOnOthersPartyWiseTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,null,otherPartyId,"count",keywordId);
    	    	    totalNewsQuery = tdpEffectOnOthersPartyWiseTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,null,otherPartyId,"news",keywordId);
    			}else{
    				totalCountQuery = tdpEffectOnOtherPartiesTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,"count",keywordId);
    				totalNewsQuery = tdpEffectOnOtherPartiesTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,"news",keywordId);
    			}
    			Long totalNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalCountQuery.toString(), fromDate, toDate, partyId, candidateId);
      		  List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
      		  candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalNewsCount);
      	    }else if(benifit.equalsIgnoreCase("positive") || benifit.equalsIgnoreCase("negative")){
      	    	Long benfitId= 1l;
      	    	if(benifit.equalsIgnoreCase("negative")){
      	    		benfitId= 2l;
      	    	}
      	    	StringBuilder totalCountQuery = null;
    			StringBuilder totalNewsQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCountQuery = tdpEffectOnOthersPartyWiseBenifitNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,otherPartyId,"count",keywordId);
    				totalNewsQuery = tdpEffectOnOthersPartyWiseBenifitNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,otherPartyId,"news",keywordId);
    			}else{
    				totalCountQuery = tdpEffectOnOtherPartiesBenifitWiseNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,"count",keywordId);
    	    		totalNewsQuery = tdpEffectOnOtherPartiesBenifitWiseNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,"news",keywordId);
    			}
    			Long totalNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalCountQuery.toString(), fromDate, toDate, partyId, candidateId);
      		  List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
      		  candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalNewsCount);
      	    }
    	}else if(type.equalsIgnoreCase("onme")){
    		if(benifit.equalsIgnoreCase("total")){
    			StringBuilder totalCountQuery = null;
    			StringBuilder totalNewsQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCountQuery = otherPartiesWiseEffectOnTdpTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,null,otherPartyId,"count",keywordId);
    	    	    totalNewsQuery = otherPartiesWiseEffectOnTdpTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,null,otherPartyId,"news",keywordId);
    			}else{
    				totalCountQuery = otherPartiesOnTdpEffectTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,"count",keywordId);
    				totalNewsQuery = otherPartiesOnTdpEffectTotalNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,"news",keywordId);
    			}
    			Long totalNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalCountQuery.toString(), fromDate, toDate, partyId, candidateId);
      		  List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
      		  candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalNewsCount);
      	    }else if(benifit.equalsIgnoreCase("positive") || benifit.equalsIgnoreCase("negative")){
      	    	Long benfitId= 1l;
      	    	if(benifit.equalsIgnoreCase("negative")){
      	    		benfitId= 2l;
      	    	}
      	    	StringBuilder totalCountQuery = null;
    			StringBuilder totalNewsQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCountQuery = otherPartiesWiseEffectOnTdpBenifitNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,otherPartyId,"count",keywordId);
    				totalNewsQuery = otherPartiesWiseEffectOnTdpBenifitNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,otherPartyId,"news",keywordId);
    			}else{
    				totalCountQuery = otherPartiesEffectOnTdpBenifitWiseNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,"count",keywordId);
    	    		totalNewsQuery = otherPartiesEffectOnTdpBenifitWiseNews(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,categoryId,sourceId,benfitId,"news",keywordId);
    			}
    			Long totalNewsCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalCountQuery.toString(), fromDate, toDate, partyId, candidateId);
      		  List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
      		  candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalNewsCount);
      	    }
    	}else if(type.equalsIgnoreCase("media")){
    	   if(benifit.equalsIgnoreCase("total")){
    		   StringBuilder totalMediaCountQuery = getTotalNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,categoryId,sourceId,keywordId);
	    	   StringBuilder totalMediaQuery = getTotalNewsInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,categoryId,sourceId,keywordId);
	    	   Long totalMediaCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalMediaCountQuery.toString(), fromDate, toDate, partyId, candidateId);
	    	   List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalMediaQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
	    	   candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalMediaCount);
    	   }else if(benifit.equalsIgnoreCase("positive")){
    		   StringBuilder totalPositiveMediaCountQuery = getPostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1l,categoryId,sourceId,keywordId);
    		   StringBuilder totalPositiveMediaNewsQuery = getPostivNegivNewsInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1l,categoryId,sourceId,keywordId);
   	    	   Long totalPositiveMediaCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalPositiveMediaCountQuery.toString(), fromDate, toDate, partyId, candidateId); 
   	    	   List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalPositiveMediaNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
	    	   candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalPositiveMediaCount);
    	   }else if(benifit.equalsIgnoreCase("negative")){
    		   StringBuilder totalNegMediaCountQuery = getPostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2l,categoryId,sourceId,keywordId);
    		   StringBuilder totalNegMediaNewsQuery = getPostivNegivNewsInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2l,categoryId,sourceId,keywordId);
    		   Long totalNegMediaCount = candidatePartyFileDAO.getNewsCountBySelectedCriteria(totalNegMediaCountQuery.toString(), fromDate, toDate, partyId, candidateId);
    		   List<Object[]> files = candidatePartyFileDAO.getNewsByCriteria(totalNegMediaNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
	    	   candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, totalNegMediaCount);
    	   }
    	}
    	return new ArrayList<FileVO>(fileMap.values());
    }
    public StringBuilder getTotalNewsQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	query.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
				" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" (cpf.sourceCandidate.candidateId =:candidateId or cpf.destinationCandidate.candidateId =:candidateId )"); 
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" ( cpf.sourceParty.partyId =:partyId or cpf.destinationParty.partyId =:partyId )");
    	}
    	if(fromDate != null){
 		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
 	    }
 	    if(toDate != null){
 		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
 	    }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	query.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
    	return query;
    }
    public StringBuilder getCategorySourceQuery(Long categoryId,Long sourceId,Long keywordId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder();
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ,UserAddress ua ");
    	}
    	if(categoryId != null && categoryId.longValue() > 0){
    		query.append(" ,CandidatePartyCategory cpc where cpf.file.isDeleted != 'Y' and cpf.candidatePartyFileId = cpc.candidatePartyFile.candidatePartyFileId and cpc.gallary.gallaryId ="+categoryId+" and ");
    	}else if(sourceId != null && sourceId.longValue() > 0 ){
    		query.append(" ,FileSourceLanguage fsl where cpf.file.isDeleted != 'Y' and fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId ="+sourceId+"  and ");
    	}else if(keywordId != null && keywordId.longValue() > 0 ){
    		query.append(" ,CandidatePartyKeyword cpk where cpf.file.isDeleted != 'Y' and cpf.candidatePartyFileId = cpk.candidatePartyFile.candidatePartyFileId and cpk.keyword.keywordId ="+keywordId+" and ");
    	}else{
    		query.append(" where cpf.file.isDeleted != 'Y' and ");
    	}
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ua.file.fileId = cpf.file.fileId and ");
    	}
    	return query;
    }
    public StringBuilder getPostivNegivNewsQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long positiveNegivId,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	query.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
				" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId from CandidatePartyFile cpf  ");
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	if(candidateId != null && candidateId.longValue() > 0){  
           query.append(" ((cpf.sourceCandidate.candidateId =:candidateId and cpf.sourceBenefit.benefitId = "+positiveNegivId+" ) or (cpf.destinationCandidate.candidateId =:candidateId  and cpf.destinationBenefit.benefitId = "+positiveNegivId+" ))");
    	}else if(partyId != null && partyId.longValue() > 0){
    	   query.append(" ( (cpf.sourceParty.partyId =:partyId  and cpf.sourceBenefit.benefitId = "+positiveNegivId+" ) or (cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId = "+positiveNegivId+" ))");
    	}
    	   if(fromDate != null){
 		       query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	 	   }
	 	   if(toDate != null){
	 		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	 	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	query.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
    	return query;
    }
    
    public StringBuilder getPartyOwnNewsQuery(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder(); 
    	query.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
				" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId from CandidatePartyFile cpf ");
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    			query.append("  cpf.sourceParty.partyId =:partyId and cpf.destinationParty.partyId =:partyId ");
	    if(fromDate != null){
		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	    }
	    if(toDate != null){
		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	    }
	    query.append(""+addLocationString(locationLvl,locationIds)+"");
	    query.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
       	return query;
    }
    
    public StringBuilder getPartyOwnNegativeNewsQuery(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder(); 
    	query.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
				" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId from CandidatePartyFile cpf ");
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    			query.append("  cpf.sourceParty.partyId =:partyId and cpf.destinationParty.partyId =:partyId " +
    					" and cpf.destinationBenefit.benefitId = 2 ");
	    if(fromDate != null){
		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	    }
	    if(toDate != null){
		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	    }
	    query.append(""+addLocationString(locationLvl,locationIds)+"");
	    query.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
       	return query;
    }
    
    public StringBuilder getPostivNegivNewsInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long positiveNegivId,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	query.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
				" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId from CandidatePartyFile cpf ");
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	query.append("  cpf.sourceCandidate.candidateId is null and cpf.sourceParty.partyId is null  " );
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpf.destinationCandidate.candidateId =:candidateId and cpf.destinationBenefit.benefitId is not null  and cpf.destinationBenefit.benefitId ="+positiveNegivId+" "); 	   
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId is not null and cpf.destinationBenefit.benefitId  ="+positiveNegivId+" ");
	    	  
    	}
       if(fromDate != null){
  		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
  	   }
  	   if(toDate != null){
  		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
  	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	query.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
    	return query;
    }
    
    public StringBuilder getTotalNewsInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long categoryId,Long sourceId,Long keywordId){
    	StringBuilder query = new StringBuilder();
    	query.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
				" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId from CandidatePartyFile cpf ");
    	query.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,locationLvl,locationIds)+"  " );
    	query.append("  cpf.sourceCandidate.candidateId is null and cpf.sourceParty.partyId is null   " );
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpf.destinationCandidate.candidateId =:candidateId ");
    	}else if(partyId != null && partyId.longValue() > 0){	 
    		query.append(" and cpf.destinationParty.partyId =:partyId ");
    	}
    	   if(fromDate != null){
 		       query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	 	   }
	 	   if(toDate != null){
	 		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	 	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	query.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
    	return query;
    }
    
    public List<NewsAnalysisVO> getSourceCategoryCount(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,String type,String benifit,Integer startIndex,Integer maxIndex,Long otherPartyId){
    	List<NewsAnalysisVO> returnList = new ArrayList<NewsAnalysisVO>();
    	if(type.equalsIgnoreCase("total")){
    		if(benifit.equalsIgnoreCase("total")){
    			StringBuilder totalCategoryQuery = getTotalNewsCountAttributeWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"category");
    			StringBuilder totalNewsQuery = getTotalNewsCountAttributeWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"source");
    			StringBuilder totalKeywordQuery = getTotalNewsCountAttributeWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"keyword");
    			List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        getData(categoryList,sourceList,returnList,keywordList);
      	    }else if(benifit.equalsIgnoreCase("positive") || benifit.equalsIgnoreCase("negative")){
      	    	Long benfitId= 1l;
      	    	if(benifit.equalsIgnoreCase("negative")){
      	    		benfitId= 2l;
      	    	}
    			StringBuilder totalCategoryQuery = getPostivNegivNewsCountAttributeWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"category");
    			StringBuilder totalNewsQuery = getPostivNegivNewsCountAttributeWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"source");
    			StringBuilder totalKeywordQuery = getPostivNegivNewsCountAttributeWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"keyword");
    			List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        getData(categoryList,sourceList,returnList,keywordList);
      	    }   				
    	}else if(type.equalsIgnoreCase("own")){
	    	if(candidateId == null || candidateId.longValue() == 0){
	    	  if(benifit.equalsIgnoreCase("total")){
		    	  StringBuilder totalOwnNewsCategoryCountQuery = getPartyOwnNewsCountCategoryWise(fromDate,toDate,partyId,locationLvl,locationIds);
		    	  StringBuilder totalOwnNewsSourceCountQuery = getPartyOwnNewsCountSourceWise(fromDate,toDate,partyId,locationLvl,locationIds);
		    	  StringBuilder totalOwnNewsKeywordCountQuery = getPartyOwnNewsCountKeywordWise(fromDate,toDate,partyId,locationLvl,locationIds);
		    	  List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalOwnNewsCategoryCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		    	  List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalOwnNewsSourceCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		    	  List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalOwnNewsKeywordCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		    	  getData(categoryList,sourceList,returnList,keywordList);
	    	  }else if(benifit.equalsIgnoreCase("negative")){
	    		  StringBuilder negOwnNewsCategoryCountQuery = getPartyOwnNegNewsCountCategoryWise(fromDate,toDate,partyId,locationLvl,locationIds,"category");
		    	  StringBuilder negOwnNewsSourceCountQuery = getPartyOwnNegNewsCountCategoryWise(fromDate,toDate,partyId,locationLvl,locationIds,"source");
		    	  StringBuilder negOwnNewsKeywordCountQuery = getPartyOwnNegNewsCountCategoryWise(fromDate,toDate,partyId,locationLvl,locationIds,"keyword");
		    	  List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(negOwnNewsCategoryCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		    	  List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(negOwnNewsSourceCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		    	  List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(negOwnNewsKeywordCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		    	  getData(categoryList,sourceList,returnList,keywordList);
	    	  }
	    	}
    	}else if(type.equalsIgnoreCase("other")){
    		if(benifit.equalsIgnoreCase("total")){
    			StringBuilder totalCategoryQuery = null;
    			StringBuilder totalNewsQuery = null;
    			StringBuilder totalKeywordQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCategoryQuery = tdpEffectOnOthersPartyWiseTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,null,otherPartyId,"category");
    	    	    totalNewsQuery = tdpEffectOnOthersPartyWiseTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,null,otherPartyId,"source");
    	    	    totalKeywordQuery = tdpEffectOnOthersPartyWiseTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,null,otherPartyId,"keyword");
    			}else{
    				totalCategoryQuery = tdpEffectOnOtherPartiesTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"category");
    				totalNewsQuery = tdpEffectOnOtherPartiesTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"source");
    				totalKeywordQuery = tdpEffectOnOtherPartiesTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"keyword");
    			}
    			List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		       List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        getData(categoryList,sourceList,returnList,keywordList);
      	    }else if(benifit.equalsIgnoreCase("positive") || benifit.equalsIgnoreCase("negative")){
      	    	Long benfitId= 1l;
      	    	if(benifit.equalsIgnoreCase("negative")){
      	    		benfitId= 2l;
      	    	}
      	    	StringBuilder totalCategoryQuery = null;
    			StringBuilder totalNewsQuery = null;
    			StringBuilder totalKeywordQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCategoryQuery = tdpEffectOnOthersPartyWiseBenifitCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,otherPartyId,"category");
    				totalNewsQuery = tdpEffectOnOthersPartyWiseBenifitCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,otherPartyId,"source");
    				totalKeywordQuery = tdpEffectOnOthersPartyWiseBenifitCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,otherPartyId,"keyword");
    			}else{
    				totalCategoryQuery = tdpEffectOnOtherPartiesBenifitWiseCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"category");
    	    		totalNewsQuery = tdpEffectOnOtherPartiesBenifitWiseCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"source");
    	    		totalKeywordQuery = tdpEffectOnOtherPartiesBenifitWiseCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"keyword");
    			}
    			List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		       List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        getData(categoryList,sourceList,returnList,keywordList);
      	    }
    	}else if(type.equalsIgnoreCase("onme")){
    		if(benifit.equalsIgnoreCase("total")){
    			StringBuilder totalCategoryQuery = null;
    			StringBuilder totalNewsQuery = null;
    			StringBuilder totalKeywordQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCategoryQuery = otherPartiesWiseEffectOnTdpTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,null,otherPartyId,"category");
    	    	    totalNewsQuery = otherPartiesWiseEffectOnTdpTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,null,otherPartyId,"source");
    	    	    totalKeywordQuery = otherPartiesWiseEffectOnTdpTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,null,otherPartyId,"keyword");
    			}else{
    				totalCategoryQuery = otherPartiesOnTdpEffectTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"category");
    				totalNewsQuery = otherPartiesOnTdpEffectTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"source");
    				totalKeywordQuery = otherPartiesOnTdpEffectTotalCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,"keyword");
    			}
    			List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		       List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        getData(categoryList,sourceList,returnList,keywordList);
      	    }else if(benifit.equalsIgnoreCase("positive") || benifit.equalsIgnoreCase("negative")){
      	    	Long benfitId= 1l;
      	    	if(benifit.equalsIgnoreCase("negative")){
      	    		benfitId= 2l;
      	    	}
      	    	StringBuilder totalCountQuery = null;
    			StringBuilder totalNewsQuery = null;
    			StringBuilder totalKeywordQuery = null;
    			if(otherPartyId != null && otherPartyId.longValue() > 0){
    				totalCountQuery = otherPartiesWiseEffectOnTdpBenifitCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,otherPartyId,"category");
    				totalNewsQuery = otherPartiesWiseEffectOnTdpBenifitCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,otherPartyId,"source");
    				totalKeywordQuery = otherPartiesWiseEffectOnTdpBenifitCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,otherPartyId,"keyword");
    			}else{
    				totalCountQuery = otherPartiesEffectOnTdpBenifitWiseCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"category");
    	    		totalNewsQuery = otherPartiesEffectOnTdpBenifitWiseCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"source");
    	    		totalKeywordQuery = otherPartiesEffectOnTdpBenifitWiseCountAttrWise(partyId,candidateId,locationLvl,locationIds,fromDate,toDate,benfitId,"keyword");
    			}
    			List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		       List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
 		        getData(categoryList,sourceList,returnList,keywordList);
      	    }
    	}else if(type.equalsIgnoreCase("media")){
    	   if(benifit.equalsIgnoreCase("total")){
    		   StringBuilder totalCategoryMediaCountQuery = getCategoryTotalNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds);
	    	   StringBuilder totalSourceMediaCountQuery =   getSourceTotalNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds);
	    	   StringBuilder totalKeywordMediaCountQuery =  getKeywordTotalNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds);
	    	   List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryMediaCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalSourceMediaCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordMediaCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       getData(categoryList,sourceList,returnList,keywordList);
    	   }else if(benifit.equalsIgnoreCase("positive")){
    		   StringBuilder totalCategoryPositiveMediaCountQuery = getCategoryPostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1l);
    		   StringBuilder totalSourcePositiveMediaNewsQuery = getSourcePostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1l);
    		   StringBuilder totalKeywordPositiveMediaNewsQuery = getKeywordPostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,1l);
    		   List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryPositiveMediaCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalSourcePositiveMediaNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordPositiveMediaNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       getData(categoryList,sourceList,returnList,keywordList);
    	   }else if(benifit.equalsIgnoreCase("negative")){
    		   StringBuilder totalCategoryPositiveMediaCountQuery = getCategoryPostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2l);
    		   StringBuilder totalSourcePositiveMediaNewsQuery = getSourcePostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2l);
    		   StringBuilder totalKeywordPositiveMediaNewsQuery = getKeywordPostivNegivNewsCountInMediaQuery(fromDate,toDate,partyId,candidateId,locationLvl,locationIds,2l);
    		   List<Object[]> categoryList = candidatePartyFileDAO.getNewsByCriteria(totalCategoryPositiveMediaCountQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       List<Object[]> sourceList = candidatePartyFileDAO.getNewsByCriteria(totalSourcePositiveMediaNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       List<Object[]> keywordList = candidatePartyFileDAO.getNewsByCriteria(totalKeywordPositiveMediaNewsQuery.toString(),fromDate,toDate,partyId,candidateId,startIndex,maxIndex);
		       getData(categoryList,sourceList,returnList,keywordList);
    	   }
    	}
    	return returnList;
    }
    
    public void getData(List<Object[]> categoryList,List<Object[]> sourceList,List<NewsAnalysisVO> returnList,List<Object[]> keywordList){
       NewsAnalysisVO category = populateToVO(categoryList);
 	   NewsAnalysisVO source = populateToVO(sourceList);
 	   NewsAnalysisVO keyword = populateToVO(keywordList);
 	   if(category != null){
 		  category.setName("Category");
 		  Collections.sort(category.getSubList(),countCompare);
 		  returnList.add(category);
 	   }
 	   if(source != null){
 		  source.setName("Source");
 		 Collections.sort(source.getSubList(),countCompare);
 		  returnList.add(source);
 	   }
 	  if(keyword != null){
 		 keyword.setName("Keyword");
 		 Collections.sort(keyword.getSubList(),countCompare);
 		  returnList.add(keyword);
 	   }
    }
    public NewsAnalysisVO populateToVO(List<Object[]> newsCountList){
    	NewsAnalysisVO newsCount = null;
    	if(newsCountList != null && newsCountList.size() > 0){
    		newsCount = new NewsAnalysisVO();
    		List<NewsAnalysisVO> list = new ArrayList<NewsAnalysisVO>();
    		newsCount.setSubList(list);
	    	for(Object[] news:newsCountList){
	    		NewsAnalysisVO vo = new NewsAnalysisVO();
	    		if(news[2] != null){
	    		 vo.setName(news[2].toString());
	    		}
	    		 vo.setId((Long)news[1]);
	    		 vo.setTotal((Long)news[0]);
	    		 list.add(vo);
	    	}
    	}
    	return newsCount;
    }
    
    public StringBuilder getPartyOwnNegNewsCountCategoryWise(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds,String type){
    	StringBuilder str = new StringBuilder(); 
		 str.append(getAttributeSelectQuery(type,locationLvl,locationIds));
	   		str.append("  cpf.sourceParty.partyId =:partyId and cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId = 2 ");
	    str.append(addLocaionAndDateQuery(locationLvl,locationIds,fromDate,toDate));
				 str.append(getAttributeGroupByQuery(type));
       	return str;
    }    
    
    public StringBuilder getPartyOwnNewsCountCategoryWise(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder(); 
    	query.append(" select count(distinct cpc.candidatePartyFile.file.fileId),cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc ");
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ,UserAddress ua where cpc.candidatePartyFile.file.fileId = ua.file.fileId and cpc.candidatePartyFile.file.isDeleted != 'Y' and ");
    	}else{
    		query.append(" where cpc.candidatePartyFile.file.isDeleted != 'Y' and ");
    	}
    	query.append(" cpc.candidatePartyFile.sourceParty.partyId =:partyId and cpc.candidatePartyFile.destinationParty.partyId =:partyId ");
	    if(fromDate != null){
		   query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
	    }
	    if(toDate != null){
		   query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
	    }
	    query.append(""+addLocationStringForCategory(locationLvl,locationIds)+"");
	    query.append(" group by cpc.gallary.gallaryId ");
       	return query;
    }
    
    public StringBuilder addLocationStringForCategory(Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder(); 
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		if(locationLvl.longValue() == 1){
				 query.append( " and ua.district.districtId in ("+locationIds+") ");
			}else if(locationLvl.longValue() == 2){
				query.append(" and ua.parliamentConstituency.constituencyId in ("+locationIds+")");
			}else if(locationLvl.longValue() == 3){
				query.append(" and ua.constituency.constituencyId in ("+locationIds+") ");
			}
    	}
    	return query;
    }
    
    public StringBuilder addLocationStringForKeyword(Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder(); 
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		if(locationLvl.longValue() == 1){
				 query.append( " and ua.district.districtId in ("+locationIds+") ");
			}else if(locationLvl.longValue() == 2){
				query.append(" and ua.parliamentConstituency.constituencyId in ("+locationIds+")");
			}else if(locationLvl.longValue() == 3){
				query.append(" and ua.constituency.constituencyId in ("+locationIds+") ");
			}
    	}
    	return query;
    }
    
    public StringBuilder getPartyOwnNewsCountSourceWise(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder(); 
    	query.append(" select count(distinct fsl.file.fileId),fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf ");
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ,UserAddress ua where fsl.file.fileId = ua.file.fileId and fsl.file.isDeleted != 'Y' and ");
    	}else{
    		query.append(" where fsl.file.isDeleted != 'Y' and  ");
    	}	
    	
    	query.append("  fsl.file.fileId = cpf.file.fileId  and cpf.sourceParty.partyId =:partyId and cpf.destinationParty.partyId =:partyId ");
	    if(fromDate != null){
		   query.append(" and date(fsl.file.fileDate) >= :fromDate ");
	    }
	    if(toDate != null){
		   query.append(" and date(fsl.file.fileDate) <= :toDate ");
	    }
	    query.append(""+addLocationString(locationLvl,locationIds)+"");
	    query.append(" group by fsl.source.sourceId ");
       	return query;
    }
    
    public StringBuilder getPartyOwnNewsCountKeywordWise(Date fromDate,Date toDate,Long partyId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder(); 
    	query.append(" select count(distinct cpk.candidatePartyFile.file.fileId),cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk " );
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ,UserAddress ua where cpk.candidatePartyFile.file.fileId = ua.file.fileId and cpk.candidatePartyFile.file.isDeleted != 'Y' and ");
    	}else{
    	   query.append("where cpk.candidatePartyFile.file.isDeleted != 'Y' and  " );
    	}
    	query.append("  cpk.candidatePartyFile.sourceParty.partyId =:partyId and cpk.candidatePartyFile.destinationParty.partyId =:partyId ");
	    if(fromDate != null){
		   query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
	    }
	    if(toDate != null){
		   query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
	    }
	    query.append(""+addLocationStringForKeyword(locationLvl,locationIds)+"");
	    query.append(" group by cpk.keyword.keywordId ");
       	return query;
    }
    
    public StringBuilder getCategoryPostivNegivNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long positiveNegivId){
    	StringBuilder query = new StringBuilder();
    	query.append("select count(distinct cpc.candidatePartyFile.file.fileId),cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc  ");
    	
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ,UserAddress ua where cpc.candidatePartyFile.file.fileId = ua.file.fileId and cpc.candidatePartyFile.file.isDeleted != 'Y' ");
    	}else{
    		query.append(" where cpc.candidatePartyFile.file.isDeleted != 'Y' ");
    	}
    	query.append(" and cpc.candidatePartyFile.sourceCandidate.candidateId is null and cpc.candidatePartyFile.sourceParty.partyId is null ");
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId =:candidateId and cpc.candidatePartyFile.destinationBenefit.benefitId is not null  and cpc.candidatePartyFile.destinationBenefit.benefitId = "+positiveNegivId+" "); 	   
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpc.candidatePartyFile.destinationParty.partyId =:partyId and cpc.candidatePartyFile.destinationBenefit.benefitId is not null and cpc.candidatePartyFile.destinationBenefit.benefitId = "+positiveNegivId+" ");  	  
    	}
       if(fromDate != null){
  		   query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
  	   }
  	   if(toDate != null){
  		   query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
  	   }
    	query.append(""+addLocationStringForCategory(locationLvl,locationIds)+"");
    	query.append(" group by cpc.gallary.gallaryId ");
    	return query;
    }
    public StringBuilder getKeywordPostivNegivNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long positiveNegivId){
    	StringBuilder query = new StringBuilder();
    	 query.append(" select count(distinct cpk.candidatePartyFile.file.fileId),cpk.keyword.keywordId,cpk.keyword.type  from CandidatePartyKeyword cpk ");
    	 if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
      		query.append(" ,UserAddress ua where cpk.candidatePartyFile.file.fileId = ua.file.fileId and cpk.candidatePartyFile.file.isDeleted != 'Y'  ");
      	}else{
      		 query.append(" where cpk.candidatePartyFile.file.isDeleted != 'Y' " );
      	}	
    	 query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId is null and cpk.candidatePartyFile.sourceParty.partyId is null  ");
    	if(candidateId != null && candidateId.longValue() > 0){
    	 query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId =:candidateId and cpk.candidatePartyFile.destinationBenefit.benefitId is not null  and cpk.candidatePartyFile.destinationBenefit.benefitId = "+positiveNegivId+" "); 	   
    	}else if(partyId != null && partyId.longValue() > 0){ 
    	 query.append(" and cpk.candidatePartyFile.destinationParty.partyId =:partyId and cpk.candidatePartyFile.destinationBenefit.benefitId is not null and cpk.candidatePartyFile.destinationBenefit.benefitId = "+positiveNegivId+" "); 	  
    	}
       if(fromDate != null){
  		   query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
  	   }
  	   if(toDate != null){
  		   query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
  	   }
    	query.append(""+addLocationStringForKeyword(locationLvl,locationIds)+"");
    	query.append(" group by cpk.keyword.keywordId ");
    	return query;
    }
    public StringBuilder getCategoryTotalNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder();
    	query.append("select count(distinct cpc.candidatePartyFile.file.fileId),cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc ");
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ,UserAddress ua where cpc.candidatePartyFile.file.fileId = ua.file.fileId and cpc.candidatePartyFile.file.isDeleted != 'Y' ");
    	}else{
    	   query.append(" where cpc.candidatePartyFile.file.isDeleted != 'Y' ");
    	}
    	query.append("and cpc.candidatePartyFile.sourceCandidate.candidateId is null and cpc.candidatePartyFile.sourceParty.partyId is null   ");
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpc.candidatePartyFile.destinationCandidate.candidateId =:candidateId ");
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpc.candidatePartyFile.destinationParty.partyId =:partyId ");
    	}
    	   if(fromDate != null){
 		       query.append(" and date(cpc.candidatePartyFile.file.fileDate) >= :fromDate ");
	 	   }
	 	   if(toDate != null){
	 		   query.append(" and date(cpc.candidatePartyFile.file.fileDate) <= :toDate ");
	 	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	query.append(" group by cpc.gallary.gallaryId ");
    	return query;
    }
    public StringBuilder getKeywordTotalNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder();
    	 query.append("select count(distinct cpk.candidatePartyFile.file.fileId),cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk ");
    	 if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
     		query.append(" ,UserAddress ua where cpk.candidatePartyFile.file.fileId = ua.file.fileId and cpk.candidatePartyFile.file.isDeleted != 'Y'  ");
     	}else{
     		 query.append(" where cpk.candidatePartyFile.file.isDeleted != 'Y' " );
     	}	
    	 query.append(" and cpk.candidatePartyFile.sourceCandidate.candidateId is null and cpk.candidatePartyFile.sourceParty.partyId is null   " );
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpk.candidatePartyFile.destinationCandidate.candidateId =:candidateId ");
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpk.candidatePartyFile.destinationParty.partyId =:partyId ");
    	}
    	   if(fromDate != null){
 		       query.append(" and date(cpk.candidatePartyFile.file.fileDate) >= :fromDate ");
	 	   }
	 	   if(toDate != null){
	 		   query.append(" and date(cpk.candidatePartyFile.file.fileDate) <= :toDate ");
	 	   }
    	query.append(""+addLocationStringForKeyword(locationLvl,locationIds)+"");
    	query.append(" group by cpk.keyword.keywordId ");
    	return query;
    }
    public StringBuilder getSourcePostivNegivNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds,Long positiveNegivId){
    	StringBuilder query = new StringBuilder();
    	 query.append("select count(distinct fsl.file.fileId),fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf  ");
    	 if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
     		query.append(" ,UserAddress ua where fsl.file.fileId = ua.file.fileId and fsl.file.isDeleted != 'Y' ");
     	}else{
     		query.append(" where fsl.file.isDeleted != 'Y'   ");
     	}	
    	 query.append(" and fsl.file.fileId = cpf.file.fileId and cpf.sourceCandidate.candidateId is null and cpf.sourceParty.partyId is null " );
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpf.destinationCandidate.candidateId =:candidateId and cpf.destinationBenefit.benefitId is not null  and cpf.destinationBenefit.benefitId = "+positiveNegivId+" "); 	   
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId is not null and cpf.destinationBenefit.benefitId = "+positiveNegivId+" ");	  
    	}
       if(fromDate != null){
  		   query.append(" and date(cpf.file.fileDate) >= :fromDate ");
  	   }
  	   if(toDate != null){
  		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
  	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	query.append(" group by fsl.source.sourceId ");
    	return query;
    }
    
    public StringBuilder getSourceTotalNewsCountInMediaQuery(Date fromDate,Date toDate,Long partyId,Long candidateId,Long locationLvl,String locationIds){
    	StringBuilder query = new StringBuilder();
    	query.append("select count(distinct fsl.file.fileId),fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf  " );
    	if(locationLvl != null && locationIds != null && locationLvl.longValue() > 0 && locationIds.trim().length() > 0){
    		query.append(" ,UserAddress ua where fsl.file.fileId = ua.file.fileId and fsl.file.isDeleted != 'Y' ");
    	}else{
    		query.append(" where fsl.file.isDeleted != 'Y'  ");
    	}	
    	query.append(" and fsl.file.fileId = cpf.file.fileId and cpf.sourceCandidate.candidateId is null and cpf.sourceParty.partyId is null ");
    	if(candidateId != null && candidateId.longValue() > 0){
    		query.append(" and cpf.destinationCandidate.candidateId =:candidateId ");
    	}else if(partyId != null && partyId.longValue() > 0){
    		query.append(" and cpf.destinationParty.partyId =:partyId ");
    	}
    	   if(fromDate != null){
 		       query.append(" and date(cpf.file.fileDate) >= :fromDate ");
	 	   }
	 	   if(toDate != null){
	 		   query.append(" and date(cpf.file.fileDate) <= :toDate ");
	 	   }
    	query.append(""+addLocationString(locationLvl,locationIds)+"");
    	query.append(" group by fsl.source.sourceId ");
    	return query;
    }
    
    public FileVO getAnalysisDetails(Long partyId,Long candidateId,Long locationLevelId,String locationLevelValue,Date fromdate,Date todate)
    {
    	FileVO result = new FileVO();
    	
    	try{
    	NewsAnalysisVO tdpEffectnewsAnalysisVO = new NewsAnalysisVO();
    	List<NewsAnalysisVO> tdpEffectonOtherPartyvos = new ArrayList<NewsAnalysisVO>();
		
		
		//for tdp targeting on other parties
    	Long tdpTotalCount = candidatePartyFileDAO.tdpEffectOnOtherPartiesTotalCount(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	List<Object[]> tdpEffectCount = candidatePartyFileDAO.tdpEffectOnOtherPartiesBenifitWiseCount(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	List<Object[]> otherPartiesTotalCount = candidatePartyFileDAO.tdpEffectOnOthersPartyWiseTotalCount(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	List<Object[]> otherPartiesEffectCount = candidatePartyFileDAO.tdpEffectOnOthersPartyWiseBenifitCount(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	
    	tdpEffectnewsAnalysisVO.setTotal(tdpTotalCount);
    	for(Object[] tdpEffect:tdpEffectCount){
    		if((Long)tdpEffect[1] == 1)
    			tdpEffectnewsAnalysisVO.setPositiveCount((Long)tdpEffect[0]);
    		if((Long)tdpEffect[1] == 2)
    			tdpEffectnewsAnalysisVO.setNegativeCount((Long)tdpEffect[0]);
    	}
    	
    	for(Object[] otherPartiesTotalEffect:otherPartiesTotalCount){
    		NewsAnalysisVO newsAnalysisVO1 = new NewsAnalysisVO();
    		newsAnalysisVO1.setId((Long)otherPartiesTotalEffect[1]);
    		newsAnalysisVO1.setName(otherPartiesTotalEffect[2].toString());
    		newsAnalysisVO1.setTotal((Long)otherPartiesTotalEffect[0]);
    		tdpEffectonOtherPartyvos.add(newsAnalysisVO1);
    	}
    	tdpEffectnewsAnalysisVO.setSubList(tdpEffectonOtherPartyvos);
    	for(Object[] otherPartiesEffect:otherPartiesEffectCount){
    		NewsAnalysisVO newsAnalysisVO1 = null;
    			newsAnalysisVO1 = checkVoExistance((Long)otherPartiesEffect[2],tdpEffectnewsAnalysisVO.getSubList());
    			if(newsAnalysisVO1 != null)
	  			  {
        			if((Long)otherPartiesEffect[1]==1)
            			newsAnalysisVO1.setPositiveCount((Long)otherPartiesEffect[0]);
            		if((Long)otherPartiesEffect[1]==2)
        				newsAnalysisVO1.setNegativeCount((Long)otherPartiesEffect[0]);
	  			  }
    	}
    	
    	//other parties targeting on tdp party
    	Long otherPartyTotalCount = candidatePartyFileDAO.otherPartiesOnTdpEffectTotalCount(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	List<Object[]> otherPartyEffectCount = candidatePartyFileDAO.otherPartiesEffectOnTdpBenifitWise(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	List<Object[]> tdpEffectOnOtherPartyTotalCount = candidatePartyFileDAO.otherPartiesWiseEffectOnTdpTotalCount(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	List<Object[]> tdpEffectOnOtherPartyCount = candidatePartyFileDAO.otherPartiesWiseEffectOnTdpBenifitCount(partyId,candidateId,locationLevelId,locationLevelValue,fromdate,todate);
    	
    	NewsAnalysisVO otherPartyEffectnewsAnalysisVO = new NewsAnalysisVO();
    	List<NewsAnalysisVO> OtherPartyEffectonTdpvos = new ArrayList<NewsAnalysisVO>();
    	
    	otherPartyEffectnewsAnalysisVO.setTotal(otherPartyTotalCount);
    	for(Object[] tdpEffect:otherPartyEffectCount){
    		if((Long)tdpEffect[1] == 1)
    			otherPartyEffectnewsAnalysisVO.setPositiveCount((Long)tdpEffect[0]);
    		if((Long)tdpEffect[1] == 2)
    			otherPartyEffectnewsAnalysisVO.setNegativeCount((Long)tdpEffect[0]);
    	}

    	for(Object[] otherPartiesTotalEffect:tdpEffectOnOtherPartyTotalCount){
    		NewsAnalysisVO newsAnalysisVO1 = new NewsAnalysisVO();
    		newsAnalysisVO1.setId((Long)otherPartiesTotalEffect[1]);
    		newsAnalysisVO1.setName(otherPartiesTotalEffect[2].toString());
    		newsAnalysisVO1.setTotal((Long)otherPartiesTotalEffect[0]);
    		OtherPartyEffectonTdpvos.add(newsAnalysisVO1);
    	}
    	otherPartyEffectnewsAnalysisVO.setSubList(OtherPartyEffectonTdpvos);
    	for(Object[] otherPartiesEffect:tdpEffectOnOtherPartyCount){
    		NewsAnalysisVO newsAnalysisVO1 = null;
    			newsAnalysisVO1 = checkVoExistance((Long)otherPartiesEffect[2],otherPartyEffectnewsAnalysisVO.getSubList());
    			if(newsAnalysisVO1 != null)
	  			  {
        			if((Long)otherPartiesEffect[1]==1)
            			newsAnalysisVO1.setPositiveCount((Long)otherPartiesEffect[0]);
            		if((Long)otherPartiesEffect[1]==2)
        				newsAnalysisVO1.setNegativeCount((Long)otherPartiesEffect[0]);
	  			  }
    	}
    	result.setTdpPartyEffect(tdpEffectnewsAnalysisVO);
    	result.setOtherPartyEffect(otherPartyEffectnewsAnalysisVO);
    	
    	}catch(Exception e){
    		LOG.error("Exception rised in getAnalysisDetails ",e);
    	}
    	return result;
    }
    
	public NewsAnalysisVO checkVoExistance(Long partyId, List<NewsAnalysisVO> list)
	{
		try{
			if(list == null || list.size() == 0)
				return null;
			for(NewsAnalysisVO vO : list)
				if(vO.getId() != null && vO.getId().equals(partyId))
					return vO;
			return null;
		}catch (Exception e) {
			LOG.error("Exception Occured in checkVoExistance() method, Exception - ",e);
			return null;
		}
	}
	
	public StringBuilder addLocaionAndDateQuery(Long level,String ids,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		if(level != null && level.longValue() > 0 && ids != null && ids.trim().length() > 0){
			 if(level.longValue() == 1)
				 str.append(" and ua.district.districtId in( "+ids+")");
			 if(level.longValue() == 3)
				 str.append(" and ua.constituency.constituencyId in("+ids+")");
			 if(level.longValue() == 2)
				 str.append(" and ua.parliamentConstituency.constituencyId in ("+ids+")");
		 }
		 if(fromDate != null)
			 str.append(" and date(cpf.file.fileDate) >= :fromDate");
			if(toDate != null)
			 str.append(" and date(cpf.file.fileDate) <= :toDate");
			
			return str;
	}
	
	public StringBuilder tdpEffectOnOtherPartiesTotalNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,String type,Long keywordId){
		 StringBuilder str = new StringBuilder();
		 if(type.equalsIgnoreCase("count")){
				str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
			}else{
			 str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
						" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
			}
		 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " );
			 if(candidateId != null && candidateId.longValue() >0){
				str.append("  cpf.sourceCandidate.candidateId = :candidateId ");
			 }else if(partyId != null && partyId.longValue() >0){
				str.append("  cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
			 }
			 str.append(" and (cpf.destinationParty.partyId is not null or cpf.destinationCandidate.candidateId is not null ) ");
			 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
			 if(! type.equalsIgnoreCase("count")){
				 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
			 }
			return str;
	 }
	
	 
		public StringBuilder tdpEffectOnOtherPartiesBenifitWiseNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,Long benfitId,String type,Long keywordId){
			StringBuilder str = new StringBuilder();
			if(type.equalsIgnoreCase("count")){
				str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
			}else{
				str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
						" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
			}
			 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " );
				 if(candidateId != null && candidateId.longValue() >0){
					str.append("  cpf.sourceCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append("  cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and (cpf.destinationParty.partyId is not null or cpf.destinationCandidate.candidateId is not null ) and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 if(! type.equalsIgnoreCase("count")){
					 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
				 }
				return str; 
		 }
		
	 
		public StringBuilder tdpEffectOnOthersPartyWiseTotalNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,Long benfitId,Long otherPartyId,String type,Long keywordId){
			 StringBuilder str = new StringBuilder();
			 if(type.equalsIgnoreCase("count")){
					str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
				}else{
				 str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
							" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
				}
			 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " ); 
			  if(candidateId != null && candidateId.longValue() >0){
					str.append("  cpf.sourceCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append("  cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and (cpf.destinationParty.partyId ="+otherPartyId+" ) ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 if(! type.equalsIgnoreCase("count")){
					 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
				 }
				 return str; 
		 }
		 
	 
		public StringBuilder tdpEffectOnOthersPartyWiseBenifitNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,Long benfitId,Long otherPartyId,String type,Long keywordId){
			 StringBuilder str = new StringBuilder();
			 if(type.equalsIgnoreCase("count")){
					str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
				}else{
				 str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
							" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
				}
			 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " ); 
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.sourceCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and cpf.destinationParty.partyId ="+otherPartyId+"  and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 if(! type.equalsIgnoreCase("count")){
					 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
				 }
				 return str;
		 }
		
	 
		public StringBuilder otherPartiesOnTdpEffectTotalNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,String type,Long keywordId){
			 StringBuilder str = new StringBuilder();
			 if(type.equalsIgnoreCase("count")){
					str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
				}else{
				 str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
							" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
				}
			 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " ); 
			 if(candidateId != null && candidateId.longValue() >0){
				str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
			 }else if(partyId != null && partyId.longValue() >0){
				str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
			 }
			 str.append(" and (cpf.sourceParty.partyId is not null or cpf.sourceCandidate.candidateId is not null ) ");
			 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
			 if(! type.equalsIgnoreCase("count")){
				 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
			 }
			 return str;
		 }
			 
	 
		public StringBuilder otherPartiesEffectOnTdpBenifitWiseNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,Long benfitId,String type,Long keywordId){
			 StringBuilder str = new StringBuilder();
			 if(type.equalsIgnoreCase("count")){
					str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
				}else{
				 str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
							" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
				}
			 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " ); 
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and (cpf.sourceParty.partyId is not null or cpf.sourceCandidate.candidateId is not null ) and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 if(! type.equalsIgnoreCase("count")){
					 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
				 }
				 return str;
		 }
				
	 
		public StringBuilder otherPartiesWiseEffectOnTdpTotalNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,Long benfitId,Long otherPartyId,String type,Long keywordId){
			 StringBuilder str = new StringBuilder();
			 if(type.equalsIgnoreCase("count")){
					str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
				}else{
				 str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
							" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
				}
			 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " ); 
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and cpf.sourceParty.partyId ="+otherPartyId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 if(! type.equalsIgnoreCase("count")){
					 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
				 }
				 return str;
		 }
				 
	 
		public StringBuilder otherPartiesWiseEffectOnTdpBenifitNews(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long categoryId,Long sourceId,Long benfitId,Long otherPartyId,String type,Long keywordId){
			 StringBuilder str = new StringBuilder();
			 if(type.equalsIgnoreCase("count")){
					str.append("select count(distinct cpf.file.fileId)  from CandidatePartyFile cpf  ");
				}else{
				 str.append("select distinct  cpf.file.fileTitle ,cpf.file.fileDescription ," +
							" cpf.file.fileDate ,cpf.file.filePath ,cpf.file.fileId ,cpf.file.font.fontId,cpf.file.descFont.fontId  from CandidatePartyFile cpf  ");
				}
			 str.append(" "+getCategorySourceQuery(categoryId, sourceId,keywordId,level,ids)+"  " );  
			   if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and cpf.sourceParty.partyId ="+otherPartyId+" and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 if(! type.equalsIgnoreCase("count")){
					 str.append(" order by cpf.file.fileDate desc,cpf.file.updatedDate desc");
				 }
				 return str;
		 }
	 
	 public StringBuilder tdpEffectOnOtherPartiesTotalCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,String type){
		 StringBuilder str = new StringBuilder();
		 
		 str.append(getAttributeSelectQuery(type,level,ids));
			 if(candidateId != null && candidateId.longValue() >0){
				str.append("  cpf.sourceCandidate.candidateId = :candidateId ");
			 }else if(partyId != null && partyId.longValue() >0){
				str.append("  cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
			 }
			 str.append(" and (cpf.destinationParty.partyId is not null or cpf.destinationCandidate.candidateId is not null ) ");
			 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
			 str.append(getAttributeGroupByQuery(type));
			return str;
	 }
	
	 
		public StringBuilder tdpEffectOnOtherPartiesBenifitWiseCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long benfitId,String type){
			StringBuilder str = new StringBuilder();
			str.append(getAttributeSelectQuery(type,level,ids));
				 if(candidateId != null && candidateId.longValue() >0){
					str.append("  cpf.sourceCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append("  cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and (cpf.destinationParty.partyId is not null or cpf.destinationCandidate.candidateId is not null ) and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 str.append(getAttributeGroupByQuery(type));
				return str; 
		 }
		
	 
		public StringBuilder tdpEffectOnOthersPartyWiseTotalCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long benfitId,Long otherPartyId,String type){
			 StringBuilder str = new StringBuilder();
			 str.append(getAttributeSelectQuery(type,level,ids));
			  if(candidateId != null && candidateId.longValue() >0){
					str.append("  cpf.sourceCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append("  cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and (cpf.destinationParty.partyId ="+otherPartyId+" ) ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 str.append(getAttributeGroupByQuery(type));
				 return str; 
		 }
		 
	 
		public StringBuilder tdpEffectOnOthersPartyWiseBenifitCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long benfitId,Long otherPartyId,String type){
			 StringBuilder str = new StringBuilder();
			 str.append(getAttributeSelectQuery(type,level,ids));
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.sourceCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.sourceParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and cpf.destinationParty.partyId ="+otherPartyId+"  and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 str.append(getAttributeGroupByQuery(type));
				 return str;
		 }
		
	 
		public StringBuilder otherPartiesOnTdpEffectTotalCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,String type){
			 StringBuilder str = new StringBuilder();
			 str.append(getAttributeSelectQuery(type,level,ids));
			 if(candidateId != null && candidateId.longValue() >0){
				str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
			 }else if(partyId != null && partyId.longValue() >0){
				str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
			 }
			 str.append(" and (cpf.sourceParty.partyId is not null or cpf.sourceCandidate.candidateId is not null ) ");
			 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
			 str.append(getAttributeGroupByQuery(type));
			 return str;
		 }
			 
	
		public StringBuilder otherPartiesEffectOnTdpBenifitWiseCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long benfitId,String type){
			 StringBuilder str = new StringBuilder();
			 str.append(getAttributeSelectQuery(type,level,ids));
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and (cpf.sourceParty.partyId is not null or cpf.sourceCandidate.candidateId is not null ) and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 str.append(getAttributeGroupByQuery(type));
				 return str;
		 }
				
	 
		public StringBuilder otherPartiesWiseEffectOnTdpTotalCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long benfitId,Long otherPartyId,String type){
			 StringBuilder str = new StringBuilder();
			 str.append(getAttributeSelectQuery(type,level,ids));
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and cpf.sourceParty.partyId ="+otherPartyId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 str.append(getAttributeGroupByQuery(type));
				 return str;
		 }
				 
	
		public StringBuilder otherPartiesWiseEffectOnTdpBenifitCountAttrWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,Long benfitId,Long otherPartyId,String type){
			 StringBuilder str = new StringBuilder();
			 str.append(getAttributeSelectQuery(type,level,ids));
			   if(candidateId != null && candidateId.longValue() >0){
					str.append(" cpf.destinationCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" cpf.destinationParty.partyId = :partyId and cpf.sourceParty.partyId != cpf.destinationParty.partyId ");
				 }
				 str.append(" and cpf.sourceParty.partyId ="+otherPartyId+" and cpf.destinationBenefit.benefitId ="+benfitId+" ");
				 str.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
				 str.append(getAttributeGroupByQuery(type));
				 return str;
		 }
	 
	 public StringBuilder getAttributeSelectQuery(String type,Long level,String ids){
		 StringBuilder query = new StringBuilder();
		 if(type.equalsIgnoreCase("category")){
		   query.append(" select count(distinct cpc.candidatePartyFile.file.fileId),cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc,CandidatePartyFile cpf     ");
		   query.append(addLocationToQuery(level,ids));
		   query.append("  and cpf.candidatePartyFileId = cpc.candidatePartyFile.candidatePartyFileId  and ");
		 }else if(type.equalsIgnoreCase("source")){
			 query.append(" select count(distinct fsl.file.fileId),fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf ");
			 query.append(addLocationToQuery(level,ids));
			 query.append(" and fsl.file.fileId = cpf.file.fileId and ");
		 }else if(type.equalsIgnoreCase("keyword")){
			 query.append(" select count(distinct cpk.candidatePartyFile.file.fileId),cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk,CandidatePartyFile cpf     ");
			 query.append(addLocationToQuery(level,ids));
			 query.append(" and cpf.candidatePartyFileId = cpk.candidatePartyFile.candidatePartyFileId  and ");
		 }
		 
		 return query;
	 }
	 
	 public StringBuilder addLocationToQuery(Long level,String ids){
		 StringBuilder query = new StringBuilder();
		 if(level != null && level.longValue() > 0 && ids != null && ids.trim().length() > 0){
			 query.append(",UserAddress ua where cpf.file.fileId = ua.file.fileId and cpf.file.isDeleted != 'Y' ");
		 }else{
			 query.append("where cpf.file.isDeleted != 'Y' ");
		 }
		 return query;
	 }
	 public StringBuilder getAttributeGroupByQuery(String type){
		 StringBuilder query = new StringBuilder();
		 if(type.equalsIgnoreCase("category")){
		   query.append(" group by cpc.gallary.gallaryId  ");
		 }else if(type.equalsIgnoreCase("source")){
			 query.append(" group by fsl.source.sourceId ");
		 }else if(type.equalsIgnoreCase("keyword")){
			 query.append(" group by cpk.keyword.keywordId ");
		 }
		 
		 return query;
	 }
	 public static Comparator<NewsAnalysisVO> countCompare =  new Comparator<NewsAnalysisVO>()
	  {
		  public int compare(NewsAnalysisVO vo1,NewsAnalysisVO vo2){
			  return (vo2.getTotal().intValue() - vo1.getTotal().intValue());
		  }
	  };
	  
	  public StringBuilder getTotalNewsCountAttributeWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate,String type){
	    	StringBuilder query = new StringBuilder();
	    	query.append(getAttributeSelectQuery(type,level,ids));
	    	if(candidateId != null && candidateId.longValue() > 0){
	    	   
	    		query.append(" (cpf.sourceCandidate.candidateId =:candidateId or cpf.destinationCandidate.candidateId =:candidateId )"); 
	    	}else if(partyId != null && partyId.longValue() > 0){
	    		query.append(" ( cpf.sourceParty.partyId =:partyId or cpf.destinationParty.partyId =:partyId )");
	    	}
	    	query.append(addLocaionAndDateQuery(level,ids,fromDate,toDate));
	    	query.append(getAttributeGroupByQuery(type));
	    	return query;
	    }
	  
	  public StringBuilder getPostivNegivNewsCountAttributeWise(Long partyId,Long candidateId,Long locationLvl,String locationIds,Date fromDate,Date toDate,Long benfitId,String type){
	    	StringBuilder query = new StringBuilder();
	    	query.append(getAttributeSelectQuery(type,locationLvl,locationIds));
	    	if(candidateId != null && candidateId.longValue() > 0){
	    		query.append(" ((cpf.sourceCandidate.candidateId =:candidateId and cpf.sourceBenefit.benefitId = "+benfitId+" ) or (cpf.destinationCandidate.candidateId =:candidateId  and cpf.destinationBenefit.benefitId = "+benfitId+" ))");
	    	}else if(partyId != null && partyId.longValue() > 0){
	    		query.append(" ( (cpf.sourceParty.partyId =:partyId  and cpf.sourceBenefit.benefitId = "+benfitId+" ) or (cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId = "+benfitId+" ))");
	    	}
	    	query.append(addLocaionAndDateQuery(locationLvl,locationIds,fromDate,toDate));
	    	query.append(getAttributeGroupByQuery(type));
	    	return query;
	    }
	  
	  public TreeMap<Long,String> getPartyNames(String partyIds){
		  TreeMap<Long,String> partyNamesMap = new TreeMap<Long,String>();
		  List<Object[]> partyNamesList = partyDAO.getPartyNames(partyIds);
		  for(Object[] partyName:partyNamesList){
			  partyNamesMap.put((Long)partyName[0], partyName[1].toString());
		  }
		  return partyNamesMap;
	  }
	  
      public SelectOptionVO generateExcelForAnalysis(Date startDate,Date endDate,String ids,String[] partyIds,Long locationLevelId,String locationLevelValue,Long candidatateId){
    	  SelectOptionVO selectOptionVO = new SelectOptionVO();
    	    selectOptionVO.setName("success");
    	    TreeMap<Long,String> partyNamesMap = getPartyNames(ids);
			Random randomNum = new Random();
			String filename= "Reports"+"/Analysis"+"/"+"report"+randomNum.nextInt(10000000)+".xls";
			selectOptionVO.setUrl(filename);
			String FILE = IWebConstants.STATIC_CONTENT_FOLDER_URL+filename;
			FileOutputStream fileOut = null;
			try{
			File file  = new File(FILE);
			file.createNewFile();
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFFont font= workbook.createFont();
		    font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
		    font.setItalic(false);
		    font.setFontHeight((short)240);
		    HSSFCellStyle style = workbook.createCellStyle();
		    style.setFont(font);
		    HSSFFont font1= workbook.createFont();
		    font1.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
		    font1.setItalic(false);
		    
		    HSSFCellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    fileOut =  new FileOutputStream(FILE);
    	  for(String id:partyIds){
				Long partyId = Long.valueOf(id);
				String name = null;
				if(candidatateId == null || candidatateId.longValue() == 0)
				 name = partyNamesMap.get(partyId);
				else 
				 name = candidateDAO.get(candidatateId).getLastname();
				AnalysisBasicInfoVO countInfo = getAnalysedNewsCount(startDate,endDate,partyId,candidatateId,locationLevelId,locationLevelValue);
				if(countInfo != null && ((countInfo.getTotalCount() != null && countInfo.getTotalCount().getTotal() > 0) || (countInfo.getOwnNews() != null && countInfo.getOwnNews().getTotal() > 0) || (countInfo.getOnOtherParty() != null && countInfo.getOnOtherParty().getTotal() > 0) || (countInfo.getOnMe() != null && countInfo.getOnMe().getTotal() > 0) || (countInfo.getInMedia() != null && countInfo.getInMedia().getTotal() > 0))){
					HSSFSheet sheet =  workbook.createSheet(name); 
					sheet.setColumnWidth(1, 4500);
					sheet.setColumnWidth(2, 4500);
					sheet.setColumnWidth(3, 4500);
					sheet.setColumnWidth(4, 4500);
					sheet.setColumnWidth(5, 4500);
					sheet.setColumnWidth(6, 4500);
					
					int rowCount = 0;
					HSSFRow rowhead=   sheet.createRow((short)rowCount);
					Cell cell = rowhead.createCell(0);
					cell.setCellValue("Total News Articles");
					cell.setCellStyle(style);
					rowCount=rowCount+2;
					 rowhead=   sheet.createRow((short)rowCount);
					 cell = rowhead.createCell(1);
					 cell.setCellValue("Total News :"+" "+countInfo.getTotalCount().getTotal());
					 cell.setCellStyle(style1);
					 cell = rowhead.createCell(3);
					 cell.setCellValue("Positive News :"+" "+countInfo.getTotalCount().getPositiveCount());
					 cell.setCellStyle(style1);
					//rowhead.createCell((short) 4).setCellValue(countInfo.getTotalCount().getPositiveCount());
					cell = rowhead.createCell(5);
					cell.setCellValue("Negative News:"+" "+countInfo.getTotalCount().getNegativeCount());
					cell.setCellStyle(style1);
					//rowhead.createCell((short) 7).setCellValue(countInfo.getTotalCount().getNegativeCount());
					sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));//rowFrom,rowTo,colFrom,colTo
					sheet.addMergedRegion(new CellRangeAddress(2,2,1,2));
					sheet.addMergedRegion(new CellRangeAddress(2,2,3,4));
					sheet.addMergedRegion(new CellRangeAddress(2,2,5,6));
					rowCount=rowCount+3;
					if(countInfo.getOwnNews() != null && countInfo.getOwnNews().getTotal() > 0){
						 rowhead=   sheet.createRow((short)rowCount);
						 cell =  rowhead.createCell(0);
						 cell.setCellValue(name+" Own News");
						 cell.setCellStyle(style);
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,4));
						rowCount=rowCount+2;
						rowhead=   sheet.createRow((short)rowCount);
						cell =  rowhead.createCell(1);
						cell.setCellValue("Total News :"+" "+countInfo.getOwnNews().getTotal());
						cell.setCellStyle(style1);
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,1,2));
						cell =  rowhead.createCell(3);
						cell.setCellValue("Negative News :"+" "+countInfo.getOwnNews().getNegativeCount());
						cell.setCellStyle(style1);
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,3,4));
						rowCount=rowCount+3;
					}
					if(countInfo.getOnOtherParty() != null && countInfo.getOnOtherParty().getTotal() > 0){
						 rowhead=   sheet.createRow((short)rowCount);
						 cell =  rowhead.createCell(0);
						 cell.setCellValue(name+" Targeting On Other Parties");
						 cell.setCellStyle(style);
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,4));
						rowCount=rowCount+2;
						rowhead=   sheet.createRow((short)rowCount);
						cell =  rowhead.createCell(1);
						cell.setCellValue("Total News :"+" "+countInfo.getOnOtherParty().getTotal());
						cell.setCellStyle(style1);
						cell =  rowhead.createCell(3);
						cell.setCellValue("Positive News :"+" "+countInfo.getOnOtherParty().getPositiveCount());
						cell.setCellStyle(style1);
						cell =  rowhead.createCell(5);
						cell.setCellValue("Negative News :"+" "+countInfo.getOnOtherParty().getNegativeCount());
						cell.setCellStyle(style1);
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,1,2));
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,3,4));
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,5,6));
						rowCount=rowCount+2;
						rowhead=   sheet.createRow((short)rowCount);
						cell = rowhead.createCell(1);
						cell.setCellValue("Other Parties");
						cell.setCellStyle(style1);
						cell = rowhead.createCell(2);
						cell.setCellValue("Total News Count");
						cell.setCellStyle(style1);
						cell = rowhead.createCell(3);
						cell.setCellValue("Positive Count");
						cell.setCellStyle(style1);
						cell = rowhead.createCell(4);
						cell.setCellValue("Negative Count");
						cell.setCellStyle(style1);
						rowCount=rowCount+1;
						for(NewsAnalysisVO vo:countInfo.getOnOtherParty().getSubList()){
							rowhead=   sheet.createRow((short)rowCount);
							rowhead.createCell(1).setCellValue(vo.getName());
							rowhead.createCell(2).setCellValue(vo.getTotal());
							rowhead.createCell(3).setCellValue(vo.getPositiveCount());
							rowhead.createCell(4).setCellValue(vo.getNegativeCount());
							rowCount=rowCount+1;
						}
						rowCount=rowCount+2;
					}
					if(countInfo.getOnMe() != null && countInfo.getOnMe().getTotal() > 0){
						 rowhead=   sheet.createRow((short)rowCount);
						 cell =  rowhead.createCell(0);
						 cell.setCellValue("Other Parties Targeting On "+name);
						 cell.setCellStyle(style);
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,0,4));
						rowCount=rowCount+2;
						rowhead=   sheet.createRow((short)rowCount);
						cell = rowhead.createCell(1);
						cell.setCellValue("Total News :"+" "+countInfo.getOnMe().getTotal());
						cell.setCellStyle(style1);
						cell = rowhead.createCell(3);
						cell.setCellValue("Positive News :"+" "+countInfo.getOnMe().getPositiveCount());
						cell.setCellStyle(style1);
						cell = rowhead.createCell(5);
						cell.setCellValue("Negative News :"+" "+countInfo.getOnMe().getNegativeCount());
						cell.setCellStyle(style1);
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,1,2));
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,3,4));
						sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,5,6));
						rowCount=rowCount+2;
						rowhead=   sheet.createRow((short)rowCount);
						cell = rowhead.createCell(1);
						cell.setCellValue("Other Parties");
						cell.setCellStyle(style1);
						cell = rowhead.createCell(2);
						cell.setCellValue("Total News Count");
						cell.setCellStyle(style1);
						cell = rowhead.createCell(3);
						cell.setCellValue("Positive Count");
						cell.setCellStyle(style1);
						cell = rowhead.createCell(4);
						cell.setCellValue("Negative Count");
						cell.setCellStyle(style1);
						rowCount=rowCount+1;
						for(NewsAnalysisVO vo:countInfo.getOnMe().getSubList()){
							rowhead=   sheet.createRow((short)rowCount);
							rowhead.createCell(1).setCellValue(vo.getName());
							rowhead.createCell(2).setCellValue(vo.getTotal());
							rowhead.createCell(3).setCellValue(vo.getPositiveCount());
							rowhead.createCell(4).setCellValue(vo.getNegativeCount());
							rowCount=rowCount+1;
						}
						rowCount=rowCount+2;
					}
				   if(countInfo.getInMedia() != null && countInfo.getInMedia().getTotal() > 0){
					 rowhead=   sheet.createRow((short)rowCount);
					 cell =  rowhead.createCell(0);
					 cell.setCellValue("News On "+name+" In Media ");
					 cell.setCellStyle(style);
					 rowCount=rowCount+2;
					 rowhead=   sheet.createRow((short)rowCount);
					 cell = rowhead.createCell(1);
					 cell.setCellValue("Total News :"+" "+countInfo.getInMedia().getTotal());
					 cell.setCellStyle(style1);
					 cell = rowhead.createCell(3);
					 cell.setCellValue("Positive News :"+" "+countInfo.getInMedia().getPositiveCount());
					 cell.setCellStyle(style1);
					 cell = rowhead.createCell(5);
					 cell.setCellValue("Negative News :"+" "+countInfo.getInMedia().getNegativeCount());
					 cell.setCellStyle(style1);
					 //rowhead.createCell((short) 7).setCellValue(countInfo.getTotalCount().getNegativeCount());
					 sheet.addMergedRegion(new CellRangeAddress(rowCount-2,rowCount-2,0,4));//rowFrom,rowTo,colFrom,colTo
					 sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,1,2));
					 sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,3,4));
					 sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,5,6));
					 
				   }
				}
			}
    	     workbook.write(fileOut);
			 fileOut.close();
			}catch(Exception e){
			  selectOptionVO.setName("fail");
			  LOG.error("Exception rised in generateExcelForAnalysis ",e);
			}finally{
				if(fileOut != null){
					 try{
					  fileOut.close();
					 }catch(Exception e1){
							
					 }
				}
			}
		  return selectOptionVO;
	  }
}
