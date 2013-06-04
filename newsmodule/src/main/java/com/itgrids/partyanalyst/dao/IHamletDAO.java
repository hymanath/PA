/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 1, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Hamlet;

public interface IHamletDAO extends GenericDao<Hamlet, Long> {

	public List<Hamlet> findByHamletId(Long hamletId);
	
	public List<Hamlet> findByHamletCode(String hamletCode);
	
	public List<Hamlet> findByHamletName(String hamletName);
	
	public List<Hamlet> findByTownshipId(Long townshipId);

	public List<Hamlet> findByTehsilTownshipAndHamletName(Long tehsilId, String townshipName, String hamletName);
	
	public List findHamletNamesByTownshipId(Long townshipId);
	
	public List getStateToHamletByHamlets(String hamletIDs);

	public List<Hamlet> findByHamletNameAndTownshipId(Long townshipId, String hamlet);
	
	public List findByHamletNameAndTownshipId(String hamletName,Long townshipId);
	
	public List getHamletIdBasedOnDistrictNameMandalIdAndTownship(String districtName,String mandalName,String townshipName,String hamletName);
	
	public List findHamletsByTehsilId(Long tehsilId);
	
	public List<Long> findHamletsByTehsilIds(List<Long> tehsilIds);
	
	@SuppressWarnings("unchecked")
	public List findByTehsilIdAndHamletId(Long hamletId,Long tehsilId);
}
