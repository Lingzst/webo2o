package com.lingzst.webo2o.dao;

import com.lingzst.webo2o.entity.Area;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.List;

public interface AreaDao {
    List<Area> queryArea();
}
