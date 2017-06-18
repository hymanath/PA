package com.itgrids.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FundSanction.class)
public abstract class FundSanction_ {

	public static volatile SingularAttribute<FundSanction, FinancialYear> financialYear;
	public static volatile SingularAttribute<FundSanction, Long> financialYearId;
	public static volatile SingularAttribute<FundSanction, Department> department;
	public static volatile SingularAttribute<FundSanction, GrantType> grantType;
	public static volatile SingularAttribute<FundSanction, Date> insertedTime;
	public static volatile SingularAttribute<FundSanction, String> isDeleted;
	public static volatile SingularAttribute<FundSanction, Long> updatedBy;
	public static volatile SingularAttribute<FundSanction, Long> insertedBy;
	public static volatile SingularAttribute<FundSanction, String> goNoDate;
	public static volatile SingularAttribute<FundSanction, Long> grantTypeId;
	public static volatile SingularAttribute<FundSanction, Long> govtSchemeId;
	public static volatile SingularAttribute<FundSanction, Long> fundSactionId;
	public static volatile SingularAttribute<FundSanction, String> workName;
	public static volatile SingularAttribute<FundSanction, GovtScheme> govtScheme;
	public static volatile SingularAttribute<FundSanction, Long> departmentId;
	public static volatile SingularAttribute<FundSanction, Long> sactionAmount;
	public static volatile SingularAttribute<FundSanction, Date> updatedTime;

}

