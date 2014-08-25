package com.itgrids.partyanalyst.dao.hibernate;
/**
 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
 * @since 19-AUG-2014
 */

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileDepartmentDAO;
import com.itgrids.partyanalyst.model.FileDepartment;

public class FileDepartmentDAO   extends GenericDaoHibernate<FileDepartment, Long>  implements IFileDepartmentDAO{
    public FileDepartmentDAO(){
    	super(FileDepartment.class);
    }
    
    public List<Object[]> getDepartmentsOfFile(Long fileId){
    	Query query = getSession().createQuery("select distinct model.department.departmentId," +
    			" model.department.departmentName from FileDepartment model" +
    			" where model.file.fileId = :fileId");
    	query.setParameter("fileId", fileId);
    	return query.list();
    }
    public void deleteFileDepartments(Long fileId){
		 Query query = getSession().createQuery("delete from FileDepartment model " +
		 		" where model.file.fileId =:fileId");
		 query.setParameter("fileId", fileId);
		 query.executeUpdate();
	}
       
}
