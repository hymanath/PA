package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.utils.IConstants;

public class CensusDAO extends GenericDaoHibernate<Census, Long> implements ICensusDAO {

	public CensusDAO() {
		super(Census.class);
	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByDistrictIdAndYear(final Long districtId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "districtId" , districtId  ))
                	.add( Expression.eq( "year" , new Long(year)  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByStateIdAndYear(final Long stateId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "stateId" , stateId  ))
                	.add( Expression.eq( "year" , new Long(year)  ))
            	    .add( Expression.eq( "level" , "STATE"  ));
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByTehsilIdAndYear(final Long tehsilId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "tehsilId" , tehsilId  ))
                	.add( Expression.eq( "year" , new Long(year)  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByTownshipIdAndYear(final Long townshipId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "townshipId" , townshipId  ))
                	.add( Expression.eq( "year" , new Long(year)  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public Set<Census> findByWardIdAndYear( final Long wardId, final int year) {
		return ( Set<Census> ) getHibernateTemplate().execute( new HibernateCallback() {

            public Object doInHibernate( Session session ) throws HibernateException, SQLException {

                Criteria criteria = session.createCriteria( Census.class )
                	.add( Expression.eq( "wardId" , wardId  ))
                	.add( Expression.eq( "year" , new Long(year)  )); 
                
                return new HashSet( criteria.list() );
            }
        } );

	}

	@SuppressWarnings("unchecked")
	public List<Census> findByYearAndTehsilIDs(Long year, String tehsilIDs){
		return getHibernateTemplate().find("from Census model where model.year="+year+" and model.tru=? and model.tehsilId in ("+tehsilIDs +")", "Total");
	}
	@SuppressWarnings("unchecked")
	public List<Census> findByYearAndTownshipIDs(Long year, String townshipIDs){
		return getHibernateTemplate().find("from Census model where model.year="+year+" and model.townshipId in ("+townshipIDs +")");
	}

	public List findCastWiseVotersForMandal(Long mandalID){
		Object[] params = {mandalID,"Total"};
		return getHibernateTemplate().find("select model.populationSC, model.populationST " +
				"from Census model where model.year in ("+IConstants.CENSUS_YEAR+") and model.tehsilId=? and model.tru=?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List findCensusDetailsForAMandal(Long stateId,Long districtId,Long tehsilId,int year,String level){
		Object[] params = {stateId,districtId,tehsilId,new Long(year),level};
		return getHibernateTemplate().find("select model.tru,model.level,model.year,model.totalMalePopulation,model.totalFemalePopulation,model.totalPopulation from Census model where model.stateId = ? and model.districtId = ? and model.tehsilId = ?  and model.year = ? and model.level = ?",params);
	}

	public List findAllRevenueVillagesInfoInMandal(Long year, Long mandalId, String levels){
		Object[] params = {mandalId, year};
		return getHibernateTemplate().find("select model.township.townshipId, model.township.townshipName, " +
				"sum(model.totalPopulation), sum(model.totalMalePopulation), sum(model.totalFemalePopulation), " +
				"sum(model.populationSC), sum(model.populationST), sum(model.populationLiterates)" +
				", sum(model.populationIlliterates), sum(model.workingPopulation) from Census model " +
				"where model.township.tehsil.tehsilId = ? and model.year = ? and " +
				" model.level in ("+levels+") group by model.township.townshipId", params);
	}
	
	  @SuppressWarnings("unchecked")
	  public List findMandalWiseCensusDetails(Long stateId,Long districtId,Long tehsilId,Long year,String level){
		Object[] params = {stateId,districtId,tehsilId,new Long(year),level};
		return getHibernateTemplate().find("select model.totalPopulation,model.totalMalePopulation,model.totalFemalePopulation, "+
				" model.populationSC,model.populationST from Census model where model.stateId = ? and model.districtId = ? and model.tehsilId = ? " +
			    " and model.year = ? and model.level = ? and model.tru = 'Total' ",params);
	}
	
	  @SuppressWarnings("unchecked")
	  public List findCensusDetailsForAPartialMandal(Long stateId,Long districtId,Long year,String level,String villageIds){
		Object[] params = {stateId,districtId,new Long(year),level};
		return getHibernateTemplate().find("select sum(model.totalPopulation),sum(model.totalMalePopulation),sum(model.totalFemalePopulation), "+
				" sum(model.populationSC),sum(model.populationST) from Census model where model.stateId = ? and model.districtId = ? and "+
				"model.year = ? and model.level = ? and model.township.townshipId in ("+villageIds+")",params);
	}
	  
	  @SuppressWarnings("unchecked")
	  public List<Object[]> findTownshipWiseCensusDetails(Long stateId,Long districtId,Long townshipId,Long year,String level){
		Object[] params = {stateId,districtId,townshipId,new Long(year),level};
		return getHibernateTemplate().find("select model.totalPopulation,model.totalMalePopulation,model.totalFemalePopulation, "+
				" model.populationSC,model.populationST from Census model where model.stateId = ? and model.districtId = ? and model.township.townshipId = ?  and model.year = ? and model.level = ?",params);
	}
	  
	  @SuppressWarnings("unchecked")
	  public List findCensusDetailsForAPartialTown(Long stateId,Long districtId,Long year,String level,String wardIds){
		  Object[] params = {stateId,districtId,year,level};
		  return getHibernateTemplate().find("select sum(model.totalPopulation),sum(model.totalMalePopulation),sum(model.totalFemalePopulation), "+
					" sum(model.populationSC),sum(model.populationST) from Census model where model.stateId = ? and model.districtId = ? and "+
					"model.year = ? and model.level = ? and model.wardId in ("+wardIds+")",params);
		  
	  }
	  
}
