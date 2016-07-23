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

	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue,String statusType){
		
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
		
		str.append(" SELECT model.departments.departmentId,model.departments.deptName," +
				"  model.board.boardId,model.board.boardName " +
				" FROM NominatedPostApplication model " +
				" WHERE model.isDeleted = 'N'" );
		if(boardLevelId !=null && boardLevelId>0){
			str.append(" and model.boardLevel.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and model.locationValue in (:levelValue) ");
		}
		
		if(statusType !=null && statusType.trim().equalsIgnoreCase("notYet")){
			str.append(" and model.applicationStatus.status = :notYet ");
		}else if(statusType !=null && statusType.trim().equalsIgnoreCase("running")){
			str.append(" and model.applicationStatus.status != :notYet ");
		}
		
		str.append(" group by model.departments.departmentId,model.board.boardId  ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(levelValue !=null && levelValue.size()>0){
			query.setParameterList("levelValue", levelValue);
		}
		if(statusType !=null && (statusType.trim().equalsIgnoreCase("notYet") || statusType.trim().equalsIgnoreCase("running")) ){
			query.setParameter("notYet",IConstants.NOMINATED_APPLIED_STATUS);
		}
		
		return query.list();
	}
	
	public Long getNominatedPostMemberId(Long levelId,Long levelValue,Long deptId,Long boardId,Long positionId){
		Query query = getSession().createQuery("select model.nominatedPostMemberId" +
											" from NominatedPostMember model" +
											" where model.boardLevel.boardLevelId = :levelId" +
											" and model.locationValue = :levelValue" +
											" and model.nominatedPostPosition.department.departmentId = :deptId" +
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
}
