<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Video Details </title>
<style>
textarea {
    background-color: white;
    border: 1px solid #7F7F7F !important;
    color: #000000;
    font: 12px/17px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 78px;
    padding: 5px 0 5px 10px;
    width: 296px;
}
#headingDiv
{
	background-color: #21B2ED;
    border: 1px solid;
    border-radius: 4px 4px 4px 4px;
    color: white;
    font-family: verdana;
    font-size: 17px;
    height: 26px;
    margin-bottom: 19px;
    margin-left: -83px;
    padding-bottom: 7px;
    padding-left: 245px;
    padding-top: 8px;
    width: 382px;
}
</style>
</head>
<body>
<form name="input" action="advVideoActionForSave.action" method="post">
<div style="margin-left: 314px; float: left; margin-top: 25px;">
<div id="headingDiv"><b>VIDEO DETAILS</b></div>
<div><b style="font-family: verdana; font-size: 13px;">Category : </b><select id="categoryId" style="margin-left: 52px; width: 217px;" name="categoery">
<option value = 0>Select</option>
<option value = 1>News</option>
<option value = 2>Politics</option>
</select></div></br>
<div><b style="font-family: verdana; font-size: 13px;" >Show : </b><input  type="text" id="showId" style="margin-left: 83px;" name="showName"> </input></div></br>
<div><b style="font-family: verdana; font-size: 13px;" >Date : </b><input  type="text" id="dateIdId" style="margin-left: 88px;" name="date"> </input></div></br>
<div><b style="font-family: verdana; font-size: 13px;" >Video Duration : </b><input  type="text" id="durationIdId" style="margin-left: 17px;" name="duration"> </input></div></br>
<div><b style="font-family: verdana; font-size: 13px;" >Thumbnail : </b><input  type="text" id="thumbId" style="margin-left: 50px;" name="thumb"> </input></div></br>
<div><b style="font-family: verdana; font-size: 13px;">Description : </b><textarea name="description" id="descriptionId" style="margin-left: 38px;"></textarea></div></br>
<div><b style="font-family: verdana; font-size: 13px;">Tags : </b><textarea name="tag" id="tagId" style="margin-left: 86px;"></textarea></div></br>
<div><b style="font-family: verdana; font-size: 13px;">Code : </b><textarea name="code" id="codeId" style="margin-left: 86px;"></textarea></div></br>
<div><input type="submit" class="btn-success" value="Submit" style="margin-left: 120px; width: 95px; height: 29px; border-radius: 4px 4px 4px 4px;"></input></div>
</div>
</form>
</body>
</html>