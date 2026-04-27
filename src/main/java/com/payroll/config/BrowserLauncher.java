package com.payroll.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

@Component
public class BrowserLauncher {
    @Value("${server.port:8099}")
    private int serverPort;

    @Value("${payroll.app.open-browser:true}")
    private boolean openBrowser;

    @EventListener(ApplicationReadyEvent.class)
    public void openHomePage() {
        if (!openBrowser || !Desktop.isDesktopSupported()) {
            System.out.println("Payroll web app is ready: http://localhost:" + serverPort + "/login.html");
            return;
        }

        try {
            Desktop.getDesktop().browse(new URI("http://localhost:" + serverPort + "/login.html"));
        } catch (Exception exception) {
            System.out.println("Payroll web app is ready: http://localhost:" + serverPort + "/login.html");
        }
    }
}
