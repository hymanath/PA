<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Daily Updates</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="js/timePickeraddon.js"></script>
<style>
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
.ui-timepicker-div dl { text-align: left; }
.ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
.ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
.ui-timepicker-div td { font-size: 90%; }
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }

.ui-timepicker-rtl{ direction: rtl; }
.ui-timepicker-rtl dl { text-align: right; }
.ui-timepicker-rtl dl dd { margin: 0 65px 10px 10px; }
.dateclass{
  padding:5px;
}
#candidateDIV,#partyDIV,#specialPageDIV{
  max-height:300px;
  overflow:scroll;
  
}
</style>
<script type="text/javascript">
$(document).ready(function(){
   $('#startdatetime').datetimepicker();
   $('#enddatetime').datetimepicker();
   $("#startdatetime").datetimepicker('option', {maxDate: new Date()});
   $("#enddatetime").datetimepicker('option', {maxDate: new Date()});
    var currentDate = new Date();  
   $("#startdatetime").datepicker("setDate",currentDate);
   $("#enddatetime").datepicker("setDate",currentDate);
});
  function sendUpdates(){
	  	if(confirm("Do you want to send updates?")){
		    document.getElementById("sendbutton").disabled = 'true';
			var partySelected = $('#partymainDIV').is(':checked');
			var candidateSelected = $('#candidatemainDIV').is(':checked');
			var specialPageSelected = $('#specialPagemainDIV').is(':checked');
			var	candidateids = "";
			var	partyids = ""
			var	specialpageids = "";
			var	from = $('#startdatetime').val();
			var	to = $('#enddatetime').val();
			$('.candidateselect').each(function(){
			  if($(this).is(':checked')){
			   candidateids = candidateids+""+$(this).val()+",";
			  }
			});
			$('.partyselect').each(function(){
			  if($(this).is(':checked')){
			   partyids = partyids+""+$(this).val()+",";
			  }
			});
			$('.specialPageselect').each(function(){
			   if($(this).is(':checked')){
			   specialpageids = specialpageids+""+$(this).val()+",";
			  }
			});
			 if(candidateids.length > 0)
			  candidateids = candidateids.slice(0,candidateids.length-1);
			 if(partyids.length > 0)
			  partyids = partyids.slice(0,partyids.length-1);
			 if(specialpageids.length > 0)
			  specialpageids = specialpageids.slice(0,specialpageids.length-1);
        var jsObj =
		    {   
        		task:"sendupdates",
		        time : new Date().getTime(),
			    partySelected:partySelected,
			    candidateSelected:candidateSelected,
			    specialPageSelected:specialPageSelected,
				candidateids:candidateids,
				partyids:partyids,
				specialpageids:specialpageids,
				from:from,
				to:to
			    
		     };
        
	    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	    var url = "<%=request.getContextPath()%>/sendDailyUpdates.action?"+rparam;
		    callAjaxToSendUpdates(url,jsObj);
	  }
  }
  function getOptions(type){
    var jsObj =
		    {   
        		task:type,
		        time : new Date().getTime()
		     };
        
	    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	    var url = "<%=request.getContextPath()%>/sendDailyUpdates.action?"+rparam;
		    callAjaxToSendUpdates(url,jsObj);
  
  }
  function callAjaxToSendUpdates(url,jsObj)
	{			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								 if(jsObj.task == "sendupdates"){
									if(myResults == "success"){
									 alert("Success");
									}
									$("#sendbutton").attr('disabled', 'false');
								 }else if(jsObj.task == "party"){
								   buildoptions(myResults,"partyDIV");
								 }else if(jsObj.task == "candidate"){
								   buildoptions(myResults,"candidateDIV");
								 }else if(jsObj.task == "specialpage"){
								   buildoptions(myResults,"specialPageDIV");
								 }
								
							}catch (e) {   
							   	//alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
	function buildoptions(results,id){
	  var str ="";
	 for(var i in results){
	  if(id == "candidateDIV"){
	    str+='<div><input name="candidateselect" class="candidateselect" type="checkbox"  value="'+results[i].id+'" >'+ results[i].name +'</input></div>';
	  }else if(id == "partyDIV"){
	    str+='<div><input name="partyselect" class="partyselect" type="checkbox"  value="'+results[i].id+'" >'+ results[i].name +'</input></div>';
	  }else if(id == "specialPageDIV"){
	    str+='<div><input name="specialPageselect" class="specialPageselect" type="checkbox"  value="'+results[i].id+'" >'+ results[i].name +'</input></div>';
	  }
	 }
	  $("#"+id).html(str);
	}
	
</script>
</head>
<body>
    
    <div class="dateclass"><b>Start Date And Time : </b><input type="text" id="startdatetime" readonly="readonly" /></div>
	<div class=""><b>End Date And Time : </b><input type="text" readonly="readonly" id="enddatetime" /></div>
    <table>
	  <tr>
	    <td valign="top">
		   <input type="checkbox" id="candidatemainDIV"/> <b> Send Update for candidates</b><br/>
		   <div id="candidateDIV"></div>
		</td>
		<td valign="top">
		   <input type="checkbox" id="partymainDIV"/> <b> Send Update for parties</b><br/>
		   <div id="partyDIV"></div>
		</td>
		<td valign="top">
		   <input type="checkbox" id="specialPagemainDIV"/> <b> Send Update for specialpages</b><br/>
		   <div id="specialPageDIV"></div>
		</td>
	  </tr>
	</table>
	<div><input type="button" id="sendbutton" onclick="sendUpdates();" value="Send Updates" /></div>
	<script>
	   getOptions("party");
	   getOptions("candidate");
	   getOptions("specialpage");
	</script>
</body>
</html>