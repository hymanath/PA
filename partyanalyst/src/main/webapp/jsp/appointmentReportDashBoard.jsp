	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<table class="table table-bordered bg_ff m_top10">
				<tr>
					<!--<td>
						<h4 class="panel-title m_top10 text-success text-capitalize">TODAY APPOINTMENTS</h4>
						<table class="table table-condensed tableAppointment" id="todayApptsForAdvancedDashBrd">
							<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="todayForAdvancedDashBrdAjaxId"></center></div>
						</table>
					</td>-->
					<td>
						<table class="table removetopborder">
							<tr>
								<td>
									<h4 class="panel-title text-success text-capitalize">TOTAL APPOINTMENTS REQUESTED</h4>
										<div ><center ><img style="display: none;" src="images/icons/loading.gif" id="totalForAdvancedDashBrdAjaxId"></center></div>
									<!--<ul class="columnChartUl" id="totalAppointmentsId"></ul>-->
									<table class="table table-condensed tableAppointment" id="totalApptsForAdvancedDashBrd">
									</table>	
									
								</td>
								<td>
									<div id="LineChartForAdvancedDashBrd" style="width:500px;height:300px;"></div>
									<div  style="text-align: center;">Total Appointments - <span id="totalApptStatusCountsAdDash"></span></div>
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
		</div>
	</div>
							
<div>
	<div class="row m_top10">
		<div class="col-md-12">
			<div class="block advancedBlock">
				<div class="row">
					<div class="pull-right col-md-3">
						<div class="input-group">
							<input class="form-control getDate" type="text">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-calendar"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="row m_top20">
					<div id="candidateWiseCounts"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="block">
				<div class="row">
					<div>
						<div class="col-md-4">
							<h4 class="text-capitalize text-success">Location Wise Appointments</h4>
						</div>
						<div class="col-md-3" id="locationValue" style="width: 212px;">
							<label class="radio-inline">
								<input type="radio"  name= "locationType" checked="checked" value="District"/>District
							</label>
							<label class="radio-inline">
								<input type="radio"  name= "locationType" value="Constituency"/>Constituency
							</label>
						</div>
						<div class="col-md-4" style="margin-top: -3px; margin-left: -28px;">
						<div class="">
							<label  class="col-sm-4 " style="margin-top: 6px; width: 115px;">Select State</label>
							<div class="col-sm-3" style="width: 129px;margin-left: -25px;">
							 <select class="dropkickClass" id="state">
								<option value="0">ALL</option>
								<option value="1">AP</option>
								<option value="36">TS</option>
							</select>
							</div>
						  </div>
						</div>
					</div>
				</div>
				<div class="row m_top10">
				<div class="col-md-3">
				 <label style="margin-right: 8px;">
					<input type="checkbox" value="totalValue" name="totalUniqueType" checked id="totalValueId" >
					Total
				  </label>
				   <label>
					<input type="checkbox" name= "totalUniqueType" value="uniqueValue" id="uniqueValueId">
					Unique
				  </label>
				</div>
				</div>
				<div class="row m_top10">
					<div class="col-md-3">
						<!--multiselect-->
						<label > Select Request Type</label>
						<select id="requestedTypeId" multiple  style="width:250px !important;" data-placeholder="Select Status">
							<option value="All" selected>All</option>
							<option value="requested">Requested</option>
							<option value="scheduled">Scheduled</option>
							<option value="waiting">Waiting</option>
						</select>
					</div>
					<div class="col-md-3">
						<!--multiselect-->
						<label > Select Candidate Type</label>
						<select id="candidateType" multiple  style="width:250px !important;" data-placeholder="Select Candidate Type">
							<option value="0" selected>All</option>
						</select>
					</div>
					<div class="col-md-3  m_top20">
					<button class="btn btn-success" id="locationWiseDetailsDiv" >Submit</button>
					</div>
					<div class="col-md-12 m_top10">
						<div id="candiCountsWiseLocations"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>

<div class="row">
	<div class="col-md-12" id="advanceDshAppointmentPrWiseDiv">
		
	</div>
</div>
<div class="row">
  <div class="col-md-12" >
    <div class="block">
      <div class="row">
        <div class="col-md-12">
          <h4 class="text-capitalize text-success">committee members wise appointments</h4>
		  <div id="committeeLvlAppntId"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" style="display:none;" id="mandalDivId">
  <div class="col-md-12" >
    <div class="block">
      <div class="row">
        <div class="col-md-12">
          
		  <div id="roleWiseApptId"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>


function getCommitteeLevelCount(){
	var apontmntCndiateId=$("#appointmentUserSelectBoxId").val();
	
	
		var jsObj = {
				appointmentCndiateId:apontmntCndiateId,
				task:""
		}
	$.ajax({
		type : 'GET',
		url : 'getCommitteeLevelAppmentsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
	       buildCommitteeLvlAppntmnts(result,jsObj);
	});     
}
function buildCommitteeLvlAppntmnts(result,jsObj){
	
   $("#committeeLvlAppntId").html("");
  var str = '';
  if(result != null){
      str +='<table class="table table-bordered" id="dataTableCommiteeId">';
            str +='<thead>';
           str+='<tr>';
                str+='<th></th>';
                str+='<th class="text-capitalize text-center" colspan="2">total</th>';
                str+='<th class="text-capitalize text-center" colspan="2">requested</th>';
                str+='<th class="text-capitalize text-center" colspan="2">appointment scheduled</th>';
              str+='</tr>';
           str+='<tr>';
              // str +='<th></th>';
                str+='<th class="text-capitalize">Committee Level</th>';
                str+='<th class="text-capitalize">total</th>';
                str+='<th class="text-capitalize">unique</th>';
                str+='<th class="text-capitalize">total</th>';
                str+='<th class="text-capitalize">unique</th>';
                str+='<th class="text-capitalize">total</th>';
                str+='<th class="text-capitalize">unique</th>';
              str+='</tr>';
            str+='</thead>';
            str+='<tbody>';
       
    for(var i in result){
	        str +='<tr>';
		str +='<td id='+result[i].roleId+'><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getLevelWiseCount(\''+result[i].roleId+'\',\''+result[i].role+'\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].role+'</a></td>';
        
        if(result[i].total>0)
        {
        str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'CommitteeMember\',\'total\',\'total\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].total+'</a></td>';
        }else{
          str+='<td style="text-align:center">'+result[i].total+'</td>';
        }
		  if(result[i].uniquecnt>0)
        {
        str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'CommitteeMember\',\'unique\',\'total\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniquecnt+'</a></td>';
        }else{
          str+='<td style="text-align:center">'+result[i].uniquecnt+'</td>';
        }
        if(result[i].requestedCnt>0)
        {
         str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'CommitteeMember\',\'total\',\'Request\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].requestedCnt+'</a></td>';
        }else{
          str+='<td style="text-align:center">'+result[i].requestedCnt+'</td>';
        }
        if(result[i].uniqueRequestedCnt>0)
        {
                str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'CommitteeMember\',\'unique\',\'Request\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniqueRequestedCnt+'</a></td>';
        }else{
          str+='<td style="text-align:center">'+result[i].uniqueRequestedCnt+'</td>';
        }
        if(result[i].scheduledCnt>0)
        {
                str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'CommitteeMember\',\'total\',\'Schedule\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].scheduledCnt+'</a></td>';
        }else{
          str+='<td style="text-align:center">'+result[i].scheduledCnt+'</td>';
        }
        if(result[i].uniqueScheduledCnt>0)
        {

		str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleType(\''+result[i].roleId+'\',\'CommitteeMember\',\'unique\',\'Schedule\',\''+jsObj.appointmentCndiateId+'\');">'+result[i].uniqueScheduledCnt+'</a></td>';
        }else{
          str+='<td style="text-align:center">'+result[i].uniqueScheduledCnt+'</td>';
        }
          str +=' </tr>';
       }
      str +='</tbody>';
      str +='</table>';
  }else{
    str +='No Data Available.';
  }
      $("#committeeLvlAppntId").html(str);
      
      $("#dataTableCommiteeId").DataTable({
							"aaSorting": [[ -1, "desc" ]],
							"iDisplayLength" : 10	,
							 "bDestroy": true,
							 "bFilter": false,
							"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]
      }); 
}

function getLevelWiseCount(levelId,level,aptUserId){
		var jsObj = {
			aptUserId:aptUserId,
			levelId:levelId
		}
	$.ajax({
		type : 'GET',
		url : 'getLevelWiseCountAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
	console.log(result);
	buildRoleWiseCount(result,level,jsObj);
	});     
}
function buildRoleWiseCount(result,level,jsObj){
	$("#roleWiseApptId").html("");
	$("#mandalDivId").show();
	 
	var str = '';
	if(result != "" && result.length >0){
		str +='<h4 class="text-capitalize">'+level+' committee members appointments</h4>';
			str +='<table class="table table-bordered">';
            str +='<thead>';
           str+='<tr>';
								str+='<th></th>';
								str+='<th class="text-capitalize text-center" colspan="2">total</th>';
								str+='<th class="text-capitalize text-center" colspan="2">requested</th>';
								str+='<th class="text-capitalize text-center" colspan="2">appointment scheduled</th>';
							str+='</tr>';
         	str+='<tr>';
							// str +='<th></th>';
								str+='<th class="text-capitalize">role</th>';
								str+='<th class="text-capitalize">total</th>';
								str+='<th class="text-capitalize">unique</th>';
								str+='<th class="text-capitalize">total</th>';
								str+='<th class="text-capitalize">unique</th>';
								str+='<th class="text-capitalize">total</th>';
								str+='<th class="text-capitalize">unique</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
		   
		for(var i in result){
            str +='<tr>';
            
		      str +='<td id='+result[i].roleId+'>'+result[i].role+'</td>';
		  if(result[i].total>0)
		    {
            str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleTypeForRole(\''+result[i].roleId+'\',\'CommitteeMemberRole\',\'unique\',\'total\',\''+jsObj.aptUserId+'\',\''+jsObj.levelId+'\');">'+result[i].total+'</a></td>';
		    }else{
			   str+='<td style="text-align:center">'+result[i].total+'</td>';
		    }
		  if(result[i].uniquecnt>0)
		   {
            str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleTypeForRole(\''+result[i].roleId+'\',\'CommitteeMemberRole\',\'unique\',\'total\',\''+jsObj.aptUserId+'\',\''+jsObj.levelId+'\');">'+result[i].uniquecnt+'</a></td>';
		   }else{
			    str+='<td style="text-align:center">'+result[i].uniquecnt+'</td>';
		   }
		  if(result[i].requestedCnt)
		  {
            str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleTypeForRole(\''+result[i].roleId+'\',\'CommitteeMemberRole\',\'unique\',\'Request\',\''+jsObj.aptUserId+'\',\''+jsObj.levelId+'\');">'+result[i].requestedCnt+'</a></td>';
		  }else{
			  str+='<td style="text-align:center">'+result[i].requestedCnt+'</td>';
		  }
		  if(result[i].uniqueRequestedCnt>0)
		  {
            str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleTypeForRole(\''+result[i].roleId+'\',\'CommitteeMemberRole\',\'unique\',\'Request\',\''+jsObj.aptUserId+'\',\''+jsObj.levelId+'\');">'+result[i].uniqueRequestedCnt+'</a></td>';
		  }else{
			  str+='<td style="text-align:center">'+result[i].uniqueRequestedCnt+'</td>';
		  }
		  if(result[i].scheduledCnt>0)
		   {
            str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleTypeForRole(\''+result[i].roleId+'\',\'CommitteeMemberRole\',\'unique\',\'Schedule\',\''+jsObj.aptUserId+'\',\''+jsObj.levelId+'\');">'+result[i].scheduledCnt+'</a></td>';
		   }else{
			    str+='<td style="text-align:center">'+result[i].scheduledCnt+'</td>';
		   }
		  if(result[i].uniqueScheduledCnt)
		  {
            str +='<td style="text-align:center"><a target="_blank" data-toggle="tooltip" data-placement="top" title="Click here to View '+result[i].role+' Wise Appointments" style="cursor:pointer;" onclick="getMemebersByScheduleTypeForRole(\''+result[i].roleId+'\',\'CommitteeMemberRole\',\'unique\',\'Schedule\',\''+jsObj.aptUserId+'\',\''+jsObj.levelId+'\');">'+result[i].uniqueScheduledCnt+'</a></td>';
		   }else{
			   str+='<td style="text-align:center">'+result[i].uniqueScheduledCnt+'</td>';
		   }
			str +=' </tr>';
		   }
			str +='</tbody>';
			str +='</table>';
	}else{
		str +='<h4 class="text-capitalize">'+level+' committee members appointments</h4>';
		str +='No Data Available.';
	}
		  $("#roleWiseApptId").html(str);
}


</script>