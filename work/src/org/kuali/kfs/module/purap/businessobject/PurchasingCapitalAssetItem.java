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
package org.kuali.kfs.module.purap.businessobject;

import org.kuali.kfs.module.purap.document.PurchasingDocument;


public interface PurchasingCapitalAssetItem {

    public Integer getCapitalAssetItemIdentifier();

    public void setCapitalAssetItemIdentifier(Integer capitalAssetItemIdentifier);

    public Integer getItemIdentifier();

    public void setItemIdentifier(Integer itemIdentifier);

    public String getCapitalAssetTransactionTypeCode();

    public void setCapitalAssetTransactionTypeCode(String capitalAssetTransactionTypeCode);

    public Integer getCapitalAssetSystemIdentifier();

    public void setCapitalAssetSystemIdentifier(Integer capitalAssetSystemIdentifier);

    public CapitalAssetTransactionType getCapitalAssetTransactionType();

    public void setCapitalAssetTransactionType(CapitalAssetTransactionType capitalAssetTransactionType);

    public PurchasingCapitalAssetSystem getPurchasingCapitalAssetSystem();

    public void setRequisitionCapitalAssetSystem(RequisitionCapitalAssetSystem requisitionCapitalAssetSystem);

    public PurchasingDocument getDocument();
 
    public void setDocument(PurchasingDocument pd);
       
    public PurchasingItem getPurchasingItem();        
          
}