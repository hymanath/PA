package com.itgrids.dao.impl;

import java.util.List;
import java.util.Set;

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
	
	public List<Object[]> getWorkLocationDetailsByPetitionMemberId(Set<Long> petitionMemberIds){
		if(petitionMemberIds != null && petitionMemberIds.size()>0){
			   StringBuilder sb = new StringBuilder();
				sb.append(" select model.petitionMemberId,model.workName,model.noOfWorks," +
						  " model.isPreviousPetition,model.previousPetitionRefNo,model.insertedTime,model.subject," +
						  " petitionDepartment.petitionDepartmentId,petitionDepartment.departmentName ,petitionSubject.petitionSubjectId,petitionSubject.subject,");
				sb.append(" model.costEstimation,model.projectDescription,model.petitionWorkDetailsId,petitionSubSubject.petitionSubjectId,petitionSubSubject.subject, " +//15
						" petitionLead.lead, petitionBriefLead.breifLead,petitionStatus.description,petitionGrant.grantUnder," +
						" model.remarks");//20
				sb.append(" from PetitionWorkDetails model " );
				sb.append(" left join model.petitionDepartment petitionDepartment  ");
				sb.append(" left join model.petitionSubject petitionSubject  ");
				sb.append(" left join model.petitionSubSubject petitionSubSubject  ");
				sb.append(" left join model.petitionLead petitionLead  ");
				sb.append(" left join model.petitionBriefLead petitionBriefLead  ");
				sb.append(" left join model.petitionStatus petitionStatus  ");
				sb.append(" left join model.petitionGrant petitionGrant  ");
				
				sb.append(" where model.petitionMemberId in(:petitionMemberIdsLst) ");
			   Query qry = getSession().createQuery(sb.toString());
				if(petitionMemberIds != null && petitionMemberIds.size() >0){
					qry.setParameterList("petitionMemberIdsLst", petitionMemberIds);
				}
				return qry.list();
		}
		return null;
	}
}
