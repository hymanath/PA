package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
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
	}*/
	
	/*public void testGetLatestMandalDetailsForAConstituencies(){
		List list = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituencies("231,232");
		System.out.println(list.size());
		//System.out.println(((Object[])list.get(3))[1]);
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testGetLatestAssemblyConstitueciesOfTehsil(){
		List list = delimitationConstituencyMandalDAO.getLatestAssemblyConstitueciesOfTehsil(90l);
		System.out.println(list.size());
		
		if(list != null){
			for(int i=0;i<list.size();i++){
			Object[] params = (Object[])list.get(i);
			
			System.out.println(" Constitiuency Id   :" + (Long)params[4]);
			System.out.println(" Constitiuency Name :" + (String)params[5]);
			}
		}
	}*/
	
	/*public void testGetAllMandalsByGivenConstituencies(){
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(232));
		List result = delimitationConstituencyMandalDAO.getLatestMandalIdsByConstituenciesIds(list);
		System.out.println(result);
	}*/
	
	public void testGetMandalsOfConstituency(){
		List tehsil = delimitationConstituencyMandalDAO.getMandalsOfConstituency(232l);
		for(int i=0;i<tehsil.size(); i++)
			System.out.println(((Object[])tehsil.get(i))[0]+"--"+((Object[])tehsil.get(i))[1]+"--"+((Object[])tehsil.get(i))[2]+"--"+((Object[])tehsil.get(i))[3]);
	}
	
}
