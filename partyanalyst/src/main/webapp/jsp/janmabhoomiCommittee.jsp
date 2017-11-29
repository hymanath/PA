<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Janmabhoomi Committee Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="D2D_Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="D2D_Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<script src="D2D_Assests/Plugins/Less/less.js"></script>
<link href="D2D_Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style type="text/css">
#menu_multilevelpushmenu{box-shadow:none;}
</style>
</head>
<body>
<section class="janmabhoomi">
	<div class="container-fluid m_top10">
		<div class="row">
			<div class="col-sm-12">
				<div class="white_block_JanB">
					<div class="row">
						<div class="col-sm-2">
						    <div id="committeeMainBlockDivId"></div>
							<div id="committeeMainBlockGraphId" style="height:200px;"></div>
						</div>
						<div id="overAllCommitteeMainBlockId"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-12">
				<div id="committeeWiseDetailsDivId"></div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-12">
				<div id="levelWisecommitteeDetailsDivId"></div>
			</div>
		</div>
	</div>		
</section>
<div class="modal fade janmabhoomi" tabindex="-1" id="committeeWiseModalOpen" role="dialog" style="z-index:9999;">
 <div class="modal-dialog" role="document" style="width:90%;">   
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<div class="row">
				<div class="col-sm-6">
					<h4 class="modal-title" id="committesLevelNameId"></h4>   
				</div>
				<div class="col-sm-3 pull-right committeeSelectBoxCls">
					  <select class="form-control chosen-select" id="committesLevelValuesId">
					  </select>
				</div>
			</div>   
		</div>
		<div class="modal-body">   
          <div id="committeeWisePopUpDetailsId"></div>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!--   /.modal-content -->  
  </div><!--  /.modal-dialog -->
</div><!--   /.modal -->
<div class="modal fade janmabhoomi" tabindex="-1" id="memberAddEditModalOpen" role="dialog" style="z-index:9999;">
 <div class="modal-dialog" role="document" style="width:90%;">   
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<div class="row">
				<div class="col-sm-8">
					<h4 class="modal-title">Add Members</h4>  
				</div>
				<div class="col-sm-2 pull-right" id="searchMemberDetailsId">
				</div>
			</div>     
		</div>
		<div class="modal-body">   
          <div id="memberAddEditPopUpDetailsId" class="bg_class_Div"></div>
          <div id="memberAddedPopUpDetailsId"></div>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
      </div>
    </div><!--   /.modal-content -->  
  </div><!--  /.modal-dialog -->
</div><!--   /.modal -->
<script src="D2D_Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="D2D_Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script type="text/javascript" src="js/janmabhoomiCommittee.js"></script>
<script type="text/javascript">

 function buildJanmabhoomiCommitteeOverview(result,committeId){
	var str='';
	str+='<div class="row">';
	
		str+='<div class="col-sm-3">';
			str+='<div class="bordermemberes" style="border:1px solid #0E0E0E;background-color:#DCDCDC">';
				str+='<h5 class="text-bold">Total Members</h5>';
				str+='<h4 class="m_top10">'+result.roleMemberCount+'</h4>';
				str+='<ul class="list-inline checkConditionChangeStatusCls">';
				var bcType=false;
				//var ocType=false;
				var stType=false;
				var scType=false;
					/*for(var i in result.desinationVOList){
						for(var j in result.desinationVOList[i].desinationMebersVOList){
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "BC"){
								bcType = true;
							}
							// if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "OC"){
								//ocType = true;
							//} 
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "SC"){
								scType = true;
							}
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "ST"){
								stType = true;
							}
						}
					} */
					/* if(ocType === true){
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss" style="background-color:#777;"></span> OC</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> OC</li>';
					} */
					if(result.bcType !=null || result.scType!=null || result.stType!=null){// result.ocType!=null
					if(result.bcType == 'contains'){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:green;"></span> BC</li>';
						bcType = true;
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> BC</li>';
					}
					if(result.scType == 'contains'){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:green;"></span> SC</li>';
						scType = true;
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> SC</li>';
					}
					if(result.stType == 'contains'){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:green;"></span> ST</li>';
						stType = true;
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> ST</li>';
					}
					}
				str+='</ul>';
				
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-2">';
			str+='<div class="bordermemberes" style="border:1px solid #45BC7E;background-color:#D7F1E4">';
				str+='<h5 class="text-bold">Added Members</h5>';
				str+='<h4 class="m_top35">'+result.addedMemberCount+'</h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2">';
			str+='<div class="bordermemberes" style="border:1px solid #75C0EA;background-color:#E2F2FB">';
				str+='<h5 class="text-bold">Not-Added Members</h5>';
				str+='<h4 class="m_top20">'+result.notAddedMemberCount+'</h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2">';
			str+='<div class="bordermemberes" style="border:1px solid #FF735D;background-color:#F6DADA">';
				str+='<h5 class="text-bold">Rejected Members</h5>';
				str+='<h4 class="m_top35">'+result.rejectedMemberCount+'</h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2 border_left">';
			str+='<div class="bordermemberes" style="border:1px solid #ddd;border-radius:10px;">';
				str+='<h5 class="text-bold">Committee Status</h5>';
				str+='<h4 class="m_top35">'+result.status+'</h4>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-12">';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered table_custom_AddEdit">';
				str+='<thead class="bg-DD">';
					str+='<tr>';
						str+='<th>Designation</th>';
						str+='<th>No of Members Required</th>';
						str+='<th>Member Added</th>';
						str+='<th>Mobile Number</th>';
						str+='<th>Voter ID</th>';
						str+='<th>CategoryName</th>';
						str+='<th>CasteName</th>';
						str+='<th>Party</th>';
						str+='<th>Status</th>';
						//if(result.status != 'Approved' && result.status != 'Ready For Approval')
							<c:if  test="${fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_ENTRY_USER_ENTITLEMENT' )  && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) }">
						if(result.status != 'Approved' &&  result.status != 'Ready for Approval'){
									str+='<th>Add Member</th>';
								}
								</c:if>
								<c:if  test="${fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_APPROVE_USER_ENTITLEMENT' )  && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) }">
								if(result.status != 'Approved' &&  result.status == 'Ready for Approval'){
						str+='<th>Add Member</th>';
								}
								</c:if>
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>'
					for(var i in result.desinationVOList){
						str+='<tr>';
						var length = result.desinationVOList[i].desinationMebersVOList.length+1;
							str+='<td rowspan="'+length+'">'+result.desinationVOList[i].designationName+'</td>';
							str+='<td rowspan="'+length+'">'+result.desinationVOList[i].roleMemberCount+'</td>';
						str+='</tr>';
						if(result.desinationVOList[i].desinationMebersVOList !=null && result.desinationVOList[i].desinationMebersVOList.length>0){
							for(var j in result.desinationVOList[i].desinationMebersVOList){
							str+='<tr>';
								if(result.desinationVOList[i].desinationMebersVOList[j].memeberName !=null && result.desinationVOList[i].desinationMebersVOList[j].memeberName.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].memeberName+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.desinationVOList[i].desinationMebersVOList[j].mobileNumber !=null && result.desinationVOList[i].desinationMebersVOList[j].mobileNumber.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].mobileNumber+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.desinationVOList[i].desinationMebersVOList[j].voterCardNo !=null && result.desinationVOList[i].desinationMebersVOList[j].voterCardNo.length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].voterCardNo+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.desinationVOList[i].desinationMebersVOList[j].categoryName !=null && result.desinationVOList[i].desinationMebersVOList[j].categoryName.length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].categoryName+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.desinationVOList[i].desinationMebersVOList[j].casteName !=null && result.desinationVOList[i].desinationMebersVOList[j].casteName.length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].casteName+'</td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.desinationVOList[i].desinationMebersVOList[j].partyName !=null && result.desinationVOList[i].desinationMebersVOList[j].partyName.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].partyName+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
								if(result.desinationVOList[i].desinationMebersVOList[j].status !=null && result.desinationVOList[i].desinationMebersVOList[j].status.trim().length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].status+'</td>';
								}else{
									str+='<td> - </td>';
								}
								var committeeLevlVal=0;
										if(result.levelId != null && result.levelId ==2){
											committeeLevlVal='2'+result.levelValue;
										}else if(result.levelId != null && (result.levelId ==3 || result.levelId ==4)){
											committeeLevlVal='1'+result.levelValue;
										}else if(result.levelId != null && result.levelId ==5){
											committeeLevlVal='2'+result.levelValue;
										}else if(result.levelId != null && (result.levelId ==6 || result.levelId ==7)){
											committeeLevlVal='1'+result.levelValue;
										}
								if(result.status != 'Approved' ){
									
									
									<c:if test="${fn:contains(sessionScope.USER.entitlements,'JANMABHOOM_COMMITTEE_EDIT_USER_ENTITLEMENT' )  && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_ENTRY_USER_ENTITLEMENT' ) }">
									if( result.desinationVOList[i].desinationMebersVOList[j].status == "Approved" && result.status != 'Approved' &&  result.status != 'Ready for Approval'){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="edit"  attr_member_id="'+result.desinationVOList[i].desinationMebersVOList[j].id+'" attr_member_name="'+result.desinationVOList[i].desinationMebersVOList[j].memeberName+'" attr_mobile_no="'+result.desinationVOList[i].desinationMebersVOList[j].mobileNumber+'" attr_voterCard_no="'+result.desinationVOList[i].desinationMebersVOList[j].voterId+'" attr_membership_no="'+result.desinationVOList[i].desinationMebersVOList[j].memberShipCardId+'" attr_committee_id="'+committeId+'">Reject</h5></td>';
									}/* else if(result.desinationVOList[i].desinationMebersVOList[j].status == "Rejected" || result.desinationVOList[i].desinationMebersVOList[j].status == "" || result.desinationVOList[i].desinationMebersVOList[j].status == null){
										
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="approval"  attr_role_id="'+result.desinationVOList[i].designationId+'" attr_committee_id="'+committeId+'" attr_member_id="'+result.desinationVOList[i].desinationMebersVOList[j].id+'" attr_publicRepre_typeId="'+result.desinationVOList[i].publicRepresentativeTypeId+'" attr_committee_lvl_id="'+result.levelId+'" attr_committee_lvl_val="'+committeeLevlVal+'" attr_state_id="'+result.stateId+'" attr_district_id="'+result.districtId+'" attr_constituency_id="'+result.constituencyId+'" attr_mandal_id="'+result.mandalId+'" attr_panchayat_id="'+result.panchayatId+'" attr_local_election_body="'+result.localElectionBodyId+'" attr_ward_id="'+result.wardId+'">Add Member</h5></td>';
								} */
								
									
									</c:if>
									<c:if test="${fn:contains(sessionScope.USER.entitlements,'JANMABHOOM_COMMITTEE_EDIT_USER_ENTITLEMENT' )  && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_APPROVE_USER_ENTITLEMENT' ) }">
									if( result.desinationVOList[i].desinationMebersVOList[j].status == "Approved" && result.status != 'Approved' &&  result.status == 'Ready for Approval'){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="edit"  attr_member_id="'+result.desinationVOList[i].desinationMebersVOList[j].id+'" attr_member_name="'+result.desinationVOList[i].desinationMebersVOList[j].memeberName+'" attr_mobile_no="'+result.desinationVOList[i].desinationMebersVOList[j].mobileNumber+'" attr_voterCard_no="'+result.desinationVOList[i].desinationMebersVOList[j].voterId+'" attr_membership_no="'+result.desinationVOList[i].desinationMebersVOList[j].memberShipCardId+'" attr_committee_id="'+committeId+'">Reject</h5></td>';
									}
									
									</c:if>
									<c:if  test="${fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_ENTRY_USER_ENTITLEMENT' )  && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) }">
								if(result.desinationVOList[i].desinationMebersVOList[j].status == "Rejected" || result.desinationVOList[i].desinationMebersVOList[j].status == "" || result.desinationVOList[i].desinationMebersVOList[j].status == null){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="approval"  attr_role_id="'+result.desinationVOList[i].designationId+'" attr_committee_id="'+committeId+'"attr_member_id="'+result.desinationVOList[i].desinationMebersVOList[j].id+'" attr_publicRepre_typeId="'+result.desinationVOList[i].publicRepresentativeTypeId+'" attr_committee_lvl_id="'+result.levelId+'" attr_committee_lvl_val="'+committeeLevlVal+'" attr_state_id="'+result.stateId+'"  attr_district_id="'+result.districtId+'" attr_constituency_id="'+result.constituencyId+'" attr_mandal_id="'+result.mandalId+'" attr_panchayat_id="'+result.panchayatId+'" attr_local_election_body="'+result.localElectionBodyId+'" attr_ward_id="'+result.wardId+'">Add Member</h5></td>';
								}
								</c:if>
									
							}
								
								
							str+='</tr>';	
							}
						}
						
					}
				str+='</tbody>'
			str+='</table>';
		str+='</div>';
		str+='</div>';
		var approvedBooleanaVal=false;
		var approvedMemebersCount = 0;
		var requiredRoleCount = 0;
	
		for(var i in result.desinationVOList){
			if(result.desinationVOList[i].desinationMebersVOList !=null && result.desinationVOList[i].desinationMebersVOList.length>0){
				for(var j in result.desinationVOList[i].desinationMebersVOList){
					if(result.desinationVOList[i].desinationMebersVOList[j].status === "Approved"){
						//approvedBooleanaVal = true;
						approvedMemebersCount = approvedMemebersCount+1;
					}
				}
			}
				requiredRoleCount = requiredRoleCount+result.desinationVOList[i].roleMemberCount;
		}
		
		if(approvedMemebersCount === requiredRoleCount){
			approvedBooleanaVal = true;
		}
		
		if(bcType === true && scType === true && stType === true && approvedBooleanaVal === true){
			if(result.status != 'Approved'){
				
				str+='<div class="col-sm-12 m_top20">';
					str+='<div class="row">';
					<c:choose>
									
						<c:when test="${fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_APPROVE_USER_ENTITLEMENT' ) && fn:contains(sessionScope.USER.entitlements,'JANMABHOOM_COMMITTEE_EDIT_USER_ENTITLEMENT' )}">
						if(result.status == 'Ready for Approval'){
						str+='<div class="col-sm-3">';
							str+='<label>Change Committee Status</label>';
							str+='<select class="form-control chosen-select" id="committeeStatusChangeId">';
								str+='<option value="approve">Approved Committee</option>';
								str+='<option value="reject">Reject Committee</option>';
							str+='</select>';
						str+='</div>';
						str+='<div class="col-sm-2">';
							str+='<button id="submitCommitteeStatusChangeId" type="button" style="margin-top: 28px;" class="btn btn-success btn-sm" attr_committee_submit="'+committeId+'">Submit</button>';
							str+='<span class="loadingImgId2"><img src="images/search.gif" style="display:none;"/></span>';
						str+='</div>';
						}
						</c:when>
						<c:when test="${fn:contains(sessionScope.USER.entitlements,'JANMABHOOM_COMMITTEE_EDIT_USER_ENTITLEMENT' )  && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) && fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_ENTRY_USER_ENTITLEMENT' ) }">
						if(result.status == 'In Progress'){
						str+='<div class="col-sm-3">';
							str+='<label>Change Committee Status</label>';
							str+='<select class="form-control chosen-select" id="committeeStatusChangeId" >';
								str+='<option value="readyforapproval">Ready for approval</option>';
							str+='</select>';
						str+='</div>';
						str+='<div class="col-sm-2">';
							str+='<button id="submitCommitteeStatusChangeId" type="button" style="margin-top: 28px;" class="btn btn-success btn-sm" attr_committee_submit="'+committeId+'">Submit</button>';
							str+='<span class="loadingImgId2"><img src="images/search.gif" style="display:none;"/></span>';
						str+='</div>';
						}
						</c:when>
						</c:choose>
						
						str+='<div class="col-sm-6">';
							str+='<div class="committeeSavingStatusDivId"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}
		}
		
	str+='</div>';
	
	$("#committeeWisePopUpDetailsId").html(str);
	$("#committeeStatusChangeId").chosen();
	$("#committeeStatusChangeId").trigger("chosen:updated");
  }

</script>
</body>
</html>