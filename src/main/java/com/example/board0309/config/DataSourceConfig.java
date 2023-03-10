package com.example.board0309.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.example.board0309.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {

    private final String MAPPER_XML_PATH; // mapper.xml 경로를 읽을 PATH 지정

    public DataSourceConfig(@Value("${mybatis.mapper-locations}") String MAPPER_XML_PATH) {
        this.MAPPER_XML_PATH = MAPPER_XML_PATH;
    }


    @Bean(name= "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(DataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(MAPPER_XML_PATH));  // resources 에서의 위치
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.board.dto");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory firstSqlSessionFactory) {
        return new SqlSessionTemplate(firstSqlSessionFactory);
    }


}
