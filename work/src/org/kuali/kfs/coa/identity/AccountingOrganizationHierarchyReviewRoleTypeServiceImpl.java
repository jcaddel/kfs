/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kfs.coa.identity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.identity.KfsKimAttributes;
import org.kuali.rice.kim.bo.role.dto.DelegateInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.KimDelegationTypeService;
import org.kuali.rice.kns.util.KualiDecimal;

public class AccountingOrganizationHierarchyReviewRoleTypeServiceImpl extends OrganizationHierarchyReviewRoleTypeServiceImpl implements KimDelegationTypeService {

    /**
     * Create role type service - org.kuali.kfs.coa.identity.AccountingOrganizationHierarchyReviewRoleTypeService for
     * KFS-COA/"Organization: Always Hierarchical, Document Type & Accounting" Attributes: Chart Code (required) Organization Code
     * Document Type Name From Amount To Amount Override Code - total amount will be passed in as qualification and role type
     * service will need to compare it to from and to amount qualifier values for assignees Requirements: - Traverse the org
     * hierarchy but not the document type hierarchy - from amount must be null or <= total amount supplied / to amount must be null
     * or >= total amount supplied, and null override code on assignment matches all override codes
     * 
     * @see org.kuali.kfs.coa.identity.OrganizationOptionalHierarchyRoleTypeServiceImpl#performMatch(org.kuali.rice.kim.bo.types.dto.AttributeSet,
     *      org.kuali.rice.kim.bo.types.dto.AttributeSet)
     */
    public boolean performMatch(AttributeSet qualification, AttributeSet roleQualifier) {
        return doesOverrideCodeMatch(qualification, roleQualifier) && isValidTotalAmount(qualification, roleQualifier) && super.performMatch(qualification, roleQualifier);
    }

    private boolean doesOverrideCodeMatch(AttributeSet qualification, AttributeSet roleQualifier) {
        return StringUtils.isBlank(qualification.get(KfsKimAttributes.ACCOUNTING_LINE_OVERRIDE_CODE)) || StringUtils.isBlank(roleQualifier.get(KfsKimAttributes.ACCOUNTING_LINE_OVERRIDE_CODE)) || qualification.get(KfsKimAttributes.ACCOUNTING_LINE_OVERRIDE_CODE).equals(roleQualifier.get(KfsKimAttributes.ACCOUNTING_LINE_OVERRIDE_CODE));
    }

    private boolean isValidTotalAmount(AttributeSet qualification, AttributeSet roleQualifier) {
        boolean isValidTotalAmount = false;
        try {
            KualiDecimal totalAmount = new KualiDecimal(qualification.get(KFSPropertyConstants.FINANCIAL_DOCUMENT_TOTAL_AMOUNT));
            String toAmountStr = roleQualifier.get(KfsKimAttributes.TO_AMOUNT);
            String fromAmountStr = roleQualifier.get(KfsKimAttributes.FROM_AMOUNT);
            if ((StringUtils.isEmpty(toAmountStr) || new KualiDecimal(toAmountStr).compareTo(totalAmount) >= 0) && (StringUtils.isEmpty(fromAmountStr) || new KualiDecimal(fromAmountStr).compareTo(totalAmount) <= 0)) {
                isValidTotalAmount = true;
            }
        }
        catch (Exception ex) {
            isValidTotalAmount = false;
        }
        return isValidTotalAmount;
    }


    public boolean doesDelegationQualifierMatchQualification(AttributeSet qualification, AttributeSet delegationQualifier) {
        return performMatch(translateInputAttributeSet(qualification), delegationQualifier);
    }

    public List<DelegateInfo> doDelegationQualifiersMatchQualification(AttributeSet qualification, List<DelegateInfo> delegationMemberList) {
        AttributeSet translatedQualification = translateInputAttributeSet(qualification);
        List<DelegateInfo> matchingMemberships = new ArrayList<DelegateInfo>();
        for (DelegateInfo dmi : delegationMemberList) {
            if (performMatch(translatedQualification, dmi.getQualifier())) {
                matchingMemberships.add(dmi);
            }
        }
        return matchingMemberships;
    }

    public AttributeSet convertQualificationAttributesToRequired(AttributeSet qualificationAttributes) {
        return qualificationAttributes;
    }
}