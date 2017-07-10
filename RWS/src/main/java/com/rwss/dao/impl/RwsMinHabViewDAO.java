package com.rwss.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsMinHabViewDAO;
import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinHabView;
import com.rwss.utils.IConstants;


@Repository
public class RwsMinHabViewDAO extends GenericDaoHibernate<RwsMinHabView,String> implements IRwsMinHabViewDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public RwsMinHabViewDAO() {
		super(RwsMinHabView.class);

	}
	@SuppressWarnings("unchecked")
	public List<String> getAllCoverageStatus(){
		StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select distinct model.coverageStatus from RwsMinHabView model");
		 Query query = getSession().createQuery(queryStr.toString());
		 return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getHabitationCoverageByStatusByLocationType(InputVO inputVo){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("select ");
		if (inputVo.getLocationType() != null && inputVo.getLocationType().trim().equalsIgnoreCase("district")) {
			queryStr.append(" model.dCode,model.dName,");
		} else if (inputVo.getLocationType().equalsIgnoreCase("constituency")) {
			queryStr.append(" model1.constituencyCode,model1.contituencyName,");
		} else if (inputVo.getLocationType().equalsIgnoreCase("mandal")) {
			queryStr.append("  model1.dCode,model1.mCode,model1.mName,");
		}

		queryStr.append(" model.coverageStatus,count(distinct model.panchCode) ");
		queryStr.append(" from  RwsMinHabView model,RwsMinConstituencyView model1 where model.mCode=model1.mCode ");
		queryStr.append(" and model.dCode=model1.dCode ");

		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			queryStr.append(" and  TO_CHAR(model.statusDate,'YY') =:year ");
		}

		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			queryStr.append(" and trim(model1.dCode) =:districtValue ");
		}

		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0	&& inputVo.getFilterValue() != null
				&& inputVo.getFilterValue().trim().length() > 0) {
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model1.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				queryStr.append(" and trim(model1.mCode) =:locationValue ");
			}
		}
		if (inputVo.getLocationType().equalsIgnoreCase("state")) {
			queryStr.append(" group by model.coverageStatus");
		} else if (inputVo.getLocationType().equalsIgnoreCase("district")) {
			queryStr.append(" group by model.dCode,model.dName,model.coverageStatus order by model.dCode,model.coverageStatus ");
		} else if (inputVo.getLocationType().equalsIgnoreCase("constituency")) {
			queryStr.append(" group by model1.constituencyCode,model1.contituencyName,model.coverageStatus order by model1.constituencyCode,model.coverageStatus ");
		} else if (inputVo.getLocationType().equalsIgnoreCase("mandal")) {
			queryStr.append(" group by model1.dCode,model1.mCode,model1.mName,model.coverageStatus order by  model1.dCode,model1.mCode,model.coverageStatus");
		}

		Query query = getSession().createQuery(queryStr.toString());

		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			query.setParameter("year", inputVo.getYear().trim());
		}
		if (inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			query.setParameter("districtValue", inputVo.getDistrictValue().trim());
		}
		return query.list();
	}
		@SuppressWarnings("unchecked")
		public List<Object[]> getLocationBasedOnSelection(InputVO inputVo){
			
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select distinct ");
		
		if (inputVo.getLocationType() != null && inputVo.getLocationType().trim() .equalsIgnoreCase("district")) {
			queryStr.append(" model1.dCode,model1.dName ");
		} else if (inputVo.getLocationType().equalsIgnoreCase("constituency")) {
			queryStr.append(" model1.constituencyCode,model1.contituencyName ");
		} else if (inputVo.getLocationType().equalsIgnoreCase("mandal")) {
			queryStr.append(" model1.mCode,model1.mName ");
		}

		queryStr.append(" from  RwsMinHabView model,RwsMinConstituencyView model1 where model1.mCode=model.mCode  and model1.dCode=model.dCode ");

		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0 && inputVo.getFilterValue() != null
				&& inputVo.getFilterValue().trim().length() > 0) {
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model1.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				queryStr.append(" and trim(model1.mCode) =:locationValue ");
			}
		}
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
	    return query.list();
		}
		@SuppressWarnings("unchecked")
		public List<Object[]> getStressedHabitationCountLocationWise(InputVO inputVo,String type){
			
	    StringBuilder queryStr = new StringBuilder();

		queryStr.append("select");
		
		if (inputVo.getLocationType() != null && inputVo.getLocationType().trim() .equalsIgnoreCase("district")) {
			queryStr.append(" model.dCode,");
		} else if (inputVo.getLocationType().equalsIgnoreCase("constituency")) {
			queryStr.append(" model1.constituencyCode,");
		} else if (inputVo.getLocationType().equalsIgnoreCase("mandal")) {
			queryStr.append(" model1.dCode,model1.mCode, ");
		}

		if (type != null && type.equalsIgnoreCase("statusWise") || inputVo.getLocationType() != null && inputVo.getLocationType().equalsIgnoreCase("state")) {
			queryStr.append(" model.coverageStatus,");
		}

		queryStr.append(" count(distinct model.panchCode) ");
		queryStr.append(" from  RwsMinHabView model,RwsMinStressedHabView RMSHV,RwsMinConstituencyView model1 ");

		queryStr.append(" where  model.panchCode=RMSHV.habCode and model.mCode=model1.mCode ");
		queryStr.append(" and model.dCode=model1.dCode ");

		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			queryStr.append(" and TO_CHAR(model.statusDate,'YY') =:year ");
		}

		if (inputVo.getStressedHabitationYearsList() != null && inputVo.getStressedHabitationYearsList().size() > 0) {
			queryStr.append(" and trim(RMSHV.year) in (:stressedHabYear) ");
		}

		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			queryStr.append(" and trim(model1.dCode) =:districtValue ");
		}

		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0 && inputVo.getFilterValue() != null
				&& inputVo.getFilterValue().trim().length() > 0) {
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model1.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				queryStr.append(" and trim(model1.mCode) =:locationValue ");
			}
		}
		if (inputVo.getLocationType().equalsIgnoreCase("state")) {
			queryStr.append(" group by model.coverageStatus");
		} else if (inputVo.getLocationType().equalsIgnoreCase("district")) {
			queryStr.append(" group by model.dCode ");
		} else if (inputVo.getLocationType().equalsIgnoreCase("constituency")) {
			queryStr.append(" group by model1.constituencyCode ");
		} else if (inputVo.getLocationType().equalsIgnoreCase("mandal")) {
			queryStr.append(" group by model1.dCode,model1.mCode ");
		}

		if (type != null && type.equalsIgnoreCase("statusWise")) {
			queryStr.append(",model.coverageStatus");
		}
		if (inputVo.getLocationType() != null && inputVo.getLocationType().equalsIgnoreCase("mandal")) {
			queryStr.append(" order by  model1.dCode,model1.mCode asc");
		}

		Query query = getSession().createQuery(queryStr.toString());

		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			query.setParameter("year", inputVo.getYear().trim());
		}
		if (inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			query.setParameter("districtValue", inputVo.getDistrictValue().trim());
		}
		if (inputVo.getStressedHabitationYearsList() != null && inputVo.getStressedHabitationYearsList().size() > 0) {
			query.setParameterList("stressedHabYear",inputVo.getStressedHabitationYearsList());
		}
		return query.list();
	}

	@Override
	public Object[] gethabitationWatersupplyDetails(InputVO inputVo) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(model.safeLpcd), sum(model.unSafeLpcd) from RwsMinHabView model,RwsMinConstituencyView model1 " +
				        " where model.mCode=model1.mCode and model.dCode=model1.dCode ");
	    
		
		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			queryStr.append(" and TO_CHAR(model.statusDate,'YY') =:year ");
		}
		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0 && inputVo.getFilterValue() != null
				&& inputVo.getFilterValue().trim().length() > 0) {
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model1.constituencyCode) =:locationValue ");
			}
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			query.setParameter("year", inputVo.getYear().trim());
		}
		if (inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
		return (Object[]) query.uniqueResult();

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RwsMinHabView> getAllHabitationDetails(InputVO inputVo) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" from RwsMinHabView model ");

		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			queryStr.append(" where TO_CHAR(model.statusDate,'YY') =:year ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			query.setParameter("year", inputVo.getYear().trim());
		}
		return query.list();

	}
	@Override
	public List<Object[]> getHabitationDetailsByStatusByLocationType(InputVO inputVo) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
		queryStr.append(" model1.dCode,model1.dName,"+
				 		" model1.constituencyCode,model1.contituencyName,"+
				        " model1.mCode,model1.mName,"+
				        " model.panchCode,model.panchName," +
				        " model.coverageStatus ");

		queryStr.append(" from  RwsMinHabView model,RwsMinConstituencyView model1 "+
				        " where model.mCode=model1.mCode and model.dCode=model1.dCode ");
			
		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			queryStr.append(" and  TO_CHAR(model.statusDate,'YY') =:year ");
		}else if(inputVo.getFromDate() != null && inputVo.getToDate() != null){
			queryStr.append(" and  date(model.statusDate) between :fromDate and :toDate ");
		}
		
		if (inputVo.getStatusList() != null && inputVo.getStatusList().size() > 0) {
			queryStr.append(" and trim(model.coverageStatus) in (:statusValues)");
		}

		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			queryStr.append(" and trim(model1.dCode) =:districtValue ");
		}

		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0
				&& inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model1.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				queryStr.append(" and trim(model1.mCode) =:locationValue ");
			}
		}

		Query query = getSession().createQuery(queryStr.toString());

		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			query.setParameter("year", inputVo.getYear().trim());
		}else if(inputVo.getFromDate() != null && inputVo.getToDate() != null){
			query.setDate("fromDate", inputVo.getFromDate());
			query.setDate("toDate", inputVo.getToDate());
		}
		if (inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue());
		}
		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			query.setParameter("districtValue", inputVo.getDistrictValue());
		}
		if (inputVo.getStatusList() != null && inputVo.getStatusList().size() > 0) {
			query.setParameterList("statusValues", inputVo.getStatusList());
		}
		if (inputVo.getStartValue() != null && inputVo.getStartValue()!=0){
			query.setFirstResult(inputVo.getStartValue());
		}
		if (inputVo.getEndValue() != null && inputVo.getEndValue()!=0){
			query.setMaxResults(inputVo.getEndValue());
		}
		return query.list();
	}
	@Override
	public List<Object[]> getOnclickHabitationsupplyDetails(InputVO inputVO) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.dCode,model.dName,model1.constituencyCode,model1.contituencyName,model.mCode,model.mName,model.panchCode, model.panchName ");
			
		if(inputVO.getType()!=null && inputVO.getType().equalsIgnoreCase(IConstants.SUPPLY_TYPE_SAFE)){
				queryStr.append(",model.safeLpcd ");
			}else if(inputVO.getType()!=null && inputVO.getType().equalsIgnoreCase(IConstants.SUPPLY_TYPE_UNSAFE)){
				queryStr.append(",model.unSafeLpcd ");
			}
		
			queryStr.append(" from RwsMinHabView model,RwsMinConstituencyView model1 where model.mCode=model1.mCode and model.dCode=model1.dCode ");
	    
		
		if (inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
			queryStr.append(" and TO_CHAR(model.statusDate,'YY') =:year ");
		}else if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
			queryStr.append(" and date(model.statusDate) between :fromDate and :toDate ");
		}
		if (inputVO.getDistrictValue() != null && inputVO.getDistrictValue().trim().length() > 0) {
			queryStr.append(" and model.dCode =:districtValue ");
		}
		if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0 && inputVO.getFilterValue() != null
				&& inputVO.getFilterValue().trim().length() > 0) {
			if (inputVO.getFilterType().equalsIgnoreCase(IConstants.DISTRICT)) {
				queryStr.append(" and model1.dCode =:locationValue ");
			} else if (inputVO.getFilterType().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
				queryStr.append(" and model1.constituencyCode =:locationValue ");
			}else if (inputVO.getFilterType().equalsIgnoreCase(IConstants.MANDAL)) {
				queryStr.append(" and model1.mCode =:locationValue ");
			}
		}
		
		queryStr.append(" group by  model.dCode,model.dName,model1.constituencyCode,model1.contituencyName,model.mCode,model.mName,model.panchCode, model.panchName");
		if(inputVO.getType()!=null && inputVO.getType().equalsIgnoreCase(IConstants.SUPPLY_TYPE_SAFE)){
			queryStr.append(",model.safeLpcd ");
		}else if(inputVO.getType()!=null && inputVO.getType().equalsIgnoreCase(IConstants.SUPPLY_TYPE_UNSAFE)){
			queryStr.append(",model.unSafeLpcd ");
		}
		queryStr.append(" order by model.dCode,model.mCode");
		Query query = getSession().createQuery(queryStr.toString());
		if (inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
			query.setParameter("year", inputVO.getYear().trim());
		}else if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("toDate", inputVO.getToDate());
		}
		if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
			 
		}
		if (inputVO.getDistrictValue() != null && inputVO.getDistrictValue().trim().length() > 0) {
			query.setParameter("districtValue", inputVO.getDistrictValue());
		}
		if (inputVO.getStartValue() != null && inputVO.getStartValue() != 0) {
			query.setFirstResult(inputVO.getStartValue());
		}
		if (inputVO.getEndValue() != null && inputVO.getEndValue() != 0) {
			query.setMaxResults(inputVO.getEndValue());
		}
		return query.list();
	}
	@Override
	public List<Object[]> getStressedHabitationDetailsByStatusByLocationType(InputVO inputVo) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
		queryStr.append(" model1.dCode,model1.dName,"+
				 		" model1.constituencyCode,model1.contituencyName,"+
				        " model1.mCode,model1.mName,"+
				        " model.panchCode,model.panchName," +
				        " model.coverageStatus ");

		queryStr.append(" from  RwsMinHabView model,RwsMinConstituencyView model1,RwsMinStressedHabView model2 "+
				        " where model.mCode=model1.mCode and model.dCode=model1.dCode and model.panchCode = model2.habCode ");
			
		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			queryStr.append(" and  TO_CHAR(model.statusDate,'YY') =:year ");
		}
		if (inputVo.getStatusList() != null && inputVo.getStatusList().size() > 0) {
			queryStr.append(" and trim(model.coverageStatus) in (:statusValues)");
		}

		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			queryStr.append(" and trim(model1.dCode) =:districtValue ");
		}
		if (inputVo.getStressedHabitationYear() != null && inputVo.getStressedHabitationYear().trim().length() > 0) {
			queryStr.append(" and trim(model2.year) =:stressedHabYear ");
		}
		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0
				&& inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model1.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				queryStr.append(" and trim(model1.mCode) =:locationValue ");
			}
		}

		Query query = getSession().createQuery(queryStr.toString());

		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			query.setParameter("year", inputVo.getYear().trim());
		}
		if (inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue());
		}
		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			query.setParameter("districtValue", inputVo.getDistrictValue());
		}
		if (inputVo.getStatusList() != null && inputVo.getStatusList().size() > 0) {
			query.setParameterList("statusValues", inputVo.getStatusList());
		}
		if (inputVo.getStartValue() != null && inputVo.getStartValue()!=0){
			query.setFirstResult(inputVo.getStartValue());
		}
		if (inputVo.getStressedHabitationYear() != null && inputVo.getStressedHabitationYear().trim().length() > 0) {
			query.setParameter("stressedHabYear",inputVo.getStressedHabitationYear());
		}
		if (inputVo.getEndValue() != null && inputVo.getEndValue()!=0){
			query.setMaxResults(inputVo.getEndValue());
		}
		return query.list();
	}

}
