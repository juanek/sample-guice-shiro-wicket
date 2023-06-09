/*
 * Copyright 2008 Les Hazlewood
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.juanek;

import ar.com.juanek.pages.*;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;
import org.wicketstuff.shiro.page.LogoutPage;

/**
 * 
 */
public abstract class ExampleApplication extends WebApplication
{

	@Override
	protected void init()
	{
		getMarkupSettings().setStripWicketTags(true);

		// Configure Shiro
		AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
		getSecuritySettings().setAuthorizationStrategy(authz);
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(
			new ShiroUnauthorizedComponentListener(LoginPage.class, UnauthorizedPage.class, authz));

		mountPage("account/login", LoginPage.class);
		mountPage("account/logout", LogoutPage.class);
		mountPage("admin", RequireAdminRolePage.class);
		mountPage("view", RequireViewPermissionPage.class);
		mountPage("auth", RequireAuthPage.class);

		getComponentInstantiationListeners().add(new GuiceComponentInjector(this));

		// best place to do this is in Application#init()
		Bootstrap.install(this);

// if you want to customize bootstrap:
		BootstrapSettings settings = new BootstrapSettings();

		final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Cyborg);
		settings.setThemeProvider(themeProvider);

		//settings.setXXX(...);
		Bootstrap.install(this, settings);
	}

	public abstract Component getExampleInfoPanel(String id);

	public abstract Component getAuthHeaderPanel(String id);

	@Override
	public Class<? extends Page> getHomePage()
	{
		return IndexPage.class;
	}

// @Override
// // You'll need to do this only if using Shiro enterprise/clustered Sessions:
// protected ISessionStore newSessionStore()
// {
// return new SecondLevelCacheSessionStore(this, new SessionPageStore(100));
// }
}
