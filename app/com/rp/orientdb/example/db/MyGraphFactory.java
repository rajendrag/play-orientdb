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

    OrientGraphFactory factory = new OrientGraphFactory("remote:127.0.0.1/surgery_uchealth", "root", "root", true);
    //private static OrientGraphFactory
    public OrientGraph getGraph() {
        OrientGraph g = factory.getTx();
        g.setUseClassForEdgeLabel(true);
        g.setUseClassForVertexLabel(true);
        return g;
    }

    public FramedGraph getFramedGraph() {
        OrientGraph g = factory.getTx();
        g.setUseClassForEdgeLabel(true);
        g.setUseClassForVertexLabel(true);
        return new DelegatingFramedGraph(g, true, false);
    }
}
