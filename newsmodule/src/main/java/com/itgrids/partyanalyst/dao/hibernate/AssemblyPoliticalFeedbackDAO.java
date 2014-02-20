package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAssemblyPoliticalFeedbackDAO;
import com.itgrids.partyanalyst.model.AssemblyPoliticalFeedback;

public class AssemblyPoliticalFeedbackDAO extends GenericDaoHibernate<AssemblyPoliticalFeedback, Long> implements IAssemblyPoliticalFeedbackDAO{

	public AssemblyPoliticalFeedbackDAO() {
		super(AssemblyPoliticalFeedback.class);
	}

	
	public List<Object[]> getAssemblyPoliticalFeedBacks(List<Long> pfbIds)
	{
		Query query = getSession().createQuery("select model.parlimentPoliticalFeedback.parlimentPoliticalFeedbackId ," +
				" model.constituency.constituencyId ,model.constituency.name ,model.impNews ," +
				" model.cmPoliticalFeedback , model.otherPoliticalBack ,model.impSource , model.cmFeedBackSource ," +
				" model.otherFeedBackSource  from AssemblyPoliticalFeedback model where " +
				"   model.parlimentPoliticalFeedback.parlimentPoliticalFeedbackId in (:pfbIds)");
		query.setParameterList("pfbIds", pfbIds);

		return query.list();
	}
}
