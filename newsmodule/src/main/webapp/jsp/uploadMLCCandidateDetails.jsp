<%@ page language="java" contentType="text/html; charset=utf-8" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <META http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Telugudesam Party </title>

<!-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	 
	<!-- YUI Skin Sam -->
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/simplePagination/gallaryResponsePagination.js" ></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>

function insertCandidateDetails()
{
	var jsObj =
		{   
			candidateName : $('#candidateName').val(),
            education: $('#education').val(),
            gender:$('#gender').val(),
			partyId : 872,
			task:"insertMLCCandidateDetails"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "insertMLCCandidateDetails.action?"+rparam;					callAjax(jsObj,url); 
}
function callAjax(jsObj,url)
{
	 var myResults;
	 var callback = {			
	success : function( o ) {
		try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	
			
				if(jsObj.task == "insertMLCCandidateDetails")
				{
					var cssObj = {    
				     'font-weight' : 'bold',
				      'color' : 'green'
			        }


					if(myResults == "success"){
				 $('#successDiv').text("Candidate Details Inserted Successfully....").css(cssObj).show().delay(2000).fadeOut(400);
					}else{

					 $('#successDiv').html('<b>Error occured .Try again...</b>');

					}
					
				}
				
				
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

$('#id').find('option').remove();

$.each(results,function(index,value){
	$('#id').append('<option value="'+result[0]+'">'+result[0]+'</option>');

});
</script>
</head>
<body>


<div style="margin-left:295px;">

  <div>Enter Candidatename :<input type="text"  id="candidateName"/></div>
  <div>Enter Education :<input type="text" style="margin-left:31px;"  id="education"/></div>
  <div>Gender :
  <select type="text" id="gender" style="margin-left:78px;">
    <option value="M">Male</option>
	<option value="F">Female</option>
  </select></div>
<a class="btn btn-primary" style="margin-left:198px;" href="javascript:{insertCandidateDetails();}">Insert</a>
<div id="successDiv"></div>

</div>


</body>
</html>