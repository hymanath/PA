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
	padding: 10px 10px 10px 0px;
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
<div id="buildGraphDiv" style="margin-left:100px;"align="center"></div>
<div id="buildTableDiv" style="margin-left:194px;"></div>
<div id="buildTableDiv1" style="margin-left:194px;"></div>

<script>

var constituencyId = '${constituencyId}';
function getPanchayatAssemblyResultDetails()
{
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
		                  $("#buildGraphDiv").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
					  }
			});
}
      
var dataArray = new Array();

function buildChartResult(result)
{

	for(var j in result)
	{
			var data = 
			{
				name : result[j].name+"-2009",
				value : result[j].margin
			}
			dataArray.push(data);
			var name = "";
			var val = "";
			if(result[j].rank == 1)
			{
					  
			   if(result[j].winPartyName == "TDP")
				{
					  var name = result[j].name+"-2013";
					  var val  = result[j].margin;
				}
				else{
					var name = result[j].name+"-2013";
					 var val   = parseFloat(result[j].margin)/2;
				}								
			}
			else
			{
			   if(result[j].winPartyName == "TDP")
				{
				   var name = result[j].name+"-2013";
				   var val   = parseFloat(result[j].margin)*2;
				}
				else{
					var name = result[j].name+"-2013";
					var val   = result[j].margin;
				}
			  ;
			}
			var data1 = 
			{
				name : name,
				value : val
			}

			dataArray.push(data1);
	}
	var data = new google.visualization.DataTable();
    data.addColumn('string', 'name');
	data.addColumn('number', 'Margin Values');
	data.addRows(dataArray.length);
	for(var i = 0 ; i < dataArray.length ;i++)
	{
		data.setValue(i,0,dataArray[i].name);
		data.setValue(i,1,dataArray[i].value);
	}

  
		var title = 'Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat'; 
        var options = {'title':title,
			vAxis: {
              title: 'PANCHAYAT',
			  'fontSize': 18
            },
            hAxis: {
              title: 'MARGIN'
            },
		    'width': '100%',
            'height': 1800,
            'fontSize': 12,
		    'chartArea': {top: 50, right: 50, bottom: 30, height:1800, width:'75%'}

        };
         var chart = new google.visualization.BarChart(document.getElementById('buildGraphDiv'));       
	     chart.draw(data, options); 
}
function buildTableResult(result,rank)
{
		var str ="<div><b><center><h5>Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat</h5></center></b></div><br/>";
		str+="<table><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>margin</th>";
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
		var str ="<div><b><center><h5>Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat</h5></center></b></div><br/>";
		str+="<table><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>margin</th>";
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
