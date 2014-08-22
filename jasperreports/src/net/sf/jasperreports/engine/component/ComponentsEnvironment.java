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
package net.sf.jasperreports.engine.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;
import net.sf.jasperreports.extensions.ExtensionsRegistry;

import org.apache.commons.collections.ReferenceMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A class that provides access to {@link ComponentsBundle component bundles}.
 * 
 * <p>
 * Component bundles are registered as JasperReports extensions of type
 * {@link ComponentsBundle} via the central extension framework (see
 * {@link ExtensionsEnvironment}).
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ComponentsEnvironment.java 3713 2010-04-08 11:06:05Z teodord $
 */
public final class ComponentsEnvironment
{
	
	private static final Log log = LogFactory.getLog(ComponentsEnvironment.class);
	
	private static final ReferenceMap cache = new ReferenceMap(
			ReferenceMap.WEAK, ReferenceMap.HARD);
	
	/**
	 * Returns the set of all component bundles present in the registry. 
	 * 
	 * @return the set of component bundles
	 */
	public static Collection getComponentBundles()
	{
		Map components = getCachedComponentBundles();
		return components.values();
	}
	
	protected static Map getCachedComponentBundles()
	{
		Object cacheKey = ExtensionsEnvironment.getExtensionsCacheKey();
		synchronized (cache)
		{
			Map components = (Map) cache.get(cacheKey);
			if (components == null)
			{
				components = findComponentBundles();
				cache.put(cacheKey, components);
			}
			return components;
		}
	}

	protected static Map findComponentBundles()
	{
		Map components = new HashMap();
		ExtensionsRegistry extensionsRegistry = 
			ExtensionsEnvironment.getExtensionsRegistry();
		List bundles = extensionsRegistry.getExtensions(ComponentsBundle.class);
		for (Iterator it = bundles.iterator(); it.hasNext();)
		{
			ComponentsBundle bundle = (ComponentsBundle) it.next();
			String namespace = bundle.getXmlParser().getNamespace();
			if (components.containsKey(namespace))
			{
				log.warn("Found two components for namespace " + namespace);
			}
			else
			{
				components.put(namespace, bundle);
			}
		}
		return components;
	}
	
	/**
	 * Returns a component bundle that corresponds to a namespace.
	 * 
	 * @param namespace a component bundle namespace
	 * @return the corresponding component bundle
	 * @throws JRRuntimeException if no bundle corresponding to the namespace
	 * is found in the registry
	 */
	public static ComponentsBundle getComponentsBundle(String namespace)
	{
		Map components = getCachedComponentBundles();
		ComponentsBundle componentsBundle = (ComponentsBundle) components.get(namespace);
		if (componentsBundle == null)
		{
			throw new JRRuntimeException("No components bundle registered for namespace " 
					+ namespace);
		}
		return componentsBundle;
	}
	
	/**
	 * Returns a component manager that corresponds to a particular component
	 * type key.
	 * 
	 * @param componentKey the component type key
	 * @return the manager for the component type
	 * @throws JRRuntimeException if the registry does not contain the specified
	 * component type
	 */
	public static ComponentManager getComponentManager(ComponentKey componentKey)
	{
		String namespace = componentKey.getNamespace();
		ComponentsBundle componentsBundle = getComponentsBundle(namespace);
		
		String name = componentKey.getName();
		return componentsBundle.getComponentManager(name);
	}
	

	private ComponentsEnvironment()
	{
	}
}
