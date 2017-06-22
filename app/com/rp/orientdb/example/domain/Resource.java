package com.rp.orientdb.example.domain;

import com.syncleus.ferma.AbstractVertexFrame;

/**
 * Created by RP on 6/15/17.
 */
public class Resource extends AbstractVertexFrame {

    @Override
    public String getId() {
        return getResourceId();
    }

    public String getName() {
        return getProperty("name");
    }

    public void setName(String name) {
        setProperty("name", name);
    }

    public String getResourceId() {
        return getProperty("resource_id");
    }

    public void setResourceId(String resourceId) {
        setProperty("resource_id", resourceId);
    }

    public Feature getFeature() {
        return out("PART_OF").has("@class", "Feature").toListExplicit(Feature.class).get(0);
    }

}
