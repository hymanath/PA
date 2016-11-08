package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITabUserInfoDAO;
import com.itgrids.partyanalyst.model.TabUserInfo;

public class TabUserInfoDAO extends GenericDaoHibernate<TabUserInfo, Long> implements ITabUserInfoDAO{

	public TabUserInfoDAO() {
		super(TabUserInfo.class);      
	}
	
	public List<Object[]> getTotalTabUserImages(){
		Query query = getSession().createQuery("select model.tabUserInfoId," +
								" model.imageStr,model.imgPath" +
								" from TabUserInfo model");
		return query.list();
	}
	public List<Object[]> getSurveyUserBasicDetailsBySurveyUserIds(List<Long> cadreSurveyUserIds){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.cadreSurveyUserId,model.mobileNo,model.name from TabUserInfo model ");
		 if(cadreSurveyUserIds != null && cadreSurveyUserIds.size() > 0){
			 queryStr.append(" where model.cadreSurveyUserId in (:cadreSurveyUserIds)");
		 }
		 Query query = getSession().createQuery(queryStr.toString());
		 if(cadreSurveyUserIds != null && cadreSurveyUserIds.size() > 0){
		  query.setParameterList("cadreSurveyUserIds", cadreSurveyUserIds);
		 }
		 return query.list();
	}
}