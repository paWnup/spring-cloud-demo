package com.ftx.solution.common.base.repository;

import com.ftx.solution.common.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;

/**
 * 给自定义方法提供实现
 *
 * @author puan
 * @date 2018-12-06 14:46
 **/
public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    /**
     * 重写父类save方法
     * 在保存的时候将createTime和updateTime进行更新
     *
     * @param entity 实体类
     * @param <S>    参数化类型
     * @return 保存之后的对象
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public <S extends T> S save(S entity) {
        //TODO 只适用于主键自增，如果手动设置主键，那么createTime将不会设置。
        if (entity.getId() == null || entity.getId() == 0) {
            entity.setCreateTime(new Date());
        }
        entity.setUpdateTime(new Date());
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public T logicDelete(ID id) {
        T entity = findOne(id);
        entity.setDelete(true);
        return save(entity);
    }
}
