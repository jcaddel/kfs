/*
 * Copyright 2006 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kfs.exceptions;

/**
 * this class represents an exception that is thrown when a piece of the <code>AccountingLineParser</code> fails
 * 
 * @see org.kuali.core.bo.AccountingLineParser
 * 
 * 
 */
public class AccountingLineParserException extends RuntimeException {

    private String errorKey;
    private String[] errorParameters;

    /**
     * Constructs a AccountingLineParserException.java.
     * 
     * @param message
     * @param errorKey key to an error message
     * @param errorParameters error message parameters
     */
    public AccountingLineParserException(String message, String errorKey, String... errorParameters) {
        super(message);
        this.errorKey = errorKey;
        this.errorParameters = errorParameters;
    }

    /**
     * Gets the errorKey attribute.
     * 
     * @return Returns the errorKey.
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * Gets the errorParameters attribute.
     * 
     * @return Returns the errorParameters.
     */
    public String[] getErrorParameters() {
        return errorParameters;
    }


}