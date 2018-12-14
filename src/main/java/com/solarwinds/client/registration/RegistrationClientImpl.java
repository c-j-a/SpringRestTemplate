package com.solarwinds.client.registration;

import com.solarwinds.client.AbstractRestClient;
import com.solarwinds.model.DpaResponse;
import com.solarwinds.model.registration.RegisterDatabase;
import com.solarwinds.model.registration.RegistrationResponse;
import com.solarwinds.model.registration.UnregisterDatabase;
import com.solarwinds.util.Constants;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class RegistrationClientImpl extends AbstractRestClient implements RegistrationClient {

    @Override
    public RegistrationResponse registerMonitor(RegisterDatabase registerDatabase) {

        System.out.println();
        System.out.println("===> registerMonitor(" + registerDatabase + ")");

        // http://localhost:8123/iwc/api/databases/register-monitor
        String url = Constants.API_URL + "/databases/register-monitor";

        ParameterizedTypeReference<DpaResponse<RegistrationResponse>> ptr = new ParameterizedTypeReference<>() {};
        DpaResponse<RegistrationResponse> dpaResponse = httpPost(url, registerDatabase, ptr);

        return dpaResponse.getDataObject();

    }

    @Override
    public RegistrationResponse unRegisterMonitor(UnregisterDatabase unregisterDatabase) {

        System.out.println();
        System.out.println("===> unRegisterMonitor(" + unregisterDatabase + ")");

        // http://localhost:8123/iwc/api/databases/unregister-monitor
        String url = Constants.API_URL + "/databases/unregister-monitor";

        ParameterizedTypeReference<DpaResponse<RegistrationResponse>> ptr = new ParameterizedTypeReference<>() {};
        DpaResponse<RegistrationResponse> dpaResponse = httpPost(url, unregisterDatabase, ptr);

        return dpaResponse.getDataObject();

    }

}
