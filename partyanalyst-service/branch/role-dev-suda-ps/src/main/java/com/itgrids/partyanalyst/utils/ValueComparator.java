package com.itgrids.partyanalyst.utils;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("unchecked")
public
class ValueComparator implements Comparator {

	  Map base;
	  Double x, y;
	  
	  public ValueComparator(Map base) {
	      this.base = base;
	  }

	  public int compare(Object a, Object b) {
		x = ((BigDecimal) base.get(a)).doubleValue();
	    y = ((BigDecimal) base.get(b)).doubleValue();
	    
	    if(x < y) {
	      return 1;
	    } else if(x == y) {
	      return 0;
	    } else {
	      return -1;
	    }
	  }
}
