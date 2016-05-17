package org.cyb.training.java.rs.app;

import javax.ws.rs.core.Application;

import org.cyb.training.java.rs.resources.RequirementResourceV1;
import org.cyb.training.java.rs.v6.resources.RequirementResources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("resources")
public class MyApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(org.cyb.training.java.rs.resources.RequirementResourceV1.class);
        s.add(org.cyb.training.java.rs.resources.RequirementResourceV2.class);
        s.add(org.cyb.training.java.rs.resources.RequirementResourceV3.class);
        s.add(org.cyb.training.java.rs.v4.resources.RequirementResources.class);
        s.add(org.cyb.training.java.rs.v6.resources.RequirementResources.class);
        s.add(org.cyb.training.java.rs.v5.resources.RequirementResources.class);
        return s;
    }
}
