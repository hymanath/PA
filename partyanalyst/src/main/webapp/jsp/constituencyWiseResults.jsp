<html>
<head>

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

</head>



<body>
<span id="errormsgId" style="font:red"></span>
<form method="GET" action="constiteuncyWiseResults.action" onSubmit="return validate()">
<div  style="width: 500px; margin-top: 73px; padding-right: 18px; margin-left: 184px; margin-bottom: 63px;"">
<div >
<span style="font-size:15px">stateno</span><span style="margin-left:57px">:</span>
<span style="margin-left:37px"><input type="text" id="stateId" name="stateNo"  style="width: 66px;margin-left:-21px"></span>
</div>
<div style="margin-top: -2px; margin-bottom: 10px;>
<span style="font-size:15px">Level</span><span style="margin-left:81px">:</span>
<span style="margin-left:37px">
<select name="Level" style="margin-left: -21px;width:103px" id="level">
<option value="1" selected="selected">Assembly</option>
<option value="2">Parliament</option>
</select>
</div>
<div >
<span style="font-size:15px">constituencyno</span><span style="margin-left:9px">:</span>
<span><input type="text" id="constituencyId" name="constituencyNo"  style="width: 66px;margin-left:15px"></span></div>
<div>
<span style="font-size:15px">Description</span><span style="margin-left:36px">:</span>
<span style="margin-left:18px"> <textarea rows="1" cols="25" name="description"style="background-color:white" id="descId"></textarea></span>
</div>
<input type="submit" value="Submit" style="margin-left: 134px; margin-top: 17px;" >
</form> 
<div>

<div style="margin-top: 41px;">
<div style="margin-top: -2px; margin-bottom: 10px;>
<span style="font-size:15px">status</span><span style="margin-left:81px">:</span>
<span style="margin-left:37px">
<select name="status" style="margin-left: -21px;width:103px" id="status">
<option value="1" selected="selected">start</option>
<option value="2">stop</option>
</select>
</div>
<div style="margin-top: -2px; margin-bottom: 10px;>
<span style="font-size:15px">Level</span><span style="margin-left:81px">:</span>
<span style="margin-left:37px">
<select name="Level" style="margin-left: -21px;width:103px" id="level1">
<option value="1" selected="selected">Assembly</option>
<option value="2">Parliament</option>
</select>
</div>
<div>
 <span>Password</span><span>:</span><input type="text" id="passId" style="width: 120px; margin-left: 78px;">
 </div>
 <div>
<input type="button" value="Submit" onclick="checkForPassword()" >
</div>
</div>
</div>





<script type="text/javascript">
function checkForPassword()
{
  var password=$("#passId").val();
  var status=$("#status option:selected").val();
  var level1=$("#level1 option:selected").val();
  
    $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});
	    
    $.ajax({
     type:    "POST",
     url:     "checkingPassword.action",
	 dataType: 'json',
     data:    {password:password,status:status,level:level1},
     success : function(data)
	          {  
               
		      },
     error:function() { 
		              }
    });
}
function validate()
{
  var str='';
  var level=$("#level option:selected").val();
  var constituencyNo=document.getElementById("constituencyId").value;
  var description=document.getElementById("descId").value;
  if(constituencyNo=="")
    str+="constituencyNo Field is mandatory";
  if(level==1)
  {
     if(constituencyNo!='' && (constituencyNo>300 || constituencyNo<=0) )
	  str+="constituencyNo must be in between 1 to 300 for Assembly.<br>";
  }
  if(level==2)
  {
     if(constituencyNo!='' && (constituencyNo>42 || constituencyNo<=0) )
	  str+="constituencyNo must be in between 1 to 42 for Parliament.<br>";
  }
  
  if(description=='')
  {
    str+="Description should not be empty.<br>";
  }
  
  if(str!='')
  { 
   $("#errormsgId").html(str);
   return false;
   }
  else
    return true;
 }

</script>
</body>
</html>
















 
 
 
 
 
 
 