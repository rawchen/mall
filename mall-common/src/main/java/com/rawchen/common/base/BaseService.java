package com.rawchen.common.base;

import com.rawchen.common.entity.DataEntity;
import com.rawchen.common.entity.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author RawChen
 * @since 2021-10-30 23:54
 */
public abstract class BaseService<D extends BaseMapper<T>, T extends DataEntity<T>> {

	/**
	 * 日志工具
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("all")
	@Autowired
	protected D dao;

	public BaseService() {
	}

	public T get(String id) {
		return dao.get(id);
	}

	public T get(int id) {
		return dao.get(id);
	}

	public T get(T entity) {
		return dao.get(entity);
	}

	public List<T> findList(T entity) {
		List<T> list = this.dao.findList(entity);
		return list;
	}

	public List<T> findAllList(T entity) {
		List<T> list = this.dao.findAllList(entity);
		return list;
	}

	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(this.findList(entity));
		return page;
	}

	@Transactional(
			readOnly = false
	)
	public void save(T entity, Object id) {
		if (id == null) {
			entity.preInsert();
			this.dao.insert(entity);
		} else {
			entity.preUpdate();
			this.dao.update(entity);
		}
	}

	@Transactional(
			readOnly = false
	)
	public void insert(T entity) {
		entity.preInsert();
		this.dao.insert(entity);
	}

	@Transactional(
			readOnly = false
	)
	public void update(T entity) {
		entity.preUpdate();
		this.dao.update(entity);
	}

	@Transactional(
			readOnly = false
	)
	public void delete(T entity) {
		this.dao.delete(entity);
	}

	@Transactional(
			readOnly = false
	)
	public void deleteByLogic(T entity) {
		this.dao.deleteByLogic(entity);
	}

	@Transactional(
			readOnly = false
	)
	public void deleteAll(String ids, T entity) {
		String[] idArray = ids.split(",");
		String[] tempStr = idArray;
		int len = idArray.length;

		for (int i = 0; i < len; ++i) {
			String id = tempStr[i];
			entity = this.get(id);
			this.dao.delete(entity);
		}
	}

	@Transactional(
			readOnly = false
	)
	public void deleteAllByLogic(String ids, T entity) {
		String[] idArray = ids.split(",");
		String[] tempStr = idArray;
		int len = idArray.length;

		for (int i = 0; i < len; ++i) {
			String id = tempStr[i];
			entity = this.get(id);
			this.dao.deleteByLogic(entity);
		}
	}

	public T findUniqueByProperty(String propertyName, Object value) {
		return dao.findUniqueByProperty(propertyName, value);
	}
}
