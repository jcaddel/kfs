/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.module.labor.web.inquirable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.gl.GLConstants;
import org.kuali.module.gl.web.Constant;
import org.kuali.module.labor.bo.PositionData;
import org.kuali.module.labor.bo.PositionFunding;

public class PositionDataInquirableImpl extends AbstractLaborInquirableImpl {

    @Override
    protected void addMoreParameters(Properties parameter, String attributeName) {
    }

    @Override
    protected List buildUserDefinedAttributeKeyList() {
        List keys = new ArrayList();
        keys.add(KFSPropertyConstants.POSITION_NUMBER);
        return keys;
    }

    @Override
    protected String getAttributeName(String attributeName) {
        return attributeName;
    }

    @Override
    protected String getBaseUrl() {
        return KFSConstants.GL_MODIFIED_INQUIRY_ACTION;
    }

    @Override
    protected Class getInquiryBusinessObjectClass(String attributeName) {
        return PositionFunding.class;
    }

    @Override
    protected String getKeyName(String keyName) {
        return keyName;
    }

    @Override
    protected Object getKeyValue(String keyName, Object keyValue) {
        return keyValue;
    }

    @Override
    protected String getLookupableImplAttributeName() {
        return null;
    }

    @Override
    protected Map getUserDefinedAttributeMap() {
        Map userDefinedAttributeMap = new HashMap();
        userDefinedAttributeMap.put(GLConstants.DummyBusinessObject.LINK_BUTTON_OPTION, "");
        
        return userDefinedAttributeMap;
    }
}