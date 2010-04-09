/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.helper;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.File;
import java.util.List;
import java.util.SortedMap;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.CombinedRangeCategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.MinMaxCategoryRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.SortOrder;

/**
 * Chart Producer for PartyPerformance Report
 * 
 * @author Sujatha Boddu
 */

public class ChartProducer {

	private final static Logger log =  Logger.getLogger(ChartProducer.class);

	public static void createPie3DChart(SortedMap<String, Integer> positions, String filename, String chartTitle) {
		
		DefaultPieDataset dataSet = new DefaultPieDataset();
	    for(String entry : positions.keySet()) {
	        dataSet.setValue(entry, positions.get(entry));
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
		     ChartUtilities.saveChartAsPNG(image, chart, 400, 400, info);
		 }
		 catch (java.io.IOException exc)
		 {
		    log.error("Error writing image to file");
		 }

	}
	
	public static void createLineChart(String title, final List<CategoryDataset> dataset, String categoryAxisLabel, 
			String valueAxisLabel, String fileName) {
		log.debug("creating Line chart....");
		
		final NumberAxis seatsRangeAxis = new NumberAxis("No. of Seats");
        seatsRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final LineAndShapeRenderer seatsRenderer = new LineAndShapeRenderer();
        seatsRenderer.setDrawOutlines(true);
        seatsRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot seatsPlot = new CategoryPlot(dataset.get(0), null, seatsRangeAxis, seatsRenderer);
        seatsPlot.setDomainGridlinesVisible(true);
        seatsPlot.setForegroundAlpha(0.5f);
        
        final NumberAxis votesRangeAxis = new NumberAxis("% of Votes");
        votesRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer votesRenderer = new BarRenderer();
        votesRenderer.setDrawBarOutline(true);
        votesRenderer.setAutoPopulateSeriesFillPaint(true);
        final BarPainter painter = votesRenderer.getBarPainter();
        votesRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot votesPlot = new CategoryPlot(dataset.get(1), null, votesRangeAxis, votesRenderer);
        votesPlot.setDomainGridlinesVisible(true);
        votesPlot.setBackgroundPaint(Color.LIGHT_GRAY);
        votesPlot.setForegroundAlpha(0.5f);
        
        final CategoryAxis domainAxis = new CategoryAxis("Year");
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.setRowRenderingOrder(SortOrder.ASCENDING);
        plot.add(seatsPlot, 2);
        plot.add(votesPlot, 1);
        
        final JFreeChart chart = new JFreeChart("Year Vs Seats & Percentage of Votes Graph",  plot);
   
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(fileName);
			ChartUtilities.saveChartAsPNG(image, chart, 450, 400, info);
		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
	}
	
	
	public static void createLineChartForPolling(String title, final List<CategoryDataset> dataset, String categoryAxisLabel, 
			String valueAxisLabel, String fileName) {
		log.debug("creating Line chart....");
		
			
        final NumberAxis seatsRangeAxis = new NumberAxis("Polling Trendz");
        seatsRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer seatsRenderer = new BarRenderer();
        seatsRenderer.setDrawBarOutline(true);
        seatsRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot seatsPlot = new CategoryPlot(dataset.get(0), null, seatsRangeAxis, seatsRenderer);
        seatsPlot.setDomainGridlinesVisible(true);
        seatsPlot.setForegroundAlpha(0.5f);
        
        final NumberAxis votesRangeAxis = new NumberAxis("% of Polling");
        votesRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer votesRenderer = new BarRenderer();
        votesRenderer.setDrawBarOutline(true);
        votesRenderer.setAutoPopulateSeriesFillPaint(true);
        final BarPainter painter = votesRenderer.getBarPainter();
        votesRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot votesPlot = new CategoryPlot(dataset.get(1), null, votesRangeAxis, votesRenderer);
        votesPlot.setDomainGridlinesVisible(true);
        votesPlot.setBackgroundPaint(Color.WHITE);
        votesPlot.setForegroundAlpha(0.5f);
        
        final CategoryAxis domainAxis = new CategoryAxis("Category");
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.setRowRenderingOrder(SortOrder.ASCENDING);
        plot.add(seatsPlot, 2);
        plot.add(votesPlot, 1);
        
        final JFreeChart chart = new JFreeChart("Polling Trendz & Poll % ",  plot);
   
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(fileName);
			ChartUtilities.saveChartAsPNG(image, chart, 380, 230, info);
		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
	}
		
	public static void createBarChart(String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName) {
		JFreeChart chart = ChartFactory.createBarChart("", domainAxisL, rangeAxisL, dataset, PlotOrientation.VERTICAL, true, true, false );
		//chart.setBackgroundPaint(new Color(0xBBBBDD));
		chart.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis axis = plot.getDomainAxis();
		axis.setCategoryMargin(0.3);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.pink, 0.0f, 0.0f, Color.lightGray);	
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.yellow, 0.0f, 0.0f, Color.lightGray);
		
		CategoryItemRenderer itemRenderer = plot.getRenderer();
		itemRenderer.setItemLabelsVisible(true);
		
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setItemMargin(0.0);
		renderer.setMaximumBarWidth(0.15);
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(fileName);

			ChartUtilities.saveChartAsPNG(image, chart, 350, 250, info);

			if(title.contains("By Revenue Villages")){
				ChartUtilities.saveChartAsPNG(image, chart, 1200, 400, info);
			}else
				ChartUtilities.saveChartAsPNG(image, chart, 400, 350, info);

		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
		
	}
	
	public static void createBarChartForCandidateVotingTrendz(String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName) {
		JFreeChart chart = ChartFactory.createBarChart(title, domainAxisL, rangeAxisL, dataset, PlotOrientation.VERTICAL, true, true, false );
		chart.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis axis = plot.getDomainAxis();
		axis.setCategoryMargin(0.3);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.pink, 0.0f, 0.0f, Color.lightGray);	
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.yellow, 0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setItemMargin(0.0);
		renderer.setMaximumBarWidth(0.15);
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(fileName);
			ChartUtilities.saveChartAsPNG(image, chart, 250, 200, info);
		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
		
	}
	
	
	public static void createALineChart(String title, final CategoryDataset dataset, String categoryAxisLabel, 
			String valueAxisLabel, String fileName){
		
		    final NumberAxis seatsRangeAxis = new NumberAxis("Seats");
	        seatsRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        final LineAndShapeRenderer seatsRenderer = new LineAndShapeRenderer();
	        seatsRenderer.setDrawOutlines(true);
	        seatsRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
	        final CategoryPlot seatsPlot = new CategoryPlot(dataset, null, seatsRangeAxis, seatsRenderer);
	        seatsPlot.setDomainGridlinesVisible(true);
	        seatsPlot.setForegroundAlpha(0.5f);
	        
	        final CategoryAxis domainAxis = new CategoryAxis("Year");
	        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
	        plot.setRowRenderingOrder(SortOrder.ASCENDING);
	        plot.add(seatsPlot, 2);
	       	       	        
	        final JFreeChart chart = new JFreeChart("",  plot);
	        chart.setBackgroundPaint(Color.WHITE);
	        
			try	 {
				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
				final File image = new File(fileName);
				ChartUtilities.saveChartAsPNG(image, chart, 350, 250, info);
			}
			catch (java.io.IOException exc)
			{
			log.error("Error writing image to file");
			}
	       
		
	}
	
	public static void createBarChartForVotingTrendz(String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName) {
		JFreeChart chart = ChartFactory.createBarChart(title, domainAxisL, rangeAxisL, dataset, PlotOrientation.VERTICAL, true, true, false );
		//chart.setBackgroundPaint(new Color(0xBBBBDD));
		chart.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis axis = plot.getDomainAxis();
		axis.setCategoryMargin(0.3);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.pink, 0.0f, 0.0f, Color.lightGray);	
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.yellow, 0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setItemMargin(0.0);
		renderer.setMaximumBarWidth(0.15);
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(fileName);
			ChartUtilities.saveChartAsPNG(image, chart, 600, 400, info);
		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
		
	}
	
	public static void createALineChartforVotingTrendz(String title, final CategoryDataset dataset, String categoryAxisLabel, 
			String valueAxisLabel, String fileName){
		
		    final NumberAxis seatsRangeAxis = new NumberAxis("Seats");
	        seatsRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        final LineAndShapeRenderer seatsRenderer = new LineAndShapeRenderer();
	        seatsRenderer.setDrawOutlines(true);
	        seatsRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
	        final CategoryPlot seatsPlot = new CategoryPlot(dataset, null, seatsRangeAxis, seatsRenderer);
	        seatsPlot.setDomainGridlinesVisible(true);
	        seatsPlot.setForegroundAlpha(0.5f);
	        
	        final CategoryAxis domainAxis = new CategoryAxis("Year");
	        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
	        plot.setRowRenderingOrder(SortOrder.ASCENDING);
	        plot.add(seatsPlot, 2);
	       	        
	        final JFreeChart chart = new JFreeChart("Voting Trendz ..",  plot);
	        
			try	 {
				final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
				final File image = new File(fileName);
				ChartUtilities.saveChartAsPNG(image, chart, 600, 250, info);
			}
			catch (java.io.IOException exc)
			{
			log.error("Error writing image to file");
			}
	}
	
	public static void createALineChartforVotingTrendzNew(String title, final CategoryDataset dataset1,final CategoryDataset dataset2,String fileName){
		
		CategoryAxis domainAxis1 = new CategoryAxis("Party");
		domainAxis1.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis1.setMaximumCategoryLabelWidthRatio(5.0f);
		BarRenderer renderer1 = new BarRenderer();
		renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		CategoryPlot subplot1 = new CategoryPlot(dataset2, domainAxis1, null, renderer1);
		subplot1.setDomainGridlinesVisible(true);
		
		CategoryAxis domainAxis2 = new CategoryAxis("Party");
		domainAxis2.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis2.setMaximumCategoryLabelWidthRatio(5.0f);
		BarRenderer3D renderer2 = new BarRenderer3D();
		renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		CategoryPlot subplot2 = new CategoryPlot(dataset1, domainAxis2, null, renderer2);
		subplot2.setDomainGridlinesVisible(true);
		
		final ValueAxis rangeAxis = new NumberAxis("Votes %");
		CombinedRangeCategoryPlot plot = new CombinedRangeCategoryPlot(rangeAxis);
		plot.add(subplot1, 3);
		plot.add(subplot2, 2);
		JFreeChart chart = new JFreeChart("Constituency Voting Trendz ",plot);
		
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(fileName);
			ChartUtilities.saveChartAsPNG(image, chart, 900, 270, info);
		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
	}
	
	public static void createPieChart(String title,final PieDataset dataset,String fileName){
		
		
			    
		JFreeChart chart = ChartFactory.createPieChart(title,dataset,
				true, // legend?
				true, // tooltips?
				false // URLs?
				);
	   				
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(fileName);
			ChartUtilities.saveChartAsPNG(image, chart,250, 250, info);
		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
	}

	public static void createLineChart(String title, String xAxis, String yAxis, CategoryDataset dataset, String path,int height,int width){
		final NumberAxis seatsRangeAxis = new NumberAxis(yAxis);
        seatsRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final LineAndShapeRenderer seatsRenderer = new LineAndShapeRenderer();
        seatsRenderer.setDrawOutlines(true);
        seatsRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot seatsPlot = new CategoryPlot(dataset, null, seatsRangeAxis, seatsRenderer);
        seatsPlot.setDomainGridlinesVisible(true);
        seatsPlot.setForegroundAlpha(0.5f);
        
        final CategoryAxis domainAxis = new CategoryAxis(xAxis);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(seatsPlot, 2);
       	        
        final JFreeChart chart = new JFreeChart(title,  plot);
        chart.setBackgroundPaint(Color.WHITE);
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(path);
			ChartUtilities.saveChartAsPNG(image, chart, width, height, info);
		}
		catch (java.io.IOException exc)
		{
		log.error("Error writing image to file");
		}
	}
	
	public static void create3DBarChart(String category,String value,CategoryDataset dataset,String fileName){
		JFreeChart chart = ChartFactory.createBarChart3D(
				"", // chart title
				category, // domain axis label
				value, // range axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
				// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
				// set the background color for the chart...
				chart.setBackgroundPaint(new Color(0xFFFFFF));
				// get a reference to the plot for further customisation...
				CategoryPlot plot = chart.getCategoryPlot();
				// set the range axis to display integers only...
				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				
				rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
				CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
		        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		        
				// disable bar outlines...
				BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
				renderer.setDrawBarOutline(false);
								
				try	 {
					final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
					final File image = new File(fileName);
					ChartUtilities.saveChartAsPNG(image, chart, 880, 300, info);
				}
				catch (java.io.IOException exc)
				{
				log.error("Error writing image to file");
				}
	}
}
