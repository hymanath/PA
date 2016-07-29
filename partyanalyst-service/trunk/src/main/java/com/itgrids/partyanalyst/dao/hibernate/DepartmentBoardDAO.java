package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDepartmentBoardDAO;
import com.itgrids.partyanalyst.model.DepartmentBoard;

public class DepartmentBoardDAO extends GenericDaoHibernate<DepartmentBoard, Long> implements IDepartmentBoardDAO{

	public DepartmentBoardDAO() {
		super(DepartmentBoard.class);
		
	}

	public List<Object[]> getDepartmentBoardByDeptId(Long deapartmentId){
		
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append("select  model.board.boardId,model.board.boardName,model.departmentBoardId from DepartmentBoard model where model.isDeleted = 'N' ");
		    
		   if(deapartmentId != null && deapartmentId.longValue() > 0){
			   queryStr.append(" and model.departments.departmentId =:deapartmentId");
		   }
		   
		  Query query = getSession().createQuery(queryStr.toString());
		  
		  if(deapartmentId != null && deapartmentId.longValue() > 0){
			  query.setParameter("deapartmentId", deapartmentId);  
		  }
		return query.list();
	}
}
