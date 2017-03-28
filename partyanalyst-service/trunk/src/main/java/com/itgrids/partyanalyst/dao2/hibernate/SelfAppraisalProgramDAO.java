package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalProgramDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalProgram;

public class SelfAppraisalProgramDAO extends GenericDaoHibernate<SelfAppraisalProgram, Long> implements
		ISelfAppraisalProgramDAO {
       public SelfAppraisalProgramDAO(){
    	   super(SelfAppraisalProgram.class);
       }
}
