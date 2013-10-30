/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.jasper.dataSource;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.itgrids.partyanalyst.dto.PositionType;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Map DataSource for PartyPerformance Jasper Report
 * 
 * @author Sujatha Boddu
 */
public class MapDataSource implements JRDataSource {

	/**
	* @param args
	*/

	private Iterator iter = null; 
	private Map.Entry<Object,Object> currRec = null; 
	TreeMap<Object, Object> map = null;

	public MapDataSource(Map<Object, Object> paraMap){ 
	/* 
	* copy HashMmap to local (sorted) TreeMap 
	*/ 
		map = new TreeMap<Object, Object>(paraMap); 
	
		/* 
		* I have to delete default parameters and some other elements 
		*/ 
		Iterator i = paraMap.entrySet().iterator(); 
	
		if (map.isEmpty()) 
		{ 
		iter = null; 
		} 
		else 
		{ 
		iter = map.entrySet().iterator(); 
		} 
	} 

	public boolean next() throws JRException { 
	boolean hasNext = false; 

	if (iter != null) 
	{ 
	hasNext = iter.hasNext(); 
	if (hasNext) 
	{ 
	currRec = (Map.Entry)iter.next(); 
	} 
	} 
	return hasNext; 
	} 

	/* 
	* fetch parameter name or parameter value - 
	* field names as used in the subreport 
	*/ 
	public Object getFieldValue(JRField field) throws JRException { 
	Object value = null; 

	String fieldName = field.getName(); 

	if ("pName".equals(fieldName)) 
	{ 
	value = currRec.getKey(); 
	} 
	else if ("pValue".equals(fieldName)) 
	{ 
	value = currRec.getValue(); 
	} 
	return value; 
	} 

	public Object get(int key) throws JRException { 
		Object value = null;
		return currRec.getValue();
	}

	public Object get(PositionType key) throws JRException { 
		return  map.get(key);
	}

}