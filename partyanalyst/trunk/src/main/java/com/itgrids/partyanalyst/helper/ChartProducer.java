/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.helper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.CombinedRangeCategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
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
	            System.out.println("Exception while creating the chart" );
	            exc.printStackTrace();
	            System.out.println("Temp Directory:"+System.getProperty("java.io.tmpdir"));

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
            System.out.println("Exception while creating the chart" );
            exc.printStackTrace();
            System.out.println("Temp Directory:"+System.getProperty("java.io.tmpdir"));
            log.error("Error Raised While Creating chart :" + exc);

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
            System.out.println("Exception while creating the chart" );
            exc.printStackTrace();
            System.out.println("Temp Directory:"+System.getProperty("java.io.tmpdir"));

		}
	}
		
	public static void createBarChart(String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName) {
		log.debug("in bar chart create method");
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
		catch (Exception exc)
		{
			exc.printStackTrace();
		log.error("Error writing image to file" + exc);
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
	            System.out.println("Exception while creating the chart" );
	            exc.printStackTrace();
	            System.out.println("Temp Directory:"+System.getProperty("java.io.tmpdir"));

			}
	       
		
	}
	
	public static void ForVotingTrendz(String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName) {
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
            System.out.println("Exception while creating the chart" );
            exc.printStackTrace();
            System.out.println("Temp Directory:"+System.getProperty("java.io.tmpdir"));

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
		exc.printStackTrace();
		}
	}

	public static void createLineChart(String title, String xAxis, String yAxis, CategoryDataset dataset, String path,int height,int width, List<Color> colors,Boolean thickLines){
		final NumberAxis seatsRangeAxis = new NumberAxis(yAxis);
        seatsRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        seatsRangeAxis.setTickLabelFont(new Font("verdana",Font.BOLD,12));
               
        final LineAndShapeRenderer seatsRenderer = new LineAndShapeRenderer();
        seatsRenderer.setDrawOutlines(true);
        seatsRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot seatsPlot = new CategoryPlot(dataset, null, seatsRangeAxis, seatsRenderer);
        seatsPlot.setDomainGridlinesVisible(true);
        seatsPlot.setForegroundAlpha(0.5f);
        
        
        final CategoryAxis domainAxis = new CategoryAxis(xAxis);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setTickLabelFont(new Font("verdana",Font.PLAIN,10));
        
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(seatsPlot, 2);
        plot.setColumnRenderingOrder(SortOrder.ASCENDING);
                       
        GradientPaint gp;
        
        if(colors != null){
        	log.debug("Colors Size::"+colors.size());
        	for(int i=0; i<colors.size(); i++){
            	if(colors.get(i) == null)
            		continue;
            	gp = new GradientPaint(0.0f, 0.0f, colors.get(i), 0.0f, 0.0f, colors.get(i));
            	seatsRenderer.setSeriesPaint(i, gp);
            }
        }
        
        //for thick lines
        if(thickLines){
        log.debug(" Partys Count in Dataset -- " + dataset.getRowCount());
        
        for(int i=0;i<dataset.getRowCount();i++){
        	seatsRenderer.setSeriesStroke(
            			i, new BasicStroke(
            			1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
            			1.0f, null, 0.0f
            			)
            			);
        	seatsRenderer.setBaseLegendTextFont(new Font("verdana",Font.PLAIN,10));
        	seatsPlot.setBackgroundPaint(new Color(219, 223, 225));
        }
        }
            
        final JFreeChart chart = new JFreeChart(title,  plot);
        chart.setBackgroundPaint(Color.WHITE);
		try	 {
			final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
			final File image = new File(path);
			ChartUtilities.saveChartAsPNG(image, chart, width, height, info);
		}
		catch (java.io.IOException exc)
		{
			log.error("Error writing image to file" + exc);
			exc.printStackTrace();
		}
	}
	
	
	public static void createLineChartWithThickness(String title, String xAxis, String yAxis, CategoryDataset dataset, String path,int height,int width, List<Color> colors,Boolean thickLines){
		final NumberAxis seatsRangeAxis = new NumberAxis(yAxis);
        seatsRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        seatsRangeAxis.setTickLabelFont(new Font("verdana",Font.BOLD,15));
        
        final LineAndShapeRenderer seatsRenderer = new LineAndShapeRenderer();
        seatsRenderer.setDrawOutlines(true);
        seatsRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot seatsPlot = new CategoryPlot(dataset, null, seatsRangeAxis, seatsRenderer);
        seatsPlot.setDomainGridlinesVisible(true);
        seatsPlot.setForegroundAlpha(0.5f);
        
        
        final CategoryAxis domainAxis = new CategoryAxis(xAxis);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setTickLabelFont(new Font("verdana",Font.BOLD,15));
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(seatsPlot, 2);
               
        GradientPaint gp;
        
        if(colors != null){
        	log.debug("Colors Size::"+colors.size());
        	for(int i=0; i<colors.size(); i++){
            	if(colors.get(i) == null)
            		continue;
            	gp = new GradientPaint(0.0f, 0.0f, colors.get(i), 0.0f, 0.0f, Color.lightGray);
            	seatsRenderer.setSeriesPaint(i, gp);
            }
        }
        
        //for thick lines
        if(thickLines){
        log.debug(" Partys Count in Dataset -- " + dataset.getRowCount());
        
        for(int i=0;i<dataset.getRowCount();i++){
        	seatsRenderer.setSeriesStroke(
            			i, new BasicStroke(
            			2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
            			1.0f, null, 0.0f
            			)
            			);
        	seatsRenderer.setBaseLegendTextFont(new Font("verdana",Font.BOLD,13));
        	seatsPlot.setBackgroundPaint(new Color(219, 223, 225));
        	
        }
        }
      
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
					if(category.contains("Problems")){
						ChartUtilities.saveChartAsPNG(image, chart, 400, 300, info);
					}else{
						ChartUtilities.saveChartAsPNG(image, chart, 880, 300, info);
					}
					
				}
				catch (java.io.IOException exc)
				{
				log.error("Error writing image to file");
				}
	}
	
	public static void create3DBarChartWithInputParams(String title,String reportType,String category,String value,String party,CategoryDataset dataset,String fileName,int width,int height,List<Color> colors, boolean setMargin){
		JFreeChart chart = ChartFactory.createBarChart(
				title, // chart title
				category, // domain axis label
				value, // range axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				true, // include legend
				true, // tool tips?
				false // URLs?
				);
				// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
				// set the background color for the chart...
				chart.setBackgroundPaint(new Color(0xFFFFFF));
				// get a reference to the plot for further customization...
				CategoryPlot plot = chart.getCategoryPlot();
				// set the range axis to display integers only...
				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				
				rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
				CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
		       // domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		        
				// disable bar outlines...
				BarRenderer renderer = (BarRenderer) plot.getRenderer();
				renderer.setDrawBarOutline(false);
				if(colors != null){
		        	GradientPaint gp;
		        	log.debug("Colors Size::"+colors.size());
		        	for(int i=0; i<colors.size(); i++){
		            	if(colors.get(i) == null)
		            		continue;
		            	gp = new GradientPaint(0.0f, 0.0f, colors.get(i), 0.0f, 0.0f, colors.get(i));
		            	renderer.setSeriesPaint(i, gp);
		            }
		        } else {
		        	GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.GREEN, 0.0f, 0.0f, Color.lightGray);
					GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.RED, 0.0f, 0.0f, Color.lightGray);	
					GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.cyan, 0.0f, 0.0f, Color.lightGray);
					renderer.setSeriesPaint(0, gp0);
					renderer.setSeriesPaint(1, gp1);
					renderer.setSeriesPaint(2, gp2);
		        }
				 
				//domainAxis.setLowerMargin(0.03);
				//domainAxis.setCategoryMargin(0.56); // ten percent
				//domainAxis.setUpperMargin(0.03); // two percent
				if(setMargin)
				{
					renderer.setItemMargin(0.50);
				} else 	
					{
						renderer.setItemMargin(0.0);
						renderer.setMaximumBarWidth(0.15);
					}
					
					
				try	 {
					final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
					final File image = new File(fileName);
					ChartUtilities.saveChartAsPNG(image, chart, width, height, info);
				}
				catch (java.io.IOException exc)
				{
				log.error("Error writing image to file");
				log.debug("Exception Raised :" + exc);
				}
	}
	
	
	public static void createProblemsPieChart(String title,final DefaultPieDataset dataset,String fileName, Color[] colors, boolean legend, int height, int width){
		
		JFreeChart chart = ChartFactory.createPieChart(title, dataset, legend, false, false);
		//To modify the title generated 
		chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 11));
		chart.getTitle().setPaint(new Color(0X89745D));
        PiePlot plot = (PiePlot)chart.getPlot();
        plot.setLabelGenerator(null);
        // Specify the colors here
        if(colors != null){
        	PieRenderer renderer = new PieRenderer(colors);
            renderer.setColor(plot, dataset);
                      
        }                
        plot.setBackgroundPaint(Color.WHITE);
        // To disable to category labels in the graph plot
        plot.setOutlineVisible(false);       
               
        try
        {
            // This will create a PNG image
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, height, width);
        }
        catch (Exception e)
        {
            System.out.println("Exception while creating the chart");
            e.printStackTrace();
            System.out.println(System.getProperty("java.io.tmpdir"));
        }
		
	}
	
	public static void createProblems3DBarChart(String title,String reportType,String category,String value,String party,CategoryDataset dataset,String fileName,int width,int height){
		JFreeChart chart = ChartFactory.createBarChart(
				title, // chart title
				category, // domain axis label
				value, // range axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				true, // tool tips?
				false // URLs?
				);
				// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
				// set the background color for the chart...
				chart.setBackgroundPaint(new Color(0xFFFFFF));
				// get a reference to the plot for further customization...
				CategoryPlot plot = chart.getCategoryPlot();
				//BarRenderer renderer = (BarRenderer) plot.getRenderer();
				//renderer.setDrawBarOutline(false);
				// set the range axis to display integers only...
				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				
				rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
				CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
		       // domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		        
				// disable bar outlines...
				BarRenderer renderer = (BarRenderer) plot.getRenderer();
				renderer.setDrawBarOutline(false);
				
				GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.RED, 0.0f, 0.0f, Color.lightGray);
				GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.GREEN, 0.0f, 0.0f, Color.lightGray);					
				GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.cyan, 0.0f, 0.0f, Color.lightGray);
				renderer.setSeriesPaint(0, gp0);
				renderer.setSeriesPaint(1, gp1);
				renderer.setSeriesPaint(2, gp2);
								
				renderer.setItemMargin(0.0);
				renderer.setMaximumBarWidth(0.15);
					
				try	 {
					final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
					final File image = new File(fileName);
					ChartUtilities.saveChartAsPNG(image, chart, width, height, info);
				}
				catch (java.io.IOException exc)
				{
				log.error("Error writing image to file");
				log.debug("Exception Raised :" + exc);
				}
	}
	
public static void createLabeledPieChart(String title,final DefaultPieDataset dataset,String fileName, Color[] colors, boolean legend, int height, int width){
		
		JFreeChart chart = ChartFactory.createPieChart(title, dataset, false, true, false);
		//To modify the title generated 
		chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 11));
		chart.getTitle().setPaint(new Color(0X89745D));
        PiePlot plot = (PiePlot)chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        //plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setInteriorGap(0.01);
        plot.setMaximumLabelWidth(0.30);	
        
       // plot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
        // Specify the colors here
        if(colors != null){
        	PieRenderer renderer = new PieRenderer(colors);
            renderer.setColor(plot, dataset);
            
        }                
        plot.setBackgroundPaint(Color.WHITE);
        
        // To disable to category labels in the graph plot
         
        plot.setOutlineVisible(false);       
               
        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
				
        try
        {
            // This will create a PNG image
            ChartUtilities.saveChartAsPNG(new File(fileName), chart, width, height,info);  //	width height best viewed 280, 180
        }
        catch (Exception e)
        {
            System.out.println("Exception while creating the chart" );
            e.printStackTrace();
            System.out.println("Temp Directory:"+System.getProperty("java.io.tmpdir"));
        }
	}


public static void createBarChartForVotesPoll(String title, String domainAxisL, String rangeAxisL, CategoryDataset dataset, String fileName,String type) {
	
	JFreeChart chart=null;
	if(type.equalsIgnoreCase("votesPoll")){
		chart = ChartFactory.createBarChart("", domainAxisL, rangeAxisL, dataset, PlotOrientation.HORIZONTAL, false, true, false );
	}else{
		chart = ChartFactory.createBarChart3D("", domainAxisL, rangeAxisL, dataset, PlotOrientation.VERTICAL, false, true, false );
	}
	
	//chart.setBackgroundPaint(new Color(0xBBBBDD));
	chart.setBackgroundPaint(Color.WHITE);
	CategoryPlot plot = chart.getCategoryPlot();
	CategoryAxis axis = plot.getDomainAxis();
	axis.setCategoryMargin(0.3);
	NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	BarRenderer renderer = (BarRenderer) plot.getRenderer();
	renderer.setDrawBarOutline(false);
	GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.RED, 0.0f, 0.0f, Color.white);
	GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.DARK_GRAY, 0.0f, 0.0f, Color.white);	
	GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.BLUE, 0.0f, 0.0f, Color.white);

	          plot.setBackgroundPaint(Color.white);
	          plot.setDomainGridlinePaint(Color.white);
	          plot.setDomainGridlinesVisible(true);
	          plot.setRangeGridlinePaint(Color.white);
	          BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
	          barRenderer.setDrawBarOutline(false);
	          renderer.setDrawBarOutline(false);

	  if(! type.equalsIgnoreCase("votesPoll")){
		  axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	  }
	
	CategoryItemRenderer itemRenderer = plot.getRenderer();
	itemRenderer.setItemLabelsVisible(true);
	
	renderer.setSeriesPaint(0, gp0);
	renderer.setSeriesPaint(1, gp1);
	renderer.setSeriesPaint(2, gp2);
	renderer.setItemMargin(0.0);
	if(type.equalsIgnoreCase("votesPoll")){
		renderer.setMaximumBarWidth(0.15);
	}else{
		renderer.setMaximumBarWidth(0.30);
	}
	try	 {
		final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		final File image = new File(fileName);

		if(type.equalsIgnoreCase("votesPoll")){
			ChartUtilities.saveChartAsPNG(image, chart, 250, 180, info);
		}else{
			ChartUtilities.saveChartAsPNG(image, chart, 200, 300, info);
		}
				
	}
	catch (Exception exc)
	{
		exc.printStackTrace();
	}		
}
  	
}
