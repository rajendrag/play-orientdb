package com.rp.orientdb.example.test;

import static play.inject.Bindings.bind;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rp.orientdb.example.db.MyGraphFactory;
import com.rp.orientdb.example.domain.Feature;
import com.rp.orientdb.example.domain.Resource;
import com.rp.orientdb.example.domain.Role;
import com.rp.orientdb.example.domain.User;
import com.rp.orientdb.example.service.UserAccessService;
import com.syncleus.ferma.DefaultClassInitializer;
import com.syncleus.ferma.FramedGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
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
        log.info("Setting up the Graph");
        FramedGraph fg = factory.getFramedGraph();
        Feature dashboard = fg.addFramedVertex("class:Feature", new DefaultClassInitializer<>(Feature.class));
        dashboard.setName("DASHBOARD");
        dashboard.setFeatureId("1");
        Resource metric1 = fg.addFramedVertex("class:Resource", new DefaultClassInitializer<>(Resource.class));
        metric1.setName("Metric1");
        metric1.setResourceId("1");
        Resource metric2 = fg.addFramedVertex("class:Resource", new DefaultClassInitializer<>(Resource.class));
        metric2.setName("Metric2");
        metric2.setResourceId("2");
        Resource metric3 = fg.addFramedVertex("class:Resource", new DefaultClassInitializer<>(Resource.class));
        metric3.setName("Metric3");
        metric3.setResourceId("3");
        dashboard.addResources(metric1, metric2, metric3);

        Feature accMgmt = fg.addFramedVertex("class:Feature", new DefaultClassInitializer<>(Feature.class));
        accMgmt.setName("ACCTMGMT");
        accMgmt.setFeatureId("2");
        Resource addAcc = fg.addFramedVertex("class:Resource", new DefaultClassInitializer<>(Resource.class));
        addAcc.setName("Add Account");
        addAcc.setResourceId("4");
        Resource updateAcc = fg.addFramedVertex("class:Resource", new DefaultClassInitializer<>(Resource.class));
        updateAcc.setName("Update Account");
        updateAcc.setResourceId("5");
        Resource deleteAcc = fg.addFramedVertex("class:Resource", new DefaultClassInitializer<>(Resource.class));
        deleteAcc.setName("Delete Account");
        deleteAcc.setResourceId("6");
        Resource viewAcc = fg.addFramedVertex("class:Resource", new DefaultClassInitializer<>(Resource.class));
        viewAcc.setName("View Account");
        viewAcc.setResourceId("7");
        accMgmt.addResources(addAcc, updateAcc, deleteAcc, viewAcc);

        Role dashboardUser = fg.addFramedVertex("class:Role", new DefaultClassInitializer<>(Role.class));
        dashboardUser.setName("DASHBOARD_USER");
        dashboardUser.setRoleId("1");
        dashboardUser.addResources(metric1, metric2, metric3);

        Role accountManager = fg.addFramedVertex("class:Role", new DefaultClassInitializer<>(Role.class));
        accountManager.setName("ACCOUNT_MANAGER");
        accountManager.setRoleId("2");
        accountManager.addResources(addAcc, updateAcc, deleteAcc, viewAcc);

        User peng = fg.addFramedVertex("class:User", new DefaultClassInitializer<>(User.class));
        peng.setName("Peng");
        peng.setUserId("1000");
        peng.addRoles(accountManager);
        User admin = fg.addFramedVertex("class:User", new DefaultClassInitializer<>(User.class));
        admin.setName("Admin");
        admin.setUserId("1001");
        admin.addRoles(dashboardUser);
        admin.addResources(viewAcc);
        fg.shutdown();
        log.info("Graph setup is done!");
    }

    @After
    public void tearDown() {
        log.info("Clearing the DB");
        OrientGraph graph = factory.getGraph();
        graph.getEdges().forEach(e -> e.remove());
        graph.getVertices().forEach(v -> v.remove());
        graph.commit();
        graph.shutdown();
        log.info("Graph should be empty now");
    }
}
