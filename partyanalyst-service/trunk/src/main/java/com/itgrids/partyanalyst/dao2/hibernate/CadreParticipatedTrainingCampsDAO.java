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
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreParticipatedTrainingCamps model where model.partyTrainingCamps.partyTrainingCampsId = ? and "+
		     "model.cadre.cadreId in (:cadreIds)");
		queryObject.setParameter(0, campId);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreCampsAndUser(Long userId, Long campId) {
		Object[] params = {userId,campId};
		return getHibernateTemplate().find("select model.cadre.cadreId from CadreParticipatedTrainingCamps model where model.cadre.user.userId = ?"+
				" and model.partyTrainingCamps.partyTrainingCampsId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getCadreByCampsListAndCadreIds(List<Long> campIds, List<Long> cadreIds) {
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreParticipatedTrainingCamps model where model.partyTrainingCamps.partyTrainingCampsId in (:campIds) and "+
		     "model.cadre.cadreId in (:cadreIds)");
		queryObject.setParameterList("campIds", campIds);
		queryObject.setParameterList("cadreIds", cadreIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreCampsListAndUser(Long userId,
			List<Long> campIds) {
		Query queryObject = getSession().createQuery("select model.cadre.cadreId from CadreParticipatedTrainingCamps model where model.cadre.user.userId = ?"+
				" and model.partyTrainingCamps.partyTrainingCampsId in (:campIds)");
		queryObject.setParameter(0, userId);
		queryObject.setParameterList("campIds", campIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<CadreParticipatedTrainingCamps> findByCadreId(Long cadreId) {
		
		return getHibernateTemplate().find("from CadreParticipatedTrainingCamps model where model.cadre.cadreId = ?", cadreId);
	}

	public Integer deleteCadreTrainingCamps(Long cadreId) {
		
		Query queryObject = getSession().createQuery("delete from CadreParticipatedTrainingCamps model where model.cadre.cadreId = ?");
		queryObject.setParameter(0, cadreId);
		return queryObject.executeUpdate();
		
	}

}
