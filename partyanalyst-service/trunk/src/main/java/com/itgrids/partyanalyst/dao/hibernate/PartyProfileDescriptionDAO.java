package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.model.PartyProfileDescription;

  public class PartyProfileDescriptionDAO extends GenericDaoHibernate<PartyProfileDescription, Long> 
                                             implements IPartyProfileDescriptionDAO {

  public PartyProfileDescriptionDAO() {
     super(PartyProfileDescription.class);
      }
      
  
}



