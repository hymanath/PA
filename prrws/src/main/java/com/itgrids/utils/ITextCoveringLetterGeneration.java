package com.itgrids.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PetitionsWorksVO;
import com.itgrids.dto.PmRequestEditVO;
import com.itgrids.dto.PmRequestVO;

public class ITextCoveringLetterGeneration  {

	public static final String GHOST_SCRIPT_PATH = "C:\\Program Files\\gs\\gs9.21\\bin\\gswin64c.exe";
	public static final String CSS = "table { border:1px solid red; }";
	public static final int	   CUTOFF_LEVEL1 = 95;
	
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
	inputVO.setLeadName("Forwarded for taking further necessary action ");
	inputVO.setGroupName("NREGS Convergence");
	inputVO.setEndValue("5");
	List<Object[]> coveringLetrImages = null;
	String endorseCode = "No.1/Min(PR,RD,ITE&C)/2017Dt.18.05.2017";
	PmRequestEditVO petitionDetailsVO = new PmRequestEditVO();
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
	String str1 = " Please find the enclosed representation recieved from #rname #ref #works #lead";
	//String staticPath =IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER+"/2018/1/24" ;//commonMethodsUtilService.createInnerFolders(IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER);
	//if(staticPath != null && staticPath.equalsIgnoreCase("FAILED"))
		//throw new Exception("File path not available . Please check once file path.");
//String datePath = commonMethodsUtilService.generateImagePathWithDateTime();
//String fileName = datePath+"_"+inputVO.getEndValue()+".PDF";
	String staticPath =IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER+"/2018/1/24/";
	String fileName = inputVO.getEndValue()+".PDF";
	generateCOVERINGLETTER(inputVO,coveringLetrImages,endorseCode,petitionDetailsVO,str1,staticPath,fileName);
}*/
	public static String generateCOVERINGLETTER(InputVO inputVO,List<Object[]> coveringLetrImages,String endorseCode,PmRequestEditVO petitionDetailsVO,String str1,String staticPath,String fileName){
		String fileUrl = "";
		try {
			String sysPath = "D:/Tomcat 7.0/webapps/PRRWS-1.0/";
			String logo ="";
			//D:/Tomcat 7.0/webapps/PRRWS-1.0/Assests/images/petition images/Group 1.png
			String deptDetailsImg ="";
			String addrDetailsImg ="";
			String sign ="";
			String toAddrImg ="";
			String headerImg ="";
			//List<PmRequestVO> representeeList = new ArrayList<PmRequestVO>();
			StringBuilder referrals = null;
			if(inputVO.getDisplayType() != null && (inputVO.getDisplayType().equalsIgnoreCase("AP MINISTER") || inputVO.getDisplayType().equalsIgnoreCase("MINISTER"))){
				str1=str1.replace("#min", "");
			}else{
				str1=str1.replace("#min", "As direced by Hon'ble Minister, ");
			}
			String depts =inputVO.getDeptCode();
			depts = inputVO.getDeptCode().replace("(", "");
			if(inputVO.getDisplayType() != null && (inputVO.getDisplayType().equalsIgnoreCase("AP MINISTER") || inputVO.getDisplayType().equalsIgnoreCase("MINISTER"))){
				str1=str1.replace("#Addr", "");
			}else{
				str1=str1.replace("#Addr", " addressed to Hon'ble Minister for "+inputVO.getDeptCode().replace("(", "")+" ");
			}
 			if(petitionDetailsVO != null){
 				if(petitionDetailsVO.getRepresentationdate() != null && !petitionDetailsVO.getRepresentationdate().equalsIgnoreCase("")){
 					 String[] strArr = petitionDetailsVO.getRepresentationdate().split("-");
 					 String dateFormat = strArr[0]+"."+strArr[1]+"."+strArr[2];
					 str1 = str1.replace("#rdate", "Dated: &nbsp;&nbsp;<b>"+dateFormat+"</b>");
				 }else{
					 str1 = str1.replace("#rdate", "");
				 }
				if(petitionDetailsVO.getRepresenteeDetailsList() != null && petitionDetailsVO.getRepresenteeDetailsList().size()>0){
					//representeeList.addAll(petitionDetailsVO.getRepresenteeDetailsList());
					for (PmRequestVO pmRequestVO : petitionDetailsVO.getRepresenteeDetailsList()) {
						 if(pmRequestVO.getName() != null && !pmRequestVO.getName().equalsIgnoreCase("")){
							 str1 = str1.replace("#rname", pmRequestVO.getName());
						 }else{
							 str1 = str1.replace("#rname", "");
						 }
						 if(pmRequestVO.getDesignation() != null && !pmRequestVO.getDesignation().equalsIgnoreCase("")){
							 str1 = str1.replace("#rdesig", ","+pmRequestVO.getDesignation());
						 }else{
							 str1 = str1.replace("#rdesig", "");
						 }
						 if(pmRequestVO.getAddressVO().getAssemblyName() != null && !pmRequestVO.getAddressVO().getAssemblyName().equalsIgnoreCase("")){
							 str1 = str1.replace("#rconst",","+pmRequestVO.getAddressVO().getAssemblyName()+"&nbsp;Constitueny");
						 }else{
							 str1 = str1.replace("#rconst","");
						 }
						 if(pmRequestVO.getAddressVO().getDistrictName() != null && !pmRequestVO.getAddressVO().getDistrictName().equalsIgnoreCase("")){
							 str1 = str1.replace("#rdist",","+pmRequestVO.getAddressVO().getDistrictName()+"&nbsp;&nbsp;District");
						 }else{
							 str1 = str1.replace("#rdist","");
						 }
					}
				}
 			}
 			
 			
				//representeeList.clear();
 			/*if(petitionDetailsVO != null){
 				referrals = new StringBuilder();
				if(petitionDetailsVO.getReferDetailsList() != null && petitionDetailsVO.getReferDetailsList().size()>0){
					for (PmRequestVO pmRequestVO : petitionDetailsVO.getReferDetailsList()) {
						int index = petitionDetailsVO.getReferDetailsList().indexOf(pmRequestVO);
						int no = 1;
						int index1=index+no;
						if(pmRequestVO.getName() != null && !pmRequestVO.getName().equalsIgnoreCase("")){
							referrals.append("<br> "+index1+")&nbsp;&nbsp; "+pmRequestVO.getName());
							 //str1 = str1.replace("#refname","<br> "+index1+". "+pmRequestVO.getName());
						 }else{
							// str1 = str1.replace("#refname","");
						 }
						if(pmRequestVO.getDesignation() != null && !pmRequestVO.getDesignation().equalsIgnoreCase("")){
							referrals.append(","+pmRequestVO.getDesignation());
							// str1 = str1.replace("#refdesig", ","+pmRequestVO.getDesignation());
						 }else{
							 //str1 = str1.replace("#refdesig", "");
						 }
						if(pmRequestVO.getCandidateAddressVO().getAssemblyName() != null && !pmRequestVO.getCandidateAddressVO().getAssemblyName().equalsIgnoreCase("")){
							referrals.append(","+pmRequestVO.getCandidateAddressVO().getAssemblyName()+"(Const)");
							//str1 = str1.replace("#refconst",","+pmRequestVO.getCandidateAddressVO().getAssemblyName());
						 }else{
							 //str1 = str1.replace("#refconst", "");
						 }
						referrals.append(".");
						//str1 = str1.replace("#refComma", ".");
					}
				}
				
			}
 			if(referrals != null){
				 str1 = str1.replace("#ref", " <br><b>Referred By :</b> :"+referrals+"<br>");
			 }else{
				 str1 = str1.replace("#ref", "");
			 }*/
 			 String estCost ="0.0";
			List<PetitionsWorksVO> worksList = new ArrayList<PetitionsWorksVO>();
			StringBuilder works = new StringBuilder();
			 if(petitionDetailsVO.getSubWorksList() != null && petitionDetailsVO.getSubWorksList().size()>0){
				 for (PetitionsWorksVO pmSubwork : petitionDetailsVO.getSubWorksList()) {
					/*PetitionsWorksVO works1 = new PetitionsWorksVO();
					 works1.setWorkId(105060l);
					 works1.setWorkName("Test Work Description1");
					 worksList.add(works1);
					 PetitionsWorksVO works2 = new PetitionsWorksVO();
					 works2.setWorkId(105061l);
					 works2.setWorkName("Test Work Description1");
					 worksList.add(works2);
					pmSubwork.getSubWorksList().addAll(worksList);*/
					
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
								/*if(inputVO.getGroupName() != null && !inputVO.getGroupName().equalsIgnoreCase("0")){
									grant = " under "+ inputVO.getGroupName();
								}
								if(!pmSubwork1.getAddressVO().getPanchayatName().isEmpty()){
									villageName= pmSubwork1.getAddressVO().getPanchayatName()+"&nbsp;&nbsp;Village and ";
								}
								if(!pmSubwork1.getAddressVO().getDistrictName().isEmpty()){
									dist= pmSubwork1.getAddressVO().getDistrictName()+"&nbsp;&nbsp;District";
									}
								if(!pmSubwork1.getAddressVO().getTehsilName().isEmpty()){
									tehsil= pmSubwork1.getAddressVO().getPanchayatName()+"&nbsp;&nbsp;Mandal , ";
									}
								if(!pmSubwork1.getAddressVO().getAssemblyName().isEmpty()){
									consti= pmSubwork1.getAddressVO().getAssemblyName()+"&nbsp;&nbsp;Constituency of ";
									}*/
							// works.append(index1+")&nbsp;&nbsp;"+pmSubwork1.getSubSubject()+"&nbsp;in&nbsp;"+villageName+""+tehsil+""+consti+""+dist+""+grant+""+estCost+".<br>");
							 //works.append(".");
						 }
					}
				}
			 }
			 String grant ="";
			 if(inputVO.getGroupName() != null && !inputVO.getGroupName().equalsIgnoreCase("0")){
					grant = " under "+ inputVO.getGroupName();
				}
			 if(estCost != ""){
				 str1 = str1.replace("#cost", "&nbsp;"+grant+"&nbsp;With an Estimated Cost of Rs.<b>"+estCost+"</b>&nbsp;Lakhs ");
			 }else{
				 str1 = str1.replace("#cost", "");
			 }
			 if(inputVO.getSchemeIdsList() != null && inputVO.getSchemeIdsList().size()>0){
				 str1 = str1.replace("#works", "&nbsp;"+inputVO.getSchemeIdsList().size()+"");
			 }else{
				 str1 = str1.replace("#works", "");
			 }
			 if(inputVO.getLeadName() != null && !inputVO.getLeadName().equalsIgnoreCase("0")){
				 str1 = str1.replace("#lead", "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				 		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				 		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				 		"**"+inputVO.getLeadName()+"**");
			 }else{
				 if(inputVO.getReportType() != null & !inputVO.getReportType().isEmpty()){
					 str1 = str1.replace("#lead", "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						 		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						 		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						 		"Comment : **"+inputVO.getReportType()+"**");
				 }else{
					 str1 = str1.replace("#lead", "<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						 		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						 		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
						 		"**Forwarded forTaking necessary action as per Rules**");
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
			StringBuffer str = new StringBuffer();
			str.append("<html>");
				str.append("<body>");
					str.append("<div class='container'>");
						str.append("<header>");
							str.append("<table class='table'>");
								str.append("<tr>");
									str.append("<td>");
										//str.append("<img src='"+deptDetailsImg+"' width='500px' height='100px'>");
									str.append("<img src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+headerImg.toString()+"' width='500px' height='100px'>");
										//str.append("<img src='http://www.mydepartments.in/PRRWS/Assests/images/petition images/dept_details.png' width='150px' height='90px'>");
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
							str.append("<tr align='center'>");
								str.append("<td ><font size='3'><u>"+endorseCode+"</u></font></td><br>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td><b>Sir,</b></td>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+str1+"</p></td><br><br>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
										"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
										"Yours faithfully,</td>");
							str.append("</tr>");
						str.append("</table>");
						str.append("<table>");
							str.append("<tr>");
								str.append("<td>");
									str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+sign.toString()+"' width='80px' height='50px'>");
								str.append("</td>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td>");
									str.append("<img src='http://www.mydepartments.in/PRRWS/Petition_Documents/Logos/"+toAddrImg.toString()+"' width='170px' height='90px'>");
								str.append("</td>");
							str.append("</tr>");
						str.append("</table>");
					str.append("</div>");	
				str.append("</body>");
			str.append("</html>");
			
			 fileUrl = staticPath.replace(IConstants.STATIC_CONTENT_FOLDER_URL,"")+fileName;
			String endorsmentNO = inputVO.getEndValue();
			OutputStream file = new FileOutputStream(new File(staticPath+fileName));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
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