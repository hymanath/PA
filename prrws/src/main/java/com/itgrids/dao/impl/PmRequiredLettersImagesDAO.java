package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRequiredLettersImagesDAO;
import com.itgrids.model.PmRequiredLettersImages;

@Repository
public class PmRequiredLettersImagesDAO extends GenericDaoHibernate<PmRequiredLettersImages, Long> implements IPmRequiredLettersImagesDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PmRequiredLettersImagesDAO() {
		super(PmRequiredLettersImages.class);
	}
	}