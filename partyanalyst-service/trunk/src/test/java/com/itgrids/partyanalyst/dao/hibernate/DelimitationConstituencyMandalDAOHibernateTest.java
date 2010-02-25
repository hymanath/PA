package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.model.Tehsil;

public class DelimitationConstituencyMandalDAOHibernateTest extends
		BaseDaoTestCase {

	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	
	public void testMandalsForAConstituency(){
		List<Tehsil> tehsil = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(new Long(3382));
		System.out.println(tehsil.size());
	}
}
