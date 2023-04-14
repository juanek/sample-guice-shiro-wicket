package ar.com.juanek;

import org.apache.shiro.SecurityUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.wicketstuff.shiro.component.ShiroConfigInfoPanel;

public class Home extends WebPage {

    public Home() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        boolean isUser = (SecurityUtils.getSubject().getPrincipal() != null);



        add(new Label("user", new IModel<Object>() {
            @Override
            public Object getObject() {
                return
                        isUser ? SecurityUtils.getSubject().getPrincipal():"Guest";
            }
        }));

        add(new Link<Void>("logout") {
            @Override
            public void onClick() {
                setResponsePage(Logout.class);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(isUser);
            }
        });

        add(new Link<Void>("login") {
            @Override
            public void onClick() {
                setResponsePage(Login.class);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(!isUser);
            }
        });


        add(new Link<Void>("account") {
            @Override
            public void onClick() {
                setResponsePage(Account.class);
            }
        });

        add(new ShiroConfigInfoPanel("info"));
    }
}
