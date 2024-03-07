package com.spring.studentsystem.config;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.spring.studentsystem.model.Student;

@Configuration
public class ConfigureRepositoryRestConfiguration implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public ConfigureRepositoryRestConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] unsupported={HttpMethod.GET,HttpMethod.POST,HttpMethod.DELETE,HttpMethod.PUT};
        disableHttpMethod(Student.class,config,unsupported);
       exposeIds(config);
    }

    public void disableHttpMethod(Class theClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedMethod ){
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedMethod)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethod));
    }
    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities=entityManager.getMetamodel().getEntities();

        List<Class>  entityClasses=new ArrayList<>();

        for (EntityType tempEntityType:entities){
            entityClasses.add(tempEntityType.getJavaType());

            Class[] domainTypes=entityClasses.toArray(new Class[0]);
            config.exposeIdsFor(domainTypes);
        }
    }
}
