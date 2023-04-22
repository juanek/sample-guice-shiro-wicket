package ar.com.juanek.pages;


/**
 * Simple index page
 */
public class IndexPage extends BasePage
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public IndexPage()
	{

	}

	@Override
	public String getTitle()
	{
		return "Shiro for Apache Wicket -- example app";
	}
}
