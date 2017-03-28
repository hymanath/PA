package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.model.PublicRepresentativeType;
import com.itgrids.partyanalyst.utils.IConstants;

public class PublicRepresentativeTypeDAO extends GenericDaoHibernate<PublicRepresentativeType, Long> implements IPublicRepresentativeTypeDAO{

	public PublicRepresentativeTypeDAO() {
		super(PublicRepresentativeType.class);
	}

	public List<Object[]> getAllPublicRepresentativeList(){
		Query query = getSession().createQuery("select model.publicRepresentativeTypeId," +
										" model.type" +
										" from PublicRepresentativeType model" +
										" order by model.orderNo");
		
		return query.list();
	}
	public List<Long> getIds(List<Long> representativeIds){
		Query query = getSession().createQuery(" select PRT.publicRepresentativeTypeId from PublicRepresentativeType PRT where PRT.publicRepresentativeTypeId not in (:representativeIds)");
		query.setParameterList("representativeIds", representativeIds);
		return query.list();
	}
	public List<Object[]> getPublicRepresentativeInfoList(){
		Query query = getSession().createQuery("select model.publicRepresentativeTypeId," +
				" model.type" +
				" from PublicRepresentativeType model where model.publicRepresentativeTypeId in ("+IConstants.REQUIRED_PUBLIC_REPRESENTATIVE_TYPE_IDS+")" +
				" order by model.orderNo");

		return query.list();
	}
}
