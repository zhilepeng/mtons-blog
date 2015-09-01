/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.dao.impl;

import mblog.data.Attach;
import mblog.persist.dao.AttachDao;
import mblog.persist.entity.AttachPO;
import mtons.modules.persist.impl.DaoImpl;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public class AttachDaoImpl extends DaoImpl<AttachPO> implements AttachDao {
	private static final long serialVersionUID = -3561107849267517664L;

	public AttachDaoImpl() {
		super(AttachPO.class);
	}

	@Override
	public List<AttachPO> findByTarget(long toId) {
		return find(Restrictions.eq("toId", toId));
	}

	@Override
	public List<AttachPO> findByTarget(Set<Long> toIds) {
		return find(Restrictions.in("toId", toIds));
	}

	@Override
	public List<AttachPO> findByIds(Set<Long> ids) {
		return find(Restrictions.in("id", ids));
	}

	@Override
	public void batchAdd(List<Attach> datas) {
		String sql = "insert into mto_attachs(store,to_id,original,preview,width,height,status) values(?,?,?,?,?,?,?)";

		session().doWork(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Attach d : datas) {
                ps.setInt(1, d.getStore());
                ps.setLong(2, d.getToId());
                ps.setString(3, d.getOriginal());
                ps.setString(4, d.getPreview());
                ps.setInt(5, d.getWidth());
                ps.setInt(6, d.getHeight());
                ps.setInt(7, d.getStatus());

                ps.addBatch();
            }

            ps.executeBatch();
        });
	}

	@Override
	public void deleteByToId(long toId) {
		Query query = createQuery("delete from AttachPO where to_id = :toId");
		query.setLong("toId", toId);
		query.executeUpdate();
	}
}
