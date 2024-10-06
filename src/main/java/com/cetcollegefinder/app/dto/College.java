package com.cetcollegefinder.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String website;
    private String description;
    private String collegeType;
    private String minority;
    private Integer autonomy;
    // private Double cutoff2023;
    // private Double cutoff2022;
    // private Double cutoff2021;
    // private Double cutoff2020;
    private Double rating;
    // One college can have multiple branches with different cutoffs
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    @JsonManagedReference("college-branch")
    private List<CollegeBranch> branches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("user-college")
    private UserDTO users;

    // Getters and Setters
}
