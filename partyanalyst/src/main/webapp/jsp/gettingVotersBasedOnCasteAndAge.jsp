<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


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
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>


   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<script type="text/javascript" src="js/highcharts/js/highchartColorPicker.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<style>
	.background{background-color:#ffffff;}
</style>

</head>

<body>

<div id="ConstituencyDiv" class="selectDiv container"  style="border: 1px solid; margin-top: 40px; padding-bottom: 13px; padding-top: 21px; border-radius: 4px;">
<div style="margin-left: 295px; padding-bottom: 23px;">
		<h4>Constituency Wise Age Details.</h4>
</div>
<div id="errorDiv" style="color:red; margin-left: 336px; margin-top: 0px; margin-bottom: 9px; "></div>
<div>
	<div style="margin-bottom: 11px; margin-left: 221px;">
		<span id="constId">Constituency<font style="color:red">*</font></span>:<s:select theme="simple" style="height: 30px; width: 200px; margin-left: 25px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getPublicationDate();"/> <span id="constErrorId" style="color:red"></span>
	</div>
	<div style="margin-bottom: 11px; margin-left: 221px;">
		<span id="pubId">Publication Date<font style="color:red">*</font></span>:
		<select id="publicationDateList" class="selectWidth" style="height: 30px; width: 200px;" name="publicationDateList">
		</select><span id="pubErrorId" style="color:red"></span>
		
		
	</div>
	<div style="color:red;margin-bottom: 11px; margin-left: 221px ">note: Age Ranges should give with hypen or ">","<","<=",">="  symbols only. Ex:18-22,>25,<25,<=25,>=25,25 .</div>
	<div class="agerangeClass">
	<div style="margin-bottom: 11px; margin-left: 221px " id="divId0" >
	<span id="ageRangeId">Age Range</span>:  <input type="text" name="ageRange"  style="margin-left: 42px; width: 185px;" class="ageRangeClass" >
	    <input type="button" value="add" class="btn btn-info" onClick="addVoterAgeRange()" >
	    <input type="button" value="delete" class="btn btn-info" onClick='deleteVoterAgeRange("divId0")'>
		<div id="refreshImg"  style="display:none;float:right;margin-right:160px;"></div>
	</div>
	</div>
	<div style="margin-bottom: 11px; margin-left: 221px;"><span>Partial Panchayats</span> :<input type="checkbox" value="patialpanchayat" name="radio1" id="partialCheckboxId" style="margin-left:19px;margin-top:-2px;"></div>
</div>
<div style="margin-bottom: 11px; margin-left: 390px;">
<input type="button" value="submit" class="btn btn-info" onClick="gettingVoterDetailsForAConstituency()">
<span><img id="ajaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/></span>
</div><br>
</div>

</div>

<script type="text/javascript">

var count=1;
function addVoterAgeRange()
{
 var str="";
 str+=" <div id='divId"+count+"' style='margin-left: 221px;'> ";
 str+=" AgeRange:  <input type='text' name='ageRange'  style='margin-left: 42px; width: 185px;' class='ageRangeClass' id='divId"+count+"'>&nbsp ";
 str+=" <input type='button'  class='ageRangeAddButton btn btn-info' value='add' onClick='addVoterAgeRange()'>&nbsp";
 var id1="divId"+count;
 str+=" <input type='button' value='delete' class='btn btn-info' onClick='deleteVoterAgeRange(\""+id1+"\") '> ";
 str+=" </div>";
 $('.agerangeClass').append(str);
 count++;
}
function deleteVoterAgeRange(id)
{
	document.getElementById(""+id+"").innerHTML='';
}


function getPublicationDate()
	{
	 
	var constituencyID = document.getElementById("constituencyList");
	var name=constituencyID.options[constituencyID.selectedIndex].name;
	var value=constituencyID.options[constituencyID.selectedIndex].value;
	var choice=false;
	var locationAlertEl =  document.getElementById("locationAlertMsg");
	
	var jsObj=
	{
		selected:value,
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	

	$('#publicationAjaxImage').css('display','block');
	callAjax(jsObj,url);
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

	//$('#publicationDateList').val(largest);
	//$('#publicationDateList').trigger("change");

}

function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	
function gettingVoterDetailsForAConstituency()
{   
  
$('#errorDiv').html("");
var str=''
if($('#constituencyList').val() == 0)
str+='please select Constituency.<br>';
if( ($('#publicationDateList').val()== null) || ($('#publicationDateList').val()== 0) )
str+='please select publication Date.<br>';
console.log($('#ageRangeId').val());

if(str.length > 0)
{
$('#errorDiv').html(str);
return;
}  
  

$("#ajaxImage").css("display"," inline-block");
var arr = new Array();
$.each($('.ageRangeClass'), function() {
arr.push(this.value );
});
	   
   var constituencyId=$('#constituencyList option:selected').val();
   var publicationId=$('#publicationDateList option:selected').val();
   //var ageOrCasteRadio = $('input:radio[name=radio1]:checked').val();
    var partialId = document.getElementById("partialCheckboxId").checked;
    var jsObj=
	{
		constituencyId:constituencyId,
		 publicationId:publicationId,
		 ageOrCasteRadio:'age',
		 ageRangeIds:arr,
		 partialId:partialId,
		 task:"gettingVoterDetails"
	};
var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "votersInfoAjaxAction.action?"+rparam;	
callAjax(jsObj,url);
}	

function callAjax(jsObj,url)
		{  //  alert(789);
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								    if(jsObj.task == "getPublicationDate") 
								   {
								     $('#publicationAjaxImage').css('display','none');
                                     buildPublicationDateList(myResults);
								   }
								   else if(jsObj.task=="gettingVoterDetails")
							       {  
								      $("#ajaxImage").css("display","none");
								      if(myResults != null)
									  {
										 if(myResults.resultCode == 0)
										 {
											window.open(myResults.message);
										 }
										 else
										 {
											alert("Exception Occured");
										 }
									  }									  
								   }
							    }catch (e) {
							                $("#votersEditSaveAjaxImg").hide();
							                $("#votersEditSaveButtnImg").removeAttr("disabled");
								           }  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
</script>


</body>
</html>