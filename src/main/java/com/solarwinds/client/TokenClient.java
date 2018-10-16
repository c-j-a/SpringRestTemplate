package com.solarwinds.client;

import com.solarwinds.model.AuthToken;

public interface TokenClient {

    AuthToken createAccessToken();

}
