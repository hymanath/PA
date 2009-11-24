<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<script type="text/javascript" src="js/json/json-min.js"></script> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript">
		function getList(name,value)
		{ 
			var jsObj=
			{
				type:name,
				value:value
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			callAjax(rparam, name);
		}


		function callAjax(param,name){
			var myResults;
	 		var url = "<%=request.getContextPath()%>/mandalPageDetailAction.action?"+param;			
	 		var callback = {			
               success : function( o ) {
				try {
					myResults = YAHOO.lang.JSON.parse(o.responseText);								
					buildSelectOption(myResults,name);								
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
 
		function buildSelectOption(results,selectedValue)
		{
			var selectedElmt;
			selectOption='';
			if(selectedValue=="STATE")
			{
				selectOption='District';
				selectedElmt=document.getElementById("districtField");
			}
			else if(selectedValue=="DISTRICT")
			{
				selectOption='Constituency';
				selectedElmt=document.getElementById("constituencyField");
			}
			else if(selectedValue=="CONSTITUENCY")
			{
				selectOption='Mandal';
				selectedElmt=document.getElementById("mandalField");
			}
			
			var len=selectedElmt.length;			
			for(i=len-1;i>=0;i--)
			{
				selectedElmt.remove(i);
			}
			var opElmt1=document.createElement('option');
			opElmt1.value=0;
			opElmt1.text='Select '+selectOption;
			
			try
			{
				selectedElmt.add(opElmt1,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt1); // IE only
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
	</script>
</head>
<body>
	<s:form action="mandalPageDetailAction" method="POST" theme="simple">
		<h2>Mandal / Tehsil Page Info</h2>
		<table>
			<tr>
				<th><s:label for="stateField" id="stateLabel"  value="%{getText('STATE')}" /></th>
				<td align="left">
					<s:select id="stateField" name="state" list="states" listKey="id" listValue="name" onchange="getList('STATE',this.options[this.selectedIndex].value)"></s:select>
	
					
				</td>
			</tr>
			<tr>
				<th><s:label for="districtField" id="districtLabel"  value="%{getText('DISTRICT')}" /></th>
				<td align="left">
					<select id="districtField" name="district" onchange="getList('DISTRICT',this.options[this.selectedIndex].value)"></select>
					
				</td>
			</tr>
			<tr>
				<th><s:label for="constituencyField" id="constituencyLabel"  value="%{getText('CONSTITUENCY')}" /></th>
				<td align="left">
					<select id="constituencyField" name="constituency" onchange="getList('CONSTITUENCY',this.options[this.selectedIndex].value)"></select> 
				</td>
			</tr>
			<tr>
				<th><s:label for="mandalField" id="mandalLabel"  value="%{getText('MANDAL')}" /></th>
				<td align="left">
					<select id="mandalField" name="mandal"></select>				 
				</td>
			</tr>
			<tr>
				<th><s:label for="partyField" id="mandalLabel"  value="%{getText('PARTY')}" /></th>
				<td align="left">
					<select id="partyField" name="party"></select>				 
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>