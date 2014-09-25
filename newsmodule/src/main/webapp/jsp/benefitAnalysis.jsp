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
   margin-left: 160px;
}
</style>

<div class="container" style="min-height:350px;"><div style="margin-top:20px;">
<h2></h2>
   <div id="errorDiv"></div>
    <div class="row offset3" style="margin-top: 20px;">

		<div>
			<label class="span2"><b>Select State:</b></label>
			<select id="stateId" class="input-block-level span3" onChange="clearDivs()">
			<option value="0">ALL</option>
			<option value="1">Andhra Pradesh</option>
			<option value="36">Telangana</option>
			</select>
		</div>
		
		<div>
			<label class="span2"><b>Select Report Type:</b></label>
			<select id="typeId" class="input-block-level span3" onChange="showCorrespondingDivs(this.value)">
			<option value="0">Select Report Type</option>
			<option value="1">Category Wise</option>
			<option value="2">Candidate Wise</option>
			<option value="3">Location Wise</option>
			</select>
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
		</div>
	</div>
	<div class="row offset3">
	<div id="calenderRadioDiv" class="span3">
	 	  <label class="radio inline"><input id="dailyRadio" type="radio" value="dailyCalender" name="calendersRadio" class="calendersRadioCls" checked="true"/>Daily</label>
		  <label class="radio inline"><input id="weeklyRadio" type="radio" value="weeklyCalender" name="calendersRadio" class="calendersRadioCls"/>Weekly</label>
		  <label class="radio inline"><input id="monthlyRadio" type="radio" value="monthlyCalender" name="calendersRadio" class="calendersRadioCls"/>Monthly</label>
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
	</div>
</div></div>

<script type="text/javascript">
function clearDivs(){
	$("#locationValueDiv,#typeDiv").hide();
	$("#groupId,#locationId,#locationValueId,#typeId").val(0);
}
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
		dateFormat: "dd/mm/yy",
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
getPartiesList();
</script>

</body>
</html>