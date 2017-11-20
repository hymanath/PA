
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">


<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Booth Committee Dashboard</title>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
<link href="Assets/css/boothIncharge.less" rel="stylesheet" type="text/less">
<script src="https://use.fontawesome.com/07d3416f74.js"></script>
<script src="Assets/js/less.js"></script>
<style type="text/css">
.footerCls
{
	position:static;
}
.sorting{
	cursor:pointer !important;
}
</style>
</head>
<body>

<c:if test="${fn:contains(sessionScope.USER.entitlements, 'BOOTH_COMMITTEE_UNLOCK_USER_ENTITLEMENT' )}">
	<div class="container-fluid">
				<div class="panel-body" id="accessToUpdationDivId">
					<div class="row" style="float:right;margin-right:15px;">
						<button class="btn btn-success btn-min btn-xs" id="addMembersBtn"> DASHBOARD </button>
						<button  class="btn btn-warning btn-min btn-xs" id="unlockBtn"> UNLOCK COMMITTEE </button>
					</div>
				</div>
				<div class="panel-body" id="unlockCommitteeId" style="display:none;" >
					<div class="row">
						<div class="col-sm-4" id="mandalMainDivId">
							<label for="">SELECT CONSTITUENCY <span style="color:red">*</span> </label>
							<select onchange="getCommitteeFinalizedBoothListforUnlock(this.value)" class="form-control" id="constituencyId" ><option value="0">Select Constituency </option></select>
						</div>								
						<div class="col-sm-4">
							<label for="committeeLocationIds1">SELECT BOOTH <span style="color:red">*</span><img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/> </label>
							<select onchange="populateDefaultValue(1);getBoothInchargeRoles();gettingBoothInchargeRoleDetails();" class="form-control" id="committeeLocationIds1" ><option value="0">Select Booth </option></select >
						</div>	
						
						<button class="btn btn-danger btn-min btn-xs" onclick="removeLock()" style="margin-top:30px"> Remove Lock</button>
					</div>
			</div>
		</div>
	</c:if>	
		<div class="panel-body" id="addMemebrsDiv" >
        <section class="m_top20">
            <div class="container-fluid">
				<div class="row">
				 <h4 class="text-center"><span style="border: 1px solid rgba(119, 119, 119, 0.467); border-radius: 20px; padding: 10px; background-color: rgb(255, 255, 255); font-weight: 600;">
				 BOOTH COMMITTEE DASHBOARD</span></h4>
					<div class="col-sm-3 pull-right m_top20" >
						<select class="form-control chosen-select" id="boothCommitteeDashbrdRolesId">
							
						</select>
					</div>
					<div class="col-sm-3 pull-right m_top20">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
							<input type="text" class="form-control pull-right"  name="daterangePicker" id="daterangePickerId"/>
						</div>
					</div>
				</div>
				<div class="row m_top10">
				  <div id="overAllBoothDlstDivId"></div>
                </div>
            </div>
        </section>
        <section class="m_top20">
            <div class="container-fluid">
                <div class="col-sm-12">
                        <div class="committeeMembers">
                            <div class="subBlock col-sm-12 text-center">
                                
								<div id="overAllSerialRangeWiseVoterDivId"></div>
					        </div>
                        </div>
                    </div>
            </div>
        </section>
		
        <section class="m_top20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="districtAccessLevelId">
							<div class="panel-heading" id="headingOne">
								<div class="row" >
									<div class="col-sm-6">
										<h4 class="panel-title m_top5 text-capital districtParliamentLevleHadingCls">DISTRICT WISE</h4>
									</div>
									<div class="col-sm-5">
										<ul class="nav navbar-nav table-menu pull-right" table-menu="">
											<li class="active districtLevelCls" style="cursor:pointer;" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="districtLevelCls" style="cursor:pointer;" attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1">
										<a class="profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="panel-body">
							   <div id="dstrctParlmntLvlBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
        <section class="m_top20">
            <div class="conatiner-fluid">
                  <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="constituencyAccessLevelId">
							<div class="panel-heading" id="headingTwo">
								<div class="row">
									<div class="col-sm-6">
										<h4 class="panel-title m_top5 text-capital">CONSTITUENCY WISE</h4>
									</div>
									<div class="col-sm-5" id="constitencyWiseDistrictParliamentId">
										<ul class="nav navbar-nav table-menu pull-right">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_select_box_id="constituencyLevelDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_select_box_id="constituencyLevelDistrictSelectBxId" attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1 collapseDivCls">
										<a class=" profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
							<div class="panel-body">
								<div class="row" >
									<div class="col-sm-3" id="constituencyWiseDistrictId">
										<select id="constituencyLevelDistrictSelectBxId" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_sub_level_drop_box_id="constituencyLevelConstituenySelectBxId" class="selectBoxCls" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
											<option value="0">All DISTRICT</option>
										</select>
									</div>
									<div  class="col-sm-3">
										<select class="selectBoxCls" id="constituencyLevelConstituenySelectBxId" attr_result_level="CONSTITUENCY" attr_result_level_div_id="constituencyLevelBoothDtlsDivId" attr_level="CONSTITUENCY" attr_parent_level_drop_box_id="constituencyLevelDistrictSelectBxId"  attr_parent_level="DISTRICT">
											<option value="0">All CONSTITUENCY</option>
										</select>
									</div>
								</div>
							  <div id="constituencyLevelBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
        <section class="m_top20">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="mandalAccessLevelId">
							<div class="panel-heading" id="headingThree">
								<div class="row">
									<div class="col-sm-6">
										<h4 class="panel-title m_top5 text-capital">MANDAL WISE</h4>
									</div>
									<div class="col-sm-5" id="mandalWiseDisticparlimentIds">
										<ul class="nav navbar-nav table-menu pull-right">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_select_box_id="mandalLevelDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_select_box_id="mandalLevelDistrictSelectBxId" attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1 collapseDivCls">
										<a class="profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
							<div class="panel-body">
								<div class="row" id="mandalWiseDistrictId" style="cursor:pointer;">
									<div  id="selectConstituencyDistrict">
										<div class="col-sm-3" id="mandalLevelDistrictDivId">
											<select id="mandalLevelDistrictSelectBxId" class="selectBoxCls"  attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_sub_level_drop_box_id="mandalLevelConstituenySelectBxId" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
												<option value="0">All DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3" id ="mandalLevelConstituencyDivId">
											<select id="mandalLevelConstituenySelectBxId" class="selectBoxCls"  attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_sub_level_drop_box_id="mandalLevelMandalSelectBxId" attr_level="CONSTITUENCY" attr_sub_level="TEHSIL" attr_parent_level_drop_box_id="mandalLevelDistrictSelectBxId"  attr_parent_level="DISTRICT">
												<option value="0">All CONSTITUENCY</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="mandalLevelMandalSelectBxId"   class="selectBoxCls" attr_result_level="TEHSIL" attr_result_level_div_id="mandalLevelBoothDtlsDivId" attr_level="TEHSIL" attr_parent_level_drop_box_id="mandalLevelConstituenySelectBxId"  attr_parent_level="CONSTITUENCY">
												<option value="0">All MANDAL</option>
											</select>
										</div>
									</div>										
								</div>
								<div id="mandalLevelBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </section>
		<section class="m_top20 panchaytBlockCls" style="display:none;">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default" id="panchayatAccessLevelId">
							<div class="panel-heading" id="headingFour">
								<div class="row">
									<div class="col-sm-3">
										<h4 class="panel-title m_top5 text-capital" >PANCHAYAT WISE</h4>
									</div>
									<div class="col-sm-4">
										<ul class="nav navbar-nav table-menu pull-right resultTypeUL">
											<li class="active resultTypeTabCls" style="cursor:pointer;" attr_heading_level="SUMMARY" >SUMMARY</li>
											<li class="resultTypeTabCls" style="cursor:pointer;" attr_heading_level="DETAILS" >BOOTH WISE</li>
										</ul>
									</div>
									<div class="col-sm-4" id ="panchayatWiseDistrictparliamentIds">
										<ul class="nav navbar-nav table-menu pull-right" table-menu="">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_select_box_id="panchatLevelDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_select_box_id="panchatLevelDistrictSelectBxId"  attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1 collapseDivCls1">
										<a class="collapsed profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
							<div class="panel-body">
								<div class="row">
									<div id="selectConstituencyDistrict">
										<div class="col-sm-3" id="panchayatWiseDisticDivId">
											<select id="panchatLevelDistrictSelectBxId"  class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_sub_level_drop_box_id="panchaytLevelConstituenySelectBxId" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
												<option value="0">All DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3" id="panchayatWiseConstituencyDivId">
											<select id="panchaytLevelConstituenySelectBxId" class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_sub_level_drop_box_id="panchaytLevelMandalSelectBxId" attr_level="CONSTITUENCY" attr_sub_level="TEHSIL" attr_parent_level_drop_box_id="panchatLevelDistrictSelectBxId"  attr_parent_level="DISTRICT">
												<option value="0">All CONSTITUENCY</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="panchaytLevelMandalSelectBxId" class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId"  attr_sub_level_drop_box_id="panchaytLevelPanchaytSelectBxId"  attr_level="TEHSIL" attr_sub_level="PANCHAYAT" attr_parent_level_drop_box_id="panchaytLevelConstituenySelectBxId"  attr_parent_level="CONSTITUENCY">
												<option value="0">All MANDAL</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="panchaytLevelPanchaytSelectBxId" class="selectBoxCls"  attr_result_level="PANCHAYAT" attr_result_level_div_id="panchaytLevelBoothDtlsDivId" attr_level="PANCHAYAT" attr_parent_level_drop_box_id="panchaytLevelMandalSelectBxId"  attr_parent_level="TEHSIL">
												<option value="0">All PANCHAYAT</option>
											</select>
										</div>
									</div>										
								</div>
								<div id="panchaytLevelBoothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
       
	</section>
	<section class="m_top20 boothBlckCls">
            <div class="conatiner-fluid">
                <div class="col-sm-12 tableBlock">
                    <div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading" id="headingFour">
								<div class="row">
									<div class="col-sm-3">
										<h4 class="panel-title m_top5 text-capital" >BOOTH WISE</h4>
									</div>
									<div class="col-sm-4" >
										<ul class="nav navbar-nav table-menu pull-right resultTypeUL">
											<li class="resultTypeTabCls boothResultTypeCls" style="cursor:pointer;" attr_heading_level="SUMMARY">SUMMARY</li>
											<li class="active resultTypeTabCls boothResultTypeCls" style="cursor:pointer;" attr_heading_level="DETAILS">BOOTH WISE</li>
										</ul>
									</div>
									<div class="col-sm-4">
										<ul class="nav navbar-nav table-menu pull-right" id="boothLevelDistParTabDivId" table-menu="">
											<li class="active locationLevelTabCls" style="cursor:pointer;" attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_select_box_id="boothBlockDistrictSelectBxId" attr_tab_level_value="DISTRICT">DISTRICT</li>
											<li class="locationLevelTabCls" style="cursor:pointer;" attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_select_box_id="boothBlockDistrictSelectBxId"  attr_tab_level_value="PARLIAMENT CONSTITUENCY">PARLIAMENT</li>
										</ul>
									</div>
									<div class="col-sm-1">
										<a class="boothClopseCls profieViewCollapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive" style="width:20px;height:20px;display:block;float:right"></a>
									</div>
								</div>
							</div>
							<div id="collapseFive" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFour">
							<div class="panel-body">
								<div class="row">
									<div>
										<div class="col-sm-3" id="boothLevelDistDivId">
											<select id="boothBlockDistrictSelectBxId"  class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_sub_level_drop_box_id="boothBlockConstituenySelectBxId" attr_level="DISTRICT" attr_sub_level="CONSTITUENCY">
												<option value="0">All DISTRICT</option>
											</select>
										</div>
										<div class="col-sm-3" id="boothLevelConDivId">
											<select id="boothBlockConstituenySelectBxId" class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_sub_level_drop_box_id="boothBlockMandalSelectBxId" attr_level="CONSTITUENCY" attr_sub_level="TEHSIL" attr_parent_level_drop_box_id="boothBlockDistrictSelectBxId"  attr_parent_level="DISTRICT">
												<option value="0">All CONSTITUENCY</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="boothBlockMandalSelectBxId" class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId"  attr_sub_level_drop_box_id="boothBlockPanchaytSelectBxId"  attr_level="TEHSIL" attr_sub_level="PANCHAYAT" attr_parent_level_drop_box_id="boothBlockConstituenySelectBxId"  attr_parent_level="CONSTITUENCY">
												<option value="0">All MANDAL</option>
											</select>
										</div>
										<div class="col-sm-3">
											<select id="boothBlockPanchaytSelectBxId" class="boothBlockCls"  attr_result_level="BOOTH" attr_result_level_div_id="boothDtlsDivId" attr_level="PANCHAYAT" attr_parent_level_drop_box_id="boothBlockMandalSelectBxId"  attr_parent_level="TEHSIL">
												<option value="0">All PANCHAYAT</option>
											</select>
										</div>
									</div>										
								</div>
								<div id="boothDtlsDivId" class="m_top20"></div>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
        </div>
	</section>
	</div>
	<!-- Model for Debate Start-->
		<div class="modal" tabindex="-1" role="dialog" id="boothInchargeDataModalDivId">
		  <div class="modal-dialog modal-lg" style="width:85%">
			<div class="modal-content" style="border-radius:0px">
			  <div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  </div>
			  <div class="modal-body">
				  <div  class="row">
					<div class="col-sm-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">BOOTH MEMBER DETAILS</h4>
							</div>
							<div class="panel-body">
								<div id="boothInchargeRoleDivId" ></div>
							</div>
						</div>
					<h4 class="modal-title" id="modalLabelNameId">CADRE DETAILS</h4>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title"></h4>
							</div>
							<div class="panel-body">
								<div id="cadreDetailsDivId"></div>
							</div>
						</div>
					</div>
				  </div>				 
					<!--<center><img id="dataLoadingsImgForDebate" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>-->
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/> 
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="Assets/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assets/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/BoothPage/boothInchargeDashboard.js" type="text/javascript"></script>
<script type="text/javascript">
boothInchargeRoles();
var accessLevelArr=[];
var accessType1 = '${sessionScope.USER.accessType}';
var accessValue1 = '${sessionScope.USER.accessValue}';
var accessType2 = '${userVO.accessType}';
var accessValue2 = '${userVO.accessValues}';
//var accessLevelId = '${userVO.accessLevelId}';
//var activityMemberId = '${userVO.activityMemberId}';
var accessValues =[];
var accessType="";
if(accessType2 == null || accessType2.length ==0){
	accessType = accessType1;
}else{
	accessType = accessType2;
}



$(document).on("click","#addMembersBtn",function(){
	$('#addMemebrsDiv').show();
	$('#unlockCommitteeId').hide();
});
$(document).on("click","#unlockBtn",function(){
	$('#addMemebrsDiv').hide();
	$('#unlockCommitteeId').show();
});

<c:forEach items="${userVO.accessValues}" var="userAccessLevelValue">
	accessValues.push( ${userAccessLevelValue} );        
</c:forEach>  

if(accessValues.length == 0){
    accessValues.push(accessValue1);
}

var accessValueStr = '';
if(accessValues != null && accessValues.length > 0){
	for(var i in accessValues){
		if(i == 0){
			accessValueStr = accessValues[i]+"";
		}else {
			accessValueStr = accessValueStr+","+accessValues[i];
		}
	}
}	   
$(document).ready(function(){
	if(accessType == "MLA" || accessType == "SEC" || accessType == "CONST INCHARGE" || accessType=="ORG SEC"){
		
		$("#districtAccessLevelId").hide();
		$("#constituencyAccessLevelId").hide();
		//block tab heading hiding 
		$("#boothLevelDistParTabDivId").hide();
		$("#mandalWiseDisticparlimentIds").hide();
		$("#mandalLevelDistrictDivId").hide();
		$("#panchayatWiseDistrictparliamentIds").hide();
		//select box hiding 
		$("#mandalLevelConstituencyDivId").hide();
		$("#panchayatWiseDisticDivId").hide(); 
		$("#panchayatWiseConstituencyDivId").hide(); 
		$("#boothLevelDistDivId").hide(); 
		$("#boothLevelConDivId").hide(); 
		/* Adding and removing class dynamically */
		$(".collapseDivCls").removeClass("col-sm-1");
		$(".collapseDivCls").addClass("col-sm-6");
		$(".collapseDivCls1").removeClass("col-sm-1");
		$(".collapseDivCls1").addClass("col-sm-5");
		//Adding Dynamic Attribute based on user access level 
		$(".resultTypeTabCls").attr("attr_panchayt_result_level","TEHSIL");
		$(".resultTypeTabCls").attr("attr_pnchyt_lvl_parent_select_box_div_id","panchaytLevelMandalSelectBxId");
		$(".boothResultTypeCls").attr("attr_booth_result_level","TEHSIL");
		$(".boothResultTypeCls").attr("attr_boot_level_parent_select_box_div_id","boothBlockMandalSelectBxId");
		//ajax call based on user access level
		getOverAllBoothDetails("STATE","CONSTITUENCY",accessValueStr);
		getLocationLevelWiseBoothCount("TEHSIL","CONSTITUENCY",accessValueStr,"mandalLevelBoothDtlsDivId");
		getLocationBasedOnSelection("TEHSIL","CONSTITUENCY",accessValueStr,"","CONSTITUENCY");
	    //setting dynamically user access type and access value based on login user
	    setAccessLevelTypeAndValue("CONSTITUENCY",accessValueStr);
		getLocationLevelWiseBoothDetails("CONSTITUENCY",accessValueStr,"All");
	  
	}else if(accessType == "MP" || accessType == "PARLIAMENT INCHARGE"){
		//block tab heading hiding 
		$("#panchayatWiseDistrictparliamentIds").hide();
		$("#mandalLevelDistrictDivId").hide();
		$("#mandalWiseDisticparlimentIds").hide();
		$("#boothLevelDistParTabDivId").hide();
		/* Adding and removing class dynamically */
		$(".collapseDivCls").removeClass("col-sm-1");
		$(".collapseDivCls").addClass("col-sm-6");
		$(".collapseDivCls1").removeClass("col-sm-1");
		$(".collapseDivCls1").addClass("col-sm-5");
		
		//select box hiding 
		$("#districtAccessLevelId").hide();
		$("#constitencyWiseDistrictParliamentId").hide();
		$("#constituencyWiseDistrictId").hide();
		$("#panchayatWiseDisticDivId").hide();
        $("#boothLevelDistDivId").hide(); 
		//Adding Dynamic Attribute based on user access level 
		$(".resultTypeTabCls").attr("attr_pnchyt_lvl_parent_select_box_div_id","panchaytLevelConstituenySelectBxId");
		$(".resultTypeTabCls").attr("attr_panchayt_result_level","CONSTITUENCY");
		$(".boothResultTypeCls").attr("attr_booth_result_level","CONSTITUENCY");
		$(".boothResultTypeCls").attr("attr_boot_level_parent_select_box_div_id","boothBlockConstituenySelectBxId");
		//setting dynamically user access type and access value based on login user
		setAccessLevelTypeAndValue("PARLIAMENT CONSTITUENCY",accessValueStr);
        //ajax call based on user access level
		getOverAllBoothDetails("STATE","PARLIAMENT CONSTITUENCY",accessValueStr);
		getLocationLevelWiseBoothCount("CONSTITUENCY","PARLIAMENT CONSTITUENCY",accessValueStr,"constituencyLevelBoothDtlsDivId");
		getLocationLevelWiseBoothCount("TEHSIL","PARLIAMENT CONSTITUENCY",accessValueStr,"mandalLevelBoothDtlsDivId");
		getLocationLevelWiseBoothDetails("PARLIAMENT CONSTITUENCY",accessValueStr,"All");
		getLocationBasedOnSelection("CONSTITUENCY","PARLIAMENT CONSTITUENCY",accessValueStr,"","PARLIAMENT CONSTITUENCY");
	
	}else if(accessType == "DISTRICT" || accessType=="DIST PRESIDNET" || accessType=="GS" || accessType=="INCHARGE MINISTER"){
			$(".boothClopseCls").addClass("collapsed");
			$("#collapseFive").removeClass("in");
			//Adding Dynamic Attribute based on user access level 
			var accessLevel = "DISTRICT";
			/* if(activityMemberId != null && activityMemberId==4 || activityMemberId==5){
				accessLevel="CONSTITUENCY";
			} */
			$(".resultTypeTabCls").attr("attr_panchayt_result_level","DISTRICT");
			$(".resultTypeTabCls").attr("attr_pnchyt_lvl_parent_select_box_div_id","panchatLevelDistrictSelectBxId");
			$(".boothResultTypeCls").attr("attr_booth_result_level","DISTRICT");
			$(".boothResultTypeCls").attr("attr_boot_level_parent_select_box_div_id","boothBlockDistrictSelectBxId");
	
			getOverAllBoothDetails("STATE",accessLevel,accessValueStr);
			getLocationLevelWiseBoothCount("DISTRICT",accessLevel,accessValueStr,"dstrctParlmntLvlBoothDtlsDivId");
			getLocationLevelWiseBoothCount("CONSTITUENCY",accessLevel,accessValueStr,"constituencyLevelBoothDtlsDivId");
			getLocationLevelWiseBoothCount("TEHSIL",accessLevel,accessValueStr,"mandalLevelBoothDtlsDivId");
			getLocationBasedOnSelection("DISTRICT",accessLevel,accessValueStr,"","All");
			//setting dynamically user access type and access value based on login user
			setAccessLevelTypeAndValue("DISTRICT",accessValueStr);
			
	}else if (accessType == "STATE"){
			$(".boothClopseCls").addClass("collapsed");
			$("#collapseFive").removeClass("in");
			//Adding Dynamic Attribute based on user access level 
			$(".resultTypeTabCls").attr("attr_panchayt_result_level","DISTRICT");
			$(".resultTypeTabCls").attr("attr_pnchyt_lvl_parent_select_box_div_id","panchatLevelDistrictSelectBxId");
			$(".boothResultTypeCls").attr("attr_booth_result_level","DISTRICT");
			$(".boothResultTypeCls").attr("attr_boot_level_parent_select_box_div_id","boothBlockDistrictSelectBxId");
	
			getOverAllBoothDetails("STATE","STATE",accessValueStr);
			getLocationLevelWiseBoothCount("DISTRICT","STATE",accessValueStr,"dstrctParlmntLvlBoothDtlsDivId");
			getLocationLevelWiseBoothCount("CONSTITUENCY","STATE",accessValueStr,"constituencyLevelBoothDtlsDivId");
			getLocationLevelWiseBoothCount("TEHSIL","STATE",accessValueStr,"mandalLevelBoothDtlsDivId");
			//getLocationLevelWiseBoothCount("PANCHAYAT","STATE",accessValueStr,"panchaytLevelBoothDtlsDivId");
			getLocationBasedOnSelection("DISTRICT","STATE",accessValueStr,"","All");
			//setting dynamically user access type and access value based on login user
			setAccessLevelTypeAndValue("STATE",accessValueStr);
	} 
	function setAccessLevelTypeAndValue(accessType,accessValue){
		
		   $(".locationLevelTabCls").attr("accessType",accessType);
		   $(".locationLevelTabCls").attr("accessValue",accessValue);
		   $(".districtLevelCls").attr("accessType",accessType);
		   $(".districtLevelCls").attr("accessValue",accessValue);
		   $(".resultTypeTabCls").attr("accessType",accessType);
		   $(".resultTypeTabCls").attr("accessValue",accessValue);
		   $("#daterangePickerId").attr("accessType",accessType);
		   $("#daterangePickerId").attr("accessValue",accessValue);
		   $("#boothCommitteeDashbrdRolesId").attr("accessType",accessType);
		   $("#boothCommitteeDashbrdRolesId").attr("accessValue",accessValue);
	}
});
</script>
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
</body>
</html>