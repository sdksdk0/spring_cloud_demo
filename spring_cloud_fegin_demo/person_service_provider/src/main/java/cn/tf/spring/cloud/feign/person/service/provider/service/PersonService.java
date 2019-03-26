package cn.tf.spring.cloud.feign.person.service.provider.service;

import cn.tf.spring.cloud.feign.person.service.provider.entity.Person;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class PersonService {

    /**
     * 通过标准的JPA的方式注入
     */
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Person person){
        entityManager.persist(person);
    }

}
