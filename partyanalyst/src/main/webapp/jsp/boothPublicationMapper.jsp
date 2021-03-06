<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hamlet Booth Publication Mapping Upload</title>

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<style>

.scopeWise_head {
	background-color:#F5EFEA;
	color:#77471D;
	font-weight:bold;
	padding:3px;
	text-align:left;
}
</style>
<script type="text/javascript">
function getPublicationDates()
 {
				var jsObj=
				{
						
						task:"publicationDates"						
				};
			var url = "<%=request.getContextPath()%>/getAllPublicationDatesAction.action";

		callAjax('',jsObj,url);

 }
		
		
		function callAjax(rparam, jsObj, url){
			var resultVO;			
			var callback = {			
		               success : function( o ) {
							try {								
									resultVO = YAHOO.lang.JSON.parse(o.responseText);										
									
									 if(jsObj.task == "publicationDates"){
                                          buildOptions(resultVO);
                                     }									
							}catch (e)  {   
							   	//alert("Invalid JSON result" + e);   
							}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };
		
			YAHOO.util.Connect.asyncRequest('GET', url, callback);			
		}
function buildOptions(results){
    
  var elmt = document.getElementById("publicationDateId");
		for(var i in results)
	   {
		 var option = document.createElement('option');
 
		  option.value=results[i].id;
		  option.text=results[i].name;
		
		  try
		  {
			elmt.add(option,null); // standards compliant
		  }
		  catch(ex)
		  {
			elmt.add(option); // IE only
		  }
	   }
 
}
</script>
</head>
<body>
<s:if test="#session.USER !=null">
  <s:if test="#session.USER.isAdmin == 'true'">
	<s:form name="VillageBoothElectionAction" action="boothPublicationMapAction" method="post" enctype="multipart/form-data" >
		<table>
			<tr>
				<td>Election Type:</td>
				<td>
					<select id="electionTypeSelect" name="electionType" class = "selectWidth">
						<option value="0">Select </option>
						<option value="1">Parliament</option>
						<option value="2">Assembly</option>
					</select>
				</td>
				
			</tr>
			<tr>
			  <td>
			    <b>Publication Date</b> 
			  </td>
			  <td>
			    <select  name="publicationDateId"  id="publicationDateId"/>	
			  </td>
			</tr>
			<tr>
				<td><s:file name="filePath" label="File Path"/></td>
				<td><s:submit name="upload" value="Upload" align="center"/></td>
				<td><s:checkbox name="isValidate" id="isValidate" label="Validate Data"/></td>
			</tr>
		</table>
	</s:form>
	<c:if test="${! empty villageBoothElectionVO }">
		<div>
			<table>
				<tr>
					<td>
						<div style="border:2px solid #F5EFEA;width:800px;margin-bottom:15px;text-align:left;">
						<DIV class="scopeWise_head">Revenue Villages Corrections</DIV>
							<c:choose>
								<c:when test="${! empty villageBoothElectionVO.villageErrors}">
									<c:forEach items="${villageBoothElectionVO.villageErrors}" var="village">
										${village }<br>
									</c:forEach>
								</c:when>
								<c:otherwise>
									No Village Level Corrections.....
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div style="border:2px solid #F5EFEA;width:800px;margin-bottom:15px;text-align:left;">
						<DIV class="scopeWise_head">Hamlets Corrections</DIV>
							<c:choose>
								<c:when test="${! empty villageBoothElectionVO.hamletErrors}">
									<c:forEach items="${villageBoothElectionVO.hamletErrors}" var="hamlet">
										${hamlet }<br>
									</c:forEach>
								</c:when>
								<c:otherwise>
									No Hamlet Level Corrections.....
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div style="border:2px solid #F5EFEA;width:800px;margin-bottom:15px;text-align:left;">
						<DIV class="scopeWise_head">Booths Corrections</DIV>
							<c:choose>
								<c:when test="${! empty villageBoothElectionVO.boothErrors}">
									<c:forEach items="${villageBoothElectionVO.boothErrors}" var="booth">
										${booth }<br>
									</c:forEach>
								</c:when>
								<c:otherwise>
									No Booth Level Corrections.....
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>		
				<tr>
					<td>
						<div style="border:2px solid #F5EFEA;width:800px;margin-bottom:15px;text-align:left;">
						<DIV class="scopeWise_head">Data Duplicate Corrections</DIV>
							<c:choose>
								<c:when test="${! empty villageBoothElectionVO.dataDuplicateErrors}">
									<c:forEach items="${villageBoothElectionVO.dataDuplicateErrors}" var="duplicate">
										${duplicate }<br>
									</c:forEach>
								</c:when>
								<c:otherwise>
									No Duplications Exists In Data.....
								</c:otherwise>
							</c:choose>
						</div>
					</td>
				</tr>	
			</table>
		</div>
	</c:if>
	<script type="text/javascript">
	  getPublicationDates();
	</script>
 </s:if>
 <s:else>
<%
	response.sendRedirect("userEntitlementAction.action");
%>
</s:else>
</s:if>
<s:else>
<%
	response.sendRedirect("loginInputAction.action");
%>
</s:else>
</body>
</html>