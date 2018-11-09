1. This is sample email notification handler where you can access email tempalte and other attributes.

2. You can write the logic inside the following method to invoke any external REST email sender service.

"protected void publishEmail(Notification notification, Map<String, String> placeHolderDataMap)"

3. You can access the subject, footer and email body as follows.

log.info("-------subject-----" +notification.getTemplate().getSubject());
log.info("--------body-------" +notification.getTemplate().getBody());
log.info("--------Footer-----" +notification.getTemplate().getFooter());
 
4.When engaging the custom email notification in the email sending flow.
Please follow below mentioned steps.

5.After a successful build, apply the OSGi bundle to the dropins folder resides in the <IS_HOME>/repository/components/ directory.

6. Then open the identity-event.properties file resides in the /wso2is-5.5.0/repository/conf/identity folder.

7. the default configuration is as follow.

module.name.2=emailSend
emailSend.subscription.1=TRIGGER_NOTIFICATION

8.You have to change the default config mentioned in above as follow

module.name.2=customEmailSend
customEmailSend.subscription.1=TRIGGER_NOTIFICATION
you have to provide the name, example "customEmailSend" which you have given in the following method in the java class CustomNotificationHandler

 @Override
    public String getName()  {
        return "customEmailSend";
    }
3. Restart the server.
