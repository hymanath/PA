<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>POLL MANAGEMENT AREA WISE INFO</title>
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
                    	<div class="col-md-4 col-md-offset-8">
                        	
                                <label>Select Division</label>
                                <select id="divisonSelectId"></select>	
                        </div>
                    </div>
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                        	<table class="table table-bordered bg_ff" id="summaryTableId">
								<tr>
								<td rowspan="4" style="vertical-align: middle;">
								<h3 id="totalBoothsId">0</h3>
								<p>TOTAL BOOTHS</p>
								</td>
								<td rowspan="4" style="vertical-align: middle;">
								<h3 id="totalVotersId">0</h3>
								<p>TOTAL VOTERS</p>
								</td>
								<td rowspan="4" style="vertical-align: middle;">
								<h3 id="totalCapturedVotersId">0</h3>
								<p>TOTAL CAPTURED VOTERS</p>
								</td>
								<td>
								<p><span id="inclinedVotersId">0</span> - INCLINED VOTERS</p>
								</td>
								<td rowspan="4" style="vertical-align: middle;">
								<h3 id="totalCadreId">0</h3>
								<p>TOTAL CADRE</p>
								</td>
								<td rowspan="4" style="vertical-align: middle;">
								<h3 id="totalCapturedCadreId">0</h3>
								<p>TOTAL CAPTURED CADRE</p>
								</td>
								</tr>
								<tr>
								<td style="vertical-align: middle;">
								<p><span id="undecidedVotersId">0</span> - UNDECIDED VOTERS</p>
								</td>
								</tr>
								<tr>
								<td style="vertical-align: middle;">
								<p><span id="otherPartyId">0</span> - OTHER PARTY</p>
								</td>
								</tr>
								<tr>
								<td style="vertical-align: middle;">
								<p><span id="nonOptedId">0</span> - NON OPTED</p>
								</td>
								</tr>
							</table>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
                        	<div class="panel panel-default">
                            	<div class="panel-heading">
                                	<h4 class="panel-title">DIVISION VOTING ACTIVITY</h4>
                                </div>
                                <div class="panel-body m_top10 pad_0">
									<div class="row">
										<div class="col-md-1 text-center m_top10" style="vertical-align:middle">
											<p>P<br/>O<br/>L<br/>L<br/>E<br/>D<br/><br/> V<br/>O<br/>T<br/>E<br/>S</p>
										</div>
										<div class="col-md-11 col-xs-12 col-sm-11" style="border-left:1px solid #ddd;">
											<table class="table tableCustom" id="votingActivityTableID">
											</table>
											<center><img id="dataLoadingsImgForVotingDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
										</div>
									</div>
								</div>
                            </div>
							<div class="row m_top10">
								<div class="col-md-12 col-xs-12 col-sm-12">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">BOOTH WISE VOTING ACTIVITY</h4>
										</div>
										<div class="panel-body pad_0">
										
										<div id="boothWiseVotingDetailsDivId" style="overflow:auto"></div>
										<center><img id="dataLoadingsImgForBoothWiseVotingDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;"/></center>
										</div>
									</div>
								</div>
							</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content modalCustom">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">CADRE</h4>
		<p class="font-12">Yet to Poll - 6000(60%)</p>
      </div>
      <div class="modal-body ">
		<div class="bg_ff table-responsive">
			<table class="table table-bordered">
				<thead>
					<th><input type="checkbox"></th>
					<th>VOTER ID</th>
					<th>NAME</th>
					<th>MOBILE NUMBER</th>
					<th>SMS SENT</th>
					<th>CALL STATUS</th>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox"></td>
						<td>ABCD1213445</td>
						<td>Ramesh</td>
						<td>9648235464</td>
						<td>YES</td>
						<td><button class="btn btn-sm btn-success btnCustom">Dialed</button></td>
					</tr>
					<tr>
						<td><input type="checkbox"></td>
						<td>ABCD1213445</td>
						<td>Ramesh</td>
						<td>9648235464</td>
						<td>YES</td>
						<td><button class="btn btn-sm btn-danger">Not Dialed</button></td>
					</tr>
					<tr>
						<td><input type="checkbox"></td>
						<td>ABCD1213445</td>
						<td>Ramesh</td>
						<td>9648235464</td>
						<td>YES</td>
						<td><button class="btn btn-sm btn-danger">Not Dialed</button></td>
					</tr>
					<tr>
						<td><input type="checkbox"></td>
						<td>ABCD1213445</td>
						<td>Ramesh</td>
						<td>9648235464</td>
						<td>YES</td>
						<td><button class="btn btn-sm btn-danger">Not Dialed</button></td>
					</tr>
					<tr>
						<td><input type="checkbox"></td>
						<td>ABCD1213445</td>
						<td>Ramesh</td>
						<td>9648235464</td>
						<td>YES</td>
						<td><button class="btn btn-sm btn-danger">Not Dialed</button></td>
					</tr>
					<tr>
						<td><input type="checkbox"></td>
						<td>ABCD1213445</td>
						<td>Ramesh</td>
						<td>9648235464</td>
						<td>YES</td>
						<td><button class="btn btn-sm btn-danger">Not Dialed</button></td>
					</tr>
				</tbody>
			</table>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary btnCustom">SEND SMS TO SELECTED VOTERS</button>
      </div>
    </div>
  </div>
</div>
<script src="dist/pollManagment/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/pollManagment/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/pollManagment/js/custom.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript">

var divisonIdGlob = "${param.divisonId}";

var EqualHeight = $("#Getheight").height();
$("#PutHeight").css("height",EqualHeight);


	getAllDivisons();
	function getAllDivisons(){
		var jsObj={}
		
		$.ajax({
			type:"POST",
			url:"getAllDivisonsAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			var str='';
			str+='<option value="0">Select Divison</option>';
			if(result != null && result.length > 0){
				for(var i in result){
					if(result[i].id == divisonIdGlob){
						str+='<option value="'+result[i].id+'" selected>'+result[i].name+'</option>';
					}else{
						str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';	
					}
					  
				}
			}
			$("#divisonSelectId").html(str);
			$("#divisonSelectId").dropkick();
			overAllPollManagementSummaryByDivisionOrWard();
			divisonVotingAcitivty();
			boothWiseVotingActivity();
			  
		});
	}
	
$("#divisonSelectId").change(function(){
	overAllPollManagementSummaryByDivisionOrWard();
	divisonVotingAcitivty();
	boothWiseVotingActivity();
});

function overAllPollManagementSummaryByDivisionOrWard(){
	var jsObj={
		divisonId:$("#divisonSelectId").val()
	}
	
	$.ajax({
		  type:'GET',
		  url: 'overAllPollManagementSummaryByDivisionOrWardAction.action',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			if(result != null){
				if(result.booths==null)result.booths=0;
				if(result.totalVoters==null)result.totalVoters=0;
				if(result.capturedVoters==null)result.capturedVoters=0;
				if(result.inclinedVoters==null)result.inclinedVoters=0;
				if(result.totalCadre==null)result.totalCadre=0;
				if(result.capturedCadre==null)result.capturedCadre=0;
				if(result.unDecidedVoters==null)result.unDecidedVoters=0;
				if(result.otherPartyVoters==null)result.otherPartyVoters=0;
				if(result.nonOptedVoters==null)result.nonOptedVoters=0;
				
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
	
	

	$(document).on("click",".notPolledVoters",function(){
	  
	  var boothId = $(this).attr("attr_boothId");
	  var searchType = $(this).attr("attr_searchType");
	  
	  getNotYetPolledMembers(boothId,searchType);
	  
	  
	});

	function getNotYetPolledMembers(boothId,searchType){
	  
	  var jsObj={
	    searchType:searchType,
	    boothId   :boothId
	  }
	  $.ajax({
	    type:"POST",
	    url:"getNotYetPolledMembersAction.action",
	    data : {task:JSON.stringify(jsObj)} ,
	  }).done(function(result){
	    var str="";    
	    if(result !=null && result.length>0){
	      for(var i in result){
	        str+='<tr>';
	            str+='<td><input type="checkbox"></td>';
	            str+='<td>'+result[i].voterCardNo+'</td>';
	            str+='<td>'+result[i].name+'</td>';
	            str+='<td>'+result[i].mobileNo+'</td>';
	            if(result[i].smsStatus !=null && result[i].smsStatus=="success"){
	              str+='<td>YES</td>';
	            }else{
	              str+='<td>NO</td>';
	            }
	            
	            if(result[i].calledStatus !=null && result[i].calledStatus=="Y"){
	              str+='<td><button class="btn btn-sm btn-success btnCustom">Dialed</button></td>';
	            }else{
	              str+='<td><button class="btn btn-sm btn-danger">Not Dialed</button></td>';
	            }
	            
	        str+='</tr>';
	      }
	      
	    }else{
	      str+='<tr><td>No Data Available.</td></tr>';
	    }
	    
	    $("#notPolledMembersbodyId").html(str);
	    
	  });
	  
	}
	
	function divisonVotingAcitivty(){
		$("#dataLoadingsImgForVotingDetails").show();
		var jsObj={
			divisonId:$("#divisonSelectId").val()
		}
		
		$.ajax({
			type:"POST",
			url:"divisonVotingAcitivtyAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			var str='';
			if(result != null && result.length > 0){
				str+='<tr>';
				str+='<td style="width:20%"></td>';
				str+='<td style="width:5%">TOTAL</td>';
				str+='<td style="width:40%"></td>';
				//str+='<td style="width:2%"></td>';
				str+='<td style="width:10%">POLLED</td>';
				str+='<td style="width:13%">YET TO POLL</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td>TOTAL VOTERS</td>';
				str+='<td>'+result[0].totalVoters+'</td>';
				str+='<td>';
				str+='<div class="progress progressCustom">';
				if(result[0].pollPercent==null)result[0].pollPercent=0;
				str+='<div class="progress-bar progressGreen" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].pollPercent+'%;">';
				str+='</div>'+result[0].pollPercent+' %</div>';
				str+='</td>';
				//str+='<td><i class="glyphicon glyphicon-arrow-up text-success"></i></td>';
				str+='<td>'+result[0].totalVotersPolled+'</td>';
				str+='<td>'+result[0].totalVotersYetToBePolled+'</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td>CADRES</td>';
				str+='<td>'+result[0].cadreCount+'</td>';
				str+='<td>';
				str+='<div class="progress progressCustom">';
				if(result[0].cadrepollPercent != null)
					str+='<div class="progress-bar progressYellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].cadrepollPercent+'%;"></div>'+result[0].cadrepollPercent+' %</div>';
				else
					str+='<div class="progress-bar progressYellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].cadrepollPercent+'%;"></div>0 %</div>';
				str+='</td>';
				//str+='<td><i class="glyphicon glyphicon-arrow-down text-danger"></i></td>';
				str+='<td>'+result[0].cadreCountPolled+'</td>';
				str+='<td>'+result[0].cadreCountYetToBePolled+'</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td>TOTAL CAPTURED CADRES</td>';
				str+='<td>'+result[0].capCadreCount+'</td>';
				str+='<td>';
				str+='<div class="progress progressCustom">';
				if(result[0].capCadrePollPercent != null)
					str+='<div class="progress-bar progressYellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].capCadrePollPercent+'%;"></div>'+result[0].capCadrePollPercent+' %</div>';
				else
					str+='<div class="progress-bar progressYellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].capCadrePollPercent+'%;"></div>0 %</div>';
				str+='</td>';
				//str+='<td><i class="glyphicon glyphicon-arrow-down text-danger"></i></td>';
				str+='<td>'+result[0].capCadreCountPolled+'</td>';
				str+='<td>'+result[0].capCadreCountYetToBePolled+'</td>';
				str+='</tr>';
				
				if(result[0].subList != null && result[0].subList.length > 0){
					for(var i in result[0].subList){
						str+='<tr>';
						str+='<td>'+result[0].subList[i].name+'</td>';
						str+='<td>'+result[0].subList[i].totalVoters+'</td>';
						str+='<td>';
						str+='<div class="progress progressCustom">';
						if(result[0].subList[i].pollPercent==null)result[0].subList[i].pollPercent=0;
						if(i==0)
						str+='<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].subList[i].pollPercent+'%;">';
						if(i==1)
						str+='<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].subList[i].pollPercent+'%;">';	
						if(i==2)
						str+='<div class="progress-bar progressBringal" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].subList[i].pollPercent+'%;">';	
						if(i==3)
						str+='<div class="progress-bar progressBringal" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].subList[i].pollPercent+'%;">';	
						str+='</div>'+result[0].subList[i].pollPercent+' %</div>';
						str+='</td>';
						//str+='<td><i class="glyphicon glyphicon-arrow-up text-success"></i></td>';
						str+='<td>'+result[0].subList[i].totalVotersPolled+'</td>';
						str+='<td>'+result[0].subList[i].totalVotersYetToBePolled+'</td>';
						str+='</tr>';
					}
				}
				str+='<tr>';
				str+='<td>NOT CAPTURED VOTERS</td>';
				str+='<td>'+result[0].nonCapVoters+'</td>';
				str+='<td>';
				str+='<div class="progress progressCustom">';
				if(result[0].nonCapVotersPollPercent != null)
					str+='<div class="progress-bar progressYellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].nonCapVotersPollPercent+'%;"></div>'+result[0].nonCapVotersPollPercent+' %</div>';
				else
					str+='<div class="progress-bar progressYellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: '+result[0].nonCapVotersPollPercent+'%;"></div>0 %</div>';
				str+='</td>';
				//str+='<td><i class="glyphicon glyphicon-arrow-down text-danger"></i></td>';
				str+='<td>'+result[0].nonCapVotersPolled+'</td>';
				str+='<td>'+result[0].nonCapVotersYetToBePolled+'</td>';
				str+='</tr>';
												
			}else{
				str+='<h4>No Data Available.</h4>';
			}
			$("#dataLoadingsImgForVotingDetails").hide();
			$("#votingActivityTableID").html(str);
		});
	}
	
	function boothWiseVotingActivity(){
		$("#dataLoadingsImgForBoothWiseVotingDetails").show();
	 var jsObj={
			wardId:$("#divisonSelectId").val()
		}
		
		$.ajax({
		  type:'GET',
		  url: 'boothWiseVotingActivityAction.action',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildBoothWiseVotingSummary(result);
			}
			else{
				$("#dataLoadingsImgForBoothWiseVotingDetails").hide();
				$("#boothWiseVotingDetailsDivId").html("NO DATA AVAILABLE...");
			}
		});
	}
	function buildBoothWiseVotingSummary(result){
	var str='';
	
	str+='<table class="table table-bordered table-condensed" id="boothWiseVotingTable">';
		str+='<thead>';
			str+='<tr class="font-12">';
			
				str+='<th rowspan="2">BOOTH NAME</th>';
				
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
				
					str+='<td id='+result[i].id+'>'+result[i].name+'</td>';
					
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
	$("#dataLoadingsImgForBoothWiseVotingDetails").hide();
	$("#boothWiseVotingDetailsDivId").html(str);
	$("#boothWiseVotingTable").dataTable();
	$("#boothWiseVotingTable").removeClass("dataTable");
}

</script>
</body>
</html>
