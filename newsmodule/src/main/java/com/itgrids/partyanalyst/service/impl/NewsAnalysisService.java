package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.hibernate.DesignationDAO;
import com.itgrids.partyanalyst.dao.hibernate.PartyDAO;
import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsAnalysisVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Designation;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsAnalysisService;

public class NewsAnalysisService implements INewsAnalysisService {
   
	private static final Logger LOG = Logger.getLogger(NewsAnalysisService.class);
    private IFileDAO fileDAO;
    private ICandidateDetailsService candidateDetailsService;
	private TransactionTemplate transactionTemplate;	
	private DesignationDAO designationDAO;
	private PartyDAO partyDAO;
	
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
		StringBuilder queryData = new StringBuilder();
		StringBuilder cpfQuery = new StringBuilder();
		StringBuilder fslQuery = new StringBuilder();
		StringBuilder cpcQuery = new StringBuilder();
		StringBuilder cpkQuery = new StringBuilder();
		queryData.append("select distinct  model.fileTitle ,model.fileDescription ," +
				" model.fileDate ,model.filePath ,model.fileId ,model.font.fontId  " +
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
}
