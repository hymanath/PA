
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
.errorCls{color:red;font-size:12px;}
.prev, .next{min-width:44px !important}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>File Upload</title>

    <link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
	<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />

	<!--jQuery
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>-->
	
	
	<!-- Bootstrap -->
    <link href="dist/activityDashboard/css/bootstrap.css" rel="stylesheet">
    <link href="dist/activityDashboard/css/custom.css" rel="stylesheet">
    <link href="daterangepicker/daterangepicker-bs3.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
    <!-- <script src="js/cadreCommittee/bootstrap.min.js"></script> -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<script type="text/javascript" src="dragAndDropPhoto/js/uploadImage.js"></script>
	
</head>
<body>
<div class="container m_top20">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						<b id="locationNameId">File Upload</b>
					</div>
					<div class="panel-body">
						<div class="row">
						<div class="col-md-6">
								<label>Activity Date </label>
								 <input type="text" id="activityDate" name="complaintRegistrationVO.dateOfIncident" class="form-control clearCls" readonly>
							</div>	
						  <div class="col-md-6">
							 <label>Day   </label>
							 <select id="day" class="form-control"></select> 
						   </div>
							
						<div id="showHideDiv" style="display:none;">
							<div class="col-md-6">
								<label>Activity Type</label>
								<s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="1" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" cssClass="input-block-level form-control"/>
							</div>
							<div class="col-md-6">
								<label  class="control-label">Activity Level</label>
								<s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="0" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level form-control"/>
							 </div>
							 <div class="col-md-6">
									<label> Activity Name </label>
									<select id="ActivityList" class="form-control">
										<option value="0"> Select Activity </option>
									</select>
							 </div>
							
							
							<div class="col-md-6" id="locationLevelsDiv"><label>Location Levels  </label>
								<select id="levelIds" onchange="hideAndShowDiv()" class="form-control">
									 <option value="2">State</option>
									 <option value="3">District</option>
									 <option value="4">Constituency</option>
									 <option value="5">Mandal/Municipality</option>
									 <option value="6">Panchayat</option>
								</select>
							</div>
							<div class="col-md-6" id="stateDiv"><label>State </label>
								<select id="statesDivId" onchange="getDistrictsForStates(this.value);" class="form-control">
									<option value="0">Select State</option>
									<option value="1">AndhraPradesh</option>
									<option value="36">Telangana</option>
								</select>
							</div> 
							<div class="col-md-6" id="districtDiv" style="display:none;"><label>District</label>
								<select class="form-control " id="districtId" class="form-control" onchange="getConstituenciesForDistricts(this.value)">
									<option value="0"> Select District </option>
								</select>
							</div>
											
							<div class="col-md-6" id="constitunecyDiv" style="display:none;"><label>Constituency : </label>
								<select class="form-control " id="constituencyId" class="form-control" onchange="getMandalCorporationsByConstituency()">
									<option value="0"> Select Constituency </option>
								</select>
							</div>
							<div class="col-md-6" style="padding-top: 10px ;display:none;" id="mandalDiv"><label>Mandal/Municipality :</label>
								<select class="form-control " id="mandalList" class="form-control" onchange="getPanchayatWardByMandal();">
									<option value="0"> Select Mandal/Municipality </option>
								</select>
							</div>
											
							<div class="col-md-6" style="padding-top: 10px;display:none;" id="panchayatDiv"><label>Panchayat/Ward :</label>
								<select class="form-control " id="panchaytList" class="form-control" >
									<option value="0"> Select Panchayat </option>
								</select>
							</div>
							
						</div>
							<div id="uploadDiv" class="col-md-6 m_top20">
							    <!--<button type="button" class="btn btn-primary" id="getImagesBtnId" onclick="getActivityImages(0)">View Images</button>-->
							</div>
							<div class="col-md-12" style="margin-top: 38px;">
								<div class="errorDiv"></div>
								<input type="file"  id="filer_input2" multiple="multiple" name="fileImage">
								<p class="text-danger font-10 text-center">You can upload 10 files at a time.</p>
							</div>	
							
							
						</div>
					</div>
				</div>
		</div>
	</div>
	<!-- <div class="col-md-10  offset10" style="margin-bottom:15px;">
		 <input type="button" value="Submit" id="submit_input2" class="btn btn-success " />
	</div>	 -->

	
	<!-- </form> -->
 
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title imgTitleCls" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">
       <div id="imagesDiv" class="row"></div>
	   <div id="paginationDiv"></div>
      </div>
      <div class="modal-footer">
        
        <button type="button" class="btn btn-primary deleteBtnCls" style="display:none">Delete</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->

<script src="dist/activityDashboard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/activityDashboard/js/bootstrap.js" type="text/javascript"></script>
<script src="daterangepicker/moment.js" type="text/javascript"></script>
<script src="daterangepicker/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/cadreCommittee/cadreCommittee.js"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/jquery.filer.min.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/custom.js?v=1.0.5"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>

<script type="text/javascript">

//showHideDiv

var actScopeId = '${activityScopeId}';
var locationValueID = '${locationValue}';
var actvityLevelId = '${activityLevel}';
var locationName = '${locationName}';
var globalLctnInfoId = '${temp}';
var globalTableName = '${task}';




var gobalLevelId = 0;
var gobalLevelValue = 0;
var gobalActivityScopeId = 0;
var gobalLocName = "";
var gobalActivityDate = null;
var gobalTempVar = "dayCalCulationNotReq";
var gobalDay = 0;
var gobalDateStr = '${eventDateStr}';
if(actScopeId>0 && locationValueID > 0 && actvityLevelId> 0)
{
	$("#showHideDiv").hide();
	$("#locationNameId").html(""+locationName+"");
	
	gobalActivityScopeId = actScopeId;
	gobalLevelId = actvityLevelId ;
	gobalLevelValue = locationValueID;
	gobalLocName = locationName;
}
else
{
  $("#showHideDiv").show();
}

 var gobalVar = 0;
  $( "#activityDate" ).daterangepicker({singleDatePicker:true});

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	
    if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    var today = mm+'/'+dd+'/'+yyyy;
    $("#activityDate").val(today);
  
  
	function getDays()
	{
	  for(var i=1;i<=20;i++){
		$("#day").append("<option value='"+i+"'>"+i+"</option>")
	  }
	}
	
	
  function getDistrictsForStates(state)
  {
    $("#districtId  option").remove();
	$("#districtId").append('<option value="0">Select District</option>');
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchaytList  option").remove();
	$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	
	if(state == 0){
		return;
	}
	
	var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	  // $("#districtId").empty();
	  // $("#searchDataImgForDist").hide();
     for(var i in result){
	   if(result[i].id == 0){
         // $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
 }
	
 function getConstituenciesForState(state){
  
   $("#searchDataImgForConst").show();
   var jsObj=
   {				
				stateId:0,
				elmtId:"stateList",
                type:"default",
				task:"getConstituenciesForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		$("#searchDataImgForConst").hide();
	   $("#constituencyId").empty();
	   
     for(var i in result){
	   if(result[i].id == 0){
         $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	     $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
  function getConstituenciesForDistricts(district){
   $("#searchDataImgForConst").show();
    //refreshExistingDetails();
	$(".allcls").hide();
   $("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchaytList  option").remove();
	$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	
	//document.getElementById('membershipId').checked = true;
	
	var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   //$("#constituencyId").empty();
	  // $("#searchDataImgForConst").hide();
     for(var i in result){
	   if(result[i].id == 0){
          //$("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
  
  function getMandalCorporationsByConstituency()
	{	
			$("#searchDataImgForMandl").show();
			//refreshExistingDetails();
			//document.getElementById('allId').checked = false;
			$(".allcls").hide();
			var constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			//document.getElementById('membershipId').checked = true;
			
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				$("#searchDataImgForMandl").hide();
				if(result !=null)
				{
					for(var i in result)
					{
						$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
					}	
				}				
				});
	}
	
	function getPanchayatWardByMandal(){
			$("#searchDataImgForPanc").show();
			//refreshExistingDetails();
			//document.getElementById('allId').checked = false;
			//$(".allcls").hide();
		     
			var mandalId=$("#mandalList").val();
			
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			
			var jsObj={
				mandalId:mandalId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				$("#searchDataImgForPanc").hide();
			for(var i in result){
				$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
				
		});	
			
	}
	
  
	
	
	function getActivityNames()
{
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'" selected="true">'+result[i].name+'</option>');	
			}
			
		});
		
}

function hideAndShowDiv()
{
	var levelId = $("#levelIds").val();
	
	
		$("#districtDiv").hide();
		$("#constitunecyDiv").hide();
		$("#mandalDiv").hide();
		$("#panchayatDiv").hide();
	
	$("#statesDivId").val(0);
	$("#districtId  option").remove();
	$("#districtId").append('<option value="0">Select District</option>');
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchaytList  option").remove();
	$("#panchaytList").append('<option value="0">Select Panchayat</option>');
		
	if(levelId == 2){
		$("#stateDiv").show();
	}
	else if(levelId == 3){
		$("#stateDiv").show();
		$("#districtDiv").show();
	}
	else if(levelId == 4){
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constitunecyDiv").show();
	}
	else if(levelId == 5){
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constitunecyDiv").show();
		$("#mandalDiv").show();
		
	}
	else if(levelId == 6){
		$("#stateDiv").show();
		$("#districtDiv").show();
		$("#constitunecyDiv").show();
		$("#mandalDiv").show();
		$("#panchayatDiv").show();
	}
}

$(document).on("click",".deleteFile",function() {
 
 var acitivityInfoDocId = $(this).attr("id");
 
 var jsObj=
   {				
	  acitivityInfoDocId:acitivityInfoDocId,
	  task:"deleteFile"				
	}
	$.ajax({
			  type:'GET',
			  url: 'deleteUploadedFileAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result.resultCode == 0){
	         $(this).closest("li").html("");
		   }
	   });

});


function validateFields()
{
	var flag = true;
	
	gobalDay = $("#day").val();
	gobalActivityDate = $("#activityDate").val();
				
	if(actScopeId >0 && locationValueID > 0 && actvityLevelId> 0){}
	else
	{
		var activityLevel = $("#activityLevelList").val();
		var activityName = $("#ActivityList").val();
		var activityDate = $("#activityDate").val();
		var levelIds = $("#levelIds").val();
		var stateId = $("#statesDivId").val();
		var uploadFileSize = 0;
		
		var str = '';
			
		if(activityLevel == 0){
			str += 'Please Select Activity Level <br>';
			flag = false;
		}
			
		if(activityName == 0){
			str += 'Please Select Activity Name <br>';
			flag = false;
		}
			
		if(levelIds == 0){
			str += 'Please Select Location Level <br>';
			flag = false;
		}
			
		if(levelIds > 0){
			if(levelIds == 2 && stateId == 0){
				str += 'Please Select State <br>';
				flag = false;
			}
			else if(levelIds == 3){
				var districtId = $("#districtId").val();
				if(stateId == 0){
					str += 'Please Select State <br>';
					flag = false;
				}
				if(districtId == 0){
					str += 'Please Select District <br>';
					flag = false;
				}
			}
				
			else if(levelIds == 4){
				var districtId = $("#districtId").val();
				var constituencyId = $("#constituencyId").val();
				if(stateId == 0){
					str += 'Please Select State <br>';
					flag = false;
				}
				if(districtId == 0){
					str += 'Please Select District <br>';
					flag = false;
				}
				if(constituencyId == 0){
					str += 'Please Select Constituency <br>';
					flag = false;
				}
			}
				
			else if(levelIds == 5){
				var districtId = $("#districtId").val();
				var constituencyId = $("#constituencyId").val();
				var mandalOrMunId = $("#mandalList").val();
				if(stateId == 0){
					str += 'Please Select State <br>';
				}
				if(districtId == 0){
					str += 'Please Select District <br>';
					flag = false;
				}
				if(constituencyId == 0){
					str += 'Please Select Constituency <br>';
					flag = false;
				}
				if(mandalOrMunId == 0){
					str += 'Please Select Mandal/Municipality <br>';
					flag = false;
				}
			}
				
			else if(levelIds == 6){
				var districtId = $("#districtId").val();
				var constituencyId = $("#constituencyId").val();
				var mandalOrMunId = $("#mandalList").val();
				var panchayatId = $("#panchaytList").val();
					
				if(stateId == 0){
					str += 'Please Select State <br>';
					flag = false;
				}
				if(districtId == 0){
					str += 'Please Select District <br>';
					flag = false;
				}
				if(constituencyId == 0){
					str += 'Please Select Constituency <br>';
				}
				if(mandalOrMunId == 0){
					str += 'Please Select Mandal/Municipality <br>';
					flag = false;
				}
				if(panchayatId == 0){
					str += 'Please Select Panchayat/Ward <br>';
					flag = false;
				}
			}
				
		}
			
		$(".errorDiv").html(str).addClass("errorCls");
		if(!flag){
			$(".jFiler-items-list").html("");
			return false;
		}
		if(flag)//setting levelId and levelValue
		{
			if(levelIds == 2)
			{
				gobalLevelId = 2;//state
				gobalLevelValue = $("#statesDivId").val();
				locationName = $("#statesDivId option:selected").text();
			}
			else if(levelIds == 3)
			{
				gobalLevelId = 3;//district
				gobalLevelValue = $("#districtId").val();
				locationName = $("#districtId option:selected").text();
			}
			else if(levelIds == 4)
			{
				gobalLevelId = 4;//constituency
				gobalLevelValue = $("#constituencyId").val();
				locationName = $("#constituencyId option:selected").text();
			}	
			else if(levelIds == 5)
			{
				var locationValue = $("#mandalList").val();
				locationName = $("#mandalList option:selected").text();
				
				var firstChar = locationValue.substr(0,1);
				if(firstChar == 2)
				{
				  gobalLevelId = 5;//mandal
				  gobalLevelValue = locationValue;
				}
				else if(firstChar == 1)
				{
					gobalLevelId = 7;//localelectionBody
					gobalLevelValue = locationValue; 
				}
			}
				
			else if(levelIds == 6)
			{
				var locationValue = $("#panchaytList").val();
				locationName = $("#panchaytList option:selected").text();
				
				var firstChar = locationValue.substr(0,1);
				if(firstChar == 1)
				{
					gobalLevelId = 6;//panchayat
					gobalLevelValue = locationValue;
				}
				else 
				{
					gobalLevelId = 8;//ward or division
					gobalLevelValue = locationValue; 
				}
			}
				gobalActivityScopeId = activityName;
				gobalLocName = locationName;
				
		}
	}
	return flag;
}

$(".bootstrap-filestyle").hide();
		
 	getDays();
	

	
</script>
</body>
</html>