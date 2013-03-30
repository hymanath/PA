package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dao.columns.enums.WardColumnNames;
import com.itgrids.partyanalyst.model.Ward;

public class WardDAO extends GenericDaoHibernate<Ward, Long> implements
		IWardDAO {

	public WardDAO() {
		super(Ward.class);
	}
	
	public List<Ward> findByProperty(WardColumnNames propertyName, Object value){

		return getHibernateTemplate().find("from Ward where " + propertyName.getValue() + "=?", value);
	}

	public List<Ward> findByWardName(Object wardName){
		return findByProperty(WardColumnNames.WARD_NAME, wardName);
	}

	public List<Ward> findByWardCode(Object wardCode){
		return findByProperty(WardColumnNames.WARD_CODE, wardCode);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ward> findByWardNameAndTownship(String wardName,Long townId){
		
		Object[] params = {wardName,townId};
		return getHibernateTemplate().find("from Ward model where model.wardName = ? and model.township.townshipId = ?",params);
	}
	/**
	 * @author anil
	 * this is  method  usefull to get  wards along  with wardsnames 
	 */
	@SuppressWarnings("unchecked")
	  public List<Object[]> findByWardsByAssemblyLocalElectionBodyId(Long  alebi,Long publicationId ){
		 	
			Object[] params = {alebi,publicationId};
		//  System.out.println(getSession());
		  List<Object[]> li = getHibernateTemplate().find("select distinct model.localBodyWard.constituencyId,concat(le.wardName, ' (' ,model.localBodyWard.name, ')') " +
		  		    "from Booth model ,LocalElectionBodyWard le " +
		 			" join model.localBody l join l.assemblyLocalElectionBody a where a.assemblyLocalElectionBodyId = ? " +
		 			" and model.publicationDate.publicationDateId =? and model.localBodyWard.constituencyId = le.constituency.constituencyId order by le.wardName  ",params );
			return 	li;
	 }
	  public List<Object[]> findByWardsByAssemblyLocalElectionBodyId1(Long  alebi,Long publicationId ){
		 	
			Object[] params = {alebi,publicationId};
		//  System.out.println(getSession());
		  List<Object[]> li = getHibernateTemplate().find(" select distinct model.constituencyId,model.name " +
		  		    "from Booth model ,LocalElectionBodyWard le " +
		 			" join model.localBody l join l.assemblyLocalElectionBody a where a.assemblyLocalElectionBodyId = ? " +
		 			" and model.publicationDate.publicationDateId =? and model.localBodyWard.constituencyId = le.constituency.constituencyId order by le.wardName  ",params );
			return 	li;
	 }
	 /* public List<Object[]> getLocalitiesForBooth(Long id)
	  {
		  List<Object[]> li = getHibernateTemplate().find(" select distinct l.localityId , l.name from Locality l1 " +
		  		    " join model.localBody l join Booth b l.assemblyLocalElectionBody a where a.assemblyLocalElectionBodyId = ? " +
		 			" and model.publicationDate.publicationDateId =? and model.localBodyWard.constituencyId = le.constituency.constituencyId order by le.wardName  ",params );
			return 	li;
		  
		  
	  }*/
	  
	  @SuppressWarnings("unchecked")
	  public List<Object[]> getWardsListByLocalEleBodyIdAndConstituencyId(Long localEleBodyId, Long publicationDateId, Long constituencyId){
		 	
			Object[] params = {localEleBodyId,publicationDateId,constituencyId};
		
		  List<Object[]> li = getHibernateTemplate().find("select distinct model.localBodyWard.constituencyId,concat(le.wardName, ' (' ,model.localBodyWard.name, ')') " +
		  		    "from Booth model ,LocalElectionBodyWard le " +
		 			" join model.localBody l join l.assemblyLocalElectionBody a where a.assemblyLocalElectionBodyId = ? " +
		 			" and model.publicationDate.publicationDateId =? and model.localBodyWard.constituencyId = le.constituency.constituencyId and model.constituency.constituencyId = ? order by le.wardName  ",params );
			return 	li;
		  
		  
	 }
	  public List<Object[]> findWardsByAssemblyLocalElectionBodyId(Long  alebi,Long publicationId ){
		  Object[] params = {alebi,publicationId};
		  List<Object[]> li = getHibernateTemplate().find(" ");
		  return 	li;
	  }
}
