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
package org.kuali.kfs.document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.Constants;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.document.Document;
import org.kuali.core.document.TransactionalDocumentBase;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.kfs.bo.GeneralLedgerPendingEntry;
import org.kuali.module.gl.service.SufficientFundsService;
import org.kuali.module.gl.util.SufficientFundsItem;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * Base implementation for a general ledger posting document.
 */
public class GeneralLedgerPostingDocumentBase extends LedgerPostingDocumentBase implements GeneralLedgerPostingDocument {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(GeneralLedgerPostingDocumentBase.class);
    
    protected List<GeneralLedgerPendingEntry> generalLedgerPendingEntries;
    
    /**
     * Default constructor.
     */
    public GeneralLedgerPostingDocumentBase() {
        super();
        setGeneralLedgerPendingEntries(new ArrayList<GeneralLedgerPendingEntry>());
    }

    /**
     * @see org.kuali.module.gl.document.GeneralLedgerPostingDocument#getGeneralLedgerPendingEntries()
     */
    public List<GeneralLedgerPendingEntry> getGeneralLedgerPendingEntries() {
        return generalLedgerPendingEntries;
    }

    /**
     * @see org.kuali.module.gl.document.GeneralLedgerPostingDocument#getGeneralLedgerPendingEntry(int)
     */
    public GeneralLedgerPendingEntry getGeneralLedgerPendingEntry(int index) {
        while (generalLedgerPendingEntries.size() <= index) {
            generalLedgerPendingEntries.add(new GeneralLedgerPendingEntry());
        }
        return generalLedgerPendingEntries.get(index);
    }

    /**
     * @see org.kuali.module.gl.document.GeneralLedgerPostingDocument#setGeneralLedgerPendingEntries(java.util.List)
     */
    public void setGeneralLedgerPendingEntries(List<GeneralLedgerPendingEntry> generalLedgerPendingEntries) {
        this.generalLedgerPendingEntries = generalLedgerPendingEntries;
    }

    /**
     * @see org.kuali.module.gl.document.GeneralLedgerPostingDocument#isBankCashOffsetEnabled()
     */
    public boolean isBankCashOffsetEnabled() {
        return SpringServiceLocator.getKualiConfigurationService().getApplicationParameterIndicator(Constants.ParameterGroups.SYSTEM, Constants.SystemGroupParameterNames.FLEXIBLE_CLAIM_ON_CASH_BANK_ENABLED_FLAG);
    }

    /**
     * @see org.kuali.module.gl.document.GeneralLedgerPostingDocument#checkSufficientFunds()
     */
    public List<SufficientFundsItem> checkSufficientFunds() {
        LOG.debug("checkSufficientFunds() started");

        if (documentPerformsSufficientFundsCheck()) {
            SufficientFundsService sufficientFundsService = SpringServiceLocator.getSufficientFundsService();
            return sufficientFundsService.checkSufficientFunds(this);
        } else {
            return new ArrayList();
        }
    }
    
    /**
     * This method checks to see if SF checking should be done for this document.  This was originally part of SufficientFundsService.checkSufficientFunds() but was externalized so documents that need to override any of the SF methods can still explicitly check this
     */
    public boolean documentPerformsSufficientFundsCheck() {
        // check for reversing entries generated by an error correction.
        return StringUtils.isBlank(this.getDocumentHeader().getFinancialDocumentInErrorNumber());
    }

    /**
     * @see org.kuali.module.gl.document.GeneralLedgerPostingDocument#getPendingLedgerEntriesForSufficientFundsChecking()
     */
    public List<GeneralLedgerPendingEntry> getPendingLedgerEntriesForSufficientFundsChecking() {
        return getGeneralLedgerPendingEntries();
    }

    /**
     * Override to call super and then iterate over all GLPEs and update the approved code appropriately.
     * 
     * @see Document#handleRouteStatusChange()
     */
    @Override
    public void handleRouteStatusChange() {
        super.handleRouteStatusChange();
        changeGeneralLedgerPendingEntriesApprovedStatusCode(); // update all glpes for doc and set their status to approved
    }

    /**
     * This method iterates over all of the GLPEs for a document and sets their approved status code to APPROVED "A".
     */
    private void changeGeneralLedgerPendingEntriesApprovedStatusCode() {
        if (getDocumentHeader().getWorkflowDocument().stateIsProcessed()) {
            for (GeneralLedgerPendingEntry glpe : getGeneralLedgerPendingEntries()) {
                glpe.setFinancialDocumentApprovedCode(Constants.DocumentStatusCodes.APPROVED);
            }
        }
    }

    /**
     * @see org.kuali.core.document.DocumentBase#toCopy()
     */
    @Override
    public void toCopy() throws WorkflowException {
        super.toCopy();
        getGeneralLedgerPendingEntries().clear();
    }

    /**
     * @see org.kuali.core.document.DocumentBase#toErrorCorrection()
     */
    @Override
    public void toErrorCorrection() throws WorkflowException {
        super.toErrorCorrection();
        getGeneralLedgerPendingEntries().clear();
    }
    
    
}
