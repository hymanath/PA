package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeConditionDAO;
import com.itgrids.partyanalyst.model.BoothInchargeCondition;

public class BoothInchargeConditionDAO extends GenericDaoHibernate<BoothInchargeCondition, Long> implements IBoothInchargeConditionDAO{

	public BoothInchargeConditionDAO() {
		super(BoothInchargeCondition.class);
	}
	public List<Long> getboothInchargeConditionIdByRange(Long minValue, Long maxValue){
		StringBuilder str = new StringBuilder();
		str.append("select model.boothInchargeConditionId from BoothInchargeCondition model where :minValue >= model.minValue  and :minValue <= model.maxValue ");
		Query query =  getSession().createQuery(str.toString());
		query.setParameter("minValue", minValue);
		return query.list();
	}
}
