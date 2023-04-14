package ar.com.juanek;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see ar.com.juanek.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private final Injector injector;

	@Inject
	public WicketApplication(Injector injector) {
		this.injector = injector;
	}
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Home.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		getMarkupSettings().setStripWicketTags(true);

		// Configure Shiro
		AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
		getSecuritySettings().setAuthorizationStrategy(authz);
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(
				new ShiroUnauthorizedComponentListener(Login.class, Unauthorized.class, authz));


		// add your configuration here
		mountPage("/",Home.class);
		mountPage("/login",Login.class);
		mountPage("/logout",Logout.class);
		mountPage("/account",Account.class);

		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));
	}
}
