<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Admin Page</title>
	<meta name="" content="">
	<!-- Bootstrap -->
	
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<!--<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>-->
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
<style type="text/css">
	#signin{clear: both;
    float: left;}
</style>

	
</head>

<body>

		<div class="container-fluid headerBg" style="padding-left: 0px; padding-right: 0px;">
		<!---Header----->
		<div class="container">	
			<div class="row">
				<!----Logo----->
				<div class="span12">
					<img src="images/Logo.png" alt="Telugudhesham party logo" />
				</div>
				
			</div>
		</div>
		<!----Header End--->
		</div>
		
		<div class="row m_top10">
		<table class="table table-hover table-bordered">
		<tr>
		<td>
		<a  class="btn btn-info" href="userRegistration.action">Registraion Form</a>
		</td><td><a  class="btn btn-info" href="javascript:{}" onClick="createNewSource();">Create New Source</a></td>
		<td><a  class="btn btn-info" href="javascript:{}" onClick="">Create New Source</a></td>
		</tr>
		<table>
		</div>
		<div id="sourceDetails"  style="width: 400px; border: 1px solid #CCCCCC; border-radius: 4px 4px 4px 4px; padding: 4px;display:none;"></div>
		
<script type="text/javascript">
function createNewSource()
{
	$('#sourceDetails').show();
	var str = "";
	str +=  '<span>Source Name : </span><input type="text" id="sourceName"></input></br>';
	str +=  '<input type="button" value="create new Source" onClick="saveNewSourceDetails();" class="btn btn-info"></input>';
	$('#sourceDetails').html(str);
}
function saveNewSourceDetails()
{
	var sourceName = $('#sourceName').val();
	var jsObj=
	{
		name  : sourceName,
		task  : "storeSource"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "storeSourceDetails.action?"+rparam;	

	callAjax(jsObj,url);
}
function callAjax(jsObj,url)
{
	 var myResults;
	 var callback = {			
	success : function( o ) {
		try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	
			
				if(jsObj.task == "storeSource")
				{
					alert("Created Successfully");
					$('#sourceDetails').hide();
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

</script>
</body>


</html>