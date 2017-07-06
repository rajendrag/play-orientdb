package com.rp.orientdb.example.db;

import com.syncleus.ferma.DelegatingFramedGraph;
import com.syncleus.ferma.FramedGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import javax.inject.Singleton;

/**
 * Created by RP on 6/19/17.
 */
@Singleton
public class MyGraphFactory {

    private final OrientGraphFactory factory = new OrientGraphFactory("remote:127.0.0.1/test", "root", "root", true);

    public OrientGraph getGraph() {
        OrientGraph g = factory.getTx();
        return g;
    }

    public FramedGraph getFramedGraph() {
        OrientGraph g = factory.getTx();
        g.setUseClassForEdgeLabel(true);
        g.setUseClassForVertexLabel(true);
        return new DelegatingFramedGraph(g, true, false);
    }
}
