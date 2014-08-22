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
package net.sf.jasperreports.engine;

import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.engine.util.JRStyledText;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRPrintText.java 3583 2010-03-12 11:35:40Z shertage $
 */
public interface JRPrintText extends JRPrintElement, JRAlignment, JRPrintAnchor, JRPrintHyperlink, JRBox, JRFont, JRCommonText
{

	/**
	 * Zero-length line break offset array used for {@link #getLineBreakOffsets()}
	 * when the text does not have any line breaks.
	 */
	public static final short[] ZERO_LINE_BREAK_OFFSETS = new short[0];

	/**
	 * @deprecated Replaced by {@link RunDirectionEnum#LTR}.
	 */
	public static final byte RUN_DIRECTION_LTR = 0;
	/**
	 * @deprecated Replaced by {@link RunDirectionEnum#RTL}.
	 */
	public static final byte RUN_DIRECTION_RTL = 1;

	
	/**
	 * Returns the possibly truncated (when {@link #getTextTruncateIndex()} is not null) text of this object.
	 * 
	 * @return the text of this object
	 * @see #getFullText()
	 * @see #getTextTruncateSuffix()
	 */
	public String getText();
		
	/**
	 * Set the text for this object.
	 * 
	 * @param text the text
	 * @see #setTextTruncateIndex(Integer)
	 */
	public void setText(String text);

	/**
	 * Returns the index to which this object's text is to be truncated.
	 * 
	 * This index is usually set at report fill time when the engine is instructed
	 * to keep the full text in the print text object.
	 * 
	 * @return the index to which this object's text is to be truncated
	 * @see JRTextElement#PROPERTY_PRINT_KEEP_FULL_TEXT
	 * @see #getTextTruncateSuffix()
	 */
	public Integer getTextTruncateIndex();

	/**
	 * Sets the index to which this object's text is to be truncated.
	 * 
	 * The test is truncated when {@link #getText()} or {@link #getStyledText(JRStyledTextAttributeSelector)}
	 * are called.
	 * 
	 * @param index the index to which this object's text is to be truncated
	 */
	public void setTextTruncateIndex(Integer index);
	
	/**
	 * Returns the suffix that is to be appended to the truncated text
	 * (as returned by {@link #getText()}.
	 * 
	 * @return the truncated text suffix
	 * @see JRTextElement#PROPERTY_TRUNCATE_SUFFIX
	 */
	public String getTextTruncateSuffix();
	
	/**
	 * Sets the suffix to be appended to the truncated text.
	 * 
	 * @param suffix the suffix to be appended to the truncated text
	 * @see #getTextTruncateSuffix()
	 */
	public void setTextTruncateSuffix(String suffix);
	
	/**
	 * Returns the full (not truncated) text of this object.
	 * 
	 * @return the full text of this object
	 * @see #getText()
	 * @see #getTextTruncateIndex()
	 */
	public String getFullText();
	
	/**
	 * Returns the original text that was set in this object.
	 * 
	 * @return the original text
	 */
	public String getOriginalText();
	
	/**
	 * Returns the styled text for this object.
	 * 
	 * The text is truncated according to {@link #getText()}.
	 * 
	 * @param attributeSelector the styled text attribute selector
	 * @return the possibly truncated styled text for this object
	 */
	public JRStyledText getStyledText(JRStyledTextAttributeSelector attributeSelector);
	
	/**
	 * Returns the full styled text of this object.
	 * 
	 * @param attributeSelector the styled text attribute selector
	 * @return the full styled text of this object
	 * @see #getFullText()
	 */
	public JRStyledText getFullStyledText(JRStyledTextAttributeSelector attributeSelector);
	
	/**
	 *
	 */
	public float getLineSpacingFactor();
		
	/**
	 *
	 */
	public void setLineSpacingFactor(float lineSpacingFactor);

	/**
	 *
	 */
	public float getLeadingOffset();
		
	/**
	 *
	 */
	public void setLeadingOffset(float leadingOffset);

	/**
	 * @deprecated Replaced by {@link #getHorizontalAlignment()}.
	 */
	public byte getTextAlignment();
		
	/**
	 * @deprecated Replaced by {@link #setHorizontalAlignment(byte)}.
	 */
	public void setTextAlignment(byte horizontalAlignment);
		
	/**
	 * @deprecated Replaced by {@link #getOwnRotationValue()}.
	 */
	public Byte getOwnRotation();
	
	/**
	 * @deprecated Replaced by {@link #setRotation(RotationEnum)}.
	 */
	public void setRotation(byte rotation);
		
	/**
	 * @deprecated Replaced by {@link #setRotation(RotationEnum)}.
	 */
	public void setRotation(Byte rotation);
	
	/**
	 * Gets the text own rotation.
	 * @return a value representing one of the text rotation constants in {@link RotationEnum}
	 */
	public RotationEnum getOwnRotationValue();
	
	/**
	 * Sets the text rotation.
	 * @param rotationEnum a value representing one of the text rotation constants in {@link RotationEnum}
	 */
	public void setRotation(RotationEnum rotationEnum);
	
	/**
	 * @deprecated Replaced by {@link #getRunDirectionValue()}.
	 */
	public byte getRunDirection();
		
	/**
	 * @deprecated Replaced by {@link #setRunDirection(RunDirectionEnum)}.
	 */
	public void setRunDirection(byte rotation);
		
	/**
	 * Gets the text run direction.
	 * @return a value representing one of the run direction constants in {@link RunDirectionEnum}
	 */
	public RunDirectionEnum getRunDirectionValue();
	
	/**
	 * Sets the text run direction.
	 * @param runDirectionEnum a value representing one of the text run direction constants in {@link RunDirectionEnum}
	 */
	public void setRunDirection(RunDirectionEnum runDirectionEnum);
	
	/**
	 *
	 */
	public float getTextHeight();
		
	/**
	 *
	 */
	public void setTextHeight(float textHeight);
		
	/**
	 * @deprecated Replaced by {@link #getOwnLineSpacingValue()}.
	 */
	public Byte getOwnLineSpacing();
		
	/**
	 * @deprecated Replaced by {@link #setLineSpacing(LineSpacingEnum)}.
	 */
	public void setLineSpacing(byte lineSpacing);
		
	/**
	 * @deprecated Replaced by {@link #setLineSpacing(LineSpacingEnum)}.
	 */
	public void setLineSpacing(Byte lineSpacing);
		
	/**
	 * 
	 */
	public LineSpacingEnum getOwnLineSpacingValue();
		
	/**
	 *
	 */
	public void setLineSpacing(LineSpacingEnum lineSpacing);
		
	/**
	 * @deprecated Replaced by {@link #setMarkup(String)}
	 */
	public void setStyledText(boolean isStyledText);
		
	/**
	 * @deprecated Replaced by {@link #setMarkup(String)}
	 */
	public void setStyledText(Boolean isStyledText);
		
	/**
	 *
	 */
	public String getOwnMarkup();
		
	/**
	 *
	 */
	public void setMarkup(String markup);
		
	/**
	 * @deprecated Replaced by {@link JRBoxContainer#getLineBox()}
	 */
	public JRBox getBox();

	/**
	 * @deprecated
	 */
	public void setBox(JRBox box);

	/**
	 * @deprecated
	 */
	public JRFont getFont();

	/**
	 * @deprecated
	 */
	public void setFont(JRFont font);
	
	/**
	 * Returns the type of the value which was used to generate this text.
	 * <p>
	 * {@link JRTextField Text fields} that have a non-<code>String</code> expression
	 * save the value type using this attribute.  This information can be used by
	 * exporters to treat numerical or date texts (for instance) in a special manner.
	 * </p>
	 * 
	 * @return the type of the original value used to generate the text
	 */
	public String getValueClassName();
	
	/**
	 * Returns the pattern used to format a value that was the source of this text. 
	 * <p>
	 * The pattern can be used to parse the text back to its source value.
	 * </p>
	 * 
	 * @return the pattern used to format this text's source value
	 * @see #getValueClassName()
	 */
	public String getPattern();
	

	/**
	 * Returns the name of the class implementing the {@link net.sf.jasperreports.engine.util.FormatFactory FormatFactory}
	 * interface to use with this text element, in case it is not the same as the one for the overall document.
	 */
	public String getFormatFactoryClass();
	
	
	/**
	 * Returns the code of the <code>java.util.Locale</code> which was used 
	 * while formatting the source value of the text.
	 * <p>
	 * The code is created using the {@link java.util.Locale#toString() java.util.Locale.toString()}
	 * convention.
	 * </p>
	 * <p>
	 * When this attribute is null, the locale returned by
	 * {@link JasperPrint#getLocaleCode() JasperPrint.getLocaleCode()} is used.
	 * This way, the locale is specified in a single place when all the (or many ) texts from a print object
	 * were formatted using the same locale.
	 * </p>
	 * 
	 * @return the code of the <code>java.util.Locale</code> used when formatting this text's source value
	 */
	public String getLocaleCode();
	
	
	/**
	 * Returns the {@link java.util.TimeZone#getID() ID} of the <code>java.util.TimeZone</code>
	 * used to format this text's date source value.
	 * <p>
	 * When this attribute is null, the time zone returned by
	 * {@link JasperPrint#getTimeZoneId() JasperPrint.getTimeZoneId()} is used.
	 * </p>
	 * 
	 * @return the {@link java.util.TimeZone#getID() ID} of the <code>java.util.TimeZone</code>
	 * used to format this text's date source value
	 */
	public String getTimeZoneId();

	
	/**
	 * Returns the line break offsets for the text if saved during report fill.
	 * 
	 * <p>
	 * The array of offsets is incremental, each offset adds to the
	 * previous value.  I.e. the positions at which line breaks occur
	 * are <code>offset[0]</code>, <code>offset[0] + offset[1]</code>,
	 * <code>offset[0] + offset[1] + offset[2]</code> and so on.
	 * 
	 * <p>
	 * This array does not include positions for explicit line breaks in the
	 * text, i.e. for line breaks that occur due to new line characters in
	 * the text.  The array only includes line break positions within a 
	 * paragraph in the text.
	 * 
	 * <p>
	 * If the text was meant to have saved line break offsets but no (non
	 * explicit) breaks were detected (e.g. if the text is a single line),
	 * this method will return a zero-length array.  If the text was not meant
	 * to include saved line breaks, the method will return <code>null</code>.
	 * 
	 * @return the line break offsets (possibly zero-length array), or
	 * <code>null</code> if no line break positions were saved during the fill
	 * 
	 * @see JRTextElement#PROPERTY_SAVE_LINE_BREAKS
	 */
	public short[] getLineBreakOffsets();

	
	/**
	 * Sets the line break offsets for the text.
	 * 
	 * @param lineBreakOffsets the line break offsets
	 * @see #getLineBreakOffsets()
	 */
	public void setLineBreakOffsets(short[] lineBreakOffsets);

}
