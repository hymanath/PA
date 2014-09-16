package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class GenerateSimplePdfReportWithJasperReports {
 
public static void main(String[] args) {
	Connection connection = null;
	Statement stmt = null;
try {
	System.out.println("పేదరికంపై గెలుపే ప్రభుత్వ లక్ష్యమని బాబు స్పష్టం చేశారు".length());
	if(true){
		return;
	}
String reportName = "C:\\Users\\Admin\\Desktop\\ireport\\report3";
Map<String, Object> parameters = new HashMap<String, Object>();
List<Student> results = new ArrayList<Student>();
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_portal?autoReconnect=true","root","root");

stmt = connection.createStatement();
String sql;
sql = "SELECT file_title,file_description FROM file where file_id in (133755,133754 )";
//sql = "SELECT file_title,file_description FROM file order by file_id desc limit 1000 ";
ResultSet rs = stmt.executeQuery(sql);
while(rs.next()){
  
	Student s = new Student();
   //s.setFile_id(org.apache.commons.lang3.StringEscapeUtils.unescapeJava(rs.getString("file_title")));
   //s.setFile_title( org.apache.commons.lang3.StringEscapeUtils.unescapeJava(rs.getString("file_description")));
   s.setFile_title("¤¶òªýsq ®¾%•-¯Ã-ÅŒtÂ¹ ®¾¢®¾n© èÇG-ÅÃ©ð 5 ¦µÇª½-B§ŒÕ ®¾¢®¾n©Õ ÊÖu§ŒÖªýˆ: ¤¶òªýsq ¦ÕŸµ¿-„Ãª½¢ Nœ¿Õ-Ÿ¿© Íä®ÏÊ “X¾X¾¢-ÍŒ¢-©ðE «¢Ÿ¿ ®¾%•-¯Ã-ÅŒtÂ¹ ®¾¢®¾n© èÇG-ÅÃ©ð ‰Ÿ¿Õ ¦µÇª½-B§ŒÕ Â¹¢åX-F©Õ …¯Ãoªá. £ÏÇ¢Ÿ¿Õ-²Än¯þ §ŒâF-M-«ªý(14« ²ÄnÊ¢), šÇšÇ-Â¹-Êq-©ãdFq ®¾Ky-å®®ý(57 « ²ÄnÊ¢), ©Çéªq¯þ Æ¢œþ {Ö“¦ð(58), ®¾¯þ-¤¶ÄªÃt(65), ¦èÇèü ‚šð(96) ¨ èÇG-ÅÃ©ð ²ÄnÊ¢ ®¾¢¤Ä-C¢-ÍÃªá. ÂÃL-¤¶ò-Jo-§ŒÖÂ¹× Íç¢CÊ Âõxœþ Â¹¢X¾Üu-šË¢’û Â¹¢åXF æ®©üq-¤¶òªýq «ª½-®¾’Ã ¯Ã©Õ’î \\œ¿Ö ¨ èÇG-ÅÃ©ð ÅíL ²ÄnÊ¢©ð E©-«œ¿¢ N¬ì†¾¢.");
	//s.setFile_title("");
	//s.setFile_id("పాట్నా: భారతీయ జనతా పార్టీకి చెక్ పెట్టేందుకు సమాజ్ వాదీ అధినేత ములాయం సింగ్ యాదవ్, బీఎస్పీ అధినేత్రి మయావతిల మధ్య మైత్రిని కుదిర్చేందుకు ప్రయత్నిస్తానని ఆర్జేడీ చీఫ్ లాలూ ప్రసాద్ యాదవ్ అన్నారు. బీజేపీ కమండల రాజకీయాలకు ధీటుగా మండల రాజకీయాలను తెరపైకి తెస్తామన్నారు.");

  
   s.setFile_id(("హైదరాబాద్,ఆగష్టు 21 : ప్రత్యేక పరిస్థితుల్లో ఆంధ్రప్రదేశ్‌లో అధికారంలోకి వచ్చామని ఏపీ ముఖ్యమంత్రి చంద్రబాబునాయుడు అన్నారు. గురువారం ఎన్టీఆర్ ట్రస్ట్ భవన్‌లో నిర్వహించిన ఏడు మిషన్లపై వర్క్‌షాప్‌లో చంద్రబాబు మాట్లాడుతూ భవిష్యత్‌లో అభివృద్ధిలో ముందుకెళ్లడానికే యాక్షన్‌ప్లాన్ అని, అందులో భాగంగానే ఏడు మిషన్లపై వర్క్‌షాప్ నిర్వహిస్తున్నట్లు ఆయన తెలిపారు. పదేళ్లుగా అవినీతిపై రాజీలేని పోరాటం చేశామన్నారు. విభజన సమయంలోనూ అనేక అంశాలను ప్రజల దృష్టికి తీసుకెళ్లామని తెలిపారు. "+

" పార్టీ -ప్రభుత్వం మధ్య సమన్వయం అవసరమన్న బాబు పార్టీ కార్యక్రమాలపై ఎప్పటికప్పుడు సమీక్షలు జర గాలని సూచించారు. పార్టీని గ్రామస్థాయి నుంచి సమన్వయం చేయాలని, నాయకుల్లో నైపుణ్యం పెరగాలని ఆయన ఆకాంక్షించారు. ప్రతి అంశంపైన స్పష్టమైన అవగాహన కలిగి ఉండాలని నాయకులకు సూచించారు. బీసీ,ఎస్సీ, ఎస్టీలకు ఇచ్చిన హామీలన్నీ నెరవేర్చుతామని మరోసారి స్పష్టం చేశారు. పదేళ్ల తర్వాత గ్రామస్థాయి నుంచి దేశస్థాయి వరకు కొత్త పాలన వచ్చిందని, ప్రతి ఒక్కరు అభివృద్ధిని సీరియస్‌గా తీసుకోవాలన్నారు. "+

" టీడీపీ అధికారంలోకి వచ్చిన తర్వాతే అనేక సంస్కరణలు తీసుకువచ్చిందని తెలిపారు. విద్యుత్ ఉత్పత్తిలోనూ అనేక సంస్కరణలు తెచ్చామని ఆయన వెల్లడించారు. గత పదేళ్లలో విచ్చలవిడిగా అవినీతి పెరిగిపోయిందని, పదేళ్లుగా అన్ని వ్యవస్థలను నిర్వీర్యం చేశారని ఆరోపించారు. ప్రధానిపై సీబీఐ విచారణకు కోర్టు ఆదేశించిందని, మంత్రులు, ఐఏఎస్, పారిశ్రామికవత్తేలు జైలుకెళ్లారన్నారు. గత పదేళ్లు...దగాపడ్డ దశాబ్దమని చంద్రబాబు అభివర్ణించారు. "+

" రాష్ట్ర విభజనతో అనేక సమస్యలొచ్చాయన్నారు. కాంగ్రెస్ తప్పు చేసినందుకే ఏపీలో ఒక్కసీటు గెలవలేద ని ఎద్దేవా చేశారు. సుస్థిర ప్రభుత్వాన్ని కోరుతూ ప్రజలు తీర్పునిచ్చారన్నారు. దేశంలో ఒక పార్టీకి పూర్తి మెజార్టీ రావడం ఇదే తొలిసారని చంద్రబాబు పేర్కొన్నారు. ఏపీ రాజధాని ఎక్కడనేది ఇంకా నిర్ణయం కాలేదని ఆయన తెలిపారు. ఇరురాష్ట్రాల్లో సమస్యల పరిష్కారానికి తెలంగాణ సీఎం కేసీఆర్ ముందుకొచ్చారని బాబు పేర్కొన్నారు. కేడర్ విభజన ఇంకా పూర్తికాలేదని, 89 వర్సిటీలు, సంస్థలు ఇంకా ఉమ్మడిగానే ఉన్నాయన్నారు. పరస్పర అంగీకారంతోనే విభజన జరగాలని బాబు ఆకాంక్షించారు. "+

" సమస్య ఏదైనా సమన్వయంతో ముందుకెళ్లాలని తెలిపారు. ఆంధ్ర ప్రదేశ్ ఆర్థికంగా కోలుకునేందుకు కేంద్రం ఉదారంగా సాయం చేయాలని కోరారు. విభజన చట్టంలో ప్రస్తావించిన సంస్థలను ఏర్పాటు చేయాల్సిన అవసరం ఉందన్నారు. ఏపీ పరిస్థితి వైకుంఠాపాళిలా ఉందన్న బాబు పాము కరిస్తే...మళ్లీ కిందకే వస్తామని వెల్లడించారు. ఏపీలో నీరు, విలువైన ఖనిజ సంపద ఉందని పేర్కొన్నారు. కృష్ణా-గోదావరి నదులను అనుసందానం చేస్తే 1000 టీఎంసీల నీటిని వాడుకోవచ్చని ఆయన చెప్పారు. విశాఖ, తూగో, పగో, కృష్ణా, గుంటూరు, ప్రకాశం జిల్లాలకు గ్రావిటీ పద్దతిలో నీరివొచ్చని ముఖ్యమంత్రి తెలిపారు. "+

" ఏపీలో అక్షరాస్యత పెంచాల్సిన అవసరం ఉందన్నారు. పేదరికంపై గెలుపే ప్రభుత్వ లక్ష్యమని బాబు స్పష్టం చేశారు. భవిష్యత్‌లో సర్వీసు రంగమే కీలకమని, సర్వీసు రంగంలోనే ఎక్కువ ఉద్యోగావకాశాలు ఉంటాయన్నారు. మైపాడు నుంచి శ్రీకాకుళం వరకు ఎన్నో బీచులున్నాయని, బీచ్‌లు, చారిత్రక కట్టడాలను అనుసందానం చేస్తూ టూరిజాన్ని అభివృద్ధి చేస్తామని బాబు తెలిపారు. టెంపుల్ టూరిజం అభివృద్ధికి కృషి చేస్తామన్నారు. ఏ పనిలోనైనా నైపుణ్యతే ముఖ్యమని బాబు అన్నారు. వాటర్‌గ్రిడ్ ద్వారా సేద్యం, పరిశ్రమలకు నీటి కొరత లేకుండా చూస్తామని హామీ ఇచ్చారు. రోడ్ గ్రిడ్‌తో రవాణాలు మెరుగుపరుస్తామని, ప్రతి గ్రామానికి బీటీ రోడ్డు నిర్మాణం చేపడుతామని చంద్రబాబు తెలియజేశారు. "));

   //s.setFile_id("పా ట్నా :  భా ర తీ య  జ న తా  పా ర్టీ కి  చె క్  పె ట్టేం దు కు  స మా జ్ వా దీ  అ ధి నే త  ము లా యం  సిం గ్  యా ద వ్ ,  బీ ఎ స్పీ  అ ధి నే త్రి  మ యా వ తి ల  మ ధ్య  మై త్రి ని  కు ది ర్చేం దు కు  ప్ర య త్ని స్తా న ని  ఆ ర్జే డీ  చీ ఫ్  లా లూ  ప్ర సా ద్  యా ద వ్  అ న్నా రు.");
   results.add(s);
}

// compiles jrxml
JasperCompileManager.compileReportToFile(reportName+".jrxml");

JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(results);
// fills compiled report with parameters and a connection
JasperPrint print = JasperFillManager.fillReport(reportName+".jasper", parameters, dataSource);
//JasperPrint print = JasperFillManager.fillReport(reportName+".jasper", parameters, connection);
// exports report to pdf
JRExporter exporter = new JRPdfExporter();
exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName + ".pdf")); // your output goes here
exporter.exportReport();
rs.close();
stmt.close();
connection.close();
} catch (Exception e) {
throw new RuntimeException("It's not possible to generate the pdf report.", e);
} finally {
// it's your responsibility to close the connection, don't forget it!

}
System.exit(0);
}
}
