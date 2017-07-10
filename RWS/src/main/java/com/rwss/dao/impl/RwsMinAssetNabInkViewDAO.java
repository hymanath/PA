package com.rwss.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsMinAssetNabInkViewDAO;
import com.rwss.model.RwsMinAssetNabInkView;

@Repository
public class RwsMinAssetNabInkViewDAO extends GenericDaoHibernate<RwsMinAssetNabInkView,String> implements IRwsMinAssetNabInkViewDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public RwsMinAssetNabInkViewDAO() {
		super(RwsMinAssetNabInkView.class);

	}
}
