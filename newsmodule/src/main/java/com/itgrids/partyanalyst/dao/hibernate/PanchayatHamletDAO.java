package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public class PanchayatHamletDAO extends GenericDaoHibernate<PanchayatHamlet,Long> implements IPanchayatHamletDAO{

	public PanchayatHamletDAO()
	{
		super(PanchayatHamlet.class);
	}/*
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletsOfAPanchayat(Long panchayatId)
	{
		return getHibernateTemplate().find("select model.hamlet.hamletId,model.hamlet.hamletName from PanchayatHamlet model where model.panchayat.panchayatId = ? order by model.hamlet.hamletName",panchayatId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCount(Long panchayatId)
	{
		return getHibernateTemplate().find("select");
	}
	
	public List<Panchayat> getPanchayatByHamletId(Long hamletId){
		
		
		return getHibernateTemplate().find("select model.panchayat from PanchayatHamlet model where model.hamlet.hamletId = ? ",hamletId);

		
	}
	
	
	public List<Long> getHamletsOfPanchayitis(List<Long> panchayitIds){
		
		
		Query query = getSession().createQuery("select model.hamlet.hamletId from PanchayatHamlet model where model.panchayat.panchayatId in(:panchayitIds)");
		
		query.setParameterList("panchayitIds", panchayitIds);
		
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object> getHamletsCountOfAPanchayat(Long panchayatId)
	{
		return getHibernateTemplate().find("select count(distinct model.hamlet.hamletId) from PanchayatHamlet model where model.panchayat.panchayatId = ?   ",panchayatId);
	}*/

}
