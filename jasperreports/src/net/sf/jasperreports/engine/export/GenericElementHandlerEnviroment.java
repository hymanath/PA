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
package net.sf.jasperreports.engine.export;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRGenericElementType;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;

import org.apache.commons.collections.ReferenceMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A class that provides access to 
 * {@link GenericElementHandler generic element handlers}.
 * 
 * <p>
 * Generic element handler bundles are registered as JasperReports extensions
 * of type {@link GenericElementHandlerBundle} via the central extension
 * framework (see {@link ExtensionsEnvironment}). 
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: GenericElementHandlerEnviroment.java 3713 2010-04-08 11:06:05Z teodord $
 */
public final class GenericElementHandlerEnviroment
{

	private static final Log log = LogFactory.getLog(
			GenericElementHandlerEnviroment.class);
	
	private static final ReferenceMap handlersCache = new ReferenceMap(
			ReferenceMap.WEAK, ReferenceMap.HARD);
	
	/**
	 * Returns a handler for a generic print element type and an exporter
	 * key.
	 *
	 * <p>
	 * The method first locates a 
	 * {@link GenericElementHandlerBundle handler bundle} that matches the type
	 * namespace, and then uses 
	 * {@link GenericElementHandlerBundle#getHandler(String, String)} to
	 * resolve an export handler.
	 * 
	 * @param type the generic element type
	 * @param exporterKey the exporter key
	 * @return a generic print element handler
	 * @throws JRRuntimeException if a handler does not exist for the 
	 * combination of element type and exporter key
	 */
	public static GenericElementHandler getHandler(JRGenericElementType type,
			String exporterKey)
	{
		Map handlerBundles = getHandlerBundles();
		GenericElementHandlerBundle bundle = (GenericElementHandlerBundle) 
				handlerBundles.get(type.getNamespace());
		if (bundle == null)
		{
			throw new JRRuntimeException(
					"No generic element handlers found for namespace " 
					+ type.getNamespace());
		}
		return bundle.getHandler(type.getName(), exporterKey);
	}

	protected static Map getHandlerBundles()
	{
		Object cacheKey = ExtensionsEnvironment.getExtensionsCacheKey();
		Map handlerBundles;
		synchronized (handlersCache)
		{
			handlerBundles = (Map) handlersCache.get(cacheKey);
			if (handlerBundles == null)
			{
				handlerBundles = loadHandlerBundles();
				handlersCache.put(cacheKey, handlerBundles);
			}
		}
		return handlerBundles;
	}

	protected static Map loadHandlerBundles()
	{
		List bundleList = ExtensionsEnvironment.getExtensionsRegistry()
				.getExtensions(GenericElementHandlerBundle.class);
		Map bundles = new HashMap();
		for (Iterator it = bundleList.iterator(); it.hasNext();)
		{
			GenericElementHandlerBundle bundle = (GenericElementHandlerBundle) it.next();
			String namespace = bundle.getNamespace();
			if (bundles.containsKey(namespace))
			{
				log.warn("Found two generic element handler bundles for namespace " 
						+ namespace);
			}
			else
			{
				bundles.put(namespace, bundle);
			}
		}
		return bundles;
	}

	
	private GenericElementHandlerEnviroment()
	{
	}
}
