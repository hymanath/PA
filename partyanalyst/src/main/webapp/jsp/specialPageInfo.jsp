<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<style>

a {
    color: #0088CC;
}
#specialPageMainDiv
{
 background: #FFFFFF;
    border-radius: 5px;
    float: none;
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;
    padding: 10px;
    width: 900px;
	margin-bottom:10px;
}

.headingClass {
   /*color: #1212B8;*/
    color: #0088CC;
    font-family: 'Abel',sans-serif,Helvetica;
    font-size: 15px;
    font-weight: normal;
    line-height: 15px;
    margin: 10px 10px 13px 10px;
    text-rendering: optimizelegibility;
    text-shadow: 1px 1px #FFFFFF;
    text-transform: uppercase;
}
p {
    color: #333333;
    font-size: 13px;
    line-height: 18px;
    margin: 0 0 10px;
}
.innerDivClass a{text-decoration:none;}
 .innerDivClass{padding: 4px;}
	#specialPageInnerDiv{margin-left: 10px;
    margin-top: 10px;}
.specialPageInfoDiv{width: 280px; display: table;background:#f5f5f5;border:1px solid #f3f3f3;border-radius:5px;margin-bottom:10px;display:inline-block;vertical-align:top;margin-right:10px;}
</style>
</head>
<body>
<div id="specialPageMainDiv">
<div id="specialPageInnerDiv">
	<c:forEach var="specialPages" items="${specialPageVOList}">
					<div class="specialPageInfoDiv">
						
						<div class="innerDivClass">
						<a href="specialPageAction.action?specialPageId=${specialPages.specialPageId}" title="${specialPages.title}">
							<h5 class="headingClass">${specialPages.title}</h5>
								<p>
								<img class="pull-left thumbnail span7" src="${specialPages.eventImagePath}" style="width: 100px; height: 100px; margin-bottom: 10px;margin-right: 10px;background:#d3d3d3;" />
									${specialPages.description}
								</p>
						</a>
						</div>
						
					</div>
		</c:forEach>
</div>
</div>

</body>
</html>