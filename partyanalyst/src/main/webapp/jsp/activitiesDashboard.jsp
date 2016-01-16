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
                        <div class="panel-body pad_0"  >
							<div class="row">
								<div class="col-md-12">
									<div id="stateWiseViewDid"></div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">
									<div class=" pad_10">
										<label class="radio-inline">
											<input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" id="locationWiseId" value="1">LOCATION WISE
										</label>
										<label class="radio-inline">
											<input type="radio" name="searchBasedOn" class="searchTypeCls" id="organizersWiseId" value="2">ORGANIZERS WISE
										</label>
										<input type="hidden" id="searchTypeId" value="locationWiseId" />
									</div>
								</div>
							</div>
							<div class="table-responsive" id="alignmentWidth" style="margin-top:10px;">
				
							</div>
							
							
							<div id="locationWiseActivityDetailsDivId"></div>
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
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			}
			$('#ActivityList').val(1);
			if(type == "onload"){
				//getTeamLeadersByActivityScope();
				getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
				getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
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
function getActivityDetailsBySearchCriteria(locationId,searchType,divId,teamSearchType,radioSearch,index)
{
	//alert(radioSearch);
	//aria-expanded
	if(searchType == 'booth'){
		$(".villageNameAnchorCls").addClass('collapsed');
		$(".villageNameAnchorCls").removeClass('bod bod-b');
	}
	/*else if(searchType == 'village'){
		$(".PlusnMinusSignV").addClass('collapsed');
		//$(".PlusnMinusSignV").attr('aria-expanded','true');
		$(".villageListCls").html("");		
		$(".mandalNameAnchorCls").removeClass('bod bod-b');
	}*/
	/*else if(searchType == 'mandal'){
		$(".PlusnMinusSignM").addClass('collapsed');
		//$(".PlusnMinusSignM").attr('aria-expanded','true');
		
		$(".mandalsLsitCls").html("");		
		$(".constiNameAnchorCls").removeClass('bod bod-b');
	}*/
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
		 
		var teamLeaderId = 0;
		
		if(radioSearch == 'location'){
			if(teamSearchType == 'organizersWiseId'){
				if(searchType == 'district'){
					teamLeaderId = locationId;
				}
			}
		}
		 
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
			teamSearchType:teamSearchType,
			teamLeaderId:teamLeaderId,
			teamMemberId:0,
			radioSearch:radioSearch,
			districtId:0,
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
						buildsLocationsResult(result,divId,teamSearchType);
					else if(searchType == 'constituency')
						buildConstituencyResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'mandal' && index == 0)
						buildMandalResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'mandal' && index == 1)
						buildVillageResult(result,divId,locationId,index);
					else if(searchType == 'village')
						buildVillageResult(result,divId,locationId,index);
			});
	}		
	  
}

function getLeadersWiseActivityDetailsBySearchCriteria(locationId,searchType,divId,teamSearchType,radioSearch,index,districtId,leaderId,ledMemsearch)
{
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
	/*else if(searchType == 'constituency'){
		$(".PlusnMinusSignd").addClass('collapsed');
		//$(".PlusnMinusSignd").attr('aria-expanded','true');
		$(".constiListCls").html("");		
		$(".constiListCls").parent().find("div").removeClass('bod bod-b');
	}*/
		
	
	 
	if(locationId >0)
	{
		var name = $('#ActivityList :selected').text();
		var actiivityLevelStr = $('#activityLevelList :selected').text();
		$('#headingDiv').html(''+name.toUpperCase()+' - '+actiivityLevelStr.toUpperCase()+' LEVEL');
		//if(searchType != "state" && searchType != "district"){
			if(showHideResults(divId)){
				$("#"+divId).html("");
				return;
			}
		//}
		
		$('html,body').animate({
			scrollTop: $("#"+divId).offset().top},
        'slow');
		
		var dates=$('.searchDateCls ').val();
		  var dateArray=dates.split("/");
		  var fromDateStr=dateArray[0];
		  var toDateStr=dateArray[1];
		
		 var activityScopeId = $("#ActivityList").val();
		 var activityLevelId = $("#activityLevelList").val();
		 //alert(leaderId);
		var teamLeaderId = 0;
		var teamMemberId = 0;
		if(leaderId != 0){
			if(ledMemsearch == 'member')
				teamMemberId = leaderId;
			else
				teamLeaderId = leaderId;
		}
		else if(radioSearch == 'location'){
			if(teamSearchType == 'organizersWiseId'){
				teamLeaderId = locationId;
			}
		}
		if(index == 3){
			locationId = districtId;
		}
		 
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
			teamSearchType:teamSearchType,
			teamLeaderId:teamLeaderId,
			teamMemberId:teamMemberId,
			radioSearch:radioSearch,
			districtId:districtId,
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
					else if(searchType == 'district' && index == 0)
						buildsLocationsResult(result,divId,teamSearchType);
					else if(searchType == 'constituency' && index == 1)
						buildConstituencyResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'constituency' && index == 2)
						buildMandalResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'constituency' && index == 3)
						buildVillageResult(result,divId,locationId);
			});
	}		
	  
}
//vvv

function buildVillageResult(result,divId,locationId,index)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading panel-village villageNameAnchorCls" role="tab" id="headingOneLevelMandal1'+i+'">';
			if(index == 1){
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'village\',\'boothLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignV collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat11'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			else{
				str+='<span role="button" onclick="getActivityDetailsBySearchCriteria(0,\'booth\',\'\',\'locationWiseId\',\'location\',\'0\');">';
			}
			
				str+='<table class="table table-col table-condensed"  style="display:inline;color:#333;" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='<tr style="background:#F4A460">';
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
							str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
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
							str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
							str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
					
					if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
							str+='<td class="dynChildWidth7 aligncenter" >'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
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
							str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
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
				if(index == 1)
					str+='</a>';
				else
					str+='</span>';
		//	str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
		if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='';
				}
				else {
					if(index == 1){
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.activityVoList[i].id+',\'dayWiseMandalInfo1'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
					}
					else{
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'village\','+result.activityVoList[i].id+',\'dayWisePanchayatInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
					}
				}
				str+='</div>';
			/*str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'village\','+result.activityVoList[i].id+',\'dayWisePanchayatInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
				str+='</div>';*/
				str+='</div>';
				if(index == 1){
					str+='<div id="collapseOne1LevelPanchayat11'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevelMandal1'+i+'">';
					str+='<div id="boothLevelId'+i+'" class="villageListCls"></div>';
					str+='</div>';
					str+='<div id="dayWiseMandalInfo1'+result.activityVoList[i].id+'"  class="daywiseSCls"></div>';
				}
				else{	
					str+='<div id="dayWisePanchayatInfo'+result.activityVoList[i].id+'" class="daywiseSCls"></div>';
					}
				str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
	
}

//mmm
function buildMandalResult(result,divId,locationId,teamSearchType)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{			
				if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
				{
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading mandalNameAnchorCls" role="tab" id="headingOneLevelMandal1'+i+'">';
			if(teamSearchType == 'organizersWiseId'){
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'mandal\',\'panchayatLevelId'+i+'\',\'locationWiseId\',\'location\',\'1\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignV collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			else{
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'village\',\'panchayatLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignV collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			
			
				str+='<table class="table table-col table-condensed" style="display:inline" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0 )
				{
					str+='<tr style="background:#F4A460">';
				} 
				else{
					str+='<tr>';
				}
				
				str+='<td style="width:175px;">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';
			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+')</span></td>';
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
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
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
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
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
			//str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.activityVoList[i].id+',\'dayWiseMandalInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
			if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0 )
				{
					str+='';
				}
				else {
					if(teamSearchType == 'organizersWiseId'){
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'constituency\','+result.activityVoList[i].id+',\'dayWiseMandalInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
					}
					else{
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.activityVoList[i].id+',\'dayWiseMandalInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
					}
				}
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
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
	
}
//ccc
function buildConstituencyResult(result,divId,locationId,teamSearchType)
{	
	var str='';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="accordion1Level1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading constiNameAnchorCls" role="tab" id="headingOneLevel1'+i+'">';
			if(teamSearchType == 'organizersWiseId'){
				str+='<a role="button" onclick="getLeadersWiseActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'mandalLevelId'+i+'\',\'organizersWiseId\',\'location\',\'2\',\''+result.activityVoList[i].id+'\',\''+result.activityVoList[i].id+'\',\'member\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1Level1'+i+'" href="#collapseOne1LevelMandal1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			else{
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'mandal\',\'mandalLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1Level1'+i+'" href="#collapseOne1LevelMandal1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			str+='<table class="table table-col table-condensed" style="display:inline" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='<tr style="background:#F4A460">';
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
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
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
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
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
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
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
			//str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'constituency\','+result.activityVoList[i].id+',\'dayWiseConstituencyInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')" >Day Wise</button>';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='';
				}
				else {
					if(teamSearchType == 'organizersWiseId'){
						str+='';
					}
					else{
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'constituency\','+result.activityVoList[i].id+',\'dayWiseConstituencyInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')" >Day Wise</button>';
					}
				}
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

var isAlreadyBuild = false;
//dddd
function buildsLocationsResult(result,divId,teamSearchType){
	
	$('#'+divId+'').html('');
	var str='';
	if(!isAlreadyBuild){
		
		isAlreadyBuild = true;
		
		str+='<table class="table table-col" style="border:1px solid #ccc" >';
		if(teamSearchType == 'organizersWiseId')
			str+='<caption class="cap-custom"><b>GENERAL SECRATORY WISE - VILLAGE/WARD</b></caption>';
		else
			str+='<caption class="cap-custom"><b>DISTRICT WISE - VILLAGE/WARD</b></caption>';
		str+='<tr class="font-12">';
		if(teamSearchType == 'organizersWiseId')
			str+='<td class="dynWidth" style="width:200px">GENERAL SECRETORY</td>';
		else
			str+='<td class="dynWidth" style="width:200px">LOCATION NAME</td>';
			
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
	}
	
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
		{
			str+='<tr>';
			str+='<td colspan="14" class="pad_0">';
			str+='<div class="panel-group m_0" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading" role="tab" id="headingOneLevel1'+i+'">';
			if(teamSearchType == 'organizersWiseId'){
				str+='<a role="button" onclick="getLeadersWiseActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'constituencyLevelId'+i+'\',\'organizersWiseId\',\'location\',\'1\',\'0\',\'0\',\'\');" class="accordion'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOneLevel1'+i+'" aria-expanded="true" aria-controls="collapseOneLevel1'+i+'">';
			}
			else{
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'constituencyLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOneLevel1'+i+'" aria-expanded="true" aria-controls="collapseOneLevel1'+i+'">';
			}
			
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
			{
				str+='<tr style="background:#F4A460">';
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
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
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
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
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
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
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
			if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
			{
				str+='';
			}
			else {
				if(teamSearchType == 'organizersWiseId'){
					str+='';
				}
				else{
					str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'district\','+result.activityVoList[i].id+',\'dayWiseInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
				}
			}
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

function getDaywiseInfo(searchType,locationId,divId,locationName)
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
			/*else if(searchType == 'constituency'){
				$(".PlusnMinusSignM").addClass('collapsed');
				$(".mandalsLsitCls").html("");		
				$(".constiNameAnchorCls").removeClass('bod bod-b');
			}*/
			/*else if(searchType == 'mandal'){
				$(".PlusnMinusSignV").addClass('collapsed');
				$(".villageListCls").html("");		
				$(".mandalNameAnchorCls").removeClass('bod bod-b');
			}*/
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
				locationName:locationName,
				teamSearchType:"locationWiseId",
				teamLeaderId:0,
				teamMemberId:0,
				radioSearch:'location',
				districtId:0,
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
			var regularExp = /\((.*)\)/;
			var day = result.activityVoList[i].name.match(regularExp)[1];
			str+='<ul class="slick-training slick'+day.trim()+'" id="'+divId+'slick'+day.trim()+'" style="display:none;">';
		
			str+='</ul>';
			

			str+='</li>';
		}
	}
	str+='</ul>';
	str+='</div>';
	$('#'+divId+'').html(str);
	dynamicwidth();
	
	getEventDocuments(divId,jObj);

}

function getDetails(){
	isAlreadyBuild = false;
	getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
	getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
}

var globallocationScope;
var globallocationValue;
var globallocationName;
var globalActivityScope;
var globalImages;
function getEventDocuments(divId,Obj)
{
	//$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
	 var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	 var activityId = $("#ActivityList").val();
	  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
		var jObj = {
		activityId:Obj.activityScopeId,
		locationScope:Obj.searchType,
		locationValue:Obj.locationId,		
		day:0,
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		locationName:Obj.locationName,
		startIndex:0,
		maxIndex:0,
		task:"daywise"
		};
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			//$('#'+divId+'').html('');
			globallocationScope = '';
			globallocationValue = '';
			globallocationName = '';
			globalActivityScope ='';
			globallocationScope = jObj.locationScope;
			globallocationValue = jObj.locationValue;
			globallocationName = jObj.locationName;
			globalActivityScope = jObj.activityId;
			buildDayWiseImages(result,divId);
			
			});
}
var globalTotalImages;
function buildDayWiseImages(result,divId)
{
	
	globalPopupresult = "";
	globalPopupresult = result;
	if(result != null)
	{
		for(var i in result)
	{
	
		$("#"+divId+"slickDay-"+result[i].day).css("display","block");	
		 $("#"+divId+"slickDay-"+result[i].day).html('<img src="./images/Loading-data.gif" />');
		
			var str ='';
			for(var j in result[i].subList)
			{
				if(j < 20)
				{
					str+='<li>';
					str+='<img src="http://mytdp.com/activity_documents/' +result[i].subList[j].path+'" alt="" style="height:25px;cursor:pointer;" data-toggle="modal" class="Imagepopup"  dayattr="'+result[i].day+'" imgpath="'+result[i].subList[j].path+'"/>';
					str+='</li>';
				}
			
			}
		
		$("#"+divId+"slickDay-"+result[i].day).html(str);
		
	}
	globalTotalImages =0;
	globalTotalImages = result[0].totalResult;
	}
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
		
}

$(".tbtn").click(function(e){
    $(".themeControll").toggleClass("active");
	e.stopPropagation();
});
$(".linkinner").click(function(e){
	e.stopPropagation();
});

$(document).on("click", "body", function(){
	 $(".themeControll").removeClass("active");
});


$(document).on('click', '.Imagepopup', function(){	
   $("#myModal").modal("show");
   
   var str='';
   
    str+='<div class="row">';
			 str+='<div class="col-md-9">';
				 str+='<nav class="navbar navbar-default navbarCollapseCustom">';
					<!-- Brand and toggle get grouped for better mobile display -->
					 str+='<div class="navbar-header">';
					   str+='<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">';
						 str+='<span class="sr-only">Toggle navigation</span>';
						 str+='<span class="icon-bar"></span>';
						 str+='<span class="icon-bar"></span>';
						 str+='<span class="icon-bar"></span>';
					   str+='</button>';
					 str+='</div>';
					<!-- Collect the nav links, forms, and other content for toggling -->
					 str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
					  str+='<ul class="nav navbar-nav" id="popupDaysDiv">';
						/* str+='<li class="active"><a href="#">Day 1 <span class="sr-only">(current)</span></a></li>';
						 str+='<li><a href="#">Day 2</a></li>';
						 str+='<li><a href="#">Day 3</a></li>';
						 str+='</li>';*/
					   str+='</ul>';
					 str+='</div>';<!-- /.navbar-collapse -->
				 str+='</nav>';
				 str+='<div class="bg_cc pad_10" id="popupImages">';
					 /*str+='<ul class="slider-for">';
						 str+='<li><img src="http://mytdp.com/activity_documents  /Chrysanthemum.jpg"></li>';
						 str+='<li><img src="http://mytdp.com/activity_documents  /Desert.jpg"></li>';
						
					 str+='</ul>';
					 str+='<ul class="slider-nav">';
						 str+='<li><img src="http://mytdp.com/activity_documents  /Chrysanthemum.jpg"></li>';
						 str+='<li><img src="http://mytdp.com/activity_documents  /Desert.jpg"></li>';
					 str+='</ul>';*/
				 str+='</div>';
				str+=' <div id="paginationDivId"></div>';
			 str+='</div>';
			str+='<div class="col-md-3" style="background:#fff;box-shadow:0 2px 10px 0 rgba(0, 0, 0, 0.35);position:absolute;bottom:0px;right:0px;top:0px;padding:0px;overflow:scroll" id="locationsPopup">';
				/* str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
				   str+='<div class="panel panel-default panel-custommodal">';
					 str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModal">';
					   str+='<a role="button" class="accordionmodal-toggle" data-toggle="collapse" data-parent="#accordionModal" href="#collapseOneModal" aria-expanded="true" aria-controls="collapseOneModal">';
						 str+='<h4 class="panel-title">Vijayanagaram';
						 str+='</h4>';
					   str+='</a>';
					 str+='</div>';
					 str+='<div id="collapseOneModal" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOneModal">';
					   str+='<div class="panel-body pad_0">';
						  str+='<ul class="villageDaysModal">';
							 str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 2</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 3</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 4</a></li>';
						  str+='</ul>';
					   str+='</div>';
					 str+='</div>';
				   str+='</div>';
				   str+='<div class="panel panel-default panel-custommodal">';
					 str+='<div class="panel-heading panel-headingModal" role="tab" id="headingTwo">';
					 str+='<a class="collapsed accordionmodal-toggle" role="button" data-toggle="collapse" data-parent="#accordionModal" href="#collapseTwoModal" aria-expanded="false" aria-controls="collapseTwoModal">';
						 str+='<h4 class="panel-title">Srikakulam';
						 str+='</h4>';
					 str+='</a>';
					 str+='</div>';
					 str+='<div id="collapseTwoModal" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">';
					   str+='<div class="panel-body pad_0">';
						  str+='<ul class="villageDaysModal">';
							 str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 2</a> </li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 3</a> </li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 4</a> </li>';
						  str+='</ul>';
					   str+='</div>';
					 str+='</div>';
				   str+='</div>';
				   str+='<div class="panel panel-default panel-custommodal">';
					 str+='<div class="panel-heading panel-headingModal" role="tab" id="headingThreeModal">';
					 str+='<a class="collapsed accordionmodal-toggle" role="button" data-toggle="collapse" data-parent="#accordionModal" href="#collapseThreeModal" aria-expanded="false" aria-controls="collapseThreeModal">';
						 str+='<h4 class="panel-title">Khammam';	
						 str+='</h4>';
					str+='</a>';
					str+='</div>';
					str+='<div id="collapseThreeModal" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThreeModal">';
					 str+='<div class="panel-body pad_0">';
						 str+='<ul class="villageDaysModal">';
							str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							str+='<li><span class="line"/></span><a href="#">Constituency 2</a></li>';
							str+='<li><span class="line"/></span><a href="#">Constituency 3</a></li>';
							str+='<li><span class="line"/></span><a href="#">Constituency 4</a></li>';
						 str+='</ul>';
					  str+='</div>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';*/
			str+='</div>';
		str+='</div>';
		
		$("#buildPoupupImage").html(str);
		$(".panel-headingModal").click(function(){
			if($(this).find(".accordionmodal-toggle").hasClass("collapsed")){
				$(this).parent().parent().find(".bodM").removeClass("bodM");
				$(this).addClass("bodM")
			}else{
				$(this).removeClass("bodM");
			}
		});
	
buildDayWiseImagesForPopup(globalPopupresult,$(this).attr("imgpath"),$(this).attr("dayattr"));
buildLocationsForPopup(globallocationScope,globallocationValue,globalActivityScope);


});
	function getAvailableDatesForPopup(locationScope,locationValue)
	{
	  var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	  var activityScopeId = $("#ActivityList").val();
	 if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
	 var locationScope ="state";
	 var locationValue =0;
		var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildAvailableDatesPopup1(result);
		});
}



function getLocationsForPopupcli(locationScope,locationValue,divId,subLevel)
{
	
	  var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	 var activityScopeId = $("#ActivityList").val();
	 if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
	  var locationScope =locationScope;
	 var locationValue =locationValue;
		var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getLocationsHierarchyForEventAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildSublevelLocationsForPopup1(result,divId,subLevel)
		});
}

function getAvailableDatesForPopupcli(locationScope,locationValue)
{
	 $("#popupDaysDiv").html('<img src="./images/Loading-data.gif" />');
	  var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	 var activityScopeId = $("#ActivityList").val();
	 if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
	  var locationScope =locationScope;
	 var locationValue =locationValue;
		var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildAvailableDatesPopup1(result,jObj);
		});
}

function buildAvailableDatesPopup1(result,jObj)
{
	var str ='';
	for(var i in result)
	{
		
		
		str+='<li class="daysCls" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
	}
	$("#popupDaysDiv").html(str);
	
			GlobalPopupScope = jObj.locationScope;
				GlobalPopuplocation =jObj.locationValue;
				
	
}


function buildLocationsForPopup(locationScope,locationValue,ActivityScope)
{
	$("#myModalLabel").html(''+globallocationName+'');
	var subLeveldivId = '';
	if(locationScope == "state")
	subLeveldivId = "popupstateConstituencies"+locationValue;
		if(locationScope == "district")
		subLeveldivId = "popupdistConstituencies"+locationValue;
		if(locationScope == "constituency")
		subLeveldivId = "constiSubLevel"+locationValue;
		if(locationScope == "mandal")
		subLeveldivId= "mandalSubLevel"+locationValue;
		var str = '';
		str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
			
					   str+='<div class="panel panel-default panel-custommodal m_0">';
						  str+='<div class="panel-heading panel-headingModal popupLevel" role="tab" id="headinglevel'+locationValue+'Modal" attr='+locationValue+'>';
						  str+='<a role="button" class="accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModal" href="#collapselevel'+locationValue+'Modal" aria-expanded="true" aria-controls="collapselevel'+globallocationValue+'Modal">';
							 str+='<h4 class="panel-title popupTitle" attr="'+globallocationName+'">'+globallocationName+'('+globalTotalImages+')';
							 str+='</h4>';
						   str+='</a>';
						 str+='</div>';
						 str+='<div id="collapselevel'+locationValue+'Modal" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headinglevel'+locationValue+'Modal">';
						   str+='<div class="panel-body pad_0">';
							  str+='<div class="" id="'+subLeveldivId+'" style="margin-left:10px;">';
								// str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							str+='</div>';
						   str+='</div>';
						 str+='</div>';
						 str+='</div>';
					
			  str+='</div>';
				$("#locationsPopup").html(str);
				
			
}
var index=0;
var index1=0;
function buildSublevelLocationsForPopup1(resultList,divId,subLevel)
{

	var str = '';
	var result = resultList.locationsList;
	index1++;
	str+='<div class="panel-group" id="accordionsub'+index1+'" role="tablist" aria-multiselectable="true" style="margin:0px">';
	for(var i in result)
	{
		index++;
		//index1++;
	  str+='<div class="panel panel-default">';
		str+='<div class="panel-heading" role="tab" id="headingOne'+index+'">';
		str+='<a role="button" data-toggle="collapse" attr="'+result[i].id+'" class="'+subLevel+'" data-parent="#accordionsub'+index1+'" href="#collapseOne'+index+'" aria-expanded="true" aria-controls="collapseOne'+index+'">';
			str+='<h4 class="panel-title popupTitle" attr="'+result[i].name+'"> '+result[i].name+'('+result[i].count+')</h4>';
		str+='</a>';
		str+='</div>';
		str+='<div id="collapseOne'+index+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+index+'">';
		  str+='<div class="panel-body">';
			str+='<div class="subLevelCls" id="'+subLevel+''+result[i].id+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#"+divId).html(str);
}
$(document).on('click','.popupLevel',function(){
	
		callPopupLoad(globallocationScope,globallocationValue,globalActivityScope);
	});

  $(document).on('click','.distSubLevel',function(){
	
		var id = $(this).attr("attr");
		var divId = "popupdistConstituencies"+id;
		getLocationsForPopupcli("district",id,divId,"constiSubLevel");
		getAvailableDatesForPopupcli("district",id);
		GlobalPopupScope = "district";
		GlobalPopuplocation =id;
		 getEventDocumentsForPopup("district",id,0,0);
  });
  
    $(document).on('click','.constiSubLevel',function(){
		GlobalPopupScope = "";
		GlobalPopuplocation = "";
		var id = $(this).attr("attr");
		var divId = "constiSubLevel"+id;
		getLocationsForPopupcli("constituency",id,divId,"mandalSubLevel");
		getAvailableDatesForPopupcli("constituency",id);
		GlobalPopupScope = "constituency";
		GlobalPopuplocation =id;
		 getEventDocumentsForPopup("constituency",id,0,0);
		
  });
  
     $(document).on('click','.mandalSubLevel',function(){
		var id = $(this).attr("attr");
		var divId = "mandalSubLevel"+id;
		getLocationsForPopupcli("mandal",id,divId,"villageSubLevel");
		getAvailableDatesForPopupcli("mandal",id);
		GlobalPopupScope = "mandal";
		GlobalPopuplocation =id;
		 getEventDocumentsForPopup("mandal",id,0,0);
  });
  
  $(document).on('click','.villageSubLevel',function(){
		var id = $(this).attr("attr");
		GlobalPopupScope = "village";
		GlobalPopuplocation =id;
		getAvailableDatesForPopupcli("village",id);
		 getEventDocumentsForPopup("village",id,0,0);
  });
   
  function callPopupLoad(locationScope,id,activityId)
  {
		if(locationScope == "state")
		{
			var divId = "popupstateConstituencies"+id;
			getLocationsForPopupcli("state",id,divId,"distSubLevel");
			getAvailableDatesForPopupcli("state",id);
		}
		
		if(locationScope == "district")
		{
			var divId = "popupdistConstituencies"+id;
			getLocationsForPopupcli("district",id,divId,"constiSubLevel");
			getAvailableDatesForPopupcli("district",id);
			getEventDocumentsForPopup("district",id,0,0);
		}
		if(locationScope == "constituency")
		{
			var divId = "constiSubLevel"+id;
			getLocationsForPopupcli("constituency",id,divId,"mandalSubLevel");
			getAvailableDatesForPopupcli("constituency",id);
			getEventDocumentsForPopup("constituency",id,0,0);
		}
		
		if(locationScope == "mandal")
		{
			var divId = "mandalSubLevel"+id;
			getLocationsForPopupcli("mandal",id,divId,"villageSubLevel");
			getAvailableDatesForPopupcli("mandal",id);
			getEventDocumentsForPopup("mandal",id,0,0);
		}
		
		if(locationScope == "village")
		{
			getEventDocumentsForPopup("village",id,0,0);
		}
		
		
  }
 $(document).on('click','.popupTitle',function(){
	  $("#myModalLabel").html(''+$(this).attr("attr")+'');
  })
  
  function getEventDocumentsForPopup(searchType,locationId,day,num)
{

	
	 $("#popupImages").html('<img src="./images/Loading-data.gif" />');
	 var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
		var jObj = {
		activityId:globalActivityScope,
		locationScope:searchType,
		locationValue:locationId,		
		day:day,
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		type:"popup",
		startIndex:num,
		maxIndex:10,
		//locationName:obj.locationName,
		 task:"popupdaywise"
		};
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWiseImagesForPopup1(result,jObj);
			});
}


function buildDayWiseImagesForPopup1(result,jObj)
{
	$("#popupImages").html('');
	var str ='';

	if(result != null)
	{
	
		str+='<ul class="slider-for">';
			for(var i in result)
			{
			for(var j in result[i].subList)
			{
				
				str+='<li><img src="http://mytdp.com/activity_documents/' +result[i].subList[j].path+'"></li>';
			}
			}
			  str+='</ul>';
		str+='<ul class="slider-nav">';	
		for(var i in result)
		{	 
			for(var j in result[i].subList)
			{
				
				 str+='<li><img src="http://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"></li>';	
				
			}
		}
				str+='</ul>';
			$("#popupImages").html(str);
			
			setTimeout(function(){		
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
			 // centerMode: true,
			focusOnSelect: true,
			  variableWidth: true

				})
			$(".slick-list").css("margin-left","17px;");	
			$(".slick-list").css("margin-right","17px;");	
			//$('.slider-nav li:first-child').trigger('click');
			//$('.slider-nav li:first-child').trigger('click');
		},300);
		
			var itemsCount=result[0].totalResult;
			
			
	    var maxResults=jObj.maxIndex;
	   if(jObj.startIndex==0){
		   $("#paginationDivId").html('');
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getEventDocumentsForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num);
				
			}
		});

		}
	GlobalPopupScope = globallocationScope;
	GlobalPopuplocation =globallocationValue;
	$(".imgTrig").trigger("click");
	}
	
	
	
}
	
	

function buildDayWiseImagesForPopup(result,path,day)
{
	
/*	var str ='';
	if(result != null)
	{
	for(var i in result)
	{
		 if(result[i].day == day)
			{
		
			str+='<ul class="slider-for">';
			for(var j in result[i].subList)
			{
				if(j < 20)
				{
						str+='<li><img src="http://mytdp.com/activity_documents/' +result[i].subList[j].path+'"></li>';
				}
			}
			  str+='</ul>';
			  str+='<ul class="slider-nav">';
			 
			for(var j in result[i].subList)
			{
				if(j < 20)
				{
				if(result[i].subList[j].path==path)
					str+='<li class="imgTrig1"><a class="imgTrig1"><img src="http://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"></a></li>';
				else
				 str+='<li><img src="http://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"></li>';		
				}
		  }
				str+='</ul>';
			$("#popupImages").html(str);
			
			
setTimeout(function(){		
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

				})
			$(".slick-list").css("margin-left","17px;");	
			$(".slick-list").css("margin-right","17px;");	
			//$('.slider-nav li:first-child').trigger('click');
			//$('.slider-nav li:first-child').trigger('click');
		},300);
		
			}
		}
	}*/
	
	getAvailableDates(globallocationScope,globallocationValue,day);

	//setTimeout(function(){imgTrigger()},800);
	
}
function imgTrigger()
{

	$('.imgTrig1').trigger('click');
}
function getAvailableDates(locationScope,locationValue,day)
	{
		
	  $("#popupDaysDiv").html('<img src="./images/Loading-data.gif" />');
	  var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	  var activityScopeId = $("#ActivityList").val();
	 if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
	
		var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				var str ='';
				for(var i in result)
				{
					if(result[i].id==day)
					{
						str+='<li class="active daysCls" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
						getEventDocumentsForPopup(jObj.locationScope,jObj.locationValue,day,0);
					}
					
					  else
					str+='<li class="daysCls" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
				}
				$("#popupDaysDiv").html(str);
				GlobalPopupScope = jObj.locationScope;
				GlobalPopuplocation =jObj.locationValue;
				
		});
}
 $(document).on('click','.daysCls',function(){
	
	 $(".daysCls").removeClass( "active" )
	 $(this).addClass("active");
	 var day = $(this).attr("attr");
		getEventDocumentsForPopup(GlobalPopupScope,GlobalPopuplocation,day,0);
  });

$(document).on('click', '.searchTypeCls', function(){
	
	var id = $(this).attr('id');
	
	if(id.trim() == 'locationWiseId')
	{
		$('#searchTypeId').val('locationWiseId');
	}
	if(id.trim() == 'organizersWiseId')
	{	
		$('#searchTypeId').val('organizersWiseId');
	}
	isAlreadyBuild = false;
	var searchRadioType = $('#searchTypeId').val();
	
	if(searchRadioType == 'locationWiseId'){
		getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
	}
	else if(searchRadioType == 'organizersWiseId'){
		getLeadersWiseActivityDetailsBySearchCriteria(1,'district','alignmentWidth','organizersWiseId','organizers',0,0,0,'');
	}
	
});
	getLocationWiseActivityDetails("district",0,"");
	function getLocationWiseActivityDetails(type,id,resultDivNum){
		var jObj = {
        searchType:type,
        activityScopeId:5,
        locationValue:id,
        task:""
        };
        $.ajax({
          type:'GET',
          url: 'getLocationWiseActivityDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(type=="district"){
				buildDistrictWiseActivityResult(result);
			}else if(type=="constituency"){
				buildConstituencyWiseActivityResult(result,resultDivNum);
			}else if(type=="mandal"){
				buildMandalWiseActivityResult(result,resultDivNum);
			}else if(type=="panchayat"){
				buildPanchayatWiseActivityResult(result,resultDivNum);
			}else if(type=="ward"){
				buildWardWiseActivityResult(result,resultDivNum);
			}
			
			
        });
	}
	
	function buildDistrictWiseActivityResult(result){
		var str='';
		
		str+='<table class="table table-col" style="border:1px solid #ccc">';
		str+='<caption class="cap-custom"><b>	</b></caption>';
		str+='<thead>';
			str+='<tr>';
			str+='<th rowspan="2" class="getChildWidth aligncenter">Location Name</th>';
			str+='<th rowspan="2" class="getChildWidth2 aligncenter">Total Locations</th>';
			str+='<th rowspan="2" class="getChildWidth3 aligncenter">Total Members Attended</th>';
			str+='<th colspan="2" class="aligncenter">Total Cadre Attended</th>';
			str+='<th colspan="2" class="aligncenter">Total Public Attended</th>';
			str+='<th colspan="2" class="aligncenter">Questionnaire Submitted</th>';
			str+='<th colspan="2" class="aligncenter">Organiser Questionnaire Submitted</th>';
			str+='<th colspan="2" class="aligncenter">Photos Attached</th>';
			str+='</tr>';
			str+='<tr>';
			str+='<th class="getChildWidth4 aligncenter">WEB</th>';
			str+='<th class="getChildWidth5 aligncenter">Info-cell</th>';
			str+='<th class="getChildWidth6 aligncenter">WEB</th>';
			str+='<th class="getChildWidth7 aligncenter">Info-cell</th>';
			str+='<th class="getChildWidth8 aligncenter">WEB</th>';
			str+='<th class="getChildWidth9 aligncenter">Info-cell</th>';
			str+='<th class="getChildWidth10 aligncenter">WEB</th>';
			str+='<th class="getChildWidth11 aligncenter">Info-cell</th>';
			str+='<th class="getChildWidth12 aligncenter">WEB</th>';
			str+='<th class="getChildWidth13 aligncenter">Info-cell</th>';
			str+='</tr>';
		str+='</thead>';
		if(result != null && result.subList != null && result.subList.length > 0){
			for(var i in result.subList){
				str+='<tr  id="'+result.subList[i].id+'">';
				str+='<td colspan="13" class="pad_0">';
				str+='<div class="panel-group m_0" id="accordionforBlood'+i+'" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default panel-customtd">';
				str+='<div class="panel-heading" role="tab" id="distHeading'+i+'">';
				//str+='<h4 class="panel-title">';
					str+='<a role="button" data-toggle="collapse" class="accordionforBlood'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-parent="#accordionforBlood'+i+'" href="#constituencyResultDivd'+i+'" aria-expanded="true" aria-controls="constituencyResultDivd'+i+'"  onclick=getLocationWiseActivityDetails("constituency","'+result.subList[i].id+'","'+i+'") >';
					str+='<div>';
						str+='<table>';
						str+='<tr>';							
							str+='<td class="dynChildWidth aligncenter">'+result.subList[i].name+'</td>';
							str+='<td class="dynChildWidth2 aligncenter">'+result.subList[i].totalLocations+'</td>';
							str+='<td class="dynChildWidth3 aligncenter">'+result.subList[i].totalMembers+'</td>';
							str+='<td class="dynChildWidth4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
							str+='<td class="dynChildWidth5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
							str+='<td class="dynChildWidth6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
							str+='<td class="dynChildWidth7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
							str+='<td class="dynChildWidth8 aligncenter">NA</td>';
							str+='<td class="dynChildWidth9 aligncenter">NA</td>';
							str+='<td class="dynChildWidth10 aligncenter">NA</td>';
							str+='<td class="dynChildWidth11 aligncenter">NA</td>';
							str+='<td class="dynChildWidth12 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
							str+='<td class="dynChildWidth13 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
						str+='</tr>';
						str+='</table>';
					str+='</div>';
					str+='</a>';
				//str+='</h4>';
				str+='</div>';
				str+='<div id="constituencyResultDiv'+i+'"></div>';
				str+='<div id="constituencyResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="distHeading'+i+'"></div>';
				str+='</div>';
				str+='</div>';
				str+='</td>';
				str+='</tr>';
			}
		}else{
			str+='<tr style="background-color:#663300;">';
			str+='<td colspan="13">';
			str+='<h4>No Data Available1.</h4>';
			str+='</td>';
			str+='</tr>';
		}
		str+='</table>';
		str+='</div>';
		
		$("#locationWiseActivityDetailsDivId").html(str);
		
		dynamicwidthforBlood();
	}
	
	function buildConstituencyWiseActivityResult(result,resultDivNum){
		var isOpened = $("#constituencyResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-body">';
					str+='<div class="panel-group panel-group1 m_0" id="accordion1Level1bd'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div class="panel-heading" role="tab" id="constituencyHeading'+i+'">';
					str+='<h4 class="panel-title">';
						str+='<a role="button" class="accordion1Level1bd'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1Level1bd" href="#mandalResultDivd'+i+'" aria-expanded="true" aria-controls="mandalResultDivd'+i+'" onclick=getLocationWiseActivityDetails("mandal","'+result.subList[i].id+'","'+i+'") >';
						str+='<div>';
							str+='<table>';
							str+='<tr>';							
								str+='<td class="dynChildWidth aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidth2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidth3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidth4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidth5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidth6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidth7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>';
								str+='<td class="dynChildWidth12 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidth13 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						str+='</div>';
						str+='</a>';
					str+='</h4>';
					str+='</div>';
					
					str+='<div id="mandalResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="constituencyHeading'+i+'"></div>';
					str+='<div id="mandalResultDiv'+i+'"></div>';
					str+='<div id="localElectionBodyResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available2.';
				str+='</td>';
				str+='</tr>';
			}
			$("#constituencyResultDiv"+resultDivNum).html(str);
			$("#constituencyResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#constituencyResultDiv"+resultDivNum).removeClass("opened");
			$("#constituencyResultDiv"+resultDivNum).html("");
		}
			dynamicwidthforBlood();
	}
		
	
  
	function buildMandalWiseActivityResult(result,resultDivNum){
		var isOpened = $("#mandalResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-body">';
					str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div  class="panel-heading" role="tab" id="mandalHeading'+i+'">';
					str+='<h4 class="panel-title">';
						str+='<a role="button" class=" accordion-toggle PlusnMinusSignV collapsed"  onclick=getLocationWiseActivityDetails("panchayat","'+result.subList[i].id+'","'+i+'") data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#panchayatResultDivd'+i+'" aria-expanded="true" aria-controls="panchayatResultDivd'+i+'">';
						str+='<div>';
							str+='<table>';
							str+='<tr>';							
								str+='<td class="dynChildWidth aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidth2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidth3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidth4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidth5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidth6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidth7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>';
								str+='<td class="dynChildWidth12 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidth13 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						str+='</div>';
						str+='</a>';
					str+='</h4>';
					str+='</div>';
					str+='<div id="panchayatResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="mandalHeading'+i+'"></div>';
					str+='<div id="panchayatResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available3.';
				str+='</td>';
				str+='</tr>';
			}
			$("#mandalResultDiv"+resultDivNum).html(str);
			$("#mandalResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#mandalResultDiv"+resultDivNum).removeClass("opened");
			$("#mandalResultDiv"+resultDivNum).html("");
		}
		
		
		var isOpened1 = $("#localElectionBodyResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened1){
			var str='';
			if(result != null && result.localBodyList != null && result.localBodyList.length > 0){
				for(var i in result.localBodyList){
					str+='<tr  id="'+result.localBodyList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-body">';
					str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelLeb1'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div  class="panel-heading" role="tab" id="mandalHeading1'+i+'">';
					str+='<h4 class="panel-title">';
						str+='<a role="button" class=" accordion-toggle PlusnMinusSignV collapsed"  onclick=getLocationWiseActivityDetails("ward","'+result.localBodyList[i].id+'","'+i+'") data-toggle="collapse" data-parent="#collapseOne1LevelLeb1'+i+'" href="#wardResultDivd'+i+'" aria-expanded="true" aria-controls="wardResultDivd'+i+'">';
						str+='<div>';
							str+='<table>';
							str+='<tr>';							
								str+='<td class="dynChildWidth aligncenter">'+result.localBodyList[i].name+'</td>';
								str+='<td class="dynChildWidth2 aligncenter">'+result.localBodyList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidth3 aligncenter">'+result.localBodyList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidth4 aligncenter">'+result.localBodyList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidth5 aligncenter">'+result.localBodyList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidth6 aligncenter">'+result.localBodyList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidth7 aligncenter">'+result.localBodyList[i].totalInfoCellPublicAttendance+'</td>';
								str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>';
								str+='<td class="dynChildWidth12 aligncenter">'+result.localBodyList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidth13 aligncenter">'+result.localBodyList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						str+='</div>';
						str+='</a>';
					str+='</h4>';
					str+='</div>';
					str+='<div id="wardResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="mandalHeading1'+i+'"></div>';
					str+='<div id="wardResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available4.';
				str+='</td>';
				str+='</tr>';
			}
			$("#localElectionBodyResultDiv"+resultDivNum).html(str);
			$("#localElectionBodyResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#localElectionBodyResultDiv"+resultDivNum).removeClass("opened");
			$("#localElectionBodyResultDiv"+resultDivNum).html("");
		}
		dynamicwidthforBlood();
		
	}
	
	function buildPanchayatWiseActivityResult(result,resultDivNum){
		var isOpened = $("#panchayatResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-body">';
					str+='<div class="panel-group panel-group1 m_0" id="accordion1LevelPanchayatbd'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div class="panel-heading" role="tab" id="panchayatHeading'+i+'">';
					str+='<h4 class="panel-title">';
						str+='<a role="button" class="accordion1LevelPanchayatbd'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1LevelPanchayatbd" href="#panchayatResultDivdummy'+i+'" aria-expanded="true" aria-controls="panchayatResultDivdummy'+i+'">';
						str+='<div>';
							str+='<table>';
							str+='<tr>';							
								str+='<td class="dynChildWidth aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidth2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidth3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidth4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidth5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidth6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidth7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>';
								str+='<td class="dynChildWidth12 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidth13 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						str+='</div>';
						str+='</a>';
					str+='</h4>';
					str+='</div>';
					
					str+='<div id="panchayatResultDivdummy'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panchayatHeading'+i+'"></div>';
					//str+='<div id="mandalResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available5.';
				str+='</td>';
				str+='</tr>';
			}
			$("#panchayatResultDiv"+resultDivNum).html(str);
			$("#panchayatResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#panchayatResultDiv"+resultDivNum).removeClass("opened");
			$("#panchayatResultDiv"+resultDivNum).html("");
		}
			dynamicwidthforBlood();
	}
	
	function buildWardWiseActivityResult(result,resultDivNum){
		var isOpened = $("#wardResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-body">';
					str+='<div class="panel-group panel-group1 m_0" id="accordion1LevelWardbd'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div class="panel-heading" role="tab" id="wardHeading'+i+'">';
					str+='<h4 class="panel-title">';
						str+='<a role="button" class="accordion1LevelWardbd'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1LevelWardbd" href="#wardResultDivdummy'+i+'" aria-expanded="true" aria-controls="wardResultDivdummy'+i+'">';
						str+='<div>';
							str+='<table>';
							str+='<tr>';							
								str+='<td class="dynChildWidth aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidth2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidth3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidth4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidth5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidth6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidth7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>';
								str+='<td class="dynChildWidth12 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidth13 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						str+='</div>';
						str+='</a>';
					str+='</h4>';
					str+='</div>';
					
					str+='<div id="wardResultDivdummy'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="wardHeading'+i+'"></div>';
					//str+='<div id="mandalResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available6.';
				str+='</td>';
				str+='</tr>';
			}
			$("#wardResultDiv"+resultDivNum).html(str);
			$("#wardResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#wardResultDiv"+resultDivNum).removeClass("opened");
			$("#wardResultDiv"+resultDivNum).html("");
		}
			dynamicwidthforBlood();
	}
	
	function dynamicwidthforBlood()
{
	$(".dynChildWidth13").css("width",$(".getChildWidth10").width());
	$(".dynChildWidth12").css("width",$(".getChildWidth9").width()+15);
	$(".dynChildWidth11").css("width",$(".getChildWidth9").width()+15);
	$(".dynChildWidth10").css("width",$(".getChildWidth9").width()+15);
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
</script>
</body>
</html>