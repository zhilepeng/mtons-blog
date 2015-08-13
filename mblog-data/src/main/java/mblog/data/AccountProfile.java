/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.data;

import java.util.List;

import mtons.modules.pojos.UserProfile;

/**
 * @author langhsu
 *
 */
public class AccountProfile extends UserProfile {
    private static final long serialVersionUID = 1748764917028425871L;

    private int roleId;

    private List<AuthMenu> authMenus;

    public AccountProfile(long id, String username) {
        super(id, username);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<AuthMenu> getAuthMenus() {
        return authMenus;
    }

    /**
     * 我也是拼了。。。冒泡排序法居然还能默写出来
     * @param authMenus
     */
    public void setAuthMenus(List<AuthMenu> authMenus) {
        for (int i = 0; i < authMenus.size(); i++) {
            for (int j = authMenus.size() - 1; j > 0; j--) {
                if (authMenus.get(i).getSort() > authMenus.get(j).getSort()) {
                    AuthMenu temp = authMenus.get(i);
                    authMenus.set(i, authMenus.get(j));
                    authMenus.set(j, temp);
                }
            }
        }
        this.authMenus = authMenus;
    }

}
