<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Privileged User Access</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="tourDetails/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
</head>
	<div class="container">    
		<!-- Title Row -->
		<div class="row m_top10" id="fadeInDown">
			<div class="col-md-12 col-xs-12 col-sm-12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">2016 Cadre Admin Dashboard</h3>
			</div>
		</div>
		<!-- Title Row End-->
		<!-- Members Count Row -->  
		<div class="row fadeInUp">
			<div class="col-md-12 col-xs-12 col-sm-12 well well-small border-radius-0 mb-10">
				<div class="row">
					<div class="col-md-6 col-xs-12 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#c8d7f4">
								<h4 class="panel-title">TODAY</h4>
							</div>
							<div class="panel-body">
								<div class="row">
									<div id="todayRegisCount">
										<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-12 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#eae798">
								<h4 class="panel-title">OVER ALL</h4>
							</div>
							<div class="panel-body">
								<div id="totalRegisCount">
									<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10">
				<div class="row">
					<div class="col-md-6 col-xs-12 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#c8d7f4">
								<h4 class="panel-title">TODAY</h4>
							</div>
							<div class="panel-body">
								<div id="todayApTgRegisCount" class="row ">
									<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xs-12 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#eae798">
								<h4 class="panel-title">OVER ALL</h4>
							</div>
							<div class="panel-body">
								<div id="totalApTgRegisCount" class="row">
									<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Members Count Row End -->
		<!----New code for constituency and district wise Start ----->
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> District  Target VS Registered Cadre</h4>
				<div style="padding:5px;">
					<input type="radio" class="radiobuttonSelectedWise" id="" name="compareC" value="today" checked="true" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> TODAY</span>
					<input type="radio" class="radiobuttonSelectedWise" id="" name="compareC" value="total" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> OVER ALL </span>
				</div>
				<div id="districtWise2016Details"   class="m_top10"></div>
				<div id="leaderDataDiv2" style="margin-top: -1px">
					
				</div>
			</div>
		</div>  
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> Constituency  Target Vs Registered Cadre</h4>
				<div style="padding:5px;">
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="todayconstituencyValue" name="compareD" value="today" checked="true" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> TODAY</span>
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="totalconstituencyValue" name="compareD" value="total" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> OVER ALL</span>
				</div>
				<div id="constituencyWise2016Details"   class="m_top10"></div>
				<div id="leaderDataDiv1" class="scrollable_div" style="margin-top: -1px">
				</div>
			</div>
		</div>
		<!----New code for constituency and district wise Start ----->
	</div>
<body>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>  
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
	var userId = 6946;  
	//var userId = 6726;                  
	var type = "Total";
	var locationType = "AP";    
	get2016LocationWiseRegisteredCounts(userId,locationType,type);
	function get2016LocationWiseRegisteredCounts(userId,locationType,type){
		var jObj = {
			type : type,
			userId : userId,        
			locationType : locationType    
		}
		$.ajax({
			type:'GET',
			url: 'get2016LocationWiseRegisteredCounts.action',
			data : {task:JSON.stringify(jObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildGet2016LocationWiseRegisteredCounts(result);
			}
		});
	}
	function buildGet2016LocationWiseRegisteredCounts(result){
		if(result.length == 2){
			//table one starts
			var str1='';
			if(result[0] !=null && result[0].length > 0 ){
				str1+='<div class="table-responsive" style="margin-top:20px;">';
				str1+='<table class="table table-bordered" >';
				str1+='<thead>';
					str1+='<th>VERY GOOD</th>';
					str1+='<th>GOOD</th>';
					str1+='<th>OK</th>';
					str1+='<th>POOR</th>';
					str1+='<th>VERY POOR</th>';
				str1+='</thead>';
				str1+='<tbody>';
					str1+='<tr>';
						str1+='<td ><div class="cCodeDiv" style="background-color:green;"/>'+result[0][0].veryGood+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:lightgreen;"/>'+result[0][0].good+'</td>';
						str1+='<td><div class="cCodeDiv" style="background-color:yellow;"/>'+result[0][0].ok+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+result[0][0].poor+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+result[0][0].veryPoor+'</td>';
					str1+='</tr>';
					str1+='</tbody>';
				str1+='</table>';
			
				if($(window).width > 768)
				{
					str1+='<div class="table-responsive">';
				}
			
				str1+='<table class="table table-bordered" id="districtWise2016DataTableId">';
				str1+='<thead>';
					str1+='<th>District</th>';
					str1+='<th>Target Cadres</th>';
					str1+='<th>Renewal</th>';
					str1+='<th>New</th>';
					str1+='<th>Total</th>';
					str1+='<th>% of Register cadres</th>';
				str1+='</thead>';
				str1+='<tbody>';
				for(var i in result[0]){
					str1+='<tr>';
					str1+='<td>'+result[0][i].name+'</td>';
					str1+='<td>'+result[0][i].targetCount+'</td>';
					
					if(result[0][i].renewalPerc == null || result[0][i].renewalPerc == 0){
						str1+='<td> - </td>';
					}else{
						if(result[0][i].renewalCount == null || result[0][i].renewalCount == 0){
						str1+='<td>'+result[0][i].renewalCount+'</td>';
						}else{
							str1+='<td>'+result[0][i].renewalCount+'<small>('+result[0][i].renewalPerc+' %)</small></td>';
						}
					}
					
					
					if(result[0][i].newPerc == null || result[0][i].newPerc == 0){
						str1+='<td> - </td>';
					}else{
						if(result[0][i].newCount == null || result[0][i].newCount == 0){
							str1+='<td>'+result[0][i].newCount+'</td>';
						}else{
							str1+='<td>'+result[0][i].newCount+'<small>('+result[0][i].newPerc+' %)</small></td>';
						}
					}
					
					
					if(result[0][i].count2016 == null || result[0][i].count2016 == 0){
						str1+='<td> - </td>';
					}else{
						str1+='<td>'+result[0][i].count2016+'</td>';
					}
					
					if(result[0][i].perc2016 == null || result[0][i].perc2016 == 0 || result[0][i].perc2016 == ""){
						str1+='<td> - </td>';
					}else{
						var colorStatus = getColorCodeByStatus(result[0][i].levelPerformanceType)
						str1+='<td style="color:'+colorStatus+';">'+result[0][i].perc2016+'</td>';
					}
					str1+='</tr>';
				}
				str1+='</tbody>';
				str1+='</table>';
				if($(window).width > 768)
				{
					str1+='</div>';
				}
			}
		
			$("#districtWise2016Details").html(str1);
			$("#districtWise2016DataTableId").dataTable({
				"order": [ 4, 'desc' ]
			});
			//table one ends
			
			
		}else{
			var str='';
			if(result !=null && result.length >0){
				str+='<div class="table-responsive" style="margin-top:20px;">';
				str+='<table class="table table-bordered" >';
				str+='<thead>';
					str+='<th>VERY GOOD</th>';
					str+='<th>GOOD</th>';
					str+='<th>OK</th>';
					str+='<th>POOR</th>';
					str+='<th>VERY POOR</th>';
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						str+='<td ><div class="cCodeDiv" style="background-color:green;"/>'+result[0][0].veryGood+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:lightgreen;"/>'+result[0][0].good+'</td>';
						str+='<td><div class="cCodeDiv" style="background-color:yellow;"/>'+result[0][0].ok+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+result[0][0].poor+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+result[0][0].veryPoor+'</td>';
					str+='</tr>';
				str+='</tbody>';
				str+='</table>';
				if($(window).width > 768)
				{
						str+='<div class="table-responsive">';
				}
				str+='<table class="table table-bordered" id="constituencyWise2016DataTableId">';
				str+='<thead>';
					str+='<th>Constituency</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Renewal</th>';
					str+='<th>New</th>';
					str+='<th>Total</th>';
					str+='<th>% of Register cadres</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result[0]){
					str+='<tr>';
					str+='<td>'+result[0][i].name+'</td>';
					str+='<td>'+result[0][i].targetCount+'</td>';
					if(result[i].renewalPerc == null || result[i].renewalPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[0][i].renewalCount == null || result[0][i].renewalCount == 0){
						str+='<td>'+result[0][i].renewalCount+'</td>';
						}else{
							str+='<td>'+result[0][i].renewalCount+'<small>('+result[0][i].renewalPerc+' %)</small></td>';
						}
					}
					
					
					if(result[0][i].newPerc == null || result[0][i].newPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[0][i].newCount == null || result[0][i].newCount == 0){
							str+='<td>'+result[0][i].newCount+'</td>';
						}else{
							str+='<td>'+result[0][i].newCount+'<small>('+result[0][i].newPerc+' %)</small></td>';
						}
					}
					if(result[0][i].count2016 == null || result[0][i].count2016 == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[0][i].count2016+'</td>';
					}
					if(result[0][i].perc2016 == null || result[0][i].perc2016 == 0 || result[0][i].perc2016 == ""){
						str+='<td> - </td>';
					}else{
						var colorStatus = getColorCodeByStatus(result[0][i].levelPerformanceType)
						str+='<td style="color:'+colorStatus+';">'+result[0][i].perc2016+'</td>';
						
					}
					
					str+='</tr>';
				}
				str+='</tbody>';
				str+='</table>';
				if($(window).width > 768)
				{
					str+='</div>';
				}
			}
		
			$("#constituencyWise2016Details").html(str);
			$("#constituencyWise2016DataTableId").dataTable({
				"order": [ 4, 'desc' ]
			});
		}
	}
</script>    
</body>
</html>