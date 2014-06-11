<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Field Voters Search</title>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
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
			<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
 
 <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.9.0/build/tabview/assets/skins/sam/tabview.css"> 
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
<style type="text/css">

.labelClass{
float:left;
margin:3px 7px 0px 0px;
}
 .selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-family: verdana;
	 font-size: 12px;
	 margin-left:auto;
	 margin-right:auto;
	 font-weight:bold;
	 color:#333333;
 }
 .titleHeading{
    color: steelblue;
    font-family: verdana;
    font-size: 13px;
    font-weight: bold;
    margin-bottom: 20px;
	font-size: 16px;
	text-align:center;
	margin-top: 20px;
 }
 #votersBySearchTabContentDiv_body table th ,#impFamDtls table th{
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    text-align: left;
   }
   #votersBySearchTabContentDiv_body table td ,#impFamDtls table td{
    color: #676A67;
    font: small-caption;
  }	
  #votersBySearchTabContentDiv_body{
    border: 1px solid black;
    margin-left: auto;
    margin-right: auto;
    width: 974px;
	overflow-x: scroll;
	display:none;
  }
  fieldset{
    border: 3px solid #CFD6DF;
    margin-bottom: 10px;
    padding: 10px;
}
body {
    color: #000000;
}
 #multipleVoterFamiliesEditDiv{
    font-family: Verdana,Arial,sans-serif;
    font-size: 1.1em;
 }
 #topButtons,#bottomButtons{
   margin-top:10px;
 }
 #impFamDtlsTitle,#categoriesDiv{
    color: steelblue;
    font-family: verdana;
    font-size: 13px;
    font-weight: bold;
}
#categoriesDiv{
  margin-top:10px;
  margin-bottom:10px;
  margin-left:20px;
}
  #topCount,#bottomCount{
    font-size: 15px;
    margin-left: 20px;
   }
   .requiredFont{
	color:red;
	font-size:13px;
}
#mainFieldset{
 margin:20px;
}
 #bottomSelCount,#topSelCount{ 
	font-size: 14px;
    font-weight: bold;
    margin-left: 10px;
}
#votersBySearchTabContentDiv_body table{
  width: 100%;
}
.dd_menu {padding:0px; margin:0; list-style-type:none;}
.dd_menu li {float:left;display:inline;}
.dd_menu li a {padding:0px 20px; display:block; color:#fff; text-decoration:none; font:12px arial, verdana, sans-serif; font-weight: bold;}
.dd_menu li:hover a {text-decoration:underline;}

.dd_menu ul {position:absolute; left:-9999px; top:-9999px; list-style-type:none;}
.dd_menu li:hover {position:relative; background:none;}
.dd_menu li:hover ul {left:-160px; top:15px; padding:3px; border:1px solid grey; width:208px;}
.dd_menu li:hover ul li {border:none;}
.dd_menu li:hover ul li a {height:18px;color:#003366; padding:5px 0px; display:block; font-size:11px; width:208px; line-height:18px; text-indent:5px; color:#444; background:#d0e0ea; text-decoration:none; border:1px solid transparent;}
.dd_menu li:hover ul li a:hover {height:18px; background:#c4d8e6; color:#003366; border:solid 1px #444;}



#voterEditDetailsShowDIV table td{ padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#voterEditDetailsShowDIV table td{padding-right:3px !important;}
#voterEditDetailsShowDIV table th{
				padding:5px !important;

			}
#voterEditDetailsShowDIV{
		font-family : arial;
		font-size: 13px;
		margin-top: 20px;
		padding: 10px 10px 10px 15px;
		
		}
#voterEditDetailsShowDIV table tr:nth-child(even){background:#EdF5FF;}

		#voterDetailsDiv table th a{
			color:#333333;

		}
	#voterEditDetailsShowDIV table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}
	#voterEditDetailsShowDIV table{border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;
	width:100%;background:#EdF5FF;padding:8px;padding-left:10px;font-weight:normal;
	font:small-caption;color: #676A67;}

	input[type="text"]{
		border-radius: 4px 4px 4px 4px;
		color: #000000;
		display: inline-block;
		font-size: 13px;
		line-height: 18px;
		padding: 4px;
	}
		#voterDetailsJqTable{
		border: 1px lightBlue solid ;
		width: 120px; 
		height: auto;
		margin-left: 151px;
		margin-bottom: 15px;
		background-color:white;
		font-weight:bold;
		border-radius: 5px 5px 5px 5px;
		}	
		.textClass{
			width: 100px; 
			height: 11px;
		
		}

		.textClass1{
			width: 44px; 
			height: 11px;
		
		}
		#requiredFieldsToCheck input {margin-right: 2px;}
</style>

<script type="text/javascript">


	
function getCategoriesForAUser(){
    var jsObj=
			{
				task:"getCategoriesForAUser"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInfoBySearchAction.action?task=categories&save=";						
		callAjaxForCandSearch(jsObj,url);

}



	function clearFieldsData(){
		$("#errorMsgAlert").html("");
		var mandalId=document.getElementById("mandalField");
		var publicationDateId=document.getElementById("publicationDateList");
		var panchayatFieldId=document.getElementById("panchayatField");
		var wardFieldId=document.getElementById("wardField");
		var hamletFieldId=document.getElementById("hamletField");
		var pollingStationFieldId=document.getElementById("pollingStationField");
		removeSelectElements(pollingStationFieldId);
		removeSelectElements(hamletFieldId);
		removeSelectElements(mandalId);
		removeSelectElements(wardFieldId);
		removeSelectElements(publicationDateId);
		removeSelectElements(panchayatFieldId);
		$('#constituencyList').val(0);
	}
	function clearErrDiv(){
	$("#errorMsgAlert").html("");
	}


	
</script>
</head>
<body>
<div class="container">
<div class="titleHeading">Field VOTERS SEARCH</div>
    <fieldset id="mainFieldset">
      <div id="AlertMsg" style="font-family: verdana;font-size: 13px;color:red;"></div>
	  <div id="errorMsgAlert" style="font-family: verdana;font-size:14px;color:red;margin-left:100px;height: 40px;"></div>
       <div id="ConstituencyDiv" class="selectDiv">
	    
		 
		 Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;width:165px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="clearErrDiv(),getMandalOrMuncipalityList();getPublicationDate();"/>
		 
		 &nbsp;&nbsp;	
	     Select Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:180px;" name="publicationDateList" >
		</select>  <span style='display:none;float: right;' id='ajaxLoad'><img src='./images/icons/search.gif' /></span>		
	  </div>
	

	<div id="mandalDiv" class="selectDiv" style="display:block;">
	     <span id="mandalSpan">Select Mandal</span><font class="requiredFont">*</font>
		 <select id="mandalField" class="selectWidth" name="state" onchange="clearErrDiv();getBoothsforFieldVoters('pollingstationByPublication','pollingStationField');" style="margin-left:60px;width:165px;"></select>
	  </div>
	   
	  <div id="pollingStationDiv" class="selectDiv" style="display:block;">
	    Select Booth <font class="requiredFont">*</font><select id="pollingStationField" class="selectWidth" name="state"  style="margin-left:68px;width:165px;" onchange="clearErrDiv();"></select>
	  </div>
	
	 
	  

	
	  <div class="selectDiv">
	   Serial No Between <input style="width:67px;margin-left:38px;" type="text" id="fromSno" /> <input style="width:67px;" type="text" id="toSno" />
	  </div>
   
	  <div class="selectDiv">
	   <table style="width:100%"><tr>
	     <td style="width:132px;"><span>Check Required Fields To Show</span></td>
	     <td><span id="requiredFieldsToCheck"></span></td>
	   </tr></table>
	  </div>
	  
	  <input style="margin-left:240px;margin-bottom:10px;"  class="btn btn-success" type="button" id="searchbtnId" value="Search"/>

	  <div id="noteDiv" style="float: right;"></div>
	   </fieldset>

	</div>

<script type="text/javascript">
 buildCategoriesListInit();
  //getCategoriesForAUserInital();
function buildCategoriesListInit(){
	  var str ='';
	    str+='<table style="margin-left:5px;"><tr>';
		str+='   <td style=""><input type="checkbox" id="mandalNameColum" />  mandal Name&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="boothColum" />  booth&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="voterIdColum" />  voterId&nbsp;&nbsp;</td>';
		
		
		str+='</tr>';
		str+='<tr>';
		str+='   <td style=""><input type="checkbox" id="voterNameColum" />  Voter Name&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="ageCol" />  Age&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="genderColum" />  Gender&nbsp;&nbsp;</td>';
		
		
		str+='</tr>';
		str+='<tr>';
		str+='   <td style=""><input type="checkbox" id="relativeNameColum" />  relative Name&nbsp;&nbsp;</td>';
		
		str+='   <td style=""><input type="checkbox" id="relationshipColum" />  relationship&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="mobileNoColum" />  mobile No&nbsp;&nbsp;</td>';
		str+='</tr>';
		str+='<tr>';
		str+='   <td style=""><input type="checkbox" id="casteCol" />  caste&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="hamletColum" />  hamlet&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="cadreColum" />  cadre&nbsp;&nbsp;</td>';
		
		
		
		str+='</tr>';
		str+='<tr>';
		str+='   <td style=""><input type="checkbox" id="localityColum" />  Locality&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="influencingPeopleColum" />  Influencing People&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="dataCollectorCol" />  Data Collector&nbsp;&nbsp;</td>';
		str+='</tr>';
		str+='<tr>';
		str+='   <td style=""><input type="checkbox" id="verifierColum" />  verifier&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="verifiedCasteColum" />  verified Caste&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="verifiedHamletColum" />  verified Hamlet&nbsp;&nbsp;</td>';
		str+='</tr>';
		str+='<tr>';
		str+='   <td style=""><input type="checkbox" id="verifiedMobileNoColum" />  verified MobileNo&nbsp;&nbsp;</td>';
		str+='   <td style=""><input type="checkbox" id="verifiedLocalityCol" /> verified Locality&nbsp;&nbsp;</td>';
	    str+='</tr></table>';
	    $("#requiredFieldsToCheck").html(str);
	  }

</script>
<script>

function getMandalOrMuncipalityList()
{
    var tempVar = "mandalList";
    var selectElmt = "mandalField";
    var reportLevelValue = $("#reportLevel").val();
	var constituencyId = $("#constituencyList").val(); 

	if(reportLevelValue == 5)
     tempVar = "muncipalityList";
			
		var jsObj=
			{
				constituencyId:constituencyId,
				tempVar:tempVar,
				selectElmt:selectElmt,
				task:"getMandalOrMuncipalityList"
					
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMandalOrMuncipalityListForVotersAnalysisAction.action?"+rparam;						
		callAjax(jsObj,url);
		
}
 
function getBoothsforFieldVoters(checkedele,selectedEle)
	{
		
		
		var mandalId=document.getElementById("mandalField");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;
		var type = "mandal";
		if(value1.charAt(0) =="1"){
		 type = "muncipality";
		}
		var value = value1.substring(1);
		var publicationValue = $('#publicationDateList').val();
		var alertEl = document.getElementById("AlertMsg");
		alertEl.innerHTML = '';
		var selectname = mandalId.options[mandalId.selectedIndex].text;
		var flag= selectname.search("MUNCIPALITY");
		 if(value1 == 0)
		{
			alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
	
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				flag:flag,
				type:type,
				constituencyId:$("#constituencyList").val(),
				publicationValue : publicationValue,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
	
	}
	
</script>

</body>
</html>