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

import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.text.CharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.export.TextRenderer;
import net.sf.jasperreports.engine.util.DelegatePropertiesHolder;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.engine.util.MaxFontSizeFinder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Default text measurer implementation.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: TextMeasurer.java 3940 2010-08-20 10:35:15Z teodord $
 */
public class TextMeasurer implements JRTextMeasurer
{

	private static final Log log = LogFactory.getLog(TextMeasurer.class);

	/**
	 *
	 */
	private static final FontRenderContext FONT_RENDER_CONTEXT = TextRenderer.LINE_BREAK_FONT_RENDER_CONTEXT;

	private JRCommonText textElement;
	private JRPropertiesHolder propertiesHolder;

	/**
	 * 
	 */
	private MaxFontSizeFinder maxFontSizeFinder;
	
	private int width;
	private int height;
	private int topPadding;
	private int leftPadding;
	private int bottomPadding;
	private int rightPadding;
	private float lineSpacing;

	private float formatWidth;
	private int maxHeight;
	private boolean canOverflow;
	private Map globalAttributes;
	private TextMeasuredState measuredState;
	private TextMeasuredState prevMeasuredState;
	
	protected static class TextMeasuredState implements JRMeasuredText, Cloneable
	{
		private final boolean saveLineBreakOffsets;
		
		protected int textOffset;
		protected int lines;
		protected int fontSizeSum;
		protected int firstLineMaxFontSize;
		protected float textHeight;
		protected float firstLineLeading;
		protected boolean isLeftToRight = true;
		protected String textSuffix;
		
		protected int lastOffset;
		protected ArrayList lineBreakOffsets;
		
		public TextMeasuredState(boolean saveLineBreakOffsets)
		{
			this.saveLineBreakOffsets = saveLineBreakOffsets;
		}
		
		public boolean isLeftToRight()
		{
			return isLeftToRight;
		}
		
		public int getTextOffset()
		{
			return textOffset;
		}
		
		public float getTextHeight()
		{
			return textHeight;
		}
		
		public float getLineSpacingFactor()
		{
			if (lines > 0)
			{
				return textHeight / fontSizeSum;
			}
			return 0;
		}
		
		public float getLeadingOffset()
		{
			return firstLineLeading - firstLineMaxFontSize * getLineSpacingFactor();
		}

		public String getTextSuffix()
		{
			return textSuffix;
		}
		
		public TextMeasuredState cloneState()
		{
			try
			{
				TextMeasuredState clone = (TextMeasuredState) super.clone();
				
				//clone the list of offsets
				//might be a performance problem on very large texts
				if (lineBreakOffsets != null)
				{
					clone.lineBreakOffsets = (ArrayList) lineBreakOffsets.clone();
				}
				
				return clone;
			}
			catch (CloneNotSupportedException e)
			{
				//never
				throw new JRRuntimeException(e);
			}
		}

		protected void addLineBreak()
		{
			if (saveLineBreakOffsets)
			{
				if (lineBreakOffsets == null)
				{
					lineBreakOffsets = new ArrayList();
				}

				int breakOffset = textOffset - lastOffset;
				lineBreakOffsets.add(Integer.valueOf(breakOffset));
				lastOffset = textOffset;
			}
		}
		
		public short[] getLineBreakOffsets()
		{
			if (!saveLineBreakOffsets)
			{
				//if no line breaks are to be saved, return null
				return null;
			}
			
			//if the last line break occurred at the truncation position
			//exclude the last break offset
			int exclude = lastOffset == textOffset ? 1 : 0;
			if (lineBreakOffsets == null 
					|| lineBreakOffsets.size() <= exclude)
			{
				//use the zero length array singleton
				return JRPrintText.ZERO_LINE_BREAK_OFFSETS;
			}
			
			short[] offsets = new short[lineBreakOffsets.size() - exclude];
			boolean overflow = false;
			for (int i = 0; i < offsets.length; i++)
			{
				int offset = ((Integer) lineBreakOffsets.get(i)).intValue();
				if (offset > Short.MAX_VALUE)
				{
					if (log.isWarnEnabled())
					{
						log.warn("Line break offset value " + offset 
								+ " is bigger than the maximum supported value of"
								+ Short.MAX_VALUE 
								+ ". Line break offsets will not be saved for this text.");
					}
					
					overflow = true;
					break;
				}
				offsets[i] = (short) offset;
			}
			
			if (overflow)
			{
				//if a line break offset overflow occurred, do not return any 
				//line break offsets
				return null;
			}
			
			return offsets;
		}
	}
	
	/**
	 * 
	 */
	public TextMeasurer(JRCommonText textElement)
	{
		this.textElement = textElement;
		this.propertiesHolder = textElement instanceof JRPropertiesHolder ? (JRPropertiesHolder) textElement : null;//FIXMENOW all elements are now properties holders, so interfaces might be rearranged
		if (textElement.getDefaultStyleProvider() instanceof JRPropertiesHolder)
		{
			this.propertiesHolder = 
				new DelegatePropertiesHolder(
					propertiesHolder, 
					(JRPropertiesHolder)textElement.getDefaultStyleProvider()
					);
		}
	}
	
	/**
	 * 
	 */
	protected void initialize(JRStyledText styledText,
			int remainingTextStart,
			int availableStretchHeight, boolean canOverflow)
	{
		width = textElement.getWidth();
		height = textElement.getHeight();
		
		topPadding = textElement.getLineBox().getTopPadding().intValue();
		leftPadding = textElement.getLineBox().getLeftPadding().intValue();
		bottomPadding = textElement.getLineBox().getBottomPadding().intValue();
		rightPadding = textElement.getLineBox().getRightPadding().intValue();

		switch (textElement.getRotationValue())
		{
			case LEFT :
			{
				width = textElement.getHeight();
				height = textElement.getWidth();
				int tmpPadding = topPadding;
				topPadding = leftPadding;
				leftPadding = bottomPadding;
				bottomPadding = rightPadding;
				rightPadding = tmpPadding;
				break;
			}
			case RIGHT :
			{
				width = textElement.getHeight();
				height = textElement.getWidth();
				int tmpPadding = topPadding;
				topPadding = rightPadding;
				rightPadding = bottomPadding;
				bottomPadding = leftPadding;
				leftPadding = tmpPadding;
				break;
			}
			case UPSIDE_DOWN :
			{
				int tmpPadding = topPadding;
				topPadding = bottomPadding;
				bottomPadding = tmpPadding;
				tmpPadding = leftPadding;
				leftPadding = rightPadding;
				rightPadding = tmpPadding;
				break;
			}
			case NONE :
			default :
			{
			}
		}
		
		/*   */
		switch (textElement.getLineSpacingValue())
		{
			case SINGLE : 
			{
				lineSpacing = 1f;
				break;
			}
			case ONE_AND_HALF : 
			{
				lineSpacing = 1.5f;
				break;
			}
			case DOUBLE : 
			{
				lineSpacing = 2f;
				break;
			}
			default : 
			{
				lineSpacing = 1f;
			}
		}

		maxFontSizeFinder = MaxFontSizeFinder.getInstance(!JRCommonText.MARKUP_NONE.equals(textElement.getMarkup()));

		formatWidth = width - leftPadding - rightPadding;
		formatWidth = formatWidth < 0 ? 0 : formatWidth;
		maxHeight = height + availableStretchHeight - topPadding - bottomPadding;
		maxHeight = maxHeight < 0 ? 0 : maxHeight;
		this.canOverflow = canOverflow;
		this.globalAttributes = styledText.getGlobalAttributes();
		
		boolean saveLineBreakOffsets = JRProperties.getBooleanProperty(propertiesHolder, 
				JRTextElement.PROPERTY_SAVE_LINE_BREAKS, false);
		
		measuredState = new TextMeasuredState(saveLineBreakOffsets);
		measuredState.lastOffset = remainingTextStart;
		prevMeasuredState = null;
		
	}

	/**
	 * 
	 */
	public JRMeasuredText measure(
		JRStyledText styledText,
		int remainingTextStart,
		int availableStretchHeight,
		boolean canOverflow
		)
	{
		/*   */
		initialize(styledText, remainingTextStart, availableStretchHeight, canOverflow);

		AttributedCharacterIterator allParagraphs = 
			styledText.getAwtAttributedString(
				JRProperties.getBooleanProperty(propertiesHolder, JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, false)
				).getIterator();

		int tokenPosition = remainingTextStart;
		int lastParagraphStart = remainingTextStart;
		String lastParagraphText = null;

		String remainingText = styledText.getText().substring(remainingTextStart);
		StringTokenizer tkzer = new StringTokenizer(remainingText, "\n", true);

		boolean rendered = true;
		while(tkzer.hasMoreTokens() && rendered) 
		{
			String token = tkzer.nextToken();

			if ("\n".equals(token))
			{
				rendered = renderParagraph(allParagraphs, lastParagraphStart, lastParagraphText);

				lastParagraphStart = tokenPosition + (tkzer.hasMoreTokens() || tokenPosition == 0 ? 1 : 0);
				lastParagraphText = null;
			}
			else
			{
				lastParagraphStart = tokenPosition;
				lastParagraphText = token;
			}

			tokenPosition += token.length();
		}

		if (rendered && lastParagraphStart < remainingTextStart + remainingText.length())
		{
			renderParagraph(allParagraphs, lastParagraphStart, lastParagraphText);
		}
		
		return measuredState;
	}

	/**
	 * 
	 */
	protected boolean renderParagraph(
		AttributedCharacterIterator allParagraphs,
		int lastParagraphStart,
		String lastParagraphText
		)
	{
		AttributedCharacterIterator paragraph = null;
		
		if (lastParagraphText == null)
		{
			paragraph = 
				new AttributedString(
					" ",
					new AttributedString(
						allParagraphs, 
						lastParagraphStart, 
						lastParagraphStart + 1
						).getIterator().getAttributes()
					).getIterator();
		}
		else
		{
			paragraph = 
				new AttributedString(
					allParagraphs, 
					lastParagraphStart, 
					lastParagraphStart + lastParagraphText.length()
					).getIterator();
		}

		LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, FONT_RENDER_CONTEXT);
		
		measuredState.textOffset = lastParagraphStart;
		
		boolean rendered = true;
		boolean renderedLine = false;
		while (lineMeasurer.getPosition() < paragraph.getEndIndex() && rendered)
		{
			rendered = renderNextLine(lineMeasurer, paragraph);
			renderedLine = renderedLine || rendered;
		}
		
		//if we rendered at least one line, and the last line didn't fit 
		//and the text does not overflow
		if (!rendered && prevMeasuredState != null && !canOverflow)
		{
			//handle last rendered row
			processLastTruncatedRow(allParagraphs, lastParagraphText, lastParagraphStart, renderedLine);
		}
		
		return rendered;
	}
	
	protected void processLastTruncatedRow(AttributedCharacterIterator allParagraphs,
			String paragraphText, int paragraphOffset,
			boolean lineTruncated)
	{
		if (lineTruncated && isToTruncateAtChar())
		{
			truncateLastLineAtChar(allParagraphs, paragraphText, paragraphOffset);
		}
		
		appendTruncateSuffix(allParagraphs);
	}

	protected void truncateLastLineAtChar(AttributedCharacterIterator allParagraphs, String paragraphText, int paragraphOffset)
	{
		//truncate the original line at char
		measuredState = prevMeasuredState.cloneState();
		AttributedCharacterIterator lineParagraph = new AttributedString(
				allParagraphs, 
				measuredState.textOffset,
				paragraphOffset + paragraphText.length()).getIterator();
		LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(
				lineParagraph, 
				BreakIterator.getCharacterInstance(), 
				FONT_RENDER_CONTEXT);
		//render again the last line
		//if the line does not fit now, it will remain empty
		renderNextLine(lineMeasurer, lineParagraph);
	}

	protected void appendTruncateSuffix(AttributedCharacterIterator allParagraphs)
	{
		String truncateSuffx = getTruncateSuffix();
		if (truncateSuffx == null)
		{
			return;
		}
		
		int lineStart = prevMeasuredState.textOffset;

		//advance from the line start until the next line start or the first newline
		StringBuffer lineText = new StringBuffer();
		allParagraphs.setIndex(lineStart);
		while (allParagraphs.getIndex() < measuredState.textOffset 
				&& allParagraphs.current() != '\n')
		{
			lineText.append(allParagraphs.current());
			allParagraphs.next();
		}
		int linePosition = allParagraphs.getIndex() - lineStart;
		
		//iterate to the beginning of the line
		boolean done = false;
		do
		{
			measuredState = prevMeasuredState.cloneState();

			String text = lineText.substring(0, linePosition) + truncateSuffx;
			AttributedString attributedText = new AttributedString(text);
			
			//set original attributes for the text part
			AttributedCharacterIterator lineAttributes = new AttributedString(
					allParagraphs, 
					measuredState.textOffset,
					measuredState.textOffset + linePosition).getIterator();
			setAttributes(attributedText, lineAttributes, 0);
			
			//set global attributes for the suffix part
			setAttributes(attributedText, globalAttributes, 
					text.length() - truncateSuffx.length(), text.length());
			
			AttributedCharacterIterator lineParagraph = attributedText.getIterator();
			
			BreakIterator breakIterator = 
				isToTruncateAtChar() 
				? BreakIterator.getCharacterInstance() 
				: BreakIterator.getLineInstance();
			LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(
					lineParagraph,
					breakIterator,
					FONT_RENDER_CONTEXT);

			if (renderNextLine(lineMeasurer, lineParagraph))
			{
				int lastPos = lineMeasurer.getPosition();
				//test if the entire suffix fit
				if (lastPos == linePosition + truncateSuffx.length())
				{
					//subtract the suffix from the offset
					measuredState.textOffset -= truncateSuffx.length();
					measuredState.textSuffix = truncateSuffx;
					done = true;
				}
				else
				{
					linePosition = breakIterator.preceding(linePosition);
					if (linePosition == BreakIterator.DONE)
					{
						//if the text suffix did not fit the line, only the part of it that fits will show

						//truncate the suffix
						String actualSuffix = truncateSuffx.substring(0, 
								measuredState.textOffset - prevMeasuredState.textOffset);
						//if the last text char is not a new line
						if (prevMeasuredState.textOffset > 0
								&& allParagraphs.setIndex(prevMeasuredState.textOffset - 1) != '\n')
						{
							//force a new line so that the suffix is displayed on the last line
							actualSuffix = '\n' + actualSuffix;
						}
						measuredState.textSuffix = actualSuffix;
						
						//restore the next to last line offset
						measuredState.textOffset = prevMeasuredState.textOffset;

						done = true;
					}
				}
			}
			else
			{
				//if the line did not fit, leave it empty
				done = true;
			}
		}
		while (!done);
	}

	protected boolean isToTruncateAtChar()
	{
		return JRProperties.getBooleanProperty(propertiesHolder, 
				JRTextElement.PROPERTY_TRUNCATE_AT_CHAR, false);
	}

	protected String getTruncateSuffix()
	{
		String truncateSuffx = JRProperties.getProperty(propertiesHolder,
				JRTextElement.PROPERTY_TRUNCATE_SUFFIX);
		if (truncateSuffx != null)
		{
			truncateSuffx = truncateSuffx.trim();
			if (truncateSuffx.length() == 0)
			{
				truncateSuffx = null;
			}
		}
		return truncateSuffx;
	}
	
	protected boolean renderNextLine(LineBreakMeasurer lineMeasurer, AttributedCharacterIterator paragraph)
	{
		int lineStartPosition = lineMeasurer.getPosition();

		TextLayout layout = lineMeasurer.nextLayout(formatWidth);

		float newTextHeight = measuredState.textHeight + layout.getLeading() + lineSpacing * layout.getAscent();
		boolean fits = newTextHeight + layout.getDescent() <= maxHeight;
		if (fits)
		{
			prevMeasuredState = measuredState.cloneState();
			
			measuredState.isLeftToRight = measuredState.isLeftToRight && layout.isLeftToRight();
			measuredState.textHeight = newTextHeight;
			measuredState.lines++;

			measuredState.fontSizeSum += 
				maxFontSizeFinder.findMaxFontSize(
					new AttributedString(
						paragraph, 
						lineStartPosition, 
						lineStartPosition + layout.getCharacterCount()
						).getIterator(),
					textElement.getFontSize()
					);

			if (measuredState.lines == 1)
			{
				measuredState.firstLineLeading = measuredState.textHeight;
				measuredState.firstLineMaxFontSize = measuredState.fontSizeSum;
			}

			// here is the Y offset where we would draw the line
			//lastDrawPosY = drawPosY;
			//
			measuredState.textHeight += layout.getDescent();
			
			measuredState.textOffset += lineMeasurer.getPosition() - lineStartPosition;
			
			if (lineMeasurer.getPosition() < paragraph.getEndIndex())
			{
				//if not the last line in a paragraph, save the line break position
				measuredState.addLineBreak();
			}
		}
		
		return fits;
	}
	
	protected JRPropertiesHolder getTextPropertiesHolder()
	{
		return propertiesHolder;
	}
	
	protected void setAttributes(
			AttributedString string,
			AttributedCharacterIterator attributes, 
			int stringOffset)
	{
		for (char c = attributes.first(); c != CharacterIterator.DONE; c = attributes.next())
		{
			for (Iterator it = attributes.getAttributes().entrySet().iterator(); it.hasNext();)
			{
				Map.Entry attributeEntry = (Map.Entry) it.next();
				AttributedCharacterIterator.Attribute attribute = (Attribute) attributeEntry.getKey();
				if (attributes.getRunStart(attribute) == attributes.getIndex())
				{
					Object attributeValue = attributeEntry.getValue();
					string.addAttribute(attribute, attributeValue, 
							attributes.getIndex() + stringOffset,
							attributes.getRunLimit(attribute) + stringOffset);
				}
			}
		}
	}
	
	protected void setAttributes(
			AttributedString string,
			Map attributes, 
			int startIndex, int endIndex)
	{
		for (Iterator it = attributes.entrySet().iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			AttributedCharacterIterator.Attribute attribute = (Attribute) entry.getKey();
			Object attributeValue = entry.getValue();
			string.addAttribute(attribute, attributeValue, startIndex, endIndex);
		}
	}
}
