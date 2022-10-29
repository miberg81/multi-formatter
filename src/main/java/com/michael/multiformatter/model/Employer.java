package com.michael.multiformatter.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
    @Id
    @CsvBindByPosition(position = 0)
    Long id;

    @Column(name="firstname")
    @CsvBindByPosition(position = 1)
    String firstName;

    @Column(name="lastname")
    @CsvBindByPosition(position = 2)
    String lastname;

    @Column(name="address")
    @CsvBindByPosition(position = 3)
    String address;
}
