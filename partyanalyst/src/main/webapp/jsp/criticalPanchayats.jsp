<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Critical Panchayats</title>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript"> 
  google.load("visualization", "1", {packages:["corechart"]});
</script>
 <style>
  #mainDiv{
   min-height:400px;
  }
  #selectDiv{
    margin-top: 75px;
  }
  #ajaxImage{
    display:none; 
  }
  
  #buildTable table,#buildGraphTable table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#buildTable table tr:nth-child(even),#buildGraphTable table tr:nth-child(even){background:#EdF5FF;border:1px solid #d3d3d3;}
#buildTable table td,#buildGraphTable table td{border:1px solid #d3d3d3;padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#buildTable table th,#buildGraphTable table th{
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
#buildTable,#buildGraphTable{
	font-family : arial;
	font-size: 13px;
    margin-top:0px;
	padding: 10px 10px 10px 0px;
	 width: 900px;
}
#buildGraphTable{
  width: 600px;
}
#buildTable table th a,#buildGraphTable table th a{
color:#333333;
}
#buildGraph{
  margin-top: 21px;
}
</style>
</head>
<body>
<center> 
 <div id="mainDiv"> 
  <s:if test="{constituenciesList != null && constituenciesList.size >0}">
    <div id="selectDiv"><span style="font-weight:bold;font-size:16px;">Select Constituency : </span> <s:select theme="simple"    name="constituencyId" id="constituencyId" list="constituenciesList" onChange="getAllCriticalPanchayats();" listKey="id" listValue="name" /></div> 
  </s:if>
  <div id="ajaxImage" style="margin-top:35px;"><img src="images/icons/goldAjaxLoad.gif"></div>
  <div id="buildGraph"></div>
  <div id="buildGraphTable"></div>
  <div id="buildTable"></div>
  <div id="partyHighTable"></div>
  <div id="partyLowTable"></div>
  </div>
</center>  
  
  <script type="text/javascript">
   var constituencyId = '${constituencyId}';
   function getAllCriticalPanchayats(){
      var constiId = $("#constituencyId").val();
	  if(constiId > 0){
	    getCriticalPanchayats(constiId);
	  }
	  
   }
   function getCriticalPanchayats(constituencyId){
   $("#buildGraph").html("");
   $("#buildGraphTable").html("");
   $("#buildTable").html("");
   if(constituencyId == 0){
     return;
   }
     $("#ajaxImage").show();
	   var jsObj=
		{	
			   constituencyId : constituencyId
		};
	
	$.ajax({
	      type : "POST",
	      url : "getAllCriticalPanchayats.action?type=criticalPanchayats",
	      data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
	  try{
		  buildCriticalPanchayats(result);
		  }catch(e){
		   $("#ajaxImage").hide();
		   $("#buildGraph").html("");
		   $("#buildGraphTable").html("");
           $("#buildTable").html("");
		   $("#buildGraph").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
		  }
		   $("#ajaxImage").hide();
   });
   }
   function buildCriticalPanchayats(result){
	   if(result != null ){ 
	       buildOrderPriorityGraph(result[0]);
		   buildOrderPriorityTable(result[1]);
		   //window.open(result[2]);
		  
	   }else{
	       $("#ajaxImage").hide();
		   $("#buildGraph").html("");
		   $("#buildGraphTable").html("");
           $("#buildTable").html("");
		   $("#buildGraph").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
	   }
   }
   if(constituencyId !=''){
	   getCriticalPanchayats(constituencyId);
   }else{
    $("#constituencyId").prepend("<option value='0'>Select Constituency</option>");
	$("#constituencyId").val(0);
   }
   
   function buildOrderPriorityTable(result){
      var str ="<div><h5>Panchayats - Top Priority & Opportunity</h5></div><br/>";
	  str+="<table>";
	  str+="<tr>";
	  str+="  <th rowspan='2'>Panchayat</th>";
	  str+="  <th rowspan='2'>Total Voters</th>";
	  str+="  <th colspan='2'>Targeted 2014 vs 2009</th>";
	  str+="  <th colspan='2'>Difference</th>";
	  str+="</tr>";
	  str+="<tr>";
	  str+="  <th>Targeted Votes</th>";
	  str+="  <th>2009 TDP Votes</th>";
	  str+="  <th>Votes</th>";
	  str+="  <th>%</th>";
	  str+="</tr>";
      for(var i in result){
	  if(result[i].type == "Highly Critical" || result[i].type == "Critical"){
	    str+="<tr>";
		  str+="<td>"+result[i].name+"</td>";
		  str+="<td>"+result[i].totalVoters+"</td>";
		  str+="<td style='color:#44d43b;'>"+result[i].targetedVoters+"</td>";
		  str+="<td>"+result[i].previousVoters+"</td>";
		  str+="<td>"+result[i].opportunity+"</td>";
		  str+="<td>"+result[i].opportunityPerc+"</td>";
		str+="</tr>";
		}
	  }
	  str+="</table>";
	  $("#buildTable").html(str);
   }
   
   function buildOrderPriorityGraph(result)
 {
  
  if(result == null || result.length == 0)
   return;

   var data = new google.visualization.DataTable();
        data.addColumn('string', 'name');
        data.addColumn('number', 'value');
      
		data.addRows(result.length);

		for(var j = 0 ; j< result.length ; j++){		
		var name = result[j].name;
		var val = result[j].totalVoters;
		  data.setValue(j,0,name);
		  data.setValue(j,1,val);
		}
        // Set chart options
		var title = 'Strategy Suggestive Model'; 
        var options = {'title':title,
                       'width':800,
                       'height':400,
					     is3D: true};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('buildGraph'));
        chart.draw(data, options);
		var str ="<div><h5>Strategy Suggestive Model Table</h5></div><br/>";
		str+="<table>";
		str+="  <tr>";
		str+="    <th>Status</th>";
		str+="    <th>No of Panchayats</th>";
		str+="    <th>Gainable Votes %</th>";
		str+="    <th>Gainable Votes</th>";
		str+="  </tr>";
		for(var j = 0 ; j< result.length ; j++){	
         str+="  <tr>";
		 str+="    <td>"+result[j].name+"</td>";
		 str+="    <td>"+result[j].totalVoters+"</td>";
		 if(result[j].opportunityPerc > 0)
		  str+="    <td>"+result[j].opportunityPerc.toFixed(2)+"</td>";
		 else
		  str+="    <td>N/A</td>";
		 if(result[j].opportunityPerc > 0)
		   str+="    <td>"+result[j].opportunity+"</td>";
		 else
		   str+="    <td></td>";
         str+="  </tr>";
        }
        str+="</table><br/>";		
      $("#buildGraphTable").html(str);
       
}
</script>
</body>
</html>