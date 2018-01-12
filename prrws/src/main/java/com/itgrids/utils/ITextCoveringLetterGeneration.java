package com.itgrids.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
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
	inputVO.setEndValue("5");
	List<Object[]> coveringLetrImages = null;
	String endorseCode = "No.1/Min(PR,RD,ITE&C)/2017Dt.18.05.2017";
	PmRequestEditVO petitionDetailsVO = new PmRequestEditVO();
	petitionDetailsVO.setWorkName("requesting for sanction ...ost of Rs.150.00 Lakhs.");
	
	generateCOVERINGLETTER(inputVO,coveringLetrImages,endorseCode,petitionDetailsVO);
}*/
	public static String generateCOVERINGLETTER(InputVO inputVO,List<Object[]> coveringLetrImages,String endorseCode,PmRequestEditVO petitionDetailsVO,String str1){
		String filePath = "";
		try {
			String sysPath = "D:/Tomcat 7.0/webapps/PRRWS-1.0/";
			String logo ="";
			String deptDetailsImg ="";
			String addrDetailsImg ="";
			String sign ="";
			String toAddrImg ="";
			List<PmRequestVO> representeeList = new ArrayList<PmRequestVO>();
			if(petitionDetailsVO != null){
				if(petitionDetailsVO.getRepresenteeDetailsList() != null && petitionDetailsVO.getRepresenteeDetailsList().size()>0){
					representeeList.addAll(petitionDetailsVO.getRepresenteeDetailsList());
				}else if(petitionDetailsVO.getReferDetailsList() != null && petitionDetailsVO.getReferDetailsList().size()>0){
					representeeList.addAll(petitionDetailsVO.getReferDetailsList());
				}
			}
			
			if(representeeList != null && representeeList.size()>0){
				for (PmRequestVO pmRequestVO : representeeList) {
					str1 = str1.replace("#rname", pmRequestVO.getName());
					 str1 = str1.replace("#rdate", petitionDetailsVO.getRepresentationdate());
					 str1 = str1.replace("#rdesig", pmRequestVO.getDesignation());
					 str1 = str1.replace("#rconst",pmRequestVO.getCandidateAddressVO().getAssemblyName());
					 str1 = str1.replace("#rdist",pmRequestVO.getCandidateAddressVO().getDistrictName());
					 if(petitionDetailsVO.getSubWorksList() != null && petitionDetailsVO.getSubWorksList().size()>0){
						 for (PetitionsWorksVO pmSubwork : petitionDetailsVO.getSubWorksList()) {
							 str1 = str1.replace("#subj",pmSubwork.getSubject());
						}
					 }
					 str1 = str1.replace("#cost",petitionDetailsVO.getEstimateCost());
					System.out.println(str1);
				}
			}
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
										str.append("<img src='D:/Tomcat 7.0/webapps/PRRWS-1.0/"+deptDetailsImg.toString()+"' width='150px' height='90px'>");
									str.append("</td>");
									str.append("<td>");
										str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='D:/Tomcat 7.0/webapps/PRRWS-1.0/"+logo.toString()+"' width='80px' height='80px'>");
									str.append("</td>");
									str.append("<td>");
										str.append("<img src='D:/Tomcat 7.0/webapps/PRRWS-1.0/"+addrDetailsImg.toString()+"' width='150px' height='90px'>");
									str.append("</td>");
								str.append("</tr>");	
							str.append("</table>");
						str.append("</header><br><br>");
						str.append("<table>");
							str.append("<tr align='center'>");
								str.append("<td ><font size='3'><b>"+endorseCode+"</b></font></td><br>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td><b>Dear Sir,</b></td>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+str1+"</p></td><br><br>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;With regards,</td>");
							str.append("</tr>");
						str.append("</table>");
						str.append("<table>");
							str.append("<tr>");
								str.append("<td>");
									str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='D:/Tomcat 7.0/webapps/PRRWS-1.0/"+sign.toString()+"' width='80px' height='50px'>");
								str.append("</td>");
							str.append("</tr>");
							str.append("<tr>");
								str.append("<td>");
									str.append("<img src='D:/Tomcat 7.0/webapps/PRRWS-1.0/"+toAddrImg.toString()+"' width='170px' height='90px'>");
								str.append("</td>");
							str.append("</tr>");
						str.append("</table>");
					str.append("</div>");	
				str.append("</body>");
			str.append("</html>");
			
			
			String endorsmentNO = inputVO.getEndValue();
			 filePath = "E:/Petitions/CoverLetter/"+endorsmentNO+".pdf";
			OutputStream file = new FileOutputStream(new File(filePath));
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
		
		return filePath;
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