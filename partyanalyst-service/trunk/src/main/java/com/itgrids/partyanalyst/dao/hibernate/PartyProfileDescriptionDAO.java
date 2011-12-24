package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.hibernate.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.model.PartyProfileDescription;

  public class PartyProfileDescriptionDAO extends GenericDaoHibernate<PartyProfileDescription, Long> 
                                             implements IPartyProfileDescriptionDAO {

  public PartyProfileDescriptionDAO() {
     super(PartyProfileDescription.class);
      }
  
  @SuppressWarnings("unchecked")
	public List<Object> getPartyProfileDescription(Long partyId){		
		return getHibernateTemplate().find("select model.description from PartyProfileDescription model where model.party.partyId=? order by model.orderNo asc",partyId);
	}
  
  public List<Object> getMaxOrderNo(Long partyId){
		
		return getHibernateTemplate().find("select max(model.orderNo) from PartyProfileDescription model where model.party.partyId=?",partyId);
	}
  
  @SuppressWarnings("unchecked")
  public List<Object[]> getPartyProfileInfo(Long partyId){		
		return getHibernateTemplate().find("select model.orderNo , model.description , model.partyProfileDescriptionId from PartyProfileDescription model where model.party.partyId=?",partyId);
	}
  
  public Integer deletePartyProfileDescriptionById(Long profDescId) {
		Query queryObject=getSession().createQuery("DELETE FROM PartyProfileDescription model where model.partyProfileDescriptionId=?");
		queryObject.setParameter(0, profDescId);
		return queryObject.executeUpdate();
	}
	
	
}



