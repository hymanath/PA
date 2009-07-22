/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.helper;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * Chart Producer for PartyPerformance Report
 * 
 * @author Sujatha Boddu
 */

public class ChartProducer {

	private final static Log log =  LogFactory.getLog(ChartProducer.class);

	public static void createPie3DChart(Map<Integer, Integer> data, String filename, String chartTitle) {
		
		DefaultPieDataset dataSet = new DefaultPieDataset();
	    for(Integer entry : data.keySet()) {
	        dataSet.setValue(entry, data.get(entry));
	    }
	        
		PiePlot3D plot = new PiePlot3D(dataSet);
		plot.setForegroundAlpha(0.5f);

		Color[] colors = new Color[] { Color.GREEN, Color.BLUE,
									   Color.YELLOW, Color.PINK, Color.RED };
		
		PieRenderer renderer = new PieRenderer(colors);
			if (renderer!=null) {
				renderer.setColor(plot, (DefaultPieDataset) plot.getDataset());
			}
		JFreeChart chart = new JFreeChart(chartTitle,plot);
			
		try	 {
			 final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection()); 
		    final File image = new File(filename);
		    ChartUtilities.saveChartAsPNG(image, chart, 300, 300, info);
		 }
		 catch (java.io.IOException exc)
		 {
		    log.error("Error writing image to file");
		 }

	}
		
}
