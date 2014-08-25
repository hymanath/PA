package com.itgrids.partyanalyst.dao.hibernate;

/**
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsTypeDAO;
import com.itgrids.partyanalyst.model.NewsType;

public class NewsTypeDAO   extends GenericDaoHibernate<NewsType, Long>  implements INewsTypeDAO{
    public NewsTypeDAO(){
    	super(NewsType.class);
    }
    
    public List<Object[]> getAllNewsTypes(){
    	Query query = getSession().createQuery(" select model.newsTypeId," +
    			" model.newsTypeName from NewsType model");
    	return query.list();
    }
    
    
}
