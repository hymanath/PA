function addThisContacts(){
			var elmts = document.getElementsByTagName('input');
			
			var checkedIds = '';

			if(elmts.length == 0)
				return;
			
			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].type == "checkbox" && elmts[i].name != "regionHeaderCheckBox" && elmts[i].name != "smsIncludeUserName" && elmts[i].checked)
				{
					checkedIds += elmts[i].value;
					checkedIds += ',';
				}
			}

			checkedIds = checkedIds.substring(0,checkedIds.length-1);
			window.opener.receiveFromChild ( checkedIds );
		}
		
function addThisCadresToContacts(){
	var elements = document.getElementsByName("cadreResult_check");
	var errorSpanElmt = document.getElementById("smsStatusTextSpan");
	$('#smsStatusTextSpan1').html('');
	selectedCadresArray = [];


	if(!elements || !errorSpanElmt){
		$('#smsStatusTextSpan1').html('<font style="color:red;margin-left:75px;">Atleast One cadre need to selected to send SMS</font>');
		return;
	}
	for(var i=0; i<elements.length; i++)
	{
		if(elements[i].checked == false)
			continue;
		
		var cId  = elements[i].value.substring(0,elements[i].value.indexOf('_'));
		var cMobile = elements[i].value.substring(elements[i].value.indexOf('_')+1,elements[i].value.lastIndexOf('_'));
		
		var cName = elements[i].value.substring(elements[i].value.lastIndexOf('_')+1,elements[i].value.length);
		var obj = {
					cadreId:cId,
					cadreMobile:cMobile,
					cadreName:cName
		          };
		if(elements[i].checked == true)
			selectedCadresArray.push(obj);
	}
	
	window.opener.receiveFromCadreChild ( selectedCadresArray );
}