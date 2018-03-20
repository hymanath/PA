package com.itgrids.dao.impl;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubWorkCoveringLetterDAO;
import com.itgrids.dto.PetitionsInputVO;
import com.itgrids.model.PmSubWorkCoveringLetter;

@Repository
public class PmSubWorkCoveringLetterDAO extends GenericDaoHibernate<PmSubWorkCoveringLetter, Long> implements IPmSubWorkCoveringLetterDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmSubWorkCoveringLetterDAO() {
		super(PmSubWorkCoveringLetter.class);
	}

	public List<String> getCoveringLetterDetailsByEndorsmentNo(String endorsmenNo){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.document.path from PmSubWorkCoveringLetter model where model.endorsmentNo ='"+endorsmenNo+"'  and model.isDeleted ='N' ");
		Query query = getSession().createQuery(str.toString());
		return query.list();				
	}
	
	public List<Object[]> getSubWorkWiseRequiredDocumentsDetailsByPetitionId(Long petiotionId){
		if(petiotionId != null){
			StringBuilder str = new StringBuilder();
			str.append(" select model.pmSubWorkDetailsId,model.reportType, model.document.path,model.documentId, model.petitionId,model.refNo from PmSubWorkCoveringLetter model where model.petitionId=:petiotionId " +
					" and model.isDeleted ='N' group by model.documentId ");
			Query query = getSession().createQuery(str.toString());
			if(petiotionId.longValue()>0L)
				query.setParameter("petiotionId", petiotionId);
			return query.list();
		}
		return null;			
	}
	public int disableExistingCoveringLettersForPetition(Long petitionId,String reporttype){
		StringBuilder sb = new StringBuilder();
		sb.append(" update PmSubWorkCoveringLetter model set model.isDeleted ='Y'  "
				+ "where model.petitionId =:petitionId and model.reportType = :reporttype ");
		Query query=getSession().createQuery(sb.toString());
		
		query.setParameter("petitionId", petitionId);
		query.setParameter("reporttype", reporttype);
		return query.executeUpdate();
	}	
	public List<Object[]> getAllTypeOfDocumentsForPetition(Set<Long> petiotionIds,String reportType){
		if(petiotionIds != null){
			StringBuilder str = new StringBuilder();
			str.append(" select distinct  model.petitionId,model.reportType,model.documentTypeId,model.pmSubWorkDetails.pmSubWorkDetailsId,model.document.documentId,model.document.path from PmSubWorkCoveringLetter model " +
					"where  " +
					"  model.isDeleted ='N' ");
			if(petiotionIds.size()>0)
				str.append(" and model.petitionId in (:petiotionIds) ");
			if(reportType != null){
				str.append(" and model.reportType = :reportType ");
			}
			str.append(" group by model.petitionId,model.reportType,model.pmSubWorkDetails.pmSubWorkDetailsId ");
			Query query = getSession().createQuery(str.toString());
			if(reportType != null){
				query.setParameter("reportType", reportType);
			}
			if(petiotionIds.size()>0)
				query.setParameterList("petiotionIds", petiotionIds);
			return query.list();
		}
		return null;			
	}
	
	public List<Object[]> getDocumentsDetailsForPDFDocument(PetitionsInputVO inputVO,List<Long> petitionIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" sub.petition_id as petition_id, sub.work_endorsment_no as work_endorsment_no ,sub.pm_sub_work_details_id as pm_sub_work_details_id," +
				" l.report_type as report_type ,l.ref_no as ref_no,l.document_type_id as document_type_id,dType.pm_document_type as pm_document_type ");
		sb.append(" from  ");
		sb.append(" pm_sub_work_details sub  ");
		sb.append(" LEFT JOIN pm_department d  on d.pm_department_id = sub.pm_department_id and d.is_deleted='N' ");
		sb.append(" LEFT JOIN pm_subject ps on sub.pm_subject_id = ps.pm_subject_id and ps.is_deleted='N' ");
		sb.append(" LEFT JOIN location_address la on sub.address_id = la.location_address_id  ");
		sb.append(" LEFT JOIN district dis on la.district_id = dis.district_id  ");
		sb.append(" LEFT JOIN constituency c on la.constituency_id = c.constituency_id , ");
		sb.append(" pm_sub_work_covering_letter l "+
				  " LEFT JOIN pm_document_type dType on l.document_type_id = dType.pm_document_type_id ");
		sb.append(" where  ");
		sb.append(" sub.pm_sub_work_details_id = l.pm_sub_work_details_id and  ");
		sb.append(" sub.pm_department_id = d.pm_department_id and  ");
		sb.append(" sub.is_deleted='N' and  ");
		sb.append(" d.is_deleted='N' ");
		
		if( inputVO.getConstituencyIdsList() != null &&  inputVO.getConstituencyIdsList().size()>0)
			sb.append(" and c.constituency_id in (:constituencyIdsList)  ");
		if( inputVO.getDeptIdsList() != null &&  inputVO.getDeptIdsList().size()>0)
			sb.append("  and  d.pm_department_id in (:deptIdsList)   ");
		if( inputVO.getStatusIdsList() != null &&  inputVO.getStatusIdsList().size()>0)
			sb.append("  and  sub.pm_statusId in (:statusIdsList)   ");
		if( inputVO.getFromDate() != null &&  inputVO.getEndDate() != null)
			sb.append(" and (date(p.inserted_time) between :startDate and :endDate)  ");
		if( inputVO.getSubjectIdsList() != null &&  inputVO.getSubjectIdsList().size()>0)
			sb.append("  and  sub.pm_subject_id in (:subjectIdsList)   ");
		if( inputVO.getSubSubjectIdsList() != null &&  inputVO.getSubSubjectIdsList().size()>0)
			sb.append("  and  sub.pm_sub_subject_id in (:subSubjectIdsList)   ");
		
		//sb.append(" GROUP BY  ");
		//sb.append(" sub.petition_id,l.report_type  ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("petition_id", StandardBasicTypes.LONG)
				.addScalar("work_endorsment_no", StandardBasicTypes.STRING)
				.addScalar("pm_sub_work_details_id", StandardBasicTypes.LONG)
				.addScalar("report_type", StandardBasicTypes.STRING)
				.addScalar("ref_no", StandardBasicTypes.STRING)
				.addScalar("document_type_id", StandardBasicTypes.LONG)
				.addScalar("pm_document_type", StandardBasicTypes.LONG);
		
		if( inputVO.getConstituencyIdsList() != null &&  inputVO.getConstituencyIdsList().size()>0)
			query.setParameterList("constituencyIdsList", inputVO.getConstituencyIdsList());
		if( inputVO.getDeptIdsList() != null &&  inputVO.getDeptIdsList().size()>0)
			query.setParameterList("deptIdsList", inputVO.getDeptIdsList());
		if( inputVO.getStatusIdsList() != null &&  inputVO.getStatusIdsList().size()>0)
			query.setParameterList("statusIdsList", inputVO.getStatusIdsList());
		if( inputVO.getSubjectIdsList() != null &&  inputVO.getSubjectIdsList().size()>0)
			query.setParameterList("subjectIdsList", inputVO.getSubjectIdsList());
		if( inputVO.getSubSubjectIdsList() != null &&  inputVO.getSubSubjectIdsList().size()>0)
			query.setParameterList("subSubjectIdsList", inputVO.getSubSubjectIdsList());
		if( inputVO.getFromDate() != null &&  inputVO.getEndDate() != null){
			query.setParameter("startDate", inputVO.getFromDate());
			query.setParameter("endDate", inputVO.getEndDate());
		}
		return query.list();
	}
}
