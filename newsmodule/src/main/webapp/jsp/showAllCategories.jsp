<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TDP News Portal</title>
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
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
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
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> 

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

</head>
<body>
<div id="categoryDivId" style="margin:22px 0px 31px 102px;"></div>
<script type="text/javascript">

 function getAllCategories()
 {
	 $.ajaxSetup({
		   jsonp: null,
		   jsonpCallback: null
		});

		$.ajax({
		  type:'POST',
		  url: 'getAllCategoriesAction.action?',
		  dataType: 'json',
			 
		  success: function(results){ 
			   buildCategoryDetails(results);
		 },
		  error:function() { 
		  }
		}); 
 }
 
 function buildCategoryDetails(results)
 {
	$("#categoryDivId").html('');
	if(results == 0 || results.length == 0)
	 return;

    var str='';
 	for(var i in results)
  	{	
		str+='<div class="span3 thumbnail galleriesListClass" style=" background: none repeat scroll 0% 0% palegoldenrod;height:100px;margin:4px;">';
	    str+='<h6><a href="javascript:{showAllgallaries1('+results[i].id+')}" style="text-transform: capitalize" class="galName" title="'+results[i].name+'">'+results[i].name+'</a></h6>';
		
		str+='<span class="badge badge-info pull-right" style="margin-top:10px;"> GALLERY COUNT: '+results[i].orderId+'</span>';
		
		
		str+='</div>';
		
	}
  $("#categoryDivId").html(str);	 
 }

 function showAllgallaries1(catId){
	 var partyId = 872;
	   var urlstr = "showNewsGallariesAction.action?candidateId="+partyId+"&category="+catId;
		
     var browser1 = window.open(urlstr,"subRegionsWiseAnalysis","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
	}

 getAllCategories();
</script>

 
</body>
</html>