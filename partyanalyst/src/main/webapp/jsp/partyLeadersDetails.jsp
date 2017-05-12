<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				<div class="panel-body">
				<div class="row">
					<div class="col-sm-3">
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
							<option value="6">Village</option>
							<option value="8">Ward</option>
							<option value="5">Mandal</option>
							<option value="7">Town</option>
							<option value="9">Division</option>
							<option value="11">District</option>
							<option value="10">State</option>
							<option value="12">Central</option>
						</select>
					</div>
					
					<div class="col-sm-3"   id="committeeTypeDivId"  style="display:none" >
						<label>Committee Type </label>
						<select class="form-control chosenClass" id="committeeTypeId" multiple>
							<option value="1">	Main </option>
							<option value="2">	Telugu Yuvatha </option>
							<option value="3">	Telugu Mahila </option>
							<option value="4">	Telugu Rythu </option>
							<option value="5">	TNTUC </option>
							<option value="6">	BC Cell </option>
							<option value="7">	SC Cell </option>
							<option value="8">	ST Cell </option>
							<option value="9">	Minority Cell </option>
							<option value="10">	Legal Cell </option>
							<option value="11">	TNSF (Student Union) </option>
							<option value="12">	Commercial Cell </option>
							<option value="13">	Cultural Cell </option>
							<option value="14">	TNUS ( Teachers Union)  </option>
							<option value="15">	TSNV (Technical Expert Cell) </option>
							<option value="16">	Doctor Cell </option>
							<option value="17">	Trade </option>
							<option value="18">	Christian </option>
							<option value="19">	Telugu Rakshana Vedika </option>
							<option value="20">	Kallu Geetha Karmikulu </option>
							<option value="21">	Chenetha </option>

						</select>
					</div>
					
					<div class="col-sm-3 pull-right">
						<button type="button" id="btnId" class="btn btn-sm" style="border-width: 4px 12px 4px 15px; margin-top: 22px;"><b>View</b></button>
					</div>
				</div>
			  </div>
			</div>
			<div class="panel panel-default" id="leadersDetailsDiv" style="display:none;">
				<div class="panel-heading">
					<h4 class="panel-title text-capital" > 2016-2018 Leaders Details By Search Criteria  <button class="btn btn-min btn-xs btn-success pull-right" onclick="exportToExcel();" id="excelBtn" style="display:none;"> Export Excel </button> </h4>
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
getDistrictsForStates(1);
getPublicRepresentsDetails();
</script>
</body>
</html>