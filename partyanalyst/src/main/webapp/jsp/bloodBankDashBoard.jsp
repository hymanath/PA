<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blood Bank Dashboard</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/BloodBankCustomFiles/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,300,500italic,400italic,300italic,700,900" rel="stylesheet" type="text/css">
<style type='text/css'>
.extraPadding tr td
{
	padding:9.3px 8px !important;
}
.extraPadding1 tr td
{
	padding:4.8px 8px !important;
}
.bloodCollectionHeightCls tr td{
	padding:12px !important;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
        	<div class="block">
            	<div class="row">
                	<div class="col-md-2 col-xs-3">
                    	<h3 class="m_top20">DASHBOARD</h3>
                    </div>
                    <div class="col-md-1 col-xs-2">
                  	  <img src="dist/img/logo.png" class="img-responsive" alt="Logo"/>
                    </div>
                    <div class="col-md-8 col-xs-7">
                    	<h3 class="text-capitalise">ntr trust blood bank</h3>
                        <p class="text-capitalise">ntr trust bhavan, road no-2, banjara hills, hyderabad - 34</p>
                        <p>Tel: 040 30799999 | E-Mail:hydbb@ntrtrust.org | Lic No: 01/HD/AP/2008/BB/CP</p>
                        
                    </div>
                </div>
             </div>
         </div>
    </div>
    <div class="row m_top20">
    	<div class="col-md-7">
        	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">blood donors</h4>
                </div>
                <div class="panel-body pad_0" id="bldDonorCnts">
                	
                </div>
            </div>
        </div>
        <div class="col-md-5">
        	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">blood donors numbers by age grouping</h4>
                </div>
                <div class="panel-body">
                	<div id="container" style="height:215px;"></div>
                </div>
            </div>
        </div>
    </div>
	<div class="row">
	<div class="col-xs-6">
		<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">AP District Wise blood collection </h4>
                </div>
                <div class="panel-body pad_0">
				<center><img id="ApdistrictWiseBloodCountajaxImage" src="images/Loading-data.gif" style="width:70px;height:60px"/></center>
                	<div id="ApdistrictWiseBloodCountDiv"></div>
                </div>
            </div>
	</div>
	<div class="col-xs-6">
	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">TS District Wise blood collection </h4>
                </div>
                <div class="panel-body pad_0">
				<center><img id="TsdistrictWiseBloodCountajaxImage" src="images/Loading-data.gif" style="width:70px;height:60px"/></center>
                	<div id="TsdistrictWiseBloodCountDiv"></div>
                </div>
            </div>
		
	</div>
	</div>
    <div class="row">
    	<div class="col-md-3">
        	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">blood collection</h4>
                </div>
                <div class="panel-body">
                	<h4 class="text-capitalise">total colleceted blood</h4>
					<div id="collectedBloodDiv"></div>
                    <!--<table class="table table-condensed m_top10">
                    	<tr>
                        	<td>All Days</td>
                            <td>16,00000ml</td>
                        </tr>
                        <tr>
                        	<td>Day 1</td>
                            <td>16,00000ml</td>
                        </tr>
                        <tr>
                        	<td>Day 1</td>
                            <td>16,00000ml</td>
                        </tr>
                        <tr>
                        	<td>Day 1</td>
                            <td>16,00000ml</td>
                        </tr>
                    </table>-->
                </div>
            </div>
        </div>
        <div class="col-md-4">
        	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">Blood donated in blood bank</h4>
                </div>
                <div class="panel-body pad_0">
				<center><img id="numberOfTimesBloodajaxImage" src="images/Loading-data.gif" style="width:70px;height:60px"/></center>
                	<div id="numberOfTimesBloodDiv"></div>
                </div>
            </div>
        </div>
        <div class="col-md-5">
        	<div class="row">
            	<div class="col-md-12">
                	<div class="panel panel-default panelDefaultCustom">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capitalise">blood donated other than blood bank</h4>
                        </div>
                        <div class="panel-body">
                        	<h4><span id="otherBloodBankId">0</span> - Members Donated</h4>
                        </div>
                     </div>
                </div>
                <div class="col-md-12" style="margin-top: -6px;">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capitalise">donate in emergency</h4>
                        </div>
                        <div class="panel-body">
                        	<h4><span id="emergencyDonarsId">0</span> - Willing to Donate in Emergency</h4>
                        </div>
                     </div>
                </div>
                <div class="col-md-12" style="margin-top: -5px;">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title text-capitalise">called for donation</h4>
                        </div>
                        <div class="panel-body">
                        	<h4><span id="donationCallerdDiv">0</span> - Willing to be Called for Donation</h4>
                        </div>
                     </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
		<div class="col-md-3">
			<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">Single Bag</h4>
                </div>
                <div class="panel-body">
                	<h4 class="text-capitalise">total single bags - <span id="totalSingleBags">0</span></h4>
                    <div id="singleBagsDivId"></div>
					<!--<table class="table table-condensed  m_top10">
                    	<tr>
                        	<td>With Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                        <tr>
                        	<td>Without Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                    </table>-->
                </div>
            </div>
        </div>
        <div class="col-md-3">
        	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">double Bag</h4>
                </div>
                <div class="panel-body">
                	<h4 class="text-capitalise">total double bags - <span id="totalDoubleBags">0</span></h4>
                    <div id="doubleBagsDivId"></div>
					<!--<table class="table table-condensed m_top10">
                    	<tr>
                        	<td>With Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                        <tr>
                        	<td>Without Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                    </table>-->
                </div>
            </div>
        </div>
        <div class="col-md-3">
        	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">triple Bag</h4>
                </div>
                <div class="panel-body">
                	<h4 class="text-capitalise">total triple bags - <span id="totalTripleBags"></span></h4>
					<div id="tripleBagsDivId"></div>
                    <!--<table class="table table-condensed m_top10">
                    	<tr>
                        	<td>With Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                        <tr>
                        	<td>Without Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                    </table>-->
                </div>
            </div>
        </div>
        <div class="col-md-3">
        	<div class="panel panel-default panelDefaultCustom">
            	<div class="panel-heading">
                	<h4 class="panel-title text-capitalise">Quadruple Bag</h4>
                </div>
                <div class="panel-body">
                	<h4 class="text-capitalise" style="font-size:17px;">total Quadruple bags - <span id="totalQuadrateBags"></span></h4>
                    <div id="quadrateBagsDivId"></div>
					<!--<table class="table table-condensed m_top10">
                    	<tr>
                        	<td>With Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                        <tr>
                        	<td>Without Sagm 350ml</td>
                            <td>10 Bags</td>
                        </tr>
                    </table>-->
                </div>
            </div>
        </div>
    </div>
</div>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="training/dist/HighCharts/highcharts.js"></script>
<script type="text/javascript">
$(function () {
	 
});
gettotalCollectedBloodDetails();
getBloodDonatedOtherThanBloodBank();
getBloodDonorInEmergency();
getCalledForDonationCount();
gettotalCollectedBloodBagsInfo();

function gettotalCollectedBloodDetails(){
	var bloodBankCampId = 1;
	
	var jObj = {
			bloodBankCampId : bloodBankCampId
		};
		
		$.ajax({
          type:'GET',
          url: 'gettotalCollectedBloodDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null){
				var str='';
				str+='<table class="table table-condensed m_top10 bloodCollectionHeightCls">';
					str+='<tr>';
						str+='<td>All Days</td>';
						str+='<td>'+result.totalBlood+' ml</td>';
					str+='</tr>';
					for(var i in result.subList){
						str+='<tr>';
							str+='<td>Day - '+(parseInt(i)+1)+'</td>';
							if(result.subList[i].totalBlood != null)
								str+='<td>'+result.subList[i].totalBlood+' ml</td>';
							else
								str+='<td>0 ml</td>';
						str+='</tr>';
					}
				str+='</table>';
				
				$("#collectedBloodDiv").html(str);
			}
		});
}

function getBloodDonatedOtherThanBloodBank(){
	var jObj = {
		};
		
		$.ajax({
          type:'GET',
          url: 'getBloodDonatedOtherThanBloodBankAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result > 0)
				$("#otherBloodBankId").html(result);
		});
}

function getBloodDonorInEmergency(){
	var jObj = {
		};
		
		$.ajax({
          type:'GET',
          url: 'getBloodDonorInEmergencyAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result > 0)
				$("#emergencyDonarsId").html(result);
		});
}

function getCalledForDonationCount(){
	var jObj = {
		};
		
		$.ajax({
          type:'GET',
          url: 'getCalledForDonationCountAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result > 0)
				$("#donationCallerdDiv").html(result);
		});
}

function gettotalCollectedBloodBagsInfo(){
	var bloodBankCampId = 1;
	
	var jObj = {
			bloodBankCampId : bloodBankCampId
		};
		
		$.ajax({
          type:'GET',
          url: 'gettotalCollectedBloodBagsInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(result != null){
				if(result.singleBagList != null){
					var str='';
					var total = 0;
					str+='<table class="table table-condensed  m_top10">';
					for(var i in result.singleBagList){
						str+='<tr>';
							str+='<td>'+result.singleBagList[i].quantityType+'</td>';
							if(result.singleBagList[i].count != null)
								str+='<td>'+result.singleBagList[i].count+' Bags</td>';
							else
								str+='<td>0 Bags</td>';
						str+='</tr>';
						total = total+parseInt(result.singleBagList[i].count);
					}
					str+='</table>';
					
					$("#totalSingleBags").html(total);
					$("#singleBagsDivId").html(str);
				}
				if(result.doubleBagList != null){
					var str='';
					var total = 0;
					str+='<table class="table table-condensed  m_top10">';
					for(var i in result.doubleBagList){
						str+='<tr>';
							str+='<td>'+result.doubleBagList[i].quantityType+'</td>';
							if(result.doubleBagList[i].count != null)
								str+='<td>'+result.doubleBagList[i].count+' Bags</td>';
							else
								str+='<td>0 Bags</td>';
						str+='</tr>';
						total = total+parseInt(result.doubleBagList[i].count);
					}
					str+='</table>';
					
					$("#totalDoubleBags").html(total);
					$("#doubleBagsDivId").html(str);
				}
				if(result.tripleBagList != null){
					var str='';
					var total = 0;
					str+='<table class="table table-condensed  m_top10">';
					for(var i in result.tripleBagList){
						str+='<tr>';
							str+='<td>'+result.tripleBagList[i].quantityType+'</td>';
							if(result.tripleBagList[i].count != null)
								str+='<td>'+result.tripleBagList[i].count+' Bags</td>';
							else
								str+='<td>0 Bags</td>';
						str+='</tr>';
						total = total+parseInt(result.tripleBagList[i].count);
					}
					str+='</table>';
					
					$("#totalTripleBags").html(total);
					$("#tripleBagsDivId").html(str);
				}
				if(result.quadrupleList != null){
					var str='';
					var total = 0;
					str+='<table class="table table-condensed  m_top10">';
					for(var i in result.quadrupleList){
						str+='<tr>';
							str+='<td>'+result.quadrupleList[i].quantityType+'</td>';
							if(result.quadrupleList[i].count != null)
								str+='<td>'+result.quadrupleList[i].count+' Bags</td>';
							else
								str+='<td>0 Bags</td>';
						str+='</tr>';
						total = total+parseInt(result.quadrupleList[i].count);
					}
					str+='</table>';
					
					$("#totalQuadrateBags").html(total);
					$("#quadrateBagsDivId").html(str);
				}
			}
		});
}
getBloodDonorDetailsByAgeGroupingInfo();
function getBloodDonorDetailsByAgeGroupingInfo(){
	var bloodBankCampId = 1;
	
	var jObj = {
			bloodBankCampId : bloodBankCampId
		};
		
		$.ajax({
          type:'GET',
          url: 'getBloodDonorDetailsByAgeGroupingInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			var finalArr= [];
			if(result != null && result.length > 0){
				for(var i in result){
					var obj={};
					//obj["name"]="Day - "+(parseInt(i)+1)+"";
					obj["name"]= result[i].date;
					var temp=[];
					if(result[i].subList != null && result[i].subList.length > 0){
						for(var j in result[i].subList){
							temp.push(parseInt(result[i].subList[j].count));
						}
					}
					obj["data"]=temp;
					finalArr.push(obj);
				}
				Highcharts.setOptions({
	colors: ['#FED501', '#D64D54', '#5C2D25', '#00B17D', '#7CB7EF', 
   '#FFA85B', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1']
	 });
    $('#container').highcharts({
		
        chart: {
            type: 'column'
        },
        title: {
            text: ' '
        },
        xAxis: {
            categories: ['Day 1', 'Day 2', 'Day 3']
        },
        yAxis: {
            min: 0,
            title: {
                text: ' '
            },
            stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
            align: 'bottom',
            x: 30,
            verticalAlign: 'bottom',
            y: 20,
            floating: false,
            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
            borderColor: '#CCC',
            shadow: false
        },
        tooltip: {
            headerFormat: '<b>{point.x}</b><br/>',
            pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                    style: {
                        textShadow: '0 0 3px black'
                    }
                }
            }
        },
        series: finalArr
    });
			}
			//console.log(finalArr);
		});
}
getBloodDonorsCountsSummary();
function getBloodDonorsCountsSummary(){
	//var campId = $("#").val();
	var jsObj={
			campId : 1
			}
	$.ajax({
			  type:'GET',
			  url: 'getBloodDonorsCountsSummaryAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   //console.log(result)
		   buildBloodDonorsCountsSummary(result.bloodBankDashBoardVO);
		 });
}
getNumberOfTimesCollectedBlood();
function getNumberOfTimesCollectedBlood(){
	$("#numberOfTimesBloodajaxImage").show();
	$("#numberOfTimesBloodDiv").html('');
	//var campId = $("#").val();
	var jsObj={
			bloodBankCampId : 1
			}
	$.ajax({
			  type:'GET',
			  url: 'getNumberOfTimesCollectedBloodAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#numberOfTimesBloodajaxImage").hide();
		   buildCollectedBlood(result)
		});
}

function buildCollectedBlood(result){
	var str='';
	if(result !=null)
		str+='<table class="table table-bordered m_0">';
			str+='<thead>';
				str+='<th class="text-center"># Times</th>';
				str+='<th class="text-center">Members</th>';
			str+='</thead>';
			str+='<tr>';
				str+='<td class="text-center">0-2</td>';
				if(result.zerototwo == null || result.zerototwo == 0){
					str+='<td class="text-center"> - </td>';
				}else{
					str+='<td class="text-center">'+result.zerototwo+'</td>';
				}
			str+='</tr>';
			str+='<tr>';
				str+='<td class="text-center">3-5</td>';
				if(result.threetofive == null || result.threetofive == 0){
					str+='<td class="text-center"> - </td>';
				}else{
					str+='<td class="text-center">'+result.threetofive+'</td>';
				}
			str+='</tr>';
			str+='<tr>';
				str+='<td class="text-center">6-10</td>';
				if(result.sixtoten == null || result.sixtoten == 0){
					str+='<td class="text-center"> - </td>';
				}else{
					str+='<td class="text-center">'+result.sixtoten+'</td>';
				}
			str+='</tr>';
			str+='<tr>';
				str+='<td class="text-center">11-15</td>';
				if(result.eleventofiftheen == null || result.eleventofiftheen == 0){
					str+='<td class="text-center"> - </td>';
				}else{
					str+='<td class="text-center">'+result.eleventofiftheen+'</td>';
				}
			str+='</tr>';
			str+='<tr>';
				str+='<td class="text-center">16-30</td>';
				if(result.sixteentothirteen == null || result.sixteentothirteen == 0){
					str+='<td class="text-center"> - </td>';
				}else{
					str+='<td class="text-center">'+result.sixteentothirteen+'</td>';
				}
			str+='</tr>';
			str+='<tr>';
				str+='<td class="text-center">31-50</td>';
				if(result.thirtyonetofifthy == null || result.thirtyonetofifthy == 0){
					str+='<td class="text-center"> - </td>';
				}else{
					str+='<td class="text-center">'+result.thirtyonetofifthy+'</td>';
				}
			str+='</tr>';
		str+='</table>';
		
		$("#numberOfTimesBloodDiv").html(str);
}
/*function buildBloodDonorsCountsSummary(result){
	var str = '';
	var totalCount = 0;
	
	var day = 1;
	str+='<table class="table tableDonors">';
	for(var i in result){
		
		totalCount = result[i].totalCount+totalCount;
    str+='<tr>';
    str+='<td>';
    str+='<div  class="">';
    str+='<h2>'+totalCount+'</h2>';
    str+='<p class="text-capitalize">total applications</p>';
    str+='</div>';
    str+='</td>';
	if(result[i].id == 1){
    str+='<td>';
    str+='<div  class="">';
    str+='<h2>'+result[i].totalCount+'</h2>';
    str+='<p class="text-capitalize">total accepted</p>';
    str+='</div>';
    str+='</td>';
	}else{
		str+='<td>';
    str+='<div  class="">';
    str+='<h2>0</h2>';
    str+='<p class="text-capitalize">total accepted</p>';
    str+='</div>';
    str+='</td>';
	}
	if(result[i].id == 2){
	str+='<td>';
    str+='<div  class="">';
    str+='<h2>'+result[i].totalCount+'</h2>';
    str+='<p class="text-capitalize">total rejected</p>';
    str+='</div>';
    str+='</td>';
	}else{
		str+='<td>';
    str+='<div  class="">';
    str+='<h2>0</h2>';
    str+='<p class="text-capitalize">total rejected</p>';
    str+='</div>';
    str+='</td>';
	}
    str+='</tr>';
    str+='<tr>';
    str+='<td>DAY WISE</td>';
    str+='<td>DAY WISE</td>';
    str+='<td>DAY WISE</td>';
	str+='</tr>';
	for(var j in result[i].bloodBankDashBoardVO){
		var dayWiseCount = 0;
		dayWiseCount = result[i].bloodBankDashBoardVO[j].totalCount+dayWiseCount;
    str+='<tr>';
    str+='<td>DAY - '+day+'';
    str+='<span class="pull-right">'+dayWiseCount+'</span>';
    str+='</td>';
	if(result[i].id == 1){
    str+='<td>DAY - '+day+'';
	if(result[i].bloodBankDashBoardVO[j].totalCount > 0){
    str+='<span class="pull-right">'+result[i].bloodBankDashBoardVO[j].totalCount+'</span>';
	}else{
		 str+='<span class="pull-right">0</span>';
	}
    str+='</td>';
	}
	else{
		 str+='<td>DAY - '+day+'';
    str+='<span class="pull-right">0</span>';
    str+='</td>';
	}
	if(result[i].id == 2){
    str+='<td>DAY - '+day+'';
    str+='<span class="pull-right">'+result[i].bloodBankDashBoardVO[j].totalCount+'</span>';
    str+='</td>';
	}else{
		str+='<td>DAY - '+day+'';
    str+='<span class="pull-right">0</span>';
    str+='</td>';
	}
    str+='</tr>';
	day++;
	}
	
   }
    str+='</table>';
	$("#bldDonorCnts").html(str);
}*/

function buildBloodDonorsCountsSummary(result){
	
	var str='';
	if(result !=null ){
		
	var totalcount1 = result[0].totalCount+result[1].totalCount+result[2].totalCount;
	str+='<table class="table tableDonors">';
                    	str+='<tr>';
                        	str+='<td>';
                            	str+='<div  class="tdBack" style="margin-bottom: -12px;">';
								if(result[0].totalCount != null)
                 	           		str+='<h2>'+result[0].totalCount+'</h2>';
                                	str+='<p class="text-capitalize">total Pending</p>';
                                str+='</div>';
                            str+='</td>';
                            str+='<td>';
                            	str+='<div  class="tdBack" style="margin-bottom: -12px;">';
								if(result[1].totalCount != null)
                 	           		str+='<h2>'+result[1].totalCount+'</h2>';
                                	str+='<p class="text-capitalize">total Approved</p>';
                               str+=' </div>';
                            str+='</td>';
                            str+='<td>';
                            	str+='<div  class="tdBack" style="margin-bottom: -12px;">';
								if(result[2].totalCount != null)
                 	           		str+='<h2>'+result[2].totalCount+'</h2>';
                                	str+='<p class="text-capitalize">total rejected</p>';
                                str+='</div>';
                            str+='</td>';
                            str+='<td>';
                            	str+='<div  class="tdBack" style="margin-bottom: -12px;">';
									str+='<h2>'+totalcount1+'</h2>';
                                	str+='<p class="text-capitalize">total applications</p>';
                                str+='</div>';
                            str+='</td>';
                        str+='</tr>';
                        str+='<tr>';
                        	str+='<td>DAY WISE</td>';
                            str+='<td>DAY WISE</td>';
                            str+='<td>DAY WISE</td>';
                            str+='<td>DAY WISE</td>';
                        str+='</tr>';
						
						str+='<tr>';
							str+='<td>';
								var rslt1 = result[0].bloodBankDashBoardVO;
								for(var s in rslt1){
									str+='<table class="table" style="margin:0px;">';
									str+='<tr>';
									str+='<td>Day - '+(parseInt(s)+1)+'';
									if(rslt1[s].totalCount != null)
										str+='<span class="pull-right">'+rslt1[s].totalCount+'</td>';
									else
										str+='<span class="pull-right"> 0 </td>';
									str+='</tr>';
									str+='</table>';
								}
							str+='</td>';
						
							str+='<td>';
								var rslt1 = result[1].bloodBankDashBoardVO;
								for(var s in rslt1){
									str+='<table class="table" style="background:#00b17d !important;margin:0px;">';
									str+='<tr>';
									str+='<td  style="background:#00b17d !important;border-top:1px solid #00b17d !important;padding:0px;">Day - '+(parseInt(s)+1)+'';
									if(rslt1[s].totalCount != null)
										str+='<span class="pull-right">'+rslt1[s].totalCount+'</td>';
									else
										str+='<span class="pull-right"> 0 </td>';
									str+='</tr>';
									str+='</table>';
								}
							str+='</td>';
						
							str+='<td>';
								var rslt1 = result[2].bloodBankDashBoardVO;
								for(var s in rslt1){
									str+='<table class="table" style="background:#ffa85b  !important;margin:0px;">';
									str+='<tr>';
									str+='<td  style="background:#ffa85b !important;border-top:1px solid #ffa85b !important;padding:0px;">Day - '+(parseInt(s)+1)+'';
									if(rslt1[s].totalCount != null)
										str+='<span class="pull-right">'+rslt1[s].totalCount+'</td>';
									else
										str+='<span class="pull-right"> 0 </td>';
									str+='</tr>';
									str+='</table>';
								}
							str+='</td>';
							str+='<td>';
							var day1=0,day2=0,day3=0;
							for(var s in result){
								for(var k  in result[s].bloodBankDashBoardVO){
									if(k==0){
										day1=day1+result[s].bloodBankDashBoardVO[k].totalCount;
									}
									if(k==1){
										day2=day2+result[s].bloodBankDashBoardVO[k].totalCount;
									}
									if(k==2){
										day3=day3+result[s].bloodBankDashBoardVO[k].totalCount;
									}
								}
							} 
							
								str+='<table class="table"  style="background:#663200  !important;margin:0px;">';
								str+='<tr>';
								str+='<td style="background:#663200 !important;border-top:1px solid #663200 !important;padding:0px;">Day - 1';
								str+='<span class="pull-right">'+day1+'</span></td>';
								str+='</td></tr>';
								str+='</table>';
								str+='<table class="table"  style="background:#663200  !important;margin:0px;">';
								str+='<tr>';
								str+='<td style="background:#663200 !important;border-top:1px solid #663200 !important;padding:0px;">Day - 2';
								str+='<span class="pull-right">'+day2+'</span>';
								str+='</td></tr>';
								str+='</table>';
								str+='<table class="table"  style="background:#663200  !important;margin:0px;">';
								str+='<tr>';
								str+='<td style="background:#663200 !important;border-top:1px solid #663200 !important;padding:0px;">Day - 3';
								str+='<span class="pull-right">'+day3+'</span>';
								str+='</td></tr>';
								str+='</table>';
							str+='</td>';
							
						str+='</tr>';
						/* for (var i in result){
							  str+='<tr>';
							for( var j = 0; j < result[i].bloodBankDashBoardVO.length; j++){
									var dayCount = '0'+(j+1);
								str+='<td>DAY - '+dayCount+'<span class="pull-right">'+result[i].bloodBankDashBoardVO[j].totalCount+'</span></td>';
							}
							str+='</tr>';
						} */
						
						
                       
                   str+=' </table>';
	}
			$("#bldDonorCnts").html(str);	   
}
getApDistrictWiseBloodDonorCounts()
function getApDistrictWiseBloodDonorCounts(){
	$("#districtWiseBloodCountDiv").html('');
	$("#districtWiseBloodCountajaxImage").show();
	var jObj={
			bloodBankCampId : 1,
			stateId : 1,
			type :"Andhra Pradesh"
			}
	$.ajax({
			  type:'GET',
			  url: 'getDistrictWiseBloodDonorCountsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)}
	   }).done(function(result){
		   $("#ApdistrictWiseBloodCountajaxImage").hide();
		  buildApDistrictWiseBloodDonorCounts(result);
		});
}

function buildApDistrictWiseBloodDonorCounts(result){
	
	var str ='';
	if(result.subList !=null ){
		str+='<table class="table table-bordered m_0 extraPadding1">';
			str+='<thead>';
				str+='<th class="text-center text-capitalise">District Id</th>';
				str+='<th class="text-center text-capitalise">District Name</th>';
				str+='<th class="text-center text-capitalise">Count</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.subList){
				str+='<tr>';
				str+='<td class="text-center">'+result.subList[i].id+'</td>';
				str+='<td >'+result.subList[i].name+'</td>';
				if( result.subList[i].count == null || result.subList[i].count == 0){
					str+='<td class="text-center"> - </td>';
				}else{
					str+='<td class="text-center">'+result.subList[i].count+'</td>';
				}
				
				str+='</tr>';
			}
			
			str+='</tbody>';
		str+='</table>';
	}
	
		
	$("#ApdistrictWiseBloodCountDiv").html(str);
}
getTsDistrictWiseBloodDonorCounts();
function getTsDistrictWiseBloodDonorCounts(){
	$("#TsdistrictWiseBloodCountDiv").html('');
	$("#TsdistrictWiseBloodCountajaxImage").show();
	var jObj={
			bloodBankCampId : 1,
			stateId : 1,
			type :"Telangana"
			}
	$.ajax({
			  type:'GET',
			  url: 'getDistrictWiseBloodDonorCountsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jObj)}
	   }).done(function(result){
		   $("#TsdistrictWiseBloodCountajaxImage").hide();
		  buildTsDistrictWiseBloodDonorCounts(result);
		});
}

function buildTsDistrictWiseBloodDonorCounts(result){
	
	var str ='';
	if(result.subList !=null ){
		str+='<table class="table table-bordered m_0 extraPadding">';
			str+='<thead>';
				str+='<th class="text-center text-capitalise">District Id</th>';
				str+='<th class="text-center text-capitalise">District Name</th>';
				str+='<th class="text-center text-capitalise">Count</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.subList){
				str+='<tr>';
				str+='<td class="text-center">'+result.subList[i].id+'</td>';
				str+='<td >'+result.subList[i].name+'</td>';
				if( result.subList[i].count == null || result.subList[i].count == 0){
						str+='<td class="text-center"> - </td>';
				}else{
				
					str+='<td class="text-center">'+result.subList[i].count+'</td>';
				}
				
				str+='</tr>';
			}
			
			str+='</tbody>';
		str+='</table>';
	}
	$("#TsdistrictWiseBloodCountDiv").html(str);
}
</script>
</body>
</html>
