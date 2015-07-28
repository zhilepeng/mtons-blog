/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.extend.planet;

import java.util.Collection;
import java.util.List;

import mtons.modules.pojos.Paging;
import mblog.data.Post;

/**
 * 文章保卫层, 带缓存策略
 *
 * - 如果不使用缓存, 则直接调用Service
 * 
 * @author langhsu
 *
 */
public interface PostPlanet {
	/**
	 * 分页查询文章
	 * 
	 * @param paging
	 * @param group
	 * @param ord
	 * @return
	 */
	Paging paging(Paging paging, int group, String ord);
	
	/**
	 * 查询指定用户的文章
	 * 
	 * @param paging
	 * @param uid
	 * @return
	 */
	Paging pagingByUserId(Paging paging, long uid);
	
	/**
	 * 走廊查询 - (只返回一张图片)
	 * 
	 * @param paging
	 * @param group
	 * @param ord
	 * @return
	 */
	Paging gallery(Paging paging, int group, String ord);
	
	/**
	 * 从缓存中获取文章
	 * 
	 * @param id
	 * @return
	 */
	Post getPost(long id);
	
	/**
	 * 发布文章, 并清除缓存
	 * 
	 * @param post
	 */
	void post(Post post);

	/**
	 * 查询最近更新的文章
	 *
	 * @param maxResutls
	 * @param ignoreUserId
	 * @return
	 */
	List<Post> findRecents(int maxResutls, long ignoreUserId);

	/**
	 * 查询热门文章
	 *
	 * @param maxResutls
	 * @param ignoreUserId
	 * @return
	 */
	List<Post> findHots(int maxResutls, long ignoreUserId);

	/**
	 * 删除文章, 且刷新缓存
	 *
	 * @param id
	 * @param authorId
	 */
	void delete(long id, long authorId);

	/**
	 * 批量删除文章, 且刷新缓存
	 *
	 * @param ids
	 */
	void delete(Collection<Long> ids);
	
}
