package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionAssignedOfficerDAO;
import com.itgrids.model.PmPetitionAssignedOfficer;

@Repository
public class PmPetitionAssignedOfficerDAO extends GenericDaoHibernate<PmPetitionAssignedOfficer, Long> implements IPmPetitionAssignedOfficerDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmPetitionAssignedOfficerDAO() {
		super(PmPetitionAssignedOfficer.class);
		
	}
	public List<Object[]> getAssignedPetitionforPetitionDeptDesignationOfficer(List<Long> pmDeptDesignationOfficerIdsList){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.petitionId,model.pmSubWorkDetailsId from PmPetitionAssignedOfficer model where model.isDeleted='N' and model.pmDepartmentDesignationOfficerId in (:pmDeptDesignationOfficerIdsList)  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("pmDeptDesignationOfficerIdsList", pmDeptDesignationOfficerIdsList);
		return query.list();
	}
	
	public List<Long> getAssingedOfficerStatusIdsList(List<Long> deptDesignationIdsList){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmStatusId from PmPetitionAssignedOfficer model where model.isDeleted='N' and " +
				" model.pmDepartmentDesignationId in (:deptDesignationIdsList) group by model.pmStatusId order by model.insertedTime desc ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("deptDesignationIdsList", deptDesignationIdsList);
		return query.list();
	}
}
