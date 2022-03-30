package com.spring.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class Address {

    @DynamoDBAttribute
    private String line1;

    @DynamoDBAttribute
    private  String city;

    @DynamoDBAttribute
    private  String country;
}