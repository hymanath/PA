<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAHANADU VISITORS INFO</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
 <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	 <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
	 
<script src="dist/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="dist/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="dist/js/dataTables.responsive.js" type="text/javascript"></script>
	 
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<style>
  #userNameId,#imeiId{
     height: 30px;
     width: 220px;
}
</style>
</head>
<body>
    <div class="container m_top10">
      <div id="locationWiseCadreInfoDiv">
		   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">MAHANADU VISITORS INFO</h3>
				</div>
			</div>
	  </div>
	   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;min-height:200px;">
				<div>
				     <div><input style="display:none;" type="button" onclick="populate();" value="Update Latest Info"/></div>
				     <table id="mainInfo"  style="margin-left: 450px;margin-top: 15px;display:none;">
				       <tr>
					      <td><b>Total Visitors :  &nbsp;<span id="totalVisitors" /></b></td>
						  <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					      <td><b>Current Visitors :  &nbsp;<span id="currentVisitors" /></b></td>
					  </tr>
					</table>
					<div id="resultTableDiv" style="margin-top:15px;background-color: #fff;"><div  style="text-align: center;"><img style="width: 70px; height: 60px;" src="images/Loading-data.gif" /></div></div>
				</div>
			</div>
		</div>
		
		<div class="row m_top10">
			<div class="col-md-6">
				<div class="panel panel-default panel-custom-default">
					<div class="panel-body">
						<center><img id="daysSummaryIdAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
						
					   <div id="daysSummaryId"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default panel-custom-default">
						<div class="panel-body">
							<center><img id="ovrAlSummaryIdAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
							
						   <div id="ovrAlSummaryId"></div>
						</div>
				</div>
			</div>
		</div>
		
		<div class="row m_top10">
        <div class="col-md-6">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="districtHeadingSummary">AP DISTRICT WISE SUMMARY</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitchSummary" checked>
                    <label class="onoffswitch-label" for="myonoffswitchSummary">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body" style="min-height:400px;">	
					<center><img id="distAjaxSummaryId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
					 <div id="districtTableSummaryId" style="margin-top: 10px;"> </div>
                </div>
            </div>
			
        </div>
        <div class="col-md-6">
        	<div class="panel panel-default panel-custom-default">
            	<div class="panel-heading">
                <p class="m_0 display-style" id="constiHeadingSummary">AP CONSTITUENCY WISE</p>
                <div class="onoffswitch pull-right">
                    <input type="checkbox" name="onoffswitch1" class="onoffswitch-checkbox" id="myonoffswitchSummary1" checked>
                    <label class="onoffswitch-label" for="myonoffswitchSummary1">
                        <span class="onoffswitch-inner"></span>
                        <span class="onoffswitch-switch"></span>
                    </label>
                </div>
                
                </div>
                <div class="panel-body" style="min-height:430px;">
					<center><img id="constAjaxSummaryId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
					
                   <div id="constiTableSummaryId" style="margin-top:10px;"></div>
                </div>
            </div>
        </div>
    </div>
	</div>
	
	<script type="text/javascript">
	  $(".dropdown-menu").html('<li><a href="eventDashboardAction.action?eventId=1">EVENTS DASHBOARD</a></li><li><a href="dashBoardAction.action">DASHBOARD</a></li><li><a href="newlogoutAction.action">LOGOUT</a></li>');
	  function getDetails(){

		  $.ajax({
			  type:'GET',
			  url: 'getTodayTotalAndCurrentUsersInfo.action'
          }).done(function(result){
		       var str ="";
	           if(result != null){
				   $("#mainInfo").show();
	        	   $("#totalVisitors").html(result.totalVisitors);
	        	   $("#currentVisitors").html(result.currentVisitors);
			     str+="<table class='table table-bordered m_top20 '>";
				  str+=" <tr>";
				  if(result.lastUpdated != null){
				    str+="   <th colspan='2'><b>Last Updated On : "+result.lastUpdated+"</b></th>";
				  }else{
					 str+="   <th colspan='2'><b>Last Updated On : N/A</b></th>"; 
				  }
				  str+=" </tr>";
				  str+=" <tr>";
				  str+="   <th>STATUS</th>";
				  str+="   <th>COUNT</th>";
				  str+=" </tr>";
				  str+="<tr>";
				  str+="   <td>Above 8 Hours</td>";
				  if(result.above8hrs != null){
				    str+="   <td>"+result.above8hrs+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>7 to 8 Hours</td>";
				  if(result.seventoeight != null){
				    str+="   <td>"+result.seventoeight+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>6 to 7 Hours</td>";
				  if(result.sixtoseven != null){
				    str+="   <td>"+result.sixtoseven+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>5 to 6 Hours</td>";
				  if(result.fivetosix != null){
				    str+="   <td>"+result.fivetosix+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>4 to 5 Hours</td>";
				  if(result.fourtofive != null){
				    str+="   <td>"+result.fourtofive+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>3 to 4 Hours</td>";
				  if(result.threetofour != null){
				    str+="   <td>"+result.threetofour+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>2 to 3 Hours</td>";
				  if(result.twotothree != null){
				    str+="   <td>"+result.twotothree+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>1 to 2 Hours</td>";
				  if(result.onetotwo != null){
				     str+="   <td>"+result.onetotwo+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>Half An Hour To One Hours</td>";
				  if(result.halfanhour != null){
				     str+="   <td>"+result.halfanhour+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td>Below Half An Hour</td>";
				  if(result.belowhalfanhour != null){
				     str+="   <td>"+result.belowhalfanhour+"</td>";
				  }else{
					  str+="   <td>0</td>";
				  }
				  str+="</tr>";
				 
				 str+="</table>";
			   }
			   $("#resultTableDiv").html(str);
	      });
	  }
	  getDetails();
	  function populate(){
		  $.ajax({
			  type:'GET',
			  url: 'populateMahanaduLatestInfoAction.action'
          }).done(function(result){
			  
		  });
	  }
	  
 $("#myonoffswitchSummary").click(function(){
	if($('#myonoffswitchSummary').is(":checked")){
		$('#myonoffswitchSummary1').prop('checked', true);
		$("#districtHeadingSummary").html("AP DISTRICT WISE");
		$("#constiHeadingSummary").html("AP CONSTITUENCY WISE");
		getLocationWiseAttendeeSummaryCount(1,3);
		getLocationWiseAttendeeSummaryCount(1,4);
	}else{
		$('#myonoffswitchSummary1').prop('checked', false);
		$("#districtHeadingSummary").html("TS DISTRICT WISE");	
		$("#constiHeadingSummary").html("TS CONSTITUENCY WISE");
		getLocationWiseAttendeeSummaryCount(36,3);
		getLocationWiseAttendeeSummaryCount(36,4);
	}
}); 
$("#myonoffswitchSummary1").click(function(){
	if($('#myonoffswitchSummary1').is(":checked")){
	getLocationWiseAttendeeSummaryCount(1,4);
	$("#constiHeadingSummary").html("AP CONSTITUENCY WISE");
	}else{
	getLocationWiseAttendeeSummaryCount(36,4);
	$("#constiHeadingSummary").html("TS CONSTITUENCY WISE");
	}
});


getLocationWiseAttendeeSummaryCount(1,3);
getLocationWiseAttendeeSummaryCount(1,4);
function getLocationWiseAttendeeSummaryCount(stateId,reportLevelId){
	var subEvents1 = [];
	var stateId =0;
	
	if(reportLevelId == 3){
		$("#distAjaxSummaryId").show();
		$("#districtTableSummaryId").html("");
	}else{
		$("#constAjaxSummaryId").show();
		$("#constiTableSummaryId").html("");
	}
	
	$("#daysSummaryIdAjax").show();
	$("#ovrAlSummaryIdAjax").show();
	$("#daysSummaryId").html("");
	$("#ovrAlSummaryId").html("");
	

	subEvents1 = [14]; //8 -- MAHANADU MAIN ENTRY
	 
	if($('#myonoffswitchSummary1').is(":checked")){
		stateId = 1;
	}else{
		stateId = 36;
	}
 
	var jObj = {
			eventId:0,			
			stateId:stateId,
			reportLevelId:reportLevelId,
			subEvents : subEvents1,
			startDate : "24/05/2015",
			endDate : "25/05/2015"
		
		}	
		
		$.ajax({
          type:'GET',
          url: 'getEventAttendeeSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildLocationSummary(result,reportLevelId);
		});

}

function buildLocationSummary(result,reportLevelId){
	var subListLength = 0;
	if(reportLevelId == 3){
		$("#distAjaxSummaryId").hide();
	}else{
		$("#constAjaxSummaryId").hide();
	}
	$("#daysSummaryIdAjax").hide();
	$("#ovrAlSummaryIdAjax").hide();
	
	var str_a = "";
	str_a += "<table class='table'>";
		str_a +="<thead>";
			str_a +="<tr>";
				str_a+="<th></th>";
				str_a+="<th>TOTAL UNIQUE VISITS</th>";
				str_a+="<th>ONLY ONE DAY VISITS</th>";
				str_a+="<th>REVISITS</th>";
			str_a +="</tr>";
		str_a +="</thead>";
		str_a +="<tbody>";
			for(var i in result[0].datesList){
				str_a +="<tr>";
					str_a+="<td> DAY "+(parseInt(i)+1)+"</td>";
					str_a+="<td>"+result[0].datesList[i].total+"</td>";
					str_a+="<td>"+result[0].datesList[i].oneDayCount+"</td>";
					str_a+="<td>"+result[0].datesList[i].revisitCount+"</td>";
				str_a +="</tr>";
			}
		str_a +="</tbody>";
	str_a += "</table>";
	$("#daysSummaryId").html(str_a);
	
	var str_b = "";
	str_b += "<table class='table'>";
		str_b +="<thead>";
			for(var i in result[0].datesList){
				str_b +="<tr>";
					var number = parseInt(i)+1;
					if(number>1){
						str_b+="<th>"+(parseInt(i)+1)+" DAY'S VISITORS</th>";
					}else{
						str_b+="<th>"+(parseInt(i)+1)+" DAY VISITORS</th>";
					}
					
					str_b+="<th>"+result[0].datesList[i].total+"</th>"
				str_b +="</tr>";
			}
		str_b +="</thead>";
	str_b += "</table>";
	$("#ovrAlSummaryId").html(str_b);
	
	var str='';
	if(reportLevelId == 3){
    str+='<div class="scrollDiv"><table  class="display" id="table'+reportLevelId+'Summary" cellspacing="0" width="100%"><thead>';
	}else{
	str+='<div class="scrollDiv1"><table  class="display" id="table'+reportLevelId+'Summary" cellspacing="0" width="100%"><thead>';
	}
	

	str+='<tr>';
	if(reportLevelId == 3){
    str+='<th>DISTRICT</th>';
	}else{
	str+='<th>CONSTITUENCY</th>';
	}
	str +='<th> TOTAL VISITORS </th>';
	subListLength = result[0].hoursList.length;
	for(var i in result[0].hoursList){
		var number = result[0].hoursList[i].id;
		if(number>1){
			str +='<th> '+result[0].hoursList[i].id+' DAYS VISITORS</th>';
		}else{
			str +='<th> '+result[0].hoursList[i].id+' DAY VISITORS</th>';
		}
	}
	
    str+='</tr></thead>';
    str+='<tbody>';
	for(var i in result){
		str+='<tr>';
		str+='<td>'+result[i].locationName+'</td>';
		str+='<td>'+result[i].total+'</td>';
		
		if(result[i].subList!=null && result[i].subList.length>0){
			for(var j in result[i].subList){
				str +='<th> '+result[i].subList[j].count+'</th>';
			}
		}else{
			for(var i=0;i<subListLength;i++){
				str +='<th> - </th>';	
			}
		}
		
		str+='</tr>';
    }                               
	str+='</tbody></table></div>';
	if(reportLevelId == 3){
	$("#districtTableSummaryId").html(str);
	}
	else{
	$("#constiTableSummaryId").html(str);
	}
	$('#table'+reportLevelId+'Summary').DataTable( {
        responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
	    { "width": "30%", "targets": 0 }]
    } );
	
	if(reportLevelId == 3){
	$('.scrollDiv').slimScroll({
	height: '350px'
	});
	}
	else{
	$('.scrollDiv1').slimScroll({
	height: '370px'
	});
	}
	
}
	</script>
</body>
</html>