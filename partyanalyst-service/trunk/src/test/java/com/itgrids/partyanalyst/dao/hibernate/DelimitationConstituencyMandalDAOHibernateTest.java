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
	
/*	public void testMandalsForAConstituency(){
		List<Tehsil> tehsil = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(new Long(3382));
		System.out.println(tehsil.size());
	}*/
	
	/*public void testGetStateDistConstituencyMandalByMandalID(){
		List rawData = delimitationConstituencyMandalDAO.getStateDistConstituencyMandalByMandalID(871l);
		for(int i=0; i<rawData.size(); i++){
			System.out.println(((Object[])rawData.get(i))[7]+"--"+((Object[])rawData.get(i))[5]+" "+((Object[])rawData.get(i))[4]);
		}
	}
	
	public void testGetMandalsOfConstituency(){
		List tehsil = delimitationConstituencyMandalDAO.getMandalsOfConstituency(232l);
		for(int i=0;i<tehsil.size(); i++)
			System.out.println(((Object[])tehsil.get(i))[0]+"--"+((Object[])tehsil.get(i))[1]+"--"+((Object[])tehsil.get(i))[2]+"--"+((Object[])tehsil.get(i))[3]);
	}*/
	
	/*public void testGetLatestMandalDetailsForAConstituencies(){
		List list = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituencies("231,232");
		System.out.println(list.size());
		//System.out.println(((Object[])list.get(3))[1]);
	}*/
	
	public void testGetLatestAssemblyConstitueciesOfTehsil(){
		List list = delimitationConstituencyMandalDAO.getLatestAssemblyConstitueciesOfTehsil(836l);
		System.out.println(list.size());
	}
	
}
