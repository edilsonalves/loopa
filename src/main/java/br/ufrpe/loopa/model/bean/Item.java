package br.ufrpe.loopa.model.bean;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author edilson
 */
@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String category;
    private String placeFound;
    private String placeLeft;
    private LocalDate dateFound;
    private LocalDate dateReturned;
    @OneToOne
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlaceFound() {
        return placeFound;
    }

    public void setPlaceFound(String placeFound) {
        this.placeFound = placeFound;
    }

    public String getPlaceLeft() {
        return placeLeft;
    }

    public void setPlaceLeft(String placeLeft) {
        this.placeLeft = placeLeft;
    }

    public LocalDate getDateFound() {
        return dateFound;
    }

    public void setDateFound(LocalDate dateFound) {
        this.dateFound = dateFound;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
