<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback Approval Administration</title></head>

<!-- YUI Dependency Files-->
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/container-min.js"></script>	
    <!-- YUI Skin Sam -->    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

<script type="text/Javascript" src="js/homePage/jquery.js"></script>

<script type="text/javascript"
	src="js/commonUtilityScript/commonUtilityScript.js"></script>
<style>
  .errorMsg{
	  color:red;
	   font-size: 13px;
	}

.commentHeaderstyle {
    color: SteelBlue;
    font-size: 17px;
    font-weight: bold;
    padding: 20px;
	</style>

<script type="text/javascript">

var dataArr = new Array();


function buildFeedbackDataTable()
{
	
    var resultsColumnDefs = [ 	
	 {
		key : "checkBox",
		label : "Select",
		sortable : true
	}, 
	  {
		key : "kindOfComment",
		label : "CommentType",
		sortable : true
		},
		{
		key : "task",
		label : "CommentOn",
		sortable : true
		},
		 {
			key : "comment",
			label : "Comment",
			sortable : true
		}, 
		{
		key : "posteddate",
		label : "PostedOn",
		sortable : true
	},
	{
		key : "userName",
		label : "PostedBy",
		sortable : true		
	},
		{
		key : "responseCategory",
		label : "Response Category",
		sortable : true	
	},
		{
		key : "status",
		label : "Accepted/Rejected",
		sortable : true
	}
		
	];

    var myConfigs = {    
				
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [20,40,60], 
						pageLinks: 20
						})
						
					};	

					var myDataSource = new YAHOO.util.DataSource(dataArr);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "checkBox","comment" , "userName" , "commentType" ,"taskName", "posteddate" , "status" ,"responseCategory"]
					};

				
	var myDataTable = new YAHOO.widget.DataTable("commentsData",resultsColumnDefs, myDataSource,myConfigs);  
}


function approveFeedBack(type)
{
	
	var selectedElmts = [];

		var elmts = document.getElementsByName("feedBackCheckBox");
	
	for(var i=0; i<elmts.length; i++)
	{
		if(elmts[i].checked == true)
			selectedElmts.push(elmts[i].value);
	}
	var errorElmt = document.getElementById("errorMsgDiv");
	if(selectedElmts.length == 0)
		return errorElmt.innerHTML = 'Atleast one Comment has to be selected to Approve Or Reject';
	

	var jsObj = {
			checkedElmts:selectedElmts,
			approvetype:type,
			task:"approveFeedBack"
		};
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/feedBackApprovalAjaxAction.action?"+param;
	callAjax(jsObj,url);
}

	
function callAjax(jsObj,url){
var myResults;

var callback = {			
			   success : function( o ) {
					try {
						myResults = YAHOO.lang.JSON.parse(o.responseText); 
						
						if(jsObj.task == "approveFeedBack")
						{
							location.reload(true);
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
<body>

<div class="commentHeaderstyle">
<table>
	<tr>
		<td>Showing All Posted Comments </td>
	</tr>
</table>
</div>
<div id="commentsData_outer" class="yui-skin-sam">
	<div id="commentsData"> </div>
</div>
<div id="buttonsDiv">
	<input type="button" value="Approve"  onclick="approveFeedBack('approve')"></input>&nbsp;&nbsp;
	<input type="button" value="Reject" onclick="approveFeedBack('reject')"></input>

</div><br><br>
<div id = "errorMsgDiv" class="errorMsg"></div>
<script type="text/javascript">
	
	
	
	<c:forEach var="feedback" items="${feedbackList}" >			
			
			var feedbackObj={
									checkBox:"<input type='checkbox' name='feedBackCheckBox' value='${feedback.commentId}'>",
									userName:"${feedback.userName}",
									responseCategory:"${feedback.responseCategory}",
									posteddate:"${feedback.posteddate}",
									status:"${feedback.status}",
									comment:"${feedback.comment}",
									kindOfComment:"${feedback.kindOfComment}",
									task:"${feedback.task}"
								};
			dataArr.push(feedbackObj);
	</c:forEach>

   buildFeedbackDataTable();
   </script>

</body>
</html>