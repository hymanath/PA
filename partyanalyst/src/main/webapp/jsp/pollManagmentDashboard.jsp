<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> POLL MANAGMENT DASHBOARD  </title>

<link href="dist/pollManagment/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/pollManagment/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="styles/jQ_datatables/css/jquery.dataTables.css"/>

</head>

<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default">
            	<div class="panel-heading bg_cc">
                	<h4 class="panel-title">POLL MANAGEMENT</h4>
                </div>
                <div class="panel-body bg_EF">
					<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<table class="table table-bordered bg_ff"  id="summaryTableId">
                            	<tr>
                                	<td rowspan="4" class="text-left" style="vertical-align: middle;">
                                    	<h3 id="startedDivisondId"></h3>
                                        <p>STARTED DIVISIONS</p>
                                    </td>
                                    <td rowspan="4" style="vertical-align: middle;">
                                    	<h3 id="totalBoothsId"></h3>
                                        <p>TOTAL BOOTHS</p>
                                    </td>
									<td rowspan="4" style="vertical-align: middle;">
                                    	<h3 id="totalVotersId"></h3>
                                        <p>TOTAL VOTERS</p>
                                    </td>
                                    <td rowspan="4" style="vertical-align: middle;">
                                    	<h3 id="totalCapturedVotersId"></h3>
                                        <p>TOTAL CAPTURED VOTERS</p>
                                    </td>
                                    <td style="vertical-align: middle;">
                                    	<p><span id="inclinedVotersId"></span> - INCLINED VOTERS</p>
                                    </td>
                                    <td rowspan="4" style="vertical-align: middle;">
                                    	<h3 id="totalCadreId"></h3>
                                        <p>TOTAL CADRE</p>
                                    </td>
									<td rowspan="4" style="vertical-align: middle;">
                                    	<h3 id="totalCapturedCadreId"></h3>
                                        <p>TOTAL CAPTURED CADRE</p>
                                    </td>
                                </tr>
                                <tr>
                                	<td style="vertical-align: middle;">
                                    	<p><span id="undecidedVotersId"></span> - UNDECIDED VOTERS</p>
                                    </td>
                                </tr>
                                <tr>
                                	<td style="vertical-align: middle;">
                                    	<p><span id="otherPartyId"></span> - OTHER PARTY VOTERS</p>
                                    </td>
                                </tr>
								<tr>
                                	<td style="vertical-align: middle;">
                                    	<p><span id="nonOptedId"></span> - NON OPTED VOTERS</p>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
							<div class="panel panel-default ">
								<div class="panel-heading">
									<h4 class="panel-title">DIVISION WISE VOTING ACTIVITY</h4>
								</div>
								<div class="panel-body pad_0">
									<div id="divisionWiseVotingDetailsDivId" style="overflow:auto;"></div>
									<center><img id="dataLoadingsImgForVotingDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
								</div>
							</div>
						</div>
					</div>
				</div>
            </div>
        </div>
    </div>
</div>

<script src="dist/pollManagment/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/pollManagment/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/pollManagment/js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<script type="text/javascript">
var locationIds = [];
getAccessValues();

function getAccessValues(){
	var jsObj={
			type:"ward"
		}
		$.ajax({
		  type:'GET',
		  url: 'getAccessValuesOfUserIdAction.action',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				for(var i in result){
					var locationVal = result[i].districtid;
					locationIds.push(locationVal);
				}
			}
			overAllPollManagementSummary();
			divisionWiseVotingActivity();
		});
	}
	
	function overAllPollManagementSummary(){
		
		var jsObj={
			locationIds:locationIds
		}
		
		$.ajax({
		  type:'GET',
		  url: 'overAllPollManagementSummaryAction.action',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				if(result.divisions==null)result.divisions=0;
				if(result.booths==null)result.booths=0;
				if(result.totalVoters==null)result.totalVoters=0;
				if(result.capturedVoters==null)result.capturedVoters=0;
				if(result.inclinedVoters==null)result.inclinedVoters=0;
				if(result.totalCadre==null)result.totalCadre=0;
				if(result.capturedCadre==null)result.capturedCadre=0;
				if(result.unDecidedVoters==null)result.unDecidedVoters=0;
				if(result.otherPartyVoters==null)result.otherPartyVoters=0;
				if(result.nonOptedVoters==null)result.nonOptedVoters=0;
				
					$("#startedDivisondId").html(result.divisions);
					$("#totalBoothsId").html(result.booths);
					$("#totalVotersId").html(result.totalVoters);
					$("#totalCapturedVotersId").html(result.capturedVoters);
					$("#inclinedVotersId").html(result.inclinedVoters);
					$("#totalCadreId").html(result.totalCadre);
					$("#totalCapturedCadreId").html(result.capturedCadre);
					$("#undecidedVotersId").html(result.unDecidedVoters);
					$("#otherPartyId").html(result.otherPartyVoters);
					$("#nonOptedId").html(result.nonOptedVoters);
				
			}
		});
	}

function divisionWiseVotingActivity(){
		
	$("#dataLoadingsImgForVotingDetails").show();
	var jsObj={
		 locationIds:locationIds
	}
	
	$.ajax({
	  type:'GET',
	  url: 'divisionWiseVotingActivityAction.action',
	  data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			buildDivisionWiseVotingSummary(result);
		}
		else{
			$("#dataLoadingsImgForVotingDetails").hide();
			$("#divisionWiseVotingDetailsDivId").html("NO DATA AVAILABLE...");
		}
	});
}

function buildDivisionWiseVotingSummary(result){
	var str='';
	
	str+='<table class="table table-bordered table-condensed" id="divisionWiseVotingTable" style="overflow:scroll">';
		str+='<thead>';
			str+='<tr class="font-12">';
			
				str+='<th rowspan="2">DIVISION NAME</th>';
				
				str+='<th colspan="3">TOTAL VOTERS</th>';
				str+='<th colspan="3">CADRES</th>';
				str+='<th colspan="3">CAPTURED CADRES</th>';
				 str+='<th colspan="3">INCLINED VOTERS</th>';
				str+='<th colspan="3">UNDECIDED VOTERS</th>';
				str+='<th colspan="3">OTHER PARTY VOTERS</th>';
				str+='<th colspan="3">NON OPTED VOTERS</th>';
				str+='<th colspan="3">NOT CAPTURED VOTERS</th>'; 
			str+='</tr>';
			str+='<tr class="font-10">';
			
				str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>';
				
				str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>';
				
				str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>';
				
				 str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>';
				
				str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>';
				
				str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>';
				
				str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>';
				
				str+='<th>TOTAL</th>';
				str+='<th>POLLED</th>';
				str+='<th>YET TO POLL</th>'; 
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
				
					str+='<td><a href="pollManagmentAreaWiseInfoAction.action?divisonId='+result[i].id+'" target="_blank">'+result[i].name+'</a></td>';
					
					str+='<td>'+result[i].totalVoters+'</td>';
					if(result[i].pollPercent != null && result[i].pollPercent.length > 0)
						str+='<td>'+result[i].pollPercent+'%</td>';
					else
						str+='<td> - </td>';
					if(result[i].yetToPollPercent != null && result[i].yetToPollPercent.length > 0)
						str+='<td>'+result[i].yetToPollPercent+'%</td>';
					else
						str+='<td> - </td>';
					
					
					str+='<td>'+result[i].cadreCount+'</td>';
					if(result[i].cadrepollPercent != null && result[i].cadrepollPercent.length > 0)
						str+='<td>'+result[i].cadrepollPercent+'%</td>';
					else
						str+='<td> - </td>';
					if(result[i].cadreYetToPollPercent != null && result[i].cadreYetToPollPercent.length > 0)
						str+='<td>'+result[i].cadreYetToPollPercent+'%</td>';
					else
						str+='<td> - </td>';
					
					
					if(result[i].capCadreCount != null && result[i].capCadreCount>0)
						str+='<td>'+result[i].capCadreCount+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].capCadrePollPercent != null && result[i].capCadrePollPercent.length > 0)
						str+='<td>'+result[i].capCadrePollPercent+'%</td>';
					else
						str+='<td> - </td>';
					if(result[i].capCadreYetTopollPercent != null && result[i].capCadreYetTopollPercent.length > 0)
						str+='<td>'+result[i].capCadreYetTopollPercent+'%</td>';
					else
						str+='<td> - </td>';
					
					 for(var j in result[i].subList){
						 
						if(result[i].subList[j].totalVoters != null && result[i].subList[j].totalVoters> 0)
							str+='<td>'+result[i].subList[j].totalVoters+'</td>';
						else
							str+='<td> - </td>';
						if(result[i].subList[j].pollPercent != null && result[i].subList[j].pollPercent.length > 0)
							str+='<td>'+result[i].subList[j].pollPercent+'%</td>';
						else
							str+='<td> - </td>';
						if(result[i].subList[j].yetToPollPercent != null && result[i].subList[j].yetToPollPercent.length > 0)
							str+='<td>'+result[i].subList[j].yetToPollPercent+'%</td>';
						else
							str+='<td> - </td>';
					}
					
					
					if(result[i].nonCapVoters != null && result[i].nonCapVoters > 0)
						str+='<td>'+result[i].nonCapVoters+'</td>';
					else
						str+='<td> - </td>';
					if(result[i].nonCapVotersPollPercent != null && result[i].nonCapVotersPollPercent.length > 0)
						str+='<td>'+result[i].nonCapVotersPollPercent+'%</td>';
					else
						str+='<td> - </td>';
					if(result[i].nonCapVotersYetToPollPercent != null && result[i].nonCapVotersYetToPollPercent.length > 0)
						str+='<td>'+result[i].nonCapVotersYetToPollPercent+'%</td>';
					else
						str+='<td> - </td>';
					
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	
	$("#dataLoadingsImgForVotingDetails").hide();
	$("#divisionWiseVotingDetailsDivId").html(str);
	$("#divisionWiseVotingTable").dataTable();
	$("#divisionWiseVotingTable").removeClass("dataTable");
}
</script>
</body>
</html>