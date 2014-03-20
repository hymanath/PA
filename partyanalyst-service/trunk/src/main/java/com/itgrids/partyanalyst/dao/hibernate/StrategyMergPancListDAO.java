package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStrategyMergPancListDAO;
import com.itgrids.partyanalyst.model.StrategyMergPancList;

public class StrategyMergPancListDAO extends GenericDaoHibernate<StrategyMergPancList,Long> implements IStrategyMergPancListDAO {

	public StrategyMergPancListDAO(){
		super(StrategyMergPancList.class);
	}
   
	public List<Object[]> getPanchayatsToMerge(Long constituencyId,String type,Long typeValue){
		
		Query query = getSession().createQuery("select model.panchayat.panchayatId,model.strategyMergePanchayat.panchayat.panchayatId from StrategyMergPancList model where " +
				" model.strategyMergePanchayat.constituency.constituencyId =:constituencyId and model.strategyMergePanchayat.type =:type and model.strategyMergePanchayat.typeValue =:typeValue " +
				" and model.panchayat.panchayatId != model.strategyMergePanchayat.panchayat.panchayatId ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("type", type);
		query.setParameter("typeValue", typeValue);
		return query.list();
	}
}
