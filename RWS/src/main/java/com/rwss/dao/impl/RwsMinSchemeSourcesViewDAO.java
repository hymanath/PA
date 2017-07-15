package com.rwss.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsMinSchemeSourcesViewDAO;
import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinSchemeSourcesView;
import com.rwss.utils.IConstants;

@Repository
public class RwsMinSchemeSourcesViewDAO extends GenericDaoHibernate<RwsMinSchemeSourcesView, String> implements IRwsMinSchemeSourcesViewDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public RwsMinSchemeSourcesViewDAO() {
		super(RwsMinSchemeSourcesView.class);

	}
	
	@Override
	public Long getWaterSourceDeatils(InputVO inputVo, String sourceType,String type) {

		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		
		sb.append(" SELECT ");
		
		//,RwsMinConstituencyView model4
		sbm.append(" FROM RwsMinSchemeSourcesView model ,RwsMinAssetNabInkView model2,"
				+ " RwsMinHabView model3 ");
		
		sbe.append(" WHERE "
				+ " model.assetCode=model2.assetCode  "
				+ " and model2.habCode=model3.panchCode ");
		
		if (IConstants.SUPPLY_TYPE_SAFE.equalsIgnoreCase(type)) {
			sb.append(" count(model3.safeLpcd) ");			
			sbe.append(" AND model3.safeLpcd != '0' and model3.safeLpcd is not null ");
			
		} else if (IConstants.SUPPLY_TYPE_UNSAFE.equalsIgnoreCase(type)) {
			sb.append(" count(model3.unSafeLpcd) ");
			sbe.append(" AND model3.unSafeLpcd != '0' and model3.unSafeLpcd is not null");
		}else if("total".equalsIgnoreCase(type)){
			   sb.append(" count(distinct model.sourceCode) " );			
		}
		if (inputVo.getFromDate() != null && inputVo.getToDate() != null) {
			sbe.append(" and model3.statusDate between :fromDate and :toDate ");
		}
		if (sourceType != null && !sourceType.isEmpty()) {
			sbe.append(" and trim(model.sourceTypeName)=:sourceType");
		}
		 if(inputVo.getDistrictValue()!= null && inputVo.getDistrictValue().trim().length()>0){
	    	   sbe.append(" and model3.dCode =:districtvalue");
	       }

		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0 && inputVo.getFilterValue() != null	&& inputVo.getFilterValue().trim().length() > 0) {
			sbm.append(" , RwsMinConstituencyView model4 ");
			
			sbe.append(" and model3.mCode=model4.mCode  "
					+ " and model3.dCode=model4.dCode ");
			
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				sbe.append(" and trim(model4.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				sbe.append(" and trim(model4.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				sbe.append(" and trim(model4.mCode) =:locationValue ");
			}
			 
		}
		
		sb.append(sbm.toString()).append(sbe.toString());

		Query query = getSession().createQuery(sb.toString()); 
				
		if (inputVo.getFromDate() != null && inputVo.getToDate() != null) {
			query.setDate("fromDate", inputVo.getFromDate());
			query.setDate("toDate", inputVo.getToDate());
		}		
		if (sourceType != null && !sourceType.isEmpty()) {
			query.setParameter("sourceType", sourceType.trim());
		}
		if (inputVo.getFilterValue() != null	&& inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
		if(inputVo.getDistrictValue()!= null && inputVo.getDistrictValue().trim().length()>0){
			query.setParameter("districtvalue", inputVo.getDistrictValue().trim());
	     }
		return (Long) query.uniqueResult();
	}
	@Override
	public List<Object[]> getWaterSourceDeatilsGroupByLocation(InputVO inputVo,String sourceType) {
		
		StringBuilder queryStr = new StringBuilder(); 
		
		queryStr.append(" select "
					  + " model3.dCode, "
    		 	      + " model3.dName, "
    		 	      + " model4.constituencyCode, "
    		 	      + " model4.contituencyName, "
    		 	      + " model3.mCode, "
    		 	      + " model3.mName, "
					  + " model3.panchCode,"
					  + " model3.panchName,"
					  + " model.sourceCode,"
					  + " model.assetCode" );
		 queryStr.append(" from "
		 			   + " RwsMinSchemeSourcesView model, "
		 			   + " RwsMinAssetNabInkView model2," +
				         " RwsMinHabView model3,"
				       + " RwsMinConstituencyView model4 " +
				         " where" +
				         " model.assetCode=model2.assetCode and " +
				         " model2.habCode=model3.panchCode and " +
				         " model3.mCode=model4.mCode and " +
				         " model3.dCode=model4.dCode ");
							 
		if (sourceType != null && !sourceType.isEmpty()) {
			queryStr.append(" and trim(model.sourceTypeName)=:sourceType");
		}
	       
		if (IConstants.SUPPLY_TYPE_SAFE.equalsIgnoreCase(inputVo.getType())) {
			queryStr.append(" AND model3.safeLpcd != '0' and model3.safeLpcd is not null ");

		} else if (IConstants.SUPPLY_TYPE_UNSAFE.equalsIgnoreCase(inputVo.getType())) {
			queryStr.append(" AND model3.unSafeLpcd != '0' and model3.unSafeLpcd is not null");
		}
		if (inputVo.getFromDate() != null && inputVo.getToDate() != null) {
			queryStr.append(" and model3.statusDate between :fromDate and :toDate ");
		}
		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0 && inputVo.getFilterValue() != null
				&& inputVo.getFilterValue().trim().length() > 0) {
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model4.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model4.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				queryStr.append(" and trim(model4.mCode) =:locationValue ");
			}
		}
		if (inputVo.getDistrictValue() != null
				&& inputVo.getDistrictValue().trim().length() > 0) {
			queryStr.append(" and trim(model4.dCode) =:districtValue ");
		}
		queryStr.append(" group by "
    			  + " model3.dCode, model3.dName, model4.constituencyCode, model4.contituencyName, model3.mCode, model3.mName, "
    			  + "model3.panchCode,"
    			  + " model3.panchName,"
    			  + " model.sourceCode, "
    			  + " model.assetCode"
    			  + " order by "
				  + " model3.dCode ");	
		Query query = getSession().createQuery(queryStr.toString());
		
		if (inputVo.getFromDate() != null && inputVo.getToDate() != null) {
			query.setDate("fromDate", inputVo.getFromDate());
			query.setDate("toDate", inputVo.getToDate());
		}
		if (sourceType != null && !sourceType.isEmpty()) {
			query.setParameter("sourceType", sourceType.trim());
		}
		if (inputVo.getFilterValue() != null && inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
		if (inputVo.getDistrictValue() != null && inputVo.getDistrictValue().trim().length() > 0) {
			query.setParameter("districtValue", inputVo.getDistrictValue());
		}
		return query.list();
	}

	@Override
	public BigDecimal gethabitationWatersupplyDetails(InputVO inputVo,String supplyTypeSafe, String sourceType) {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		
		sb.append(" SELECT ");
		
		//,RwsMinConstituencyView model4
		sbm.append(" FROM RwsMinSchemeSourcesView model ,RwsMinAssetNabInkView model2,"
				+ " RwsMinHabView model3 ");
		
		sbe.append(" WHERE "
				+ " model.assetCode=model2.assetCode  "
				+ " and model2.habCode=model3.panchCode ");
		
		if (IConstants.SUPPLY_TYPE_SAFE.equalsIgnoreCase(supplyTypeSafe)) {
			sb.append(" sum(model3.safeLpcd) ");		
			
		} else if (IConstants.SUPPLY_TYPE_UNSAFE.equalsIgnoreCase(supplyTypeSafe)) {
			sb.append(" sum(model3.unSafeLpcd) ");
		}

		if (sourceType != null && !sourceType.isEmpty()) {
			sbe.append(" and trim(model.sourceTypeName)=:sourceType");
		}
		 if(inputVo.getDistrictValue()!= null && inputVo.getDistrictValue().trim().length()>0){
	    	   sbe.append(" and model3.dCode =:districtvalue");
	       }
		 if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
				sbe.append(" and TO_CHAR(model3.statusDate,'YY') =:year ");
			}

		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0 && inputVo.getFilterValue() != null	&& inputVo.getFilterValue().trim().length() > 0) {
			sbm.append(" , RwsMinConstituencyView model4 ");
			
			sbe.append(" and model3.mCode=model4.mCode  and model3.dCode=model4.dCode ");
			
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				sbe.append(" and trim(model4.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				sbe.append(" and trim(model4.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				sbe.append(" and trim(model4.mCode) =:locationValue ");
			}
			 
		}
		
		sb.append(sbm.toString()).append(sbe.toString());

		Query query = getSession().createQuery(sb.toString()); 
		
		if (inputVo.getYear() != null && inputVo.getYear().trim().length() > 0) {
			query.setParameter("year", inputVo.getYear().trim());
		}
		if (sourceType != null && !sourceType.isEmpty()) {
			query.setParameter("sourceType", sourceType.trim());
		}
		if (inputVo.getFilterValue() != null	&& inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
		if(inputVo.getDistrictValue()!= null && inputVo.getDistrictValue().trim().length()>0){
			query.setParameter("districtvalue", inputVo.getDistrictValue().trim());
	     }
		return (BigDecimal) query.uniqueResult();
	
	}
	
	@Override
	public List<Object[]> getWaterSourceDeatils2(InputVO inputVo, String sourceType,String type) {

		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		
		sb.append(" SELECT ");
		
		//,RwsMinConstituencyView model4
		sbm.append(" FROM RwsMinSchemeSourcesView model ,RwsMinAssetNabInkView model2,"
				+ " RwsMinHabView model3 " );
		
		sbe.append(" WHERE "
				+ " model.assetCode=model2.assetCode  "
				+ " and model2.habCode=model3.panchCode ");
				
		if(inputVo.getLocationType()!= null && (inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL) 
				|| inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY) )){
			sbm.append(", RwsMinConstituencyView model4 "); 
			sbe.append(" and model3.mCode=model4.mCode  "
					+ " and model3.dCode=model4.dCode ");
		}
			
		
		if (IConstants.SUPPLY_TYPE_SAFE.equalsIgnoreCase(type)) {
			sb.append(" count(model3.safeLpcd) ");			
			sbe.append(" AND model3.safeLpcd != '0' and model3.safeLpcd is not null ");
			
		} else if (IConstants.SUPPLY_TYPE_UNSAFE.equalsIgnoreCase(type)) {
			sb.append(" count(model3.unSafeLpcd) ");
			sbe.append(" AND model3.unSafeLpcd != '0' and model3.unSafeLpcd is not null");
		}else if("total".equalsIgnoreCase(type)){
			   sb.append(" count(distinct model.sourceCode) " );			
		}
		if (inputVo.getFromDate() != null && inputVo.getToDate() != null) {
			sbe.append(" and model3.statusDate between :fromDate and :toDate ");
		}
		

		if(inputVo.getLocationType()!= null && inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.STATE)){
			sb.append(" ,'01','Andra Pradesh' ");
		}else if(inputVo.getLocationType()!= null && inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			sb.append(" ,model3.dCode,model3.dName ");		
		}else if(inputVo.getLocationType()!= null && inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sb.append(" ,model4.constituencyCode,model4.contituencyName ");
			
		}else if(inputVo.getLocationType()!= null && inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
			sb.append(" ,model4.mCode,model4.mName, model4.dCode,model4.dName ");
		}
		
		if (sourceType != null && !sourceType.isEmpty()) {
			sbe.append(" and trim(model.sourceTypeName)=:sourceType");
		}
		 if(inputVo.getDistrictValue()!= null && inputVo.getDistrictValue().trim().length()>0){
	    	   sbe.append(" and model3.dCode =:districtvalue");
	       }

		if (inputVo.getFilterType() != null && inputVo.getFilterType().trim().length() > 0 && inputVo.getFilterValue() != null	&& inputVo.getFilterValue().trim().length() > 0) {
			
			//sbm.append(", RwsMinConstituencyView model4 ");
			
			
			if (inputVo.getFilterType().equalsIgnoreCase("district")) {
				sbe.append(" and trim(model3.dCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("constituency")) {
				sbe.append(" and trim(model4.constituencyCode) =:locationValue ");
			} else if (inputVo.getFilterType().equalsIgnoreCase("mandal")) {
				sbe.append(" and trim(model3.mCode) =:locationValue ");
			}
			 
		}
		
		if(inputVo.getLocationType()!= null && inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			sbe.append("  group by model3.dCode,model3.dName order by model3.dCode");
		}else if(inputVo.getLocationType()!= null && inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sbe.append(" group by model4.constituencyCode,model4.contituencyName order by model4.constituencyCode");
		}else if(inputVo.getLocationType()!= null && inputVo.getLocationType().trim().equalsIgnoreCase(IConstants.MANDAL)){
			sbe.append(" group by model4.mCode,model4.mName, model4.dCode,model4.dName order by model4.mCode, model4.dCode");
		}
		sb.append(sbm.toString()).append(sbe.toString());

		Query query = getSession().createQuery(sb.toString()); 
		
		if (inputVo.getFromDate() != null && inputVo.getToDate() != null) {
			query.setDate("fromDate", inputVo.getFromDate());
			query.setDate("toDate", inputVo.getToDate());
		}

		if (sourceType != null && !sourceType.isEmpty()) {
			query.setParameter("sourceType", sourceType.trim());
		}
		if (inputVo.getFilterValue() != null	&& inputVo.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVo.getFilterValue().trim());
		}
		if(inputVo.getDistrictValue()!= null && inputVo.getDistrictValue().trim().length()>0){
			query.setParameter("districtvalue", inputVo.getDistrictValue().trim());
	     }
		return query.list();
	}
}
