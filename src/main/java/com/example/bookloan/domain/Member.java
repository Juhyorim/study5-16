package com.example.bookloan.domain;

import com.example.bookloan.domain.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "members")
@Entity
public class Member {
  @OneToMany(mappedBy = "member") //readonly
  private List<Loan> loans = new ArrayList<>();

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  private String name;

  @Embedded
  private Address address;

  public Member(String name, String city, String street) {
    this.name = name;
    this.address = new Address(city, street);
  }

  public void edit(String name, String city, String street) {
    if (name != null)
      this.name = name;
    this.address.edit(city, street);
  }

  public void addLoan(Loan loan) {
    this.loans.add(loan);
  }
}