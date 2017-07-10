package com.rwss.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsMinAssetViewDAO;
import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinAssetView;

@Repository
public class RwsMinAssetViewDAO extends GenericDaoHibernate<RwsMinAssetView,String> implements IRwsMinAssetViewDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public RwsMinAssetViewDAO() {
		super(RwsMinAssetView.class);
	}
	
	@Override
	public List<String> getAvliableAssets() {
		   Query query = getSession().createQuery("select distinct model.assetType from RwsMinAssetView model");	
		return query.list();
	} 
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssetTypeCountDetails(InputVO inputVO){
		
	   StringBuilder queryStr = new StringBuilder();
	  
	   queryStr.append("select model.assetType,count(distinct model.assetCode)");
	   if(inputVO.getLocationType().equalsIgnoreCase("district")){
		   queryStr.append(" ,model3.dCode,model3.dName");
	   }else if (inputVO.getLocationType().equalsIgnoreCase("constituency")) {
		   queryStr.append(" ,model4.constituencyCode,model4.contituencyName");
	   }else if (inputVO.getLocationType().equalsIgnoreCase("mandal")) {
		   queryStr.append(" ,model3.mCode,model3.mName,model3.dCode,model3.dName");
	   }
	   
	   queryStr.append(" from RwsMinAssetView model ,RwsMinAssetNabInkView model2," +
				       " RwsMinHabView model3,RwsMinConstituencyView model4 " +
				       " where " +
				       " model.assetCode=model2.assetCode and " +
				       " model2.habCode=model3.panchCode and " +
				       " model3.mCode=model4.mCode and " +
				       " model3.dCode=model4.dCode");
	   
	   
	   if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
		   queryStr.append(" and model.dateCreated between :fromDate and :toDate ");
	   }
	   if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0 && inputVO.getFilterValue() != null
				&& inputVO.getFilterValue().trim().length() > 0) {
				if (inputVO.getFilterType().equalsIgnoreCase("district")) {
					queryStr.append(" and trim(model4.dCode) =:locationValue ");
				} else if (inputVO.getFilterType().equalsIgnoreCase("constituency")) {
					queryStr.append(" and trim(model4.constituencyCode) =:locationValue ");
				}  else if (inputVO.getFilterType().equalsIgnoreCase("mandal")) {
					queryStr.append(" and trim(model4.mCode) =:locationValue  ");
				} if(inputVO.getDistrictValue() !=null && inputVO.getDistrictValue().trim().length()>0){
					queryStr.append(" and trim(model4.dCode) =:districtValue");
				}
	  }
	   queryStr.append(" group by model.assetType ");
	 
	   if(inputVO.getLocationType()!= null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
		   queryStr.append(" ,model3.dCode,model3.dName order by model3.dCode ");
	   }else if (inputVO.getLocationType()!= null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")) {
		   queryStr.append(" ,model4.constituencyCode,model4.contituencyName order by model4.constituencyCode ");
	   }else if (inputVO.getLocationType()!= null &&  inputVO.getLocationType().trim().equalsIgnoreCase("mandal")) {
		   queryStr.append(" ,model3.mCode,model3.mName,model3.dCode,model3.dName order by model3.mCode,model3.dCode");
	   }
	   Query query = getSession().createQuery(queryStr.toString());	
	   
	   if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
		   query.setDate("fromDate", inputVO.getFromDate());
		   query.setDate("toDate", inputVO.getToDate());
	   };
	   
	   if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
		}
	   if(inputVO.getDistrictValue() !=null && inputVO.getDistrictValue().trim().length()>0){
		   query.setParameter("districtValue",inputVO.getDistrictValue());
		}
	   return query.list();
	}
	@Override
	public List<Object[]> getAssestDetailsByAssetType(InputVO inputVO){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select distinct " +
		   				   " model4.dCode,model4.dName," +
		   				   " model4.constituencyCode,model4.contituencyName," +
		   				   " model4.mCode,model4.mName," +
		   				   " model3.panchCode,model3.panchName," +
		   				   " model.assetCode,model.assetName," +
		   				   " model.assetType,model.status,model.assetCost " +
		   				   " from " +
		   				   " RwsMinAssetView model,RwsMinAssetNabInkView model2, "+
		   			       " RwsMinHabView model3,RwsMinConstituencyView model4 " +
					       " where " +
					       " model.assetCode=model2.assetCode and " +
					       " model2.habCode=model3.panchCode and " +
					       " model3.mCode=model4.mCode and " +
					       " model3.dCode=model4.dCode ");
		   
		   if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
			   queryStr.append(" and model.dateCreated between :fromDate and :toDate");
		   }
		   if(inputVO.getAssetType() != null && inputVO.getAssetType().length()>0){
			   queryStr.append(" and trim(model.assetType)=:assetType");
		   }
		   if (inputVO.getDistrictValue() != null && inputVO.getDistrictValue().trim().length() > 0) {
				queryStr.append(" and trim(model4.dCode) =:districtValue ");
			}
		   
		   if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0 && inputVO.getFilterValue() != null
					&& inputVO.getFilterValue().trim().length() > 0) {
					if (inputVO.getFilterType().equalsIgnoreCase("district")) {
						queryStr.append(" and trim(model4.dCode) =:locationValue ");
					} else if (inputVO.getFilterType().equalsIgnoreCase("constituency")) {
						queryStr.append(" and trim(model4.constituencyCode) =:locationValue ");
					}else if (inputVO.getFilterType().equalsIgnoreCase("mandal")) {
						queryStr.append(" and trim(model4.mCode) =:locationValue ");
					}
		  }
		  
		   
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("toDate", inputVO.getToDate());
		};

		if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
		}
		if (inputVO.getAssetType() != null && inputVO.getAssetType().length() > 0) {
			query.setParameter("assetType", inputVO.getAssetType().trim());
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


}
