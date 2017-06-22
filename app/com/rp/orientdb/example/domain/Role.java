package com.rp.orientdb.example.domain;

import com.syncleus.ferma.AbstractVertexFrame;
import java.util.List;

/**
 * Created by RP on 6/15/17.
 */
public class Role extends AbstractVertexFrame {

    @Override
    public String getId() {
        return getRoleId();
    }

    public String getName() {
        return getProperty("name");
    }

    public void setName(String name) {
        setProperty("name", name);
    }

    public String getRoleId() {
        return getProperty("role_id");
    }

    public void setRoleId(String roleId) {
        setProperty("role_id", roleId);
    }

    public List<Resource> getResources() {
        return (List<Resource>) out("HAS_ACCESS").has("@class", "Resource").toListExplicit(Resource.class);
    }
}
