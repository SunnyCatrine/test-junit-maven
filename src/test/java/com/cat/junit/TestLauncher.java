package com.cat.junit;

import com.cat.junit.service.UserServiceTest;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;

public class TestLauncher {
    public static void main(String[] args) {
        Launcher launcher = LauncherFactory.create();

//        launcher.registerLauncherDiscoveryListeners();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
//        launcher.registerTestExecutionListeners(listener);

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors
                        .selectClass(UserServiceTest.class))
                .filters(TagFilter.excludeTags("login"))
                .build();

        launcher.execute(request, listener);

        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            listener.getSummary().printTo(printWriter);
        }
    }
}
