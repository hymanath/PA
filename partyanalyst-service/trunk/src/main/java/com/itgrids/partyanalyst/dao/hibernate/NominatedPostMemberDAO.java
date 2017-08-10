package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostMemberDAO;
import com.itgrids.partyanalyst.model.NominatedPostMember;
import com.itgrids.partyanalyst.utils.IConstants;

public class NominatedPostMemberDAO extends GenericDaoHibernate<NominatedPostMember, Long> implements INominatedPostMemberDAO{

	public NominatedPostMemberDAO() {
		super(NominatedPostMember.class);
	}

	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType,Long searchLevelId,Long searchLevelValue){
		
		StringBuilder str = new StringBuilder();
		/*
		//Old
		str.append(" SELECT model.nominatedPostPosition.department.departmentId,model.nominatedPostPosition.department.departmentName," +
				"  model.nominatedPostPosition.board.boardId,model.nominatedPostPosition.board.boardName " +
				" FROM NominatedPostMember model " +
				" WHERE model.isDeleted = 'N'" +
				" and model.nominatedPostPosition.isDeleted = 'N' " );
		//New
		str.append(" SELECT model.nominatedPostMember.nominatedPostPosition.department.departmentId," +
				"  model.nominatedPostMember.nominatedPostPosition.department.departmentName," +
				"  model.nominatedPostMember.nominatedPostPosition.board.boardId,model.nominatedPostMember.nominatedPostPosition.board.boardName " +
				" FROM NominatedPostApplication model1 , NominatedPost model " +
				" WHERE  model1.nominationPostCandidateId = model.nominationPostCandidateId" +
				" and model.isDeleted = 'N' " +
				" and model.nominatedPostMember.isDeleted ='N' " +
				" and  model1.isDeleted = 'N'" +
				" and model1.nominationPostCandidate.isDeleted ='N' ");
		
		if(boardLevelId !=null && boardLevelId>0){
			str.append(" and model.nominatedPostMember.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and model.nominatedPostMember.locationValue in (:levelValue) ");
		}
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" and model1.applicationStatus.status = :notYet ");
		}
		
		str.append(" group by model.nominatedPostMember.nominatedPostPosition.department.departmentId,model.nominatedPostMember.nominatedPostPosition.board.boardId  ");
			
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(levelValue !=null && levelValue.size()>0){
			query.setParameterList("levelValue", levelValue);
		}
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}
		*/
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
		str.append(" SELECT distinct departments.departmentId,departments.deptName," +
				"  board.boardId,board.boardName " +
				" FROM NominatedPostApplication model " +
				" left join model.board board " +
				" left join model.departments departments " +
				" WHERE model.isDeleted = 'N'" );
		if(boardLevelId !=null && boardLevelId.longValue()>0L){
			str.append(" and model.boardLevel.boardLevelId =:boardLevelId ");
	}
		}
		else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" SELECT distinct departments.departmentId,departments.deptName," +
					"  board.boardId,board.boardName " +
					" FROM NominatedPostFinal model " +
					" left join model.nominatedPostMember.nominatedPostPosition.board board " +
					" left join model.nominatedPostMember.nominatedPostPosition.departments departments " +
					" WHERE  model.isDeleted='N' and "+
				" model.nominatedPostMember.isDeleted='N' and "+
				" model.nominatedPostMember.nominatedPostPosition.isDeleted='N' " );
			if(boardLevelId !=null && boardLevelId.longValue()>0L){
				str.append(" and model.nominatedPostMember.boardLevel.boardLevelId =:boardLevelId ");
		}
			}
		
		
		/*if(levelValue !=null && levelValue.size()>0){
			str.append(" and model.locationValue in (:levelValue) ");
		}*/
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" and model.applicationStatus.status in ("+IConstants.NOMINATED_APPLIED_STATUS+")");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" and model.applicationStatus.applicationStatusId not in ("+IConstants.NOMINATED_POST_NOT_RUNNING_STATUS+") ");
		}
		//,Long searchlevelId,Long searchLevelValue
			
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			if(searchLevelId != null && searchLevelId.longValue()>0L){
				if(searchLevelId.longValue() == 1L)
					str.append(" and model.locationValue  = :searchLevelValue ");
				else if(searchLevelId.longValue() ==2L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.address.state.stateId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.address.district.districtId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.address.constituency.constituencyId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.address.tehsil.tehsilId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.address.localElectionBody.localElectionBodyId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.address.panchayatId =:searchLevelValue ");
			}
		}
		else 	if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			if(searchLevelId != null && searchLevelId.longValue()>0L){
				if(searchLevelId.longValue() == 1L)
					str.append(" and model.locationValue  = :searchLevelValue ");
				else if(searchLevelId.longValue() ==2L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.state.stateId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==3L && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.district.districtId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==4L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.constituency.constituencyId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==5L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.tehsil.tehsilId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==6L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.localElectionBody.localElectionBodyId =:searchLevelValue ");
				else if(searchLevelId.longValue() ==7L  && searchLevelValue != null && searchLevelValue.longValue()>0L)
					str.append(" and model.nominatedPostMember.address.panchayatId =:searchLevelValue ");
			}
		}
		str.append(" group by departments.departmentId,board.boardId  ");
		str.append(" order by departments.deptName");
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(searchLevelValue != null && searchLevelValue.longValue()>0L)
			query.setParameter("searchLevelValue", searchLevelValue);
		/*if(levelValue !=null && levelValue.size()>0){
			query.setParameterList("levelValue", levelValue);
		}*/
		/*if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") )){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}else if(statusType !=null && (statusType.trim().equalsIgnoreCase("running"))){
			query.setParameter("running",IConstants.NOMINATED_POST_NOT_RUNNING_STATUS);
		}*/
		
		return query.list();
	}
	
	public Long getNominatedPostMemberId(Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId){
		Query query = getSession().createQuery("select model.nominatedPostMemberId" +
											" from NominatedPostMember model" +
											" where model.boardLevel.boardLevelId = :levelId" +
											" and model.locationValue = :levelValue" +
											" and model.nominatedPostPosition.departments.departmentId = :deptId" +
											" and model.nominatedPostPosition.board.boardId = :boardId" +
											" and model.nominatedPostPosition.position.positionId = :positionId" +
											" and model.isDeleted = 'N' and model.nominatedPostPosition.isDeleted = 'N'");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("deptId", deptId);
		query.setParameter("boardId", boardId);
		query.setParameter("positionId", positionId);
		
		return (Long) query.uniqueResult();
	}

	/*
	 * Swadhin
	 */
	public List<Object[]> getPositionList(){
		Query query = getSession().createQuery(" select distinct model.nominatedPostPosition.position.positionId, model.nominatedPostPosition.position.positionName" +
											   " from NominatedPostMember model" +
											   " where " +
											   " model.isDeleted = 'N' and " +
											   " model.nominatedPostPosition.isDeleted = 'N'  order by model.nominatedPostPosition.position.orderNo ");  
		return query.list();		
	}
	/*
	 * Swadhin
	 */
	public List<Object[]> getLocationLevelList(){
		Query query = getSession().createQuery("select distinct model.boardLevel.boardLevelId, model.boardLevel.level " +
											   " from NominatedPostMember model " +
											   " where " +
											   " model.isDeleted = 'N' and model.nominatedPostPosition.isDeleted = 'N' ");
		return query.list();
	}
	/*
	 * Swadhin
	 */
	public List<Object[]> getDepartmentList(){
		Query query = getSession().createQuery("select distinct model.nominatedPostPosition.departments.departmentId, model.nominatedPostPosition.departments.deptName " +
											   " from NominatedPostMember model " +
											   " where " +
											   " model.isDeleted = 'N' and " +
											   " model.nominatedPostPosition.isDeleted = 'N' order by model.nominatedPostPosition.departments.deptName ");
		return query.list();
	}
	/*
	 * Swadhin
	 */
	public List<Object[]> getBoardList(){
		Query query = getSession().createQuery("select distinct model.nominatedPostPosition.board.boardId, model.nominatedPostPosition.board.boardName " +
				   							   " from NominatedPostMember model " +
				   							   " where " +
				   							   " model.isDeleted = 'N' and " +
				   							   " model.nominatedPostPosition.isDeleted = 'N' ");
		return query.list();
	}
	

	/*
	 * Swadhin
	 */ 
	public List<Object[]> getDepartmentList(Long boardLevelId){ 
		StringBuilder str = new StringBuilder(); 
		str.append(" select distinct model.nominatedPostPosition.departments.departmentId, model.nominatedPostPosition.departments.deptName " +
				   " from NominatedPostMember model " +
				   " where " );
		if(boardLevelId > 0l){
			str.append(" model.boardLevel.boardLevelId = :boardLevelId and ");
		}
		str.append("  model.isDeleted = 'N' and " +
				   " model.nominatedPostPosition.isDeleted = 'N'");
		Query query = getSession().createQuery(str.toString());
		if(boardLevelId > 0l){
			query.setParameter("boardLevelId", boardLevelId);   
		}
		return query.list();  
	}
	/* 
	 * Swadhin
	 */
	public List<Object[]> getBoardsList(List<Long> deptId,Long boardLevelId){
		StringBuilder str = new StringBuilder(); 
		str.append(" select distinct model.nominatedPostPosition.board.boardId, model.nominatedPostPosition.board.boardName " +
				   " from NominatedPostMember model " +
				   " where " );
		if(deptId != null && deptId.size() > 0l){
			str.append(" model.nominatedPostPosition.departments.departmentId in (:deptId) and ");
		}
		str.append(" model.isDeleted = 'N' and " +
				   " model.nominatedPostPosition.isDeleted = 'N' and model.boardLevelId=:boardLevelId order by model.nominatedPostPosition.board.boardName ");  
		Query query = getSession().createQuery(str.toString());
		if(deptId != null && deptId.size() > 0l){
			query.setParameterList("deptId", deptId);   
		}		  
		if(boardLevelId != null && boardLevelId.longValue()>0L)
			query.setParameter("boardLevelId", boardLevelId);
		
		return query.list();  
	}
	
	public List<Object[]> getTotalBoardsAndCorpIdsByMembrIdsList (List<Long> memberIdsList){
		Query query = getSession().createQuery("select distinct model.nominatedPostMemberId, model.nominatedPostPosition.departments.departmentId, " +
				" model.nominatedPostPosition.departments.deptName,  model.nominatedPostPosition.board.boardId, model.nominatedPostPosition.board.boardName " +
				   " from NominatedPostMember model where model.isDeleted='N' and model.nominatedPostPosition.isDeleted = 'N' and model.nominatedPostMemberId in (:memberIdsList)");
		query.setParameterList("memberIdsList", memberIdsList);
		return query.list();
	}
	public List<Object[]> getLocationByDepartment(Long levelId,Long departmentId,Long boardId,Long positionId){
		StringBuilder str = new StringBuilder();
		str.append(" select model.nominatedPostMemberId,state.stateId,district.districtId, " +
				 "   constituency.constituencyId,tehsil.tehsilId, " +
				 "   panchayat.panchayatId,localElectionBody.localElectionBodyId ,ward.constituencyId from NominatedPostMember model " +
				 " left join  model.address.state state " +
				 " left join model.address.district district " +
				 " left join model.address.constituency constituency " +
				 " left join model.address.tehsil tehsil " +
				 " left join model.address.localElectionBody localElectionBody " +
				 " left join model.address.ward ward " +
				 " left join model.address.panchayat panchayat  " );
		str.append(" where ");
		if(levelId != null && levelId.longValue()>0){
			if(levelId.equals(5)){
				str.append(" model.boardLevel.boardLevelId  in (5,6) ");
			}else if(levelId.equals(7)){
				str.append("  model.boardLevel.boardLevelId  in (7,8) ");
			}else{
				str.append("  model.boardLevel.boardLevelId  =:levelId ");
			}
		}
		if(departmentId != null && departmentId.longValue()>0){
			str.append(" and model.nominatedPostPosition.departments.departmentId  =:departmentId ");
		}
		if(boardId != null && boardId.longValue()>0){
			str.append(" and model.nominatedPostPosition.board.boardId  =:boardId ");
		}
		if(positionId != null && positionId.longValue()>0){
			str.append(" and model.nominatedPostPosition.position.positionId  =:positionId ");
		}
		Query query = getSession().createQuery(str.toString());
		if(levelId != null && levelId.longValue()>0 && !levelId.equals(5) && !levelId.equals(7)){
			query.setParameter("levelId", levelId);
		}
		if(departmentId != null && departmentId.longValue()>0){
			query.setParameter("departmentId", departmentId);
		}
		if(boardId != null && boardId.longValue()>0){
			query.setParameter("boardId", boardId);
		}
		if(positionId != null && positionId.longValue()>0){
			query.setParameter("positionId", positionId);
		}
		return query.list();
		
	}
	public List<Object[]> getNominatedPostPositionDtls(Long departmentId,Long boardId,Long positionId) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.nominatedPostMemberId,model.boardLevelId,model.locationValue,model.addressId " +
		 		" from NominatedPostMember model " +
		 		" where model.nominatedPostPosition.departmentId=:departmentId " +
		 		" and model.nominatedPostPosition.boardId=:boardId " +
		 		" and model.nominatedPostPosition.positionId=:positionId " +
		 		" and model.nominatedPostPosition.isDeleted='N' and model.isDeleted='N' ");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("departmentId", departmentId);
		  query.setParameter("boardId", boardId);
		  query.setParameter("positionId", positionId);
		  return query.list();
	}
}
