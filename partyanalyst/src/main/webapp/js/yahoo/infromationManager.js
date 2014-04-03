	function buildUsers(results,jobj)
	{
	
		var str='';
		str+='<table class="table table-striped table-bordered">';
        str+='<thead>';
	    str+=' <tr>';
        str+='<th>';
        str+='<input id="selectAllId" onclick="selectAll();" type="checkbox" class="selectAllCheck" data-original-title="Tooltip on top" data-toggle="tooltip" data-placement="top">Select All</th>';
         str+='<th>First Name</th>';
         str+='<th>Last Name</th>';
        str+='<th>Contact Number</th>';
		str+='<th>Designation</th>';
        str+='</tr>';
        str+='</thead>';
		str+='<tbody>';
	  for(var i in results)
		{
		
        str+='<tr >';
        str+='<td><input type="checkbox" class="userCheck" id=user'+results[i].registrationID+' value='+results[i].registrationID+'></td>';
        str+='<td >'+results[i].firstName+'</td>';
        str+='<td>'+results[i].lastName+'</td>';
        str+='<td class="mobile" value='+results[i].mobile+'>'+results[i].mobile+'</td>';
		 str+='<td>'+results[i].designation+'</td>';
        str+='</tr>'
	     }   
        str+='</tbody>';
        str+='</table>';
   /*str+='<div class="row-fluid"><div class="span3"><a class="btn btn-mini btn-block" id="addOtherContact" onclick="addOtherContact();">Click Here to add new contact nummber</a></div>'; 
   str+='<div class="span8" id="otherContactsDiv" style="display:none;"><input type="text" placeholder="Name"> &nbsp;&nbsp;&nbsp; <input type="text" placeholder="Contact Number" id="mobileNumber"></div>';
   str+='</div><textarea rows="3" id="description" class="span10" placeholder="Enter Your Message Here" style="background:#fff;"></textarea>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><button type="submit" class="btn btn-large btn-primary " onclick="sendSMS()">Send SMS</button>';
   str+='</div>';*/

 /*str+='<div class="row-fluid"> ';
 str+='<div class="span12 bs-docs-example form-inline"><label>Do you want to add new Name &amp; Contact number please enter and submit details: &nbsp;';
 str+='<input type="text" class="input-small" placeholder="First Name">';
 str+='<input type="text" class="input-small" placeholder="Last Name">';
 str+='<input type="password" placeholder="Phone Number" class="input-small">';
 str+=' <button type="submit" class="btn">submit</button>';

          
 str+='</label></div>';
 str+='</div><textarea rows="3" class="span9 m-top15" placeholder="Enter Your Message Here"></textarea>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-large btn-info" type="submit">Send SMS</button>';*/
 /*str+='<div class="row-fluid"><div class="span3"><a class="btn btn-mini btn-block" id="showOtherContact" onclick="showOtherContact();">Click Here to add new contact nummber</a></div></div>'; 
 str+='<div class="row-fluid" id="otherContactsDiv" style="display:none;">';
 str+='<div class="span12 bs-docs-example form-inline otherInfo">';
 //str+='<label>Do you want to add new Name &amp; Contact number please enter and submit details: &nbsp; ';
str+=' <input type="text" class="input-small firstname" placeholder="First Name" id="firstName" >&nbsp;&nbsp;';
str+='<input type="text" class="input-small lastName" placeholder="Last Name" id="lastName">&nbsp;&nbsp;';
str+='<input type="text" placeholder="Phone Number" class="input-small mobileNumber" id="mobileNumber" >&nbsp;&nbsp;';
str+='<button type="submit" class="btn" onclick="addOtherContact(\'otherContactsDiv\');">add&nbsp;&nbsp;</button>';
str+='<button type="submit" class="btn" onclick="deleteOtherContact(\'otherContactsDiv\');">delete&nbsp;&nbsp;</button>';
str+='</label></div></div>';*/
str+='<textarea rows="3" id="description" class="span9 m-top15" placeholder="Enter Your Message Here" style="background:#fff;"></textarea>';
str+='&nbsp;&nbsp; &nbsp;&nbsp;<button class="btn btn-large btn-info" type="submit" onclick="sendSMS()">Send SMS</button><img id="ajaxImage" src="./images/icons/search.gif" alt="Processing Image" style="display:none;float:right;"/>';
$("#userInfoDiv").html(str);

	}

	function showOtherContact()
	{
		$("#otherContactsDiv").css("display","block");
	}
	var k=0;
	function addOtherContact(id)
	{
	
	var str='';
	var divid = 'otherContactsDiv'+k+'';
	str+='<br/><br/><div class="row-fluid" id="otherContactsDiv'+k+'">';
	str+='<div class="span12 bs-docs-example form-inline otherInfo">';
  // str+='<label>Do you want to add new Name &amp; Contact number please enter and submit details: &nbsp; ';
	
	str+=' <input type="text" class="input-small firstname" placeholder="First Name" id="firstName'+k+'" >&nbsp;&nbsp;';
str+='<input type="text" class="input-small lastName" placeholder="Last Name" id="lastName'+k+'">&nbsp;&nbsp;';
str+='<input type="text" placeholder="Phone Number" class="input-small mobileNumber" id="mobileNumber'+k+'" >&nbsp;&nbsp;';
	
	str+='<button type="submit" class="btn" onclick="addOtherContact(\''+divid+'\')">add</button>&nbsp;&nbsp;';
	str+='<button type="submit" class="btn" onclick="deleteOtherContact(\''+divid+'\');">delete</button>&nbsp;&nbsp;';
	str+='</label></div></div>';
	$("#"+id+"").append(str);
	k++;
	}
	function deleteOtherContact(id)
	{
	$("#"+id+"").remove();
	}
	