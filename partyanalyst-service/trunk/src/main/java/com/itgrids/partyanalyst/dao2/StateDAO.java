/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.hibernate.Query;

import com.itgrids.partyanalyst.entity.State;


/**
 * Interface for StateDAO.
 * 
 * @author <a href="mailto:shan.amrita@gmail.com">Shan Nagarajan</a> 
 */

public interface StateDAO {
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
	 * IStateDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(State entity);

	/**
	 * Delete a persistent State entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IStateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(State entity);

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
	 * entity = IStateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to update
	 * @return State the persisted State entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public State update(State entity);

	public State findById(Long id);

	/**
	 * Find all State entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the State property to query
	 * @param value
	 *            the property value to match
	 * @return List<State> found by query
	 */
	public List<State> findByProperty(String propertyName, Object value);

	public List<State> findByStateName(Object stateName);

	public List<State> findByAdminCapital(Object adminCapital);

	public List<State> findByLegisCapital(Object legisCapital);

	public List<State> findByJudiciaryCapital(Object judiciaryCapital);

	public List<State> findByStateLanguage(Object stateLanguage);

	public List<State> findByStateSymbol(Object stateSymbol);

	public List<State> findByStateSong(Object stateSong);

	public List<State> findByStateAnimal(Object stateAnimal);

	public List<State> findByStateBird(Object stateBird);

	public List<State> findByStateTree(Object stateTree);

	public List<State> findByStateSport(Object stateSport);

	public List<State> findByStateDance(Object stateDance);

	public List<State> findByStateFlower(Object stateFlower);

	public List<State> findByIsoCode(Object isoCode);

	/**
	 * Find all State entities.
	 * 
	 * @return List<State> all State entities
	 */
	public List<State> findAll();
	
	//public List<Object[]> getAllStateDetails();
}