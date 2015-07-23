package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreInsuranceInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreInsuranceInfo;

public class TdpCadreInsuranceInfoDAO extends GenericDaoHibernate<TdpCadreInsuranceInfo, Long> implements ITdpCadreInsuranceInfoDAO{

	public TdpCadreInsuranceInfoDAO() {
		super(TdpCadreInsuranceInfo.class);
	}

	public List<Object[]> getDeathsAndHospitalizationDetails(Long typeId,String type){
		
		StringBuilder str=new StringBuilder();
		str.append(" select count(model.tdpCadreInsuranceInfoId),model.insuranceType.insuranceTypeId,model.insuranceType.type from TdpCadreInsuranceInfo model " );
		
		if(type.equalsIgnoreCase("panchayat")){
			str.append("left join model.tdpCadre.userAddress.panchayat panchayat " +
					" where panchayat.panchayatId=:typeId ");
		}
		else if(type.equalsIgnoreCase("mandal")){
			str.append(" left join model.tdpCadre.userAddress.tehsil tehsil " +
					" where tehsil.tehsilId=:typeId ");
		}
		else if(type.equalsIgnoreCase("constituency")){
			str.append(" left join model.tdpCadre.userAddress.constituency constituency " +
					" where constituency.constituencyId=:typeId");
		}
		else if(type.equalsIgnoreCase("district")){
			str.append(" left join model.tdpCadre.userAddress.district district " +
					" where district.districtId=:typeId");
		}
		
		str.append(" group by model.insuranceType.insuranceTypeId");
			
		Query query=getSession().createQuery(str.toString());
		
		if(type !="" && type !=null){
			query.setParameter("typeId",typeId);
		}
		
		return query.list();
	}
}
