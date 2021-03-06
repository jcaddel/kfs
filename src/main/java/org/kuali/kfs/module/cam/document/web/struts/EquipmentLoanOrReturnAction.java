/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 * 
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.cam.document.web.struts;

import static org.kuali.kfs.module.cam.CamsPropertyConstants.Asset.CAPITAL_ASSET_NUMBER;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kfs.module.cam.CamsConstants;
import org.kuali.kfs.module.cam.CamsPropertyConstants;
import org.kuali.kfs.module.cam.businessobject.Asset;
import org.kuali.kfs.module.cam.businessobject.AssetLocation;
import org.kuali.kfs.module.cam.document.EquipmentLoanOrReturnDocument;
import org.kuali.kfs.module.cam.document.service.AssetLocationService;
import org.kuali.kfs.module.cam.document.service.PaymentSummaryService;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.rice.krad.service.BusinessObjectService;


public class EquipmentLoanOrReturnAction extends KualiTransactionalDocumentActionBase {
    protected static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EquipmentLoanOrReturnAction.class);

    /**
     * This method had to override because equipmentLoanOrReturn information has to be refreshed before display
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward docHandlerForward = super.docHandler(mapping, form, request, response);
        EquipmentLoanOrReturnForm equipmentLoanOrReturnForm = (EquipmentLoanOrReturnForm) form;
        EquipmentLoanOrReturnDocument equipmentLoanOrReturnDocument = (EquipmentLoanOrReturnDocument) equipmentLoanOrReturnForm.getDocument();
        BusinessObjectService service = SpringContext.getBean(BusinessObjectService.class);
        Asset asset = equipmentLoanOrReturnDocument.getAsset();
        asset = handleRequestFromLookup(request, equipmentLoanOrReturnForm, equipmentLoanOrReturnDocument, service, asset);

        asset = handleRequestFromWorkflow(equipmentLoanOrReturnForm, equipmentLoanOrReturnDocument, service);
        asset = equipmentLoanOrReturnDocument.getAsset();

        asset.refreshReferenceObject(CamsPropertyConstants.Asset.ASSET_LOCATIONS);
        asset.refreshReferenceObject(CamsPropertyConstants.Asset.ASSET_PAYMENTS);
        SpringContext.getBean(AssetLocationService.class).setOffCampusLocation(asset);
        SpringContext.getBean(PaymentSummaryService.class).calculateAndSetPaymentSummary(asset);

        return docHandlerForward;
    }

    /**
     * This method handles when request is from a work flow document search
     * 
     * @param equipmentLoanOrReturnForm Form
     * @param equipmentLoanOrReturnDocument Document
     * @param service BusinessObjectService
     * @return Asset
     */
    protected Asset handleRequestFromWorkflow(EquipmentLoanOrReturnForm equipmentLoanOrReturnForm, EquipmentLoanOrReturnDocument equipmentLoanOrReturnDocument, BusinessObjectService businessObjectService) {
        Asset newAsset = new Asset();
        if (equipmentLoanOrReturnForm.getDocId() != null) {
            newAsset.setCapitalAssetNumber(equipmentLoanOrReturnDocument.getCapitalAssetNumber());
            newAsset = (Asset) businessObjectService.retrieve(newAsset);
            equipmentLoanOrReturnDocument.setAsset(newAsset);
        }
        return newAsset;
    }

    /**
     * This method handles the request coming from asset lookup screen
     * 
     * @param request Request
     * @param equipmentLoanOrReturnForm Current form
     * @param equipmentLoanOrReturnDocument Document
     * @param service Business Object Service
     * @param asset Asset
     * @return Asset
     */
    protected Asset handleRequestFromLookup(HttpServletRequest request, EquipmentLoanOrReturnForm equipmentLoanOrReturnForm, EquipmentLoanOrReturnDocument equipmentLoanOrReturnDocument, BusinessObjectService businessObjectService, Asset asset) {
        Asset newAsset = asset;
        if (equipmentLoanOrReturnForm.getDocId() == null && asset == null) {
            newAsset = new Asset();
            HashMap<String, Object> keys = new HashMap<String, Object>();
            String capitalAssetNumber = request.getParameter(CAPITAL_ASSET_NUMBER);
            keys.put(CAPITAL_ASSET_NUMBER, capitalAssetNumber);
            newAsset = (Asset) businessObjectService.findByPrimaryKey(Asset.class, keys);
            
            // set document status
            equipmentLoanOrReturnDocument.setNewLoan(true);
            equipmentLoanOrReturnDocument.setReturnLoan(false);
            
            if (newAsset != null) {
                // populate equipmentLoanOrReturn info when loan type is renew or return loan
                if (!request.getParameter(CamsConstants.AssetActions.LOAN_TYPE).equals(CamsConstants.AssetActions.LOAN)) {
                    populateEquipmentLoanOrReturnDocument(equipmentLoanOrReturnDocument, newAsset);
                    equipmentLoanOrReturnDocument.setNewLoan(false);
                }
                // populate loan return date when loan type is return loan
                if (request.getParameter(CamsConstants.AssetActions.LOAN_TYPE).equals(CamsConstants.AssetActions.LOAN_RETURN)) {
                    equipmentLoanOrReturnDocument.setLoanReturnDate(SpringContext.getBean(DateTimeService.class).getCurrentSqlDate());
                    equipmentLoanOrReturnDocument.setReturnLoan(true);
                }
                // reset loan date and expect return date for renew loan
                if (request.getParameter(CamsConstants.AssetActions.LOAN_TYPE).equals(CamsConstants.AssetActions.LOAN_RENEW)) {
                    equipmentLoanOrReturnDocument.setLoanDate(SpringContext.getBean(DateTimeService.class).getCurrentSqlDate());
                    equipmentLoanOrReturnDocument.setExpectedReturnDate(null);
                }
                equipmentLoanOrReturnDocument.setCapitalAssetNumber(newAsset.getCapitalAssetNumber());
                equipmentLoanOrReturnDocument.setAsset(newAsset);
            }
        }
        return newAsset;
    }

    /**
     * This method populate equipmentloanOrReturn document from asset and asset location
     * 
     * @param equipmentLoanOrReturnDocument EquipmentLoanOrReturnDocument
     * @param newAsset Asset
     * @return
     */
    protected void populateEquipmentLoanOrReturnDocument(EquipmentLoanOrReturnDocument equipmentLoanOrReturnDocument, Asset newAsset) {
        equipmentLoanOrReturnDocument.setLoanDate(newAsset.getLoanDate());
        equipmentLoanOrReturnDocument.setLoanReturnDate(newAsset.getLoanReturnDate());
        equipmentLoanOrReturnDocument.setExpectedReturnDate(newAsset.getExpectedReturnDate());
        // populate borrower address
        AssetLocation borrowerLocation = new AssetLocation();
        borrowerLocation.setCapitalAssetNumber(newAsset.getCapitalAssetNumber());
        borrowerLocation.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.BORROWER);
        borrowerLocation = (AssetLocation) SpringContext.getBean(BusinessObjectService.class).retrieve(borrowerLocation);
        if (borrowerLocation != null) {
            equipmentLoanOrReturnDocument.setBorrowerUniversalIdentifier(borrowerLocation.getAssetLocationContactIdentifier());
            equipmentLoanOrReturnDocument.setBorrowerAddress(borrowerLocation.getAssetLocationStreetAddress());
            equipmentLoanOrReturnDocument.setBorrowerCityName(borrowerLocation.getAssetLocationCityName());
            equipmentLoanOrReturnDocument.setBorrowerStateCode(borrowerLocation.getAssetLocationStateCode());
            equipmentLoanOrReturnDocument.setBorrowerZipCode(borrowerLocation.getAssetLocationZipCode());
            equipmentLoanOrReturnDocument.setBorrowerCountryCode(borrowerLocation.getAssetLocationCountryCode());
            equipmentLoanOrReturnDocument.setBorrowerPhoneNumber(borrowerLocation.getAssetLocationPhoneNumber());
        }
        // populate stored at address
        AssetLocation storeAtLocation = new AssetLocation();
        storeAtLocation.setCapitalAssetNumber(newAsset.getCapitalAssetNumber());
        storeAtLocation.setAssetLocationTypeCode(CamsConstants.AssetLocationTypeCode.BORROWER_STORAGE);
        storeAtLocation = (AssetLocation) SpringContext.getBean(BusinessObjectService.class).retrieve(storeAtLocation);
        if (storeAtLocation != null) {
            equipmentLoanOrReturnDocument.setBorrowerStorageAddress(storeAtLocation.getAssetLocationStreetAddress());
            equipmentLoanOrReturnDocument.setBorrowerStorageCityName(storeAtLocation.getAssetLocationCityName());
            equipmentLoanOrReturnDocument.setBorrowerStorageStateCode(storeAtLocation.getAssetLocationStateCode());
            equipmentLoanOrReturnDocument.setBorrowerStorageZipCode(storeAtLocation.getAssetLocationZipCode());
            equipmentLoanOrReturnDocument.setBorrowerStorageCountryCode(storeAtLocation.getAssetLocationCountryCode());
            equipmentLoanOrReturnDocument.setBorrowerStoragePhoneNumber(storeAtLocation.getAssetLocationPhoneNumber());
        }
    }

}
