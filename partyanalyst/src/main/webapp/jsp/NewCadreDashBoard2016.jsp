<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CADRE DASHBOARD</title>

<link rel="stylesheet" type="text/css" href="newCoreDashBoard/css/bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="css/style.css"> 
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/DatatableBootstrap/DatatableB.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="css/cadreRegistrationCSS/animate.css" >
<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->
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
</style>
<body>
<div class="container">
		<div class="row" >
			<button type="button" class="btn btn-primary pull-right show2014DashBoard">click here to view 2014 Cadre DashBoard </button>
		</div>
		<!-- Title Row -->
		<div class="row m_top10" id="fadeInDown">
			<div class="col-md-12 col-xs-12 col-sm-12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">2016 Cadre Admin Dashboard</h3>
			</div>
		</div><!-- Title Row End-->
			<!-- Members Registered Previous Row -->
			
		<div class="row " id="PreviousmembersCount">
			
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10">
				<div class="table-responsive">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
						    <tr class="">
								<td>
									<img style="margin:auto;display:block" src="images/TS.png">
									<h3 class="text-center" style="margin:auto;">TS</h3>
								</td>
								<td><div id="tsConstiCountId"><h2>119</h2>
									<p style="font-size:12px;">Total Constituencies</p></div>
								</td>
								<td><p><span class="text-red">2014 </span></p><div id="ts2014NewCountId"><h2 class="text_color f_26">794427</h2></div></td>
								<td>
									<p><span class="text-blue">2016 </span></p>
									<div id="ts2016CountId" class="text-muted"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
								</td>
								<td>
									<p><span class="text-orange">Total - [%] </span></p>
									<div id="ts2016PrecCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
								</td>
								
							</tr>
							<tr class="">
								<td>
									<img style="margin:auto;display:block" src="images/AP.png">
									<h3 class="text-center" style="margin:auto;">AP</h3>
								</td>
								<td><div id="apConstiCountId"><h2>175</h2><p style="font-size:12px;">Total Constituencies</p></div></td>
								<td><p><span class="text-red">2014 </span></p><div id="ap2014NewCountId"><h2 class="text_color f_26">4716975</h2></div></td>
								<td><p><span class="text-blue">2016 </span></p><div id="ap2016CountId" class="text-muted"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
								<td><p><span class="text-orange">Total - [%] </span></p><div id="ap2016PrecCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		  
		</div><!-- Members Registered Previous Row -->
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
			<!--<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 membercount" style="background:#ffffff;">
						<tbody>
							<tr>
								<td style="background-color:#c8d7f4"><p style="font-size: 18px; font-weight: bold;">TODAY</p></td>
								<!--<td><div id="thisWeekRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="monthRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<td style="background-color:#eae798"><p style="font-size: 18px; font-weight: bold;">OVER ALL</p></td>					
							</tr>
							<tr>
								<td class="indiEle">
									
								</td>
								
								<td class="indiEle">
									
								</td>
							</tr>
						</tbody>
					</table>
			</div>-->
		</div><!-- Members Count Row End -->
		<!----New code for constituency and district wise Start ----->
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> District  Target VS Registered Cadre  
				
				</h4>
			
				<div style="padding:5px;">
					<input type="radio" class="radiobuttonSelectedWise" id="" name="compareC" value="today" checked="true" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> TODAY</span>
					<!--<input type="radio" class="" id="" name="compareC" value="asoftoday" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> AS OF TODAY </span>-->
					<input type="radio" class="radiobuttonSelectedWise" id="" name="compareC" value="total" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> OVER ALL </span>
					
					<div class="btn-group pull-right">
					<button type="button" class="btn btn-mini btn-success  aptsclass " value="AP" name="constTargetBtn" id="apDistTargetComp" checked="checked">AP</button>
					<button type="button" class="btn btn-mini aptsclass " value="TS" name="constTargetBtn" id="tgDistTargetComp" >TS</button>
				</div>
				</div>
				<div id="districtWise2016Details"   class="m_top10"></div>
				<div id="leaderDataDiv2" class="" style="margin-top: -1px">
					
				</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> Constituency  Target Vs Registered Cadre  
				
				
				</h4>
				
				<div style="padding:5px;">
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="todayconstituencyValue" name="compareD" value="today" checked="true" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> TODAY</span>
					<!--<input type="radio" class="typeRd" id="" name="compareD" value="asoftoday" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> AS OF TODAY</span>-->
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="totalconstituencyValue" name="compareD" value="total" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> OVER ALL</span>
					
					<div class="btn-group pull-right">
					<button type="button" value="AP" class="btn btn-mini btn-success   aptsconsSele" name="distTargetBtn" id="apConsTargetComp" checked="checked">AP</inpbuttonut>
					<button type="button" value="TS" class="btn btn-mini   aptsconsSele" name="distTargetBtn" id="tsConsTargetComp">TS</button>
				</div>
				</div>
				<div id="constituencyWise2016Details"   class="m_top10"></div>
				<div id="leaderDataDiv1" class="scrollable_div" style="margin-top: -1px">
					
				</div>
			</div>
		</div>
		<!----New code for constituency and district wise End ----->
		
</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script type="text/javascript" src="newCoreDashBoard/js/bootstrap.min.js"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
$('#fadeInDown').addClass('animated fadeInDown');
$('#fadeInLeft').addClass('animated fadeInLeft');
$('#fadeInRight').addClass('animated fadeInRight');
$('.fadeInUp').addClass('animated fadeInUp');
$('#fadeInUp1').addClass('animated fadeInUp');
$('#PreviousmembersCount').addClass('animated fadeInUp');
$('#membersCount').addClass('animated fadeInX');

//color codes code start
var statusColorArr = [];
var statusarr = ['VeryGood','Good','Ok','Poor','VeryPoor'];
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

	function getColorCodeByStatus(status){
	    if(typeof status != "undefined"  && status != null && status!='' && status.lenght > 0  ) {
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

//color codes starts end

 $(".show2014DashBoard").click(function(){
	window.location.replace('cadreDashBoardAction.action');
});
//dist

$(document).on("click",".aptsclass",function(){
	
	  $(".aptsclass").removeClass("btn-success");
	  $(this).addClass("btn-success");
	  
	 get2016LocationWiseRegisteredCountsDistrictWise( $('input[name=compareD]:checked').val(),3,$(this).val() );
	
});
$(document).on("click",".radiobuttonSelectedWise",function(){
	 
	 var selectedRadioButton = $(this).val();
	 var state = ''; 
	 $('.aptsclass').each(function(i, obj){
          if($(this).hasClass("btn-success")){
			  state = $(this).val();
		  }
     });
	 get2016LocationWiseRegisteredCountsDistrictWise(selectedRadioButton,3,state);
	 
	
});
//cons
$(document).on("click",".aptsconsSele",function(){
	 
	  $(".aptsconsSele").removeClass("btn-success");
	  $(this).addClass("btn-success");
	  
	 get2016LocationWiseRegisteredCountsConstituencyWise( $('input[name=compareD]:checked').val(),4,$(this).val() );
	
});

$(document).on("click",".radiobuttonSelectedConsWise",function(){
	
	 var selectedRadioButton = $(this).val();
	 var state = ''; 
	 $('.aptsconsSele').each(function(i, obj){
          if($(this).hasClass("btn-success")){
			  state = $(this).val();
		  }
     });
	 get2016LocationWiseRegisteredCountsConstituencyWise(selectedRadioButton,4,state);
});

 get2016LocationWiseRegisteredCounts("total");
 get2016LocationWiseRegisteredCounts("today");
 getDataSourceTypeWiseRegisteredDetails();
 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"AP");
 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"AP");

function get2016LocationWiseRegisteredCounts(typeId){

			if(typeId == 'total'){
				$("#ap2016CountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
				$("#ap2016PrecCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
				$("#ts2016CountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
				$("#ts2016PrecCountId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	
				$("#totalApTgRegisCount").html('');
			}else if(typeId == 'today'){
				$("#todayApTgRegisCount").html('');
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
					if(typeId == 'total'){
						var str='';
						var str1='';
						str+='<h2 class="text-center"><small style="color:#d3d3d3">Total</small>&nbsp;&nbsp;'+result[0].count2016+'</h2>';
						str+='<div class="col-md-12 col-xs-12 col-sm-12" style="border-top:1px solid #ddd;padding-top:10px">';
						str+='<div class="col-md-6 col-xs-12 col-sm-6" style="border-right: 1px solid #d3d3d3;">';
						str+='<p class="text-center text-muted">Renewal</p>';
						str+='<p style="font-size: 18px;" class="text-muted text-center"><b>'+result[0].renewalCount+'</b></p>';
						str+='</div>';
						
						str+='<div class="col-md-6 col-xs-12 col-sm-6">';
						str+='<p style="text-align: center;" class="text-muted">New</p>';
						str+='<p style="text-align: center;font-size: 18px;" class="text-muted"><b>'+result[0].newCount+'</b></p>';
						str+='</div>';
						
						str+='</div>';
						
						str1+='<h2 class="text-center"><small style="color:#d3d3d3">Total</small>&nbsp;&nbsp;'+result[1].count2016+'</h2>';
						str1+='<div class="col-md-12 col-xs-12 col-sm-12" style="border-top:1px solid #ddd;padding-top:10px">';
						str1+='<div class="col-md-6 col-xs-12 col-sm-6" style="border-right: 1px solid #d3d3d3;">';
						str1+='<p class="text-center text-muted">Renewal</p>';
						str1+='<p style="font-size: 18px;" class="text-muted text-center"><b>'+result[1].renewalCount+'</b></p>';
						str1+='</div>';
						
						str1+='<div class="col-md-6 col-xs-12 col-sm-6">';
						str1+='<p style="text-align: center;" class="text-muted">New</p>';
						str1+='<p style="text-align: center;font-size: 18px;" class="text-muted"><b>'+result[1].newCount+'</b></p>';
						str1+='</div>';
						
						str1+='</div>';
						
						
						$("#ap2016CountId").html(str)
						$("#ap2016PrecCountId").html('<h2>'+result[0].percentage+'</h2>');
					
						$("#ts2016CountId").html(str1)
						$("#ts2016PrecCountId").html('<h2>'+result[1].percentage+'</h2>');
						var overalltotal = result[0].count2016+result[1].count2016;
						$("#totalApTgRegisCount").html('<div class="col-md-4 col-xs-12 col-sm-6 mytooltip text-center" ><strong><h4 class="panel-title text-center"> TOTAL  </h4></strong><h3 class="text-center" style="margin:0px">'+overalltotal+'</h3><span class="text-skyblue"></span></div><div class="col-md-4 col-xs-12 col-sm-6 mytooltip text-center" ><strong><h4 class="panel-title text-center"> AP  </h4></strong><h3 class="text-center" style="margin:0px">'+result[0].count2016+'</h3><span class="text-skyblue"></span></div><div  class="col-md-4 col-xs-12 col-sm-6 mytooltip text-center" ><strong><h4 class="panel-title text-center">TELANGANA </h4></strong> <h3 class="text-center" style="margin:0px">'+result[1].count2016+'</h3><span class="text-skyblue"> </span></div>');
						
					}else if(typeId == 'today'){
						var todaytotal = result[0].count2016+result[1].count2016;
						$("#todayApTgRegisCount").html('<div class="col-md-4 col-xs-12 col-sm-6 mytooltip text-center" ><strong><h4 class="panel-title text-center">  TOTAL </h4></strong><h3 class="text-center" style="margin:0px"> '+todaytotal+'</h3></div><div class="col-md-4 col-xs-12 col-sm-6 mytooltip text-center" ><strong><h4 class="panel-title text-center">  AP </h4></strong><h3 class="text-center" style="margin:0px"> '+result[0].count2016+'</h3></div><div  class="col-md-4 col-xs-12 col-sm-6 text-center mytooltip" ><strong><h4 class="panel-title text-center">TELANGANA </h4></strong><h3 class="text-center" style="margin:0px"> '+result[1].count2016+'</h3></div>');
						
					
					}
					
				}
		 });
	}
	
	function getDataSourceTypeWiseRegisteredDetails(){
				var jObj = {}
		
			$.ajax({
			  type:'GET',
			  url: 'getDataSourceTypeWiseRegisteredDetailsAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				if(result !=null && result.length >0){
					
					var str='';
					var str1='';
					//today
					var todayCount = result[0].tabTotal+result[0].webTotal+result[0].partyOfcHydTotal+result[0].partyOfcVijTotal+result[0].onlineTotal;
					str+='<div class="col-md-8 col-xs-12 col-sm-8">';
					str+='<div class="table-responsive">';
						
					
					str+='<table class="table tableCadreDash">';
						str+='<thead>';
							str+='<th></th>';
							str+='<th>New</th>';
							str+='<th>Renewal</th>';
							str+='<th>Total</th>';
						str+='</thead>';
						str+='<tbody style="background:#fff;">';
						str+='<tr>';
							str+='<td>Tab</td>';
							if(result[0].tabNew == null || result[0].tabNew == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].tabNew+'</td>';
							}
							if(result[0].tabRenewal == null || result[0].tabRenewal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].tabRenewal+'</td>';
							}
							if(result[0].tabTotal == null || result[0].tabTotal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].tabTotal+'</td>';
							}
							
						str+='</tr>';
						str+='<tr>';
							str+='<td>Web</td>';
							if(result[0].webNew == null || result[0].webNew == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].webNew+'</td>';
							}
							if(result[0].webRenewal == null || result[0].webRenewal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].webRenewal+'</td>';
							}
							if(result[0].webTotal == null || result[0].webTotal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].webTotal+'</td>';
							}
							
						str+='</tr>';
						str+='<tr>';
							str+='<td>Partyoffice(Hyd)</td>';
							if(result[0].partyOfcHydNew == null || result[0].partyOfcHydNew == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].partyOfcHydNew+'</td>';
							}
							if(result[0].partyOfcHydRenewal == null || result[0].partyOfcHydRenewal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].partyOfcHydRenewal+'</td>';
							}
							if(result[0].partyOfcHydTotal == null || result[0].partyOfcHydTotal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].partyOfcHydTotal+'</td>';
							}
							
							
						str+='</tr>';
						str+='<tr>';
							str+='<td>Partyoffice(Vij)</td>';
							if(result[0].partyOfcVijNew == null || result[0].partyOfcVijNew == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].partyOfcVijNew+'</td>';
							}
							if(result[0].partyOfcVijRenewal == null || result[0].partyOfcVijRenewal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].partyOfcVijRenewal+'</td>';
							}
							if(result[0].partyOfcVijTotal == null || result[0].partyOfcVijTotal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].partyOfcVijTotal+'</td>';
							}
							
							
						str+='</tr>';
						str+='<tr>';
							str+='<td>Online</td>';
							if(result[0].onlineNew == null || result[0].onlineNew == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].onlineNew+'</td>';
							}
							if(result[0].onlineRenewal == null || result[0].onlineRenewal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].onlineRenewal+'</td>';
							}
							if(result[0].onlineTotal == null || result[0].onlineTotal == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[0].onlineTotal+'</td>';
							}
							
						str+='</tr>';
						str+='</tbody>';
					str+='</table>';
					str+='</div>';
					str+='</div>';
					   str+='<div class="col-md-1  hidden-xs col-sm-1" style="padding-left:0px">';
					   str+='<img src="images/icons/arrowBrace.png" style="margin-top: 100px;">';
					   str+='</div>';
					   str+='<div class="col-md-3 col-xs-12 col-sm-3" style="margin-top: 100px;">';
					   str+='<h2 class="f_26">'+todayCount+'</h2>';
					   str+='<p>Members Registered <br>';
					   str+='<span style="font-weight:bold;">Today</span>';
					   str+='</p> </div>';
					
					
					//total
					var totalCount = result[1].tabTotal+result[1].webTotal+result[1].partyOfcHydTotal+result[1].partyOfcVijTotal+result[1].onlineTotal;
					str1+='<div class="col-md-8 col-xs-12 col-sm-8">';
					str1+='<div class="">';
					str1+='<table class="table tableCadreDash">';
						str1+='<thead>';
							str1+='<th></th>';
							str1+='<th>New</th>';
							str1+='<th>Renewal</th>';
							str1+='<th>Total</th>';
						str1+='</thead>';
						str1+='<tbody style="background:#fff;">';
						str1+='<tr>';
							str1+='<td>Tab</td>';
							if(result[1].tabNew == null || result[1].tabNew == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].tabNew+'</td>';
							}
							if(result[1].tabRenewal == null || result[1].tabRenewal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].tabRenewal+'</td>';
							}
							if(result[1].tabTotal == null || result[1].tabTotal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].tabTotal+'</td>';
							}
							
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Web</td>';
							if(result[1].webNew == null || result[1].webNew == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].webNew+'</td>';
							}
							if(result[1].webRenewal == null || result[1].webRenewal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].webRenewal+'</td>';
							}
							if(result[1].webTotal == null || result[1].webTotal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].webTotal+'</td>';
							}
							
							
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Partyoffice(Hyd)</td>';
							if(result[1].partyOfcHydNew == null || result[1].partyOfcHydNew == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].partyOfcHydNew+'</td>';
							}
							if(result[1].partyOfcHydRenewal == null || result[1].partyOfcHydRenewal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].partyOfcHydRenewal+'</td>';
							}
							if(result[1].partyOfcHydTotal == null || result[1].partyOfcHydTotal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].partyOfcHydTotal+'</td>';
							}
							
							
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Partyoffice(Vij)</td>';
							if(result[1].partyOfcVijNew == null || result[1].partyOfcVijNew == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].partyOfcVijNew+'</td>';
							}
							if(result[1].partyOfcVijRenewal == null || result[1].partyOfcVijRenewal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].partyOfcVijRenewal+'</td>';
							}
							if(result[1].partyOfcVijTotal == null || result[1].partyOfcVijTotal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].partyOfcVijTotal+'</td>';
							}
							
							
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Online</td>';
							if(result[1].onlineNew == null || result[1].onlineNew == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].onlineNew+'</td>';
							}
							if(result[1].onlineRenewal == null || result[1].onlineRenewal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].onlineRenewal+'</td>';
							}
							if(result[1].onlineTotal == null || result[1].onlineTotal == 0){
								str1+='<td> - </td>';
							}else{
								str1+='<td>'+result[1].onlineTotal+'</td>';
							}
						
						str1+='</tr>';
						str1+='</tbody>';
					str1+='</table>';
					str1+='</div>';
					str1+='</div>';

					str1+='<div class="col-md-1  hidden-xs col-sm-1" style="padding-left:0px">';
					str1+='<img src="images/icons/arrowBrace.png" style="margin-top: 100px;">';
					str1+='</div>';
					str1+='<div class="col-md-3 col-xs-12 col-ms-3" style="margin-top: 100px;">';
					str1+='<h2 class="f_26">'+totalCount+'</h2>';
					str1+='<p>Members Registered <br>';
					str1+='<span style="font-weight:bold;">Total</span>';
					str1+='</p> </div>';
					  
					  
					
					$("#todayRegisCount").html(str);
					$("#totalRegisCount").html(str1);
					
				}
		 });
	}
	
	function get2016LocationWiseRegisteredCountsConstituencyWise(type,locationScope,locationType){
		setcolorsForStatus();
		$("#constituencyWise2016Details").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
				var type = type;
				var locationScopeId = locationScope;
				var locationType =locationType;
				
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
				$("#constituencyWise2016Details").html('');
			build2016LocationWiseRegisteredCountsConstituencyWise(result);
					
		 });
	}
	
	function get2016LocationWiseRegisteredCountsDistrictWise(type,locationScope,locationType){
		setcolorsForStatus();
		$("#districtWise2016Details").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
				var type = type;
				var locationScopeId = locationScope;
				var locationType =locationType;
				
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
				$("#districtWise2016Details").html('');
			build2016LocationWiseRegisteredCountsDistrictWise(result);
			
					
		 });
	}
	function build2016LocationWiseRegisteredCountsDistrictWise(result){
		
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
						str+='<td ><div class="cCodeDiv" style="background-color:green;"/>'+result[0].veryGood+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:lightgreen;"/>'+result[0].good+'</td>';
						str+='<td><div class="cCodeDiv" style="background-color:yellow;"/>'+result[0].ok+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+result[0].poor+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+result[0].veryPoor+'</td>';
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
			
			if($(window).width > 768)
			{
				str+='<div class="table-responsive">';
			}
			
			str+='<table class="table table-bordered" id="districtWise2016DataTableId">';
				str+='<thead>';
					str+='<th>District</th>';
					str+='<th>Target Cadres</th>';
					str+='<th>Renewal</th>';
					str+='<th>New</th>';
					str+='<th>Total</th>';
					str+='<th>% of Register cadres</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].targetCount+'</td>';
					
					if(result[i].renewalPerc == null || result[i].renewalPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[i].renewalCount == null || result[i].renewalCount == 0){
						str+='<td>'+result[i].renewalCount+'</td>';
						}else{
							str+='<td>'+result[i].renewalCount+'<small>('+result[i].renewalPerc+' %)</small></td>';
						}
					}
					
					
					if(result[i].newPerc == null || result[i].newPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[i].newCount == null || result[i].newCount == 0){
							str+='<td>'+result[i].newCount+'</td>';
						}else{
							str+='<td>'+result[i].newCount+'<small>('+result[i].newPerc+' %)</small></td>';
						}
					}
					
					
					if(result[i].count2016 == null || result[i].count2016 == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[i].count2016+'</td>';
					}
					
					if(result[i].perc2016 == null || result[i].perc2016 == 0 || result[i].perc2016 == ""){
						str+='<td> - </td>';
					}else{
						var colorStatus = getColorCodeByStatus(result[i].levelPerformanceType)
						str+='<td style="color:'+colorStatus+';">'+result[i].perc2016+'</td>';
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
		
		$("#districtWise2016Details").html(str);
		$("#districtWise2016DataTableId").dataTable({
			"order": [ 4, 'desc' ]
		});
	}
	   
	function build2016LocationWiseRegisteredCountsConstituencyWise(result){
		
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
						str+='<td ><div class="cCodeDiv" style="background-color:green;"/>'+result[0].veryGood+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:lightgreen;"/>'+result[0].good+'</td>';
						str+='<td><div class="cCodeDiv" style="background-color:yellow;"/>'+result[0].ok+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+result[0].poor+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+result[0].veryPoor+'</td>';
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
				for(var i in result){
					str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					str+='<td>'+result[i].targetCount+'</td>';
					if(result[i].renewalPerc == null || result[i].renewalPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[i].renewalCount == null || result[i].renewalCount == 0){
						str+='<td>'+result[i].renewalCount+'</td>';
						}else{
							str+='<td>'+result[i].renewalCount+'<small>('+result[i].renewalPerc+' %)</small></td>';
						}
					}
					
					
					if(result[i].newPerc == null || result[i].newPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[i].newCount == null || result[i].newCount == 0){
							str+='<td>'+result[i].newCount+'</td>';
						}else{
							str+='<td>'+result[i].newCount+'<small>('+result[i].newPerc+' %)</small></td>';
						}
					}
					if(result[i].count2016 == null || result[i].count2016 == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[i].count2016+'</td>';
					}
					if(result[i].perc2016 == null || result[i].perc2016 == 0 || result[i].perc2016 == ""){
						str+='<td> - </td>';
					}else{
						var colorStatus = getColorCodeByStatus(result[i].levelPerformanceType)
						str+='<td style="color:'+colorStatus+';">'+result[i].perc2016+'</td>';
						
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
	

</script>
</body>
</html>
