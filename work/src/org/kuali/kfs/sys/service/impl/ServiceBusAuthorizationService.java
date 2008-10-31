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
package org.kuali.kfs.sys.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.service.ParameterService;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.AuthenticationService;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.ksb.service.AuthorizationService;

public class ServiceBusAuthorizationService implements AuthorizationService {
    private ParameterService parameterService;

    public boolean isAdministrator(HttpServletRequest request) {
        String networkId = SpringContext.getBean(AuthenticationService.class).getPrincipalName(request);
        Person user = SpringContext.getBean(org.kuali.rice.kim.service.PersonService.class).getPersonByPrincipalName(networkId);
        if (user == null) {
            throw new RuntimeException("Failed to fetch user " + networkId);
        }
        return KIMServiceLocator.getIdentityManagementService().isMemberOfGroup(user.getPrincipalId(), org.kuali.kfs.sys.KFSConstants.KFS_GROUP_NAMESPACE, getParameterService().getParameterValue(ParameterConstants.FINANCIAL_SYSTEM_ALL.class, KFSConstants.CoreApcParms.SERVICE_BUS_ACCESS_GROUP_PARM));
    }

    private ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = SpringContext.getBean(ParameterService.class);
        }
        return parameterService;
    }
}

