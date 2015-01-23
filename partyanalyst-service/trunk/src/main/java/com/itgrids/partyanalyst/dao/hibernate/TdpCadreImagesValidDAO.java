package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreImagesValidDAO;
import com.itgrids.partyanalyst.model.TdpCadreImagesValid;

public class TdpCadreImagesValidDAO extends GenericDaoHibernate<TdpCadreImagesValid, Long> implements ITdpCadreImagesValidDAO{

	public TdpCadreImagesValidDAO() 
	{
		super(TdpCadreImagesValid.class);
	}
	
	public List<TdpCadreImagesValid> checkForExists(Long tdpCadreId)
	{
		Query query = getSession().createQuery("select model from TdpCadreImagesValid model where model.tdpCadreId = :tdpCadreId");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
		
	}

	public List<Object[]> getValidOrInValidDetails(Long districtId,	Long constituencyId, String type) {
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("select model.tdpCadre.tdpCadreId,model.tdpCadre.image,model.tdpCadre.firstname from TdpCadreImagesValid model where model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear= 2014 and model.tdpCadre.image is not null and model.tdpCadre.photoType = 'NEW' and model.tdpCadre.cardNumber is null");
		if(districtId != null && districtId > 0)
		  {
			  queryString.append(" and model.tdpCadre.userAddress.district.districtId = :districtId");
		  }
		  if(constituencyId != null && constituencyId > 0)
		  {
			  queryString.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :constituencyId");
		  }
		  queryString.append(" and model.status = :type");
		Query query = getSession().createQuery(queryString.toString());
		if(districtId != null && districtId > 0)
		{
		  query.setParameter("districtId", districtId);
		}
		if(constituencyId != null && constituencyId > 0)
		{
		  query.setParameter("constituencyId", constituencyId);
		}
		query.setParameter("type", type);
		return query.list();
	}
	
	/*public List<Long> getExistinceTdpCadre(List<Long> tdpCadreIds)
	{
		Query query = getSession().createQuery("select model.tdpCadreId from TdpCadreImagesValid model where model.tdpCadreId in (:tdpCadreIds)");
		
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
	}*/

}
