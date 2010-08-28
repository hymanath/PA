package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreParticipatedTrainingCampsDAO;
import com.itgrids.partyanalyst.model.CadreParticipatedTrainingCamps;

public class CadreParticipatedTrainingCampsDAO extends GenericDaoHibernate<CadreParticipatedTrainingCamps, Long> implements ICadreParticipatedTrainingCampsDAO {

	public CadreParticipatedTrainingCampsDAO() {
		super(CadreParticipatedTrainingCamps.class);		
	}

	@SuppressWarnings("unchecked")
	public List getCadreByCampsAndCadreIds(Long campId, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreParticipatedTrainingCamps model where model.partyTrainingCamps.partyTrainingCampsId = "+campId+" and "+
		     "model.cadre.cadreId in (:cadreIds)");
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreCampsAndUser(Long userId, Long campId) {
		Object[] params = {userId,campId};
		return getHibernateTemplate().find("select model.cadre.cadreId from CadreParticipatedTrainingCamps model where model.cadre.registration.registrationId = ?"+
				" and model.partyTrainingCamps.partyTrainingCampsId = ?",params);
	}

}
