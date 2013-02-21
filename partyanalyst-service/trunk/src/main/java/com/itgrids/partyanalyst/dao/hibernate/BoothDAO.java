package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.columns.enums.BoothColumnNames;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothDAO extends GenericDaoHibernate<Booth, Long> implements IBoothDAO{

	public BoothDAO() {
		super(Booth.class);
	}
	
	public List<Booth> findByPartNo(Object partNo) {
		return findByProperty(BoothColumnNames.PART_NO, partNo);
	}
	
	public List<Booth> findByPartName(Object partName) {
		return findByProperty(BoothColumnNames.PART_NAME, partName);
	}
	
	public List<Booth> findByVillageName(Object villageName) {
		return findByProperty(BoothColumnNames.VILLAGE_COVERED, villageName);
	}

	@SuppressWarnings("unchecked")
	public List<Booth> findByProperty(BoothColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Booth where " + propertyName.getValue() + "=?", value);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> findByTehsil(Long tehsilId){
		return getHibernateTemplate().find("from Booth model where model.tehsil.tehsilId =?", tehsilId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> findByConstituency(Long constituencyId){
		return getHibernateTemplate().find("from Booth model where model.boothId in(select model1.booth.boothId from BoothConstituencyElection where model1.constituencyElectionId in(select model2.constituencyElectionId from ConstituencyElection model2 where model2.constituencyId =? ) )", constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> findByTehsilAndPartNo(String tehsilName, String partNo){
		Object[] params = {tehsilName, partNo};
		return getHibernateTemplate().find("from Booth model where model.tehsil.tehsilName = ? and model.partNo = ?", params);
	}
	
	//Tehsil, Local Body and Local Body Ward Info Of an Assembly Constituency...
	public List findTehsilsByElectionAndConstituency(String electionYear,Long constituencyId){
		Object[] params = {constituencyId, Long.parseLong(electionYear)};
		return getHibernateTemplate().find("select distinct model.tehsil.tehsilId, model.tehsil.tehsilName from Booth model " +
				"where model.constituency.constituencyId = ? and model.year= ? and model.localBody is null",params);
	}
	
	public List findLocalBodiesByElectionAndConstituency(String electionYear,Long constituencyId, String localBodyTypes){
		Object[] params = {constituencyId, Long.parseLong(electionYear)};
		return getHibernateTemplate().find("select distinct model.localBody.localElectionBodyId, model.localBody.name, " +
				"model.localBody.electionType.electionType from Booth model where model.constituency.constituencyId = ? " +
				"and model.year= ? and model.localBody.electionType.electionType in ("+localBodyTypes+") and model.localBody is not null",params);
	}
	
	public List findLocalBodyWardsByElectionAndConstituency(String electionYear,Long constituencyId, String localBodyTypes){
		Object[] params = {constituencyId, Long.parseLong(electionYear)};
		return getHibernateTemplate().find("select distinct model.boothLocalBodyWard.localBodyWard.constituencyId, " +
				"model.boothLocalBodyWard.localBodyWard.localElectionBody.name, model.boothLocalBodyWard.localBodyWard.name from Booth model " +
				"where model.constituency.constituencyId = ? and model.year= ? and " +
				"model.localBody.electionType.electionType in ("+localBodyTypes+") and model.localBody is not null",params);
	}

	public List findByConstituencyAndElectionYear(Long constituencyId,	Long year) {
		Object[] params = {constituencyId, year};
		return getHibernateTemplate().find("select count(model.boothId) from Booth model where " +
				"model.constituency.constituencyId = ? and model.year = ? and model.boothId in(select model1.booth.boothId " +
				" from BoothConstituencyElection model1)",params);
	}
	
	public List findByConstituencyAndElectionYearAndPubDtId(Long constituencyId,Long year,Long publicationDtId) {
		Object[] params = {constituencyId, year,publicationDtId};
		return getHibernateTemplate().find("select count(model.boothId) from Booth model where " +
				"model.constituency.constituencyId = ? and model.year = ? and model.publicationDate.publicationDateId = ? ",params);
	}
	
	public List findByPartNoConstituencyIdAndYear(Long constituencyId, Long year, String partNo){
		Object[] params = {constituencyId, year, partNo};
		return getHibernateTemplate().find("select count(model.boothId) from Booth model where " +
		"model.constituency.constituencyId = ? and model.year = ? and model.partNo = ? " +
		" and model.boothId in(select model1.booth.boothId from BoothConstituencyElection model1 ) ",params);
	}
	
	public List findByPartNoConstituencyIdAndYearAndPubDtId(Long constituencyId, Long year, String partNo,Long publicationDtId){
		Object[] params = {constituencyId, year, partNo,publicationDtId};
		return getHibernateTemplate().find("select count(model.boothId) from Booth model where " +
		"model.constituency.constituencyId = ? and model.year = ? and model.partNo = ? " +
		"  and model.publicationDate.publicationDateId = ?  ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findBoothIdPartNoConstituencyIdAndYear(Long constituencyId, Long year, String partNo){
		
		//Object[] params = {constituencyId, year, partNo};
		//return getHibernateTemplate().find("select model.boothId from Booth model where model.constituency.constituencyId = ? and model.year = ? and model.partNo = ?",params);
		Object[] params = {constituencyId, partNo,constituencyId};
		return getHibernateTemplate().find("select model.boothId from Booth model where model.constituency.constituencyId = ? and model.partNo = ? "+
				"and model.year = (select max(model1.year) from Booth model1 where model1.constituency.constituencyId = ?)",params);
	}

	@SuppressWarnings("unchecked")
	public List<Booth> findbyConstituencyNameDistrictIdPartnoAndElectionYear(
			String acName, Long districtId, Long electionYear, String partNumber) {
		Object[] params = {acName, districtId, electionYear, partNumber};
		return getHibernateTemplate().find("from Booth model where model.constituency.name = ? and " +
		"model.constituency.district.districtId = ? and model.year = ? and model.partNo = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findbyConstituencyNameDistrictIdAndElectionYear(
			String acName, Long districtId, Long electionYear) {
		Object[] params = {acName, districtId, electionYear};
		return getHibernateTemplate().find("select count(model.boothId) from Booth model where model.constituency.name = ? and " +
		"model.constituency.district.districtId = ? and model.year = ?",params);
	}
	
	public List findBoothInfoByConstituencyIdAndYear(Long constituencyId, Long year){
		Object[] params = {constituencyId, year};
		return getHibernateTemplate().find("select model.boothId, model.partNo, model.location " +
				"from Booth model where model.constituency.constituencyId = ? and model.year = ? and model.localBody is null", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> findByBoothIds(List<Long> boothIds) {
		Query queryObject = getSession().createQuery("from Booth model where model.boothId in (:boothIds)");
		queryObject.setParameterList("boothIds", boothIds);
		return queryObject.list();
	}

	public int updateLocalBodyInfoByBoothIdsAndWardId(Long localBodyId, List<Long> boothIds) {
		Query queryObject = getSession().createQuery("update Booth model set model.localBody = (select model1 from " +
				"LocalElectionBody model1 where model1.localElectionBodyId = ?) where model.boothId in (:boothIds)");
		queryObject.setParameter(0, localBodyId);
		queryObject.setParameterList("boothIds", boothIds);
		return queryObject.executeUpdate();
	}
	
	public List findVotersInfoForConstituencyInAnYearByLocalElectionBody(Long constituencyId, Long year, String localBodyTypes){
		Object[] params = {year, constituencyId};
		return getHibernateTemplate().find("select model.localBody.localElectionBodyId, model.localBody.name, sum(model.maleVoters), " +
				" sum(model.femaleVoters),sum(model.totalVoters), model.localBody.electionType.electionType from Booth model where model.year = (select max(model1.year) from Booth model1 where " +
				" model1.year <= ?) and model.constituency.constituencyId =? and model.localBody.electionType.electionType in ("+localBodyTypes+")" +
				" group by model.localBody.localElectionBodyId order by model.localBody.name",params);
	}
	
	public List findVotersInfoForConstituencyInAnYearByLocalElectionBodyWard(Long constituencyId, Long year, String wardType){
		Object[] params = {year, constituencyId, wardType};
		return getHibernateTemplate().find("select model.boothLocalBodyWard.localBodyWard.constituencyId, model.boothLocalBodyWard.localBodyWard.name, " +
				" sum(model.maleVoters), sum(model.femaleVoters),sum(model.totalVoters), model.boothLocalBodyWard.localBodyWard.localElectionBody.name, " +
				" model.boothLocalBodyWard.localBodyWard.localElectionBody.electionType.electionType " +
				" from Booth model where model.year = (select max(model1.year) from Booth model1 where model1.year <= ?) and " +
				" model.constituency.constituencyId =? and model.boothLocalBodyWard.localBodyWard.localElectionBody.electionType.electionType = ? " +
				" group by model.boothLocalBodyWard.localBodyWard.constituencyId order by model.boothLocalBodyWard.localBodyWard.name",params);
	}
	
	public List findAssemblyConstituenciesDetailsForParliament(String assemblyIds, String electionYear){
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, sum(model.maleVoters)," +
				"sum(model.femaleVoters), sum(model.totalVoters) from Booth model where model.constituency.constituencyId in("+assemblyIds+") " +
				"and model.year = (select max(model1.year) from Booth model1 where model1.year <= ?) group by model.constituency.constituencyId " +
				"order by model.constituency.name, model.year desc",new Long(electionYear));
	}
	
	public List findVoterInformationByMandalIdsAndDelimitationYear (String mandalsIds,String year, Long constituencyId)
	{
		Object[] params = {new Long(year), constituencyId};
		return getHibernateTemplate().find("select model.tehsil.tehsilId," +
				" model.tehsil.tehsilName,sum(model.maleVoters),sum(model.femaleVoters),sum(model.totalVoters) " +
				" from Booth model where model.tehsil.tehsilId in (" +mandalsIds+") and model.year = (select max(model1.year) from Booth model1 " +
				" where model1.year <= ?) and model.constituency.constituencyId =? and model.localBody is null group by " +
				" model.tehsil.tehsilId order by model.tehsil.tehsilName",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findByConstituencyElectionAndPartNo(
			Long constituencyId, Long electionYear, String partNos) {
		Object[] params = {electionYear, constituencyId};
		return getHibernateTemplate().find("select distinct model.boothId from Booth model where " +
				"model.partNo in ( "+ partNos + ") and model.year = ? and model.constituency.constituencyId = ?",params);
	}
	
		public List findBoothsInfoForAMandalByConstituencyAndYear(Long tehsilId, Long year, Long constituencyId){
		Object[] params = {constituencyId, tehsilId, year};
		return getHibernateTemplate().find("select model.boothId, model.partNo, model.location from Booth model where model.constituency.constituencyId = ? and " +
				"model.tehsil.tehsilId = ? and model.year = ? and model.localBody is null", params);
	}
	
	public List findBoothsInfoForALocalElectionBodyByConstituencyAndYear(Long localBodyId, Long year, Long constituencyId){
		Object[] params = {constituencyId, localBodyId, year};
		return getHibernateTemplate().find("select model.boothId, model.partNo, model.location, model.villagesCovered from Booth model where model.constituency.constituencyId = ? and " +
				"model.localBody.localElectionBodyId = ? and model.year = ? ", params);
	}
	
	public List findBoothsInfoForALocalBodyWardByConstituencyAndYear(Long localBodyWardId, Long year, Long constituencyId){
		Object[] params = {constituencyId, localBodyWardId, year};
		return getHibernateTemplate().find("select model.boothId, model.partNo, model.location, model.villagesCovered from Booth model where model.constituency.constituencyId = ? and " +
				"model.boothLocalBodyWard.localBodyWard.constituencyId = ? and model.year = ? ", params);
	}
	@SuppressWarnings("unchecked")
	public List<Long> getCountOfPartNumbersInAConstituency(Long constituencyId, Long electionYear) {
		Object[] params = {electionYear, constituencyId};
		return getHibernateTemplate().find("select count(*) from Booth model where " +
				"and model.year = ? and model.constituency.constituencyId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getPartNumbersAndVillagesCoveredInAConstituency(Long constituencyId, Long electionYear) {
		Object[] params = {electionYear, constituencyId};
		return getHibernateTemplate().find("select model.partNo,model.villagesCovered from Booth model where " +
				" model.year = ? and model.constituency.constituencyId = ?",params);
	}
	
	public int updateVillagesCoveredInfoInAConstituency(Long constituencyId, String villagesCovered,String partNo,Long electionYear) {
		
		StringBuilder query = new StringBuilder();
		query.append(" update Booth model set model.villagesCovered = ? ");
		query.append(" where model.constituency.constituencyId = ? ");
		query.append(" and model.partNo = ? ");
		query.append(" and model.year = ? ");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setString(0, villagesCovered);
		queryObject.setLong(1, constituencyId);		
		queryObject.setString(2, partNo);
		queryObject.setLong(3, electionYear);
		
		return queryObject.executeUpdate();
	}

	public List findBoothsInLocalElectionBodies(String localElectionBodyIds,
			String constituencyIds, Long year) {
		
		return getHibernateTemplate().find("select model.boothId, model.partNo, model.constituency.constituencyId, model.localBody.localElectionBodyId from Booth model where model.constituency.constituencyId in ( " +constituencyIds+ " ) and " +
					"model.localBody.localElectionBodyId in (" + localElectionBodyIds + ") and model.year = ? ", year);		
	}

	public List findBoothsInTehsils(String tehsilIds, String constituencyIds,
			Long year) {
		return getHibernateTemplate().find("select model.boothId, model.partNo, model.constituency.constituencyId, model.tehsil.tehsilId from Booth model where model.constituency.constituencyId in ( " +constituencyIds+ " ) and " +
				"model.tehsil.tehsilId in (" + tehsilIds + ") and model.year = ? ", year);
	}

	public List getVillageToBoothByBooths(String boothIds) {
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName, model.boothId, model.partNo, model.location, model.villagesCovered" +
				" from Booth model where model.boothId in("+boothIds+") ");
	}
	
	public List getLocalElectionBodyToBoothByBooths(String localElectionBodyIds) {
		return getHibernateTemplate().find("select model.localBody.localElectionBodyId, model.localBody.name, model.boothId, model.partNo, model.location, model.villagesCovered" +
				" from Booth model where model.boothId in("+localElectionBodyIds+") ");
	}
	
		public int removeMappingToLocalBody(List<Long> boothIds) {
				
				StringBuilder query = new StringBuilder();
				query.append("update Booth model set model.localBody = null");
				query.append(" where model.boothId in (:boothIds)");
				
				Query queryObject = getSession().createQuery(query.toString());
				
				queryObject.setParameterList("boothIds", boothIds);
				
				return queryObject.executeUpdate();
			}

		public List<Booth> getPollingStationByConstituencyId(Long constituencyId)
		{
			return getHibernateTemplate().find("from Booth model where model.year = 2009 and model.constituency.constituencyId =? ", constituencyId);	
		}
		//get Polling Stations By MandalId
		public List<Booth> getPollingStationByMandalId(Long mandalId)
		{
			return getHibernateTemplate().find("from Booth model where model.year = 2009 and model.tehsil.tehsilId =? ", mandalId);	
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsInAMandalByPublication(Long mandalId, Long publicationId)
		{	
			Object[] params = {mandalId,publicationId};
			return getHibernateTemplate().find(" select model.boothId,model.partNo from Booth model where model.tehsil.tehsilId = ? and model.publicationDate.publicationDateId = ? ",params);
		}
	
		public List<Booth> getBoothsByPublicationDateTehsilConstituenctPartNos(Long publicationDateId,Long thesilId,Long constituencyId,String partNos){
			Object[] params = {publicationDateId,thesilId, constituencyId};
			return getHibernateTemplate().find("from Booth model where model.publicationDate.publicationDateId = ? and model.tehsil.tehsilId = ? and model.constituency.constituencyId = ? and model.partNo in ("+partNos+") ", params);	
		}
		/**
		 * @author prasad
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getPublicationDetailsBasedOnConstituency(Long constituencyId)
		{
			return getHibernateTemplate().find("select distinct model.publicationDate.publicationDateId, " +
					"model.publicationDate.date from Booth model where " +
					"model.constituency.constituencyId = ? order by model.publicationDate.date)",constituencyId);
		}
		
		@SuppressWarnings("unchecked")
		public List<Booth> findByPublicationDateConstituencyAndPartNo(Long stateId,
				Long districtId, String constituencyName ,Long publicationDateId,String  partNo){
			
			Object[] params ={constituencyName, stateId, districtId, publicationDateId,  partNo}; 
			return getHibernateTemplate().find("from Booth model " +
					"where model.constituency.name = ? " +
					"and model.constituency.state.stateId = ? " +
					"and model.constituency.district.districtId = ? " +
					"and model.publicationDate.publicationDateId = ? " +					
					"and model.partNo = ?",params);
		}
		
		public List<Object[]> findBoothsInLocalElectionBodyByPublicationDateId(
				Long localElectionBodyId, Long publicationDateId) {		
			
			Object[] params ={localElectionBodyId, publicationDateId};
			
			return getHibernateTemplate().find("select model.boothId , model.partNo from Booth model where model.localBody.localElectionBodyId = ? and model.publicationDate.publicationDateId = ?",params);
			
		}
		
	/*@SuppressWarnings("unchecked")
	public List<Long> getTehsilIdsByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId) {
		
		Object[] params ={constituencyId, publicationDateId};
		
		return getHibernateTemplate().find("select distinct model.tehsil.tehsilId from Booth model where model.constituency.constituencyId = ? " +
				"and model.publicationDate.publicationDateId = ?" , params);
			
		}*/

		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsInAConstituencyByPublication(Long constituencyId, Long publicationId)
		{	
			Object[] params = {constituencyId,publicationId};
			return getHibernateTemplate().find(" select model.boothId,model.partNo from Booth model where model.constituency.constituencyId = ? and model.publicationDate.publicationDateId = ? ",params);
		}
		
		public List<Booth> getBoothDetailsByBoothId(Long boothid)
		{
			Object[] params={boothid};
		return getHibernateTemplate().find("from Booth model where model.boothId=?",params);
		}

	    public List<Object[]> getBoothsInAPanchayat(Long panchayatId,Long publicationDateId){
	    	Object[] params={panchayatId,publicationDateId};
	    	return getHibernateTemplate().find("select model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where model.panchayat.panchayatId = ? and model.publicationDate.publicationDateId = ?",params);
	    }
	    
	    public List<Object[]> getBoothsInAMunicipality(Long lclElecBodyId,Long publicationDateId){
	    	Object[] params={lclElecBodyId,publicationDateId};
	    	return getHibernateTemplate().find("select model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where model.localBody.localElectionBodyId = ? and model.publicationDate.publicationDateId = ? order by model.partNo",params);
	    }
	    
	    public List<Long> getStateIdByPublicationId(Long publicationDateId){
	    	return getHibernateTemplate().find("select distinct model.constituency.state.stateId from Booth model where model.publicationDate.publicationDateId = ? ",publicationDateId);
	    }
	    
	    public List<Object[]> getBoothInfo(Long publicationId,Long constituencyId,String partNo){
	    	Object[] params={publicationId,constituencyId,partNo};
	    	return getHibernateTemplate().find("select model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model where model.publicationDate.publicationDateId = ? and model.constituency.constituencyId = ? and model.partNo =? ",params);
	    }
	    
	    public List<Long> getTotalaVotesByBoothIds(List<Long> boothIds)
		{
			Query queryObj = getSession().createQuery("select sum(model.totalVoters) from Booth model where model.boothId in (:boothIds) ");
			queryObj.setParameterList("boothIds", boothIds);
			return queryObj.list();
		}
	    
	    public List<Object[]> getBoothsInAPanchayatForPresentElectionYear(Long panchayatId,Long year){
	    	Object[] params={panchayatId,year};
	    	return getHibernateTemplate().find("select model.boothId,model.partNo from Booth model where model.panchayat.panchayatId = ? and model.year = ?",params);
	    }
	    
	    public List<Object[]> getBoothsCount(Long id,Long publicationDateId,String type)
		  {
			  
			 
			  StringBuilder str = new StringBuilder();
			  //str.append("select distinct count(model.boothId) from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			  str.append("select distinct model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");

			  if(type.equalsIgnoreCase("constituency"))
				  str.append(" model.constituency.constituencyId = :id ");
			  else if(type.equalsIgnoreCase("mandal"))
				  str.append(" model.tehsil.tehsilId = :id and model.localBody is null ");
			  else if(type.equalsIgnoreCase("localElectionBody"))
				  str.append(" model.localBody.localElectionBodyId = :id ");
			  else if(type.equalsIgnoreCase("panchayat"))
			    str.append(" model.panchayat.panchayatId = :id ");
			  else if(type.equalsIgnoreCase("ward"))
				  str.append(" model.localBodyWard.constituencyId = :id ");
			  Query query =getSession().createQuery(str.toString());
			  query.setParameter("id",id);
			  query.setParameter("publicationDateId",publicationDateId);
			  
			return query.list();
			  
		  }
	    

	    public List<Long> getAllPublicationDetailsForConstituency(Long constituencyId){
	    	
	    	Query query = getSession()
					.createQuery(
							"select distinct model.publicationDate.publicationDateId from Booth model where model.constituency.constituencyId = ? " +
							" and model.publicationDate.publicationDateId not in (select distinct model1.publicationDate.publicationDateId from PublicationElection model1)");
			
			
			query.setParameter(0, constituencyId);
		
		return query.list();
	    	
	    }
	    
	    @SuppressWarnings("unchecked")
		public List<Object[]> getBoothIdsByPanchayatIdsInAPublication(List<Long> panchayatIds,Long publicationDateId)
	    {
	    	Query query = getSession().createQuery("select model.boothId, model.panchayat.panchayatId from Booth model where model.panchayat.panchayatId in(:panchayatIds) and " +
	    			" model.publicationDate.publicationDateId = :publicationDateId and model.localBody is null ");
	    	query.setParameterList("panchayatIds", panchayatIds);
	    	query.setParameter("publicationDateId",publicationDateId);
	    	return query.list();
	    }
		
		@SuppressWarnings("unchecked")
		public List<Long> checkForUrbanBooth(Long boothId,Long publicationDateId)
		{
			Query query = getSession().createQuery("select model.boothId from Booth model where model.localBody is not null and model.panchayat is null and model.boothId = :boothId and " +
					" model.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("boothId",boothId);
			query.setParameter("publicationDateId",publicationDateId);
			
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothIdsInLocalBodiesForAPublication(List<Long> localBodiesList, Long publicationDateId)
		{
			Query query = getSession().createQuery("select model.boothId,model.localBody.localElectionBodyId from Booth model where model.localBody.localElectionBodyId in(:localElectionBodyId) " +
					" and model.publicationDate.publicationDateId = :publicationDateId and model.panchayat is null ");
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("localElectionBodyId", localBodiesList);
			return query.list(); 
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(String type, Long publicationDateId, List<Long> panchayatIdsList)
		{
			StringBuilder str = new StringBuilder();
			str.append("select distinct model.boothId from Booth model where model.publicationDate.publicationDateId = :publicationDateId ");
			if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			  str.append(" and model.panchayat.panchayatId in (:id) and model.localBody is null ");
			else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
				str.append(" and model.localBody.localElectionBodyId in (:id) and model.panchayat is null ");
			Query query = getSession().createQuery(str.toString());
			
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("id", panchayatIdsList);
			
			return query.list();
		}
		 
		/**
		 * This Method is used to get all the booths in the constituency based on 
		 * constituencyId and publicationDateId
		 * @author Prasad Thiragabathina
		 * @param constituencyId
		 * @param publicationDateId
		 * @return List<Long>
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsBasedOnConstituencyAndPublicationDate(
				Long constituencyId, Long publicationDateId) {
			Object[] parms = {constituencyId,publicationDateId};
			return getHibernateTemplate().find("select model.boothId ,model.partNo from Booth model where " +
					"model.constituency.constituencyId = ? " +
					"and model.publicationDate.publicationDateId = ? ",parms);
		}
		public List<Object[]> getBoothInWard(Long wardId, Long publicationDateId)
		{
			Query query = getSession().createQuery("select distinct model.boothId,model.partNo, model.location, model.villagesCovered from Booth model " +
					"where model.localBodyWard.constituencyId = :id and model.publicationDate.publicationDateId =:publicationDateId"); 
					
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("id", wardId);
			return query.list(); 
		}
		public List<Object[]> getWardsInMuncipality(Long id, Long publicationDateId)
		{
			Query query = getSession().createQuery("select distinct model.localBodyWard.constituencyId,model.localBodyWard.name from Booth model " +
					"where model.localBody.localElectionBodyId in(select a.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody a " +
					"where a.assemblyLocalElectionBodyId = :id ) and model.publicationDate.publicationDateId =:publicationDateId "); 
					
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("id", id);
			return query.list(); 
		}
		public List<Object> getNoOfWardsInMuncipality(Long id, Long publicationDateId)
		{
			Query query = getSession().createQuery("select count( distinct model.localBodyWard.constituencyId) from Booth model " +
					"where model.localBody.localElectionBodyId = :id and model.publicationDate.publicationDateId =:publicationDateId" );
								
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("id", id);
			return query.list(); 
		}
		
		
		public List<Object[]> getWardsByLocalElecBodyId(Long id,Long publicationDateId){
			StringBuilder query = new StringBuilder();
			query.append("select distinct model.localBodyWard.constituencyId,model.localBodyWard.name from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			query.append(" model.localBody.localElectionBodyId = :id ");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("id", id);
			return queryObj.list();
	}
		
		public List<Object[]> getWardsByLocalElecBodyIds(List<Long> ids,Long publicationDateId){
			

			StringBuilder query = new StringBuilder();
			query.append("select distinct model.localBodyWard.constituencyId,model.localBody.localElectionBodyId from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			query.append(" model.localBody.localElectionBodyId in (:ids) and model.localBodyWard.constituencyId != null");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameterList("ids", ids);
			return queryObj.list();
	
			
			
		}
		
		public List<Object[]> getBoothsForWard(Long wardId,Long publicationDateId){
			StringBuilder query = new StringBuilder();
			query.append("select distinct model.boothId,model.partNo from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			query.append(" model.localBodyWard.constituencyId = :wardId ");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("wardId", wardId);
			return queryObj.list();
		}
		
		public List<Long> getBoothIdsForWard(Long wardId,Long publicationDateId){
			StringBuilder query = new StringBuilder();
			query.append("select distinct model.boothId from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			query.append(" model.localBodyWard.constituencyId = :wardId ");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("wardId", wardId);
			return queryObj.list();
		}
		
		public List<Long> getWardIdsByLocalElectionBodyIds(List<Long> localElectionBodyIds){
			
			Query query = getSession().createQuery("select distinct model.localBodyWard.constituencyId from " +
					" Booth model where " +
					" model.localBody.localElectionBodyId in (:localElectionBodyIds) and model.localBodyWard is not null");
			
			query.setParameterList("localElectionBodyIds", localElectionBodyIds);
			
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getWardIdsByLocalEleBodyIdsList(List<Long> localEleBodyIds, Long publicationDateId)
		{
			Query query = getSession().createQuery("select distinct model.localBodyWard.constituencyId from Booth model where model.publicationDate.publicationDateId =:publicationDateId and " +
					" model.localBody.localElectionBodyId in (:localEleBodyIds) and model.localBodyWard.constituencyId != null ");
			
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("localEleBodyIds", localEleBodyIds);
			return query.list();
		}
	
		public List<Long> getBoothsCountByPublicationId(String type,Long id,Long publicationDateId){
			StringBuilder query = new StringBuilder();
			query.append("select  count(model.boothId) from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			if(type.equalsIgnoreCase("constituency"))
				query.append(" model.constituency.constituencyId = :id ");
			else if(type.equalsIgnoreCase("mandal"))
				query.append(" model.tehsil.tehsilId = :id and model.localBody is null ");
			else if(type.equalsIgnoreCase("panchayat"))
				query.append(" model.panchayat.panchayatId = :id ");
			else if(type.equalsIgnoreCase("booth"))
				query.append(" model.boothId = :id ");
			else if(type.equalsIgnoreCase("ward"))
				query.append(" model.localBodyWard.constituencyId = :id ");
			else if(type.equalsIgnoreCase("localBody"))
				query.append(" model.localBody.localElectionBodyId = :id ");
			
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("id", id);
			return queryObj.list();
		}
		
		public List<Booth> getBoothByPartNoPublicationId(Long constituencyId,Long publicationId,String partNo){
			Query query = getSession().createQuery("from  Booth model where model.partNo = :partNo and model.constituency.constituencyId = :constituencyId" +
					" and model.publicationDate.publicationDateId = :publicationId ");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("partNo", partNo);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
}
