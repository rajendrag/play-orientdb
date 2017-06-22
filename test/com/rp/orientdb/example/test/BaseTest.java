package com.rp.orientdb.example.test;

import static play.inject.Bindings.bind;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rp.orientdb.example.domain.Feature;
import com.rp.orientdb.example.domain.User;
import com.rp.orientdb.example.service.UserAccessService;
import java.io.File;
import java.util.Map;
import javax.inject.Inject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.Application;
import play.Configuration;
import play.Logger;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.test.WithApplication;

/**
 * Created by RP on 6/21/17.
 */
public class BaseTest extends WithApplication {

    private static Logger.ALogger log = Logger.of(BaseTest.class );

    protected static Application APP_CACHED;
    protected static com.google.inject.Injector INJECTOR_CACHED;
    protected static Configuration CONFIGURATION;
    protected static GuiceApplicationBuilder APPLICATION_BUILDER;

    static {
        Map<String, Object> testConfig = Maps.newHashMap();
        Configuration extraConfig = new Configuration(testConfig);
        if (APPLICATION_BUILDER == null) {
            APPLICATION_BUILDER = new GuiceApplicationBuilder()
                    .configure(extraConfig)
                    .in(new File("."));
        }
        if(APP_CACHED == null) {
            APP_CACHED = APPLICATION_BUILDER.build();
            INJECTOR_CACHED = Guice.createInjector(APPLICATION_BUILDER.applicationModule());
        }
        if (CONFIGURATION == null) {
            CONFIGURATION = APP_CACHED.configuration();
        }
    }

    @Inject
    UserAccessService userAccessService;

    @Override
    protected Application provideApplication() {
        if (APP_CACHED == null) {
            APP_CACHED =  APPLICATION_BUILDER.build();
            INJECTOR_CACHED = Guice.createInjector(APPLICATION_BUILDER.applicationModule());
        }
        INJECTOR_CACHED.injectMembers(this);
        return APP_CACHED;
    }

    @BeforeClass
    public static void setUp() {
        /*Feature dashboard = new Feature();
        dashboard.setName("DASHBOARD");
        dashboard.setFeatureId("1");
        */

    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Runs after all tests in the annotation above.");
    }

}
