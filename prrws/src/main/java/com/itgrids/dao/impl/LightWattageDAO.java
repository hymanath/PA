package com.itgrids.dao.impl;



import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightWattageDAO;
import com.itgrids.model.LightWattage;
@Repository
public class LightWattageDAO extends GenericDaoHibernate<LightWattage ,Long> implements ILightWattageDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	public LightWattageDAO()
	{
	super(LightWattage.class);

}
	
}
