package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.NewsActivityVO;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;
 

 
/**
* You'll need these jar's below:
*
* jasperreports-5.0.1.jar
* iText-2.1.7.jar (needed to generate PDF)
* jfreechart-1.0.12.jar (needed to graphics and charts)
* jcommon-1.0.15.jar (needed to graphics and charts)
* commons-beanutils-1.8.2.jar
* commons-collections-3.2.1.jar
* commons-digester-2.1.jar
* commons-logging-1.1.jar
*/
public class GenerateSimplePdfReportWithJasperReports1 {
 
public static void main(String[] args) {

try {
/*String reportName = "C:\\Users\\Admin\\Desktop\\ireport\\report6";
Map<String, Object> parameters = new HashMap<String, Object>();
List<Student> activities = new ArrayList<Student>();
Student category = new Student();
activities.add(category);
  category.setCategory("Activities");
  List<Student> distList = new ArrayList<Student>();
  category.setDistrictList(distList);
  List<Student> constituencyList = new ArrayList<Student>();
  Student d1 = new Student();
  d1.setDistrict("d1");
  d1.setConstituencyList(constituencyList);
  Student d2 = new Student();
  d2.setConstituencyList(constituencyList);
  d2.setDistrict("d2");
  distList.add(d1);
  distList.add(d2);

Student c1 = new Student();
c1.setConstituency("c1");
Student c2 = new Student();
c2.setConstituency("c2");
List<Student> dataList1 = new ArrayList<Student>();
List<Student> dataList2 = new ArrayList<Student>();
c1.setDataList(dataList1);
c2.setDataList(dataList2);
Student da1 = new Student();
da1.setTitle("t1");
da1.setDescription("d1");
Student da2 = new Student();
da2.setTitle("t2");
da2.setDescription("d2");
dataList1.add(da1);
dataList1.add(da2);

Student da3 = new Student();
da3.setTitle("t3");
da3.setDescription("d3");
Student da4 = new Student();
da4.setTitle("t4");
da4.setDescription("d4");
dataList2.add(da3);
dataList2.add(da4);
constituencyList.add(c1);
constituencyList.add(c2);



// compiles jrxml
JasperCompileManager.compileReportToFile(reportName+".jrxml");

JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(activities);
// fills compiled report with parameters and a connection
JasperPrint print = JasperFillManager.fillReport(reportName+".jasper", parameters, dataSource);
//JasperPrint print = JasperFillManager.fillReport(reportName+".jasper", parameters, connection);
// exports report to pdf
JRExporter exporter = new JRPdfExporter();
exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName + ".pdf")); // your output goes here
exporter.exportReport();*/
	FileInputStream fileIn = new FileInputStream("D:/tmp/employee.ser");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    NewsActivityVO vo = (NewsActivityVO) in.readObject();
    List<NewsActivityVO> mainList = new ArrayList<NewsActivityVO>();
    List<NewsActivityVO> categList = vo.getList();
    for(NewsActivityVO categ:categList){
    	List<NewsActivityVO> distList = categ.getList();
    	for(NewsActivityVO dist:distList){
    		List<NewsActivityVO> constiList = dist.getList();
        	for(NewsActivityVO consti:constiList){
        		//mainList.addAll(consti.getList());
        		for(NewsActivityVO all:consti.getList()){
        			all.setId(null);
        			
        			all.setDescription(all.getDescription());
        			NewsActivityVO  vo1 = new NewsActivityVO();
        			vo1.setFont(all.getTitleFont());
        			
        			    vo1.setDescription((all.getTitle()+"<style pdfFontName=\"Calibriabc\" size=\"12\">("+all.getPaper()+")</style>"));
        			
        			vo1.setId(1l);
        			mainList.add(vo1);
        			mainList.add(all);
        			//System.out.println(all.getPaper());
        		}
        	}
    	}
    }
    
		String reportName = "C:\\Users\\Admin\\Desktop\\ireport\\subreport\\report2";
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		// compiles jrxml
		JasperCompileManager.compileReportToFile(reportName+".jrxml");
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(mainList);
		// fills compiled report with parameters and a connection
		JasperPrint print = JasperFillManager.fillReport(reportName+".jasper", parameters, dataSource);
		//JasperPrint print = JasperFillManager.fillReport(reportName+".jasper", parameters, connection);
		// exports report to pdf
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName + ".pdf")); // your output goes here
		exporter.exportReport();
		System.out.println("Completed");
} catch (Exception e) {
throw new RuntimeException("It's not possible to generate the pdf report.", e);
} finally {
// it's your responsibility to close the connection, don't forget it!

}
System.exit(0);
}
}
