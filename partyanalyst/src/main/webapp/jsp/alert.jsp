<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alert </title>
	<!-- Bootstrap -->
	<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
	<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
	<link href="dist/Alert/custom.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
	<!-- JQuery files (Start) -->
	
	<script src="dist/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<link href="dist/Appointment/custom.css" rel="stylesheet" type="text/css">
	<script src="dist/Appointment/DropkickNew/dropkick.2.1.8.min.js" type="text/javascript"></script>
	<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
	<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	<script src="dist/CreateAlert/createAlert.js" type="text/javascript"></script>
	<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
	<script src="dist/newmultiselect/chosen.jquery.min.js" type="text/javascript"></script>
	<style type="text/css">
	.onoffswitch {
        position: relative; width: 70px;
        -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
    }
    .onoffswitch-checkbox {
        display: none;
    }
    .onoffswitch-label {
        display: block; overflow: hidden; cursor: pointer;
        border-radius: 20px;
    }
    .onoffswitch-inner {
        display: block; width: 200%; margin-left: -100%;
        transition: margin 0.3s ease-in 0s;
    }
    .onoffswitch-inner:before, .onoffswitch-inner:after {
        display: block; float: left; width: 50%; height: 23px; padding: 0; line-height: 23px;
        font-size: 14px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
        box-sizing: border-box;
    }
    .onoffswitch-inner:before {
        content: "+ Ve";
        padding-left: 10px;
        background-color: #34A7C1; color: #FFFFFF;
    }
    .onoffswitch-inner:after {
        content: "- Ve";
        padding-right: 10px;
        background-color: #d9534f; color: #FFF;
        text-align: right;
    }
    .onoffswitch-switch {
        display: block; width: 18px; margin: 5px;
        background: #FFFFFF;
        position: absolute; top: 0; bottom: 0;
        right: 40px;
        border-radius: 20px;
        transition: all 0.3s ease-in 0s; 
		height:17px;
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
        margin-left: 0;
    }
    .onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
        right: 0px; 
    }
	.involveBlock
	{
		background:#F8F9FB;
		border:1px solid #ddd;
		padding:5px;
		margin-top:5px;
	}
	.involveBlockNew
	{
		border:1px dashed #ddd;
		background:#F8F9FB;
		height:105px;
		cursor:pointer;
	}
	.involveBlockNew .media-left , .involveBlockNew .media-body
	{
		padding:17px;
		position:relative;
	}
	.text-capital
	{
		text-transform:uppercase;
	}
	.removeIconNew
	{
		position:absolute;
		top:0px;
		right:15px;
		display:none;
		cursor:pointer;
	}
	.involveBlock:hover .removeIconNew
	{
		display:block;
	}
	.involveBlock:hover
	{
		box-shadow:0px 0px 5px 2px rgba(0,0,0,0.4)
	}
	.bg_EF
	{
		background:#EFF3F4
	}
	.bg_cc
	{
		background:#ccc;
	}
	.m_top10
	{
		margin-top:10px;
	}
#searchedMembersId_length,#searchedMembersId_info{display:none;}
	</style>
	</head>  	
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default">
            	<div class="panel-heading bg_cc">
                	<h4 class="panel-title">NEW ALERT</h4>
                </div>
				<form id="saveAlertForm" name="saveAlertForm" enctype="multipart/form-data" action="saveAlertAction.action" method="POST">
                <div class="panel-body bg_EF">
                	<div class="row">
                    	<div class="col-md-4 col-sm-6 col-xs-12">
                        	<label>Select Alert Type</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="alertTypeId" name="alertVO.alertTypeId" onchange="updateStateDetails(this.value);">
                            	<option value="0">Select Alert</option>
                            </select>
                        </div>
						<div class="col-md-4 col-sm-6 col-xs-12">
                        	<label>Select Alert Impact</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="alertImpactId" name="alertVO.alertImpactId">
                            	<option value="0">Select Alert Impact</option>
                            </select>
                        </div>
                    </div>
                    <div class="row m_top10">
                    	
                        <div class="col-md-3 col-sm-6 col-xs-12">
                        	<label>Information Source For Alert</label><span class="text-danger">*</span>
                            <select class="dropkickClass"  id="alertSourceId" name="alertVO.alertSourceId" >
                            	<option value="0"> Select Alert Source </option>
                            </select>  
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                        	<label>Alert Severity</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="alertSeverityId" name="alertVO.severity">
                            		<option value="0">Select Alert Severity</option>
												<option value="1">High</option>
												<option value="2">Medium</option>
												<option value="3">Low</option>
                            </select>
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12">
                        	<label>Alert Location Level</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="alertlevelId1" attr-index="1" onchange="disableByLevel(1);" >
											<option value="2">State</option>
											 <option value="3">District</option>
											 <option value="4">Constituency</option>
											 <option value="5">Mandal/Muncipality</option>
											 <option value="6">Village/Ward</option>
                            </select>
                        </div>
                        <div class="col-md-2 col-sm-6 col-xs-12 stateShowCls1">
                        	<label>State</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="stateId1" onChange="getDistrictsForReferPopup(1);" name="alertVO.stateId">
											 <option value="0">Select State</option>            
								 			 <option value="1"> Andhra Pradesh </option>      
								 			 <option value="36"> Telangana </option>       
                            </select>
                        </div>
                        <div class="col-md-2 col-sm-6 col-xs-12 locationsFilterCls distCls1">
                        	<label>District</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="referdistrictId1" onChange="getConstituenciesBydistrictForReferPopup(1);" name="alertVO.districtId">
											 <option value="0">Select District</option></select>
                            </select>
                        </div>
                        <div class="col-md-2 col-sm-6 col-xs-12 locationsFilterCls constiCls1">
                        	<label>Constituency</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="referconstituencyId1" onChange="getMandalsByConstituencyForReferPopup(1);" name="alertVO.constituencyId">
							<option value="0">Select Assembly</option>
                            </select>
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12 locationsFilterCls mandalCls1">
                        	<label>Mandal/Muncipality/Cor</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="refermandalNameId1" onChange="getPanchayatsForReferPopup(1);" name="alertVO.tehsilId">
								<option value="0">Select Mandal/ Municipality</option>
                            </select>
                        </div>
                        <div class="col-md-3 col-sm-6 col-xs-12 locationsFilterCls panchayatCls1">
                        	<label>Panchayat/Ward/Division</label><span class="text-danger">*</span>
                            <select class="dropkickClass" id="referpanchayatId1" name="alertVO.panchayatId">
											<option value="0">Select Panchayat/Ward</option>
                            </select>
                        </div>
                    </div>
					
					<div class="row m_top10">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<label>Alert Title : </label><span class="text-danger">*</span>
							 <label class="radio-inline">
                            	<input type="radio" value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"/>Telugu
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"/>English
                            </label>
							 <div class="row m_top10">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<input type="text" class="form-control " id="alertTitleId" name="alertVO.title"></textarea>
								</div>
							</div> 
                        </div>
                    </div>
					
                    <div class="row m_top10">
						<label style="margin-left:15px;"> Description : </label><span class="text-danger">*</span>
                    	<div class="col-md-12 col-sm-12 col-xs-12">
                        	<textarea class="form-control alertclearCls" id="alertdescriptionId" name="alertVO.desc"></textarea>
                        </div>
                    </div> 
                    <div class="row m_top10">
                    	<div class="col-md-12 col-sm-12 col-xs-12 m_top10">
                        	<h4 class="text-success text-capital">Involve members linking to this alert<small class="text-muted" id="involvedMembers">(0 - Members added)</small></h4>
                        </div>
						
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="row membersBlock"></div>
						</div>
						
                        <div class="col-md-3 col-xs-12 col-sm-6 m_top10">
                        	<div class="involveBlockNew" btn-attr = "involve">
                            	<div class="media">
                                	<div class="media-left" style="font-size:36px">
                                    	+
                                    </div>
                                    <div class="media-body">
                                    	Click to Search Involved Members Link to this alert
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
					
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'UPDATE_ALERT_ENTITLEMENT')}">
						 <div class="row m_top10">
                    	<div class="col-md-12 col-sm-12 col-xs-12 m_top10">
                        	<h4 class="text-success text-capital">Assign members linking to this alert<small class="text-muted" id="assignedMembers">(0 - Members added)</small></h4>
                        </div>
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div class="row assignedMembersBlock"></div>
						</div>
						
                        <div class="col-md-3 col-xs-12 col-sm-6 m_top10">
                        	<div class="involveBlockNew" btn-attr = "assign">
                            	<div class="media">
                                	<div class="media-left" style="font-size:36px">
                                    	+
                                    </div>
                                    <div class="media-body">
                                    	Click to Search Assigned Members Link to this alert
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
					
					</c:if>
					<!-- Assign End  -->
					<div class="row m_top10">
                    	<div class="col-md-12 col-sm-12 col-xs-12 m_top10">
                        	<h4 class="text-success text-capital">Upload Document linking to this alert</h4>
                        </div>
						<div class="col-md-6 col-sm-12 col-xs-12 m_top10">    
							<div class="fileBlockNew">
								<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
									<div class="media-left">
										<input type="file" id="uploadFileId0" name="imageForDisplay" class="btn btn-mini"/>
										<div id="extraUploadFileDiv"></div>  
									</div>
									<button type="button" style="margin-right: 220px;"class="btn btn-primary btn-xs pull-right m_top20" id="addFile">
										<i class="glyphicon glyphicon-plus"></i>
									</button>
								</div>   
							</div>
						</div>
                    </div>  
					
					
					<div class="row m_top10">

						<div class="col-md-3 col-xs-12 col-sm-3 m_top10">
						<div style="font-weight:bold;color:red;font-size:15px;" class="col-md-12" id="errorDiv1"></div>
							<button type="button" class="btn btn-success btn-block btnNewCustom1" onclick="createAlert();" id="addThisalertId">CREATE ALERT</button>
							<span class="m_top10" id="createAlertajaxImg"></span>
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

<input type="file" class="btn btn-mini cloneFileCls" style="display:none;"/>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width:90%" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Search Members</h4>
      </div>
      <div class="modal-body">
        <div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
					<div style="background:#fff">
					<div class="col-md-3 advanceSearchCls">
						<label>Search Type</label>
						<select class="dropkickClass"  id="advanceSearchTypeId" onchange="showHideBySearchType();buildLevels();">
							<option value="0">Select Search Type</option>
							<option value="name">Name</option>
							<option value="mobileno">Mobile No</option>
							<option value="mebershipno">Membership No</option>
							<option value="votercardno">Voter Id Card No</option>
							<option value="PR">Public Representative</option>
							<option value="committee">Party Committee</option>
						</select>  
					</div>
					
					
					 <div class="col-md-6 col-xs-12 col-sm-6 advanceSearchCls advanceprclsDiv">     
						<label class="advanceNameCls" id="searchNameLabel">Search By <b>NAME / MEMBERSHIP NO / VOTER ID CARD NO</b></label>
						<input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId">
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6 ">  
						<label class="advancePRCls">Search Designation</label>
						 <select class="advancePRCls dropkickClass"  id="advanceDesignationId" onchange="getLevelByDesignation();">
							<option value="0">Select Designation</option>
						</select>
						<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
					</div>    
					
						
						<div class="col-md-3 col-xs-12 col-sm-6  levelShowCls" >
							<label>Level</label>
							<select class="dropkickClass" id="alertlevelId" attr-index="0" onchange="disableByLevel('');" >
							<option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Muncipality</option>
							 <option value="6">Village/Ward</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6  stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" id="stateId" onChange="getDistrictsForReferPopup('');">
								 <option value="0">All</option>
								 <option value="1">AP</option>
								 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
							 <option value="0">All</option></select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
							<option value="0">All</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup('');" >
								<option value="0">All</option>
							 </select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6  locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" >
							<option value="0">All</option>
							</select>
						</div>
						
						
						
						<div>
									<div class="advanceCadreCommittee" id="referCommitteeDiv">
									 <div class="col-md-3 col-xs-12 col-sm-6 ">
										<label>Select Committee</label>
										<select id="referCommitteeId" class="dropkickClass" >
											<option value="0">All</option>
											<option value="1">Main</option><option value="2">Telugu Yuvatha</option>
											<option value="3">Telugu Mahila</option><option value="4">Telugu Rythu</option>
											<option value="17">Trade</option><option value="6">BC Cell</option><option value="7">SC Cell</option>
											<option value="8">ST Cell</option><option value="9">Minority Cell</option><option value="18">Christian</option>
											<option value="11">TNSF (Student Union)</option><option value="5">TNTUC</option><option value="15">TSNV (Technical Expert Cell)</option>
											<option value="10">Legal Cell</option><option value="16">Doctor Cell</option><option value="20">Kallu Geetha Karmikulu</option>
											<option value="21">Chenetha</option><option value="19">Telugu Rakshana Vedika</option><option value="14">TNUS ( Teachers Union) </option>
											<option value="12">Commercial Cell</option><option value="13">Cultural Cell</option>
										</select>
									  </div>
									</div> 
									<div >
										<div class="col-md-6 col-xs-12 col-sm-6  advanceCadreCommittee">
											<select id="cadreCommitteeDiv" multiple class="" style="width:250px !important;"></select>
											<div id="representativesDiv"></div>
											<div id="referRoleErrorDiv"></div>
										</div>
									</div>
								</div></br>
								
								
				


											<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
						<div class="col-md-2 col-xs-12 col-sm-4">
							<button type="button" class="btn btn-block btn-success m_top20 advancedSearchBtn btnNewCustom1" onclick="handleBySearchType();" id="searchBtnId"  style="margin-top: 25px;display:none;">Search Member</button>
						</div>
						<div class="col-md-1 col-xs-12 col-sm-12">
							<img src="images/search.gif" style="display:none;" id="ajaxImgForAppintId"/>
						</div>
							<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
						
						<div class="row m_top25">
						<div class="col-md-12 col-sm-12 col-xs-12" id="clearSerchDivId">
							<div id="apptmemberDetailsDiv" class="table-responsive"></div>
						</div>
					</div>
						</div>
						</div>
						</div>
				</div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="myModalConformation" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content" style="background:rgba(0,0,0,0.8);">
		<div class="modal-body">
			<button type="button" style="color:#fff;" class="close modalCloseAndShow" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<div class="media" style="color:#fff;">
				<div class="media-left">
					<img src="img/alert.png" class="media-object" />
				</div>
				<div class="media-body" id="duplicateCandidateBlock">
					
				</div>
			</div>
			<p class="text-capital" style="color:yellow;" id="memberConfirmation">already added member to this alert</p>
		</div>
	</div>
  </div>
</div>
		<!-- language convertion-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script type="text/javascript">
 
$(document).on("click",".modalCloseAndShow",function(){
	$("#myModalConformation").modal('hide');
	$("#myModal").modal('show');
});
var btnAttr;
$(document).on("click",".involveBlockNew",function(){
	$("#myModal").modal('show');
	 $("#apptmemberDetailsDiv").html("");
	 $("#advanceSearchTypeId").val(0);
	 
	 btnAttr = $(this).attr("btn-attr");
	
	var select = new Dropkick("#advanceSearchTypeId");
				select.refresh();	
		showHideBySearchType();
					
})
// Load the Google Transliterate API
   google.load("elements", "1", {
         packages: "transliteration"
       });

</script> 
<script type="text/javascript">


 var control;
	var lang;
	var cloneCount=0;
	var involvedCadreIds = [];
	var assignCadreIds = [];
	/* var globalSelectedMemberIdsArr = []; */
   $(document).on("click",".apptDetailsDiv",function(){
		console.log($(this).attr("attr_name"));
		 if($(this).is(':checked')){
		
			 $("#involvedCandidatesDiv").show();
			 $(".membersBlock").show();
			  var name  = $(this).attr("attr_name");
			  var image = $(this).attr("attr_img_url");
			  var attrId = $(this).attr("attr_id");
			  var attrConsti =  $(this).attr("attr-consti");
			   var mobile = $(this).attr("attr_mobile");
			/* $(".membersBlock").append('<div class="block"><input type="hidden" class="form-control candidatecls"  name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'" /><div id="memberDiv'+attrId+'" class="row m_top10"><div class="col-md-3 col-md-offset-1"><p>Name : '+name+'</p></div>  <div class="col-md-3"><p>Constituency : '+attrConsti+' </p></div><span class="closeIcon" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove"></i></span></span><div class="col-md-3"><label>Alert Impact</label><select class="form-control"  id="alertImpactId" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive </option>	<option value="2">Negative </option></select></div></div></div>');*/
			var str ='';
				/* str+='<div id="involveBlockParent'+attrId+'"  class="col-md-3 col-xs-12 col-sm-6">';
				str+='<div class="involveBlock" attr_cadreId="'+attrId+'">'; */
				str+='<div class="col-md-3 col-xs-12 col-sm-6">';
				str+='<div class="involveBlock">';
				str+='<div class="media"><div class="media-left">';
				str+='<img src="'+image+'" onerror="setDefaultImage(this);" alt="image" style="height:30px;width:30px;" class="img-circle">';
				str+='</div>';
				str+='<div class="media-body">';
				if(btnAttr == "involve")
				{
					str+='<input type="hidden" class="form-control memberDatacls" name="alertVO.idNamesList['+cloneCount+'].id" value="'+attrId+'"/>';
				}
				else
				{
					str+='<input type="hidden" class="form-control assignmemberDatacls" name="alertVO.assignList['+cloneCount+'].id" value="'+attrId+'"/>';
				}
				
				str+='<div class="col-md-12"><b>'+name+'</b></div>';
				str+='<div class="col-md-12"><b>'+mobile+'</b></div>';
				str+='<div class="col-md-12"><label>'+attrConsti+'</label></div>';
				str+='<div class="col-md-12">';
			if(btnAttr == "involve")
			{
				str+='<div class="form-inline">';
				//str+='<select class="form-control" name="alertVO.idNamesList['+cloneCount+'].orderId"><option value="1">Positive</option><option value="2">Negative</option></select>';
				str+='<div class="onoffswitch" style="display:inline-block">';
				str+='<input type="checkbox"  name="alertVO.idNamesList['+cloneCount+'].name" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch'+cloneCount+'" checked>';
				str+='<label class="onoffswitch-label" for="myonoffswitch'+cloneCount+'">';
				str+='<span class="onoffswitch-inner"></span>';
				str+='<span class="onoffswitch-switch"></span>';
				str+='</label>';
				str+='</div>';
				str+='</div>';
			}
			str+='</div></div></div>';
			if(btnAttr == "involve")
			{
				str+='<span class="closeIcon" btn-type="involve" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove removeIconNew" style="display:block;"></i></span></div></div>';
				$("#duplicateCandidateBlock").html('');
			}
			else
			{
				str+='<span class="closeIcon" btn-type="assign" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove removeIconNew" style="display:block;"></i></span></div></div>';
				$("#duplicateCandidateBlock").html('');
				
			}
			if(btnAttr == "involve")
			{
					if(jQuery.inArray(attrId, involvedCadreIds) == -1 )
					{
						
						involvedCadreIds.push(attrId);	
						$(".membersBlock").append(str);
						$("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
						var addStr ='';
						addStr+='<p class="text-capital" >'+name+'</p>';
						addStr+='<p>'+mobile+'</p>';
						addStr+='<p class="text-capitalize">'+attrConsti+'</p>';
						$("#duplicateCandidateBlock").html(''+addStr+'');
						$("#memberConfirmation").html("Member Added");
						
						$("#myModalConformation").modal('show');
						setTimeout(function(){ $("#myModalConformation").modal('hide');
							 }, 2000);
							 setTimeout(function(){ $("body").addClass("modal-open");	
						}, 3000);
					}else{
						var duplicateStr ='';
						duplicateStr+='<p class="text-capital" >'+name+'</p>';
						duplicateStr+='<p>'+mobile+'</p>';
						duplicateStr+='<p class="text-capitalize">'+attrConsti+'</p>';
						$("#duplicateCandidateBlock").html(''+duplicateStr+'');
						$("#memberConfirmation").html("already added member to this alert");
						$("#myModalConformation").modal('show');
						 setTimeout(function(){ $("body").addClass("modal-open");	
						}, 3000);
					}	
			}
			else
				
				{
						if(jQuery.inArray(attrId, assignCadreIds) == -1 )
						{
							
							assignCadreIds.push(attrId);	
							$(".assignedMembersBlock").append(str);
							$("#assignedMembers").html('('+assignCadreIds.length+' - Members added)');
							var addStr ='';
							addStr+='<p class="text-capital" >'+name+'</p>';
							addStr+='<p>'+mobile+'</p>';
							addStr+='<p class="text-capitalize">'+attrConsti+'</p>';
							$("#duplicateCandidateBlock").html(''+addStr+'');
							$("#memberConfirmation").html("Member Added");
							
							$("#myModalConformation").modal('show');
							setTimeout(function(){ $("#myModalConformation").modal('hide');
								 }, 2000);
								 setTimeout(function(){ $("body").addClass("modal-open");	
							}, 3000);
						}else{
							var duplicateStr ='';
							duplicateStr+='<p class="text-capital" >'+name+'</p>';
							duplicateStr+='<p>'+mobile+'</p>';
							duplicateStr+='<p class="text-capitalize">'+attrConsti+'</p>';
							$("#duplicateCandidateBlock").html(''+duplicateStr+'');
							$("#memberConfirmation").html("already added member to this alert");
							$("#myModalConformation").modal('show');
							 setTimeout(function(){ $("body").addClass("modal-open");	
							}, 3000);
						}	
				}
				
				 
						 
				  cloneCount = cloneCount+1;
				  /* globalSelectedMemberIdsArr.push(attrId); */
				   $('html, body').animate({
						scrollTop: $('.membersBlock').offset().bottom
					}, 2000);
		 }/* else{ 
			var attrId = $(this).attr("attr_id");
			cloneCount = cloneCount-1;
			var index = involvedCadreIds.indexOf(attrId);
			if (index >= 0) {
				  involvedCadreIds.splice( index, 1 );
				}
			$("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
			$("#involveBlockParent"+attrId).remove();
			
			removeParticularValue(globalSelectedMemberIdsArr,attrId);
		 } */
   });
    /* function removeParticularValue(arr, item) {
	      for(var i = arr.length; i--;) {
	          if(arr[i] === item) {
	              arr.splice(i, 1);
	          }
	      }
	  } */
   function onLoad() {
	
       lang = $("input[name=language]:checked").val();
     var options = {
         sourceLanguage:
             google.elements.transliteration.LanguageCode.ENGLISH,
         destinationLanguage:
             [''+lang+''],
         shortcutKey: 'alt+t',
         transliterationEnabled: true
     };

     // Create an instance on TransliterationControl with the required
     // options.
     control =
         new google.elements.transliteration.TransliterationControl(options);

     // Enable transliteration in the textbox with id
     // 'descrptionId'.

	 	if ($('#alertdescriptionId').length){
			control.makeTransliteratable(['alertdescriptionId']);
		}
		if ($('#alertTitleId').length){
			control.makeTransliteratable(['alertTitleId']);
		}
   }
   function languageChangeHandler() {
  
        var lang1 = $("input[name=language]:checked").val();
		if(lang1 =="en")
	   {
		control.disableTransliteration();
		}
		else
	   {
		   control.enableTransliteration();
           control.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            lang1);
			}
      }
 google.setOnLoadCallback(onLoad);
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
  var alertSourceId = $("#alertSourceId").val();
  var alertSeverityId = $("#alertSeverityId").val();
  var  level=$("#alertlevelId1").val();
  var  state=$("#stateId1").val();
  var  district=$("#referdistrictId1").val();
  var  assembly=$("#referconstituencyId1").val();
  var  mandal=$("#refermandalNameId1").val();
  var  panchayat=$("#referpanchayatId1").val();
 // var  candidate=$("#candidatesNameListId").val();
  //var  candidateName=$("#candidateNameId").val();
  var  description=$("#alertdescriptionId").val().trim();
  
  //var categoryId=$("#alertCategory").val();
  var title=$("#alertTitleId").val().trim();
  var alertImpact =$("#alertImpactId").val();

  $("#errorDiv1").html('');
  $("#errorDiv1").css("color","red");
  $("#candidateId").val('');
  if(alertType==0)
  {
    $("#errorDiv1").html(" Please select Alert Type ");
        return;
  }
  if(alertImpact==0)
  {
    $("#errorDiv1").html(" Please select Alert Impact ");
        return;
  }
 /* if(categoryId == 0){
	  $("#errorDiv1").html(" Please select Alert Category ");
        return;
  }*/
  if(alertSourceId==0)
  {
    $("#errorDiv1").html(" Please select Alert Source ");
        return;
  }
  
   if(alertSeverityId==0)
  {
    $("#errorDiv1").html(" Please select Alert Severity ");
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
	  var mandalName = $("#refermandalNameId1 option:selected").text();
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
	   var panchayatName = $("#referpanchayatId1 option:selected").text();
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
	var candidateId="";
	$(".memberDatacls").each(function(){
		if($(this).val() != null && $(this).val().length > 0)
		candidateId += $(this).val()+",";
	})
 /*if(candidateId==0 || candidateId.length == 0)
  {
    $("#errorDiv1").html(" Please select Candidate ");
        return;
  }*/
	var n=candidateId.lastIndexOf(",");
    candidateId=candidateId.substring(0,n) ;
	$("#candidateId").val(candidateId);
	
	
	

	 if(title.length==0 ||title=='')
	  {
		$("#errorDiv1").html(" Please enter Alert Title.  ");
			return;
	  }
  
   if(description.length==0 ||description=='')
	  {
		$("#errorDiv1").html(" Please enter description ");
			return;
	  }
	  
$("#createAlertajaxImg").html('<img src="images/search.gif"/>');
var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					$("#createAlertajaxImg").html('');
					if(uploadResult.indexOf("success") !=-1)
					{
					 $("#errorDiv1").html("Alert Created Successfully ").css("color","green");	
					 setTimeout(function(){ $("#errorDiv1").html("");
					 }, 1000);
					}
					clearFields();
					disableByLevel(1);
					return false;
				}
			};

		YAHOO.util.Connect.setForm('saveAlertForm',true);
		YAHOO.util.Connect.asyncRequest('POST','saveAlertAction.action',uploadHandler);
}

function clearFields()
{
	
	$("#extraUploadFileDiv").html("");
	$("#uploadFileId0").val("");
	$("#apptmemberDetailsDiv").html("");
	$(".membersBlock").html("");
	$("#assignedMembers").html("");
	$(".assignedMembersBlock").html("");
	$("#involvedCandidatesDiv").hide();	
	
	showHideBySearchType();	
	
	involvedCadreIds =[];
	$("#involvedMembers").html('(0 - Members added)');
	
	$("#alertTitleId").val("");
	$(".alertclearCls").val("");
	$(".clearCls").val("");
	$("#alertCategory").val(0);
	
	$("#advanceSearchTypeId").val(0);
	var select = new Dropkick("#advanceSearchTypeId");
	select.refresh();	
	
	$("#stateId1").val(0);
	var select = new Dropkick("#stateId1");
	select.refresh();
	
	$("#alertTypeId").val(0);
	var select = new Dropkick("#alertTypeId");
	select.refresh();
	
	$("#alertCategory").val(0);
	var select = new Dropkick("#alertCategory");
	select.refresh();
	
	$("#alertSeverityId").val(0);  
	var select = new Dropkick("#alertSeverityId");
	select.refresh();
	
	$("#alertlevelId1").val(2);
	var select = new Dropkick("#alertlevelId1");
	select.refresh();
	
	$("#alertSourceId").val(0);
	var select = new Dropkick("#alertSourceId");
	select.refresh();
	
	$("#alertImpactId").val(0);
	var select = new Dropkick("#alertImpactId");
	select.refresh();
	
}
function getAlertType(){
		$("#alertTypeId").html('');
		var jsObj =
		        {
			task:""
		          }
				$.ajax({
					  type:'GET',
					  url: 'getAlertTypeAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#alertTypeId').append('<option value="0"> Select Alert Type </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#alertTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#alertTypeId").dropkick();
							var select1 = new Dropkick("#alertTypeId");
							select1.refresh();
					}
				  
				});
		}
		
var loginUserId = "${sessionScope.USER.registrationID}";
function getAlertsource(){
		$("#alertSourceId").html('');
		var jsObj =
		        {
					userId : loginUserId,
				task:""
		          }
				$.ajax({
					  type:'GET',
					  url: 'getAlertSourceForUserAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#alertSourceId').append('<option value="0"> Select Alert Source </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#alertSourceId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#alertSourceId").dropkick();
							var select1 = new Dropkick("#alertSourceId");
							select1.refresh();
					}
				  
				});
		}
function buildapptmemberDetails(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<table id="searchedMembersId">';
			str+='<thead style="display:none;"><th></th><th></th><th></th></thead>';
			str+='<tbody>';
			var xindex =0;
			for(var i in result){
				if( xindex == 0)
			{
				str+='<tr>';
			}
				
				
				str+='<td style="padding:0px !important;">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<ul class="createAppointmentSearch">';
						str+='<li>';
							str+='<div class="row">';
								
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="media">';
										str+='<div class="media-left">';
											str+='<img class="media-object thumbnailSearch thumbnail" src="'+result[i].imageURL+'" onerror="setDefaultImage(this);" alt="Candidate Image" style="width: 60px !important; height: 60px  !important;">';
										str+='</div>';
										str+='<div class="media-body">';
										
							
										if(result[i].constituency !=null && result[i].constituency.length>0 ){
											<c:choose>
											<c:when test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS')}">
											if(result[i].id != null && result[i].id > 0){
												if(result[i].candidateType=="voter"){
												 str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div>';
												}else{
													str+='<a  target="_blank" data-toggle="tooltip" data-placement="top" title="Cadre Details" style="cursor:pointer;" href="cadreDetailsAction.action?cadreId='+result[i].id+'"><div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span >'+result[i].constituency+' Constituency</span></div></a>';
												}
											}
											else
											str+='<span style="color:#34A7C1;">'+result[i].name+'</span> ';
											</c:when>
											<c:otherwise>
											str+='<div style="color:#34A7C1;"><span >'+result[i].name+'</span>   -   <span>'+result[i].constituency+' Constituency</span></div>';
											</c:otherwise>
											</c:choose>
											
										}else{
											str+='<div style="color:#34A7C1;">'+result[i].name+'</div>';
										}
										if(result[i].mobileNo !=null && result[i].mobileNo.length>0){
												str+='<p ><span><i class="fa fa-mobile" style="font-size:15px"></i> &nbsp '+result[i].mobileNo+'</span>';
										}else{
											str+='<p><span><i class="fa fa-mobile" style="font-size:15px"></i>   - </span>';
										}
										
										if(result[i].designation !=null && result[i].designation.length>0){
											
												str+='<span style="margin-left:10px;"> '+result[i].designation+'</span></p>';
										}else{
											
											//str+='<span style="margin-left:10px;">Designation: - </span></p>';
											
											 if($("#searchTypeId").val()=="mobileno" || $("#searchTypeId").val() == "mebershipno" || $("#searchTypeId").val() == "votercardno" || $("#advanceSearchTypeId").val() == 1){
												 if(result[i].candidateType == 'cadre'){
													str+='<span style="margin-left:10px;"> - Cadre</span></p>'; 
												 }else{
													 str+='<span style="margin-left:10px;"> - </span></p>';
												 }
											}else{
												str+='<span style="margin-left:10px;"> - </span></p>';
											} 
										}
										str+='</div>';
									str+='</div>';
								str+='</div>';
								str+='<div class="btn btn-success btn-sm col-md-4 col-md-offset-4 m_top10 col-xs-6 col-xs-offset-3" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv candidatecls close'+result[i].id+'"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Select Candidate" attr-consti="'+result[i].constituency+'"> &nbsp;SELECT</label></div>';	
							  
								/*if(result[i].appointmentCandidateId != null && result[i].appointmentCandidateId > 0){
									
									str+='<div class="col-md-1 m_top10"><a  title="Click here to View '+result[i].name+' History" data-toggle="tooltip" data-placement="top" class="historyShowModalBtn"  style="cursor:pointer;" attr-id="'+result[i].appointmentCandidateId+'" attr-name="'+result[i].name+'" attr-designation="'+result[i].designation+'" attr-mobile="'+result[i].mobileNo+'"><i class="glyphicon glyphicon-time" style="color: rgb(142, 142, 142); font-size: 16px;"></i></a></div>&nbsp;&nbsp;';
									
								}
								
								
							if(result[i].designation==null){
								result[i].designation = "";
							}
							if(result[i].aptExists == false && result[i].appointmentCandidateId != null){
								str+='<div class="col-md-1   m_top10" attr_id="'+result[i].id+'" >';
							}
							else{
								str+='<div class="col-md-1 col-xs-offset-1 m_top10" attr_id="'+result[i].id+'" >';
							}
							if(result[i].aptExists == false)
							{
								str+='<div class="btn btn-success btn-sm" style="border-radius:20px;"><label style="margin-bottom: 0px; line-height: 10px;"><input style="margin-left: 0px; margin-top: 0px;" type="checkbox" data-toggle="tooltip" data-placement="top" class="apptDetailsDiv"  attr_designation = "'+result[i].designation+'" attr_candidateType="'+result[i].candidateType+'" attr_name="'+result[i].name+'" attr_mobile="'+result[i].mobileNo+'" attr_desg="'+result[i].designationId+'" attr_memberShipNo="'+result[i].memberShipId+'" attr_voterCardNo="'+result[i].voterCardNo+'" attr_id="'+result[i].id+'" attr_close_id="uncheck'+result[i].id+'" attr_img_url="'+result[i].imageURL+'" attr_candidateType_id='+result[i].candidateTypeId+' title="Create Appointment Request"> &nbsp;SELECT</label></div>';	
							}								
							else{
								str+='<label data-toggle="tooltip"  data-placement="top" title="This Candidate Already in '+result[i].aptName+' Appointment with '+result[i].aptStatus+' Status: you can not addtion to another Appointment"> ';
								str+=''+result[i].aptName+' - '+result[i].aptStatus+'';
								str+='</label>';
							}
							
								str+='</div>';
								
							str+='</div>';*/
						str+='</li>';
					 
					str+='</ul>';
				str+='</div>';
					
				str+='</td>';
				xindex++;
				var screenWidth = $(window).width();
				
				if(screenWidth <=500)
				{
					str+='</tr>';
						xindex = 0;
						/* if(result.length-1 == i){
						if(xindex % 1 == 2){
							str+='<td></td>';
							str+='</tr>';
						}
						if(xindex % 1 == 1){
							str+='<td></td>';
							str+='<td></td>';
							str+='</tr>';
						}
					}
					 if( xindex == 3){
						str+='</tr>';
						xindex = 0;
					} */
				}
				else if(screenWidth >500 && screenWidth <=800)
				{
					if(result.length-1 == i){
					if(xindex % 2 == 2){
						str+='<td></td>';
						str+='</tr>';
					}
					if(xindex % 2 == 1){
						str+='<td></td>';
						str+='<td></td>';
						str+='</tr>';
					}
				}
				 if( xindex == 2){
					str+='</tr>';
					xindex = 0;
				}  
				}
				else{
					if(result.length-1 == i){
					if(xindex % 3 == 2){
						str+='<td></td>';
						str+='</tr>';
					}
					if(xindex % 3 == 1){
						str+='<td></td>';
						str+='<td></td>';
						str+='</tr>';
					}
				}
				 if( xindex == 3){
					str+='</tr>';
					xindex = 0;
				} 
				}
				
			}
			str+='</tbody>';
			str+='</table>';
		}
		
		$("#apptmemberDetailsDiv").html(str);
		/* Make Selected People next time who has already selected for referral in first search */
		/* if(globalSelectedMemberIdsArr != null && globalSelectedMemberIdsArr.length > 0){
			for(var i=0;i<globalSelectedMemberIdsArr.length;i++){
				$(".close"+globalSelectedMemberIdsArr[i]).prop("checked",true);
				$(".searhMemberCls"+globalSelectedMemberIdsArr[i]).css("background-color", "lightgrey"); 
			}	
		} */
		$('[data-toggle="tooltip"]').tooltip()
		$('.check').tooltip()
		
		applyPagination();
	}
	
	function updateStateDetails(id){
		if(id == 2){
			$('#stateId1').find('option').remove();
			$('#stateId1').append("<option value='0'> Select State </option>");
			$('#stateId1').append("<option value='1'> Andhra Pradesh </option>");
		}else{
			$('#stateId1').find('option').remove();
			$('#stateId1').append("<option value='0'> Select State </option>");
			$('#stateId1').append("<option value='1'> Andhra Pradesh </option>");
			$('#stateId1').append("<option value='36'> telangana </option>");
		}
		
		$("#stateId1").dropkick();
		var selects = new Dropkick("#stateId1");
		selects.refresh();
	}
getAlertType();
buildLevels();
showHideSearch("advanceSearch");
showHideBySearchType();
disableByLevel(1);
getAlertsource();
getAlertImpactScope();

$("#apptmemberDetailsDiv").html("");

//showHideSearch("advanceSearch");
//showHideBySearchType();
getMemberTypes();
function getAlertImpactScope(){
		$("#alertImpactId").html('');
		var jsObj =
		        {
			task:""
		          }
				$.ajax({
					  type:'GET',
					  url: 'getAlertImpactScopeAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#alertImpactId').append('<option value="0"> Select Alert Impact </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#alertImpactId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
						}
						$("#alertImpactId").dropkick();
							var select1 = new Dropkick("#alertImpactId");
							select1.refresh();
					}
				  
				});
		}
	var fileNum=0;
	$(document).on("click","#addFile",function(){
		fileNum = fileNum+1;
		var c = $(".cloneFileCls").clone(true);
		c.removeAttr("style");
		c.attr("id","uploadFileId"+fileNum);
		c.attr("name","imageForDisplay");
		c.removeAttr("class").addClass("btn btn-mini");
		$("#extraUploadFileDiv").append(c);
	});
</script>
</body>
</html>