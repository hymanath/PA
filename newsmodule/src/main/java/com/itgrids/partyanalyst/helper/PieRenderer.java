/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.helper;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * PieChart Renderer
 * 
 * @author Sujatha Boddu
 */

public class PieRenderer {
	
    private Color[] color;
   
    public PieRenderer(Color[] color)
    {
        this.color = color;
    }       
   
    @SuppressWarnings("unchecked")
	public void setColor(PiePlot plot, DefaultPieDataset dataset)
    {
        List<Comparable> keys = dataset.getKeys();
        int aInt;
       
        for (int i = 0; i < keys.size(); i++)
        {
            aInt = i % this.color.length;
            if(this.color[aInt] != null)
            	plot.setSectionPaint(keys.get(i), this.color[aInt]);
        }
    }
}

