/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.WardColumnNames;
import com.itgrids.partyanalyst.model.Ward;

/**
 * Interface for WardDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IWardDAO extends GenericDao<Ward, Long>{

	/**
	 * Find all Ward entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Ward property to query
	 * @param value
	 *            the property value to match
	 * @return List<Ward> found by query
	 */
	public List<Ward> findByProperty(WardColumnNames propertyName, Object value);

	public List<Ward> findByWardName(Object wardName);

	public List<Ward> findByWardCode(Object wardCode);
	
	public List<Ward> findByWardNameAndTownship(String wardName,Long townId);
	  public List<Object[]> findByWardsByAssemblyLocalElectionBodyId(Long  alebi,Long publicationId );
	  
	  public List<Object[]> getWardsListByLocalEleBodyIdAndConstituencyId(Long  localEleBodyId, Long publicationDateId, Long constituencyId);
	  

}