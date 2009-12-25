<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Problem</title>
<style type="text/css">
.problemDetailsTable td
	{
		text-align:left;
		color:#926682;
		
	}
.personDetailsTable
{
	text-align:left;
	color:#926682;	
}
.requiredFont
	{
		color:red;
		margin-left:5px;
	}
.accessDivMain
	{
		padding-bottom:20px;
	}
.accessDivHead
	{	
		
		color:#664141;
	}
.accessDivBody
	{
		padding-top:10px;
	}
</style>
<script type="text/javascript">
function getCurrentDate()
{
			var m_names = new Array("January", "February", "March", 
			"April", "May", "June", "July", "August", "September", 
			"October", "November", "December");


			var d = new Date();
			var curr_date = d.getDate();
			var curr_month = d.getMonth();
			var curr_year = d.getFullYear();
			
			var todayDate=curr_date + "/" + m_names[curr_month] + "/" + curr_year;

			window.document.form.reportedDateField.value = todayDate;
}
function getPersonDetails(value)
{
	//var probSource = document.form.probSource.value;
	var elmt = document.getElementById("personDetailsDiv");
	if(!elmt)
		alert("No div present to display personal details");
	if(value=="External Person" || value=="Call Center") 
	{		
		elmt.style.display = 'block';
	}
	else
	{	
		elmt.style.display = 'none';
	}
}
</script>
</head>
<body>
<s:form action="problemManagementAction" method="POST" theme="simple" name="form">
<h2>Add New Problem</h2>
 
<div id = "addNewProblemMainDiv">		
	<div id="problemDetailsDiv" class="accessDivMain" >
			
			<div id="problemDetailsDivBody" class="accessDivBody">
				<table class="problemDetailsTable">
					<tr class="accessDivHead">
						<th align="left" colspan="2"><u>Problem Details</u></th>
					</tr>
					<tr></tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="problemField" id="problemLabel"  value="%{getText('problem')}" /></td>
						<td style="padding-left: 15px;"><s:textfield id="problemField" name="problem" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="descriptionField" id="descriptionLabel"  value="%{getText('description')}" /></td>
						<td style="padding-left: 15px;"><s:textarea id="descriptionField" name="description" cols="50"/></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="locationField" id="locationLabel"  value="%{getText('location')}"
						/></td>
						<td style="padding-left:15px;"><s:textfield id="locationLabel" name="location" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="reportedDateField" id="reportedDateLabel" value="%{getText('reportedDate')}"
						/></td>
						<td style="padding-left:15px;"><s:textfield id="reportedDateField" name="reportedDate" size="53"/> 
						</td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="existingFromField" id="existingFromLabel" value="%{getText('existingFrom')}"
						/></td>
						<td style="padding-left:15px;"><s:textfield id="existingFromField" name="existingFrom" size="53"/> 
						</td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font><s:label for="problemSourceField" id="problemSourceLabel"  value="%{getText('problemSource')}" /></td>
						<td style="padding-left:15px;"> 
							<s:select name="probSource" id="probSource"  list="problemSources" listKey="id" listValue="name" onchange="getPersonDetails(this.options[this.selectedIndex].text);"></s:select>
						</td>
					</tr>													
				</table>
			</div>
			<div id="personDetailsDiv" class="accessDivBody" style="display: none;">
				<table class="personDetailsTable">
					<tr class="accessDivHead">
						<th align="left" colspan="2"><u>Complained Person Details</u></th>
					</tr>
					<tr></tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="personNameField" id="personNameFieldLabel"  value="%{getText('name')}" /></td>
						<td style="padding-left: 15px;"><s:textfield id="personNameField" name="name" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font> <s:label for="mobileField" id="mobileFieldLabel"  value="%{getText('mobile')}" /></td>
						<td style="padding-left: 15px;"><s:textfield id="mobileField" name="mobile" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="telephoneNoField" id="telephoneNoLabel"  value="%{getText('telephoneNo')}" /></td>
						<td style="padding-left:15px;"><s:textfield id="telephoneNoField" name="phone" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></td>
						<td style="padding-left:15px;"><s:textfield id="emailField" name="email" size="53"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><font class="requiredFont"> * </font> <s:label for="addressField" id="addressLabel"  value="%{getText('address')}" /></td>
						<td style="padding-left:15px;"><s:textfield id="addressField" name="address" size="53"/>  </td>
					</tr>
				</table>
			</div>
			</div>
			<div style="text-align: center;">
			<s:submit name="Save" value="Save"></s:submit> 
		</div>
</div>
</s:form>
<script type="text/javascript">
getCurrentDate()
</script>
</body>
</html>