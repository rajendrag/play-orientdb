package com.rp.orientdb.example.domain;

import com.syncleus.ferma.AbstractVertexFrame;
import com.syncleus.ferma.VertexFrame;
import com.syncleus.ferma.pipes.TraversalFunctionPipe;
import com.syncleus.ferma.traversals.TraversalFunction;
import com.syncleus.ferma.traversals.VertexTraversal;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;
import com.tinkerpop.pipes.branch.LoopPipe.LoopBundle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by RP on 6/15/17.
 */
public class User extends AbstractVertexFrame {

    public String getName() {
        return getProperty("name");
    }

    public void setName(String name) {
        setProperty("name", name);
    }

    public String getUserId() {
        return getProperty("user_id");
    }

    public void setUserId(String userId) {
        setProperty("user_id", userId);
    }

    /**
     * TODO: Use Tinkerpop way (Pipelines) to figure out how to fetch the resources
     * @return
     */
    public List<Resource> getResources() {
        //return has_access.has(Resource.class).toListExplicit(Resource.class);
        /*return out("HAS_ACCESS")
                .loop(new TraversalFunction<VertexFrame, VertexTraversal<?, ?, ?>>() {
            @Override
            public VertexTraversal<?, ?, ?> compute(VertexFrame argument) {

                return argument.out("HAS_ACCESS");
            }
        }, 2)
                .toList(Resource.class);*/
        List<Resource> resources = new ArrayList<>((List<Resource>) out("HAS_ACCESS").has("@class", "Resource").toListExplicit(Resource.class));
        resources.addAll(getRoles().stream().map(r -> r.getResources()).flatMap(List::stream).collect(Collectors.toList()));
        return resources;
    }

    public List<Role> getRoles() {
        return (List<Role>) out("HAS_ACCESS").has("@class", "Role").toListExplicit(Role.class);
    }

}
