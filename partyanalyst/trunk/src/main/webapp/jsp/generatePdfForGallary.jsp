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

.marginClass{

	margin:5px;
}
</style>

 <script>
$(function() {
$( "#fromDate" ).datepicker();
$( "#toDate" ).datepicker();
});
</script>
</head>
<body>

<div class="well span10" style="margin-left:79px;"> 
<!--START-->

   <div class="span8 marginClass" style="margin-left:147px;">
				<div class="span2">Select Category</div>
				<div class="selectDiv">
								 
				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="categoryId" list="categoryList" listKey="ids" listValue="names" onChange="getCandidateGallariesBySelectedCriteria();" headerKey="0" headerValue="Select Category"/>
				 </div>
   </div>

   <div class="span8 marginClass" style="margin-left:147px;">
		<div class="span2">Select Gallery</div>
		<div class="selectDiv">
						 
		<select id="gallariesId"><option value="0">Select Gallery</option></select>
		 </div>
   </div>


	

	<!--END-->



	<!--<div  style="text-align:center;">
	Select Gallary:
	 <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="gallariesId" list="selectOptionList" listKey="id" listValue="name" headerKey="0" headerValue="Select Gallary"/>

	
    </div>-->

	<div  class="span8 marginClass" id="showOptionsDiv" style="text-align:center;margin:11px;">
	<a href="javaScript:{showOptionsDiv();}">Select Options <i class="icon-circle-arrow-down"></i></a>
    </div>

	<div  id="hideOptionsDiv" style="text-align:center;display:none;margin:11px;">
	<a href="javaScript:{hideOptionsDiv();}">Hide Options <i class="icon-circle-arrow-up"></i></a>
    </div>

	
			

  <div id="optionsDiv" style="display:none;margin-left:147px;">

			<!--<div class="span8 marginClass">
				<div class="span2">Select Category</div>
				<div class="selectDiv">
								 
				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="categoryId" list="categoryList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				 </div>
	       </div>-->

		   <div class="span8 marginClass">
				<div class="span2">Select ImpactLevel</div>
				<div class="selectDiv">
				

				<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="impactLevelId" list="regionsScopesList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				</div>
	     </div>

		 <div class="span8 marginClass">
				<div class="span2">Select NewsImportance</div>
				<div class="selectDiv">
					
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="importance" id="importanceId" list="newsImportanceList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				 </div>
	        </div>

			 <div class="span8 marginClass">
				<div class="span2">Select Source</div>
				<div class="selectDiv">
					
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="source" id="sourceId" list="sourceList" listKey="ids" listValue="names" headerKey="0" headerValue="All"/>
				 </div>
	        </div>
			

			
			<div class="span8 marginClass">

				<div>

				   Start Date: <input type="text" id="fromDate"/>
				   End Date <input type="text" id="toDate" />

				</div>
			</div>
	   </div>

	<div style="text-align:center;"><label><input type="checkbox" id="allFileInd"><b>With All Files</b></input></label></div>


<input type="button" class="btn btn-info pull-right" name="Pdf" onClick="generatePdfForSelectedGallary();" value="GeneratePdf"/>


</div>


<script>


function getCandidateGallariesBySelectedCriteria(){


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
function generatePdfForSelectedGallary(){

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

    var betweenDates;

	if(startDate != "" || endDate != "")
		betweenDates = "true";
	else 
		betweenDates = "false";
	
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

				  if( myResults.noFilesExist =="true"){
					alert("No Files Exist");
					return false;
				  }

				   window.location.href="generatePdfForAGallary.action";

			} else if(jsObj.task == "getGallariesByCategory"){

				 $('#gallariesId').find('option').remove();
				  
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

}

function hideOptionsDiv(){
	$('#optionsDiv').hide('slow');
	
	$('#showOptionsDiv').show();
	$('#hideOptionsDiv').hide();
}
</script>
</body>
</html>