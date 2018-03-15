package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITourTypeDAO;
import com.itgrids.partyanalyst.model.TourType;

public class TourTypeDAO extends GenericDaoHibernate<TourType, Long> implements ITourTypeDAO {
   
	public TourTypeDAO(){
		super(TourType.class);
	}
	
	public List<Object[]> getAllTourTypes(){
		
		Query query = getSession().createQuery(" SELECT model.tourTypeId,model.tourType " +
				"  FROM TourType model  " +
				"  WHERE model.isDeleted ='N' and model.parentId is null ");
		
		return query.list();
	}
	public List<Object[]> getChildTourType(Long tourTypeParentId) {
		Query query = getSession().createQuery("SELECT model.tourTypeId,model.tourType " +
				"  FROM TourType model  " +
				"  WHERE model.isDeleted ='N' and model.parentId=:tourTypeParentId");
		query.setParameter("tourTypeParentId", tourTypeParentId);
		return query.list();
	}
}
