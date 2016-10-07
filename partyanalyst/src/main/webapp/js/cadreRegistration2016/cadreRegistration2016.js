function getRegistrationPersonDetails(){

var jsObj={  
			status :"new",
			voterId : 10,
			familyVoterId :0 ,
			cadreId : 1400698
		};
		$.ajax({          
			type : 'GET',    
			url : 'getRegistrationPersonDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
			buildCadreFamilyDetails(result);
			buildCadreRelativesDetails(result);
		  console.log(result);
		});

}
function buildCadreFamilyDetails(result) {
	$(".cadreFamilyDetailsCls").html('');
	var str = '';
	str += '<ul class="searchResults">';
	if (result.cadreFamilyDetails != null
			&& result.cadreFamilyDetails.length > 0) {
		for ( var i in result.cadreFamilyDetails) {
			str += '<li>';
			str += '<div class="media">';
			str += '<div class="media-left">';
			str += '<img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>';
			str += '</div>';
			str += '<div class="media-body">';
			str += '<h5 class="text-capitalize">'
					+ result.cadreFamilyDetails[i].voterName + '</h5>';
			str += '<p>S/o:' + result.cadreFamilyDetails[i].relativeName
					+ '</p>';
			str += '<p>V.ID' + result.cadreFamilyDetails[i].voterCadreNO
					+ ' </p>';
			str += '<p>H.no:' + result.cadreFamilyDetails[i].houseNo
					+ '&nbsp;&nbsp;|';
			str += '<span>&nbsp;&nbsp;Gender : '
					+ result.cadreFamilyDetails[i].gender
					+ '&nbsp;&nbsp;|</span>';
			str += '<span>&nbsp;&nbsp;Age :' + result.cadreFamilyDetails[i].age
					+ ' </span>';
			str += '</p>';
			str += '<div class="checkboxAlign">';
			str += '<input id="checkbox-18" class="checkbox-custom" name="checkbox-1" type="checkbox">';
			str += '<label for="checkbox-18" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>';
			str += '</div>';
			str += '</div>';
			str += '</div>';
			str += '</li>';
		}
	}
	str += '</ul>';
	str += '<p class="m_top30">Note: If no nominee is present in the above list. Please click <a href="#" class="text-capital" id="addNewNomineeId"> add new nominee</a></p>';
	$(".cadreFamilyDetailsCls").html(str);

}
  $(document).on("click","#addNewNomineeId",function(){       
	$("#newNomineeDetailsId").show();
   });

function buildCadreRelativesDetails(result) {
	$("#relativeId").append('<option  value="0">Select Relationship</option>');
	 if (result.relativesList != null
			&& result.relativesList.length > 0) {
				
	   for ( var i in result.relativesList) {           
		   if(result.relativeType == result.relativesList[i].name)
		   {
			    $("#relativeId").append('<option selected value="'+result.relativesList[i].id+'">'+result.relativesList[i].name+'</option>');
		   }else
		   {
		   $("#relativeId").append('<option value="'+result.relativesList[i].id+'">'+result.relativesList[i].name+'</option>');
		   }
	   }
	   $("#relativeId").trigger("chosen:updated");       
	}
		     
   }	   
