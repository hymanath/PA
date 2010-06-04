package com.itgrids.partyanalyst.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import com.itgrids.partyanalyst.dto.ProblemsCountByStatus;

public class ProblemsCountByStatusComparator implements
		Comparator<ProblemsCountByStatus> {
	public int compare(ProblemsCountByStatus a, ProblemsCountByStatus b) {
		 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 try{
			 return formatter.parse(b.getDate()).compareTo(formatter.parse(a.getDate()));	 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		return 0;
	}

}
