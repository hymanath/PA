package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.model.AlertMeekosamPetitioner;

public class AlertMeekosamPetitionerDAO extends
		GenericDaoHibernate<AlertMeekosamPetitioner, Long> implements
		IAlertMeekosamPetitionerDAO {
	public AlertMeekosamPetitionerDAO() {
		super(AlertMeekosamPetitioner.class);
		
	}
}
