package com.itgrids.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> hashKey;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> mobileNoo;
	public static volatile SingularAttribute<User, String> isDeleted;
	public static volatile SingularAttribute<User, String> isEnabled;
	public static volatile SingularAttribute<User, String> passwordHashText;

}

