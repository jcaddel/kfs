<%--
 Copyright 2006-2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/jsp/kfs/kfsTldHeader.jsp"%>

<kul:documentPage showDocumentInfo="true"
	documentTypeName="RoutingFormDocument"
	htmlFormAction="researchRoutingFormPersonnel"
	headerDispatch="save" headerTabActive="personnel" showTabButtons="true">
	
	<kul:errors keyMatch="${Constants.DOCUMENT_ERRORS}" />

	<kra-rf:routingFormHiddenDocumentFields/>
	
	<div align="right">
		<kul:help documentTypeName="${DataDictionary.RoutingFormDocument.documentTypeName}" pageName="Personnel" altText="page help"/>
	</div>

	<kra-rf:routingFormPersonnel/>

    <c:if test="${!empty KualiForm.document.routingFormPersonnel}">
	  <kfs:documentControls transactionalDocument="false" suppressRoutingControls="true" viewOnly="${!KualiForm.documentActions[Constants.KUALI_ACTION_CAN_EDIT]}" />
	</c:if>
</kul:documentPage>
