package com.gx.ext.mail;

public interface MailServerInfoCache {
    MailServerInfo getDefaultMailServerInfo();

    void setDefaultMailServerInfo(MailServerInfo mailServerInfo);

    MailServerInfo getMailServerInfo(String name);

    void setMailServerInfo(MailServerInfo mailServerInfo);
}
