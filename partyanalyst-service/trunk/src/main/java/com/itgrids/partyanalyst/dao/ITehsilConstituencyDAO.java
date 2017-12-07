/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

public interface ITehsilConstituencyDAO {
	public List<Long> getTehsilIdsByConstituencyId(List<Long> constituencyIds);
}