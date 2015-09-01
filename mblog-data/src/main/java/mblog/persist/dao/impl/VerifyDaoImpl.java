/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.dao.impl;

import mblog.persist.dao.VerifyDao;
import mblog.persist.entity.VerifyPO;
import mtons.modules.persist.impl.DaoImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * @author langhsu on 2015/8/14.
 */
public class VerifyDaoImpl extends DaoImpl<VerifyPO> implements VerifyDao  {

    public VerifyDaoImpl() {
        super(VerifyPO.class);
    }

    @Override
    public VerifyPO get(long userId, int type) {
        Criteria c = createCriteria();
        c.add(Restrictions.eq("userId", userId));
        c.add(Restrictions.eq("type", type));
        return (VerifyPO) c.uniqueResult();
    }

    @Override
    public VerifyPO getByUserId(long userId) {
        return findUniqueBy("userId", userId);
    }
}
