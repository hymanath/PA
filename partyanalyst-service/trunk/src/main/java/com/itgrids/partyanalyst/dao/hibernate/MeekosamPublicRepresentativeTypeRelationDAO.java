package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamPublicRepresentativeTypeRelationDAO;
import com.itgrids.partyanalyst.model.MeekosamPublicRepresentativeTypeRelation;

public class MeekosamPublicRepresentativeTypeRelationDAO extends GenericDaoHibernate<MeekosamPublicRepresentativeTypeRelation, Long> implements IMeekosamPublicRepresentativeTypeRelationDAO {
	public MeekosamPublicRepresentativeTypeRelationDAO() {
		super(MeekosamPublicRepresentativeTypeRelation.class);
	}
	
	public List<Object[]> getReferalNamesByTypeAndDist(Long typeId,Long districtId){
		Query query = getSession().createQuery("select distinct model.meekosamPublicRepresentative.meekosamPublicRepresentativeId," +
												" model.meekosamPublicRepresentative.name," +
												" model.meekosamPublicRepresentativeTypeRelationId" +
												" from MeekosamPublicRepresentativeTypeRelation model" +
												" where model.meekosamPublicRepresentativeType.meekosamPublicRepresentativeTypeId = :typeId" +
												" and model.isDeleted = 'N' and model.userAddress.district.districtId = :districtId");
		query.setParameter("typeId", typeId);
		query.setParameter("districtId", districtId);
		return query.list();
	}
	
	public List<Long> getMeekosamPublicRepresentativeTypeRelationId(Long typeId,Long districtId,Long nameId){
		Query query = getSession().createQuery("select distinct model.meekosamPublicRepresentativeTypeRelationId" +
												" from MeekosamPublicRepresentativeTypeRelation model" +
												" where model.meekosamPublicRepresentativeType.meekosamPublicRepresentativeTypeId = :typeId" +
												" and model.isDeleted = 'N' and model.userAddress.district.districtId = :districtId" +
												" and model.meekosamPublicRepresentative.meekosamPublicRepresentativeId = :nameId");
		query.setParameter("typeId", typeId);
		query.setParameter("districtId", districtId);
		query.setParameter("nameId", nameId);
		return query.list();
	}
}
