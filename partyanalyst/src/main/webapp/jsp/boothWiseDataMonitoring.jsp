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
					<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>SELECT DISTRICT<span style="color:red">*</span>&nbsp;&nbsp;<span style="color:red;" id="errMsgDivId"></span></label>
						<select class="form-control" id="districtId" onchange="getConstituencies(this.value)">
						    <option value="0">Select District</option>
                        </select>
					</div>
                	<div class="col-md-3 col-xs-12 col-sm-6">
                    	<label>SELECT CONSTITUENCY</label><span style="color:red"> *</span>
						<select class="form-control" id="constituencyId" onchange="getUsers(this.value)">
						    <option value="0">Select Constituency</option>
                        </select>
					</div>
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-6">
                    	<button class="btn btn-success text-capital" id="getRegStatusId">submit</button>
						<img src="./images/icons/search.gif" id="populatingDtsDivImgId" style="margin-left:1070px; margin-top:6px;display:none;" />
					</div>
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
	var stateId = 0;
	var jsObj = { 
	      stateId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictListAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#districtId").append('<option value="0">Select District</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#districtId").trigger("chosen:updated");		
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
		url : 'getConstituencyListAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#constituencyId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#constituencyId").trigger("chosen:updated");		
	});
}
</script>
</body>
</html>