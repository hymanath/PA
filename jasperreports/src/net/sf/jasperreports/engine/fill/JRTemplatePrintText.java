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
import java.io.IOException;
import java.io.ObjectInputStream;

import net.sf.jasperreports.engine.JRAnchor;
import net.sf.jasperreports.engine.JRBox;
import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPrintHyperlinkParameters;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRReportFont;
import net.sf.jasperreports.engine.JRStyledTextAttributeSelector;
import net.sf.jasperreports.engine.PrintElementVisitor;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.util.JRBoxUtil;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.JRStyledTextParser;
import net.sf.jasperreports.engine.util.LineBoxWrapper;


/**
 * Implementation of {@link net.sf.jasperreports.engine.JRPrintText} that uses
 * a {@link net.sf.jasperreports.engine.fill.JRTemplateText} instance to
 * store common attributes. 
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRTemplatePrintText.java 3991 2010-10-12 20:10:55Z lucianc $
 */
public class JRTemplatePrintText extends JRTemplatePrintElement implements JRPrintText
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	private String text = "";
	private Integer textTruncateIndex;
	private String textTruncateSuffix;
	private short[] lineBreakOffsets;
	private transient String truncatedText;
	private float lineSpacingFactor;
	private float leadingOffset;
	private RunDirectionEnum runDirectionValue;
	private float textHeight;
	private String anchorName;
	private String hyperlinkReference;
	private String hyperlinkAnchor;
	private Integer hyperlinkPage;
	private String hyperlinkTooltip;
	private JRPrintHyperlinkParameters hyperlinkParameters;

	/**
	 * The bookmark level for the anchor associated with this field.
	 * @see JRAnchor#getBookmarkLevel()
	 */
	protected int bookmarkLevel = JRAnchor.NO_BOOKMARK;
	
	/**
	 * Creates a print text element.
	 * 
	 * @param text the template text that the element will use
	 */
	public JRTemplatePrintText(JRTemplateText text)
	{
		super(text);
	}

	/**
	 *
	 */
	public String getText()
	{
		if (truncatedText == null && text != null)
		{
			if (getTextTruncateIndex() == null)
			{
				truncatedText = text;
			}
			else
			{
				if (!JRCommonText.MARKUP_NONE.equals(getMarkup()))
				{
					truncatedText = JRStyledTextParser.getInstance().write(
							getFullStyledText(JRStyledTextAttributeSelector.ALL), 
							0, getTextTruncateIndex().intValue());
				}
				else
				{
					truncatedText = text.substring(0, getTextTruncateIndex().intValue());
				}
			}
			
			if (textTruncateSuffix != null)
			{
				truncatedText += textTruncateSuffix;
			}
		}
		return truncatedText;
	}
		
	/**
	 *
	 */
	public void setText(String text)
	{
		this.text = text;
		this.truncatedText = null;
	}

	public Integer getTextTruncateIndex()
	{
		return textTruncateIndex;
	}

	public void setTextTruncateIndex(Integer textTruncateIndex)
	{
		this.textTruncateIndex = textTruncateIndex;
		this.truncatedText = null;
	}

	public String getTextTruncateSuffix()
	{
		return textTruncateSuffix;
	}

	public void setTextTruncateSuffix(String textTruncateSuffix)
	{
		this.textTruncateSuffix = textTruncateSuffix;
		this.truncatedText = null;
	}

	public short[] getLineBreakOffsets()
	{
		return lineBreakOffsets;
	}

	public void setLineBreakOffsets(short[] lineBreakOffsets)
	{
		this.lineBreakOffsets = lineBreakOffsets;
	}

	public String getFullText()
	{
		String fullText = this.text;
		if (textTruncateIndex == null && textTruncateSuffix != null)
		{
			fullText += textTruncateSuffix;
		}
		return fullText;
	}

	public String getOriginalText()
	{
		return text;
	}
	
	public JRStyledText getStyledText(JRStyledTextAttributeSelector attributeSelector)
	{
		if (getText() == null)
		{
			return null;
		}
		
		return 
			JRStyledTextParser.getInstance().getStyledText(
				attributeSelector.getStyledTextAttributes(this), 
				getText(), 
				!JRCommonText.MARKUP_NONE.equals(getMarkup()),
				JRStyledTextAttributeSelector.getTextLocale(this)
				);
	}

	public JRStyledText getFullStyledText(JRStyledTextAttributeSelector attributeSelector)
	{
		if (getFullText() == null)
		{
			return null;
		}

		return 
			JRStyledTextParser.getInstance().getStyledText(
				attributeSelector.getStyledTextAttributes(this), 
				getFullText(), 
				!JRCommonText.MARKUP_NONE.equals(getMarkup()),
				JRStyledTextAttributeSelector.getTextLocale(this)
				);
	}
	
	/**
	 *
	 */
	public float getLineSpacingFactor()
	{
		return lineSpacingFactor;
	}
		
	/**
	 *
	 */
	public void setLineSpacingFactor(float lineSpacingFactor)
	{
		this.lineSpacingFactor = lineSpacingFactor;
	}

	/**
	 *
	 */
	public float getLeadingOffset()
	{
		return leadingOffset;
	}
		
	/**
	 *
	 */
	public void setLeadingOffset(float leadingOffset)
	{
		this.leadingOffset = leadingOffset;
	}

	/**
	 * @deprecated Replaced by {@link #getHorizontalAlignment()}.
	 */
	public byte getTextAlignment()
	{
		return getHorizontalAlignment();
	}
		
	/**
	 * @deprecated Replaced by {@link #setHorizontalAlignment(byte)}.
	 */
	public void setTextAlignment(byte horizontalAlignment)
	{
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
		return ((JRTemplateText)this.template).getHorizontalAlignmentValue();
	}
		
	/**
	 *
	 */
	public HorizontalAlignEnum getOwnHorizontalAlignmentValue()
	{
		return ((JRTemplateText)this.template).getOwnHorizontalAlignmentValue();
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
		return ((JRTemplateText)this.template).getVerticalAlignmentValue();
	}
		
	/**
	 *
	 */
	public VerticalAlignEnum getOwnVerticalAlignmentValue()
	{
		return ((JRTemplateText)this.template).getOwnVerticalAlignmentValue();
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
	 * @deprecated Replaced by {@link #getRotationValue()}.
	 */
	public byte getRotation()
	{
		return getRotationValue().getValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #getOwnRotationValue()}.
	 */
	public Byte getOwnRotation()
	{
		return getOwnRotationValue() == null ? null : getOwnRotationValue().getValueByte();
	}

	/**
	 * @deprecated Replaced by {@link #setRotation(RotationEnum)}.
	 */
	public void setRotation(byte rotation)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #setRotation(RotationEnum)}.
	 */
	public void setRotation(Byte rotation)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 *
	 */
	public RotationEnum getRotationValue()
	{
		return ((JRTemplateText)this.template).getRotationValue();
	}
		
	/**
	 *
	 */
	public RotationEnum getOwnRotationValue()
	{
		return ((JRTemplateText)this.template).getOwnRotationValue();
	}
		
	/**
	 *
	 */
	public void setRotation(RotationEnum rotation)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #getRunDirectionValue()}.
	 */
	public byte getRunDirection()
	{
		return getRunDirectionValue().getValue();
	}

	
	/**
	 * @deprecated Replaced by {@link #setRunDirection(RunDirectionEnum)}.
	 */
	public void setRunDirection(byte runDirection)
	{
		setRunDirection(RunDirectionEnum.getByValue(runDirection));
	}

	/**
	 *
	 */
	public RunDirectionEnum getRunDirectionValue()
	{
		return this.runDirectionValue;
	}

	/**
	 *
	 */
	public void setRunDirection(RunDirectionEnum runDirectionValue)
	{
		this.runDirectionValue = runDirectionValue;
	}
	/**
	 *
	 */
	public float getTextHeight()
	{
		return textHeight;
	}
		
	/**
	 *
	 */
	public void setTextHeight(float textHeight)
	{
		this.textHeight = textHeight;
	}

	/**
	 * @deprecated Replaced by {@link #getLineSpacingValue()}.
	 */
	public byte getLineSpacing()
	{
		return getLineSpacingValue().getValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #getOwnLineSpacingValue()}.
	 */
	public Byte getOwnLineSpacing()
	{
		return getOwnLineSpacingValue() == null ? null : getOwnLineSpacingValue().getValueByte();
	}

	/**
	 * @deprecated Replaced by {@link #setLineSpacing(LineSpacingEnum)}.
	 */
	public void setLineSpacing(byte lineSpacing)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #setLineSpacing(LineSpacingEnum)}.
	 */
	public void setLineSpacing(Byte lineSpacing)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * 
	 */
	public LineSpacingEnum getLineSpacingValue()
	{
		return ((JRTemplateText)template).getLineSpacingValue();
	}
		
	/**
	 * 
	 */
	public LineSpacingEnum getOwnLineSpacingValue()
	{
		return ((JRTemplateText)template).getOwnLineSpacingValue();
	}

	/**
	 * 
	 */
	public void setLineSpacing(LineSpacingEnum lineSpacing)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #getMarkup()}
	 */
	public boolean isStyledText()
	{
		return JRCommonText.MARKUP_STYLED_TEXT.equals(getMarkup());
	}
		
	/**
	 * @deprecated Replaced by {@link #getOwnMarkup()}
	 */
	public Boolean isOwnStyledText()
	{
		String mkp = getOwnMarkup();
		return JRCommonText.MARKUP_STYLED_TEXT.equals(mkp) ? Boolean.TRUE : (mkp == null ? null : Boolean.FALSE);
	}

	/**
	 * @deprecated Replaced by {@link #setMarkup(String)}.
	 */
	public void setStyledText(boolean isStyledText)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 * @deprecated Replaced by {@link #setMarkup(String)}.
	 */
	public void setStyledText(Boolean isStyledText)
	{
		throw new UnsupportedOperationException();
	}
		
	/**
	 *
	 */
	public String getMarkup()
	{
		return ((JRTemplateText)template).getMarkup();
	}
		
	public String getOwnMarkup()
	{
		return ((JRTemplateText)template).getOwnMarkup();
	}

	/**
	 *
	 */
	public void setMarkup(String markup)
	{
		throw new UnsupportedOperationException();
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
		return ((JRTemplateText)template).getLineBox();
	}
		
	/**
	 * @deprecated Replaced by {@link #getLineBox()}
	 */
	public void setBox(JRBox box)
	{
		JRBoxUtil.setBoxToLineBox(box, getLineBox());
	}

	/**
	 * @deprecated
	 */
	public JRFont getFont()
	{
		return (JRTemplateText)template;
	}
		
	/**
	 * @deprecated
	 */
	public void setFont(JRFont font)
	{
	}

	/**
	 *
	 */
	public String getAnchorName()
	{
		return anchorName;
	}
		
	/**
	 *
	 */
	public void setAnchorName(String anchorName)
	{
		this.anchorName = anchorName;
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
		return ((JRTemplateText)this.template).getHyperlinkTypeValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #setHyperlinkType(HyperlinkTypeEnum)}.
	 */
	public void setHyperlinkType(byte hyperlinkType)
	{
		setHyperlinkType(HyperlinkTypeEnum.getByValue(hyperlinkType));
	}

	/**
	 *
	 */
	public void setHyperlinkType(HyperlinkTypeEnum hyperlinkType)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated Replaced by {@link #getHyperlinkTargetValue()}.
	 */
	public byte getHyperlinkTarget()
	{
		return getHyperlinkTargetValue().getValue();
	}
		
	/**
	 *
	 */
	public HyperlinkTargetEnum getHyperlinkTargetValue()
	{
		return ((JRTemplateText)this.template).getHyperlinkTargetValue();
	}
		
	/**
	 * @deprecated Replaced by {@link #setHyperlinkTarget(HyperlinkTargetEnum)}.
	 */
	public void setHyperlinkTarget(byte hyperlinkTarget)
	{
		setHyperlinkTarget(HyperlinkTargetEnum.getByValue(hyperlinkTarget));
	}

	/**
	 *
	 */
	public void setHyperlinkTarget(HyperlinkTargetEnum hyperlinkTarget)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	public String getLinkTarget()
	{
		return ((JRTemplateText)template).getLinkTarget();
	}
		
	/**
	 *
	 */
	public void setLinkTarget(String linkTarget)
	{
	}
	/**
	 *
	 */
	public void setLinkTarget(byte hyperlinkTarget)
	{
	}

	/**
	 *
	 */
	public String getHyperlinkReference()
	{
		return hyperlinkReference;
	}
		
	/**
	 *
	 */
	public void setHyperlinkReference(String hyperlinkReference)
	{
		this.hyperlinkReference = hyperlinkReference;
	}
		
	/**
	 *
	 */
	public String getHyperlinkAnchor()
	{
		return hyperlinkAnchor;
	}
		
	/**
	 *
	 */
	public void setHyperlinkAnchor(String hyperlinkAnchor)
	{
		this.hyperlinkAnchor = hyperlinkAnchor;
	}
		
	/**
	 *
	 */
	public Integer getHyperlinkPage()
	{
		return hyperlinkPage;
	}
		
	/**
	 *
	 */
	public void setHyperlinkPage(Integer hyperlinkPage)
	{
		this.hyperlinkPage = hyperlinkPage;
	}


	public int getBookmarkLevel()
	{
		return bookmarkLevel;
	}


	public void setBookmarkLevel(int bookmarkLevel)
	{
		this.bookmarkLevel = bookmarkLevel;
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
	 *
	 */
	public JRReportFont getReportFont()
	{
		return ((JRTemplateText)template).getReportFont();
	}

	/**
	 *
	 */
	public void setReportFont(JRReportFont reportFont)
	{
	}

	/**
	 *
	 */
	public String getFontName()
	{
		return ((JRTemplateText)template).getFontName();
	}

	/**
	 *
	 */
	public String getOwnFontName()
	{
		return ((JRTemplateText)template).getOwnFontName();
	}

	/**
	 *
	 */
	public void setFontName(String fontName)
	{
	}


	/**
	 *
	 */
	public boolean isBold()
	{
		return ((JRTemplateText)template).isBold();
	}

	/**
	 *
	 */
	public Boolean isOwnBold()
	{
		return ((JRTemplateText)template).isOwnBold();
	}

	/**
	 *
	 */
	public void setBold(boolean isBold)
	{
	}

	/**
	 * Alternative setBold method which allows also to reset
	 * the "own" isBold property.
	 */
	public void setBold(Boolean isBold)
	{
	}


	/**
	 *
	 */
	public boolean isItalic()
	{
		return ((JRTemplateText)template).isItalic();
	}

	/**
	 *
	 */
	public Boolean isOwnItalic()
	{
		return ((JRTemplateText)template).isOwnItalic();
	}

	/**
	 *
	 */
	public void setItalic(boolean isItalic)
	{
	}

	/**
	 * Alternative setItalic method which allows also to reset
	 * the "own" isItalic property.
	 */
	public void setItalic(Boolean isItalic)
	{
	}

	/**
	 *
	 */
	public boolean isUnderline()
	{
		return ((JRTemplateText)template).isUnderline();
	}

	/**
	 *
	 */
	public Boolean isOwnUnderline()
	{
		return ((JRTemplateText)template).isOwnUnderline();
	}

	/**
	 *
	 */
	public void setUnderline(boolean isUnderline)
	{
	}

	/**
	 * Alternative setUnderline method which allows also to reset
	 * the "own" isUnderline property.
	 */
	public void setUnderline(Boolean isUnderline)
	{
	}

	/**
	 *
	 */
	public boolean isStrikeThrough()
	{
		return ((JRTemplateText)template).isStrikeThrough();
	}

	/**
	 *
	 */
	public Boolean isOwnStrikeThrough()
	{
		return ((JRTemplateText)template).isOwnStrikeThrough();
	}

	/**
	 *
	 */
	public void setStrikeThrough(boolean isStrikeThrough)
	{
		setStrikeThrough(isStrikeThrough ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 * Alternative setStrikeThrough method which allows also to reset
	 * the "own" isStrikeThrough property.
	 */
	public void setStrikeThrough(Boolean isStrikeThrough)
	{
	}

	/**
	 *
	 */
	public int getFontSize()
	{
		return ((JRTemplateText)template).getFontSize();
	}

	/**
	 *
	 */
	public Integer getOwnFontSize()
	{
		return ((JRTemplateText)template).getOwnFontSize();
	}

	/**
	 *
	 */
	public void setFontSize(int fontSize)
	{
	}

	/**
	 * Alternative setSize method which allows also to reset
	 * the "own" size property.
	 */
	public void setFontSize(Integer fontSize)
	{
	}

	/**
	 * @deprecated Replaced by {@link #getFontSize()}.
	 */
	public int getSize()
	{
		return getFontSize();
	}

	/**
	 * @deprecated Replaced by {@link #getOwnFontSize()}.
	 */
	public Integer getOwnSize()
	{
		return getOwnFontSize();
	}

	/**
	 * @deprecated Replaced by {@link #setFontSize(int)}.
	 */
	public void setSize(int size)
	{
	}

	/**
	 * @deprecated Replaced by {@link #setFontSize(Integer)}.
	 */
	public void setSize(Integer size)
	{
	}

	/**
	 *
	 */
	public String getPdfFontName()
	{
		return ((JRTemplateText)template).getPdfFontName();
	}

	/**
	 *
	 */
	public String getOwnPdfFontName()
	{
		return ((JRTemplateText)template).getOwnPdfFontName();
	}

	/**
	 *
	 */
	public void setPdfFontName(String pdfFontName)
	{
	}


	/**
	 *
	 */
	public String getPdfEncoding()
	{
		return ((JRTemplateText)template).getPdfEncoding();
	}

	/**
	 *
	 */
	public String getOwnPdfEncoding()
	{
		return ((JRTemplateText)template).getOwnPdfEncoding();
	}

	/**
	 *
	 */
	public void setPdfEncoding(String pdfEncoding)
	{
	}


	/**
	 *
	 */
	public boolean isPdfEmbedded()
	{
		return ((JRTemplateText)template).isPdfEmbedded();
	}

	/**
	 *
	 */
	public Boolean isOwnPdfEmbedded()
	{
		return ((JRTemplateText)template).isOwnPdfEmbedded();
	}

	/**
	 *
	 */
	public void setPdfEmbedded(boolean isPdfEmbedded)
	{
	}

	/**
	 * Alternative setPdfEmbedded method which allows also to reset
	 * the "own" isPdfEmbedded property.
	 */
	public void setPdfEmbedded(Boolean isPdfEmbedded)
	{
	}


	public String getValueClassName()
	{
		return ((JRTemplateText) template).getValueClassName();
	}

	public String getPattern()
	{
		return ((JRTemplateText) template).getPattern();
	}

	public String getFormatFactoryClass()
	{
		return ((JRTemplateText) template).getFormatFactoryClass();
	}

	public String getLocaleCode()
	{
		return ((JRTemplateText) template).getLocaleCode();
	}

	public String getTimeZoneId()
	{
		return ((JRTemplateText) template).getTimeZoneId();
	}

	
	public JRPrintHyperlinkParameters getHyperlinkParameters()
	{
		return hyperlinkParameters;
	}

	
	public void setHyperlinkParameters(JRPrintHyperlinkParameters hyperlinkParameters)
	{
		this.hyperlinkParameters = hyperlinkParameters;
	}

	public String getLinkType()
	{
		return ((JRTemplateText) template).getLinkType();
	}

	public void setLinkType(String type)
	{
	}

	
	public String getHyperlinkTooltip()
	{
		return hyperlinkTooltip;
	}

	
	public void setHyperlinkTooltip(String hyperlinkTooltip)
	{
		this.hyperlinkTooltip = hyperlinkTooltip;
	}

	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID; //NOPMD
	/**
	 * @deprecated
	 */
	private byte runDirection;
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();

		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
		{
			runDirectionValue = RunDirectionEnum.getByValue(runDirection);
		}
	}

	public <T> void accept(PrintElementVisitor<T> visitor, T arg)
	{
		visitor.visit(this, arg);
	}
	
}
