package com.solarwinds.client.registration;

import com.solarwinds.model.registration.RegisterDatabase;
import com.solarwinds.model.registration.RegistrationResponse;
import com.solarwinds.model.registration.UnregisterDatabase;

public interface RegistrationClient {

    RegistrationResponse registerMonitor(RegisterDatabase registerDatabase);

    RegistrationResponse unRegisterMonitor(UnregisterDatabase monitorUnregister);

}
