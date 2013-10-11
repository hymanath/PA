package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
	public List<Object[]> getCensusDetailsOfAState(Long stateId, Long year)
	{
		Query query = getSession().createQuery("select model.tru, model.totalPopulation, model.totalMalePopulation, model.totalFemalePopulation " +
				" from Census model where model.stateId = :stateId and model.year = :year and model.level = :level order by model.censusId ");
		
		query.setParameter("stateId",stateId);
		query.setParameter("year",year);
		query.setParameter("level","STATE");
		return query.list();
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
		return getHibernateTemplate().find("select model.totalPopulation,model.populationSC,model.populationST,model.populationLiterates,model.populationIlliterates, "+
				" model.workingPopulation,model.nonWorkingPopulation from Census model where model.stateId = ? and model.districtId = ? and model.tehsilId = ? " +
			    " and model.year = ? and model.level = ? and model.tru = 'Total' ",params);
	}
	
	  @SuppressWarnings("unchecked")
	  public List findCensusDetailsForAPartialMandal(Long stateId,Long districtId,Long year,String level,String villageIds){
		Object[] params = {stateId,districtId,new Long(year),level};
		return getHibernateTemplate().find("select sum(model.totalPopulation),sum(model.populationSC),sum(model.populationST),sum(model.populationLiterates),sum(model.populationIlliterates), "+
				" sum(model.workingPopulation),sum(model.nonWorkingPopulation) from Census model where model.stateId = ? and model.districtId = ? and "+
				"model.year = ? and model.level = ? and model.township.townshipId in ("+villageIds+")",params);
	}
	  
	  @SuppressWarnings("unchecked")
	  public List<Object[]> findTownshipWiseCensusDetails(Long stateId,Long districtId,Long townshipId,Long year,String level){
		Object[] params = {stateId,districtId,townshipId,new Long(year),level};
		return getHibernateTemplate().find("select model.totalPopulation,model.populationSC,model.populationST,model.populationLiterates,model.populationIlliterates, "+
				" model.workingPopulation,model.nonWorkingPopulation from Census model where model.stateId = ? and model.districtId = ? and model.township.townshipId = ?  and model.year = ? and model.level = ?",params);
	}
	  
	  @SuppressWarnings("unchecked")
	  public List findCensusDetailsForAPartialTown(Long stateId,Long districtId,Long year,String level,String wardIds){
		  Object[] params = {stateId,districtId,year,level};
		  return getHibernateTemplate().find("select sum(model.totalPopulation),sum(model.populationSC),sum(model.populationST),sum(model.populationLiterates), "+
					" sum(model.populationIlliterates),sum(model.workingPopulation),sum(model.nonWorkingPopulation) from Census model where model.stateId = ? and model.districtId = ? and "+
					"model.year = ? and model.level = ? and model.wardId in ("+wardIds+")",params);
		  
	  }
	 
	  
	  String censusString = "select " +
	  		" model.houseHolds," +
	  		" model.totalPopulation," +
	  		" model.totalMalePopulation," +
	  		" model.totalFemalePopulation," +
	  		" model.populationUnderSix," +
	  		" model.maleUnderSix," +
	  		" model.femaleUnderSix," +
	  		" model.populationSC," +
	  		" model.maleSC," +
	  		" model.femaleSC,"+
	  		" model.populationST," +
	  		" model.maleST," +
	  		" model.femaleST," +
	  		" model.populationLiterates," +
	  		" model.maleLiterates," +
	  		" model.femaleLiterates," +
	  		" model.populationIlliterates," +
	  		" model.maleIlliterates," +
	  		" model.femaleIlliterates," +
	  		" model.workingPopulation," +
	  		" model.workingMale," +
	  		" model.workingFemale," +
	  		" model.mainWorkPopulation," +
	  		" model.mainWorkMale," +
	  		" model.mainWorkFemale," +
		    " model.mainCLPopulation," +
		    " model.mainCLMale," +
		    " model.mainCLFemale," +
		    " model.mainALPopulation," +
		    " model.mainALMale," +
		    " model.mainALFemale," +
		    " model.mainHHPopulation," +
		    " model.mainHHMale," +
		    " model.mainHHFemale," +
		    " model.mainOTPopulation," +
		    " model.mainOTMale," +
		    " model.mainOTFemale," +
		    " model.margWorkPopulation," +
		    " model.margWorkMale," +
		    " model.margWorkFemale, " +
		    " model.margCLPopulation," +
		    " model.margCLMale," +
		    " model.margCLFemale," +
		    " model.margALPopulation," +
		    " model.margALMale," +
		    " model.margALFemale," +
		    " model.margHHPopulation," +
		    " model.margHHMale," +
		    " model.margHHFemale," +
		    " model.margOTPopulation," +
		    " model.margOTMale," +
		    " model.margOTFemale," +
		    " model.nonWorkingPopulation," +
		    " model.nonWorkingMale," +
		    " model.nonWorkingFemale, " +
		    " model.sexRatio," +
		    " model.sexRatioSC," +
		    " model.sexRatioST," +
		    " model.houseHoldsSize," +
		    " model.percentageSC," +
		    " model.percentageST," +
		    " model.sexRatioUnderSix," +
		    " model.maleLiteratureRate," +
		    " model.femaleLiteratureRate," +
		    " model.genderGap," +
		    " model.popLiteraturePercentage," +
		    " model.maleLiteraturePercentage," +
		    " model.femaleLiteraturePercentage, "+
		    " model.totalPopPercentage," +
		    " model.totalWorkingPopPercentage," +
		    " model.totalWorkingMalePercentage," +
		    " model.totalWorkingFemalePercentage," +
		    " model.totalMainPopPercentage," +
		    " model.totalMainMalePercentage," +
		    " model.totalMainFemalePercentage," +
		    " model.totalMargPopPercentage," +
		    " model.totalMargMalePercentage," +
		    " model.totalMargFemalePercentage, " +
		    " model.nonWorkingPopPercentage," +
		    " model.nonWorkingMalePercentage," +
		    " model.nonWorkingFemalePercentage," +
		    " model.popCLPercentage," +
		    " model.maleCLPercentage," +
		    " model.femaleCLPercentage," +
		    " model.popALPercentage," +
		    " model.maleALPercentage," +
		    " model.femaleALPercentage," +
		    " model.popHHPercentage," +
		    " model.maleHHPercentage," +
		    " model.femaleHHPercentage," +
		    " model.popOWPercentage," +
		    " model.maleOWPercentage," +
		    " model.femaleOWPercentage," +
		    " model.mainMargCLPopulation," +
		    " model.mainMargCLMale," +
		    " model.mainMargCLFemale," +
		    " model.mainMargALPopulation," +
		    " model.mainMargALMale," +
		    " model.mainMargALFemale," +
		    " model.mainMargHHPopulation," +
		    " model.mainMargHHMale," +
		    " model.mainMargHHFemale," +
		    " model.mainMargOWPopulation," +
		    " model.mainMargOWMale," +
		    " model.mainMargOWFemale," +
		    " model.wpr" ;
	  
	  String censusSumString = "select " +
		" sum(model.houseHolds)," +
		" sum(model.totalPopulation)," +
		" sum(model.totalMalePopulation)," +
		" sum(model.totalFemalePopulation)," +
		" sum(model.populationUnderSix)," +
		" sum(model.maleUnderSix)," +
		" sum(model.femaleUnderSix)," +
		" sum(model.populationSC)," +
		" sum(model.maleSC)," +
		" sum(model.femaleSC),"+
		" sum(model.populationST)," +
		" sum(model.maleST)," +
		" sum(model.femaleST)," +
		" sum(model.populationLiterates)," +
		" sum(model.maleLiterates)," +
		" sum(model.femaleLiterates)," +
		" sum(model.populationIlliterates)," +
		" sum(model.maleIlliterates)," +
		" sum(model.femaleIlliterates)," +
		" sum(model.workingPopulation)," +
		" sum(model.workingMale)," +
		" sum(model.workingFemale)," +
		" sum(model.mainWorkPopulation)," +
		" sum(model.mainWorkMale)," +
		" sum(model.mainWorkFemale)," +
	    " sum(model.mainCLPopulation)," +
	    " sum(model.mainCLMale)," +
	    " sum(model.mainCLFemale)," +
	    " sum(model.mainALPopulation)," +
	    " sum(model.mainALMale)," +
	    " sum(model.mainALFemale)," +
	    " sum(model.mainHHPopulation)," +
	    " sum(model.mainHHMale)," +
	    " sum(model.mainHHFemale)," +
	    " sum(model.mainOTPopulation)," +
	    " sum(model.mainOTMale)," +
	    " sum(model.mainOTFemale)," +
	    " sum(model.margWorkPopulation)," +
	    " sum(model.margWorkMale)," +
	    " sum(model.margWorkFemale), " +
	    " sum(model.margCLPopulation)," +
	    " sum(model.margCLMale)," +
	    " sum(model.margCLFemale)," +
	    " sum(model.margALPopulation)," +
	    " sum(model.margALMale)," +
	    " sum(model.margALFemale)," +
	    " sum(model.margHHPopulation)," +
	    " sum(model.margHHMale)," +
	    " sum(model.margHHFemale)," +
	    " sum(model.margOTPopulation)," +
	    " sum(model.margOTMale)," +
	    " sum(model.margOTFemale)," +
	    " sum(model.nonWorkingPopulation)," +
	    " sum(model.nonWorkingMale)," +
	    " sum(model.nonWorkingFemale), " +
	    " sum(model.sexRatio)," +
	    " sum(model.sexRatioSC)," +
	    " sum(model.sexRatioST)," +
	    " sum(model.houseHoldsSize)," +
	    " sum(model.percentageSC)," +
	    " sum(model.percentageST)," +
	    " sum(model.sexRatioUnderSix)," +
	    " sum(model.maleLiteratureRate)," +
	    " sum(model.femaleLiteratureRate)," +
	    " sum(model.genderGap)," +
	    " sum(model.popLiteraturePercentage)," +
	    " sum(model.maleLiteraturePercentage)," +
	    " sum(model.femaleLiteraturePercentage), "+
	    " sum(model.totalPopPercentage)," +
	    " sum(model.totalWorkingPopPercentage)," +
	    " sum(model.totalWorkingMalePercentage)," +
	    " sum(model.totalWorkingFemalePercentage)," +
	    " sum(model.totalMainPopPercentage)," +
	    " sum(model.totalMainMalePercentage)," +
	    " sum(model.totalMainFemalePercentage)," +
	    " sum(model.totalMargPopPercentage)," +
	    " sum(model.totalMargMalePercentage)," +
	    " sum(model.totalMargFemalePercentage), " +
	    " sum(model.nonWorkingPopPercentage)," +
	    " sum(model.nonWorkingMalePercentage)," +
	    " sum(model.nonWorkingFemalePercentage)," +
	    " sum(model.popCLPercentage)," +
	    " sum(model.maleCLPercentage)," +
	    " sum(model.femaleCLPercentage)," +
	    " sum(model.popALPercentage)," +
	    " sum(model.maleALPercentage)," +
	    " sum(model.femaleALPercentage)," +
	    " sum(model.popHHPercentage)," +
	    " sum(model.maleHHPercentage)," +
	    " sum(model.femaleHHPercentage)," +
	    " sum(model.popOWPercentage)," +
	    " sum(model.maleOWPercentage)," +
	    " sum(model.femaleOWPercentage)," +
	    " sum(model.mainMargCLPopulation)," +
	    " sum(model.mainMargCLMale)," +
	    " sum(model.mainMargCLFemale)," +
	    " sum(model.mainMargALPopulation)," +
	    " sum(model.mainMargALMale)," +
	    " sum(model.mainMargALFemale)," +
	    " sum(model.mainMargHHPopulation)," +
	    " sum(model.mainMargHHMale)," +
	    " sum(model.mainMargHHFemale)," +
	    " sum(model.mainMargOWPopulation)," +
	    " sum(model.mainMargOWMale)," +
	    " sum(model.mainMargOWFemale)," +
	    " sum(model.wpr)" ;
	  
      @SuppressWarnings("unchecked")
	  public List<Object[]> findMandalWiseCompleteCensusDetails(Long stateId,Long districtId,Long tehsilId,Long year,String level){
		Object[] params = {stateId,districtId,tehsilId,new Long(year),level};
		return getHibernateTemplate().find(censusString + " from Census model where model.stateId = ? and model.districtId = ? and model.tehsilId = ? " +
			    " and model.year = ? and model.level = ? and model.tru = 'Total' ",params);
	}
	  
	  @SuppressWarnings("unchecked")
	  public List<Object[]> findCompleteCensusDetailsForAPartialMandal(Long stateId,Long districtId,Long year,String level,String villageIds){
		Object[] params = {stateId,districtId,year,level};
		return getHibernateTemplate().find(censusSumString + " from Census model where model.stateId = ? and model.districtId = ? and "+
				"model.year = ? and model.level = ? and model.township.townshipId in ("+villageIds+")",params);
	}
	 
	  @SuppressWarnings("unchecked")
	  public List<Object[]> findCompleteCensusDetailsForAPartialTown(Long stateId,Long districtId,Long year,String level,String wardIds){
		  Object[] params = {stateId,districtId,year,level};
		  return getHibernateTemplate().find(censusSumString + " from Census model where model.stateId = ? and model.districtId = ? and "+
					"model.year = ? and model.level = ? and model.wardId in ("+wardIds+")",params);
		  
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Object[]> findTownshipWiseCompleteCensusDetails(Long stateId,Long districtId,Long townshipId,Long year,String level){
		Object[] params = {stateId,districtId,townshipId,new Long(year),level};
		return getHibernateTemplate().find(censusString + " from Census model where model.stateId = ? and model.districtId = ? and model.township.townshipId = ?  and model.year = ? and model.level = ?",params);
	}
	  
	  public List<Object[]> getDistrictPopulationForDifferentYears(Long districtId , List<Long> years)
	  {
		  
		  
		  Query query = getSession().createQuery("select " +
		  		"model.totalPopulation, model.totalMalePopulation , model.totalFemalePopulation , model.houseHolds," +
		  		" model.populationSC , model.populationST ,model.workingPopulation , model.workingMale ," +
		  		"model.workingFemale , model.nonWorkingPopulation ,model.populationUnderSix , model.populationLiterates , " +
		  		"model.maleLiterates , model.femaleLiterates , model.nonWorkingPopPercentage from Census model where " +
		  		"model.districtId = :districtId and model.level = :level and model.year in(:years) " +
		  		"order by model.year");
		  
		  
		  query.setParameter("districtId", districtId);
		  query.setParameter("level", IConstants.DISTRICT);
		  query.setParameterList("years", years);
		  
		  return query.list();
		  
	  }
	  
	  
	  public List<Object[]> getStatePopulationForDifferentYears(Long stateId , List<Long> years)
	  {
		  
		  
		  Query query = getSession().createQuery("select " +
		  		"model.totalPopulation, model.totalMalePopulation , model.totalFemalePopulation , model.houseHolds," +
		  		" model.populationSC , model.populationST ,model.workingPopulation , model.workingMale ," +
		  		"model.workingFemale , model.nonWorkingPopulation ,model.populationUnderSix , model.populationLiterates , " +
		  		"model.maleLiterates , model.femaleLiterates , model.nonWorkingPopPercentage from Census model where " +
		  		"model.stateId = :stateId and model.level = :level and model.year in(:years) " +
		  		"order by model.year");
		  
		  
		  query.setParameter("stateId", stateId);
		  query.setParameter("level", IConstants.STATE);
		  query.setParameterList("years", years);
		  
		  return query.list();
		  
	  }
}
