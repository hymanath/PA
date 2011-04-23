<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;" %>
<HTML>
<HEAD>
<link  href="css/main.css" rel="stylesheet" type="text/css" />
<TITLE>Party Performance Report In Each Booth</TITLE>

<style type="text/css">
#errorMessage
{
	color:red;
	font-weight:bold;
	padding-top:5px;
}
#partyboothresultsheading{
	background-image:url(images/icons/constituencyManagement/header_body_blue.png);
	height:30px;
	width:255px;
	 color: #ffffff;
    font-family: times New Roman;
    font-size: 24px;
    font-weight: bold;
    margin-top: 32px;
}
#tableDiv{
	margin-left: 75px;
	 margin-top: 18px;
}
th.thstyle{
	 color: #724400;
	 font-size:13px
}
 select.selectstyle{
 width:150px;
 }
 #btnStyle{
	background: url("images/icons/homePage_new/btn_homePage.png") no-repeat scroll 0 0 transparent;
	border:none;
    color: #FFFFFF;
    font-size: 15px;
	font-weight: bold;
    height: 31px;
    width: 101px;
 }
</style>

<!-- Dependencies -->
<script src="http://yui.yahooapis.com/2.7.0/build/yahoo/yahoo-min.js"></script>

<!-- Source file -->
<script src="http://yui.yahooapis.com/2.7.0/build/json/json-min.js"></script>
<script src="js/partyBoothResults/boothResults.js" ></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

<script type="text/javascript">

var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
		String electionTypeMsg = rb.getString("electionTypeMsg");
		String electionYearMsg = rb.getString("electionYearMsg");
		String ConstituencyNameMsg = rb.getString("ConstituencyNameMsg");
		String partyNameMsg = rb.getString("partyNameMsg");
			%> }
	
	function showAjaxDiv()
	{
		var elmt = document.getElementById("ajaxImg");
		if(elmt)
			elmt.style.display = "block";

		return true;
	}


	function showImage2()
	{
		var img = document.getElementById("ajaxImg2");
		img.style.display = 'block';
		setTimeout('stopImage2()',500);
	}

	function stopImage2()
	{
		var img = document.getElementById("ajaxImg2");
		img.style.display='none';
	}

	
	function clearConstituencys()
	{
			clearOptionsListForSelectElmtId("ConstituencyName1");
			createSelectOptionsForSelectElmtId("ConstituencyName1");
		
	}

	function clearpartyNames()
	{
			clearOptionsListForSelectElmtId("partyName1");
			createSelectOptionsForSelectElmtId("partyName1");
	}

	function removeErrorMessage()
		{
			if(document.getElementById("errorMessage"))
			{
				document.getElementById("errorMessage").innerHTML="";
			}
		}

	
	function validateAndForwardToAction()
	{
		    var errorFlag=0;
			var message="";	
			
			if(document.getElementById("electionType1").value==0)
			{
				message+='<%=electionTypeMsg %>';
				message+='<br/>';
				errorFlag=1;
			}

			if(document.getElementById("electionYear1").value==0)
			{
				message+='<%=electionYearMsg %>';
				message+='<br/>';
				errorFlag=1;
			}

			if(document.getElementById("ConstituencyName1").value==0){
				message+='<%=ConstituencyNameMsg %>';
				message+='<br/>';
				errorFlag=1;
			}
			
			if(document.getElementById("partyName1").value==0)
			{
				message+='<%=partyNameMsg %>';
				message+='<br/>';
				errorFlag=1;
				electionFlag=1;
			}
			
			if(errorFlag==1){
				document.getElementById("errorMessage").innerHTML = message;
				return false;
			}
			
			else
			{
				showAjaxDiv();
				document.BoothPerformanceReport.action="partyBoothResult2Action.action";
				document.BoothPerformanceReport.method="post"
				document.BoothPerformanceReport.submit();
				return true;							
			}	
      }

</script>
</HEAD>
<body>

	<s:url action="partyBoothResult1AjaxAction" id="getConsituencyURL" />
	<s:url action="partyBoothResultPartyAjaxAction" id="getPartyURL" />
	<div  id= "partyboothresultsheading">
	<table border="0" cellpadding="0" cellspacing="0">          
		<tr><td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
			<td><div>Party Booth Results</div></td>
	       <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
	   </tr>
		</table>
	</div>
	<table>
	  <tr>
	   <th align="left">
		<div id="errorMessage"></div>
	   </th><td></td>
	  </tr>
	</table>
	 <s:form name="BoothPerformanceReport">
	 <div id="tableDiv">
	   <table cellpadding="10px" cellspacing="0px" width="100px"  style="border: 3px ridge #3385AD;background-color: #F2F0EE;">
		 <tr id="ElectionTyperow">
			<th class="thstyle">Election Type</th>
		      <td> <s:select cssClass="selectstyle" label="Election Type"  name="electionType" list="electionTypes" listKey="id" listValue="name" headerKey="0" headerValue="Select" id="electionType1" onchange="removeErrorMessage()" theme="simple"/>
			  </td>
		   </tr>
           <tr id="ElectionYearrow">
			 <th class="thstyle">Election Year</th>
			  <td><s:select cssClass="selectstyle" label="Election Year"  name="electionYear"
					list="electionYears" headerKey="0" headerValue="Select" id="electionYear1"		onchange="getConstituenciesList(this.form,'%{getConsituencyURL}'),clearConstituencys(),removeErrorMessage()" theme="simple"/>
				</td>
			</tr>
			<tr id="constituencyRow">
			  <th class="thstyle">Constituency</th>
				<td width="150px"><s:select cssClass="selectstyle" label="Constituency" name="constituencyName" 
				list="%{#{'0':'Select'}}" theme="simple" id="ConstituencyName1"  onchange="getConstituenciesList(this.form,'%{getPartyURL}'),showImage2(),clearpartyNames(),removeErrorMessage()"/>
				</td>

				<td style="border:none;">
				<img id="ajaxImg2" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
				</td>
   			</tr>

			<tr id="partyRow">
				<th class="thstyle">Party</th>
				 <td><s:select cssClass="selectstyle" label="Party" name="partyName" id="partyName1" 		                         onchange="removeErrorMessage()"
					  list="%{#{'0':'Select'}}" theme="simple"/>			
				 </td>
				 <td style="border:none;">
					<img id="ajaxImg" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
				</td>
			</tr>
		</table>
		</div>
	<div>
		<table>
			<tr><th></th>
				<td style="padding: 20px;">
					<input id="btnStyle" type="button" id="subbutton" value="View Report" onclick="validateAndForwardToAction()" />
				</td>
			</tr>
		</table>
	</div>
</table>
</s:form>
</body>
</HTML>
