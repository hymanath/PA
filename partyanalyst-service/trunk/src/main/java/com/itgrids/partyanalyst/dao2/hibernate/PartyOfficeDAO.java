package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyOfficeDAO;
import com.itgrids.partyanalyst.model.PartyOffice;

public class PartyOfficeDAO extends GenericDaoHibernate<PartyOffice, Long> implements IPartyOfficeDAO {
	public PartyOfficeDAO(){
		super(PartyOffice.class);
	}
	public List<Object[]> getTotalAttendedEmployeesCadreId(Date fromDate, Date toDate){
		Query query = getSession().createQuery(" select distinct partyOffice.partyOfficeId, partyOffice.officeName, employee.tdpCadre.tdpCadreId " +
											   " from PartyOffice partyOffice, Employee employee, EventAttendee EA " +
											   " where " +
											   //" EWL.partyOffice.event.parentEventId = 44 " +
											   " employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
											   " partyOffice.event.eventId = EA.event.eventId and " +
											   " date(EA.attendedTime) between :fromDate and :toDate and " +
											   " employee.isDeleted = 'N' and " +
											   " employee.isActive = 'Y' and " +
											   " partyOffice.isDeleted = 'N' " +  
											   " order by " +
											   " partyOffice.partyOfficeId ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
}
/*#attended cadre Id
select distinct PO.party_office_id, PO.office_name, EMP.tdp_cadre_id from party_office PO, employee EMP, event_attendee EA, event E, tdp_cadre TC
where
PO.event_id = EA.event_id and EMP.tdp_cadre_id = EA.tdp_cadre_id and EMP.tdp_cadre_id = TC.tdp_cadre_id and EA.event_id = E.event_id and date(EA.attended_time) between '2016-08-01' and '2016-08-01'
order by PO.party_office_id;
*/