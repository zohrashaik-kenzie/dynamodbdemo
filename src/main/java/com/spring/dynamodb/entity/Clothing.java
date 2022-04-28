package com.spring.dynamodb.entity;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


@DynamoDBTable(tableName = "ClothingItems")
public class Clothing {
    private String id;
    private String clothingType;
    private String color;
    private Double price;
    private String status;
    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @DynamoDBAttribute(attributeName = "clothing_type")
    public String getClothingType() {
        return clothingType;
    }
    public void setClothingType(String clothingType) {
        this.clothingType = clothingType;
    }
    @DynamoDBAttribute(attributeName = "color")
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @DynamoDBAttribute(attributeName = "price")
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}