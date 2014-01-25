<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Voters </title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<!-- Then get JQuery UI -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="js/jQuery/image-crop/js/jquery.Jcrop.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/jQuery/image-crop/css/jquery.Jcrop.css" type="text/css" />

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>	
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
 <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

<script src="js/colpick.js" type="text/javascript"></script>
 <script type="text/javascript" src="js/voterFlag.js"></script>
<link rel="stylesheet" href="css/colpick.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />

  <style>
  #mainDiv{margin-left: auto;
    margin-right: auto;
    width: 741px;
  }
  .requiredFont
  {
	 color:red;
	 font-size:12px;
  }
  </style>
</head>
<body>
 <br>
<div id="mainDiv" class="contenttable widget"> 
<h4>INSERT PREDICTED CASTE</h4>
<div id="insertPredictCasteDiv" style="margin-top:10px;border: 1px solid #D3D3D3;padding:10px;">
<div id="errorDiv"></div>
Select Caste <font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Caste" name="constituencyList" id="casteStateId" list="predictionCasteList" listKey="id" listValue="name"/><BR>
From <input type="text" style="margin-left:80px;" id="fromValue"/><BR>
To <input type="text" style="margin-left:97px;" id="toValue"/><BR>
<div style="margin-left:120px;"><input type="button" value="Submit" class="btn btn-success" onclick="insertPredictedCaste()"/></div>
</div><br/>
<div id="casteCountDiv"></div><br/>
</div>
 <script type="text/javascript">

 function getCounts()
 {
	  var jsObj=
	  {
		task:"getCount"
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCasteCountAction.action?"+rparam;

	callAjax(jsObj,url);

 }
 function insertPredictedCaste()
 {
	 var flag=false;
	 var str='<font color="red">';
	 var errorDiv =$("#errorDiv");
	 
	 var fromValue =  $.trim($("#fromValue").val());
	 var toValue =  $.trim($("#toValue").val());
	 var casteStateId = $('#casteStateId').val();
	 
	 if(fromValue.length == 0 || isNaN(fromValue))
	 {
		flag=true;
		str+='Enter a number for From Value<br/>';
	 }
	 
	 if(toValue.length == 0 || isNaN(toValue))
	 {
		flag=true;
		str+='Enter a number for To Value<br/>';
	 }
	 
	 if(casteStateId == null || casteStateId == 0)
	 {
		flag=true;
		str+='Please Select a Caste<br/>';
	 }
	 str+='<BR></font>';

	 if(flag == true)
		$("#errorDiv").html(str);
	 else
	 {
		$("#errorDiv").html('');
		var jsObj=
		{
			casteStateId: casteStateId,
			firstRecord	: fromValue,
			maxRecords	: toValue,
			task:"insertPredictedCaste"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "insertPredictedCasteAction.action?"+rparam;
		callAjax(jsObj,url);
	 }
 }

function callAjax(jsObj,url)
{
	var myResults;
	var callback = {			
					success : function( o ) {
					try 
					{												
						myResults = YAHOO.lang.JSON.parse(o.responseText);					
						if(jsObj.task == "getCount")
						{
							buildCasteCount(myResults);
						}
						
					}catch (e){
				   
					}  
		   },
		   scope : this,
		   failure : function( o ) {}
		   };
	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

	function buildCasteCount(results)

	{
		var str ='';
		if(results.length > 0)
		{
			str+='<table class="table table-bordered">';
			str+='<tr>';
			str+='<th>Insert Type</th>';
			str+='<th>Count</th>';
			str+='</tr>';
			for(var i in results)
			{
			str+='<tr>';
			str+='<td>'+results[i].type+' </td>';
			str+='<td>'+results[i].totalVoters+' </td>';
			str+='</tr>';
			}
			str+='</table>';
			$("#casteCountDiv").html(str);
		}
	}
 </script>
 <script>
 getCounts();
 </script>
 </body>
 </html>