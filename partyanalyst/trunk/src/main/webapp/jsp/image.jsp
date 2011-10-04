
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s2" uri="/struts-tags" %>
<%@ taglib prefix="img" uri="/struts-images"%>
<%@ page import="java.util.ResourceBundle;"%>

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
<link rel="stylesheet" type="text/css"
	href="styles/yuiStyles/resize.css">
<link rel="stylesheet" type="text/css"
	href="styles/yuiStyles/layout.css">
<link rel="stylesheet" type="text/css"
	href="styles/yuiStyles/container.css">
<link rel="stylesheet" type="text/css"
	href="styles/yuiStyles/button.css">
<link rel="stylesheet" type="text/css"
	href="styles/yuiStyles/tabview.css">
<link type="text/css" rel="stylesheet"
	href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css"
	href="styles/yuiStyles/paginator.css">
<link rel="stylesheet" type="text/css"
	href="styles/yuiStyles/calendar.css">
<link rel="stylesheet" type="text/css"
	href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css"
	href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css"
	href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<!-- YUI Dependency files (End) -->
<link rel="stylesheet" type="text/css"
	href="styles/constituencyManagement/constituencyManagement.css">
<!-- YUI Dependency files (End) -->

<script type="text/javascript"
	src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript"
	src="js/calendar Component/calendarComponent.js"></script>

<style type="text/css">
.calendarWidth {
	height: 24px;
	width: 22px;
}

.detailsHead {
	color: #247CD4;
	font-size: 19px;
	font-weight: bold;
	padding: 10px;
	text-align: center;
}

#betweenDatesDiv {
	text-align: left;
	margin-left: 20px;
}

#currentDateApprovalProblemsDiv {
	text-align: left;
	margin-top: 30px;
	margin-left: 54px;
	margin-bottom: 30px;
}

#dropDownSelectDiv { //
	text-align: left; //
	margin-top: 30px; //
	margin-left: 52px;
}
</style>
<script type="text/javascript">	
	var Localization = { <%ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String select = rb.getString("select");
			String title = rb.getString("title");
			String description = rb.getString("description");
			String identifiedDate = rb.getString("identifiedDate");
			String source = rb.getString("source");
			String location = rb.getString("location");
			String scope = rb.getString("scope");
			String problemLabel = rb.getString("problem");%> }
	var newProbDataTable;
	var recordsArray = new Array();
	var selectedProblemHistoryIdsArray = new Array();	
		function callAjax(param,jsObj,url){
			
			var results;	
			var callback = {			
			    success : function( o ) {
					try {							
						"",					
							results = YAHOO.lang.JSON.parse(o.responseText);		
							if(jsObj.task == "performDeletionOrAcceptenceProblems"){
								selectedProblemHistoryIdsArray = "" ;
								getAllProblems();	
							} else if(jsObj.task == "currentDate" || jsObj.task == "betweenDates" || jsObj.task == "selectedDate"){
								buildNewProblemsDataTable(results.approvalProblems);
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
		function getAllProblemsBetweenDates()
		{
			var startDate = document.getElementById("identifiedFromText").value;
			var endDate = document.getElementById("reportedFromText").value;
			var selection = document.getElementsByName("problemRetrivalType");
			var selectedRadio;
			for(var i=0;i<selection.length;i++){
				if(selection[i].checked == true){
					selectedRadio = selection[i].value;					
				}
			}			
			var jsObj=
			{		
					choice:selectedRadio,
					fromDate:startDate,
					toDate:endDate,					
					task:"betweenDates"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getAllProblems.action?"+rparam;					
			callAjax(rparam,jsObj,url);
		}

		function getAllProblems()
		{
			var jsObj=
			{					
					task:"currentDate"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getAllProblems.action?"+rparam;					
			callAjax(rparam,jsObj,url);
		}

		function getAllProblemsForParticularDate()			
		{
			document.getElementById("alertMsg").innerHTML='';
			var selection = document.getElementsByName("problemType");
			var selectedRadio;
			var flag;
			for(var i=0;i<selection.length;i++){
				if(selection[i].checked == true){
					selectedRadio = selection[i].value;	
					flag = true;
				}
				
			}
			var selectDate = document.getElementById("selectedDate").value;
			if(selectDate ==''){
			document.getElementById("alertMsg").innerHTML ='<font color="red">Please Select Date</font>';
			return;
			}
			if(flag && selectDate !=''){
			
			var jsObj=
			{		
					choice:selectedRadio,			
					selectedDate:selectDate,
					task:"selectedDate"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getAllProblems.action?"+rparam;					
			callAjax(rparam,jsObj,url);			
		}
		else{
     		document.getElementById("alertMsg").innerHTML ='<font color="red">Please Select Any One type of Problems</font>';
		    }
		}
		
		function buildNewProblemsDataTable(results)
		{	
			document.getElementById("approvalDiv").style.display = 'block';
			
			var myColumnDefs = [ 
		            {key:"select", label: "<%=select%>", formatter:"checkbox"},
		            {key:"problemId", hidden: true}, 
		            {key:"problemHistoryId", hidden: true}, 
		            {key:"problem", label: "<%=problemLabel%>", sortable:true, maxAutoWidth:150}, 
		            {key:"description", label: "<%=description%>", sortable:true, maxAutoWidth:250}, 
					{key:"impactLevel", label: "Severity", sortable:true},						
					{key:"postedDate", 	label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true,sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},			
					{key:"name", label: "<%=source%>", sortable:true}	
		        ]; 
			
		        var myDataSource = new YAHOO.util.DataSource(results); 
		        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		        myDataSource.responseSchema = { 
		            fields:["problemId","problemHistoryId","problem","description","impactLevel","postedDate","name"] 
		        }; 
				
				var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10 
			    }) 
				}; 

				newProbDataTable = new YAHOO.widget.DataTable("problsDataTable", myColumnDefs, myDataSource,myConfigs); 
		        							
				var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  }; 
				  newProbDataTable.subscribe("checkboxClickEvent", function(oArgs) {
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
		            oDT: newProbDataTable 
		      };	    
		}

		function deleteRecordFromArray(record)
		{
			for(i=0;i<recordsArray.length;i++)
			{	
				if(recordsArray[i]._sId == record._sId)
					recordsArray.splice(i,1);
			}		
		}
		
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

		function getSelectedRecords(value)
		{
			selectedProblemHistoryIdsArray=new Array();
			selectedChoice = value;
			
			if(value!="select"){
				for(var i in recordsArray)
				{
					selectedProblemHistoryIdsArray.push(recordsArray[i]._oData.problemHistoryId);			
				}
				//var selectedChoice = document.getElementById("dropDownSelect").value;
				if(selectedChoice!="select"){
					var jsObj=
					{		
							choice : selectedChoice,			
							selectedProblemHistoryIds:selectedProblemHistoryIdsArray,
							task:"performDeletionOrAcceptenceProblems"						
					};
				
					var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					var url = "<%=request.getContextPath()%>/getAllProblems.action?"+rparam;					
					callAjax(rparam,jsObj,url);					
				}
			}			
		}	

		function selectAndDeSelectAdvancedSearch()
		{
			if(document.getElementById("betweenDatesDiv").style.display == 'block'){
				document.getElementById("betweenDatesDiv").style.display = 'none';
			}else{
				document.getElementById("betweenDatesDiv").style.display = 'block'
			}			
		}
		
	</script>

</head>
<body>
<center><h3>Image Admin Control</h3></center>
<img:image src="%{image}" resize="true" height="25" refresh="false"/>

</body>
</html>