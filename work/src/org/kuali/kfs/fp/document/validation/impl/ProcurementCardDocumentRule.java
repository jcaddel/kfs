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
package org.kuali.module.financial.rules;

import static org.kuali.Constants.ACCOUNTING_LINE_ERRORS;
import static org.kuali.Constants.AMOUNT_PROPERTY_NAME;
import static org.kuali.Constants.ZERO;
import static org.kuali.KeyConstants.ERROR_DOCUMENT_BALANCE_CONSIDERING_SOURCE_AND_TARGET_AMOUNTS;
import static org.kuali.KeyConstants.ERROR_ZERO_AMOUNT;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.ACCOUNT_NUMBER_GLOBAL_RESTRICTION_PARM_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.FUNCTION_CODE_GLOBAL_RESTRICTION_PARM_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.GLOBAL_FIELD_RESTRICTIONS_GROUP_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.MCC_OBJECT_CODE_GROUP_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.MCC_OBJECT_SUB_TYPE_GROUP_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.MCC_PARM_PREFIX;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.OBJECT_CONSOLIDATION_GLOBAL_RESTRICTION_PARM_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.OBJECT_LEVEL_GLOBAL_RESTRICTION_PARM_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.OBJECT_SUB_TYPE_GLOBAL_RESTRICTION_PARM_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.OBJECT_TYPE_GLOBAL_RESTRICTION_PARM_NM;
import static org.kuali.module.financial.rules.ProcurementCardDocumentRuleConstants.SUB_FUND_GLOBAL_RESTRICTION_PARM_NM;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.KeyConstants;
import org.kuali.PropertyConstants;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.KualiDecimal;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kfs.bo.AccountingLine;
import org.kuali.kfs.document.AccountingDocument;
import org.kuali.kfs.rules.AccountingDocumentRuleBase;
import org.kuali.module.financial.bo.ProcurementCardTargetAccountingLine;
import org.kuali.module.financial.bo.ProcurementCardTransactionDetail;
import org.kuali.module.financial.document.ProcurementCardDocument;
import org.kuali.workflow.KualiWorkflowUtils.RouteLevelNames;

import edu.iu.uis.eden.exception.WorkflowException;


/**
 * Business rule(s) applicable to Procurement Card document.
 */
public class ProcurementCardDocumentRule extends AccountingDocumentRuleBase {

    /**
     * Inserts proper errorPath, otherwise functions just like super.
     * 
     * @see org.kuali.core.rule.UpdateAccountingLineRule#processUpdateAccountingLineBusinessRules(org.kuali.core.document.FinancialDocument,
     *      org.kuali.core.bo.AccountingLine, org.kuali.core.bo.AccountingLine)
     */
    @Override
    public boolean processUpdateAccountingLineBusinessRules(AccountingDocument transactionalDocument, AccountingLine accountingLine, AccountingLine updatedAccountingLine) {
        fixErrorPath(transactionalDocument, accountingLine);

        return super.processUpdateAccountingLineBusinessRules(transactionalDocument, accountingLine, updatedAccountingLine);
    }

    /**
     * Only target lines can be changed, so we need to only validate them
     * 
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#processCustomAddAccountingLineBusinessRules(org.kuali.core.document.FinancialDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    @Override
    protected boolean processCustomAddAccountingLineBusinessRules(AccountingDocument transactionalDocument, AccountingLine accountingLine) {
        boolean allow = true;

        if (accountingLine instanceof ProcurementCardTargetAccountingLine) {
            LOG.debug("validating accounting line # " + accountingLine.getSequenceNumber());

            // Somewhat of an ugly hack... the goal is to have fixErrorPath run for cases where it's _not_ a new accounting line.
            // If it is a new accounting line, all is well. But if it isn't then this might be a case where approve is called
            // and we need to run validation with proper errorPath for that.
            if (accountingLine.getSequenceNumber() != null) {
                fixErrorPath(transactionalDocument, accountingLine);
            }

            LOG.debug("beginning object code validation ");
            allow = validateObjectCode(transactionalDocument, accountingLine);

            LOG.debug("beginning account number validation ");
            allow = allow & validateAccountNumber(transactionalDocument, accountingLine);

            LOG.debug("end validating accounting line, has errors: " + allow);
        }

        return allow;
    }

    /**
     * Only target lines can be changed, so we need to only validate them
     * 
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#processCustomUpdateAccountingLineBusinessRules(org.kuali.core.document.FinancialDocument,
     *      org.kuali.core.bo.AccountingLine, org.kuali.core.bo.AccountingLine)
     */
    @Override
    protected boolean processCustomUpdateAccountingLineBusinessRules(AccountingDocument transactionalDocument, AccountingLine accountingLine, AccountingLine updatedAccountingLine) {
        return processCustomAddAccountingLineBusinessRules(transactionalDocument, updatedAccountingLine);
    }

    /**
     * Only target lines can be changed, so we need to only validate them
     * 
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#processCustomReviewAccountingLineBusinessRules(org.kuali.core.document.FinancialDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    @Override
    protected boolean processCustomReviewAccountingLineBusinessRules(AccountingDocument transactionalDocument, AccountingLine accountingLine) {
        return processCustomAddAccountingLineBusinessRules(transactionalDocument, accountingLine);
    }

    /**
     * Checks object codes restrictions, including restrictions in parameters table.
     * 
     * @param transactionalDocument
     * @param accountingLine
     * @return boolean
     */
    public boolean validateObjectCode(AccountingDocument transactionalDocument, AccountingLine accountingLine) {
        ProcurementCardDocument pcDocument = (ProcurementCardDocument) transactionalDocument;
        ErrorMap errors = GlobalVariables.getErrorMap();

        String errorKey = PropertyConstants.FINANCIAL_OBJECT_CODE;
        boolean objectCodeAllowed = true;

        /* object code exist done in super, check we have a valid object */
        if (ObjectUtils.isNull(accountingLine.getObjectCode())) {
            return false;
        }

        /* make sure object code is active */
        if (!accountingLine.getObjectCode().isFinancialObjectActiveCode()) {
            errors.putError(errorKey, KeyConstants.ERROR_INACTIVE, "object code");
            objectCodeAllowed = false;
        }

        /* check object type global restrictions */
        objectCodeAllowed = objectCodeAllowed && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, OBJECT_TYPE_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectTypeCode(), errorKey, "Object type");

        /* check object sub type global restrictions */
        objectCodeAllowed = objectCodeAllowed && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, OBJECT_SUB_TYPE_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectSubTypeCode(), errorKey, "Object sub type");

        /* check object level global restrictions */
        objectCodeAllowed = objectCodeAllowed && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, OBJECT_LEVEL_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectLevelCode(), errorKey, "Object level");

        /* check object consolidation global restrictions */
        objectCodeAllowed = objectCodeAllowed && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, OBJECT_CONSOLIDATION_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getObjectCode().getFinancialObjectLevel().getFinancialConsolidationObjectCode(), errorKey, "Object consolidation code");

        /* get mcc restriction from transaction */
        String mccRestriction = "";
        ProcurementCardTargetAccountingLine line = (ProcurementCardTargetAccountingLine) accountingLine;
        List pcTransactions = pcDocument.getTransactionEntries();
        for (Iterator iter = pcTransactions.iterator(); iter.hasNext();) {
            ProcurementCardTransactionDetail transactionEntry = (ProcurementCardTransactionDetail) iter.next();
            if (transactionEntry.getFinancialDocumentTransactionLineNumber().equals(line.getFinancialDocumentTransactionLineNumber())) {
                mccRestriction = transactionEntry.getProcurementCardVendor().getTransactionMerchantCategoryCode();
            }
        }

        if (StringUtils.isBlank(mccRestriction)) {
            return objectCodeAllowed;
        }

        /* check object code is in permitted list for mcc */
        objectCodeAllowed = objectCodeAllowed && executeApplicationParameterRestriction(MCC_OBJECT_CODE_GROUP_NM, MCC_PARM_PREFIX + mccRestriction, accountingLine.getFinancialObjectCode(), errorKey, "Object code");

        /* check object sub type is in permitted list for mcc */
        objectCodeAllowed = objectCodeAllowed && executeApplicationParameterRestriction(MCC_OBJECT_SUB_TYPE_GROUP_NM, MCC_PARM_PREFIX + mccRestriction, accountingLine.getObjectCode().getFinancialObjectSubTypeCode(), errorKey, "Object sub type code");

        return objectCodeAllowed;
    }

    /**
     * Checks account number restrictions, including restrictions in parameters table.
     * 
     * @param transactionalDocument
     * @param accountingLine
     * @return boolean
     */
    public boolean validateAccountNumber(AccountingDocument transactionalDocument, AccountingLine accountingLine) {
        ProcurementCardDocument pcDocument = (ProcurementCardDocument) transactionalDocument;
        ErrorMap errors = GlobalVariables.getErrorMap();

        String errorKey = PropertyConstants.ACCOUNT_NUMBER;
        boolean accountNumberAllowed = true;

        /* account exist and object exist done in super, check we have a valid object */
        if (ObjectUtils.isNull(accountingLine.getAccount()) || ObjectUtils.isNull(accountingLine.getObjectCode())) {
            return false;
        }

        /* global account number restrictions */
        accountNumberAllowed = accountNumberAllowed && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, ACCOUNT_NUMBER_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getAccountNumber(), errorKey, "Account number");

        /* global sub fund restrictions */
        accountNumberAllowed = accountNumberAllowed && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, SUB_FUND_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getAccount().getSubFundGroupCode(), errorKey, "Sub fund code");

        /* global function code restrictions */
        accountNumberAllowed = accountNumberAllowed && executeApplicationParameterRestriction(GLOBAL_FIELD_RESTRICTIONS_GROUP_NM, FUNCTION_CODE_GLOBAL_RESTRICTION_PARM_NM, accountingLine.getAccount().getFinancialHigherEdFunctionCd(), errorKey, "Function code");

        return accountNumberAllowed;
    }

    /**
     * Overrides FinancialDocumentRuleBase.isDocumentBalanceValid and changes the default debit/credit comparision to checking
     * the target total against the total balance. If they don't balance, and error message is produced that is more appropriate for
     * PCDO.
     * 
     * @param transactionalDocument
     * @return boolean True if the document is balanced, false otherwise.
     */
    @Override
    protected boolean isDocumentBalanceValid(AccountingDocument transactionalDocument) {
        ProcurementCardDocument pcDocument = (ProcurementCardDocument) transactionalDocument;

        KualiDecimal targetTotal = pcDocument.getTargetTotal();
        KualiDecimal sourceTotal = pcDocument.getSourceTotal();

        boolean isValid = targetTotal.compareTo(sourceTotal) == 0;

        if (!isValid) {
            GlobalVariables.getErrorMap().putError(ACCOUNTING_LINE_ERRORS, ERROR_DOCUMENT_BALANCE_CONSIDERING_SOURCE_AND_TARGET_AMOUNTS, new String[] { sourceTotal.toString(), targetTotal.toString() });
        }

        return isValid;
    }

    /**
     * On procurement card, positive source amounts are credits, negative source amounts are debits
     * 
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#isDebit(FinancialDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    public boolean isDebit(AccountingDocument transactionalDocument, AccountingLine accountingLine) throws IllegalStateException {
        // disallow error correction
        IsDebitUtils.disallowErrorCorrectionDocumentCheck(this, transactionalDocument);
        return IsDebitUtils.isDebitConsideringSection(this, transactionalDocument, accountingLine);
    }

    /**
     * Override for fiscal officer full approve, in which case any account can be used.
     * 
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#accountIsAccessible(org.kuali.core.document.FinancialDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    @Override
    protected boolean accountIsAccessible(AccountingDocument transactionalDocument, AccountingLine accountingLine) {
        KualiWorkflowDocument workflowDocument = transactionalDocument.getDocumentHeader().getWorkflowDocument();
        List activeNodes = null;
        try {
            activeNodes = Arrays.asList(workflowDocument.getNodeNames());
        }
        catch (WorkflowException e) {
            LOG.error("Error getting active nodes " + e.getMessage());
            throw new RuntimeException("Error getting active nodes " + e.getMessage());
        }

        if (workflowDocument.stateIsEnroute() && activeNodes.contains(RouteLevelNames.ACCOUNT_REVIEW_FULL_EDIT)) {
            return true;
        }

        return super.accountIsAccessible(transactionalDocument, accountingLine);
    }

    /**
     * For transactions that are credits back from the bank, accounting lines can be negative. It still checks that an amount is not
     * zero.
     * 
     * @see org.kuali.module.financial.rules.FinancialDocumentRuleBase#isAmountValid(org.kuali.core.document.FinancialDocument,
     *      org.kuali.core.bo.AccountingLine)
     */
    @Override
    public boolean isAmountValid(AccountingDocument document, AccountingLine accountingLine) {
        KualiDecimal amount = accountingLine.getAmount();

        // Check for zero, negative amounts (non-correction), positive amounts (correction)
        String correctsDocumentId = document.getDocumentHeader().getFinancialDocumentInErrorNumber();
        if (ZERO.compareTo(amount) == 0) { // amount == 0
            GlobalVariables.getErrorMap().putError(AMOUNT_PROPERTY_NAME, ERROR_ZERO_AMOUNT, "an accounting line");
            LOG.info("failing isAmountValid - zero check");
            return false;
        }

        return true;
    }

    /**
     * Override to avoid seeing ERROR_DOCUMENT_SINGLE_ACCOUNTING_LINE_SECTION_TOTAL_CHANGED error message on PCDO.
     * 
     * @param propertyName
     * @param persistedSourceLineTotal
     * @param currentSourceLineTotal
     */
    @Override
    protected void buildTotalChangeErrorMessage(String propertyName, KualiDecimal persistedSourceLineTotal, KualiDecimal currentSourceLineTotal) {
        return;
    }

    /**
     * Fix the GlobalVariables.getErrorMap errorPath for how PCDO needs them in order to properly display errors on the
     * interface. This is different from kuali accounting lines because instead PCDO has accounting lines insides of
     * transactions. Hence the error path is slighly different. 
     * 
     * @param transactionalDocument
     * @param accountingLine
     */
    private void fixErrorPath(AccountingDocument financialDocument, AccountingLine accountingLine) {
        List transactionEntries = ((ProcurementCardDocument) financialDocument).getTransactionEntries();
        ProcurementCardTargetAccountingLine targetAccountingLineToBeFound = (ProcurementCardTargetAccountingLine) accountingLine;
        
        String errorPath = PropertyConstants.DOCUMENT;
        
        // originally I used getFinancialDocumentTransactionLineNumber to determine the appropriate transaction, unfortunatly
        // this makes it dependent on the order of transactionEntries in FP_PRCRMNT_DOC_T. Hence we have two loops below.
        boolean done = false;
        int transactionLineIndex = 0;
        for (Iterator iterTransactionEntries = transactionEntries.iterator(); !done && iterTransactionEntries.hasNext(); transactionLineIndex++) {
            ProcurementCardTransactionDetail transactionEntry = (ProcurementCardTransactionDetail) iterTransactionEntries.next();
            
            // Loop over the transactionEntry to find the accountingLine's location. Keep another counter handy.
            int accountingLineCounter = 0;
            for (Iterator iterTargetAccountingLines = transactionEntry.getTargetAccountingLines().iterator(); !done && iterTargetAccountingLines.hasNext(); accountingLineCounter++) {
                ProcurementCardTargetAccountingLine targetAccountingLine = (ProcurementCardTargetAccountingLine) iterTargetAccountingLines.next();
                
                if(targetAccountingLine.getSequenceNumber().equals(targetAccountingLineToBeFound.getSequenceNumber())) {
                    // Found the item, capture error path, and set boolean (break isn't enough for 2 loops).
                    errorPath = errorPath + "." + PropertyConstants.TRANSACTION_ENTRIES + "[" + transactionLineIndex + "]." + PropertyConstants.TARGET_ACCOUNTING_LINES + "[" + accountingLineCounter + "]";
                    done = true;
                }
            }
        }
        
        if (!done) {
            LOG.warn("fixErrorPath failed to locate item accountingLine=" + accountingLine.toString());
        }
        
        // Clearing the error path is not a universal solution but should work for PCDO. In this case it's the only choice
        // because KualiRuleService.applyRules will miss to remove the previous transaction added error path (only this
        // method knows how it is called).
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        errorMap.clearErrorPath();
        errorMap.addToErrorPath(errorPath);
    }
}