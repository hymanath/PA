<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ResourceBundle;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feed Back Form</title>
<link rel="SHORTCUT ICON" type="image/x-icon"
	href="images/icons/homePage/faviIcon.jpg">
<style>
.h3 {
	color: #000080;
	font-family: lucida grande, tahoma, verdana, arial, sans-serif;
	size: 6px;
	font-weight: bold;
}

#element {
	color:#0174DF;
	font-size: 15px;
	text-align: left;
	margin-left: 75px;
	margin-top: 34px;
}

#style {
	font-size: 13px;
 margin-right: 418px;
	margin-top: 11px;
	size: 6px;
}

#fontstyle {
	color: rgb(0, 0, 128);
	font-weight: bold;
	text-align: left;
	margin-left: 84px;
	font-size: 12px;
}

.fontcolor {
	color: #FF8000;
}

.required {
color:red;
}

</style>
</head>
<body>

    <h1><font color="#FF8000" face="Arial"> FeedBack Form</font></h1>
      <div align="right" class="h3" width="100%">
        <table border="0" cellpadding="0" cellspacing="0" width="92%">
	      <tr>
		    <td><font class="fontcolor" size="2px">Please note:</font>Although it is most unlikely that	you will experience any problems responding </td>
	     </tr>
	        <tr>
		      <td>to this form, certain non-standard browsers will not respond properly. If you experience any</td>
	       </tr>
	       <tr>
		   <td>difficulties, (or if you are not using a forms-capable browser) you may mail your response to</td>
	      </tr>
	     <tr>
		  <td>this form to: <a href="mailto:info@itgrids.com">info@itgrids.com</a></td>
	  </tr>
   </table>
  </div>
   <s:form action="userFeedbackSubmitAction" method="POST">
     <div id = "userFeedbackDiv">
	<table>
			<tr>
				<td colspan="2">
					<div style="color: red;">
						<s:actionerror />
						<s:fielderror />
					</div>
				</td>
			</tr>
	</table>
	<c:if test="${result != null && result == 'SUCCESS'}">
				<DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px; ">Feedback Submitted successfully</DIV>
	</c:if>
     <div class="h3" id="element">What kind of comments would you like to send<font class="required">*</font></div><br>
       <div class="h3" id="style">
	     <s:radio theme="simple" cssClass="selectWidth" name="commentType" list="#session.feedbackCommentList" listKey="id" listValue="name" >
           </s:radio>
       </div>
       <div id="element" class="h3"> What about us do you want to comment on?<font class="required">*</font>&nbsp&nbsp
           <s:select theme="simple" cssClass="selectWidth" name="commentTask" list="#session.feedbackTaskList" listKey="id" listValue="name" headerKey = "0" headerValue = "Choose Any" >
    	  </s:select>
      </div>
	     <div id="element" class="h3">
		 <table><tr>
		<td valign="top" width="200px"> <s:label for="commentId" theme="simple" id="fnameLabel"  value="Enter Your Comments"/><font class="required">*</font></td>
        <td> <s:textarea  id="commentId"theme="simple"name="comment" rows="5" cols="39" align="right" /></td>
		  </tr></table>
	      </div>
		 
       <div class="h3" id="element">Please contact as soon as possible regarding this matter:
          <input TYPE="radio" NAME="responseCategory" VALUE="Early">Early 
          <input TYPE="radio" NAME="responseCategory" VALUE="Late">Late<br>
       </div><br><br>
       <input TYPE="submit" VALUE="Submit">&nbsp &nbsp
        <input TYPE="reset" VALUE="Reset">
   </div>
  </s:form>
</body>
</html>
