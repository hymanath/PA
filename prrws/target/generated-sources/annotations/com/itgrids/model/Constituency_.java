package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Constituency.class)
public abstract class Constituency_ {

	public static volatile SingularAttribute<Constituency, Long> districtId;
	public static volatile SingularAttribute<Constituency, Long> electionScopeId;
	public static volatile SingularAttribute<Constituency, ElectionScope> electionScope;
	public static volatile SingularAttribute<Constituency, String> areaType;
	public static volatile SingularAttribute<Constituency, Long> stateId;
	public static volatile SingularAttribute<Constituency, String> name;
	public static volatile SingularAttribute<Constituency, Long> constituencyId;
	public static volatile SingularAttribute<Constituency, District> district;
	public static volatile SingularAttribute<Constituency, String> nameLocal;

}

