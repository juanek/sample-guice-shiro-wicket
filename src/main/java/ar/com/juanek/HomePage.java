package ar.com.juanek;

import org.apache.shiro.SecurityUtils;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	//http://localhost:8080/wicket/bookmarkable/ar.com.juanek.HomePage

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		System.out.println("subject "+SecurityUtils.getSubject().getPrincipal());

		// TODO Add your page's components here

	}
}
