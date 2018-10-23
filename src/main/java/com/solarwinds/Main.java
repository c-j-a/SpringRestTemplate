package com.solarwinds;

import com.solarwinds.client.LicenseClient;
import com.solarwinds.client.TokenClient;
import com.solarwinds.config.AppConfig;
import com.solarwinds.config.DpaClientRequestInterceptor;
import com.solarwinds.model.AuthToken;
import com.solarwinds.model.LicenseAllocation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	private static AnnotationConfigApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		// Create the Access Token
		TokenClient authClient = applicationContext.getBean(TokenClient.class);
		AuthToken authToken = authClient.createAccessToken();

		if (authToken != null) {
			DpaClientRequestInterceptor interceptor = applicationContext.getBean(DpaClientRequestInterceptor.class);
			interceptor.setAuthToken(authToken);

			int databaseId = 1;

			testLicensing(authToken, databaseId);
		}

		applicationContext.close();
	}

	private static void testLicensing(AuthToken authToken, int databaseId) {
		// Get License Information
		LicenseClient licenseClient = applicationContext.getBean(LicenseClient.class);
		licenseClient.getLicenseInfo(authToken, databaseId);

		// Update License Information
		LicenseAllocation licenseAllocation = new LicenseAllocation();
		licenseAllocation.setPerformanceLicenseProduct("DPACAT2");
		licenseAllocation.setVmLicenseProduct("REMOVE");
		// This will cause an error
		//licenseAllocation.setPerformanceLicenseProduct("REMOVE");
		//licenseAllocation.setVmLicenseProduct("DPAVM");
		licenseClient.updateLicenseAllocation(authToken, databaseId, licenseAllocation);
	}

}
