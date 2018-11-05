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


package custom.sample.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.base.IdentityRuntimeException;
import org.wso2.carbon.identity.core.handler.InitConfig;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.event.Event;
import java.util.HashMap;
import java.util.Map;
import org.wso2.carbon.identity.event.handler.notification.NotificationHandler;
import org.wso2.carbon.identity.event.handler.notification.email.bean.Notification;
import org.wso2.carbon.identity.event.handler.notification.util.NotificationUtil;


public class CustomNotificationHandler extends NotificationHandler {


    private static Log log = LogFactory.getLog(CustomNotificationHandler.class);
    private Notification notification;

    @Override
    public String getName()  {
        return "customEmailSend";
    }

    public void handleEvent(Event event) throws IdentityEventException {
        log.info("***** inside handleEvent *****");
        Map<String, String> placeHolderData = new HashMap<>();

        for (Map.Entry<String, Object> entry : event.getEventProperties().entrySet()) {
            if (entry.getValue() instanceof String) {
                placeHolderData.put(entry.getKey(), (String) entry.getValue());
            }
        }

        Notification notification = NotificationUtil.buildNotification(event, placeHolderData);

        publishEmail(notification, placeHolderData);
    }
    @Override
    public void init(InitConfig configuration) throws IdentityRuntimeException {
        super.init(configuration);
    }

    protected void publishEmail(Notification notification, Map<String, String> placeHolderDataMap) {

       log.info("-------subject-----" +notification.getTemplate().getSubject());
       log.info("--------body-------" +notification.getTemplate().getBody());
       log.info("--------Footer-----" +notification.getTemplate().getFooter());
       log.info("--------send to----" + notification.getSendTo());
    }
}
