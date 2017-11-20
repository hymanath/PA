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
				<div class="col-sm-3">
					<h4 class="modal-title">Memberes Details</h4>   
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
 <div class="modal-dialog" role="document" style="width:85%;">   
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">Add Memberes</h4>      
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

 function buildJanmabhoomiCommitteeOverview(result,statusType,committeId){
	var str='';
	
	str+='<div class="row">';
	
		str+='<div class="col-sm-3">';
			str+='<div class="bordermemberes" style="border:1px solid #0E0E0E;background-color:#DCDCDC">';
				str+='<h5 class="text-bold">Total Memberes</h5>';
				str+='<h4 class="m_top10">'+result.roleMemberCount+'</h4>';
				str+='<ul class="list-inline checkConditionChangeStatusCls">';
				var bcType=false;
				//var ocType=false;
				var stType=false;
				var scType=false;
					for(var i in result.desinationVOList){
						for(var j in result.desinationVOList[i].desinationMebersVOList){
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "BC"){
								bcType = true;
							}
							/* if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "OC"){
								ocType = true;
							} */
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "SC"){
								scType = true;
							}
							if(result.desinationVOList[i].desinationMebersVOList[j].categoryName == "ST"){
								stType = true;
							}
						}
					}
					/* if(ocType === true){
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss" style="background-color:#777;"></span> OC</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> OC</li>';
					} */
					if(bcType === true){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:#777;"></span> BC</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> BC</li>';
					}
					if(scType === true){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:#777;"></span> SC</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> SC</li>';
					}
					if(stType === true){
						str+='<li style="margin-left:12px;" class="fillBg"><span class="rangeWiseCss" style="background-color:#777;"></span> ST</li>';
					}else{
						str+='<li style="margin-left:12px;"><span class="rangeWiseCss"></span> ST</li>';
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
				str+='<h4 class="m_top35">'+statusType+'</h4>';
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
						str+='<th>Party</th>';
						str+='<th>Status</th>';
					if(!(statusType === "Approved"))
						str+='<th>Add Member</th>';
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
								if(result.desinationVOList[i].desinationMebersVOList[j].voterId !=null && result.desinationVOList[i].desinationMebersVOList[j].voterId.length>0){
									str+='<td>'+result.desinationVOList[i].desinationMebersVOList[j].voterId+'</td>';
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
								
								
									<c:choose>
									
									<c:when test="${fn:contains(sessionScope.USER.entitlements,'JANMABHOOM_COMMITTEE_EDIT_USER_ENTITLEMENT' )  || fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_APPROVE_USER_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_ENTRY_USER_ENTITLEMENT' ) }">
									if(!(statusType === "Approved") && result.desinationVOList[i].desinationMebersVOList[j].status == "Approved" || result.desinationVOList[i].desinationMebersVOList[j].status == "Inprogress"){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="edit"  attr_member_id="'+result.desinationVOList[i].desinationMebersVOList[j].id+'" attr_member_name="'+result.desinationVOList[i].desinationMebersVOList[j].memeberName+'" attr_mobile_no="'+result.desinationVOList[i].desinationMebersVOList[j].mobileNumber+'" attr_voterCard_no="'+result.desinationVOList[i].desinationMebersVOList[j].voterId+'" attr_membership_no="'+result.desinationVOList[i].desinationMebersVOList[j].memberShipCardId+'" attr_committee_id="'+committeId+'">Edit</h5></td>';
									}else if(!(statusType === "Approved") && result.desinationVOList[i].desinationMebersVOList[j].status == "Rejected" || result.desinationVOList[i].desinationMebersVOList[j].status == "" || result.desinationVOList[i].desinationMebersVOList[j].status == null){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="proposal"  attr_role_id="'+result.desinationVOList[i].designationId+'" attr_committee_id="'+committeId+'">Add Member</h5></td>';
								}
									
									</c:when>
									<c:when test="${fn:contains(sessionScope.USER.entitlements,'JANMABHOOM_COMMITTEE_EDIT_USER_ENTITLEMENT' )  || fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) || fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_APPROVE_USER_ENTITLEMENT' ) }">
									if(!(statusType === "Approved") && result.desinationVOList[i].desinationMebersVOList[j].status == "Approved" || result.desinationVOList[i].desinationMebersVOList[j].status == "Inprogress"){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="edit"  attr_member_id="'+result.desinationVOList[i].desinationMebersVOList[j].id+'" attr_member_name="'+result.desinationVOList[i].desinationMebersVOList[j].memeberName+'" attr_mobile_no="'+result.desinationVOList[i].desinationMebersVOList[j].mobileNumber+'" attr_voterCard_no="'+result.desinationVOList[i].desinationMebersVOList[j].voterId+'" attr_membership_no="'+result.desinationVOList[i].desinationMebersVOList[j].memberShipCardId+'" attr_committee_id="'+committeId+'">Edit</h5></td>';
									}
									
									</c:when>
									<c:when  test="${fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_ENTRY_USER_ENTITLEMENT' )  || fn:contains(sessionScope.USER.entitlements, 'JANMABHOOM_COMMITTEE_DASHBOARD_USER_ENTITLEMENT' ) }">
								if(!(statusType === "Approved") && result.desinationVOList[i].desinationMebersVOList[j].status == "Rejected" || result.desinationVOList[i].desinationMebersVOList[j].status == "" || result.desinationVOList[i].desinationMebersVOList[j].status == null){
									str+='<td><h5 style="color:green;text-decoration:underline;" class="memberAddEditDetailsCls" attr_type="proposal"  attr_role_id="'+result.desinationVOList[i].designationId+'" attr_committee_id="'+committeId+'">Add Member</h5></td>';
								}
								</c:when>
								<c:otherwise>
								if(!(statusType === "Approved"))
								str+='<td>-</td>';
								</c:otherwise>
									</c:choose>
									
									
								
								
							str+='</tr>';	
							}
						}
						
					}
				str+='</tbody>'
			str+='</table>';
		str+='</div>';
		str+='</div>';
		var approvedBooleanaVal=false;
		for(var i in result.desinationVOList){
			if(result.desinationVOList[i].desinationMebersVOList !=null && result.desinationVOList[i].desinationMebersVOList.length>0){
				for(var j in result.desinationVOList[i].desinationMebersVOList){
					if(result.desinationVOList[i].desinationMebersVOList[j].status == "Approved"){
						approvedBooleanaVal = true;
					}
				}
			}
		}
		
		if(!(statusType === "Approved") && bcType === true && scType === true && stType === true && approvedBooleanaVal === true){
			str+='<div class="col-sm-12 m_top20">';
				str+='<div class="row">';
				
					str+='<div class="col-sm-3">';
						str+='<label>Change Committee Status</label>';
						str+='<select class="form-control chosen-select" id="committeeStatusChangeId">';
							str+='<option value="approve">Approved</option>';
						str+='</select>';
					str+='</div>';
					
					str+='<div class="col-sm-2">';
						str+='<button type="button" class="btn btn-success btn-sm">Submit</button>';
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
		}
		
	str+='</div>';
	
	$("#committeeWisePopUpDetailsId").html(str);
	$("#committeeStatusChangeId").chosen();
	$("#committeeStatusChangeId").trigger("chosen:updated");
  }

</script>
</body>
</html>