package ar.com.juanek;

import org.apache.shiro.SecurityUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class Logout extends WebPage {

    public Logout() {
        SecurityUtils.getSubject().logout();
        System.out.println("logout");
        System.out.println("subject "+SecurityUtils.getSubject().getPrincipal());

        add(new Link<Void>("home"){
            @Override
            public void onClick() {
                setResponsePage(Home.class);
            }
        } );
    }
}
