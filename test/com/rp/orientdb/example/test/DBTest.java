package com.rp.orientdb.example.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rp.orientdb.example.domain.Resource;
import com.rp.orientdb.example.service.UserAccessService;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

/**
 * Created by RP on 6/15/17.
 */
public class DBTest extends WithApplication {

    @Inject
    UserAccessService userAccessService;

    @Override
    protected Application provideApplication() {
        GuiceApplicationBuilder builder = new GuiceApplicationBuilder().in(new File("."));
        Injector injector = Guice.createInjector(builder.applicationModule());
        injector.injectMembers(this);
        return builder.build();
    }

    @Test
    public void testResources() {
        List<String> resources = userAccessService.getResources("100000_ADMIN");
        Assert.assertNotNull(resources);
        Assert.assertEquals(8, resources.size());
    }
}
