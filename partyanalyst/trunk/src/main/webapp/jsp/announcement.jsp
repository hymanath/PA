<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    
	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	

	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>


<!-- JQuery files (End) -->
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>	
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>	
<link rel="stylesheet" type="text/css" href="styles/jQuery/jquery.datepick.css">

<script type="text/javascript" src="js/jQuery/jquery.datepick.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.datepick-en-GB.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"></script>

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>

<script type="text/javascript">

 function validateForm(){
	 
    var constituency = document.getElementById("constituency");
    
	var constSelectElVal = constituency.options[constituency.selectedIndex].value ;

   var title = document.forms["announcementForm"]["title"].value ;
   var announcement = document.forms["announcementForm"]["announcement"].value
   var fromDate = document.forms["announcementForm"]["fromDateField"].value
   var toDate = document.forms["announcementForm"]["toDateField"].value
   var alertConstituency = document.getElementById("alertConstituency");
   var alertTitle = document.getElementById("alertTitle");
   var alertAnnouncement =      document.getElementById("alertAnnouncement");
    var alertFromDate = document.getElementById("alertFromDate");
	var alertToDate = document.getElementById("alertToDate");
   
    alertTitle.innerHTML = "";
	alertAnnouncement.innerHTML = "";
	alertConstituency.innerHTML = "";
	alertFromDate.innerHTML =  "";
    alertToDate.innerHTML = " ";
	
   var value = true;
if (title==null || title==""){
	
		alertTitle.innerHTML = "Title is Required";
		value = false;
	}
  
if (announcement==null || announcement==""){
	
		alertAnnouncement.innerHTML = "Announcement  is Required";
		value = false;
		
	}
if(constSelectElVal == 0 ){
	alertConstituency.innerHTML = "Constituency is Required";
    value = false;  

}

if (fromDate==null || fromDate==""){
	
		alertFromDate.innerHTML = "FromDate  is Required";
		value = false;
		
}
if (toDate ==null || toDate ==""){
	
		alertToDate.innerHTML = "ToDate is Required";
		value = false;
		
}
if (announcement.length > 700){
	
		alertAnnouncement.innerHTML = "Announcement length  must lessthan 700 characters";
		value = false;
		
}
if (title.length > 150){
	
		alertTitle.innerHTML = "Title length must lessthan 150 characters";
		value = false;
		
}

    return value;
 }
function getAllConstituenciesInState(){
var stateSelectEl = document.getElementById("stateList_c");
	var stateId = stateSelectEl.options[stateSelectEl.selectedIndex].value;
	getAllConstituenciesInStateByType(2,stateId,'constituency');
}

function showStateSelect()
{
	document.getElementById("stateSelectDiv").style.display = 'block';
}

function hideStateSelect()
{
	document.getElementById("stateSelectDiv").style.display = 'none';
}
getAllConstituenciesInStateByType(2,1,'constituency');
function load(){
var currentTask = '${windowTask}';
var type = '${type}';
  if(currentTask =='update_existing'){
      if(type == 'Parliament')
	  { alert("parliment");
	     document.getElementById("stateSelectDiv").style.display = 'none';
         document.getElementById("p_radio").checked = true;		
	 }
	 if(type == 'Assembly')
	  {  
	  alert("assembly");
	     document.getElementById("stateSelectDiv").style.display = 'block';
         document.getElementById("a_radio").checked = true;
	 }
  }
 }
 function updateAnnouncement(){
    var announcementId = '${announcementInfo.announcementId}';
	var constituency = document.getElementById("constituency");
    
	var constSelectElVal = constituency.options[constituency.selectedIndex].value ;
    var title = document.forms["announcementForm"]["title"].value ;
    var announcement = document.forms["announcementForm"]["announcement"].value ;
    var fromDate = document.forms["announcementForm"]["fromDateField"].value ;
    var toDate = document.forms["announcementForm"]["toDateField"].value ;
     taskName = "updateannouncement";
   var jsObj= {
               task : taskName,
               announcementId : announcementId,
               constituencyId : constSelectElVal,
                title : title,
				announcement : announcement,
				fromDate : fromDate,
				toDate : toDate
        };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateAnnouncementAction.action?"+rparam;
    var myResults;	
var callback = {			
    success : function( o ) {
		try {												
			myResults =YAHOO.lang.JSON.parse(o.responseText);	if(task ="updateannouncement"){
				
				alert("success");
			}
							
		}catch (e) {   		
		   	alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     			alert( "Failed to load result" + o.status + " " + o.statusText);
              }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);	
   
 }
</script>
<style type="text/css">
   
  
.main{
margin:0px; padding:0px; border:solid 1px #a6a6a6; -moz-border-radius:10px 10px 10px 10px;
	-webkit-border-radius: 10px 10px 10px 10px;
	-o-border-radius: 10px 10px 10px 10px;
	-ms-border-radius:10px 10px 10px 10px;
}
.textfieldsec{
margin:8px 5px 0px 5px; padding:0px;  width:350px; height:20px;
}

.textfieldsec .leftsec{
margin:0px 0px 0px 0px; padding:3px 0px 0px 0px; float:left;  width:90px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px; color:#000000; font-style:normal; font-weight:normal;
}

.textfieldsec .rightsec{
margin:0px; padding:0px; float:right;  width:250px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:11px; color:#000000; font-style:normal; font-weight:normal;
}

 .form{
margin:0px; padding:0px; float:right; background:#FFFFFFF; width:250px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:11px; color:#000000; font-style:normal; font-weight:normal; height:20px; border:1px solid #7f9db9;
}

 .form1{
margin:0px; padding:0px; float:right; background:#FFFFFFF; width:250px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:11px; color:#000000; font-style:normal; font-weight:normal; height:50px; border:1px solid #7f9db9;
}


</style>

</head>
<body onload="load()" >
<s:form name="announcementForm" action="announcementsAction" onsubmit="return validateForm()" method="get">
 <table width="360" border="0" cellspacing="0" cellpadding="0">
  <tr>
				<td colspan="2">
					<div style="color: red;font-weight:bold;" id="errorMessageDiv">
						<s:actionerror />
						<s:fielderror />
						<s:actionmessage/>						
					</div>
				</td>
			</tr>
  <tr>
  
    <td>
    <div class="main">
    <table width="360" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#666666">
          <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
            
            </div>
            <div class="rightsec">
            <input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="getAllConstituenciesInState();showStateSelect()"/>Assembly
			<input type="radio" name="a_radio" id="p_radio" onclick="getAllParliamentConstInCountry('constituency');hideStateSelect()"/>Parliament
            </div>
            </div>
            </td>
		  </tr>
		  <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
            
            </div>
            <div class="rightsec" id="stateSelectDiv">
            <s:select theme="simple" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/>
            </div>
            </div>
            </td>
          </tr>
		  <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
            
            </div>
            <div class="rightsec">
             <div id="alertConstituency" style="color:red;font-weight:bold;"></div>

            </div>
            </div>
            </td>
          </tr>
		  <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
            
            </div>
            <div class="rightsec">
            <s:select theme="simple" label="Select Your Constituency" name="constituency" id="constituency" list="constituenciesList" listKey="id" listValue="name"/>
            </div>
            </div>
            </td>
          </tr>
		  <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
            
            </div>
            <div class="rightsec">
             <div id="alertTitle" style="color:red;font-weight:bold;"></div>
            
            </div>
            </div>
            </td>
          </tr>
		  <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
           *Title
            </div>
            <div class="rightsec">

            <s:textfield id="title" name="title" class="form"/>
            </div>
            </div>
            </td>
          </tr>
		  <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
			</div>
            <div class="rightsec">
            <div id="alertAnnouncement" style="color:red;font-weight:bold;"></div>
            </div>
            </div>
            </td>
          </tr>
          <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
          *Announcement
            </div>
            <div class="rightsec">
            <s:textarea id="announcement" name="message" cols="" rows="" class="form1"></s:textarea>
            </div>
            </div>
            </td>
          </tr>
           <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">            
            </div>
            <div class="rightsec">
            <div id="alertFromDate" style="color:red;font-weight:bold;"></div>
            </div>
            </div>
            </td>
		  </tr>
          <tr>
            <td>
            <div class="textfieldsec">
            <div class="leftsec">
            *From Date
            </div>
            <div class="rightsec">
            <s:textfield id="fromDateField"  name="fromdate" class="form" readonly="true" onfocus="showCalendar(this.id)"/>
            </div>
            </div>
            </td>
		  </tr>
		  <tr>
		    <td>
            <div class="textfieldsec">
            <div class="leftsec">
            </div>
            <div class="rightsec">
            <div id="alertToDate" style="color:red;font-weight:bold;"></div>
            </div>
            </div>
            </td>
          </tr>
		  <tr>
		    <td>
            <div class="textfieldsec">
            <div class="leftsec">
           *To Date
            </div>
            <div class="rightsec">
            <s:textfield id="toDateField"  name="todate" size="25"  readonly="true"  class="form" onfocus="showCalendar(this.id)"/>
            </div>
            </div>
            </td>
          </tr>
          <c:if test="${windowTask == 'new'}">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="73%" height="40">&nbsp;</td>
                 <s:submit value="Submit"  align="center"/>
              </tr>
            </table></td>
          </tr>
          </c:if>
          <c:if test="${windowTask == 'update_existing'}">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="73%" height="40">&nbsp;</td>
                 <input type="button" value="Submit" onclick="updateAnnouncement()" align="center"/>
              </tr>
            </table></td>
          </tr>
          </c:if>
          
          
        </table></td>
      </tr>
    </table>
    </div>
    </td>
    
  </tr>
</table>																	</s:form>	     		
</body>
</html>