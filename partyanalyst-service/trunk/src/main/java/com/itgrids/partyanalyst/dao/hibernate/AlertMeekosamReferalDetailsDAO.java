package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.AlertMeekosamReferalDetails;

import com.itgrids.partyanalyst.dao.IAlertMeekosamReferalDetailsDAO;

public class AlertMeekosamReferalDetailsDAO extends GenericDaoHibernate<AlertMeekosamReferalDetails, Long> implements IAlertMeekosamReferalDetailsDAO{

	public AlertMeekosamReferalDetailsDAO() {
		super(AlertMeekosamReferalDetails.class);
	}

}
