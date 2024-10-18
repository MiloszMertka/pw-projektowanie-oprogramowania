package pl.edu.pw.ee.catering.model.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Image {

    @Column(nullable = false, name = "IMAGE_NAME")
    private String name;

    @Column(nullable = false)
    private String path;
}
