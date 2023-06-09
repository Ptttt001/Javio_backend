package com.example.demo.obj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="my_table")
public class my_table {

    public my_table() {
    }

    public my_table(long id, String name) {
        this.id=id;
        this.name = name;
    }

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
