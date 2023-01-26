package com.vista.accouting.common;

import java.util.Set;

public class ErrorResponse extends BaseResponse {
    //for backward compatibility
    private String code;
    private String message;
    private String info;
    private Set<Error> errors;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Error> getErrors() {
        return errors;
    }

    public void setErrors(Set<Error> errors) {
        this.errors = errors;
        if (errors != null) {
            Error error = errors.stream().findFirst().get();
            this.code = error.getCode();
            this.message = error.getMessage();
            this.info = error.getInfo();
        } else {
            this.code = null;
            this.message = null;
            this.info = null;
        }
    }

    public static class Error {
        private String code;
        private String message;
        private String info;
        private String reference;
        private String value;

        public String getCode() {
            return code;
        }

        public Error setCode(String code) {
            this.code = code;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public Error setMessage(String message) {
            this.message = message;
            return this;
        }

        public String getInfo() {
            return info;
        }

        public Error setInfo(String info) {
            this.info = info;
            return this;
        }

        public String getReference() {
            return reference;
        }

        public Error setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Error setValue(String value) {
            this.value = value;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            return code.equalsIgnoreCase(o.toString());
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", info='" + info + '\'' +
                    ", reference='" + reference + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", info='" + info + '\'' +
                ", errors=" + errors +
                '}';
    }
}
