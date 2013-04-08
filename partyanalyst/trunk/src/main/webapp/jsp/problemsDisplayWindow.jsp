<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>  
<script type="text/javascript" src="js/yahoo/element-min.js"></script> 	
<script src="js/yahoo/resize-min.js"></script> 
<script src="js/yahoo/layout-min.js"></script>  
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>   
<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- Skin CSS files resize.css must load before layout.css -->  
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
<style type="text/css">
#mainDiv
{
	color: #005580;
    font-family: arial;
    font-size: 17px;
    font-weight: bolder;
    margin-bottom: 10px;
}
.leftmargin
{
	margin-bottom: 10px;
}
.paginationIds{
    margin-left: 5px;
}
.btn {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background-color: #F5F5F5;
    background-image: linear-gradient(to bottom, #FFFFFF, #E6E6E6);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) #A2A2A2;
    border-image: none;
    border-radius: 4px 4px 4px 4px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);
    color: #333333;
    cursor: pointer;
    display: inline-block;
    font-size: 14px;
    line-height: 20px;
    margin-bottom: 0;
    padding: 4px 12px;
    text-align: center;
    text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
    vertical-align: middle;
}
</style>
<title>Problems</title>
<script type="text/javascript">
var  locationValue    = '${locationValue}';
var  locationId       = '${locationId}';
var  status           = '${status}';
var  srcId            = '${srcId}';
var  title            = '${title}';
var  maxIndex = 5;
var count;
var custom_paginator = {
	maxIndex:"",
	startIndex:"",
	ajaxCallURL:"",
	initialParams:"",
	resultsShown:"",
	callBackFunction:"",
	jObj:{},
	results:{},
	totalRecords:"",
	paginatorElmt:"",		
	paginator:function(obj){
		this.startIndex = obj.startIndex;
		this.ajaxCallURL = obj.ajaxCallURL;
		this.jObj = obj.jObj;
		this.callBackFunction = obj.callBackFunction;
		this.paginatorElmt = obj.paginatorElmt;
		this.maxIndex = obj.maxIndex;		
	},	
	doAjaxCall:function(start){
		
		var url = this.ajaxCallURL+"&startIndex="+start+"&maxIndex="+this.maxIndex;
		
		var callback = {	
		
	    success : function( o ) {
		
			try 
			{				
				results = YAHOO.lang.JSON.parse(o.responseText);
				
				if(results != null && results.length>0)
				    this.totalRecords = parseInt(results[0].userProblems);	
				else
                     this.totalRecords = 0;
					 this.buildPaginator();
				this.callBackFunction();
				
			}
			catch (e)
			{   		
				//alert("Invalid JSON result" + e);   
			}  
		},
		scope : this,
		failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				  }
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	},
	initialize:function (){		
		this.doAjaxCall(this.startIndex);
	},
	buildPaginator:function()
	{
	
		var paginatorElmt = document.createElement('Div');
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		var iteration = Math.ceil(this.totalRecords/this.maxIndex);		
		var countIndex = this.startIndex;
		var str = '';

		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
			{			
				str += '<a href="javascript:{}" id="customPaginationId'+i+'" onclick="copyId(this.id);custom_paginator.doAjaxCall('+countIndex+')" class="paginationIds btn btn-info">'+i+'</a>';
				countIndex+=this.maxIndex;
			}
		}
		
		if(document.getElementById("custom_paginator_class")!=null)	
     	  document.getElementById("custom_paginator_class").innerHTML = str;
		if(clickid != null)
		{
		 $("#"+clickid).addClass('pagenationStyle');
		}
		else
		{
		  $("#customPaginationId1").addClass('pagenationStyle');
		}
	}
};
function getProblemDtailsByStatus()
{	
	var jObj=
	{
		locationValue:locationValue,
		locationId:locationId,
		
		status : status,
		title : title,
		srcId:srcId,
		startIndex:0,
		maxIndex:10,
		
		task:"getProblemDetailsByLocation"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getProblemsByLocation.action?"+rparam;
	
	
	
	custom_paginator.paginator({
		   startIndex:0,
		   maxIndex:this.maxIndex,
		   jObj:jObj,
		   ajaxCallURL:url,
		   paginatorElmt:"custom_paginator_class",
		   callBackFunction:function(){
	          buildProblemDetailsByStatus(results);
		   }
	     });	
	   
	   custom_paginator.initialize();
}
function buildProblemDetailsByStatus(result)
{
	count = result[0].userProblems;
	var status = result[0].status;
	$('#headingDiv').html('<div style="font-family: arial;font-size: 15px;"><b style="color:green;">STATUS : </b><span>'+result[0].status+'</span></div><div style="margin-left: 171px;margin-top: -20px;font-family: arial;font-size: 15px;"><b style="color:green;">TOTAL COUNT  : </b><span>'+result[0].userProblems+'</span></div>');
	
	var str='';
	var title = "New";
	var divEle = document.getElementById("problemsShowDIV");
	if(result != null)
	{
	for(var i in result)
	{
		str += '<div class ="widget-block">';	
		str += '<div  style="padding: 5px; background: none repeat scroll 0% 0% #FFFFFF; margin-bottom: 10px; border-radius: 4px 4px 4px 4px;">';
		str+='<div class="leftmargin"><a target="_blank" title="Click Here To View Problem Complete details" class ="problemTitleClass" href="completeProblemDetailsAction.action?problemId='+result[i].problemId+'" ><b style="font-size: 16px;font-family: arial;color:palevioletred;">'+(result[i].problem)+'</b></a></div>';
		str+='<div class="leftmargin"><span class="pull-left" style="color:#51A451;margin-right: 4px; font-size: 16px;font-family: arial;">Existing From:</span><span>'+result[i].existingFrom+'</span><span style="margin-left:10px;color:#51A451;margin-right: 4px;font-size: 16px;font-family: arial;">Identified On:</span><span>'+result[i].identifiedOn+'</span><div class="star pull-right"></div><input type="hidden" style="display:none;" value='+result[i].averageRating.avgRating +'" >';
		str+='</div>';
		
		str += '<div class="leftmargin"><font style="color:#51A451;font-size: 16px;font-family: arial;">Description: </font><span style="font-family:arial;">'+result[i].description+' </span></div>';
		
		
	    str += '<div class="leftmargin"><font style="color:#51A451;font-size: 16px;font-family: arial;">Posted by: </font><span style="font-size: 16px;font-family: arial;">'+initialCap(result[i].name)+' '+initialCap(result[i].lastName)+'</span><font style="color:#51A451;font-size: 16px;"font-family: arial;>&nbsp;&nbsp;&nbsp;Ref NO:</font> '+result[i].referenceNo;
	    
	    if(result[i].problemLocation != null)
	    str+='<font style="color:#51A451;font-size:16px;font-family: arial;">&nbsp;&nbsp;&nbsp;Location: </font><span style="font-family: arial; font-size: 16px;">'+initialCap(result[i].problemLocation);
	    str+='</div>';
		str+='</div>';
	 str += '</div>';
	 
	}
	
	 divEle.innerHTML = str;
	}
}
getProblemDtailsByStatus();
</script>
</head>
<body  style="background: none repeat scroll 0% 0% rgb(238, 238, 238);">
<div id="mainDiv">PROBLEM DETAILS</div>
<div id="headingDiv">
<span id="statusId" style="color:green;">STATUS : </span>
<span id="countId" style="color:green">COUNT : </span>
</div>
<div id="custom_paginator_class" class="paginatorElmtClass" style="margin-top:10px;margin-left:20px;margin-bottom: 30px;"></div>
<div id="problemsShowDIV">
</div>
</body>
</html>
