package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommitteIvrDistrictDetailDAO;
import com.itgrids.partyanalyst.model.CommitteIvrDistrictDetail;

public class CommitteIvrDistrictDetailDAO extends GenericDaoHibernate<CommitteIvrDistrictDetail, Long> implements ICommitteIvrDistrictDetailDAO{

	public CommitteIvrDistrictDetailDAO() {
		super(CommitteIvrDistrictDetail.class);
	}
	
	public List<Object[]> getDistrictWiseIvrDetails(Long campainId)
	{
		Query query = getSession().createQuery("select model.districtId,model.districtName,model.callStatus,model.optionId,model.count from CommitteIvrDistrictDetail model" +
				"  where model.campainId = :campainId");
		query.setParameter("campainId", campainId);
		return query.list();
	}

}
