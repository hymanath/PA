<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>BOARD WISE NOMINATED POSTS</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.tableShort thead th:nth-child(1)
{
	width:10% !important
}
.tableShort thead th:last-child
{
	width:7% !important
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">Select Location</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-3" id="stateMainId" >
							<label>State</label>
							<select class="form-control" id="stateId">
								<option value="">Select State</option>
								<option value="1">Andhara Pradesh</option>
								<option value="36">Telangana</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3" style="display:none" id="districtMainId">
							<label>District</label>
							<select class="form-control" id="districtId">
								<option value="">Select District</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3" style="display:none" id="constituencyMainId">
							<label>Constituency</label>
							<select class="form-control" id="constituencyId">
								<option value="">Select Constituency</option>
							</select>
						</div>
						<div class="col-md-3 col-xs-12 col-sm-3" style="display:none" id="manTowDivMainId">
							<label>Mandal/Town/Division</label>
							<select class="form-control" id="manTowDivId">
								<option value="">Select Mandal/Town/Division</option>
							</select>
						</div>
						
					</div class="row">
					<div>
						<div class="col-md-3 col-xs-12 col-sm-3">						
							<input type="button" class="btn btn-primary btn-sm" value="Submit" style="margin-top: 25px;" id="locationWiseDataId"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12">
			<div id="departmentsBuildId"></div>
		</div>
	</div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/nominatedPosts/nominatedPostManagement.js" type="text/javascript"></script>
<script type="text/javascript">
/* var globalLevelId = '${param.lId}';
var globalStateId = '${param.stId}'; 
var globalStatus='${param.sts}';*/
var globalLevelId = 2;
var globalStateId = 1;

$("#stateId").val(globalStateId);

var grlobalDistrictArr=[];
var globalAssmblyArr=[];
var globalMandalTowDivArr=[];
$(document).ready(function() {
	if(globalLevelId !=1 && globalLevelId !=2){
		getDistrictsForStates(globalStateId);
	}else{
		$("#districtMainId").hide();
		$("#constituencyMainId").hide();
		$("#manTowDivMainId").hide();
	}	
});

</script>
</body>
</html>