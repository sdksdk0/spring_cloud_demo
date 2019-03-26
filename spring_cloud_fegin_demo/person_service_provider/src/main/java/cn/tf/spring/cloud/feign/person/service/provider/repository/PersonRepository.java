package cn.tf.spring.cloud.feign.person.service.provider.repository;

import cn.tf.spring.cloud.feign.person.service.provider.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {



}
