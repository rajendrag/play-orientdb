package com.rp.orientdb.example.db;

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
        return factory.getTx();
    }
}
