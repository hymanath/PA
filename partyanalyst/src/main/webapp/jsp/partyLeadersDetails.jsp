<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="dist/alertDashBoard/dist/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="dist/alertDashBoard/dist/css/custom.css" type="text/css" rel="stylesheet"/>

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
						<select class="form-control" id="districtId">
							<option value="0">Select District</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Constituency</label>
						<select class="form-control" id="constituencyId">
							<option value="0">Select Constituency</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Mandal/Town/Division</label>
						<select class="form-control" id="mandalId" >
							<option value="0">Select Level</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Village/Ward</label>
						<select class="form-control" id="panchayatDivId">
							<option value="0">Select Village/Ward</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Leader Type</label>
						<select class="form-control" id="leaderTypeId">
							<option value="0">Committee Member</option>
							<option value="1">Public representitive</option>
						</select>
					</div>
					<div class="col-sm-3">
						<label>Designation</label>
						<select class="form-control" id="designationId">
							<option value="0">Select Designation</option>
						</select>
					</div>
					<div class="col-sm-3">
						<button type="button" id="btnId" class="btn btn-sm" style="border-width: 4px 12px 4px 15px; margin-top: 22px;"><b>View</b></button>
					</div>
				</div>
			  </div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title text-capital">Title</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<table class="table table-bordered" id="partyLeaderDetailsId">
							
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="dist/alertDashBoard/dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="alertDashBoard/dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/LocationHierarchy/partyLeadersDetailsHierarchy.js" type="text/javascript"></script>

<script  type="text/javascript" >
getDistrictsForStates(1);
getCommitteeRoles();
</script>
</body>
</html>