var control_timeout, footerHeight;
$(document).foundation();
$(document).ready(function(){
	$("html").niceScroll({ autohidemode: false });
	$('#menu').localScroll({hash:true, onAfterFirst:function(){$('html, body').scrollTo( {top:'-=25px'}, 'fast' );}});
	$('.flexslider').flexslider({
      animation: "fade",
      directionNav: true,
      controlNav: false,
      pauseOnAction: true,
      pauseOnHover: true,
      direction: "horizontal",
      slideshowSpeed: 5500
    });
	
	$('#button-send').click(function(event){
		$('#button-send').html('Sending E-Mail...');
		event.preventDefault();
		
		$('html, body').scrollTo( $('#contact'), 'fast' );
		$.ajax({
			type: 'POST',
			url: 'send_form_email.php',
			data: $('#contact_form').serialize(),
			success: function(html) {
				if(html.success == '1')
				{
					$('#button-send').html('Send E-Mail');
                                        $('#error').hide();
					$('#success').show();
				}
				else
				{   
					$('#button-send').html('Send E-Mail');
                                        $('#error').html(html.error_details + " Could not send the email.")
					$('#error').show();
				}					
			},
			error: function(){
				$('#button-send').html('Send E-Mail');
                                $('#error').html("Could not send the email. Please try again later.");
				$('#error').show();
			}
		});
		
	});
	
	
});


function valemail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
