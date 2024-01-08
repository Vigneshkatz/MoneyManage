package com.katziio.app.service.account;

import com.katziio.app.dto.Request;
import com.katziio.app.dto.response.Response;
import com.katziio.app.exception.InvalidDTOException;

public interface AccountService {
    Response create(Request request) throws InvalidDTOException, Exception;

    Response update(Request request);
}
