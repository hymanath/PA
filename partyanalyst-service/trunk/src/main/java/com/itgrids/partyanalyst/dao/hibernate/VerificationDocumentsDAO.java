package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVerificationDocumentsDAO;
import com.itgrids.partyanalyst.model.VerificationDocuments;

public class VerificationDocumentsDAO extends GenericDaoHibernate<VerificationDocuments, Long> implements IVerificationDocumentsDAO {

	public VerificationDocumentsDAO(){
		super(VerificationDocuments.class);
	}

}
