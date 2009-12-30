<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Constituency Page</title>
</head>  
<body>  
<s:form action="constituencyPageAction" method="POST">  
    <h2>Constituency Page</h2>  
     
    <s:textfield label="Enter Constituency Id :" name="constituencyId"></s:textfield>  
    <s:submit label="Save" name="Save"></s:submit>  
     
</s:form>  
</body>  
</html>
   