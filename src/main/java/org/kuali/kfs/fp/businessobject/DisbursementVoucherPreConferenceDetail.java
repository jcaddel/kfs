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

package org.kuali.kfs.fp.businessobject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * This class is used to represent a disbursement voucher pre-conference detail.
 */
public class DisbursementVoucherPreConferenceDetail extends PersistableBusinessObjectBase {

    private String documentNumber;
    private String dvConferenceDestinationName;
    private Date disbVchrConferenceStartDate;
    private Date disbVchrConferenceEndDate;
    private KualiDecimal disbVchrConferenceTotalAmt;
    private String disbVchrExpenseCode;


    private List dvPreConferenceRegistrants;

    /**
     * Default no-arg constructor.
     */
    public DisbursementVoucherPreConferenceDetail() {
        dvPreConferenceRegistrants = new ArrayList<DisbursementVoucherPreConferenceRegistrant>();
    }

    /**
     * Gets the documentNumber attribute.
     * 
     * @return Returns the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }


    /**
     * Gets the dvPreConferenceRegistrants attribute.
     * 
     * @return Returns the dvPreConferenceRegistrants.
     */
    public List getDvPreConferenceRegistrants() {
        return dvPreConferenceRegistrants;
    }

    /**
     * Sets the dvPreConferenceRegistrants attribute value.
     * 
     * @param dvPreConferenceRegistrants The dvPreConferenceRegistrants to set.
     */
    public void setDvPreConferenceRegistrants(List dvPreConferenceRegistrants) {
        this.dvPreConferenceRegistrants = dvPreConferenceRegistrants;
    }

    /**
     * Sets the documentNumber attribute.
     * 
     * @param documentNumber The documentNumber to set.
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Gets the dvConferenceDestinationName attribute.
     * 
     * @return Returns the dvConferenceDestinationName
     */
    public String getDvConferenceDestinationName() {
        return dvConferenceDestinationName;
    }


    /**
     * Sets the dvConferenceDestinationName attribute.
     * 
     * @param dvConferenceDestinationName The dvConferenceDestinationName to set.
     */
    public void setDvConferenceDestinationName(String dvConferenceDestinationName) {
        this.dvConferenceDestinationName = dvConferenceDestinationName;
    }

    /**
     * Gets the disbVchrConferenceStartDate attribute.
     * 
     * @return Returns the disbVchrConferenceStartDate
     */
    public Date getDisbVchrConferenceStartDate() {
        return disbVchrConferenceStartDate;
    }


    /**
     * Sets the disbVchrConferenceStartDate attribute.
     * 
     * @param disbVchrConferenceStartDate The disbVchrConferenceStartDate to set.
     */
    public void setDisbVchrConferenceStartDate(Date disbVchrConferenceStartDate) {
        this.disbVchrConferenceStartDate = disbVchrConferenceStartDate;
    }

    /**
     * Gets the disbVchrConferenceEndDate attribute.
     * 
     * @return Returns the disbVchrConferenceEndDate
     */
    public Date getDisbVchrConferenceEndDate() {
        return disbVchrConferenceEndDate;
    }


    /**
     * Sets the disbVchrConferenceEndDate attribute.
     * 
     * @param disbVchrConferenceEndDate The disbVchrConferenceEndDate to set.
     */
    public void setDisbVchrConferenceEndDate(Date disbVchrConferenceEndDate) {
        this.disbVchrConferenceEndDate = disbVchrConferenceEndDate;
    }

    /**
     * Gets the disbVchrConferenceTotalAmt attribute.
     * 
     * @return Returns the disbVchrConferenceTotalAmt
     */
    public KualiDecimal getDisbVchrConferenceTotalAmt() {
        KualiDecimal totalConferenceAmount = KualiDecimal.ZERO;

        if (dvPreConferenceRegistrants != null) {
            for (Iterator iter = dvPreConferenceRegistrants.iterator(); iter.hasNext();) {
                DisbursementVoucherPreConferenceRegistrant registrantLine = (DisbursementVoucherPreConferenceRegistrant) iter.next();
                totalConferenceAmount = totalConferenceAmount.add(registrantLine.getDisbVchrExpenseAmount());
            }
        }

        return totalConferenceAmount;
    }


    /**
     * Sets the disbVchrConferenceTotalAmt attribute.
     * 
     * @param disbVchrConferenceTotalAmt The disbVchrConferenceTotalAmt to set.
     */
    public void setDisbVchrConferenceTotalAmt(KualiDecimal disbVchrConferenceTotalAmt) {
        this.disbVchrConferenceTotalAmt = disbVchrConferenceTotalAmt;
    }

    /**
     * Gets the disbVchrExpenseCode attribute.
     * 
     * @return Returns the disbVchrExpenseCode
     */
    public String getDisbVchrExpenseCode() {
        return disbVchrExpenseCode;
    }


    /**
     * Sets the disbVchrExpenseCode attribute.
     * 
     * @param disbVchrExpenseCode The disbVchrExpenseCode to set.
     */
    public void setDisbVchrExpenseCode(String disbVchrExpenseCode) {
        this.disbVchrExpenseCode = disbVchrExpenseCode;
    }

    /**
     * @see org.kuali.rice.krad.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper_RICE20_REFACTORME() {
        LinkedHashMap m = new LinkedHashMap();
        m.put(KFSPropertyConstants.DOCUMENT_NUMBER, this.documentNumber);
        return m;
    }
}
