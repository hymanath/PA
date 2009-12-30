<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>
</head>  
<body>  
<s:form action="RegistrationAction" method="POST">  
    <h2>User Registration Form</h2>  
     
    <s:textfield label="%{getText('firstName')}" name="firstName"></s:textfield>  
    <s:textfield label="%{getText('middleName')}" name="middleName"></s:textfield>
    <s:textfield label="%{getText('lastName')}" name="lastName"></s:textfield>
    <s:textfield label="%{getText('gender')}" name="gender"></s:textfield>
    <s:textfield label="%{getText('userName')}" name="userName"></s:textfield><s:actionerror/>
    <s:password label="%{getText('password')}" name="password"></s:password>
    <s:textfield label="%{getText('dateOfBirth')}" name="dateOfBirth"></s:textfield>
    <s:textfield label="%{getText('email')}" name="email"></s:textfield>
    <s:textfield label="%{getText('telephoneNo')}" name="phone"></s:textfield>
    <s:textfield label="%{getText('mobile')}" name="mobile"></s:textfield>
    <s:textfield label="%{getText('address')}" name="address"></s:textfield> 
    <s:textfield label="%{getText('country')}" name="country"></s:textfield>
    <s:textfield label="%{getText('pincode')}" name="pincode"></s:textfield>
    <s:submit label="Save" name="Save"></s:submit>  
</s:form>  
</body>  
</html>