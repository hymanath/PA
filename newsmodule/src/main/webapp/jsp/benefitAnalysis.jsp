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

<div class="container"><div style="margin-top:20px;">
<legend class="boxHeading text-center" style="border-left:1px solid #d3d3d3;border-right:1px solid #d3d3d3;border-top:1px solid #d3d3d3;">Benefit Wise Analysis</legend>
<div class="span12 content_widget" style="margin-left:0px;margin-top:-20px;border-left:1px solid #d3d3d3;border-right:1px solid #d3d3d3;border-bottom:1px solid #d3d3d3;min-height:350px;width: 944px;">
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
			<!-- <option value="3">Location Wise</option>-->
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
	<div class="row offset3" style="margin-top: 10px;"><button class="btn btn-success offset1" onclick="getBenefitDetails()" id="buttonId"> Get Details</button>
	</div>
	
</div></div></div>
	<div id="categoryBenefitsDiv" style="margin-top: 10px;"></div>
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

getPartiesList();
getCandidateGroups();

function getCandidateGroupWiseBenifit(){
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
			var actualDates = dateStr.split(to);
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
	
	var groupId = $('#groupId').val();
	
	if(groupId == 0){
		errorStr=errorStr+"<div>Please Select Group<br/></div>";
	}
	var stateId = $('#stateId').val();
	var partyId = 872;
	if(errorStr.length == 0){
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
			  var str ="";
			   if(result != null && result.length > 0){
				   str+="<table>";
				   str+="  <tr>";
				   str+="    <th><b>Candidate Name</b></th>";
				   str+="    <th><b>Positive News Count</b></th>";
				   str+="    <th><b>Negative News Count</b></th>";
				   str+="  </tr>";
					for(var i in result){
						   str+="  <tr>";
						   str+="    <td>"+result[i].name+"</td>";
						   str+="    <td><a href='javascript:{}' onclick='getCandidateGroupNews(\""+type+"\",\""+fromDate+"\",\""+toDate+"\","+result[i].id+",1);'>"+result[i].count+"</a></td>";
						   str+="    <td><a href='javascript:{}' onclick='getCandidateGroupNews(\""+type+"\",\""+fromDate+"\",\""+toDate+"\","+result[i].id+",2);'>"+result[i].negCount+"</a></td>";
						   str+="  </tr>";
					}
				   str+="</table>";
			   }else{
				     
			   }		 
	     });
	}else{
		$("#errorDiv").html("<div style='color:red;font-weight:bold;'>"+errorStr+"</div>");
	}
}
function getCandidateGroupNews(type,fromDate,toDate,candidateId,benfitId){
	
       var browser1 = window.open("getAllNewsAction.action?type="+type+"&fromDate="+fromDate+"&toDate="+toDate+"&candidateId="+candidateId+"&benfitId="+benfitId,"viewNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
        browser1.focus();

}



function getBenefitDetails(){
	
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
	var stateId = $('#stateId').val();
	
	var partyIds= "";
	
	var typesId = $("#typeId").val();
	if(typesId = 3){

		var partyId = $("#partiesMultiSelId").multiselect("getChecked").map(function(){
			return this.value;
		}).get();
		for(var i in partyId){
			partyIds = partyIds+""+partyId[i]+",";
		}
	
	}
	if(errorStr.length == 0){
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
				buildCategoryWiseBenefitDetails(result,stateId,fromDate,toDate);	
			});
}
}

function buildCategoryWiseBenefitDetails(result,stateId,fromDate,toDate){
	$('#categoryBenefitsDiv').html("");
	if(result != null && result.length > 0){
		var str = '';
		str += '<table class="table table-bordered " >';
		str += '<thead class="alert alert-success">';
		str += '<tr>';
		str += '<th rowspan="2">Category</th>';
		for(var i in result[0].benfitVOList)
		{
		
		  str += '<th colspan="2">'+result[0].benfitVOList[i].name+'</th>';
		}	
		str += '</tr>';
		for(var i=0;i<result[0].benfitVOList.length;i++)
		{
			str += '<th> +ve </th>';
			str += '<th> -ve </th>';
		}
		str += '<tr>';
		str += '</tr>';
		str += '<thead>';
		str += '<tbody>';
		
		for(var i in result)
		{
			str += '<tr>';
			str += '<td>'+result[i].name+'</td>';
			for(var j in result[i].benfitVOList){
				if(result[i].benfitVOList[j].count > 0){
					str += '<td title="Click To See News" onclick="getCategoryBenefitNews('+result[i].benfitVOList[j].id+','+result[i].id+',1,'+stateId+',\''+fromDate+'\',\''+toDate+'\') " style="cursor:pointer;">'+result[i].benfitVOList[j].count+'</td>';
				}
				else{
					str += '<td>'+result[i].benfitVOList[j].count+'</td>';
				}
				if(result[i].benfitVOList[j].negCount > 0){
					str += '<td title="Click To See News" onclick="getCategoryBenefitNews('+result[i].benfitVOList[j].id+','+result[i].id+',2,'+stateId+',\''+fromDate+'\',\''+toDate+'\') " style="cursor:pointer;">'+result[i].benfitVOList[j].negCount+'</td>';
				}
				else{
					str += '<td >'+result[i].benfitVOList[j].negCount+'</td>';
				}
			}
			str += '</tr>';
		}
		
		str += '</tbody>';
		str += '</table>';
		$('#categoryBenefitsDiv').html(str);
		
	}
}


function getCategoryBenefitNews(partyId,categoryId,benefitId,stateId,fromDate,toDate){

	var browser1 = window.open("getCategoryBenifitNewsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&stateId="+stateId+"&benefitId="+benefitId+"&categoryId="+categoryId+"&partyId="+partyId,"viewNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
	 browser1.focus();
}

</script>

</body>
</html>