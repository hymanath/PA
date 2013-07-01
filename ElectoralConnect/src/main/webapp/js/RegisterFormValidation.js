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


   $(".form-horizontal").validate({
	onkeyup: function(element) {$(element).valid()},
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
				number: true,
				minlength: 10,
			    maxlength: 10
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
            email:"Please enter a valid email address"
            },
		mobileNo:{
				number: "Please provide valid Number",
				minlength: "mobile number should be 10 digits",
				maxlength: "mobile number should be 10 digits"
			},
		epicId: {
				 epic: "Please enter valid Epic Number"
			}
	  },
		/*success: function(label) {
			        if (label.attr('for') == "emailId"){ 
                     label.html("emailId available").addClass("valid");
					  var element = '#' + label.attr('for');
                      $(element).addClass('valid');
					}
					else
						label.html('').addClass('valid')
			 
			},*/
			success: function(element) {
				element.text('ok').addClass('valid');

			},
	});

});

