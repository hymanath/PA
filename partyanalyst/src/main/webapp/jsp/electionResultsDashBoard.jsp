<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>House Hold Survey Report</title>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
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
			<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
 
 <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.9.0/build/tabview/assets/skins/sam/tabview.css"> 
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
</head>
<body>

<input type="button" value="TEST" onClick="testIt();"/>
<div id="test"></div>
<script>

function testIt()
{
	$.ajax({
          type:'POST',
          url: 'getElectionResultsSummary.action',
          dataType: 'json',
          data: {},

          success: function(result){ 

			   buildResult(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });

}

function buildResult(result)
{
	var str ='';

	str+='<table border="1">';
	 str+='<thead>';
	  str+='<tr>';
	   str+='<th rowspan="2"></th>';
	   str+='<th colspan="6">SC</th>';
	   str+='<th colspan="6">ST</th>';
	   str+='<th colspan="6">GENERAL</th>';
	   str+='<th colspan="6">RURAL</th>';
	   str+='<th colspan="6">URBAN</th>';
	   str+='<th colspan="6">RURAL-URBAN</th>';
	  str+='</tr>';

      str+='<tr>';
	   $.each(result.subList[0].reservationDetails,function(index,value){
		   $.each(value.partiesDetails,function(index1,value1){
			     str+='<th>'+value1.name+'</th>';			 
	       });
	   });
	  str+='</tr>';
	 str+='</thead>';
	 str+='<tbody>';
    
	 $.each(result.subList,function(index,value){
		 str+='<tr>';
		 str+='<td>'+value.name+'</td>';
		  $.each(value.reservationDetails,function(index1,value1){
		     $.each(value1.partiesDetails,function(index2,value2){
			     str+='<th><a href="javascript:{getConstituenciesDetailsForSubReport('+value1.name+','+value2.id+')}">'+value2.count+'</a></th>';			 
	       });
	     });
		 str+='</tr>';
	 });

	 str+='</tbody>';
	str+='</table>';

	$('#test').html(str);

}

function getConstituenciesDetailsForSubReport(type,partyId)
{
	$.ajax({
          type:'POST',
          url: 'getConstituenciesDetailsForSubReport.action',
          dataType: 'json',
          data: {type:type,partyId:partyId},

          success: function(result){ 

			   buildResult(result);
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

</script>
</body>
</html>