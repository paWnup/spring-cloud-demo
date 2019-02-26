package com.ftx.solution.common.base.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * repository工厂，覆盖spring-data-jpa默认的
 * 用于生成自定义的repository
 * 这样我们所有的repository就都有自定义的方法
 *
 * @author puan
 * @date 2018-12-06 15:03
 **/
public class BaseRepositoryFactoryBean<T, ID extends Serializable> extends JpaRepositoryFactoryBean {

    public BaseRepositoryFactoryBean(Class repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BaseRepositoryFactory(em);
    }

    private static class BaseRepositoryFactory extends JpaRepositoryFactory {
        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl((Class) information.getDomainType(), em);
        }

        @Override
        protected Class getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}
