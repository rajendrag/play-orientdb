package com.rp.orientdb.example.service;

import com.google.inject.ImplementedBy;
import com.rp.orientdb.example.domain.Resource;
import com.rp.orientdb.example.domain.Role;
import com.rp.orientdb.example.service.impl.UserAccessServiceImpl;
import java.util.List;

/**
 * Created by RP on 6/20/17.
 */
@ImplementedBy(UserAccessServiceImpl.class)
public interface UserAccessService {

    /**
     * This fetches all the resource names using MATCH pattern and goes $dept of 2
     *
     * @param userId
     * @return
     */
    public List<String> getResourceNames(String userId);

    /**
     * This fetches all the Resource vertices using OGM (Ferma) and goes to two level deeper
     * @param userId
     * @return
     */
    public List<Resource> getResources(String userId);

    List<Role> getRoles(String userId);
}
