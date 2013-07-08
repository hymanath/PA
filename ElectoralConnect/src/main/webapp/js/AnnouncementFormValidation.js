$(document).ready(function(){
	  jQuery.validator.setDefaults({
       debug: true,
	   success: "valid"
       });

   $('[name="AnnouncementForm"]').validate({
	onfocusout: function(e) {
      this.element(e);
      }, onkeyup: false,

	  rules: {
		title: {	
			required: true		
			},
		description: {
		    required :true
			
			},
		/* date: {
		    required: true
			}, */
		filetitle: {
			required:{ 
				depends: function(element) {
                        if ($('#attachFileId').is(':checked')){
                         return true;
                         }else{
                         return false;}
				      }
			         }
			},
		fileDescription: {
			required:{ 
				depends: function(element) {
                        if ($('#attachFileId').is(':checked')){
                         return true;
                         }else{
                         return false;}
				      }
			    }
			},
		docs: {
			required:{ 
				depends: function(element) {
                        if ($('#attachFileId').is(':checked')){
                         return true;
                         }else{
                         return false;}
				      }
			         },
				accept: 'docx?|pdf'
			}
	  },	
	  messages: {
		title: {
			required: "Please enter the Title",
		   
			},
		description: {
		   required: "Please enter the Description"
			
		   },
		/*date:{
			required: "Please select the Date"
			},*/
		filetitle: {
			required: "Enter the File title"
			},
		fileDescription: {
			required: "Enter the File description"
			},
	    docs: {
			required: "Upload the Document",
				accept: "Upload only pdf,doc,docx files"
			}
	  },
	      success: function(label) {
					  label.html('ok').addClass('valid');
					
			},
		
	});



});  

