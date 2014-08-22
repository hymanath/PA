/**
 * 
 */
package net.sf.jasperreports.components.charts;

import java.io.Serializable;

import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 * @version $Id: ChartComponent.java 3936 2010-08-13 15:34:10Z shertage $
 */
public interface ChartComponent extends Component, Serializable
{
	public ChartSettings getChartSettings();

	public ChartDataset getDataset();

	public ChartPlot getPlot();

	public EvaluationTimeEnum getEvaluationTime();

	public String getEvaluationGroup();
}
