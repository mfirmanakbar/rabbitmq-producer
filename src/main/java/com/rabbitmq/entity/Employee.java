package com.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rabbitmq.json.CustomLocalDateSerializer;

import java.time.LocalDate;

public class Employee {

    @JsonProperty("employee_id")
    private String id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_join_date")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate joinDate;

    public Employee() {
    }

    public Employee(String id, String name, LocalDate joinDate) {
        super();
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
}
