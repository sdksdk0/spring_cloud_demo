package cn.tf.spring.cloud.feign.person.service.provider.web.controller;


import cn.tf.spring.cloud.feign.person.service.provider.entity.Person;
import cn.tf.spring.cloud.feign.person.service.provider.repository.PersonRepository;
import cn.tf.spring.cloud.feign.person.service.provider.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class PersonServiceProviderController {

    private final static Random random = new Random();
    @Autowired
    private PersonRepository personRepository;

    /**
     * 保存
     *
     * @param person {@link Person}
     * @return 如果成功，<code>true</code>
     */
    @PostMapping(value = "/person/save")
    public boolean savePerson(@RequestBody Person person) {

        cn.tf.spring.cloud.feign.person.service.provider.entity.Person person1 =
                new cn.tf.spring.cloud.feign.person.service.provider.entity.Person();
        BeanUtils.copyProperties(person,person1);
        personRepository.save(person1);
        return true;
    }



    /**
     * 查找所有的服务
     *
     * @return
     */
    @GetMapping(value = "/person/find/all")
    @HystrixCommand(fallbackMethod = "fallbackForFindAllPersons",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "400")
            }
    )
    public Collection<Person> findAllPersons() throws Exception {
        // 如果随机时间 大于 400 ，那么触发容错
        int value = random.nextInt(500);
        Thread.sleep(value);
        System.out.println("findAllPersons() costs " + value + " ms.");
        List<Person> personList = new ArrayList<>();
        Iterable<Person> personIterable = personRepository.findAll();
        personIterable.forEach(person -> {
            personList.add(person);
        });
        return personList;
    }

    /**
     * {@link #findAllPersons()} Fallback 方法
     *
     * @return 返回空集合
     */
    public Collection<Person> fallbackForFindAllPersons() {
        System.err.println("fallbackForFindAllPersons() is invoked!");
        return Collections.emptyList();
    }
    @GetMapping("/person/list")
    public Page<cn.tf.spring.cloud.feign.person.service.provider.entity.Person> list(Pageable pageable){
        return personRepository.findAll(pageable);
    }


}
