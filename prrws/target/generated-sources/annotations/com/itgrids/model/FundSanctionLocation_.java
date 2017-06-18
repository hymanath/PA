package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FundSanctionLocation.class)
public abstract class FundSanctionLocation_ {

	public static volatile SingularAttribute<FundSanctionLocation, FundSanction> fundSanction;
	public static volatile SingularAttribute<FundSanctionLocation, LocationScope> locationScope;
	public static volatile SingularAttribute<FundSanctionLocation, Long> locationValue;
	public static volatile SingularAttribute<FundSanctionLocation, Long> fundSanctionLocationId;
	public static volatile SingularAttribute<FundSanctionLocation, Long> locationScopeId;
	public static volatile SingularAttribute<FundSanctionLocation, Long> fundSanctionId;
	public static volatile SingularAttribute<FundSanctionLocation, String> isDeleted;
	public static volatile SingularAttribute<FundSanctionLocation, LocationAddress> locationAddress;
	public static volatile SingularAttribute<FundSanctionLocation, Long> addressId;

}

