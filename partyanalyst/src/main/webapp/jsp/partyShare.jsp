<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
 <head>
  <title> Party Share  Report </title>
  </head>

 <body>
 <script type="text/javascript">
   function showExcel(url){
	   
	   window.open(url);
   }
 </script>
<br/><br/><br/><br/><br/><br/>

 <s:if test="consMap != null">
 
  <div class="container m_top20">
	<div class="row">
	  <div class=" offset4 span4 thumnail">
          <lable>
                      <h4 class="pull-left">Select MulitiReports &nbsp; </h4>     				  
						</lable>
					
</div>
</div>

</div>
<div class="span12"></div>
 <div class="container m_top20">
	<div class="row">
	  <div class=" offset4 span4 thumnail">
         
						<s:form action="getReportForPartyShare">
 						<table><tr><td><s:select list="consMap"   name="constValues" id="constId" multiple="true" ></s:select></td></tr> 
                       <tr><td></td><td><input type="submit" value="GenerateExcel" class="btn btn-info pull-left sapn3" />
                        <s:if test="resultStatus != null && resultStatus.message != null">
                        &nbsp;&nbsp;<a href="javascript:{};" class="btn btn-info pull-right sapn3" onclick="showExcel('${resultStatus.message}');">View Excel</a>
                       </s:if>
					   </td></tr>
					   </table>
					   </s:form>
                      
</div>
</div>

</div>
</s:if>
<br/><br/><br/><br/><br/><br/><br/><br/>
 </html>