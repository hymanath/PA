package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDesignationDAO;
import com.itgrids.partyanalyst.model.Designation;

public class DesignationDAO extends GenericDaoHibernate<Designation, Long> implements IDesignationDAO{

	public DesignationDAO() {
		super(Designation.class);
	}

	
    @SuppressWarnings("unchecked")
	public List<Object[]> getDesignationsList()
    {
      Query query = getSession().createQuery(" select model.designationId,model.designation from Designation model " +
      		" order by model.designation ");
      return query.list();
    }
	
}
