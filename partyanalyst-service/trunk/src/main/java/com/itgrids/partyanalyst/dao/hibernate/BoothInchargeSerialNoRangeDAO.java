package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeSerialNoRangeDAO;
import com.itgrids.partyanalyst.model.BoothInchargeSerialNoRange;

public class BoothInchargeSerialNoRangeDAO extends GenericDaoHibernate<BoothInchargeSerialNoRange, Long> implements IBoothInchargeSerialNoRangeDAO {

	public BoothInchargeSerialNoRangeDAO() {
		super(BoothInchargeSerialNoRange.class);
		
	}

   public List<Object[]> getAllInchargeSerialNoRange(){
	   Query query = getSession().createQuery("select model.boothInchargeSerialNoRangeId,model.range from BoothInchargeSerialNoRange model where model.isDeleted='N' ");
	   return query.list();
   }

}
