package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionWorkDetailsDAO;
import com.itgrids.model.PetitionWorkDetails;

@Repository
public class PetitionWorkDetailsDAO extends GenericDaoHibernate<PetitionWorkDetails, Long> implements IPetitionWorkDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PetitionWorkDetailsDAO() {
		super(PetitionWorkDetails.class);

	}
	
	public List<Object[]> getWorkLocationDetailsByPetitionMemberId(List<Long> petitionMemberIds){
		
		   StringBuilder sb = new StringBuilder();
		   
			sb.append(" select model.petitionMemberId,model.workName,model.noOfWorks," +
					  " model.isPreviousPetition,model.previousPetitionRefNo,model.insertedTime " +
					  " from PetitionWorkDetails model  ");
			sb.append(" where model.petitionMemberId in(:petitionMemberIdsLst) ");
			
			   Query qry = getSession().createQuery(sb.toString());
			   
				if(petitionMemberIds != null && petitionMemberIds.size() >0){
					qry.setParameterList("petitionMemberIdsLst", petitionMemberIds);
				}
		
		return qry.list();
		
	}
}
