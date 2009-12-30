function displayAnchorDiv(id)
{
	var elmt=document.getElementById(id);
	elmt.style.padding=' 5px 7px';
	elmt.style.background='#2A2F35';
	var divId=id+"Div";			
	var divElmt=document.getElementById(divId);
	if(divElmt.style.display=="none")
		divElmt.style.display="block";			
}
function hideAnchorDiv(id)
{
	var elmt=document.getElementById(id);
	elmt.style.padding=' 1px 8px';
	elmt.style.background='none';
	
	var divId=id+"Div";			
	var divElmt=document.getElementById(divId);
	divElmt.style.display="none";
}