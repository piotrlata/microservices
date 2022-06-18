package com.shop.product.model.dao;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.shop.common.model.dao.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Auditable implements IdentifiedDataSerializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private Category category;

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 1;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(name);
        out.writeString(description);
        out.writeDouble(price);
        out.writeInt(quantity);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.name = in.readString();
        this.description = in.readString();
        this.price = in.readDouble();
        this.quantity = in.readInt();
    }
}
