package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.SourceLanguage;

public class CategoryDAO extends GenericDaoHibernate<Category, Long> implements ICategoryDAO {
	public CategoryDAO() {
		super(Category.class);
	}
	public List<Object[]> getCategoryDetails(){
		   return getHibernateTemplate().find("select model.categoryId,model.categoryType from Category  model order by model.categoryType ");
	   }

}
