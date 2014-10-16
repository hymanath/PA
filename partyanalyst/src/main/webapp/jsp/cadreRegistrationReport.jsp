<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Registration Report</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<script type="text/javascript" src="js/exportexcel.js"></script>
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-center{text-align: center;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.height-320{height: 300px; overflow: auto;width: 440px;}
	.f-16{font-size: 16px;}
	body {
    color: #333333;
    font-size: 14px;
    line-height: 20px;
    margin: 0;
    }
	p {
    color: #333;
    font-size: 14px;
   }
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   .text-right {
    text-align: right;
   }
   .imgStyle{
      margin-left: 75px;
      margin-top: 30px;
	}

  #userStatusDialogDIV,#locationStatusDialogDIV{
    padding-top: 35px;
  }
	.survey_nav{height:38px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:38px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 12px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
	.highlight {
        cursor: pointer;
    }
	</style>
</head>
<body>
   <div class="container m_top10">
        <div class="row" style="margin-top:10px;">
		  <div class="span12 m_top20 survey_nav">
			<ul class="inline unstyled">
				<li><a onclick="showHideTabs(this.id);" id="userReportTab" class="highlight selected">Users Working Status</a></li>
                <li><a onclick="showHideTabs(this.id);" id="locationReportTab" class="highlight">Location Wise Cadre Info</a></li>
			</ul>
		  </div>
		</div>
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">Users Working Status</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
				<label style="margin-left:100px;"><b>From Date :</b>&nbsp;<input type="text" readonly="readonly" id="fromDate"/></label>
					 <label><b>To Date   :</b>&nbsp;<input type="text" readonly="readonly" id="toDate" /> </label>
					<input type="button" class="btn btn-success" id="getCandidateDataCollectionInfoId" onclick="getCandidateDataCollectionInfo();" value="Submit"/>
					<img id="ajaxImgStyle" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
					<div id="userStatusDialogDIV"></div>
			  </div>
			</div>
		</div>
		<div id="locationWiseCadreInfoDiv"  style="display:none;">
		   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">Location Wise Cadre Registration Info</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
				  <div id="errMsgDiv"></div>
				  <table  style="margin-left: 270px;">
				     <tr>
					   <td><b>Select Report Level :</b></td>
					   <td><select id="locationsDispalyId" onchange="showCorrespondingLocs(this.value);">
							 <option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Municipality</option>
							 <option value="6">Panchayat</option>
							 <option value="9">Booth</option>
					       </select> 
					   </td>
				     </tr>
				     <tr id="statesDispalyMainDiv">
						 <td><b>Select State :</b></td>
						 <td>  
						   <select id="statesDispalyId">
							 <option value="0">All</option>
							 <option value="1">Andhra Pradesh</option>
							 <option value="36">Telangana</option>
						   </select> 
						 </td>
					 </tr>
					 <tr id="districtsDispalyMainDiv" style="display:none;">
						 <td><b>Select District :</b></td>
						 <td>  
						   <select id="districtsDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="constituencyDispalyMainDiv" style="display:none;">
						 <td><b>Select Constituency :</b></td>
						 <td>  
						   <div id="constituencySelectDIV"><select id="constituencyDispalyId">
						   </select></div>
						 </td>
					 </tr>
					 <tr id="mandalDispalyMainDiv" style="display:none;">
						 <td><b>Select Mandal/Municipality :</b></td>
						 <td>  
						   <select id="mandalDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="panchayatDispalyMainDiv" style="display:none;">
						 <td><b>Select Panchayat :</b></td>
						 <td>  
						   <select id="panchayatDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="boothDispalyMainDiv" style="display:none;">
						 <td><b>Select Booth :</b></td>
						 <td>  
						   <select id="boothDispalyId">
						   </select> 
						 </td>
					 </tr>
					  <tr id="formDateDiv" >
						 <td><b>From Date :</b></td>
						 <td>  
						   <input type="text" id="fromDate1" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls fromDateCls" placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					  <tr id="toDateDiv">
						 <td><b>To Date :</b></td>
						 <td>  
						    <input type="text" id="toDate1" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls fromDateCls"  placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					 
					 <tr><td></td><td> <input style="margin-top:10px;" type="button" id="locationSubmitBtn" class="btn btn-success" onclick="getLocationWiseCadreInfo();" value="Submit"/><img id="ajaxImgStyleNew" style="display:none;margin-left:10px; margin-top:10px;" src="images/icons/search.gif"/></td></tr>
				  </table>
				  <div id="locationStatusDialogDIV"></div>
			  </div>
		   </div>
		</div>
	</div>
<script type="text/javascript">
    $("#fromDate,#fromDate1").datepicker({
		dateFormat: "dd-mm-yy",
		changeMonth: true,
        changeYear: true,
		maxDate: new Date()
	})
	$("#fromDate,#fromDate1").datepicker("setDate", new Date());
	$("#toDate,#toDate1").datepicker({
		dateFormat: "dd-mm-yy",
		changeMonth: true,
        changeYear: true,
		maxDate: new Date()
	})
	$("#toDate,#toDate1").datepicker("setDate", new Date());
   function getCandidateDataCollectionInfo(){
    var allConstituencies = "";
	var ruralConstis = "";
    $("#userStatusDialogDIV").html("");
    $("#errStatusDiv").html("");

	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	
	if(startDate.trim().length >0 && endDate.trim().length >0)
		{
			var dt1  = parseInt(startDate.substring(0,2),10);
			var mon1 = parseInt(startDate.substring(3,5),10);
			var yr1  = parseInt(startDate.substring(6,10),10);
			var dt2  = parseInt(endDate.substring(0,2),10);
			var mon2 = parseInt(endDate.substring(3,5),10);
			var yr2  = parseInt(endDate.substring(6,10),10);
			
			var date1 = new Date(yr1, mon1, dt1);
			var date2 = new Date(yr2, mon2, dt2);

			if(startDate != "" || endDate != ""){

				if(startDate == ""){
					$('#errStatusDiv').html('<font style="color:red;">From Date should not Empty </font>');
					return;
				}
				else if(endDate == ""){
					$('#errStatusDiv').html('<font style="color:red;">To Date should not Empty </font>');
					return;
				}					
				if(date2 < date1){ 
					$('#errStatusDiv').html('<font style="color:red;">From Date should not greater than To Date </font>');
					return;
				}
			}	
		}
		
	$("#getCandidateDataCollectionInfoId").attr("disabled","disabled");
	$("#ajaxImgStyle").show();
    $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"candidateDataCollectionInfo",fromDate:$("#fromDate").val(),toDate:$("#toDate").val()}
       }).done(function(result){
	       var str='';
	       if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel();" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover"><thead>';
				str+='<tr>';
				str+='<th rowspan="2">User</th>';
				for(var i in result[0].infoList){
				  str+='<th colspan="3">'+result[0].infoList[i].date+'</th>';
				}
				str+='</tr>';
				str+='<tr>';
				for(var i in result[0].infoList){
				  str+='<th>Start Time</th>';
				  str+='<th>End Time</th>';
				  str+='<th>Count</th>';
				}
				str+='</tr>';
				str+='</thead><tbody>';
				for(var i in result){
				  str+='<tr>';
				  str+='  <td>'+result[i].name+'</td>';
				  for(var j in result[i].infoList){
				    if(result[i].infoList[j].area != null){
				      str+='  <td>'+result[i].infoList[j].area+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
					if(result[i].infoList[j].location != null){
				      str+='  <td>'+result[i].infoList[j].location+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
					if(result[i].infoList[j].totalCount != null){
				      str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
				  }
				  str+='</tr>';
				}
				str+='</tbody></table></div>';
				 str+='<input type="button" style="margin-top:15px;margin-left: 375px;" class="btn" onclick="generateExcel();" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#userStatusDialogDIV").html(str);
		   $("#ajaxImgStyle").hide();
		   $("#getCandidateDataCollectionInfoId").removeAttr("disabled");
       });
   }
   function generateExcel(){
     tableToExcel("resultTableDiv", 'Users Working Status');
   }
   function showCorrespondingLocs(locationLvl){
      var state = false;
	  var district = false;
	  var constituency = false;
	  var mandal = false;
	  var panchayat = false;
	  var booth = false;
	  
	  if(locationLvl == 2){
	     state = true;
	  }else if(locationLvl == 3){
	     district = true;
	  }else if(locationLvl == 4){
	     showCompleteConstituencyDetails("ALL");
	     constituency = true;
	  }else if(locationLvl == 5){
		 $("#mandalDispalyId option").remove();
	     showCompleteConstituencyDetails("mandal")
	     constituency = true;
		 mandal = true;
	  }else if(locationLvl == 6){
	     $("#panchayatDispalyId option").remove();
	     showRuralConstituencyDetails();
	     constituency = true;
		 panchayat = true;
	  }else if(locationLvl == 9){
	     $("#boothDispalyId option").remove();
	     showCompleteConstituencyDetails("booth")
	     constituency = true;
		 booth = true;
	  }
	  if(state){
	     $("#statesDispalyMainDiv").show();
		 $("#statesDispalyId").val(0);
	  }else{
	     $("#statesDispalyMainDiv").hide();
	  }
	  if(district){
	     $("#districtsDispalyMainDiv").show();
		 $("#districtsDispalyId").val(0);
	  }else{
	    $("#districtsDispalyMainDiv").hide();
	  }
	  if(constituency){
	    $("#constituencyDispalyMainDiv").show();
	  }else{
	    $("#constituencyDispalyMainDiv").hide();
	  }
	  if(mandal){
	     $("#mandalDispalyMainDiv").show();
	  }else{
	     $("#mandalDispalyMainDiv").hide();
	  }
	  if(panchayat){
	    $("#panchayatDispalyMainDiv").show();
	  }else{
	    $("#panchayatDispalyMainDiv").hide();
	  }
	  if(booth){
	    $("#boothDispalyMainDiv").show();
	  }else{
	    $("#boothDispalyMainDiv").hide();
	  }
   }
 function getDistricts(){
     var jsObj=
		{				
				stateId:1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForAStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtsDispalyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtsDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
   function getConstituencies(repType){
    $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:"assemblyNames",type:repType}
   }).done(function(result){
	   if(repType == 'ALL'){
          allConstituencies = result;
	   }else{
	      ruralConstis = result;
	   }
   });
  }
  
  function showCompleteConstituencyDetails(type){
   if(type == "ALL"){
     $("#constituencySelectDIV").html('<select id="constituencyDispalyId"></select>');
     $("#constituencyDispalyId").append('<option value="0">ALL</option>');
   }else{
     if(type == "mandal"){
	   $("#constituencySelectDIV").html('<select id="constituencyDispalyId" onchange="getTehsils(this.value);"></select>');
	 }else if(type == "booth"){
	   $("#constituencySelectDIV").html('<select id="constituencyDispalyId" onchange="getBooths(this.value);"></select>');
	 }
     $("#constituencyDispalyId").append('<option value="0">Select Constituency</option>');
   }
     for(var i in allConstituencies){
	      $("#constituencyDispalyId").append('<option value='+allConstituencies[i].id+'>'+allConstituencies[i].name+'</option>');
	 }
  }
  function showRuralConstituencyDetails(){
     $("#constituencySelectDIV").html('<select id="constituencyDispalyId"  onchange="getPanchayats(this.value);"></select>');
     $("#constituencyDispalyId").append('<option value="0">Select Constituency</option>');
     for(var i in ruralConstis){
	      $("#constituencyDispalyId").append('<option value='+ruralConstis[i].id+'>'+ruralConstis[i].name+'</option>');
	 }
  }
  
  function getTehsils(constiId){
      $("#mandalDispalyId option").remove();
	  if(constiId == 0){
	    return;
	  }
      var jsObj=
		{
			id:constiId,
			task:"subRegionsInConstituency",
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:"null",
			constId:constiId				
		}
    $.ajax({
          type:'GET',
          url: 'locationsHierarchiesAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
     for(var i in result){
	   if(result[i].id == 0){
          $("#mandalDispalyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#mandalDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });	
  }
  function getPanchayats(constiId){
     $("#panchayatDispalyId option").remove();
	  if(constiId == 0){
	    return;
	  }
     $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:"panchayatNames",constituencyId:constiId}
   }).done(function(result){
     $("#panchayatDispalyId").append('<option value="0">ALL</option>');
     for(var i in result){
	   $("#panchayatDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   });
  } 
  function getBooths(constiId){
    $("#boothDispalyId option").remove();
	  if(constiId == 0){
	    return;
	  }
     $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:"boothNames",constituencyId:constiId}
   }).done(function(result){
     $("#boothDispalyId").append('<option value="0">ALL</option>');
     for(var i in result){
	   $("#boothDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   });
  }
  function getReqIds(id){
    var selectedId = $("#"+id).val();
	if(selectedId == null){
	  return "";
	}
	if(selectedId == 0){
	 selectedId = "";
	 var options = $('#'+id+' option');
	 var values = $.map(options ,function(option) {
        return option.value;
     });
	  for(var i in values){
	    if(values[i] != 0){
		   if(selectedId.length > 0){
		     selectedId=selectedId+','+values[i];
		   }else{
		     selectedId = values[i];
		   }
		}
	  }
	}
	return selectedId;
  }
  function getLocationWiseCadreInfo(){
   $("#errMsgDiv").html('');
   $("#locationStatusDialogDIV").html("");
    var locationId = $("#locationsDispalyId").val();
	var reqTask="";
	var reqIds ="";
	var errMsg="";
	
	var startDate = $('#fromDate1').val();
	var endDate	= $('#toDate1').val();
		
	if(locationId == 2){
	  reqTask="stateInfo";
	  reqIds = getReqIds("statesDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select State";
	  }
	}else if(locationId == 3){
	  reqTask="districtInfo";
	  reqIds = getReqIds("districtsDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select District";
	  }
	}else if(locationId == 4){
	  reqTask="assemblyInfo";
	  reqIds = getReqIds("constituencyDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Constituency";
	  }
	}else if(locationId == 5){
	  reqTask="mandalInfo";
	  reqIds = getReqIds("mandalDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Mandal";
	  }
	}else if(locationId == 6){
	  reqTask="panchayatInfo";
	  reqIds = getReqIds("panchayatDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Panchayat";
	  }
	}else if(locationId == 9){
	  reqTask="boothInfo";
	  reqIds = getReqIds("boothDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Booth";
	  }
	}
	if(startDate.trim().length >0 && endDate.trim().length >0)
		{
			var dt1  = parseInt(startDate.substring(0,2),10);
			var mon1 = parseInt(startDate.substring(3,5),10);
			var yr1  = parseInt(startDate.substring(6,10),10);
			var dt2  = parseInt(endDate.substring(0,2),10);
			var mon2 = parseInt(endDate.substring(3,5),10);
			var yr2  = parseInt(endDate.substring(6,10),10);
			
			var date1 = new Date(yr1, mon1, dt1);
			var date2 = new Date(yr2, mon2, dt2);

			if(startDate != "" || endDate != ""){

				if(startDate == ""){
					 errMsg ="From Date should not Empty";
				}
				else if(endDate == ""){
					 errMsg ="To Date should not Empty";
				}					
				if(date2 < date1){ 
					  errMsg ="From Date should not greater than To Date";					
				}
			}	
		}
		
	if(errMsg.length > 0){
	  $("#errMsgDiv").html('<div style="font-weight:bold;color:red;margin-bottom:8px;margin-left:400px;margin-top:5px;">'+errMsg+'</div>');
	  return;
	}
	$("#locationSubmitBtn").attr("disabled","disabled");
	$("#ajaxImgStyleNew").show();
         $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:reqTask,ids:reqIds,fromDate:startDate,toDate:endDate}
	   }).done(function(result){
	       var str='';
	       if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel();" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv"><table class="table table-bordered table-striped table-hover"><thead>';
				str+='<tr>';
				if(locationId == 2){
				    str+='<th>STATE</th>';
			    }else if(locationId == 3){
				    str+='<th>DISTRICT</th>';
			    }else if(locationId == 4){
				    str+='<th>CONSTITUENCY</th>';
			    }else if(locationId == 5){
				    str+='<th>MANDAL/MUNICIPALITY</th>';
			    }else if(locationId == 6){
				    str+='<th>PANCHAYAT</th>';
			    }else if(locationId == 9){
				    str+='<th>BOOTH</th>';
			    }
			    str+='<th>2014 CADRE COUNT</th>';
			    str+='<th>2012 CADRE COUNT</th>';
				str+='</tr>';
				str+='</thead><tbody>';
				for(var i in result){
				  str+='<tr>';
				  str+='  <td>'+result[i].location+'</td>';
				    if(result[i].apCount != null){
				      str+='  <td>'+result[i].apCount+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
					if(result[i].tgCount != null){
				      str+='  <td>'+result[i].tgCount+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
				  str+='</tr>';
				}
				str+='</tbody></table></div>';
				 str+='<input type="button" style="margin-top:15px;margin-left: 375px;" class="btn" onclick="generateExcel();" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#locationStatusDialogDIV").html(str);
		   $("#ajaxImgStyleNew").hide();
		   $("#locationSubmitBtn").removeAttr("disabled");
	   });
  }
  function showHideTabs(id){
     $("#userReportTab").removeClass("selected");
	 $("#locationReportTab").removeClass("selected");
	 $("#userStatusDialogDIV").html("");
	 $("#locationStatusDialogDIV").html("");
     if(id == "userReportTab"){
       $("#userReportTab").addClass("selected");
	   $("#locationWiseCadreInfoDiv").hide();
	   $("#usersWorkingStatusDiv").show();
	 }else{
       $("#locationReportTab").addClass("selected");
	   $("#usersWorkingStatusDiv").hide();
	   $("#locationWiseCadreInfoDiv").show();
	   
	 }
  }
  getDistricts();
  getConstituencies("ALL");
  getConstituencies("rural");
</script>
</body>
</html>