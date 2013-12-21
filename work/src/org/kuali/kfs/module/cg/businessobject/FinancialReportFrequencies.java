/*
 * Copyright 2011 The Kuali Foundation.
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
package org.kuali.kfs.module.cg.businessobject;

import java.util.LinkedHashMap;

import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * FinancialReportFrequencies under Contracts and Grants section.
 */

public class FinancialReportFrequencies extends PersistableBusinessObjectBase implements MutableInactivatable {

    private String reportFrequency;
    private String reportFrequencyDescription;
    private boolean active;

    /**
     * Default constructor.
     */
    public FinancialReportFrequencies() {
    }


    public String getReportFrequency() {
        return reportFrequency;
    }


    public void setReportFrequency(String reportFrequency) {
        this.reportFrequency = reportFrequency;
    }


    public String getReportFrequencyDescription() {
        return reportFrequencyDescription;
    }


    public void setReportFrequencyDescription(String reportFrequencyDescription) {
        this.reportFrequencyDescription = reportFrequencyDescription;
    }


    @Override
    public boolean isActive() {
        return active;
    }


    @Override
    public void setActive(boolean active) {
        this.active = active;
    }


    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put("reportFrequency", this.reportFrequency);
        m.put("reportFrequencyDescription", reportFrequencyDescription);
        m.put(KFSPropertyConstants.ACTIVE, active);
        return m;
    }
}