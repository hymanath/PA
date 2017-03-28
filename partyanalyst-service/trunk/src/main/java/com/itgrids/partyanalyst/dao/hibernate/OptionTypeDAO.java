package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IOptionTypeDAO;
import com.itgrids.partyanalyst.model.OptionType;

public class OptionTypeDAO extends GenericDaoHibernate<OptionType, Long> implements IOptionTypeDAO{

	public OptionTypeDAO() {
		super(OptionType.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getOptionTypes(){
		Query query = getSession().createQuery("select model.optionTypeId,model.optionType from OptionType model");
		return query.list();	
	}
}
