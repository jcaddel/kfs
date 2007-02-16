/*
 * Copyright 2005-2006 The Kuali Foundation.
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
package org.kuali.module.financial.rules;

import org.kuali.core.document.TransactionalDocument;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.module.financial.document.YearEndDocumentUtil;

/**
 * Business rule(s) applicable to <code>YearEndTransferOfFundsDocument</code>s
 * 
 * @see org.kuali.module.financial.rules.TransferOfFundsDocumentRule
 */
public class YearEndTransferOfFundsDocumentRule extends TransferOfFundsDocumentRule {

    /**
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#customizeExplicitGeneralLedgerPendingEntry(org.kuali.kfs.document.AccountingDocument, org.kuali.kfs.bo.AccountingLine, org.kuali.kfs.bo.GeneralLedgerPendingEntry)
     */
    @Override
    protected void customizeExplicitGeneralLedgerPendingEntry(AccountingDocument accountingDocument, AccountingLine accountingLine, GeneralLedgerPendingEntry explicitEntry) {
        super.customizeExplicitGeneralLedgerPendingEntry(accountingDocument, accountingLine, explicitEntry);
        YearEndDocumentUtil.customizeExplicitGeneralLedgerPendingEntry(accountingDocument, accountingLine, explicitEntry);
    }

    /**
     * @see org.kuali.kfs.rules.AccountingDocumentRuleBase#customizeOffsetGeneralLedgerPendingEntry(org.kuali.kfs.document.AccountingDocument, org.kuali.kfs.bo.AccountingLine, org.kuali.kfs.bo.GeneralLedgerPendingEntry, org.kuali.kfs.bo.GeneralLedgerPendingEntry)
     */
    @Override
    protected boolean customizeOffsetGeneralLedgerPendingEntry(AccountingDocument accountingDocument, AccountingLine accountingLine, GeneralLedgerPendingEntry explicitEntry, GeneralLedgerPendingEntry offsetEntry) {
        boolean success = super.customizeOffsetGeneralLedgerPendingEntry(accountingDocument, accountingLine, explicitEntry, offsetEntry);
        YearEndDocumentUtil.customizeExplicitGeneralLedgerPendingEntry(accountingDocument, accountingLine, explicitEntry);
        return success;
    }
    
    
}