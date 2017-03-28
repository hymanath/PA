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

import com.itgrids.partyanalyst.dao.CountryDAO;
import com.itgrids.partyanalyst.dao.EntityManagerHelper;
import com.itgrids.partyanalyst.entity.Country;


/**
 * A data access object (DAO) providing persistence and search support for
 * Country entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.itgrids.partyanalyst.entity.Country
 * @author <a href="mailto:shan.amrita@gmail.com">Shan Nagarajan</a>
 */

public class CountryDAOImpl implements CountryDAO {
	// property constants
	public static final String COUNTRY_NAME = "countryName";
	public static final String CAPITAL = "capital";
	public static final String ISO_CODE = "isoCode";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Country entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CountryDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Country entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Country entity) {
		EntityManagerHelper.log("saving Country instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Country entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CountryDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Country entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Country entity) {
		EntityManagerHelper.log("deleting Country instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Country.class,
					entity.getCountryId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Country entity and return it or a copy of it
	 * to the sender. A copy of the Country entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CountryDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Country entity to update
	 * @return Country the persisted Country entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Country update(Country entity) {
		EntityManagerHelper.log("updating Country instance", Level.INFO, null);
		try {
			Country result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Country findById(Long id) {
		EntityManagerHelper.log("finding Country instance with id: " + id,
				Level.INFO, null);
		try {
			Country instance = getEntityManager().find(Country.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Country entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Country property to query
	 * @param value
	 *            the property value to match
	 * @return List<Country> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Country> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding Country instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Country model where model."
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

	public List<Country> findByCountryName(Object countryName) {
		return findByProperty(COUNTRY_NAME, countryName);
	}

	public List<Country> findByCapital(Object capital) {
		return findByProperty(CAPITAL, capital);
	}

	public List<Country> findByIsoCode(Object isoCode) {
		return findByProperty(ISO_CODE, isoCode);
	}

	/**
	 * Find all Country entities.
	 * 
	 * @return List<Country> all Country entities
	 */
	@SuppressWarnings("unchecked")
	public List<Country> findAll() {
		EntityManagerHelper.log("finding all Country instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Country model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}