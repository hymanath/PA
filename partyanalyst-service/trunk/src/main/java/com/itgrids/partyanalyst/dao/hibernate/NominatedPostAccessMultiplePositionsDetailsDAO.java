package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostAccessMultiplePositionsDetailsDAO;
import com.itgrids.partyanalyst.model.NominatedPostAccessMultiplePositionsDetails;

public class NominatedPostAccessMultiplePositionsDetailsDAO extends GenericDaoHibernate<NominatedPostAccessMultiplePositionsDetails, Long> implements
		INominatedPostAccessMultiplePositionsDetailsDAO {

	public NominatedPostAccessMultiplePositionsDetailsDAO() {
		super(NominatedPostAccessMultiplePositionsDetails.class);
		
	}
	
	public List<Object[]> getDepartMentIdsByBoardIdsList(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.departmentsId,model.boardId from NominatedPostAccessMultiplePositionsDetails model where model.isDeleted ='N'");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
		
	}
}
