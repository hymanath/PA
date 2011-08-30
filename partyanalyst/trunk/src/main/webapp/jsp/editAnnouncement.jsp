<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Announcement Search</title>
</head>
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



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">


<!-- YUI Dependency files (End) -->


<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

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
	
	<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">

<!-- JQuery files (End) -->

<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>	
<link rel="stylesheet" type="text/css" href="styles/jQuery/jquery.datepick.css">

<script type="text/javascript" src="js/jQuery/jquery.datepick.min.js"></script>
<script type="text/javascript" src="js/jQuery/jquery.datepick-en-GB.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"></script>

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>


<style type="text/css">
#announcementSearchHeading
{
	margin-top:20px;
	margin-bottom:20px;
}

#headerImageCenterSpan {
color:#FFFFFF;
font-size:14px;
font-weight:bold;
position:relative;
top:6px;
}

#headerImageCenterDiv {
text-align:center;
}

#announcementSearchMain {
-moz-border-radius:6px 6px 6px 6px;
background-color:#B2BDC4;
border:1px solid #B2BDC4;
padding-left:20px;
padding-top:20px;
width:78%;
}



.tdClass {
width:100px;
}

.tdradio {
width:80px;
}

.tdradiohead{
 width:90px;
}

</style>

<script type="text/javascript" >
getAllConstituenciesInStateByType(2,1,'constituency');
function getAllConstituenciesInState()
{
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
function callAjax(jsObj,url)
{
	var myResults;	
	
	var callback = {			
	
	success : function( o )
	{
		try {
			if(o.responseText)
				myResults =YAHOO.lang.JSON.parse(o.responseText);	
			if(jsObj.task == "editannouncement")
			{    
				buildAnnouncementDataTable(myResults);
			}
			if(jsObj.task == "getannouncementbydate")
			{      
				buildAnnouncementDataTable(myResults);
			}
			if(jsObj.task == "updateannouncement")
			{
				  
			}
			else if(jsObj.task == "getConstituencies" || 
				jsObj.task == "getAllParliamentConstituencies")
			{     
		
		clearOptionsListForSelectElmtId(jsObj.elmtId);
		createOptionsForSelectElmtIdWithSelectOption(jsObj.elmtId,myResults);
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

function limitText(limitField, limitCount, limitNum)
{		
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
}

function getAllConstituenciesInStateByType(electionType, stateId, element)
{   
	clearOptionsListForSelectElmtId(element);
	showBusyImgWithId(element);
	var jsObj=
	{				
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllConstituenciesInState.action?"+rparam;						
callAjax(jsObj,url);
}

function createOptionsForSelectElmtIdWithSelectOption(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
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

function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}

function getAllParliamentConstInCountry(element)
{
	var jsObj=
	{				
			task: "getAllParliamentConstituencies",
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllParliamentConstInCountry.action?"+rparam;						
callAjax(jsObj,url);
}


function build()
{
   taskName = "editannouncement";
   var jsObj= {
               task : taskName	
        };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "editAnnouncementAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getAnnouncementResults()
{   constituencyEle = document.getElementById("constituency");
    constituencyId = constituencyEle.options[constituencyEle.selectedIndex].value;
    FromDate = document.getElementById("fromDateField").value;
    ToDate  =  document.getElementById("toDateField").value;
	taskName = "getannouncementbydate";
    var jsObj= {
               task : taskName,
			   fromDate :FromDate,
			   toDate :ToDate,
			   constituencyId: constituencyId
        };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "searchAnnouncementByDateAction.action?"+rparam;						
	callAjax(jsObj,url);

}



function buildAnnouncementDataTable(result)
{ 
 YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("announcementId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='openAnnouncementForm("+id+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";
		
  };

  
  var AnnouncementResultColumnDefs = [ 		    	             
		    	            {key:"title", label: "Title", sortable: true},
							{key:"message", label: "Description", sortable: true},
		    	           	{key:"fromDate", label: "FromDate", sortable: true},
		    				{key:"toDate", label: "ToDate",sortable:true},
							{key:"constituencyName", label: "Constituency", sortable: true},
		    				{key:"type", label: "ElectionType",sortable:true},
							{key:"edit", label: "Edit",formatter:YAHOO.widget.DataTable.edit}
							
		    	        ]; 
	var AnnouncementResultDataSource = new YAHOO.util.DataSource(result); 
	


    var myConfigs = {    
				
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [20,40,60], 
						pageLinks: 20
						})
						
					};	
	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "title","message","fromDate","announcementId","toDate","constituencyName","type"]
					};

		var AnnouncementSearchResultDataTable = new YAHOO.widget.DataTable("searchResult", AnnouncementResultColumnDefs,myDataSource, myConfigs);

		
	}
	
function openAnnouncementForm(id)
{
	var urlStr = "newAnnouncementAction.action?announcementId="+id+"&windowTask=update_existing";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=550,width=500,left=200,top=200");	
	updateBrowser.focus();	
}


</script>
<body>

<script type="text/javascript" >
  
  build();

$(function()
{
	$("#fromDateField").datepicker();
	$("#toDateField").datepicker();
});
</script>

	<div id="announcementSearchHeading"><center>
        <table border="0" cellpadding="0" cellspacing="0">          
          <tr>
            <td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
            <td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">Announcement Search</span></div></td>
            <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
          </tr>
        </table>    	
     </center>
	 </div> 
   <center>
     <div id="announcementSearchMain">
        
             <table width="100%" class="annSearchInputTable">	
				<tr>
				  <td class="tdClass">From Date </td>
				   <td><s:textfield id="fromDateField"  name="fromDate" readonly="true" onfocus="showCalendar(this.id)" size="16" theme="simple" /> </td>
                </tr>
				<tr>
				   <td class="tdClass">To Date </td>
                   <td><s:textfield id="toDateField"  name="toDate" size="16"  readonly="true" onfocus="showCalendar(this.id)" theme="simple"/> </td>
				</tr>
			 </table>
			  <table width="100%" class="annSearchInputTable">
	           <TR>
			      <td class="tdradiohead"> </td>
		          <TD class="tdradio"><input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="getAllConstituenciesInState();showStateSelect()"/>Assembly</TD>
	
		          <TD><input type="radio" name="a_radio" id="p_radio" onclick="getAllParliamentConstInCountry('constituency');hideStateSelect()"/>Parliament</TD>
	           </TR>
            </Table>

        <DIV id="stateSelectDiv">
            <Table width="100%" class="annSearchInputTable">            
	           <TR>
		         <TD class="tdClass">Select State </TD>
		         <TD><s:select theme="simple" name="state" cssClass="annSelect" id="stateList_c" list="#session.statesList" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/></TD>
               </TR>
            </Table>
        </DIV>

            <Table width="100%" class="annSearchInputTable">
	            <TR>
                  <TD class="tdClass">Select Constituency  </TD>
		          <TD><s:select theme="simple" cssClass="annSelect" name="constituency" id="constituency" list="#session.constituenciesList" listKey="id" listValue="name"/></TD>
                </TR>     
            </Table>
			<table>
			   <tr>
			      <td> <input type="button" onclick="getAnnouncementResults()" value="Search"/>
				  </td>
			   </tr>
			</table>
         
	</div>
   </center>
<div id="searchResult" class="yui-skin-sam"></div>
</body>
</html>