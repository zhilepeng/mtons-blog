/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.shiro.authc;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.subject.WebSubjectContext;

import mblog.data.AccountProfile;

public class AccountSubjectFactory implements SubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        WebSubjectContext wsc = (WebSubjectContext) context;
        AuthenticationInfo info = wsc.getAuthenticationInfo();

        AccountProfile profile = null;
        AccountSubject subject = null;
        if (info instanceof AccountAuthenticationInfo) {
        	profile = ((AccountAuthenticationInfo) info).getProfile();
            subject = doCreate(wsc, profile);
            subject.getSession(true).setAttribute("profile", profile);
        }else{
            Session session = wsc.getSession();
            if(session != null){
            	profile = (AccountProfile)session.getAttribute("profile");
            }
            subject = doCreate(wsc, profile);
        }
        

        return doCreate(wsc, profile);
    }

    private AccountSubject doCreate(WebSubjectContext wsc, AccountProfile profile) {
        return new AccountSubject(wsc.resolvePrincipals(), wsc.resolveAuthenticated(), wsc.resolveHost(),
                wsc.resolveSession(), wsc.isSessionCreationEnabled(), wsc.resolveServletRequest(),
                wsc.resolveServletResponse(), wsc.resolveSecurityManager(), profile);
    }
}
