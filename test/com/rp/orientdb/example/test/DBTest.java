package com.rp.orientdb.example.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rp.orientdb.example.domain.Resource;
import com.rp.orientdb.example.domain.Role;
import com.rp.orientdb.example.service.UserAccessService;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import play.Application;
import play.Configuration;
import play.Logger;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

/**
 * Created by RP on 6/15/17.
 */
public class DBTest extends BaseTest {

    private static final Logger.ALogger log = Logger.of(DBTest.class);

    @Inject
    Configuration configuration;

    @Test
    public void testResources() {
        log.info("In testResources()");
        List<String> resources = userAccessService.getResourceNames("1000");
        Assert.assertNotNull(resources);
        resources.stream().forEachOrdered(System.out::println);
        Assert.assertEquals(4, resources.size());
        Assert.assertTrue(resources.contains("Add Account"));
        Assert.assertTrue(resources.contains("Update Account"));
        Assert.assertTrue(resources.contains("Delete Account"));
        Assert.assertTrue(resources.contains("View Account"));

        resources = userAccessService.getResourceNames("1001");
        Assert.assertNotNull(resources);
        resources.stream().forEachOrdered(System.out::println);
        Assert.assertEquals(4, resources.size());
        Assert.assertTrue(resources.contains("Metric1"));
        Assert.assertTrue(resources.contains("Metric2"));
        Assert.assertTrue(resources.contains("Metric3"));
        Assert.assertTrue(resources.contains("View Account"));

        log.info("Done testResources()");
    }

    @Test
    public void testFramedResources() {
        log.info("In testFramedResources()");
        List<Resource> resources = userAccessService.getResources("1000");
        Assert.assertNotNull(resources);
        Assert.assertEquals(4, resources.size());
        List<String> resourceNames = resources.stream().map(r -> {
            System.out.println("Resource name :"+r.getName());
            return r.getName();
        }).collect(Collectors.toList());
        Assert.assertTrue(resourceNames.contains("Add Account"));
        Assert.assertTrue(resourceNames.contains("Update Account"));
        Assert.assertTrue(resourceNames.contains("Delete Account"));
        Assert.assertTrue(resourceNames.contains("View Account"));

        resources = userAccessService.getResources("1001");
        Assert.assertNotNull(resources);
        Assert.assertEquals(4, resources.size());
        resourceNames = resources.stream().map(r -> {
            System.out.println("Resource name :"+r.getName());
            return r.getName();
        }).collect(Collectors.toList());
        Assert.assertTrue(resourceNames.contains("Metric1"));
        Assert.assertTrue(resourceNames.contains("Metric2"));
        Assert.assertTrue(resourceNames.contains("Metric3"));
        Assert.assertTrue(resourceNames.contains("View Account"));
        log.info("Done testFramedResources()");
    }


}
