<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title> Party Analyst - Suggestive Model</title>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
 <script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
 <script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.js"></script>

<link rel="stylesheet" href="/resources/demos/style.css" />

<link rel="stylesheet" href="styles/jQ_datatables/css/demo_page.css" />
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_table.css" />
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
 <script type="text/javascript" src="js/suggestiveModel.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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
	<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
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
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
		<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>


	<!-- YUI Dependency files (End) -->


<script type="text/javaScript" >
google.load("visualization", "1", {packages:["corechart"]});

</script>
<style type="text/css">	
#suggestiveMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 990px;margin-top:20px;margin-bottom:20px;}
.requiredFont{color :red}
</style>
</head>
<body>
<div id="suggestiveMainDiv" align="center">
 <div class="widget blue">
  <div id="errorDiv"></div>
 <div id="mainDiv" align="center"  style="margin-top:15px;color:#222222;">

 <table style="width:58%;">
           <tr> <td>Constituency <font id="requiredValue" class="requiredFont">*</font> </td>
			<td></td><td><select id="listConstituencyNames" onchange="getPublicationDate();">
			<option value="0"> Select Constituency </option>
			</select></td>
			<td> Publication Date <font id="requiredValue" class="requiredFont">*</font> </td>
			<td></td><td><select id="publicationDateList">
			<option value="0"> Select Publication </option>
			</select></td>
		</tr>
			<tr><td>From Value</td><td></td><td> <input type="text" id="fromValue" style="width:136px;"/> </td>
			<td>To Value </td><td></td><td><input type="text" id="toValue" style="width:136px;"/></td></tr>		
	</table>	
	<input type="button" value="submit" onclick="getFamilyInfo();" class="btn btn-success"/>
 </div>

  <br/> <br/>
 <div id="familyDetailsDiv" style="overflow-x: scroll; height: 500px;display:none;">
 </div>
 </div>

</div>

<script type="text/javascript">
function getConstituencyList(){

var jsObj= 
	{	
		task:"getConstituencies"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getConstituenciesByPartyNYearAction.action?"+param;
	callAjax(param,jsObj,url);

}

function callAjax(param,jsObj,url){
	var myResults;					
		var callback = {			
				success : function( o ) {
					try 
					{												
						if(o.responseText)
							myResults = YAHOO.lang.JSON.parse(o.responseText);
					    if(jsObj.task == "getConstituencies")
						{	
							populateConstituencyListDropdown(myResults);
						}
						else if(jsObj.task == "getPublicationDate")
								{
								//$('#publicationAjaxImage').css('display','none');

									buildPublicationDateList(myResults);
								}
						else if(jsObj.task == "getFamilyDetails")
						{
							
							buildFamilyDetails(myResults);
						}
						
						}catch (e){
					//alert("Invalid JSON result" + e);   
					
					}  
				},
			       scope : this,
			       failure : function( o ) {
			        			//alert( "Failed to load result" + o.status + " " + o.statusText);
			        }
			    };
		YAHOO.util.Connect.asyncRequest('POST', url, callback);
	}
function populateConstituencyListDropdown(results){

	var list = document.getElementById("listConstituencyNames");
	removeSelectElements(list);
	if(results!=null)
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
			addOptions(list,opElmt);			
		}	
}
function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	

}
function addOptions(list,opElmt){
	try
		{
		list.add(opElmt,null); // standards compliant
		}
	catch(ex)
		{
		list.add(opElmt); // IE only
		}
}
function getFamilyInfo()
{
	var errorDiv =$("#errorDiv");
	$("#familyDetailsDiv").css("display","none");
	var constituencyId = $("#listConstituencyNames").val();
	var publicationId = $("#publicationDateList").val();
	var fromValue =  $.trim($("#fromValue").val());
	var toValue =  $.trim($("#toValue").val());

	var str ='<font color="red">';
	var flag = true;
	if(constituencyId == 0)
	{
		str+='Select Constituency<br/>';
		flag =false;
	}
	if(publicationId == 0)
	{
	str+='Select Publication<br/>';
	flag =false;
	}
	if(fromValue == "" || isNaN(fromValue)) {
		str+='Enter From Value Number<br/>';
		flag =false;
	}
	else if(isNaN(toValue))
	{
		str+='Enter To Value Number<br/>';
		flag =false;
	}
	if(toValue == "")
	{
     toValue = 0;
	}
	else
	{
	 if(toValue < fromValue)
	{
        str+='From Value must be greter than To value<br/>';
		flag =false;
	}
	}
	if(flag == false)
	{
			errorDiv.html(str);
			return;
	}
	
	else
	{
errorDiv.html('');
var jsObj= 
	{	
	        constituencyId:constituencyId,
			publicationId:publicationId,
			minVal:fromValue,
			maxVal:toValue,
		task:"getFamilyDetails"		
	};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getFamilyDetailsAction.action?"+param;
	callAjax(param,jsObj,url);
	}
}
function getPublicationDate()
	{
	
	var constituencyId= $("#listConstituencyNames").val();
	var choice=false;
	
	var jsObj=
	{
		selected:constituencyId,
		task:"getPublicationDate"
	};
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+param;	
	//$('#publicationAjaxImage').css('display','block');
	callAjax(param,jsObj,url);
}

function buildFamilyDetails(results)
{
	
	var str = '';
	if(results.length > 0)
	{
		$("#familyDetailsDiv").css("display","block");
	str+='<table class="table table-bordered">';
	str+='<th>SNO</th>';
	str+='<th>Booth</th>';
	str+='<th>Caste</th>';
	str+='<th>Elder Person Voter Id</th>';
	str+='<th>Elder Person Name</th>';
	str+='<th>Gender</th>';
	str+='<th>Age</th>';
	
	str+='<th>House No</th>';
	str+='<th>Members Count</th>';
	str+='<th>Younger Person Voter Id</th>';
	str+='<th>Voter Name</th>';
	str+='<th>Younger Gender</th>';
	str+='<th>Younger Age</th>';
	
	var j = 1;
	for(var i in results)
		{
	str+='<tr>';	
    str+='<td>'+j+'</td>';
	str+='<td>'+results[i].partNo+'</td>';
	str+='<td>'+results[i].elderCaste+'</td>';
	str+='<td>'+results[i].voterIdCardNo+'</td>';
	str+='<td>'+results[i].elder+'</td>';
    str+='<td>'+results[i].elderGender+'</td>';
	str+='<td>'+results[i].elderAge+'</td>';
	
	str+='<td>'+results[i].houseNo+'</td>';
	str+='<td>'+results[i].count+'</td>';
	str+='<td>'+results[i].voterGroup+'</td>';
	str+='<td>'+results[i].younger+'</td>';
	str+='<td>'+results[i].youngerGender+'</td>';
	str+='<td>'+results[i].youngerAge+'</td>';
	
	str+='</tr>';
		j++;
		}
	str+='</table>';

	$("#familyDetailsDiv").html(str);
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
	$('#publicationDateList').trigger("change");

}
</script>
<script>
 getConstituencyList();

</script>
</body>
</html>
