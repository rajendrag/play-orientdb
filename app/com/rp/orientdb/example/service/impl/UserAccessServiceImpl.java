package com.rp.orientdb.example.service.impl;

import com.rp.orientdb.example.db.MyGraphFactory;
import com.rp.orientdb.example.domain.Resource;
import com.rp.orientdb.example.query.UserAccessQueryHelper;
import com.rp.orientdb.example.service.UserAccessService;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by RP on 6/20/17.
 */
@Singleton
public class UserAccessServiceImpl implements UserAccessService {

    @Inject
    MyGraphFactory graphFactory;

    @Inject
    UserAccessQueryHelper queryHelper;

    @Override
    public List<String> getResources(String userId) {
        OrientGraph graph = graphFactory.getGraph();
        Iterable<Vertex> vertices = graph.command(queryHelper.resourcesOfUserQuery())
                .execute(Collections.singletonMap("userId", userId));
        List<String> resources = StreamSupport.stream(vertices.spliterator(), false).map(v -> (String)v.getProperty("name")).collect(
                Collectors.toList());
        graph.shutdown();
        return resources;
    }
}
