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
package net.sf.jasperreports.engine.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLStreamHandlerFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRRuntimeException;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRImageLoader.java 3939 2010-08-20 09:52:00Z teodord $
 */
public final class JRImageLoader
{


	/**
	 * Configuration property specifying the name of the class implementing the {@link JRImageReader} interface
	 * to be used by the engine. If not set, the engine will try to an image reader implementation that corresponds to the JVM version.
	 */
	public static final String PROPERTY_IMAGE_READER = JRProperties.PROPERTY_PREFIX + "image.reader";

	/**
	 * Configuration property specifying the name of the class implementing the {@link JRImageEncoder} interface
	 * to be used by the engine. If not set, the engine will try to an image encoder implementation that corresponds to the JVM version.
	 */
	public static final String PROPERTY_IMAGE_ENCODER = JRProperties.PROPERTY_PREFIX + "image.encoder";

	/**
	 * @deprecated Replaced by {@link #NO_IMAGE_RESOURCE}.
	 */
	public static final byte NO_IMAGE = 0;
	
	/**
	 * @deprecated Replaced by {@link #SUBREPORT_IMAGE_RESOURCE}.
	 */
	public static final byte SUBREPORT_IMAGE = 1;
	
	/**
	 * @deprecated Replaced by {@link #CHART_IMAGE_RESOURCE}.
	 */
	public static final byte CHART_IMAGE = 2;

	/**
	 * @deprecated Replaced by {@link #CROSSTAB_IMAGE_RESOURCE}.
	 */
	public static final byte CROSSTAB_IMAGE = 3;

	public static final String NO_IMAGE_RESOURCE = "net/sf/jasperreports/engine/images/image-16.png";
	public static final String SUBREPORT_IMAGE_RESOURCE = "net/sf/jasperreports/engine/images/subreport-16.png";
	public static final String CHART_IMAGE_RESOURCE = "net/sf/jasperreports/engine/images/chart-16.png";
	public static final String CROSSTAB_IMAGE_RESOURCE = "net/sf/jasperreports/engine/images/crosstab-16.png";
	public static final String COMPONENT_IMAGE_RESOURCE = "net/sf/jasperreports/engine/images/component-16.png";

	private static final String[] IMAGE_LOCATION = new String[]
		{
		NO_IMAGE_RESOURCE,
		SUBREPORT_IMAGE_RESOURCE,
		CHART_IMAGE_RESOURCE,
		CROSSTAB_IMAGE_RESOURCE
		};

	/**
	 *
	 */
	private static JRImageReader imageReader;
	private static JRImageEncoder imageEncoder;
	

	static
	{
		String readerClassName = JRProperties.getProperty(PROPERTY_IMAGE_READER);
		if (readerClassName == null)
		{
			imageReader = new JRJdk14ImageReader();
		}
		else
		{
			try 
			{
				Class clazz = JRClassLoader.loadClassForRealName(readerClassName);	
				imageReader = (JRImageReader) clazz.newInstance();
			}
			catch (Exception e)
			{
				throw new JRRuntimeException(e);
			}
		}


		String encoderClassName = JRProperties.getProperty(PROPERTY_IMAGE_ENCODER);
		if (encoderClassName == null)
		{
			imageEncoder = new JRJdk14ImageEncoder();
		}
		else
		{
			try 
			{
				Class clazz = JRClassLoader.loadClassForRealName(encoderClassName);	
				imageEncoder = (JRImageEncoder) clazz.newInstance();
			}
			catch (Exception e)
			{
				throw new JRRuntimeException(e);
			}
		}
	}


	/**
	 * @deprecated Replaced by {@link JRLoader#loadBytes(File)}.
	 */
	public static byte[] loadImageDataFromFile(File file) throws JRException
	{
		return JRLoader.loadBytes(file);
	}


	/**
	 * @deprecated Replaced by {@link JRLoader#loadBytes(URL)}.
	 */
	public static byte[] loadImageDataFromURL(URL url) throws JRException
	{
		return JRLoader.loadBytes(url);
	}


	/**
	 * @deprecated Replaced by {@link JRLoader#loadBytes(InputStream)}.
	 */
	public static byte[] loadImageDataFromInputStream(InputStream is) throws JRException
	{
		return JRLoader.loadBytes(is);
	}


	/**
	 * @deprecated Replaced by {@link JRLoader#loadBytesFromLocation(String)}.
	 */
	public static byte[] loadImageDataFromLocation(String location) throws JRException
	{
		return JRLoader.loadBytesFromLocation(location);
	}

	/**
	 * @deprecated Replaced by {@link JRLoader#loadBytesFromLocation(String, ClassLoader)}.
	 */
	public static byte[] loadImageDataFromLocation(String location, ClassLoader classLoader) throws JRException
	{
		return JRLoader.loadBytesFromLocation(location, classLoader);
	}
	
	/**
	 * @deprecated Replaced by {@link JRLoader#loadBytesFromLocation(String, ClassLoader, URLStreamHandlerFactory)}.
	 */
	public static byte[] loadImageDataFromLocation(
		String location, 
		ClassLoader classLoader,
		URLStreamHandlerFactory urlHandlerFactory
		) throws JRException
	{
		return JRLoader.loadBytesFromLocation(location, classLoader, urlHandlerFactory);
	}


	/**
	 * Encoding the image object using an image encoder that supports the supplied image type.
	 * 
	 * @param image the java.awt.Image object to encode
	 * @param imageType the type of the image as specified by one of the constants defined in the JRRenderable interface
	 * @return the encoded image data
	 */
	public static byte[] loadImageDataFromAWTImage(Image image, byte imageType) throws JRException
	{
		return imageEncoder.encode(image, imageType);
	}


	/**
	 * Encodes the image object using an image encoder that supports the JRRenderable.IMAGE_TYPE_JPEG image type.
	 * 
	 * @deprecated Replaced by {@link JRImageLoader#loadImageDataFromAWTImage(Image, byte)}.
	 */
	public static byte[] loadImageDataFromAWTImage(BufferedImage bi) throws JRException
	{
		return loadImageDataFromAWTImage(bi, JRRenderable.IMAGE_TYPE_JPEG);
	}


	/**
	 * Encodes the image object using an image encoder that supports the JRRenderable.IMAGE_TYPE_JPEG image type.
	 * 
	 * @deprecated Replaced by {@link JRImageLoader#loadImageDataFromAWTImage(Image, byte)}.
	 */
	public static byte[] loadImageDataFromAWTImage(Image image) throws JRException
	{
		return loadImageDataFromAWTImage(image, JRRenderable.IMAGE_TYPE_JPEG);
	}


	/**
	 * @deprecated To be removed in future releases.
	 */
	public static Image getImage(byte index) throws JRException
	{
		return loadImage(IMAGE_LOCATION[index]);
	}


	/**
	 *
	 */
	public static Image loadImage(byte[] bytes) throws JRException
	{
		return imageReader.readImage(bytes);
	}


	/**
	 * Loads an image from an specified resource.
	 * 
	 * @param image the resource name
	 * @throws JRException
	 * @deprecated To be removed in future releases.
	 */
	protected static Image loadImage(String image) throws JRException 
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(image);
		if (url == null)
		{
			//if (!wasWarning)
			//{
			//	if (log.isWarnEnabled())
			//		log.warn("Failure using Thread.currentThread().getContextClassLoader() in JRImageLoader class. Using JRImageLoader.class.getClassLoader() instead.");
			//	wasWarning = true;
			//}
			classLoader = JRImageLoader.class.getClassLoader();
		}
		InputStream is;
		if (classLoader == null)
		{
			is = JRImageLoader.class.getResourceAsStream("/" + image);
		}
		else
		{
			is = classLoader.getResourceAsStream(image);
		}
		
		return imageReader.readImage(JRLoader.loadBytes(is));
	}


	private JRImageLoader()
	{
	}
}
