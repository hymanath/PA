
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>File Upload</title>

    <script type="text/javascript" src="js/cadreCommittee/cadreCommittee.js"></script>
	<script type="text/javascript" src="dragAndDropPhoto/js/jquery.filer.min.js?v=1.0.5"></script>
	<script type="text/javascript" src="dragAndDropPhoto/js/custom.js?v=1.0.5"></script>
	
	
    <link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
	<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />

	<!--jQuery
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>-->
	
	
	<!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
    <!-- <script src="js/cadreCommittee/bootstrap.min.js"></script> -->
    <link href="dist/Daterange/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
	  <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
</head>
<body>
<div class="container m_top20">

     <div class="col-sm-5">
			<label>Activity Type</label>
            <s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="1" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" cssClass="input-block-level form-control"/>
		</div>
	 <div class="col-sm-5">
	    <label  class="control-label">Activity Level</label>
        <s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="0" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level form-control"/>
     </div>
	 <div class="col-md-5">
			<label> Activity Name </label>
			<select id="ActivityList" class="form-control">
				<option value="0"> Select Activity </option>
			</select>
	 </div>
	
	<div class="col-md-5"> <label>Day   </label><select id="day" class="form-control"></select> </div>
	 
    <div class="col-md-5">
	    <label>Activity Date </label>
		 <input type="text" id="activityDate" name="complaintRegistrationVO.dateOfIncident" class="form-control clearCls" readonly>
							
	</div>
	
	<div class="col-md-5" id="locationLevelsDiv"><label>Location Levels  </label>
		<select id="levelIds" onchange="hideAndShowDiv()" class="form-control">
			 <option value="2">State</option>
			 <option value="3">District</option>
			 <option value="4">Constituency</option>
			 <option value="5">Mandal/Municipality</option>
			 <option value="6">Panchayat</option>
		</select>
	</div>
	<div>
		<div class="col-md-5" id="stateDiv"><label>State </label>
			<select id="statesDivId" onchange="getDistrictsForStates(this.value);" class="form-control">
			    <option value="0">Select State</option>
				<option value="1">AndhraPradesh</option>
				<option value="36">Telangana</option>
			</select>
		</div> 
		<div class="col-md-5" id="districtDiv" style="display:none;"><label>District</label>
			<select class="form-control " id="districtId" class="form-control" onchange="getConstituenciesForDistricts(this.value)">
				<option value="0"> Select District </option>
			</select>
		</div>
						
		<div class="col-md-5" id="constitunecyDiv" style="display:none;"><label>Constituency : </label>
			<select class="form-control " id="constituencyId" class="form-control" onchange="getMandalCorporationsByConstituency()">
				<option value="0"> Select Constituency </option>
			</select>
		</div>
						
		<div class="col-md-5" style="padding-top: 10px ;display:none;" id="mandalDiv"><label>Mandal/Municipality :</label>
			<select class="form-control " id="mandalList" class="form-control" onchange="getPanchayatWardByMandal();">
				<option value="0"> Select Mandal/Municipality </option>
			</select>
		</div>
						
		<div class="col-md-5" style="padding-top: 10px;display:none;" id="panchayatDiv"><label>Panchayat :</label>
			<select class="form-control " id="panchaytList" class="form-control" >
				<option value="0"> Select Panchayat </option>
			</select>
		</div>
	</div>
	
	<div class="col-md-10" style="margin-top: 38px;">
		 <input type="file"  id="filer_input2" multiple="multiple" name="fileImage">
	</div>	

	<!-- <div class="col-md-10  offset10" style="margin-bottom:15px;">
		 <input type="button" value="Submit" id="submit_input2" class="btn btn-success " />
	</div>	 -->

	
	<!-- </form> -->
 
</div>
<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/js/moment.min.js" type="text/javascript"></script>
<script src="dist/Daterange/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript">

	 /*$(document).ready(function(){

	$( "#activityDate" ).datepicker({ dateFormat: 'dd/mm/yy' });
	});*/
	$( "#activityDate" ).datepicker({ dateFormat: 'dd/mm/yy' });

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
	   $("#districtId").empty();
	   $("#searchDataImgForDist").hide();
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
	   $("#constituencyId").empty();
	   $("#searchDataImgForConst").hide();
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

/*$("#activityDate").datepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,
	        changeYear: true,
			maxDate: new Date()
		});*/
		
 	getDays();
	//submitImages();
</script>
</body>
</html>