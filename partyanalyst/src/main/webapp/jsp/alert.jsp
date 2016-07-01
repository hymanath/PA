<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>  
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alerts </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
		

	
	
	
	<link  rel="stylesheet" type="text/css" href="debate/js/jquery.google.api/jquery-ui1.10.3.css"/>
	
	
	<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<link rel="stylesheet" type="text/css" href="styles/custom-yui-styles.css">	
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 	
	<link rel="stylesheet" type="text/css" href="debate/pagination/simplePagination.css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
		 		
	
	<!-- YUI Dependency files (Start) -->
		<script type="text/javascript" src="debate/js/yahoo/yahoo-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/yahoo-dom-event.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/element-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/container-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/dom-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/yui-min.js"></script>
		<script type="text/javascript" src="debate/js/json/json-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/connection-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datasource-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datatable-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/paginator-min.js"></script>
		
		
		
		<!-- Skin CSS files resize.css must load before layout.css --> 
		<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
		<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">

		<!-- YUI Dependency files (End) -->
		
		
	
	<!-- JQuery files (Start) -->
	<script src="debate/js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>
	<script type="text/javascript" src="debate/js/bootstrap.min.js"></script>
	<script src="debate/js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
	<script src="debate/js/jquery-ui-themes-1.10.3.js"></script>
	<script src="debate/js/jquery-ui-timepicker-addon.js"></script>
	<script src="debate/js/jquery-ui-sliderAccess.js"></script>
	
	<script src="debate/js/jquery.google.api/trentrichardson.com.examples.timepicker.jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="js/debate.js"></script>
	
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.js"></script>
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.filter.js"></script>
	<script type="text/javascript" src="debate/js/jquery.dataTables.js"></script>	
	<script type="text/javascript" src="debate/js/jQuery/debate/js/jquery-ui-1.8.24.custom.min.js"/>
	<script type="text/javascript" src="debate/pagination/pagination1.js"></script>
	<script type="text/javascript" src="debate/pagination/jquery.simplePagination.js"></script>
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	
 </head>                                         							
<body>
<div class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">Create Alert</h4>
				</div>
				<div id="errorDiv1" class="col-md-12" style="font-weight:bold;color:red;font-size:15px;height:25px;"></div>
				<form id="saveAlertForm" name="saveAlertForm" enctype="multipart/form-data" action="saveAlertAction.action" method="POST">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3">
							<label>Alert Type</label>
							<select class="dropkickClass" name="alertVO.alertTypeId">
								<option value="1">Select Alert</option>
							</select>
						</div>
						
						<div class="col-md-3">
							<label>Alert Severity</label>
							<select class="dropkickClass" name="alertVO.severity">
								<option value="1">High</option>
								<option value="2">Medium</option>
								<option value="3">Low</option>
							</select>
						</div>
						<div class="col-md-3 levelShowCls" >
							<label>Level</label>
							<select class="dropkickClass" id="levelId" onchange="disableByLevel();" >
							<option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Muncipality</option>
							 <option value="6">Village/Ward</option>
							</select>
						</div>
						<div class="col-md-3 stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" class="stateCls" id="stateId" onChange="getDistrictsForReferPopup();" name="alertVO.stateId">
							 <option value="0">Select State</option>
							 <option value="1">AP</option>
							 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup();" name="alertVO.districtId">
							 <option value="0">Select District</option></select>
						</div>
						<div class="col-md-3 locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup();" name="alertVO.constituencyId">
							<option value="0">Select Assembly</option>
							</select>
						</div>
						<div class="col-md-3 locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup();" name="alertVO.tehsilId">
								<option value="0">Select Mandal/ Municipality</option>
							 </select>
						</div>
						<div class="col-md-3 locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" name="alertVO.panchayatId">
							<option value="0">Select Panchayat/Ward</option>
							</select>
						</div>
						
					</div>
					<div class="row">
						<div class="col-md-6">
							<label>Search Candidates</label>
							<div class="input-group">
								<input type="text" class="form-control" id="candidateNameId"/>
								<span class="input-group-addon" onclick="getCandidateNameDetails()">
									<i class="glyphicon glyphicon-search" ></i>
								</span>
							</div>
						</div>
						
						<img id="ajaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none";/>
						<div class="col-md-3">
							<label>Select Candidates</label>
							<select class="dropkickClass"  id="candidatesNameListId" name="alertVO.candidateId">
							<option value="0">Select Candidate</option>	
							</select>
						</div>
						
						<div class="col-md-3">
							<label>Alert Impact</label>
							<select class="dropkickClass"  id="alertImpactId" name="alertVO.alertImpactId">
							<option value="1">Positive </option>	
							<option value="2">Negative </option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<label>Description</label>
							<textarea class="form-control" id="descriptionId" name="alertVO.desc"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3" style="margin-top:10px">
						<input style="font-weight:bold;" class="btn btn-primary" id="addThisalertId" onclick="createAlert();" type="button" value="CREATE ALERT" ></input>
						<!--<button  type="button" class="btn btn-primary">CREATE ALERT</button>-->
						</div>
					</div>
				</div>
				
				<input type="hidden" class="form-control" id="locationLevelValhidden" name="alertVO.locationValue" />
				<input type="hidden" class="form-control" id="locationLevelIdhidden" name="alertVO.locationLevelId" />
				
				</form>
			</div>
		</div>
	</div>
                 
</div>
<!--<div id="errorDiv" class="offset1" style="font-weight:bold;color:red;font-size:15px;height:25px;"></div>
	<div><h4> Select Candidate : <input style="margin-left:31px;" type="candidateName" id="candidateNameId" />
	<button type="button" onclick="getCandidateNameDetails()">view</button></h4>
	<h4>Enter CandidateName: <select style="margin-left: 53px; width: 220px;" id="candidatesNameListId" onchange="diplayValues()"></select></h4>
	<img id="ajaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none";/>
	</div>-->
<script src="dist/CreateAlert/createAlert.js" type="text/javascript"></script>
<script type="text/javascript">
$(".dropkickClass").dropkick()
function getCandidatesByName(){
		var  CandidateName=$("#candidateNameId").val();
		var jsObj =
		        {
			CandidateName : CandidateName
		          }
		//$('#ajaxImage').show();
				$.ajax({
					  type:'GET',
					  url: 'getCandidatesByNameAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#candidatesNameListId').append('<option value="0"> Select Candidate </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#candidatesNameListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#candidatesNameListId").dropkick();
							var select1 = new Dropkick("#candidatesNameListId");
							select1.refresh();
					}
				  
				});
		}
		function getCandidateNameDetails()
		{
			
		 $("#errorDiv").html('');
		 var CandidateName=$("#candidateNameId").val();
		 if(CandidateName.trim().length<=3 || CandidateName ==""){
          $("#errorDiv").html('plz enter minimum 3 characters ');
	      return;
         }	
		 getCandidatesByName();
		}
		
function createAlert()
{
  var  alertType=$("#alertTypeId").val();
  var  level=$("#levelId").val();
  var  state=$("#stateId").val();
  var  district=$("#referdistrictId").val();
  var  assembly=$("#referconstituencyId").val();
  var  mandal=$("#refermandalNameId").val();
  var  panchayat=$("#referpanchayatId").val();
  var  candidate=$("#candidatesNameListId").val();
  var  candidateName=$("#candidateNameId").val();
  var  description=$("#descriptionId").val();
   $("#errorDiv1").html('');
  $("#errorDiv1").css("color","red");
  if(alertType==0)
  {
    $("#errorDiv1").html(" Please select Alert Type ");
        return;
  }
  if(level==0)
  {
     $("#errorDiv1").html(" Please select level ");
  }
  
  if(level==2)
  {
    if(state==0)
      {
      $("#errorDiv1").html(" Please select state ");
          return;
    }
	$("#locationLevelIdhidden").val(2);
	$("#locationLevelValhidden").val(state);
    
  }
  if(level==3)
  {
    if(state==0)
    {
      $("#errorDiv1").html(" Please select state ");
          return;
    }
    if(district==0)
    {
      $("#errorDiv1").html(" Please select District ");
          return;
    }
	$("#locationLevelIdhidden").val(3);
	$("#locationLevelValhidden").val(district);
  }
  
 if(level==4)
  {
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
    if(assembly==0)
    {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
    }
	$("#locationLevelIdhidden").val(4);
	$("#locationLevelValhidden").val(assembly);
  }
  if(level==5)
  {
	  var mandalName = $("#refermandalNameId option:selected").text();
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
    if(assembly==0)
     {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
     }
    
    if(mandal==0)
    {
      $("#errorDiv1").html(" Please select Mandal/ Municipality ");
          return;
    }
	$("#locationLevelValhidden").val(mandal);
		if(mandalName.indexOf('Mandal') == -1)
		$("#locationLevelIdhidden").val(7);
	else
		$("#locationLevelIdhidden").val(5);
		
  }
  if(level==6)
  {
	   var panchayatName = $("#referpanchayatId option:selected").text();
    if(state==0)
      {
        $("#errorDiv1").html(" Please select state ");
            return;
      }
    if(district==0)
      {
        $("#errorDiv1").html(" Please select District ");
            return;
      }
      if(assembly==0)
     {
      $("#errorDiv1").html(" Please select Assembly ");
          return;
     }
    
      if(mandal==0)
     {
      $("#errorDiv1").html(" Please select Mandal/ Municipality ");
          return;
     }
     if(panchayat==0)
     {
    $("#errorDiv1").html(" Please select Panchayat ");
        return;
     }
	 $("#locationLevelValhidden").val(panchayat);
		 if(panchayatName.indexOf('WARD') == -1)
			$("#locationLevelIdhidden").val(6);
		else
			$("#locationLevelIdhidden").val(8);
    }
  if(candidate==0)
  {
    $("#errorDiv1").html(" Please select Candidate ");
        return;
  }
   if(description.length==0||description=='')
  {
    $("#errorDiv1").html(" Please select description ");
        return;
  }

var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					if(uploadResult.indexOf("success") !=-1)
					{
					 $("#errorDiv1").html(" Alert Created Successfully ").css("color","green");	
					}
					return false;
				}
			};

		YAHOO.util.Connect.setForm('saveAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveAlertAction.action',uploadHandler);
}


</script>
</body>
</html>