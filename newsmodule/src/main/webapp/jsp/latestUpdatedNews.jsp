<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst</title>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
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

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div>
<div id="latestNewsDiv"></div>
<div id="custom_paginator_class" class="paginatorElmtClass" style="margin-top:10px;margin-left:20px;margin-bottom: 30px;"></div>
</div>
<script type="text/javascript">

var contentId = "${contentId}";
var startIndex=0;
	var maxIndex=5;

	var clickid = null;
function copyId(id)
{
  clickid = id;
}

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
				    this.totalRecords = parseInt(results[0].count);	
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
				str += '<a href="javascript:{}" id="customPaginationId'+i+'" onclick="copyId(this.id);custom_paginator.doAjaxCall('+countIndex+')">'+i+'</a>';
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

function getDetails()
{
var jObj=
	{
			//startIndex:0,
		   //maxIndex:this.maxIndex,
		   contentId:contentId,
	  task:"getLatestNews"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "latestUpdatedNewsAction.action?"+rparam;
	
	
	
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
	
	var str='';
	for(var i in result)
	{
		str += '<div class ="widget-block">';
				
		/* str+='<div class="leftmargin"><a target="_blank" title="Click Here To View Problem Complete details" class ="problemTitleClass" href="completeProblemDetailsAction.action?problemId='+result[i].problemId+'" ><h6>'+(result[i].problem)+'</h6></a></div>';
		str+='<div class="leftmargin"><span class="pull-left" style="color:#51A451;margin-right: 4px;">Existing From:</span><span>'+result[i].existingFrom+'</span><span style="margin-left:10px;color:#51A451;margin-right: 4px;">Identified On:</span><span>'+result[i].identifiedOn+'</span><div class="star pull-right"></div><input type="hidden" style="display:none;" value='+result[i].averageRating.avgRating +'" >';
		str+='</div>';
		
		str += '<div class="leftmargin"><font style="color:#51A451;font-size: 12px;">Description: </font><span style="font-family:arial;">'+result[i].description+' </span></div>';
		
		
	    str += '<div class="leftmargin"><font style="color:#51A451;font-size: 12px;">Posted by: </font>'+initialCap(result[i].name)+' '+initialCap(result[i].lastName)+'<font style="color:#51A451;font-size: 12px;">&nbsp;&nbsp;&nbsp;Ref NO:</font> '+result[i].referenceNo;
	    
	    if(result[i].problemLocation != null)
	    str+='<font style="color:#51A451;font-size: 12px;">&nbsp;&nbsp;&nbsp;Location: </font>'+initialCap(result[i].problemLocation);
	    str+='</div>';*/
		str +=''+result[i].fileTitle1+'';
		
	 str += '</div>';
	 
	}
	
	$("#latestNewsDiv").html(str);
	}

  getDetails();
</script>
</body>
</html>