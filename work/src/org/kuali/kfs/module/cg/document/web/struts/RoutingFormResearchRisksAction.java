/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * $Source: /opt/cvs/kfs/work/src/org/kuali/kfs/module/cg/document/web/struts/RoutingFormResearchRisksAction.java,v $
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
package org.kuali.module.kra.routingform.web.struts.action;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.Constants;
import org.kuali.core.util.SpringServiceLocator;
import org.kuali.module.kra.routingform.bo.RoutingFormResearchRisk;
import org.kuali.module.kra.routingform.document.RoutingFormDocument;
import org.kuali.module.kra.routingform.web.struts.form.RoutingForm;

public class RoutingFormResearchRisksAction extends RoutingFormAction {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RoutingForm routingForm = (RoutingForm) form;
        routingForm.setResearchRiskTypes(SpringServiceLocator.getRoutingFormResearchRiskService().getAllResearchRiskTypes());
        return super.execute(mapping, form, request, response);
    }
    
    
    public ActionForward insertRoutingFormResearchRisk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;
        
        int addLineIndex = super.getSelectedLine(request);
        
        RoutingFormDocument routingFormDocument = routingForm.getRoutingFormDocument();
        
        RoutingFormResearchRisk[] newRoutingFormResearchRisksArray = routingForm.getNewRoutingFormResearchRisks().toArray(new RoutingFormResearchRisk[routingForm.getNewRoutingFormResearchRisks().size()]);
        
        RoutingFormResearchRisk routingFormResearchRisk = routingForm.getNewRoutingFormResearchRisk(addLineIndex);
        routingFormDocument.addRoutingFormResearchRisk(routingFormResearchRisk);
        
        newRoutingFormResearchRisksArray[addLineIndex] = new RoutingFormResearchRisk();
        
        // use getters and setters on the form to reinitialize the properties on the form.                
        routingForm.setNewRoutingFormResearchRisks(Arrays.asList(newRoutingFormResearchRisksArray));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteRoutingFormResearchRisk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RoutingForm routingForm = (RoutingForm) form;

        // Remove the item from the list.
        int lineToDelete = super.getLineToDelete(request);
        routingForm.getRoutingFormDocument().getRoutingFormResearchRisks().remove(lineToDelete);        
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
}