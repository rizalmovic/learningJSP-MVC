package com.rizalmovic.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
@Table(name = "product_metas")
public class ProductMeta {

    @Column(name = "meta_key")
    private String key;

    @Column(name = "meta_value")
    private String value;
}
