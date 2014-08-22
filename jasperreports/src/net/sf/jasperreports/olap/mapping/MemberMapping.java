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
package net.sf.jasperreports.olap.mapping;

import java.util.Iterator;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: MemberMapping.java 3035 2009-08-27 12:05:03Z teodord $
 */
public class MemberMapping implements Mapping
{
	private final Member member;
	private final MemberProperty property;
	
	public MemberMapping(Member member, MemberProperty property)
	{
		this.member = member;
		this.property = property;
	}

	public Member getMember()
	{
		return member;
	}

	public MemberProperty getProperty()
	{
		return property;
	}

	public Iterator memberMappings()
	{
		return new SingleIt(member);
	}
	
	protected static class SingleIt implements Iterator
	{
		boolean first;
		final Object o;
		
		SingleIt (Object o)
		{
			this.o = o;
			first = true;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		public boolean hasNext()
		{
			boolean next = first;
			first = false;
			return next;
		}

		public Object next()
		{
			return o;
		}
	}
}
