package com.xworkz.finalproject.dto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "department")
public class DepartmentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String address;
    private String area;
    private LocalDateTime createdAt;
    private String createdby;
    private LocalDateTime modifiedat;
    private String modifiedby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public LocalDateTime getModifiedat() {
        return modifiedat;
    }

    public void setModifiedat(LocalDateTime modifiedat) {
        this.modifiedat = modifiedat;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                ", createdAt=" + createdAt +
                ", createdby='" + createdby + '\'' +
                ", modifiedat=" + modifiedat +
                ", modifiedby='" + modifiedby + '\'' +
                '}';
    }
}
