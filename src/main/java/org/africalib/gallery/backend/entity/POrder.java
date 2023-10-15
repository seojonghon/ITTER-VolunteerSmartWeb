package org.africalib.gallery.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "porders")
public class POrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int memberId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String address;

    @Column
    private String payment;

    @Column(length = 16)
    private String cardNumber;

    @Column(length = 500) //nullable = false
    private String pitems;
}
