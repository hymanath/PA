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
}