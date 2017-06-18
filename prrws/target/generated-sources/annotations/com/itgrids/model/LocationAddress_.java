package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LocationAddress.class)
public abstract class LocationAddress_ {

	public static volatile SingularAttribute<LocationAddress, Tehsil> tehsil;
	public static volatile SingularAttribute<LocationAddress, Long> tehsilId;
	public static volatile SingularAttribute<LocationAddress, Long> districtId;
	public static volatile SingularAttribute<LocationAddress, Long> panchayatId;
	public static volatile SingularAttribute<LocationAddress, Long> stateId;
	public static volatile SingularAttribute<LocationAddress, Constituency> constituency;
	public static volatile SingularAttribute<LocationAddress, State> state;
	public static volatile SingularAttribute<LocationAddress, Long> locationAddressId;
	public static volatile SingularAttribute<LocationAddress, Panchayat> panchayat;
	public static volatile SingularAttribute<LocationAddress, Long> constituencyId;
	public static volatile SingularAttribute<LocationAddress, District> district;

}

