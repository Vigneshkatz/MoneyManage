package com.katziio.app.service.account;

import com.katziio.app.dto.Request;
import com.katziio.app.dto.Response;

public interface AccountService {
    Response create(Request request);
}
