package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstiCasteGroupPercDAO;
import com.itgrids.partyanalyst.model.ConstiCasteGroupPerc;

public class ConstiCasteGroupPercDAO extends GenericDaoHibernate<ConstiCasteGroupPerc, Long> implements IConstiCasteGroupPercDAO{

	public ConstiCasteGroupPercDAO() {
		super(ConstiCasteGroupPerc.class);
		// TODO Auto-generated constructor stub
	}

	
	public List<Object[]> getData()
	{
		Query query = getSession().createQuery("select model.constiCasteGroupPercId,model.casteGroup from ConstiCasteGroupPerc model");
		
		return query.list();
	}
	

	
	public List<Object[]> getConstituencyCastePer()
	{
		Query query = getSession().createQuery("select CCG.constituency,CCG.casteGroup.casteGroupId,CCG.casteGroup.casteGroupName,CCG.groupPerc from ConstiCasteGroupPerc CCG" +
		
				" order by CCG.groupPerc desc");
		
		return query.list();	
	}
	
	public List<Object[]> getConstituencyCastePerByConstiId(Long constituencyId)
	{
		//0 casteGroupId, 1 casteGroupName,2 groupPerc,3 rank
		Query query = getSession().createQuery("select CCG.casteGroup.casteGroupId,CCG.casteGroup.casteGroupName,CCG.groupPerc,CCG.rank from ConstiCasteGroupPerc CCG where CCG.constituency =:constituencyId " +
		
				" order by CCG.rank ");
		query.setParameter("constituencyId",constituencyId);
		return query.list();	
	}
	
}
