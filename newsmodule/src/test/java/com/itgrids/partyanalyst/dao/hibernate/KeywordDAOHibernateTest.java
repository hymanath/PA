package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IKeywordDAO;

public class KeywordDAOHibernateTest extends BaseDaoTestCase{
	
	private IKeywordDAO keywordDAO;

	
public void setKeywordDAO(IKeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}


/*public void test()
{
	keywordDAO.getAll();
}*/

   /*public void testgetTotalKeyWords()
   {
	System.out.println(keywordDAO.getTotalKeyWords().size());
   }*/

/*public void testgetKeyWordsList()
{
	List<String> keywordList = new ArrayList<String>(0);
	keywordList.add("aaa");
	keywordList.add("type1");
	List<Object[]> list = keywordDAO.getKeyWordsList(keywordList);
	System.out.println(list.size());
	for(Object[] params:list)
	 System.out.println(params[0]+" "+params[1]);
	
}*/

public void testgetExistingKeywordsByKeywordsList()
{
	List<String> keywordList = new ArrayList<String>(0);
	keywordList.add("aaa");
	keywordList.add("type1");
	List<String> list = keywordDAO.getExistingKeywordsByKeywordsList(keywordList);
	System.out.println(list.size());
	for(String name:list)
	 System.out.println(name);
	
}
   
}
