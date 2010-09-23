package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

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
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findTehsilsByElectionAndConstituency(String electionYear,Long constituencyId){
		Object[] params = {constituencyId, electionYear};
		return getHibernateTemplate().find("select distinct model.tehsil from Booth model where model.boothId in(select model1.booth.boothId from " +
				"BoothConstituencyElection model1 where model1.constituencyElection.constituency.constituencyId = ? " +
				"and model1.constituencyElection.election.electionYear=?)",params);
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
		"model.constituency.district.districtId = ? and model.year = (select max(model1.year) from Booth model1 where " +
		"model1.year <= ?) and model.partNo = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findbyConstituencyNameDistrictIdAndElectionYear(
			String acName, Long districtId, Long electionYear) {
		Object[] params = {acName, districtId, electionYear};
		return getHibernateTemplate().find("select count(model.boothId) from Booth model where model.constituency.name = ? and " +
		"model.constituency.district.districtId = ? and model.year = (select max(model1.year) from Booth model1 where " +
		"model1.year <= ?)",params);
	}

}
