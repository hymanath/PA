package com.itgrids.partyanalyst.helper;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.utils.IConstants;

public class ChartUtils {

	public static List<Color> getLineChartColors(Set<String> seriesSet){
		List<Color> colors = new LinkedList<Color>();
		for(String series:seriesSet){
			if(series.contains(IConstants.TDP)){
        		colors.add(IConstants.TDP_COLOR);
        		System.out.println(" ........ " + series);
        	}
        		
        	else
        		if(series.contains(IConstants.INC)){
        			colors.add(IConstants.INC_COLOR);	
        			System.out.println(" ........ " + series);
        		}
            	
            	else
            		if(series.contains(IConstants.BJP)){
            			colors.add(IConstants.BJP_COLOR);
            			System.out.println(" ........ " + series);
            		}
                		
                	else
                		if(series.contains(IConstants.PRP)){
                			colors.add(IConstants.PRP_COLOR);
                			System.out.println(" ........ " + series);
                		}
                    		
                    	else
                    		if(series.contains(IConstants.TRS)){
                    			colors.add(IConstants.TRS_COLOR);
                    			System.out.println(" ........ " + series);
                    		}
			
                    		else
                        		if(series.contains("Others") || series.contains(IConstants.IND)){
                        			colors.add(Color.BLACK);
                        			System.out.println(" ........ " + series);
                        		}
			
                        		
                        	else{
                        		System.out.println(" ........ " + series);
                        		colors.add(null);
                        	}
		}
		return colors;
	}
	
}
