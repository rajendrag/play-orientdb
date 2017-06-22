package com.rp.orientdb.example.domain;

import com.syncleus.ferma.AbstractVertexFrame;
import java.util.List;

/**
 * Created by RP on 6/15/17.
 */
public class Feature extends AbstractVertexFrame {

    public String getName() {
        return getProperty("name");
    }

    public void setName(String name) {
        setProperty("name", name);
    }

    public String getFeatureId() {
        return getProperty("feature_id");
    }

    public void setFeatureId(String featureId) {
        setProperty("feature_id", featureId);
    }

    public List<? extends Resource> getResources() {
        return in("PART_OF").has(Resource.class).toListExplicit(Resource.class);
    }
}
