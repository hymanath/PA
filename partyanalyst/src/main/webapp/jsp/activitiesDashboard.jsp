<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Activities Dashboard </title>
<link href="dist/activityDashboard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Slick/slick.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Slick/slick-theme.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/FancyBox/jquery.fancybox.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>

<style type="text/css">

.dk-selected 
{
	background:#ccc;
}
.table-col tr td
{
	border-top:0px !important;
	vertical-align:middle !important
}
.panel-group .panel-customtd
{
	border-radius:0px;
}
.panel-customtd .panel-heading , .panel-village
{
	padding:21px 5px;
}
.panel-village:hover  > .btn-hover{
	display:block
}
.panel-heading:hover > .btn-hover
{
	display:block
}
.btn-hover
{
	display:none;
	float:right;
	position:relative;
	margin-right:3px;
	top:-8px
}
.panel-customtd .panel-heading:hover , .panel-customtd .panel-heading:hover table
{
	background:#dcd0c4;
	border-radius:0px;
}
.panel-customtd .panel-heading:hover:after
{
	
}
.panel-group3 .panel-default .panel-body
{
	background:#efeae5
}
.slick-training
{
	background:#fff;
	height:30px;
	margin-left:50px;
}
.aligncenter{
	text-align:center;
}
.font-10
{
	font-size:10px;
}
.prev{
	width:60px !important;
}
.next{
	width:60px !important;
}
.slick-list{
	margin-left:30px;
}
</style>
</head>

<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-sm-12 col-xs-12">
        	<div class="panel panel-default panel-custom">
            	<div style="padding:10px 15px;background:#ccc">
					<h4 class="panel-title"> ACTIVITIES DASHBOARD
						<span class="pull-right col-md-12" >
							<div class="input-group col-md-3 pull-right" style="margin-top:-27px">
								<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								<input type="text" class="searchDateCls form-control" />
							</div>
						</span>
					</h4>
				</div>
                <div class="panel-body">
					<div class="panel panel-default panel-custom1 m_0">
                    	<div class="bg_66" style="padding:10px 15px;background:#663300;color:#fff">
                        	<h4 class="panel-title" id="headingDiv" style="font-weight:bold;">VILLAGE/WARD</h4>
                        </div>
						
						<div class="row">
							<div class="col-md-12">
								<div id="stateWiseViewDid"></div>
							</div>
						</div>
						<div class="row">
			<div class="col-md-12" >
				<div class="panel panel-default panel-custom">
					<div class="panel-heading ">
						<h4 class="panel-title">ACTIVITY RESPONSES</h4>
						<span class="pull-right" style="margin-top: -20px;"><a href="javascript:{}" class="btn btn-success btn-xs" id="hideData" style="display:none">Hide Data</a></span>
						<span class="pull-right" style="margin-top: -20px;"><a href="javascript:{}" class="btn btn-success btn-xs" id="showData" style="display:none" >Show Data</a></span></div>
					<div class="panel-body" id="qstnsDivId">
						<div class="row col-md-3 col-md-offset-0" id="">
								<label> Questions </label>
								<select id="questnsListId" class="form-control"></select>
							</div>
						
						<div class="row col-md-3 col-md-offset-0" id="reportTypeId">
							<label> Report Type </label>
								<select class="form-control" id="reportList" >
								<option value="1"> District</option>
								<option value="2"> Constituency</option>
								</select>
						</div>
						
						<div class="row col-md-3 col-md-offset-0" id="" style="margin-top: 25px">							
							<button id="searchId" class="btn btn-block btn-custom btn-success" type="button" onclick="getOptionDetailsForQuestion();">GET REPORT</button>
						</div>				
					</div> 
					
				</div>
			</div>
			
			<div class="col-md-12" id="responceDivId" style="display:none;">
				<div class="panel panel-default panel-custom">
					
			<div class="panel panel-default m_top10" id="questionDetailsDivId" style="display:none;">
							  <div class="panel-heading">
								<h4 class="panel-title"> QUESTION WISE REPORT </h4>
							  </div>
							  <div class="panel-body">
								<div id="questionWiseDetailsDiv"></div>
							  </div>
						</div>
							<div class="col-md-2" style="margin-bottom: 15px;">
							<button class="btn btn-success pull-right" id="lcnExcelBtn" onclick="generateExcel('responseTab')" style="float: right; text-align: right; border-left-width: 0px; margin-left: 2px; margin-right: -919px;">Export Excel</button>
							</div>
						<div class="row  m_top10" id="optnsCntDiv" style="display:none;padding: 25px;">
							<div class="col-md-12">
								<div class="bg_66" style="padding:10px 15px;background:#663300;color:#fff">
									<h4 class="panel-title" id="actvtyQstnOptnHdng" style="font-weight:bold;display:none;">ACTIVITY QUESTION OPTION RESPONCE</h4>
								</div>
							</div>
							<div class="col-md-12">
								<div  id="optionsCntId" >
								</div>
							</div>
						</div>
						</div>
						</div>
		</div>
		
                        <div class="panel-body pad_0 m_top20" id="nonBloodDonationDivId">
							<div class="row">
								<div class="col-md-12">
									<div class=" pad_10">
										<label class="radio-inline">
											<input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" id="locationWiseId" value="1">LOCATION WISE
										</label>
										<label class="radio-inline">
											<input type="radio" name="searchBasedOn" class="searchTypeCls" id="organizersWiseId" value="2">ORGANIZERS WISE
										</label>
										<button class="btn btn-success" id="lcnExcelBtn" onclick="generateExcel('districtWiseActivityDetailsExcelDivId')" style="margin-left: 650px; display:none;">Export Excel</button>
										<input type="hidden" id="searchTypeId" value="locationWiseId" />
									</div>
								</div>
							</div>
							<div class="table-responsive" id="alignmentWidth" style="margin-top:10px;">
				
							</div>
						</div>
						<button class="btn btn-success" id="excelBtn" onclick="generateExcel('districtWiseActivityDetailsExcelDivId')" style="margin-left: 950px; margin-top: 5px; margin-bottom: 5px;display:none;">Export Excel</button>
						<div id="locationWiseActivityDetailsDivId" style="display:none;"></div>
						<div id="districtWiseActivityDetailsExcelDivId" style="display:none;"></div>
						<div id="constWiseActivityDetailsExcelDivId" style="display:none;"></div>
						<div id="mandalWiseActivityDetailsExcelDivId" style="display:none;"></div>
						<div id="villageWiseActivityDetailsExcelDivId" style="display:none;"></div>
					</div>
				</div>
			</div>
		</div>
    </div>
</div>
<div class="themeControll">

  <div class="linkinner">
	<div class="row">
		<div class="col-md-10 col-md-offset-1 m_top10">
			<label>Activity Type</label>
            <s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="1" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" cssClass="input-block-level form-control"/>
		</div>
		<div class="col-md-10 col-md-offset-1">
			<label>Activity Level</label>
			<s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="1" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames('onchange');" cssClass="input-block-level form-control"/>
		</div>
		<div class="col-md-10 col-md-offset-1">
			<label> Activity Name </label>
			<select id="ActivityList" class="form-control">
				<option value="0"> Select Activity </option>
			</select>
		</div>
		<div class="col-md-10 col-md-offset-1 m_top20" style="margin-bottom:10px;">
			<button id="submitId" class="btn btn-block btn-success btn-sm btn-custom" onclick="getDetails();"> Get Details </button>
		</div>
	</div>
  </div>
  <p class="tbtn"> <i class="glyphicon glyphicon-filter"></i> FILTERS</p>
</div>

<!-------start popup build------------>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document"  id="slick-modal" style="width:90%">
    <div class="modal-content customModal">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">
       <div id="buildPoupupImage"></div>
      </div>
    </div>
  </div>
</div>
<!--------End Popup build----------->
<script type="text/javascript" src="js/activitiesResponces.js" ></script>
<script src="dist/activityDashboard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/activityDashboard/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/js/custom.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/moment.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script src="dist/activityDashboard/Slick/slick.js" type="text/javascript"></script>
<script src="dist/activityDashboard/FancyBox/jquery.fancybox.js" type="text/javascript"></script>
<script src="js/utility.js" type="text/javascript"></script>
<script src="dist/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<script src="js/Activities/activityDashboard.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/>
<script type="text/javascript">
jQuery( document ).ready(function( $ ) {
 $(".select").dropkick();
$(function () {
	var cb = function(start, end, label) {
	//console.log(start.toISOString(), end.toISOString(), label);
	//$('.searchDateCls').html(start.format('D MMMM, YYYY')- + ' / ' + end.format('D MMMM, YYYY'));
	//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
  }
  var optionSet1 = {
	startDate: moment().subtract(30, 'days'),
	endDate: moment(),
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [ moment().subtract(30, 'days'),moment()],
	   //'Lat 60 Days': [moment().subtract(60, 'days'),moment()],
	   //'Last 180 Days': [moment().subtract(6, 'months'),moment()],
	   //'Last 365 Days': [moment().subtract(1, 'year'),moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   //'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary',
	cancelClass: 'btn-small',	
	locale: {
		applyLabel: 'Submit',
		format: 'DD-MM-YYYY',
		separator: ' / ',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };
	//$('.searchDateCls').val(moment().format('DD-MM-YYYY') + ' / ' + moment().format('DD-MM-YYYY'));

  $('.searchDateCls').daterangepicker(optionSet1, cb);

 // $('.searchDateCls').on('show.daterangepicker', function() { console.log("show event fired"); });
  //$('.searchDateCls').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  getActivityNames('onload');
//getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
//getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
$(".searchDateCls").val(" ");


}); 
});
	var GlobalPopupScope;
	var GlobalPopuplocation;

$(".panel-heading","click",function(){
	if($(this).find(".accordion-toggle,.PlusnMinusSign1,.accordion1-toggle,.accordion2-toggle,.accordion3-toggle").hasClass("collapsed")){
		$(this).parent().parent().find(".bod").removeClass("bod");
		$(this).addClass("bod")
	}else{
		$(this).removeClass("bod")
	}
});
$(".panel-headingModal").click(function(){
	if($(this).find(".accordionmodal-toggle").hasClass("collapsed")){
		$(this).parent().parent().find(".bodM").removeClass("bodM");
		$(this).addClass("bodM")
	}else{
		$(this).removeClass("bodM");
	}
});

	  


$(document).on('click', '.panel-heading', function(){
	//alert(2);
	if($(this).find(".accordion-toggle,.accordion1-toggle,.accordion2-toggle,.accordion3-toggle").hasClass("collapsed")){
		$(this).parent().parent().find(".bod").removeClass("bod");
		$(this).parent().parent().find(".bod").removeClass("bod-b");
		$(this).removeClass("bod");
		$(this).removeClass("bod-b");
		
	}else{
		$(this).addClass("bod")
		$(this).addClass("bod-b");
		//$(".villageDays").hide();
	}
});
$(".panel-headingModal").click(function(){
	if($(this).find(".accordionmodal-toggle").hasClass("collapsed")){
		$(this).parent().parent().find(".bodM").removeClass("bodM");
		$(this).addClass("bodM")
	}else{
		$(this).removeClass("bodM");
	}
});

		
$(document).on('click','.ranges li',function(){
    if($(this).html()!='Custom'){
		isAlreadyBuild = false;
		getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
		getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
	 }
  });
  
  $(document).on('click','.applyBtn',function(){
		isAlreadyBuild = false;
         getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
		getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
  });
//alert($(".getChildWidth9").width())

//$(".your-class").slick();
$(".villageDays").hide();
$(".btn-hover").click(function()
{
	$(".villageDays").toggle();
	
})

$(".fancybox").fancybox();

function generateExcel(divId){
	tableToExcel(divId, 'Location Wise Report');
}
function getActivityQuestionaryOptionsByActivityDate()
{
	alert('aa')
	var activityDate = "05-01-2016";
	var day = 1;
	var jobj = {
		activityDate : activityDate,
		day:day,
		activityScopeId:1,
		task:""
	}
	 $.ajax({
     type : "GET",
     url  : "getActivityQuestionaryOptionsByActivityDateAction.action",
     data : {task:JSON.stringify(jobj)}
     }).done(function(result){

	 })
	
}

</script>
<script>
	
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
<script>
getActivityQuestionaryOptionsByActivityDate();
</script>
</body>
</html>