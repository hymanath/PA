<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script type="text/javascript">
		function getDistricts(stateID)
		{				
				var url = "<%=request.getContextPath()%>/mptcElectionDistrict.action?stateID="+stateID;
				var myResults;			
		 		var callback = {			
		               success : function( o ) {
								try {
									myResults = YAHOO.lang.JSON.parse(o.responseText);
									buildDistrictList(myResults);								
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

		function buildDistrictList(results){ 
			var selectedElmt = document.getElementById("districtID");
			var len=selectedElmt.length;			
			for(i=len-1;i>=0;i--)
			{
				selectedElmt.remove(i);
			}	
			for(var val in results)
			{			
				var opElmt=document.createElement('option');
				opElmt.value=results[val].id;
				opElmt.text=results[val].name;
				
				try
				{
					selectedElmt.add(opElmt,null); // standards compliant
				}
				catch(ex)
				{
					selectedElmt.add(opElmt); // IE only
				}			
			}
		}

		function getElectionType(){
			var typeEle = document.getElementById("electionTypeID");
			var hiddenEle = document.getElementById("hiddenEleId");
			hiddenEle.value = typeEle.options[typeEle.selectedIndex].text
		}

	</script>
</head>
<body>
<s:form name="mptcElection" action="mptcElectionUpload" method="post" enctype="multipart/form-data" theme="simple">
 	<h3>Election Data Upload</h3>
	<table>
		
		<tr>
			<td><s:label for="countryField" id="countryLabel"  value="%{getText('COUNTRY')}" /></td>		
			<td>
				<select id="countryID" name="countryID">
					<option value="1">India</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /></td>		
			<td>
				<s:select id="stateID" name="stateID" list="stateList" listKey="id" listValue="name" 
						onchange="getDistricts(this.options[this.selectedIndex].value)"></s:select>
			</td>
		</tr>
		<tr>
			<td><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /></td>
			<td><select name="districtID" id="districtID">
					<option value="0">Select option</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Election Type</td>
			<td><select name="electionTypeID" id="electionTypeID" onchange="getElectionType()">
				<option value="0">Select</option>
				<option value="3">MPTC</option>
				<option value="4">ZPTC</option>
				<option value="5">MUNCIPALITY</option>
				<option value="6">CORPORATION</option>
				<option value="7">GMC</option>
			</select><input id="hiddenEleId" type="hidden" name="electionType"></td>			
		</tr>
		<tr>
			<td>Election Subtype</td>
			<td><select name="elecSubtype">
				<option value="0">Select option..</option>
				<option value="MAIN">Main Election</option>
				<option value="BYE">Bye Election</option>
			</select></td>			
		</tr>
		<tr>
			<td>Election Year</td>
			<td>
				<s:textfield size="5" maxlength="4" label="Election Year" name="year" id="year" />
			</td>
		</tr>

		<tr>
			<td colspan="2" align="right"><s:file id="file" size="40" name="file" label="InputFile" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><s:submit name="upload" value="Upload" /></td>
			<td></td>
		</tr>
	</table>
</s:form>
</body>
</html>