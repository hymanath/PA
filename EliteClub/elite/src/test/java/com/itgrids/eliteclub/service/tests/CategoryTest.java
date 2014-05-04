package com.itgrids.eliteclub.service.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dao.FileDAO;
import com.itgrids.eliteclub.dao.impl.FileDAOImpl;


@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/app-config.xml"
@ContextConfiguration("/applicationContext.xml")
@ActiveProfiles("dev")

public class CategoryTest {
	Logger log=LogManager.getLogger();
	
	@Autowired
	private FileDAO  fileDAO;
	
	
	@Test
	public void testLoadFilesByCategory()
	{
		//List<?> objects=fileDAO.loadFilesByCategory();
		
		Set<Integer> fileIds= new HashSet<Integer>();
		fileIds.add(1);
		fileIds.add(2);
		fileIds.add(3);
		List<?> objects=fileDAO.getVoiceIdsForFileIds(fileIds);
		System.out.println(objects.toString().replace("[", "").replace("]", "")
	            .replace(", ", ","));
		
	}
	
	
	

}
