package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.model.FileGallary;
public class FileGallaryDAO extends GenericDaoHibernate<FileGallary, Long> implements IFileGallaryDAO{
	
	public FileGallaryDAO(){
		super(FileGallary.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStartingRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath " +
				" from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
				" order by model.file.fileId asc ");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");
		query.setMaxResults(1);
		
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath " +
				" from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
				" order by model.file.fileId asc ");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");				
		return query.list(); 
	}
}
