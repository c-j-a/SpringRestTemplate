package com.solarwinds;

import com.solarwinds.client.annotation.AnnotationsClient;
import com.solarwinds.client.licensing.LicenseClient;
import com.solarwinds.client.monitor.MonitorClient;
import com.solarwinds.client.registration.RegistrationClient;
import com.solarwinds.client.token.TokenClient;
import com.solarwinds.config.AppConfig;
import com.solarwinds.config.DpaClientRequestInterceptor;
import com.solarwinds.model.annotation.Annotation;
import com.solarwinds.model.licensing.LicenseAllocation;
import com.solarwinds.model.licensing.LicenseInfo;
import com.solarwinds.model.licensing.LicenseSummary;
import com.solarwinds.model.monitor.MonitorCommand;
import com.solarwinds.model.monitor.MonitoredInstance;
import com.solarwinds.model.registration.RegisterDatabase;
import com.solarwinds.model.registration.RegistrationResponse;
import com.solarwinds.model.registration.UnregisterDatabase;
import com.solarwinds.model.token.AuthToken;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static AnnotationConfigApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // Create the Access Token
        initializeAuthToken();

        int databaseId = 1;

        // Make some License Allocation calls
//        testLicensing(databaseId);

        // Make some Annotation calls
//        testAnnotations(databaseId);

        // Make some Database Monitor calls
//        testMonitorInformation(databaseId);

        // Register and Unregister databases
//        testRegistration();

        applicationContext.close();
    }

    /**
     * This call will create the Access Token required for all web service calls.
     */
    private static void initializeAuthToken() {
        // Create the Access Token
        TokenClient authClient = applicationContext.getBean(TokenClient.class);
        AuthToken authToken = authClient.createAccessToken();
        System.out.println("AuthToken: " + authToken);
        if (authToken != null) {
            // Register the token with our ClientHttpRequestInterceptor which will
            // automatically add the AuthToken to every call.
            DpaClientRequestInterceptor interceptor = applicationContext.getBean(DpaClientRequestInterceptor.class);
            interceptor.setAuthToken(authToken);
        }
    }

    /**
     * This method shows how to use the License API
     * @param databaseId The single database to request information for
     */
    private static void testLicensing(int databaseId) {

        LicenseClient licenseClient = applicationContext.getBean(LicenseClient.class);

        // -----------------------------------------------------------
        // Get the currently installed license information
        // -----------------------------------------------------------
        List<LicenseSummary> licenseSummaries = licenseClient.getInstalledLicenses();
        for (LicenseSummary licenseSummary : licenseSummaries) {
            System.out.println(licenseSummary);
        }

        // -----------------------------------------------------------
        // Get License Information for the given database
        // -----------------------------------------------------------
        LicenseInfo licenseInfo = licenseClient.getLicenseInfo(databaseId);
        System.out.println("LicenseInfo: " + licenseInfo);

        // -----------------------------------------------------------
        // This will Update License Information for the given database
        // Setting the Performance License and the VM License to what
        // it currently is
        // -----------------------------------------------------------
        LicenseAllocation licenseAllocation = new LicenseAllocation();
        licenseAllocation.setPerformanceLicenseProduct(licenseInfo.getPerformanceLicenseProduct());
        licenseAllocation.setVmLicenseProduct(licenseInfo.getVmLicenseProduct());
        licenseInfo = licenseClient.updateLicenseAllocation(databaseId, licenseAllocation);
        System.out.println("LicenseInfo: " + licenseInfo);

        // -----------------------------------------------------------
        // This call will cause an error, we can't have a
        // VmLicenseProduct without a PerformanceLicenseProduct
        // -----------------------------------------------------------
        try {
            licenseAllocation.setPerformanceLicenseProduct("REMOVE");
            licenseAllocation.setVmLicenseProduct("DPAVM");
            licenseClient.updateLicenseAllocation(databaseId, licenseAllocation);
        } catch (Exception e) {
            /* Ignore */
        }

    }

    private static void testAnnotations(int databaseId) {
        AnnotationsClient annotationsClient = applicationContext.getBean(AnnotationsClient.class);

        // -----------------------------------------------------------
        // Gets a List of annotations for the last 30 days
        // -----------------------------------------------------------
        OffsetDateTime start = OffsetDateTime.now();
        OffsetDateTime end = start;
        start = start.minusDays(30);
        List<Annotation> annotations = annotationsClient.getAnnotations(databaseId, start, end);
        for (Annotation annotation : annotations) {
            System.out.println("Existing Annotation: " + annotation);
        }

        // -----------------------------------------------------------
        // Creates a new annotations
        // -----------------------------------------------------------
        OffsetDateTime eventTime = OffsetDateTime.now();
        eventTime = eventTime.minusDays(1);
        Annotation newAnnotation = new Annotation();
        newAnnotation.setTitle("Test Title API");
        newAnnotation.setTime(eventTime);
        newAnnotation.setDescription("Test Event created by DPA API");
        newAnnotation.setCreatedBy("DPA API");
        Annotation resultAnnotation = annotationsClient.createAnnotation(databaseId, newAnnotation);
        System.out.println("New Annotation: " + resultAnnotation);

    }

    private static void testMonitorInformation(int databaseId) {
        MonitorClient monitorClient = applicationContext.getBean(MonitorClient.class);

        // -----------------------------------------------------------
        // Commands for an individual database
        // -----------------------------------------------------------

        // -----------------------------------------------------------
        // Get Monitor Information for a single database
        // -----------------------------------------------------------
        MonitoredInstance monitor = monitorClient.getMonitorInformation(databaseId);
        System.out.println("MonitoredInstance: " + monitor);

        MonitorCommand changeCommand = null;
        MonitorCommand revertCommand = null;
        if (monitor.isStarted()) {
            changeCommand = MonitorCommand.createStop();
            revertCommand = MonitorCommand.createStart();
        }
        else if (monitor.isStopped()) {
            changeCommand = MonitorCommand.createStart();
            revertCommand = MonitorCommand.createStop();
        }

        if (changeCommand != null) {
            // If on turn off, if off turn on
            String result = monitorClient.setMonitorStatus(databaseId, changeCommand);
            System.out.println(changeCommand.getCommand() + "[" + databaseId + "] Result: " + result);
            waitForState(monitorClient, changeCommand, databaseId);

            // and then set it back to its original state
            result = monitorClient.setMonitorStatus(databaseId, revertCommand);
            System.out.println(changeCommand.getCommand() + "[" + databaseId + "] Result: " + result);
            waitForState(monitorClient, revertCommand, databaseId);
        }

        // -----------------------------------------------------------
        // Commands for all databases
        // -----------------------------------------------------------

        // -----------------------------------------------------------
        // Get Monitor Information for all databases
        // -----------------------------------------------------------
		List<MonitoredInstance> monitoredInstances = monitorClient.getMonitorInformation();

        // Save a List of Running Monitor IDs
		List<Integer> running = new ArrayList<>();
		for (MonitoredInstance monitoredInstance : monitoredInstances) {
			if (monitoredInstance.getMonitorState().equals(MonitorClient.MonitorState.RUNNING.getState())) {
				running.add(monitoredInstance.getDbId());
			}
		}
		System.out.println("Running Monitors: " + running);

        // Start all the Monitors
        MonitorCommand monitorStatus = MonitorCommand.createStart();
        String result = monitorClient.setMonitorStatus(monitorStatus);
        System.out.println(monitorStatus.getCommand() + " All Result: " + result);
        waitFor(30000);

        // -----------------------------------------------------------
        // Stop all the Monitors
        // -----------------------------------------------------------
        monitorStatus = MonitorCommand.createStop();
        result = monitorClient.setMonitorStatus(monitorStatus);
        System.out.println(monitorStatus.getCommand() + " All Result: " + result);
        waitFor(30000);

        // Try to put it back the way we found it by restarting the ones that were running
        for (Integer id : running) {
            monitorClient.setMonitorStatus(id, MonitorCommand.createStart());
        }

        // -----------------------------------------------------------
        // Try to cause some errors
        // -----------------------------------------------------------
        try {
            // Ask for a database that doesn't exist
            monitorClient.getMonitorInformation(Integer.MAX_VALUE);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            // Start a database that doesn't exist
            monitorClient.setMonitorStatus(Integer.MAX_VALUE, MonitorCommand.createStart());
        } catch (Exception e) {
            System.out.println(e);
        }

        // -----------------------------------------------------------
        // Update the monitor database user
        // -----------------------------------------------------------
//        result = monitorClient.updateMonitorPassword(databaseId, new MonitorPassword("NewPassword"));
//        System.out.println("Update Password Result: " + result);

    }

    private static void testRegistration() {
        RegistrationClient monitorClient = applicationContext.getBean(RegistrationClient.class);

        // -----------------------------------------------------------
        // Register a MySQL Database
        // -----------------------------------------------------------
        RegisterDatabase registerDatabase = new RegisterDatabase();
        registerDatabase.setDatabaseType("MYSQL");
        registerDatabase.setServerName("10.199.8.176");
        registerDatabase.setPort(3306);
        registerDatabase.setSysAdminUser("root");
        registerDatabase.setSysAdminPassword("Confio123");
        registerDatabase.setMonitoringUser("dpa_test_m");
        registerDatabase.setMonitoringUserPassword("Confio123");
        registerDatabase.setMonitoringUserIsNew(true);
        registerDatabase.setJdbcUrlProperties("");
        registerDatabase.setDisplayName("DPA-WIN-MySQL57-API");

        RegistrationResponse response = monitorClient.registerMonitor(registerDatabase);
        System.out.println("Register: " + response);

        // -----------------------------------------------------------
        // Now un-register that MySQL Database
        // -----------------------------------------------------------
        UnregisterDatabase unregisterDatabase = new UnregisterDatabase();
        unregisterDatabase.setDatabaseId(response.getDatabaseId());
        unregisterDatabase.setRemoveMonitoringUser(true);
        unregisterDatabase.setRemoveDatabaseObjects(true);
        unregisterDatabase.setSysAdminUser("root");
        unregisterDatabase.setSysAdminPassword("Confio123");

        RegistrationResponse unRegisterResponse = monitorClient.unRegisterMonitor(unregisterDatabase);
        System.out.println("UnRegister: " + unRegisterResponse);

    }

    private static void waitForState(MonitorClient monitorClient, MonitorCommand command, int databaseId) {
        // Now we will give it some time to start/stop (around 30 seconds)
        for (int i = 0; i < 6; i++) {
            waitFor(5000);
            MonitoredInstance monitor = monitorClient.getMonitorInformation(databaseId);
            if (MonitorCommand.START.equals(command.getCommand()) && monitor.isStarted()) {
                System.out.println("Database [" + databaseId + "] Started");
                break;
            }
            else if (MonitorCommand.STOP.equals(command.getCommand()) && monitor.isStopped()) {
                System.out.println("Database [" + databaseId + "] Stopped");
                break;
            }
        }
    }

    private static void waitFor(long time) {
        try { Thread.sleep(time); } catch (InterruptedException e) { /* Ignore */ }
    }

}
