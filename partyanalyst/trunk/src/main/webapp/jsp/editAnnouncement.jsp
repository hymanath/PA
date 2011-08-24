<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" >
function build(){
	  taskName = "editannouncement";
   var jsObj= {
               task : taskName	
        };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "editAnnouncementAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}
function callAjax(param,jsObj,url){
var myResults;	
var callback = {			
    success : function( o ) {
		try {												
			myResults =YAHOO.lang.JSON.parse(o.responseText);	if(task ="editannouncement"){
				
				buildAnnouncementDataTable(myResults);
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

function buildAnnouncementDataTable(result)
{
 YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var id= oRecord.getData("announcementsId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='openAnnouncementForm("+id+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";
		
  };

  
  var AnnouncementResultColumnDefs = [ 		    	             
		    	            {key:"title", label: "Title", sortable: true}, 
		    	           	{key:"fromDate", label: "FromDate", sortable: true},
		    				{key:"toDate", label: "ToDate",sortable:true},
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
						 fields : [ "title","fromDate" , "toDate"]
					};

		var AnnouncementSearchResultDataTable = new YAHOO.widget.DataTable("searchResult", AnnouncementResultColumnDefs,myDataSource, myConfigs);

		
	}
	
function openAnnouncementForm(id){
	var task = "update_existing";
	var urlStr = "editAnnouncementPageAction.action?announcementId="+id+"&windowTask="+task;
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,left=200,top=200");	
	updateBrowser.focus();	
    }
/*function deleteAnnouncement(id){
var ask = confirm("Do You want to delete");
if (ask ==  true){
		 taskName = "deleteannouncement";
   var jsObj= {
               task : taskName,	
			   announcementId : id
        };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteAnnouncementPageAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
    }
}*/

</script>
<body>
edit announcement
<script type="text/javascript" >
build();
</script>

<div id="searchResult" class="yui-skin-sam"></div>
</body>
</html>