<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> 

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
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">

<style>

#impFamDtls table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;clear:both;}
#impFamDtls table a{color:#0088CC;text-decoration:none;}

#impFamDtls table tr:nth-child(even){background:#EdF5FF;}

#impFamDtls table tr:nth-child(odd){background:#ffffff;}
#impFamDtls table th{ background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}

.buttonsTop{
	  margin-top:20px;
	  width:880px;
	  text-align: center;
	}
	.buttonLeft{
	  margin-left:10px;
	}
	
	#impFamPancBothDtls,#impFamDtls{
 margin-top:12px;
 margin-left: auto;
    margin-right: auto;
    width:100%;
}
#multipleVoterFamiliesEditDiv{ background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #D3D3D3;
    margin-bottom: 10px;
    margin-top: 10px;
    padding-left: 10px;
}


.btn {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background-color: #F5F5F5;
    background-image: linear-gradient(to bottom, #FFFFFF, #E6E6E6);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) #A2A2A2;
    border-image: none;
    border-radius: 4px 4px 4px 4px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);
    color: #333333;
    cursor: pointer;
    display: inline-block;
    font-size: 14px;
    line-height: 20px;
    margin-bottom: 0;
    padding: 4px 12px;
    text-align: center;
    text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
    vertical-align: middle;
}
a {
    color: #0088CC;
    text-decoration: none;
}
</style>
</head>
<body>

<div id="mainDiv" style="width:990px;margin-left:auto;margin-right:auto;margin-top:50px;">


 <div id="impFamDtlsTitle"></div>

<div class="widget blue whitegloss">
 <h5>Check Required Fields To Show</h5>

          <div id="impFamilySelectedDetails" style="padding:10px 0 10px 27px;border:1px solid #c3c3c3;border-radius:5px;height:100px;">

           

			<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="genderId">Gender</input></label>

			<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="ageId">Age</input></label>

			 <label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="guardianNameId" >House No</input></label>

		    <label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="guardianNameId" >Guardian Name</input></label>

		    <label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="relationShipId">Relation Ship</input></label>
			
			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="mobileId  ">Mobile No</input></label>

			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="casteId">Caste</input></label>

			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="partyId">Party</input></label>

			<!--<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes" id="moneyId">Money</input></label>-->
			<div id="impFamilySelectedDetails1"></div>

			<div style="float:right;margin-top:-2px;margin-right:10px;"><a class="btn" href="javaScript:{checkForAttributesToDisplay();}">Show Details</a></div>
		  </div>

           
		   <div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable"></div>
	       <div class="buttonsTop"><span class="buttonLeft"><input class="btn" onclick="selectAll('familyMemberCheck')" type="button" value="Select All" /></span><span class="buttonLeft"><input onclick="deSelectAll('familyMemberCheck')" class="btn" type="button" value="De Select All" /></span><span class="buttonLeft"><input class="btn" type="button" value="Edit" onclick="getAllVoterFamiliesForEdit();"/></span></div>
		   <div id="multipleVoterFamiliesEditDiv" style="display:none;"></div><br><br>
		</div>


</div>




<form id="getAllVoterFamiliesForEditForm" method="post" action="getMultipleFamilesInfoForEditAction.action" name="getAllVoterFamiliesForEditForm">
	   <input type="hidden" name="task" id="getAllVoterFamiliesForEditFormValues" />
</form>
<form id="getAllVoterFamiliesInfoForEditForm" method="post" action="getMultipleFamilesInfoForEditAction.action" name="getAllVoterFamiliesInfoForEditForm">
	   <input type="hidden" name="task" id="getAllVoterFamiliesInfoForEditFormValues" />
</form>


<script type="text/javascript">

 google.load("visualization", "1", {packages:["corechart"]});
var votersLimitExist = false;
var impFamiliesEditArray = window.opener.impFamiliesEditArray;
var id="${id}";
var publicationDateId = "${publicationDateId}";
var hno ="${hno}";
function editSelectedFamilies(){


  if(impFamiliesEditArray.length > 0){
   if(impFamiliesEditArray.length > 30){
      alert("Please select atmost 30 families to edit");
      return;
   }
   totalCategories = 0;
     var jsObj=
	{
		selectedFamilies:impFamiliesEditArray,
		task:"editAllFamilies"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMultipleFamilesInfoAction.action?"+rparam+"&save=";	
	callAjax(jsObj,url);
  }
}
function callAjax(jsObj,url)
		{
			
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task =="editAllFamilies"){
								    if(myResults == "notLogged"){
									 openDialogForLoginWindow();
									}else{
										pResults = myResults;
								     buildVotersInFamily(myResults);
									}
								}

								else if(jsObj.task == "getUserCategories")
								{
									buildCategories(myResults);
								}
								else if(jsObj.task == "getVotersInAFamily")
								{
								    buildVotersInFamily(myResults,jsObj.hno);
									pResults = myResults;
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

function buildVotersInFamily(results){
	  getUserCategories();

	$('.requiredAttrClass').each(function(){
	  $(this).attr('checked','checked');
    });

	$('.notRequiredAttrClass').each(function(){
		$(this).attr('checked',false);
	});

    $("#multipleVoterFamiliesEditDiv").html("");
  
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	  {
		var name = oData;
		var id= oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
					
	  };
     var votersResultColumnDefs = [ 		    	             
		    	            {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"mobileNo",label:"MobileNo",sortable:true}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    
}

function getUserCategories(){

	var jsObj=
			{
			 task:"getUserCategories"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoriesAction.action?"+rparam;						
		callAjax(jsObj,url);

}
function buildCategories(results){

    var str='';

	for(var i in results){
      
       str+='<label style="float:left;margin:3px;"><input type="checkbox" style="margin:0px 7px 4px 0px;" class="attributeTypeClassIni1" value="'+results[i].id+','+results[i].name+'"/>'+results[i].name+'</label>';
	}

	$('#impFamilySelectedDetails1').html(str);

}


function selectAll(id){
	 $('.'+id).each(function() {
            $(this).attr('checked','checked');
        });
}

function deSelectAll(id){
    $('.'+id).each(function() {
     $(this).removeAttr('checked');
	});
}

function getAllVoterFamiliesForEdit(){
 $("#multipleVoterFamiliesEditDiv").hide();
  var impFamiliesEditInfo = new Array();
	 $('.familyMemberCheck').each(function() {
           if($(this).is(':checked')){
		      var voterId = $(this).val();
			  var boothId = $(this).closest("tr").find(".selectedBoothId").val();
		       var obj={
				 boothId:boothId,
				 voterId:voterId
			   }
			   impFamiliesEditInfo.push(obj);
		   }
        });
    if(impFamiliesEditInfo.length > 0){
	     $("#multipleVoterFamiliesEditDiv").html("");
		 if(votersLimitExist){
		    if(impFamiliesEditInfo.length > 30)
			{
			  alert("Please select atomst 30 voters to edit");
			   return;
			}
		  }
		 totalCategories = 0;
		  var jsObj=
		  {
			selectedVoters:impFamiliesEditInfo,
			task:"allFamiliesEditInfo"
		  };
		  $("#getAllVoterFamiliesInfoForEditFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
			  
			  var uploadHandler = {
			
				success: function(o) {
				  $("#multipleVoterFamiliesEditDiv").show();
					var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
					showAllVoterSelectedUpdatedStatus(uploadResult);
				}
			};

		
		  YAHOO.util.Connect.setForm('getAllVoterFamiliesInfoForEditForm',false);
		  YAHOO.util.Connect.asyncRequest('POST','getMultipleFamilesInfoForEditAction.action?save=',uploadHandler);
		/*var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMultipleFamilesInfoForEditAction.action?"+rparam+"&save=";	
		callAjax(jsObj,url);*/
	}
}
function getVotersInAFamily(id,publicationDateId,hNo){

    var jsObj=
			{
					
				hno:hNo,
				id:id,
				publicationDateId:publicationDateId,
				task:"getVotersInAFamily"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "votersFamilyDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);

}

editSelectedFamilies();
if(id != null && $.trim(id) !="" && hno != null && $.trim(hno) !="" && publicationDateId != null && $.trim(publicationDateId) !="")
getVotersInAFamily(id,publicationDateId,hno);
</script>
</body>