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
<link rel="stylesheet" type="text/css" href="styles/bootstrapInHome/bootstrap.css"/> 
<link rel="stylesheet" type="text/css" href="css/style.css"> 
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
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
	.Previousmembercount td{width:20%;}
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
</style>
<body>
<div class="container">
		<div class="row-fluid" >
			<button type="button" class="btn btn-primary pull-right show2014DashBoard">click here to view 2014 Cadre DashBoard </button>
		</div>
		<!-- Title Row -->
		<div class="row-fluid m_top10" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">2016 Cadre Admin Dashboard</h3>
			</div>
		</div><!-- Title Row End-->
			<!-- Members Registered Previous Row -->
			
		<div class="row-fluid " id="PreviousmembersCount">
		
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" >
						<tbody>
						    <tr class="">
								<td>
									<h2>TS</h2>
									<img class="pull-right" src="images/TS.png" style="margin-top: -40px;">
								</td>
								<td><div id="tsConstiCountId"><h2>119</h2><p style="font-size:12px;">Registration Started Constituencies</p></div></td>
								<td><p><span class="text-red">2014 </span></p><div id="ts2014NewCountId"><h2 class="text_color f_26">794427</h2></div></td>
								<td><p><span class="text-blue">2016 </span></p><div id="ts2016CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><p><span class="text-orange">Total - [%] </span></p><div id="ts2016PrecCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								
							</tr>
							<tr class="">
								<td>
									<h2>AP</h2>
									<img class="pull-right" src="images/AP.png" style="margin-top: -40px;">
								</td>
								<td><div id="apConstiCountId"><h2>175</h2><p style="font-size:12px;">Registration Started Constituencies</p></div></td>
								<td><p><span class="text-red">2014 </span></p><div id="ap2014NewCountId"><h2 class="text_color f_26">4716975</h2></div></td>
								<td><p><span class="text-blue">2016 </span></p><div id="ap2016CountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
								<td><p><span class="text-orange">Total - [%] </span></p><div id="ap2016PrecCountId"><img class="imgStyle" src="images/icons/search.gif"/></div></td>
							</tr>
							
						</tbody>
					</table>
			</div>
		  
		</div><!-- Members Registered Previous Row -->
		<!-- Members Count Row -->
		<div class="row-fluid fadeInUp">
			<div class="span12 show-grid well well-small border-radius-0 mb-10">
				    <table class="table table-bordered border-radius-0 mb-0 membercount" style="background:#ffffff;">
						<tbody>
							<tr>
								<td style="background-color:#c8d7f4"><div id="todayRegisCount" class="row-fluid offset1"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<!--<td><div id="thisWeekRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>
								<td><div id="monthRegisCount"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>-->
								<td style="background-color:#eae798"><div id="totalRegisCount" class="row-fluid offset1"><img class="ajaxImgStyle" src="images/icons/search.gif"/></div></td>					
							</tr>
							<tr>
								<td class="indiEle">
									<div id="todayApTgRegisCount" class="row-fluid ">
										<img class="ajaxImgStyle" style="margin-left:130px;" src="images/icons/search.gif"/>
									</div>
								</td>
								
								<td class="indiEle">
									<div id="totalApTgRegisCount" class="row-fluid">
										<img class="ajaxImgStyle" style="margin-left:134px;" src="images/icons/search.gif"/>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
			</div>
		</div><!-- Members Count Row End -->
		<!----New code for constituency and district wise Start ----->
		<div class="row-fluid">
			<div class="span12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
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
					<input type="button" class="btn btn-mini btn-success apele aptsclass apdistcls" value="AP" name="constTargetBtn" id="apDistTargetComp" checked="checked">AP</input>
					<input type="button" class="btn btn-mini aptsclass tsdistclass" value="TS" name="constTargetBtn" id="tgDistTargetComp" >TS</input>
				</div>
				</div>
				<div id="districtWise2016Details"   class="m_top10"></div>
				<div id="leaderDataDiv2" class="" style="margin-top: -1px">
					
				</div>
			</div>
			</div>
			<div class="row-fluid">
			<div class="span12 show-grid well well-small border-radius-0 mb-10 fadeInUp " style="margin-left:0px;" >
				<h4> Constituency  Target Vs Registered Cadre  
				
				
				</h4>
				
				<div style="padding:5px;">
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="" name="compareD" value="today" checked="true" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> TODAY</span>
					<!--<input type="radio" class="typeRd" id="" name="compareD" value="asoftoday" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> AS OF TODAY</span>-->
					<input type="radio" class="typeRd radiobuttonSelectedConsWise" id="" name="compareD" value="total" style="margin-top:0px;"/>
					<span style="margin-right:10px;"> OVER ALL</span>
					
					<div class="btn-group pull-right">
					<input type="button" value="AP" class="btn btn-mini btn-success  apconsSele" name="distTargetBtn" id="apConsTargetComp" checked="checked">AP</input>
					<input type="button" value="TS" class="btn btn-mini  tsconsSele" name="distTargetBtn" id="tsConsTargetComp">TS</input>
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
<script type="text/javascript" src="js/bootstrap3/bootstrap.js"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
$('#fadeInDown').addClass('animated fadeInDown');
$('#fadeInLeft').addClass('animated fadeInLeft');
$('#fadeInRight').addClass('animated fadeInRight');
$('.fadeInUp').addClass('animated fadeInUp');
$('#fadeInUp1').addClass('animated fadeInUp');
$('#PreviousmembersCount').addClass('animated fadeInUp');
$('#membersCount').addClass('animated fadeInX');
 $(".show2014DashBoard").click(function(){
	window.location.replace('cadreDashBoardAction.action');
});
//dist
$(document).on("click",".apdistcls",function(){
	$("#tgDistTargetComp").removeAttr('checked');
	$("#tgDistTargetComp").removeClass("btn-success");
	$("#apDistTargetComp").addClass("btn-success");
	$("#apDistTargetComp").attr('checked', 'checked');
	 if($(".aptsclass").hasClass("btn-success")){
		 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"AP");
	 }else{
		 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"TS");
	 }
	
	
	
});
$(document).on("click",".tsdistclass",function(){
	$("#apDistTargetComp").removeAttr('checked');
	$("#apDistTargetComp").removeClass("btn-success");
	$("#tgDistTargetComp").attr('checked', 'checked');
	$("#tgDistTargetComp").addClass("btn-success");
	if($(".aptsclass").hasClass("btn-success")){
		 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"AP");
	 }else{
		 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"TS");
	 }
	
	
});
$(document).on("click",".radiobuttonSelectedWise",function(){
	 var selectedRadioButton = $(this).val();
	 if(selectedRadioButton == "today"){
		 if($(".aptsclass").hasClass("btn-success")){
			 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"AP");
		 }else{
			 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"TS");
		 }
	 }
	 if(selectedRadioButton == "total"){
		 if($(".aptsclass").hasClass("btn-success")){
			 get2016LocationWiseRegisteredCountsDistrictWise("total",3,"AP");
		 }else{
			 get2016LocationWiseRegisteredCountsDistrictWise("total",3,"TS");
		 }
	 }
	
});
//cons
$(document).on("click",".apconsSele",function(){
	$("#tsConsTargetComp").removeAttr('checked');
	$("#tsConsTargetComp").removeClass("btn-success");
	$("#apConsTargetComp").addClass("btn-success");
	$("#apConsTargetComp").attr('checked', 'checked');
	
		 if($(".aptsconsSele").hasClass("btn-success")){
			 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"AP");
		 }else{
			 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"TS");
		 }
	 
	
	
});
$(document).on("click",".tsconsSele",function(){
	$("#apConsTargetComp").removeAttr('checked');
	$("#apConsTargetComp").removeClass("btn-success");
	$("#tsConsTargetComp").attr('checked', 'checked');
	$("#tsConsTargetComp").addClass("btn-success");
	
	
		 if($(".aptsconsSele").hasClass("btn-success")){
			 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"AP");
		 }else{
			 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"TS");
		 }
	 
	
});
$(document).on("click",".radiobuttonSelectedConsWise",function(){
	 var selectedRadioButton = $(this).val();
	 if(selectedRadioButton == "today"){
		 if($(".aptsconsSele").hasClass("btn-success")){
			 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"AP");
		 }else{
			 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"TS");
		 }
	 }
	 if(selectedRadioButton == "total"){
		 if($(".aptsconsSele").hasClass("btn-success")){
			 get2016LocationWiseRegisteredCountsConstituencyWise("total",4,"AP");
		 }else{
			 get2016LocationWiseRegisteredCountsConstituencyWise("total",4,"TS");
		 }
	 }
	
});

 get2016LocationWiseRegisteredCounts("total");
 get2016LocationWiseRegisteredCounts("today");
 getDataSourceTypeWiseRegisteredDetails();
 get2016LocationWiseRegisteredCountsDistrictWise("today",3,"AP");
 get2016LocationWiseRegisteredCountsConstituencyWise("today",4,"AP");

function get2016LocationWiseRegisteredCounts(typeId){

			if(typeId == 'total'){
				$("#ap2016CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
				$("#ap2016PrecCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');
				$("#ts2016CountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');	
				$("#ts2016PrecCountId").html('<img class="imgStyle" src="images/icons/search.gif"/>');	
			}else if(typeId == 'today'){
				
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
						str+='<h2>'+result[0].count2016+'</h2>';
						str+='<div class="span12">';
						str+='<div class="span6" style="border-right: 1px solid #d3d3d3;">';
						str+='<p class="text-muted">Renewal<p>';
						str+='<p style="font-size: 18px;"><b>'+result[0].renewalCount+'</b></p>';
						str+='</div>';
						
						str+='<div class="span6">';
						str+='<p style="text-align: center;" class="text-muted">New</p>';
						str+='<p style="text-align: center;font-size: 18px;"><b>'+result[0].newCount+'</b></p>';
						str+='</div>';
						
						str+='</div>';
						
						
						str1+='<h2>'+result[1].count2016+'</h2>';
						str1+='<div class="span12">';
						str1+='<div class="span6" style="border-right: 1px solid #d3d3d3;">';
						str1+='<p class="text-muted">Renewal<p>';
						str1+='<p style="font-size: 18px;"><b>'+result[1].renewalCount+'</b></p>';
						str1+='</div>';
						
						str1+='<div class="span6">';
						str1+='<p style="text-align: center;" class="text-muted">New</p>';
						str1+='<p style="text-align: center;font-size: 18px;"><b>'+result[1].newCount+'</b></p>';
						str1+='</div>';
						
						str1+='</div>';
						
						
						$("#ap2016CountId").html(str)
						$("#ap2016PrecCountId").html('<h2>'+result[0].percentage+'</h2>');
					
						$("#ts2016CountId").html(str1)
						$("#ts2016PrecCountId").html('<h2>'+result[1].percentage+'</h2>');
						$("#totalApTgRegisCount").html('<div style="cursor:pointer;background-color:#EAE798" class="span6 mytooltip text-center" ><strong><span style="margin-left:10px;">AP  </span></strong><br><span >'+result[0].count2016+'</span><span class="text-skyblue"></span></div><div style="cursor:pointer;background-color:#EAE798;" class="span6 mytooltip text-center" ><strong><span style="margin-left:10px;">TELANGANA </span></strong> <br><span >'+result[1].count2016+'</span><span class="text-skyblue"> </span></div>');
						
					}else if(typeId == 'today'){
						$("#todayApTgRegisCount").html('<div style="cursor:pointer;background-color:#C8D7F4;" class="span6 mytooltip text-center" ><strong><span style="margin-left:10px;">  AP </span></strong><br><span style="margin-left:10px;"> '+result[0].count2016+'</span></div><div style="cursor:pointer;background-color:#C8D7F4;" class="span6 text-center mytooltip" ><strong><span style="margin-right:15px;"> TELANGANA </span></strong><br/><span style="margin-right:15px;"> '+result[1].count2016+'</span></div>');
						
					
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
					str+='<table class="table tableCadreDash span7">';
						str+='<thead>';
							str+='<th></th>';
							str+='<th>New</th>';
							str+='<th>Renewal</th>';
							str+='<th>Total</th>';
						str+='</thead>';
						str+='<tbody style="background:#fff;">';
						str+='<tr>';
							str+='<td>Tab</td>';
							str+='<td>'+result[0].tabNew+'</td>';
							str+='<td>'+result[0].tabRenewal+'</td>';
							str+='<td>'+result[0].tabTotal+'</td>';
						str+='</tr>';
						str+='<tr>';
							str+='<td>Web</td>';
							str+='<td>'+result[0].webNew+'</td>';
							str+='<td>'+result[0].webRenewal+'</td>';
							str+='<td>'+result[0].webTotal+'</td>';
						str+='</tr>';
						str+='<tr>';
							str+='<td>Partyoffice(Hyd)</td>';
							str+='<td>'+result[0].partyOfcHydNew+'</td>';
							str+='<td>'+result[0].partyOfcHydRenewal+'</td>';
							str+='<td>'+result[0].partyOfcHydTotal+'</td>';
						str+='</tr>';
						str+='<tr>';
							str+='<td>Partyoffice(Vij)</td>';
							str+='<td>'+result[0].partyOfcVijNew+'</td>';
							str+='<td>'+result[0].partyOfcVijRenewal+'</td>';
							str+='<td>'+result[0].partyOfcVijTotal+'</td>';
						str+='</tr>';
						str+='<tr>';
							str+='<td>Online</td>';
							str+='<td>'+result[0].onlineNew+'</td>';
							str+='<td>'+result[0].onlineRenewal+'</td>';
							str+='<td>'+result[0].onlineTotal+'</td>';
						str+='</tr>';
						str+='</tbody>';
					str+='</table>';

					   str+='<div class="span1">';
					   str+='<img src="images/icons/arrowBrace.png" style="margin-top: 100px; margin-left: 44px;">';
					   str+='</div>';
					   str+='<div class="span3" style="margin-top: 100px; margin-left: 45px;">';
					   str+='<h2 class="f_26">'+todayCount+'</h2>';
					   str+='<p>Members Registered <br>';
					   str+='<span style="font-weight:bold;">Today</span>';
					   str+='</p> </div>';
					  
					   str+='</td>';
					str+='</div>';
					
					//total
					var totalCount = result[1].tabTotal+result[1].webTotal+result[1].partyOfcHydTotal+result[1].partyOfcVijTotal+result[1].onlineTotal;
					str1+='<table class="table tableCadreDash span7">';
						str1+='<thead>';
							str1+='<th></th>';
							str1+='<th>New</th>';
							str1+='<th>Renewal</th>';
							str1+='<th>Total</th>';
						str1+='</thead>';
						str1+='<tbody style="background:#fff;">';
						str1+='<tr>';
							str1+='<td>Tab</td>';
							str1+='<td>'+result[1].tabNew+'</td>';
							str1+='<td>'+result[1].tabRenewal+'</td>';
							str1+='<td>'+result[1].tabTotal+'</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Web</td>';
							str1+='<td>'+result[1].webNew+'</td>';
							str1+='<td>'+result[1].webRenewal+'</td>';
							str1+='<td>'+result[1].webTotal+'</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Partyoffice(Hyd)</td>';
							str1+='<td>'+result[1].partyOfcHydNew+'</td>';
							str1+='<td>'+result[1].partyOfcHydRenewal+'</td>';
							str1+='<td>'+result[1].partyOfcHydTotal+'</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Partyoffice(Vij)</td>';
							str1+='<td>'+result[1].partyOfcVijNew+'</td>';
							str1+='<td>'+result[1].partyOfcVijRenewal+'</td>';
							str1+='<td>'+result[1].partyOfcVijTotal+'</td>';
						str1+='</tr>';
						str1+='<tr>';
							str1+='<td>Online</td>';
							str1+='<td>'+result[1].onlineNew+'</td>';
							str1+='<td>'+result[1].onlineRenewal+'</td>';
							str1+='<td>'+result[1].onlineTotal+'</td>';
						str1+='</tr>';
						str1+='</tbody>';
					str1+='</table>';

					   str1+='<div class="span1">';
					   str1+='<img src="images/icons/arrowBrace.png" style="margin-top: 100px; margin-left: 49px;">';
					   str1+='</div>';
					   str1+='<div class="span3" style="margin-top: 100px; margin-left: 44px;">';
					   str1+='<h2 class="f_26">'+totalCount+'</h2>';
					   str1+='<p>Members Registered <br>';
					   str1+='<span style="font-weight:bold;">Total</span>';
					   str1+='</p> </div>';
					  
					   str1+='</td>';
					str1+='</div>';
					
					$("#todayRegisCount").html(str);
					$("#totalRegisCount").html(str1);
					
				}
		 });
	}
	
	function get2016LocationWiseRegisteredCountsConstituencyWise(type,locationScope,locationType){
		$("#constituencyWise2016Details").html('<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>');
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
		$("#districtWise2016Details").html('<img style="margin-left: 180px;margin-top: 101px;" id="ajaxImgStyle" src="images/icons/loading.gif"/>');
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
			str+='<table class="table table-bordered" id="districtWise2016DataTableId">';
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
					if(result[i].renewalCount == null || result[i].renewalCount == 0){
						str+='<td>'+result[i].renewalCount+'</td>';
					}else{
						str+='<td>'+result[i].renewalCount+'<small>('+result[i].renewalPerc+')</small></td>';
					}
					if(result[i].newCount == null || result[i].newCount == 0){
						str+='<td>'+result[i].newCount+'</td>';
					}else{
						str+='<td>'+result[i].newCount+'<small>('+result[i].newPerc+')</small></td>';
					}
					str+='<td>'+result[i].count2016+'</td>';
					str+='<td>'+result[i].perc2016+'</td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		}
		
		$("#districtWise2016Details").html(str);
		$("#districtWise2016DataTableId").dataTable({
			"order": [ 4, 'desc' ]
		});
	}
	   
	function build2016LocationWiseRegisteredCountsConstituencyWise(result){
		
		var str='';
		if(result !=null && result.length >0){
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
					if(result[i].renewalCount == null || result[i].renewalCount == 0){
						str+='<td>'+result[i].renewalCount+'</td>';
					}else{
						str+='<td>'+result[i].renewalCount+'<small>('+result[i].renewalPerc+')</small></td>';
					}
					if(result[i].newCount == null || result[i].newCount == 0){
						str+='<td>'+result[i].newCount+'</td>';
					}else{
						str+='<td>'+result[i].newCount+'<small>('+result[i].newPerc+')</small></td>';
					}
					str+='<td>'+result[i].count2016+'</td>';
					str+='<td>'+result[i].perc2016+'</td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		}
		
		$("#constituencyWise2016Details").html(str);
		$("#constituencyWise2016DataTableId").dataTable({
			"order": [ 4, 'desc' ]
		});
	}
</script>
</body>
</html>
