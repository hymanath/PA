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
<!-- YUI Dependency files (Start)
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
	 Skin CSS files resize.css must load before layout.css  
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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<!-- Then get JQuery UI -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="js/jQuery/image-crop/js/jquery.Jcrop.js" type="text/javascript"></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
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

  <style>
  #mainDiv{margin-left: auto;
    margin-right: auto;
    width: 741px;
  }
  #picker,#picker1{
	margin:0;
	padding:4px;
	border:0;
	width:70px;
	height:20px;
	border-right:20px solid green;
	line-height:20px;
}
 .colpick {
	
	z-index:1006;
}
/*#createFlag {
    border: 1px solid #d3d3d3;
    border-radius: 10px;
    margin-left: 15px;
    padding: 10px 40px;
    width: 560px;
}*/
#flagTable th{background:#CDE6FC}
#flagTable * tr:nth-child(odd){background:#EDF5FF}
#flagDiv {
     margin-left: auto;
    margin-right: auto;
    width: 700px;
}
#flagDiv table{ border: 1px solid #D3D3D3;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    padding: 10px;
	width:100%;
}
#flagTable * td,#flagTable  * th{text-align:center}
  </style>
</head>
<body>
 <br>
<div id="mainDiv" class="contenttable widget"> 
<h3 style="background:#05A8E9;border-radius:3px;text-align:center;">FLAG MAINTENENCE</h3>
<div id="outerDiv" style="margin-left:20px;">
<input type="button" onclick="createFlag();"class="btn btn-success" value="Create Flag">
<input type="button" onclick=" getFlags();" class="btn btn-success" value="View Flags">
<img id="viewAjaxImg" src="images/icons/search.gif" style="display:none;"/>
</div>

<div id="createFlag" style="display:none;margin-top:25px;" class="widget green"></div>
   <br><div id="viewFlags" style="display:none;"></div>
    <div id="dialog"><div id="innerDiv"></div></div>
	
<br/>
</div>
 <script type="text/javascript">
$( document ).ready(function() {

	$('#picker').live("click",function(){
	
	$('#picker').colpick({
	layout:'hex',
	submit:0,
	colorScheme:'dark',
	onChange:function(hsb,hex,rgb,fromSetColor) {
		if(!fromSetColor) $('#picker').val(hex).css('border-color','#'+hex);
	}
});
	});

$('#picker1').keyup(function(){

	$(this).colpickSetColor(this.value);
});
$('#picker1').live("click",function(){
	
	$('#picker1').colpick({
	layout:'hex',
	submit:0,
	colorScheme:'dark',
	onChange:function(hsb,hex,rgb,fromSetColor) {
	
		if(!fromSetColor) $('#picker1').val(hex).css('border-color','#'+hex);
	}
});

	});

$('#picker').keyup(function(){

	$(this).colpickSetColor(this.value);
});
  });
function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "saveflagDetails")
								{
								showFlagStatus(myResults);
										
								}
								else if(jsObj.task == "updateflagDetails")
								{
									updateFlagStatus(myResults);
								}
								else if(jsObj.task == "getflagDetails")
								{
								buildFlags(myResults);
								}
								else if(jsObj.task == "deleteflagDetails")
								{
									deleteFlagStatus(myResults);
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
	function createFlag()
	{
	$("#viewFlags").css("display","none");
	$("#createFlag").css("display","block");
	var str=' ';
	str+='<h4 style="text-align:center;"> Create Flag</h4>';
	str+='<div id="errorDiv" style="font-family:verdana; margin-left: auto;margin-right: auto;width: 353px;"></div>';
	
	str+=' <form class="form-horizontal" style="margin-top:10px;margin-left:auto;margin-right:auto;width:550px">';
   	str+='  <div class="control-group">';
   	str+='  <label class="control-label" for="inputName"><b style="font-family:verdana;">Flag Name</b></label>';
   	str+='  <div class="controls">';
  	str+='   <input type="text" id="flagName" placeholder="Name">';
   	str+='  </div>';
   	str+='  </div>';
   	str+='  <div class="control-group">';
    	str+=' <label class="control-label" for="inputDescription"><b style="font-family:verdana;">Description</b></label>';
   	str+='  <div class="controls">';
    	str+=' <textarea rows="3" id="flagDescription"></textarea>';
   	str+='  </div>';
   	str+='  </div>';
		str+=' <div class="control-group">';
   	str+='  <label class="control-label" for="inputName"><b style="font-family:verdana;">Flag Color</b></label>';
   	str+='  <div class="controls">';
 	str+=' <input type="text" id="picker" readonly></input>';
   	str+='  </div>';
    	str+=' </div>';
   	str+='  <div class="control-group">';
    	str+=' <div class="controls">';
  	str+=' <input class="btn btn-success" type="button"  value="Save" id="saveflag" onclick="saveFlag();"/>';
	str+='<img id="saveAjaxImg" src="images/icons/search.gif" style="display:none;"/>';
  	str+='   </div>';
  	str+='   </div>';
    	str+=' </form><br/>';
		$("#createFlag").html(str);
	}
	function buildFlags(result)
	{
$("#viewAjaxImg").css("display","none");
$("#createFlag").css("display","none");

var str=' ';
if(result != null && result !='')
		{
$("#viewFlags").css("display","block");
str+='<div id="flagDiv">';
str+='<div id="errorDiv1"></div>';
str+='<table id="flagTable" class="table">';
str+='<tr>';
str+='<th> Name';
str+='</th>';
str+='<th> Description';
str+='</th>';
str+='<th>Color';
str+='</th>';
str+='<th> Edit';
str+='</th>';
str+='<th> Delete';
str+='</th>';

str+='</tr>';
for(var i in result)
	{
str+='<tr id=flag'+result[i].statusId+'>';
str+='<td><input type="hidden" class="name" value='+result[i].firstName+'>'+result[i].firstName+'</td>';
str+='<td ><input type="hidden" class="description" value='+result[i].description+'>'+result[i].description+'</td>';
str+='<td><input type="hidden"  class="color" value='+result[i].color+'><span style="background-color:#'+result[i].color+' ">&nbsp;&nbsp;&nbsp;&nbsp;</span></td>';

str+='<td>';
str+='<img width="15" height="20" src="images/icons/edit.png" class="editor_edit"  style="cursor:pointer;" onclick="PopulateFlag('+result[i].statusId+');"></img></td>';
str+='<td><img width="15" height="20" src="images/icons/delete.png" class="editor_remove" style="cursor:pointer;" onclick="deleteFlag('+result[i].statusId+');"></img>';
str+='</td>';

str+='</tr>';
	}
str+='</table>';
str+='</div>';
$("#viewFlags").html(str);
		}
	}
	function setColorForUpdate(hex) {
		$('#picker1').val(hex).css('border-color','#'+hex);
		
	}
	function PopulateFlag(id)
	{
		$("#dialog").dialog({                   
		    modal: true,
            title: "<b>Flag Details</b>",
			width:500,
            height: 400
     });
	
		var str=''
		var flagId = id;
		var name = $('#flag'+id+'').find(".name").val();
		var desc =  $('#flag'+id+'').find(".description").val();
		var color =  $('#flag'+id+'').find(".color").val();
	str+='<div id="updateErrorDiv" style="margin-left:100px;"></div>';

	str+=' <form class="form-horizontal" style="margin-top:30px;">';
   	str+='  <div class="control-group">';
   	str+='  <label class="control-label" for="inputName"><b style="font-family:verdana;">Flag Name</b></label>';
   	str+='  <div class="controls">';
  	str+='  <input type="text" id="flagName1" value='+name+'>';
   	str+='  </div>';
   	str+='  </div>';
   	str+='  <div class="control-group">';
    	str+=' <label class="control-label" for="inputDescription"><b style="font-family:verdana;">Description</b></label>';
   	str+='  <div class="controls">';
    	str+=' <textarea rows="3" id="flagDescription1">'+desc+'</textarea>';
   	str+='  </div>';
   	str+='  </div>';
		str+=' <div class="control-group">';
   	str+='  <label class="control-label" for="inputName"><b style="font-family:verdana;">Flag Color</b></label>';
   	str+='  <div class="controls">';
 	str+='  <input type="text" id="picker1" value='+color+' readonly></input>';
   	str+='  </div>';
    str+=' </div>';
   	str+='  <div class="control-group">';
    str+=' <div class="controls">';
  	str+=' <input class="btn btn-success" type="button"  id="updatebtn" value="Update" onclick="upDateFlag('+flagId+');"/>';
	str+='   </div>';
  	str+='   </div>';
    str+=' </form>';
	$("#innerDiv").html(str);
		setColorForUpdate(color);
	}

	function upDateFlag(id)
  {
	
	var str='';
	var flag=false;
	var flagId = id;
	var flagName= $.trim($("#flagName1").val());
	var flagDescription = $.trim($("#flagDescription1").val());
	var color=$.trim($("#picker1").val());
	if(flagName == "")
	{
		str+='Flag Name is required<br/>';
		flag=true;
	}
	if(flagDescription == "")
	{
		str+='Description Name is required<br/>';
		flag=true;
	}
	if(color == "")
	{
	str+='Flag Color is required';
	flag=true;
	}
	if(flag ==true)
	{
$("#updateErrorDiv").html(str).css("color","red");
	return;
	}
else
	{
	$("#updateErrorDiv").html('');
    var jsObj=
	  {
		 flagId:flagId,
		 name:flagName,
		 description:flagDescription,
		 color:color,
		task:"updateflagDetails"
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveflagDetailsAction.action?"+rparam;

	callAjax(jsObj,url);
	}
}
function saveFlag()
{
	var str='';
	var flag=false;
	var flagName= $.trim($("#flagName").val());
	var flagDescription = $.trim($("#flagDescription").val());
	var color=$.trim($("#picker").val());
	if(flagName == "")
	{
		str+='Flag Name is required<br/>';
		flag=true;
	}
	if(flagDescription == "")
	{
		str+='Description Name is required<br/>';
		flag=true;
	}
	if(color == "")
	{
	str+='Flag Color  is required';
	flag=true;
	}
	if(flag ==true)
	{
$("#errorDiv").html(str).css("color","red");
	return;
	}
else
	{
	$("#saveAjaxImg").css("display","inline-block");
	$("#errorDiv").html('');
	var jsObj=
	  {
		 flagId:0,
		 name:flagName,
		 description: flagDescription,
		 color:color,
		 task:"saveflagDetails"
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveflagDetailsAction.action?"+rparam;

	callAjax(jsObj,url);
	}
}
function getFlags()
{
$("#viewAjaxImg").css("display","inline-block");
	var str='';
	var flagName= $("#flagName").val();
	var flagDescription = $("#flagDescription").val();
	var color=$("#picker").val();
    var jsObj=
	  {
		 name:flagName,
		 description: flagDescription,
		 color:color,
		task:"getflagDetails"
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getflagDetailsAction.action?"+rparam;

	callAjax(jsObj,url);
}
function deleteFlag(id)
  {
	if (!confirm("Do you want to delete")){
      return false;
    }
	var str='';
	var flagId = id;
    var jsObj=
	  {
		 flagId:flagId,
		 task:"deleteflagDetails"
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteflagDetailsAction.action?"+rparam;

	callAjax(jsObj,url);
}
function showFlagStatus(result)
{
	$("#saveAjaxImg").css("display","none");
	if(result.resultCode == 0)
	{
		$("#errorDiv").html("flag Saved Successfully").css("color","green");
		$("#flagName").val ('');
		$("#flagDescription").val('') ;
		$("#picker").val('') ;
}
else if(result.resultCode == 121)
	{
		$("#errorDiv").html("flag already Exist").css("color","red");
		
	}
else
$("#errorDiv").html("");
}
function updateFlagStatus(result)
{
	if(result.resultCode == 0)
	{
		$("#updateErrorDiv").html("flag Updated Successfully").css("color","green");
		setTimeout($("#dialog").dialog('close'), 3000);
		getFlags();
	}
	else
$("#updateErrorDiv").html("");
}
function deleteFlagStatus(result)
{
	if(result.resultCode == 0)
	{
		getFlags();
	}
	
}

getFlags();
</script>
</body>
</html>