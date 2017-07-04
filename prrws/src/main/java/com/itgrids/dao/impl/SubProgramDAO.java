package com.itgrids.dao.impl;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ISubProgramDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.SubProgram;
@Repository
public class SubProgramDAO extends GenericDaoHibernate<SubProgram, Long> implements ISubProgramDAO {
	public SubProgramDAO(){
		super(SubProgram.class);
	}
	
	public List<Object[]> getGovtSubProgramsDetails(Long govtSchemesId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(" select model.subProgramId,model.programName from SubProgram model "+
	               " where isDeleted= 'N' ");
	    Query query = getSession().createQuery(sb.toString());
	    query.setParameter("govtSchemesId",govtSchemesId);
	    return query.list(); 
	    
	  }
}
