package com.example.vouchercreator.Model;

import javax.persistence.*;
import java.beans.ConstructorProperties;

@Entity
@Table(name = "pakiety")
public class Pakiet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Integer cena;

    private String pakiet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public String getPakiet() {
        return pakiet;
    }

    public void setPakiet(String pakiet) {
        this.pakiet = pakiet;
    }

    @Override
    public String toString() {
        return "Pakiet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cena=" + cena +
                ", pakiet='" + pakiet + '\'' +
                '}';
    }
}