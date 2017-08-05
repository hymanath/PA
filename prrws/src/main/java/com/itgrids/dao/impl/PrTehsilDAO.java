package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPrTehsilDAO;
import com.itgrids.model.Diseases;
import com.itgrids.model.PrTehsil;

@Repository
public class PrTehsilDAO extends GenericDaoHibernate<PrTehsil, Long> implements IPrTehsilDAO {
	public PrTehsilDAO(){
		super(PrTehsil.class);
	}
	
	public List<Object[]> getPrPanchayatTehsilName(){
	    StringBuilder sb = new StringBuilder();
	    //0-prPanchayatCode,1-prTehsilCode,2-prTehsilName
	    sb.append(" select distinct model.uniquecode,model.prTehsil.uniqueCode,model.prTehsil.tehsilName from PrPanchayat model");
	    Query query = getSession().createQuery(sb.toString());
	    return query.list();
	}
	public List<Object[]> getPrTehsilConsituencyName(){
	    StringBuilder sb = new StringBuilder();
	    //0-prTehsilCode,1-prConstituencyCode,2-prConsituencyName
	    sb.append(" select   distinct PT.uniqueCode,PC.constituencyCode,PC.constituencyName from PrTehsil PT,PrConstituency PC,Tehsil T,Constituency C,TehsilConstituency TC " +
	    		  " where  PT.prTehsilId = T.prTehsilId AND PC.prConstituencyId = C.prConstituencyId" +
	    		  " AND TC.constituencyId = C.constituencyId AND TC.tehsilId = T.tehsilId ");
	    Query query = getSession().createQuery(sb.toString());
	   
	    return query.list();
	}
	
}
