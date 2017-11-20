package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.columns.enums.BoothColumnNames;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
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
	
	public List<Object[]> findBoothsInfoForALocalBodyWardByConstituencyAndPublicationId(Long wardId , Long constituencyId , Long publicationId)
	{
		Object[] params = {constituencyId, wardId, publicationId};
		return getHibernateTemplate().find("select model.boothId, model.partNo" +
				" from Booth model where model.constituency.constituencyId = ? and " +
				"model.boothLocalBodyWard.localBodyWard.constituencyId = ? and model.publicationDate.publicationDateId = ? ", params);
	
		
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
		public List<Booth> getPollingStationByMandalId(Long mandalId, Long constituencyId)
		{
			Object[] params = {mandalId, constituencyId};
			return getHibernateTemplate().find("from Booth model where model.year = 2009 and model.tehsil.tehsilId =? and model.constituency.constituencyId = ?", params);	
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
			return getHibernateTemplate().find(" select distinct model.boothId,model.partNo from Booth model where model.constituency.constituencyId = ? and model.publicationDate.publicationDateId = ? order by model.boothId",params);
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
	    
	    public List<Object[]> getBoothsInAMunicipality(Long lclElecBodyId,Long publicationDateId,Long constituencyId){
	    	Object[] params={lclElecBodyId,publicationDateId,constituencyId};
	    	return getHibernateTemplate().find("select model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where model.localBody.localElectionBodyId = ? and model.publicationDate.publicationDateId = ? " +
	    			" and model.constituency.constituencyId = ? order by model.partNo",params);
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
	    
	    public List<Object[]> getBoothsInAPanchayatByPublicationId(Long panchayatId,Long publicationId)
	    {
	    	
	    	Query query = getSession().createQuery("select model.boothId,model.partNo from Booth model where " +
	    			"model.publicationDate.publicationDateId = ? and model.panchayat.panchayatId = ?");
	    	
	    	query.setParameter(0, publicationId);
	    	query.setParameter(1, panchayatId);
	    	
	    	return query.list();
	    	
	    }
	    
	    public List<Object[]> getBoothsInAPanchayatForPresentElectionYear(Long panchayatId,Long year){
	    	Object[] params={panchayatId,year};
	    	return getHibernateTemplate().find("select model.boothId,model.partNo from Booth model where model.panchayat.panchayatId = ? and model.year = ?",params);
	    }
	    
	    public List<Object[]> getBoothsCount(Long id,Long publicationDateId,String type,Long constituencyId,Long tehsilId)
		  {
			  
			 
			  StringBuilder str = new StringBuilder();
			  //str.append("select distinct count(model.boothId) from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			  str.append("select distinct model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");

			  if(type.equalsIgnoreCase("constituency"))
				  str.append(" model.constituency.constituencyId = :id ");
			  else if(type.equalsIgnoreCase("mandal"))
				  str.append(" model.tehsil.tehsilId = :id and model.localBody is null and model.constituency.constituencyId = :constituencyId ");
			  else if(type.equalsIgnoreCase("localElectionBody"))
				  str.append(" model.localBody.localElectionBodyId = :id and model.constituency.constituencyId = :constituencyId ");
			  else if(type.equalsIgnoreCase("panchayat"))
			    str.append(" model.panchayat.panchayatId = :id and model.constituency.constituencyId = :constituencyId and model.tehsil.tehsilId = :tehsilId ");
			  else if(type.equalsIgnoreCase("ward"))
				  str.append(" model.localBodyWard.constituencyId = :id ");
			  Query query =getSession().createQuery(str.toString());
			  query.setParameter("id",id);
			  query.setParameter("publicationDateId",publicationDateId);
			  if(type.equalsIgnoreCase("localElectionBody") || type.equalsIgnoreCase("mandal"))
				  query.setParameter("constituencyId",constituencyId);
			  if(type.equalsIgnoreCase("panchayat")){
				  query.setParameter("constituencyId",constituencyId);
				  query.setParameter("tehsilId",tehsilId);
			  }
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
	    	Query query = getSession().createQuery("select model.boothId, model.panchayat.panchayatId, model.partNo, model.villagesCovered from Booth model where model.panchayat.panchayatId in(:panchayatIds) and " +
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
		public List<Object[]> getBoothIdsInLocalBodiesForAPublication(List<Long> localBodiesList, Long publicationDateId,Long constituencyId)
		{
			Query query = getSession().createQuery("select model.boothId,model.localBody.localElectionBodyId from Booth model where model.localBody.localElectionBodyId in(:localElectionBodyId) " +
					" and model.publicationDate.publicationDateId = :publicationDateId and model.constituency.constituencyId = :constituencyId and model.panchayat is null ");
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("localElectionBodyId", localBodiesList);
			query.setParameter("constituencyId", constituencyId);
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
		public List<Object> getNoOfWardsInMuncipality(Long id, Long publicationDateId,Long constituencyId)
		{
			Query query = getSession().createQuery("select count( distinct model.localBodyWard.constituencyId) from Booth model " +
					"where model.localBody.localElectionBodyId = :id and model.publicationDate.publicationDateId =:publicationDateId" +
					" and model.constituency.constituencyId=:constituencyId" );
								
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("id", id);
			query.setParameter("constituencyId", constituencyId);
			return query.list(); 
		}
		
		
		public List<Object[]> getWardsByLocalElecBodyId(Long id,Long publicationDateId,Long constituencyId){
			StringBuilder query = new StringBuilder();
			query.append("select distinct model.localBodyWard.constituencyId,model.localBodyWard.name from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			query.append(" model.localBody.localElectionBodyId = :id and model.constituency.constituencyId = :constituencyId ");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("constituencyId", constituencyId);
			queryObj.setParameter("id", id);
			return queryObj.list();
	}
		
		public List<Object[]> getWardsByLocalElecBodyIds(List<Long> ids,Long publicationDateId,Long constituencyId){
			

			StringBuilder query = new StringBuilder();
			query.append("select distinct model.localBodyWard.constituencyId,model.localBody.localElectionBodyId from Booth model where model.publicationDate.publicationDateId = :publicationDateId and ");
			query.append(" model.localBody.localElectionBodyId in (:ids) and model.constituency.constituencyId = :constituencyId and model.localBodyWard.constituencyId != null ");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameterList("ids", ids);
			queryObj.setParameter("constituencyId", constituencyId);
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
	
		public List<Long> getBoothsCountByPublicationId(String type,Long id,Long publicationDateId,Long constituencyId){
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
			if(!("constituency".equalsIgnoreCase(type)))
				query.append(" and model.constituency.constituencyId = :constituencyId ");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("id", id);
			if(!("constituency".equalsIgnoreCase(type)))
				queryObj.setParameter("constituencyId", constituencyId);
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
		
		public Booth getBoothByPartNoAndPublicationIdInAConstituency(String partNo, Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery(" select model from Booth model where model.constituency.constituencyId = :constituencyId and " +
					" model.publicationDate.publicationDateId = :publicationDateId and model.partNo = :partNo ");
			query.setParameter("partNo",partNo);
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return (Booth)query.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Booth> getBoothsByPartNosAndPublicationIdInAConstituency(List<String> partNosList, Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery(" select model from Booth model where model.constituency.constituencyId = :constituencyId and " +
					" model.publicationDate.publicationDateId = :publicationDateId and model.partNo in (:partNosList) group by model.boothId order by model.boothId ");
			query.setParameterList("partNosList",partNosList);
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothIdsAndPartNosOfAConstituencyInAPublication(Long constituencyId,Long publicationDateId)
		{
			Query query = getSession().createQuery("select model.boothId,model.partNo from Booth model where model.constituency.constituencyId = :constituencyId " +
					" and model.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return query.list();
		}
		public List<Object[]> getBoothLocations(String partNo,Long constituencyId){
			Query query = getSession().createQuery("select distinct model.location,model.villagesCovered,model.publicationDate.publicationDateId,model.publicationDate.date from" +
					" Booth model where model.partNo = :partNo and model.constituency.constituencyId = :constituencyId order by model.publicationDate.date desc");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("partNo", partNo);
			return query.list();
		}
		public Integer updatePanchayatByBoothId(Long boothId, Long panchayatId)
		{
			Query query = getSession().createQuery("update Booth model set model.panchayat.panchayatId = :panchayatId where model.boothId = :boothId");
			query.setParameter("boothId",boothId);
			query.setParameter("panchayatId",panchayatId);
			
			return query.executeUpdate();
		}
		@SuppressWarnings("unchecked")
		public List getPartNoByBoothId(Long boothId)
		{
		return getHibernateTemplate().find("select model.partNo,model.publicationDate.publicationDateId from Booth model where model.boothId = ?",boothId);	
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsInAMandalByPublicationAndConstId(Long mandalId, Long publicationId, Long constituencyId)
		{	
			Object[] params = {mandalId,publicationId,constituencyId};
			return getHibernateTemplate().find(" select model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where model.tehsil.tehsilId = ? and model.publicationDate.publicationDateId = ? " +
					" and model.constituency.constituencyId = ?",params);
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsByPanchayatId(Long panchayatId){
			Object[] params = {panchayatId};
			String queryString =null;

			queryString="select model.boothId,model.partNo from Booth model where model.panchayat.panchayatId=?";
			
			return getHibernateTemplate().find(queryString,params);
			
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getConstituencyForPanchayat(Long panchayatId){
			Object[] params = {panchayatId};
			String queryString =null;
			queryString="select distinct model.constituency.constituencyId from Booth model where model.panchayat.panchayatId=? and " +
					" model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ";
			return getHibernateTemplate().find(queryString,params);
		}
		
		@SuppressWarnings("unchecked")
		public List<String> getPartNoByPanchayatIdAndPublicationDateIdsList(Long locationValue, List<Long> publicationDateIdsList, Long constituencyId, String type)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("select model.partNo from Booth model where model.publicationDate.publicationDateId in (:publicationDateIdsList)  ");
			if(type.equalsIgnoreCase("panchayat"))
				stringBuilder.append(" and model.panchayat.panchayatId = :locationValue ");
			else if(type.equalsIgnoreCase("ward"))
				stringBuilder.append(" and model.localBodyWard.constituencyId = :locationValue and model.constituency.constituencyId = :constituencyId  ");
			
				Query queryObj = getSession().createQuery(stringBuilder.toString());
				
				queryObj.setParameter("locationValue", locationValue);
				queryObj.setParameterList("publicationDateIdsList", publicationDateIdsList);
				if(type.equalsIgnoreCase("ward"))
					queryObj.setParameter("constituencyId", constituencyId);
				
			return queryObj.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getWardsByLocalElecBodyIdAndPublicationIdsList(Long locationValue, List<Long> publicationDateIdsList, Long constituencyId){
			

			StringBuilder query = new StringBuilder();
			query.append("select distinct model.localBodyWard.constituencyId from Booth model where model.publicationDate.publicationDateId in (:publicationDateIdsList) and ");
			query.append(" model.localBody.localElectionBodyId = :locationValue and model.constituency.constituencyId = :constituencyId and model.localBodyWard.constituencyId != null ");
			Query queryObj = getSession().createQuery(query.toString()) ;
			queryObj.setParameterList("publicationDateIdsList", publicationDateIdsList);
			queryObj.setParameter("locationValue", locationValue);
			queryObj.setParameter("constituencyId", constituencyId);
			return queryObj.list();
			
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Long> getBoothIdsByPanchayatIdAndPublicationDateIdsList(Long locationValue, List<Long> publicationDateIdsList, Long constituencyId, String type)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("select model.boothId from Booth model where model.publicationDate.publicationDateId in (:publicationDateIdsList)  ");
			if(type.equalsIgnoreCase("panchayat"))
				stringBuilder.append(" and model.panchayat.panchayatId = :locationValue ");
			else if(type.equalsIgnoreCase("ward"))
				stringBuilder.append(" and model.localBodyWard.constituencyId = :locationValue and model.constituency.constituencyId = :constituencyId  ");
			
				Query queryObj = getSession().createQuery(stringBuilder.toString());
				
				queryObj.setParameter("locationValue", locationValue);
				queryObj.setParameterList("publicationDateIdsList", publicationDateIdsList);
				if(type.equalsIgnoreCase("ward"))
					queryObj.setParameter("constituencyId", constituencyId);
				
			return queryObj.list();
		}
		
		@SuppressWarnings("unchecked")
		public List getBoothIdByPartNo(String partNo)
		{
		return getHibernateTemplate().find("select model.boothId from Booth model where model.partNo = ?",partNo);	
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Long> getBoothIdsByLocalValuesList(String locationType,Long locationValue,Long constituencyId,List<Long> publicationDateIdsList)
		{
		  StringBuilder stringBuilder = new StringBuilder();
		  stringBuilder.append("select model.boothId from Booth model where model.publicationDate.publicationDateId in (:publicationDateIdsList) ");
		  if(locationType.equalsIgnoreCase("Constituency"))
			  stringBuilder.append(" and model.constituency.constituencyId =:locationValue ");
		  else if(locationType.equalsIgnoreCase("Mandal"))
			  stringBuilder.append(" and model.tehsil.tehsilId =:locationValue ");
		  else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			  stringBuilder.append(" and model.localBody.localElectionBodyId =:locationValue ");
		  else if(locationType.equalsIgnoreCase("panchayat"))
			  stringBuilder.append(" and model.panchayat.panchayatId = :locationValue ");
		  else if(locationType.equalsIgnoreCase("ward"))
			  stringBuilder.append(" and model.localBodyWard.constituencyId = :locationValue ");
		  else if(locationType.equalsIgnoreCase("booth"))
			  stringBuilder.append(" and model.boothId = :locationValue ");
			  
		  stringBuilder.append(" and model.constituency.constituencyId = :constituencyId ");
		  
		  Query queryObj = getSession().createQuery(stringBuilder.toString());
		  queryObj.setParameterList("publicationDateIdsList", publicationDateIdsList);
		  queryObj.setParameter("locationValue", locationValue);
		  queryObj.setParameter("constituencyId", constituencyId);
		 return queryObj.list();
		}

		@SuppressWarnings("unchecked")
		public List<String> getPartNosByBoothIdsList(Long constituencyId, Long publicationDateId, List<Long> boothIdsList)
		{
			Query queryObj = getSession().createQuery("select model.partNo from Booth model where model.boothId in(:boothIds) " +
					" and model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId  ");
			
			queryObj.setParameter("constituencyId", constituencyId);
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameterList("boothIds", boothIdsList);
			
			return queryObj.list();
		}
	
		public Long getBoothsInPanchayatDAO(long panchayatId)
		{
			Query query = getSession().createQuery("select count(distinct model.partNo) from Booth model " +
					" where model.panchayat.panchayatId = :panchayatId");
			query.setParameter("panchayatId", panchayatId);
			return (Long) query.uniqueResult();
			
		}
		
		public List<?> getVoterCountInPanchayat(Long panchayatId , Long publicationid,List<Long> boothIds)
		{
			return getHibernateTemplate().find("");
			
		}
		
		/**
		 * @author Sravanthi
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsInAPanchayatUsingConstituencyId(Long panchayatId,Long publicationDateId,Long constituencyId,String type,Long tehsilId){
			Object[] params={panchayatId,publicationDateId,constituencyId,tehsilId};
			
			 StringBuilder stringBuilder = new StringBuilder();
			  stringBuilder.append("select model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where  model.panchayat.panchayatId =:panchayatId and model.publicationDate.publicationDateId = :publicationDateId");
			  if(type.equalsIgnoreCase("Constituency") || type.equalsIgnoreCase("Mandal"))
				  stringBuilder.append(" and model.tehsil.tehsilId = :tehsilId and model.constituency.constituencyId = :constituencyId");
			 /* else if(type.equalsIgnoreCase("panchayat"))
				  stringBuilder.append(" and model.panchayat.panchayatId = :panchayatId and model.constituency.constituencyId = :constituencyId and model.tehsil.tehsilId = :tehsilId  ");*/
			 Query queryobj = getSession().createQuery(stringBuilder.toString());	    		
			 queryobj.setParameter("panchayatId", panchayatId);
	    	queryobj.setParameter("publicationDateId", publicationDateId);
	    	  if(type.equalsIgnoreCase("Constituency") || type.equalsIgnoreCase("Mandal"))
	    		  queryobj.setParameter("constituencyId", constituencyId);
	    	//if(type.equalsIgnoreCase("Mandal"))
	    	  queryobj.setParameter("tehsilId", tehsilId);
			return queryobj.list();
	    }
		
		public List<Object[]> getBoothsInPanchayat(long panchayatId)
		{
			Query query = getSession().createQuery("select distinct model.boothId,model.partNo from Booth model " +
					" where model.panchayat.panchayatId = :panchayatId");
			query.setParameter("panchayatId", panchayatId);
			
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothIdsByLocalEleBodyId(Long id,Long publicationId,Long constituencyId)
		{
			Query queryObj = getSession().createQuery("select model.boothId,model.partNo from Booth model where model.localBody.localElectionBodyId =:id " +
					" and model.publicationDate.publicationDateId =:publicationDateId and model.constituency.constituencyId =:constituencyId ");
			
			queryObj.setParameter("id", id);
			queryObj.setParameter("publicationDateId", publicationId);
			queryObj.setParameter("constituencyId", constituencyId);
			
			return queryObj.list();
			
		}
		
		public List<Object[]> getBoothsByPanchayatIDConstiId(Long panchayatId,Long constituencyId,Long publicationId){
			Query queryObj = getSession().createQuery("select model.boothId,model.partNo from Booth model where model.constituency.constituencyId = :constituencyId  " +
					" and model.publicationDate.publicationDateId = :publicationDateId and model.panchayat.panchayatId = :panchayatId ");
			
			queryObj.setParameter("panchayatId", panchayatId);
			queryObj.setParameter("publicationDateId", publicationId);
			queryObj.setParameter("constituencyId", constituencyId);
			
			return queryObj.list();
		}
		/**
		 * This DAO is used for getting all booths in a selected level (Constituency ,Mandal , panchayat) 
		 * @param Long id
		 * @param Long level
		 * @param Long publicationId
		 * @return List<Object[]>
		 */
		public List<Object[]> getAllBoothsInSelectedType(Long id, Long level,Long publicationId)
		{
			StringBuilder queryString = new StringBuilder();
			queryString.append("select b.boothId,b.partNo from Booth b where   ");
			if(level == 1)
			{
				queryString.append("b.constituency.constituencyId = :id");
			}
			else if(level == 2)
			{
				queryString.append("b.tehsil.tehsilId = :id");
			}
			else if(level == 3)
			{
				queryString.append("b.panchayat.panchayatId = :id");
			}
			queryString.append(" and b.publicationDate.publicationDateId = :publicationId" );
			
			Query query = getSession().createQuery(queryString.toString());
			query.setParameter("id", id);
			query.setParameter("publicationId", publicationId);
			return query.list();
			
		}
		/**
		 * This DAO is used for getting all booths in a selected level (Mandal , panchayat) 
		 * @param String type
		 * @param List<Long> ids
		 * @param Long publicationId
		 * @return List<Object[]>
		 */
		public List<Object[]> getAllBoothsForPanchayatsOrMandals(String type,List<Long> ids,Long publicationId)
		{
			StringBuilder queryString = new StringBuilder();
			queryString.append("select b.boothId,b.partNo, ");
			if(type.equalsIgnoreCase("mandal"))
			{
				queryString.append("b.tehsil.tehsilName from Booth b where  b.tehsil.tehsilId in (:ids) and b.localBody.localElectionBodyId is null ");
			}
			else if(type.equalsIgnoreCase("panchayat"))
			{
				queryString.append("b.panchayat.panchayatName from Booth b where b.panchayat.panchayatId in (:ids)");
			}
			queryString.append(" and  b.publicationDate.publicationDateId = :publicationId" );
			Query query = getSession().createQuery(queryString.toString());
			query.setParameterList("ids", ids);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
		/**
		 * This DAO is used for getting all booths in a selected wards 
		 * List<Long> ids
		 * @return List<Object[]>
		 */
		public List<Object[]> getBoothsForSelectedWards(List<Long> ids,Long publicationId)
		{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select distinct model.boothId,model.partNo from Booth model " +
			 		"where model.localBodyWard.constituencyId in (:ids)  ");
			Query query = getSession().createQuery(queryString.toString());
			query.setParameterList("ids", ids);
			//query.setParameter("publicationId", publicationId);
			return query.list();
		}
		
		public List<String> getPartNosForBooths(List<Long> locationIds){
			Query queryObj = getSession().createQuery("select model.partNo from Booth model where model.boothId in(:locationIds) ");
			queryObj.setParameterList("locationIds", locationIds);
			return queryObj.list();
		}
		/**
		 * This DAO is used for getting all booths in a muncipality
		 * @param Long localBodyId
		 * @param Long publicationId
		 * @return List<Object[]>
		 */
		public List<Object[]> getAllBoothsInAMuncipality(Long localBodyId,Long publicationId)
		{
			Query query = getSession().createQuery("select model.boothId,model.partNo, model.villagesCovered from Booth model " +
					" where model.localBody.localElectionBodyId = :localBodyId and " +
					"  model.publicationDate.publicationDateId = :publicationId");
			query.setParameter("localBodyId", localBodyId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getMandalsListByConstituencyId(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery(" select distinct model.tehsil.tehsilId from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getPanchayatsListByConstituencyId(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.panchayat.panchayatId from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId and model.panchayat is not null ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getMuncipalitiesListByConstituencyId(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId and model.localBody is not null ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getMuncipalitiesListNamesByConstituencyId(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId,model.localBody.name,model.localBody.electionType.electionType from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId and model.localBody is not null ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Long> getBoothsListByConstituencyId(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.boothId from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getWardsListByConstituencyId(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery(" select distinct model.localBodyWard.constituencyId from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId " +
					" and model.localBodyWard.constituencyId is not null ");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsByBoothIdsList(List<Long> boothIdsList)
		{
			Query query = getSession().createQuery("select model.boothId,model.partNo,model.villagesCovered from Booth model where model.boothId in (:boothIdsList)");
			query.setParameterList("boothIdsList", boothIdsList);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getMuncipalitiesByMuncipalityIdsList(Long constituencyId,Long publicationId,List<Long> muncipalityIdsList)
		{
			Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId,model.localBody.name from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId and model.localBody.localElectionBodyId in (:muncipalityIdsList) and model.localBody is not null order by model.localBody.name ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			query.setParameterList("muncipalityIdsList", muncipalityIdsList);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getWardsByWardIdsList(Long constituencyId,Long publicationId,List<Long> wardIdsList)
		{
			Query query = getSession().createQuery(" select distinct model.localBodyWard.constituencyId,model.localBodyWard.name from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId and " +
					" model.localBodyWard.constituencyId in (:wardIdsList) and model.localBodyWard.constituencyId is not null order by model.localBodyWard.name ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			query.setParameterList("wardIdsList", wardIdsList);
			return query.list();
		}
		
		public List<Object[]> getPanchayatiesCountByTahsilAndConstituencyId(Long constituencyId,Long tehsilId,Long publicationId,String type){
			StringBuilder str = new StringBuilder();
			str.append("select distinct model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationId");
			
			if(type.equalsIgnoreCase("mandal"))
				str.append(" and model.tehsil.tehsilId =:tehsilId ");
			
			str.append(" order by model.panchayat.panchayatName ");
			Query query =getSession().createQuery(str.toString());
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationId", publicationId);
			if(type.equalsIgnoreCase("mandal"))
			query.setParameter("tehsilId", tehsilId);
			
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getPanchayatiesByMandalsListAndConstituencyId(List<Long> mandalsList,Long constituencyId,Long publicationDateId){
			
			Query query =getSession().createQuery("select model.tehsil.tehsilId," +//0
					" model.tehsil.tehsilName," +//1
					" model.panchayat.panchayatId," +//2
					" model.panchayat.panchayatName" +//3
					//" model.boothId ," +//4
					" from Booth model where " +//5
					" model.publicationDate.publicationDateId = :publicationDateId and model.constituency.constituencyId =:constituencyId and " +
					" model.tehsil.tehsilId in(:mandalsList)" +
					" group by model.tehsil.tehsilId,model.panchayat.panchayatId " +
					" order by model.tehsil.tehsilName, model.panchayat.panchayatName ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameterList("mandalsList",mandalsList);
			return query.list();
		}
		
		public Long getTotalVotersInBooths(List<Long> boothIds){
			Query query = getSession().createQuery("select sum(model.totalVoters) from Booth model where model.boothId in(:boothIds)");
			query.setParameterList("boothIds", boothIds);
			return (Long)query.uniqueResult();
		}
		
		public String getBoothPartNoByBoothId(Long boothId)
		{
			Query query = getSession().createQuery(" select model.partNo from Booth model where model.boothId =:boothId ");
			query.setParameter("boothId", boothId);
			return (String) query.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getPanchayatsByPanchayatIdsList(Long constituencyId,Long publicationId,List<Long> panchayatIdsList)
		{
			Query query = getSession().createQuery(" select distinct model.panchayat.panchayatId from Booth model where model.constituency.constituencyId =:constituencyId " +
					" and model.publicationDate.publicationDateId =:publicationDateId and model.panchayat.panchayatId in (:panchayatIdsList)");
			
			query.setParameter("publicationDateId", publicationId);
			query.setParameter("constituencyId", constituencyId);
			query.setParameterList("panchayatIdsList", panchayatIdsList);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsListByBoothIdsList(List<Long> boothIdsList)
		{
			Query query = getSession().createQuery("select model.boothId,model.partNo,model.constituency.constituencyId,model.constituency.name from Booth model where model.boothId in (:boothIdsList) order by model.constituency.name,model.boothId ");
			query.setParameterList("boothIdsList", boothIdsList);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsByPanchayatIdsListAndConstituencyIdInAPublication(List<Long> panchayatIdsList,Long constituencyId,Long publicationDateId)
		{
			Query query = getSession().createQuery("select model.panchayat.panchayatId,model.panchayat.panchayatName,model.boothId,model.partNo from Booth model where " +
					" model.publicationDate.publicationDateId =:publicationDateId and model.constituency.constituencyId =:constituencyId and model.panchayat.panchayatId in (:panchayatIdsList) " +
					" order by model.panchayat.panchayatName,model.boothId ");
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("constituencyId", constituencyId);
			query.setParameterList("panchayatIdsList", panchayatIdsList);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsInAMandalByPublicationAndConstIds(Long mandalId, Long publicationId, Long constituencyId)
		{	
			Object[] params = {mandalId,publicationId,constituencyId};
			return getHibernateTemplate().find(" select model.boothId,model.partNo,model.location,model.villagesCovered from Booth model where model.tehsil.tehsilId = ? and model.publicationDate.publicationDateId = ? " +
					" and model.panchayat.panchayatId  is not Null and model.constituency.constituencyId = ?",params);
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getPanchayatsCountByMandalId(Long mandalId,Long constituencyId,Long publicationDateId)
		{
			Query query = getSession().createQuery(" select count(distinct model.panchayat.panchayatId) from Booth model where model.tehsil.tehsilId =:tehsilId and " +
					" model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId ");
			
			query.setParameter("tehsilId", mandalId);
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getBoothsCountByLocationId(String type,Long id,Long constituencyId,Long publicationDateId)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("select count(model.boothId) from Booth model where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId  ");
			
			if(type.equalsIgnoreCase(IConstants.MANDAL))
				stringBuilder.append(" and model.tehsil.tehsilId =:id and model.localBody is null ");
			else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
				stringBuilder.append(" and model.panchayat.panchayatId =:id ");
			else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
				stringBuilder.append(" and model.localBody.localElectionBodyId =:id ");
			else if(type.equalsIgnoreCase(IConstants.WARD))
				stringBuilder.append(" and model.localBodyWard.constituencyId = :id  ");
			
			Query query = getSession().createQuery(stringBuilder.toString());
			query.setParameter("id", id);
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getWardsCountByLocalEleBodyId(Long localEleBodyId,Long publicationDateId,Long constituencyId)
		{
			Query query = getSession().createQuery(" select count(distinct model.localBodyWard.constituencyId) from Booth model where model.constituency.constituencyId =:constituencyId " +
					" and model.publicationDate.publicationDateId =:publicationDateId and model.localBody.localElectionBodyId =:localEleBodyId ");
			
			query.setParameter("localEleBodyId", localEleBodyId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("constituencyId", constituencyId);
			
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsForConstituencyList(List<Long> constituencyIds)
		{
			Query query = getSession().createQuery("select model.boothId,model.partNo,model.constituency.name from Booth model " +
					" where model.constituency.constituencyId in (:constituencyIds)");
			query.setParameterList("constituencyIds", constituencyIds);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsForLocalEleBodyByCOnstituencyId(Long constituencyId,Long localEleBodyId,Long publicationDateId)
		{
			Query query = getSession().createQuery(" select distinct model.localBodyWard.constituencyId,model.localBodyWard.name from Booth model where " +
					" model.constituency.constituencyId =:constituencyId and model.localBody.localElectionBodyId =:localElectionBodyId and " +
					" model.publicationDate.publicationDateId =:publicationDateId order by model.localBodyWard.name ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("localElectionBodyId", localEleBodyId);
			query.setParameter("publicationDateId", publicationDateId);
			
			return query.list();
		}

		
		@SuppressWarnings("unchecked")
		public List<Long> getWardIdsByLocalEleBodyId(Long localEleBodyId,Long publicationDateId,Long constituencyId)
		{
			Query query = getSession().createQuery(" select distinct model.localBodyWard.constituencyId from Booth model where model.constituency.constituencyId =:constituencyId " +
					" and model.publicationDate.publicationDateId =:publicationDateId and model.localBody.localElectionBodyId =:localEleBodyId ");
			
			query.setParameter("localEleBodyId", localEleBodyId);
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("constituencyId", constituencyId);
			
			return query.list();
		}

		@SuppressWarnings("unchecked")
		public Long getTotalaVotesForSelectedBooth(Long boothId)
		{
			Query queryObj = getSession().createQuery("select model.totalVoters from Booth model where model.boothId =:boothId ");
			queryObj.setParameter("boothId", boothId);
			return (Long) queryObj.uniqueResult();
		}
		
		/**
		 * This DAO is used to get all constituencys List From Constituences By BoothIds
		 * @param List<Long> boothIds
		 * @return List<Object[]>
		 * @date 19-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getConstityencysByBooths(List<Long> Ids)
		{
			Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name, model.boothId from Booth model where " +
					"  model.boothId in (:Ids)");
			query.setParameterList("Ids", Ids);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getConstituencyDetailsByTehsilId(Long tehsilId)
		{
			Query query = getSession().createQuery("select distinct model.constituency.constituencyId from Booth model " +
					" where model.tehsil.tehsilId = :tehsilId and model.publicationDate.publicationDateId =:publicationDateId ");
			query.setParameter("tehsilId", tehsilId);
			query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getboothsByTehsilId(Long tehsilId)
		{
			Query query = getSession().createQuery("select distinct model.boothId from Booth model " +
					" where model.tehsil.tehsilId = tehsilId ");
			query.setParameter("tehsilId", tehsilId);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getboothsByWardId(Long wardId)
		{
			Query query = getSession().createQuery("select distinct model.boothId from Booth model " +
					" where model.constituency.constituencyId = :wardIds) ");
			query.setParameter("wardIds", wardId);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getPanchayatsListByTehsilId(Long tehsilId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model " +
					" where model.tehsil.tehsilId =:tehsilId and model.publicationDate.publicationDateId =:publicationDateId and model.panchayat is not null ");
			
			query.setParameter("tehsilId", tehsilId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getPanchayatsIdsListByTehsilId(List<Long> tehsilIdsList,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.panchayat.panchayatId from Booth model " +
					" where model.tehsil.tehsilId in (:tehsilIdsList) and model.publicationDate.publicationDateId =:publicationDateId and model.panchayat is not null ");
			
			query.setParameterList("tehsilIdsList", tehsilIdsList);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsByPanchayat(Long panchayatId,Long publicationId)
		{
			Query query = getSession().createQuery("select model.boothId,model.partNo from Booth model " +
					" where model.panchayat.panchayatId = :panchayatId and " +
					" model.publicationDate.publicationDateId = :publicationId ");
			query.setParameter("panchayatId", panchayatId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
		
		public List<Long> getBoothsByPanchayatId(Long panchayatId,Long publicationId)
		{
			Query query = getSession().createQuery("select model.boothId from Booth model " +
					" where model.panchayat.panchayatId = :panchayatId and " +
					" model.publicationDate.publicationDateId = :publicationId ");
			query.setParameter("panchayatId", panchayatId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
		
		public Long getTotalVotesForBooth(List<Long> boothIdsList,Long constituencyId)
		{
			Query query = getSession().createQuery(" select sum(model.totalVoters) from Booth model where model.boothId in (:boothIdsList) and model.constituency.constituencyId =:constituencyId ");
			query.setParameter("constituencyId", constituencyId);
			query.setParameterList("boothIdsList", boothIdsList);
			return (Long) query.uniqueResult();
		}
		public List<Object[]> getPanchayatsNamesListByConstituencyId(Long constituencyId,Long publicationId){
			Query query = getSession().createQuery("select distinct model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model " +
					" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId and model.panchayat is not null ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationId);
			return query.list();
		}
		
		public List<Object[]> getTehsilListByConstituency(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.tehsil.tehsilId,model.tehsil.tehsilName from Booth model " +
					" where model.constituency.constituencyId = :constituencyId and " +
					" model.publicationDate.publicationDateId = :publicationId");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
		
		public List<Object[]> getLEBListByConstituency(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId,model.localBody.name from Booth model " +
					" where model.constituency.constituencyId = :constituencyId and " +
					" model.publicationDate.publicationDateId = :publicationId");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
		
		public List<Long> getTehsildByConstituency(Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select distinct model.tehsil.tehsilId from Booth model " +
					" where model.constituency.constituencyId = :constituencyId and " +
					" model.publicationDate.publicationDateId = :publicationId");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
		
		public List<Booth> getboothsDetailsByTehsilId(Long localElectionBodyId,Long publicationDateId){
			
			Query query=getSession().createQuery("select model from Booth model where model.localBody.localElectionBodyId = :localElectionBodyId and model.publicationDate.publicationDateId = :publicationDateId " +
					"order by model.partNo asc");
			query.setParameter("localElectionBodyId", localElectionBodyId);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
		}

		public Long getTotalVotesByBoothIdsList(List<Long> boothIdsList)
		{
			Query query = getSession().createQuery(" select sum(model.totalVoters) from Booth model where model.boothId in (:boothIdsList) ");
			query.setParameterList("boothIdsList", boothIdsList);
			return (Long) query.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothIdsByPanchayatIds(List<Long> locationValues,Long constituencyId,List<Long> publicationDateIdsList)
		{
			
			
			Query query = getSession().createQuery("select model.boothId , model.panchayat.panchayatId from " +
					"Booth model where model.panchayat.panchayatId in(:locationValues) " +
					"and  model.publicationDate.publicationDateId in (:publicationDateIdsList)" +
					"and model.constituency.constituencyId = :constituencyId " +
					"group by  model.panchayat.panchayatId");

			query.setParameterList("locationValues", locationValues);
			query.setParameter("constituencyId", constituencyId);
			query.setParameterList("publicationDateIdsList", publicationDateIdsList);
		    return query.list();
		 
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Booth> getBoothsListByConstituencyId(Long constituencyId)
		{
			Query query = getSession().createQuery(" from Booth model where model.constituency.constituencyId =:constituencyId ");
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getHamletIdsListByConstituencyId(Long constituencyId,Long publicationDateId)
		{
			Query query = getSession().createQuery(" select distinct model2.hamlet.hamletId from Booth model, PanchayatHamlet model2 " +
					" where model.panchayat.panchayatId = model2.panchayat.panchayatId and model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			
			 return query.list();
		}
		
		public Long getLatestPublicationDateIdForAConstituency(Long constituencyId)
		{
			Long id = null;
			Query query = getSession().createQuery("select model.publicationDate.publicationDateId from Booth model where model.constituency.constituencyId = :constituencyId order by model.publicationDate.date DESC");
			query.setParameter("constituencyId",constituencyId);
			query.setFirstResult(0);
			query.setMaxResults(1);
			try{
				id = (Long) query.list().get(0);
			}catch(Exception e){
				id = 0L;
			}
			 return id;
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getTehsilsForAConstituencyForAPublication(Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery("select distinct model.panchayat.tehsil.tehsilId,model.panchayat.tehsil.tehsilName,model.panchayat.tehsil.district.districtId from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getPanchayatsForAConstituencyForAPublication(Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery("select distinct model.panchayat.panchayatId,model.panchayat.panchayatName,model.panchayat.tehsil.tehsilId from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getHamletsForAConstituencyForAPublication(Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery("select distinct model.hamlet.hamletId, model.hamlet.hamletName, model.panchayat.panchayatId,model.panchayat.tehsil.tehsilId from PanchayatHamlet model,Booth model2 where " +
					" model.panchayat.panchayatId = model2.panchayat.panchayatId and model2.constituency.constituencyId = :constituencyId and model2.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Booth> getBoothOfAConstituencyInAPublication(Long constituencyId, Long publicationDateId)
		{
			Query query = getSession().createQuery("select model from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId ");
			query.setParameter("constituencyId",constituencyId);
			query.setParameter("publicationDateId",publicationDateId);
			return query.list();
		}
		
		
		public List<Object[]> getTotalVotesForBooth(List<Long> boothIds)
		{
			Query query = getSession().createQuery("select model.boothId,sum(model.totalVoters),model.location,model.villagesCovered from Booth model where model.boothId in(:boothIds) group by model.boothId");
			query.setParameterList("boothIds", boothIds);
			return query.list(); 
		}
			
		public List<Object[]> getBoothsForUrbanConstituencyes(List<Long> wardIds,Long constituencyId,Long publicationId)
		{
			Query query = getSession().createQuery("select model.boothId,model.partNo,model.totalVoters,model.localBodyWard.constituencyId from Booth model " +
					" where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationId " +
					" and model.localBodyWard.constituencyId in (:wardIds) group by model.boothId");
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationId", publicationId);
			query.setParameterList("wardIds", wardIds);
			return query.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getBoothsAndWardsInUrbanConstituency(Long constituencyId,Long publicationId)
		{
			
			Object[] parms = {constituencyId,publicationId};
			return getHibernateTemplate().find("select model.boothId,model.partNo," +
					" model.localBodyWard.constituencyId,model.localBodyWard.name from Booth model " +
					" where model.constituency.constituencyId = ? and model.publicationDate.publicationDateId = ? order by model.localBodyWard.constituencyId asc ",parms);
		}
		
		public Long getTotalVotesForSelectedBooth(Long boothId)
		{
			Query query = getSession().createQuery(" select model.totalVoters from Booth model where model.boothId =:boothId ");
			query.setParameter("boothId", boothId);
			return (Long) query.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Long> getBoothIdsByConstituencyIdAndPublicationId(Long constituencyId,Long publicationDateId)
		{
			Query query = getSession().createQuery(" select model.boothId from Booth model where model.constituency.constituencyId =:constituencyId " +
					" and model.publicationDate.publicationDateId =:publicationDateId ");
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
		}
	
			
			public List<Object[]> getDescriptionForMandalLevel(Long tehsilId,Long publicationId){
		        Query query = getSession().createQuery("select pbp.description," +
		        		"pbp.booth.panchayat.panchayatName,ph.panchayat.panchayatName,pbp.hamlet.hamletName," +
		        		"pbp.booth.partNo,pbp.booth.panchayat.panchayatId,pbp.booth.boothId,pbp.booth.panchayat.panchayatId from PartialBoothPanchayat pbp,PanchayatHamlet ph where " +
		        		" pbp.booth.publicationDate.publicationDateId = :publicationId and  " +
		        		" pbp.booth.tehsil.tehsilId = :tehsilId and " +
		        		//"pbp.panchayat.panchayatId = pbp.booth.panchayat.panchayatId and " +
		        		" pbp.hamlet.hamletId = ph.hamlet.hamletId ");
		        
		        query.setParameter("publicationId", publicationId);
		        query.setParameter("tehsilId", tehsilId);
		    	return query.list();
		    }
		   
		    public List<Object[]> getDescriptionForPanchayatLevel(Long panchayatId,Long publicationId){
		        Query query = getSession().createQuery("select pbp.description," +
		        		"pbp.booth.panchayat.panchayatName,ph.panchayat.panchayatName,pbp.hamlet.hamletName," +
		        		"pbp.booth.partNo,pbp.booth.panchayat.panchayatId,pbp.booth.boothId,pbp.booth.panchayat.panchayatId from PartialBoothPanchayat pbp,PanchayatHamlet ph where " +
		        		" pbp.booth.publicationDate.publicationDateId = :publicationId and " +
		        		" pbp.panchayat.panchayatId = :panchayatId and " +
		        		" pbp.hamlet.hamletId = ph.hamlet.hamletId ");
		        query.setParameter("publicationId", publicationId);
		        query.setParameter("panchayatId", panchayatId);
		        return query.list();
		    }
		    
		    public List<Object[]> getDescriptionForBoothLevel(Long panchayatId,Long boothId){
		        Query query = getSession().createQuery("select pbp.description," +
		        		"pbp.booth.panchayat.panchayatName,ph.panchayat.panchayatName,ph.hamlet.hamletName," +
		        		"pbp.booth.partNo,pbp.booth.boothId,pbp.booth.boothId,pbp.booth.panchayat.panchayatId from PartialBoothPanchayat pbp,PanchayatHamlet ph where " +
		        		" pbp.booth.boothId = :boothId  and pbp.hamlet.hamletId = ph.hamlet.hamletId");
		        query.setParameter("boothId", boothId);
		        //query.setParameter("panchayatId", panchayatId);
		        return query.list();
		    }
		public List<Object[]> getPartialPanchayats(Long publicationId,Long constituencyId){
			Query query = getSession().createQuery(" select model.panchayat.panchayatId,model.boothId from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationId and model.isPartial = 'Y' and model.panchayat is not null");
			
			query.setParameter("publicationId", publicationId);
			query.setParameter("constituencyId", constituencyId);
			
			return query.list();
		}
		
		public List<Long> getPanchayatsMandalId(Long mandalId,Long constituencyId,Long publicationDateId)
		{
			Query query = getSession().createQuery(" select distinct model.panchayat.panchayatId " +
					" from Booth model where " +
					" model.tehsil.tehsilId =:tehsilId and " +
					" model.constituency.constituencyId =:constituencyId " +
					" and model.publicationDate.publicationDateId =:publicationDateId ");
			
			query.setParameter("tehsilId", mandalId);
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
		}
		
		public List<Object[]> getPartialBoothsDetailsOfPanchayat(List<Long> boothsList,Long publicationId){
			
	        Query query = getSession().createQuery("select pbp.description,ph.panchayat.panchayatName," +
	        		"pbp.booth.panchayat.panchayatName,pbp.hamlet.hamletName,pbp.booth.partNo," +
	        		"pbp.booth.panchayat.panchayatId,pbp.booth.boothId,pbp.booth.panchayat.panchayatId from " +
	        		"PartialBoothPanchayat pbp,PanchayatHamlet ph  where " +
		        	" pbp.booth.publicationDate.publicationDateId = :publicationId and " +
		        	" pbp.booth.boothId in(:boothsList) and pbp.hamlet.hamletId = ph.hamlet.hamletId ");
	        
	        query.setParameter("publicationId", publicationId);
	        query.setParameterList("boothsList", boothsList);
	        return query.list();
		}
		
	public List<Long> getPartialBoothsForPanchayatLevel(Long panchayatId,Long publicationId){
			
	        Query query = getSession().createQuery("select pbp.booth.boothId " +
	        		" from PartialBoothPanchayat pbp,Booth b where " +
	        		" pbp.booth.publicationDate.publicationDateId = :publicationId and " +
	        		" pbp.panchayat.panchayatId = :panchayatId and pbp.booth.isPartial like '%Y%' and pbp.booth.boothId = b.boothId ");
	        query.setParameter("publicationId", publicationId);
	        query.setParameter("panchayatId", panchayatId);
	        return query.list();
		}
	
	public List<Object[]> getDescriptionForHamletLevel(Long publicationId,Long hamletId){
		   Query query = getSession().createQuery("select pbp.description," +
	        		"pbp.booth.panchayat.panchayatName,ph.panchayat.panchayatName,ph.hamlet.hamletName," +
	        		"pbp.booth.partNo,pbp.hamlet.hamletId,pbp.booth.boothId,pbp.booth.panchayat.panchayatId from PartialBoothPanchayat pbp,PanchayatHamlet ph where " +
	        		" pbp.booth.publicationDate.publicationDateId = :publicationId and pbp.hamlet.hamletId = :hamletId  " +
	        		" and pbp.hamlet.hamletId = ph.hamlet.hamletId");
	        query.setParameter("hamletId", hamletId);
	        query.setParameter("publicationId", publicationId);
	        return query.list();

	}
	
	public List<Object[]> getPanchayatByBoothId(Long boothId,Long publicationId){
		 Query query = getSession().createQuery(" select model.boothId,model.panchayat.panchayatId from Booth model where model.boothId = (:boothId) and " +
		 		" model.publicationDate.publicationDateId = :publicationId");
	        query.setParameter("boothId", boothId);
	        query.setParameter("publicationId", publicationId);
	        return query.list();
		
		
	}
	
	public Long findVotersCountByPublicationIdForPartialPanchayat(Long userId,Long panchayatId,Long publicationDateId)
	{
	StringBuilder str = new StringBuilder();
	str.append("select count(bpv.voter.voterId) from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd where ph.panchayat.panchayatId = :panchayatId and ph.hamlet.hamletId = uvd.hamlet.hamletId " +
	" and uvd.user.userId = :userId and uvd.voter.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = :publicationDateId ");

	Query query = getSession().createQuery(str.toString()) ;
	query.setParameter("userId", userId);
	query.setParameter("publicationDateId", publicationDateId);
	query.setParameter("panchayatId", panchayatId);

	return (Long)query.uniqueResult();
	}
	
	public List<Long> getTehsilsForAfterDelimation(Long constituencyId,Long year)
	{
		Query query = getSession().createQuery("select distinct model.tehsil.tehsilId from Booth model " +
				" where model.constituency.constituencyId = :constituencyId " +
				" and model.year = :year ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("year", year);
		return query.list();
	}
	
	
	public List<Long> getPanchayatsForAfterDelimation(Long constituencyId,Long year)
	{
		Query query = getSession().createQuery("select distinct model.panchayat.panchayatId from Booth model " +
				" where model.constituency.constituencyId = :constituencyId " +
				" and model.year = :year ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("year", year);
		return query.list();
	}
	public List<Long> getBoothsBeforDelimation(Long year , List<Long> tehsilIds)
	{
		Query query = getSession().createQuery("select distinct model.boothId from Booth model " +
				" where model.tehsil.tehsilId in (:tehsilIds) " +
				" and model.year = :year ");
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("year", year);
		return query.list();
	}	
	
	public List<Long> getBoothsBeforDelimationByPanchayat(Long year , List<Long> panchayatis)
	{
		Query query = getSession().createQuery("select distinct model.boothId from Booth model " +
				" where model.panchayat.panchayatId in (:panchayatis) " +
				" and model.year = :year ");
		query.setParameterList("panchayatis", panchayatis);
		query.setParameter("year", year);
		return query.list();
	}	
	
		public List<Long> getBoothsInAPanchayat1(Long panchayatId,Long publicationDateId){
	    	Object[] params={panchayatId,publicationDateId};
	    	return getHibernateTemplate().find("select model.boothId from Booth model where model.panchayat.panchayatId = ? and model.publicationDate.publicationDateId = ? ",params);
	    }
		
	public	List<Object[]> getboothNamesByBoothIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select model.boothId,model.partNo " +
				" from Booth model where model.boothId in (:boothIds)");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public String getMuncipaltyName(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.localBody.name " +
				" from Booth model where model.constituency.constituencyId = :constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return (String)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTehsilsForConstituency(Long constituencyId,Long publicationDateId)
	{
		Object[] parms = {constituencyId,publicationDateId};
		return getHibernateTemplate().find("select distinct model.tehsil.tehsilId,model.tehsil.tehsilName from Booth model where model.constituency.constituencyId = ? and model.publicationDate.publicationDateId = ? ",parms);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsForConstituency(List<Long> mandalIds,Long publicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.panchayat.panchayatId , model.panchayat.panchayatName , model.tehsil.tehsilId from Booth model where model.tehsil.tehsilId in (:mandalIds) and model.publicationDate.publicationDateId = :publicationDateId");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("mandalIds", mandalIds);
		return query.list();
	}
	
    public List<Object[]> getPanchayatMandalDetails(Long constituencyId,Long publicationId){
	Query query = getSession().createQuery("select distinct model.panchayat.panchayatId , model.panchayat.panchayatName , model.tehsil.tehsilId,model.tehsil.tehsilName from Booth model where model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId order by model.tehsil.tehsilName ");

	query.setParameter("publicationId", publicationId);
	query.setParameter("constituencyId", constituencyId);

	return query.list();
	}

	public List<Object[]> getMunicDetails(Long constituencyId,Long publicationId){
	Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId ,model.localBody.name ,model.localBody.electionType.electionType from Booth model where model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId");

	query.setParameter("publicationId", publicationId);
	query.setParameter("constituencyId", constituencyId);

	return query.list();
	}


	public List<Object[]> getWardDetailsByLocalEleBodyId(Long localEleBodyId,Long publicationDateId,Long constituencyId)
	{
		Query query = getSession().createQuery(" select distinct model.localBodyWard.constituencyId,model.localBodyWard.name,model1.wardName,model.localBody.localElectionBodyId,model.boothId from" +
				" Booth model,LocalElectionBodyWard model1 where model.constituency.constituencyId =:constituencyId " +
				" and model.publicationDate.publicationDateId =:publicationDateId and model.localBody.localElectionBodyId =:localEleBodyId and model.localBodyWard.constituencyId = model1.constituency.constituencyId ");
		
		query.setParameter("localEleBodyId", localEleBodyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List getConstituneycId(Long boothId)
	{
		return getHibernateTemplate().find("select model.constituency.constituencyId from Booth model where model.boothId = ? ",boothId);
	}
	
	public List<Booth> getModelByBoothId(Long boothId){
		return getHibernateTemplate().find(" from Booth model where model.boothId = ? ",boothId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Booth> getBoothDataForAPublication(Long publicationDateId)
	{
		Query query = getSession().createQuery("Select model from Booth model where model.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public Booth getBoothByConstituencyPublicationPartNo(Long constituencyId,Long publicationDateId,String partNo)
	{
		Query query = getSession().createQuery("Select model from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId and model.partNo = :partNo ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("partNo",partNo);
		
		return (Booth) query.uniqueResult();
	}
	
	public Long getBoothIdByConstituencyPublicationPartNo(Long constituencyId,Long publicationDateId,String partNo)
	{
		Query query = getSession().createQuery("Select model.boothId from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId and model.partNo = :partNo ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("partNo",partNo);
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getPanchayatAndLebIds(Long constituencyId,Long publicationDateId){
		Query query = getSession().createQuery("Select model.panchayat.panchayatId,model.localBody.localElectionBodyId from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId  ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	
	public List<Object[]> getPanchayatsAndLebIds(List<Long> constituencyIdsList,Long publicationDateId){
		Query query = getSession().createQuery("Select model.panchayat.panchayatId,model.localBody.localElectionBodyId from Booth model where model.constituency.constituencyId in (:constituencyIdsList) and model.publicationDate.publicationDateId = :publicationDateId  ");
		query.setParameter("constituencyIdsList",constituencyIdsList);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
	}
	
	public List<Object[]> getDistrictsPanchayatAndLebIds(Long distictId,Long publicationDateId){
		Query query = getSession().createQuery("Select model.panchayat.panchayatId,model.localBody.localElectionBodyId from Booth model where model.constituency.district.distictId = :distictId and model.publicationDate.publicationDateId = :publicationDateId  ");
		query.setParameter("distictId",distictId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPincodesForBoothIdsList(List<Long> boothIdsList)
	{
		Query query = getSession().createQuery("Select model.boothId,model.pincode from Booth model where model.pincode is not null and model.boothId in(:boothIdsList)");
		query.setParameterList("boothIdsList",boothIdsList);
		return query.list();
	}
	
	public List<Long> getBoothIdByConstituencyPublication(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("Select model.boothId from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		//query.setParameter("partNo",partNo);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsByConstituencyAndPublication(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("SELECT DISTINCT model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId order by model.panchayat.panchayatName");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	public List<Object[]> getBoothOfAConstituencyByPublication(Long constituencyId, Long publicationDateId,Long tehsilId,Long localElecBodyId)
	{
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select model.boothId,model.partNo from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId and model.refBooth is null ");
		
		if(tehsilId != null && tehsilId.longValue() > 0){
			 queryStr.append(" and model.tehsil.tehsilId =:tehsilId");
		}
		if(localElecBodyId != null && localElecBodyId.longValue() > 0){
			 queryStr.append(" and model.localBody.localElectionBodyId =:localElecBodyId");
		}
		queryStr.append(" order by  cast(model.partNo , int)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		if(tehsilId != null && tehsilId.longValue() > 0){
			query.setParameter("tehsilId",tehsilId);
		}
		if(localElecBodyId != null && localElecBodyId.longValue() > 0){
			query.setParameter("localElecBodyId",localElecBodyId);
		}
		return query.list();
	}
	
	public List<Object[]> getTotalaVotesByBooths(List<Long> boothIds)
	{
		Query queryObj = getSession().createQuery("select sum(model.maleVoters),sum(model.femaleVoters),sum(model.totalVoters),count(*) from Booth model where model.boothId in (:boothIds) ");
		queryObj.setParameterList("boothIds", boothIds);
		return queryObj.list();
	}
	
	public List<Object[]> getAllTheBoothsDetailsByConstituencyId(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select B.boothId,B.partNo from Booth B " +
				"where B.publicationDate.publicationDateId = :publicationDateId and B.constituency.constituencyId = :constituencyId");
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Object[]> getAllTheBoothsDetailsByConstituencyIdForCTP(Long constituencyId)
	{
		Query query = getSession().createQuery("select B.boothId,B.partNo from Booth B " +
				"where B.isCtpUsed = 'Y' and B.constituency.constituencyId = :constituencyId order by B.boothId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	
	
	public List<Object[]> getTotalaVotesDetailsByBoothIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select B.totalVoters , B.boothId from Booth B where B.boothId in(:boothIds)");
		
		query.setParameterList("boothIds", boothIds);
		return query.list();
		
	}
	
	public Long getTotalVoter(Long boothId)
	{
		Query query = getSession().createQuery("select model.totalVoters from Booth model where model.boothId = :boothId and model.publicationDate.publicationDateId =:publicationDateId ");
		query.setParameter("boothId", boothId);
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getBoothDetailsByBoothIds(Set<Long> boothIds)
	{
		Query query = getSession().createQuery("select B.boothId,B.constituency.constituencyId,B.partNo from Booth B where B.boothId in(:boothIds)");
		
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseBoothCount()
	{
		Query query = getSession().createQuery("select count(B.boothId),B.constituency.constituencyId from Booth B where " +
				"B.publicationDate.publicationDateId = :publicationDateId group by B.constituency.constituencyId ");
		
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		
		return query.list();
		
	}
	
	 public List<Object[]> getPanchayatBoothDetails(Long constituencyId,Long publicationId){
			Query query = getSession().createQuery("select distinct model.panchayat.panchayatId , model.panchayat.panchayatName , model.boothId from Booth model where model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId");

			query.setParameter("publicationId", publicationId);
			query.setParameter("constituencyId", constituencyId);

			return query.list();
			}
	 
	 @SuppressWarnings("unchecked")
		public List<Object[]> getPanchayatBooths(List<Long> boothIds) {
			Query queryObject = getSession().createQuery("select distinct model.panchayat.panchayatId , model.panchayat.panchayatName , model.boothId from Booth model where model.boothId in (:boothIds)");
			queryObject.setParameterList("boothIds", boothIds);
			return queryObject.list();
		}
		
		 @SuppressWarnings("unchecked")
			public List<Object[]> getBoothsByPanhcayats(List<Long> panchayatIds,Long publicationId) {
				Query queryObject = getSession().createQuery("select distinct model.panchayat.panchayatId , model.panchayat.panchayatName , model.boothId from Booth model where  model.panchayat.panchayatId in (:panchayatIds) and model.publicationDate.publicationDateId = :publicationId");
				queryObject.setParameterList("panchayatIds", panchayatIds);
				queryObject.setParameter("publicationId", publicationId);
				return queryObject.list();
			}
			
			
	public List<Object[]> getTotalBoothsCountByConstituencyIds(List<Long> constituencyIds,Long publicationDateId)
	{
		Query query = getSession().createQuery("select count(B.boothId),B.constituency.constituencyId,B.constituency.name from Booth B " +
				"where B.constituency.constituencyId in(:constituencyIds) and B.publicationDate.publicationDateId = :publicationDateId group by B.constituency.constituencyId");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	
	public List<Object[]> getTotalBoothsCountByConstituencyIdsForCTP(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select count(B.boothId),B.constituency.constituencyId,B.constituency.name from Booth B " +
				" where B.constituency.constituencyId in(:constituencyIds) and B.isCtpUsed = 'Y' group by B.constituency.constituencyId ");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
		
	}
	
	public List<Object[]> getTotalBoothsCountForPanchayatisByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(B.boothId),B.panchayat.panchayatId,B.panchayat.panchayatName from Booth B " +
				"where B.constituency.constituencyId = :constituencyId and B.publicationDate.publicationDateId = :publicationDateId group by B.panchayat.panchayatId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		
		return query.list();
		
	}

	public List<Object[]> getBoothCountInfoByConstiIds(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select count(B.boothId),B.constituency.constituencyId,B.constituency.name from Booth B where " +
				"B.publicationDate.publicationDateId = :publicationDateId and B.constituency.constituencyId in (:constituencyIds) group by B.constituency.constituencyId ");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		
		return query.list();
		
	}
	
	public List<Object[]> getmandalDetailsByBoothIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select B.boothId,B.tehsil.tehsilId,B.tehsil.tehsilName, B.panchayat.panchayatName  from Booth B where B.boothId in(:boothIds)");
		
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
		
		
	}
	
	public List<Object[]> getMuncipalityDetyailsByBoothIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery("select B.boothId, B.localBody.localElectionBodyId,B.localBody.name from Booth B where B.boothId in(:boothIds)");
		
		query.setParameterList("boothIds", boothIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getTotalBoothsDetailsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select B.boothId,B.partNo from Booth B where B.constituency.constituencyId = :constituencyId and " +
				"B.publicationDate.publicationDateId = :publicationDateId");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		
		return query.list();
	}
		
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByConstituencyIdsAndPublicationId(List<Long> constituencyIds,Long publicationDateId)
	{
		Query query = getSession().createQuery(" select distinct model.boothId from Booth model where model.constituency.constituencyId in (:constituencyIds) " +
				" and model.publicationDate.publicationDateId =:publicationDateId ");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getBoothIdsByConstituencyIdsForCTP(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery(" select distinct model.boothId from Booth model where model.constituency.constituencyId in (:constituencyIds) " +
				" and model.isCtpUsed = 'Y'");
		
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}

	public List<Object[]> getAllDetails()
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name,model.tehsil.tehsilId,model.tehsil.tehsilName,model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model where model.constituency.state.stateId = 1 and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.refBooth is null and model.panchayat is not null  and model.constituency.areaType='URBAN' order by model.constituency.constituencyId");
		
		return query.list();
	}
	
	public List<Object[]> getBoothsInAConstituency(Long constituencyId,Long publicationDateId){
		Query query = getSession().createQuery(" select distinct model.boothId,concat('Booth-',model.partNo) from Booth model where model.constituency.constituencyId =:constituencyId " +
				" and model.publicationDate.publicationDateId =:publicationDateId and model.refBooth is null ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	public List<Object[]> getBoothNamesByIds(List<Long> boothIds)
	{
		Query query = getSession().createQuery(" select model.boothId,concat('Booth-',model.partNo) from Booth model where model.boothId in(:boothIds) ");
		query.setParameterList("boothIds", boothIds);
		return query.list();
		
	}
	
	public List<Object[]> getBoothNames(List<Long> boothIds)
	{
		Query query = getSession().createQuery(" select model.boothId,model.partNo,model.location,model.villagesCovered,panch.panchayatName from Booth model left join model.panchayat panch where model.boothId in(:boothIds) ");
		query.setParameterList("boothIds", boothIds);
		return query.list();
		
	}
	
	public List<Object[]> getAllBoothsInUrban(Long constituencyId,Long publicationId)
	{
		Query query = getSession().createQuery("select model.boothId,model.partNo, model.villagesCovered from Booth model " +
				" where model.constituency.constituencyId = :constituencyId and " +
				"  model.publicationDate.publicationDateId = :publicationId order by model.boothId ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	/**
	 * This DAO is used for getting all booths in a muncipality's
	 * @param Long localBodyId
	 * @param Long publicationId
	 * @return List<Object[]>
	 */
	public List<Object[]> getAllBoothsInMuncipalities(List<Long> localBodyIds,Long publicationId){
		Query query = getSession().createQuery("select model.boothId,model.partNo, model.villagesCovered from Booth model " +
				" where model.localBody.localElectionBodyId in( :localBodyIds) and " +
				"  model.publicationDate.publicationDateId = :publicationId" +
				" order by model.localBody.localElectionBodyId , model.partNo");
		query.setParameterList("localBodyIds", localBodyIds);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public Long getConstituencyIdByLocationIdAndType(Long locationId,String locationType)
	{
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select distinct model.constituency.constituencyId from Booth model where model.publicationDate.publicationDateId = :publicationId ");
			
			;
			if(locationType.equals("panchayat"))
			{
				queryStr.append(" and model.panchayat.panchayatId = :locationId ");
			}
			else if(locationType.equals("booth"))
			{
				queryStr.append("  and model.boothId = :locationId ");
			}
			else if(locationType.equals("mandal"))
			{
				queryStr.append(" and model.tehsil.tehsilId  = :locationId ");
			}else if(locationType.equals("localBody"))
			{
				queryStr.append(" and model.localBody.localElectionBodyId  = :locationId ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("publicationId", IConstants.VOTER_DATA_PUBLICATION_ID);
			query.setParameter("locationId", locationId);
			
			return (Long) query.uniqueResult();
		
	}
	
	public List<Object[]> getAllBoothsInPanchayat(List<Long> panchayatIds,Long publicationId){
		Query query = getSession().createQuery("select model.boothId,model.partNo, model.villagesCovered from Booth model " +
				" where model.panchayat.panchayatId in(:panchayatIds) and " +
				"  model.publicationDate.publicationDateId = :publicationId" +
				" order by model.partNo");
		query.setParameterList("panchayatIds", panchayatIds);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Object[]> getAllLocalBodies(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId,concat(model.localBody.name,' ',model.localBody.electionType.electionType) " +
				" from Booth model where model.publicationDate.publicationDateId =:publicationId and model.constituency.constituencyId =:constituencyId order by model.localBody.name");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		
		return query.list();
	}
	
	public List<Long> getAllBoothIdsByConsti(Long constituencyId,Long publicationId)
	{
		Query query = getSession().createQuery("select model.boothId from Booth model " +
				" where model.constituency.constituencyId = :constituencyId and " +
				"  model.publicationDate.publicationDateId = :publicationId");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Long> getAllBoothIdsInPanchayat(List<Long> panchayatIds,Long publicationId){
		Query query = getSession().createQuery("select model.boothId from Booth model " +
				" where model.panchayat.panchayatId in(:panchayatIds) and " +
				"  model.publicationDate.publicationDateId = :publicationId");
		query.setParameterList("panchayatIds", panchayatIds);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Long> getAllBoothIdsInLocalBodies(List<Long> localBodyIds,Long publicationId){
		Query query = getSession().createQuery("select model.boothId from Booth model " +
				" where model.localBody.localElectionBodyId in( :localBodyIds) and " +
				"  model.publicationDate.publicationDateId = :publicationId");
		query.setParameterList("localBodyIds", localBodyIds);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Long> getAllBoothsInAMandal(Long tehsilId,Long publicationId,Long constituencyId){
		Query query = getSession().createQuery("select model.boothId from Booth model " +
				" where model.tehsil.tehsilId = :tehsilId and model.localBody.localElectionBodyId is null and " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId ");
		query.setParameter("tehsilId", tehsilId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	public List<Long> getAllPanchayatsInAMandal(Long tehsilId,Long publicationId,Long constituencyId){
		Query query = getSession().createQuery("select distinct model.panchayat.panchayatId from Booth model " +
				" where model.tehsil.tehsilId = :tehsilId and model.localBody.localElectionBodyId is null and " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId and model.panchayat.panchayatId is not null ");
		query.setParameter("tehsilId", tehsilId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Long> getAllTehsilsInAConstituency(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.tehsil.tehsilId from Booth model where " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId and model.tehsil.tehsilId is not null ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	public List<Long> getLBSInAConstituency(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId from Booth model where " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId and  model.localBody.localElectionBodyId is not null");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	public List<Long> getAllPanchayatsInAConstituency(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.panchayat.panchayatId from Booth model where " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId and model.panchayat.panchayatId is not null ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Long> getAllBoothsInAConstituency(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.boothId from Booth model where " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	
	public List<Object[]> getAllDetails1()
	{
	Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name,model.localBody.localElectionBodyId, model.localBody.name, model.boothId, model.partNo from Booth model where model.constituency.state.stateId = 1 and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.refBooth is null and  model.localBody is not null and model.constituency.areaType='URBAN' order by model.constituency.constituencyId");
	return query.list();
	}
	
	
	public List<Long> getLocalbodiesByConstituencyIds(List<Long> constiIds,Long publicationDateId){
		
		Query query=getSession().createQuery("select distinct model.localBody.localElectionBodyId from Booth model where model.constituency.constituencyId in (:constiIds) and model.publicationDate.publicationDateId = :publicationDateId " +
				" and model.localBody.localElectionBodyId is not null" );
		query.setParameterList("constiIds", constiIds);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	public List<Long> getTehsilsByConstituencyIds(List<Long> constiIds,Long publicationDateId){
		
		Query query=getSession().createQuery("select distinct model.tehsil.tehsilId from Booth model where model.constituency.constituencyId in (:constiIds) and model.publicationDate.publicationDateId = :publicationDateId " +
				" and model.localBody is null" );
		query.setParameterList("constiIds", constiIds);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	
	public List<Long> getPanchayatsByConstituencyIds(List<Long> constituencyIds,Long publicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.panchayat.panchayatId from Booth model where model.constituency.constituencyId in (:constituencyIds) and model.publicationDate.publicationDateId = :publicationDateId order by model.panchayat.panchayatName");
		query.setParameterList("constituencyIds",constituencyIds);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	
	public List<Object[]> getBoothsInAMuncipality(List<Long> boothIds){
		Query query = getSession().createQuery("select model.boothId,model.villagesCovered,model.localBody.name from Booth model where model.boothId in (:boothIds) and model.panchayat is null " +
				" group by model.boothId ");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public List<Object[]> getBooths(List<Long> boothIds){
		Query query = getSession().createQuery("select model.boothId, model.panchayat.panchayatName,model.tehsil.tehsilName from Booth model where model.boothId in (:boothIds) and model.panchayat is not null" +
				" group by model.boothId");
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public List<Object[]> getTotalVotersInBooths(Long constituencyId,Long publicationDateId){
		//0 id,1name,2total,3districtId,4tehsilName,5 localBody,6localBody type
		Query query = getSession().createQuery("select model.boothId, model.partNo,model.totalVoters,model.constituency.district.districtId,th.tehsilName,lb.name,lb.electionType.electionType from Booth model Left Join model.tehsil th Left Join model.localBody lb" +
				" where model.constituency.constituencyId =:constituencyId and model.publicationDate.publicationDateId =:publicationDateId and model.totalVoters is not null");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	public List<Long> getAllBoothIdsInALocalBody(Long localBodyId,Long publicationId,Long constituencyId){
		Query query = getSession().createQuery("select model.boothId from Booth model " +
				" where model.localBody.localElectionBodyId = :localBodyId and " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId");
		query.setParameter("localBodyId", localBodyId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}

	public List<String> getUnregisteredBoothsByBooths(List<Long> boothIdsList,Long constituencyId, Long publicationDateId,Long tehsilId,String tehsilType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.partNo from Booth model where model.constituency.constituencyId = :constituencyId  and ");
		queryStr.append(" model.publicationDate.publicationDateId = :publicationDateId and model.boothId not in (:boothIdsList) ");
		
		if(tehsilType.equalsIgnoreCase("notRural"))
		{
			queryStr.append("and model.localBody.localElectionBodyId =:tehsilId ");
		}
		else
		{
			queryStr.append(" and model.tehsil.tehsilId = :tehsilId ");
		}
		queryStr.append(" order by model.boothId");
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameterList("boothIdsList",boothIdsList);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("tehsilId", tehsilId);
		
		return query.list();
	}
	
	public List<Object[]> findBoothInfoByConstituencyAndPublication(Long constituencyId, Long publicationDateId){
		
		Query query = getSession().createQuery("select distinct model.boothId, model.partNo, model.tehsil.tehsilId, model.tehsil.tehsilName,  model.localBody.localElectionBodyId  " +
				" from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = :publicationDateId order by model.partNo");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		
		return query.list();
		
	}
	
	public List<Object[]> getAllTehsilsDetailsInAConstituency(Long constituencyId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.tehsil.tehsilId,model.tehsil.tehsilName from Booth model where " +
				"  model.publicationDate.publicationDateId = :publicationId and model.constituency.constituencyId =:constituencyId and model.tehsil.tehsilId is not null and model.localBody.localElectionBodyId is null ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	
	
	public List<Object[]> getConstituenciesNameByType(List<Long> ids,String type){
		
		
		StringBuilder str = new StringBuilder();
		
		 if(type.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			 str.append("select distinct model.constituency.constituencyId,model.constituency.district.districtName ");
		 }
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL)){
			 str.append("select distinct model.tehsil.tehsilId,model.constituency.name ");
		 }
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY)){
				 str.append("select distinct model.localBody.localElectionBodyId,model.constituency.name");
		}
		
		str.append(" from Booth model where " );
			
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append("  model.constituency.constituencyId  in (:ids) ");
		}
		else if(type.equalsIgnoreCase(IConstants.TEHSIL) ){
			 str.append(" model.tehsil.tehsilId in (:ids)");
		}
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY)){
			 str.append("  model.localBody.localElectionBodyId in (:ids) ");
		}
		
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("ids", ids);
		return query.list();
		
		
				
	}
	
	public List<Object[]> getAllBoothsOfAConstituency(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select model.boothId,model.partNo,model.location from Booth model where model.constituency.constituencyId = :constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public List<Long> getLocationIds(Long id,String type,Long publicationId){
		
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.TEHSIL)){
			 str.append("select distinct model.tehsil.tehsilId");
		 }
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY)){
				 str.append("select distinct model.localBody.localElectionBodyId");
		}
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
			 str.append("select distinct model.panchayat.panchayatId");
	}
		else if(type.equalsIgnoreCase(IConstants.WARD)){
			 str.append("select distinct model.localBodyWard.constituencyId");
	}
		
		str.append(" from Booth model where model.constituency.constituencyId = :id and model.publicationDate.publicationDateId = :publicationId" );
		if(type.equalsIgnoreCase(IConstants.TEHSIL))
			 str.append(" and model.localBody.localElectionBodyId is null");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("id", id);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Object[]> getAllPublicationsForConstituencies(List<Long> constiIds){
		Query query = getSession().createQuery("select distinct model.publicationDate.publicationDateId,model.publicationDate.name from Booth model where " +
				" model.constituency.constituencyId in(:constiIds) and model.publicationDate.publicationDateId > 6 and model.publicationDate.publicationDateId < 12 order by model.publicationDate.date desc ");
		query.setParameterList("constiIds", constiIds);
		return query.list();
	}
	
	public List<Object[]> getAllBoothsByTehsilOrLclBdyId(Long locationId,Long publicationId,String type){
		StringBuilder str = new StringBuilder(); 
		
		str.append("select model.boothId,model.partNo from Booth model where model.publicationDate.publicationDateId =:publicationId ");
		if(type.equalsIgnoreCase("LocalBody")){
			str.append(" and model.localBody.localElectionBodyId = :locationId");
		}else{
			str.append(" and model.tehsil.tehsilId = :locationId and model.localBody.localElectionBodyId is null ");
		}
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationId", locationId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	public List<Object[]> findTehsilsByDistrictIdAndPublicationDateId(Long districtId, Long publicationDateId) {
		
		String queryString = "select distinct model.tehsil.tehsilId , model.tehsil.tehsilName from Booth model where " +
				"model.publicationDate.publicationDateId = :publicationDateId and model.constituency.district.districtId = :districtId " +
				"and model.localBody.localElectionBodyId is null order by model.tehsil.tehsilName";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("districtId", districtId);
		
		return query.list();
	}
	
	public List<Object[]> getTehsilsIdsAndLocalBodyIdsListByConstituencyIds(List<Long> constituencyIdsList, Long publicationDateId) {
		
		String queryString = "select distinct model.tehsil.tehsilId , model.localBody.localElectionBodyId from Booth model where " +
				"model.publicationDate.publicationDateId = :publicationDateId and model.constituency.constituencyId in (:constituencyIdsList) " +
				"and model.localBody.localElectionBodyId is null order by model.tehsil.tehsilName";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyIdsList", constituencyIdsList);
		
		return query.list();
	}
	
	public List<Object[]> getTehsilsIdsAndLocalBodyIdsListByDistricts(List<Long> districtIdsList, Long publicationDateId) {
			
			String queryString = "select distinct model.tehsil.tehsilId , model.localBody.localElectionBodyId from Booth model where " +
					"model.publicationDate.publicationDateId = :publicationDateId and model.constituency.district.districtId in (:districtIdsList) " +
					"and model.localBody.localElectionBodyId is null order by model.tehsil.tehsilName";
			
			Query query = getSession().createQuery(queryString);
			
			query.setParameter("publicationDateId", publicationDateId);
			query.setParameter("districtIdsList", districtIdsList);
			
			return query.list();
		}

	public List<Object[]> getAllLocalBodiesByDistrictIdAndPublicationDateId(Long districtId,Long publicationId){
		Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId,concat(model.localBody.name,' ',model.localBody.electionType.electionType) " +
				" from Booth model where model.publicationDate.publicationDateId =:publicationId and model.constituency.district.districtId =:districtId order by model.localBody.name");
		
		query.setParameter("districtId", districtId);
		query.setParameter("publicationId", publicationId);
		
		return query.list();
	}
	
	public List<Long> getBoothsDetailIds(String type,Long constituencyId,Set<Long> locationVal){
		StringBuilder query = new StringBuilder("select model.boothId from Booth model where model.constituency.constituencyId = :constituencyId and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.refBooth.boothId is null  ");
		if(type.equalsIgnoreCase("tehsil")){
			query.append(" and model.tehsil.tehsilId in (:locationVal) and model.localBody.localElectionBodyId is null ");
		}else if(type.equalsIgnoreCase("munic")){
			query.append("  and  model.localBody.localElectionBodyId  in (:locationVal) ");
		}else if(type.equalsIgnoreCase("panchayat")){
			query.append("  and  model.panchayat.panchayatId in (:locationVal) ");
		}else if(type.equalsIgnoreCase("ward")){
			query.append("  and  model.localBodyWard.constituencyId  in (:locationVal) ");
		}
		Query queryObj = getSession().createQuery(query.toString());
		queryObj.setParameter("constituencyId", constituencyId);
		if(type.length() > 0)
		queryObj.setParameterList("locationVal", locationVal);
		return queryObj.list();
	}
	
	public Long getBoothResults(List<Long> boothIds,Long electionId){
		Query queryObj = getSession().createQuery("select sum(model.validVotes) from BoothResult model where model.boothConstituencyElection.booth.boothId in(:boothIds) and  model.boothConstituencyElection.constituencyElection.election.electionId = :electionId  ");
		queryObj.setParameterList("boothIds", boothIds);
		queryObj.setParameter("electionId", electionId);
		return (Long)queryObj.uniqueResult();
	
	}
	
	public Long getCandidateBoothResults(List<Long> boothIds,List<Long> partyIds,Long electionId){
		Query queryObj = getSession().createQuery("select sum(model.votesEarned) from CandidateBoothResult model where model.boothConstituencyElection.booth.boothId in(:boothIds) and  model.boothConstituencyElection.constituencyElection.election.electionId = :electionId and  model.nomination.party.partyId in(:partyIds) ");
		queryObj.setParameterList("boothIds", boothIds);
		queryObj.setParameterList("partyIds", partyIds);
		queryObj.setParameter("electionId", electionId);
		return (Long)queryObj.uniqueResult();
	
	}
	
	public List<Object[]> getBoothsInfo(Long constituencyId,Set<String> boothIds){
		//0boothId, 1partNo, 2tehsilId, 3lclBodyId, 4panchayatId, 5wardId
		Query queryObj = getSession().createQuery("select model.boothId,model.partNo,model.tehsil.tehsilId,model.localBody.localElectionBodyId,model.panchayat.panchayatId,model.localBodyWard.constituencyId from Booth model where model.constituency.constituencyId = :constituencyId and model.partNo in(:boothIds) and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and model.refBooth.boothId is null ");
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameterList("boothIds", boothIds);
		return queryObj.list();
	}
	

	 public List<Object[]> getPanchaytsInfoByStateId(Long publicationId,Long stateId){
	    	Object[] params={publicationId,stateId};
	    	return getHibernateTemplate().find("select distinct model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model where model.publicationDate.publicationDateId = ? and model.constituency.state.stateId = ? order by model.panchayat.panchayatName ",params);
	    }
	 
	 public List<Long> getLocalElectionBody(Long tehsilId){
		 
		 Query query=getSession().createQuery("select distinct localBody.localElectionBodyId from Booth model" +
		 		" left join model.localBody localBody " +
		 		" where model.tehsil.tehsilId =:tehsilId and model.publicationDate.publicationDateId=:publicationDateId");
		 
		 query.setParameter("tehsilId", tehsilId);
		 query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		 return query.list();
	 }
	 /*select distinct local_election_body_id from booth where constituency_id = 244 and publication_date_id = 3 and local_election_body_id=155;*/
	 public Long getLocalElectionBodyDetails(Long localElectionBody,Long constituencyId,Long publicationDateId){
		 Query query=getSession().createQuery(" select distinct model.localBody.localElectionBodyId from Booth model " +
		 		" where model.localBody.localElectionBodyId =:localElectionBody " +
		 		" and model.constituency.constituencyId =:constituencyId" +
		 		" and model.publicationDate.publicationDateId =:publicationDateId ");
		 
		 if(localElectionBody !=null){
			 query.setParameter("localElectionBody", localElectionBody);
		 }
		 if(constituencyId !=null){
			 query.setParameter("constituencyId", constituencyId);
		 }
		 query.setParameter("publicationDateId", publicationDateId);
		 
		 return (Long)query.uniqueResult();
	 }
	 
	 public Long getLocalElectionBodyByConstituency(Long constituencyId){
		 
		 Query query = getSession().createQuery(" select distinct model.localBody.localElectionBodyId from Booth model where model.constituency.constituencyId = :constituencyId and model.localBody.localElectionBodyId is not null ");
		 
		 query.setParameter("constituencyId", constituencyId);
		 
		 return (Long) query.uniqueResult();
	 }
	 
	 public List<Long> getConstituencyIdByPanchayatId(Long panchayatId,Long publicationDateId){
		
		 Query query = getSession().createQuery(" select DISTINCT model.constituency.constituencyId from Booth model where model.panchayat.panchayatId = :panchayatId and model.publicationDate.publicationDateId = :publicationDateId ");
		 
		 query.setParameter("panchayatId", panchayatId);
		 query.setParameter("publicationDateId", publicationDateId);
		 
		 return query.list();
	}
	 
	 public List<Long> getConstituencyIdByTehsilId(Long tehsilId,Long publicationDateId){
			
		 Query query = getSession().createQuery(" select DISTINCT model.constituency.constituencyId from Booth model where model.tehsil.tehsilId = :tehsilId and model.publicationDate.publicationDateId = :publicationDateId ");
		 
		 query.setParameter("tehsilId", tehsilId);
		 query.setParameter("publicationDateId", publicationDateId);
		 
		 return query.list();
	}
	 
	 public List<Object[]> getBoothsByTehsilId(Long tehsilId,Long publicationDateId){
			
		 Query query = getSession().createQuery(" select DISTINCT model.boothId,model.partNo from Booth model where model.tehsil.tehsilId = :tehsilId and model.publicationDate.publicationDateId = :publicationDateId ");
		 
		 query.setParameter("tehsilId", tehsilId);
		 query.setParameter("publicationDateId", publicationDateId);
		 
		 return query.list();
	}
	 
	 public List<Object[]> getBoothsByLocalElectionBody(Long localElectionBody,Long publicationDateId){
			
		 Query query = getSession().createQuery(" select DISTINCT model.boothId,model.partNo from Booth model where model.localBody.localElectionBodyId = :localElectionBody and model.publicationDate.publicationDateId = :publicationDateId ");
		 
		 query.setParameter("localElectionBody", localElectionBody);
		 query.setParameter("publicationDateId", publicationDateId);
		 
		 return query.list();
	}
	 
	 public List<Long> getConstituencyIdByLocalElectionBody(Long localElectionBody,Long publicationDateId){
			
		 Query query = getSession().createQuery(" select DISTINCT model.constituency.constituencyId from Booth model where model.localBody.localElectionBodyId = :localElectionBody and model.publicationDate.publicationDateId = :publicationDateId ");
		 
		 query.setParameter("localElectionBody", localElectionBody);
		 query.setParameter("publicationDateId", publicationDateId);
		 
		 return query.list();
	}
	 
	 
	 public List<Object[]> getWardsByConstituencies(List<Long> constituencyIds,Long latestPublicationId){
			
			/*Query query = getSession().createQuery(" select distinct model.localBodyWard.constituencyId,model.localBodyWard.name, " +
							" concat(model.localBody.name,' ',model.localBody.electionType.electionType),model.constituency.name from Booth model " +
							" where model.constituency.constituencyId in (:constituencyIds) " +
							" and model.publicationDate.publicationDateId = :latestPublicationId group by model.constituency.constituencyId " +
							" order by model.localBodyWard.constituencyId asc ");*/
			Query query = getSession().createQuery(" select distinct model.localBodyWard.constituencyId,model.localBodyWard.name, " +
							" concat(model.localBody.name,' ',model.localBody.electionType.electionType),model.constituency.name from Booth model " +
							" where model.localBodyWard.constituencyId in (select distinct model2.localBodyWard.constituencyId from Booth model2 " +
							" where model2.publicationDate.publicationDateId = :latestPublicationId and model2.constituency.constituencyId in (:constituencyIds) " +
							" order by model2.constituency.name ) " +
							" and model.publicationDate.publicationDateId = :latestPublicationId group by model.constituency.constituencyId,model.localBodyWard.constituencyId " +
							" order by model.localBodyWard.constituencyId,model.constituency.name asc  ");
			query.setParameterList("constituencyIds", constituencyIds);
			query.setParameter("latestPublicationId", latestPublicationId);
			
			return query.list();
		}
	 
	 
	 public Long getBoothsCountByDivisionIds(List<Long> divisionIds,Long publicationId){
		Query query=getSession().createQuery(" select count(model.boothId) from Booth model where model.publicationDate.publicationDateId=:publicationId and " +
				" model.localBodyWard.constituencyId in (:divisionIds)");
		query.setParameterList("divisionIds", divisionIds);
		query.setParameter("publicationId", publicationId);
		
		return (Long)query.uniqueResult();
	}
	 
	 public List<Object[]>  getBoothWiseTotalVoters(List<Long> totalBooths,Long wardId){
		 
		 Query query = getSession().createQuery("select model.boothId,model.partNo,model.totalVoters ,count(distinct tc.tdpCadreId) " +
					" from  Booth model,TdpCadre tc " +
					" where model.boothId  in (:totalBooths)" +
					" and tc.userAddress.ward.constituencyId = :wardId  " +
					" group by model.boothId ");
			
			query.setParameterList("totalBooths", totalBooths);
			query.setParameter("wardId", wardId);
			
			return  query.list();
	 }
	 
	 public List<Long> getAllBoothIdsByWard(Long wardId,Long publicationId)
	{
			Query query = getSession().createQuery("select model.boothId from Booth model " +
					" where model.localBodyWard.constituencyId= :wardId and " +
					"  model.publicationDate.publicationDateId = :publicationId");
			query.setParameter("wardId", wardId);
			query.setParameter("publicationId", publicationId);
			return query.list();
	}
		 
	 public List<Object[]> getBoothsDataByDivisionId(Long divisionId,Long publicationId){
			Query query=getSession().createQuery(" select distinct model.boothId,model.partNo from Booth model where model.publicationDate.publicationDateId=:publicationId and " +
					" model.localBodyWard.constituencyId =:divisionId");
			query.setParameter("divisionId", divisionId);
			query.setParameter("publicationId", publicationId);
			return query.list();
		}
	 
	 
		public List<Object[]> getTotalVotersByBooths(List<Long> boothIds){
			//0 id,1name,2total,3districtId,4tehsilName,5 localBody,6localBody type
			Query query = getSession().createQuery("select model.boothId, model.partNo,model.totalVoters from Booth model "+
					" where model.boothId in(:boothIds) and model.publicationDate.publicationDateId = 17 and model.totalVoters is not null group by model.boothId");
			query.setParameterList("boothIds", boothIds);
			return query.list();
		}
		
		public List<Object[]> getMandalMuncipalityIds(Long boothId)
		{
			Query query = getSession().createQuery("select distinct model.localBody.localElectionBodyId,model.tehsil.tehsilId from Booth model left join model.localBody " +
					" left join model.tehsil where" +
					" model.boothId=:boothId and model.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"");
			query.setParameter("boothId", boothId);
			return query.list();
		}
		
		public List getBoothLocations(Long localElebodyId,String voterIdCardNo){
			Query query=getSession().createSQLQuery("select distinct b.location from booth_publication_voter bpv,booth b,voter v " +
					" where " +
					" bpv.booth_id = b.booth_id and v.voter_id=bpv.voter_id and b.local_election_body_id=:localElebodyId" +
					" and v.voter_id_card_no=:voterIdCardNo" +
					" and b.publication_date_id = :publicationDateId");
				query.setParameter("localElebodyId", localElebodyId);
				query.setParameter("voterIdCardNo", voterIdCardNo);
				query.setParameter("publicationDateId", 11l);	
			return query.list();
		}
		public List<Object[]> getboothList(Long localElecBdyId){
			Query query=getSession().createQuery("select model.boothId,model.partNo"
					+ " from Booth model"
					+ " where model.localBody.localElectionBodyId =:localElecBdyId " +
					" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("localElecBdyId", localElecBdyId);
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			
			return query.list();
		}
		public List<Object[]> getBoothListFrPanchayat(Long panchayId){
			Query query=getSession().createQuery("select distinct model.boothId,model.partNo" +
					" from Booth model" +
					" where model.panchayat.panchayatId=:panchayId" +
					" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("panchayId", panchayId);
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			
			return query.list();
					
			
		}
		public List<Object[]> getWardsByLocalElecBody(Long id,Long publicationDateId,Long constituencyId){
			StringBuilder query = new StringBuilder();
			query.append(" select distinct c1.constituency_id AS ward_id , c1.name as name" +
					"  from booth B, constituency C ,constituency c1 " +
					" where c1.local_election_body_id = B.local_election_body_id and B.constituency_id=C.constituency_id " +
					" and B.publication_date_id=:publicationDateId and " +
					" B.local_election_body_id=:id and B.constituency_id=:constituencyId ");
			
			Query queryObj = getSession().createSQLQuery(query.toString()).addScalar("ward_id",Hibernate.LONG)
					.addScalar("name",Hibernate.STRING);
			
			queryObj.setParameter("publicationDateId", publicationDateId);
			queryObj.setParameter("constituencyId", constituencyId);
			queryObj.setParameter("id", id);
			return queryObj.list();
	}
		
		public List<Constituency> getConstituencyIdByTehsilId(Long tehsilId){
			Query query = getSession().createQuery("select distinct model.constituency from Booth model" +
												" where model.tehsil.tehsilId = :tehsilId" +
												" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			query.setParameter("tehsilId", tehsilId);
			return query.list();
		}
		
		public List<Constituency> getConstituencyIdByLebId(Long lebId){
			Query query = getSession().createQuery("select distinct model.constituency from Booth model" +
												" where model.localBody.localElectionBodyId = :lebId" +
												" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			query.setParameter("lebId", lebId);
			return query.list();
		}
		
		public List<Long> getConstituencyIdsByLebId(Long lebId){
			Query query = getSession().createQuery("select distinct model.constituency.constituencyId from Booth model" +
												" where model.localBody.localElectionBodyId = :lebId" +
												" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			query.setParameter("lebId", lebId);
			return query.list();
		}
		
		public List<Object[]> getBoothsForTehsilId(List<Long> tehsilIds,Long constituencyId){
			Query query = getSession().createQuery("select distinct model.boothId," +
												" model.partNo," +
												" model.villagesCovered" +
												" from Booth model" +
												" where model.tehsil.tehsilId in (:tehsilIds)" +
												" and model.constituency.constituencyId = :constituencyId" +
												" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			query.setParameterList("tehsilIds", tehsilIds);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
		public List<Object[]> getBoothsForMuncipality(List<Long> lcalElcBdyId,Long constituencyId){
			Query query = getSession().createQuery("select distinct model.boothId," +
												" model.partNo," +
												" model.villagesCovered" +
												" from Booth model" +
												" where model.tehsil.tehsilId in (:lcalElcBdyId)" +
												" and model.constituency.constituencyId = :constituencyId" +
												" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			query.setParameterList("lcalElcBdyId", lcalElcBdyId);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
		public Long getTotalBoothsByConstituencyId(Long constituencyId){
			Query qry = getSession().createQuery(" select " +
					                             " count(distinct model.boothId) from Booth model " +
					                             " where model.constituency.constituencyId = :constituencyId" +
					                             " and model.publicationDate.publicationDateId = :publicationDate");
				
				if(constituencyId != null && constituencyId.longValue()>0l){
					qry.setParameter("constituencyId", constituencyId);
				}
				qry.setParameter("publicationDate", IConstants.VOTER_DATA_PUBLICATION_ID);
				return (Long) qry.uniqueResult();
		}
		public List<Object[]> getBoothsForMuncipalitys(List<Long> lcalElcBdyId,Long constituencyId){
			Query query = getSession().createQuery("select distinct model.boothId," +
												" model.partNo," +
												" model.villagesCovered " +
												" from Booth model " +
												" where model.panchayat.panchayatId in (:lcalElcBdyId)" +
												" and model.constituency.constituencyId = :constituencyId" +
												" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			query.setParameterList("lcalElcBdyId", lcalElcBdyId);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
		public List<Object[]> getBoothsForMuncipalityWise(List<Long> lcalElcBdyId,Long constituencyId){
			Query query = getSession().createQuery("select distinct model.boothId," +
												" model.partNo," +
												" model.villagesCovered " +
												" from Booth model " +
												" where model.localBody.localElectionBodyId in (:lcalElcBdyId)" +
												" and model.constituency.constituencyId = :constituencyId" +
												" and model.publicationDate.publicationDateId = :publicationDate");
			
			query.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
			query.setParameterList("lcalElcBdyId", lcalElcBdyId);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
		}
		               
		public List<Long>  getBoothCommitteesTotalCount(Long userAccessLevelId,Set<Long> userAccessLevelValues)
		  {
			 
			  StringBuilder queryStr = new StringBuilder();
			  queryStr.append("select distinct model.boothId from BoothInchargeCommittee model where model.booth.publicationDate.publicationDateId = :publicationDateId ");
			  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			         queryStr.append(" and model.address.state.stateId in (:userAccessLevelValues)");  
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			         queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues)");  
			       }/*else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			            queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			       }*/else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			            queryStr.append(" and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			          queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues)");  
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			          queryStr.append(" and model.address.localBody.localElectionBodyId in (:userAccessLevelValues)"); 
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			          queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues)"); 
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			          queryStr.append(" and model.address.localBodyWard.constituencyId in (:userAccessLevelValues)"); 
			       }
			 
			  Query query =getSession().createQuery(queryStr.toString());
			 
			  query.setParameter("publicationDateId",IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		
			  if(userAccessLevelValues != null && userAccessLevelValues.size()>0){
				   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			  }
			  
			  return query.list();
			  
		  }

		public Long  getBoothCommitteesOnlyTotalCount(Long userAccessLevelId,Set<Long> userAccessLevelValues)
		  {
			  StringBuilder queryStr = new StringBuilder();
			  queryStr.append("select distinct model.boothId from Booth model where model.publicationDate.publicationDateId = :publicationDateId ");
			  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			         queryStr.append(" and model.constituency.state.stateId in (:userAccessLevelValues)");  
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			         queryStr.append(" and model.constituency.district.districtId in (:userAccessLevelValues)");  
			       }/*else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			            queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			       }*/else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			            queryStr.append(" and model.constituency.constituencyId in (:userAccessLevelValues) ");  
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			          queryStr.append(" and model.tehsil.tehsilId in (:userAccessLevelValues)");  
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			          queryStr.append(" and model.localBody.localElectionBodyId in (:userAccessLevelValues)"); 
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			          queryStr.append(" and model.panchayat.panchayatId in (:userAccessLevelValues)"); 
			       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			          queryStr.append(" and model.localBodyWard.constituencyId in (:userAccessLevelValues)"); 
			       }
			 
			  Query query =getSession().createQuery(queryStr.toString());
			 
			  query.setParameter("publicationDateId",IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
		
			  if(userAccessLevelValues != null && userAccessLevelValues.size()>0){
				   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			  }
			  
			  return (Long) query.uniqueResult();
			  
		  }

		public List<Object[]> getLocationWiseCommitteesCountByLocIds(CommitteeInputVO committeeBO) {

			StringBuilder sbS = new StringBuilder();
			StringBuilder sbM = new StringBuilder();
			StringBuilder sbE = new StringBuilder();

			sbS.append(" select count(distinct model.boothId) ");
			sbM.append(" from  BoothInchargeCommittee model ");
			sbM.append(" where model.booth.publicationDate.publicationDateId =:publicationDateId ");
			if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
				sbS.append(",model.address.state.stateId,model.address.state.stateName ");
				sbE.append(" group by model.address.constituency.state.stateId ");
			}
			else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
				sbS.append(",model.address.district.districtId,model.address.district.districtName ");
				sbE.append(" group by model.address.district.districtId ");
			}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
				sbS.append(",model.userAddress.parliamentConstituency.constituencyId,model.userAddress.parliamentConstituency.name ");
				sbM.append(" and model.userAddress.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
				sbE.append(" group by model.userAddress.parliamentConstituency.constituencyId ");
			}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
				sbS.append(",model.address.constituency.constituencyId,model.address.constituency.name");
				sbE.append(" group by model.address.constituency.constituencyId ");
			}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
				sbS.append(",model.address.tehsil.tehsilId,model.address.tehsil.tehsilName ");
				sbE.append(" group by model.address.tehsil.tehsilId ");
			}
			if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
				sbM.append("and model.address.state.stateId = :stateId ");
			}
			StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
			Query query = getSession().createQuery(sbf.toString());
			if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
				query.setParameter("stateId",committeeBO.getStateId());
			}
			query.setParameter("publicationDateId",IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
			return query.list();
		}

		public List<Object[]> levelWiseBasicCommitteesCount(CommitteeInputVO committeeBO) {

			

			StringBuilder str = new StringBuilder();
	        
			str.append(" select 5,'booth'," +//1
					   "        1,'booth'," +//3
					   "        1,'booth'," +//5
					   "        count(distinct model.boothId)" +//6
					   " from   BoothInchargeCommittee model " +
					  "  where  " );
					
			
			//locations
			if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
				str.append(" model.address.constituency.state.stateId in (:tdpCommitteeLevelValues) ");
			}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
				str.append(" model.address.constituency.district.districtId in (:tdpCommitteeLevelValues) ");
			}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
				str.append(" and model.address.userAddress.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
			}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
				str.append(" model.address.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
			}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
				str.append(" model.address.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
			}
			
				
			if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
				str.append(" and model.address.constituency.state.stateId = :stateId ");
			}
			
		/*	if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
				str.append(committeeBO.getCommitteesQueryString());
			}
			*/
			//str.append(" order by model.tdpBasicCommittee.tdpBasicCommitteeId ");

			Query query = getSession().createQuery(str.toString());
			
			/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
				query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
			}*/
			//query.setParameterList("tdpCommitteeLevelIds",committeeBO.getTdpCommitteeLevelIds()); 
			
			if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
				
				query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
				
			}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
				
				query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
				
			}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
				
				query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
				
			}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
				
				query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
				
			}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
				
				query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
			}
			
						
			if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
				query.setParameter("stateId",committeeBO.getStateId());
			}
			
			return query.list();
		}
	public List<Object[]> committeesPerformanceCohort(CommitteeInputVO committeeBO)
	{
    	StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		StringBuilder sbE = new StringBuilder();
		
		sbS.append("select count(distinct model.boothId),1,'boothIncharge' ");//2
		sbE.append(" group by ");
		if(committeeBO.getGroupingLocation().equalsIgnoreCase("State")){
			sbS.append(" ,model.address.state.stateId,model.address.state.stateName ");//4
			sbE.append(" model.constituency.state.stateId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("District")){
			sbS.append(" ,model.address.district.districtId,model.address.district.districtName ");//4
			sbE.append(" model.address.district.districtId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Parliament")){
			sbS.append(" ,model.address.parliamentConstituency.constituencyId,model.address.parliamentConstituency.name ");//4
			sbE.append(" ,model.address.parliamentConstituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Constituency")){
			sbS.append(" ,model.address.constituencyId,model.address.constituency.name ");//4
			sbE.append(" model.address.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Mandal")){
			sbS.append(" ,model.address.tehsil.tehsilId,model.tehsil.tehsilName ");//4
			sbE.append(" model.address.tehsil.tehsilId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("localElectionBody")){
			sbS.append(" ,model.address.localElectionBody.localElectionBodyId,model.address.localElectionBody.name " +//4
					   " ,model.address.localElectionBody.electionType.electionTypeId,model.constituency.localElectionBody.electionType.electionType ");//6
			sbE.append(" model.constituency.localElectionBody.localElectionBodyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Village")){
			sbS.append(" ,model.address.panchayat.panchayatId,model.address.panchayat.panchayatName ");//10
			sbE.append(" model.address.panchayat.panchayatId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Ward")){
			sbS.append(" ,model.address.ward.constituencyId,model.address.ward.name ");//12
			sbE.append(" model.address.ward.constituencyId ");
		}
		sbM.append(" from  BoothInchargeCommittee model where ");
		//locations
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			sbS.append(",model.address.state.stateId,model.address.state.stateName ");
			sbM.append(" model.address.state.stateId in (:userAccessLocationValues) ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			sbS.append(",model.address.district.districtId,model.address.district.districtName ");
			sbM.append(" model.address.district.districtId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			sbS.append(",model.address.parliamentConstituency.constituencyId,model.address.parliamentConstituency.name ");
			sbM.append(" model.address.parliamentConstituency.constituencyId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			sbS.append(" ,model.address.constituencyId,model.address.constituency.name ");
			sbM.append(" model.address.constituency.constituencyId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			sbS.append(",model.address.tehsil.tehsilId,model.tehsil.tehsilName ");
			sbM.append(" model.address.tehsil.tehsilId in (:userAccessLocationValues) ");
		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.address.state.stateId = :stateId ");
		}
		
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		Query query = getSession().createQuery(sbf.toString());
		//query.setParameterList("tdpCommitteeLevelIds",committeeBO.getTdpCommitteeLevelIds()); 
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getTehsilIds());
		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		return query.list();
    }
	
	public List<Object[]> getVoterDetailsByBoothId(Long boothId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.voter.voterId,model.serialNo from BoothPublicationVoter model where model.boothId =:boothId " +
				" order by model.serialNo ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public List<Object[]> getVoterSerialNoByVoterIdsList(List<Long> voterIdsList,Long boothId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.voter.voterId,model.serialNo from BoothPublicationVoter model where model.boothId =:boothId and " +
				"  model.voter.voterId in (:voterIdsList) " +
				"order by model.serialNo ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("boothId", boothId);
		query.setParameterList("voterIdsList", voterIdsList);
		return query.list();
	}
	
	public List<Object[]> getTotalVotersForLocations(Set<Long> locationIdSet,Long locationTypeId,Long publicationDateId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select sum(model.totalVoters)  " );
				if(locationTypeId != null && locationTypeId == 2l){
					sb.append(" ,model.constituency.district.districtId  ");
				}else if(locationTypeId != null && (locationTypeId == 3l || locationTypeId == 10l)){
					sb.append(" ,model.constituency.constituencyId  ");
				}else if(locationTypeId != null && locationTypeId == 4l){
					sb.append(" ,model.tehsil.tehsilId ");
				}else if(locationTypeId != null && (locationTypeId == 5l || locationTypeId == 6l)){
					sb.append(" ,model.panchayat.panchayatId ");
				}else if(locationTypeId != null && locationTypeId == 7l){
					sb.append(" ,model.localBody.localElectionBodyId ");
				}else if(locationTypeId != null && locationTypeId == 8l){
					sb.append(", model.localBodyWard.constituencyId ");
				}
				sb.append(" from Booth model where  ");
				
				if(publicationDateId != null && publicationDateId.longValue() > 0l){
					sb.append("  model.publicationDate.publicationDateId = :publicationDateId ");
				}
		
				if(locationTypeId != null && locationTypeId == 2l){
					sb.append(" and model.constituency.district.districtId in (:locationIdSet) ");
				}else if(locationTypeId != null && (locationTypeId == 3l || locationTypeId == 10l)){
					sb.append("  and model.constituency.constituencyId in (:locationIdSet) ");
				}else if(locationTypeId != null && locationTypeId == 4l){
					sb.append(" and model.tehsil.tehsilId in (:locationIdSet) ");
				}else if(locationTypeId != null && (locationTypeId == 5l || locationTypeId == 6l)){
					sb.append(" and model.panchayat.panchayatId in (:locationIdSet) ");
				}else if(locationTypeId != null && locationTypeId == 7l){
					sb.append(" and model.localBody.localElectionBodyId in (:locationIdSet) ");
				}else if(locationTypeId != null && locationTypeId == 8l){
					sb.append(" and model.localBodyWard.constituencyId in (:locationIdSet) ");
				}
		
		if(locationTypeId != null && locationTypeId == 2l){
			sb.append(" group by model.constituency.district.districtId  ");
		}else  if(locationTypeId != null && (locationTypeId == 3l || locationTypeId == 10l)){
			sb.append(" group by model.constituency.constituencyId  ");
		}else if(locationTypeId != null && locationTypeId == 4l){
			sb.append(" group by model.tehsil.tehsilId ");
		}else if(locationTypeId != null && (locationTypeId == 5l || locationTypeId == 6l)){
			sb.append(" group by model.panchayat.panchayatId ");
		}else if(locationTypeId != null && locationTypeId == 7l){
			sb.append(" group by model.localBody.localElectionBodyId ");
		}else if(locationTypeId != null && locationTypeId == 8l){
			sb.append(" group by  model.localBodyWard.constituencyId ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(publicationDateId != null && publicationDateId.longValue() > 0l){
			query.setParameter("publicationDateId", publicationDateId);
		}
		if (locationIdSet != null && locationIdSet.size() > 0l) {
			query.setParameterList("locationIdSet", locationIdSet);
		}
		return query.list();
		
	}
	
	public List<Object[]> getTehsilAndLEBIdsByConstituency(List<Long> constituencyIds,Long publicationDateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct t.tehsilId,t.tehsilName" +
				" from Booth model" +
				" left join model.tehsil t" +
				" left join model.localBody leb" +
				" where model.publicationDate.publicationDateId = :publicationDateId");
		if(constituencyIds != null && constituencyIds.size()>0 && !constituencyIds.isEmpty()){
			sb.append(" and model.constituency.constituencyId in (:constituencyIds)");
		}
		
		Query query = getSession().createQuery(sb.toString());
			query.setParameter("publicationDateId",publicationDateId);
		if(constituencyIds != null && constituencyIds.size()>0 && !constituencyIds.isEmpty())
			query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
	}
	
	public List<Object[]> getPanchayatByMandal(List<Long> mandalIds,Long publicationDateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct p.panchayatId,p.panchayatName" +
				" from Booth model" +
				" left join model.panchayat p" +
				" where model.publicationDate.publicationDateId = :publicationDateId");
		if(mandalIds != null && mandalIds.size()>0 && !mandalIds.isEmpty()){
			sb.append(" and model.tehsil.tehsilId in (:mandalIds)");
		}
		
		Query query = getSession().createQuery(sb.toString());
			query.setParameter("publicationDateId",publicationDateId);
		if(mandalIds != null && mandalIds.size()>0 && !mandalIds.isEmpty())
			query.setParameterList("mandalIds", mandalIds);
		
		return query.list();
	}
	
	public List<Object[]> getMunciplaitiesByLeb(List<Long> lebIds,Long publicationDateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct w.constituencyId,w.name" +
				" from Booth model" +
				" left join model.localBodyWard w" +
				" where model.publicationDate.publicationDateId = :publicationDateId");
		if(lebIds != null && lebIds.size()>0 && !lebIds.isEmpty()){
			sb.append(" and model.localBody.localElectionBodyId in (:lebIds)");
		}
		
		Query query = getSession().createQuery(sb.toString());
			query.setParameter("publicationDateId",publicationDateId);
		if(lebIds != null && lebIds.size()>0 && !lebIds.isEmpty())
			query.setParameterList("lebIds", lebIds);
		
		return query.list();
	}
	
	public List<Object[]> getLocationWiseMandalAndConstituency(List<Long> thesilIds,String searchType,boolean isLocalBody){
		//0 id,1name,2total,3districtId,4tehsilName,5 localBody,6localBody type
		Query query = null;
		if(searchType != null && searchType.equalsIgnoreCase("mandal") && !isLocalBody){
			 query = getSession().createQuery("select model.constituency.constituencyId,model.constituency.name,model.tehsil.tehsilId,model.tehsil.tehsilName from TehsilConstituency model "+
					" where model.tehsil.tehsilId in(:thesilIds) " +
					" order by model.constituency.constituencyId ");
		}else if(searchType != null && searchType.equalsIgnoreCase("mandal") && isLocalBody){
			 query = getSession().createSQLQuery("select c.constituency_id as constituencyId,c.name as constituencyName,leb.local_election_body_id as tehsilId,concat(leb.name,'-',et.election_type) as tehsilName" +
			 		" From local_election_body leb,tehsil_constituency tc, constituency c, election_type et where leb.tehsil_id=tc.tehsil_id " +
			 		"and tc.constituency_id=c.constituency_id and et.election_type_id=leb.election_type_id and leb.local_election_body_id in(:thesilIds) ")
			 		.addScalar("constituencyId",Hibernate.LONG)
			 		.addScalar("constituencyName",Hibernate.STRING)
			 		.addScalar("tehsilId",Hibernate.LONG)
			 		.addScalar("tehsilName",Hibernate.STRING);
			}
		if(thesilIds != null && thesilIds.size() >0){
			query.setParameterList("thesilIds", thesilIds);
		}
		
		return query.list();
	}
	
	public List<Object[]> getLocationWiseMandalAndpanchayat(List<Long> panchayatIds,String searchType){
		Query query = null;
		if(searchType != null && searchType.equalsIgnoreCase("panchayat")){
			 query = getSession().createQuery("select model.tehsil.tehsilId,model.tehsil.tehsilName,model.panchayat.panchayatId,model.panchayat.panchayatName from Booth model "+
					" where model.panchayat.panchayatId in(:panchayatIds)  " +
					" order by model.tehsil.tehsilId ");
		}
		if(panchayatIds != null && panchayatIds.size() >0){
			query.setParameterList("panchayatIds", panchayatIds);
		}
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyInfoByConstituencyIdElectionYearAndElectionType(Long constituencyId) {
		Object[] params = {constituencyId};	
		return getHibernateTemplate().find("select distinct model.constituencyId," +
				" model.name,model.district.districtId," +
				" model.district.districtName,model.state.stateId," +
				" model.state.stateName" +			
				" from Constituency model where model.constituencyId  = ? ",params);
			
	}
	
	public List<Object[]> getAllAssemblyConstituenciesByStateTypeId(Long stateTypeId,Long stateId,Long electionYear)
	{
		
		List<Object[]> result = null;
		if(electionYear == null)
		{
			if(stateTypeId.longValue() == 0L)
			{
				Query query = getSession().createQuery("select  distinct C.constituencyId , C.name from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null order by C.name asc");
				query.setParameter("stateId", stateId);
				result =  query.list();
			}		
			else if(stateTypeId.longValue() == 1L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId > 10 order by C.name asc");	
				query.setParameter("stateId", stateId);
				result =   query.list();
			}
			else if(stateTypeId.longValue() == 2L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId < 11 order by C.name asc");
				query.setParameter("stateId", stateId);
				result =   query.list();
			}
		}
		else
		{
			if(stateTypeId.longValue() == 0L)
			{
				Query query = getSession().createQuery("select  distinct C.constituencyId , C.name from Constituency C,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear ="+electionYear+" order by C.name asc");
				query.setParameter("stateId", stateId);

				result =  query.list();
			}		
			else if(stateTypeId.longValue() == 1L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C ,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId > 10 and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear = "+electionYear+" order by C.name asc");
				
				query.setParameter("stateId", stateId);
				
				result =   query.list();
			}
			else if(stateTypeId.longValue() == 2L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C ,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId < 11 and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear = "+electionYear+" order by C.name asc");
				
				query.setParameter("stateId", stateId);

				result =   query.list();
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituenciesByDistrictId(Long districtId)
	{
		return getHibernateTemplate().find("select distinct model.constituencyId , model.name from Constituency model where model.district.districtId=? and model.electionScope.electionType.electionTypeId = 2 and model.deformDate IS NULL" +
				" order by model.name",districtId);
	}
	
}
