<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  
<title>Party Analyst</title>

<style type="text/css">
#demoRequestData table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#demoRequestData table tr:nth-child(even){background:#EdF5FF;}
#demoRequestData table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#demoRequestData table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}
#mainDiv{width: 950px; margin: 25px auto; float: none;}
.headingCls{ text-align: center;margin-bottom: 17px;}
.headingSpan{ color: #fff; font-weight: bold; padding: 2px 30px 3px; border-radius: 3px 3px 3px 3px; font-size: 18px; background: none repeat scroll 0 0 #06ABEA;}

</style>
</head>
<body>
<div id="mainDiv">
<div class="headingCls">
 <span class="headingSpan">Aspirant Demo Requests</span></div>
<div id="errorMsgDiv"></div>
<div id="demoRequestData">
 
</div>
</div>
<script type="text/javascript">
function getDemoRequestData()
{
	var jsObj=
	{		
	  task:"getDemoRequestData"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDemoRequestDataAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
	 var myResults;

	 var callback = {			
	               success : function( o ) {
					try {												
						myResults = YAHOO.lang.JSON.parse(o.responseText);					
						if(jsObj.task == "getDemoRequestData")
						  buildDemoRequestData(myResults);
						else if(jsObj.task == "deleteDemoRequest")
						 showDemoRequestDeleteStatus(myResults);
								
						
					}catch (e) {
					     
						}  
               },
               scope : this,
               failure : function( o ) {
                			//alert( "Failed to load result" + o.status + " " + o.statusText);
                         }
               };

YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function buildDemoRequestData(results)
{
	$("#demoRequestData").html();
	if(results == null || results.length == 0)
	{
     $("#demoRequestData").html('No Data Found.');
	 return;
	}
	var str = '';
    str +='<table id="demoReqTab">';
	str+='<thead>';
	str +='<tr>';
	str +='<th>Aspirant</th>';
	str +='<th>Constituency</th>';
	str +='<th>Mobile No</th>';
	str +='<th>Email</th>';
	str +='<th>Requested Time</th>';
	str +='<th>Assigned To</th>';
	str +='<th>View Details</th>';
	str +='<th>Actions</th>';
	str +='<th>Delete</th>';
	str +='</tr>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in results)
	{
		str +='<tr>';
		
		str +='<td>'+results[i].name+'</td>';
		str +='<td>'+results[i].constituency+'</td>';
		str +='<td>'+results[i].mobileNo+'</td>';
		str +='<td>'+results[i].email+'</td>';
		str +='<td>'+results[i].requestedTime+'</td>';
		str +='<td>'+results[i].assignedTo+'</td>';
		str +='<td><img src="images/icons/edit.png" onclick="editDemoRequestData('+results[i].id+')"/></td>';
		str +='<td><a href="javascript:{}" onclick="editDemoRequestData('+results[i].id+')">'+results[i].count+'</a> - <a href="javascript:{}" onclick="addDemoRequestActions('+results[i].id+')">Add</a></td>';
		str +='<td><img src="images/icons/delete.png" onclick="deleteDemoRequestData('+results[i].id+')"/></td>';
		str +='</tr>';
	}
	str+='</tbody>';
	$("#demoRequestData").html(str);
	
	$('#demoReqTab').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null,null
		] 
		});

}

function editDemoRequestData(id)
{
    var urlStr="demoRequestDataUpdateAction.action?demoRequestId="+id+"";
	var updateBrowser = window.open(urlStr,"demoRequestUpdate"+id+"","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}


function addDemoRequestActions(id)
{
    var urlStr="addDemoRequestActions.action?demoRequestId="+id+"";
	var updateBrowser = window.open(urlStr,"addDemoRequestActions"+id+"","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}

function deleteDemoRequestData(id)
{
  var jsObj=
	{
	  demoRequestId:id,
	  task:"deleteDemoRequest"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteDemoRequestAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function showDemoRequestDeleteStatus(results)
{
  $("#errorMsgDiv").html('');
  if(results. resultCode == 0)
     getDemoRequestData();

  else
  {
   $("#errorMsgDiv").html('Error Occured! Try Again.');
   return;
  }
}
							
getDemoRequestData();
function parentWindowRefresh()
{
  getDemoRequestData();	
}
</script>
</body>
</html>