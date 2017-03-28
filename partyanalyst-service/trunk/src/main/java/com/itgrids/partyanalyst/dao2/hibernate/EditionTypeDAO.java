package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEditionTypeDAO;
import com.itgrids.partyanalyst.model.EditionType;

public class EditionTypeDAO extends GenericDaoHibernate<EditionType, Long>  implements IEditionTypeDAO {
	public EditionTypeDAO(){
		super(EditionType.class);
	}
	public List<Object[]> getEditionTypeList(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.editionTypeId, model.editionType from EditionType model order by model.editionTypeId");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}


}
