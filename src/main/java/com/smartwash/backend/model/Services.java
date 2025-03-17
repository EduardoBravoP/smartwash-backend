package com.smartwash.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "services")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Services {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String description;
    private Float price;
    private Integer duration;
}