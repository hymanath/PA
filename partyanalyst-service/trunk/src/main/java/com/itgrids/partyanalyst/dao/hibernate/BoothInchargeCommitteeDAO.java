package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.model.BoothInchargeCommittee;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothInchargeCommitteeDAO extends GenericDaoHibernate<BoothInchargeCommittee,Long> implements IBoothInchargeCommitteeDAO {

	public BoothInchargeCommitteeDAO() {
		super(BoothInchargeCommittee.class);

	}
	
	public Long getElectionBoothDetails(Date startDate, Date endDate,Long locationTypeId, Long locationValue, String type,
			List<Long> committeeEnrollmentYearsIdsLst) {

		StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select  count(distinct model.boothInchargeCommitteeId) "
				+ " from BoothInchargeCommittee model where "
				+ " model.isDeleted = 'N' ");

		if (locationTypeId != null && locationTypeId.longValue() == 1l) {
			queryStr.append(" and model.address.state.stateId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 2l) {
			queryStr.append(" and model.address.district.districtId =:locationValue ");
		}
		else if (locationTypeId != null && locationTypeId.longValue() == 4l) {
			queryStr.append(" and model.address.constituency.constituencyId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 5l) {
			queryStr.append(" and model.address.tehsil.tehsilId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 6l) { // town/division
			queryStr.append(" and model.address.localBody.localElectionBodyId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 7l) {
			queryStr.append(" and model.address.panchayat.panchayatId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 8l) {
			queryStr.append(" and model.address.ward.wardId =:locationValue ");
		}

		if (type != null && type.trim().length() > 0L) {
			if (type.trim().equalsIgnoreCase("started")) {
				queryStr.append(" and model.isConfirmed ='N' and model.startDate is not null and model.completedDate is null");
				if (startDate != null && endDate != null) {
					queryStr.append(" and date(model.startDate) between :startDate and :endDate ");
				}
			} else if (type.trim().equalsIgnoreCase("completed")) {
				queryStr.append(" and model.isConfirmed ='Y' and model.startDate is not null and model.completedDate is not null");
				if (startDate != null && endDate != null) {
					queryStr.append(" and date(model.completedDate) between :startDate and :endDate ");
				}
			}
		}

		if (committeeEnrollmentYearsIdsLst != null && committeeEnrollmentYearsIdsLst.size() > 0) {
			queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in(:committeeEnrollmentYearsIdsLst) ");
		}
		Query qry = getSession().createQuery(queryStr.toString());

		if (locationValue != null && locationValue.longValue() > 0) {
			qry.setParameter("locationValue", locationValue);
		}
		if (type != null && type.trim().length() > 0L && !type.trim().equalsIgnoreCase("total")) {
			if (startDate != null && endDate != null) {
				qry.setDate("startDate", startDate);
				qry.setDate("endDate", endDate);
			}
		}

		if (committeeEnrollmentYearsIdsLst != null 	&& committeeEnrollmentYearsIdsLst.size() > 0) {
			qry.setParameterList("committeeEnrollmentYearsIdsLst",committeeEnrollmentYearsIdsLst);
		}

		return (Long) qry.uniqueResult();

	}

}
