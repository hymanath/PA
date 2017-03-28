package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsPaperDAO;
import com.itgrids.partyanalyst.model.NewsPaper;

public class NewsPaperDAO extends GenericDaoHibernate<NewsPaper, Long> implements INewsPaperDAO {

	public NewsPaperDAO(){
		super(NewsPaper.class);
	}
	public List<Object[]> getNewPaperList(){
	   Query query = getSession().createQuery("select model.newsPaperId,model.newsPaper from NewsPaper model");
	  return query.list();
	}
}
