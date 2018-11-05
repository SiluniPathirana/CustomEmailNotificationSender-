/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


package custom.sample.email.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.email.mgt.EmailTemplateManager;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.carbon.user.core.service.RealmService;
import custom.sample.email.CustomNotificationHandler;

/**
 * @scr.component name="custom.sample.email" immediate="true"
 *
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 * @scr.reference name="emailTemplateManager.service"
 * interface="org.wso2.carbon.email.mgt.EmailTemplateManager" cardinality="1..1"
 * policy="dynamic" bind="setEmailTemplateManager" unbind="unsetEmailTemplateManager"
 *
 */


public class CustomEmailSendHandlerComponent {
    private static Log log = LogFactory.getLog(CustomEmailSendHandlerComponent.class);

    private static RealmService realmService;
    private static EmailTemplateManager emailTemplateManager;

    protected void activate(ComponentContext ctxt) {

        log.info("CustomEmailSendHandlerComponent bundle activated successfully..");
        BundleContext bundleContext = ctxt.getBundleContext();
        bundleContext.registerService(AbstractEventHandler.class.getName(), new CustomNotificationHandler(), null);

    }

    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.debug("CustomEmailSendHandlerComponent is deactivated ");
        }
    }

    protected void setRealmService(RealmService realmService) {
        log.error("Setting the Realm Service");

        if (log.isDebugEnabled()) {
            log.debug("Setting the Realm Service");
        }
        CustomEmailSendHandlerComponent.realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("UnSetting the Realm Service");
        }
        CustomEmailSendHandlerComponent.realmService = null;
    }
    public static RealmService getRealmService() {
        return CustomEmailSendHandlerComponent.realmService;
    }

    protected void setEmailTemplateManager(EmailTemplateManager emailTemplateManager) {
        if (log.isDebugEnabled()) {
            log.debug("Setting the Email Template Manager");
        }
        CustomEmailSendHandlerComponent.emailTemplateManager = emailTemplateManager;
    }

    protected void unsetEmailTemplateManager(EmailTemplateManager emailTemplateManager) {
        if (log.isDebugEnabled()) {
            log.debug("UnSetting the Email Template Manager");
        }
        CustomEmailSendHandlerComponent.emailTemplateManager = null;
    }

    public static EmailTemplateManager getEmailTemplateManager() {
        return CustomEmailSendHandlerComponent.emailTemplateManager;
    }

}