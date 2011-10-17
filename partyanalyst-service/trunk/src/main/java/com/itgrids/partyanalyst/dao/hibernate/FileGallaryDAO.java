package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dto.FileVO;
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
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription  " +
				" from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
				" order by model.file.fileId asc ");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");				
		return query.list(); 
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getNewsRecordsBySearchCriteria(FileVO fileVO,String type){
		StringBuilder query = new StringBuilder();
		query.append("select model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.regionScopes.scope,model.file.regionScopes.regionScopesId ,model.file.locationValue " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate ");
		if(fileVO.getLocationScope()!= null)
			query.append("  and model.file.regionScopes.regionScopesId = :locationScope");
		if(fileVO.getLocation()!= null)
			query.append("  and model.file.locationValue = :location");	
		if(fileVO.getKeywords()!= null && fileVO.getKeywords().trim().length()>0)
			query.append("  and model.file.keywords like '%"+fileVO.getKeywords()+"%' ");	
		query.append("  order by model.file.fileId asc");
		
        Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("candidateId", fileVO.getCandidateId());
		queryObject.setString("type", type);
		queryObject.setString("isDelete", "false");
		queryObject.setString("isPrivate", "false");
		if(fileVO.getLocationScope()!= null)
		   queryObject.setLong("locationScope", fileVO.getLocationScope());
		if(fileVO.getLocation()!= null)
		   queryObject.setLong("location", fileVO.getLocation());
		 return	queryObject.list();
	}
}
