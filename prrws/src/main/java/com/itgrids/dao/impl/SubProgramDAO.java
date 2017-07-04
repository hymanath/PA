package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.dao.ISubProgramDAO;
import com.itgrids.model.SubProgram;

public class SubProgramDAO extends GenericDaoHibernate<SubProgram, Long> implements ISubProgramDAO {
	public SubProgramDAO(){
		super(SubProgram.class);
	}
}
