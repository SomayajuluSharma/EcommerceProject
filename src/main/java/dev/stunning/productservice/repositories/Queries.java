package dev.stunning.productservice.repositories;

public interface Queries {
    String Bring_Product_By_id = "Select title from Product where id = :id";
}
