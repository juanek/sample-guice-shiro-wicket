package ar.com.juanek;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.markup.html.WebPage;

public class Login extends WebPage {
    public Login() {
        System.out.println("logout");
        String username = "root";
        String password = "secret";
        boolean rememberMe = false;
        final Subject currentUser = SecurityUtils.getSubject();
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password,
                rememberMe);
        try
        {
            currentUser.login(token);
            //return true;

            // the following exceptions are just a few you can catch and handle accordingly. See the
            // AuthenticationException JavaDoc and its subclasses for more.
        }
        catch (final IncorrectCredentialsException ice)
        {
            error("Password is incorrect.");
        }
        catch (final UnknownAccountException uae)
        {
            error("There is no account with that username.");
        }
        catch (final AuthenticationException ae)
        {
            error("Invalid username and/or password.");
        }
        catch (final Exception ex)
        {
            error("Login failed");
        }

        System.out.println("login subject "+SecurityUtils.getSubject().getPrincipal());
        continueToOriginalDestination();
    }
}
