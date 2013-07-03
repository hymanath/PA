$(document).ready(function(){
	  jQuery.validator.setDefaults({
       debug: true,
	   success: "valid"
       });

   $.validator.addMethod("onlychar", function(value, element) {
          return this.optional(element) || /^[a-zA-Z]+$/.test(value);
    });

	$.validator.addMethod("epic", function(value, element) {
        return this.optional(element) || /^[a-zA-Z]+[0-9]+$/i.test(value);
    });

	$.validator.addMethod("mobile", function(value, element) {
        return this.optional(element) || /^((\+){0,1}91(\s){0,1}(\-){0,1}){0,1}[789][0-9]{3}(\s){0,1}(\-){0,1}[0-9]{6}$/i.test(value);
    });

   $(".form-horizontal").validate({
	onfocusout: function(e) {
      this.element(e);
      }, onkeyup: false,

	  rules: {
		firstName: {	
			required: true,
		    onlychar: true,
			minlength: 2,
			maxlength: 8
			
			},
		lastName: {
		    required :true,
			onlychar: true,
			minlength: 2,
			maxlength: 8
			},
		emailId: {
			required: true,
			email: true
			},
		mobileNo: {
		    mobile: true
			},
		epicId: {
			epic: true
			}
	  },	
	  messages: {
		firstName: {
			required: "Please enter your firstname",
		    onlychar: "Please enter valid firstname",
            minlength: "Please enter atleast 2 characters",
			maxlength: "Enter only maximum of 8 characters"
			
			},
		lastName: {
		   required: "Please enter your lastname",
			onlychar:"Please enter valid lastname",
		    minlength: "Please enter atleast 2 characters",
			maxlength: "Enter only maximum of 8 characters"
		   },
		emailId:{
		    required: "Please provide the Email ID",
            email: "Please enter a valid email address"
            },
		mobileNo:{
				
					mobile: "Please provide valid Number"
			},
		epicId: {
				 epic: "Please enter valid Epic Number"
			}
	  },
	      success: function(label) {

			  if (label.attr('for') == "emailId"){
                     /*label.html("ok").addClass("valid");
					  var element = '#' + label.attr('for');
                      $(element).addClass('valid');*/
					}
					else{
					  label.html('ok').addClass('valid');
					  var element = '#' + label.attr('for');
                      $(element).addClass('valid');
					}
			},
		
	});

    

});

