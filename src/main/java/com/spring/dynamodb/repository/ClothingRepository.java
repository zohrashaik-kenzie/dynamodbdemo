package com.spring.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.spring.dynamodb.entity.Clothing;
import com.spring.dynamodb.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ClothingRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public String deleteBootsByStatus() {
        Clothing boots = new Clothing();
        boots.setId("bo_3980264e");
        try {
            DynamoDBDeleteExpression deleteExpression = new DynamoDBDeleteExpression();
            Map<String, ExpectedAttributeValue> expected = new HashMap<>();
            expected.put("status", new ExpectedAttributeValue(new AttributeValue("received")));
            deleteExpression.setExpected(expected);
            dynamoDBMapper.delete(boots, deleteExpression);
            return "Customer Id : "+ boots.getId() +" Deleted!";
        } catch (ConditionalCheckFailedException e) {
            System.out.println(e);
            return "Could not delete: " +  boots.getId() ;
        }
    }
}