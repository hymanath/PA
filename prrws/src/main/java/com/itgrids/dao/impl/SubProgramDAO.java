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
		
		if(govtSchemesId == null || govtSchemesId.longValue() == 0L){
			  StringBuilder sb = new StringBuilder();
			    sb.append(" select distinct model.subProgramId,model.programName from SubProgram model "+
			               " where isDeleted= 'N' order by model.orderNo asc  ");
			    Query query = getSession().createQuery(sb.toString());
			    return query.list(); 
		}
		else if (govtSchemesId.longValue() > 0L){
			StringBuilder sb = new StringBuilder();
		    sb.append(" select distinct model.subProgram.subProgramId,model.subProgram.programName from FundSanction model "+
		               " where isDeleted= 'N' and model.govtSchemeId =:govtSchemesId order by model.subProgram.orderNo asc  ");
		    Query query = getSession().createQuery(sb.toString());
		    query.setParameter("govtSchemesId",govtSchemesId);
		    return query.list(); 
		}
		return null;
	  }
}
