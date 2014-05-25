<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAHANADU</title>
 <script type="text/javascript" src="js/LocationHierarchy/locationHierarchyMahaNadu.js"></script>
	<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
	<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<link type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet" />
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
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->
<link href="styles/mahanadu/bootstrap.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
  <style>
  .yui-skin-sam.yui-dt table th {
		background-color: #CDE6FC;
		font-size: 13px;
		font-weight: bold;
		padding: 7px;
		text-align: left;
		border-collapse :collapse;
}
.yui-skin-sam.yui-dt table  {
		
		border-collapse :collapse;
}
.yui-skin-sam.yui-dt tbody{
border: 1px solid #CDCDCD;
}
 .yui-skin-sam.yui-dt td {
	font-weight: normal;
	padding: 8px 8px 8px 10px;
}
#buildDataTable table tr:nth-child(2n){
background: none repeat scroll 0 0 #F9F9F9;
}


.yui-dt-sortable{
	color:#000;
}
.yui-dt-sortable>td{
border:1px solid #000000;
}
		body{color:#333333!important;}
		input{height:40px !important;box-shadow: 1px 0 5px rgba(0, 0, 0, 0.28) inset!important; border-radius:10px!important;border:1px solid #ccc!important;}
		select{height:40px; box-shadow: 1px 0 5px rgba(0, 0, 0, 0.28) inset!important;border-radius:10px!important;border:1px solid #ccc!important;}		
		label{font-size:17px;}
		.well-small{background:transparent!important;box-shadow: 1px 0 3px rgba(0, 0, 0, 0.41);}
		.container{margin-bottom:20px;}
		.text-error{color:red!important;}
		.text-center{color:#000000!important; }
		.mahanadubg{background:#fff url(images/mahaNadu/Mahanadu_bg.gif) repeat-x;}
		.m_top10{margin-top:10px;}
		.m_top15{margin-top:15px;}
		.m_top20{margin-top:20px;}
		.containerBorder{border: 1px solid rgb(204, 204, 204); padding:10px;}
		.MahanaduHeading{height:42px; width:100%; border-bottom:1px solid rgba(0,0,0,.5);border-radius:6px 6px 0px 0px; text-transform:uppercase;line-height:45px;
			background: #ffe933;
			background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZTkzMyIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmNmMzEiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
			background: -moz-linear-gradient(top,  #ffe933 0%, #ffcf31 100%);
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffe933), color-stop(100%,#ffcf31));
			background: -webkit-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: -o-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: -ms-linear-gradient(top,  #ffe933 0%,#ffcf31 100%);
			background: linear-gradient(to bottom,  #ffe933 0%,#ffcf31 100%);
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffe933', endColorstr='#ffcf31',GradientType=0 );
		}
		.multySelect{border: 1px solid rgb(204, 204, 204); border-radius: 4px; background: none repeat scroll 0% 0% rgb(255, 255, 255); padding: 5px 10px; height: 100px; overflow: auto;box-shadow: 1px 0 5px rgba(0, 0, 0, 0.28) inset!important; border-radius:10px!important;}
		.multySelect ul li{height:26px;}
		
		.ui-multiselect{
		  height:40px;
		}
		.ui-multiselect-filter input[type="search"] { width: 130px;}
		#ui-datepicker-div{
		  display:none;
		}
		
		#searchResultsDiv_body table {
    width: 100%;
}
.yui-skin-sam .yui-dt table {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-bottom-color: #7F7F7F;
    border-bottom-style: solid;
    border-bottom-width: 1px;
    border-collapse: separate;
    border-image-outset: 0 0 0 0;
    border-image-repeat: stretch stretch;
    border-image-slice: 100% 100% 100% 100%;
    border-image-source: none;
    border-image-width: 1 1 1 1;
    border-left-color-ltr-source: physical;
    border-left-color-rtl-source: physical;
    border-left-color-value: #7F7F7F;
    border-left-style-ltr-source: physical;
    border-left-style-rtl-source: physical;
    border-left-style-value: solid;
    border-left-width-ltr-source: physical;
    border-left-width-rtl-source: physical;
    border-left-width-value: 1px;
    border-right-color-ltr-source: physical;
    border-right-color-rtl-source: physical;
    border-right-color-value: #7F7F7F;
    border-right-style-ltr-source: physical;
    border-right-style-rtl-source: physical;
    border-right-style-value: solid;
    border-right-width-ltr-source: physical;
    border-right-width-rtl-source: physical;
    border-right-width-value: 1px;
    border-spacing: 0;
    border-top-color: #7F7F7F;
    border-top-style: solid;
    border-top-width: 1px;
    font-family: arial;
    font-size: inherit;
    margin-bottom: 0;
    margin-left: 0;
    margin-right: 0;
    margin-top: 0;
    padding-bottom: 0;
    padding-left: 0;
    padding-right: 0;
    padding-top: 0;
}

	</style>
</head>
<body  class="mahanadubg">
 <form id="uploadCadreForm" method="post" enctype="multipart/form-data" action="uploadCadreForMahanadu.action" name="uploadCadreForm">
    <div class="container">	
		<!----Header Div--------->
		<div class="row">
			<div class="span12 text-center">
				<a href="#" title="Telugu Desam Mahanadu 2014" >
					<img src="images/mahaNadu/MahanaduLogo.png" alt="Telugu Desam Mahanadu 2014" />
				</a>
			</div>
		</div><!----Header Div End--------->
		
		<!----Mahanadu Banner Div--------->
		<div class="row">
			<div class="span12 containerBorder m_top10">
				<img src="images/mahaNadu/MahanaduBanner.png" title="Telugu Desam Mahanadu 2014" alt="Telugu Desam Mahanadu 2014" />
			</div>
		</div><!----Mahanadu Banner Div End--------->
		
		<!----Form Div--------->
		<div class="row">
			<div class="span12 containerBorder">
				<p> * Please Submit First Name, Last Name and Mobile No to Generate Party Cadre Details</p>
				<div class="well well-small mahanadu-well form-inline">
					
				 <label>Constituency <span class="text-error"><span class="text-error">* </span> </span></label>             
					
					<s:select id="constitList"  list="constituencyList" listKey="id" listValue="name"  ></s:select>
							
					<span id="errodiv" style="color:red;"> </span>		
					<br><br>
				  <label>First Name<span class="text-error"><span class="text-error"> </span> </span></label>             
					<input class="input-medium searchCls" type="text" id="firstName">      
				
				  <label>&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp Last Name<span class="text-error"> </span></label>              
					<input class="input-medium searchCls" type="text" id="lastName">
					
				  <label >&nbsp&nbsp&nbsp&nbsp&nbsp Mobile No<span class="text-error"> </span></label>              
					<input class="input-medium searchCls" type="text" id="mobileId">
					
				  &nbsp&nbsp&nbsp
				  <button type="button" class="btn btn-success" onclick="searchCadre();">&nbsp Submit &nbsp</button>				 
				</div>
				
				<!----Heading Div------->
				<div class="text-center MahanaduHeading"><h3>Party Cadre</h3></div><!----Heading Div END------->
				

				<div id="errorMsgDiv"></div>
				<!---- Personal Details ------>
				<h3>Personal Details</h3>
				<p class="text-right" style="margin-top:-40px;">Fields marked with <span class="text-error">* </span> are mandatory</p>
				<div class="well well-small mahanadu-well form-inline">				
					<div class="row-fluid">
						<div class="span5">	
							<label>First Name<span class="text-error">* </span>&nbsp&nbsp&nbsp</label>             
							<s:textfield  type="text" name="cadreVo.firstName"  id="firstNameId" />
							
							<label class="m_top20">Last Name<span class="text-error">* </span> &nbsp&nbsp&nbsp</label>             
							<input type="text" name="cadreVo.lastName"  id="lastNameId">  
							
							<div class="row-fluid m_top20">
								<div class="span3">									
									<label>Gender <span class="text-error">* </span></label>
								</div>
								<div class="span3">
									<label style="margin-top: -10px;"><input type="radio" checked="checked" value="Male" id="optionsRadios" name="cadreVo.gender">
									Male </label>
								</div>
                                <div class="span4 form-inline">
									<label style="margin-top: -10px;">
									  <input type="radio" value="Female" id="optionsRadios1" name="cadreVo.gender">
									  Female
									</label>
								</div>  	
							</div>
							<label>Father Name &nbsp;</label>             
							<input type="text" name="cadreVo.fatherName">  
						</div>
						<div class="span5">	
							<label>Blood Group &nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp</label>             
							<s:select id="bloodGroupId" cssClass="regionSelect input-medium" name="cadreVo.bloodGroupId" list="#session.bloodGroups" listKey="id" listValue="name" headerKey="0" headerValue="Select Group" ></s:select>
							<br/><label class="m_top20">Age&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp &nbsp&nbsp&nbsp	 </label>             
							<input type="text" class="input-medium" name="cadreVo.age">  
							
							<br/><label class="m_top20">No of Family Members &nbsp</label>             
							<input type="text" class="input-medium" name="cadreVo.noOfFamilyMembers"> 
							
							<br>	<label class="m_top20">No of Voters &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></label>             
							<input type="text" class="input-medium" name="cadreVo.noOfVoters"> 
						</div>
						<div class="span2">	
							<a class="thumbnail" href="#">
								<span id="uploadImg"><img  style="width: 140px; height: 120px;" id="" src="images/mahaNadu/user image.jpg"></span>
							</a>
							<input type="file" style="width: 225px;" id="uploadFileId" onchange="changeImg();" name="uploadImage" class="m_top10">	
						</div>	
					</div>
				</div>
				
				<!-----Contact Details----->
				<h3>Contact Details</h3>
				<div class="well well-small mahanadu-well form-inline">
				
				  <label>Mobile No<span class="text-error"><span class="text-error">* </span> </span></label>             
					<input type="text" name="cadreVo.mobileNo"  id="mobileNoId">      
				
				  <label>&nbsp&nbsp&nbsp&nbsp&nbsp Land No</label>              
					<input type="text" name="cadreVo.landNo">
					
				  <label >&nbsp&nbsp&nbsp&nbsp&nbsp Email Id</label>              
					<input  type="text" name="cadreVo.emailId"> 
				</div>
				
				<!-----Current Address----->
				<h3>Current Address</h3>
					<div class="well well-small mahanadu-well form-inline">				
					<div class="row-fluid">
						<div class="span6">	
						    <label>Address<span class="text-error">* </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							
							<textarea rows="4" cols="50"  name="address" class="input-xlarge" id="addressId">
							</textarea>
							<br/><label class="m_top20">District<span class="text-error">* </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="districtField" cssClass="regionSelect input-xlarge" name="cadreVo.districtId" list="#session.districtsList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'constituenciesInDistrict','cadreReg','constituencyField','currentAdd');" ></s:select>
							
							</div>
						<div class="span6">	
							<br/><label class="m_top20">Constituency<span class="text-error">* </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="constituencyField" cssClass="regionSelect input-xlarge" name="cadreVo.constituencyId" list="#session.constituenciesList" listKey="id" listValue="name" onchange="getBooths('currentAdd','constituencyField','boothField',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreReg','boothsInTehsilOrMunicipality');"></s:select> 
						
							<label class="m_top20">Booth No &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="boothField" cssClass="regionSelect input-xlarge" name="cadreVo.boothNo" list="#session.boothsList" listKey="id" listValue="name"></s:select>
						</div>						
					</div>
				</div>
				
				<!-----Social Status----->
				<h3>Social Status</h3>
					<div class="well well-small mahanadu-well form-inline">				
					<div class="row-fluid">
						<div class="span6">	
							<label>Education&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
							<s:select id="educationField" cssClass="regionSelect input-xlarge" name="cadreVo.educationId" list="#session.eduQualsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Education"></s:select>
							<br><label class="m_top20">Profession &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>             
                            <s:select id="professionField" cssClass="regionSelect input-xlarge" name="cadreVo.professionId"list="#session.occupationsList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Occupation"></s:select>							
						</div>
						<div class="span6">	
							<label>Annual Income&nbsp;&nbsp;</label>  
                            <input type="text" class="input-small" name="cadreVo.annualIncome"></input> 							
							<label>&nbsp;&nbsp;Income Source</label>  
                             <input type="text" id="incomeSource" cssClass="regionSelect input-small" name="cadreVo.sourceIncome" >							
							<!--<s:select id="incomeSource" cssClass="regionSelect input-small" name="cadreVo.sourceIncome" list="#session.incSource" listKey="id" listValue="name" headerKey="0" headerValue="Select Group" ></s:select>-->
							<br><label class="m_top20">Cast Category &nbsp;&nbsp;</label>             
                            <s:select id="casteCateg" cssClass="regionSelect span8 xlarge" name="cadreVo.casteCategory" list="#session.casteCategory" listKey="id" listValue="name" headerKey="0" headerValue="Select Group" ></s:select>							
						</div>						
					</div>
				</div>
				

				
				<!-----Member Designation----->
				<h3>Member Designation</h3>
					<div class="well well-small mahanadu-well form-inline">
						<div class="row-fluid">
							<div class="span2">
								<label> Party Designation </label>
							</div>
						<div class="span4">
						    <s:select id="partyDesig"  list="#session.partyDesig" multiple="true" listKey="id" listValue="name"  ></s:select>	
							
						</div>
						<div class="span2">
						<label> &nbsp; Govt Designation </label>
						</div>
						<div class="span4">
						    <s:select id="govDesig"  list="#session.govDesig" multiple="true" listKey="id" listValue="name"  ></s:select>	
						</div>
					</div>
					</div>
					<input type="hidden" name="partyDesigIds" id="partyDesigIds">
					<input type="hidden" name="govtDesigIds" id="govtDesigIds">
					
				<!-----Member Type----->
				<h3>Member Type</h3>
					<div class="well well-small mahanadu-well form-inline">
										
					<label>Member Type<span class="text-error"><span class="text-error">* </span> </span></label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="cadreVo.memberType" id="optionsRadios3" value="Active" >
									Active </label> &nbsp;&nbsp;
						<input type="text" class="input-medium" name="activeDateField" readonly="true" id="activeDateField" ></input>  
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="radio" name="cadreVo.memberType" id="optionsRadios4" value="Normal" >
									Inactive </label> &nbsp;&nbsp;
					      
				
				                
					
					
				                
					</div>					
					<!-----Register Button----->
					<button class="btn btn-large pull-right btn-success" id="cadreSaveBth" type="button" onclick="uploadCadre();">&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;  &nbsp; Register &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</button>
			</div><!----Form Div End--------->
		</div>
		
		<!---Footer---->
			
				<div class="row-fluid"> 
					<div style="border-top:2px solid #ffdc2d;" class="m_top20">
				   <div class="span12"><img class="pull-right" src="images/mahaNadu/PA_LOGO.png"></div>
				</div>
				  <!--COPYRIGHT SECTION END--> 
				</div>
			<!---Footer End---->
	</div>
</form>
<div id="dialogueTab" align="center" style="width:900px;">
		<div id="buildDataTable"  class="yui-skin-sam"></div>	
	</div>
	
<script type="text/javascript">
$(document).ready(function(){
   $('#activeDateField').datepicker({
            changeMonth: true,
            changeYear: true,
			dateFormat: 'dd/mm/yy',
			maxDate: new Date(),
			yearRange: "-100:+0"
        });
		
		 $('#partyDesig').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode"	,
			noneSelectedText:"Select Designation"
	}).multiselectfilter({ });
	 $('#govDesig').multiselect({	
			multiple: true,
			selectedList: 1,
			hide: "explode",
			noneSelectedText:"Select Designation"
	}).multiselectfilter({ });
	
});
function uploadCadre()
{
	
 if(validatefields())
 {
    var partyDesigIds ="";
	var govtDesigIds ="";
	var selectedPartyDesig = $("#partyDesig").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	 for(var i in selectedPartyDesig)
	 {
		partyDesigIds = partyDesigIds+""+selectedPartyDesig[i]+",";
	 }
	 if(partyDesigIds!=0 && partyDesigIds.length > 0){
	   partyDesigIds = partyDesigIds.substring(0,partyDesigIds.length - 1);
	 }
	 $("#partyDesigIds").val(partyDesigIds);
	 var selectedGovtDesig = $("#govDesig").multiselect("getChecked").map(function(){
			return this.value;    
		}).get();
	 for(var i in selectedGovtDesig)
	 {
		govtDesigIds = govtDesigIds+""+selectedGovtDesig[i]+",";
	 }
	 if(govtDesigIds!=0 && govtDesigIds.length > 0){
	   govtDesigIds = govtDesigIds.substring(0,govtDesigIds.length - 1);
	 }
   $("#govtDesigIds").val(govtDesigIds);
    $("#cadreSaveBth").attr("disabled","disabled");	
 var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showUploadStatus(uploadResult);	
					 $("#cadreSaveBth").removeAttr('disabled'); 
				}
			};

		
		YAHOO.util.Connect.setForm('uploadCadreForm',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadCadreForMahanadu.action',uploadHandler);
	}
	else
		return;
}
function validatefields()
{
$("#errorMsgDiv").html('');
var flag = true;
var str = '';
var firstName = $.trim($("#firstNameId").val());
var lastName = $.trim($("#lastNameId").val());
var isgenderChecked = jQuery("input[name='cadreVo.gender']:checked");
var mobileNo =  $.trim($("#mobileNoId").val());
var districtId = $("#districtField").val();
var constituencyId = $("#constituencyField").val();
var ismemberTypeChecked = jQuery("input[name='cadreVo.memberType']:checked");
var address = $.trim($("#addressId").val());
var str='';
if(firstName.length == 0 )
	{
	str+='firstName is required<br/>';
	flag = false;
	}
	if(lastName.length == 0 )
	{
	str+='lastName is required<br/>';
	flag = false;
	}
	if(address.length == 0 )
	{
	str+='address is required<br/>';
	flag = false;
	}
	if(districtId == 0 )
	{
	str+='Select District<br/>';
	flag = false;
	}
	if(constituencyId == null || constituencyId == 0)
	{
	str+='Select Constituency<br/>';
	flag = false;
	}
	if(ismemberTypeChecked.length == 0)
	{
	str+='Member Type is required<br/>';
	flag = false;
	}else{
	   if($("#optionsRadios3").is(':checked') && $("#activeDateField").val().length == 0){
	      str+='In Member Type, Active Date is required<br/>';
	      flag = false;
	   }
	}
	if(isgenderChecked.length == 0)
	{
	str+='gender is required<br/>';
	flag = false;
	}
	if(mobileNo.length == 0 )
	{
	str+='mobileNo is required<br/>';
	flag = false;
	}
	if(isNaN(mobileNo)) {
			
			str+='Enter valid MobileNo';
			
			flag =false;
			
			}
	else if(!(mobileNo.length == 0 || (mobileNo.length >=10 && mobileNo.length<=12)))
		{
			str+='Enter valid MobileNo';
			flag =false;
		
		}
	if(!flag)
	{
	$("#errorMsgDiv").html(str).css("color","red");
	$('html, body').animate({ scrollTop: $("#errorMsgDiv").offset().top }, "slow");
	}
	if(flag)
	$("#errorMsgDiv").html('');
	return flag;
}
function changeImg(){
  //uploadFileId
}

function searchCadre(){
		
		
	var cosntiId = document.getElementById("constitList").value;
	var searchType = "";
	var searchBy = '';
	
	var firstNameVal = $('#firstName').val();
	var lastNameVal = $('#lastName').val();
	var mobileVal = $('#mobileId').val();
	$('#errodiv').html('');
	if(firstNameVal.trim().length == 0 && lastNameVal.trim().length == 0 && mobileVal.trim().length == 0){
		$('#errodiv').html('<b>Plese Enter Any Search Criteria Value.</b>');
		return;
	}
	
	 $('#dialogueTab').dialog({
            autoOpen: true,
			width:950,
			title:"Cadre Details ",
            modal: true,
			resizable: false
        });
		$('#buildDataTable').html('');
		
		
	if(firstNameVal.trim().length > 0 && lastNameVal.trim().length > 0 && mobileVal.trim().length > 0){
		searchType = "all";
		searchBy = firstNameVal+","+lastNameVal+","+mobileVal;
	}
	else if(firstNameVal.trim().length > 0 && lastNameVal.trim().length > 0 ){
		searchType = "firstTwo";
		searchBy = firstNameVal+","+lastNameVal;
	}
	else if(lastNameVal.trim().length > 0 && mobileVal.trim().length > 0){
		searchType = "secondTwo";
		searchBy = lastNameVal+","+mobileVal;
	}	
	else if(firstNameVal.trim().length > 0  && mobileVal.trim().length > 0){
		searchType = "firstLast";
		searchBy = firstNameVal+","+mobileVal;
	}
	else if(firstNameVal.trim().length > 0 && lastNameVal.trim().length == 0 && mobileVal.trim().length == 0){
		searchType = "firstone";
		searchBy = firstNameVal;
	}
	else if(firstNameVal.trim().length == 0 && lastNameVal.trim().length > 0 && mobileVal.trim().length == 0){
		searchType = "secondone";
		searchBy = lastNameVal;
	}	
	else if(firstNameVal.trim().length == 0 && lastNameVal.trim().length == 0 && mobileVal.trim().length > 0){
		searchType = "thirdone";
		searchBy = mobileVal;
	}
	
	
	YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{		
		var cadreId = oRecord.getData("cadreId");
		var str ="<a href='javascript:{editCadreInfo("+cadreId+")}'> <img src='images/icons/edit.png' style='text-decoration: none; border: 0px none;'></a>";
		elLiner.innerHTML =str;
	}
	
	YAHOO.widget.DataTable.CadreName = function(elLiner, oRecord, oColumn, oData)
	{		
		var firstName = oRecord.getData("firstName");
		var lastName = oRecord.getData("lastName");
		var str =""+firstName+" "+lastName+"";
		elLiner.innerHTML =str;
	}
	
	YAHOO.widget.DataTable.Area = function(elLiner, oRecord, oColumn, oData)
	{		
		var boothId = oRecord.getData("boothNo");
		var str ='';
		if(boothId > 0)
			str +="Booth No - "+boothId+"";	
		else
			str +="<span  style='margin-left:50px;'> -  </span>";	
		elLiner.innerHTML =str;
	}
	
	
	YAHOO.widget.DataTable.CadreImage = function(elLiner, oRecord, oColumn, oData)
	{		
		var cadreImg = oRecord.getData("image");
		var str ="";
		if(cadreImg != "human.jsp"){
		str +="<img height='85px' width='85px' src='images/cadre_images/"+cadreImg+"'/>";
		}
		else{
		str +="<img height='85px' width='85px' src='images/cadre_images/human.jpg'/>";
		}
		
		elLiner.innerHTML =str;
	}
	
var votersByLocBoothColumnDefs = [

{key:"firstName", label: " Cadre Name ",width:200,formatter:YAHOO.widget.DataTable.CadreName,sortable: true},
{key:"mobileNo",label:" Contact No ",width:100,sortable: true},
{key:"districtName",label:" Booth No ",width:150,formatter:YAHOO.widget.DataTable.Area,sortable: true},
{key:"memberType",label:" Cadre Type ",width:100,sortable: true},
{key:"lastName",label:" Photo ",width:150,formatter:YAHOO.widget.DataTable.CadreImage,sortable: true},
{key:"lastName", label: " Edit ", width:50,formatter:YAHOO.widget.DataTable.Type,sortable: true}
];
//parentLocationId
var urlStr = "searchCadreInfoAction.action?searchBy="+searchBy+"&cosntituencyId="+cosntiId+"&searchType="+searchType+"&";
$("#commentsData_outer").show();
var votersByLocBoothDataSource = new YAHOO.util.DataSource(urlStr);

votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "cadreVOList",
fields: ["firstName","lastName","mobileNo","boothNo","cadreId","image","memberType"],

metaFields: {
totalRecords: "count" // Access to value in the server response
},
};

var myConfigs = {
initialRequest: "sort=firstName&dir=asc&startIndex=0&results=10", // Initial request for first page of data
sortedBy : {key:"firstName", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
dynamicData: true, // Enables dynamic server-driven data
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("buildDataTable",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}


return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};

return ;
}
	
function editCadreInfo(cadreId){
	alert(cadreId);
}
</script>
</body>

</html>