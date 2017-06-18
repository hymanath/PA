package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PanchayatHamlet.class)
public abstract class PanchayatHamlet_ {

	public static volatile SingularAttribute<PanchayatHamlet, Long> panchayatId;
	public static volatile SingularAttribute<PanchayatHamlet, Long> panchayatHamletId;
	public static volatile SingularAttribute<PanchayatHamlet, Long> hamletId;
	public static volatile SingularAttribute<PanchayatHamlet, Panchayat> panchayat;
	public static volatile SingularAttribute<PanchayatHamlet, Hamlet> hamlet;

}

