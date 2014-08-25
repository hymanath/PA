package com.itgrids.partyanalyst.dao.hibernate;
/**
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileNewsTypeDAO;
import com.itgrids.partyanalyst.model.FileNewsType;

public class FileNewsTypeDAO   extends GenericDaoHibernate<FileNewsType, Long>  implements IFileNewsTypeDAO{
    public FileNewsTypeDAO(){
    	super(FileNewsType.class);
    }
    
    public List<Object[]> getNewsTypes(Long fileId){
    	Query query = getSession().createQuery("select distinct model.newsType.newsTypeId," +
    			" model.newsType.newsTypeName from FileNewsType model" +
    			" where model.file.fileId = :fileId ");
    	
    	query.setParameter("fileId", fileId);    	
    	return query.list();
    }
    
    public void deleteFileNewsTypes(Long fileId){
		 Query query = getSession().createQuery("delete from FileNewsType model " +
		 		" where model.file.fileId =:fileId");
		 query.setParameter("fileId", fileId);
		 query.executeUpdate();
	}
       
}
