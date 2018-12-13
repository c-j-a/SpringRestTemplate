package com.solarwinds.client.token;

import com.solarwinds.model.token.AuthToken;

public interface TokenClient {

    AuthToken createAccessToken();

}
