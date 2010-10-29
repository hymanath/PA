package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.columns.enums.BoothColumnNames;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Tehsil;

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
				"model.constituency.constituencyId = ? and model.year = ?",params);
	}
	
	public List findByPartNoConstituencyIdAndYear(Long constituencyId, Long year, String partNo){
		Object[] params = {constituencyId, year, partNo};
		return getHibernateTemplate().find("select count(model.boothId) from Booth model where " +
		"model.constituency.constituencyId = ? and model.year = ? and model.partNo = ?",params);
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
		Object[] params = {year, constituencyId, localBodyTypes};
		return getHibernateTemplate().find("select model.localBody.localElectionBodyId, model.localBody.name, sum(model.maleVoters), " +
				" sum(model.femaleVoters),sum(model.totalVoters) from Booth model where model.year = (select max(model1.year) from Booth model1 where " +
				" model1.year <= ?) and model.constituency.constituencyId =? and model.localElectionBody.electionType.electionType in ("+localBodyTypes+")" +
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
		return getHibernateTemplate().find("select model.boothId from Booth model where " +
				"model.partNo in ( "+ partNos + ") and model.year = ? and model.constituency.constituencyId = ?",params);
	}
	
	
}
