package cn.tf.spring.cloud.feign.person.service.provider.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Person实体
 */
@Entity
@Table(name="person")
public class Person extends cn.tf.spring.cloud.feign.api.domain.Person{

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "VARCHAR(128) NOT NULL")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
