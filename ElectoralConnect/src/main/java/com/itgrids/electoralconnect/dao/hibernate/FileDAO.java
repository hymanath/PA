package com.itgrids.electoralconnect.dao.hibernate;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IFileDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.model.File;
import com.itgrids.electoralconnect.model.UserRoles;


public class FileDAO extends GenericDaoHibernate<File, Long> implements IFileDAO{

	public FileDAO() {
		super(File.class);
	}

}
