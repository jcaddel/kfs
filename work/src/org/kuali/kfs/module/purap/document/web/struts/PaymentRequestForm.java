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
package org.kuali.module.purap.web.struts.form;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.core.web.ui.ExtraButton;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kfs.KFSConstants;
import org.kuali.kfs.context.SpringContext;
import org.kuali.module.purap.PurapAuthorizationConstants;
import org.kuali.module.purap.PurapConstants;
import org.kuali.module.purap.bo.PurApItem;
import org.kuali.module.purap.bo.PurchaseOrderItem;
import org.kuali.module.purap.bo.PurchaseOrderVendorStipulation;
import org.kuali.module.purap.document.PaymentRequestDocument;
import org.kuali.module.purap.document.authorization.PaymentRequestDocumentActionAuthorizer;
import org.kuali.module.purap.service.PurapService;

/**
 * Struts Action Form for Payment Request document.
 */
public class PaymentRequestForm extends AccountsPayableFormBase {

    private PurchaseOrderVendorStipulation newPurchaseOrderVendorStipulationLine;
    
    /**
     * Constructs a PaymentRequestForm instance and sets up the appropriately casted document. 
     */
    public PaymentRequestForm() {
        super();
        setDocument(new PaymentRequestDocument());
        this.setNewPurchasingItemLine(setupNewPurchasingItemLine());
        setNewPurchaseOrderVendorStipulationLine(new PurchaseOrderVendorStipulation());
    }

    public PaymentRequestDocument getPaymentRequestDocument() {
        return (PaymentRequestDocument) getDocument();
    }

    public void setPaymentRequestDocument(PaymentRequestDocument purchaseOrderDocument) {
        setDocument(purchaseOrderDocument);
    }

    /**
     * @see org.kuali.core.web.struts.form.KualiForm#getAdditionalDocInfo1()
     */
    @Override
    public KeyLabelPair getAdditionalDocInfo1() {
        if (ObjectUtils.isNotNull(this.getPaymentRequestDocument().getPurapDocumentIdentifier())) {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.purapDocumentIdentifier", ((PaymentRequestDocument)this.getDocument()).getPurapDocumentIdentifier().toString());
        } else {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.purapDocumentIdentifier", "Not Available");
        }
    }

    /**
     * @see org.kuali.core.web.struts.form.KualiForm#getAdditionalDocInfo2()
     */
    @Override
    public KeyLabelPair getAdditionalDocInfo2() {
        if (ObjectUtils.isNotNull(this.getPaymentRequestDocument().getStatus())) {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.statusCode", ((PaymentRequestDocument)this.getDocument()).getStatus().getStatusDescription());
        } else {
            return new KeyLabelPair("DataDictionary.PaymentRequestDocument.attributes.statusCode", "Not Available");
        }
    }

    /**
     * @see org.kuali.module.purap.web.struts.form.PurchasingFormBase#setupNewPurchasingItemLine()
     */
    @Override
    public PurApItem setupNewPurchasingItemLine() {
        return new PurchaseOrderItem();
    }

    /**
     * Recreates the purchase order vendor stipulation line using the current user and current date stamp.
     * 
     * @return - PO vendor stipulation based on current but with current user and date stamp.
     */
    public PurchaseOrderVendorStipulation getAndResetNewPurchaseOrderVendorStipulationLine() {
        PurchaseOrderVendorStipulation aPurchaseOrderVendorStipulationLine = getNewPurchaseOrderVendorStipulationLine();
        setNewPurchaseOrderVendorStipulationLine(new PurchaseOrderVendorStipulation());
    
       // aPurchaseOrderVendorStipulationLine.setDocumentNumber(getPurchaseOrderDocument().getDocumentNumber());
        aPurchaseOrderVendorStipulationLine.setVendorStipulationAuthorEmployeeIdentifier(GlobalVariables.getUserSession().getUniversalUser().getPersonUniversalIdentifier());
        aPurchaseOrderVendorStipulationLine.setVendorStipulationCreateDate(SpringContext.getBean(DateTimeService.class).getCurrentSqlDate());

        return aPurchaseOrderVendorStipulationLine;
}

    public PurchaseOrderVendorStipulation getNewPurchaseOrderVendorStipulationLine() {
        return newPurchaseOrderVendorStipulationLine;
    }

    public void setNewPurchaseOrderVendorStipulationLine(PurchaseOrderVendorStipulation newPurchaseOrderVendorStipulationLine) {
        this.newPurchaseOrderVendorStipulationLine = newPurchaseOrderVendorStipulationLine;
    }

    /**
     * Determines if the payment request document has reached the INITIATE status.
     * 
     * @return - true if preq is initiated, false otherwise
     */
    public boolean isPaymentRequestInitiated() { 
        return StringUtils.equals(this.getPaymentRequestDocument().getStatusCode(),PurapConstants.PaymentRequestStatuses.INITIATE);
    } 

    /** 
     * Determines if a user is able to close a purchase order.
     * This is used by the checkbox "close PO" on the payment request form.
     * 
     * @return - true if able to close a PO, false otherwise
     */
    public boolean isAbleToClosePurchaseOrder(){
        boolean valid = false;
        
        PaymentRequestDocument preq = (PaymentRequestDocument)this.getDocument();
        
        if( SpringContext.getBean(PurapService.class).isFullDocumentEntryCompleted(preq) == false &&
            isApUser() &&
            PurapConstants.PurchaseOrderStatuses.OPEN.equals(preq.getPurchaseOrderDocument().getStatusCode()) ){
            
            valid = true;
        }
                
        return valid;
    }
    
    /**
     * Helper method to indicate if the current document has reached full document entry.
     * 
     * @return - true if document has reached full entry, false otherwise
     */
    public boolean isFullDocumentEntryCompleted(){
        PaymentRequestDocument preq = (PaymentRequestDocument)this.getDocument();
        return SpringContext.getBean(PurapService.class).isFullDocumentEntryCompleted(preq);        
    }
    
    /**
     * Build additional payment request specific buttons and set extraButtons list.
     * 
     * @return - list of extra buttons to be displayed to the user 
     */
    @Override
    public List<ExtraButton> getExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();

        PaymentRequestDocument preqDoc = this.getPaymentRequestDocument();

        String externalImageURL = SpringContext.getBean(KualiConfigurationService.class).getPropertyString(KFSConstants.RICE_EXTERNALIZABLE_IMAGES_URL_KEY);
        String appExternalImageURL = SpringContext.getBean(KualiConfigurationService.class).getPropertyString(KFSConstants.EXTERNALIZABLE_IMAGES_URL_KEY);

        if (this.getEditingMode().containsKey(PurapAuthorizationConstants.PaymentRequestEditMode.DISPLAY_INIT_TAB)) {
            if (this.getEditingMode().get(PurapAuthorizationConstants.PaymentRequestEditMode.DISPLAY_INIT_TAB).equals("TRUE")) {
                addExtraButton("methodToCall.continuePREQ", externalImageURL + "buttonsmall_continue.gif", "Continue");
                addExtraButton("methodToCall.clearInitFields", externalImageURL + "buttonsmall_clear.gif", "Clear");

            }
            else {
                PaymentRequestDocumentActionAuthorizer preqDocAuth = new PaymentRequestDocumentActionAuthorizer(preqDoc);
                
                //if preq holdable and user can put on hold, show button
                if (preqDocAuth.canHold()){
                    addExtraButton("methodToCall.addHoldOnPayment", appExternalImageURL + "buttonsmall_hold.gif", "Hold");
                }

                //if person can remove hold
                if (preqDocAuth.canRemoveHold() ){
                        addExtraButton("methodToCall.removeHoldFromPayment", appExternalImageURL + "buttonsmall_removehold.gif", "Remove");
                }

                //if preq can have a cancel request and user can submit request cancel, show button
                if (preqDocAuth.canRequestCancel()){
                    addExtraButton("methodToCall.requestCancelOnPayment", appExternalImageURL + "buttonsmall_requestcancel.gif", "Cancel");
                }
                
                //if person can remove request cancel
                if (preqDocAuth.canRemoveRequestCancel()){
                    addExtraButton("methodToCall.removeCancelRequestFromPayment", appExternalImageURL + "buttonsmall_remreqcanc.gif", "Remove");
                }                

                //add the calculate button
                if(preqDocAuth.canCalculate()){
                    addExtraButton("methodToCall.calculate", appExternalImageURL + "buttonsmall_calculate.gif", "Calculate");
                }
            }
        }

        return extraButtons;
    }
    
}