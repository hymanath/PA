package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tehsil.class)
public abstract class Tehsil_ {

	public static volatile SingularAttribute<Tehsil, Long> districtId;
	public static volatile SingularAttribute<Tehsil, Long> tehsilId;
	public static volatile SingularAttribute<Tehsil, District> district;
	public static volatile SingularAttribute<Tehsil, String> tehsilName;

}

