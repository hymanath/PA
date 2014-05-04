/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.eliteclub.dao;

import java.util.List;

import com.itgrids.eliteclub.model.BoothPollingUpdates;






/**
 * Interface for BoothPollingUpdates.
 * 
 * @author Anilkumar Ravula
 */

public interface IBoothPollingUpdatesDAO extends AbstractDao<BoothPollingUpdates, Long> {
	
public void flushSession();


}