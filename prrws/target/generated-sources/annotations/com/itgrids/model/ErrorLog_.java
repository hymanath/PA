package com.itgrids.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ErrorLog.class)
public abstract class ErrorLog_ {

	public static volatile SingularAttribute<ErrorLog, String> response;
	public static volatile SingularAttribute<ErrorLog, Integer> statusCode;
	public static volatile SingularAttribute<ErrorLog, Long> errorLogId;
	public static volatile SingularAttribute<ErrorLog, String> exceptionStack;
	public static volatile SingularAttribute<ErrorLog, String> exceptionMsg;
	public static volatile SingularAttribute<ErrorLog, String> inputUrl;
	public static volatile SingularAttribute<ErrorLog, Date> insertedTime;
	public static volatile SingularAttribute<ErrorLog, String> inputJson;

}

