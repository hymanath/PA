package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostMemberDAO;
import com.itgrids.partyanalyst.model.NominatedPostMember;

public class NominatedPostMemberDAO extends GenericDaoHibernate<NominatedPostMember, Long> implements INominatedPostMemberDAO{

	public NominatedPostMemberDAO() {
		super(NominatedPostMember.class);
	}

	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT model.nominatedPostPosition.department.departmentId,model.nominatedPostPosition.department.departmentName," +
				"  model.nominatedPostPosition.board.boardId,model.nominatedPostPosition.board.boardName " +
				" FROM NominatedPostMember model " +
				" WHERE model.isDeleted = 'N'" +
				" and model.nominatedPostPosition.isDeleted = 'N' " );
		
		if(boardLevelId !=null && boardLevelId>0){
			str.append(" and model.boardLevelId =:boardLevelId ");
		}
		if(levelValue !=null && levelValue.size()>0){
			str.append(" and model.locationValue in (:levelValue) ");
		}
		
		str.append(" group by model.nominatedPostPosition.department.departmentId,model.nominatedPostPosition.board.boardId  ");
			
		Query query = getSession().createQuery(str.toString());
		
		if(boardLevelId !=null && boardLevelId>0){
			query.setParameter("boardLevelId", boardLevelId);
		}
		if(levelValue !=null && levelValue.size()>0){
			query.setParameterList("levelValue", levelValue);
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
