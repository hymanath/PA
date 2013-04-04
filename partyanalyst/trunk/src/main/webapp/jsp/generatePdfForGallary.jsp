<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

 <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />


<style>
#fromDate,#toDate{
 cursor: text !important;
}
.marginClass{

	margin:5px;
}
.selectBoxWidth {
    padding: 2px;
    width: 250px;
}
.span2{
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	font-weight: bold;
	margin-top: 8px;	
}
select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 250px;
}

select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
    border-radius: 4px 4px 4px 4px;
	color: #000000;
    display: inline-block;
    font-size: 13px;
    line-height: 18px;
    padding: 4px;
}
input, button, select, textarea {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
}
#mainDiv {margin-left:auto;margin-right:auto;width:990px;}

#headingDiv
{
	background-color: #06ABEA;
    border-radius: 4px 4px 4px 4px;
	color : #FFFFFF;
    font-family: verdana;
    font-size: 22px;
    margin-bottom: 10px;
    margin-left: -10px;
    margin-top: 5px;
    padding: 6px;
    width: 807px;
    border: 1px solid aliceblue;
}
</style>

 <script>
$(document).ready(function(){
$('#categoryId').val(0);
$('#gallariesId').val(0);

});

$(function(){
		$( "#fromDate" ).datepicker({
					dateFormat: 'dd-mm-yy',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date()	
		});
		$( "#toDate" ).datepicker({	
					dateFormat: 'dd-mm-yy',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date()
		});
});

</script>
</head>
<body>
<div align="center"><div id="headingDiv">PDF GENERATION FOR NEWS</div></div>
<div id="mainDiv">
<div class="well span10" style="margin-left:79px; border: 1px solid #CECDCD;  background-color: #FFFFFF;" > 
<!--START-->
	<div id="ErrDiv">&nbsp;</div>

   <div class="span8 marginClass" style="margin-left:147px;">
				<div class="span2"  style="margin-left: 25px;">Select Category :</div>
				<div class="selectDiv">
								 
				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="categoryId" list="categoryList" listKey="ids" listValue="names" onChange="getCandidateGallariesBySelectedCriteria();" headerKey="0" headerValue="Select Category" />
				 </div>
   </div>

   <div class="span8 marginClass" style="margin-left:147px;">
		<div class="span2"  style="margin-left: 25px;">Select Gallery :</div>
		<div class="selectDiv">
						 
		<select id="gallariesId" onchange="validate();"><option value="0">Select Gallery</option></select>
		 </div>
   </div>


	

	<!--END-->



	<!--<div  style="text-align:center;">
	Select Gallary:
	 <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="gallariesId" list="selectOptionList" listKey="id" listValue="name" headerKey="0" headerValue="Select Gallary"/>

	
    </div>-->

	<div  class="span8 marginClass" id="showOptionsDiv" style="text-align:center;margin:11px;">
	<a href="javaScript:{showOptionsDiv();}" style="margin-left: 208px; position:relative">Select Options <i class="icon-circle-arrow-down"></i></a>
    </div>

	<div  id="hideOptionsDiv" style="text-align:center;display:none;margin:11px;">
	<a href="javaScript:{hideOptionsDiv();}" style="margin-left: 70px; margin-top: 0px; position: relative; top: 10px;" >Hide Options <i class="icon-circle-arrow-up" style="margin-left: 10px; margin-top: 1px;"></i></a>
    </div>

	
			

  <div id="optionsDiv" style="display:none;margin-left:147px;">

			<!--<div class="span8 marginClass">
				<div class="span2">Select Category</div>
				<div class="selectDiv">
								 
				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="categoryId" list="categoryList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				 </div>
	       </div>-->

		   <div class="span8 marginClass">
				<div class="span2">Select Impact Level :</div>
				<div class="selectDiv">
				

				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="impactLevelId" list="regionsScopesList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				</div>
	     </div>

		 <div class="span8 marginClass">
				<div class="span2">Select News Importance :</div>
				<div class="selectDiv">
					
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="importanceId" list="newsImportanceList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				 </div>
	        </div>

			 <div class="span8 marginClass">
				<div class="span2">Select Source :</div>
				<div class="selectDiv">
					
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="source" id="sourceId" list="sourceList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				 </div>
	        </div>
			

			
			<div class="span8 marginClass">

				<div  style="font-weight: bold;">

				   Start Date : <input type="text" READONLY="READONLY" id="fromDate"/>
				   End Date : <input type="text" READONLY="READONLY" id="toDate" />

				</div>
			</div>
	   </div>

	<div id="allFilesDiv" style="width: 725px; height: 25px; float: right;" align="center" ><label ><input type="checkbox" id="allFileInd" onchange="dateMadeNull(),hideOptionsDiv();"><b>With All Files</b></input></label></div>


<input type="button" class="btn btn-info pull-right" name="Pdf" onClick="generatePdfForSelectedGallary();" value="Generate Pdf"/>


</div>

</div>
<script>

function validate(){
$('#gallariesId').click(function(){	if($('#gallariesId').val()!=0 || $('#gallariesId').val()!= null)
	$('#ErrDiv').html('&nbsp;');
});

}
function getCandidateGallariesBySelectedCriteria(){
$('#ErrDiv').html('&nbsp;');

 var jsObj =
	    { 
		  categoryId:$('#categoryId').val(),
		  task:"getGallariesByCategory"
	     };
 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 var url = "getGallariesByCategory.action?"+rparam;						
 callnewAjax(jsObj,url);
}

var gallaryName;
function dateMadeNull(){
	$('#fromDate').val('');
	$('#toDate').val('');
	$('#impactLevelId').val(0);
	$('#importanceId').val(0);
	$('#sourceId').val(0);
}
function generatePdfForSelectedGallary()
{
	var selectType;
    if ($('#allFileInd').is(':checked')) 
		selectType = "true";
	else
		selectType = "false";

	var gallaryId = $('#gallariesId').val();
	gallaryName = $('#gallariesId :selected').text();
	var sourceId = $('#sourceId').val();
	var categoryId = $('#categoryId').val();
	var impactLevelId = $('#impactLevelId').val();
	var importanceId = $('#importanceId').val();
	var languageId = $('#languageId').val();
	var startDate = $('#fromDate').val();
	var endDate =  $('#toDate').val();

	  var dt1  = parseInt(startDate.substring(0,2),10);
      var mon1 = parseInt(startDate.substring(3,5),10);
      var yr1  = parseInt(startDate.substring(6,10),10);
      var dt2  = parseInt(endDate.substring(0,2),10);
      var mon2 = parseInt(endDate.substring(3,5),10);
      var yr2  = parseInt(endDate.substring(6,10),10);
      var date1 = new Date(yr1, mon1, dt1);
      var date2 = new Date(yr2, mon2, dt2);
    var betweenDates;
	$('#ErrDiv').html('&nbsp');
	if(categoryId == null || categoryId=='0'){
			$('#ErrDiv').html('<font style="color:red;margin-left: 175px;">Please Select Category</font>');
			return false;
	}
	if(gallaryId == null || gallaryId=='0'){
			$('#ErrDiv').html('<font style="color:red;margin-left: 175px;">Please Select Gallary</font>');
			return false;
	}
	if(startDate != "" || endDate != ""){

		if(startDate == ""){
			$('#ErrDiv').html('<font style="color:red;margin-left: 175px;">Start Date should not Empty </font>');
			 return true;
		}
		else if(endDate == ""){
			$('#ErrDiv').html('<font style="color:red;margin-left: 175px;">End Date should not Empty </font>');
			 return true;
		}
		
		if(date2 < date1){ 
			  $('#ErrDiv').html('<font style="color:red;margin-left: 175px;">Start Date should not greater than End Date </font>');
			 return true;
			}
		betweenDates = "true";
	}		
	else 
		betweenDates = "false";
	

	startDate=mon1+"/"+dt1+"/"+yr1;	
	endDate=mon2+"/"+dt2+"/"+yr2;
	var jsObj =
	{ 
		gallaryId    :gallaryId,
		gallaryName  :gallaryName,		
		categoryId   :categoryId,
		impactLevelId:impactLevelId,
		importanceId :importanceId,
		languageId   :0,
		sourceId     :sourceId,
		betweenDates :betweenDates,
		startDate    :startDate,
		endDate      :endDate,
		allFiles     :selectType,
		task         :"generatePdf"
         
	};
 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 var url = "callAjaxToGeneratePdfFileForAgallary.action?"+rparam;	
 

 callnewAjax(jsObj,url);
 $('#processingImg').show();
}

function getFilesInGallaries(){


	var jsObj =
	{ 
		gallaryId:$('#gallariesId').val(),
	};
 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 var url = "getFilesInAGallary.action?"+rparam;						
 callnewAjax(jsObj,url);

}



function callnewAjax(jsObj,url){
var callback = {			
	 success : function( o ) {
		try
		{ 
			 myResults = YAHOO.lang.JSON.parse(o.responseText); 

			 if( jsObj.task == "generatePdf"){
			 $('#processingImg').hide();
				  if( myResults.noFilesExist =="true"){
					alert("No Files Exist");
					return false;
				  }
				
				   window.location.href="generatePdfForAGallary.action";

			} else if(jsObj.task == "getGallariesByCategory"){
					 $('#gallariesId').find('option').remove();
					   $('#gallariesId').append('<option value=0>Select Gallery</option>');
				   for(var i=0;i<myResults.length;i++)					 
						$('#gallariesId').append('<option value='+myResults[i].id+'>'+myResults[i].name+' </option>');

			 }
	 }

		catch(e)
		{
			
		}  
	 },
	scope : this,
	failure : function( o )
	{
							
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function showOptionsDiv(){

	$('#optionsDiv').show('slow');
	$('#showOptionsDiv').hide();
	$('#hideOptionsDiv').show();
	$('#allFileInd').attr("checked",false);
}

function hideOptionsDiv(){
	var checkedCount=1;	
	
	if($('#allFileInd').is(":checked")){
		$('#optionsDiv').hide('slow');
		$('#showOptionsDiv').show();
		$('#hideOptionsDiv').hide();
		checkedCount=checkedCount+1;
	}
	else if($('#allFileInd').attr("checked",false)){
		$('#showOptionsDiv').show();
		$('#hideOptionsDiv').hide();
		checkedCount=checkedCount+1;
	}
	if(checkedCount=1){		
		$('#optionsDiv').hide('slow');			
	}
}
</script>
</body>
</html>