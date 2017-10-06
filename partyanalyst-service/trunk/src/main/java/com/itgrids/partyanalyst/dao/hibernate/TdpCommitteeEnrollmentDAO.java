package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeEnrollment;

public class TdpCommitteeEnrollmentDAO extends GenericDaoHibernate<TdpCommitteeEnrollment, Long>  implements ITdpCommitteeEnrollmentDAO {
	public TdpCommitteeEnrollmentDAO() {
		super(TdpCommitteeEnrollment.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCadreCommitteYearsList(){
		   Query query = getSession().createQuery(" select model.tdpCommitteeEnrollmentId,model.description from TdpCommitteeEnrollment model order by model.tdpCommitteeEnrollmentId desc ");
		   
		  return query.list();
	 }
	@SuppressWarnings("unchecked")
	public List<Object[]> getTdpCadreEnrollmentYear(){
	 Query query = getSession().createQuery(" select model.tdpCommitteeEnrollmentId,model.description from TdpCommitteeEnrollment model where model.isActive='Y' order by model.tdpCommitteeEnrollmentId desc ");
     return query.list();
	 }
}
