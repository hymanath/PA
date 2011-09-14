package com.itgrids.partyanalyst.dao.hibernate;

import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.utils.IConstants;

import java.sql.SQLException;
import java.util.List;

import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.columns.enums.ElectionColumnNames;
import com.itgrids.partyanalyst.dao.columns.enums.StateColumnNames;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;


public class StateDAO extends GenericDaoHibernate<State, Long> implements IStateDAO {

	public StateDAO() {
		super(State.class);
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
	public List<State> findByProperty(StateColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from State where " + propertyName.getValue() + "=?", value);
	}

	public List<State> findByStateName(Object stateName){
		return findByProperty(StateColumnNames.STATE_NAME, stateName);
	}

	public List<State> findByAdminCapital(Object adminCapital){
		return findByProperty(StateColumnNames.ADMIN_CAPITAL, adminCapital);
	}

	public List<State> findByLegisCapital(Object legisCapital){
		return findByProperty(StateColumnNames.LEGIS_CAPITAL, legisCapital);
	}

	public List<State> findByJudiciaryCapital(Object judiciaryCapital){
		return findByProperty(StateColumnNames.JUDICIARY_CAPITAL, judiciaryCapital);
	}

	public List<State> findByStateLanguage(Object stateLanguage){
		return findByProperty(StateColumnNames.STATE_LANGUAGE, stateLanguage);
	}

	public List<State> findByStateSymbol(Object stateSymbol){
		return findByProperty(StateColumnNames.STATE_SYMBOL, stateSymbol);
	}

	public List<State> findByStateSong(Object stateSong){
		return findByProperty(StateColumnNames.STATE_SONG, stateSong);
	}

	public List<State> findByStateAnimal(Object stateAnimal){
		return findByProperty(StateColumnNames.STATE_ANIMAL, stateAnimal);
	}

	public List<State> findByStateBird(Object stateBird){
		return findByProperty(StateColumnNames.STATE_BIRD, stateBird);
	}

	public List<State> findByStateTree(Object stateTree){
		return findByProperty(StateColumnNames.STATE_TREE, stateTree);
	}

	public List<State> findByStateSport(Object stateSport){
		return findByProperty(StateColumnNames.STATE_SPORT, stateSport);
	}

	public List<State> findByStateDance(Object stateDance){
		return findByProperty(StateColumnNames.STATE_DANCE, stateDance);
	}

	public List<State> findByStateFlower(Object stateFlower){
		return findByProperty(StateColumnNames.STATE_FLOWER, stateFlower);
	}

	public List<State> findByIsoCode(Object isoCode){
		return findByProperty(StateColumnNames.ISO_CODE, isoCode);
	}

	public List<State> findByStateCode(Object stateCode){
		return findByProperty(StateColumnNames.STATE_CODE, stateCode);
	}
	
	@SuppressWarnings("unchecked")
	public List<State> findByStateId(Long stateId){
		return getHibernateTemplate().find("from State model where model.stateId=?", stateId);
	}

	public Object[] listOfColumn( final StateColumnNames propertyName) {       

        return ( Object[] ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria( State.class ).add( Expression.like( "columnNameAsMappedForHibernate" , propertyName ) ); 
                // u can add more expressions by using criteria.add(Expression.like(...)); in next lines
                return ( criteria.list() );
            }
        } );
	}
	

	@SuppressWarnings("unchecked")
	public List<State> findByCountryId(Long countryId){
		return getHibernateTemplate().find("from State model where model.country.countryId=?", countryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<State> findByCountryIdForSearch(Long countryId){
		return getHibernateTemplate().find("from State model where model.country.countryId=? and model.stateId in ("+IConstants.STATES_FOR_SEARCH_FUNCTIONALITY+") ", countryId);
	}


	@SuppressWarnings("unchecked")
	public List findStatesByCountryId(Long countryId) {
		return getHibernateTemplate().find("select model.stateId,model.stateName from State model where model.country.countryId = ?",countryId);
	}

 
	@SuppressWarnings("unchecked")
	public List findStateIdByNameAndCountryId(String stateName, Long countryId) {
		Object[] params = {stateName,countryId};
		return getHibernateTemplate().find("select model.stateId from State model where model.stateName = ? and model.country.countryId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getAllStatesByCountryIdOrderByStateId(Long countryId){
		return getHibernateTemplate().find("select model.stateId,model.stateName from State model where model.country.countryId=? order by model.stateId asc", countryId);
	}
	public List findStatesByCountryIdAndCountryAccess(Long countryId,Long userId) {
		Object[] params = {countryId, userId};
		return getHibernateTemplate().find("select model.stateId,model.stateName from State model where model.country.countryId = ? and "+
	" model.country.countryId not in(select model1.country.countryId from UserCountryAccessInfo model1 where model1.user.registrationId = ?)",params);
	}
	
	public List findStatesByCountryIdAndCountryAccessAndStateAccess(Long countryId,Long userId) {
		Object[] params = {countryId,userId,userId};
		return getHibernateTemplate().find("select model.stateId,model.stateName from State model where model.country.countryId = ? and "+
	" model.country.countryId not in(select model1.country.countryId from UserCountryAccessInfo model1 where model1.user.registrationId = ?) "+
	" and model.stateId not in (select model2.state.stateId from UserStateAccessInfo model2 where model2.user.registrationId = ?)",params);
	}
}
