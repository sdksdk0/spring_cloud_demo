package cn.tf.spring.cloud.feign.api.service;

import cn.tf.spring.cloud.feign.api.domain.Person;
import cn.tf.spring.cloud.feign.api.hystrix.PersonServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

@FeignClient(value="person-service",fallback =  PersonServiceFallback.class)
public interface PersonService {

    @PostMapping(value="/person/save")
    boolean save(@RequestBody  Person person);

    @GetMapping(value = "/person/find/all")
    Collection<Person> findAll();

}
