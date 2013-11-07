package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.hibernate.mapping.Array;

import com.itgrids.partyanalyst.dao.IFileKeywordDAO;

public class FileKeywordDAOHibernateTest extends BaseDaoTestCase{
	
	
	
	private IFileKeywordDAO fileKeywordDAO;

	public void setFileKeywordDAO(IFileKeywordDAO fileKeywordDAO) {
		this.fileKeywordDAO = fileKeywordDAO;
	}

     public void test()
     {
    	 List<Long> keyWords =new ArrayList<Long>();
    	 keyWords.add(1l);
    	 keyWords.add(2l);
	    List<Long> fileIds = fileKeywordDAO.getFilesForEachKeyWord(keyWords);
	    System.out.println(fileIds);
     }
     
     
	}