<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="dist/Appointment/custom.css" rel="stylesheet" type="text/css">
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
<script src="dist/newmultiselect/chosen.jquery.min.js" type="text/javascript"></script>
<div class="row">
	<div class="col-md-12" id="commomCadreSearchDiv">
			<div class="panel panel-default" style="border-radius:0px;">
				<div class="panel-heading" style="background:#ccc;border-radius:0px;">
					<h4 class="panel-title changHeading">SEARCH CADRE</h4>
				</div>
				<div class="panel-body" style="background:#fff;">
					
				<div>
					<div class="row">
					<div class="col-md-12">
					<div style="background:#fff">
					<div class="row">
					<div class="col-md-12 m_top10">
						<h3 class="panel-title text-success"></h3>
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6 advanceSearchCls">
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
					
					
					 <div class="col-md-3  col-xs-12 col-sm-6 advanceSearchCls advanceprclsDiv">
						<label class="advanceNameCls" id="searchNameLabel">Search By Name/Membership No*<span class="text-danger">*</span></label>
						<input type="text" class="form-control advanceNameCls clearCls" id="advanceSearchValueId">
						
					</div>
					<div class="col-md-3 col-xs-12 col-sm-6 ">
						<label class="advancePRCls">Search Designation</label>
						 <select class="advancePRCls dropkickClass"  id="advanceDesignationId" onchange="getLevelByDesignation();">
							<option value="0">Select Designation</option>
						</select>
						<span id="advanceErrDigitsId" class="full-right" style="color:red;"></span>
					</div>
					
						
						<div class="col-md-3 col-xs-12 col-sm-6 levelShowCls" >
							<label>Level</label>
							<select class="dropkickClass" id="commonLevelId" attr-index="0" onchange="disableByLevel('');" >
							<option value="10">State</option>
							 <option value="11">District</option>
							 <option value="1">Constituency</option>
							 <option value="5">Mandal/Muncipality</option>
							 <option value="6">Village/Ward</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6 stateShowCls" >
							<label>State</label>
							 <select class="dropkickClass" class="stateCls" id="stateId" onChange="getDistrictsForReferPopup('');">
							 <option value="0">ALL</option>
							 <option value="1">AP</option>
							 <option value="36">TS</option>
							 </select>
						</div>
				   
						<div class="col-md-3 col-xs-12 col-sm-6 locationsFilterCls distCls">
							 <label>District</label>
							 <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup('');" >
							 <option value="0">ALL</option></select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6 locationsFilterCls constiCls">
							<label>Assembly</label>
							<select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup('');" >
							<option value="0">ALL</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6 locationsFilterCls mandalCls">
							<label>Mandal/ Municipality</label>
							 <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup('');" >
								<option value="0">ALL</option>
							 </select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-6 locationsFilterCls panchayatCls">
							<label>Panchayat/Ward</label>
							<select class="dropkickClass" id="referpanchayatId" >
							<option value="0">ALL</option>
							</select>
						</div>
						<div>
									<div class="advanceCadreCommittee" id="referCommitteeDiv">
									 <div class="col-md-3">
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
										<div class="col-md-6 col-xs-12 col-sm-6">
											<select id="cadreCommitteeDiv" multiple class="advanceCadreCommittee" style="width:250px !important;"></select>
											<div id="representativesDiv"></div>
											<div id="referRoleErrorDiv"></div>
										</div>
									</div>
								</div></br>
						<p id="errorDivId" style="color:red;clear:both;margin-left:5px;"></p>
						<div class="col-md-2  col-xs-12 col-sm-6">
							<button type="button" class="btn btn-block btn-success m_top20 advancedSearchBtn btnNewCustom1" onclick="handleBySearchType();" id="searchBtnId"  style="margin-top: 25px;display:none;">Search Member</button>
						</div>
						<div class="col-md-1  col-xs-12 col-sm-1">
							<img src="images/search.gif" style="display:none;" id="ajaxImgForAppintId"/>
						</div>
							<div style="margin-top: 50px;"><img id="searchMemberAjax" src="images/icons/loading.gif" style="display:none;"/></div>
						
						<div class="row m_top25">
						<div class="col-md-12 col-xs-12 col-sm-12" id="clearSerchDivId">
							<div id="apptmemberDetailsDiv" class="table-responsive"></div>
						</div>
						
						
						</div>
						</div>
						</div>
						</div>
						</div>
				</div>
				
				</div>
				<div class="m_top10" id="involvedCandidatesDiv" style="padding:0px 15px;">
							<div class="col-md-12 col-xs-12 col-sm-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">ASSIGN CANDIDATES</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="membersBlock" style="display:none;"></div>	
										</div>
									</div>
									
								</div>
							</div>
						</div>
			</div>
			</div>
			</div>
			
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
<script type="text/javascript">
function buildapptmemberDetails(result){
		var str='';
		if(result !=null && result.length>0){
			str+='<table id="searchedMembersId">';
			str+='<thead style="display:none"><th></th><th></th><th></th></thead>';
			str+='<tbody>';
			var xindex =0;
			for(var i in result){
				if( xindex == 0)
			{
				str+='<tr>';
			}
				str+='<td style="padding:0px !important;width:33% !important">';
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
		$('[data-toggle="tooltip"]').tooltip()
		$('.check').tooltip()
		
		applyPagination();
	}

$("#apptmemberDetailsDiv").html("");

</script>
<!--<script src="dist/commonCadreSearch/commonCadreSearch.js" type="text/javascript"></script>-->
