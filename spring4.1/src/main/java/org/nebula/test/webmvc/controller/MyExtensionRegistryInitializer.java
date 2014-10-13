package org.nebula.test.webmvc.controller;

import com.google.protobuf.ExtensionRegistry;
import org.springframework.http.converter.protobuf.ExtensionRegistryInitializer;

/**
 * Created by Administrator on 2014-10-8.
 */
public class MyExtensionRegistryInitializer implements ExtensionRegistryInitializer {
    @Override
    public void initializeExtensionRegistry(ExtensionRegistry extensionRegistry) {
    //    nothing
    }
}
