package com.payment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.payment.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /** You can define JPA queries **/
//    @Query("select p from Product p where p.name = :name")
    public Account findByAccountNumber(Integer accNum);
    
    
//
//    /** No need to define @Query here, Spring DATA-JPA supports 
//     *  resolution of keywords inside method names. **/
//    public List<Product> findByNameContainingIgnoreCase(String searchString);

}