<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MEDIA PARTY SCREEN</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body
{
	background-image:url("dist/mediaScreenParty/background.png");
}
.mCSB_inside > .mCSB_container
{
	margin-right:10px;
}
.text-capital
{
	text-transform:uppercase;
}
.count
{
	background-color:#ddd;
	border-radius:50%;
	height:25px;
	width:25px;
	display:block;
	text-align:center;
	font-size:16px;
	color:#fff;
}
h1,h2,h3,h4,h5,h6,.table
{
	margin:0px;
}
<!--.menuR
{
	display:none;
}-->
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
</style>
</head>
<body>
<div class="container">
 <!--<div class="row " id="PreviousmembersCount">
			
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10">
				<div class="table-responsive">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
						    <tr class="">
								<td style="width:190px">
									<h3 style="display:inline-block;">TS</h3>
									<img style="display:inline-block;width:110px;" class="pull-right" src="images/TS.png">
								</td>
								<td style="width:190px"><div id="tsConstiCountId"><h3>119</h3></div>
								</td>
								<td style="width:190px"><div id="ts2014NewCountId"><h3>794427</h3><span style="color:red;">2014-2016</span></div></td>
								<td style="width:190px">
									<div id="ts2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
								</td>
								<td style="width:190px">
									<p><span class="text-orange">Total - [%] </span></p>
									<div id="ts2016PrecCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
								</td>
								
							</tr>
							<tr class="">
								<td>
									<h3 style="display:inline-block;">AP</h3>
									<img style="display:inline-block;width:110px;" class="pull-right" src="images/AP.png">
								</td>
								<td><div id="apConstiCountId"><h3>175</h3></div></td>
								<td style="width:190px"><div id="ap2014NewCountId"><h3>4716975</h3><span style="color:red;">2014-2016</span></div></td>
								<td style="width:190px"><div id="ap2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
								<td style="width:190px"><p><span class="text-orange">Total - [%] </span></p><div id="ap2016PrecCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							</tr>
							<tr>
								<td>
									<h3>TOTAL  <small>AP & TS</small></h3>
								</td>
								<td><div><h3>294</h3></div></td>
								<td><div><h3>5511402</h3><span style="color:red;">2014-2016</span></div></td>
								<td style="width:190px"><div id="totalCountId"></div><div id="totalCountImgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
								<td><div id="totalPencentageId"></div><div id="totalPerceId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
						</tbody>
					</table>
				</div>
			</div>
		</div>-->
  <div class="row">		
	<div class="row">
		<div class="col-md-6 col-xs-12 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#fff">
					<div class="row">
						<div class="col-md-2 col-xs-4 col-sm-3">
							<img src="dist/mediaScreenParty/Notification.png" class="img-responsive"/>
						</div>
						<div class="col-md-10 col-xs-4 col-sm-9">
							<h5 class="text-capital"><span style="color:#3A98DE">Today TOp 20</span></h5>
							<h4 class="text-capital">Constituencies in ap</h4>
							<h5 style="margin-right: 25px;" class="pull-right">Memberships</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0px;" id="constituencyListPanel1">
					<div id="todayapImgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
	
		<div class="col-md-6 col-xs-12 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#fff">
					<div class="row">
						<div class="col-md-2 col-xs-4 col-sm-3">
							<img src="dist/mediaScreenParty/Notification.png" class="img-responsive"/>
						</div>
						<div class="col-md-10 col-xs-4 col-sm-9">
							<h5 class="text-capital"><span style="color:#3A98DE">Today TOp 20</span></h5>
							<h4 class="text-capital">Constituencies in ts</h4>
							<h5 style="margin-right: 25px;" class="pull-right">Memberships</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0px;" id="constituencyListPanel2">
					<div id="todaytsImgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
	</div>	
  </div>
   <div class="row">		
	<div class="row">
		<div class="col-md-6 col-xs-12 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#fff">
					<div class="row">
						<div class="col-md-2 col-xs-4 col-sm-3">
							<img src="dist/mediaScreenParty/Notification.png" class="img-responsive"/>
						</div>
						<div class="col-md-10 col-xs-4 col-sm-9">
							<h5 class="text-capital"><span style="color:#9D0D3E">OverAll TOp 20</span></h5>
							<h4 class="text-capital">Constituencies in ap</h4>
							<h5 style="margin-right: 25px;" class="pull-right">Memberships</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0px;" id="constituencyListPanel3">
				<div id="totalapimgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
	
		<div class="col-md-6 col-xs-12 col-sm-6">
			<div class="panel panel-default">
				<div class="panel-heading" style="background:#fff">
					<div class="row">
						<div class="col-md-2 col-xs-4 col-sm-3">
							<img src="dist/mediaScreenParty/Notification.png" class="img-responsive"/>
						</div>
						<div class="col-md-10 col-xs-4 col-sm-9">
							<h5 class="text-capital"><span style="color:#9D0D3E">OverAll TOp 20</span></h5>
							<h4 class="text-capital">Constituencies in ts</h4>
							<h5 style="margin-right: 25px;" class="pull-right">Memberships</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="padding:0px;" id="constituencyListPanel4">
				<div id="totaltsimgId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
				</div>
			</div>
		</div>
	</div>	
  </div>
</div>		
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<!--<script src="js/FieldMonitoring/fieldMonitoringDashboard.js" type="text/javascript"></script>-->
<script src="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>		
<script type="text/javascript">


//get2016LocationWiseRegisteredCounts("total");
function get2016LocationWiseRegisteredCounts(typeId){
$("#ts2016CountId").show();
$("#ts2016PrecCountId").show();
$("#ap2016CountId").show();
$("#ap2016PrecCountId").show();
$("#totalCountImgId").show();
$("#totalPerceId").show();
			if(typeId == 'total'){
				$("#ap2016CountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
				$("#ap2016PrecCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
				$("#ts2016CountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
				$("#ts2016PrecCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
			}
				var type = typeId;
				var locationScopeId = 2;
				var locationType ="";
				
			var jObj = {
				type:type,
				locationScopeId:locationScopeId,
				locationType:locationType
				
			}
			$.ajax({
			  type:'GET',
			  url: 'get2016LocationWiseRegisteredCountsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				if(result !=null && result.length >0){
					var totalRegCount=0;
					var totalPergeCount=0;
						var str='';
						var str1='';
						str+='<h3>'+result[0].count2016+'</h3>';
						str+='<span style="color:green;">2016-2018</span>';
						str1+='<h3>'+result[1].count2016+'</h3>';
						str1+='<span style="color:green;">2016-2018</span></p>';
						
						$("#ap2016CountId").html(str)
						$("#ap2016PrecCountId").html('<h3>'+result[0].percentage+'</h3><span class="text-warning">Total</span>');
					
						$("#ts2016CountId").html(str1)
						$("#ts2016PrecCountId").html('<h3>'+result[1].percentage+'</h3><span class="text-warning">Total</span>');
						totalRegCount=result[0].count2016+result[1].count2016;
						$("#totalCountId").html('<h3>'+totalRegCount+'</h3><span style="color:green;">2016-2018</span>');
						$('#totalPencentageId').html('<h3>'+((totalRegCount * 100) / 5511402).toFixed(2)+'</h3><span class="text-warning">Total</span>');
						//$("#ts2016CountId").hide();
//$("#ts2016PrecCountId").hide();
//$("#ap2016CountId").hide();
//$("#ap2016PrecCountId").hide();
$("#totalCountImgId").hide();
$("#totalPerceId").hide();
				}
		 });
	}
	
getConstituencyWiseDisrictList1("Today",1);
getConstituencyWiseDisrictList2("Today",36);
getConstituencyWiseDisrictList3("Total",1);
getConstituencyWiseDisrictList4("Total",36);
function getConstituencyWiseDisrictList1(type,stateId){
	$("#todayapImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"constituencyListPanel1",type);
		}
		$("#todayapImgId").hide();
	});
}

function getConstituencyWiseDisrictList2(type,stateId){
	$("#todaytsImgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"constituencyListPanel2",type);
		}
		$("#todaytsImgId").hide();
	});
}
function getConstituencyWiseDisrictList3(type,stateId){
	$("#totalapimgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"constituencyListPanel3",type);
		}
		$("#totalapimgId").hide();
	});
}
function getConstituencyWiseDisrictList4(type,stateId){
	$("#totaltsimgId").show();
	var jObj = {
		type:type,
		stateId:stateId
	}
	$.ajax({
	  type:'GET',
	  url: 'getConstituencyWiseTodayAndOverAllCountsAction.action',
	  data : {task:JSON.stringify(jObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			buildingConstituencyList(result,"constituencyListPanel4",type);
		}
		$("#totaltsimgId").hide();
	});
}

function buildingConstituencyList(result,divId,type){
	var str='';
	str+='<div class="'+divId+'" style="height:300px;overflow-y:scroll;">';
		str+='<table class="table table-condensed" style="margin-bottom:0px;">';
		for(var i in result){
			var temp = parseInt(i)+1;
			str+='<tr>';
				if(type == "Today"){
					str+='<td><span class="count" style="background-color:#3A98DE"><b>'+temp+'</b></span></td>';
				}else{
					str+='<td><span class="count" style="background-color:#9D0D3E"><b>'+temp+'</b></span></td>';
				}
				str+='<td class="text-capital">'+result[i].name+'</td>';
				str+='<td>'+result[i].attenteeCount+'</td>';
			str+='</tr>';
		}
		
		str+='</table>';
	str+='</div>';
	$("#"+divId).html(str);
	//$("."+divId).mCustomScrollbar({setHeight:'200px'})
	
	var div = $("."+divId);
	
	setInterval(function(){
		var pos = div.scrollTop();
		div.scrollTop(pos + 2);
	}, 100)
	
}	
</script>		
</body>
</html>