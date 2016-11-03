<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FIELD MONITORING REPORT</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/WebMonitoring/responsive.css" rel="stylesheet" type="text/css"/>
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<style type="text/css">
</style>
<div class="container m_top20">
  <h4></h4>
  <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="block bg_ff">
				<h4>FIELD MONITORING REPORT<button class="btn btn-success btn-sm">Export to PDF/Excel</button></h4>
				<div id="fieldUserDetailsImgId" style="display:none;"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				<div id="errorDivId"></div>
				<div id="fieldUserDetailsId"></div>
			</div>
		</div>
	</div>
</div>

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>


<script type="text/javascript">
getFieldMonitoringReport();

function getFieldMonitoringReport(){
	$("#fieldUserDetailsImgId").show();
	$("#fieldUserDetailsId").html("");
	
	var jsObj = { 
	}
	$.ajax({
		type : 'GET',
		url : 'getFieldMonitoringUserWiseDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null && result.length > 0){
			buildFieldMonitoringReport(result);
		}
		else{
			$("#fieldUserDetailsImgId").hide();
			$("#fieldUserDetailsId").html('<h4>NO DATA AVAILABLE...</h4>');
		}
	});
}

function buildFieldMonitoringReport(result){
	var str = '';
	
	str+='<div class="table-responsive">';
		str+='<table class="table b_1 m_top10" id="tabWiseDtlsId">';
			str+='<thead>';
				str+='<th>FM USER ID</th>';
				str+='<th>FM USER CONTACT NUMBER</th>';
				str+='<th>DISTRICT</th>';
				if(result[0].idnameList != null && result[0].idnameList.length > 0){
					for(var i in result[0].idnameList){
						str+='<th>'+result[0].idnameList[i].name+'</th>'
					}
				}
				if(result[0].idnameList1 != null && result[0].idnameList1.length > 0){
					for(var i in result[0].idnameList1){
						str+='<th>'+result[0].idnameList1[i].name+'</th>'
					}
				}
			str+='</thead>';
			for(var i in result){   
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td> - </td>';
					str+='<td>'+result[i].districtName+'</td>';
					if(result[i].idnameList != null && result[i].idnameList.length > 0){
						for(var j in result[i].idnameList){
							str+='<td>'+result[i].idnameList[j].attenteeCount+'</td>'
						}
					}
					if(result[i].idnameList1 != null && result[i].idnameList1.length > 0){
						for(var j in result[i].idnameList1){
							str+='<td>'+result[i].idnameList1[j].attenteeCount+'</td>'
						}
					}
				str+='</tr>';  
			}
		str+='</table>';
	str+='</div>';
	
	$("#fieldUserDetailsImgId").hide();
	$("#fieldUserDetailsId").html(str);
	$("#tabWiseDtlsId").dataTable();
}
</script>
</body>
</html>