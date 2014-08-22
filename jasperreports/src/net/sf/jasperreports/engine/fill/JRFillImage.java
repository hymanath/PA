/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.fill;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import net.sf.jasperreports.engine.JRBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRHyperlinkParameter;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRImageRenderer;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintHyperlinkParameters;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRVisitor;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.util.JRStyleResolver;
import net.sf.jasperreports.engine.util.LineBoxWrapper;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRFillImage.java 3985 2010-09-29 06:21:03Z teodord $
 */
public class JRFillImage extends JRFillGraphicElement implements JRImage
{


	/**
	 *
	 */
	private JRGroup evaluationGroup;

	/**
	 *
	 */
	private JRRenderable renderer;
	private boolean hasOverflowed;
	private Integer imageHeight;
	private Integer imageWidth;
	private Integer imageX;
	private String anchorName;
	private String hyperlinkReference;
	private String hyperlinkAnchor;
	private Integer hyperlinkPage;
	private String hyperlinkTooltip;
	private JRPrintHyperlinkParameters hyperlinkParameters;
	protected final JRLineBox lineBox;


	/**
	 *
	 */
	protected JRFillImage(
		JRBaseFiller filler,
		JRImage image, 
		JRFillObjectFactory factory
		)
	{
		super(filler, image, factory);
		
		lineBox = image.getLineBox().clone(this);

		evaluationGroup = factory.getGroup(image.getEvaluationGroup());
	}


	protected JRFillImage(JRFillImage image, JRFillCloneFactory factory)
	{
		super(image, factory);
		
		lineBox = image.getLineBox().clone(this);

		evaluationGroup = image.evaluationGroup;
	}


	/**
	 *
	 */
	public ModeEnum getModeValue()
	{
		return JRStyleResolver.getMode(this, ModeEnum.TRANSPARENT);
	}

	/**
	 * @deprecated Replaced by {@link #getScaleImageValue()}.
	 */
	public byte getScaleImage()
	{
		return getScaleImageValue().getValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #getOwnScaleImageValue()}.
	 */
	public Byte getOwnScaleImage()
	{
		return getOwnScaleImageValue() == null ? null : getOwnScaleImageValue().getValueByte();
	}

	/**
	 * @deprecated Replaced by {@link #setScaleImage(ScaleImageEnum)}.
	 */
	public void setScaleImage(byte scaleImage)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated Replaced by {@link #setScaleImage(ScaleImageEnum)}.
	 */
	public void setScaleImage(Byte scaleImage)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 */
	public ScaleImageEnum getScaleImageValue()
	{
		return JRStyleResolver.getScaleImageValue(this);
	}
		
	public ScaleImageEnum getOwnScaleImageValue()
	{
		return ((JRImage)this.parent).getOwnScaleImageValue();
	}

	/**
	 *
	 */
	public void setScaleImage(ScaleImageEnum scaleImage)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated Replaced by {@link #getHorizontalAlignmentValue()}.
	 */
	public byte getHorizontalAlignment()
	{
		return getHorizontalAlignmentValue().getValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #getOwnHorizontalAlignmentValue()}.
	 */
	public Byte getOwnHorizontalAlignment()
	{
		return getOwnHorizontalAlignmentValue() == null ? null : getOwnHorizontalAlignmentValue().getValueByte();
	}

	/**
	 *
	 */
	public HorizontalAlignEnum getHorizontalAlignmentValue()
	{
		return JRStyleResolver.getHorizontalAlignmentValue(this);
	}
		
	public HorizontalAlignEnum getOwnHorizontalAlignmentValue()
	{
		return ((JRImage)this.parent).getOwnHorizontalAlignmentValue();
	}

	/**
	 * @deprecated Replaced by {@link #setHorizontalAlignment(HorizontalAlignEnum)}.
	 */
	public void setHorizontalAlignment(byte horizontalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #setHorizontalAlignment(HorizontalAlignEnum)}.
	 */
	public void setHorizontalAlignment(Byte horizontalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 *
	 */
	public void setHorizontalAlignment(HorizontalAlignEnum horizontalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #getVerticalAlignmentValue()}.
	 */
	public byte getVerticalAlignment()
	{
		return getVerticalAlignmentValue().getValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #getOwnVerticalAlignmentValue()}.
	 */
	public Byte getOwnVerticalAlignment()
	{
		return getOwnVerticalAlignmentValue() == null ? null : getOwnVerticalAlignmentValue().getValueByte();
	}

	/**
	 *
	 */
	public VerticalAlignEnum getVerticalAlignmentValue()
	{
		return JRStyleResolver.getVerticalAlignmentValue(this);
	}
		
	public VerticalAlignEnum getOwnVerticalAlignmentValue()
	{
		return ((JRImage)this.parent).getOwnVerticalAlignmentValue();
	}

	/**
	 * @deprecated Replaced by {@link #setVerticalAlignment(VerticalAlignEnum)}.
	 */
	public void setVerticalAlignment(byte verticalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #setVerticalAlignment(VerticalAlignEnum)}.
	 */
	public void setVerticalAlignment(Byte verticalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 *
	 */
	public void setVerticalAlignment(VerticalAlignEnum verticalAlignment)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 *
	 */
	public boolean isUsingCache()
	{
		return ((JRImage)this.parent).isUsingCache();
	}
		
	/**
	 *
	 */
	public Boolean isOwnUsingCache()
	{
		return ((JRImage)this.parent).isOwnUsingCache();
	}
		
	/**
	 *
	 */
	public void setUsingCache(boolean isUsingCache)
	{
	}
		
	/**
	 *
	 */
	public void setUsingCache(Boolean isUsingCache)
	{
	}
		
	/**
	 *
	 */
	public boolean isLazy()
	{
		return ((JRImage)this.parent).isLazy();
	}
		
	/**
	 *
	 */
	public void setLazy(boolean isLazy)
	{
	}

	/**
	 * @deprecated Replaced by {@link #getOnErrorTypeValue()}.
	 */
	public byte getOnErrorType()
	{
		return getOnErrorTypeValue().getValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #setOnErrorType(OnErrorTypeEnum)}.
	 */
	public void setOnErrorType(byte onErrorType)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	public OnErrorTypeEnum getOnErrorTypeValue()
	{
		return ((JRImage)this.parent).getOnErrorTypeValue();
	}
		
	/**
	 *
	 */
	public void setOnErrorType(OnErrorTypeEnum onErrorType)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated Replaced by {@link #getEvaluationTimeValue()}.
	 */
	public byte getEvaluationTime()
	{
		return getEvaluationTimeValue().getValue();
	}
		
	/**
	 *
	 */
	public EvaluationTimeEnum getEvaluationTimeValue()
	{
		return ((JRImage)this.parent).getEvaluationTimeValue();
	}
		
	/**
	 *
	 */
	public JRGroup getEvaluationGroup()
	{
		return this.evaluationGroup;
	}
		
	/**
	 * @deprecated Replaced by {@link #getLineBox()}
	 */
	public JRBox getBox()
	{
		return new LineBoxWrapper(getLineBox());
	}

	/**
	 *
	 */
	public JRLineBox getLineBox()
	{
		return lineBox;
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public byte getBorder()
	{
		return getBox().getBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Byte getOwnBorder()
	{
		return getBox().getOwnBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBorder(byte border)
	{
		getBox().setBorder(border);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBorder(Byte border)
	{
		getBox().setBorder(border);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getBorderColor()
	{
		return getBox().getBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getOwnBorderColor()
	{
		return getBox().getOwnBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBorderColor(Color borderColor)
	{
		getBox().setBorderColor(borderColor);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public int getPadding()
	{
		return getBox().getPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Integer getOwnPadding()
	{
		return getBox().getOwnPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setPadding(int padding)
	{
		getBox().setPadding(padding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setPadding(Integer padding)
	{
		getBox().setPadding(padding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public byte getTopBorder()
	{
		return getBox().getTopBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Byte getOwnTopBorder()
	{
		return getBox().getOwnTopBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setTopBorder(byte topBorder)
	{
		getBox().setTopBorder(topBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setTopBorder(Byte topBorder)
	{
		getBox().setTopBorder(topBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getTopBorderColor()
	{
		return getBox().getTopBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getOwnTopBorderColor()
	{
		return getBox().getOwnTopBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setTopBorderColor(Color topBorderColor)
	{
		getBox().setTopBorderColor(topBorderColor);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public int getTopPadding()
	{
		return getBox().getTopPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Integer getOwnTopPadding()
	{
		return getBox().getOwnTopPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setTopPadding(int topPadding)
	{
		getBox().setTopPadding(topPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setTopPadding(Integer topPadding)
	{
		getBox().setTopPadding(topPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public byte getLeftBorder()
	{
		return getBox().getLeftBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Byte getOwnLeftBorder()
	{
		return getBox().getOwnLeftBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setLeftBorder(byte leftBorder)
	{
		getBox().setLeftBorder(leftBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setLeftBorder(Byte leftBorder)
	{
		getBox().setLeftBorder(leftBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getLeftBorderColor()
	{
		return getBox().getLeftBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getOwnLeftBorderColor()
	{
		return getBox().getOwnLeftBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setLeftBorderColor(Color leftBorderColor)
	{
		getBox().setLeftBorderColor(leftBorderColor);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public int getLeftPadding()
	{
		return getBox().getLeftPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Integer getOwnLeftPadding()
	{
		return getBox().getOwnLeftPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setLeftPadding(int leftPadding)
	{
		getBox().setLeftPadding(leftPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setLeftPadding(Integer leftPadding)
	{
		getBox().setLeftPadding(leftPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public byte getBottomBorder()
	{
		return getBox().getBottomBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Byte getOwnBottomBorder()
	{
		return getBox().getOwnBottomBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBottomBorder(byte bottomBorder)
	{
		getBox().setBottomBorder(bottomBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBottomBorder(Byte bottomBorder)
	{
		getBox().setBottomBorder(bottomBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getBottomBorderColor()
	{
		return getBox().getBottomBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getOwnBottomBorderColor()
	{
		return getBox().getOwnBottomBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBottomBorderColor(Color bottomBorderColor)
	{
		getBox().setBottomBorderColor(bottomBorderColor);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public int getBottomPadding()
	{
		return getBox().getBottomPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Integer getOwnBottomPadding()
	{
		return getBox().getOwnBottomPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBottomPadding(int bottomPadding)
	{
		getBox().setBottomPadding(bottomPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setBottomPadding(Integer bottomPadding)
	{
		getBox().setBottomPadding(bottomPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public byte getRightBorder()
	{
		return getBox().getRightBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Byte getOwnRightBorder()
	{
		return getBox().getOwnRightBorder();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setRightBorder(byte rightBorder)
	{
		getBox().setRightBorder(rightBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setRightBorder(Byte rightBorder)
	{
		getBox().setRightBorder(rightBorder);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getRightBorderColor()
	{
		return getBox().getRightBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Color getOwnRightBorderColor()
	{
		return getBox().getOwnRightBorderColor();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setRightBorderColor(Color rightBorderColor)
	{
		getBox().setRightBorderColor(rightBorderColor);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public int getRightPadding()
	{
		return getBox().getRightPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public Integer getOwnRightPadding()
	{
		return getBox().getOwnRightPadding();
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setRightPadding(int rightPadding)
	{
		getBox().setRightPadding(rightPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getBox()}
	 */
	public void setRightPadding(Integer rightPadding)
	{
		getBox().setRightPadding(rightPadding);
	}

	/**
	 * @deprecated Replaced by {@link #getHyperlinkTypeValue()}.
	 */
	public byte getHyperlinkType()
	{
		return getHyperlinkTypeValue().getValue();
	}

	/**
	 *
	 */
	public HyperlinkTypeEnum getHyperlinkTypeValue()
	{
		return ((JRImage)parent).getHyperlinkTypeValue();
	}
		
	/**
	 *
	 */
	public byte getHyperlinkTarget()
	{
		return ((JRImage)this.parent).getHyperlinkTarget();
	}
		
	/**
	 *
	 */
	public String getLinkTarget()
	{
		return ((JRImage)this.parent).getLinkTarget();
	}
		
	/**
	 *
	 */
	public JRExpression getExpression()
	{
		return ((JRImage)this.parent).getExpression();
	}

	/**
	 *
	 */
	public JRExpression getAnchorNameExpression()
	{
		return ((JRImage)this.parent).getAnchorNameExpression();
	}

	/**
	 *
	 */
	public JRExpression getHyperlinkReferenceExpression()
	{
		return ((JRImage)this.parent).getHyperlinkReferenceExpression();
	}

	/**
	 *
	 */
	public JRExpression getHyperlinkAnchorExpression()
	{
		return ((JRImage)this.parent).getHyperlinkAnchorExpression();
	}

	/**
	 *
	 */
	public JRExpression getHyperlinkPageExpression()
	{
		return ((JRImage)this.parent).getHyperlinkPageExpression();
	}

		
	/**
	 *
	 */
	protected JRRenderable getRenderer()
	{
		return this.renderer;
	}
		
	/**
	 *
	 */
	protected String getAnchorName()
	{
		return this.anchorName;
	}

	/**
	 *
	 */
	protected String getHyperlinkReference()
	{
		return this.hyperlinkReference;
	}

	/**
	 *
	 */
	protected String getHyperlinkAnchor()
	{
		return this.hyperlinkAnchor;
	}

	/**
	 *
	 */
	protected Integer getHyperlinkPage()
	{
		return this.hyperlinkPage;
	}
		

	protected String getHyperlinkTooltip()
	{
		return this.hyperlinkTooltip;
	}
		

	/**
	 *
	 */
	protected JRTemplateImage getJRTemplateImage()
	{
		return (JRTemplateImage) getElementTemplate();
	}

	protected JRTemplateElement createElementTemplate()
	{
		JRTemplateImage template = 
			new JRTemplateImage(
				getElementOrigin(), 
				filler.getJasperPrint().getDefaultStyleProvider(), 
				this
				);
		
		if (getScaleImageValue() == ScaleImageEnum.REAL_HEIGHT
				|| getScaleImageValue() == ScaleImageEnum.REAL_SIZE)
		{
			template.setScaleImage(ScaleImageEnum.RETAIN_SHAPE);
		}
		
		return template;
	}


	/**
	 *
	 */
	protected void evaluate(
		byte evaluation
		) throws JRException
	{
		initDelayedEvaluations();
		
		reset();
		
		evaluatePrintWhenExpression(evaluation);

		if (isPrintWhenExpressionNull() || isPrintWhenTrue())
		{
			if (isEvaluateNow())
			{
				hasOverflowed = false;
				evaluateImage(evaluation);
			}
		}
	}
	

	/**
	 *
	 */
	protected void evaluateImage(
		byte evaluation
		) throws JRException
	{
		evaluateProperties(evaluation);
		
		JRExpression expression = this.getExpression();

		JRRenderable newRenderer = null;
		
		Object source = evaluateExpression(expression, evaluation);
		if (source != null)
		{
			if (isUsingCache() && filler.fillContext.hasLoadedImage(source))
			{
				newRenderer = filler.fillContext.getLoadedImage(source).getRenderer();
			}
			else
			{
				Class expressionClass = expression.getValueClass();
				
				if (Image.class.getName().equals(expressionClass.getName()))
				{
					Image img = (Image) source;
					newRenderer = JRImageRenderer.getInstance(img, getOnErrorTypeValue());
				}
				else if (InputStream.class.getName().equals(expressionClass.getName()))
				{
					InputStream is = (InputStream) source;
					newRenderer = JRImageRenderer.getInstance(is, getOnErrorTypeValue());
				}
				else if (URL.class.getName().equals(expressionClass.getName()))
				{
					URL url = (URL) source;
					newRenderer = JRImageRenderer.getInstance(url, getOnErrorTypeValue());
				}
				else if (File.class.getName().equals(expressionClass.getName()))
				{
					File file = (File) source;
					newRenderer = JRImageRenderer.getInstance(file, getOnErrorTypeValue());
				}
				else if (String.class.getName().equals(expressionClass.getName()))
				{
					String location = (String) source;
					newRenderer = 
						JRImageRenderer.getInstance(
							location, 
							getOnErrorTypeValue(), 
							isLazy(), 
							filler.reportClassLoader,
							filler.urlHandlerFactory,
							filler.fileResolver
							);
				}
				else if (JRRenderable.class.getName().equals(expressionClass.getName()))
				{
					newRenderer = (JRRenderable) source;
				}

				if (isUsingCache())
				{
					JRPrintImage img = new JRTemplatePrintImage(getJRTemplateImage());
					img.setRenderer(newRenderer);
					filler.fillContext.registerLoadedImage(source, img);
				}
			}
		}

		setValueRepeating(this.renderer == newRenderer);
	
		this.renderer = newRenderer;
		
		this.anchorName = (String) evaluateExpression(this.getAnchorNameExpression(), evaluation);
		this.hyperlinkReference = (String) evaluateExpression(this.getHyperlinkReferenceExpression(), evaluation);
		this.hyperlinkAnchor = (String) evaluateExpression(this.getHyperlinkAnchorExpression(), evaluation);
		this.hyperlinkPage = (Integer) evaluateExpression(this.getHyperlinkPageExpression(), evaluation);
		this.hyperlinkTooltip = (String) evaluateExpression(this.getHyperlinkTooltipExpression(), evaluation);
		hyperlinkParameters = JRFillHyperlinkHelper.evaluateHyperlinkParameters(this, expressionEvaluator, evaluation);
	}
	

	protected boolean prepare(
		int availableHeight,
		boolean isOverflow
		) throws JRException
	{
		boolean willOverflow = false;

		if (
			this.isPrintWhenExpressionNull() ||
			( !this.isPrintWhenExpressionNull() && 
			this.isPrintWhenTrue() )
			)
		{
			this.setToPrint(true);
		}
		else
		{
			this.setToPrint(false);
		}

		if (!this.isToPrint())
		{
			return willOverflow;
		}
		
		boolean isToPrint = true;
		boolean isReprinted = false;

		if (isEvaluateNow())
		{
			if (isOverflow && this.isAlreadyPrinted() && !this.isPrintWhenDetailOverflows())
			{
				isToPrint = false;
			}
	
			if (
				isToPrint && 
				this.isPrintWhenExpressionNull() &&
				!this.isPrintRepeatedValues() &&
				isValueRepeating()
				)
			{
				if (
					( !this.isPrintInFirstWholeBand() || !this.getBand().isFirstWholeOnPageColumn() ) &&
					( this.getPrintWhenGroupChanges() == null || !this.getBand().isNewGroup(this.getPrintWhenGroupChanges()) ) &&
					( !isOverflow || !this.isPrintWhenDetailOverflows() )
					)
				{
					isToPrint = false;
				}
			}

			if (
				isToPrint && 
				this.isRemoveLineWhenBlank() &&
				this.getRenderer() == null
				)
			{
				isToPrint = false;
			}
	
			if (isToPrint)
			{
				if (availableHeight < getRelativeY() + getHeight())
				{
					isToPrint = false;
					willOverflow = true;
				}
				else if (!isLazy() && (getScaleImageValue() == ScaleImageEnum.REAL_HEIGHT
						|| getScaleImageValue() == ScaleImageEnum.REAL_SIZE))
				{
					int padding = getLineBox().getBottomPadding().intValue() 
							+ getLineBox().getTopPadding().intValue();
					boolean reprinted = isOverflow 
						&& (this.isPrintWhenDetailOverflows() 
								&& (this.isAlreadyPrinted() 
										|| (!this.isAlreadyPrinted() && !this.isPrintRepeatedValues())));
					boolean imageOverflowAllowed = 
							filler.isBandOverFlowAllowed() && !reprinted && !hasOverflowed;
					boolean fits = fitImage(availableHeight - getRelativeY() - padding, imageOverflowAllowed, 
							getHorizontalAlignmentValue());
					if (fits)
					{
						if (imageHeight != null)
						{
							setStretchHeight(imageHeight.intValue() + padding);
						}
					}
					else
					{
						hasOverflowed = true;
						isToPrint = false;
						willOverflow = true;
						setStretchHeight(availableHeight - getRelativeY() - padding);
					}
				}
			}
			
			if (
				isToPrint && 
				isOverflow && 
				//(this.isAlreadyPrinted() || !this.isPrintRepeatedValues())
				(this.isPrintWhenDetailOverflows() && (this.isAlreadyPrinted() || (!this.isAlreadyPrinted() && !this.isPrintRepeatedValues())))
				)
			{
				isReprinted = true;
			}
		}
		else
		{
			if (isOverflow && this.isAlreadyPrinted() && !this.isPrintWhenDetailOverflows())
			{
				isToPrint = false;
			}
	
			if (
				isToPrint && 
				availableHeight < this.getRelativeY() + getHeight()
				)
			{
				isToPrint = false;
				willOverflow = true;
			}
			
			if (
				isToPrint && 
				isOverflow && 
				//(this.isAlreadyPrinted() || !this.isPrintRepeatedValues())
				(this.isPrintWhenDetailOverflows() && (this.isAlreadyPrinted() || (!this.isAlreadyPrinted() && !this.isPrintRepeatedValues())))
				)
			{
				isReprinted = true;
			}
		}

		this.setToPrint(isToPrint);
		this.setReprinted(isReprinted);
		
		return willOverflow;
	}

	protected void reset()
	{
		imageHeight = null;
		imageWidth = null;
		imageX = null;
		
		super.reset();
	}

	protected boolean fitImage(int availableHeight, boolean overflowAllowed,
			HorizontalAlignEnum hAlign) throws JRException
	{
		imageHeight = null;
		imageWidth = null;
		imageX = null;
		
		Dimension2D imageSize = renderer == null ? null : renderer.getDimension();
		if (imageSize == null)
		{
			return true;
		}
		
		int realHeight = (int) imageSize.getHeight();
		int realWidth = (int) imageSize.getWidth();
		boolean fitted;
		
		int reducedHeight = realHeight;
		int reducedWidth = realWidth;
		if (realWidth > getWidth())
		{
			double wRatio = ((double) getWidth()) / realWidth;
			reducedHeight = (int) (wRatio * realHeight);
			reducedWidth = getWidth();
		}		
		
		if (reducedHeight <= availableHeight)
		{
			imageHeight = Integer.valueOf(reducedHeight);
			if (getScaleImageValue() == ScaleImageEnum.REAL_SIZE)
			{
				imageWidth = Integer.valueOf(reducedWidth);
			}
			fitted = true;
		}
		else if (overflowAllowed)
		{
			fitted = false;
		}
		else
		{
			imageHeight = Integer.valueOf(availableHeight);
			if (getScaleImageValue() == ScaleImageEnum.REAL_SIZE)
			{
				double hRatio = ((double) availableHeight) / realHeight;
				imageWidth = Integer.valueOf((int) (hRatio * realWidth));
			}
			fitted = true;
		}

		if (imageWidth != null && imageWidth.intValue() != getWidth())
		{
			switch (hAlign)
			{
			case RIGHT:
				imageX = Integer.valueOf(getX() + getWidth() - imageWidth.intValue());
				break;
			case CENTER:
				imageX = Integer.valueOf(getX() + (getWidth() - imageWidth.intValue()) / 2);
				break;
			default:
				break;
			}
		}
		
		return fitted;
	}

	/**
	 *
	 */
	protected JRPrintElement fill() throws JRException
	{
		EvaluationTimeEnum evaluationTime = this.getEvaluationTimeValue();
		JRTemplatePrintImage printImage;
		JRRecordedValuesPrintImage recordedValuesImage;
		if (isEvaluateAuto())
		{
			printImage = recordedValuesImage = new JRRecordedValuesPrintImage(getJRTemplateImage());
		}
		else
		{
			printImage = new JRTemplatePrintImage(getJRTemplateImage());
			recordedValuesImage = null;
		}
		
		printImage.setX(this.getX());
		printImage.setY(this.getRelativeY());
		printImage.setWidth(getWidth());
		printImage.setHeight(this.getStretchHeight());

		if (isEvaluateNow())
		{
			this.copy(printImage);
		}
		else if (isEvaluateAuto())
		{
			initDelayedEvaluationPrint(recordedValuesImage);
		}
		else
		{
			filler.addBoundElement(this, printImage, evaluationTime, getEvaluationGroup(), band);
		}
		
		return printImage;
	}
		

	/**
	 *
	 */
	protected void copy(JRPrintImage printImage)
	{
		if (imageX != null)
		{
			printImage.setX(imageX.intValue());
		}
		if (imageWidth != null)
		{
			printImage.setWidth(imageWidth.intValue());
		}
		
		printImage.setRenderer(this.getRenderer());
		printImage.setAnchorName(this.getAnchorName());
		printImage.setHyperlinkReference(this.getHyperlinkReference());
		printImage.setHyperlinkAnchor(this.getHyperlinkAnchor());
		printImage.setHyperlinkPage(this.getHyperlinkPage());
		printImage.setHyperlinkTooltip(getHyperlinkTooltip());
		printImage.setBookmarkLevel(this.getBookmarkLevel());
		printImage.setHyperlinkParameters(hyperlinkParameters);
		transferProperties(printImage);
	}


	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	/**
	 *
	 */
	public void visit(JRVisitor visitor)
	{
		visitor.visitImage(this);
	}

	
	protected void resolveElement(JRPrintElement element, byte evaluation) throws JRException
	{
		evaluateImage(evaluation);

		JRPrintImage printImage = (JRPrintImage) element;

		if (getScaleImageValue() == ScaleImageEnum.REAL_SIZE)//to avoid get dimension and thus unnecessarily load the image
		{
			int padding = printImage.getLineBox().getBottomPadding().intValue() 
			+ printImage.getLineBox().getTopPadding().intValue();
			fitImage(getHeight() - padding, false, 
					printImage.getHorizontalAlignmentValue());
		}
		
		copy(printImage);
	}


	public int getBookmarkLevel()
	{
		return ((JRImage)this.parent).getBookmarkLevel();
	}


	public JRFillCloneable createClone(JRFillCloneFactory factory)
	{
		return new JRFillImage(this, factory);
	}
	
	protected void collectDelayedEvaluations()
	{
		super.collectDelayedEvaluations();
		
		collectDelayedEvaluations(getExpression());
		collectDelayedEvaluations(getAnchorNameExpression());
		collectDelayedEvaluations(getHyperlinkReferenceExpression());
		collectDelayedEvaluations(getHyperlinkAnchorExpression());
		collectDelayedEvaluations(getHyperlinkPageExpression());	
	}


	public JRHyperlinkParameter[] getHyperlinkParameters()
	{
		return ((JRImage) parent).getHyperlinkParameters();
	}


	public String getLinkType()
	{
		return ((JRImage) parent).getLinkType();
	}


	public JRExpression getHyperlinkTooltipExpression()
	{
		return ((JRImage) parent).getHyperlinkTooltipExpression();
	}

}
