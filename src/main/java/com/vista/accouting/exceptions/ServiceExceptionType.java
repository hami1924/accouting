package com.vista.accouting.exceptions;

public enum ServiceExceptionType {
    Ok(200),
    Bad_Request(400),
    Unauthorized(401),
    Forbidden(403),
    OiForbidden(403),
    LimitForbidden(403),
    Not_Found(404),
    Not_Acceptable(406),
    Unsupported_Media_Type(415),
    Internal_Server_Error(500),
    NonAuthoritativeInformation(203),
    Service_Unavailable(503);

    private final int statusCode;

    private ServiceExceptionType(int statusCode) {
        this.statusCode = statusCode;
    }

    public int statusCode() {
        return this.statusCode;
    }
}