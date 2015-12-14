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
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Date/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Slick/slick.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/Slick/slick-theme.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/FancyBox/jquery.fancybox.css" rel="stylesheet" type="text/css">
<link href="dist/Slick/slick.css" rel="stylesheet" type="text/css">
<link href="dist/Slick/slick-theme.css" rel="stylesheet" type="text/css">
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
</style>
</head>

<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-sm-12 col-xs-12">
        	<div class="panel panel-default panel-custom">
            	<div style="padding:10px 15px;background:#ccc">
					<h4 class="panel-title"> ACTIVITIES DASHBOARD
						<span class="pull-right" >
							<div class="input-group col-md-12" style="margin-top:-8px">
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
                        <div class="panel-body pad_0"  >
							<div class="row">
								<div class="col-md-12">
									<div id="stateWiseViewDid"></div>
								</div>
							</div>
							<div class="table-responsive" id="alignmentWidth" style="margin-top:10px;">
				
							</div>
						</div>
                
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
<script src="dist/Slick/slick.js" type="text/javascript"></script>
<script type="text/javascript">
$(".select").dropkick();	
$(".panel-heading","click",function(){
	if($(this).find(".accordion-toggle,.PlusnMinusSign1,.accordion1-toggle,.accordion2-toggle,.accordion3-toggle").hasClass("collapsed")){
		$(this).parent().parent().find(".bod").removeClass("bod");
		$(this).addClass("bod")
	}else{
		$(this).removeClass("bod")
	}
});

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
//getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid');
//getActivityDetailsBySearchCriteria(1,'district','alignmentWidth');
$(".searchDateCls").val(" ");


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
		$(".villageDays").hide();
	}
});
$(document).on('click','.ranges li',function(){
    if($(this).html()!='Custom'){
		getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid');
		getActivityDetailsBySearchCriteria(1,'district','alignmentWidth');
	 }
  });
  
  $(document).on('click','.applyBtn',function(){
         getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid');
		getActivityDetailsBySearchCriteria(1,'district','alignmentWidth');
  });
//alert($(".getChildWidth9").width())

//$(".your-class").slick();
$(".villageDays").hide();
$(".btn-hover").click(function()
{
	$(".villageDays").toggle();
	
})

$(".fancybox").fancybox();

function getActivityNames(type)
{
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'" selected="true">'+result[i].name+'</option>');	
			}
			if(type == "onload"){
				getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid');
				getActivityDetailsBySearchCriteria(1,'district','alignmentWidth');
			}
		});
		
}

function buildResult(result)
{
	$('#stateWiseViewDid').html('');
	var str='';
	
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered bg_ff" >';
	str+='<tr class="text-center">';
	str+='<td class="pad_0" rowspan="2" style="box-sizing:none;"><img src="dist/activityDashboard/img/andhrap.jpg" class="img-responsive head-td" style="height:176px;" ></td>';
	var i=0;
		if(result.totalCount != null && result.totalCount >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.totalCount+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	
		if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].plannedCount+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
		if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].ivrcovered+'<small class="font-10 text-danger" title="IVR covered percentage from planned activities.">('+result.activityVoList[i].ivrcoveredPerc+'%)</small></h3><hr class="custom-hr"/>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	/*	if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].ivrcoveredPerc+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';*/
		if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].ivrNotPlanned+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
				str+='<td style="color:#a94442;"><h3 class="m_top20 m_bottom10" >'+result.activityVoList[i].ivrTotal+'<small class="font-10 text-danger" title="IVR covered percentage from total activities.">('+result.activityVoList[i].ivrTotalPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
				str+='<td style="color:#a94442;"><h3 class="m_top20 m_bottom10" >'+result.activityVoList[i].infoCellTotal+'<small class="font-10 text-danger" title="INFO cell covered percentage from total activities.">('+result.activityVoList[i].infoCellTotalPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].infoCellcovered+'<small class="font-10 text-danger" title="INFO Cell covered percentage from planned activities.">('+result.activityVoList[i].infoCellcoveredPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	/*	if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].infoCellcoveredPerc+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';*/
		if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].infoCellNotPlanned+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].whatsAppCovered+'<small class="font-10 text-danger" title="Whatsapp covered percentage from total INFO Cell covered activities.">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].whatsAppCoveredPerc+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';*/
		if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].imagesCount+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	
	
	str+='</tr>';
	str+='<tr>';

	str+='<td class="bg_ef text-center">TOTAL</td>';
	str+='<td class="bg_ef text-center">PLANNED</td>';
	
	str+='<td class="bg_ef text-center">IVR COVERED   <small>(PLANNED) </small></td>';
	//str+='<td class="bg_ef text-center">IVR COVERED %</td>';
	str+='<td class="bg_ef text-center"> IVR COVERED <small> (NOT-PLANNED)</small> </td>';
	str+='<td class="bg_ef text-center" style="color:#a94442;"> IVR TOTAL </td>';
	
	str+='<td class="bg_ef text-center"  style="color:#a94442;">INFO CELL TOTAL </td>';
	str+='<td class="bg_ef text-center">INFO CELL COVERED   <small>(PLANNED) </small> </td>';
	//str+='<td class="bg_ef text-center">INFO CELL COVERED %</td>';
	str+='<td class="bg_ef text-center">INFO CELL COVERED <small> (NOT-PLANNED)</small></td>';
	
	str+='<td class="bg_ef text-center">WHATSAPP IMAGES COVERED</td>';
	//str+='<td class="bg_ef text-center">WHATSAPP IMAGES COVERED %</td>';
	str+='<td class="bg_ef text-center">NO OF WHATSAPP IMAGES RECIEVED</td>';
	
	
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	$('#stateWiseViewDid').html(str);
	dynamicwidth();
}
function getActivityDetailsBySearchCriteria(locationId,searchType,divId)
{
	
	//aria-expanded
	if(searchType == 'booth'){
		$(".villageNameAnchorCls").addClass('collapsed');
		$(".villageNameAnchorCls").removeClass('bod bod-b');
	}
	else if(searchType == 'village'){
		$(".PlusnMinusSignV").addClass('collapsed');
		//$(".PlusnMinusSignV").attr('aria-expanded','true');
		$(".villageListCls").html("");		
		$(".mandalNameAnchorCls").removeClass('bod bod-b');
	}
	else if(searchType == 'mandal'){
		$(".PlusnMinusSignM").addClass('collapsed');
		//$(".PlusnMinusSignM").attr('aria-expanded','true');
		
		$(".mandalsLsitCls").html("");		
		$(".constiNameAnchorCls").removeClass('bod bod-b');
	}
	else if(searchType == 'constituency'){
		$(".PlusnMinusSignd").addClass('collapsed');
		//$(".PlusnMinusSignd").attr('aria-expanded','true');
		$(".constiListCls").html("");		
		$(".constiListCls").parent().find("div").removeClass('bod bod-b');
	}
		
	
	 
	if(locationId >0)
	{
		var name = $('#ActivityList :selected').text();
		var actiivityLevelStr = $('#activityLevelList :selected').text();
		$('#headingDiv').html(''+name.toUpperCase()+' - '+actiivityLevelStr.toUpperCase()+' LEVEL');
		if(searchType != "state" && searchType != "district"){
			if(showHideResults(divId)){
				$("#"+divId).html("");
				return;
			}
		}
		
		$('html,body').animate({
			scrollTop: $("#"+divId).offset().top},
        'slow');
		
		var dates=$('.searchDateCls ').val();
		  var dateArray=dates.split("/");
		  var fromDateStr=dateArray[0];
		  var toDateStr=dateArray[1];
		
		 var activityScopeId = $("#ActivityList").val();
		 var activityLevelId = $("#activityLevelList").val();
		//console.log(dateArray.length);
			if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
			var jObj = {
			stateId:1,
			searchType:searchType,
			conditionType:"locationWiseResult",		
			startDate:fromDateStr,     //30-11-2015
			endDate:toDateStr,       //08-12-2015
			locationId:locationId,
			activityScopeId:activityScopeId,   //1
			activityLevelId:activityLevelId,   //1
			task:"getActivityDetailsBySearchCriteria"
			};
			$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
			
			$.ajax({
			  type:'GET',
			  url: 'getActivityDetailsBySearchCriteria.action',
			 data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				//console.log(result);
				if(result != null)
					if(searchType == 'state')
						buildResult(result);
					else if(searchType == 'district')
						buildsLocationsResult(result,divId);
					else if(searchType == 'constituency')
						buildConstituencyResult(result,divId,locationId);
					else if(searchType == 'mandal')
						buildMandalResult(result,divId,locationId);
					else if(searchType == 'village')
						buildVillageResult(result,divId,locationId);
			});
	}		
	  
}
//vvv

function buildVillageResult(result,divId,locationId)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading panel-village villageNameAnchorCls bg_ef" role="tab" id="headingOneLevelMandal1'+i+'">';
			str+='<span role="button" onclick="getActivityDetailsBySearchCriteria(0,\'booth\',\'\');">';
			
				str+='<table class="table table-col table-condensed"  style="display:inline;color:#333;" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='<tr class="bg_ef">';
				}
				else
				{
					str+='<tr>';
				}
					str+='<td style="width:180px;">'+result.activityVoList[i].name+'</td>';
					if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
						str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
					else
						str+='<td class="dynChildWidth aligncenter"> - </td>';
					if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
							str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
					else
						str+='<td class="dynChildWidth2 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
							str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth3 aligncenter"> - </td>';
				/*	if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
							str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
					else
						str+='<td class="dynChildWidth2"> - </td>';*/
					if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
							str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
					else
						str+='<td class="dynChildWidth4 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
							str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
							str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
					
					if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
							str+='<td class="dynChildWidth7 aligncenter" >'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth7 aligncenter"> - </td>';
					/*if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
							str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
					else
						str+='<td class="dynChildWidth2"> - </td>';*/
					if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
							str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
					else
						str+='<td class="dynChildWidth8 aligncenter"> - </td>';
					if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
							str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth9 aligncenter"> - </td>';
					/*if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
							str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
					else
						str+='<td class="dynChildWidth2"> - </td>';*/
					if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
							str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
					else
						str+='<td class="dynChildWidth10 aligncenter"> - </td>';
					
					str+='</tr>';
				
				
				str+='</table>';
				str+='</span>';
		//	str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'village\','+result.activityVoList[i].id+',\'dayWisePanchayatInfo'+result.activityVoList[i].id+'\')">Day Wise</button>';
				str+='</div>';
					str+='</div>';
					str+='<div id="dayWisePanchayatInfo'+result.activityVoList[i].id+'" class="daywiseSCls"></div>';
				str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
}

//mmm
function buildMandalResult(result,divId,locationId)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading mandalNameAnchorCls bg_ef" role="tab" id="headingOneLevelMandal1'+i+'">';
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'village\',\'panchayatLevelId'+i+'\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignV collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			
				str+='<table class="table table-col table-condensed" style="display:inline" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal ==0 )
				{
					str+='<tr class="bg_ef">';
				} 
				else{
					str+='<tr>';
				}
				
				str+='<td style="width:185px;">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';
			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+')</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
			/*if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
				str+='<tr>';
				str+='</table>';
				str+='</a>';
				//str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.activityVoList[i].id+',\'dayWiseMandalInfo'+result.activityVoList[i].id+'\')">Day Wise</button>';
				str+='</div>';
				str+='<div id="collapseOne1LevelPanchayat1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevelMandal1'+i+'">';
					str+='<div id="panchayatLevelId'+i+'" class="villageListCls"></div>';
					str+='</div>';
					str+='<div id="dayWiseMandalInfo'+result.activityVoList[i].id+'"  class="daywiseSCls"></div>';
				str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
}
//ccc
function buildConstituencyResult(result,divId,locationId)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="accordion1Level1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading constiNameAnchorCls bg_ef" role="tab" id="headingOneLevel1'+i+'">';
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'mandal\',\'mandalLevelId'+i+'\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1Level1'+i+'" href="#collapseOne1LevelMandal1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			
				str+='<table class="table table-col table-condensed" style="display:inline" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal ==0)
				{
					str+='<tr class="bg_ef">';
				}
				else {
					str+='<tr>';
				}
				
				str+='<td style="width:185px;" class="removeDiv">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';

			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
			/*if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter" > - </td>';
			/*if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
				
				str+='<tr>';
				str+='</table>';
				str+='</a>';
				//str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'constituency\','+result.activityVoList[i].id+',\'dayWiseConstituencyInfo'+result.activityVoList[i].id+'\')" >Day Wise</button>';
				str+='</div>';
				
				str+='<div id="collapseOne1LevelMandal1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevel1'+i+'">';
				str+='<div id="mandalLevelId'+i+'" class="mandalsLsitCls"></div>';
				str+='</div>';
				str+='<div id="dayWiseConstituencyInfo'+result.activityVoList[i].id+'"  class="daywiseSCls"></div>';
				str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
}
/*
if(searchType == 'mandal'){
		$(".PlusnMinusSignM").addClass('collapsed');
		$(".mandalsLsitCls").html("");		
		$(".mandalsLsitCls").parent().find("div").removeClass('bod bod-b');
	}
*/
//dddd
function buildsLocationsResult(result,divId){
	
	$('#'+divId+'').html('');
	var str='';
	str+='<table class="table table-col" style="border:1px solid #ccc" >';
	str+='<caption class="cap-custom"><b>DISTRICT WISE - VILLAGE/WARD</b></caption>';
	str+='<tr class="font-12">';
	str+='<td class="dynWidth" style="width:230px">LOCATION NAME</td>';
	str+='<td class="getChildWidth">TOTAL</td>';
	str+='<td class="getChildWidth2">PLANNED</td>';
	
	str+='<td class="getChildWidth3">IVR COVERED   <span style="font-size: 11px;">(PLANNED) </span></td>';
	//str+='<td class="getChildWidth3">IVR COVERED %</td>';
	str+='<td class="getChildWidth4"> IVR COVERED (NOT-PLANNED) </td>';
	str+='<td class="getChildWidth5" style="color:#a94442;"> IVR TOTAL </td>';
	
	str+='<td class="getChildWidth6" style="color:#a94442;">INFO CELL TOTAL</td>';
	str+='<td class="getChildWidth7">INFO CELL COVERED  <span style="font-size: 11px;">(PLANNED) </span></td>';
	//str+='<td class="getChildWidth7">INFO CELL COVERED %</td>';
	str+='<td class="getChildWidth8">INFO CELL COVERED (NOT-PLANNED)</td>';
	
	
	str+='<td class="getChildWidth9">WHATSAPP IMAGES COVERED</td>';
	//str+='<td class="getChildWidth9">WHATSAPP IMAGES COVERED %</td>';
	str+='<td class="getChildWidth10">NO OF WHATSAPP IMAGES RECIEVED</td>';
	
	str+='</tr>';
		
    
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
		{
			str+='<tr>';
			str+='<td colspan="14" class="pad_0">';
			str+='<div class="panel-group m_0" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading bg_ef" role="tab" id="headingOneLevel1'+i+'">';
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'constituencyLevelId'+i+'\');" class="accordion'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOneLevel1'+i+'" aria-expanded="true" aria-controls="collapseOneLevel1'+i+'">';
			
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
			{
				str+='<tr class="bg_ef">';
			}
			else {
				str+='<tr>';
			}
			str+='<td style="width:190px;">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';
			
			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
			/*if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
			

			str+='</tr>';
			str+='</table>';
			str+='</a>';
			//str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			//str+='<button class="btn btn-custom btn-hover btn-xs " href="javascript:{getDaywiseInfo(\'district\','+result.activityVoList[i].id+',\'dayWiseInfo'+result.activityVoList[i].id+'\');}">Day Wise</button>';
			str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'district\','+result.activityVoList[i].id+',\'dayWiseInfo'+result.activityVoList[i].id+'\')">Day Wise</button>';
			str+='</div>';			
			str+='<div id="constituencyLevelId'+i+'" class="constiListCls"> </div>';
			str+='<div id="collapseOneLevel1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevel1'+i+'">';
		
		str+='</div>';
		str+='</div>';
		str+='<div id="dayWiseInfo'+result.activityVoList[i].id+'" class="daywiseSCls">';
		str+='</div>';
		
		str+='</td>';
		str+='</tr>';
		}		
	}

	str+='</table>';
	$('#'+divId+'').html(str);
	dynamicwidth();

}
function dynamicwidth()
{
	$(".dynChildWidth10").css("width",$(".getChildWidth10").width());
	$(".dynChildWidth9").css("width",$(".getChildWidth9").width()+15);
	$(".dynChildWidth8").css("width",$(".getChildWidth8").width()+15);
	$(".dynChildWidth7").css("width",$(".getChildWidth7").width()+15);
	$(".dynChildWidth6").css("width",$(".getChildWidth6").width()+15);
	$(".dynChildWidth5").css("width",$(".getChildWidth5").width()+15);
	$(".dynChildWidth4").css("width",$(".getChildWidth4").width()+15);
	$(".dynChildWidth3").css("width",$(".getChildWidth3").width()+15);
	$(".dynChildWidth2").css("width",$(".getChildWidth2").width()+15);
	$(".dynChildWidth").css("width",$(".getChildWidth").width()+15);
}

function getDaywiseInfo(searchType,locationId,divId)
{
	$(".daywiseSCls").html("");	
	var isOpened = $("#"+divId+"").hasClass("opened");	
	console.log(isOpened);
	if(!isOpened)
	{	
		$(".daywiseSCls").removeClass("opened");
		
			$('html,body').animate({
				scrollTop: $("#"+divId).offset().top},
				'slow');
			if(searchType == 'district'){
				$(".PlusnMinusSignd").addClass('collapsed');
				$(".constiListCls").html("");	
				$(".constiListCls").parent().find("div").removeClass('bod bod-b');
			}
			else if(searchType == 'constituency'){
				$(".PlusnMinusSignM").addClass('collapsed');
				$(".mandalsLsitCls").html("");		
				$(".constiNameAnchorCls").removeClass('bod bod-b');
			}
			else if(searchType == 'mandal'){
				$(".PlusnMinusSignV").addClass('collapsed');
				$(".villageListCls").html("");		
				$(".mandalNameAnchorCls").removeClass('bod bod-b');
			}
			else if(searchType == 'village'){
				$(".villageNameAnchorCls").addClass('collapsed');
			}
			
			if(showHideResults(divId)){
				$("#"+divId).html("");
				//return;
			}
				
			  var dates=$('.searchDateCls ').val();
			  var dateArray=dates.split("/");
			  var fromDateStr=dateArray[0];
			  var toDateStr=dateArray[1];
			
			 var activityScopeId = $("#ActivityList").val();
			 var activityLevelId = $("#activityLevelList").val();
			 if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
				var jObj = {
				stateId:1,
				searchType:searchType,
				conditionType:"daywiseResult",
				locationId:locationId,
				activityScopeId:activityScopeId,   //1
				activityLevelId:activityLevelId,   //1
				startDate:fromDateStr,   //30-11-2015
				endDate:toDateStr,     //08-12-2015
				task:"getActivityDetailsBySearchCriteria"
				};
					$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
				
				$.ajax({
				  type:'GET',
				  url: 'getActivityDetailsBySearchCriteria.action',
				 data : {task:JSON.stringify(jObj)} ,
				}).done(function(result){
					//console.log(result);
					$("#"+divId+"").addClass("opened");
					if(result != null){
						buildDayWiseResults(result,divId,jObj);
					}
				});
	}else
		$(".daywiseSCls").removeClass("opened");	
}

function buildDayWiseResults(result,divId,jObj)
{
	var str='';
	
	str+='<div>';
	str+='<ul class="villageDays getwidthForRes">';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		
		for(var i in result.activityVoList)
		{
			str+='<li>';
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			str+='<tr>';
			str+='<td style="width:175px;"><span class="days" style="border-radius: 20px;  text-shadow: 1px 1px rgba(0, 0, 0, 0.5); font-size: 12px; padding: 6px; line-height: 14px;">'+result.activityVoList[i].name+'</span></td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount>0)
				str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';
			
			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter"  style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'</td>';
			else
				str+='<td class="dynChildWidth5 aligncenter"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
			str+='<td class="dynChildWidth6 aligncenter"  style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'</td>';
		  else
			str+='<td class="dynChildWidth6 aligncenter"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter"> - </td>';
			/*if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth7">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth7"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
			/*if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
			str+='</tr>';
			str+='</table>';
			/*var regularExp = /\((.*)\)/;
			var day = result.activityVoList[i].name.match(regularExp)[1];
			str+='<ul class="slick-training slick'+day.trim()+'" id="'+divId+'slick'+day.trim()+'" style="display:none;">';
		
			str+='</ul>';*/

			str+='</li>';
		}
	}
	str+='</ul>';
	str+='</div>';
	$('#'+divId+'').html(str);
	dynamicwidth();
	var getWidth=$(".getwidthForRes").width()-80;
	var responsive=$("#alignmentWidth").width();
	$(".slick-training").css("width",getWidth);
	$('.slick-training').slick({
      dots: false,
      slide: 'li',
      infinite: false,
      speed: 300,
      variableWidth: true,
      });
	  
	  $(".slick-training li").click(function(){
	$(".slick-training-modal").css("width","90%");
	$('.slick-training-modal').slick({
	  dots: false,
	  slide: 'li',
	  infinite: false,
	  speed: 300,
	  variableWidth: true,
	  });
	$('.slider-for').slick({
	  slidesToShow: 1,
	  slidesToScroll: 1,
	  slide: 'li',
	  arrows: false,
	  fade: true,
	  asNavFor: '.slider-nav'
	});
	$('.slider-nav').slick({
	  slidesToShow: 3,
	  slidesToScroll: 1,
	  slide: 'li',
	  asNavFor: '.slider-for',
	  dots: false,
	  centerMode: true,
	  focusOnSelect: true,
	  variableWidth: true
	});
	$('.slider-nav li:first-child').trigger('click');
})
	
	//getEventDocuments(divId,jObj);
}

function getDetails(){
	getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid');
	getActivityDetailsBySearchCriteria(1,'district','alignmentWidth');
}

function getEventDocuments(divId,jObj)
{
	
	 var activityId = $("#ActivityList").val();
		var jObj = {
		activityId:jObj.activityScopeId,
		locationScope:jObj.searchType,
		locationValue:jObj.locationId,		
		day:0,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWiseImages(result,divId);
		});
}
function buildDayWiseImages(result,divId)
{
	if(result != null)
	{
		for(var i in result)
	{
		var str ='';
			for(var j in result[i].subList)
			{
			str+='<li>';
			str+='<a class="fancybox" rel="group" href="activity/'+result[i].subList[j].path+'"><img src="activity/'+result[i].subList[j].path+'" alt="" style="height:25px" /></a>';
			str+='</li>';	
			}
		$("#"+divId+"slickDay-"+result[i].day).css("display","block");	
		$("#"+divId+"slickDay-"+result[i].day).html(str);
	}
	}
	
	
}

$(".tbtn").click(function(){
    $(".themeControll").toggleClass("active");
});

</script>
</body>
</html>

