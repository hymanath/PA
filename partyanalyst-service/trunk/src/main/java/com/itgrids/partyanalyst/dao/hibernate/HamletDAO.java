/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 1, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.model.Hamlet;

public class HamletDAO extends GenericDaoHibernate<Hamlet, Long> implements IHamletDAO {

	
	public HamletDAO(){
		super(Hamlet.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Hamlet> findByHamletCode(String hamletCode) {
		
		return getHibernateTemplate().find("from Hamlet model where model.hamletCode = ?", hamletCode);
	}

	@SuppressWarnings("unchecked")
	public List<Hamlet> findByHamletId(Long hamletId) {
		
		return getHibernateTemplate().find("from Hamlet model where model.hamletId = ?", hamletId);
	}

	@SuppressWarnings("unchecked")
	public List<Hamlet> findByHamletName(String hamletName) {
		
		return getHibernateTemplate().find("from Hamlet model where model.hamletName = ?", hamletName);
	}

	@SuppressWarnings("unchecked")
	public List<Hamlet> findByTownshipId(Long townshipId) {		
		return getHibernateTemplate().find("from Hamlet model where model.township.townshipId = ?",townshipId);
	}

	@SuppressWarnings("unchecked")
	public List<Hamlet> findByTehsilTownshipAndHamletName(Long tehsilId, String townshipName, String hamletName) {
		Object[] params = {tehsilId, townshipName, hamletName};
		return getHibernateTemplate().find("from Hamlet model where model.township.tehsil.tehsilId = ?" +
				"and model.township.townshipName = ? and model.hamletName = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findHamletNamesByTownshipId(Long townshipId) {		
		return getHibernateTemplate().find("select model.hamletId, model.hamletName from Hamlet model where model.township.townshipId = ?",townshipId);
	}
	
	public List getStateToHamletByHamlets(String hamletIDs){
		return getHibernateTemplate().find("select model.township.tehsil.district.state.stateId, model.township.tehsil.district.state.stateName, " +
				"model.township.tehsil.district.districtId, model.township.tehsil.district.districtName, model.township.tehsil.tehsilId, " +
				"model.township.tehsil.tehsilName, model.township.townshipId, model.township.townshipName, model.hamletId, model.hamletName " +
				" from Hamlet model where model.hamletId in("+hamletIDs+") ");
	}

	@SuppressWarnings("unchecked")
	public List<Hamlet> findByHamletNameAndTownshipId(Long townshipId, String hamlet) {
		Object[] params = {townshipId, hamlet};
		return getHibernateTemplate().find("from Hamlet model where model.township.townshipId = ?" +
				" and model.hamletName = ?", params);
	}

}
