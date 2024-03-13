package com.product.eurukaclient.enity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
@Data
@Entity
@Table(name = "product_look_up")
public class ProductLookUpEntity implements Serializable {
    @Id
    private String productId;
    private String title;
}
