package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IReportEmailDAO;
import com.itgrids.partyanalyst.model.ReportEmail;

public class ReportEmailDAO extends GenericDaoHibernate<ReportEmail, Long> implements IReportEmailDAO {
	public ReportEmailDAO(){
		super(ReportEmail.class);
	}
	public List<Object[]> getEmailList(Long emailReportId){
		Query query = getSession().createQuery("select reportEmail.userEmail.userEmailId, reportEmail.userEmail.email from ReportEmail reportEmail " +
											   " where reportEmail.emailReport.emailReportId = :emailReportId and reportEmail.isEnabled = 'Y'");
		query.setParameter("emailReportId", emailReportId);
		return query.list();
	}
	public List<Object[]> getDeptList(Long emailReportId){
		Query query = getSession().createQuery(" select reportEmail.emailReport.emailReportId, reportEmail.userEmail.userEmailId, reportEmail.userEmail.email, userEmailDepartment.departmentId" +
											   " from ReportEmail reportEmail, UserEmailDepartment userEmailDepartment " +
											   " where reportEmail.userEmail.userEmailId = userEmailDepartment.userEmail.userEmailId and reportEmail.emailReport.emailReportId = :emailReportId and reportEmail.isEnabled = 'Y' ");
		query.setParameter("emailReportId", emailReportId);
		return query.list();
	}
}
/*select RE.email_report_id, UED.user_email_id, UE.email, UED.department_id
from 
report_email RE, user_email_department UED, user_email UE
where 
RE.user_email_id = UED.user_email_id and UE.user_email_id = UED.user_email_id and RE.is_enabled = 'Y'
order by 
RE.user_email_id;
*/