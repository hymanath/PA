<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
  <%-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> --%>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
		<script type="text/javascript"  src="js/constituencyInfo.js"></script>	
		<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
<style>
#mainDiv{
margin-left:auto;
margin-right:auto;
width:980px;
margin-top:30px;
}
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-size: 12px;
	 font-weight:bold;
	 color:#333333;

 }
 .requiredFont{
	color:red;
	font-size:13px;
}
</style>
</head>
<body>
<div id="mainDiv" >
<div  class="widget green">
<div id="errorDiv" style="display:none;"></div>
<div id="ConstituencyDiv" class="selectDiv">
	 Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name"  onchange="getPublicationDate();"/> &nbsp;&nbsp;
	  Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
	  <img id="publicationAjaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;float:right;margin-right:165px;"/>
	
		</select>
	 </div>
 <img id="basicInfoAjaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;float:right;margin-right:350px;margin-top:-29px;"/>
	
	 <div id="constituencyBasicInfo"></div><br>
	 </div>
</div>

<script type="text/javascript">
/*$("#constituencyList").live("change",function(){


if($(this).val() != 0 && $("#reportLevel").val() == 1 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
		{
	    getConstituencyBasicInfo();
		 
		}
});*/
 
function getConstituencyBasicInfo()
{

$("#basicInfoAjaxImage").css("display","block");
	var id=$("#constituencyList").val();
	var publicationid=$("#publicationDateList").val();
	if(id == 0)
	{
	
$("#basicInfoAjaxImage").css("display","none");
		$("#errorDiv").html("Please Select Constituency").css({'display':'block','color':'red'});
		return;
	}
	$("#errorDiv").html('');
	 var jsObj=
			 {		

			    constituencyId:id,
				publicationId:publicationid,
				task:"getConstituencyBasicInfo"				
			 };
			 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			 var url = "getConstituencyBasicInfoAction.action?"+rparam;						
			 callAjax(jsObj,url);
}

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);	
									if(jsObj.task == "getConstituencyBasicInfo")
								{
										buildConstituencyBasicInfo(myResults);
								}
								else if(jsObj.task == "getPublicationDate")
								{
								$('#publicationAjaxImage').css('display','none');

									buildPublicationDateList(myResults);
								}
									}catch (e) {
							   
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
	
	function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	var publicationDatesList;
	function buildPublicationDateList(results)
	{
		
	publicationDatesList=results;
	var selectedElmt=document.getElementById("publicationDateList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);

	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}

	var largest = Math.max.apply(Math, publicationIdsArray);
	
	$('#publicationDateList').val(largest);
	 getConstituencyBasicInfo();
	$('#publicationDateList').trigger("change");
	

}
</script>

<script type="text/javascript">

</script>
</body>
</html>