(function( $ ) {
	$.widget( "ui.combobox", {
	_create: function() {
		var self = this,
		select = this.element.hide(),
		selected = select.children( ":selected" ),
		value = selected.val() ? selected.text() : "";
		
		var input = this.input = $( "<input>")
					.insertAfter( select )
					.val( value )
					.autocomplete({
						delay: 0,
						minLength: 0,
						source: function( request, response ) {
							var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
							response( select.children( "option" ).map(function() {
								var text = $( this ).text();
								if ( this.value && ( !request.term || matcher.test(text) ) )
									return {
										label: text.replace(
											new RegExp(
												"(?![^&;]+;)(?!<[^<>]*)(" +				$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
											), "<strong>$1</strong>" ),
										value: text,
										option: this
									};
							}) );
						},
						select: function( event, ui ) {
							ui.item.option.selected = true;
							self._trigger( "selected", event, {
								item: ui.item.option
							});

                            /*To clear the error message when enter text in the autocompleter field*/
                                // if(select.val() != "")
								//select.next().next().html('');								
								

							//callForId(select.attr('id'));
							//callAjaxForgetInstnDetails($(select).attr('id'),$(ui.item).val());
							
						},						
						change: function( event, ui ) {								
						
							//setEdiIndicator($(select).attr('id').substr(12));		
							if ( !ui.item ) {
								var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
								valid = false;
								select.children( "option" ).each(function() {
									if ( $( this ).text().match( matcher ) ) {
										this.selected = valid = true;				
										return false;
									}									
								});
								

								/*If user enters new text that is not available in the autocompleter then this block will be execute*/
								if(!valid ){	
									

									 /*To clear the error message when enter text in the autocompleter field*/
									    if(select.val() != "")
								           select.next().next().html('');	
										
									//$(select).append('<option value="New" selected>'+$(this).val()+'</option>');
									//callForId(select.attr('id'));	
									
									//callAjaxForgetInstnDetails($(select).attr('id'),$(ui.item).val());
									return false;
								}							
							}
						}
					})
					.addClass( "ui-widget ui-widget-content ui-corner-left" );
					input.data( "autocomplete" )._renderItem = function( ul, item ) {
					return $( "<li></li>" )
						.data( "item.autocomplete", item )
						.append( "<a>" + item.label + "</a>" )
						.appendTo( ul );				
				};
			},
			destroy: function() {
				/*
				this.input.remove();
				this.button.remove();
				this.element.show();
				$.Widget.prototype.destroy.call( this );
				*/
			},
			open: function() {
				this.input.show();
			//	$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				this.input.hide();
			//	$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		});
	})( jQuery );