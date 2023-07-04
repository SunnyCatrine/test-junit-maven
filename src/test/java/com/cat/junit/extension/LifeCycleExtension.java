package com.cat.junit.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LifeCycleExtension implements BeforeEachCallback, AfterTestExecutionCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("beforeEach " + extensionContext.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        System.out.println("afterTestExecution" + extensionContext.getDisplayName());
    }
}
