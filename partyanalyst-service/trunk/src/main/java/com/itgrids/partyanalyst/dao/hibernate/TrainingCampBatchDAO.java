package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.model.TrainingCampBatch;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class TrainingCampBatchDAO extends GenericDaoHibernate<TrainingCampBatch, Long> implements ITrainingCampBatchDAO{

	public TrainingCampBatchDAO() {
		super(TrainingCampBatch.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> getBatchesForSchedule(Long scheduleId)
	{
		Query query = getSession().createQuery("select distinct model.trainingCampBatchId,model.trainingCampBatchName from TrainingCampBatch model " +
				" where model.trainingCampSchedule.trainingCampScheduleId =:scheduleId and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		query.setParameter("scheduleId", scheduleId);
		return query.list();
	}
	public List<Object[]> getAllBatchesForSchedules(List<Long> scheduleIds){
		
		Query query = getSession().createQuery("select distinct model.trainingCampBatchId,model.trainingCampBatchName from TrainingCampBatch model where model.trainingCampSchedule.trainingCampScheduleId in (:scheduleIds) and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		query.setParameterList("scheduleId", scheduleIds);
		return query.list();
		
	}
	
	/*public List<Object[]> getAllBatchesForTrainers(){
		Query query = getSession().createQuery(" select model.trainingCampBatchId,model.trainingCampBatchName," +
				" model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName, " +
				" model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampSchedule.trainingCampProgram.programName," +
				" date(model.fromDate) " +
				" from TrainingCampBatch model " +
				" where model.attendeeTypeId=2 and model.attendeeType.isDeleted='false' and model.isCancelled='false' ");
		return query.list();
	}*/
	/*public List<Long> getUpcomingBatchConfirmation(Date fromDate,Date toDate,String status){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select distinct model.trainingCampBatchId from TrainingCampBatch model " +
				" where ");
		
		if(fromDate !=null && toDate !=null){
			str.append(" (date(model.fromDate)>=:fromDate and date(model.toDate)<=:toDate) ");
		}
		
		if(status !=null){
			str.append( " and model.trainingCampSchedule.status = '"+status+"'  ");//Not Started
		}
		str.append(" and model.batchStatus.status = 'Confirmed'  ");
		
		Query query = getSession().createQuery(str.toString());
		
		return query.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTrainingCampBatchesOfSchedule(Long trainingCampScheduleId)
	{
		Query query = getSession().createQuery("SELECT model.trainingCampBatchId,model.trainingCampBatchName,model.trainingCampBatchCode,model.fromDate,model.toDate from TrainingCampBatch model " +
				" where model.trainingCampSchedule.trainingCampScheduleId = :trainingCampScheduleId and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		query.setParameter("trainingCampScheduleId",trainingCampScheduleId);
		return query.list();
	}
	
	public List<Object[]> getCompletedBatchIds(Date currDate,String type,List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.trainingCampBatchId,model.trainingCampBatchName, " +// 0 - batchId, 1 - batchName
				" model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName, " +// 2 - campId,3 - campName
				" model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampSchedule.trainingCampProgram.programName, " +// 4 - progId,5 - progName
				" model.trainingCampSchedule.trainingCampScheduleId,model.trainingCampSchedule.trainingCampScheduleCode," +//6-scheduleId,7-scheduleCode
				" date(model.fromDate),date(model.toDate), " +//8-trainingBatchfromdate,9-todate
				" date(model.trainingCampSchedule.fromDate),date(model.trainingCampSchedule.toDate) ");//10-schedulefromdate,11-todate
		sb.append(" from TrainingCampBatch model ");
		sb.append(" where ");
		if(batchIds != null && batchIds.size()>0){
			sb.append(" model.trainingCampBatchId in (:batchIds) and ");
		}
		sb.append(" model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		
		if(type.equalsIgnoreCase("completed")){
			sb.append(" and date(model.fromDate) < :currDate and date(model.toDate) < :currDate ");
		}
		else if(type.equalsIgnoreCase("running")){
			sb.append(" and date(model.fromDate) <= :currDate and  date(model.toDate) >= :currDate ");
		}
		else if(type.equalsIgnoreCase("upcoming")){
			sb.append(" and date(model.fromDate) > :currDate and date(model.toDate) > :currDate ");
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
  	        }
		if(programYearIds != null && programYearIds.size()>0){
			sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
		}
		sb.append(" and model.isCancelled = 'false' ");
		
		Query query = getSession().createQuery(sb.toString());
		
		
		query.setParameter("currDate", currDate);
		if(batchIds != null && batchIds.size()>0){
		  query.setParameterList("batchIds", batchIds);
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		}
		if(programYearIds != null && programYearIds.size()>0){
			query.setParameterList("programYearIds", programYearIds);
		}
		return query.list();
	}
	
	public Long getMaxNumbersForBacth(Long batchId)
	{
		Query query = getSession().createQuery("select model.maxMembers from TrainingCampBatch model where model.trainingCampBatchId = :batchId and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		query.setParameter("batchId", batchId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getCampDistrictsByBatchId(Long batchId,List<Long> enrollmentYrIds,List<Long> programIds){
		
		Query query=getSession().createQuery(" select model1.district.districtId,model1.district.districtName" +
		" from TrainingCampBatch model,TrainingCampDistrict model1" +
		" where model.trainingCampSchedule.trainingCamp.trainingCampId =model1.trainingCampId and model.trainingCampBatchId=:trainingCampBatchId " +
		" and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' " +
		" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programIds) " +
		" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in(:enrollmentYrIds)" +
		" order by model1.district.districtId asc");
		query.setParameter("trainingCampBatchId",batchId);
		query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		query.setParameterList("programIds", programIds);
		return query.list();
	}
	public Object[] getBatchDates(Long batchId,Date fromDate,Date toDate,List<Long> programYearIds,List<Long> enrollmentYearIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select date(model.fromDate),date(model.toDate),model.trainingCampBatchName,model.trainingCampSchedule.trainingCamp.campName " +
				"	from  TrainingCampBatch model where model.trainingCampBatchId =:trainingCampBatchId and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		
		if(enrollmentYearIds != null && enrollmentYearIds.size() >0){
			sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds) ");
		}
		if(fromDate!=null && toDate!=null){
			sb.append(" and date(model.fromDate) >= :fromDate and date(model.toDate) <= :toDate ");
		}
		if(programYearIds != null && programYearIds.size()>0){
			sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programYearIds)");
  	        }
		sb.append(" and model.isCancelled = 'false' ");
		Query query=getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size() >0){
			query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		}
	
		query.setParameter("trainingCampBatchId",batchId);
		if(programYearIds != null && programYearIds.size()>0){
			query.setParameterList("programYearIds", programYearIds);
		}
		return (Object[])query.uniqueResult();
	}
	
	public List<Object[]> getCentersAndBatchCountByProgram(Long programId,Date fromDate,Date toDate){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select   model.trainingCampSchedule.trainingCampId,model.trainingCampSchedule.trainingCamp.campName,count(distinct model.trainingCampBatchId) " +
				" from     TrainingCampBatch model " +
				" where    model.trainingCampSchedule.trainingCampProgramId=:trainingCampProgramId and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		if(fromDate!=null && toDate!=null){
			sb.append(" and date(model.fromDate) >= :fromDate and date(model.toDate) <= :toDate ");
		}
		sb.append(" and model.isCancelled = 'false' " +
				" group by model.trainingCampSchedule.trainingCampId " +
				" order by model.trainingCampSchedule.trainingCamp.campName");
		
		Query query=getSession().createQuery(sb.toString());
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		
		query.setParameter("trainingCampProgramId",programId);
		return query.list();
	}
    public Object[] getBatchCountByCamp(Long programId,Long campId,Date fromDate,Date toDate){
		
    	StringBuilder sb=new StringBuilder();

		sb.append(" select model.trainingCampSchedule.trainingCampId,model.trainingCampSchedule.trainingCamp.campName,count(distinct model.trainingCampBatchId) " +
		" from   TrainingCampBatch model " +
		" where  model.trainingCampSchedule.trainingCampProrgamId=:trainingCampProgramId and " +
		"        model.trainingCampSchedule.trainingCampId=:trainingCampId and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		if(fromDate!=null && toDate!=null){
			sb.append(" and date(model.fromDate) >= :fromDate and date(model.toDate) <= :toDate ");
		}
		sb.append(" and model.isCancelled = 'false' ");
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("trainingCampProgramId",programId);
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		
		query.setParameter("trainingCampId",campId);
		return (Object[])query.uniqueResult();
	}

	public List<Long> getBatchIds(Date startDate,Date endDate,Long stateId,List<Long> enrollmentYearIds,List<Long> programYearIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.trainingCampBatchId from TrainingCampBatch model,TrainingCampDistrict model1 " +
				" where model.trainingCampSchedule.trainingCampId=model1.trainingCampId and model.isCancelled = 'false' " +
				" and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		
		if(startDate!=null && endDate!=null){
			sb.append(" and date(model.fromDate)>=:startDate and date(model.toDate)<=:endDate ");
		}
		
		if(stateId==1l){
			sb.append(" and model1.districtId between 11 and 23 ");
		}else if(stateId==2l){
			sb.append(" and model1.districtId  between 1 and 10  ");
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in(:enrollmentYearIds)  ");
		}
		if(programYearIds != null && programYearIds.size()>0){
			sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
		}
		Query query = getSession().createQuery(sb.toString());
		if(startDate!=null && endDate!=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		}
		if(programYearIds != null && programYearIds.size()>0){
			query.setParameterList("programYearIds", programYearIds);
		}
		return query.list();
	}
	public Object[] getBatchDatesWithOutDates(Long batchId,Long enrollmentYearId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
		 
		Query query=getSession().createQuery("select date(model.fromDate),date(model.toDate) from  TrainingCampBatch model where model.trainingCampBatchId =:trainingCampBatchId and " +
				" model.isCancelled = 'false'  and model.attendeeType.isDeleted='false' "+queryStr.toString()+" ");
		
		query.setParameter("trainingCampBatchId",batchId);
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			   query.setParameter("enrollmentYearId",enrollmentYearId);
		return (Object[])query.uniqueResult();
	}
	public List<Object[]> getBatchesInfoByProgramAndCamp(Long programId,Long campId){
		
		//Date presentDate=new DateUtilService().getCurrentDateAndTime();
		
		
		Query query = getSession().createQuery("select model.trainingCampBatchId,model.trainingCampBatchName " +
				" from TrainingCampBatch model " +
				" where model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId =:programId" +
				" and  model.trainingCampSchedule.trainingCamp.trainingCampId =:campId and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1  " +
				//" and date(model.fromDate) <= :presentDate " +
				" order by date(model.fromDate) desc "); 
		
		query.setParameter("programId",programId);
		query.setParameter("campId",campId);
		//query.setParameter("presentDate",presentDate);
		return query.list();
	}
	
public List<Object[]> getBatchsInfoByProgramAndCamp(List<String> datesList,List<Long> campIdsList){
		
		if(new CommonMethodsUtilService().isListOrSetValid(datesList)){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select distinct  model.trainingCampBatchId,model.trainingCampBatchName,model.trainingCampSchedule.trainingCamp.trainingCampId, " +
					" model.trainingCampSchedule.trainingCamp.campName,date(model.fromDate),  date(model.toDate) from TrainingCampBatch model  where " +
					" model.trainingCampSchedule.trainingCamp.trainingCampId in (:campIdsList)  and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1 ");

			for (int i=0;i<datesList.size();i++) {
				if(i==0)
					queryStr.append(" and ( ");
				queryStr.append(" ( '"+datesList.get(i)+"' between date(model.fromDate) and date(model.toDate) )   ");
				if(i==datesList.size()-1)
					queryStr.append(" ) ");
				else
					queryStr.append(" or ");
			}
			
			//queryStr.append(" group by  model.trainingCampBatchId  ");
			Query query = getSession().createQuery(queryStr.toString()); 
			
			query.setParameterList("campIdsList",campIdsList);
			/*for (int i=0;i<datesList.size();i++) {
				query.setParameter("presentDate"+i+"", datesList.get(i));
			}*/
			return query.list();
			/*
			 StringBuilder sb = new StringBuilder();
			  
			  sb.append(" select distinct model.trainingCampBatch.trainingCampBatchId, model.trainingCampBatch.trainingCampBatchName, " +
			  		" model.trainingCampBatch.trainingCampSchedule.trainingCampId, model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
			  		" date(model.attendance.attendedTime) " +
				  	" from TrainingCampAttendance model where ");
			  sb.append("  model.trainingCampBatch.attendeeType.isDeleted='false' " );
			  sb.append(" and model.trainingCampBatch.isCancelled='false' ");
			  if(campIdsList != null && campIdsList.size()>0)
				  sb.append(" and  model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId in (:campIdsList) ");
					for(int i=0;i<datesList.size();i++){
						if(i==0)
							sb.append(" and date(model.attendance.attendedTime) in (  ");
						
							sb.append(" '"+datesList.get(i)+"'");
						if(i<datesList.size()-1)
							sb.append(",");
						else
							sb.append(" )");
					}
						
			  Query query = getSession().createQuery(sb.toString());
			  if(campIdsList != null && campIdsList.size()>0)
				  query.setParameterList("campIdsList", campIdsList);
			 // query.setParameterList("datesList", datesList);
			  
			  return query.list();*/
		}
		
		return null;
	}
	

	public List<TrainingCampBatch> getAllRecordsByBatchId(Long batchId)
	{
		Query query = getSession().createQuery(" select model from TrainingCampBatch model where model.trainingCampBatchId > :batchId and model.isCancelled = 'false' and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' order by model.trainingCampBatchId ");
		
		query.setParameter("batchId", batchId);
		
		return query.list();
	}
	
	public List<Object[]> getCampConstsByBatchId(Long batchId,List<Long> enrollmentYrIds,List<Long> programIds){
		
		Query query=getSession().createQuery(" select c.constituencyId,c.name " +
		" from TrainingCampBatch model,TrainingCampDistrict model1,Constituency c " +
		" where model.trainingCampSchedule.trainingCamp.trainingCampId =model1.trainingCampId and model.trainingCampBatchId=:trainingCampBatchId and model.isCancelled = 'false' " +
		"  and model1.district.districtId=c.district.districtId and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' " +
		" and c.electionScope.electionScopeId=2 and c.deformDate is null " +
		" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programIds)" +
		" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in(:enrollmentYrIds) " +
		" order by c.constituencyId asc");
		query.setParameter("trainingCampBatchId",batchId);
		query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		query.setParameterList("programIds", programIds);
		return query.list();
	}
	
	public List<Object[]> getBatcheIdsForACampOrProgram(List<Long> programIds,Long campId,Date fromDate,Date toDate,String type,List<Long> enrollmentYearIds){
		StringBuilder sb = new StringBuilder();
		Date currDate=new DateUtilService().getCurrentDateAndTime();
		
		sb.append(" select distinct model.trainingCampBatchId,model.trainingCampBatchName,model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName " +
				" from TrainingCampBatch model,TrainingCampDistrict model1  " +
				" where model.trainingCampSchedule.trainingCampId=model1.trainingCampId and model.isCancelled = 'false' and model1.districtId between 11 and 23 " +
				" and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		
		if(campId!=null && campId>0l){
			sb.append(" and model.trainingCampSchedule.trainingCamp.trainingCampId=:campId and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIds) ");
		}else if(programIds!=null && programIds.size() > 0){
			sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIds) ");
		}
		
		if(fromDate!=null && toDate!=null){
			sb.append(" and date(model.fromDate)>=:fromDate and date(model.toDate)<=:toDate ");
		}
			
		if(type.equalsIgnoreCase("c")){
			sb.append(" and date(model.fromDate) < :currDate and date(model.toDate) < :currDate ");
		}
		else if(type.equalsIgnoreCase("r")){
			sb.append(" and date(model.fromDate) <= :currDate and  date(model.toDate) >= :currDate ");
		}
		else if(type.equalsIgnoreCase("u")){
			sb.append(" and date(model.fromDate) > :currDate and date(model.toDate) > :currDate ");
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
  	        }
		
		Query query = getSession().createQuery(sb.toString());
		
		if(campId>0l){
			query.setParameter("campId", campId);
			query.setParameterList("programIds", programIds);
		}else if(programIds.size() > 0){
			query.setParameterList("programIds", programIds);
		}
		
		if(fromDate!=null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
		if(!type.equalsIgnoreCase("all")){
			query.setParameter("currDate", currDate);
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		}
		return query.list();
	}
	
	public List<Object[]> getBatchAndCampNameForABatch(Long batchId,List<Long> enrollmentYrIds,List<Long> programIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.trainingCampBatchName,model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName " +
				" from TrainingCampBatch model " +
				" where model.trainingCampBatchId=:batchId and model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' ");
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYrIds) ");
		}
		
		if(programIds != null && programIds.size() >0){
			sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIds) ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("batchId", batchId);
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		}
		if(programIds != null && programIds.size() >0){
			query.setParameterList("programIds", programIds);
		}
		return query.list();
	}
	
	public List<Object[]> getAllCentersForTrainers(){
		Query query = getSession().createQuery(" select model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName, " +
				" model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampSchedule.trainingCampProgram.programName " +
				" from TrainingCampBatch model " +
				" where model.attendeeTypeId=2 and model.attendeeType.isDeleted='false' and model.isCancelled='false' ");
		return query.list();
	}
	
	public List<Object[]> getBatches(String type,Long programId,Long campId){
		StringBuilder sb = new StringBuilder();
		Date currDate=new DateUtilService().getCurrentDateAndTime();
		
		sb.append(" select model.trainingCampBatchId,model.trainingCampBatchName " +
				" from TrainingCampBatch model " +
				" where model.attendeeType.attendeeTypeId=1 and model.attendeeType.isDeleted='false' and model.isCancelled='false' ");
		
		if(type.equalsIgnoreCase("c")){
			sb.append(" and date(model.fromDate) < :currDate and date(model.toDate) < :currDate ");
		}
		else if(type.equalsIgnoreCase("r")){
			sb.append(" and date(model.fromDate) <= :currDate and  date(model.toDate) >= :currDate ");
		}
		else if(type.equalsIgnoreCase("u")){
			sb.append(" and date(model.fromDate) > :currDate and date(model.toDate) > :currDate ");
		}
		
		sb.append(" and model.trainingCampSchedule.trainingCamp.trainingCampId=:campId and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId=:programId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(type.equalsIgnoreCase("c") || type.equalsIgnoreCase("r") || type.equalsIgnoreCase("u")){
			query.setParameter("currDate", currDate);
		}
		
		query.setParameter("programId", programId);
		query.setParameter("campId", campId);
		
		return query.list();
	}
	
	public List<String> getBatchNameWithDateAndCamp(Date date,Long campId,List<Long> enrollmentYearIds,List<Long> programYearIds){
		Query query = getSession().createQuery("select distinct model.trainingCampBatchName " +
				"from TrainingCampBatch model " +
				"where model.trainingCampSchedule.trainingCampId=:campId " +
				"and :date between date(model.fromDate) and date(model.toDate) " +
				" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds) " +
				" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in(:enrollmentYearIds)" +
				"and model.isCancelled='false' ");
		query.setParameter("date", date);
		query.setParameter("campId", campId);
		query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		query.setParameterList("programYearIds", programYearIds);
		return query.list();
	}
	
	public List<String> getExcudingTdpCadreIdsList(List<Long> enrollmentYearIds,List<Long> programYearIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.tdp_cadre_id from training_camp_staff model,training_camp_schedule model1 " +
				" where  model.training_camp_program_id = model1.training_camp_program_id ");
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			sb.append(" and model1.enrollment_year_id in (:enrollmentYearIds)");
  	        }
		if(programYearIds != null && programYearIds.size()>0){
			sb.append(" and model.training_camp_program_id in(:programYearIds)");
		}
		Query query = getSession().createSQLQuery(sb.toString());
		if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			query.setParameterList("enrollmentYearIds",enrollmentYearIds);
		}
		if(programYearIds != null && programYearIds.size()>0){
			query.setParameterList("programYearIds",programYearIds);
		}
		return query.list();
	}
	
	public List<Object[]> getBatchInviteeDetails(List<Long> batchIds,List<Long> enrollmentYrIds,List<Long> programYearIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" " +
		 " select DISTINCT TC.camp_name as campname ,TCB.training_camp_batch_id as batchId ,TCB.training_camp_batch_code as batchCode," +
		 "        date(TCB.from_date) as fromdate,date(TCB.to_date) as toDate ,TCBA.tdp_cadre_id as cadreId" +
		 " from   training_camp_batch TCB left join training_camp_batch_attendee TCBA  on TCBA.training_camp_batch_id=TCB.training_camp_batch_id and TCBA.is_deleted = 'false' " +
		 "        join training_camp_schedule TCS on TCB.training_camp_schedule_id = TCS.training_camp_schedule_id " +
		 "        join training_camp TC on TCS.training_camp_id = TC.training_camp_id  " +
		 " where   TCB.training_camp_batch_id in (:batchIds) and TCB.is_cancelled = 'false'  and TCB.attendee_type_id = 1 and TCBA.is_deleted='false'  " );
		
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			sb.append(" and TCS.enrollment_year_id in (:enrollmentYrIds) ");
		}
		if(programYearIds != null && programYearIds.size() >0){
		      sb.append(" and TCS.training_camp_program_id in (:programYearIds) ");
		    }
		sb.append(" order by TCB.training_camp_batch_id asc");
		Query query=getSession().createSQLQuery(sb.toString())
		 .addScalar("campname",Hibernate.STRING)
		 .addScalar("batchId",Hibernate.LONG)
		 .addScalar("batchCode",Hibernate.STRING)
		 .addScalar("fromdate",Hibernate.DATE)
		 .addScalar("toDate",Hibernate.DATE)
		 .addScalar("cadreId",Hibernate.LONG);
		query.setParameterList("batchIds",batchIds);
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0){
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		}
		if(programYearIds != null && programYearIds.size() >0){
	    	query.setParameterList("programYearIds", programYearIds);
	    }
	    return query.list();
	}
	public List<Object[]> getFromAndToDate(Long programId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select TCB.fromDate, TCB.toDate from TrainingCampBatch TCB " +
						" where " +
						" TCB.trainingCampSchedule.trainingCampProgram.trainingCampProgramId = :programId and " +
						" TCB.isCancelled = 'false' and TCB.attendeeTypeId= 1 ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("programId", programId);
		return query.list();
	}
	public List<Long> getRunningBatchIds(Date todayDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.trainingCampBatchId" +
				" from TrainingCampBatch model" +
				" where model.attendeeType.attendeeTypeId = 1 and model.isCancelled = 'false'");
		if(todayDate != null)
				sb.append(" and model.fromDate <= :todayDate and model.toDate >= :todayDate");
		
		Query query = getSession().createQuery(sb.toString());
		if(todayDate != null)
			query.setDate("todayDate", todayDate);
		
		return query.list();
		
	}
	
	public List<Object[]> getTraingCampBatchDetaisByDatesAndProgramIdsAndEnroleMentIds(Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.trainingCampBatchId,model.trainingCampBatchCode,");// 0  bastch Id, 1 batch cod
		sb.append("model.trainingCampBatchName," ); // 2 batch name
		sb.append("date(model.fromDate),date(model.toDate),");// 3  from date   4  todate
		sb.append("model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,");			//  5 programm id
		sb.append("model.trainingCampSchedule.trainingCampProgram.programName,"); 	//6  programm name
		sb.append("model.trainingCampSchedule.trainingCamp.trainingCampId,");     		// 7   campId
		sb.append("model.trainingCampSchedule.trainingCamp.campName, ");	//8   camp name
		sb.append(" model.trainingCampSchedule.trainingCampScheduleId,model.trainingCampSchedule.trainingCampScheduleCode,model.trainingCampBatchTypeId ");   // 9 scheduleid, 10 schedule code,11 batchTypeId //		
		sb.append("from TrainingCampBatch model where model.isCancelled = 'false' ");
		if(fromDate != null && toDate !=null ){
			sb.append(" and ( (model.fromDate between :fromDate and  :toDate) OR (model.toDate between :fromDate and  :toDate)) ");
		}
		
		if(enrollmentYearIds != null && enrollmentYearIds.size() >0){
			sb.append(" and model.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds) ");
		}
		if(programYearIds != null && programYearIds.size() >0){
			sb.append(" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)   ");
		}
		sb.append(" OR (model.trainingCampBatchTypeId = 2 ) ");
		
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate != null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(enrollmentYearIds != null && enrollmentYearIds.size() >0){
			query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		}
		if(programYearIds != null && programYearIds.size() >0){
			query.setParameterList("programYearIds", programYearIds);
		}	
		return query.list();
		
	}
	
	public List<Object[]> getMinAndMaxDatesOfTraingCamp(){
		StringBuilder sb = new StringBuilder();
		sb.append("select date(min(model.fromDate)),date(max(model.toDate))" +
				" from TrainingCampBatch model where model.isCancelled = 'false' ");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getProgramIdsAndNameBasedOnBatchList(List<Long> batchIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.trainingCampBatchId, model.trainingCampBatchCode,");// 0  batch Id, 1 batch code
		sb.append("model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,");			//  2 program id
		sb.append("model.trainingCampSchedule.trainingCampProgram.programName, model.trainingCampBatchTypeId "); 	//3  program name,4 batchtypeId
		sb.append("from TrainingCampBatch model where model.isCancelled = 'false' ");
		if(batchIdsList != null && batchIdsList.size() >0){
			sb.append(" and model.trainingCampBatchId in(:batchIdsList) ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(batchIdsList != null && batchIdsList.size() >0){
			query.setParameterList("batchIdsList", batchIdsList);
		}	
		return query.list();
	}
	public List<Object[]> getProgramIdAndBatchIdListByPassingEnrollmentYearId(Long yearId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.trainingCampBatchId, model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId " +
				  " from TrainingCampBatch model " +
				  " where model.trainingCampBatchTypeId = 2 " +
				  " and model.trainingCampSchedule.enrollmentYearId = :yearId ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("yearId", yearId);
		return query.list(); 
	}
}