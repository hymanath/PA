<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Benefits Analysis</title>

 <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
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
</style>

<div class="container "><div style="margin-top:20px;">
<h2></h2>
   <div id="errorDiv"></div>
    <div class="row offset3" style="margin-top: 20px;">

		<div>
			<label class="span2">Select State:</label>
			<select id="stateId" class="input-block-level span3" onChange="clearDivs()">
			<option value="0">ALL</option>
			<option value="1">Andhra Pradesh</option>
			<option value="36">Telangana</option>
			</select>
		</div>
		
		<div>
			<label class="span2">Select Type:</label>
			<select id="typeId" class="input-block-level span3" onChange="showCorrespondingDivs(this.value)">
			<option value="0">Select Type</option>
			<option value="1">Category Wise</option>
			<option value="2">Candidate Wise</option>
			<option value="3">location Wise</option>
			</select>
		</div>
		<div  id="groupDiv" style="display:none;">
			<label class="span2">Select Group:</label>
			<select id="groupId" class="input-block-level span3">
			<option value="0">Select Group</option>
			</select>
		</div>
		
		<div id="locationDiv" style="display:none;">
			<label  class="span2">Select Location</label>
			<select id="locationId" class="input-block-level span3" onChange="getLocationDetails(this.value)">
			<option value="0">Select Location</option>
			<option value="1">State</option>
			<option value="2">District</option>
			<option value="3">Assembly</option>
			</select>
		</div>
		<div id="locationValueDiv" style="display:none;">
			<label  class="span2">Select Location Value</label>
			<select id="locationValueId" class="input-block-level span3">
			</select>
		</div>
	</div>
	<div class="row offset3" style="margin-top: 10px;">
	<div id="calenderRadioDiv" class="span3">
	 	  <label class="radio inline"><input id="dailyRadio" type="radio" value="dailyCalender" name="calendersRadio" class="calendersRadioCls" checked="true"/>Daily</label>
		  <label class="radio inline"><input id="weeklyRadio" type="radio" value="weeklyCalender" name="calendersRadio" class="calendersRadioCls"/>Weekly</label>
		  <label class="radio inline"><input id="monthlyRadio" type="radio" value="monthlyCalender" name="calendersRadio" class="calendersRadioCls"/>Monthly</label>
 	</div>
	</div>
	<div class="row offset3" style="margin-top: 10px;">
 	<div id="dailyCalenderDiv">
			<label class="span2">Select Date:</label>
 			<input type="text" readonly="true" id="dailyCalenderId" style="height:30px" class="input-block-level span3" >
 	</div>
 	<div id="weeklyCalenderDiv" style="display:none;">
		<!--<span id="startDate"></span> - <span id="endDate"></span>-->
		<label class="span2">Select Week</label>
 			<input type="text" readonly="true" id="startDate" class="input-block-level span2" style="height:30px">
			<input type="text" readonly="true" id="endDate" class="input-block-level span2" style="height:30px">
 	</div>
 	<div id="monthlyCalenderDiv" style="display:none;">
			<label class="span2">Select Month:</label>
 			<input type="text" readonly="true" id="monthlyCalenderId" style="height:30px" class="input-block-level span3">
 	</div>
	</div>
</div></div>

<script>
function clearDivs(){
	$("#locationValueDiv,#typeDiv").hide();
	$("#groupId,#locationId,#locationValueId,#typeId").val(0);
}
function showCorrespondingDivs(value){
	if(value == 2){		
		$("#locationDiv").hide();
		$("#locationValueDiv").hide();
		$("#groupDiv").show();		
	}
	else if(value == 3){
		$("#groupDiv").hide();
		$("#locationDiv").show();
	}
	else{
		$("#locationDiv").hide();
		$("#groupDiv").hide();
		$("#locationValueDiv").hide();
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
	 }
	 else if(radioValue == "monthlyCalender")
	 {
		  $("#dailyCalenderDiv").hide();
		  $("#weeklyCalenderDiv").hide();
		  $("#monthlyCalenderDiv").show();
		  $("#ui-datepicker-div").addClass("hideCalendar");
		  $("#ui-datepicker-div").addClass('hideTodayButton');
	 }
	 else{
		 $("#weeklyCalenderDiv").hide();
		 $("#monthlyCalenderDiv").hide();
		 $("#dailyCalenderDiv").show();
		 $("#ui-datepicker-div").removeClass("hideCalendar");
		 $("#ui-datepicker-div").removeClass('hideTodayButton');
	 } 
		 
	});
 
 $(document).ready(function() {
	 $("#dailyCalenderId").datepicker({
		dateFormat: "mm/dd/yy",
		changeMonth: true,
   		changeYear: true,
		maxDate: new Date(),	
	});
	$('#dailyCalenderId').datepicker('setDate', new Date());
	
	var date = new Date();
	var iMonth =date.getMonth();
	var iYear = date.getFullYear();
	
	$('#monthlyCalenderId').datepicker({
     changeMonth: true,
     changeYear: true,
     dateFormat: 'MM yy',
	
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
          iMonth = jQuery.inArray(selDate.substring(0, selDate.length - 5), $(this).datepicker('option', 'monthNames'));
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
            $('#weeklyCalenderDiv').find('.ui-datepicker-current-day a').addClass('ui-state-active')
        }, 1);
    }
    
    $('#weeklyCalenderDiv').datepicker( {
        showOtherMonths: true,
        selectOtherMonths: true,
        onSelect: function(dateText, inst) { 
            var date = $(this).datepicker('getDate');
            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 6);
            var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
            $('#startDate').val($.datepicker.formatDate( dateFormat, startDate, inst.settings ));
            $('#endDate').val($.datepicker.formatDate( dateFormat, endDate, inst.settings ));          
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
    
    $('#weeklyCalenderDiv .ui-datepicker-calendar tr').live('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('#weeklyCalenderDiv .ui-datepicker-calendar tr').live('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
});

</script>

</body>
</html>