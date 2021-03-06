package com.itgrids.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PmRequestEditVO;
import com.itgrids.dto.PmRequestVO;

public class ITextCoveringLetterGeneration  {

	public static final String GHOST_SCRIPT_PATH = "C:\\Program Files\\gs\\gs9.21\\bin\\gswin64c.exe";
	public static final String CSS = "table { border:1px solid red; }";
	public static final int	   CUTOFF_LEVEL1 = 95;
	public static CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	/*public static void convertToImage(String indiDEST){
		String output = indiDEST.replace(".pdf", ".jpg");
		ProcessBuilder processBuilder = new ProcessBuilder(GHOST_SCRIPT_PATH,
			"-dSAFER", "-dBATCH", "-dNOPAUSE", "-sDEVICE=jpeg",
			"-r210","-dJPEGQ=80","-sOutputFile="+output+"",
			""+indiDEST+"");
			try {
				processBuilder.start();
			} catch (IOException e) {
				System.out.println("Exception Raised In PDF convertToImage for Paper - "+e);
			}
	}*/
	
	/*public static void main(String a[]){
	InputVO inputVO = new InputVO();
	inputVO.setLeadName("Forwarded for Taking necessary action as per rules");
	inputVO.setGroupName("NREGS Convergence");
	inputVO.setEndValue("8");
	inputVO.setDisplayType("OSD");
	inputVO.setDeptCode("(PR,RD,RWS,ITE&C)");
	List<Object[]> coveringLetrImages = null;
	String endorseCode = "Entd No. 234567/OSD(PR,RD,RWS,ITE&C)/2018, Dt:18.05.2018";
	//String endorseCode = "Entd No. 234567/Min(PR,RD,RWS,ITE&C)/2018, Dt:18.05.2018";
	PmRequestEditVO petitionDetailsVO = new PmRequestEditVO();
	petitionDetailsVO.setRepresentationdate("18-05-2018");
	List<PmRequestVO> representeeList = new ArrayList<PmRequestVO>();
	PmRequestVO pmRequestVO2 = new PmRequestVO();
	pmRequestVO2.setName("Bandla Ashok");
	pmRequestVO2.setDesignation("MLA");
	AddressVO addr1 = new AddressVO();
	addr1.setAssemblyName("Proddatur");
	addr1.setDistrictName("Kadapa");
	pmRequestVO2.setCandidateAddressVO(addr1);
	representeeList.add(pmRequestVO2);
	PmRequestVO pmRequestVO3 = new PmRequestVO();
	pmRequestVO3.setName("Bandla Ashok");
	pmRequestVO3.setDesignation("MLA");
	AddressVO addr2 = new AddressVO();
	addr2.setAssemblyName("Proddatur");
	addr2.setDistrictName("Kadapa");
	pmRequestVO3.setCandidateAddressVO(addr2);
	representeeList.add(pmRequestVO3);
	List<Long> workIds = new ArrayList<Long>();
	workIds.add(105061l);
	workIds.add(105060l);
	inputVO.setSchemeIdsList(workIds);
	List<PmRequestVO> representeeList1 = new ArrayList<PmRequestVO>();
	PmRequestVO pmRequestVO1 = new PmRequestVO();
	pmRequestVO1.setName("Bandla Ashok");
	pmRequestVO1.setDesignation("MLA");
	
	
	AddressVO addr = new AddressVO();
	addr.setAssemblyName("Proddatur");
	addr.setDistrictName("Kadapa");
	pmRequestVO1.setAddressVO(addr);
	representeeList1.add(pmRequestVO1);
	petitionDetailsVO.setRepresenteeDetailsList(representeeList1);
	//representeeList.add(pmRequestVO3);
	petitionDetailsVO.getReferDetailsList().addAll(representeeList);
	petitionDetailsVO.setWorkName("requesting for sanction ...ost of Rs.150.00 Lakhs.");
	String str1 = " #min I am here with forwarding the representation  #rdate of #rname#rdesig#rconst#rdist#Addr requesting for sanction of #works #grant #cost ";
	//String staticPath =IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER+"/2018/1/24" ;//commonMethodsUtilService.createInnerFolders(IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER);
	//if(staticPath != null && staticPath.equalsIgnoreCase("FAILED"))
		//throw new Exception("File path not available . Please check once file path.");
//String datePath = commonMethodsUtilService.generateImagePathWithDateTime();
//String fileName = datePath+"_"+inputVO.getEndValue()+".PDF";
	String staticPath =IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER+"/2018/2/8/";
	String fileName = inputVO.getEndValue()+".PDF";
	generateCOVERINGLETTER(inputVO,coveringLetrImages,endorseCode,petitionDetailsVO,str1,staticPath,fileName);
}*/
	public static String generateCOVERINGLETTER(InputVO inputVO,List<Object[]> coveringLetrImages,String endorseCode,PmRequestEditVO petitionDetailsVO,String str1,String staticPath,String fileName){
		String fileUrl = "";
		try {
			//Font customFont = Font.createFont(Font.DEFAULTSIZE, new File("Fonts\\Albertus MT Light.ttf")).deriveFont(12f);
			//Font customFont = Font.createFont(Font.DEFAULTSIZE, new File("Fonts\\Albertus MT Light.ttf")).deriveFont(12f);
			String sysPath = "D:/Tomcat 7.0/webapps/PRRWS-1.0/";
			String logo ="";
			//D:/Tomcat 7.0/webapps/PRRWS-1.0/Assests/images/petition images/Group 1.png
			String deptDetailsImg ="";
			String addrDetailsImg ="";
			//"sign2.png",to_address.png,header1.png
			//"sign.png",to_address.png,header.png
			
			/*String sign ="sign2.png";
			String toAddrImg ="to_address.png";
			String headerImg ="emptyheader.png";*/
			String sign ="";
			String toAddrImg ="";
			String headerImg ="";
			
			//List<PmRequestVO> representeeList = new ArrayList<PmRequestVO>();
			StringBuilder referrals = null;
			if(inputVO.getDisplayType() != null && (inputVO.getDisplayType().equalsIgnoreCase("AP MINISTER") || inputVO.getDisplayType().equalsIgnoreCase("MINISTER"))){
				str1=str1.replace("#min", "");
			}else{
				str1=str1.replace("#min", "As directed by Hon'ble Minister, ");
			}
			str1 = str1.trim();
			String depts =inputVO.getDeptCode();
			depts = inputVO.getDeptCode().replace("(", "");
			if(inputVO.getDisplayType() != null && (inputVO.getDisplayType().equalsIgnoreCase("AP MINISTER") || inputVO.getDisplayType().equalsIgnoreCase("MINISTER"))){
				str1=str1.replace("#Addr", "");
			}else{
				str1=str1.replace("#Addr", " addressed to Hon'ble Minister for "+depts.replace(")", "")+" ");
			}
			str1 = str1.trim();
 			if(petitionDetailsVO != null){
 				
 				if(petitionDetailsVO.getGrievanceDescription() != null && !petitionDetailsVO.getGrievanceDescription().equalsIgnoreCase("")){
 					String lastStr = petitionDetailsVO.getGrievanceDescription().substring(petitionDetailsVO.getGrievanceDescription().trim().length()-1, petitionDetailsVO.getGrievanceDescription().trim().length());
 					if(lastStr != null && lastStr.equalsIgnoreCase(".")){
 						petitionDetailsVO.setGrievanceDescription(petitionDetailsVO.getGrievanceDescription().substring(0, petitionDetailsVO.getGrievanceDescription().trim().length()-1));
 					}else{
 						petitionDetailsVO.setGrievanceDescription(petitionDetailsVO.getGrievanceDescription().substring(0, petitionDetailsVO.getGrievanceDescription().trim().length()));
 					}
 					
					 str1 = str1.replace("#workName",petitionDetailsVO.getGrievanceDescription());
				 }else{
					 str1 = str1.replace("#workName","");
				 }
 				str1 = str1.trim();
 				if(petitionDetailsVO.getRepresentationdate() != null && !petitionDetailsVO.getRepresentationdate().equalsIgnoreCase("")){
 					 String[] strArr = petitionDetailsVO.getRepresentationdate().split("-");
 					 String dateFormat = strArr[0]+"."+strArr[1]+"."+strArr[2];
					 str1 = str1.replace("#rdate"," Dated: <b>"+dateFormat+"</b>");
				 }else{
					 str1 = str1.replace("#rdate","");
				 }
 				str1 = str1.trim();
				if(petitionDetailsVO.getRepresenteeDetailsList() != null && petitionDetailsVO.getRepresenteeDetailsList().size()>0){
					//representeeList.addAll(petitionDetailsVO.getRepresenteeDetailsList());
					for (PmRequestVO pmRequestVO : petitionDetailsVO.getRepresenteeDetailsList()) {
						 if(pmRequestVO.getName() != null && !pmRequestVO.getName().equalsIgnoreCase("")){
							 str1 = str1.replace("#rname",""+pmRequestVO.getName());
						 }else{
							 str1 = str1.replace("#rname","");
						 }
						 if(pmRequestVO.getDesignation() != null && !pmRequestVO.getDesignation().equalsIgnoreCase("")){
							 if(pmRequestVO.getDesignation().toUpperCase().contains("MINISTER")){
								 //pmRequestVO.setDesignation(pmRequestVO.getDesignation().replace("State Minister", " Hon'ble Minister "));
								 pmRequestVO.setDesignation(pmRequestVO.getDesignation().replace("State Minister", " "));
								 str1 = str1.replace("#rdesig",","+pmRequestVO.getDesignation()+" ");
							 }else if(pmRequestVO.getDesignation().toUpperCase().contains("WHIP")){
								 //pmRequestVO.setDesignation(pmRequestVO.getDesignation().replace("State Minister", " Hon'ble Minister "));
								// pmRequestVO.setDesignation("Govt.Whip");
								 pmRequestVO.setDesignation(pmRequestVO.getDesignation().replace(", MLA Govt. Whip", ""));
								 pmRequestVO.setDesignation(pmRequestVO.getDesignation().replace("MLA", ""));
								 pmRequestVO.setDesignation(pmRequestVO.getDesignation().replace("Govt. Whip", ""));
								 str1 = str1.replace("#rdesig",","+pmRequestVO.getDesignation()+" ");
							 }else{
								 str1 = str1.replace("#rdesig",", "+pmRequestVO.getDesignation()+" ");
							 }
						 }else{
							 str1 = str1.replace("#rdesig","");
						 }
						 if(!pmRequestVO.getDesignation().toUpperCase().contains("MINISTER") && !pmRequestVO.getDesignation().toUpperCase().contains("WHIP")){
							 
							 if(!pmRequestVO.getDesignation().toUpperCase().contains("MLC")){
								 if(pmRequestVO.getAddressVO().getAssemblyName() != null && !pmRequestVO.getAddressVO().getAssemblyName().equalsIgnoreCase("")){
									 str1 = str1.replace("#rconst"," "+pmRequestVO.getAddressVO().getAssemblyName()+" Constituency, ");
								 }else{
									 str1 = str1.replace("#rconst","");
								 }
							 }else{
								 str1 = str1.replace("#rconst","");
							 }
							 if(pmRequestVO.getAddressVO().getDistrictName() != null && !pmRequestVO.getAddressVO().getDistrictName().equalsIgnoreCase("")){
								 str1 = str1.replace("#rdist",""+pmRequestVO.getAddressVO().getDistrictName()+" District ");
							 }else{
								 str1 = str1.replace("#rdist","");
							 }
						 }else{
							 str1 = str1.replace("#rconst","");
							 str1 = str1.replace("#rdist","");
						 }
						 
					}
				}
 			}
 			//str1 = str1.trim();
 			 String estCost ="0.00";
			/*List<PetitionsWorksVO> worksList = new ArrayList<PetitionsWorksVO>();
			StringBuilder works = new StringBuilder();
			 if(petitionDetailsVO.getSubWorksList() != null && petitionDetailsVO.getSubWorksList().size()>0){
				 for (PetitionsWorksVO pmSubwork : petitionDetailsVO.getSubWorksList()) {
					 for (PetitionsWorksVO pmSubwork1 : pmSubwork.getSubWorksList()) {
						 if(inputVO.getSchemeIdsList().contains(pmSubwork1.getWorkId())){
							 int index =pmSubwork.getSubWorksList().indexOf(pmSubwork1);
								int no = 1;
								int index1=index+no;
								String villageName ="";
								String tehsil ="";
								String consti ="";
								String dist ="";
								
								String grant ="";
								if(!pmSubwork1.getEstimateCost().isEmpty()){
									//estCost= "&nbsp;&nbsp;With an Estimated Cost of Rs.<b>"+pmSubwork1.getEstimateCost()+"</b>&nbsp;Lakhs ";
									BigDecimal decmial= new BigDecimal(pmSubwork1.getEstimateCost());
									BigDecimal decmial1= new BigDecimal(estCost);
									BigDecimal totalCost = decmial.add(decmial1);
									estCost = totalCost.toString();
								}
						 }
					}
				}
			 }*/
			 if(!petitionDetailsVO.getEstimateCost().isEmpty()){
					BigDecimal decmial= new BigDecimal(petitionDetailsVO.getEstimateCost());
					BigDecimal decmial1= new BigDecimal(estCost);
					BigDecimal totalCost = decmial.add(decmial1);
					estCost = totalCost.toString();
					estCost = commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(Double.valueOf(estCost));
				}
			 
			 
			 str1 = str1.trim();
			 String grant ="";
			 if(inputVO.getGroupName() != null && !inputVO.getGroupName().equalsIgnoreCase("0")){
					//grant = " under "+ inputVO.getGroupName();
					str1 = str1.replace("#grant",". under "+ inputVO.getGroupName());
				}else{
					str1 = str1.replace("#grant",".");
				}
			 str1 = str1.trim();
			 //if(petitionDetailsVO.getEstimateCost() != null && ( !petitionDetailsVO.getEstimateCost().equalsIgnoreCase("0") && !petitionDetailsVO.getEstimateCost().trim().isEmpty()) ){
			 if(!estCost.trim().equalsIgnoreCase("0.0") && !estCost.trim().equalsIgnoreCase("0.00")){
				 str1 = str1.replace("#cost"," With an Estimated Cost of Rs.&nbsp;<b>"+estCost+"</b>&nbsp;Lakhs. ");
			 }else{
				 str1 = str1.replace("#cost"," .");
			 }
			 if(petitionDetailsVO.getNoOfWorks() != null && petitionDetailsVO.getNoOfWorks().longValue()==1l){
				 str1 = str1.replace("#works",""+petitionDetailsVO.getNoOfWorks()+" work");
			 }if(petitionDetailsVO.getNoOfWorks() != null && petitionDetailsVO.getNoOfWorks().longValue()>1l){
				 str1 = str1.replace("#works",""+petitionDetailsVO.getNoOfWorks()+" works");
			 }else{
				 str1 = str1.replace("#works","");
			 }
			 String leadName = "";
			 if(inputVO.getLeadName() != null && !inputVO.getLeadName().equalsIgnoreCase("0")){
				 leadName = "&nbsp;"+inputVO.getLeadName();
			 }else{
				 if(inputVO.getReportType() != null & !inputVO.getReportType().isEmpty()){
					 leadName="Comment : "+inputVO.getReportType()+"";
				 }else{
					 leadName = "&nbsp;Forwarded for Taking necessary action as per rules";
				 }
			 }
			 /*if(inputVO.getGroupName() != null && !inputVO.getGroupName().equalsIgnoreCase("0")){
				 str1 = str1.replace("#grname"," under Grant - "+ inputVO.getGroupName()+".");
			 }else{
				 str1 = str1.replace("#grname","");
			 }*/
			System.out.println(str1);
			if(coveringLetrImages != null && coveringLetrImages.size()>0){
				for (Object[] objects : coveringLetrImages) {
					if(objects[1] != null && objects[1].toString().equalsIgnoreCase("LOGO")){
						logo = objects[2].toString();
					}else if(objects[1] != null && objects[1].toString().equalsIgnoreCase("DEPARTMENT DETAILS")){
						deptDetailsImg = objects[2].toString();
					}else if(objects[1] != null && objects[1].toString().equalsIgnoreCase("ADDRESS DETAILS")){
						addrDetailsImg = objects[2].toString();
					}else if(objects[1] != null && objects[1].toString().equalsIgnoreCase("SIGNATURE")){
						sign = objects[2].toString();
					}else if(objects[1] != null && objects[1].toString().equalsIgnoreCase("TO ADDRESS DETAILS")){
						toAddrImg = objects[2].toString();
					}else if(objects[1] != null && objects[1].toString().equalsIgnoreCase("HEADER")){
						headerImg = objects[2].toString();
					}
				}
			}
			if(inputVO.getPath() != null && !inputVO.getPath().equalsIgnoreCase("withHeader")){
				headerImg ="emptyheader.png";
			}
			if(inputVO.getFilterId() != null && inputVO.getFilterId().longValue()== 398l){
				toAddrImg = "to_address1.png";
			}else if(inputVO.getFilterId() != null && inputVO.getFilterId().longValue()== 381l){
				toAddrImg = "to_address.png";
			}
			StringBuffer str = new StringBuffer();
			str.append("<html>");
				str.append("<body>");
					str.append("<div class='container'>");
						str.append("<header>");
							str.append("<table class='table'>");
								str.append("<tr>");
									str.append("<td>");
										//str.append("<img src='"+deptDetailsImg+"' width='500px' height='100px'>");
									str.append("<img src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+headerImg.toString()+"' width='500px' height='75px'>");
										//str.append("<img src='D:/Tomcat 7.0/webapps/PRRWS-1.0/Assests/images/petition images/headerImg.png' width='150px' height='90px'>");
										//str.append("<img src='"+IConstants.STATIC_CONTENT_FOLDER_URL+"/"+deptDetailsImg.toString()+"' width='150px' height='90px'>");
									str.append("</td>");
									/*str.append("<td>");
										str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+logo.toString()+"' width='80px' height='80px'>");
									str.append("</td>");
									str.append("<td>");
										str.append("<img src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+addrDetailsImg.toString()+"' width='150px' height='90px'>");
									str.append("</td>");*/
								str.append("</tr>");	
							str.append("</table>");
						str.append("</header><br><br>");
						str.append("<table>");
						str.append("<tr>");
						str.append("<td>");
						str.append("</td>");
						str.append("</tr><br><br>");
						str.append("<tr>");
						str.append("<td>");
						str.append("</td>");
						str.append("</tr><br><br>");
						str.append("<tr>");
						str.append("<td align='center'><u><font size='3' face='Albertus MT Lt'>"+endorseCode+"</font></u></td><br>");
					str.append("</tr>");
					str.append("<tr>");
						str.append("<td><font size='4' face='Albertus MT Lt'>Sir,</font></td>");
					str.append("</tr>");
					str.append("<tr>");
						str.append("<td align='justify'><p style='line-height:200%'><font size='4' face='Albertus MT Lt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+str1+"</font></p><br/><br/></td>");
					str.append("</tr>");
					str.append("<tr>");
					str.append("<td ><p><font size='4' face='Albertus MT Lt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+leadName+". </font></p></td><br>");
				str.append("</tr>");
					/*str.append("<tr>");
						str.append("<td align='right'>" +
								"<font size='4' face='Albertus MT Lt'>&nbsp;Yours faithfully,<br/><br/></font></td>");
							str.append("</tr>");
						*/
							str.append("<tr>");
								str.append("<td >");
									str.append("<p><img align='right' src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+sign.toString()+"' width='95px' height='70px' /></p>");
								//str.append("<p><img align='right' src='D:/Tomcat 7.0/webapps/PRRWS-1.0/Assests/images/petition images/"+sign.toString()+"' width='85px' height='50px' /></p>");
								str.append("</td>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td>");
									str.append("<img src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+toAddrImg.toString()+"' width='180px' height='90px'>");
								//str.append("<img src='D:/Tomcat 7.0/webapps/PRRWS-1.0/Assests/images/petition images/"+toAddrImg.toString()+"' width='180px' height='90px'>");
								str.append("</td>");
							str.append("</tr>");
						str.append("</table>");
					str.append("</div>");	
				str.append("</body>");
			str.append("</html>");
			
			 fileUrl = staticPath.replace(IConstants.STATIC_CONTENT_FOLDER_URL,"")+fileName;
			String endorsmentNO = inputVO.getEndValue();
			
			OutputStream file = new FileOutputStream(new File(staticPath+fileName));
			Document document = new Document(PageSize.A4,70,40, 10, 10);
			FontFactory.register(IConstants.PDF_FONT_PATH,IConstants.PDF_FONT);
			//document.setMargins(70, 50, 10, 10); 
			/*Font subtitleFont = FontFactory.getFont("Times Roman", 12, BaseColor.BLACK);
	        Paragraph subTitle = new Paragraph(str1.toString(), subtitleFont);
	        document.add(subTitle);*/
			PdfWriter.getInstance(document, file);
			document.open();
			//Font code = new Font(FontFamily.COURIER, 40, Font.NORMAL, BaseColor.RED);
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(str.toString()));
			document.close();
			file.close();
			//System.out.println("UPDATE `ntr_health_campaign`.`student` SET `auto_gen_hallticket_status`='1' WHERE `student_id`='"+endorsmentNO+"';");
		}catch (Exception e) {
			System.out.println(e);
			//System.out.println("UPDATE `ntr_health_campaign`.`student` SET `auto_gen_hallticket_status`='2' WHERE `student_id`='"+endorsmentNO+"';");
			return "FAILURE";
		}
		
		return fileUrl;
	}
	
	
	/*public static String generateHallTktPDFGEST(List<StudentVO> list){
		try {
			for(StudentVO st:list){
				try {
					StringBuffer str = new StringBuffer();
					str.append("<html>");
                    
					str.append("<body>");
					str.append("<table bgcolor='#fff' border='1' bordercolor='#f0484a' cellpadding='5'>");
						str.append("<tr>");
							str.append("<td>");
								str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/Can.png' style='width:100%'>");
							str.append("</td>");
						str.append("</tr>");
						str.append("<tr>");
								str.append("<td colspan='3'>");
									str.append("<table >");
										str.append("<tr>");
											str.append("<td ><font size='2' ><b> NAME OF THE STUDENT :</b></font></td>");
											str.append("<td ><font size='2'><span>"+st.getFullName()+"</span></font></td>");
											str.append("<td rowspan='5' width='27%'>");
												str.append("<p style='text-align:center;'><font size='2'><b>HALL TICKET NUMBER</b></font></p><br>");
												str.append("<p style='padding:3px 8px;color:#000;text-align:center;line-height:10px;'><font size='2'><b>"+st.getHallTicketNo()+"</b></font></p><br>");
												str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/ht.jpg' align='center' height='105px' width='103px'>");
											str.append("</td>");
											str.append("<td rowspan='5' border='1'>");
											str.append("<p style='text-align:center;' bgcolor='#fcf7e3' ><font size='1'  ><b>HALL TICKET NUMBER</b></font></p><br>");
											str.append("<p style='padding:3px 8px;color:#000;text-align:center;line-height:7px;'><font size='2'><b>"+st.getHallTicketNo()+"</b></font></p><br>");
											str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/ht.png' align='center' height='105px' width='103px'>");
										str.append("</td>");
										str.append("</tr>");
										str.append("<tr>");
											str.append("<td ><font size='2' ><b>FATHER'S NAME:</b></font></td>");
											str.append("<td ><font size='2'><span>"+st.getRelativeName()+"</span></font></td>");
											//str.append("<td><font size='2'><b>MEDIUM : </b> English</font></td>");
										str.append("</tr>");
										str.append("<tr>");
											str.append("<td><font size='2'><b>EXAMINATION CENTER</b></font></td>");
											str.append("<td align='center'><font size='1'><span>"+st.getExamCenter()+"</span></font></td>");
											str.append("</tr>");
										str.append("<tr>");
											str.append("<td><font size='2'><b>DATE & TIME</b></font>");
											str.append("<td align='center'><font size='1'>10th December, 2017 (Sunday)<br/> 9:30 am to 11:30 am</font>");
											str.append("</td>");
										str.append("</tr>");
										str.append("<tr height='52px'>");
											str.append("<td ><font size='2'><br><b>Signature Of the Candidate</b></font></td>");
											str.append("<td ><font size='2'><br><b>Signature of the Invigilator</b></font></td>");
										str.append("</tr>");
										str.append("<tr>");
										str.append("	<td colspan='3'>");
												str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/ins_gest1.jpg' style='width:100%'>");
											str.append("</td>");
										str.append("</tr>");
									str.append("</table>");
								str.append("</td>");
							str.append("</tr>");
							
					str.append("</table>");
					str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/cut.png' style='width:100%'>");
					str.append("<table bgcolor='#fff' cellpadding='5'  border='1' bordercolor='red'>");
					str.append("<tr>");
						str.append("<td>");
							str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/OF.png' style='width:100%'>");
						str.append("</td>");
					str.append("</tr>");
					str.append("<tr>");
							str.append("<td colspan='3'>");
								str.append("<table bgcolor='#fff' border='1' bordercolor='red'>");
									str.append("<tr>");
									str.append("<td><font size='2'><b>NAME OF THE STUDENT :</b></font></td>");
									str.append("<td><font size='2'><span>"+st.getFullName()+"</span></font></td>");
									str.append("<td rowspan='5' width='27%'>");
									str.append("<font size='2'><p style='text-align:center;'><b>HALL TICKET NUMBER</b></font></p><br>");
									str.append("<font size='2'><p style='padding:3px 8px;color:#000;text-align:center;line-height:10px;'><b>"+st.getHallTicketNo()+"</b></font></p><br>");
									str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/ht.jpg' align='center' height='105px' width='103px'>");
									str.append("</td>");
									str.append("<td rowspan='5'>");
									str.append("<p style='text-align:center;' bgcolor='#fcf7e3' ><font size='1'  ><b>HALL TICKET NUMBER</b></font></p><br>");
									str.append("<p style='padding:3px 8px;color:#000;text-align:center;line-height:7px;'><font size='2'><b>"+st.getHallTicketNo()+"</b></font></p><br>");
									str.append("<img src='D:/tomcat 6.0/webapps/NtrTrustHealthCamp/Images/ht.png' align='center' height='105px' width='103px'>");
									str.append("</td>");
									str.append("</tr>");
									str.append("<tr>");
									str.append("<td ><font size='2'><b>FATHER'S NAME:</b></font></td>");
									//str.append("<td><font size='2'><b>MEDIUM : </b> English</font></td>");
									str.append("<td ><font size='2'><span>"+st.getRelativeName()+"</span></font></td>");
									str.append("</tr>");
									str.append("<tr>");
									str.append("<td><font size='2'><b>EXAMINATION CENTER</b></font></td>");
									str.append("<td align='center'><font size='1'><span>"+st.getExamCenter()+"</span></font></td>");
									str.append("</tr>");
									str.append("<tr>");
									
									str.append("<td><font size='2'><b>Phone Number</b></font></td>");
									str.append("<td><font size='2'><span>"+st.getMobileNo()+"</span></font></td>");
									
									str.append("<td><font size='2'><b>E-Mail ID</b></font></td>");
									str.append("<td><font size='2'><span>"+st.getEmail()+"</span></font></td>");
									
									str.append("</tr>");
									str.append("<tr height='52px'>");
										str.append("<td><font size='2'><br><b>Signature Of the Candidate</b></font></td>");
										str.append("<td><font size='2'><br><b>Signature of the Invigilator</b></font></td>");
									str.append("</tr>");
								str.append("</table>");
							str.append("</td>");
						str.append("</tr>");
						str.append("</table>");
						str.append("</body>");
						str.append("</html>");
					String filePath = "E:/Hall Ticks/GEST/"+st.getMobileNo()+".pdf";
					OutputStream file = new FileOutputStream(new File(filePath));
					Document document = new Document();
					PdfWriter.getInstance(document, file);
					document.open();
					HTMLWorker htmlWorker = new HTMLWorker(document);
					htmlWorker.parse(new StringReader(str.toString()));
					document.close();
					file.close();
					System.out.println("UPDATE `ntr_health_campaign`.`student` SET `auto_gen_hallticket_status`='1' WHERE `student_id`='"+st.getStudentId()+"';");
					convertToImage(filePath);
				} catch (Exception e) {
					System.out.println(e);
					System.out.println("UPDATE `ntr_health_campaign`.`student` SET `auto_gen_hallticket_status`='2' WHERE `student_id`='"+st.getStudentId()+"';");
					return "FAILURE";
				}
			}
		}catch (Exception e) {
		    e.printStackTrace();
		}
		return "SUCCESS";
	}*/
}