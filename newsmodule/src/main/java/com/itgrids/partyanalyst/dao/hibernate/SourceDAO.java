package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.model.Source;

public class SourceDAO extends GenericDaoHibernate<Source, Long> implements
		ISourceDAO {
	public SourceDAO() {
		super(Source.class);
	}
   public List<Object[]> getSourceDetails(){
	   return getHibernateTemplate().find("select model.sourceId,model.source from Source  model order by model.source ");
   }
   @SuppressWarnings("unchecked")
   public List<Object> getSourceIdBySource(String source)
   {
	   return getHibernateTemplate().find("select model.sourceId from Source  model where model.source =?",source);
   }
}
