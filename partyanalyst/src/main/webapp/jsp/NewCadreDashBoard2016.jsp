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
.panel
{
	margin-bottom:00px;
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
	border-spacing:0px 2px ;
	text-align:left;
	font-size:16px !important;
}
.tableCadreDash thead th
{
	border-bottom:0px !important;
	text-align:center;
	background-color:#999999;
}
.tableCadreDash tr td
{
	border:1px solid #ddd !important;
	background-color:#999999;
	padding:2px 6px!important;
	color:#fff !important;
	font-size:14px !important;
	font-weight:bold;
	border-radius:8px;
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
.text-capitalize{text-transform:uppercase;}

</style>
<body>
<div class="container">
		<div class="row" >
			<button type="button" class="btn btn-primary pull-right show2014DashBoard">click here to view 2014 Cadre DashBoard </button>
		</div>
		<!-- Title Row -->
		<div class="row m_top10" id="fadeInDown">
			<div class="col-md-12 col-xs-12 col-sm-12 well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase"><b>2016 Cadre Admin Dashboard</b></h3>
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
									<h2 style="display:inline-block;">TS</h2>
									<img style="display:inline-block;width:110px;" class="pull-right" src="images/TS.png">
								</td>
								<td><div id="tsConstiCountId"><h2>119</h2>
									<p>Registration Started Constituencies</p></div>
								</td>
								<td><div id="ts2014NewCountId"><h2>794130</h2><p>Members Registered in<br><span class="text-red">2014</span></p></div></td>
								<td>
									<div id="ts2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
								</td>
								<td>
									<p><span class="text-orange">Total - [%] </span></p>
									<div id="ts2016PrecCountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>
								</td>
								
							</tr>
							<tr class="">
								<td>
									<h2 style="display:inline-block;">AP</h2>
									<img style="display:inline-block;width:110px;" class="pull-right" src="images/AP.png">
								</td>
								<td><div id="apConstiCountId"><h2>175</h2><p>Registration Started Constituencies</p></div></td>
								<td><div id="ap2014NewCountId"><h2>4672124</h2><p>Members Registered in<br><span class="text-red">2014</span></p></div></td>
								<td><div id="ap2016CountId"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></td>
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
			<label class="pull-right text-muted" style="margin-top: -10px;">Note: R - Renewal &amp; N - New</label>
				<div class="row">
					<div class="col-md-6 col-xs-12 col-sm-6 m_top15">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#EAEAEA;border-bottom:0px">
								<h4 class="panel-title">TODAY</h4>
							</div>
							<div class="panel-body" style="background-color:#EAEAEA">
								<div class="row">
									<div id="todayRegisCount">
										<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
									</div>
								</div>
							</div>
						</div>
						<div id="todayApTgRegisCount" class="row ">
							<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
						</div>
					</div>
					<div class="col-md-6 col-xs-12 col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#DBF1E4;border-bottom:0px">
								<h4 class="panel-title">OVER ALL</h4>
							</div>
							<div class="panel-body" style="background-color:#DBF1E4">
								<div class="row">
									<div id="totalRegisCount">
										<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
									</div>
								</div>
							</div>
						</div>
						<div id="totalApTgRegisCount" class="row">
							<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>
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
		<!-- Members Count Row End -->
		<!----New code for constituency and district wise Start ----->
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4 Style="color:green"> DISTRICT  TARGET VS REGISTERED CADRE
				</h4>
			
				<div style="padding:5px;">
					<input type="radio" class="radiobuttonSelectedWise" id="" name="compareC" value="today"  style="margin-top:0px;"/>
					<span style="margin-right:10px;"> TODAY</span>
					<!--<input type="radio" class="" id="" name="compareC" value="asoftoday" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> AS OF TODAY </span>-->
					<input type="radio" class="radiobuttonSelectedWise" id="" name="compareC" value="total" style="margin-top:0px;" checked="true"/>
					<span style="margin-right:10px;"> OVER ALL </span>
					<button class="btn btn-success btn-xs" id="distExcelExpBtnId" attr_tab_user_type="Tab">Export To Excel</button>
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
				<h4 style="color:green"> CONSTITUENCY  TARGET VS REGISTERED CADRE </h4>
				
				<div style="padding:5px;">
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="todayconstituencyValue" name="compareD" value="today" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> TODAY</span>
					<!--<input type="radio" class="typeRd" id="" name="compareD" value="asoftoday" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> AS OF TODAY</span>-->
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="totalconstituencyValue" name="compareD" value="total"  checked="true" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> OVER ALL</span>      
					<button class="btn btn-success btn-xs" id="constExcelExpBtnId" attr_tab_user_type="Tab" >Export To Excel</button>
					<div class="btn-group pull-right">  
						<button type="button" value="AP" class="btn btn-mini btn-success   aptsconsSele" name="distTargetBtn" id="apConsTargetComp" checked="checked">AP</inpbuttonut>
						<button type="button" value="TS" class="btn btn-mini   aptsconsSele" name="distTargetBtn" id="tsConsTargetComp">TS</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="constituencyWise2016DetailsOvrVw" class="m_top10"></div>
					</div>
					
					<div class="col-md-12 col-xs-12 col-sm-12">
						<div id="constituencyWise2016Details" class="m_top10"></div>
					</div>
				</div>
				
				<div id="leaderDataDiv1" class="scrollable_div" style="margin-top: -1px">
					
				</div>
			</div>
		</div>
		<input type="hidden" value="" id="hideConstId"/>  
		<!----New code for constituency and district wise End ----->
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
						<input type="radio" class="scopeRadioCls" name="scopeType" id="inlineRadio1" value="Total" style="margin-top: 0px;"><h5 style="margin-top:-1px">Over All</h5>
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
						<input type="radio" class="locationRadioCls" name="selectionType" id="inlineRadio" value="panchayat" style="margin-top: 0px;"><h5 style="margin-top:-1px">Panchayat Wise</h5>
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
</div>
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
var glDistricvtId = 0;
var statusColorArr = [];
var statusarr = ['VeryGood','Good','Ok','Poor','VeryPoor'];
function setcolorsForStatus(){
		statusColorArr = new Array();
		var colorStatic = new Array('#008000','#90EE90','#556B2F','#FFA500','#C43C35');
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

//color codes starts end

 $(".show2014DashBoard").click(function(){
	window.location.replace('cadreDashBoardAction.action');
});
//dist

$(document).on("click",".aptsclass",function(){
		glDistricvtId = 0;
	  $(".aptsclass").removeClass("btn-success");
	  $(this).addClass("btn-success");
	  
	 get2016LocationWiseRegisteredCountsDistrictWise( $('input[name=compareD]:checked').val(),3,$(this).val() );
	
});
$(document).on("click",".radiobuttonSelectedWise",function(){
	 glDistricvtId = 0;
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
		glDistricvtId = 0;
	  $(".aptsconsSele").removeClass("btn-success");
	  $(this).addClass("btn-success");
	  
	 get2016LocationWiseRegisteredCountsConstituencyWise( $('input[name=compareD]:checked').val(),4,$(this).val() );
	
});

$(document).on("click",".radiobuttonSelectedConsWise",function(){
		glDistricvtId = 0;
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
 get2016LocationWiseRegisteredCounts("total");
 //getDataSourceTypeWiseRegisteredDetails();
 get2016LocationWiseRegisteredCountsDistrictWise("total",3,"AP");
 get2016LocationWiseRegisteredCountsConstituencyWise("total",4,"AP");

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
				locationType:locationType,
				districtId:0
				
			}
			$.ajax({
			  type:'GET',
			  url: 'get2016LocationWiseRegisteredCountsForConstitunencyAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				if(result !=null && result.length >0){
					if(typeId == 'total'){
						var str='';
						var str1='';
						str+='<h2>'+result[0].count2016+'</h2>';
						str+='<p>Members Registered in <span class="text-blue">2016</span></p>';
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
						
						str1+='<h2>'+result[1].count2016+'</h2>';
						str1+='<p>Members Registered in <span class="text-blue">2016</span></p>';
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
						$("#totalApTgRegisCount").html('<div class="col-md-4 col-xs-12 col-sm-12 mytooltip text-center m_top10" ><div style="padding: 5px; background-color: #DBF1E4"><strong><h4 class="panel-title text-center text-muted"> TOTAL  </h4></strong><h3 class="text-center" style="margin:0px">'+overalltotal+'</h3><span class="text-skyblue"></span></div></div><div class="col-md-4 col-xs-12 col-sm-12 mytooltip text-center m_top10" ><div style="padding: 5px; background-color: #DBF1E4"><strong><h4 class="panel-title text-center text-muted"> ANDHRA PRADESH  </h4></strong><h3 class="text-center" style="margin:0px">'+result[0].count2016+'</h3><span class="text-skyblue"></span></div></div><div  class="col-md-4 col-xs-12 col-sm-12 mytooltip text-center m_top10" ><div style="padding: 5px; background-color: #DBF1E4"><strong><h4 class="panel-title text-center text-muted">TELANGANA </h4></strong> <h3 class="text-center" style="margin:0px">'+result[1].count2016+'</h3><span class="text-skyblue"> </span></div></div>');
						
					}else if(typeId == 'today'){
						var todaytotal = result[0].count2016+result[1].count2016;
						$("#todayApTgRegisCount").html('<div class="col-md-4 col-xs-12 col-sm-12 mytooltip text-center m_top10" ><div style="padding: 5px; background-color: rgb(234, 234, 234);"><strong><h4 class="panel-title text-center text-muted">  TOTAL </h4></strong><h3 class="text-center" style="margin:0px"> '+todaytotal+'</h3></div></div><div class="col-md-4 col-xs-12 col-sm-12 mytooltip text-center m_top10" ><div style="padding: 5px; background-color: rgb(234, 234, 234);"><strong><h4 class="panel-title text-center text-muted">  ANDHRA PRADESH </h4></strong><h3 class="text-center" style="margin:0px"> '+result[0].count2016+'</h3></div></div><div  class="col-md-4 col-xs-12 col-sm-12 text-center mytooltip m_top10" ><div style="padding: 5px; background-color: rgb(234, 234, 234);"><strong><h4 class="panel-title text-center text-muted">TELANGANA </h4></strong><h3 class="text-center" style="margin:0px"> '+result[1].count2016+'</h3></div></div>');
						
					
					}
					
				}
		 });
	}
	
	/*function getDataSourceTypeWiseRegisteredDetails(){
		var jObj = {
			type : "Today"
		}
	
		$.ajax({
		  type:'GET',
		  url: 'getDataSourceTypeWiseRegisteredDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			
		});
	}
	
	function getDataSourceTypeWiseRegisteredDetails(){
		var jObj = {
			type : "Total"
		}
	
		$.ajax({
		  type:'GET',
		  url: 'getDataSourceTypeWiseRegisteredDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			
		});
	}*/
	getDataSourceTypeCounts("Today");
	getDataSourceTypeCounts("Total");
	function getDataSourceTypeCounts(type){
		var jObj = {
			type:type
		}
		$.ajax({
		  type:'GET',
		  url: 'getDataSourceTypeWiseCountsByTypeAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			if(result !=null && result.length >0){
				var str='';
				var todayCount = 0;
				str+='<div class="col-md-8 col-xs-12 col-sm-8">';
					str+='<div class="table-responsive">';
						str+='<table class="table tableCadreDash">';
							str+='<tbody >';
							for(var i in result){
								str+='<tr>';
									str+='<td><span class="text-capitalize" style="color:#d5d5d5">'+result[i].name+':&nbsp;</span>';
										if(result[i].inviteeCount != null){
											str+=''+result[i].inviteeCount+'';
										}else{
											str+='0';
										}
										if(result[i].inviteeAttendeeCnt != null){
											str+='(R - '+result[i].inviteeAttendeeCnt+')';
										}else{
											str+='(R - 0)';
										}
										if(result[i].attenteeCount != null){
											str+='(N - '+result[i].attenteeCount+')';
										}else{
											str+='(N - 0)';
										}
									str+='</td>';
								str+='</tr>';
								if(result[i].inviteeCount != null){
									todayCount = todayCount+parseInt(result[i].inviteeCount);
								}
							}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
				
				str+='<div class="col-md-1  hidden-xs col-sm-1" style="padding-left:0px">';
					str+='<img src="images/icons/arrowBrace.png" class="img-responsive" style="height: 150px; margin-top: 3px;">';
			    str+='</div>';
			    str+='<div class="col-md-3 col-xs-12 col-sm-3" style="margin-top: 40px;padding-left:0px;padding-right:0px">';
					str+='<h2 class="f_26" style="margin-bottom: 0px;">'+todayCount+'</h2>';
					str+='<p>Members Registered <br>';
						if(type == 'Today')
							str+='<span style="font-weight:bold;">Today</span>';
						else if(type == 'Total')
							str+='<span style="font-weight:bold;">Total</span>';
					str+='</p> </div>';
					
				if(type == 'Today')
					$("#todayRegisCount").html(str);
				else if(type == 'Total')
					$("#totalRegisCount").html(str);
			}
			else{
				if(type == 'Today')
					$("#todayRegisCount").html('NO DATA AVAILABLE');
				else if(type == 'Total')
					$("#totalRegisCount").html('NO DATA AVAILABLE');
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
					var partyOfficeceTotal = result[0].partyOfcHydTotal+result[0].partyOfcVijTotal;
					var paryOfficeNew = result[0].partyOfcHydNew+result[0].partyOfcVijNew;
					var partyOfficeRenewal = result[0].partyOfcHydRenewal+result[0].partyOfcVijRenewal;
					str+='<div class="col-md-8 col-xs-12 col-sm-8">';
					str+='<div class="table-responsive">';
						
					
					str+='<table class="table tableCadreDash">';
						/* str+='<thead>';
							str+='<th></th>';
							str+='<th>New</th>';
							str+='<th>Renewal</th>';
							str+='<th>Total</th>';
						str+='</thead>'; */
						str+='<tbody >';
						str+='<tr>';
							str+='<td><span class="text-capitalize" style="color:#d5d5d5">Tab:&nbsp;</span>';
							if(result[0].tabTotal == null || result[0].tabTotal == 0){
								str+='0';
							}else{
								str+=''+result[0].tabTotal+'';
							}
							if(result[0].tabRenewal == null || result[0].tabRenewal == 0){
								str+='(R - 0)';
							}else{
								str+='(R - '+result[0].tabRenewal+')';
							}
							if(result[0].tabNew == null || result[0].tabNew == 0){
								str+='(N - 0)';
							}else{
								str+='(N - '+result[0].tabNew+')';
							}
							
							
							str+='</td>';
						str+='</tr>';
						str+='<tr>';
							str+='<td><span class="text-capitalize" style="color:#d5d5d5">Web:&nbsp;</span>';
							if(result[0].webTotal == null || result[0].webTotal == 0){
								str+='0';
							}else{
								str+=''+result[0].webTotal+'';
							}
							if(result[0].webRenewal == null || result[0].webRenewal == 0){
								str+='(R - 0)';
							}else{
								str+='(R - '+result[0].webRenewal+')';
							}
							if(result[0].webNew == null || result[0].webNew == 0){
								str+='(N - 0)';
							}else{
								str+='(N - '+result[0].webNew+')';
							}
							
							
							str+='</td>';
						str+='</tr>';
						
						str+='<tr>';
							str+='<td><span class="text-capitalize" style="color:#d5d5d5">Party Office:&nbsp;</span>';
							if(result[0].partyOfficeceTotal == null || result[0].partyOfficeceTotal == 0){
								str+='0';
							}else{
								str+=''+result[0].partyOfficeceTotal+'';
							}
							if(result[0].partyOfficeRenewal == null || result[0].partyOfficeRenewal == 0){
								str+='(R - 0)';
							}else{
								str+='(R - '+result[0].partyOfficeRenewal+')';
							}
							if(result[0].paryOfficeNew == null || result[0].paryOfficeNew == 0){
								str+='(N - 0)';
							}else{
								str+='(N - '+result[0].paryOfficeNew+')';
							}
							
							
							str+='</td>';
						str+='</tr>';
						str+='<tr>';
							str+='<td><span class="text-capitalize" style="color:#d5d5d5">Online:&nbsp;</span>';
							if(result[0].onlineTotal == null || result[0].onlineTotal == 0){
								str+='0';
							}else{
								str+=''+result[0].onlineTotal+'';
							}
							if(result[0].onlineRenewal == null || result[0].onlineRenewal == 0){
								str+='(R - 0)';
							}else{
								str+='(R - '+result[0].onlineRenewal+')';
							}
							if(result[0].onlineNew == null || result[0].onlineNew == 0){
								str+='(N - 0)';
							}else{
								str+='(N - '+result[0].onlineNew+')';
							}
							
							
							str+='</td>';
						str+='</tr>';
						str+='</tbody>';
					str+='</table>';
					str+='</div>';
					str+='</div>';
					   str+='<div class="col-md-1  hidden-xs col-sm-1" style="padding-left:0px">';
					   str+='<img src="images/icons/arrowBrace.png" class="img-responsive" >';
					   str+='</div>';
					   str+='<div class="col-md-3 col-xs-12 col-sm-3" style="margin-top: 10px;padding-left:0px;padding-right:0px">';
					   str+='<h2 class="f_26">'+todayCount+'</h2>';
					   str+='<p>Members Registered <br>';
					   str+='<span style="font-weight:bold;">Today</span>';
					   str+='</p> </div>';
					
					
					//total
					var totalCount = result[1].tabTotal+result[1].webTotal+result[1].partyOfcHydTotal+result[1].partyOfcVijTotal+result[1].onlineTotal;
					var partyOfficeceTotal = result[1].partyOfcHydTotal+result[1].partyOfcVijTotal;
					var paryOfficeNew = result[1].partyOfcHydNew+result[1].partyOfcVijNew;
					var partyOfficeRenewal = result[1].partyOfcHydRenewal+result[1].partyOfcVijRenewal;
					str1+='<div class="col-md-8 col-xs-12 col-sm-8">';
					str1+='<div class="">';
					str1+='<table class="table tableCadreDash">';
						/* str1+='<thead>';
							str1+='<th></th>';
							str1+='<th>New</th>';
							str1+='<th>Renewal</th>';
							str1+='<th>Total</th>';
						str1+='</thead>'; */
						str1+='<tbody >';
						str1+='<tr>';
							str1+='<td><span class="text-capitalize" style="color:#d5d5d5">Tab:&nbsp;</span>';
							if(result[1].tabTotal == null || result[1].tabTotal == 0){
								str1+='0';
							}else{
								str1+=''+result[1].tabTotal+'';
							}
							if(result[1].tabRenewal == null || result[1].tabRenewal == 0){
								str1+='(R - 0)';
							}else{
								str1+='(R - '+result[1].tabRenewal+')';
							}
							if(result[1].tabNew == null || result[1].tabNew == 0){
								str1+='(N - 0)';
							}else{
								str1+='(N - '+result[1].tabNew+')';
							}
							
							
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td><span class="text-capitalize" style="color:#d5d5d5">Web:&nbsp;</span>';
							if(result[1].webTotal == null || result[1].webTotal == 0){
								str1+='0';
							}else{
								str1+=''+result[1].webTotal+'';
							}
							if(result[1].webRenewal == null || result[1].webRenewal == 0){
								str1+='(R - 0)';
							}else{
								str1+='(R - '+result[1].webRenewal+')';
							}
							if(result[1].webNew == null || result[1].webNew == 0){
								str1+='(N - 0)';
							}else{
								str1+='(N - '+result[1].webNew+')';
							}
							
							
							str1+='</td>';
						str1+='</tr>';
						
						str1+='<tr>';
							str1+='<td><span class="text-capitalize" style="color:#d5d5d5">Party Office:&nbsp;</span>';
							if(result[1].partyOfficeceTotal == null || result[1].partyOfficeceTotal == 0){
								str1+='0';
							}else{
								str1+=''+result[1].partyOfficeceTotal+'';
							}
							if(result[1].partyOfficeRenewal == null || result[1].partyOfficeRenewal == 0){
								str1+='(R - 0)';
							}else{
								str1+='(R - '+result[1].partyOfficeRenewal+')';
							}
							if(result[1].paryOfficeNew == null || result[1].paryOfficeNew == 0){
								str1+='(N - 0)';
							}else{
								str1+='(N - '+result[1].paryOfficeNew+')';
							}
							
							
							str1+='</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td><span class="text-capitalize" style="color:#d5d5d5">Online:&nbsp;</span>';
							if(result[1].onlineTotal == null || result[1].onlineTotal == 0){
								str1+='0';
							}else{
								str1+=''+result[1].onlineTotal+'';
							}
							if(result[1].onlineRenewal == null || result[1].onlineRenewal == 0){
								str1+='(R - 0)';
							}else{
								str1+='(R - '+result[1].onlineRenewal+')';
							}
							if(result[1].onlineNew == null || result[1].onlineNew == 0){
								str1+='(N - 0)';
							}else{
								str1+='(N - '+result[1].onlineNew+')';
							}
							
							
							str1+='</td>';
						str1+='</tr>';
						str1+='</tbody>';
					str1+='</table>';
					str1+='</div>';
					str1+='</div>';

					str1+='<div class="col-md-1  hidden-xs col-sm-1" style="padding-left:0px">';
					str1+='<img src="images/icons/arrowBrace.png"  class="img-responsive">';
					str1+='</div>';
					str1+='<div class="col-md-3 col-xs-12 col-ms-3" style="margin-top: 10px;padding-left:0px;padding-right:0px">';
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
				locationType:locationType,
				districtId:0
				
			}
			$.ajax({
			  type:'GET',
			  url: 'get2016LocationWiseRegisteredCountsForConstitunencyAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				$("#constituencyWise2016Details").html('');
				vsResultGlob = result;
				build2016LocationWiseRegisteredCountsConstituencyWise(result,type,"0",locationType);
		 });
	}
	function get2016LocationWiseRegisteredCountsConstituencyWiseForOnChange(type,locationScope,locationType,districtId){
		$("#subDistTrId"+districtId).show();
		$("#subDistTrId"+districtId).html('<img src="images/icons/loading.gif" style="margin-left:500px;width:80px;height:80px;" id="parentDistrictWiseImgId"/>');
		setcolorsForStatus();
				var type = type;
				var locationScopeId = locationScope;
				var locationType =locationType;
				
			var jObj = {
				type:type,
				locationScopeId:locationScopeId,
				locationType:locationType,
				districtId:districtId
				
			}
			$.ajax({
			  type:'GET',
			  url: 'get2016LocationWiseRegisteredCountsForConstitunencyAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				inBuild2016LocationWiseRegisteredCountsConstituencyWise(result,type,districtId);		
		 });
	}
	$(document).on("change","#districtId",function(){
		glDistricvtId =$('#districtId').val();
		var locationType = ''; 
			 $('.aptsconsSele').each(function(i, obj){
				  if($(this).hasClass("btn-success")){
					  locationType = $(this).val();
				  }
			 });
		build2016LocationWiseRegisteredCountsConstituencyWise(vsResultGlob,$('input[name=compareD]:checked').val(),$(this).val(),locationType);
		buildLocationWiseRegConsWiseDistChngePerformanceCnts(vsResultGlob,$('input[name=compareD]:checked').val(),$(this).val(),locationType);
	});
	
	function get2016LocationWiseRegisteredCountsDistrictWise(type,locationScope,locationType){
		setcolorsForStatus();
		$("#districtWise2016Details").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
				var type = type;
				var locationScopeId = locationScope;
				var locationType =locationType;
				
			var jObj = {
				type:type,
				locationScopeId:locationScopeId,
				locationType:locationType,
				districtId:0
				
			}
			$.ajax({
			  type:'GET',
			  url: 'get2016LocationWiseRegisteredCountsForConstitunencyAction.action',
			  data : {task:JSON.stringify(jObj)} ,
            }).done(function(result){
				$("#districtWise2016Details").html('');
			build2016LocationWiseRegisteredCountsDistrictWise(result,type);
			
					
		 });
	}
	function build2016LocationWiseRegisteredCountsDistrictWise(result,type){              
		var str='';
		if(result !=null && result.length >0){
			str+='<div class="table-responsive" style="margin-top:20px;">';
			str+='<div class="span12 pull-right">';
			str+='<i class="glyphicon glyphicon-info-sign" id="percentageId" title="Click to how to calculating percentages" style="margin-left: 20px;margin-bottom:9px;;cursor:pointer;cursor:pointer;"></i>';
			str+='</div>';
			str+='<table class="table table-bordered " >';
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
						str+='<td><div class="cCodeDiv" style="background-color:darkolivegreen;"/>'+result[0].ok+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+result[0].poor+'</td>';
						str+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+result[0].veryPoor+'</td>';
						
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
			if($(window).width > 768)
			{
				str+='<div class="table-responsive">';
			}
			
			str+='<table class="table table-bordered " id="districtWise2016DataTableId">';
				str+='<thead>';
					str+='<th>DISTRICT ID</th>';   
					str+='<th>DISTRICT</th>';
					str+='<th>TARGET</th>';
					str+='<th>RENEWAL</th>';
					str+='<th>RENEWAL.%</th>';
					str+='<th>NEW</th>';
					str+='<th>NEW.%</th>';
					str+='<th>MAN POWER</th>';  
					/* str+='<th>TOTAL REG</th>';
					if(type == "total"){
						str+='<th>TODAY REG</th>';
					} */
					if(type == "total"){
						str+='<th>TOTAL REG.  COUNT</th>';
						str+='<th>TODAY REG.  COUNT</th>';    
					}else if(type == "today"){
						str+='<th>TODAY REG.  COUNT</th>';   
					}
					str+='<th>REG.%</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					str+='<tr class="tejasClass">';
					str+='<td><i class="glyphicon glyphicon-plus vsDistrictResultCls" style="cursor:pointer;" attr_distId="'+result[i].id+'" attr_type="'+type+'"></i>'+result[i].id+'</td>';
					str+='<td>'+result[i].name+'</td>';    
					
					str+='<td>'+result[i].targetCount+'</td>';
					
					if(result[i].renewalPerc == null || result[i].renewalPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[i].renewalCount == null || result[i].renewalCount == 0){
						str+='<td>'+result[i].renewalCount+'</td>';
						}else{
							str+='<td>'+result[i].renewalCount+'</td>';
						}
					}
					if(result[i].renewalPerc == null || result[i].renewalPerc == 0 ){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[i].renewalPerc+'</td>';
					}
					
					if(result[i].newPerc == null || result[i].newPerc == 0){
						str+='<td> - </td>';
					}else{
						if(result[i].newCount == null || result[i].newCount == 0){
							str+='<td>'+result[i].newCount+'</td>';
						}else{
							str+='<td>'+result[i].newCount+'</td>';
						}
					}
					if(result[i].newPerc == null || result[i].newPerc == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[i].newPerc+'</td>';
					}
					if(result[i].mapPowerCount == null || result[i].mapPowerCount == 0){
						str+='<td> - </td>';  
					}else{
						str+='<td>'+result[i].mapPowerCount+'</td>';
					}
					
					if(result[i].count2016 == null || result[i].count2016 == 0){
						str+='<td> - </td>';
					}else{
						str+='<td>'+result[i].count2016+'</td>';
					}    
					if(type == "total"){
						if(result[i].count2016Today == null || result[i].count2016Today == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].count2016Today+'</td>';
						}
					}
					
					
					if(result[i].perc2016 == null || result[i].perc2016 == 0 || result[i].perc2016 == ""){
						str+='<td> - </td>';
					}else{
						var colorStatus = getColorCodeByStatus(result[i].levelPerformanceType)
						str+='<td style="color:'+colorStatus+';">'+result[i].perc2016+'</td>';
					}
					 
					str+='</tr>';
					str+='<span>';
					str+='<tr class="subDistTrCls" id="subDistTrId'+result[i].id+'">';
						
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
		/*$("#districtWise2016DataTableId").dataTable({
			"aaSorting": [[ 4, "desc" ]], 
			"iDisplayLength" : 15,
			"aLengthMenu": [[15,20,50, 100, -1], [15,20,50, 100, "All"]]	
		});*/
	}
$(document).on("click",".vsDistrictResultCls",function(){
	var districtId = $(this).attr("attr_distId");
	$(".subDistTrCls").html("");
	$("#subDistTrId"+districtId).html("");
	var type = $(this).attr("attr_type");
	var locationScope = 4;
	var locationType = ''; 
	 $('.aptsclass').each(function(i, obj){
          if($(this).hasClass("btn-success")){
			  locationType = $(this).val();
		  }
     });
	if($(this).hasClass("glyphicon-plus") == true)
	{
		$(".vsDistrictResultCls").removeClass("glyphicon-minus").addClass("glyphicon-plus");
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
		get2016LocationWiseRegisteredCountsConstituencyWiseForOnChange(type,locationScope,locationType,districtId);
	}else{
		$(".vsDistrictResultCls").removeClass("glyphicon-minus").addClass("glyphicon-plus");
	}
});
function inBuild2016LocationWiseRegisteredCountsConstituencyWise(result,type,matchedId){
	var str='';
	str+='<td colspan="9" style="background: silver">';
	if(result !=null && result.length >0){
		//str+='<span class="pull-right"><i class="glyphicon glyphicon-remove clearSubTr" attr_distId="'+matchedId+'" style="cursor:pointer;"></i></span>';
		str+='<table class="table table-bordered " id="constituencyWise2016DataTableId1">';
				str+='<thead>';
					str+='<th>AC_No</th>';
					str+='<th>DISTRICT</th>';
					str+='<th>CONSTITUENCY</th>';  
					
					str+='<th>2014 REG. COUNT</th>'; 
					str+='<th> 2014 TOTAL RENEWAL COUNT </th>'; 
					str+='<th> 2014 TOTAL RENEWAL % </th>'; 
					
					str+='<th>TARGET</th>';
					
					if(type == "total"){
						str+='<th>TOTAL REG.  COUNT</th>';
						//str+='<th>TODAY REG.  COUNT</th>';    
					}else if(type == "today"){
						str+='<th>TODAY REG.  COUNT</th>';   
					}
					str+='<th>TARGET ACHIEVED %</th>'; 
					
					
					str+='<th>RENEWAL</th>';
					str+='<th>RENEWAL.%</th>';
					str+='<th>NEW</th>';
					str+='<th>NEW.%</th>';
					if(type == "total"){
						str+='<th>TODAY REG.  COUNT</th>';    
					}
					str+='<th>MAN POWER</th>';    
					/* str+='<th>TOTAL REG</th>';
					if(type == "total"){
						str+='<th>TODAY REG</th>';    
					} */
					   
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					 if(matchedId == result[i].value){
						str+='<tr class="'+result[i].value+'cls distrctCls">';
						str+='<td>'+result[i].no+'</td>';    
						str+='<td attr_district_name ='+result[i].districtname+'>'+result[i].districtname+'</td>';
						str+='<td attr_const_id="'+result[i].id+'" attr_const_name="'+result[i].name+'" class="getDtlsCls" style="cursor:pointer;"><a>'+result[i].name+'</a></td>';
						
						
						str+='<td>'+result[i].count2014+'</td>';
						str+='<td>'+result[i].totalRenewal+'</td>';
						str+='<td>'+result[i].totalRenPerc+'</td>';
						
						
						
						str+='<td>'+result[i].targetCount+'</td>';
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
						
						if(result[i].renewalPerc == null || result[i].renewalPerc == 0){
							str+='<td> - </td>';
						}else{
							if(result[i].renewalCount == null || result[i].renewalCount == 0){
							str+='<td>'+result[i].renewalCount+'</td>';
							}else{
								str+='<td>'+result[i].renewalCount+'</td>';
							}
						}
						if(result[i].renewalPerc == null || result[i].renewalPerc == 0 ){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].renewalPerc+'</td>';
						}
						
						if(result[i].newPerc == null || result[i].newPerc == 0){
							str+='<td> - </td>';
						}else{
							if(result[i].newCount == null || result[i].newCount == 0){
								str+='<td>'+result[i].newCount+'</td>';
							}else{
								str+='<td>'+result[i].newCount+'</td>';
							}
						}
						if(result[i].newPerc == null || result[i].newPerc == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].newPerc+'</td>';
						}
						
						if(type == "total"){
							if(result[i].count2016Today == null || result[i].count2016Today == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[i].count2016Today+'</td>';
							}
						}  
						
						
						if(result[i].mapPowerCount == null || result[i].mapPowerCount == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].mapPowerCount+'</td>';
						}
						
						
						
						str+='</tr>';
					}
					
				}
				str+='</tbody>';
			str+='</table>';
	}else{
		str+='No DATA AVAILABLE';
	}
	str+='</td>';
	$("#subDistTrId"+matchedId).html(str);
	$("#constituencyWise2016DataTableId1").dataTable({
			"aaSorting": [[ 4, "desc" ]], 
			"iDisplayLength" : 10,
			"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]
		});
}
function getDistricts(locationType){
	//$("#districtId").html("");  
     var jsObj=
		{				
				stateid:locationType			
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForCadreAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
			   if(glDistricvtId == result[i].id)
					str +='<option value='+result[i].id+' selected="true">'+result[i].name+'</option>';
				else
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#districtId").html(str);   
	   }
   });
  }
	
	
	var vsResultGlob = "";
	var buildVrygdCount =0;
		var buildGdCount = 0;
		var buildOkCount = 0;
		var buildPoorCount = 0;
		var buildVeryPoorCount = 0;
	function build2016LocationWiseRegisteredCountsConstituencyWise(result,type,matchedId,locationType){
		var str='';
		var str1='';
		buildVrygdCount =0;
		 buildGdCount = 0;
		 buildOkCount = 0;
		 buildPoorCount = 0;
		 buildVeryPoorCount = 0;
		if(result !=null && result.length >0){
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
						str1+='<td ><div class="cCodeDiv" style="background-color:green;"/>'+result[0].veryGood+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:lightgreen;"/>'+result[0].good+'</td>';
						str1+='<td><div class="cCodeDiv" style="background-color:darkolivegreen;"/>'+result[0].ok+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+result[0].poor+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+result[0].veryPoor+'</td>';
					str1+='</tr>';
				str1+='</tbody>';
			str1+='</table>';
			$("#constituencyWise2016DetailsOvrVw").html(str1);
			if($(window).width > 768)
			{
				str+='<div class="table-responsive">';
			}
			str+='<div class="table-responsive">';
			str+='<table class="table table-bordered " id="constituencyWise2016DataTableId">';
				str+='<thead>';
					str+='<th>AC_No</th>';
					str+='<th>DISTRICT</th>';
					str+='<th>CONSTITUENCY</th>'; 
					str+='<th>2014 REG. COUNT</th>'; 
					str+='<th>2014 TOTAL RENEWAL COUNT </th>'; 
					str+='<th>2014 TOTAL RENEWAL % </th>'; 
					
					str+='<th>TARGET</th>';
					
					if(type == "total"){
						str+='<th>TOTAL REG.  COUNT</th>'; 
					}else if(type == "today"){
						str+='<th>TODAY REG.  COUNT</th>';   
					}
					str+='<th>TARGET ACHIEVED %</th>'; 
					
					
					str+='<th>RENEWAL</th>';
					str+='<th>RENEWAL.%</th>';
					str+='<th>NEW</th>';
					str+='<th>NEW.%</th>';
					if(type == "total"){
						str+='<th>TODAY REG.  COUNT</th>';    
					}
					str+='<th>MAN POWER</th>';    
					   
				str+='</thead>';
				str+='<tbody>';
				
				for(var i in result){
					 if(matchedId == 0){
						str+='<tr class="'+result[i].value+'cls distrctCls">';
						str+='<td>'+result[i].no+'</td>';    
						str+='<td attr_district_name ='+result[i].districtname+'>'+result[i].districtname+'</td>';
						str+='<td attr_const_id="'+result[i].id+'" attr_const_name="'+result[i].name+'" class="getDtlsCls" style="cursor:pointer;"><a>'+result[i].name+'</a></td>';
						
						str+='<td>'+result[i].count2014+'</td>';
						str+='<td>'+result[i].totalRenewal+'</td>';
						str+='<td>'+result[i].totalRenPerc+'</td>';
						
						str+='<td>'+result[i].targetCount+'</td>';
						
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
						
						if(result[i].renewalPerc == null || result[i].renewalPerc == 0){
							str+='<td> - </td>';
						}else{
							if(result[i].renewalCount == null || result[i].renewalCount == 0){
							str+='<td>'+result[i].renewalCount+'</td>';
							}else{
								str+='<td>'+result[i].renewalCount+'</td>';
							}
						}
						if(result[i].renewalPerc == null || result[i].renewalPerc == 0 ){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].renewalPerc+'</td>';
						}
						
						if(result[i].newPerc == null || result[i].newPerc == 0){
							str+='<td> - </td>';
						}else{
							if(result[i].newCount == null || result[i].newCount == 0){
								str+='<td>'+result[i].newCount+'</td>';
							}else{
								str+='<td>'+result[i].newCount+'</td>';
							}
						}
						if(result[i].newPerc == null || result[i].newPerc == 0 ){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].newPerc+'</td>';
						}
						
						if(type == "total"){
							if(result[i].count2016Today == null || result[i].count2016Today == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[i].count2016Today+'</td>';
							}
						}  
						
						if(result[i].mapPowerCount == null || result[i].mapPowerCount == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].mapPowerCount+'</td>';
						}
						
						
						
						str+='</tr>';
					}else if(matchedId == result[i].value){
						str+='<tr class="'+result[i].value+'cls distrctCls">';
						str+='<td>'+result[i].no+'</td>';    
						str+='<td attr_district_name ='+result[i].districtname+'>'+result[i].districtname+'</td>';
						str+='<td attr_const_id="'+result[i].id+'" attr_const_name="'+result[i].name+'" class="getDtlsCls" style="cursor:pointer;"><a>'+result[i].name+'</a></td>';
						
						str+='<td>'+result[i].count2014+'</td>';
						str+='<td>'+result[i].totalRenewal+'</td>';
						str+='<td>'+result[i].totalRenPerc+'</td>';
						
						
						str+='<td>'+result[i].targetCount+'</td>';
						
						if(result[i].count2016 == null || result[i].count2016 == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].count2016+'</td>';
						}
						
						 
						 
						
						if(result[i].perc2016 == null || parseFloat(result[i].perc2016) == parseFloat("0.00") || result[i].perc2016 == ""){
							str+='<td> - </td>';
						}else{
							var colorStatus = getColorCodeByStatus(result[i].levelPerformanceType)
							str+='<td style="color:'+colorStatus+';">'+result[i].perc2016+'</td>';
							if(parseFloat(result[i].perc2016) > parseFloat("100.00")){
								buildVrygdCount = buildVrygdCount+1;
							}else if(parseFloat(result[i].perc2016) >=  parseFloat("90.00") && parseFloat(result[i].perc2016)<  parseFloat("100.00")){
								buildGdCount = buildGdCount+1;
							}else if(parseFloat(result[i].perc2016) >=  parseFloat("80.00") && parseFloat(result[i].perc2016) <  parseFloat("90.00")){
								buildOkCount = buildOkCount+1;
							}else if(parseFloat(result[i].perc2016) >=  parseFloat("60.00") && parseFloat(result[i].perc2016) <  parseFloat("80.00")){
								buildPoorCount = buildPoorCount+1;
							}else{
								buildVeryPoorCount = buildVeryPoorCount+1;
							}
						}
						
						if(result[i].renewalPerc == null || result[i].renewalPerc == 0){
							str+='<td> - </td>';
						}else{
							if(result[i].renewalCount == null || result[i].renewalCount == 0){
							str+='<td>'+result[i].renewalCount+'</td>';
							}else{
								str+='<td>'+result[i].renewalCount+'</td>';
							}
						}
						if(result[i].renewalPerc == null || result[i].renewalPerc == 0 ){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].renewalPerc+'</td>';
						}
						
						if(result[i].newPerc == null || result[i].newPerc == 0){
							str+='<td> - </td>';
						}else{
							if(result[i].newCount == null || result[i].newCount == 0){
								str+='<td>'+result[i].newCount+'</td>';
							}else{
								str+='<td>'+result[i].newCount+'</td>';
							}
						}
						if(result[i].newPerc == null || result[i].newPerc == 0 ){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].newPerc+'</td>';
						}
						
						if(type == "total"){
							if(result[i].count2016Today == null || result[i].count2016Today == 0){
								str+='<td> - </td>';
							}else{
								str+='<td>'+result[i].count2016Today+'</td>';
							}
						}  
						
						if(result[i].mapPowerCount == null || result[i].mapPowerCount == 0){
							str+='<td> - </td>';
						}else{
							str+='<td>'+result[i].mapPowerCount+'</td>';
						}
						
						
						
						str+='</tr>';
					}
				}
				str+='</tbody>';
			str+='</table>';
			str+='</div>';
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
		$('#constituencyWise2016DataTableId_length').css("margin-bottom","10px");
		
		
		$('#constituencyWise2016DataTableId_length').append('<span class="filterCls" style="margin-left: 400px;"><label>District Filter : </label><select id="districtId" class="form-control" style="width: 180px;"></select></span>');		
		getDistricts(locationType);		
	}
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
		if(scopeType == "today"){  
			$( "#inlineRadio2" ).prop( "checked", true );  
		}else{
			$( "#inlineRadio1" ).prop( "checked", true );          
			         
		}
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
					str+='<th rowspan="2">PANCHAYAT</th>';
				}
                if(location == "booth"){
					str+='<th rowspan="2">PANCHAYAT</th>';
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
					str+='<td>'+result[i].mandalName+'&nbsp;&nbsp;Mandal</td> ';
				}else{
				if(result[i].panchayatName != null){
					str+='<td>'+result[i].mandalName+'&nbsp;&nbsp;Mandal</td> ';
				}else{
					str+='<td>'+result[i].localElectionBody+'&nbsp;&nbsp;Muncipality</td> ';
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
					
					/*if(result[i].localElectionBody == null || result[i].localElectionBody.trim().length < 1){
						str+='<td>-</td> ';
					}else{
						str+='<td>'+result[i].localElectionBody+'</td> ';
					}*/
					
					str+='<td>'+result[i].boothName+'</td>';   
				}
				
			   str+='<td>'+result[i].totalVoter+'</td>';  
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
 function buildLocationWiseRegConsWiseDistChngePerformanceCnts(result,type,matchedId,locationType){

	 $("#constituencyWise2016DetailsOvrVw").html("");
		var str1='';
		if(result !=null && result.length >0){
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
						str1+='<td ><div class="cCodeDiv" style="background-color:green;"/>'+buildVrygdCount+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:lightgreen;"/>'+buildGdCount+'</td>';
						str1+='<td><div class="cCodeDiv" style="background-color:darkolivegreen;"/>'+buildOkCount+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:orange;"/>'+buildPoorCount+'</td>';
						str1+='<td ><div class="cCodeDiv" style="background-color:#C43C35;"/>'+buildVeryPoorCount+'</td>';
						
					str1+='</tr>';
				str1+='</tbody>';
			str1+='</table>';
			$("#constituencyWise2016DetailsOvrVw").html(str1);
		}
  }
			
/* $(document).on("click",".clearSubTr",function(){
	$(".subDistTrCls").html("");
}); */
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
