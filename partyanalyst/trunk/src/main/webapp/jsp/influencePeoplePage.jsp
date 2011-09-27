<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

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
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
    
<!-- YUI Dependency files (End) -->


<link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">	
<title>Influence People Details</title>

	<style type="text/css">

		body
		{			
			direction:ltr;
			font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
			font-size:11px;
			margin:0;
			padding:0;
		}

		#influencePeopleData_main
		{
			padding:40px;
			text-align:left;
			color:#FFFFFF;
		}
		
		#influencePeopleData_body .yui-pg-container 
		{
			margin:6px 450px;
			text-align:right;
		}

		#influencePeopleData_head
		{
			color:#764916;
			font-size:17px;
			font-weight:bold;
			padding:5px;
			text-decoration:underline;
		}

		.peopleDataMain
		{
			border:1px solid #DBD8D4;
			margin-top:20px;
		}

		.elbutton
		{   
			
			color:#FFFAF0; 
	        font: bold small 'trebuchet ms',helvetica,sans-serif; 
			background-color:#483D8B;
	  
			
	} 
			
		}

		/*#buttoneleId
		{
			 cursor:pointer;
			 cursor:hand;
		}*/
		/*.elbutton
		{
		
		
		}*/

		#smsStatus
		{
			text-align:center;
		}

		#messageBox_mask
		{
			background-color:#A3B4BF;
			opacity:0.5;
			filter:alpha(opacity=50)
		}

		#messageBox_outer .yui-panel .bd, #messageBox_outer .yui-panel .ft 
		{
			background-color:#FFFFFF;
		}

		#messageBox_outer .yui-panel .hd 
		{
			padding:2px 2px 2px 10px;
		}
	</style>


	<script type="text/javascript">
		
		function buildDataTable()
		{
			<c:forEach var="region" items="${influencingPeopleDetailsVO}" varStatus ="status">

				buildYUIDTTable("peopleDataTable_${status.index}","peopleRegion_body_ ${status.index}");				

			</c:forEach>

		}

		function buildYUIDTTable(tableId,divId)
		{
				var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get(tableId));
				resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
				resultsDataSource.responseSchema = {
					fields : [
					{
						key : ""
					},{
						key : "firstName"
					}, {
						key : "lastName"
					}, {
						key : "email"
					}, {
						key : "mobile",parser:"number"
					}, {
						key : "gender"
					}, {
						key : "cast"
					} , {
						key : "constituencyName"
					}, {
						key : "districtName"
					}, {
						key : "stateName"
					},{
						key : "moreDetails"
					},{
						key : "edit"
					},{
						key : "delete"
					} ]
				};

				var resultsColumnDefs = [{
					key : "",
					label : "All <input type='checkbox' name='regionHeaderCheckBox' onclick='selectAllPeopleInRegion(${region.regionId})'></input>",
					sortable : false
				},
				{
					key : "firstName",
					label : "First Name",
					sortable : true
				}, {
					key : "lastName",
					label : "Second Name",
					sortable : true
				}, {
					key : "email",
					label : "Email",
					sortable : true
				}, {
					key : "mobile",
					parser:"number",
					label : "Mobile",
					sortable : true
				}, {
					key : "gender",
					label : "Gender",
					sortable : true
				}, {
					key : "cast",
					label : "Cast",
					sortable : true
				}, {
					key : "constituencyName",
					label : "Constituency",
					sortable : true
				} , {
					key : "districtName",
					label : "District / Parliament",
					sortable : true
				} , {
					key : "stateName",
					label : "State",
					sortable : true
				},{
					key : "moreDetails",
					label : "More Details",
					sortable : false
				} ,{
					key : "edit",
					label : "Edit",
					sortable : false
				},{
					key : "delete",
					label : "Delete",
					sortable : false
				}];

				var myConfigs = {
				paginator : new YAHOO.widget.Paginator({
					rowsPerPage: 50
				})
			};


			var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,myConfigs); 
		}

		function selectAllPeopleInRegion(id)
		{
			var name = "influencePeopleCheck_"+id;
			var elmts = document.getElementsByName(name);

			if(elmts.length == 0)
				return;

			for(var i=0; i<elmts.length; i++)
			{
				if(!elmts[i].checked)
					elmts[i].checked = true;
				else
					elmts[i].checked = false;
			}	

		}
		
		function selectAllPeople()
		{
			var elmts = document.getElementsByTagName('input');

			if(elmts.length == 0)
				return;

			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].type == "checkbox" && !elmts[i].checked)
					elmts[i].checked = true;
			}
		}

		function DeSelectAllPeople()
		{
			var elmts = document.getElementsByTagName('input');

			if(elmts.length == 0)
				return;

			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].type == "checkbox" && elmts[i].checked)
					elmts[i].checked = false;
			}
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
		
		function sendSMSToSelectedPeople()
		{
				var str = '';	
				str += '<table width="100%">';
				str += '<tr>';
				str += '<th>SMS Text :</th>';
				str += '<td>';
				str += '<textarea id="influencePeopleMsg" onkeyup="limitText(\'influencePeopleMsg\',\'maxcount\',200)" rows="3" cols="38"></textarea>';
				str +='<div id="limitDiv">';
				str +='<table style="width:100%;"><tr>';
				str +='<td align="left" style="width:50%;color:#4B4242;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
				str +='<td align="right" style="width:50%;color:#4B4242;"><div>Max 200 chars</div></td>';
				str +='</tr></table>';
				str +='</div>';	
				str += '</td>';
				str += '</tr>';

				str += '<tr>';
				str += '<td></td>';
				str += '<td><input id="smsIncludeUserName" type="checkbox" name="smsIncludeUserName"></input> Include User Name</td>';				
				str += '</tr>';

				str += '<tr>';
				str += '<td></td>';
				str += '<td><input id="smsIncludeSenderName" type="checkbox" onclick="enableSenderName()" name="smsIncludeUserName"></input> Include Sender Name ';
				str += '<input type="text" id="senderNameText" disabled="disabled" value="${sessionScope.UserName}" />';
				str += '</td>';				
				str += '</tr>';				
				
				str += '</table>';
				str	+= '<div id="smsStatus"></div>';
				
				var connectPopupPanel = new YAHOO.widget.Dialog("messageBox", {      
							 width:'500px',
							 fixedcenter : true, 
							 visible : true,
							 constraintoviewport : true, 
							 iframe :true,
							 modal :true,
							 hideaftersubmit:true,
							 close:true,
							 draggable:true,
							 buttons: [ { text:"Send", handler:handleYes, isDefault:true }] 
				   });	 
				
				connectPopupPanel.setHeader("Send Message");
				connectPopupPanel.setBody(str);
				connectPopupPanel.render();
		}
		
		function enableSenderName()
		{
			var elmt = document.getElementById("senderNameText");
			if(elmt.disabled == true)
				elmt.disabled = false;
			else
				elmt.disabled = true;
		}

		function handleYes()
		{
			var messageElmt = document.getElementById("influencePeopleMsg");
			var statusElmt = document.getElementById("smsStatus");
			var includeElmt = document.getElementById("smsIncludeUserName");
			var includeSenderElmtCheck = document.getElementById("smsIncludeSenderName");
			var senderNameTextElmt = document.getElementById("senderNameText");
			
			var message = messageElmt.value;
			var includeName = false;
			var senderName = '';

			if(includeElmt.checked == true)
				includeName = true;
			
			if(includeSenderElmtCheck.checked == true)
				senderName = senderNameTextElmt.value;

			if(message == "")
			{
				statusElmt.innerHTML = '<font color="red"> Message Content Is Empty .. </font>';
				return;
			}
			else
				statusElmt.innerHTML = '';

		
			var elmts = document.getElementsByTagName('input');
			
			var checkedIds = '';

			if(elmts.length == 0)
				return;
			
			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].type == "checkbox" && elmts[i].name != "regionHeaderCheckBox" && elmts[i].name != "smsIncludeUserName" && elmts[i].checked)
				{
					checkedIds += elmts[i].value;
					checkedIds += ',';
				}
			}

			checkedIds = checkedIds.substring(0,checkedIds.length-1);
			
			if(checkedIds == '' || checkedIds == null)
			{
				statusElmt.innerHTML = '<font color="red"> No People Has Been Selected To Send Message .. </font>';
				return;
			}
			else
				statusElmt.innerHTML = '';
			
			var jsObj= 
			{	
				checkedIdString : checkedIds,
				smsMessage : message,
				includeName : includeName,
				senderName : senderName,
				task: "sendSMSToInfluencePeople"				
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "sendSMSToInfluencePeopleAction.action?"+param;
			
			callAjax(jsObj,url);	
		}

		function callAjax(jsObj,url)
		{
			var myResults;
	 					
	 		var callback = {			
	 		               success : function( o ) 
							  {
								try {												
										if(o.responseText)
											myResults = YAHOO.lang.JSON.parse(o.responseText);
											
										if(jsObj.task == "sendSMSToInfluencePeople")
										{
											var statusElmt = document.getElementById("smsStatus");

											if(myResults.resultStatus == null)
												statusElmt.innerHTML = '<font color="green">SMS has been sent to '+myResults.totalSmsSent+' people successfully .. </font>';
											else
												statusElmt.innerHTML = '<font color="red">SMS cannot been sent to selected people due to some techniccal difficulty.. </font>';
										}
										if(jsObj.task == "deleteInfluencingPerson"){
											alert("Succesfully Deleted");
										}
									}
								catch (e)
									{   
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
			
			function getPersonDetails(id)
			{
				var urlStr = "getInfluencingPersonDetailsAction.action?windowTask=getInfluencingPersonDetailsPopup&personId="+id;
				var browser2 = window.open(urlStr,"influencingPersonDetailPopup","scrollbars=yes,height=570,width=600,left=200,top=50");	
				browser2.focus();
			}
			
			function editPersonDetails(id)
			{
				var type='edit';
				var browser2 = window.open("<s:url action="influencingPeopleAction.action"/>?windowTask="+type+"&influencingPersonId="+id,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
			    browser2.focus();
			}

			function deletePersonDetails(id)
			{
			  var ask = confirm("Do You want to delete");
			  if (ask ==  true)
			  {
				var jsObj= 
				{		
					influencingPeopleId :id,		  			
					task: "deleteInfluencingPerson"		
				};
				
				var param="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/deleteInfluencingPeopleAjaxAction.action?"+param;
				callAjax(jsObj,url);
				window.opener.document.location.reload(true);
				window.location.reload(true);
			  }	
			
			  else
			  {
				return;	
			  }	
			}
			
			function showAllPeopleRecords()
			{
				var elmt = document.getElementById("influencePeopleMainDataDiv");
				var str = '';
				str += '<table id="allPeopleDataTable">';	
				<c:forEach var="region" items="${influencingPeopleDetailsVO}" varStatus ="status">
					<c:forEach var="people" items="${region.influencingPeopleDetails}" varStatus ="status">
						str += '<tr>';
						str += '	<td><input type="checkbox" name="influencePeopleCheck_${region.regionId}" value="${people.influencingPersonId}"></input></td>';
						str += '	<td>${people.firstName}</td>';
						str += '	<td>${people.lastName}</td>';
						str += '	<td>${people.email}</td>';
						str += '	<td>${people.mobile}</td>';
						str += '	<td>${people.gender}</td>';
						str += '	<td>${people.cast}</td>';
						str += '	<td>${people.constituency}</td>';
						str += '	<td>${people.district}</td>';
						str += '	<td>${people.state}</td>';
						str += '	<td><a href="javascript:{}" onclick="getPersonDetails(${people.influencingPersonId})">More Details</a></td>';
						str += '	<td>';
						str += '		<a href="javascript:{}" onclick="editPersonDetails(${people.influencingPersonId})">';
						str += '			<img style="text-decoration: none; border: 0px none;" src="images/icons/edit.png">';
						str += '		</a>';
						str += '	</td>';
						str += '	<td>';
						str += '		<a href="javascript:{}" onclick="deletePersonDetails(${people.influencingPersonId})">';
						str += '			<img style="text-decoration: none; border: 0px none;" src="images/icons/delete.png">';
						str += '		</a>';
						str += '	</td>';
						str += '</tr>';							
					</c:forEach>
				</c:forEach>
				str += '</table>';

				elmt.innerHTML = str;

				buildYUIDTTable("allPeopleDataTable","influencePeopleMainDataDiv");
			}

	</script>
</head>
<body>
	<div id="messageBox_outer" class="yui-skin-sam"><div id="messageBox"></div></div>
	<div id="influencePeopleData_main">		
		<div id="influencePeopleData_head">
			<center> Influencing People In ${regionName}</center>
		</div>
		<div id="influencePeopleData_body" class="yui-skin-sam">		
			<div id="selectButtonsDiv" class="selectButtonsDiv">
				<table width="100%">
					<tr>
						<td align="left">
							<input type="button" class="elbutton" value="View Results By Regions" onclick="javascript:{window.location.reload();}"></input> </div>
							<input type="button" class="elbutton" value="Show All Results" onclick="showAllPeopleRecords()"></input>
						</td>
						<td align="right">
							<input type="button" class="elbutton" value="select All" onclick="selectAllPeople()"></input>
						
							<input type="button" class="elbutton" value="DeSelect All" onclick="DeSelectAllPeople()"></input>
							<input type="button"  class="elbutton"value="Send SMS" onclick="sendSMSToSelectedPeople()"></input>
						</td>
					</tr>
				</table>
				
			</div>
			
			<div id="influencePeopleMainDataDiv" style="width:28.28cm;">
			<c:forEach var="region" items="${influencingPeopleDetailsVO}" varStatus ="status">
				<div id="peopleRegion_main_ ${status.index}" class="peopleDataMain">
					<div id="peopleRegion_head_ ${status.index}" class="scopeWise_head">
						<table>
							<tr>
								<td><img src="images/icons/system_grps.png"></td>
								<td>${region.regionName} ${region.regionType}</td>
							</tr>
						</table>
					</div>
					<div id="peopleRegion_body_ ${status.index}">
						<table id="peopleDataTable_${status.index}">						
							<c:forEach var="people" items="${region.influencingPeopleDetails}" varStatus ="status">
								<tr>
									<td><input type="checkbox" name="influencePeopleCheck_${region.regionId}" value="${people.influencingPersonId}"></input></td>
									<td>${people.firstName}</td>
									<td>${people.lastName}</td>
									<td>${people.email}</td>
									<td>${people.mobile}</td>
									<td>${people.gender}</td>
									<td>${people.cast}</td>
									<td>${people.constituency}</td>
									<td>${people.district}</td>
									<td>${people.state}</td>
									<td><a href="javascript:{}" onclick="getPersonDetails(${people.influencingPersonId})">More Details</a></td>
									<td>
										<a href="javascript:{}" onclick="editPersonDetails(${people.influencingPersonId})">
											<img style="text-decoration: none; border: 0px none;" src="images/icons/edit.png">
										</a>
									</td>
									<td>
										<a href="javascript:{}" onclick="deletePersonDetails(${people.influencingPersonId})">
											<img style="text-decoration: none; border: 0px none;" src="images/icons/delete.png">
										</a>
									</td>
								</tr>							
							</c:forEach>
						</table>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>		
	</div>
		
	<script type="text/javascript">
		
		buildDataTable();
	</script>
</body>
</html>