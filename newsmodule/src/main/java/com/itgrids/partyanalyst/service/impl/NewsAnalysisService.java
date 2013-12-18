package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.hibernate.DesignationDAO;
import com.itgrids.partyanalyst.dao.hibernate.PartyDAO;
import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsAnalysisVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Designation;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsAnalysisService;
import com.itgrids.partyanalyst.utils.CommonStringUtils;

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
	public NewsAnalysisVO analyseNewsWithSelectedParameters(AnalysisVO analysisVO){
		if(LOG.isInfoEnabled()){
			LOG.info("Enter in to analyseNewsWithSelectedParameters method ");
		}
		try{
			if(analysisVO.isByCategory()){
				 return getSourceDestinationCategoryPresentQuery(analysisVO);
			 }else if(analysisVO.isByKeyword()){
				 return getSourceDestinationKeywordPresentQuery(analysisVO);
			 }else if(!analysisVO.isByDestiCand() && !analysisVO.isBySourceCand() && !analysisVO.isByCategory() && !analysisVO.isByKeyword() && !analysisVO.isSourceCand() && !analysisVO.isSourceParty() && !analysisVO.isDestiCand() && !analysisVO.isDestiParty() && (analysisVO.isSourcePresent() || analysisVO.isLocationPresent() || analysisVO.getFromDate() != null || analysisVO.getToDate() != null )){
				 return getOnlySourceDestinationPresent(analysisVO); 
			 }else{
				 return getSourceDestinationPresentIncludeCandidateParty(analysisVO);
			 }
		}catch(Exception e){
			LOG.error("Exception rised in analyseNewsWithSelectedParameters ",e);
			return new NewsAnalysisVO();
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
			   		" where cpc.candidatePartyFile.file.fileId = fsl.file.fileId ");
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
				   		" ,FileSourceLanguage fsl where cpc.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
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
			query.append(" cpc.gallary.gallaryId,cpc.gallary.name from CandidatePartyCategory cpc where ");
			query.append("  cpc.gallary.gallaryId in ("+vo.getGallaryKeyWordIds()+")  ");
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
		 return getDataForCategoryOrKeywordPresent( query.toString(),vo);
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
			   		" where cpk.candidatePartyFile.file.fileId = fsl.file.fileId ");
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
				   		" ,FileSourceLanguage fsl where cpk.candidatePartyFile.file.fileId = fsl.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
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
			   		" where  cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+") ");
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
			  query.append(" cpk.keyword.keywordId,cpk.keyword.type from CandidatePartyKeyword cpk where ");
			  query.append("  cpk.keyword.keywordId in ("+vo.getGallaryKeyWordIds()+")  ");
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
		return getDataForCategoryOrKeywordPresent(query.toString(),vo);
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
			   query.append(" fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl where fsl.source.sourceId in ("+vo.getSourceIds()+") ");
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
				   		" FileSourceLanguage fsl where fsl.source.sourceId in ("+vo.getSourceIds()+") ");
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
		   query.append("  cast(null as char),cast(null as char) from File file where  ");
		   if(vo.getLocationLvl().longValue() == 1){
				 query.append( " file.userAddress.district.districtId in ("+vo.getLocationValues()+") ");
			}else if(vo.getLocationLvl().longValue() == 2){
				query.append(" file.userAddress.parliamentConstituency.constituencyId in ("+vo.getLocationValues()+")");
			}else if(vo.getLocationLvl().longValue() == 3){
				query.append(" file.userAddress.constituency.constituencyId in ("+vo.getLocationValues()+") ");
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
			query.append("select count(distinct file.fileId),cast(null as char),cast(null as char),cast(null as char),cast(null as char) from File file ");
			if(vo.getFromDate() != null && vo.getToDate() != null){
				query.append(" where file.fileDate >= :fromDate and file.fileDate <= :toDate");
			}else if(vo.getFromDate() != null){
				query.append(" where file.fileDate >= :fromDate ");
			}else if(vo.getToDate() != null){
				query.append(" where file.fileDate <= :toDate ");
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
			   query.append(" fsl.source.sourceId,fsl.source.source from FileSourceLanguage fsl,CandidatePartyFile cpf where fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
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
				   		" FileSourceLanguage fsl,CandidatePartyFile cpf where  fsl.file.fileId = cpf.file.fileId and fsl.source.sourceId in ("+vo.getSourceIds()+") ");
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
		   query.append(" cast(null as char),cast(null as char) from File file,CandidatePartyFile cpf  where  file.fileId = cpf.file.fileId ");
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
				query.append("select count(distinct file.fileId),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char),cast(null as char) from File file ");
				if(vo.getFromDate() != null && vo.getToDate() != null){
					query.append(" where file.fileDate >= :fromDate and file.fileDate <= :toDate");
				}else if(vo.getFromDate() != null){
					query.append(" where file.fileDate >= :fromDate ");
				}else if(vo.getToDate() != null){
					query.append(" where file.fileDate <= :toDate ");
				}
			}
		}
		return getDataForSourceDestinationPresentIncludeCandidateParty(query.toString(),vo);
	}
	
	public NewsAnalysisVO getDataForCategoryOrKeywordPresent(String query,AnalysisVO vo){
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
			    			levels.add("Category/Keyword");
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
	public NewsAnalysisVO getDataForSourceDestinationPresentIncludeCandidateParty(String query,AnalysisVO vo){
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
		queryCount.append("select distinct count(model.fileId) from File model ");
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
		 LinkedHashMap<Long,FileVO> fileMap = new LinkedHashMap<Long, FileVO>();
		List<Object[]> files = fileDAO.getSelectedNewsBySearchCriteria(queryData.toString(), fromDate, toDate, startIndex, maxIndex);
		Long count = fileDAO.getSelectedNewsCountBySearchCriteria(queryCount.append(query).toString(), fromDate, toDate);
		candidateDetailsService.populateNewsDataToVO(files, fileIds, fileMap, count);
		
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
	public List<SelectOptionVO> getProgramsWiseNews(List<Long> categIds, List<Long> constituencyIds,String fromDateStr , String toDateStr ,Long startIndex,Long maxIndex,Long partyid){
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

			Long count = candidatePartyCategoryDAO.getCategoeryAndConsttituencyWiseTotalCount(categIds,constituencyIds,fromDate,toDate,partyid);
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
					selectOptionVO.setOrderId(count);
					returnList.add(selectOptionVO);
					
					
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
				if(constiIds != null && constiIds.size() > 0)
				{
					returnList = new ArrayList<SelectOptionVO>();
					categCountMap = new HashMap<Long, Long>();
					for (Long constituencyId : constiIds) {
						selectOptionVO1 = new SelectOptionVO();
						selectOptionVO1.setLocation(constiMap.get(constituencyId));
						List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
						Map<Long,Long> catgWistCountMap = constituencyWiseCountMap.get(constituencyId);
						for (Long catgId : categIds) {
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
							for (Long catgId : catgSet)
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
}
