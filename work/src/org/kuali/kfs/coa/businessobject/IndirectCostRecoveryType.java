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
package org.kuali.kfs.coa.businessobject;

import java.util.List;

import org.kuali.core.bo.Inactivateable;
import org.kuali.core.bo.KualiCodeBase;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kfs.coa.businessobject.IndirectCostRecoveryExclusionType;

/**
 * This class...
 */
public class IndirectCostRecoveryType extends KualiCodeBase implements Inactivateable {
    
    private String code;
    private String name;
    private boolean active;
    private List IndirectCostRecoveryExclusionTypeDetails;

    public IndirectCostRecoveryType () {
        IndirectCostRecoveryExclusionTypeDetails = new TypedArrayList(IndirectCostRecoveryExclusionType.class);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List getIndirectCostRecoveryExclusionTypeDetails() {
        return IndirectCostRecoveryExclusionTypeDetails;
    }

    public void setIndirectCostRecoveryExclusionTypeDetails(List indirectCostRecoveryExclusionTypeCollection) {
        this.IndirectCostRecoveryExclusionTypeDetails = indirectCostRecoveryExclusionTypeCollection;
    }
    
}
