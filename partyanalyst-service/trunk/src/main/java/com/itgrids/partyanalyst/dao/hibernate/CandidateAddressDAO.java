package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.ICandidateAddressDAO;
import com.itgrids.partyanalyst.model.CandidateAddress;



public class CandidateAddressDAO extends GenericDaoHibernate<CandidateAddress, Long> implements ICandidateAddressDAO{
	
	public CandidateAddressDAO() {
		super(CandidateAddress.class);
	}
	
	public  List getCandidateAddressDetailsByCandidateId(Long candidateId){
		

		Query query = getSession().createQuery("select model.addressType.addressTypeId,model.address.addressId from CandidateAddress model where model.candidate.candidateId=?");
		query.setParameter(0,candidateId);
		return query.list();
	
		
	}
	
	public List<CandidateAddress> getCandidateAddressDetails(Long candidateId){
		Query query = getSession().createQuery(" from CandidateAddress model where model.candidate.candidateId = ?");
		query.setParameter(0,candidateId);
		return query.list();
		
	}
	
	public Object getCandidateAddressByAddressId(Long addressId){
		Query query = getSession().createQuery("from CandidateAddress model where model.address. addressId= ?");
		query.setParameter(0,addressId);
		return query.uniqueResult();
		
		
		
		
	}


}
