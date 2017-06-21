package com.rp.orientdb.example.query;

import com.google.inject.ImplementedBy;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.rp.orientdb.example.query.impl.UserAccessQueryHelperImpl;

/**
 * Created by RP on 6/20/17.
 */
@ImplementedBy(UserAccessQueryHelperImpl.class)
public interface UserAccessQueryHelper {

    public OCommandSQL resourcesOfUserQuery();

}
