package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStudentCadreRelationDAO;
import com.itgrids.partyanalyst.model.StudentCadreRelation;

public class StudentCadreRelationDAO extends GenericDaoHibernate<StudentCadreRelation, Long> implements IStudentCadreRelationDAO{

	public StudentCadreRelationDAO() {
		super(StudentCadreRelation.class);
		
	}
	
		public List<Object[]> getNtrTrustStudentDetailsInstitutionWise(List<Long> tdpCadreIds){
			
			try {
				StringBuilder str = new StringBuilder();
				
				str.append("select model.student.institutionCourse.institution.institutionId,model.student.institutionCourse.institution.institutionName,count(model.student.studentId) from StudentCadreRelation model " +
						" where model.tdpCadreId in (:tdpCadreIds) " +
						" group by model.student.institutionCourse.institution.institutionId ");
				
				Query query =getSession().createQuery(str.toString());
				
				if(tdpCadreIds !=null && tdpCadreIds.size() >0){
					query.setParameterList("tdpCadreIds", tdpCadreIds);
				}
				
				return query.list();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		
		public List<Object[]> getStudentFormalDetailsByCadre(List<Long> tdpCadreIds,Long institutionId){
			
			StringBuilder str= new StringBuilder();
			
			str.append("select model.student.institutionCourse.institution.institutionId," +
					" model.student.studentId,model.student.studentName,date(model.student.dateOfBirth),model.student.yearOfJoining," +
					" model.student.institutionCourse.course.courseId,model.student.institutionCourse.course.courseCode," +
					" model.student.casteId,model.student.tdpCadreId,model.student.membershipNo,   " +
					" model.student.guardianDetails," +
					" model.student.parentAliveStatus," +
					" model.relation,model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.firstname " +
					" from StudentCadreRelation model " +
					" where model.tdpCadreId in (:tdpCadreIds)  ");
			
			if(institutionId !=null && institutionId>0){
				str.append(" and model.student.institutionCourse.institution.institutionId =:institutionId ");
			}
			str.append(" group by model.student.studentId ");
			
			Query query=getSession().createQuery(str.toString());
			
			query.setParameterList("tdpCadreIds", tdpCadreIds);
			
			if(institutionId !=null && institutionId>0){
				query.setParameter("institutionId",institutionId);
			}
			
			return query.list();
		}

}
