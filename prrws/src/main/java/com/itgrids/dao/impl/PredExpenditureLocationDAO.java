package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPredExpenditureLocationDAO;
import com.itgrids.model.PredExpenditureLocation;

@Repository
public class PredExpenditureLocationDAO extends GenericDaoHibernate<PredExpenditureLocation,Long> implements IPredExpenditureLocationDAO {

	@Autowired
	SessionFactory sessionFactory;

	 public PredExpenditureLocationDAO() {
		 super(PredExpenditureLocation.class);
	 }
	 
	 public Object[] getTotalAmountForOverview(String filterType,List<Long> locationIds){
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select sum(model.predExpenditure.grossAmount), sum(model.predExpenditure.deductions),sum(model.predExpenditure.netAmount) "
		 		+ "  from PredExpenditureLocation model ");
		 sb.append(" left join model.locationAddress locationAddress "
					+ "  left join locationAddress.district district "
					+ "  left join locationAddress.state state "
					+ "  left join locationAddress.constituency constituency "
					+ "  left join locationAddress.parliament parliament "
					+ "  left join locationAddress.tehsil  tehsil"
					+ "  left join locationAddress.panchayat  panchayat"
					+ "  left join locationAddress.predddo  predddo " 
					+ "  where "
					+ "  state.stateId = 1 and model.isDeleted='N' ");
		 if (filterType != null && filterType.trim().length() > 0 && locationIds != null && locationIds.size() > 0) {
				if (filterType.equalsIgnoreCase("district")) {
					sb.append(" AND district.districtId in(:locationIds) and district is not null ");
				} else if (filterType.equalsIgnoreCase("parliament")) {
					sb.append(" AND parliament.constituencyId in(:locationIds) and parliament is not null ");
				} else if (filterType.equalsIgnoreCase("constituency")) {
					sb.append(" AND constituency.constituencyId in(:locationIds) and constituency is not null ");
				} else if (filterType.equalsIgnoreCase("mandal")) {
					sb.append(" AND tehsil.tehsilId in(:locationIds) and tehsil is not null ");
				} else if (filterType.equalsIgnoreCase("panchayat")) {
					sb.append(" AND panchayat.panchayatId in(:locationIds) and panchayat is not null ");
				} else if (filterType.equalsIgnoreCase("division")) {
					sb.append(" AND predddo.predDdoId in(:locationIds) and predddo is not null ");
				}
			}
		 Query query = getSession().createQuery(sb.toString());
		 if (filterType != null && filterType.trim().length() > 0 && locationIds != null && locationIds.size() > 0) {
			 query.setParameterList("locationIds", locationIds);
		 }
		 return (Object[])query.uniqueResult();
	 }
	 
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationWisePrExpenditureDtls(String locationType,String filterType, List<Long> locationIds) {
		StringBuilder sbs = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		StringBuilder sbf = new StringBuilder();

		sbs.append("SELECT sum(model.predExpenditure.grossAmount), sum(model.predExpenditure.deductions),sum(model.predExpenditure.netAmount) ");

		if (locationType.equalsIgnoreCase("district")) {
			sbs.append(",district.districtId, district.districtName ");
			sbg.append(" GROUP BY district.districtId ORDER BY district.districtId ");
			sbf.append(" AND district is not null ");
		} else if (locationType.equalsIgnoreCase("parliament")) {
			sbs.append(",parliament.constituencyId, parliament.name ");
			sbg.append(" GROUP BY parliament.constituencyId ORDER BY parliament.name ");
			sbf.append(" AND parliament is not null ");
		} else if (locationType.equalsIgnoreCase("constituency")) {
			sbs.append(",constituency.constituencyId, constituency.name ");
			sbg.append(" GROUP BY constituency.constituencyId ORDER BY constituency.name ");
			sbf.append(" AND constituency is not null ");
		} else if (locationType.equalsIgnoreCase("mandal")) {
			sbs.append(",tehsil.tehsilId,tehsil.tehsilName ");
			sbg.append(" GROUP BY tehsil.tehsilId ORDER BY tehsil.tehsilName ");
			sbf.append(" AND tehsil is not null ");
		} else if (locationType.equalsIgnoreCase("panchayat")) {
			sbs.append(",panchayat.panchayatId,panchayat.panchayatName ");
			sbg.append(" GROUP BY panchayat.panchayatId ORDER BY panchayat.panchayatName ");
			sbf.append(" AND panchayat is not null ");
		} else if (locationType.equalsIgnoreCase("division")) {
			sbs.append(",predddo.predDdoId,predddo.division ");
			sbg.append(" GROUP BY predddo.predDdoId ORDER BY predddo.division ");
			sbf.append(" AND predddo is not null ");
		}
		sbs.append(",state.stateId, state.stateName,district.districtId,district.districtName,"
				+ " constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName, "
				+ " parliament.constituencyId,parliament.name,panchayat.panchayatId,panchayat.panchayatName,"
				+ " predddo.predDdoId,predddo.division ");

		sbs.append(" FROM PredExpenditureLocation model  ");
		sbs.append(" left join model.locationAddress locationAddress "
				+ "  left join locationAddress.district district "
				+ "  left join locationAddress.state state "
				+ "  left join locationAddress.constituency constituency "
				+ "  left join locationAddress.parliament parliament "
				+ "  left join locationAddress.tehsil  tehsil"
				+ "  left join locationAddress.panchayat  panchayat"
				+ "  left join locationAddress.predddo  predddo " 
				+ "  where "
				+ "  state.stateId = 1 and model.isDeleted='N' ");

		if (filterType != null && filterType.trim().length() > 0
				&& locationIds != null && locationIds.size() > 0) {
			if (filterType.equalsIgnoreCase("district")) {
				sbf.append(" AND district.districtId in(:locationIds) ");
			} else if (filterType.equalsIgnoreCase("parliament")) {
				sbf.append(" AND parliament.constituencyId in(:locationIds) ");
			} else if (filterType.equalsIgnoreCase("constituency")) {
				sbf.append(" AND constituency.constituencyId in(:locationIds) ");
			} else if (filterType.equalsIgnoreCase("mandal")) {
				sbf.append(" AND tehsil.tehsilId in(:locationIds) ");
			} else if (filterType.equalsIgnoreCase("panchayat")) {
				sbf.append(" AND panchayat.panchayatId in(:locationIds) ");
			} else if (filterType.equalsIgnoreCase("division")) {
				sbf.append(" AND predddo.predDdoId in(:locationIds) ");
			}
		}

		String queryStr = sbs.toString() + sbf.toString() + sbg.toString();
		Query query = getSession().createQuery(queryStr);
		
		if (filterType != null && filterType.trim().length() > 0 && locationIds != null && locationIds.size() > 0) {
			query.setParameterList("locationIds", locationIds);
		}
		return query.list();
	}
}
