<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mahatma Gandhi National Rural EGS</title>
<link href="Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="Assests/Plugins/DateTime/bootstrap-datetimepicker-build.less" type="text/less" rel="stylesheet"/>
<link href="Assests/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Scroller/jquery.mCustomScrollbar.css" type="text/less" rel="stylesheet"/>
<script src="Assests/Plugins/Less/less.js"></script>
<link href="Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<link href="Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>

</head>

<div>Test...</div>

<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="Assests/Plugins/DateTime/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="Assests/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>

<script>
//getAllConvergenceTypes();
function getAllConvergenceTypes()
{
	alert(2)
	var json = {
	}
	
	$.ajax({
		url: 'getAllConvergenceTypes',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			//buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId)
		}
	});
}
//getComponentByConvergType();
function getComponentByConvergType()
{
	var json = {
		convergenceTypeId : 1
	}
	
	$.ajax({
		url: 'getComponentByConvergType',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			//buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId)
		}
	});
}
//getNREGSConsolidatedAbstrct();
function getNREGSConsolidatedAbstrct()
{
	//$("#nregsOverviewBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : "2017-04-01",
		toDate : "2017-07-30",
		locationType: "district",
		locationIdStr : "12",
		componentIds : [1,2]
	}
	$.ajax({
		url: 'getNREGSConsolidatedAbstrct',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			//buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId);
		}
	});
}
</script>
