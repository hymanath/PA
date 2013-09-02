<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre ${windowTask}</title>

<!-- YUI Dependency files (Start) -->

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
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">


<!-- YUI Dependency files (End) -->


<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>

<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/messageCenter.js"></script>

<script type = "text/javascript">
var accessValue = '${sessionScope.USER.accessValue}';
var accessType = '${sessionScope.USER.accessType}';
var winTask = '${windowTask}';
var isProblemAdding = <%=request.getParameter("addProblem")%>;
var voterId = '${voterId}'; 
function populateLocations(val,source)
{	
	REPORTLEVEL = val;
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");
	var row7El = document.getElementById("row7");
	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s"); 
	var selectedDistrict = $("#districtField_s").val(); 
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var hamletFieldEl = document.getElementById("hamletField_s");		
	var boothFieldEl = document.getElementById("boothField_s");
	var parliamentConstituencyEl = document.getElementById("parliamentConstituencyField_s");
		
 if(source == 'onChange')
{
	if(accessType == 'COUNTRY')
	{
		stateFieldEl.selectedIndex = '0';
		districtFieldEl.selectedIndex = '0';
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
	} else if(accessType == 'STATE')
	{
		districtFieldEl.selectedIndex = '0';
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
	} else if(accessType == 'DISTRICT')
	{
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
		getSubRegionsInDistrict(selectedDistrict,'cadreSearch','constituencyField_s','cadreSearch')
	}else if( accessType == 'MP')
	{
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
		//getSubRegionsInDistrict(selectedDistrict,'cadreSearch','constituencyField_s','cadreSearch')
	} else if(accessType == 'MLA')
	{
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
		getSubRegionsInConstituency(accessValue,'cadreSearch','mandalField_s','cadreSearch')
	}						
} else if(source == "onLoad")
	{
		//setCadreValue(accessValue,'onChange');
		if(val == 9)
		{
			mandalField_sVal = mandalFieldEl.options[mandalFieldEl.selectedIndex].text;
			var flag = mandalField_sVal.search("Greater Municipal Corp");
			if(flag == '-1')
			{
				if(row6El.style.display == 'none')
					row6El.style.display = '';						
			} else {
				if(row5El.style.display == 'none')
					row5El.style.display = '';
				if(row6El.style.display == 'none')
					row6El.style.display = '';
			}
		}
	}	
	
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	row6El.style.display = 'none';	
	row7El.style.display == 'none';
	var value = val;
	if(value == 1)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';	
		    row7El.style.display = 'none';
		
	} else if(value == 2)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		   row7El.style.display = 'none';
	} else if(value == 3)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		    row7El.style.display = 'none';
	} else if(value == 4)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		    row7El.style.display = 'none';
	} else if(value == 5)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		    row7El.style.display = 'none';
	} else if(value == 6)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';
		    row7El.style.display = 'none';
	} else if(value == 7)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		    row7El.style.display = 'none';
	} else if(value == 8)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';
		    row7El.style.display = 'none';
	} else if(value == 9)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row6El.style.display == 'none')
			row6El.style.display = '';
		    row7El.style.display = 'none';
	}
	else if(value == 10)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row7El.style.display == 'none')
			row7El.style.display = '';
	}
	if( accessType == 'MP'){
	  row2El.style.display = 'none';
	}
			 
}

function getCadreId(selectEle)
{
	if(selectEle.checked == true && isProblemAdding != null && isProblemAdding == true)
	{
		deSelectCheckBox();
		selectEle.checked = true;
	}
	else
	  return;
}

function setCadreIdToProblem()
{
	var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("addSelectedCadreErrorMsg");
    var cid = null;
	var cMobile = null;
	var cName = null;

	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		
		cid  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		cMobile = elements[i].value.substring(elements[i].value.indexOf('_')+1,elements[i].value.lastIndexOf('_'));
		cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
	}
	if(cid == null)
	{
		errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Cadre to Add the Problem</font>';
		return;
	}
	else if(cid != null)
	{
		errorSpanElmt.innerHTML = '';
		window.opener.setSelectedCadre(cid,cName);
		window.close();
	}
 }
function getUpdatedData(){
	getCadresResults('search');
}
</script>
<style>
.btnClass
{
	background-image:url(images/icons/indexPage/swasthic_body.png);
	background-repeat:repeat-x;
	border:1px solid #ADADAD;
	color:#244565;
	font-weight:bold;
	padding:5px;
	text-decoration:none;
}

.yui-skin-sam 
{
	font-weight:bold;
}
.yui-skin-sam .yui-dt th
{
	background-image:url(images/YUI-images/sprite.png)
}

#yui-dt0-th-Categorize
{
	background-color:blue;
}
#searchResult table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 5px;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 5px;
    text-align: left;
	color:#333333;
	border:none;
	}
	 #searchResult table td{padding:2px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;border:none;}
	#searchResult table tr:nth-child(even){background:#EdF5FF;}
	#searchResult table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
</style>
<script>
function addNewCandidate(){
var urlStr = "cadreRegisterPageAction.action?cadreId=0&windowTask=new";
window.open(urlStr,"addNewCadrePeople","scrollbars=yes,height=570,width=1300,left=300,top=50").focus();	
}
</script>
</head>
<body>


 <h2 style="text-align:center;color:#06ABEA;">CADRE SEARCH RESULTS TO SEND SMS</h2>


<div><span id="peopleCount" style="font-size:13px;font-weight:bold;color:#0082A3;margin-left: 50px;"></span></div>


<div style="float:right;">
<input type="button" class="btnClass" onClick="addNewCandidate();" value="Click Here To Add Cadre"/>
<input type="button" class="btnClass" onClick="closeWindow();" value="Click here to close the window"/>

</div>
	<div id="smsDialogBox" class="yui-skin-sam"></div>

	<div id="searchResultsDiv_main" class="yui-skin-sam">

		<span id="smsStatusTextSpan1"></span>
	
		<div id="resultsCount" style="margin-left:30px;color:#707070;font-weight:bold;font-size:13px;text-align:center;"></div>
		<div id="searchResultsDiv_body" style="margin-top: 50px;">
			<div id="smsResult"></div>
			<div id="searchResult"></div>
		</div>		
		
		<div id="searchResultsDiv_footer" style="text-align:center;"></div>
		<div id="cadreProblemSelectErrorDiv" style="text-align:center;"><span id="addSelectedCadreErrorMsg"></span></div>
		<div id="cadreProblemSelectDiv" style="text-align:center;"></div>
	</div>

	<script type="text/javascript">	
		expandFilterOptions();

	$('document').ready(function()
	{
       
	    $('.cadre').live("change",function(){

			if($(this).is(':checked'))
                   window.opener.document.getElementById("cadreCount").innerHTML = parseInt(window.opener.document.getElementById("cadreCount").innerHTML) +1;
			else{
				window.opener.document.getElementById("cadreCount").innerHTML = parseInt(window.opener.document.getElementById("cadreCount").innerHTML) -1;
				$('.selectAllCadre').attr('checked',false);
			}
		});

      $('.selectAllCadre').live("change",function(){
		if($(this).is(':checked')){
			$('.cadre').attr('checked','true');

			$('.cadre').each(function(index,value){
				var cadreId = $(this).val().split("-")[0];
				var mobileNumber = $(this).val().split("-")[1];

			   if(window.opener.selectedCadreDetails[cadreId] == undefined){
				        window.opener.selectedCadreDetails[cadreId] = mobileNumber;
				window.opener.document.getElementById("cadreCount").innerHTML = parseInt(window.opener.document.getElementById("cadreCount").innerHTML) +1;
			   }


			   if($.inArray(mobileNumber, window.opener.selectedMobileNumbers) == -1)
                       window.opener.selectedMobileNumbers.push(mobileNumber);

			});

		}	
	  });

       getCadresResults2('search');

		$(".toggleDiv").click(function(){
			$(".toggleDiv").hide();
			$(".toggleDiv1").show();
			$(".fullSaintDesc").toggle();
		  return false;
		});  
		
		$(".toggleDiv1").click(function(){
			$(".toggleDiv1").hide();
			$(".toggleDiv").show();
			$(".fullSaintDesc").toggle();
		 return false;
		});  
			 
		$('.removePictureLink').live("click",function(){
			var isVerified = confirm("Are you want to remove Cadre Image?");
			if(isVerified){
			   var cadreID = $(this).attr("id");
				var jsobj = {			
				cadreId:cadreID,
				task:"removeCadreImage"				
			 };
			  var rparam = "task="+YAHOO.lang.JSON.stringify(jsobj);
			  var url = "removeCadreImageAction.action?"+rparam;	 
			  callAjaxForRegionSelect(jsobj,url);				
			}				
		 });
	});

		 <%
		
		ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
		String exceptionMsg = rb.getString("exceptionMsg");

		%>

		errorMsglabel = '<%=exceptionMsg%>';
						

		CLICKTYPE = '${windowTask}';
		partyHasCommitees = '${isPartyCommitee}';
		partyHasCamps = '${isTrainingCamps}';
		partyHasSkills = '${isCadreSkills}';
		
        
		<c:forEach var="social" items="${socialStatus}">
			var obj = {
			           	id:'${social.id}',
						name:'${social.name}'
					  };
					socialStatus.push(obj);
		</c:forEach>
		<c:forEach var="edu" items="${eduStatus}">
			var obj = {
			           	id:'${edu.id}',
						name:'${edu.name}'
					  };
					eduStatus.push(obj);
		</c:forEach>
		<c:forEach var="committe" items="${partyCommitteesList}">
			var obj = {
			           	id:'${committe.id}',
						name:'${committe.name}'
					  };
					partyCommitte.push(obj);
		</c:forEach>
		<c:forEach var="skill" items="${cadreSkillsList}">
			var obj = {
			           	id:'${skill.id}',
						name:'${skill.name}'
					  };
					cadreSkills.push(obj);
		</c:forEach>
		<c:forEach var="camps" items="${partyTrainingCampsList}">
			var obj = {
			           	id:'${camps.id}',
						name:'${camps.name}'
					  };
					partyTrainingCamps.push(obj);
		</c:forEach>
		<c:forEach var="occupation" items="${occupationsList}">
			var obj = {
			           	id:'${occupation.id}',
						name:'${occupation.name}'
					  };
					occupations.push(obj);
		</c:forEach>
				
		buildselectBoxes();
		getLocationWiseRangeDetails();

 var locationId = '${locationValue}';
 var locationType = '${locationType}';

<!--  cadre search  ---> 

function getCadresResults2(btnType)
{
    var genderType="";
	if(window.opener.selectedCriteria.gender == "All")
		genderType = "allGenders";
	else if(window.opener.selectedCriteria.gender == "M")
		genderType = "Male";
	else if(window.opener.selectedCriteria.gender == "F")
		genderType = "Female";

var locationValue;
	if(window.opener.selectedCriteria.cadreLocationId == 5)
		 locationValue =  window.opener.selectedCriteria.cadreReportLevelValue;
	else
		locationValue = window.opener.selectedCriteria.reportLevelValue

var jsObj=
		{	extra				    :"one",
			reportLevel				: window.opener.selectedCriteria.cadreLocationId,
			reportLocationValue		: locationValue,
			socialStatus			: window.opener.selectedCriteria.socialStatus,
			socialStatusArray		: [],
			cadreType				: "all",
			cadreName				: window.opener.selectedCriteria.searchName,
			searchType				: "location",
			gender					: genderType,
			searchCriteria			: "all",
			searchCriteriaArray		: [],
			searchCriteriaValue		: "0",
			performSearch			: "and",
			txtAreaValue			: "",
			includeCadreName		: "NO",
			bloodGroupId			: "0",
			taskType				: "search",
			senderName				: "",
			nameSearchTYpe			: "StartingWith", 
			cadreRegTypeRadioValue	: "allCadres",	
			task					: "cadreSearch",
		    memberShipNo            :"" ,
	
		}
		
	
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);

	if(btnType == "search")
	{
		var search = "forTotalCount";
		var url = "getCadreDetailsForSMSAjaxAction.action?"+rparam1+"&windowTask=Search&sort=total&startIndex=0&results=-5";
		callAjaxForRegionSelect(jsObj,url);
		buildCadreSearchResultDataTableForSMS(rparam1);
	}
}

var count=0;

function buildCadreSearchResultDataTableForSMS(rparam)
{
  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
  {
	var name = oData;
	var id= oRecord.getData("cadreId");
	var mobile= oRecord.getData("mobile");
	var firstName= oRecord.getData("firstName");
  if(window.opener.selectedCadreDetails[id] == undefined || mobile == "")
      elLiner.innerHTML='<input type="checkbox"  onClick="pushIntoCadreObject(\''+id+'\',\''+mobile+'\')" name="cadreResult_check" class="cadre"  value="'+id+'-'+mobile+'">';
  else
     elLiner.innerHTML='<input type="checkbox" checked onClick="pushIntoCadreObject(\''+id+'\',\''+mobile+'\')" name="cadreResult_check" class="cadre" value="'+id+'-'+mobile+'">';	
				
  };
  
  YAHOO.widget.DataTable.fullName = function(elLiner, oRecord, oColumn, oData) 
  {
	var name = oData;
	var id= oRecord.getData("cadreId");
	var mobile= oRecord.getData("mobile");
	var firstName= oRecord.getData("firstName");
	var lastName= oRecord.getData("lastName");
	
  if(window.opener.selectedCadreDetails[id] == undefined || mobile == "")
      elLiner.innerHTML=firstName+' '+lastName;
  else
     elLiner.innerHTML=firstName+' '+lastName;	
				
  };
  
  var CadreSearchResultColumnDefs = [ 
		    	            {key:"select", label: "<input type='checkbox' class='selectAllCadre'/>", formatter:YAHOO.widget.DataTable.select},
							{key:"firstName", label: "Name",sortable: true, formatter:YAHOO.widget.DataTable.fullName} ,
		    	            {key:"mobile", label: "Mobile", sortable: true}, 
		    	           	{key:"strCadreLevel", label: "Cadre Level", sortable: true},
							/*{key:"educationStr", label: "Education",sortable:true},
		    				{key:"professionStr", label: "Occupation",sortable:true},*/
                            {key:"casteCategoryStr", label: "Caste ",sortable:true}
		    	        ]; 
	var CadreSearchResultDataSource = new YAHOO.util.DataSource("getCadreDetailsForSMSAjaxAction.action?"+rparam+"&windowTask="+winTask+"&"); 
	CadreSearchResultDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	
	CadreSearchResultDataSource.responseSchema = { 
            resultsList:"cadreInfo", 
		fields: [
				{key:"firstName"},
				"lastName","image","mobile", "strCadreLevel","memberType",
				"educationStr","professionStr","casteCategoryStr","cadreId","email","pincode"
				],
		metaFields: {
			totalRecords: "totalSearchCount" // Access to value in the server response
		}         
        };


    var myConfigs = {
			        initialRequest: "sort=firstName&dir=asc&startIndex=0&results=100", // Initial request for first page of data
			        dynamicData: true, // Enables dynamic server-driven data
			        sortedBy : {key:"firstName", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
			        paginator: new YAHOO.widget.Paginator({ rowsPerPage:100 }) // Enables pagination 
		};

		var CadreSearchResultDataTable = new YAHOO.widget.DataTable("searchResult", CadreSearchResultColumnDefs,CadreSearchResultDataSource, myConfigs);

		CadreSearchResultDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) 
		{	
			if(oResponse.meta.totalRecords == 0)
			{		

				$('#peopleCount').html("<font style='color:red;font-weight:bold;'>No records found matching your search criteria. Changing the search criteria might help you.</font>");
			}else{
		        oPayload.totalRecords = oResponse.meta.totalRecords;
				$('#peopleCount').html("Total Count : <span>"+oResponse.meta.totalRecords +"</span>");
			}
		        return oPayload;
		}
		//function calling to build Result
		return {
			oDS: CadreSearchResultDataSource,
			oDT: CadreSearchResultDataTable,
    	};
	}

function getCadreInfo(cadreId)
{
	var urlStr = "getCadreInfoAction.action?windowTask=cadreInfoPopup&cadreId="+cadreId;
	var cadreViewBrowser = window.open(urlStr,"cadreInfoPopup","scrollbars=yes,height=600,width=600,left=200,top=50");	
	cadreViewBrowser.focus();
}

function openRegistrationForm(cadreId)
{
	var task = "update_existing";
	var urlStr = "cadreRegisterPageAction.action?cadreId="+cadreId+"&windowTask="+task;
	var updateBrowser = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
	updateBrowser.focus();				
}
/*
function showCadreSearchResults(searchCount)
 {
	var totalSearchCount = searchCount;
	var headElmt = document.getElementById("searchResultsDiv_head");
	var bodySearchElmt = document.getElementById("searchResult");
	var footerElmt = document.getElementById("searchResultsDiv_footer");
	var resultsCountEl = document.getElementById("resultsCount");
	
	
	$('#msgCenterId').css('display','block');
	
	
	if(!headElmt || !bodySearchElmt || !footerElmt)
		return;
	
	bodySearchElmt.style.display = 'block';
	resultsCountEl.innerHTML ='';
	headElmt.innerHTML = 'Search Results';
		
	if(totalSearchCount == 0)
	{
		bodySearchElmt.innerHTML = '<div style="color:#C0566F;font-size:12px;">No Search results found.</div>';
		footerElmt.style.display = 'none';
		return;
		
	} 
	else 
	{
	resultsCountEl.innerHTML = '<span>'+totalSearchCount+'</span> cadres found with this selection criteria';

	var fStr = '';
	
	fStr += '<span id="smsStatusTextSpan"></span>';
	footerElmt.innerHTML = fStr;
	footerElmt.style.display = '';
	}
	
	if(isProblemAdding != null && isProblemAdding == true)
	{
		footerElmt.style.display = 'none';
		
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setCadreIdToProblem()" value="Add Selected cadre"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
	if(isProblemAdding != null && isProblemAdding == false)
	{
		footerElmt.style.display = 'none';
		
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setOrganizers()" value="Add Organizers"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
	if(voterId !== ''){
	   footerElmt.style.display = 'none';
		
		var cadreProbDivEle = document.getElementById("cadreProblemSelectDiv");
		var cadreProbStr = '';
		cadreProbStr += '<span><input type="button" class="btnClass" onclick="setCadreToVoter()" value="Add Selected cadre"/><BR><BR></span>';
		cadreProbDivEle.innerHTML = cadreProbStr;
	}
}*/
/*
function setOrganizers(){
   var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("addSelectedCadreErrorMsg");
	var candidates = new Array();
    var cid = null;
	var cName = null;

	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		
		cid  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
	   var obj1 = {};
	   obj1["id"] = cid;
	   obj1["name"] = cName;
	   candidates.push(obj1);
	}
	if(cid == null)
	{
		errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Cadre to Add As Organiser</font>';
		return;
	}
	else if(cid != null)
	{
		errorSpanElmt.innerHTML = '';
		window.opener.setSelectedOrganizers(candidates);
		window.close();
	}

}*/
/*
function setCadreToVoter(){
   var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("addSelectedCadreErrorMsg");
    var cid = null;
	var cName = null;
	var cCount = 0;

	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		cCount = cCount+1;
		cid  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
	}
	if(cCount == 0)
	{
		errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Cadre</font>';
		return;
	}else if(cCount > 1){
	    errorSpanElmt.innerHTML = '<font style="color:red;">Please Select Only One Cadre</font>';
		return;
	}
	else if(cid != null && cCount == 1)
	{
		errorSpanElmt.innerHTML = '';
		window.opener.mapSelectedCadreToVoter(cid,cName,voterId);
		window.close();
	}
}*/
<!--  cadre search  --- end> 
function pushIntoCadreObject(cadreId , mobileNumber)
{
	if(window.opener.selectedCadreDetails[cadreId] == undefined)
	{
      window.opener.selectedCadreDetails[cadreId] = mobileNumber;
	    window.opener.selectedMobileNumbers.push("91"+mobileNumber);	
	}
	else
	{

		delete window.opener.selectedCadreDetails[cadreId];
		var index = window.opener.selectedMobileNumbers.indexOf("91"+mobileNumber);
         window.opener.selectedMobileNumbers.splice(index, 1);
	}
}

function closeWindow()
{
	window.close();
}
</script>
</body>
</html>