package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreLanguageEfficiencyDAO;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreLanguageEfficiencyDAOHibernateTest extends BaseDaoTestCase {
	
	ICadreLanguageEfficiencyDAO cadreLanguageEfficiencyDAO;

	public ICadreLanguageEfficiencyDAO getCadreLanguageEfficiencyDAO() {
		return cadreLanguageEfficiencyDAO;
	}

	public void setCadreLanguageEfficiencyDAO(
			ICadreLanguageEfficiencyDAO cadreLanguageEfficiencyDAO) {
		this.cadreLanguageEfficiencyDAO = cadreLanguageEfficiencyDAO;
	}
	
	public void testFindByCadreId()
	{
		List<CadreLanguageEfficiency> result=cadreLanguageEfficiencyDAO.findByCadreId(10L);
		for(CadreLanguageEfficiency cadreLanguageEfficiency: result){
			
			System.out.print("id:"+ cadreLanguageEfficiency.getCadreLanguageEfficiencyId()+"\t");
			System.out.print("Cadre Id"+cadreLanguageEfficiency.getCadre().getCadreId()+"\t");
			System.out.print("language Id:"+cadreLanguageEfficiency.getLanguage().getLanguageId()+"\t");
			System.out.print("speak"+cadreLanguageEfficiency.getIsAbleToSpeak()+"\t");
			System.out.print("read"+cadreLanguageEfficiency.getIsAbleToRead()+"\t");
			System.out.println("write"+cadreLanguageEfficiency.getIsAbleToWrite()+"\t");
			
		}
	}	
	public void testFindByCadreIdandLanguage()
	{
		List<CadreLanguageEfficiency> result=cadreLanguageEfficiencyDAO.findByCadreIdandLanguage(2l, IConstants.LANGUAGE_ENGLISH);
		System.out.println(result.size());
		for(CadreLanguageEfficiency cadreLanguageEfficiency: result){
			
			System.out.print("id:"+ cadreLanguageEfficiency.getCadreLanguageEfficiencyId()+"\t");
			System.out.print("Cadre Id"+cadreLanguageEfficiency.getCadre().getCadreId()+"\t");
			System.out.print("language Id:"+cadreLanguageEfficiency.getLanguage().getLanguageId()+"\t");
			System.out.print("speak"+cadreLanguageEfficiency.getIsAbleToSpeak()+"\t");
			System.out.print("read"+cadreLanguageEfficiency.getIsAbleToRead()+"\t");
			System.out.println("write"+cadreLanguageEfficiency.getIsAbleToWrite()+"\t");
			
		}
	}
	public void testDeleteSkillsByCadreId()
	{
		int result=cadreLanguageEfficiencyDAO.deleteLanguageDetailsByCadre(5l, 1l);
		System.out.println("No of records deleted:"+result);
		setComplete();
		
	}
	
}
