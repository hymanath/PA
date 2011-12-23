<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>SocialAuth Demo</title>
        <script>
            function validate(obj){
                var val = obj.id.value;
                if(trimString(val).length <= 0){
                    alert("Please enter OpenID URL");
                    return false;
                }else{
                    return true;
                }
            }
            function trimString(tempStr)
            {
               return tempStr.replace(/^\s*|\s*$/g,"");
            }
        </script>
    </head>
    <body>
        <table cellpadding="10" cellspacing="10" align="center">
            <tr><td colspan="8"><h3 align="center">Welcome to Social Auth Demo</h3></td></tr>
            <tr><td colspan="8"><p align="center">Please click on any icon.</p></td></tr>
            <tr>
                <td><a href="socialAuthenticationAction.action?id=facebook"><img src="images/facebook_icon.png" alt="Facebook" 
                title="Facebook" border="0"></img></a></td>
                <td><a href="socialAuthenticationAction.action?id=twitter"><img src="images/twitter_icon.png" alt="Twitter" 
                title="Twitter" border="0"></img></a></td>
                <td><a href="socialAuthenticationAction.action?id=google"><img src="images/gmail-icon.jpg" alt="Gmail" 
                title="Gmail" border="0"></img></a></td>
                <td><a href="socialAuthenticationAction.action?id=yahoo"><img src="images/yahoomail_icon.jpg" alt="YahooMail" 
                title="YahooMail" border="0"></img></a></td>
                <td><a href="socialAuthenticationAction.action?id=hotmail"><img src="images/hotmail.jpeg" alt="HotMail" 
                title="HotMail" border="0"></img></a></td>
                <td><a href="socialAuthenticationAction.action?id=linkedin"><img src="images/linkedin.gif" alt="Linked In" 
                title="Linked In" border="0"></img></a></td>
                <td><a href="socialAuthenticationAction.action?id=foursquare"><img src="images/foursquare.jpeg" alt="FourSquare" 
                title="FourSquare" border="0"></img></a></td>
                <td><a href="socialAuthenticationAction.action?id=myspace"><img src="images/myspace.jpeg" alt="MySpace" 
                title="MySpace" border="0"></img></a></td>
            </tr>
            <tr>
                <td colspan="8" align="center">
                    <form action="socialAuthenticationAction.action" onsubmit="return validate(this);">
                        or enter OpenID url: <input type="text" value="" name="id"/>
                        <input type="submit" value="Submit"/> 
                    </form>
                </td>
            </tr>
            
        </table>
    </body>
</html>
