package net.sf.jasperreports.engine.util;

import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.engine.JRBreak;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JREllipse;
import net.sf.jasperreports.engine.JRFrame;
import net.sf.jasperreports.engine.JRGenericElement;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRLine;
import net.sf.jasperreports.engine.JRRectangle;
import net.sf.jasperreports.engine.JRStaticText;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.JRTextField;
import net.sf.jasperreports.engine.JRVisitor;

/**
 * An abstract visitor class that treats all report elements in the same way. 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: UniformElementVisitor.java 3991 2010-10-12 20:10:55Z lucianc $
 * @see #visitElement(JRElement)
 */
public abstract class UniformElementVisitor implements JRVisitor
{

	/**
	 * Method that gets called when any element is visited, no matter what its type is.
	 * 
	 * @param element the element to be visited
	 */
	protected abstract void visitElement(JRElement element);
	
	public void visitBreak(JRBreak breakElement)
	{
		visitElement(breakElement);
	}

	public void visitChart(JRChart chart)
	{
		visitElement(chart);
	}

	public void visitComponentElement(JRComponentElement componentElement)
	{
		visitElement(componentElement);
	}

	public void visitCrosstab(JRCrosstab crosstab)
	{
		visitElement(crosstab);
	}

	public void visitEllipse(JREllipse ellipse)
	{
		visitElement(ellipse);
	}

	public void visitFrame(JRFrame frame)
	{
		visitElement(frame);
	}

	public void visitGenericElement(JRGenericElement element)
	{
		visitElement(element);
	}

	public void visitImage(JRImage image)
	{
		visitElement(image);
	}

	public void visitLine(JRLine line)
	{
		visitElement(line);
	}

	public void visitRectangle(JRRectangle rectangle)
	{
		visitElement(rectangle);
	}

	public void visitStaticText(JRStaticText staticText)
	{
		visitElement(staticText);
	}

	public void visitSubreport(JRSubreport subreport)
	{
		visitElement(subreport);
	}

	public void visitTextField(JRTextField textField)
	{
		visitElement(textField);
	}

}
