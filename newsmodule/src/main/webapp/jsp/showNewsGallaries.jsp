<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


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




<div id="gallarysId" style="margin:22px 0px 31px 102px;"></div>
<script>
var partyId = '${candidateId}';
var categoryId = '${category}';
getAllGallaries();
function getAllGallaries()
{

   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});

	$.ajax({
	  type:'POST',
	  url: 'getAllTheGallariesOfAparty.action?candidateId='+partyId+'&category='+categoryId,
	  dataType: 'json',
		 
	  success: function(results){ 
		   buildGallariesDetails(results.fileVOList);
	 },
	  error:function() { 
	  }
	});

}


function getAllFilesOfAGallary(gallaryId){		
	
	   var urlstr = "showAllFilesOfAGallary.action?gallaryId="+gallaryId+"";
		
     var browser1 = window.open(urlstr,"showAllFilesOfAGallary","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}

function buildGallariesDetails(results)
{
	var str='';
  for(var i in results)
  {
	 
	  str+='<div class="span3 breadcrumb" style="height:100px;margin:4px;">';
    str+='<h6><a href="javascript:{getAllFilesOfAGallary('+results[i].gallaryId+')}">'+results[i].gallaryName+'</a></h6>';
	str+='<span>'+results[i].gallaryDescription+'</span><br>';
	str+='<span class="badge badge-info pull-right" style="margin-top:10px;"> NEWS COUNT:'+results[i].totalResultsCount+'</span>';
	str+='</div>';
	
  }
  document.getElementById("gallarysId").innerHTML =str;

}
</script>

</body>