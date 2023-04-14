package ar.com.juanek;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated,loginPage = Login.class)
public class Account extends WebPage {
    public Account() {
        System.out.println("account ok");
        add(new Link<Void>("home"){
            @Override
            public void onClick() {
                setResponsePage(Home.class);
            }
        } );
    }
}
