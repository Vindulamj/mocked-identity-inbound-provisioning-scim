/*
 * Copyright (c) 2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.scim.common.utils;

import org.wso2.carbon.identity.base.IdentityException;

public class IdentitySCIMException extends IdentityException {

    private static final long serialVersionUID = 3477076930789578976L;

    public IdentitySCIMException(String error) {
        super(error);
    }

    public IdentitySCIMException(String message, Throwable cause) {
        super(message, cause);
    }
}