<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>2009 ASSEMBLY VS 2013 PANCHAYAT RESULTS REPORT</title>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript"> 
  google.load("visualization", "1", {packages:["corechart"]});
</script>
<style type="text/css">

  #ajaxImage{
    display:none; 
  }
  
#buildTableDiv table,#buildTableDiv1 table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#buildTableDiv table tr:nth-child(even),#buildTableDiv1 table tr:nth-child(even){background:#EdF5FF;border:1px solid #d3d3d3;}
#buildTableDiv table td,#buildTableDiv1 table td{border:1px solid #d3d3d3;padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#buildTableDiv table th,#buildTableDiv1 table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 4px;
    padding-right: 4px;
    padding-top: 4px;
    text-align: left;
	color:#333333;
	border:1px solid #d3d3d3;
	}
#buildTableDiv,#buildTableDiv1{
	font-family : arial;
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
</div>
<div id="headingDiv" style="display:none;margin-top:40px;"><b><center><h5>Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat</h5><br/><br/><div style="margin-bottom:2px;"><i><font size="2" color="red">MARGIN</i></font></div></center></b></div>
<div id="buildGraphDiv" style="margin-left:100px;"align="center"></div>
<div id="buildTableDiv" style="margin-left:194px;"></div>
<div id="buildTableDiv1" style="margin-left:194px;"></div>

<script>

var constituencyId = '${constituencyId}';
function getPanchayatAssemblyResultDetails()
{
    $("#headingDiv").hide();
	$("#buildGraphDiv").html("");
	$("#buildTableDiv").html("");
	$("#buildTableDiv1").html("")

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
					  buildChartResult(result);
					  buildTableResult(result,1);
                      buildTableResult1(result,2)
					}
					else{
						  $("#ajaxImage").hide();
		                  $("#buildGraphDiv").html("");
		                  $("#buildTableDiv").html("");
						  $("#buildTableDiv1").html("");
		                  $("#buildGraphDiv").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
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
			  'fontStyle':'bold'

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
}

function buildTableResult(result,rank)
{
		var str ="<div><b><center><h5>Election Results Comparision Table b/w 2009(TDP @ Top Positions) Assembly & 2013 Panchayat</h5></center></b></div><br/>";
		str+="<table><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>Margin</th>";
		str+="<th>2013 Total Votes</th>";
		str+="<th>Win Party Name</th>";
		str+="  </tr>";
		
		for(var j = 0 ; j< result.length ; j++){	
	      if(result[j].rank == rank){
         str+="<tr>";
		 str+="<td>"+result[j].name+"</td>";
		 str+="<td>"+result[j].totalVoters+"</td>";
		 str+="<td>"+result[j].totalValidVotes+"</td>";
		 str+="<td>"+result[j].selectedPartyTotalVoters+"</td>";
		 str+="<td>"+result[j].margin+"</td>";
		 str+="<td>"+result[j].winPartyTotal+"</td>";
		 str+="<td>"+result[j].winPartyName+"</td>";
		  }
        }
        str+="</table><br/>";		
      $("#buildTableDiv").html(str);

}

function buildTableResult1(result,rank)
{
		var str ="<div><b><center><h5>Election Results Comparision Table b/w 2009(TDP @ Lower Positions) Assembly & 2013 Panchayat</h5></center></b></div><br/>";
		str+="<table><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>Margin</th>";
		str+="<th>2013 Total Votes</th>";
		str+="<th>Win Party Name</th>";
		str+="  </tr>";
		
		for(var j = 0 ; j< result.length ; j++){	
	     if(result[j].rank == rank){
         str+="<tr>";
		 str+="<td>"+result[j].name+"</td>";
		 str+="<td>"+result[j].totalVoters+"</td>";
		 str+="<td>"+result[j].totalValidVotes+"</td>";
		 str+="<td>"+result[j].selectedPartyTotalVoters+"</td>";
		 str+="<td>"+result[j].margin+"</td>";
		 str+="<td>"+result[j].winPartyTotal+"</td>";
		 str+="<td>"+result[j].winPartyName+"</td>";
		}
        }
        str+="</table><br/>";		
      $("#buildTableDiv1").html(str);
}
</script>
</body>
</html>
