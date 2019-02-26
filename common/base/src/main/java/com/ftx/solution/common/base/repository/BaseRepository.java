package com.ftx.solution.common.base.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 自定义的repository，用于自定义一些公共的方法
 *
 * @author puan
 * @date 2018-12-06 14:17
 **/
@NoRepositoryBean //让Spring Data Jpa在启动时不会去实例化这个接口
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 逻辑删除
     *
     * @param id ID
     * @return 删除的实体类
     */
    T logicDelete(ID id);

}
