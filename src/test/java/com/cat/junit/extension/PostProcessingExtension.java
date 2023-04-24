package com.cat.junit.extension;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class PostProcessingExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) throws Exception {
        testInstance.getClass().getDeclaredField("fieldName").set(testInstance, "newFieldValue");

        System.out.println("postprocessing ");

    }
}
