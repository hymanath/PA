package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;

public class TdpCadreFamilyDetailsDAO extends GenericDaoHibernate<TdpCadreFamilyDetails, Long> implements ITdpCadreFamilyDetailsDAO{

	public TdpCadreFamilyDetailsDAO() {
		super(TdpCadreFamilyDetails.class);
	}

	
	public Integer inActiveCadreFamilyDetailsById(List<Long> tdpCadreIdList)
	{
		Query query = getSession().createQuery("update TdpCadreFamilyDetails model set model.isDeleted = 'H' where model.tdpCadreId in (:tdpCadreIdList) and model.isDeleted = 'N' ");
		query.setParameterList("tdpCadreIdList", tdpCadreIdList);
		Integer count = query.executeUpdate();
		
		return count;
	}
	
	
	public List<Object[]> getCadreFamilyDetailsBytdpCadreId(Long tdpCadreId)
	{
		//0voterId,1educationId,2occupationId,3voterName,4age,5gender,6relationId,7voterIdCardNo,8qualification,9occupation,10relation,11relativeName
		Query query = getSession().createQuery("select model.voter.voterId, model.educationId,model.occupationId,model.voterName,model.age,model.gender,model.familyRelationId,model.voter.voterIDCardNo,model.education.qualification," +
				"  model.occupation.occupation,model.voterRelation.description,model.voter.relativeName from TdpCadreFamilyDetails model where model.tdpCadreId = :tdpCadreId " +
				" and model.isDeleted = 'N' ");
		query.setParameter("tdpCadreId", tdpCadreId);
		
		return query.list();
	}
	
}
