<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/css/custom.css" type="text/css" rel="stylesheet"/>

	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
	
<style type="text/css">
.switch-btn
{
  padding:3px;
  background-color: #fff;
  display: inline-block;
  border-radius: 14px;
}
.switch-btn li
{
  list-style:none;
  display: inline-block;
  padding: 4px 8px;
  cursor: pointer;
  text-transform:uppercase;
}
.switch-btn li.active
{
  background-color: #4A5863;
  color: #fff;
  border-radius: 14px;
  -moz-transition: .5s all ease-out;
}
.switch-btn li:hover
{
  background-color: #4A5863;
  -moz-transition: .5s all ease-out;
  border-radius: 14px;
  color:#fff;
}
.text-capital{
	text-transform: uppercase !important;
}
</style>
</head>
<body>
<div class="container">
<div class="row">
    <div class="col-md-12 col-xs-12 col-sm-12 m_top10">        
            
    </div>
  </div>
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12"> 
			<div class="panel panel-default panelheightsAlert">
			  <div class="panel-heading">
				<h4 class="panel-title text-capital" style="height: 30px;">Party Leaders Details  
					<ul class="switch-btn pull-right"  style="border: 1px solid rgb(211, 211, 211);">       
						<li attr_type="1" class="active" >AP</li>
						<li attr_type="36"  >TS</li>
					</ul>  			
				</h4>
			  </div>
			  <div id="errorMessegeId" style="color: red; padding-left: 15px;"></div>
				<div class="panel-body">
				<div class="row">
					<div class="col-sm-3" id="districtDivId">
						<label>District</label>
						<select class="form-control " id="districtId" >
							<option value="0">All</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Constituency</label>
						<select class="form-control " id="constituencyId">
							<option value="0">All</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Mandal/Town/Division</label>
						<select class="form-control " id="mandalId" >
							<option value="0">All</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Village/Ward</label>
						<select class="form-control " id="panchayatDivId">
							<option value="0">All</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<label>Leader Type</label>
						<select class="form-control" id="leaderTypeId">
                            <option value="0">All</option>
							<option value="1">Public representitive</option>
							<option value="2">Committee Member</option>
						</select>
					</div>
					
					<div class="col-sm-3">
						<label>Enrollment Year </label>
						<select class="form-control chosenClass" id="enrollmentId" multiple >
							<option value="4">2016-2018</option>
							<option value="3">2014-2016</option>
						</select>
					</div>
					
					<div class="col-sm-3">
						<label>Designation</label>
						<select class="form-control chosenClass" id="designationId" multiple>
							<option value="0">All</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3"  id="committeeLevelDivId" style="display:none">
						<label>Committee Level </label>
						<select class="form-control chosenClass" id="committeeLevelId" multiple>
							<option value="0">All</option>
						</select>
					</div>
					
					<div class="col-sm-3"   id="committeeTypeDivId"  style="display:none" >
						<label>Committee Type </label>
						<select class="form-control chosenClass" id="committeeTypeId" multiple>
							<option value="0">All</option>
						</select>
					</div>
					
					<div class="col-sm-3 pull-right">
						<button type="button" id="btnId" class="btn btn-sm" onclick="getLeadersDetasils('')" style="border-width: 4px 12px 4px 15px; margin-top: 22px;"><b>View</b></button>
					</div>
				</div>
			  </div>
			</div>
			<div class="panel panel-default" id="leadersDetailsDiv" style="display:none;">
				<div class="panel-heading">
					<h4 class="panel-title text-capital" > 2016-2018 Leaders Details By Search Criteria  <button class="btn btn-min btn-xs btn-success pull-right" onclick="exportToExcel('EXPORTEXCEL');" id="excelBtn" style="display:none;" > Export Excel </button> </h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="table table-bordered  text-capital" id="partyLeaderDetailsId" style="padding:10px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="dist/alertDashBoard/dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/exportexcel.js" type="text/javascript"></script>
<script src="js/LocationHierarchy/partyLeadersDetailsHierarchy.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
 
<script  type="text/javascript" >
$(".chosenClass").chosen();
var pageType='STATE';
var areasList= [];
var locationIdsArr=[];
<c:forEach var="item" items="${statesList}">
	pageType = '${item.type}';
	var obj = {
		id:'${item.id}',
		name:'${item.name}'
	}
	locationIdsArr.push('${item.id}');
	areasList.push(obj);
 </c:forEach>

 function buildOptions(){
	 if(pageType == 'Constituency'){
		 $('#districtDivId').hide();
		 $('.switch-btn').hide();
		if(areasList != null && areasList.length>0){
			 $("#constituencyId").html('');
			  $("#constituencyId").append('<option value="0">ALL</option>');
				for(var i in areasList){
					$("#constituencyId").append('<option value='+areasList[i].id+'>'+areasList[i].name+'</option>');
				}
		}
	 }else if(pageType == 'District'){
		  $('.switch-btn').hide();
		 if(areasList != null && areasList.length>0){
			 $("#districtId").html('');
			  $("#districtId").append('<option value="0">ALL</option>');
			for(var i in areasList){
				$("#districtId").append('<option value='+areasList[i].id+'>'+areasList[i].name+'</option>');
		   }
		}
	 }else{
		 getDistrictsForStates(1);
	 }
	 getPublicRepresentsDetails();
	// getCommitteeRoles();
	 $("#committeeLevelDivId").hide();
	 $("#committeeTypeDivId").hide();
 }
 
buildOptions();
</script>
</body>
</html>