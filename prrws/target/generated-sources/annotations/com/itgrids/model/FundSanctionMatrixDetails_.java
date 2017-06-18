package com.itgrids.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FundSanctionMatrixDetails.class)
public abstract class FundSanctionMatrixDetails_ {

	public static volatile SingularAttribute<FundSanctionMatrixDetails, FundSanctionMatrixRange> fundSanctionMatrixRange;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, Long> presentYearId;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, Date> insertedTime;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, Long> fundSanctionMatrixRangeId;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, String> scopeValue;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, Long> fundSanctionMatrixDetailsId;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, String> isDeleted;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, Long> scopeId;
	public static volatile SingularAttribute<FundSanctionMatrixDetails, Long> previousYearId;

}

