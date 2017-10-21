package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IHabitationDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.Habitation;
import com.itgrids.utils.IConstants;
@Repository
public class HabitationDAO extends GenericDaoHibernate<Habitation, Long> implements IHabitationDAO {
	public HabitationDAO(){
		super(Habitation.class);
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Object[]> getHabitationDataList(InputVO inputVO){
		StringBuilder sb = new StringBuilder();
		sb.append(" select  "
				+ " habitation.habitationId, "//0
				+ " habitation.districtName, "//1
				+ " habitation.mandalName , "//2
				+ " habitation.panchayatName ,"//3
				+ " habitation.villageName , "//4
				+ " habitation.panchName , "//5
				+ " habitation.panchCode , "//6
				+ " habitation.censusYear , "//7
				+ " habitation.censusPlainPopulation , "//8
				+ " habitation.censusScPopulation , "//9
				+ " habitation.censusStPopulation , "//10
				+ " habitation.plainPopulationCovered , "//11
				+ " habitation.statusDate , "//12
				+ " habitation.unSafeLpcd , "//13
				+ " habitation.safeLpcd , "//14
				+ " habitation.coverageStatus , "//15
				+ " habitation.previousYearStatus , "//16
				+ " habitation.latitude , "//17
				+ " habitation.longtitude , "//18
				+ " habitation.scPopulationCovered , "//19
				+ " habitation.districtCode , "//20
				+ " habitation.mandalCode , "//21
				+ " habitation.panchayatCode , "//22
				+ " habitation.villageCode "//23
				+ " from Habitation habitation "
				+ " where  "
				+ " habitation.isDeleted = 'N' ");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
	public int deleteObsolateData(List<Long> habitationIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" update habitation set is_deleted='Y' where habitation_id in (:habitationIdList) ");
		Query query = getSession().createSQLQuery(sb.toString());
		query.setParameterList("habitationIdList", habitationIdList);
		return query.executeUpdate();
	}
	public List<Object[]> getHabitationDetails(InputVO inputVO,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		StringBuilder sg = new StringBuilder();
		sb.append(" select  ");
		if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
			sb.append(" '0' as id ,'Andhra Pradesh' as name , model.coverage_status as status, count(model.habitation_id) as count, sum(model.total_population) as total ");
			sg.append(" group by model.coverage_status ");
		}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
			sb.append(" model.district_code as id, model.district_name as name, model.coverage_status as status , count(model.habitation_id) as count,sum(model.total_population) as total ");
			sg.append(" group by model.district_code, model.coverage_status ");
		}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
			sb.append(" model.mandal_code as id, model.mandal_name as name, model.coverage_status as status, count(model.habitation_id) as count,sum(model.total_population) as total");
			sg.append(" group by model.district_code, model.mandal_code, model.coverage_status ");
		}
		sb.append(" from habitation model where model.is_deleted = 'N' ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.status_date) between :fromDate and :toDate ");
		}
		Query query = getSession().createSQLQuery(sb.toString()+" "+sg.toString()).
				addScalar("id", StandardBasicTypes.LONG).
				addScalar("name",StandardBasicTypes.STRING).
				addScalar("status", StandardBasicTypes.STRING).
				addScalar("count", StandardBasicTypes.LONG).
				addScalar("total", StandardBasicTypes.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	public List<Object[]> getTressedHabitationDetails(InputVO inputVO,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		StringBuilder sg = new StringBuilder();
		sb.append(" select  ");
		if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("state")){
			sb.append(" '0' as id,'Andhra Pradesh' as name, model1.coverage_status as status, count(model.habitation_id) as count, sum(model.total_population) as total ");
			sg.append(" group by model1.coverage_status ");
		}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
			sb.append(" model.district_code as id, model.district_name as name, model1.coverage_status as status, count(model.habitation_id) as count ,sum(model.total_population) as total ");
			sg.append(" group by model.district_code, model1.coverage_status ");
		}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
			sb.append(" model.mandal_code as id, model.mandal_name as name , model1.coverage_status as status , count(model.habitation_id) as count ,sum(model.total_population) as total ");
			sg.append(" group by model.mandal_code, model1.coverage_status ");
		}
		sb.append(" from habitation model,tressed_habitation model1 where model.is_deleted = 'N' and model1.is_deleted = 'N' ");
		sb.append(" and model.panch_code=model1.habitation_code ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.status_date) between :fromDate and :toDate ");
		}
		Query query = getSession().createSQLQuery(sb.toString()+" "+sg.toString()).
				addScalar("id", StandardBasicTypes.LONG).
				addScalar("name",StandardBasicTypes.STRING).
				addScalar("status", StandardBasicTypes.STRING).
				addScalar("count", StandardBasicTypes.LONG).
				addScalar("total", StandardBasicTypes.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	public List<Object[]> getHabitationDetailsForConstituency(InputVO inputVO,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select RT.constituency_code as id,'' as name ,H.coverage_status as status ,count(H.habitation_id) as count ,sum(H.total_population) as total ");
		sb.append(" from habitation H, rws_tehsil RT ");
		sb.append(" where ");
		sb.append(" H.is_deleted='N' ");
		sb.append(" and H.district_code=RT.rws_district_id ");
		sb.append(" and H.mandal_code = RT.tehsil_code_int ");
		if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			sb.append(" and H.district_code = :districtId ");
		}else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sb.append(" and RT.constituency_code = :constituencyId ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(H.status_date) between :fromDate and :toDate ");
		}
		sb.append(" group by ");
		sb.append(" RT.constituency_code,H.coverage_status ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("id", StandardBasicTypes.LONG)
				.addScalar("name",StandardBasicTypes.STRING)
				.addScalar("status", StandardBasicTypes.STRING)
				.addScalar("count", StandardBasicTypes.LONG)
				.addScalar("total", StandardBasicTypes.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			query.setParameter("districtId", Long.valueOf(inputVO.getFilterValue()));
		}else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			query.setParameter("constituencyId", Long.valueOf(inputVO.getFilterValue()));
		}
		return query.list();
	}
	public List<Object[]> getTressedHabitationDetailsForConstituency(InputVO inputVO,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select RT.constituency_code as id,'' as name,TH.coverage_status as status ,count(H.habitation_id) as count ,sum(H.total_population) as total ");
		sb.append(" from habitation H, rws_tehsil RT, tressed_habitation TH ");
		sb.append(" where ");
		sb.append(" H.is_deleted='N' and TH.is_deleted='N' ");
		sb.append(" and H.panch_code = TH.habitation_code ");
		sb.append(" and H.district_code=RT.rws_district_id ");
		sb.append(" and H.mandal_code = RT.tehsil_code_int ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(H.status_date) between :fromDate and :toDate ");
		}
		sb.append(" group by ");
		sb.append(" RT.constituency_code,TH.coverage_status ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("id", StandardBasicTypes.LONG)
				.addScalar("name",StandardBasicTypes.STRING)
				.addScalar("status", StandardBasicTypes.STRING)
				.addScalar("count", StandardBasicTypes.LONG)
				.addScalar("total", StandardBasicTypes.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	public List<Object[]> getHabitationDetailsForMandal(InputVO inputVO,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		StringBuilder sg = new StringBuilder();
		sb.append(" select  ");
		sb.append(" H.district_code as distId,H.mandal_code as id, H.mandal_name as name, H.coverage_status as status, count(H.habitation_id) as count,sum(H.total_population) as total");
		sg.append(" group by H.district_code, H.mandal_code, H.coverage_status ");
		
		sb.append(" from habitation H ");
		if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sb.append(" , rws_tehsil RT ");
		}
		sb.append(" where H.is_deleted = 'N' ");
		if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sb.append(" and H.district_code=RT.rws_district_id ");
			sb.append(" and H.mandal_code = RT.tehsil_code_int ");
		}
		if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			sb.append(" and H.district_code = :districtId ");
		}else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sb.append(" and RT.constituency_code = :constituencyId ");
		}else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.MANDAL)){
			sb.append(" and H.district_code = :districtId ");
			sb.append(" and H.mandal_code = :mandalId ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(H.status_date) between :fromDate and :toDate ");
		}
		Query query = getSession().createSQLQuery(sb.toString()+" "+sg.toString()).
				addScalar("distId", StandardBasicTypes.LONG).
				addScalar("id", StandardBasicTypes.LONG).
				addScalar("name",StandardBasicTypes.STRING).
				addScalar("status", StandardBasicTypes.STRING).
				addScalar("count", StandardBasicTypes.LONG).
				addScalar("total", StandardBasicTypes.LONG);
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			query.setParameter("districtId", Long.valueOf(inputVO.getFilterValue()));
		}else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			query.setParameter("constituencyId", Long.valueOf(inputVO.getFilterValue()));
		}else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.MANDAL)){
			query.setParameter("districtId", Long.valueOf(inputVO.getDistrictValue()));//1
			query.setParameter("mandalId", Long.valueOf(inputVO.getFilterValue()));//5
		}
		return query.list();
	}
	
}
