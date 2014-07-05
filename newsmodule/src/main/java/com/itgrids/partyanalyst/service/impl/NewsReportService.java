package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.service.INewsReportService;

public class NewsReportService implements INewsReportService {

	private static final Logger LOG = Logger.getLogger(NewsReportService.class);

	private ICandidatePartyFileDAO candidatePartyFileDAO;

	private IPartyDAO partyDAO;
	
	private IDistrictDAO districtDAO;
	
	private IConstituencyDAO constituencyDAO;

	public ICandidatePartyFileDAO getCandidatePartyFileDAO() {
		return candidatePartyFileDAO;
	}

	public void setCandidatePartyFileDAO(final 
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(final IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void getNewsCountsPartyWise(final Date fromDate,final Date toDate,final Long locationLvl){
		try{

			//getting party Details started
			final List<Object[]> partiesList = candidatePartyFileDAO.getAllPartyIds(fromDate, toDate);
			final Set<Long> partyIds = new HashSet<Long>();
			for(Object[] parties:partiesList){
				if(parties[0] != null){
					partyIds.add((Long)parties[0]);
				}
				if(parties[1] != null){
					partyIds.add((Long)parties[1]);
				}
			}

			final Map<Long,String> partyNames = new HashMap<Long,String>();
			final List<Long> parties = new ArrayList<Long>();
			final List<Object[]> partyDetails = partyDAO.getPartyNames(partyIds);
			for(Object[] party:partyDetails){
				partyNames.put((Long)party[1], party[0].toString());
				parties.add((Long)party[1]);
			}

			if(parties.contains(872l)){
				parties.remove(872l);
				parties.add(0, 872l);
			}
			//getting party Details end

			//getting location details
			Map<Long,String> locationNames = new HashMap<Long,String>();
			List<Long> locationIds = new ArrayList<Long>();
			List<Object[]> locationsArray = getLocationDetails(locationLvl);
            
			//getting location Details end

			final List<Object[]> totalCountList = candidatePartyFileDAO.getTotalCounts(fromDate, toDate, getQueryForTotalCount(locationLvl));

			//0 count,1 queryType,2 partyId,3 locationId,4 locationName
			final List<Object[]> allCounts = new ArrayList<Object[]>();
			for(Long partyId:partyIds){
				allCounts.addAll(candidatePartyFileDAO.getAllCounts(fromDate, toDate, getQueryForPartyTotalCount(locationLvl, partyId), partyId));
				allCounts.addAll(candidatePartyFileDAO.getAllCounts(fromDate, toDate, getQueryForPartyPosCount(locationLvl, partyId), partyId));
				allCounts.addAll(candidatePartyFileDAO.getAllCounts(fromDate, toDate, getQueryForPartyNegCount(locationLvl, partyId), partyId));
			}


			Map<Long,Map<Long,Map<String,Long>>> locationMap = new HashMap<Long,Map<Long,Map<String,Long>>>();//Map<locationId,Map<partyId,Map<countType,count>>>
			Map<Long,Map<String,Long>> partyMap = null;
			Map<String,Long> countType = null;

			for(Object[] count:allCounts){

				partyMap = locationMap.get((Long)count[3]);

				if(partyMap == null){
					partyMap = new HashMap<Long,Map<String,Long>>();
					locationMap.put((Long)count[3],partyMap);
				}

				countType = partyMap.get((Long)count[2]);

				if(countType == null){
					countType = new HashMap<String,Long>();
					partyMap.put((Long)count[2],countType);
				}

				countType.put(count[1].toString(),(Long)count[0]);
			}

		}catch(Exception e){
			LOG.error("Exception rised in getNewsCountsPartyWise",e);
		}
	}

	public List<Object[]> getLocationDetails(Long locationLvl){
		if(locationLvl.longValue() == 1l){
			
		}else if(locationLvl.longValue() == 2l){
			
		}else if(locationLvl.longValue() == 3l){
			
		}
		
		return null;
	}
	public String getQueryForTotalCount(final Long locationLvl){

		final StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select count(distinct ua.file.fileId) " );	
		queryStr.append(getAttributesString(locationLvl));		
		queryStr.append(" from UserAddress ua where date(ua.file.fileDate) >= :fromDate and  date(ua.file.fileDate) <= :toDate and  ua.file.isDeleted !='Y' group by  ");		
		queryStr.append(getGroupByString(locationLvl));

		return queryStr.toString();
	}

	public String getQueryForPartyTotalCount(final Long locationLvl,final Long partyId){
		final StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct cpf.file.fileId),'paTot',"+partyId+" " );

		queryStr.append(getAttributesString(locationLvl));

		queryStr.append(" from CandidatePartyFile cpf,UserAddress ua where date(cpf.file.fileDate) >= :fromDate and  date(cpf.file.fileDate) <= :toDate and  cpf.file.isDeleted !='Y' and " +
				"  ( cpf.sourceParty.partyId =:partyId or cpf.destinationParty.partyId =:partyId ) and ua.file.fileId = cpf.file.fileId  group by ");

		queryStr.append(getGroupByString(locationLvl));

		return queryStr.toString();
	}

	public String getQueryForPartyPosCount(final Long locationLvl,final Long partyId){
		final StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct cpf.file.fileId),'pos',"+partyId+" " );

		queryStr.append(getAttributesString(locationLvl));

		queryStr.append(" from CandidatePartyFile cpf,UserAddress ua where date(cpf.file.fileDate) >= :fromDate and  date(cpf.file.fileDate) <= :toDate and  cpf.file.isDeleted !='Y' and " +
				"  ( (cpf.sourceParty.partyId =:partyId  and cpf.sourceBenefit.benefitId = 1 ) or (cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId = 1)) and ua.file.fileId = cpf.file.fileId  group by ");

		queryStr.append(getGroupByString(locationLvl));

		return queryStr.toString();
	}

	public String getQueryForPartyNegCount(final Long locationLvl,final Long partyId){
		final StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct cpf.file.fileId),'neg',"+partyId+" " );

		queryStr.append(getAttributesString(locationLvl));

		queryStr.append(" from CandidatePartyFile cpf,UserAddress ua where date(cpf.file.fileDate) >= :fromDate and  date(cpf.file.fileDate) <= :toDate and  cpf.file.isDeleted !='Y' and " +
				"  ( (cpf.sourceParty.partyId =:partyId  and cpf.sourceBenefit.benefitId = 2 ) or (cpf.destinationParty.partyId =:partyId and cpf.destinationBenefit.benefitId = 2)) and ua.file.fileId = cpf.file.fileId  group by ");

		queryStr.append(getGroupByString(locationLvl));

		return queryStr.toString();
	}

	public StringBuilder getAttributesString(final Long locationLvl){
		final StringBuilder queryStr = new StringBuilder();

		if(locationLvl.longValue() == 1l){
			queryStr.append(", ua.district.districtId,ua.district.districtName  ");
		}else if(locationLvl.longValue() == 2l){
			queryStr.append(", ua.constituency.constituencyId,ua.constituency.name  ");
		}else if(locationLvl.longValue() == 3l){
			queryStr.append(", ua.parliamentConstituency.constituencyId,ua.parliamentConstituency.name  ");
		}

		return queryStr;
	}

	public StringBuilder getGroupByString(final Long locationLvl){
		final StringBuilder queryStr = new StringBuilder();

		if(locationLvl.longValue() == 1l){
			queryStr.append(" ua.district.districtId ");
		}else if(locationLvl.longValue() == 2l){
			queryStr.append(" ua.constituency.constituencyId ");
		}else if(locationLvl.longValue() == 3l){
			queryStr.append(" ua.parliamentConstituency.constituencyId ");
		}

		return queryStr;
	}
}
