function displayImage(candidateName){	
	overlib('<div><img style="height:115px;width:115px;" src="images/candidates/'+candidateName+'.jpg" alt="'+candidateName+' Image not available"></img></div>');
}
function removeImage(id){
	document.getElementById(id).style.textDecoration='none';
	nd();
}




function displayImageById(candidateId,candidateName){	
	overlib('<div><img style="height:115px;width:115px;" src="images/candidates/'+candidateId+'.jpg" alt="'+candidateName+' Image not available"></img></div>');
}
function removeImageById(id){
	document.getElementById(id).style.textDecoration='none';
	nd();
}
