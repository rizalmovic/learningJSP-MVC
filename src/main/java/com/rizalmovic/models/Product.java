package com.rizalmovic.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "sku", unique = true)
    private String sku;

    @NonNull
    private String name;

    @Enumerated(EnumType.STRING)
    private SourceEnum source;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection
    @CollectionTable(name = "product_metas", joinColumns = @JoinColumn(name = "product_id"))
    private List<ProductMeta> metas = new ArrayList<>();

    private Timestamp created_at;
    private Timestamp updated_at;
}
