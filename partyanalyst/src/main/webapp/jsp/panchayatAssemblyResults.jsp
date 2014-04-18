<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>2009 ASSEMBLY VS 2013 PANCHAYAT RESULTS REPORT</title>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript"> 

  google.load("visualization", "1", {packages:["corechart"]});
</script>
<style type="text/css">

  #ajaxImage{
    display:none; 
  }
  div
  {
   font-family : arial;
  }
#buildTableDiv table,#buildTableDiv1 table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#buildTableDiv table tr:nth-child(even),#buildTableDiv1 table tr:nth-child(even),#rank1summary table tr:nth-child(even),#rank2summary table tr:nth-child(even){background:#EdF5FF;border:1px solid #d3d3d3;}

#buildTableDiv table td,#buildTableDiv1 table td,#rank1summary table td,#rank2summary table td{border:1px solid #d3d3d3;padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color:#676A67;}

#buildTableDiv table th,#buildTableDiv1 table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 4px;
    padding-right: 4px;
    padding-top: 4px;
    text-align: left;
	}
#buildTableDiv,#buildTableDiv1{
	font-size: 13px;
    margin-top:0px;
	padding: 30px 10px 10px 0px;
	 width: 900px;
}
</style>

</head>
<body>
<div id="mainDiv" align="center">
     <div id="errorMsgDiv" >&nbsp;</div><br><br>
       <s:if test="{constituenciesList != null && constituenciesList.size >0}">
        <div id="selectDiv"><span style="font-weight:bold;font-size:16px;">Select Constituency : </span> <s:select theme="simple"    name="constituencyId" id="constituencyId" list="constituenciesList" onChange="getPanchayatAssemblyResultDetails();" listKey="id" listValue="name" /></div> 
       </s:if>
	 </div>
	 <div id="ajaxImage" style="margin-top:35px;" align="center"><img src="images/icons/goldAjaxLoad.gif"></div>
</div><br>

<div class="row-fluid">

<div id="reportId" style='color:#23318B;display:none;margin-top:10px;margin-left:45px;'><h4 style="margin-left:145px;">Summary Report</h4><br/>
<div class="span6" id="rank1summary" style='padding-left:120px'></div>
<div class="span4"id="rank2summary"></div></div></div>

<div id="chartDiv">
<div class="span6" id="rank1SummaryPieChart" style='padding-left:55px'></div>
<div class="span4"id="rank2SummaryPieChart"></div></div>


<div id="headingDiv" style="display:none;margin-top:10px;margin-left:119px;"><b><center><div class="span8" style="color:#23318B;"><h5>Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat</h5></center></b></span><div class="span4"  style="margin-left: -47px;"><input class="btn btn-small btnStyle" type="button"  onClick="showChart();" value="Show/Hide Chart"></div></div>

<div id="buildGraphDiv" style="margin-left:100px;margin-top:65px;clear:both" align="center"></div><br>
<div id="buildTableDiv" style="margin-left:194px;clear:both"></div><br>
<div id="buildTableDiv1" style="margin-left:194px;"></div>

<script>

var constituencyId = '${constituencyId}';
function getPanchayatAssemblyResultDetails()
{

    $("#headingDiv").hide();
	$("#chartDiv").hide()
	$("#rank1summary").html("");
    $("#rank2summary").html("");
	$("#buildGraphDiv").html("");
	$("#buildTableDiv").html("");
	$("#buildTableDiv1").html("");

	    var constituencyId = $("#constituencyId").val();
		$("#ajaxImage").show();
		  var jsObj = 
	       {
		      constituencyId:constituencyId,
              effectElectionId : 38,
			  partyId:872,
	       }	
		    $.ajax({
				type : "POST",
				url : "getPanchayatAssemblyResultDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
					if(result != null && result.length > 0)
					{
					  $("#ajaxImage").hide();
					  $("#errorMsgDiv").html('');
					  summaryRank1(result);
					  summaryRank2(result);
					  buildChartResult(result);
					  buildTableResult(result,1);
                      buildTableResult1(result,2)
					}
					else{
						  $("#ajaxImage").hide();
						  $("#reportId").hide();
		                  $("#buildGraphDiv").html("");
						  $("#rank1summary").html("");
						  $("#rank2summary").html("");
		                  $("#buildTableDiv").html("");
						  $("#buildTableDiv1").html("");
						  $("#chartDiv").hide();
		                  $("#errorMsgDiv").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
					  }
			});
}
      

function buildChartResult(result)
{

	$("#headingDiv").show();
     var data = new google.visualization.DataTable();
     data.addColumn('string', 'name');
     data.addColumn('number', '2009');
	 data.addColumn('number', '2013');
	 data.addRows(result.length);
		
	 for(var j = 0 ; j< result.length ; j++)
	 {	
		if(result[j].rank == 1)
		{
			if(result[j].winPartyName == "TDP")
			{
			   var name = result[j].name;
			   var val  = result[j].margin;
			   var val1  =result[j].margin;
			}
			else{
			   var name = result[j].name;
			   var val  = result[j].margin;
			   var val1   = parseFloat(result[j].margin)/2;
			}								
		}
		else
		{
			if(result[j].winPartyName == "TDP")
			{
			   var name = result[j].name;
			   var val  = result[j].margin;
			   var val1   = parseFloat(result[j].margin)*2;
			}
			else{
			    var name = result[j].name;
				var val   = result[j].margin;
				var val1   = result[j].margin;
			}
		}
		data.setValue(j,0,name);
		data.setValue(j,1,val);
		data.setValue(j,2,val1);
	 }
      var view = new google.visualization.DataView(data);
      view.setColumns([0,1,{ calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },2,{ calc: "stringify",
                         sourceColumn: 2,
                         type: "string",
                         role: "annotation" }
		  	  ]);

        var options = {
			vAxis: {
              title: 'PANCHAYAT',
			  'fontSize': 18,
			  titleTextStyle: {color: 'red'},
			  textStyle: {color: '#140E7C'},
			  fontStyle:'bold'

            },
            hAxis: {
              title: 'MARGIN',
			  titleTextStyle: {color: 'red',fontSize: '14'},
            },
		    'width': '100%',
            'height': 3400,
            'fontSize': 12,
			'fontColor':'green',
		   'chartArea': {top: 50, right: 50, bottom: 30, height:3400, width:'75%'},
			series: [{color: '#59A2BE', visibleInLegend: true}, {color: '#F06081', visibleInLegend: true}]
        };

         var chart = new google.visualization.BarChart(document.getElementById('buildGraphDiv'));       
	     chart.draw(view, options); 
		 $("#buildGraphDiv").hide();
		  $("#marginDiv").hide();

}

function buildTableResult(result,rank)
{
		var str ="<div style='color:#23318B;'><b><h5 >Election Results Comparision Table b/w 2009(TDP @ Top Position) Assembly & 2013 Panchayat</h5></b></div><br/>";
		str+="<table id='tableId'><thead><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>Margin</th>";
		str+="<th>2013 Total Votes</th>";
		str+="<th>Win Party Name</th>";
		str+="  </tr></thead></tbody>";
		
		for(var j = 0 ; j< result.length ; j++){	
	      if(result[j].rank == rank){
         str+="<tr>";
		 str+="<td>"+result[j].name+"</td>";
		 str+="<td>"+result[j].totalVoters+"</td>";
		 str+="<td>"+result[j].totalValidVotes+"</td>";
		 str+="<td>"+result[j].selectedPartyTotalVoters+"</td>";
		 str+="<td>"+result[j].margin+"</td>";
		 str+="<td>"+result[j].winPartyTotal+"</td>";
		  if(result[j].winPartyName == "TDP")
		 str+="<td style='background:green;color:white'>"+result[j].winPartyName+"</td>";
		 else
		str+="<td style='background:#FFC0CB;color:white'>"+result[j].winPartyName+"</td>";
		}
        }
		 str+="</tbody>";
        str+="</table><br/>";		
      $("#buildTableDiv").html(str);
	  $('#tableId').dataTable({"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 25,
		"aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
		  "aoColumns": [null,null,null,null,null,null,null] 
		});
		
		window.open(result[0].url);

}

function buildTableResult1(result,rank)
{
		var str ="<div style='color:#23318B'><b><h5 >Election Results Comparision Table b/w 2009(TDP @ Lower Positions) Assembly & 2013 Panchayat</h5></b></div><br/>";
		str+="<table id='table1Id'>";
		str+="<thead><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>Margin</th>";
		str+="<th>2013 Total Votes</th>";
		str+="<th>Win Party Name</th>";
		str+="  </tr></thead><tbody>";
		
		for(var j = 0 ; j< result.length ; j++){	
	     if(result[j].rank == rank){
			
         str+="<tr>";
		 str+="<td>"+result[j].name+"</td>";
		 str+="<td>"+result[j].totalVoters+"</td>";
		 str+="<td>"+result[j].totalValidVotes+"</td>";
		 str+="<td>"+result[j].selectedPartyTotalVoters+"</td>";
		 str+="<td>"+result[j].margin+"</td>";
		 str+="<td>"+result[j].winPartyTotal+"</td>";
		 if(result[j].winPartyName == "TDP")
		 str+="<td style='background:green;color:white'>"+result[j].winPartyName+"</td>";
		 else
		str+="<td style='background:#FFC0CB;color:white'>"+result[j].winPartyName+"</td>";
		}
        }
		 str+="</tbody>";
        str+="</table>";		
      $("#buildTableDiv1").html(str);
	  $('#table1Id').dataTable({"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 25,
		"aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
		  "aoColumns": [null,null,null,null,null,null,null] 
		});
}
var flip =0;
function showChart()
{
   $('#buildGraphDiv').toggle(flip++ % 2 === 0);

}

 function summaryRank1(result)
 {
     $("#reportId").show();
	 $("#chartDiv").show();
	 var topCount = 0;
	 var regained =0;
	 var lost = 0;
	 var regaintotalvotes =0;
	 var losttotalvotes = 0;
	 var count = 0;
	 var reg = 0;
	 
	 for(var j = 0 ; j< result.length ; j++){

		if(result[j].rank == 1){
				  if(result[j].winPartyName == "TDP")
				  {
					
					count ++;
					topCount =  count;
					regaintotalvotes = result[j].totalValidVotes + regaintotalvotes;
				  }
		  
				  else
				 {
					   reg++;
					   regained = reg;
					  losttotalvotes = result[j].totalValidVotes + losttotalvotes;
					  
				 }
		}
	 }
	var totalPanchayats = topCount + regained;
	 lost = totalPanchayats - topCount;

	
	 var str = '';
	 str+='<table>';
	 str+='<tr>';
	 str+='<td>In 2009 TDP @ top position Panchayats ';
	  str+='</td>';
	   str+='<td>'+totalPanchayats+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Win Panchayats in 2013';
	  str+='</td>';
	  str+='<td>'+ topCount+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Lost in 2013';
	  str+='</td>';
	   str+='<td>'+lost+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Total votes in Win Panchayats';
	  str+='</td>';
	  str+='<td>'+regaintotalvotes+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Total votes in lost Panchayats';
	  str+='</td>';
	  str+='<td>'+losttotalvotes+'</td>';
	 str+='</tr>';
	 str+='</table><br><br>';
	 $("#rank1summary").html(str);

	 	 var data = new google.visualization.DataTable();
        data.addColumn('string', 'name');
        data.addColumn('number', 'value');
      
		data.addRows(2);
		  data.setValue(0,0,"Win Panchayats in 2013");
		  data.setValue(0,1,topCount);
		  data.setValue(1,0,"Lost Panchayats in 2013");
		  data.setValue(1,1,lost);
		
		var title = ''; 
        var options = {'title':title,
                       'width':550,
                       'height':400};

        var chart = new google.visualization.PieChart(document.getElementById('rank1SummaryPieChart'));
        chart.draw(data, options);
 }

 function summaryRank2(result)
 {
	 var topCount = 0;
	 var regained =0;
	 var lost = 0;
	 var regaintotalvotes =0;
	 var losttotalvotes = 0;
	 var count = 0;
	 var reg = 0;
	 
	 for(var j = 0 ; j< result.length ; j++){

		if(result[j].rank == 2){
				  if(result[j].winPartyName == "TDP")
				  {
					
					count ++;
					topCount =  count;
					regaintotalvotes = result[j].totalValidVotes + regaintotalvotes;
				  }
		  
				  else
				 {
					   reg++;
					   regained = reg;
					  losttotalvotes = result[j].totalValidVotes + losttotalvotes;
					  
				 }
		}
	 }
	var totalPanchayats = topCount + regained;
	 lost = totalPanchayats - topCount;
	
	 var str = '';
	 str+='<table>';
	 str+='<tr>';
	 str+='<td>In 2009 TDP @ second or lower position Panchayats ';
	  str+='</td>';
	   str+='<td>'+totalPanchayats+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Regained Panchayats in 2013';
	  str+='</td>';
	  str+='<td>'+ topCount+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Lost in 2013';
	  str+='</td>';
	   str+='<td>'+lost+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Total votes in Regained Panchayats';
	  str+='</td>';
	  str+='<td>'+regaintotalvotes+'</td>';
	 str+='</tr>';
	  str+='<tr>';
	 str+='<td>Total votes in lost Panchayats';
	  str+='</td>';
	  str+='<td>'+losttotalvotes+'</td>';
	 str+='</tr>';
	 str+='</table>';
	 $("#rank2summary").html(str);

	 var data = new google.visualization.DataTable();
        data.addColumn('string', 'name');
        data.addColumn('number', 'value');
      
		data.addRows(2);
		  data.setValue(0,0,"Regained Panchayats in 2013");
		  data.setValue(0,1,topCount);
		  data.setValue(1,0,"Lost Panchayats in 2013");
		  data.setValue(1,1,lost);
		
		var title = ''; 
        var options = {'title':title,
                       'width':550,
                       'height':400};

        var chart = new google.visualization.PieChart(document.getElementById('rank2SummaryPieChart'));
        chart.draw(data, options);
 }
</script>
</body>
</html>
