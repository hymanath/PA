<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <title>Comments Control Admin Page</title>
	 <!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/calendar/assets/skins/sam/calendar.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/calendar/calendar-min.js"></script> 
<script type="text/Javascript" src="js/homePage/jquery.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>


<style type="text/css">

#content {
    background: none repeat scroll 0 0 #FAFAFA;
    border: 1px solid #CBCBCB;
    box-shadow: 0 0 10px #CBCBCB;
    margin: 40px auto 0;
    padding: 0 60px 30px;
    width: 650px;
	margin-bottom: 10px;
}
#headingDiv {
    background-color: #567AAF;
    color: #FFFFFF;
    font-size: 15px;
    font-weight: bold;
    height: 25px;
    margin-top: 15px;
    text-align: center;
    width: 205px;
	margin-left: 214px;
}
.checkpagesDiv {
    font-size: 13px;
    font-weight: bold;
    margin-top: 25px;
}

#showHidePages{
   margin-top: 21px;
   font-size: 13px;
   font-weight: bold;
}
#buildCustomPagesDiv{

  margin-left: 86px;
  margin-top: 33px;
}
#customNameId{
	margin-bottom:10px;
}
#showHideCandidate{
  margin-left: 149px;
}
#showHideParty{
  margin-left: 149px;
}
#showHideSpecial{
  margin-left: 149px;
}
.imageButton{
	background-image: url("images/icons/homePage_new/btn_homePage.png");
    border: 0 none;
    color: #FFFFFF;
    cursor: pointer;
    font-weight: bold;
    
}
.imgFieldset{
  border: 4px solid #567AAF;
  border-radius: 4px 4px 4px 4px;
}
.imageButton1{
 background: none repeat scroll 0 0 #567AAF;
 
  border: medium none;
  border-radius: 4px 4px 4px 4px;
  color: #FFFFFF;
  cursor: pointer;
  font-family: inherit;
   font-size: 12px;
   font-weight: bold;
   padding: 4px 6px;
}

/*.canSelect
{
	width : 150px;
}*/

</style>
<script type="text/javascript">

var globalResult='';
function selectCheckBoxes()
{
   if(document.getElementById("candidate_page").checked == true)
    {
	      document.getElementById("showHideCandidate").style.display ="block";
		  document.getElementById("showHideParty").style.display ="none";
		  document.getElementById("showHideSpecial").style.display ="none";
    }
	 
   else if(document.getElementById("party_page").checked == true)
    {
	      document.getElementById("showHideParty").style.display ="block";
		  document.getElementById("showHideCandidate").style.display ="none";
		  document.getElementById("showHideSpecial").style.display ="none";
    }   
	   
	else if(document.getElementById("special_page").checked == true)
	{
	      document.getElementById("showHideSpecial").style.display ="block";
		  document.getElementById("showHideCandidate").style.display ="none";
		  document.getElementById("showHideParty").style.display ="none";
    }  
}
 function displayPages()
 {
   var elmt = document.getElementById("redenDisplayPagesDiv");
   if(!elmt)
     return;
    var str = '';
	
	str += '<table width="100%" style="margin-left:40px;">';
	str += '  <tr>';
	str += '     <td width="20%"> <div class ="checkpagesDiv"> <input type="radio" name = "checkPages"  id="candidate_page" value="candidatePage" onClick="selectCheckBoxes()"checked> CandidatePage </input> </div> </td>';
	str += '     <td width="20%"> <div class ="checkpagesDiv"> <input type="radio" name = "checkPages"  id="party_page" value="PartyPage" onClick="selectCheckBoxes()"> PartyPage </input> </div> </td>';
	str += '     <td width="20%"> <div class ="checkpagesDiv"> <input type="radio" name = "checkPages"  id="special_page" value="specialpage" onClick="selectCheckBoxes()"> SpecialPage </input> </div> </td>';
	str += '  </tr>';
	str += '  <tr>';
	str += '</table>';
	str += '  <div id= "showHidePages">';
	str += "    <div id ='showHideCandidate'>  Select Candidate :&nbsp;&nbsp;";
	str+="<select css='canSelect' style='width:200px;' id='candidateListId' name='Candidate Page'>";
	<c:forEach var="candidateList" items="${candidatesList}">
	str+='<option value="${candidateList.id}">${candidateList.name}</option>';
	</c:forEach>
	str+="</select></div> ";
	str += "    <div id ='showHideParty'>  Select Party :&nbsp;&nbsp;";
	str += "<select cssClass='canSelect' style='width:200px;' theme='simple' id='partyListId' name='Party Page' >";
	<c:forEach var="partyListVar" items="${partyList}">
	str+='<option value="${partyListVar.id}">${partyListVar.name}</option>';
	</c:forEach>
	str +="</select></div> ";
	str += "    <div id ='showHideSpecial'>  Select Special :&nbsp;&nbsp;";
	str += "<select cssClass='canSelect' style='width:200px;' theme='simple' id='specialListId' name='Special Page'>";
	<c:forEach var="specialListVar" items="${specialPages}">
	str+='<option value="${specialListVar.id}">${specialListVar.name}</option>';
	</c:forEach>
	str +="</select></div> ";
	str += '  </div>';
	elmt.innerHTML = str;
	selectCheckBoxes();
	
 }
 
function buildCustomPages()
{ 
  var elmt = document.getElementById("buildCustomPagesDiv")
       
    if(!elmt)
	   return;

	var str ='';
	
		
	str += '<table style="margin:5px;width:40%;margin-left:100px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton1" value="Create CustomPage" onclick="buildCustomPages()"></td>';
	str += '	<td><input type="button" class="imageButton1" value="Update Custompage" onclick="buildUploadCustomPages()"></td>';
	str += '</tr>';
	str += '</table>';
    str += '<div id = "clearDiv">' ;
	str += '<fieldset class="imgFieldset" style="width:400px; height:233px; margin-left: 21px;">';
	str += '<div id clearDiv>' ;
	str += '<h2 align="center" style="margin-bottom:20px; color:#06ABEA;">Create A Custom page</h2>';
	str += '<div id="gallaryCreateInnerDiv" ></div>';
	str += '<table width="75%" style="margin-left:37px; margin-bottom:30px;"><tr><td><b><font color="#4B74C6">CustomPage Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="customNameId" style ="width: 136px;"/></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">CustomPage Type</font><b></td>';
	str += '<td><select  id="customTypeListId"  onchange="" style="width:147px;"><option value="0">Select CustomType</option></select></td></tr></table>';
	
	str += '<table style="margin-left:90px" ><tr><td style="padding-right: 11px"><input type="button" style=" width: 100px; height: 30px;" class="imageButton" value="Create"  onClick="createCustomPages(\'create\')"></td><td style="padding-right: 10px"><input type="button" style=" width: 100px; height: 30px;" class="imageButton" value="Cancel" onclick="clearDiv(\'clearDiv\')"></td></tr></table>';
    str += '<div id ="renderCreateUpdateDiv"> </div> ';
	str += '</fieldset>';
	str += '</div>' ;
	str+='</div>';
	elmt.innerHTML = str;
	customPagesType("create");

}

function  buildUploadCustomPages()
{ 
  var elmt = document.getElementById("buildCustomPagesDiv")
       
    if(!elmt)
	   return;

	var str ='';
	
		
	str += '<table style="margin:5px;width:40%;margin-left:100px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton1" value="Create CustomPage" onclick="buildCustomPages()"></td>';
	str += '	<td><input type="button" class="imageButton1" value="Update Custompage" onclick="buildUploadCustomPages()"></td>';
	str += '</tr>';
	str += '</table>';
    str += '<div id = "clearDiv">' ;
	str += '<fieldset class="imgFieldset" style="width:400px; height:233px; margin-left: 21px;">';
	str += '<div id clearDiv>' ;
	str += '<h2 align="center" style="margin-bottom:20px; color:#06ABEA;">Update A Custom page</h2>';
	str += '<div id="gallaryCreateInnerDiv" ></div>';
	str += '<table width="75%" style="margin-left:37px; margin-bottom:30px;"><tr><td><b><font color="#4B74C6">CustomPage Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="customNameId" style ="width: 136px;"/></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">CustomPage Type</font><b></td>';
	str += '<td><select  id="customTypeListId"  onchange="" style="width:147px;"><option value="0">Select CustomType</option></select></td></tr></table>';
	
	str += '<table style="margin-left:90px; margin-bottom:15px;" ><tr><td style="padding-right: 11px"><input type="button" style=" width: 100px; height: 30px;" class="imageButton" value="Update"  onClick="updateCustomPages(\'update\')"></td><td style="padding-right: 10px"><input type="button" style=" width: 100px; height: 30px;" class="imageButton" value="Cancel" onclick="clearDiv(\'clearDiv\')"></td></tr></table>';
    str += '<div id ="renderCreateUpdateDiv"> </div> ';
	str += '<div id ="moreCustomTypeDiv" style="display:none;"> </div>'; 
	str += '</fieldset>';
	str += '</div>' ;
	str+='</div>';
	elmt.innerHTML = str;
	customPagesType("update");

}

function clearcustomPagesType()
{
  var elmt = document.getElementById("customTypeListId");

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}
	 
	
   	var option = document.createElement('option');
	option.value= 0;
	option.text= "Select CustomType";
	try
	{
	  elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
	  elmt.add(option); // IE only
	}
	

}

function clearDiv(clearDiv)
{
  document.getElementById("clearDiv").style.display ="none";
}

function customPagesType(element)
{
	var status = '';
	if(element == "update")
	{   
		status = "update";
        clearcustomPagesType();
		
	}
    else
       status = "create";
      
  var jsObj =
		{   
		  status:status,
	      task:"customPagesType"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "customPagesTypeAction.action?"+rparam;						
	callAjax(jsObj,url);

}
function buildResults(results,divId)
 {
 
  var elmt = document.getElementById(divId);
   if(results.length<=0 && divId=="customTypeListId")
     {
   	   var option = document.createElement('option');
		option.value= 0;
		option.text= "Select CustomType";
		 try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	 }
	if(results.length>0 && results !=null )
	{
	  for(var i in results)
	  {
		var option = document.createElement('option');
		
		  option.value=results[i].ids;
		  option.text=results[i].names;
		  
		try
		 {
			elmt.add(option,null); // standards compliant
		 }
		catch(ex)
		 {
			elmt.add(option); // IE only
		 }
	  }
    }
 }
 function createCustomPages(create)
 {

    var pageId ='';
	var pageName ='';
	var selectPageEle ='';
	if(document.getElementById("candidate_page").checked == true)
	{
	  selectPageEle = document.getElementById("candidateListId");
	  pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	  pageName = selectPageEle.name;
	}
	else if(document.getElementById("party_page").checked == true)
	{
	  selectPageEle = document.getElementById("partyListId");
	  pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	  pageName = selectPageEle.name;
	}
	else if(document.getElementById("special_page").checked == true)
	{
	  selectPageEle = document.getElementById("specialListId");
	  pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	  pageName = selectPageEle.name;
	}
	var customPageName = document.getElementById("customNameId").value;
	
	var customPageTypeEle = document.getElementById("customTypeListId");
	var customPageType = customPageTypeEle.options[customPageTypeEle.selectedIndex].value;

	var errorDivEle = document.getElementById("gallaryCreateInnerDiv");
	var eFlag = false;

	var str = '<font color="red">';

	if(customPageName.length == 0)
	{
		str += 'Name is Required<br>';
		eFlag = true;
	}
	
	if(customPageType.length == 0)
	{
		str += 'Type is Required<br>';
		eFlag = true;
	}
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	var jsObj =
		{   
		  pageId:pageId,
		  pageName:pageName,
		  customPageName:customPageName,
		  customPageType:customPageType,
		  create:create,
	      task:"createCustomPages"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createCustomPagesAction.action?"+rparam;						
	callAjax(jsObj,url);
 }
function updateCustomPages(update)
{
    var pageId ='';
	var pageName ='';
	var selectPageEle ='';
	if(document.getElementById("candidate_page").checked == true)
	{
	  selectPageEle = document.getElementById("candidateListId");
	  pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	  pageName = selectPageEle.name;
	}
	else if(document.getElementById("party_page").checked == true)
	{
	  selectPageEle = document.getElementById("partyListId");
	  pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	  pageName = selectPageEle.name;
	}
	else if(document.getElementById("special_page").checked == true)
	{
	  selectPageEle = document.getElementById("specialListId");
	  pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	  pageName = selectPageEle.name;
	}
	var customPageName = document.getElementById("customNameId").value;
	
	var customPageTypeEle = document.getElementById("customTypeListId");
	var customPageType = customPageTypeEle.options[customPageTypeEle.selectedIndex].value;
	
	var errorDivEle = document.getElementById("gallaryCreateInnerDiv");
	var eFlag = false;

	var str = '<font color="red">';

	if(customPageName.length == 0)
	{
		str += 'Name is Required<br>';
		eFlag = true;
	}
	
	if(customPageType.length == 0)
	{
		str += 'Type is Required<br>';
		eFlag = true;
	}
	str += '</font>';
	errorDivEle.innerHTML = str;
	if(eFlag)
		return;

	var jsObj =
		{   
		  pageId:pageId,
		  pageName:pageName,
		  update:update,
          customPageName:customPageName,
          customPageType:customPageType,
	      task:"updateCustomPages"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createCustomPagesAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getCustomPageNameAndType()
{
	var pageId ='';
	var pageName ='';
	var selectPageEle ='';
	if(document.getElementById("candidate_page").checked == true)
	{
	   selectPageEle = document.getElementById("candidateListId");
	   pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	   pageName = selectPageEle.name;
	}
	else if(document.getElementById("party_page").checked == true)
	{
	   selectPageEle = document.getElementById("partyListId");
	   pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	   pageName = selectPageEle.name;
	}
	else if(document.getElementById("special_page").checked == true)
	{
	   selectPageEle = document.getElementById("specialListId");
	   pageId = selectPageEle.options[selectPageEle.selectedIndex].value;
	   pageName = selectPageEle.name;
	}
	var jsObj =
		{   
		  pageId:pageId,
		  pageName:pageName,
		  task:"getCustomPageNameAndType"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCustomPageNameAndTypeAction.action?"+rparam;						
	callAjax(jsObj,url);

}

function renderCreateCustomPages(results,display)
{
   if(document.getElementById("renderCreateUpdateDiv")!=null)
	   var errorEle = document.getElementById("renderCreateUpdateDiv");

	var str = '';
   if(display=="create")
	{
	  
	  if(results.resultCode == 0)
	  {
		cleardescriptionFields();
		str += '<font color="green"><b>Data Saved Successfully.</b>';
	  }
	  else if(results.resultCode == 1) 
	  {
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	  }
	  else if(results.resultCode == 121) 
	  {
		str += '<font color="green"><b> This jsp already Existed.</b>';
	  }

	}
	else if(display=="update")
	{
	  
	  if(results.resultCode == 0)
	  {
		cleardescriptionFields();
		str += '<font color="green"><b>Data Updated Successfully.</b>';
	  }
	  else if(results.resultCode == 1) 
	  {
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	  }
	}
	errorEle.innerHTML=str;

}
function cleardescriptionFields()
{
   document.getElementById("customNameId").value = '';
   document.getElementById("customTypeListId").value = 0;
   
}
function renderCustomPageNameAndType(results)
{ 
   var moreEle = '';
   globalResult = results;
   if(results != null && results.length>0)
   {
      document.getElementById("customNameId").value =results[0].name;
      document.getElementById("customTypeListId").value= results[0].typeId;
      var str = '';

      if(results[0].error == 121)
	   {
		   if(document.getElementById("moreCustomTypeDiv").style.display ="none")
                document.getElementById("moreCustomTypeDiv").style.display ="block";

		   moreEle = document.getElementById("moreCustomTypeDiv");
		   str += '<font color="red"><b>No records are available.</b>';
	   }
	      
	  if(results.length >1)
       {
      
         if(document.getElementById("moreCustomTypeDiv").style.display ="none")
             document.getElementById("moreCustomTypeDiv").style.display ="block";
	  
	     moreEle = document.getElementById("moreCustomTypeDiv");
		 str += '<a style="color: blue; margin-left:182px;" href="javascript:{}" onClick="getMoreDetails();">More &gt;&gt;</a>';
		
	   }
	   moreEle.innerHTML=str;
   }

  
}
function getMoreDetails()
{
       
	$.fx.speeds._default = 600;
    $("#moreCustomTypeDiv").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 'auto',
								width:400,
								position:[100,100],								
								modal: true,
							    overlay: { opacity: 0.5, background: 'black'}
								});

    var str ='';
	var moreEle='';
	if(document.getElementById("moreCustomTypeDiv").style.display ="block")
           moreEle = document.getElementById("moreCustomTypeDiv");
	 for(var i=1;i<globalResult.length;i++)
	 {
	     
         str += '<font color="blue"> also pointed '+globalResult[i].name+' with '+globalResult[i].type+' </br>';
		
     }
	 moreEle.innerHTML=str;

}
	   

function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
         	 
          if(jsObj.task == "customPagesType")
		  {
			  if(jsObj.status == "update")
                   getCustomPageNameAndType();

               buildResults(myResults,"customTypeListId");
		  }
		 else if(jsObj.task == "createCustomPages") 
		 {
		    renderCreateCustomPages(myResults,jsObj.create);
     	 }
		 else if(jsObj.task == "updateCustomPages")  
		 {
		    renderCreateCustomPages(myResults,jsObj.update);
     	 }
		 else if(jsObj.task == "getCustomPageNameAndType")  
		 {
		    renderCustomPageNameAndType(myResults);
     	 }
		}
		catch(e)
		{   
		 //alert("Invalid JSON result" + e);   
		}  
	 },
	   scope : this,
	   failure : function( o )
	   {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
       }
    };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}


</script>
</head>

<body>
  <div id ="content">
     <div id ="headingDiv"> Maintain Custom Pages </div>
     <div id ="redenDisplayPagesDiv">  </div>
	 <div id ="buildCustomPagesDiv">  </div>
	      
	    
  </div>
  <script type="text/javascript">
  displayPages();
  buildCustomPages();
  
  
  
  </script>
</body>
</html>