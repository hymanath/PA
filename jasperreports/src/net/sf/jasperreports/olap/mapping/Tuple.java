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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: Tuple.java 3035 2009-08-27 12:05:03Z teodord $
 */
public class Tuple
{
	private final List members;
	
	public Tuple ()
	{
		this.members = new ArrayList();
	}
	
	public Tuple (TupleMember member)
	{
		this.members = new ArrayList(1);
		addMember(member);
	}
	
	public void addMember (TupleMember member)
	{
		members.add(member);
	}
	
	public List getMembers ()
	{
		return members;
	}
	
	public String[] getMemberUniqueNames ()
	{
		String[] names = new String[members.size()];
		Iterator it = members.iterator();
		for (int i = 0; i < names.length; ++i)
		{
			TupleMember member = (TupleMember) it.next();
			names[i] = member.getUniqueName();
		}
		return names;
	}
}
