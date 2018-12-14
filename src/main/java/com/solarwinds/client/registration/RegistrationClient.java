package com.solarwinds.client.registration;

import com.solarwinds.model.registration.RegisterDatabase;
import com.solarwinds.model.registration.RegistrationResponse;
import com.solarwinds.model.registration.UnregisterDatabase;

public interface RegistrationClient {

    /**
     * Register a Database Instance
     *
     * @param registerDatabase The RegisterDatabase Object containing all necessary information.
     * @return A RegistrationResponse
     */
    RegistrationResponse registerMonitor(RegisterDatabase registerDatabase);

    /**
     * Un-Register a Database Instance
     *
     * @param unregisterDatabase The UnregisterDatabase Object containing all necessary information.
     * @return A RegistrationResponse
     */
    RegistrationResponse unRegisterMonitor(UnregisterDatabase unregisterDatabase);

}
