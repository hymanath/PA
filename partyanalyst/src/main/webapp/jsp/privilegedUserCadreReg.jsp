<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>2016-2018 Cadre Registration Details</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="tourDetails/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
</head>
	<style>
		body{background:#e5e5e5 }
		table.dataTable thead .sorting , table.dataTable thead .sorting_desc , table.dataTable thead .sorting_asc
		{
			background-image:none      
		}
		/* Loader */
		.spinner {
			margin: 30px auto;
			width: 40px;
			height: 40px;
			position: relative;
			text-align: center;
  
			-webkit-animation: sk-rotate 2.0s infinite linear;
			animation: sk-rotate 2.0s infinite linear;
		}

		.dot1, .dot2 {
			width: 60%;
			height: 60%;
			display: inline-block;
			position: absolute;
			top: 0;
			background-color: #1ABC9C;
			border-radius: 100%;
  
			-webkit-animation: sk-bounce 2.0s infinite ease-in-out;
			animation: sk-bounce 2.0s infinite ease-in-out;
		}

		.dot2 {
			top: auto;
			bottom: 0;
			-webkit-animation-delay: -1.0s;
			animation-delay: -1.0s;
		}

		@-webkit-keyframes sk-rotate { 100% { -webkit-transform: rotate(360deg) }}
		@keyframes sk-rotate { 100% { transform: rotate(360deg); -webkit-transform: rotate(360deg) }}

		@-webkit-keyframes sk-bounce {
			0%, 100% { -webkit-transform: scale(0.0) }
			50% { -webkit-transform: scale(1.0) }
		}

		@keyframes sk-bounce {
			0%, 100% { 
				transform: scale(0.0);
				-webkit-transform: scale(0.0);
			} 50% { 
				transform: scale(1.0);
				-webkit-transform: scale(1.0);
			}
		}

		p{margin:0px}
		.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
		.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
		.border-none{border:none;}
		.text-lowercase{text-transform:lowercase;}
		.text-uppercase{text-transform:uppercase;}
		.text-capitalize{text-transform:capitalize;}
		.text-center{text-align: center;}
		.text-red{color:#dc504a;}
		.text-green{color:#4dbd74;}
		.text-orange{color:#f9a834;}
		.text-skyblue{color:#46acca;}
		.mb-0{margin-bottom:0px}
		.mb-10{margin-bottom:10px}
		.Previousmembercount td:first-child{width:13%;}
		.Previousmembercount td:nth-child(2){width:13%;vertical-align:middle}
		.Previousmembercount td:nth-child(3){width:13%;vertical-align:middle}
		.Previousmembercount td:last-child{width:13%;vertical-align:middle;text-align:center}
		.Previousmembercount td{width:22%;}
		.Previousmembercount td:last-child{width:10%;}
		.membercount td{width:25%;}
		.membercount td h2, .Previousmembercount td h2{margin:0px;}
		.progress{height:10px;}
		.height-300{height: 300px; overflow: auto;}
		.height-500{height: 500px; overflow: auto;}
		.height-320{height: 300px; overflow: auto;width: 440px;}
		.f-16{font-size: 16px;}
		body {
			color: #333333;
			font-size: 14px;
			line-height: 20px;
			margin: 0;
		}
		p {
			color: #333;
			font-size: 14px;
		}
		.background {
			background: none repeat scroll 0 0 #e5e5e5;
		}
		.text-right {
			text-align: right;
		}
		.imgStyle{
			margin-left: 75px;
			margin-top: 30px;
		}
	
		.ajaxImgStyle {
			margin-bottom: 30px;
			margin-left: 94px;
			margin-top: 30px;
		}
		.dataTables_length, .dataTables_filter , .dataTables_info {
			color : #666666 !important;
		}
	
		table.dataTable tr.odd td.sorting_1 {
			background-color: #d3d3d3;
		}
		table.dataTable tr.even td.sorting_1 {
			background-color: #fafafa;
		}
		table.dataTable tr.odd {
			background-color: #f3f3f3;
		}
		#inActiveUsersId  thead th{
			background-color: #dff0d8  !important;
			color : #468847 !important;
			line-height: 20px !important;
		}
			
		#leaderDataDiv1,#leaderDataDiv2 {font-size:10px !important;font-family:verdana;font-weight:bold;}
	
	
		.summary td {
			//color: #666699;
			padding: 7px 17px;
			cursor:pointer;
			border-bottom:1px solid lightblue;
			border-left:1px solid lightblue;
			border-right:1px solid lightblue;
		}
		.summary th {
			border:1px solid lightblue;     
			color: #003399;
			font-size: 14px;
			font-weight: normal;
			padding: 12px;
		}
	
		.summary{
			margin-left:25px;margin-bottom:10px;
		}
	
		.info td {
			color: #666699;
			padding: 7px 17px;
			cursor:pointer;
			border-left:    1px solid #6699CC;
			border-right:  1px solid #6699CC; 
			border-bottom: 1px solid #6699CC;
		}
		.info th {
			border-left:    1px solid #6699CC;
			border-right:  1px solid #6699CC; 
			border-top: 1px solid #6699CC;
			color: #003399;
			font-size: 14px;
			font-weight: normal;
			padding: 12px;
		}	
	
		.typeRd{margin:5px;}
		.cCodeDiv{height:8px;width:8px;margin:6px;float:left;}
	
	
		.progress {
			height: 3px !important;
		}	
		.tableCadreDash
		{
			border-collapse:unset;
			border-spacing:0px 10px ;
			text-align:center;
		}
		.tableCadreDash thead th
		{
			border-bottom:0px !important;
			text-align:center;
		}

		.tableCadreDash tr td
		{
			border:1px solid #ADD8E6 !important;
	
		}


		.text-blue{color:#0044CC}
		.text_color{color: #999999;}
		.text-muted {
			color: #777777;
		}
		.f_26{font-size: 26.5px !important;}
		.well
		{
			background-color:#fff;
		}
		.text_capital{text-transform:uppercase !important}
	</style>
	<div class="container">    
		<!-- Title Row -->
		<div class="row m_top10" id="fadeInDown">
			<div class="col-md-12 col-xs-12 col-sm-12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">2016 Cadre Admin Dashboard</h3>
				<div class="row">
					<div class="col-md-4 col-xs-12 col-sm-6" style="margin-top: 15px;">
						<input type="radio" class="radiobuttonSelectedWise" id="todayId" name="compareC" value="Today" style="margin-top:0px;"/>
						<span style="margin-right:10px;"> TODAY</span>
						<input type="radio" class="radiobuttonSelectedWise" id="totalId" name="compareC" value="Total" checked="true" style="margin-top:0px;"/>
						<span style="margin-right:10px;"> OVER ALL </span> 
					</div>
					<div class="col-md-8 col-xs-12 col-sm-3">
						<button type="button" class="btn btn-primary pull-right text_capital userWiseReport">Cadre Demographics Report</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12 " >
				<div id="progressImgId"></div>
			</div>
		</div>
		<!-- Title Row End-->
		<!-- Members Count Row 
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
		Members Count Row End -->  
		<!----New code for constituency and district wise Start ----->
		<div class="row distCls" style="display:none;">
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> District  Target VS Registered Cadre</h4>
				<button class="btn btn-success btn-xs" id="distExcelExpBtnId" attr_tab_user_type="Tab" style="margin-left:1030px">Export To Excel</button>
				<div id="districtWise2016Details"   class="m_top10"></div>
				<div id="leaderDataDiv2" style="margin-top: -1px">
				</div>
			</div>
		</div>  
		<div class="row constCls" style="display:none;">
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> Constituency  Target Vs Registered Cadre</h4> 
				<button class="btn btn-success btn-xs" id="constExcelExpBtnId" attr_tab_user_type="Tab" style="margin-left:1030px">Export To Excel</button>
				<div id="constituencyWise2016Details"   class="m_top10"></div>
				<div id="leaderDataDiv1" class="scrollable_div" style="margin-top: -1px">
				</div>
			</div>
		</div>
		<!----New code for constituency and district wise Start ----->
	</div>
	<input type="hidden" id="hideConstId" value=""></input>
	<!-- Modal -->  
<div class="modal fade" id="cadreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
  <div class="modal-dialog modal-lg" role="document" style="width:85%">
    <div class="modal-content" style="border-radius:0px">
      <div class="modal-header" style="background-color:#CCC">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel1">KUPPAM CONSTITUENCY DETAILED REPORT</h4>
      </div>
      <div class="modal-body">
        <div class="row webModal">
				<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
					<label class="radio-inline">
						<input type="radio" class="scopeRadioCls" name="scopeType" id="inlineRadio1" value="Total" style="margin-top: 0px;" checked><h5 style="margin-top:-1px">Over All</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="scopeRadioCls" name="scopeType" id="inlineRadio2" value="Today" style="margin-top: 0px;"><h5 style="margin-top:-1px">Today</h5>      
					</label>  
				</div>
				<div class="col-md-12 col-xs-12 col-sm-12 m_top10">     
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="boothRadio1" value="booth" style="margin-top: 0px;" checked><h5 style="margin-top:-1px">Booth Wise</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio2" value="panchayat" style="margin-top: 0px;"><h5 style="margin-top:-1px">Panchayat Wise</h5>
					</label>
					<label class="radio-inline">
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio3" value="mandal" style="margin-top: 0px;"><h5 style="margin-top:-1px">Mandal Wise</h5>
					</label>
				</div>
		  <div class="col-md-12 col-xs-12 col-sm-12">
			<div id="kupamRegDtlsId"></div>
		  </div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- end--> 
<!-- Modal -->
<div class="modal fade" id="tabUserDtlsId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
  <div class="modal-dialog modal-lg" role="document" style="width:85%">
    <div class="modal-content" style="border-radius:0px">
      <div class="modal-header" style="background-color:#CCC">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="constNameId">KUPPAM CONSTITUENCY DETAILED REPORT</h4>
      </div>
      <div class="modal-body">
		<div class="row tabModal">
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20">  
				<div class="row m_top10">
					<div class="col-md-3 col-xs-12 col-sm-3">
						<span class="input-group pull-right">
							<input type="text" id="dateRangeIdForCadre"	 class="form-control" />
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>  
						</span>
					</div>
					<div class="col-md-1 col-xs-12 col-sm-3" style="margin-top: -8px;">
						<button class="btn btn-success pull-right m_top10" id="tabUserWiseDetailsId" type="submit" style="margin-right: 16px;">Submit</button>
					</div>
				</div>  
				<div class="row showTabUserWiseDetails">
				  <div class="col-md-12 col-xs-12 col-sm-12 m_top20 mtop-20">
				     <div id="notReceiveRegistrationFieldStaffDivId"></div>
					</div>
					<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<div id="tabUserWiseReportDiv"></div>
					</div>
				</div>
			 </div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button glyphicon glyphicon-comment" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- end-->   
<div class="modal fade" tabindex="-1" id="perModalDivId" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" style="width:45%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">PERCENTAGES INFORMATION</h4>
			</div>
			<div class="modal-body" id="perInfmaDetailsDivId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div><!--  /.modal-content -->  
	</div><!--  /.modal-dialog -->
</div>
<body>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>  
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
	var userId = ${userId};
	var stateName = '${stateName}';
	var globalUserId = userId;      
	var type = "Total";       
	var locationType = stateName;
	$(document).ready(function() {
		initialiseDatePickerForCadreRegistration();
	});

	function initialiseDatePickerForCadreRegistration(){
		$("#dateRangeIdForCadre").daterangepicker({
			opens: 'right',
			parentEl:'#tabUserDtlsId',
			startDate: moment(),    
			endDate: moment(),
			locale: {
			  format: 'DD/MM/YYYY'     
			},  
		})
	}  
	get2016LocationWiseRegisteredCounts(userId,locationType,type);            
	function get2016LocationWiseRegisteredCounts(userId,locationType,type){
		$("#districtWise2016Details").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#constituencyWise2016Details").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$("#progressImgId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		setcolorsForStatus();
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
			$("#districtWise2016Details").html('');
			$("#constituencyWise2016Details").html('');
			$("#progressImgId").html('');  
			if(result != null && result.length > 0){
				buildGet2016LocationWiseRegisteredCounts(result,type);  
			}
		});
	}
	function buildGet2016LocationWiseRegisteredCounts(result,type){
		if(result.length == 2){
			$(".distCls").show();
			$(".constCls").show();
			//table one starts
			var str1='';
			if(result[0] !=null && result[0].length > 0 ){
				//debugger;
				str1+='<div class="table-responsive" style="margin-top:20px;">';
				str1+='<div class="span12 pull-right">';
				str1+='<i class="glyphicon glyphicon-info-sign" id="percentageId" title="Click to how to calculating percentages" style="margin-left: 20px;margin-bottom:9px;;cursor:pointer;cursor:pointer;"></i>';
				str1+='</div>';
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
					str1+='<th>District Id</th>';
					str1+='<th>District</th>';
					str1+='<th>Target Cadres</th>';
					str1+='<th>Renewal</th>';
					str1+='<th>New</th>';
					str1+='<th>Man Power</th>';
					str1+='<th>Total Count</th>';
					if(type.trim() == "Total"){
						str1+='<th>Today Count</th>';
					}
					str1+='<th>% of Register cadres</th>';
				str1+='</thead>';
				str1+='<tbody>';
				for(var i in result[0]){
					str1+='<tr>';
					str1+='<td>'+result[0][i].id+'</td>';
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
					if(result[0][i].mapPowerCount == null || result[0][i].mapPowerCount == 0){
						str1+='<td> - </td>';  
					}else{
						str1+='<td>'+result[0][i].mapPowerCount+'</td>';
					}
					
					
					if(result[0][i].count2016 == null || result[0][i].count2016 == 0){
						str1+='<td> - </td>';
					}else{
						str1+='<td>'+result[0][i].count2016+'</td>';
					}  
					
					if(type.trim() == "Total"){
						if(result[0][i].count2016Today == null || result[0][i].count2016Today == 0){
							str1+='<td> - </td>';
						}else{
							str1+='<td>'+result[0][i].count2016Today+'</td>';
						}
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
				"aaSorting": [[ 4, "desc" ]], 
				"iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]	
			});
			//$("#districtWise2016DataTableId").removeClass("dataTable")
			//table one ends
			//table two starts
			var str='';
			if(result[1] !=null && result[1].length >0){
				str+='<div class="table-responsive" style="margin-top:20px;">';
				str+='<div class="span12 pull-right">';
				str+='<i class="glyphicon glyphicon-info-sign" id="percentageId" title="Click to how to calculating percentages" style="margin-left: 20px;margin-bottom:9px;;cursor:pointer;cursor:pointer;"></i>';
				str+='</div>';
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
						str+='<td ><div class="cCodeDiv" style="background-color:green;"/>'+result[1][0].veryGood+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:lightgreen;"/>'+result[1][0].good+'</td>';
						str+='<td><div class="cCodeDiv" style="background-color:yellow;"/>'+result[1][0].ok+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+result[1][0].poor+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+result[1][0].veryPoor+'</td>';
					str+='</tr>';
				str+='</tbody>';
				str+='</table>';
				if($(window).width > 768)
				{
						str+='<div class="table-responsive">';
				}
				str+='<table class="table table-bordered" id="constituencyWise2016DataTableId">';
				str+='<thead>';
					str+='<th>Constituency No</th>';
					str+='<th>Constituency</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Renewal</th>';
					str+='<th>New</th>';
					str+='<th>Man Power</th>';   
					str+='<th>Total Count</th>';
					if(type.trim() == "Total"){  
						str+='<th>Today Count</th>';    
					}  
					str+='<th>% of Register cadres</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result[1]){       
					str+='<tr>';
					str+='<td>'+result[1][i].no+'<i style="cursor:pointer;margin-left:7px;" class="glyphicon glyphicon-info-sign tabUserDtlsCls" attr_const_id="'+result[1][i].id+'" attr_const_name="'+result[1][i].name+'" data-toggle="tooltip" data-placement="right" title="" data-original-title="Tab User Detailed Report"></i></td>';
					str+='<td attr_const_id="'+result[1][i].id+'" attr_const_name="'+result[1][i].name+'" class="getDtlsCls" style="cursor:pointer;"><a>'+result[1][i].name+'</a></td>';  
					str+='<td>'+result[1][i].targetCount+'</td>';
					if(result[1][i].renewalPerc == null || result[1][i].renewalPerc == 0){       
						str+='<td> - </td>';  
					}else{
						if(result[1][i].renewalCount == null || result[1][i].renewalCount == 0){
						str+='<td>'+result[1][i].renewalCount+'</td>';
						}else{
							str+='<td>'+result[1][i].renewalCount+'<small>('+result[1][i].renewalPerc+' %)</small></td>';
						}
					}
					
					
					if(result[1][i].newPerc == null || result[1][i].newPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[1][i].newCount == null || result[1][i].newCount == 0){
							str+='<td>'+result[1][i].newCount+'</td>';
						}else{
							str+='<td>'+result[1][i].newCount+'<small>('+result[1][i].newPerc+' %)</small></td>';
						}
					}
					if(result[1][i].mapPowerCount == null || result[1][i].mapPowerCount == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[1][i].mapPowerCount+'</td>';
					}
					
					if(result[1][i].count2016 == null || result[1][i].count2016 == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[1][i].count2016+'</td>';
					}
					if(type.trim() == "Total"){  
						if(result[1][i].count2016Today == null || result[1][i].count2016Today == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[1][i].count2016Today+'</td>';
						}
					}
					if(result[1][i].perc2016 == null || result[1][i].perc2016 == 0 || result[1][i].perc2016 == ""){
						str+='<td> - </td>';
					}else{
						var colorStatus = getColorCodeByStatus(result[1][i].levelPerformanceType)
						str+='<td style="color:'+colorStatus+';">'+result[1][i].perc2016+'</td>';
						
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
				"aaSorting": [[ 4, "desc" ]], 
				"iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]
			});
			//$("#constituencyWise2016DataTableId").removeClass("dataTable");
			//table two ends
			$('[data-toggle="tooltip"]').tooltip();
			
		}else{
			$(".constCls").show();    
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
					str+='<th>Constituency No</th>';    
					str+='<th>Constituency</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Renewal</th>';
					str+='<th>New</th>';
					str+='<th>Man Power</th>';        
					str+='<th>Total Count</th>';
					if(type == "Total"){
						str+='<th>Today Count</th>';    
					}
					str+='<th>% of Register cadres</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result[0]){  
					str+='<tr>';
					str+='<td>'+result[0][i].no+'<i style="cursor:pointer;margin-left:7px;" class="glyphicon glyphicon-info-sign tabUserDtlsCls"  attr_const_id="'+result[0][i].id+'" attr_const_name="'+result[0][i].name+'" data-toggle="tooltip" data-placement="right" title="" data-original-title="Tab User Detailed Report"></i></td>';
					str+='<td attr_const_id="'+result[0][i].id+'" attr_const_name="'+result[0][i].name+'" class="getDtlsCls" style="cursor:pointer;"><a>'+result[0][i].name+'</a></td>';     
					str+='<td>'+result[0][i].targetCount+'</td>';
					if(result[0][i].renewalPerc == null || result[0][i].renewalPerc == 0){  
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
					if(result[0][i].mapPowerCount == null || result[0][i].mapPowerCount == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[0][i].mapPowerCount+'</td>';
					}
					if(result[0][i].count2016 == null || result[0][i].count2016 == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[0][i].count2016+'</td>';
					}
					if(type == "Total"){       
						if(result[0][i].count2016Today == null || result[0][i].count2016Today == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[0][i].count2016Today+'</td>';
						}
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
				"aaSorting": [[ 4, "desc" ]], 
				"iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]
			});
		}
		$('[data-toggle="tooltip"]').tooltip();    
	}
	var statusColorArr = [];  
	var statusarr = ['VeryGood','Good','Ok','Poor','VeryPoor'];
	function getColorCodeByStatus(status){
		if(typeof status != "undefined"  && status != null && status!='') {
			if(statusColorArr != null && statusColorArr.length > 0){
				for(var i in statusColorArr){
					if(statusColorArr[i].status.toLowerCase() == status.toLowerCase())
					return statusColorArr[i].color;
				}
			}
		}
	    else
			return '#000';
	}
	function setcolorsForStatus(){
		statusColorArr = new Array();
		var colorStatic = new Array('#008000','#90EE90','#FFFF00','#FFA500','#C43C35');
		var colorCount = 0;
		for(var i in statusarr){
			var obj = {
				status : statusarr[i],
				color : colorStatic[colorCount]
			}
			statusColorArr.push(obj)
			  
			if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				colorCount++;
			}
		return statusColorArr;  
	}
	$(document).on("click",".radiobuttonSelectedWise",function(){
		$("#progressImgId").hide();      
		var selectedRadioButton = $(this).val();
		var userId = globalUserId;
		var state = locationType;
		get2016LocationWiseRegisteredCounts(userId,state,selectedRadioButton);                        
	});
	$(document).on("click",".getDtlsCls",function(){         
		var constName = $(this).attr("attr_const_name");
		$("#myModalLabel1").html(constName+" CONSTITUENCY DETAILED REPORT") 
		$( "#boothRadio1" ).prop( "checked", true );         
		$("#cadreModal").modal("show");
		$("#kupamRegDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var constituencyId = $(this).attr("attr_const_id");
		$("#hideConstId").attr("value",constituencyId);
		var selectionType = "booth";    
		var scopeType = $("input:radio[name=compareC]:checked").val();
		if(scopeType == "Today"){
			$( "#inlineRadio2" ).prop( "checked", true );
		}else{
			$( "#inlineRadio1" ).prop( "checked", true );          
			         
		}
		getRegistrationCountDtls(constituencyId,selectionType,scopeType);
	});
	//
	$(document).on('click','.locationRadioCls',function(){
		$("#kupamRegDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var selectionType=$("input:radio[name=selectionType]:checked").val();
		var scopeType=$("input:radio[name=scopeType]:checked").val();
		var constituencyId = $("#hideConstId").val();
		getRegistrationCountDtls(constituencyId,selectionType,scopeType);  
	});
	
	$(document).on('click','.scopeRadioCls',function(){
		var selectionType=$("input:radio[name=selectionType]:checked").val();
		$("#kupamRegDtlsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var scopeType=$("input:radio[name=scopeType]:checked").val();
		var constituencyId = $("#hideConstId").val();
		getRegistrationCountDtls(constituencyId,selectionType,scopeType);    
	});
	
	function getRegistrationCountDtls(constituencyId,selectionType,scopeType){
		var jObj = {
			constituencyId : constituencyId,
			scope : scopeType,        
			location : selectionType    
		}
		$.ajax({
			type:'GET',
			url: 'get2016LocationWiseRegisterationDtls.action',    
			data : {task:JSON.stringify(jObj)}
		}).done(function(result){ 
			$("#kupamRegDtlsId").html("");
			if(result != null && result.length > 0){
				buildRegistrationCountDtls(result,selectionType,scopeType);
			}      
		});      
	}
	function buildRegistrationCountDtls(result,location,scope){    
		var str = '';  
		str+='<div class="table-responsive m_top20">';
          str+='<table class="table table-bordered" id="regCadreCountTableId">';
            str+='<thead class="text-capital text-center">';
              str+='<tr>';
                str+='<th rowspan="2">MANDAL/MUNICIPALITY</th>';
				if(location == "panchayat"){
					str+='<th rowspan="2">PANCHAYAT/WARD</th>';    
				}
                if(location == "booth"){
					str+='<th rowspan="2">PANCHAYAT/WARD</th>';     
					//str+='<th rowspan="2">MUNICIPALITY</th>';
					str+='<th rowspan="2">BOOTH NO</th>';
				}
                str+='<th rowspan="2">TOTAL VOTERS</th>';
                str+='<th rowspan="2">2014 TOTAL CADRE</th>';
				if(scope == "today"){
					str+='<th colspan="6" class="text-capital text-center">2016 CADRE</th>';
				}else{
					str+='<th colspan="5" class="text-capital text-center">2016 CADRE</th>';
				}
                    
              str+='</tr>';     
              str+='<tr>';
				str+='<th>RENEWAL CADRE 2016</th>';
                str+='<th>RENEWAL CADRE PERCENT(%)</th>';
                str+='<th>TOTAL CADRE 2016</th>';
				if(scope == "today"){
					str+='<th>TOTAL CADRE ON TODAY</th>'; 
				}
                
                str+='<th>NEW CADRE</th>';
                str+='<th>NEW CADRE PERCENT(%)</th>';
              str+='</tr>';
            str+='</thead>';
			for(var i in result){  
				str+='<tr>';
				if(location == "mandal"){
					str+='<td>'+result[i].mandalName+'&nbsp;&nbsp;</td> ';
				}else{
					if(result[i].panchayatName != null){
						str+='<td>'+result[i].mandalName+'&nbsp;&nbsp;</td> ';
					}else{
						str+='<td>'+result[i].localElectionBody+'&nbsp;&nbsp;</td> ';
					}
				}
				if(location == "panchayat"){
					str+='<td>'+result[i].panchayatName+'</td>';
				}
				
				if(location == "booth"){
					if(result[i].panchayatName == null || result[i].panchayatName.trim().length < 1){
						str+='<td>-</td> ';
					}else{
						str+='<td>'+result[i].panchayatName+'</td> ';
					}
					
					str+='<td>'+result[i].boothName+'</td>';   
				}
				
				if(result[i].totalVoter == 0){
					str+='<td>-</td>';    
				}else{
					str+='<td>'+result[i].totalVoter+'</td>';
				}
			     
			   str+='<td>'+result[i].cadreCount2014+'</td>';
			   str+='<td>'+result[i].renewalCount+'</td>';
			   if(result[i].cadreCount2014 > 0){
				   var precent = (result[i].renewalCount*(100/result[i].cadreCount2014)).toFixed(0);
				   str+='<td>'+precent+'</td>';
			   }else{
				   str+='<td>0</td>';    
			   }
			   str+='<td>'+result[i].cadreCount2016OverAll+'</td>';
			   if(scope == "today"){
					str+='<td>'+result[i].cadreCount2016Today+'</td>';  
			   }			  
            
              
              str+='<td>'+result[i].newCount+'</td>'; 
			  if(result[i].cadreCount2016OverAll > 0){    
				  var precent = (result[i].newCount*(100/result[i].cadreCount2016OverAll)).toFixed(0);   
				  str+='<td>'+precent+'</td>';     
			  }else{
				  str+='<td>0</td>';    
			  }
              
			  str+='</tr>';
			}
          str+='</table>';
        str+='</div>';
		$("#kupamRegDtlsId").html(str);  
		$("#regCadreCountTableId").dataTable();
	}
	$(document).on('click','#tabUserWiseDetailsId',function(){
		var constId = $(this).attr("attr_const_id");
		var dates = $("#dateRangeIdForCadre").val();
		var fromDate;
		var toDate;
		if(dates != null ){
			var datesArr = dates.split("-");
			fromDate=datesArr[0];
			toDate=datesArr[1];    
		}      
		getCadreRegistrationCountByConstituency(constId,fromDate,toDate);
	});
	$(document).on('click','.tabUserDtlsCls',function(){
		initialiseDatePickerForCadreRegistration();  
		var constId = $(this).attr("attr_const_id");
		var constName = $(this).attr("attr_const_name");
		$("#constNameId").html(constName +" CONSTITUENCY DETAILED REPORT");    
		$("#tabUserWiseDetailsId").attr("attr_const_id",constId);  
		$("#tabUserDtlsId").modal("show");     
		var dates = $("#dateRangeIdForCadre").val();
		var fromDate;
		var toDate;
		if(dates != null ){
			var datesArr = dates.split("-");
			fromDate=datesArr[0];
			toDate=datesArr[1];    
		}
		getCadreRegistrationCountByConstituency(constId,fromDate,toDate);
	});
	function getCadreRegistrationCountByConstituency(constituencyId,fromDate,toDate){
		$("#tabUserWiseReportDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj={  
			constituencyId : constituencyId,          
			fromDate : fromDate,
			toDate : toDate
		};  
		$.ajax({          
			type : 'GET',    
			url : 'getCadreRegistrationCountByConstituency.action',    
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#tabUserWiseReportDiv").html('');
			if(result != null && result.length > 0){
				buildCadreRegistrationOverViewResult(result);    
			}else{
				$("#tabUserWiseReportDiv").html("NO DATA AVAILABLE.");
			}
		});
	}
	function buildCadreRegistrationOverViewResult(tabUserInfoList){
		var str='';
			str+='<h4>FIELD USER REGISTRATION DETAILS</h4>';
			str+='<table class="table table-bordered table-condensed m_top10" id="tabUserWiseReportDataTableId"> ';
				str+='<thead> ';
					str+='<tr>';
						str+='<th>Field Staff Name </th>';
						str+='<th>Image</th>';
						str+='<th>MobileNo</th>';
						str+='<th>No.Of Samples</th>';
						str+='<th>First Record Time</th>';
						str+='<th>Last Record Time</th>';
					str+='</tr>'; 
				str+='</thead>'; 
				str+='<tbody>';   
				for(var j in tabUserInfoList){
					
						str+='<tr> ';
						
						  
							if(tabUserInfoList[j].name != null){
								str+='<td>'+tabUserInfoList[j].name+'</td>';
							}else{
								str+='<td> - </td>';	
							}  
						 
							str+='<td><img src="http://mytdp.in/tab_user_images/'+tabUserInfoList[j].imagePathStr+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
							if(tabUserInfoList[j].mobileNumber != null && tabUserInfoList[j].mobileNumber.length > 0){
								str+='<td>'+tabUserInfoList[j].mobileNumber+'</td> ';	 	 
							}else{
								str+='<td> - </td> ';	 
							}
							
							 if(tabUserInfoList[j].apTotal != null && tabUserInfoList[j].apTotal> 0){
								//str+='<td><a style="cursor:pointer;" class="noOfSamplesDetailsPopUpView" attr_tab_user_info_id='+tabUserInfoList[j].id+'>'+tabUserInfoList[j].apTotal+'</a></td> ';
								str+='<td>'+tabUserInfoList[j].apTotal+'</td> ';      	 	 
							 }else{
								str+='<td> - </td> ';	 
							 }
							  
							 if(tabUserInfoList[j].startTime != null && tabUserInfoList[j].startTime.length> 0){
								str+='<td>'+tabUserInfoList[j].startTime.substring(0,19)+'</td> ';	 	 
							 }else{
								str+='<td> - </td> ';	 
							 }
							 
							 if(tabUserInfoList[j].endTime != null && tabUserInfoList[j].endTime.length> 0){
								str+='<td>'+tabUserInfoList[j].endTime.substring(0,19)+'</td> ';	 	 
							 }else{
								str+='<td> - </td> ';	 
							 }
								str+='</tr>';
					}
			str+='</tbody>'; 
		str+='</table>';
		
		$("#tabUserWiseReportDiv").html(str);  
		$("#tabUserWiseReportDataTableId").dataTable();  
	}
	function setDefaultImage(img){
		img.onerror = "";
		img.src = "images/cadre_images/human.jpg";
		return true;
	}  
$(document).on("click","#percentageId",function(){
	$("#perModalDivId").modal('show');
	var str="";
	str+='<table class="table table-bordered">';
	str+='<thead>';
		str+='<th>PERCENTAGE</th>';
		str+='<th>PERFORMANCE TYPE</th>';
	str+='</thead>';
	str+='<tbody>';
				str+='<tr>';
						str+='<td> >100% </td>';
						str+='<td> VERY GOOD </td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td> 91-100% </td>';
						str+='<td> GOOD </td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td> 81-90%  </td>';
						str+='<td> OK</td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td>  61-80% </td>';
						str+='<td> POOR</td>';
				str+='</tr>';
				str+='<tr>';
						str+='<td>  <=60% </td>';
						str+='<td> VERY POOR </td>';
				str+='</tr>';
				str+='</tbody>';
				str+='</table>';
			$("#perInfmaDetailsDivId").html(str);
});
	 $(".userWiseReport").click(function(){
		window.open('userWiseCadreDemographicReportAction.action');
	 });
</script>
<script>  
	$(document).on("click","#constExcelExpBtnId",function(){
		generateExcelReportForCadreConst();	
	});
	$(document).on("click","#distExcelExpBtnId",function(){
		generateExcelReportForCadreDist();	
	});
	function generateExcelReportForCadreConst(){
		tableToExcel(constituencyWise2016DataTableId, 'Location Wise Registrations Report');
	}
	function generateExcelReportForCadreDist(){
		tableToExcel(districtWise2016DataTableId, 'Location Wise Registrations Report');        
	}
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