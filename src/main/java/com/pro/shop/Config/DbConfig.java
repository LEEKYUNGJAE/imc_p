package com.pro.shop.Config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.pro.shop.mapper")
public class DbConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory (DataSource datasource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        sqlSessionFactory.setDataSource(datasource);


        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

        return sqlSessionFactory.getObject();

    }
    

    @Bean
    public SqlSessionTemplate sessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(sqlSessionFactory);

    }
    
}
