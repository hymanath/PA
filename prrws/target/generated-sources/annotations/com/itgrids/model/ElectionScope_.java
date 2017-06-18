package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ElectionScope.class)
public abstract class ElectionScope_ {

	public static volatile SingularAttribute<ElectionScope, Long> electionTypeId;
	public static volatile SingularAttribute<ElectionScope, Long> electionScopeId;
	public static volatile SingularAttribute<ElectionScope, Long> stateId;
	public static volatile SingularAttribute<ElectionScope, ElectionType> electionType;

}

