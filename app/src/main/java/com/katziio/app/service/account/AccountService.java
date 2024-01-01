package com.katziio.app.service.account;

import com.katziio.app.dto.request.Request;
import com.katziio.app.dto.response.Response;

public interface AccountService {
    Response create(Request request);
}
