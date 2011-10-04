<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problem Management</title>
<!-- YUI Dependency files (Start) -->

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
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
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
	<!-- YUI Dependency files (End) --> <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">
	<!-- YUI Dependency files (End) -->
	
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>
	
	<style type="text/css">
		.calendarWidth
		{
			height:24px;
			width:22px;
		}
		.detailsHead {
			color:#247CD4;
			font-size:19px;
			font-weight:bold;
			padding:10px;
			text-align:center;
		}
		#betweenDatesDiv
		{
			text-align:left;
			margin-left:20px;
		}		
		#currentDateApprovalProblemsDiv
		{
			text-align:left;
			margin-top:30px;
			margin-left:54px;	
			margin-bottom:30px;		
		}
		#dropDownSelectDiv
		{
			//text-align:left;
			//margin-top:30px;
			//margin-left:52px;					
		}
	</style>
	<script type="text/javascript">	
	var Localization = { <%		
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String select = rb.getString("select");
			String imageName = rb.getString("imageName");
			String title = rb.getString("title");
			String description = rb.getString("description");
			String filePath = rb.getString("filePath");
		%> }
	var ResultDataTable;
    var recordsArray = new Array();
    var selectedProblemFileIdsArray = new Array();	

	function hasRecordInArray(record)
		{	
			var status = true;
			for(i=0;i<recordsArray.length;i++)
			{	
				if(recordsArray[i]._sId == record._sId)
					status=false;
			}
			return status;
		} 
        


		function deleteRecordFromArray(record)
		{
			for(i=0;i<recordsArray.length;i++)
			{	
				if(recordsArray[i]._sId == record._sId)
					recordsArray.splice(i,1);
			}		
		}


function getImagesInfo(problemFileId,fileName)
{
	
	var urlStr = "getImageInfoAction.action?fileName="+fileName;
	var imageViewBrowser = window.open(urlStr,"imageInfoPopup","scrollbars=yes,height=600,width=600,left//=200,top=50");	
	imageViewBrowser.focus();
}


		function getSelectedRecords(value)
		{
			selectedProblemHistoryIdsArray=new Array();
			selectedChoice = value;
			
			if(value!="select"){
				for(var i in recordsArray)
				{
					selectedProblemFileIdsArray.push(recordsArray[i]._oData.problemFileId);			
				}
				
				if(selectedChoice!="select"){
					var jsObj=
					{		
							choice : selectedChoice,			
							selectedProblemFileIds:selectedProblemFileIdsArray,
							task:"performDeletionOrAcceptenceImage"						
					};
				
					var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					var url = "<%=request.getContextPath()%>/getAllImageDetails.action?"+rparam;					
					callAjax(rparam,jsObj,url);					
				}
			}			
		}	

 YAHOO.widget.DataTable.viewDetails = function(elLiner, oRecord, oColumn, oData) 
  {
	var fname = oData;
	
	var id= oRecord.getData("problemFileId");
	
	elLiner.innerHTML ='<a href="javascript:{}" onclick="getImagesInfo('+id+',\''+fname+'\')">'+fname+' </a>';
  };


function buildNewImageDataTable(result)
{ 
 
  

  
  var ColumnDefs = [ 	    
	                        {key:"select", label: "Select", formatter:"checkbox"},
                            {key:"problemFileId", hidden: true},
	                        {key:"fileName1", label: "View", sortable: true,  formatter:YAHOO.widget.DataTable.viewDetails},
		    	            {key:"fileTitle1", label: "Title", sortable: true},
							{key:"fileDescription1", label: "Description", sortable: true},
                             	{key:"problem", label: "Problem", sortable: true},
		    	           	{key:"scope", label: "Severity", sortable: true},
	                         {key:"existingFrom", label: "Existing From", sortable: true},
	                         {key:"identifiedOn", label: "Identification Date", sortable: true},
	                        {key:"name", label: "Source", sortable: true}
		    	        ]; 
	var DataSource = new YAHOO.util.DataSource(result); 
	


    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10 
			    }) 
				}; 
				
	var myDataSource = new YAHOO.util.DataSource(result);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "problemFileId","fileName1","fileTitle1","fileDescription1","filePath1"]
					};

		 ResultDataTable = new YAHOO.widget.DataTable("imageTable", ColumnDefs,myDataSource, myConfigs);
         

		 ResultDataTable.subscribe("checkboxClickEvent", function(oArgs) {
					  	    elCheckbox = oArgs.target; 
							
					  	    var newValue = elCheckbox.checked; 
					  	    var record = this.getRecord(elCheckbox); 
					  	    var column = this.getColumn(elCheckbox); 
					  	    record.setData(column.key,newValue);				  	
					  	    					  	        
					  	   	if(newValue && hasRecordInArray(record))
					  	  	{
						  	  	recordsArray.push(record);					  	  	
					  	  	}else
							{
								deleteRecordFromArray(record);
								elCheckbox.checked = false;							
							}	  	  				  	  	
				});	
				return { 
		            oDS: myDataSource, 
		            oDT: ResultDataTable 
		      };	    
		
	}


		function getImages()
		{
			
			var jsObj=
			{						
					task:"getImage"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getAllImageDetails.action?"+rparam;					
			callAjax(rparam,jsObj,url);
		}
       function callAjax(param,jsObj,url){
			
			var results;	
			var callback = {			
			    success : function( o ) {
					try {							
											
							results = YAHOO.lang.JSON.parse(o.responseText);		
							if(jsObj.task == "getImage"){
							buildNewImageDataTable(results);
							}	
							
                             if(jsObj.task == "performDeletionOrAcceptenceImage"){
							  getImages();
							}

					}catch (e) {   		
					   	alert("Invalid JSON result" + e);   
					}  
			    },
			    scope : this,
			    failure : function( o ) {
			     			//alert( "Failed to load result" + o.status + " " + o.statusText);
			              }
			    };
	
			YAHOO.util.Connect.asyncRequest('GET', url, callback);
		}	
		
	</script>	

</head>
<body>

<script type="text/javascript">	
getImages();
</script>
<div class="detailsHead">
	 <u>Image Management Admin Control</u>
</div>


<div id="currentDateApprovalProblemsDiv">
	<table>
		<tr>
			<td style="font-family:verdana;font-weight:bold;">
				<input type="checkbox" onclick="getImages()" checked="true"/> All Images Posted For Today 
			</td>		
			<td style="padding-left:450px">
				<div id="advancedSearchDiv" class="button">
					<a href="javascript:{}" onclick="selectAndDeSelectAdvancedSearch()" style="text-decoration: none;color:white;">Advanced Search</a>
				</div>	
			</td>
		</tr>	
	</table>
</div>


<div class="yui-skin-sam">
<div id="betweenDatesDiv" style="display:block;">
<table>
	
	<tr>
		<td style="padding-left:22px;"><h4>Select To View Images Posted In Between Dates</h4>
			<fieldset>
			<legend style="font-family:arial,helvetica,clean,sans-serif;">Between Dates</legend>
			<s:radio theme="simple" list="{'Newly Posted', 'Approved','Rejected'}" name="problemRetrivalType"></s:radio>
			<table border="0">
				<tr>							
					<td><font class="requiredFont"> * </font></td>	
					<td>
						<input type="text"  READONLY="READONLY" name ="occuredDate" id="identifiedFromText" size="25"/>
						<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div>
					</td>					
					<td valign="top">
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('identifiedFromText_Div','identifiedFromText',new Date())"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>
					</td>						
					
					<td><font class="requiredFont"> * </font></td>
					<td>
						<input type="text" READONLY="READONLY" name ="reportedDate" id="reportedFromText" size="25"/>
						<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal"></div></div>
					</td>					
					<td valign="top">
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('reportedFromText_Div','reportedFromText',new Date())"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>
					</td>

					<td><input type="button" value="view" onclick="getAllProblemsBetweenDates()"></input></td>
				</tr>
			</table>
			</fieldset>	

				
			</td>
		
			<td style="padding-left:55px;">
				<div id="betweenDatesDiv">
					<table>
					<tr>
					   <td><h4>Select To View Images Posted On A Particular Date</h4></td>
					</tr>
						<tr>
							<td>
								<fieldset>
								<legend style="font-family:arial,helvetica,clean,sans-serif;">Particular Date</legend>
								<s:radio theme="simple" list="{'Newly Posted', 'Approved','Rejected'}" name="problemType"></s:radio>
								<table>
									<tr>							
										<td><font class="requiredFont"> * </font></td>	
										<td>
											<input type="text"  READONLY="READONLY" name ="occuredDate" id="selectedDate" size="25"/>
											<div class="yui-skin-sam"><div id="selectedDateDiv" class="tinyDateCal"></div></div>
										</td>					
										<td valign="top">
											<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('selectedDateDiv','selectedDate',new Date())"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>
										</td>		
										
										<td><input type="button" value="view" onclick="getAllProblemsForParticularDate()"></input></td>
									</tr>
								</table>
								<span id="alertMsg"></span>
								</fieldset>	
					
									
								</td>
							</tr>
						</table>
					</div>				
			</td>
		</tr>
	</table>
</div>	




<table>
<tr>
<td>
<div>
<input type="button" value="Approve" onclick="getSelectedRecords('accept')"></input>
<input type="button" value="Reject" onclick="getSelectedRecords('delete')"></input>
</div>

</td>
</tr>
<tr>
<td><div id="imageTable" ></div></td>
</tr>
</table>


</body>
</html>