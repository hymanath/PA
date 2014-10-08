<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Benefits Analysis</title>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
 </head>
<body>
<style>

.hideTodayButton .ui-datepicker-buttonpane .ui-datepicker-current
{
    visibility:hidden;
}

.hideCalendar .ui-datepicker-calendar
{
	display:none!important;
	visibility:hidden!important
}
#calenderRadioDiv{
   margin-left: 100px;
}
</style>

<div class="container"><div style="margin-top:20px;">
<legend class="boxHeading text-center" style="border-left:1px solid #d3d3d3;border-right:1px solid #d3d3d3;border-top:1px solid #d3d3d3;">Benefit Wise Analysis</legend>
<div class="span12 content_widget" style="margin-left:0px;margin-top:-20px;border-left:1px solid #d3d3d3;border-right:1px solid #d3d3d3;border-bottom:1px solid #d3d3d3;min-height:350px;width: 944px;">
   <div class="row offset3" style="padding-left:20px;padding-top: 10px;" id="errorDiv"></div>
    <div class="row offset3" style="margin-top: 10px;">

		<div>
			<label class="span2"><b>Select State:</b></label>
			<select id="stateId" class="input-block-level span3">
			<option value="0">ALL</option>
			<option value="1">Andhra Pradesh</option>
			<option value="36">Telangana</option>
			</select>
		</div>
		
		<!--<div>
			<label class="span2"><b>Select Report Type:</b></label>
			<select id="typeId" class="input-block-level span3" onChange="showCorrespondingDivs(this.value)">
			<option value="0">Select Report Type</option>
			<option value="1">Category Wise</option>
			<option value="2">Candidate Wise</option>
			 <!--<option value="3">Location Wise</option>-->
			<!--</select>
		</div>
		<div id="partiesMultiSelDiv" style="display:none;">
			<label  class="span2"><b>Select Party:</b></label>
			<select id="partiesMultiSelId"></select>
		</div>
		<div  id="groupDiv" style="display:none;">
			<label class="span2"><b>Select Group:</b></label>
			<select id="groupId" class="input-block-level span3">
			<option value="0">Select Group</option>
			</select>
		</div>
		
		<div id="locationDiv" style="display:none;">
			<label  class="span2"><b>Select Location:</b></label>
			<select id="locationId" class="input-block-level span3" onChange="getLocationDetails(this.value)">
			<option value="0">Select Location</option>
			<option value="1">State</option>
			<option value="2">District</option>
			<option value="3">Assembly</option>
			</select>
		</div>
		<div id="locationValueDiv" style="display:none;">
			<label  class="span2"><b>Select Location Value:</b></label>
			<select id="locationValueId" class="input-block-level span3">
			</select>
		</div>-->
	</div>
	<div class="row offset2">
	<div id="calenderRadioDiv" class="span5">
	 	  <label class="radio inline"><input id="dailyRadio" type="radio" value="dailyCalender" name="calendersRadio" class="calendersRadioCls" checked="true"/>Daily</label>
		  <label class="radio inline"><input id="weeklyRadio" type="radio" value="weeklyCalender" name="calendersRadio" class="calendersRadioCls"/>Weekly</label>
		  <label class="radio inline"><input id="monthlyRadio" type="radio" value="monthlyCalender" name="calendersRadio" class="calendersRadioCls"/>Monthly</label>
		  <!-- <label class="radio inline"><input id="betweenDatesRadio" type="radio" value="betweenDates" name="calendersRadio" class="calendersRadioCls"/>Between Date</label>-->
 	</div>
	</div>
	<div class="row offset3" style="margin-top: 10px;">
		<div id="dailyCalenderDiv">
				<label class="span2"><b>Select Date:</b></label>
				<input type="text" readonly="true" id="dailyCalenderId" style="height:30px;width:220px;" class="input-block-level span3" >
		</div>
		<div id="weeklyCalenderDiv" style="display:none;">
			<label class="span2"><b>Select Week:</b></label>
				<input type="text" readonly="true" id="startDate" class="input-block-level span2" style="height:30px;width:220px;">
		</div>
		<div id="monthlyCalenderDiv" style="display:none;">
				<label class="span2"><b>Select Month:</b></label>
				<input type="text" readonly="true" id="monthlyCalenderId" style="height:30px;width:220px;" class="input-block-level span3">
		</div>
		<div id="betweenDatesDiv" style="display:none;">
				<div>
					<label class="span2"><b>Select From Date:</b></label>
					<input type="text" placeholder="Select Date" class="input-block-level span2 date" style="height:30px;width:220px;" id="fromDateId" readonly/>
				</div>
				<div>
					<label class="span2"><b>Select To Date:</b></label>
					<input type="text" placeholder="Select Date" class="input-block-level span2 date" style="height:30px;width:220px;" id="toDateId" readonly/>
					</div>
				
		</div>
	</div>
	<div class="row offset3" style="margin-top: 10px;margin-left: 340px;"><button class="btn btn-success offset1" onclick="getBenefitDetails()" id="buttonId"> Get Details</button><img src="images/search.jpg" style="display: none;margin-left:10px;" id="submitDataImg"><img src="images/search.jpg" style="display: none;margin-left:10px;" id="submitDataImg1"><img src="images/search.jpg" style="display: none;margin-left:10px;" id="submitDataImg2"></div>
	
	
	<div id="categoryBenefitsDiv" style="margin-top: 40px;"></div>
	<div id="candidateBenefitsDiv1" style="margin-top: 40px;"></div>
	<div id="candidateBenefitsDiv2" style="margin-top: 40px;"></div>
	<div id="candidateBenefitsDiv3" style="margin-top: 40px;"></div>
	<div id="locationBenefitsDiv1" style="margin-top: 40px;"></div>
	<div id="locationBenefitsDiv2" style="margin-top: 40px;"></div>
	<div id="locationBenefitsDiv3" style="margin-top: 40px;"></div>
	<div class="row offset3" id="getAllNewsBtn" style="margin-top: 10px;margin-left: 340px;display:none;"><button class="btn btn-success offset5" onclick="getAllNewsDetails()"> Get News Details</div>
</div></div></div>
	
<script type="text/javascript">

function clearDivs(){
	//$("#locationValueDiv,#typeDiv").hide();
	//$("#groupId,#locationId,#locationValueId,#typeId").val(0);
	$("#categoryBenefitsDiv,#candidateBenefitsDiv1,#candidateBenefitsDiv2,#locationBenefitsDiv1,#locationBenefitsDiv2,#locationBenefitsDiv3").html("");
	
}
var flag= false;
function showCorrespondingDivs(value){
    if(value == 1){ 
	     $("#partiesMultiSelDiv").show();
		 $("#locationDiv").hide();
		 $("#locationValueDiv").hide();
		 $("#groupDiv").hide();	
	}else if(value == 2){		
		$("#locationDiv").hide();
		$("#locationValueDiv").hide();
		$("#groupDiv").show();	
        $("#partiesMultiSelDiv").hide();		
	}
	else if(value == 3){
		$("#groupDiv").hide();
		$("#locationDiv").show();
		
		$("#partiesMultiSelDiv").hide();	
	}
	else{
		$("#locationDiv").hide();
		$("#groupDiv").hide();
		$("#locationValueDiv").hide();
		$("#partiesMultiSelDiv").hide();
	}
}
 function getLocationDetails(locationValue){
		var stateIds= new Array();
		var name ="";
		if($("#stateId").val() == 0){
			stateIds.push(1);
			stateIds.push(36);
		}
		else{
			var id = $("#stateId").val();
			stateIds.push(id);
		}
		if(locationValue == 3){
			$("#locationValueDiv").show();
			name= "assemblyConstituency";
				
		}
		else if(locationValue == 2){
			$("#locationValueDiv").show();
			name = "districts";
			
		}
		else{
			$("#locationValueDiv").hide();
			return;
			
		}
		getAssemblyConstituenciesAndDistricts(stateIds,name);	
	}
 function getAssemblyConstituenciesAndDistricts(stateIds,name){
	var jsObj={
			stateIds : stateIds,
			task:name
	}
	$.ajax({
          type:'GET',
          url: 'getAssemblyConstituenciesandDistrictsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
				$('#locationValueDiv').find('option').remove();
		$.each(result,function(index,value){
			$('#locationValueId').append('<option value="'+value.id+'">'+value.name+'</option>'); 
		});
	});
 }

 
 $(".calendersRadioCls").live("click",function(){
	 var radioValue = $('input[name=calendersRadio]:checked').val();
	
	 if(radioValue == 'weeklyCalender') {
		$("#dailyCalenderDiv").hide();
		$("#monthlyCalenderDiv").hide();
	    $("#weeklyCalenderDiv").show();
		$("#ui-datepicker-div").removeClass("hideCalendar");
		$("#ui-datepicker-div").removeClass('hideTodayButton');
		$("#betweenDatesDiv").hide();
	 }
	 else if(radioValue == "monthlyCalender")
	 {
		  $("#dailyCalenderDiv").hide();
		  $("#weeklyCalenderDiv").hide();
		  $("#monthlyCalenderDiv").show();
		  $("#ui-datepicker-div").addClass("hideCalendar");
		  $("#ui-datepicker-div").addClass('hideTodayButton');
		  $("#betweenDatesDiv").hide();
	 }
	 else if(radioValue == "betweenDates"){
		 $("#weeklyCalenderDiv").hide();
		 $("#monthlyCalenderDiv").hide();
		 $("#dailyCalenderDiv").hide();
		 $("#ui-datepicker-div").removeClass("hideCalendar");
		 $("#ui-datepicker-div").removeClass('hideTodayButton');
		 $("#betweenDatesDiv").show();
	 }
	 else{
		 $("#weeklyCalenderDiv").hide();
		 $("#monthlyCalenderDiv").hide();
		 $("#dailyCalenderDiv").show();
		 $("#ui-datepicker-div").removeClass("hideCalendar");
		 $("#ui-datepicker-div").removeClass('hideTodayButton');
		 $("#betweenDatesDiv").hide();
	 } 
		 
	});
 
 $(document).ready(function() {
	 $("#dailyCalenderId").datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
   		changeYear: true,
		maxDate: new Date(),	
	});
	$('#dailyCalenderId').datepicker('setDate', new Date());
	$('.date').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date(),
	});
	$('.date').datepicker("setDate", new Date());
	
	var date = new Date();
	var iMonth =date.getMonth();
	var iYear = date.getFullYear();
	
	$('#monthlyCalenderId').datepicker({
     changeMonth: true,
     changeYear: true,
	 maxDate: new Date(),
     dateFormat: 'mm/yy',
	
	  onClose: function() {
        iMonth = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
        $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
		
     },
     beforeShow: function() {
       if ((selDate = $(this).val()).length > 0) 
       {
		  $("#ui-datepicker-div").addClass("hideCalendar");
		  $("#ui-datepicker-div").addClass('hideTodayButton');
          iYear = selDate.substring(selDate.length - 4, selDate.length);
          iMonth = selDate.substring(0, selDate.length - 5);
		  iMonth = parseInt(iMonth)-1;
          $(this).datepicker('option', 'defaultDate', new Date(iYear, iMonth, 1));
          $(this).datepicker('setDate', new Date(iYear, iMonth, 1));
       }
    }
  });
  $('#monthlyCalenderId').datepicker('setDate', new Date(iYear, iMonth, 1));
});	
	
$(function() {
    var startDate;
    var endDate;
    
    var selectCurrentWeek = function() {
        window.setTimeout(function () {
            $('#ui-datepicker-div').find('.ui-datepicker-current-day a').addClass('ui-state-active')
        }, 1);
    }
    
    $('#startDate').datepicker( {
        showOtherMonths: true,
        selectOtherMonths: true,
		dateFormat: "dd/mm/yy",
		maxDate: new Date(),
        onSelect: function(dateText, inst) { 
            var date = $(this).datepicker('getDate');
            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 6);
            var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
            $('#startDate').val($.datepicker.formatDate( dateFormat, startDate, inst.settings )+" to "+$.datepicker.formatDate( dateFormat, endDate, inst.settings ));
                    
            selectCurrentWeek();
        },
        beforeShowDay: function(date) {
            var cssClass = '';
            if(date >= startDate && date <= endDate)
                cssClass = 'ui-datepicker-current-day';
            return [true, cssClass];
        },
        onChangeMonthYear: function(year, month, inst) {
            selectCurrentWeek();
        }
    });
    $('#startDate').live('click',
	function(){
	selectCurrentWeek();
	});
    //$('#startDate .ui-datepicker-calendar tr').live('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    //$('#startDate .ui-datepicker-calendar tr').live('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
});

function getPartiesList()
{
	var jsObj=
	{
		task:'getPartyList'
	};
	 $.ajax({
		type: "POST",
		url: "getPartiesListAction.action",
		data: {task : JSON.stringify(jsObj)}
		})
		.done(function( result ) {
		
		$('#partiesMultiSelId').find('option').remove();
		$.each(result,function(index,value){
			$('#partiesMultiSelId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		 $('#partiesMultiSelId').multiselect({	
				multiple: true,
				selectedList: 1,
				noneSelectedText:"Select Party",
				hide: "explode"	
		        }).multiselectfilter({  
                  			
		       }); 
			    $(".ui-multiselect").each(function(){
		         $(this).attr("style","width:220px;height:30px;");
	          });
     });
}


function getCandidateGroups(){
	$.ajax({
		type: "POST",
		url: "getCandidateGroups.action"
		})
		.done(function( result ) {
		
		$('#groupId').find('option').remove();
		$('#groupId').append('<option value="0">Select Group</option>');
		$.each(result,function(index,value){
			$('#groupId').append('<option value="'+value.id+'">'+value.name+'</option>');
		});
		 
     });
}

//getPartiesList();
//getCandidateGroups();
var candidateIds= new Array();
function getCandidateGroupWiseBenifit(groupId){
	$("#errorDiv").html("");
	$('#candidateBenefitsDiv1,#candidateBenefitsDiv2,#candidateBenefitsDiv3').html("");
	var typeChecked = $('input[name=calendersRadio]:checked').val();
	var type = "";
	var fromDate = "";
	var toDate = "";
	var errorStr ="";
	if(typeChecked == "dailyCalender"){
		type = "daily";
		fromDate = $("#dailyCalenderId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select Date<br/></div>";
		}
		toDate = fromDate;
	}else if(typeChecked == "weeklyCalender"){
		type = "weekly";
		var dateStr = $("#startDate").val();
		if($.trim(dateStr).length > 0){
			var actualDates = dateStr.split("to");
			fromDate = $.trim(actualDates[0]);
			toDate = $.trim(actualDates[1]);
	    }else{
	    	errorStr=errorStr+"<div>Please Select Week<br/></div>";
	    }
	}else if(typeChecked == "monthlyCalender"){
		type = "monthly";
		fromDate =  $("#monthlyCalenderId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select Month<br/></div>";
		}
	}
	else if(typeChecked == "betweenDates"){
		type = "betweenDates";
		fromDate =  $("#fromDateId").val();
		toDate = $("#toDateId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select From Date<br/></div>";
		}
		else if($.trim(toDate).length == 0){
			errorStr=errorStr+"<div>Please Select To Date<br/></div>";
		}
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorDiv').html("From Date should be Less Than To Date");
		 return;
		}
		}
	}	
	
	/*var groupId = $('#groupId').val();
	
	if(groupId == 0){
		errorStr=errorStr+"<div>Please Select Group<br/></div>";
	}*/
	var stateId = $('#stateId').val();
	var partyId = 872;
	if(errorStr.length == 0){
	  $("#submitDataImg1").show();
	  $("#submitDataImg").hide();
	  $("#submitDataImg2").hide();
	  $("#buttonId").attr('disabled','disabled');
		var jsObj= 
			{
				type:type,
				fromDate:fromDate,
				toDate:toDate,
				groupId:groupId,
				stateId:stateId,
				partyId:partyId
			};
		$.ajax({
			type: "POST",
			url: "getCandidateGroupWiseBenifit.action",
			data: {task : JSON.stringify(jsObj)}
			})
			.done(function( result ) {
				if(groupId==3){
				  $("#submitDataImg1").hide();
				  $("#buttonId").removeAttr('disabled');
			  }
			  var str ="";
			 
			   if(result != null && result.length > 0){
					flag = true;
					
					if(groupId == 1){
					str+='<div style= "color:#468847"><b><h5 >Top 5 MLA Candidates Negative News Count</h5></b></div>';
				   }
				   else if(groupId == 2){
					str+='<div style= "color:#468847"><b><h5 >Top 5 MP Candidates Negative News Count</h5></b></div>';
				   }
				   else{
					str+='<div style= "color:#468847"><b><h5 >Top 5 Ministers Negative News Count</h5></b></div>';
				   }					
				   str += '<table class="table table-bordered " >';
		           str += '<thead class="alert alert-success">';
				   str+="  <tr>";
				   str+="    <th><b>Candidate Name</b></th>";
				   //str+="    <th><b>Positive News Count</b></th>";
				   str+="    <th><b>Negative News Count</b></th>";
				   str+="  </tr>";
				   str += '</thead>';
				   str += '<tbody>';
					for(var i in result){
						   str+="  <tr>";
						   str+="    <td>"+result[i].name+"</td>";
						    var obj = {
								name: groupId,
								id: result[i].id
								}
							candidateIds.push(obj);
						  // str+="    <td><a href='javascript:{}' onclick='getCandidateGroupNews(\""+type+"\",\""+fromDate+"\",\""+toDate+"\","+result[i].id+",1);'>"+result[i].count+"</a></td>";
						  if(result[i].negCount > 0){
						   str+="    <td><a href='javascript:{}' onclick='getCandidateGroupNews(\""+type+"\",\""+fromDate+"\",\""+toDate+"\","+result[i].id+",2);'>"+result[i].negCount+"</a></td>";  
						   }
						   else{
						   str+="<td>"+result[i].negCount+"</td>";
						  
						   }
						    str+="  </tr>";
					}
					str += '<input type="hidden" class="candidateIds" value='+candidateIds+' />';
					str += '<input type="hidden" id="dateTypeId" value='+type+' />';
					str += '<input type="hidden" id="fromDate1Id" value='+fromDate+' />';
					str += '<input type="hidden" id="toDate1Id" value='+toDate+' />';
				   str += '</tbody>';
				   str+="</table>";
				   if(groupId == 1){
					$('#candidateBenefitsDiv1').html(str);
				   }
				   else if(groupId == 2){
					$('#candidateBenefitsDiv2').html(str);
				   }
				   else{
					$('#candidateBenefitsDiv3').html(str);
				   }
			   }else{
			        // $('#candidateBenefitsDiv1').html('<div style="padding-left: 165px;font-weight:bold;" class="row offset3">No News Available</div>');
				     
			   }		 
	     });
	}else{
		$("#errorDiv").html("<div style='color:red;font-weight:bold;'>"+errorStr+"</div>");
	}
}
function getCandidateGroupNews(type,fromDate,toDate,candidateId,benfitId){
	
       var browser1 = window.open("getCategoryBenifitNewsAction.action?type="+type+"&fromDate="+fromDate+"&toDate="+toDate+"&candidateId="+candidateId+"&benefitId="+benfitId+"&buildType=candidate","viewNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
        browser1.focus();

}

/*function getBenefitDetails(){
   $('#categoryBenefitsDiv').html("");
    $("#errorDiv").html("");
    var type = $("#typeId").val();
	if(type == 0){
	$("#errorDiv").html("<div style='color:red;font-weight:bold;'>Please Select Report Type</div>");
	return;
	}
	if(type == 1){
	  getCategoryBenefitDetails();
	}else if(type == 2){
	  getCandidateGroupWiseBenifit();
	}
}*/
function getBenefitDetails(){
	clearDivs();
	flag= false;
 getCategoryBenefitDetails();
 getCandidateGroupWiseBenifit(1);
 getCandidateGroupWiseBenifit(2);
 getCandidateGroupWiseBenifit(3);
 getLocationBenefitDetails("districtWiseBenefits");
 getLocationBenefitDetails("parliamentWiseBenefits");
 getLocationBenefitDetails("assemblyWiseBenefits");
 
}
function getCategoryBenefitDetails(){
	
	$("#errorDiv").html("");
	var typeChecked = $('input[name=calendersRadio]:checked').val();
	var type = "";
	var fromDate = "";
	var toDate = "";
	var errorStr ="";
	if(typeChecked == "dailyCalender"){
		type = "daily";
		fromDate = $("#dailyCalenderId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select Date<br/></div>";
		}
		toDate = fromDate;
	}else if(typeChecked == "weeklyCalender"){
		type = "weekly";
		var dateStr = $("#startDate").val();
		if($.trim(dateStr).length > 0){
			var actualDates = dateStr.split("to");
			fromDate = $.trim(actualDates[0]);
			toDate = $.trim(actualDates[1]);
	    }else{
	    	errorStr=errorStr+"<div>Please Select Week<br/></div>";
	    }
	}else if(typeChecked == "monthlyCalender"){
		type = "monthly";
		fromDate =  $("#monthlyCalenderId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select Month<br/></div>";
		}
	}
	else if(typeChecked == "betweenDates"){
		type = "betweenDates";
		fromDate =  $("#fromDateId").val();
		toDate = $("#toDateId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select From Date<br/></div>";
		}
		else if($.trim(toDate).length == 0){
			errorStr=errorStr+"<div>Please Select To Date<br/></div>";
		}
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorDiv').html("From Date should be Less Than To Date");
		 return;
		}
		}
	}
	var stateId = $('#stateId').val();
	var partyIds = 872;
	
	//var typesId = $("#typeId").val();
	/*var partyId = $("#partiesMultiSelId").multiselect("getChecked").map(function(){
		return this.value;
	}).get();
	for(var i in partyId){
		partyIds = partyIds+""+partyId[i]+",";
	}
	if(partyIds.length > 0){
		partyIds = partyIds.substring(0, partyIds.length - 1);
	}else{
	  errorStr=errorStr+"<div>Please Select Party<br/></div>";
	}*/
	
	if(errorStr.length == 0){
	    $("#submitDataImg").show();
		$("#submitDataImg1").hide();
		$("#submitDataImg2").hide();
		$("#buttonId").attr('disabled','disabled');
		var jsObj=
		{
			type:type,
			stateId : stateId,
			partyIds :partyIds,
			fromDate : fromDate,
			toDate : toDate,
			task:'categoryBenefits'
		};
		 $.ajax({
			type: "POST",
			url: "getCategoryWiseBenifitAction.action",
			data: {task : JSON.stringify(jsObj)}
			}).done(function( result ) {
			    $("#submitDataImg").hide();
				$("#buttonId").removeAttr('disabled');
				buildCategoryWiseBenefitDetails(result,stateId,fromDate,toDate,type);	
			});
}else{
		$("#errorDiv").html("<div style='color:red;font-weight:bold;'>"+errorStr+"</div>");
	}
}
var categoryIds = new Array();
function buildCategoryWiseBenefitDetails(result,stateId,fromDate,toDate,type){
	$('#categoryBenefitsDiv').html("");
	if(result != null && result.length > 0){
		flag = true;
		var str = '';
		str+='<div style= "color:#468847"><b><h5 >Top 5 Categories Negative News Count</h5></b></div>';
		str += '<table class="table table-bordered " >';
		str += '<thead class="alert alert-success">';
		str += '<tr>';
		str += '<th rowspan="2"><b>Category</b></th>';
		for(var i in result[0].benfitVOList)
		{
		
		  str += '<th colspan="2"><b>'+result[0].benfitVOList[i].name+'</b></th>';
		}	
		str += '</tr>';
		for(var i=0;i<result[0].benfitVOList.length;i++)
		{
			//str += '<th> +ve </th>';
			str += '<th><b>Negative News Count</b></th>';
		}
		str += '<tr>';
		str += '</tr>';
		str += '<thead>';
		str += '<tbody>';
		var rows = 0;
		for(var i in result)
		{
		    rows= rows+1;
			if(rows < 6){
			str += '<tr>';
			str += '<td>'+result[i].name+'</td>';
			//categoryIds.push(result[i].id);
			categoryIds = categoryIds+""+result[i].id+",";
			for(var j in result[i].benfitVOList){
				/*if(result[i].benfitVOList[j].count > 0){
					str += '<td title="Click To See News" onclick="getCategoryBenefitNews('+result[i].benfitVOList[j].id+','+result[i].id+',1,'+stateId+',\''+fromDate+'\',\''+toDate+'\',\''+type+'\') " style="cursor:pointer;">'+result[i].benfitVOList[j].count+'</td>';
				}
				else{
					str += '<td>'+result[i].benfitVOList[j].count+'</td>';
				}*/
				if(result[i].benfitVOList[j].negCount > 0){
					str += '<td title="Click To See News" onclick="getCategoryBenefitNews('+result[i].benfitVOList[j].id+','+result[i].id+',2,'+stateId+',\''+fromDate+'\',\''+toDate+'\',\''+type+'\') " style="cursor:pointer;">'+result[i].benfitVOList[j].negCount+'</td>';
				}
				else{
					str += '<td >'+result[i].benfitVOList[j].negCount+'</td>';
				}
			}
			str += '</tr>';
		  }
		}
		str += '<input type="hidden" value = '+categoryIds+' />';
		str += '<input type="hidden" value = '+stateId+' id="stateHiddenId" />';
		str += '</tbody>';
		str += '</table>';
		$('#categoryBenefitsDiv').html(str);
		
	}else{
	    $('#categoryBenefitsDiv').html('<div style="padding-left: 165px;font-weight:bold;" class="row offset3">No News Available</div>');
	}
}


function getCategoryBenefitNews(partyId,categoryId,benefitId,stateId,fromDate,toDate,type){

	var browser1 = window.open("getCategoryBenifitNewsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&stateId="+stateId+"&benefitId="+benefitId+"&categoryId="+categoryId+"&partyId="+partyId+"&type="+type+"&buildType=category","viewNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
	 browser1.focus();
}
var locationIds= new Array();
function getLocationBenefitDetails(task){
	
	$("#errorDiv").html("");
	var typeChecked = $('input[name=calendersRadio]:checked').val();
	var type = "";
	var fromDate = "";
	var toDate = "";
	var errorStr ="";
	if(typeChecked == "dailyCalender"){
		type = "daily";
		fromDate = $("#dailyCalenderId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select Date<br/></div>";
		}
		toDate = fromDate;
	}else if(typeChecked == "weeklyCalender"){
		type = "weekly";
		var dateStr = $("#startDate").val();
		if($.trim(dateStr).length > 0){
			var actualDates = dateStr.split("to");
			fromDate = $.trim(actualDates[0]);
			toDate = $.trim(actualDates[1]);
	    }else{
	    	errorStr=errorStr+"<div>Please Select Week<br/></div>";
	    }
	}else if(typeChecked == "monthlyCalender"){
		type = "monthly";
		fromDate =  $("#monthlyCalenderId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select Month<br/></div>";
		}
	}
	else if(typeChecked == "betweenDates"){
		type = "betweenDates";
		fromDate =  $("#fromDateId").val();
		toDate = $("#toDateId").val();
		if($.trim(fromDate).length == 0){
			errorStr=errorStr+"<div>Please Select From Date<br/></div>";
		}
		else if($.trim(toDate).length == 0){
			errorStr=errorStr+"<div>Please Select To Date<br/></div>";
		}
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorDiv').html("From Date should be Less Than To Date");
		 return;
		}
		}
	}
	var stateId = $('#stateId').val();
	var partyId = 872;
		
	if(errorStr.length == 0){
		 $("#submitDataImg2").show();
		  $("#submitDataImg1").hide();
		   $("#submitDataImg").hide();
		 $("#buttonId").attr('disabled','disabled');
		var jsObj=
		{
			type:type,
			stateId : stateId,
			partyId :partyId,
			fromDate : fromDate,
			toDate : toDate,
			task : task
		};
		 $.ajax({
			type: "POST",
			url: "getLocationWiseBenifitAction.action",
			data: {task : JSON.stringify(jsObj)}
			}).done(function( result ) {
		
			    
				var str ="";
			   if(result != null && result.length > 0){
					flag= true;
					 if(task == "districtWiseBenefits"){
				   str+='<div style= "color:#468847"><b><h5 >Top 5 Districts Negative News Count</h5></b></div>';
				   }
				    else if(task == "parliamentWiseBenefits"){
					str+='<div style= "color:#468847"><b><h5 >Top 5 Parliament Constituencies Negative News Count</h5></b></div>';
				   }
				   else{
					str+='<div style= "color:#468847"><b><h5 >Top 5 Assembly Constituencies Negative News Count</h5></b></div>';
				   }
				   
			       str += '<table class="table table-bordered " >';
		           str += '<thead class="alert alert-success">';
				   str+="  <tr>";
				   if(task == "districtWiseBenefits"){
				   str+="    <th><b>District Name</b></th>";
				   }
				    else if(task == "parliamentWiseBenefits"){
					str+="    <th><b>Parliament Constitutency Name</b></th>";
				   }
				   else{
					str+="    <th><b>Assembly Constitutency Name</b></th>";
				   }
				   //str+="    <th><b>Positive News Count</b></th>";
				   str+="    <th><b>Negative News Count</b></th>";
				   str+="  </tr>";
				   str += '</thead>';
				   str += '<tbody>';
				   var rows=0;
					for(var i in result){
					 rows= rows+1;
					if(rows < 6){
						   str+="  <tr>";
						   str+="    <td>"+result[i].name+"</td>";
						   var obj = {
								name: task,
								id: result[i].id
								}
						   locationIds.push(obj);
						   //str+="    <td><a href='javascript:{}' onclick='getLocationNews(\""+type+"\",\""+fromDate+"\",\""+toDate+"\","+result[i].id+",1,\""+task+"\");'>"+result[i].count+"</a></td>";
						  if(result[i].negCount >0){
						   str+="    <td><a href='javascript:{}' onclick='getLocationNews(\""+type+"\",\""+fromDate+"\",\""+toDate+"\","+result[i].id+",2,\""+task+"\","+partyId+");'>"+result[i].negCount+"</a></td>";
						   
						   }
						   else{
								str+="    <td>"+result[i].negCount+"</td>";
						   }
						   str+="  </tr>";
					}
					}
					str += '<input type="hidden" value = '+locationIds+'/>';
				   str += '</tbody>';
				   str+="</table>";
				   if(task == "districtWiseBenefits"){
					$('#locationBenefitsDiv1').html(str);
				   }
				   else if(task == "parliamentWiseBenefits"){
					$('#locationBenefitsDiv2').html(str);
				   }
				   else{
					$('#locationBenefitsDiv3').html(str);
				   }
				  }	
				  if(task == "assemblyWiseBenefits"){  
					if(flag){
						$("#getAllNewsBtn").show();
					}
					else{
						$("#getAllNewsBtn").hide();
					}
					$("#submitDataImg2").hide();
					$("#buttonId").removeAttr('disabled');
				}
				 
			});

		}
		else{
			$("#errorDiv").html("<div style='color:red;font-weight:bold;'>"+errorStr+"</div>");
		}
		
}

function getLocationNews(type,fromDate,toDate,locationId,benfitId,name,partyId){
	
       var browser1 = window.open("getCategoryBenifitNewsAction.action?type="+type+"&fromDate="+fromDate+"&toDate="+toDate+"&locationId="+locationId+"&benefitId="+benfitId+"&partyId="+partyId+"&name="+name+"&buildType=location","viewNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
        browser1.focus();

}
var districtIds="";
var acIds=""
var pcIds=""
var candidateGrp1 = [];
var candidateGrp2 = [];
var candidateGrp3 = [];

function getAllNewsDetails(){
var type = $("#dateTypeId").val();
var fromDate = $("#fromDate1Id").val();
var toDate = $("#toDate1Id").val();
var stateId = $("#stateHiddenId").val();
var benefitId = 2;
var partyId = 872;

$.each(locationIds, function(){
    if(this.name == "districtWiseBenefits"){
		districtIds = districtIds+""+this.id+",";
	}
	else if(this.name == "parliamentWiseBenefits"){	
		pcIds = pcIds+""+this.id+",";
	}
	else{
		acIds = acIds+""+this.id+",";
		
	}
});
$.each(candidateIds, function(){
    if(this.name == 1){
		candidateGrp1= candidateGrp1+''+this.id+",";
	}
	else if(this.name == 2){
		
		candidateGrp2= candidateGrp2+''+this.id+",";
	}
	else{

		candidateGrp3= candidateGrp3+''+this.id+",";
	}
	});
	
	
	var browser1 = window.open("getCategoryBenifitNewsAction.action?type="+type+"&fromDate="+fromDate+"&toDate="+toDate+"&stateId="+stateId+"&districtIds="+districtIds+"&acIds="+acIds+"&pcIds="+pcIds+"&candidateGrp1="+candidateGrp1+"&candidateGrp2="+candidateGrp2+"&candidateGrp3="+candidateGrp3+"&categoryIds="+categoryIds+"&benefitId="+benefitId+"&partyId="+partyId+"&buildType=newsDetails","viewNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
 }
 


</script>

</body>
</html>