package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.model.Activity;

public class ActivityDAO extends GenericDaoHibernate<Activity, Long> implements IActivityDAO{

	public ActivityDAO() {
		super(Activity.class);
		
	}
	
	public List<Object[]> getAllActivities(){
		
		Query query = getSession().createQuery(" SELECT model.activityId,model.activityName " +
				" FROM Activity model" +
				" WHERE model.isActive = 'Y' ");
		
		return query.list();
	} 
	
	public List<Object[]> getActivitysTotal(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId){
		   StringBuilder sb = new StringBuilder();
	       StringBuilder sbm = new StringBuilder();
	       StringBuilder sbe = new StringBuilder();
	       StringBuilder sbg = new StringBuilder();
	       
	       sb.append(" SELECT a1.activity_scope_id as scopeId,a.activity_name as name,a2.level as level,count(ali.activity_location_info_id) as total ");
	       sbm.append(" from activity a,activity_scope a1,activity_location_info ali,user_address ua,activity_level a2 ");	
	       sbe.append(" WHERE a1.activity_level_id = a2.activity_level_id and a.activity_id = a1.activity_id and a1.is_deleted='N' and "
	       		+ "a.is_active='Y' and ali.activity_scope_id = a1.activity_scope_id and ali.address_id = ua.user_address_id ");
	       if(year!=null && !year.trim().isEmpty()){
		         sbe.append(" and year(a1.start_date) =:year  ");
		       }
		       else if(fromDate!=null && toDate!=null){
		         sbe.append(" and date(a1.start_date) between :fromDate and :toDate  ");
		       }
	       sbg.append(" group by ali.activity_scope_id,a1.activity_level_id ");
	       if(locationTypeId !=null && locationTypeId.longValue()>0l){
		         if(locationTypeId ==2l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.state_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==3l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.district_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==4l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.constituency_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==5l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.tehsil_id  in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==6l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append( "  and ua.panchayat_id in  (:locationValues)  " );
		           }	           
		         }
		         
		       }
		       sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());  
		       
		       Query query = getSession().createSQLQuery(sb.toString())
		    		   .addScalar("scopeId",Hibernate.LONG)
		    		   .addScalar("name",Hibernate.STRING)
		    		   .addScalar("level",Hibernate.STRING)
		               .addScalar("total",Hibernate.LONG);
					 if(year!=null && !year.trim().isEmpty()){
						 query.setParameter("year", Integer.parseInt(year));
					 }
		       else if(fromDate!=null && toDate!=null){
		         query.setParameter("fromDate", fromDate);
		         query.setParameter("toDate", toDate);
		       }
		      
		      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
		        query.setParameterList("locationValues", locationValues);
		      }
		      
		return query.list();
		
	}
	
	public List<Object[]> getActivitysConductedCount(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId){
		
		   StringBuilder sb = new StringBuilder();
	       StringBuilder sbm = new StringBuilder();
	       StringBuilder sbe = new StringBuilder();
	       StringBuilder sbg = new StringBuilder();
	       
	       sb.append(" SELECT a1.activity_scope_id as scopeId,a.activity_name as name,a2.level as level,count(ali.activity_location_info_id) as coundectedCount ");
	       sbm.append(" from activity a,activity_scope a1,activity_location_info ali,user_address ua,activity_level a2  ");
	       sbe.append(" WHERE a1.activity_level_id = a2.activity_level_id and a.activity_id = a1.activity_id and a1.is_deleted='N' and a.is_active='Y' and "
	       		+ "ali.activity_scope_id = a1.activity_scope_id and ali.address_id = ua.user_address_id "
	       		+ "and (ali.conducted_date is not null OR ali.ivr_status='Y' ) ");
	       if(year!=null && !year.trim().isEmpty()){
		         sbe.append(" and year(a1.start_date) =:year  ");
		       }
		       else if(fromDate!=null && toDate!=null){
		         sbe.append(" and date(a1.start_date) between :fromDate and :toDate  ");
		       }
	       sbg.append(" group by ali.activity_scope_id,a1.activity_level_id ");
	       if(locationTypeId !=null && locationTypeId.longValue()>0l){
		         if(locationTypeId ==2l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.state_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==3l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.district_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==4l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.constituency_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==5l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.tehsil_id  in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==6l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append( "  and ua.panchayat_id in  (:locationValues)  " );
		           }	           
		         }
		         
		       }
		       sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());  
		       
		       Query query = getSession().createSQLQuery(sb.toString())
		    		   .addScalar("scopeId",Hibernate.LONG)
		    		   .addScalar("name",Hibernate.STRING)
		    		   .addScalar("level",Hibernate.STRING)
		               .addScalar("coundectedCount",Hibernate.LONG);
					 if(year!=null && !year.trim().isEmpty()){
						 query.setParameter("year", Integer.parseInt(year));
					 }
		       else if(fromDate!=null && toDate!=null){
		         query.setParameter("fromDate", fromDate);
		         query.setParameter("toDate", toDate);
		       }
		      
		      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
		        query.setParameterList("locationValues", locationValues);
		      }
	
		return query.list();
		
	}
	
	public List<Object[]>getConductedInfoTotal(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId){
		   StringBuilder sb = new StringBuilder();
	       StringBuilder sbm = new StringBuilder();
	       StringBuilder sbe = new StringBuilder();
	       StringBuilder sbg = new StringBuilder();
	       
	       sb.append(" SELECT a1.activity_scope_id as scopeId,a.activity_name as name,a2.level as level,count(aci.activity_conducted_info_id) as total ");
	       sbm.append(" from activity a,activity_scope a1,activity_conducted_info aci,user_address ua,activity_level a2  ");
	       sbe.append(" WHERE a1.activity_level_id = a2.activity_level_id and a.activity_id = a1.activity_id and a1.is_deleted='N' and a.is_active='Y' and "
	       		+ "aci.activity_scope_id = a1.activity_scope_id and aci.address_id = ua.user_address_id ");
	       if(year!=null && !year.trim().isEmpty()){
		         sbe.append(" and year(aci.conducted_date) =:year  ");
		       }
		       else if(fromDate!=null && toDate!=null){
		         sbe.append(" and date(aci.conducted_date) between :fromDate and :toDate  ");
		       }
	       sbg.append(" group by aci.activity_scope_id,a1.activity_level_id ");
	       if(locationTypeId !=null && locationTypeId.longValue()>0l){
		         if(locationTypeId ==2l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.state_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==3l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.district_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==4l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.constituency_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==5l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.tehsil_id  in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==6l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append( "  and ua.panchayat_id in  (:locationValues)  " );
		           }	           
		         }
		         
		       }
		       sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());  
		       
		       Query query = getSession().createSQLQuery(sb.toString())
		    		   .addScalar("scopeId",Hibernate.LONG)
		    		   .addScalar("name",Hibernate.STRING)
		    		   .addScalar("level",Hibernate.STRING)
		               .addScalar("total",Hibernate.LONG);
					 if(year!=null && !year.trim().isEmpty()){
						 query.setParameter("year", Integer.parseInt(year));
					 }
		       else if(fromDate!=null && toDate!=null){
		         query.setParameter("fromDate", fromDate);
		         query.setParameter("toDate", toDate);
		       }
		      
		      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
		        query.setParameterList("locationValues", locationValues);
		      }
	
	
		return query.list();
	}

	public List<Object[]> getConductedInfoCount(Date fromDate, Date toDate, String year, List<Long> locationValues,Long locationTypeId) {
		   StringBuilder sb = new StringBuilder();
	       StringBuilder sbm = new StringBuilder();
	       StringBuilder sbe = new StringBuilder();
	       StringBuilder sbg = new StringBuilder();
	       
	       sb.append(" SELECT a1.activity_scope_id as scopeId,a.activity_name as name,a2.level as level,count(aci.activity_conducted_info_id) as counductedCount ");
	       sbm.append(" from activity a,activity_scope a1,activity_conducted_info aci,user_address ua,activity_level a2  ");
	       sbe.append(" WHERE a1.activity_level_id = a2.activity_level_id and a.activity_id = a1.activity_id and a1.is_deleted='N' and a.is_active='Y' and "
	       		+ "aci.activity_scope_id = a1.activity_scope_id and aci.address_id = ua.user_address_id and (aci.info_cell_count is not null OR aci.ivr_status='Y' ) ");
	       if(year!=null && !year.trim().isEmpty()){
		         sbe.append(" and year(aci.conducted_date) =:year  ");
		       }
		       else if(fromDate!=null && toDate!=null){
		         sbe.append(" and date(aci.conducted_date) between :fromDate and :toDate  ");
		       }
	       sbg.append(" group by aci.activity_scope_id,a1.activity_level_id ");
	       if(locationTypeId !=null && locationTypeId.longValue()>0l){
		         if(locationTypeId ==2l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.state_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==3l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.district_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==4l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.constituency_id in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==5l){
		           
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append(" and ua.tehsil_id  in (:locationValues) ");
		           }
		           
		         }else if(locationTypeId ==6l){
		           if(locationValues !=null && locationValues.size()>0){
		        	   sbe.append( "  and ua.panchayat_id in  (:locationValues)  " );
		           }	           
		         }
		         
		       }
		       sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());  
		       
		       Query query = getSession().createSQLQuery(sb.toString())
		    		   .addScalar("scopeId",Hibernate.LONG)
		    		   .addScalar("name",Hibernate.STRING)
		    		   .addScalar("level",Hibernate.STRING)
		               .addScalar("counductedCount",Hibernate.LONG);
					 if(year!=null && !year.trim().isEmpty()){
						 query.setParameter("year", Integer.parseInt(year));
					 }
		       else if(fromDate!=null && toDate!=null){
		         query.setParameter("fromDate", fromDate);
		         query.setParameter("toDate", toDate);
		       }
		      
		      if(locationTypeId !=null && locationTypeId.longValue()>0l && locationValues !=null && locationValues.size()>0){
		        query.setParameterList("locationValues", locationValues);
		      }
	
	
		return query.list();

	}
}
