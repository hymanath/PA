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
package net.sf.jasperreports.engine.query;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.util.JRProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * JDBC query executer for SQL queries.
 * <p/>
 * This query executer implementation offers built-in support for SQL queries.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRJdbcQueryExecuter.java 4170 2011-01-27 12:31:13Z shertage $
 */
public class JRJdbcQueryExecuter extends JRAbstractQueryExecuter
{
	private static final Log log = LogFactory.getLog(JRJdbcQueryExecuter.class);

	protected static final String CLAUSE_ID_IN = "IN";
	protected static final String CLAUSE_ID_NOTIN = "NOTIN";
	
	protected static final String CLAUSE_ID_EQUAL = "EQUAL";
	protected static final String CLAUSE_ID_NOTEQUAL = "NOTEQUAL";
	
	protected static final String CLAUSE_ID_LESS = "LESS";
	protected static final String CLAUSE_ID_GREATER = "GREATER";
	protected static final String CLAUSE_ID_LESS_OR_EQUAL = "LESS]";
	protected static final String CLAUSE_ID_GREATER_OR_EQUAL = "[GREATER";
	
	protected static final String CLAUSE_ID_BETWEEN = "BETWEEN";
	protected static final String CLAUSE_ID_BETWEEN_CLOSED = "[BETWEEN]";
	protected static final String CLAUSE_ID_BETWEEN_LEFT_CLOSED = "[BETWEEN";
	protected static final String CLAUSE_ID_BETWEEN_RIGHT_CLOSED = "BETWEEN]";
	
	protected static final String TYPE_FORWARD_ONLY = "forwardOnly";
	protected static final String TYPE_SCROLL_INSENSITIVE = "scrollInsensitive";
	protected static final String TYPE_SCROLL_SENSITIVE = "scrollSensitive";
	
	protected static final String CONCUR_READ_ONLY = "readOnly";
	protected static final String CONCUR_UPDATABLE = "updatable";
	
	protected static final String HOLD_CURSORS_OVER_COMMIT = "hold";
	protected static final String CLOSE_CURSORS_AT_COMMIT = "close";

	private Connection connection;
	
	/**
	 * The statement used to fire the query.
	 */
	private PreparedStatement statement;

	private ResultSet resultSet;

	
	public JRJdbcQueryExecuter(JRDataset dataset, Map parameters)
	{
		super(dataset, parameters);
		
		connection = (Connection) getParameterValue(JRParameter.REPORT_CONNECTION);

		if (connection == null)
		{
			if (log.isWarnEnabled())
			{
				log.warn("The supplied java.sql.Connection object is null.");
			}
		}
		
		registerFunctions();
		
		parseQuery();		
	}

	
	/**
	 * Registers built-in {@link JRClauseFunction clause functions}.
	 * @see #registerFunctions()
	 * @see #appendClauseChunk(StringBuffer, String[])
	 */
	protected void registerFunctions()
	{
		registerClauseFunction(CLAUSE_ID_IN, JRSqlInClause.instance());
		registerClauseFunction(CLAUSE_ID_NOTIN, JRSqlNotInClause.instance());	
		
		registerClauseFunction(CLAUSE_ID_EQUAL, JRSqlEqualClause.instance());		
		registerClauseFunction(CLAUSE_ID_NOTEQUAL, JRSqlNotEqualClause.instance());		
		
		registerClauseFunction(CLAUSE_ID_LESS, JRSqlLessOrGreaterClause.instance());		
		registerClauseFunction(CLAUSE_ID_GREATER, JRSqlLessOrGreaterClause.instance());		
		registerClauseFunction(CLAUSE_ID_LESS_OR_EQUAL, JRSqlLessOrGreaterClause.instance());		
		registerClauseFunction(CLAUSE_ID_GREATER_OR_EQUAL, JRSqlLessOrGreaterClause.instance());
		
		registerClauseFunction(CLAUSE_ID_BETWEEN, JRSqlBetweenClause.instance());	
		registerClauseFunction(CLAUSE_ID_BETWEEN_CLOSED, JRSqlBetweenClause.instance());	
		registerClauseFunction(CLAUSE_ID_BETWEEN_LEFT_CLOSED, JRSqlBetweenClause.instance());	
		registerClauseFunction(CLAUSE_ID_BETWEEN_RIGHT_CLOSED, JRSqlBetweenClause.instance());	
	}


	protected String getParameterReplacement(String parameterName)
	{
		return "?";
	}

	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#createDatasource()
	 */
	public JRDataSource createDatasource() throws JRException
	{
		JRDataSource dataSource = null;
		
		createStatement();
		
		if (statement != null)
		{
			try
			{
				resultSet = statement.executeQuery();
				
				dataSource = new JRResultSetDataSource(resultSet);
			}
			catch (SQLException e)
			{
				throw new JRException("Error executing SQL statement for : " + dataset.getName(), e);
			}
		}
		
		return dataSource;
	}
	
	
	protected void createStatement() throws JRException
	{
		String queryString = getQueryString();
		
		if (log.isDebugEnabled())
		{
			log.debug("SQL query string: " + queryString);
		}
		
		if (connection != null && queryString != null && queryString.trim().length() > 0)
		{
			try
			{
				String type = JRProperties.getProperty(dataset,	JRJdbcQueryExecuterFactory.PROPERTY_JDBC_RESULT_SET_TYPE);
				String concurrency = JRProperties.getProperty(dataset, JRJdbcQueryExecuterFactory.PROPERTY_JDBC_CONCURRENCY);
				String holdability = JRProperties.getProperty(dataset, JRJdbcQueryExecuterFactory.PROPERTY_JDBC_HOLDABILITY);
				
				if (type == null && concurrency == null && holdability == null)
				{
					statement = connection.prepareStatement(queryString);
				}
				else
				{
					type = type == null ? TYPE_FORWARD_ONLY : type; 
					concurrency = concurrency == null ? CONCUR_READ_ONLY : concurrency; 
			
					if (holdability == null)
					{
						statement = 
							connection.prepareStatement(
								queryString, 
								getResultSetType(type), 
								getConcurrency(concurrency)
								);
					}
					else
					{
						statement = 
							connection.prepareStatement(
								queryString, 
								getResultSetType(type), 
								getConcurrency(concurrency),
								getHoldability(holdability, connection)
								);
					}
				}
				
				int fetchSize = JRProperties.getIntegerProperty(dataset,
						JRJdbcQueryExecuterFactory.PROPERTY_JDBC_FETCH_SIZE,
						0);
				if (fetchSize != 0)
				{
					statement.setFetchSize(fetchSize);
				}
				
				int maxFieldSize = JRProperties.getIntegerProperty(dataset,
						JRJdbcQueryExecuterFactory.PROPERTY_JDBC_MAX_FIELD_SIZE,
						0);//FIXMENOW check the default of all zero default properties
				if(maxFieldSize != 0)
				{
					statement.setMaxFieldSize(maxFieldSize);
				}
				
				Integer reportMaxCount = (Integer) getParameterValue(JRParameter.REPORT_MAX_COUNT);
				if (reportMaxCount != null)
				{
					statement.setMaxRows(reportMaxCount.intValue());
				}

				List parameterNames = getCollectedParameters();
				if (!parameterNames.isEmpty())
				{
					for(int i = 0, paramIdx = 1; i < parameterNames.size(); i++)
					{
						QueryParameter queryParameter = (QueryParameter) parameterNames.get(i);
						if (queryParameter.isMulti())
						{
							paramIdx += setStatementMultiParameters(paramIdx, queryParameter.getName(), queryParameter.isIgnoreNulls());
						}
						else
						{
							setStatementParameter(paramIdx, queryParameter.getName());
							++paramIdx;
						}
					}
				}
			}
			catch (SQLException e)
			{
				throw new JRException("Error preparing statement for executing the report query : " + "\n\n" + queryString + "\n\n", e);
			}
		}
	}


	protected void setStatementParameter(int parameterIndex, String parameterName) throws SQLException
	{
		JRValueParameter parameter = getValueParameter(parameterName);
		Class clazz = parameter.getValueClass();
		Object parameterValue = parameter.getValue();
		
		if (log.isDebugEnabled())
		{
			log.debug("Parameter #" + parameterIndex + " (" + parameterName + " of type " + clazz.getName() + "): " + parameterValue);
		}

		setStatementParameter(parameterIndex, clazz, parameterValue);
	}


	protected int setStatementMultiParameters(int parameterIndex, String parameterName, boolean ignoreNulls) throws SQLException
	{
		Object paramValue = getParameterValue(parameterName);
		int count;
		int index = 0;
		if (paramValue.getClass().isArray())
		{
			int arrayCount = Array.getLength(paramValue);
			for (count = 0; count < arrayCount; ++count)
			{
				Object value = Array.get(paramValue, count);
				if(!ignoreNulls || value != null)
				{
					setStatementMultiParameter(parameterIndex + index, parameterName, count, value);
					++index;
				}
			}
		}
		else if (paramValue instanceof Collection)
		{
			Collection values = (Collection) paramValue;
			count = 0;
			for (Iterator it = values.iterator(); it.hasNext(); ++count)
			{
				Object value = it.next();
				
				if(!ignoreNulls || value != null)
				{
					setStatementMultiParameter(parameterIndex + index, parameterName, count, value);
					++index;
				}
			}
		}
		else
		{
			throw new JRRuntimeException("Multi parameter value is not array nor collection.");
		}
		return index;
	}

	
	protected int setStatementMultiParameters(int parameterIndex, String parameterName) throws SQLException
	{
		return setStatementMultiParameters(parameterIndex, parameterName, false);
	}

	
	protected void setStatementMultiParameter(int parameterIndex, String parameterName, int valueIndex, Object value) throws SQLException
	{
		if (value == null)
		{
			throw new JRRuntimeException("Multi parameters cannot contain null values.");
		}
		
		Class type = value.getClass();
		
		if (log.isDebugEnabled())
		{
			log.debug("Parameter #" + parameterIndex + 
					" (" + parameterName + "[" + valueIndex + "] of type " + type.getName() + "): " + value);
		}
		
		setStatementParameter(parameterIndex, type, value);
	}

	
	protected void setStatementParameter(int parameterIndex, Class parameterType, Object parameterValue) throws SQLException
	{
		if (java.lang.Boolean.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.BIT);
			}
			else
			{
				statement.setBoolean(parameterIndex, ((Boolean)parameterValue).booleanValue());
			}
		}
		else if (java.lang.Byte.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.TINYINT);
			}
			else
			{
				statement.setByte(parameterIndex, ((Byte)parameterValue).byteValue());
			}
		}
		else if (java.lang.Double.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.DOUBLE);
			}
			else
			{
				statement.setDouble(parameterIndex, ((Double)parameterValue).doubleValue());
			}
		}
		else if (java.lang.Float.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.FLOAT);
			}
			else
			{
				statement.setFloat(parameterIndex, ((Float)parameterValue).floatValue());
			}
		}
		else if (java.lang.Integer.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.INTEGER);
			}
			else
			{
				statement.setInt(parameterIndex, ((Integer)parameterValue).intValue());
			}
		}
		else if (java.lang.Long.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.BIGINT);
			}
			else
			{
				statement.setLong(parameterIndex, ((Long)parameterValue).longValue());
			}
		}
		else if (java.lang.Short.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.SMALLINT);
			}
			else
			{
				statement.setShort(parameterIndex, ((Short)parameterValue).shortValue());
			}
		}
		else if (java.math.BigDecimal.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.DECIMAL);
			}
			else
			{
				statement.setBigDecimal(parameterIndex, (BigDecimal)parameterValue);
			}
		}
		else if (java.lang.String.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.VARCHAR);
			}
			else
			{
				statement.setString(parameterIndex, parameterValue.toString());
			}
		}
		else if (java.sql.Timestamp.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.TIMESTAMP);
			}
			else
			{
				statement.setTimestamp( parameterIndex, (java.sql.Timestamp)parameterValue );
			}
		}
		else if (java.sql.Time.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.TIME);
			}
			else
			{
				statement.setTime( parameterIndex, (java.sql.Time)parameterValue );
			}
		}
		else if (java.util.Date.class.isAssignableFrom(parameterType))
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.DATE);
			}
			else
			{
				statement.setDate( parameterIndex, new java.sql.Date( ((java.util.Date)parameterValue).getTime() ) );
			}
		}
		else
		{
			if (parameterValue == null)
			{
				statement.setNull(parameterIndex, Types.JAVA_OBJECT);
			}
			else
			{
				statement.setObject(parameterIndex, parameterValue);
			}
		}
	}

	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#close()
	 */
	public synchronized void close()
	{
		if (resultSet != null)
		{
			try
			{
				resultSet.close();
			}
			catch (SQLException e)
			{
				log.error("Error while closing result set.", e);
			}
			finally
			{
				resultSet = null;
			}
		}
		
		if (statement != null)
		{
			try
			{
				statement.close();
			}
			catch (SQLException e)
			{
				log.error("Error while closing statement.", e);
			}
			finally
			{
				statement = null;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.util.JRQueryExecuter#cancelQuery()
	 */
	public synchronized boolean cancelQuery() throws JRException
	{
		if (statement != null)
		{
			try
			{
				statement.cancel();
				return true;
			}
			catch (Exception e)
			{
				throw new JRException("Error cancelling SQL statement", e);
			}
		}
		
		return false;
	}
	
	protected static int getResultSetType(String type)
	{
		if (TYPE_FORWARD_ONLY.equals(type))
		{
			return ResultSet.TYPE_FORWARD_ONLY;
		}
		else if (TYPE_SCROLL_INSENSITIVE.equals(type))
		{
			return ResultSet.TYPE_SCROLL_INSENSITIVE;
		}
		else if (TYPE_SCROLL_SENSITIVE.equals(TYPE_SCROLL_SENSITIVE))
		{
			return ResultSet.TYPE_SCROLL_SENSITIVE;
		}
		
		return ResultSet.TYPE_FORWARD_ONLY;
	}
	
	protected static int getConcurrency(String concurrency)
	{
		if (CONCUR_READ_ONLY.equals(concurrency))
		{
			return ResultSet.CONCUR_READ_ONLY;
		}
		else if (CONCUR_UPDATABLE.equals(concurrency))
		{
			return ResultSet.CONCUR_UPDATABLE;
		}
		
		return ResultSet.CONCUR_READ_ONLY;
	}
	
	protected static int getHoldability(String holdability, Connection connection) throws SQLException
	{
		if (HOLD_CURSORS_OVER_COMMIT.equals(holdability))
		{
			return ResultSet.HOLD_CURSORS_OVER_COMMIT;
		}
		else if (CLOSE_CURSORS_AT_COMMIT.equals(holdability))
		{
			return ResultSet.CLOSE_CURSORS_AT_COMMIT;
		}
		
		return connection.getHoldability();
	}
	
}
