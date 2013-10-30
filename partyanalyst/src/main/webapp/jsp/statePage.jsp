<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> State Page</title>
</head>  
<body>  
<s:form name="statePage" action="statePageAction" method="POST">  
    <h2>State Page</h2>  
     
    <s:textfield label="Enter State Name :" name="stateId"></s:textfield>  
    <s:submit label="Save" name="Save"></s:submit>  
     
</s:form>  
</body>  
</html>
   