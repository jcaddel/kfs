/*
 * Copyright 2006-2007 The Kuali Foundation.
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
package org.kuali.module.vendor;


/**
 * Holds constants for Vendor.
 * 
 */
public class VendorConstants {

    public static class Workgroups {
        public static final String WORKGROUP_ACCOUNTS_PAYABLE = "PURAP.WORKGROUP.ACCOUNTS_PAYABLE"; 
        public static final String WORKGROUP_PURCHASING = "PURAP.WORKGROUP.PURCHASING"; 
        public static final String WORKGROUP_TAXNBR_ACCESSIBLE = "PURAP.WORKGROUP.TAXNBR_ACCESSIBLE"; 
    }
    
    //Miscellaneous generic constants
    public static final String NONE = "NONE";
    public static final String CREATE_NEW_DIVISION = "create division";
    public static final String NAME_DELIM = ", ";
    public static final String DASH = "-";
    public static final String VENDOR_HEADER_ATTR = "vendorHeader";
    public static final String VENDOR_LOOKUPABLE_IMPL = "vendorLookupable";
    //Vendor Tax Types
    public static final String TAX_TYPE_FEIN = "FEIN";
    public static final String TAX_TYPE_SSN = "SSN";

    //VENDOR PHONE TYPES
    public static class PhoneTypes {
        public static final String TOLL_FREE = "TF";
        public static final String PHONE = "PH";
        public static final String FAX = "FX";
    }

}