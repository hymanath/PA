<div class="row">
  <div class="col-md-12 col-xs-12 col-sm-12">
    <table class="table table-bordered bg_ff m_top10">
      <tr>
        <td>
          <h4 class="panel-title m_top10">TODAY APPOINTMENTS</h4>
          <table class="table table-condensed tableAppointment" id="todayApptsForAdvancedDashBrd">
            <div ><center ><img style="display: none;" src="images/icons/loading.gif" id="todayForAdvancedDashBrdAjaxId"></center></div>
          </table>
        </td>
        <td>
          <table class="table removetopborder">
            <tr>
              <td>
                <h4 class="panel-title">TOTAL APPOINTMENTS REQUESTED</h4>
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
<div class="row">
	<div class="col-md-12" id="advanceDshAppointmentPrWiseDiv">
		
	</div>
</div
<div class="row">
  <div class="col-md-12" id="advanceDshAppointmentPrWiseDiv">
    <div class="block">
      <div class="row">
        <div class="col-md-12">
          <h4 class="text-capitalize">committee members wise appointments</h4>
		  <div id="committeeLvlAppntId"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>


function getCommitteeLevelCount(){
		
		var jsObj = {
			task:""
		}
	$.ajax({
		type : 'GET',
		url : 'getCommitteeLevelAppmentsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){ 
			buildCommitteeLvlAppntmnts(result);
	});     
}
function buildCommitteeLvlAppntmnts(result){
	 $("#committeeLvlAppntId").html("");
	var str = '';
	if(result != null){
			str +='<table class="table table-bordered">';
            str +='<thead>';
            str +='<tr>';
            str +='<th></th>';
            str +='<th class="text-capitalize text-center" colspan="2">total</th>';
            str +='<th class="text-capitalize text-center" colspan="2">requested</th>';
            str +='<th class="text-capitalize text-center" colspan="2">appointment scheduled</th>';
            str +='</tr>';
            str +='<tr>';
            str +='<th></th>';
            str +='<th class="text-capitalize">total</th>';
            str +='<th class="text-capitalize">unique</th>';
            str +='<th class="text-capitalize">total</th>';
            str +='<th class="text-capitalize">unique</th>';
            str +='<th class="text-capitalize">total</th>';
            str +='<th class="text-capitalize">unique</th>';
            str +='</tr>';
            str +='</thead>';
			str +=' <tbody>';
		   
		for(var i in result){
            str +='<tr>';
			if(result[i].role == 'State'){
				str +='<td>State Committee Members</td>';
			}else if(result[i].role == 'District'){
				str +='<td>District Committee Members</td>';
			}else if(result[i].role == 'Mandal'){
				str +='<td>Mandal Committee Members</td>';
			}else if(result[i].role == 'Town'){
				str +='<td>Town Committee Members</td>';
			}else if(result[i].role == 'Ward'){
				str +='<td>Ward Committee Members</td>';
			}else if(result[i].role == 'Village'){
				str +='<td>Village Committee Members</td>';
			}
            str +='<td style="text-align:center">'+result[i].total+'</td>';
            str +='<td style="text-align:center">'+result[i].uniquecnt+'</td>';
            str +='<td style="text-align:center">'+result[i].requestedCnt+'</td>';
            str +='<td style="text-align:center">'+result[i].uniqueRequestedCnt+'</td>';
            str +='<td style="text-align:center">'+result[i].scheduledCnt+'</td>';
            str +='<td style="text-align:center">'+result[i].uniqueScheduledCnt+'</td>';
			str +=' </tr>';
		   }
			str +='</tbody>';
			str +='</table>';
	}else{
		str +='No Data Available.';
	}
		  $("#committeeLvlAppntId").html(str);
}
</script>