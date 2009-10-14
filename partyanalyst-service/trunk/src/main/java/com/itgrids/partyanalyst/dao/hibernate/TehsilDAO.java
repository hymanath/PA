package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.columns.enums.TehsilColumnNames;
import com.itgrids.partyanalyst.model.Tehsil;

public class TehsilDAO extends GenericDaoHibernate<Tehsil, Long> implements ITehsilDAO
{

	public TehsilDAO() {
		super(Tehsil.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByProperty(TehsilColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from Tehsil where " + propertyName.getValue() + "=?", value);
	}

	public List<Tehsil> findByTehsilName(Object tehsilName){
		return findByProperty(TehsilColumnNames.TEHSIL_NAME, tehsilName);
	}

	public List<Tehsil> findByTehsilCode(Object tehsilCode){
		return findByProperty(TehsilColumnNames.TEHSIL_CODE, tehsilCode);
	}

	public List<Tehsil> findByDeformDate(Object deformDate){
		return findByProperty(TehsilColumnNames.DEFORM_DATE, deformDate);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByConstituency(Long constituencyId){
		return getHibernateTemplate().find("from Tehsil model where model.tehsilId in(select model1.tehsil.tehsilId from Booth model1 where model1.boothId in(select model2.booth.boothId from BoothConstituencyElection model2 where model2.constituencyElection.constiElecId in(select model3.constiElecId from ConstituencyElection model3 where model3.constituency.constituencyId=?)))", constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByTehsilNameAndDistrict(String name, String districtName){
		String[] params = {name,districtName};
		return getHibernateTemplate().find("from Tehsil model where model.tehsilName = ? and model.district.districtName = ?", params);
	}
	
}
