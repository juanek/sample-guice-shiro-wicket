package ar.com.juanek;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.HashMap;
import java.util.Map;

public class WicketServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> wicketFilterParams = new HashMap<String, String>();
        wicketFilterParams.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        wicketFilterParams.put(WicketFilter.IGNORE_PATHS_PARAM,"/static/");
        wicketFilterParams.put("applicationClassName","ar.com.juanek.WicketApplication");
        filter("/*").through(WicketFilter.class, wicketFilterParams);
        bind(WicketFilter.class).to(CustomWicketFilter.class).in(Scopes.SINGLETON);
        bind(WebApplication.class).to(WicketApplication.class);


    }

    @Singleton
    private static class CustomWicketFilter extends WicketFilter {

        @Inject
        private Provider<WebApplication> webApplicationProvider;

        @Override
        protected IWebApplicationFactory getApplicationFactory() {
            return new IWebApplicationFactory() {
                @Override
                public WebApplication createApplication(WicketFilter filter) {

                    return webApplicationProvider.get();
                }

                @Override
                public void destroy(WicketFilter filter) {
                    // nothing
                }
            };
        }


    }


}
