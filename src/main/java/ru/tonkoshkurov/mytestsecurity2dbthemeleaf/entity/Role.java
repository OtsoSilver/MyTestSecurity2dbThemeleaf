package ru.tonkoshkurov.mytestsecurity2dbthemeleaf.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Enabled
@Entity
@Table(name= "roles")
public class Role {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique=true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<User>();
}
