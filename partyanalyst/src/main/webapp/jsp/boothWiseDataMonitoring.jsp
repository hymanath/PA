<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booth Wise Data Monitoring</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/> 
<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/simplePagination.css"/>             
</head> 
<body>
<div class="container">  
	<div class="row">     
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="block">
				<div class="row">
					<div id="errMsgDivId" style="color:red;"></div>
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>SELECT DISTRICT<span style="color:red">*</span>&nbsp;&nbsp;</label>
						<select class="form-control" id="districtId" onchange="getConstituencies(this.value)">
						    <option value="0">Select District</option>
                        </select>
					</div>
                	<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>SELECT CONSTITUENCY</label><span style="color:red"> *</span>
						<select class="form-control" id="constituencyId">
						    <option value="0">Select Constituency</option>
                        </select>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6 m_top20">
                    	<button class="btn btn-success text-capital" id="submitBtnId">submit</button>
					</div>
				</div>
				<div class="row m_top20">
					<div id="verifiactionDivId" style="display:none;">	
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="block bg_F4 pad_20" style="margin-left:20px; margin-right:15px;margin-top:7px;" id="dataverificationId">
								<div class="row">
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">total registrations : <span id="totalRegisteredId"></span></h5>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">verified passed : <span id="verPassedId"></span></h5>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">verifed - junck/rejected : <span id="verRejectedId"></span></h5>
									</div>
									<div class="col-md-3 col-xs-12 col-sm-3">
										<h5 class="text-capital">verification pending : <span id="verfPendingId"></span></h5>
									</div>
								</div>
							</div>
						</div>
					</div>	
				</div>
				<div class="row">
					<img src="./images/icons/search.gif" id="populatingDtsDivImgId" style="margin-left:1070px; margin-top:6px;display:none;" />
					<div class="m_top20 table-responsive col-md-12 col-xs-12 col-sm-6" id="resultDivId"></div>
				</div>
			</div>
        </div> 
    </div>
</div>

<input type="hidden" id="hiddenBoothId"/>

<div class="modal fade" id="issuesDataMonitroingDashboardModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:80%;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" id="mainModelCloseId" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4><span id="modalHeadingId" style="color:red"></span></h4>
        <!--<p id="userDescriptionId"><i>Rahul - 9984845464</i></p>-->
      </div>
      <div class="modal-body">  
<div>
	<input type="radio" class="filterClass" name="fltrCls" value="Total" checked="true" style="cursor:pointer;"/> All &nbsp&nbsp
	<input type="radio" class="filterClass" name="fltrCls" value="Pending" style="cursor:pointer;margin-left: 30px;"/> Not Verified &nbsp&nbsp
	<input type="radio" class="filterClass" name="fltrCls" value="Verified" style="cursor:pointer"/> Verified &nbsp&nbsp
	<input type="radio" class="filterClass" name="fltrCls" value="Rejected" style="cursor:pointer"/> Rejected &nbsp&nbsp
	<input type="radio" class="filterClass" name="fltrCls" value="ImageNotMatched" style="cursor:pointer;margin-left: 30px;"/> Image Not Matched &nbsp&nbsp
	<input type="radio" class="filterClass" name="fltrCls" value="ImproperImage" style="cursor:pointer"/> Improper Image &nbsp&nbsp
	<input type="radio" class="filterClass" name="fltrCls" value="NoImage" style="cursor:pointer"/> No Image &nbsp&nbsp
	
	<!-- Hidden Variables -->
	<input type="hidden" id="hdnCdrSrvyUserId"/>
	<input type="hidden" id="hdnTabUserInfoId"/>
	<!--<input type="hidden" id="hiddenDistrictId"/>
	<input type="hidden" id="hiddenConstituencyId"/>
	<input type="hidden" id="hiddenCadreId"/>
	<input type="hidden" id="hiddenCadreSurveyUserId"/>
	<input type="hidden" id="hiddenTabUserInfoId"/>
	<input type="hidden" id="hiddenReasonId">-->
</div>	  
        <div>
          <!-- Nav tabs -->
          <ul class="nav nav-tabs" id="issuesDataMonitroing" role="tablist">
            <li role="presentation" class="active text-capital"><a href="#self" aria-controls="self" role="tab" data-toggle="tab">voter with self photo</a></li>
            <li role="presentation" class="text-capital"><a href="#relative" aria-controls="relative" role="tab" data-toggle="tab">registered with relative voter id</a></li>
         </ul>
          <!-- Tab panes -->
          <div class="tab-content">      
            <div role="tabpanel" class="tab-pane activeCls active" id="self">
			  <div id="selfTblDivId"></div>
			  <div id="selfPaginationId"></div>    
            </div>
	        <div role="tabpanel" class="tab-pane relativeCls" id="relative">  
			  <div id="relativeDivId"></div>
			  <div id="relativePaginationId"></div>
            </div>
          </div>
        </div>
      </div>    
      <div class="modal-footer">  
      	<p><small>For Bulk Update</small></p>
        <button class="btn btn-success" id="bulkApproveId">Approve</button>
        <button class="btn btn-danger" id="bulkRejectId">Reject</button><br> 
		<span id="globalErrId" style="color:red;" class="pull-right"></span>  
		<span id="globalSuccId" style="color:green;" class="pull-right"></span> 
      </div>
	   
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="rejectedModalId" role="dialog">
    <div class="modal-dialog" style="height:60px;">
      <div class="modal-content">
        <div class="modal-header">    
          <button type="button" class="close closeButtonCls" data-dismiss="modal">&times;</button>  
          <h4 class="modal-title"></h4>
        </div>  
        <div class="modal-body" style="padding-bottom: 98px; padding-right: 0px; padding-top: 7px;">
		 <div class="row">
		    <div class="col-md-4">
			   <select class="form-control" id="rsnSlctBxId">  
		       </select>
			   <span style="color:red;" class="reasonErrorCls"></span>
		   </div>
		   <div class="col-md-2">
		      <button class="btn btn-success" id="submitBtnReasonId">Submit</button>
			  <span style="color:green;" class="reasonSuccessCls"></span>
           </div>
		 </div>
	    <!--<div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>-->
      </div>
     </div>
  </div>
 </div>      
<div id="confirmModalId" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" style="top:70px">
      <div class="modal-dialog modal-sm" role="document" style="width: 30%;">
      <div class="modal-content" style="border: 3px solid #000;">  
		<div class="modal-header">    
          <button type="button" class="close closeButtonCls" data-dismiss="modal">&times;</button>  
          <h4 class="modal-title">Conform Message</h4>            
        </div> 
        <div class="modal-body" >
          <!--<div  style="text-align: center;"><img src="images/groupIcon.png" style="width: 100px;"/></div>-->
          <h4 style="text-align: center;">Are you sure want to approved.</h4> 
          <div style="text-align: center;">
            <button type="button" id="groupingApprovedYes" class="groupingConfirmCls btn btn-success " style="width: 27%; border-radius: 5px; height: 40px;">Yes</button>
            <button type="button" id="groupingApprovedNo"  class="groupingConfirmCls btn btn-danger " data-dismiss="modal"style="width: 27%; border-radius: 5px; height: 40px;" >No</button>
          </div>
		  <span id="successApprovedId" style="color:green;"></span>
		  <span id="errorApprovedId" style="color:red;"></span>  
        </div>
      </div>
      </div>
  </div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<!--<script src="js/dataMonitoring/dataMonitoring.js" type="text/javascript"></script>-->
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>  
<script type="text/javascript" src="newCoreDashBoard/js/simplePagination3.js" ></script>

<script type="text/javascript">
getDistricts();
function getDistricts(){
	$('#districtId').find('option').remove();
	var stateId = 1;
	var jsObj = { 
	      stateId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictListNewAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#districtId").append('<option value="0">Select District</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
	});
}

function getConstituencies(districtId){
	$('#constituencyId').find('option').remove();
	var stateId = 0;
	var jsObj = { 
		districtId :districtId,
		stateId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getConstituencyListAction2.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#constituencyId").append('<option value="0">Select Constituency</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
	});
}

function getOverAllCounts(districtId,constituencyId){
	var jsObj = { 
		districtId :districtId,
		constituencyId : constituencyId
	}
	$.ajax({
		type : 'GET',
		url : 'getOverAllDataVerificationDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null){
			$("#totalRegisteredId").html(result.totalCount);
			$("#verfPendingId").html(result.pendingCount);
			$("#verPassedId").html(result.approvedCount);
			$("#verRejectedId").html(result.rejectedCount);
			
			$("#verifiactionDivId").show();
		}
	});
}

$(document).on("click","#submitBtnId",function(){
	$("#errMsgDivId").html("");
	$("#verifiactionDivId").hide();
	$("#resultDivId").html("");
	
	var districtId = $("#districtId").val();
	var constituencyId = $("#constituencyId").val();
	
	if(districtId == 0){
		$("#errMsgDivId").html("Select District");
		return;
	}
	if(constituencyId == 0){
		$("#errMsgDivId").html("Select Constituency");
		return;
	}
	
	getOverAllCounts(districtId,constituencyId);
	getReasons();
	$("#populatingDtsDivImgId").show();
	
	var jsObj = { 
		districtId :districtId,
		constituencyId : constituencyId
	}
	$.ajax({
		type : 'GET',
		url : 'getBoothWiseDataVerificationDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildBoothWiseResult(result);
		}
		else{
			$("#resultDivId").html('<h4 style="color:red">NO DATA AVAILABLE...</h4>');
			$("#populatingDtsDivImgId").hide();
		}
	});
});

function buildBoothWiseResult(result){
	var str='';
	
	str+='<h4 style="margin-top : 10px;color:red;"><b>BOOTH WISE REGISTRATION DETAILS<b></h4>';
	str+='<table class="table table-bordered" id="boothWiseTableId">';
		str+='<thead>';
			str+='<th>Part No</th>';
			str+='<th>Mandal/Muncipality</th>';
			str+='<th>Panchayat</th>';
			str+='<th>Total Registrations</th>';
			str+='<th>Verification Approved</th>';
			str+='<th>Verification Rejected</th>';
			str+='<th>Verification Pending</th>';
			str+='<th></th>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].partNo+'</td>';
					if(result[i].mandalId != null && result[i].mandalId > 0){
						str+='<td>'+result[i].mandal+' Mandal</td>';
						str+='<td>'+result[i].panchayat+'</td>';
					}
					else{
						str+='<td>'+result[i].leb+' Muncipality</td>';
						str+='<td> - </td>';
					}
					str+='<td>'+result[i].totalCount+'</td>';
					str+='<td>'+result[i].approvedCount+'</td>';
					str+='<td>'+result[i].rejectedCount+'</td>';
					str+='<td>'+result[i].pendingCount+'</td>';
					str+='<td><button class="btn btn-info verifyCls" attr_boothId="'+result[i].boothId+'" attr_partNo="'+result[i].partNo+'">Verify Records</button></td>';
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	
	$("#populatingDtsDivImgId").hide();
	$("#resultDivId").html(str);
	$("#boothWiseTableId").dataTable();
}

function getBoothWiseCadreDetails(boothId,constituencyId,resultType,verificationStatus){
	$("#selfTblDivId").html(' ');
	$("#relativeDivId").html(' ');
	$("#selfTblDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$("#relativeDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	
	var jsObj = { 
		boothId :boothId,
		constituencyId : constituencyId,
		resultType : resultType,
		verificationStatus : verificationStatus
	}
	$.ajax({
		type : 'GET',
		url : 'getBoothWiseRegisteredMemberDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null)
			buildBoothWiseDetails(result,resultType);
	});
}

$(document).on("click",".verifyCls",function(){
	$("#issuesDataMonitroingDashboardModal").modal('show');
	$("#issuesDataMonitroing li").removeClass("active");
	$("#issuesDataMonitroing li:first-child").addClass("active");  
	$(".activeCls").addClass("active");
	$(".relativeCls").removeClass("active");
	
	$('input[name=fltrCls][value=Total]').prop('checked', 'checked');
	var boothId = $(this).attr("attr_boothId");
	var partNo = $(this).attr("attr_partNo");
	var constituencyId = $("#constituencyId").val(); 
	var resultType="All"
	var verificationStatus = "Total";
	
	$("#modalHeadingId").html("PART NO :"+partNo+" REGISTRATION DETAILS");
	$("#hiddenBoothId").val(boothId);
	
	getBoothWiseCadreDetails(boothId,constituencyId,resultType,verificationStatus);
	
});

function buildBoothWiseDetails(result,resultType){
	if(resultType=="All" || resultType=="Self"){  
		var str = '';
		//var selfTotalCount=0;
			str+='<table class="table" id="selfTableId">';
				str+='<thead class="text-capital">';
					str+='<th>captured photo</th>';
					str+='<th>Voter photo</th>';
					str+='<th style="width: 230px;">Verification Status</th>';
					str+='<th>Rejected Reason</th>';
					str+='<th>name</th>';
					str+='<th>mobile number</th>';
					str+='<th>gender</th>';
					str+='<th><input id="globalSelectOwnId" type="checkbox"/></th>';
				str+='</thead>';
				str+='<tbody class="b_1">';
				//selfTotalCount=result[0][0].totalCount;
				if(result.idnameList != null && result.idnameList.length > 0){
					for(var i in result.idnameList){
						if(result.idnameList[i].status == "Approved"){
							str+='<tr>';
								str+='<td>';
									str+='<img src="https://www.mytdp.com/images/cadre_images/'+result.idnameList[i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>';
									str+='<img src="https://www.mytdp.com/voter_images/'+result.idnameList[i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td><img src="images/right.jpg" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td></td>';
								str+='<td>'+result.idnameList[i].name+'</td>';
								str+='<td>'+result.idnameList[i].mobileNo+'</td>';  
								str+='<td>'+result.idnameList[i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					
							/*str+='<tr>';
								str+='<td><img src="images/right.jpg" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td>';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str+='</td>';
							str+='</tr>';*/
						}
						if(result.idnameList[i].status == "Rejected"){
							str+='<tr>';
								str+='<td>';
									str+='<img src="https://www.mytdp.com/images/cadre_images/'+result.idnameList[i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>';
									str+='<img src="https://www.mytdp.com/voter_images/'+result.idnameList[i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td><img src="images/close.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/><button class="btn btn-xs btn-success updateImageBtnId"  style="margin-left: 0px; margin-top: 10px;" attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'" attr_status="'+result.idnameList[i].status+'" attr_divId="successMsgId'+i+'" attr_loading_img_id="changeImgLoadingImgId'+i+'">Approve</button>';
								str+='<button class="btn btn-xs btn-info changeImageBtnId"  style="margin-left: 10px; margin-top: 10px;" attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'" attr_status="'+result.idnameList[i].status+'" attr_divId="successMsgId'+i+'" attr_loading_img_id="changeImgLoadingImgId'+i+'">Change Image</button>';
								str+='<span class="successMsgCls" id="successMsgId'+i+'"></span><span id="changeImgLoadingImgId'+i+'" style="display:none;"><img src="./images/icons/search.gif"/></span></td>';
								str+='<td>';
									str+='<input type="text" value="'+result.idnameList[i].wish+'" class="form-control" disabled></input>';
								str+='</td>';
								str+='<td>'+result.idnameList[i].name+'</td>';
								str+='<td>'+result.idnameList[i].mobileNo+'</td>';
								str+='<td>'+result.idnameList[i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					     
							/*str+='<tr>';
								str+='<td><img src="images/close.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/><button class="btn btn-xs btn-success updateImageBtnId"  style="margin-left: 141px; margin-top: -30px;" attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'" attr_status="'+result.idnameList[i].status+'" attr_divId="successMsgId'+i+'" attr_loading_img_id="changeImgLoadingImgId'+i+'">Approve</button>';
								str+='<button class="btn btn-xs btn-info changeImageBtnId"  style="margin-left: 10px; margin-top: -30px;" attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'" attr_status="'+result.idnameList[i].status+'" attr_divId="successMsgId'+i+'" attr_loading_img_id="changeImgLoadingImgId'+i+'">Change Image</button>';
								str+='<span class="successMsgCls" id="successMsgId'+i+'"></span><span id="changeImgLoadingImgId'+i+'" style="display:none;"><img src="./images/icons/search.gif"/></span></td>';
								str+='<td colspan="3">';
									str+='<input type="text" value="'+result.idnameList[i].wish+'" class="form-control" disabled></input>';    
										//str+='<option></option>';  
									//str+='</select>';
								str+='</td>';
							str+='</tr>';*/
						}
						if(result.idnameList[i].status == "noStatus"){
							str+='<tr>';
								str+='<td>';
									str+='<img src="https://www.mytdp.com/images/cadre_images/'+result.idnameList[i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>';
									str+='<img src="https://www.mytdp.com/voter_images/'+result.idnameList[i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>';  
									str+='<button class="btn btn-success singleApproveCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'">Approve</button>';
									str+='<button class="btn btn-danger singleRejectCls btn-sm"  attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'" style="margin-left: 5px;" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'">Reject</button>';
									str+='<button class="btn btn-sm btn-info changeImageBtnId" style="margin-left: 10px; margin-top: 10px;" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'" attr_status="'+result.idnameList[i].status+'" attr_divId="successMsgId'+i+'" attr_loading_img_id="changeImgLoadingImgId'+i+'">Change Image</button>';
									str+='<span class="successMsgCls" id="successMsgId'+i+'"></span><span id="changeImgLoadingImgId'+i+'" style="display:none;"><img src="./images/icons/search.gif"/></span>';
								str+='</td>'; 
								str+='<td>';
									str+='<select class="select" id="ownHideSelectBoxId'+i+'" style="display:none;">';
										str+='<option value="0">Andhra Pradesh</option>';                
									str+='</select>'; 
									str+='<span id="ownErrorId'+i+'" style="color:red;"></span>';
								str+='</td>';  
								str+='<td>'+result.idnameList[i].name+'</td>';
								str+='<td>'+result.idnameList[i].mobileNo+'</td>';
								str+='<td>'+result.idnameList[i].gender+'</td>';
								str+='<td><input attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'" class="localSelectOwnCls" type="checkbox"/></td>';  
							str+='</tr>'; 
					
					
							/*str+='<tr>';    
								str+='<td>';  
									str+='<button class="btn btn-success singleApproveCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'">Approve</button>';
									str+='<button class="btn btn-danger singleRejectCls btn-sm"  attr_position_id="'+i+'" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'" style="margin-left: 5px;" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'">Reject</button>';
									str+='<button class="btn btn-sm btn-info changeImageBtnId" style="margin-left: 5px;" attr_cadre_id="'+result.idnameList[i].cadreId+'" attr_dist_id="'+result.idnameList[i].districtid+'" attr_const_id="'+result.idnameList[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.idnameList[i].cadreUserId+'" attr_tab_user_id="'+result.idnameList[i].tabUserId+'" attr_status="'+result.idnameList[i].status+'" attr_divId="successMsgId'+i+'" attr_loading_img_id="changeImgLoadingImgId'+i+'">Change Image</button>';
									str+='<span class="successMsgCls" id="successMsgId'+i+'"></span><span id="changeImgLoadingImgId'+i+'" style="display:none;"><img src="./images/icons/search.gif"/></span>';
								str+='</td>'; 
								str+='<td colspan="3">';
									str+='<select class="select" id="ownHideSelectBoxId'+i+'" style="display:none;">';
										str+='<option value="0">Andhra Pradesh</option>';                
									str+='</select>'; 
									str+='<span id="ownErrorId'+i+'" style="color:red;"></span>';
								str+='</td>';    
							str+='</tr>'; */
						}
					}
				}
					
				str+='</tbody>';
			str+='</table>';
			$("#selfTblDivId").html(str);
			$("#selfTableId").dataTable();
			if(result.idnameList != null && result.idnameList.length > 0){
				for(var i in result.idnameList){
					if(result.idnameList[i].status == "noStatus"){
						$("#ownHideSelectBoxId"+i).html(globalSrt);  
					}
				}
			}
			
			/*if(minValue == 0 && selfTotalCount > 40){
				$("#selfPaginationId").pagination({
					items: selfTotalCount,
					itemsOnPage: 40,
					cssStyle: 'light-theme',
					onPageClick: function(pageNumber) {					
						var num=(pageNumber-1)*40;
						getMembersDetails(surveyUserId,tabUserId,0,userName,userMobile,num,"Self",verificationStatus);
					}
				});
			}	*/
		}
		
		if(resultType=="All" || resultType=="Relative"){
			//var relativeTotalCount=0;
			var str2 = '';
			str2+='<table class="table" id="familyTableId">';
				str2+='<thead class="text-capital">';
					str2+='<th>captured photo</th>';
					//str2+='<th>Relative Voter photo</th>';
					str2+='<th style="width: 230px;">Verification Status</th>';
					str2+='<th>Rejected Reason</th>';
					str2+='<th>name</th>';
					str2+='<th>mobile number</th>';
					str2+='<th>gender</th>';
					str2+='<th><input id="globalSelectFamilyId" type="checkbox"/></th>';  
				str2+='</thead>';
				str2+='<tbody class="b_1">';
				if(result.subList1 != null && result.subList1.length > 0){
					for(var i in result.subList1){ 
						//relativeTotalCount=result.subList1[0].totalCount;
						if(result.subList1[i].status == "Approved"){
							str2+='<tr>';
								str2+='<td>';
									str2+='<img src="https://www.mytdp.com/images/cadre_images/'+result.subList1[i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								/*str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/voter_images/'+result.subList1[i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';*/
								str2+='<td><img src="images/right.jpg" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str2+='<td>';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
								str2+='<td>'+result.subList1[i].name+'</td>';
								str2+='<td>'+result.subList1[i].mobileNo+'</td>';
								str2+='<td>'+result.subList1[i].gender+'</td>';
								str2+='<td>';
									//str+='<input type="checkbox"/>';
								str2+='</td>';
							str2+='</tr>';  

					
							/*str2+='<tr>';
								str2+='<td><img src="images/right.jpg" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str2+='<td colspan="3">';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
							str2+='</tr>';*/
						}
						if(result.subList1[i].status == "Rejected"){
							str2+='<tr>';
								str2+='<td>';
									str2+='<img src="https://www.mytdp.com/images/cadre_images/'+result.subList1[i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								/*str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/voter_images/'+result.subList1[i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';*/
								str2+='<td><img src="images/close.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/>';
								str2+='<button class="btn btn-xs btn-success updateImageBtnId"  style="margin-left: 0px; margin-top: 10px;" attr_position_id="'+i+'" attr_cadre_id="'+result.subList1[i].cadreId+'" attr_dist_id="'+result.subList1[i].districtid+'" attr_const_id="'+result.subList1[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.subList1[i].cadreUserId+'" attr_tab_user_id="'+result.subList1[i].tabUserId+'" attr_status="'+result.subList1[i].status+'" attr_divId="successMsgRelId'+i+'" attr_loading_img_id="changeImgLoadingRelImgId'+i+'">Approve</button>';
								str2+='<span class="successMsgCls" id="successMsgRelId'+i+'"></span><span id="changeImgLoadingRelImgId'+i+'" style="display:none;"><img src="./images/icons/search.gif"/></span></td>';
								str2+='<td>';
									str2+='<input type="text" value="'+result.subList1[i].wish+'" disabled></input>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
								str2+='<td>'+result.subList1[i].name+'</td>';
								str2+='<td>'+result.subList1[i].mobileNo+'</td>';
								str2+='<td>'+result.subList1[i].gender+'</td>';
								str2+='<td>';
									//str+='<input type="checkbox"/>';
								str2+='</td>';
							str2+='</tr>';  

					
							/*str2+='<tr>';
								str2+='<td><img src="images/close.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/>';
								str2+='<button class="btn btn-xs btn-success updateImageBtnId"  style="margin-left: 141px; margin-top: -30px;" attr_position_id="'+i+'" attr_cadre_id="'+result.subList1[i].cadreId+'" attr_dist_id="'+result.subList1[i].districtid+'" attr_const_id="'+result.subList1[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.subList1[i].cadreUserId+'" attr_tab_user_id="'+result.subList1[i].tabUserId+'" attr_status="'+result.subList1[i].status+'" attr_divId="successMsgRelId'+i+'" attr_loading_img_id="changeImgLoadingRelImgId'+i+'">Approve</button>';
								str2+='<span class="successMsgCls" id="successMsgRelId'+i+'"></span><span id="changeImgLoadingRelImgId'+i+'" style="display:none;"><img src="./images/icons/search.gif"/></span></td>';
								str2+='<td colspan="3">';
									str2+='<input type="text" value="'+result.subList1[i].wish+'" disabled></input>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
							str2+='</tr>';*/
						}
						if(result.subList1[i].status == "noStatus"){
							str2+='<tr class="familyDeleteRow'+i+'">';
								str2+='<td>';
									str2+='<img src="https://www.mytdp.com/images/cadre_images/'+result.subList1[i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								/*str2+='<td rowspan="2">';
									str2+='<img src="https://www.mytdp.com/voter_images/'+result.subList1[i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';  */
								str2+='<td>';
									str2+='<button class="btn btn-success singleApproveCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result.subList1[i].cadreId+'" attr_dist_id="'+result.subList1[i].districtid+'" attr_const_id="'+result.subList1[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.subList1[i].cadreUserId+'" attr_tab_user_id="'+result.subList1[i].tabUserId+'">Approve</button>';//familyHideSelectBoxId0
									str2+='<button class="btn btn-danger singleRejectCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result.subList1[i].cadreId+'" attr_reason_id="familyHideSelectBoxId'+i+'" style="margin-left: 5px;" attr_dist_id="'+result.subList1[i].districtid+'" attr_const_id="'+result.subList1[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.subList1[i].cadreUserId+'" attr_tab_user_id="'+result.subList1[i].tabUserId+'">Reject</button>';
								str2+='</td>';
								str2+='<td>';
									str2+='<select class="select" id="familyHideSelectBoxId'+i+'" style="display:none;">';      
										str2+='<option value="0">Andhra Pradesh</option>';                  
									str2+='</select>';
									str2+='<span id="familyErrorId'+i+'" style="color:red;"></span>';
								str2+='</td>';    
								str2+='<td>'+result.subList1[i].name+'</td>';
								str2+='<td>'+result.subList1[i].mobileNo+'</td>';
								str2+='<td>'+result.subList1[i].gender+'</td>';
								str2+='<td><input attr_position_id="'+i+'"  attr_cadre_id="'+result.subList1[i].cadreId+'" attr_reason_id="familyHideSelectBoxId'+i+'" attr_dist_id="'+result.subList1[i].districtid+'" attr_const_id="'+result.subList1[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.subList1[i].cadreUserId+'" attr_tab_user_id="'+result.subList1[i].tabUserId+'" class="localSelectFamilyCls" type="checkbox"/></td>';
							str2+='</tr>'; 
							/*str2+='<tr class="familyDeleteRow'+i+'">';
								str2+='<td>';
									str2+='<button class="btn btn-success singleApproveCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result.subList1[i].cadreId+'" attr_dist_id="'+result.subList1[i].districtid+'" attr_const_id="'+result.subList1[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.subList1[i].cadreUserId+'" attr_tab_user_id="'+result.subList1[i].tabUserId+'">Approve</button>';//familyHideSelectBoxId0
									str2+='<button class="btn btn-danger singleRejectCls btn-sm" attr_position_id="'+i+'" attr_cadre_id="'+result.subList1[i].cadreId+'" attr_reason_id="familyHideSelectBoxId'+i+'" style="margin-left: 5px;" attr_dist_id="'+result.subList1[i].districtid+'" attr_const_id="'+result.subList1[i].constitunecyId+'" attr_cadre_survey_user_id="'+result.subList1[i].cadreUserId+'" attr_tab_user_id="'+result.subList1[i].tabUserId+'">Reject</button>';
								str2+='</td>';
								str2+='<td colspan="3">';
									str2+='<select class="select" id="familyHideSelectBoxId'+i+'" style="display:none;">';      
										str2+='<option value="0">Andhra Pradesh</option>';                  
									str2+='</select>';
									str2+='<span id="familyErrorId'+i+'" style="color:red;"></span>';
								str2+='</td>';    
							str2+='</tr>'; */   
						}
					}
				}
					
					
					str2+='</tbody>';
					str2+='</table>';
					$("#relativeDivId").html(str2);
					$("#familyTableId").dataTable();
					if(result.subList1 != null && result.subList1.length > 0){
						for(var i in result.subList1){
							if(result.subList1[i].status == "noStatus"){    
								$("#familyHideSelectBoxId"+i).html(globalSrt);        
							}
						}
					}
					
					//$(".select").chosen({width:'100%'});        
					/*if(minValue == 0 && relativeTotalCount > 40){
						$("#relativePaginationId").pagination({
						items: relativeTotalCount,
						itemsOnPage: 40,
						cssStyle: 'light-theme',
						onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*40;
						getMembersDetails(surveyUserId,tabUserId,0,userName,userMobile,num,"Relative",verificationStatus);
					}
				});
			}	*/
		}
}

$(document).on("click",".filterClass",function(){
		
	var radioVal = $(this).val();
	var boothId = $("#hiddenBoothId").val();
	var constituencyId = $("#constituencyId").val();
	
	getBoothWiseCadreDetails(boothId,constituencyId,"All",radioVal);
});

$(document).on('click','#globalSelectOwnId',function(){
	if($(this).is(':checked')){
		$(".localSelectOwnCls").prop( "checked", true);
		$('.singleApproveCls').prop('disabled', true);
		$('.singleRejectCls').prop('disabled', true);
	}else{
		$(".localSelectOwnCls").prop( "checked", false);
		$('.singleApproveCls').prop('disabled', false);
		$('.singleRejectCls').prop('disabled', false);    
	}
	$('.localSelectOwnCls').each(function(){
		if($(this).is(':checked')){
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).show();
			//to clear error message  
			var position = $(this).attr("attr_position_id");
			$("#ownErrorId"+position).html("");
			$("#globalErrId").html("");				
		}else{
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).hide();
			//to clear error message
			var position = $(this).attr("attr_position_id");
			$("#ownErrorId"+position).html("");
			$("#globalErrId").html("");				
		}  
	});
}); 

$(document).on('click','#globalSelectFamilyId',function(){
	if($(this).is(':checked')){
		$(".localSelectFamilyCls").prop( "checked", true);  
		$('.singleApproveCls').prop('disabled', true);
		$('.singleRejectCls').prop('disabled', true);
	}else{
		$(".localSelectFamilyCls").prop( "checked", false);
		$('.singleApproveCls').prop('disabled', false);
		$('.singleRejectCls').prop('disabled', false);    
	}
	$('.localSelectFamilyCls').each(function(){
		if($(this).is(':checked')){
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).show();
			//to clear error message
			var position = $(this).attr("attr_position_id");
			$("#familyErrorId"+position).html(""); 
			$("#globalErrId").html("");
		}else{    
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).hide(); 
			//to clear error message
			var position = $(this).attr("attr_position_id");
			$("#familyErrorId"+position).html("");  
			$("#globalErrId").html("");
		}  
	});
});

//by selecting single check box show and hide the chexk box. for own tab
$(document).on('click','.localSelectOwnCls',function(){
	if($(this).is(':checked')){
		var selectReasonId = $(this).attr("attr_reason_id");
		$("#"+selectReasonId).show();
		$("#globalErrId").html("");
	}else{
		var selectReasonId = $(this).attr("attr_reason_id");
		$("#"+selectReasonId).hide(); 
		//to clear error message.
		var position = $(this).attr("attr_position_id");
		$("#ownErrorId"+position).html("");	
		$("#globalErrId").html("");
	}
	var count = $("input.localSelectOwnCls:checked").length;
	if(count >= 1){
		$('.singleApproveCls').prop('disabled', true);
		$('.singleRejectCls').prop('disabled', true);
	}else if( count == 0){
		$('.singleApproveCls').prop('disabled', false);
		$('.singleRejectCls').prop('disabled', false);   
	}
	/*var cadreId = $(this).attr("attr_cadre_id");
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	var tabUserInfoId = $(this).attr("attr_tab_user_id");
	var districtId = $(this).attr("attr_dist_id");
	var constituencyId = $(this).attr("attr_const_id");
	var reasonId = $(this).attr("attr_reason_id");
	
	$("#hiddenCadreId").val(cadreId);
	$("#hiddenCadreSurveyUserId").val(cadreSurveyUserId);
	$("#hiddenTabUserInfoId").val(tabUserInfoId);
	$("#hiddenDistrictId").val(districtId);
	$("#hiddenConstituencyId").val(constituencyId);
	$("#hiddenReasonId").val(reasonId);*/
});
//by selecting single check box show and hide the chexk box. for family tab
$(document).on('click','.localSelectFamilyCls',function(){
	
	if($(this).is(':checked')){   
		var selectReasonId = $(this).attr("attr_reason_id");
		$("#"+selectReasonId).show();
	}else{
		var selectReasonId = $(this).attr("attr_reason_id");
		$("#"+selectReasonId).hide(); 
		//to clear error message
		var position = $(this).attr("attr_position_id");  
		$("#familyErrorId"+position).html(""); 
	}
	var count = $("input.localSelectFamilyCls:checked").length;
	if(count >= 1){
		$('.singleApproveCls').prop('disabled', true);
		$('.singleRejectCls').prop('disabled', true);
	}else if( count == 0){
		$('.singleApproveCls').prop('disabled', false);
		$('.singleRejectCls').prop('disabled', false);   
	}
});

//single rejected 
$(document).on("click",".singleRejectCls",function(){
	var cadreId = $(this).attr("attr_cadre_id");
	$("#submitBtnReasonId").attr("attr_cadre_id",cadreId);
	var position = $(this).attr("attr_position_id");
	$("#submitBtnReasonId").attr("attr_position_id",position);
	var districtId = $(this).attr("attr_dist_id");
	$("#submitBtnReasonId").attr("attr_dist_id",districtId);
	var constituencyId = $(this).attr("attr_const_id");
	$("#submitBtnReasonId").attr("attr_const_id",constituencyId);
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	$("#submitBtnReasonId").attr("attr_cadre_survey_user_id",cadreSurveyUserId);
	var tabUserInfoId = $(this).attr("attr_tab_user_id");
	$("#submitBtnReasonId").attr("attr_tab_user_id",tabUserInfoId);
	$(".reasonErrorCls").html('');
	$(".reasonSuccessCls").html('');
	$("#rsnSlctBxId").html(globalSrt);  
	$("#rejectedModalId").modal("show");  
});	
$(document).on('click','#submitBtnReasonId',function(){ 
	$(".reasonErrorCls").html('');  
	var cadreId = $(this).attr("attr_cadre_id");
	var reasonId = $("#rsnSlctBxId").val();
	var position = $(this).attr("attr_position_id"); 
	var distId = $(this).attr("attr_dist_id"); 
	var constId = $(this).attr("attr_const_id"); 
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	var tabUserInfoId = $(this).attr("attr_tab_user_id");
	if(reasonId == 0){  
		$(".reasonErrorCls").html('Please Select Reason...');  
		return;  
	} 
	var rejectList = [];
	rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId,"districtId" : distId,"constitunecyId" : constId,"cadreUserId" : cadreSurveyUserId, "tabUserInfoId" : tabUserInfoId  });
	var singleReject = {"data" : rejectList};
	$.ajax({
		type:'GET',      
		url: 'updateRejectListAction.action',      
		dataType: 'json',
		data: {task:JSON.stringify(singleReject)}  
	}).done(function(result){  
		if(result != null){ 
			if(result.resultCode == 1){      
				$(".reasonSuccessCls").html('Updated Successfully...'); 
			}else{
				$(".reasonSuccessCls").html('Updation Failed...'); 
			}
			setTimeout(function(){ $("#rejectedModalId").modal("hide"); }, 3000);
			$(".closeButtonCls").trigger("click");
			//for hide deleted row(s)
			if($("#self").hasClass('active')){
				$(".ownDeleteRow"+position).remove();   
				$(".ownDeleteRow"+position).remove();
			}else{
				$(".familyDeleteRow"+position).remove();   
				$(".familyDeleteRow"+position).remove();          
			}
					 
		}
	});
});

//bulk reject
$(document).on('click','#bulkRejectId',function(){
	$("#globalSuccId").html(''); 
	$("#globalErrId").html('');     
	var count = 0;
	var checkCount = 0;
	if($("#self").hasClass('active')){
		$('.localSelectOwnCls').each(function(){  
			if($(this).is(':checked')){
				checkCount = checkCount + 1;
				var selectReasonId = $(this).attr("attr_reason_id");
				reasonId = $("#"+selectReasonId).val();
				if(reasonId == 0){
					var position = $(this).attr("attr_position_id");
					$("#ownErrorId"+position).html("please Select Reason");
					count = count + 1;
					return false;         
				}else{
					var position = $(this).attr("attr_position_id");
					$("#ownErrorId"+position).html("");  
				}  
			}
		});
	}else{
		$('.localSelectFamilyCls').each(function(){  
			if($(this).is(':checked')){
				checkCount = checkCount + 1;
				var selectReasonId = $(this).attr("attr_reason_id");
				reasonId = $("#"+selectReasonId).val();
				if(reasonId == 0){
					var position = $(this).attr("attr_position_id");
					$("#familyErrorId"+position).html("please Select Reason");
					count = count + 1;
					return false;         
				}else{
					var position = $(this).attr("attr_position_id");  
					$("#familyErrorId"+position).html("");  
				}  
			}
		});
	}
	if(count > 0){  
		return;    
	}   
	if(checkCount == 0){
		$("#globalErrId").html("Please Select Atleast One member");  
		return;    
	}  		
	var cadreId = '';
	var selectReasonId = '';
	var reasonId = '';
	var positionIdArr = [];
	var position = 0;
	var rejectList = [];
	if($("#self").hasClass('active')){
		$('.localSelectOwnCls').each(function(){
			if($(this).is(':checked')){
				cadreId = $(this).attr("attr_cadre_id");
				var distId = $(this).attr("attr_dist_id"); 
				var constId = $(this).attr("attr_const_id"); 
				var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
				var tabUserInfoId = $(this).attr("attr_tab_user_id");
				selectReasonId = $(this).attr("attr_reason_id");
				reasonId = $("#"+selectReasonId).val();
				rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId, "districtId" : distId,"constitunecyId" : constId,"cadreUserId" : cadreSurveyUserId, "tabUserInfoId" : tabUserInfoId}); 
				//collect row position for delete row.
				position = $(this).attr("attr_position_id");
				positionIdArr.push(position);
			}
		});
	}else{
		$('.localSelectFamilyCls').each(function(){
			if($(this).is(':checked')){
				cadreId = $(this).attr("attr_cadre_id");
				var distId = $(this).attr("attr_dist_id"); 
				var constId = $(this).attr("attr_const_id"); 
				var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
				var tabUserInfoId = $(this).attr("attr_tab_user_id");
				selectReasonId = $(this).attr("attr_reason_id");
				reasonId = $("#"+selectReasonId).val();
				rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId, "districtId" : distId,"constitunecyId" : constId,"cadreUserId" : cadreSurveyUserId, "tabUserInfoId" : tabUserInfoId});
				//collect row position for delete row.
				position = $(this).attr("attr_position_id");
				positionIdArr.push(position);      
			}
		});
	}
	
	var singleReject = {"data" : rejectList};        
	$.ajax({
		type:'GET',      
		url: 'updateRejectListAction.action',      
		dataType: 'json',
		data: {task:JSON.stringify(singleReject)}  
	}).done(function(result){
		if(result != null){  
			if(result.resultCode == 1){ 
				$("#globalSuccId").html('Updated Successfully...'); 
				//for hide deleted row(s)
				if($("#self").hasClass('active')){
					$(".localSelectOwnCls").prop( "checked",false);
					for(var i in positionIdArr){
						$(".ownDeleteRow"+positionIdArr[i]).remove();   
						$(".ownDeleteRow"+positionIdArr[i]).remove();
					}
				}else{
					$(".localSelectFamilyCls").prop( "checked",false);    
					for(var i in positionIdArr){
						$(".familyDeleteRow"+positionIdArr[i]).remove();   
						$(".familyDeleteRow"+positionIdArr[i]).remove();       
					}     
				}
				$('.singleApproveCls').prop('disabled', false);
				$('.singleRejectCls').prop('disabled', false);      
			}else{
				$("#globalErrId").html('Updation Failed...');     
			}
		}else{    
		}
	});
});
//single approve
$(document).on("click",".singleApproveCls",function(){  
$("#successApprovedId").html('');
	var cadreId = $(this).attr("attr_cadre_id");
	var position = $(this).attr("attr_position_id");
	var districtId = $(this).attr("attr_dist_id");
	var constituencyId = $(this).attr("attr_const_id");
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	var tabUserInfoId = $(this).attr("attr_tab_user_id");
	$("#groupingApprovedYes").attr("attr_position_id",position);
	$("#groupingApprovedYes").attr("attr_cadre_id",cadreId);
	$("#groupingApprovedYes").attr("attr_dist_id",districtId);
	$("#groupingApprovedYes").attr("attr_const_id",constituencyId);
	$("#groupingApprovedYes").attr("attr_cadre_survey_user_id",cadreSurveyUserId);
	$("#groupingApprovedYes").attr("attr_tab_user_id",tabUserInfoId);
	$("#confirmModalId").modal("show");      
});
$(document).on("click","#groupingApprovedYes",function(){
	var cadreId = $(this).attr("attr_cadre_id");
	var position = $(this).attr("attr_position_id");
	var distId = $(this).attr("attr_dist_id");
	var constId = $(this).attr("attr_const_id");
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	var tabUserInfoId = $(this).attr("attr_tab_user_id");
		
	var rejectList = [];
	rejectList.push({"cadreId" : cadreId,"districtId" : distId,"constitunecyId" : constId,"cadreUserId" : cadreSurveyUserId, "tabUserInfoId" : tabUserInfoId}); 
	var singleReject = {"data" : rejectList};
	$.ajax({
		type:'GET',      
		url: 'updateApproveListAction.action',      
		dataType: 'json',
		data: {task:JSON.stringify(singleReject)}  
	}).done(function(result){
		if(result != null){  
			if(result.resultCode == 1){ 
				$("#successApprovedId").html('Updated Successfully...'); 
			}else{
				$("#errorApprovedId").html('Updation Failed...');       
			} 
			setTimeout(function(){ $("#confirmModalId").modal("hide"); }, 2000);
			$(".closeButtonCls").trigger("click");      
			//for hide deleted row(s)
			if($("#self").hasClass('active')){
				$(".ownDeleteRow"+position).remove();   
				$(".ownDeleteRow"+position).remove();
			}else{
				$(".familyDeleteRow"+position).remove();   
				$(".familyDeleteRow"+position).remove();              
			}
		}else{    
		}
	});  
});

//bulk approve      
$(document).on('click','#bulkApproveId',function(){  
	var isMemberSelected="NO";
	if($("#self").hasClass('active')){
		$('.localSelectOwnCls').each(function(){  
			if($(this).is(':checked')){
				isMemberSelected="Yes";
			}
		});
	}
	$('.localSelectFamilyCls').each(function(){  
		if($(this).is(':checked')){
			isMemberSelected="Yes";
		}
	});
	if(isMemberSelected == "NO"){
		$("#globalErrId").html("Please Select Atleast One Member.");
		return;       
	}    
	$("#globalErrId").html(' ');  
	var cadreId = '';	
	var rejectList = []; 
	var positionIdArr = [];
	var position = 0;
	if($("#self").hasClass('active')){
		$('.localSelectOwnCls').each(function(){  
			if($(this).is(':checked')){
				cadreId = $(this).attr("attr_cadre_id");
				var distId = $(this).attr("attr_dist_id"); 
				var constId = $(this).attr("attr_const_id"); 
				var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
				var tabUserInfoId = $(this).attr("attr_tab_user_id");
				rejectList.push({"cadreId" : cadreId,"districtId" : distId,"constitunecyId" : constId,"cadreUserId" : cadreSurveyUserId,"tabUserInfoId" : tabUserInfoId});
				//collect row position for delete row.
				position = $(this).attr("attr_position_id");
				positionIdArr.push(position);
			}
		});
	}else{
		$('.localSelectFamilyCls').each(function(){    
			if($(this).is(':checked')){
				cadreId = $(this).attr("attr_cadre_id");
				var distId = $(this).attr("attr_dist_id"); 
				var constId = $(this).attr("attr_const_id"); 
				var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
				var tabUserInfoId = $(this).attr("attr_tab_user_id");
				rejectList.push({"cadreId" : cadreId,"districtId" : distId,"constitunecyId" : constId,"cadreUserId" : cadreSurveyUserId,"tabUserInfoId" : tabUserInfoId});
				//collect row position for delete row.
				position = $(this).attr("attr_position_id");
				positionIdArr.push(position);
			}  
		});
	}
	var singleReject = {"data" : rejectList};        
	$.ajax({
		type:'GET',      
		url: 'updateApproveListAction.action',      
		dataType: 'json',
		data: {task:JSON.stringify(singleReject)}  
	}).done(function(result){
		if(result != null){  
			if(result.resultCode == 1){ 
				$("#globalSuccId").html('Updated Successfully...');
				//for hide deleted row(s)
				if($("#self").hasClass('active')){
					 
					for(var i in positionIdArr){
						$(".ownDeleteRow"+positionIdArr[i]).remove();   
						$(".ownDeleteRow"+positionIdArr[i]).remove();
					}  
				}else{
					
					for(var i in positionIdArr){
						$(".familyDeleteRow"+positionIdArr[i]).remove();     
						$(".familyDeleteRow"+positionIdArr[i]).remove();      
					}	
				}
				$('.singleApproveCls').prop('disabled', false);    
				$('.singleRejectCls').prop('disabled', false);
			}else{
				$("#globalErrId").html('Updation Failed...');   
			}
		}else{        
		}
	});
});
$(document).on("click","#submitBtnReasonId",function(){
	var cadreId = $(this).attr("attr_cadre_id");
	var reasonId = $("#rsnSlctBxId").val();
	if(reasonId == 0){  
		$(".reasonErrorCls").html("Please Select Reason.");
		return;
	}
	$(".reasonErrorCls").html(' ');
});
$(document).on("click","#groupingApprovedNo",function(){
	var cadreId = $(this).attr("attr_cadre_id");
	$("#confirmModalId").modal("hide");  
});   
$(document).on('click','.closeButtonCls',function(){  
	setTimeout(function(){
		$('body').addClass("modal-open");
	}, 500);    
});
$(document).on('click','#bulkApproveId',function(){
	//$("#getRegStatusId").trigger("click");
}); 
$(document).on('click','#bulkRejectId',function(){
	//$("#getRegStatusId").trigger("click");    
});

var globalSrt = '';
function getReasons(){
	$.ajax({
		type:'GET',
		url: 'getReasonAction.action',           
		dataType: 'json',
		data: {}
	}).done(function(result){     
		
		if(result != null && result.length > 0){
			globalSrt = '';  
			globalSrt+='<option value="'+0+'">Select Reason</option>';
			for(var i in result){
				globalSrt+='<option value="'+result[i].id+'">'+result[i].name+'</option>';  
			}
		}else{
			
		}     
	});
}

$(document).on('click','.changeImageBtnId',function(){  
	var cadreId = $(this).attr("attr_cadre_id");
	var distId = $(this).attr("attr_dist_id"); 
	var constId = $(this).attr("attr_const_id");
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	var tabUserInfoId = $(this).attr("attr_tab_user_id");
	var status = $(this).attr("attr_status");
	var divId = $(this).attr("attr_divId");
	var loadingImgId = $(this).attr("attr_loading_img_id");
	$("#"+divId).html("");
	$(".successMsgCls").html("");
	$("#"+loadingImgId).show();
	  
	var jsObj = {
		cadreId :cadreId,
		districtId : distId,
		constitunecyId : constId ,
		cadreUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		status : status
	}
	
	$.ajax({
		type:'GET',      
		url: 'changeImageByVoterImageAction.action',      
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null){
			if(result.message == "Updated Successfully")
				$("#"+divId).html('<span style="color:green">'+result.message+'</span>');
			else
				$("#"+divId).html('<span style="color:red">Sorry,Exception Occured.Try Again...</span>');
		}
		else
			$("#"+divId).html('<span style="color:red">Sorry,Exception Occured.Try Again...</span>');
		$("#"+loadingImgId).hide();
	});
});

$(document).on('click','.updateImageBtnId',function(){  
	var cadreId = $(this).attr("attr_cadre_id");
	var distId = $(this).attr("attr_dist_id"); 
	var constId = $(this).attr("attr_const_id");
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
	var tabUserInfoId = $(this).attr("attr_tab_user_id");
	var status = $(this).attr("attr_status");
	var divId = $(this).attr("attr_divId");
	var loadingImgId = $(this).attr("attr_loading_img_id");
	$("#"+divId).html("");
	$(".successMsgCls").html("");
	$("#"+loadingImgId).show();
	  
	var jsObj = {
		cadreId :cadreId,
		districtId : distId,
		constitunecyId : constId ,
		cadreUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		status : status
	}
	
	$.ajax({
		type:'GET',      
		url: 'updateRejectedImagesAction.action',      
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null){
			if(result == "success")
				$("#"+divId).html('<span style="color:green">Approved Successfully...</span>');
			else
				$("#"+divId).html('<span style="color:red">Sorry,Exception Occured.Try Again...</span>');
		}
		else
			$("#"+divId).html('<span style="color:red">Sorry,Exception Occured.Try Again...</span>');
		$("#"+loadingImgId).hide();
	});
});
</script>
</body>
</html>