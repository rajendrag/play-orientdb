package com.rp.orientdb.example.service;

import com.google.inject.ImplementedBy;
import com.rp.orientdb.example.domain.Resource;
import com.rp.orientdb.example.service.impl.UserAccessServiceImpl;
import java.util.List;

/**
 * Created by RP on 6/20/17.
 */
@ImplementedBy(UserAccessServiceImpl.class)
public interface UserAccessService {

    public List<String> getResources(String userId);

}
