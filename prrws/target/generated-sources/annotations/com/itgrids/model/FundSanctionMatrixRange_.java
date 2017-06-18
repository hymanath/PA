package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FundSanctionMatrixRange.class)
public abstract class FundSanctionMatrixRange_ {

	public static volatile SingularAttribute<FundSanctionMatrixRange, FinancialYear> previousYear;
	public static volatile SingularAttribute<FundSanctionMatrixRange, String> min;
	public static volatile SingularAttribute<FundSanctionMatrixRange, LocationScope> locationScope;
	public static volatile SingularAttribute<FundSanctionMatrixRange, String> orderNo;
	public static volatile SingularAttribute<FundSanctionMatrixRange, String> max;
	public static volatile SingularAttribute<FundSanctionMatrixRange, String> rangeValue;
	public static volatile SingularAttribute<FundSanctionMatrixRange, FinancialYear> presentYear;
	public static volatile SingularAttribute<FundSanctionMatrixRange, Long> presentYearId;
	public static volatile SingularAttribute<FundSanctionMatrixRange, Long> fundSanctionMatrixRangeId;
	public static volatile SingularAttribute<FundSanctionMatrixRange, Long> scopeId;
	public static volatile SingularAttribute<FundSanctionMatrixRange, Long> previousYearId;

}

