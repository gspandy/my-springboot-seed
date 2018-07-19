/**   
* @Title: BaseService.java 
* @Package com.fosunling.portal.service 
* @version V1.0   
*/
package com.seed.springboot.common.support;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.seed.springboot.common.utils.exception.BusinessException;
import com.seed.springboot.common.utils.idgen.IdGenerate;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: BaseService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author RuYang ruyang@fosun.com
 * @date 2017年12月6日 下午1:07:57
 * 
 */
@Transactional(readOnly = true)
@Slf4j
public class BaseService<M extends BaseMapper<T>, T extends BaseEntity<T>> implements IService<T> {

	@Autowired
	private M mapper;
	
	public M getMapper() {
		return mapper;
	}
	
	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#select(java.lang.Object)
	 */
	@Override
	public List<T> select(T record) {
		// TODO Auto-generated method stub
		return mapper.select(record);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectByKey(java.lang.Object)
	 */
	@Override
	public T selectByKey(Object key) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(key);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectAll()
	 */
	@Override
	public List<T> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectOne(java.lang.Object)
	 */
	@Override
	public T selectOne(T record) {
		// TODO Auto-generated method stub
		return mapper.selectOne(record);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectCount(java.lang.Object)
	 */
	@Override
	public int selectCount(T record) {
		// TODO Auto-generated method stub
		return mapper.selectCount(record);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#save(java.lang.Object)
	 */
	@Override
	public int save(T record) {
		// TODO Auto-generated method stub
		if (record.getIsNewRecord()) {
			record.preInsert();
			return mapper.insertSelective(record);
		} else {
			record.preUpdate();
			return mapper.updateByPrimaryKeySelective(record);
		}
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#batchSave(java.util.List)
	 */
	@Override
	public int batchSave(List<T> list) {
		// TODO Auto-generated method stub
		int result = 0;
		for (T record : list) {
			int count = mapper.insertSelective(record);
			result += count;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#update(java.lang.Object)
	 */
	@Override
	public int update(T entity) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(entity);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#delete(java.lang.Object)
	 */
	@Override
	public int delete(T record) {
		// TODO Auto-generated method stub
		return mapper.delete(record);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#batchDelete(java.util.List)
	 */
	@Override
	public int batchDelete(List<T> list) {
		// TODO Auto-generated method stub
		int result = 0;
		for (T record : list) {
			int count = mapper.delete(record);
			if (count < 1) {
				log.error("删除数据失败");
				throw new BusinessException("删除数据失败!");
			}
			result += count;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#deleteByKey(java.lang.Object)
	 */
	@Override
	public int deleteByKey(Object key) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(key);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectByExample(java.lang.Object)
	 */
	@Override
	public List<T> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return mapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectCountByExample(java.lang.Object)
	 */
	@Override
	public int selectCountByExample(Object example) {
		// TODO Auto-generated method stub
		return mapper.selectCountByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#updateByExample(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int updateByExample(T record, Object example) {
		// TODO Auto-generated method stub
		return mapper.updateByExampleSelective(record, example);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#deleteByExample(java.lang.Object)
	 */
	@Override
	public int deleteByExample(Object example) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(example);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectByRowBounds(java.lang.Object, org.apache.ibatis.session.RowBounds)
	 */
	@Override
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return mapper.selectByRowBounds(record, rowBounds);
	}

	/* (non-Javadoc)
	 * @see com.seed.springboot.common.support.IService#selectByExampleAndRowBounds(java.lang.Object, org.apache.ibatis.session.RowBounds)
	 */
	@Override
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return mapper.selectByExampleAndRowBounds(example, rowBounds);
	}

	protected String generateId() {
		return IdGenerate.nextId();
	}
	
}
