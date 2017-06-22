package com.rp.orientdb.example.test;

import static play.inject.Bindings.bind;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rp.orientdb.example.db.MyGraphFactory;
import com.rp.orientdb.example.domain.Feature;
import com.rp.orientdb.example.domain.User;
import com.rp.orientdb.example.service.UserAccessService;
import com.syncleus.ferma.DefaultClassInitializer;
import com.syncleus.ferma.FramedGraph;
import java.io.File;
import java.util.Map;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

    @Inject
    MyGraphFactory factory;

    @Override
    protected Application provideApplication() {
        if (APP_CACHED == null) {
            APP_CACHED =  APPLICATION_BUILDER.build();
            INJECTOR_CACHED = Guice.createInjector(APPLICATION_BUILDER.applicationModule());
        }
        INJECTOR_CACHED.injectMembers(this);
        return APP_CACHED;
    }

    @Before
    public void setUp() {
        FramedGraph fg = factory.getFramedGraph();
        Feature dashboard = fg.addFramedVertex("class:Feature", new DefaultClassInitializer<>(Feature.class));
        dashboard.setName("DASHBOARD");
        dashboard.setFeatureId("1");
        fg.shutdown();
    }

    @After
    public void tearDown() {
        System.out.println("Runs after all tests in the annotation above.");
    }

    @Test
    public void testFeature() {
        System.out.println("Testing ");
    }
}
