/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.partyanalyst.dao.impl;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.itgrids.partyanalyst.dao.EntityManagerHelper;
import com.itgrids.partyanalyst.dao.StateDAO;
import com.itgrids.partyanalyst.entity.State;


/**
 * A data access object (DAO) providing persistence and search support for State
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.itgrids.partyanalyst.entity.State
 * @author <a href="mailto:shan.amrita@gmail.com">Shan Nagarajan</a>
 */

public class StateDAOImpl implements StateDAO {
	// property constants
	public static final String STATE_NAME = "stateName";
	public static final String ADMIN_CAPITAL = "adminCapital";
	public static final String LEGIS_CAPITAL = "legisCapital";
	public static final String JUDICIARY_CAPITAL = "judiciaryCapital";
	public static final String STATE_LANGUAGE = "stateLanguage";
	public static final String STATE_SYMBOL = "stateSymbol";
	public static final String STATE_SONG = "stateSong";
	public static final String STATE_ANIMAL = "stateAnimal";
	public static final String STATE_BIRD = "stateBird";
	public static final String STATE_TREE = "stateTree";
	public static final String STATE_SPORT = "stateSport";
	public static final String STATE_DANCE = "stateDance";
	public static final String STATE_FLOWER = "stateFlower";
	public static final String ISO_CODE = "isoCode";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved State entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * StateDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(State entity) {
		EntityManagerHelper.log("saving State instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent State entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * StateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(State entity) {
		EntityManagerHelper.log("deleting State instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(State.class,
					entity.getStateId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved State entity and return it or a copy of it to
	 * the sender. A copy of the State entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = StateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to update
	 * @return State the persisted State entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public State update(State entity) {
		EntityManagerHelper.log("updating State instance", Level.INFO, null);
		try {
			State result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public State findById(Long id) {
		EntityManagerHelper.log("finding State instance with id: " + id,
				Level.INFO, null);
		try {
			State instance = getEntityManager().find(State.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all State entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the State property to query
	 * @param value
	 *            the property value to match
	 * @return List<State> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<State> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding State instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from State model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	} 

	public List<State> findByStateName(Object stateName) {
		return findByProperty(STATE_NAME, stateName);
	}

	public List<State> findByAdminCapital(Object adminCapital) {
		return findByProperty(ADMIN_CAPITAL, adminCapital);
	}

	public List<State> findByLegisCapital(Object legisCapital) {
		return findByProperty(LEGIS_CAPITAL, legisCapital);
	}

	public List<State> findByJudiciaryCapital(Object judiciaryCapital) {
		return findByProperty(JUDICIARY_CAPITAL, judiciaryCapital);
	}

	public List<State> findByStateLanguage(Object stateLanguage) {
		return findByProperty(STATE_LANGUAGE, stateLanguage);
	}

	public List<State> findByStateSymbol(Object stateSymbol) {
		return findByProperty(STATE_SYMBOL, stateSymbol);
	}

	public List<State> findByStateSong(Object stateSong) {
		return findByProperty(STATE_SONG, stateSong);
	}

	public List<State> findByStateAnimal(Object stateAnimal) {
		return findByProperty(STATE_ANIMAL, stateAnimal);
	}

	public List<State> findByStateBird(Object stateBird) {
		return findByProperty(STATE_BIRD, stateBird);
	}

	public List<State> findByStateTree(Object stateTree) {
		return findByProperty(STATE_TREE, stateTree);
	}

	public List<State> findByStateSport(Object stateSport) {
		return findByProperty(STATE_SPORT, stateSport);
	}

	public List<State> findByStateDance(Object stateDance) {
		return findByProperty(STATE_DANCE, stateDance);
	}

	public List<State> findByStateFlower(Object stateFlower) {
		return findByProperty(STATE_FLOWER, stateFlower);
	}

	public List<State> findByIsoCode(Object isoCode) {
		return findByProperty(ISO_CODE, isoCode);
	}

	/**
	 * Find all State entities.
	 * 
	 * @return List<State> all State entities
	 */
	@SuppressWarnings("unchecked")
	public List<State> findAll() {
		EntityManagerHelper
				.log("finding all State instances", Level.INFO, null);
		try {
			final String queryString = "select model from State model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}