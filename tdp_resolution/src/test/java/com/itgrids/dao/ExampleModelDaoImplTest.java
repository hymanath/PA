package com.itgrids.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.itgrids.model.ExampleModel;


public class ExampleModelDaoImplTest extends EntityDaoImplTest{

	@Autowired
	ExampleDao exampleDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Example.xml"));
		return dataSet;
	}


	@Test
	public void findById(){
		Assert.assertNotNull(exampleDao.get(1));
		Assert.assertNull(exampleDao.get(3));
	}

	
	@Test
	public void saveProperty(){
		ExampleModel exampleModel = new ExampleModel();
		exampleDao.save(exampleModel );
		Assert.assertEquals(exampleDao.getAll().size(), 1);
	}
	
	

}
