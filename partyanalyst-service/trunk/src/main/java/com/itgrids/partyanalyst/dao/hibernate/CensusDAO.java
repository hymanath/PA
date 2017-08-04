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
				" sum(model.houseHolds)," + 			//0
				" sum(model.totalPopulation)," +		//1
				" sum(model.totalMalePopulation)," +	//2
				" sum(model.totalFemalePopulation)," +	//3
				" sum(model.populationUnderSix)," +		//4
				" sum(model.maleUnderSix)," +			//5
				" sum(model.femaleUnderSix)," +			//6
				" sum(model.populationSC)," +			//7
				" sum(model.maleSC)," +					//8
				" sum(model.femaleSC),"+				//9
				" sum(model.populationST)," +			//10
				" sum(model.maleST)," +					//11
				" sum(model.femaleST)," +				//12
				" sum(model.populationLiterates)," +	//13
				" sum(model.maleLiterates)," +			//14
				" sum(model.femaleLiterates)," +		//15
				" sum(model.populationIlliterates)," +	//16
				" sum(model.maleIlliterates)," +		//17
				" sum(model.femaleIlliterates)," +		//18
				" sum(model.workingPopulation)," +		//19
				" sum(model.workingMale)," +			//20
				" sum(model.workingFemale)," +			//21
				" sum(model.mainWorkPopulation)," +		//22
				" sum(model.mainWorkMale)," +			//23
				" sum(model.mainWorkFemale)," +			//24
			    " sum(model.mainCLPopulation)," +		//25
			    " sum(model.mainCLMale)," +				//26
			    " sum(model.mainCLFemale)," +			//27
			    " sum(model.mainALPopulation)," +		//28
			    " sum(model.mainALMale)," +				//29
			    " sum(model.mainALFemale)," +			//30
			    " sum(model.mainHHPopulation)," +		//31
			    " sum(model.mainHHMale)," +				//32
			    " sum(model.mainHHFemale)," +			//33
			    " sum(model.mainOTPopulation)," +		//34
			    " sum(model.mainOTMale)," +				//35
			    " sum(model.mainOTFemale)," +			//36
			    " sum(model.margWorkPopulation)," +		//37
			    " sum(model.margWorkMale)," +			//38
			    " sum(model.margWorkFemale), " +		//39
			    " sum(model.margCLPopulation)," +		//40
			    " sum(model.margCLMale)," +				//41
			    " sum(model.margCLFemale)," +			//42
			    " sum(model.margALPopulation)," +		//43
			    " sum(model.margALMale)," +				//44
			    " sum(model.margALFemale)," +			//45
			    " sum(model.margHHPopulation)," +		//46
			    " sum(model.margHHMale)," +				//47
			    " sum(model.margHHFemale)," +			//48
			    " sum(model.margOTPopulation)," +		//49
			    " sum(model.margOTMale)," +				//50
			    " sum(model.margOTFemale)," +			//51
			    " sum(model.nonWorkingPopulation)," +	//52
			    " sum(model.nonWorkingMale)," +			//53
			    " sum(model.nonWorkingFemale), " +		//54
			    " sum(model.sexRatio)," +				//55
			    " sum(model.sexRatioSC)," +				//56
			    " sum(model.sexRatioST)," +				//57
			    " sum(model.houseHoldsSize)," +			//58
			    " sum(model.percentageSC)," +			//59
			    " sum(model.percentageST)," +			//60
			    " sum(model.sexRatioUnderSix)," +		//61
			    " sum(model.maleLiteratureRate)," +		//62
			    " sum(model.femaleLiteratureRate)," +	//63
			    " sum(model.genderGap)," +							//64		
			    " sum(model.popLiteraturePercentage)," +			//65
			    " sum(model.maleLiteraturePercentage)," +			//66
			    " sum(model.femaleLiteraturePercentage), "+ 		//67
			    " sum(model.totalPopPercentage)," +					//68
			    " sum(model.totalWorkingPopPercentage)," +			//69
			    " sum(model.totalWorkingMalePercentage)," +			//70
			    " sum(model.totalWorkingFemalePercentage)," +		//71
			    " sum(model.totalMainPopPercentage)," +				//72
			    " sum(model.totalMainMalePercentage)," +			//73
			    " sum(model.totalMainFemalePercentage)," +			//74
			    " sum(model.totalMargPopPercentage)," +				//75
			    " sum(model.totalMargMalePercentage)," +			//76
			    " sum(model.totalMargFemalePercentage), " +			//77
			    " sum(model.nonWorkingPopPercentage)," +			//78
			    " sum(model.nonWorkingMalePercentage)," +			//79
			    " sum(model.nonWorkingFemalePercentage)," +			//80
			    " sum(model.popCLPercentage)," +					//81
			    " sum(model.maleCLPercentage)," +					//82
			    " sum(model.femaleCLPercentage)," +					//83
			    " sum(model.popALPercentage)," +					//84
			    " sum(model.maleALPercentage)," +					//85
			    " sum(model.femaleALPercentage)," +					//86
			    " sum(model.popHHPercentage)," +					//87
			    " sum(model.maleHHPercentage)," +					//88
			    " sum(model.femaleHHPercentage)," +					//89
			    " sum(model.popOWPercentage)," +					//90
			    " sum(model.maleOWPercentage)," +					//91
			    " sum(model.femaleOWPercentage)," +					//92
			    " sum(model.mainMargCLPopulation)," +				//93
			    " sum(model.mainMargCLMale)," +						//94
			    " sum(model.mainMargCLFemale)," +					//95
			    " sum(model.mainMargALPopulation)," +				//96
			    " sum(model.mainMargALMale)," +						//97
			    " sum(model.mainMargALFemale)," +					//98
			    " sum(model.mainMargHHPopulation)," +				//99
			    " sum(model.mainMargHHMale)," +						//100
			    " sum(model.mainMargHHFemale)," +					//101
			    " sum(model.mainMargOWPopulation)," +				//102
			    " sum(model.mainMargOWMale)," +						//103
			    " sum(model.mainMargOWFemale)," +					//104
			    " sum(model.wpr)" ;									//105
	  			
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
		  		"model.districtId = :districtId and model.level = :level and model.year in(:years) and model.tru ='total' and model.tehsilId is null  " +
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
		  		"model.stateId = :stateId and model.level = :level and model.year in(:years) and model.tru ='total' and model.districtId is null " +
		  		"order by model.year");
		  
		  
		  query.setParameter("stateId", stateId);
		  query.setParameter("level", IConstants.STATE);
		  query.setParameterList("years", years);
		  
		  return query.list();
		  
	  }
	  
	  public List<Long> getCensusDetailsInConstituency(List<Long> tehsilIDs,Long year) {
  
		  
		  Query query = getSession().createQuery("select model.houseHolds from Census model where " +
		  		"model.tehsilId in (:tehsilIDs) and model.level = :level and model.year =:year");

		  query.setParameter("year", year);
		  query.setParameter("level", IConstants.TEHSIL);
		  query.setParameterList("tehsilIDs", tehsilIDs);
		  
		  return query.list();
	  }
	  
	  public List<Object[]> getCensusDetailsInConstituencyByTehsilIdsAndYears(List<Long> tehsilIDs,Long year) {
  
		  
		  Query query = getSession().createQuery(censusSumString+" ,model.year from Census model where " +
		  		"model.tehsilId in (:tehsilIDs) and model.level = :level and model.year =:year and model.tru ='total' ");

		  query.setParameter("year", year);
		  query.setParameter("level", IConstants.TEHSIL);
		  query.setParameterList("tehsilIDs", tehsilIDs);
		  
		  return query.list();
	  }

	
	public List<Object[]> getTotalCensusPopulation(Set<Long> locationIdSet,Long year) {
		
		Query query = getSession().createQuery("select model.totalPopulation, model.tehsilId from Census  model " +
				"where model.tehsilId in (:tehsilIDs) and " +
				"model.year =:years and tru =:tru order by model.year asc");
		
		query.setParameterList("tehsilIDs", locationIdSet);
		query.setParameter("years", year);
		query.setParameter("tru", "total");

		return  query.list();
	}
}
