package org.wso2.carbon.identity.scim.provider.resources;

import org.wso2.charon.core.exceptions.AbstractCharonException;

/**
 * Created by User on 3/30/2017.
 */
public class NotImplementedException extends AbstractCharonException {
    public NotImplementedException() {
        this.code = 501;
        this.description = "Not Implemented";
    }

    public NotImplementedException(String description) {
        this.code = 501;
        this.description = description;
    }

    public NotImplementedException(int code, String message) {
        this.code = code;
        this.description = message;
    }
}
