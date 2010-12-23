
function callAjax1(param,jsObj,url){
	var myResults;	
	var callback = {			
	    success : function( o ) {
			try {	
					if(o.responseText.length!=0){
						myResults = YAHOO.lang.JSON.parse(o.responseText);	
					}						
					if(jsObj.task == "getStates")
					{
						clearOptionsListForSelectElmtId("stateId");
						createOptionsForSelectElmtId("stateId",myResults);
					}
					if(jsObj.task == "getDistricts")
					{
						clearOptionsListForSelectElmtId("districtField");
						createOptionsForSelectElmtId("districtField",myResults);	
					}	
					if(jsObj.task == "getConstituencies")
					{
						clearOptionsListForSelectElmtId("constituencyField");
						createOptionsForSelectElmtId("constituencyField",myResults);
					}		
					if(jsObj.task == "getMandals")
					{
						clearOptionsListForSelectElmtId("mandalField");
						createOptionsForSelectElmtId("mandalField",myResults);
					}		
					if(jsObj.task == "getTowhships")
					{
						clearOptionsListForSelectElmtId("villageField");
						createOptionsForSelectElmtId("villageField",myResults);
					}
					if(jsObj.task == "getVillages")
					{
						clearOptionsListForSelectElmtId("hamletField");
						createOptionsForSelectElmtId("hamletField",myResults);
					}
					if(jsObj.task == "getParties")
					{
						clearOptionsListForSelectElmtId("party");
						createOptionsForSelectElmtId("party",myResults);
					}
					if(jsObj.task == "getPositions")
					{
						clearOptionsListForSelectElmtId("position");
						createOptionsForSelectElmtId("position",myResults);
					}
					if(jsObj.task == "getInfluencingRange")
					{
						clearOptionsListForSelectElmtId("influRange");
						createOptionsForSelectElmtId("influRange",myResults);
					}
			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

function getSelectOptionVOList(id, task)
{	
	if(id == 0)
		return;
	var jsObj=
		{
				locationId:id,
				task:task						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "problemManagementReportAjaxAction.action?"+rparam;						
		callAjax1(rparam,jsObj,url);
}

function influenceRangeFunction(id,text){
		var specifyBox = document.getElementById("influenceRangeInputId");
		specifyBox.value = text;
}

function getInfluencingPeoplePopUp(divId){
	var elmt = document.getElementById(divId);
	var divChild = document.createElement('div');
	divChild.setAttribute('id','addCommentDiv');
	var contentStr = '';
	contentStr +='';
	contentStr+='<div class="hd" align="left">Influencing People Registration</div>';
	contentStr+='<div class="bd" align="left">';
	contentStr +='<form action="influencingPeopleSaveAction.action" method="GET" onsubmit="getInfluencingPeopleInAConstituency()">';
	contentStr +='<table class="registrationTable">';			
	contentStr +='<tr><th><font class="requiredFont"> * </font>First Name:</th><td><input type="text" id="firstNameField" name="firstName"/></td></tr>';
	contentStr +='<tr><th><font class="requiredFont"> * </font>Last Name:</th><td><input type="text" id="lastNameField" name="lastName"/></td></tr>';					
	contentStr +='<tr><th>Email:</th><td><input type="text" id="emailField" name="email"/></td></tr>';										
	contentStr +='<tr><th><font class="requiredFont"> * </font>Mobile:</th><td><input type="text" id="mobileField" name="mobile"/></td></tr>';
	contentStr +='<tr><th><font class="requiredFont"> * </font>Gender:</th><td><input id="male" type="radio" name="gender" value="M" checked="checked"/>Male';
	contentStr +='<input id="feMale" type="radio" name="gender" value="F"/>Female</td></tr>';
	contentStr +='<tr><th><font class="requiredFont"> * </font>Occupation:</th><td><input type="text" class="selectWidth" id="occupationField"  name="occupation"/></td></tr>';
	contentStr +='<tr><th><font class="requiredFont"> * </font>Cast:</th><td><input type="text" class="selectWidth" id="castField"  name="cast"/> </td></tr>';	
	contentStr +='<tr><th><font class="requiredFont"> * </font>STATE:</th>';
	contentStr +='<td><select id="stateId" name="state" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,\'getDistricts\')" /></td></tr>';
	contentStr +='<tr><th> <font class="requiredFont"> * </font>DISTRICT:</th>';
	contentStr +='<td><select id="districtField" name="district" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,\'getConstituencies\')" /></td></tr>';
	contentStr +='<tr><th><font class="requiredFont"> * </font>CONSTITUENCY:</th>';
	contentStr +='<td><select id="constituencyField" name="constituency" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,\'getMandals\')" /></td></tr>';
	contentStr +='<tr><th> <font class="requiredFont"> * </font>MANDAL:</td>';
	contentStr +='<th><select id="mandalField" name="mandal" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,\'getTowhships\')" /></td></tr>';
	contentStr +='<tr><th> <font class="requiredFont"> * </font>Revenue VILLAGE:</th>';
	contentStr +='<td><select id="villageField" name="village" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,\'getVillages\')" /></td></tr>';
	contentStr +='<tr><th> <font class="requiredFont"> * </font>Hamlet:</th>';
	contentStr +='<td><select id="hamletField" name="hamlet" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,\'getHamletIdAndRange\')"/></td></tr>';
	contentStr +='<tr>';
	contentStr +='<td width="100px;"> <font class="requiredFont"> * </font> Party: </td>';
	contentStr +='<td align="left"><select id="party" name="party" class="selectWidth"/></td>';
	contentStr +='</tr>';
	contentStr +='<tr>';
	contentStr +='<td width="100px;"> <font class="requiredFont"> * </font>Position: </td>';
	contentStr +='<td align="left"><select id="position" name="position" class="selectWidth" /></td>';
	contentStr +='<td><div id="specifyPosition"></div></td>';
	contentStr +='</tr>';					
	contentStr +='<tr>';
	contentStr +='<td width="100px;"> <font class="requiredFont"> * </font>Influence Range: </td>';
	contentStr +='<td align="left"><select id="influRange" name="influenceRange" class="selectWidth" onchange="influenceRangeFunction(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)"/></td>';
	contentStr +='<td><input type="hidden" id="influenceRangeInputId" name="influencingRange"></td>';
	contentStr +='<td><div id="specifyInfluenceRange"></div></td>';
	contentStr +='</tr>';
	contentStr +='<tr><td><input type="submit" value="Save"></td></tr>';
	contentStr +='</table>';
	contentStr +='</form>';
	contentStr +='</div>';
	divChild.innerHTML=contentStr;
	elmt.appendChild(divChild);
		
	var myPanel = new YAHOO.widget.Dialog("addCommentDiv", {

		width : "620px",
		fixedcenter : true,
		visible : true,
		constraintoviewport : true,
		iframe : true,
		modal : true,
		hideaftersubmit : true,
		close : true
	});
	myPanel.render();
	getSelectOptionVOList(this.value,"getStates");
	getSelectOptionVOList(1,"getParties");
	getSelectOptionVOList(1,"getPositions");
	getSelectOptionVOList(1,"getInfluencingRange");
	
}