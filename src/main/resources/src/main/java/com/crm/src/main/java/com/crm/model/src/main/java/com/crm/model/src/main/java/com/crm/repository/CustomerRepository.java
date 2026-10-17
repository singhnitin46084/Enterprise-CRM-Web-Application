package com.crm.repository;

import com.crm.model.Customer;
import com.crm.model.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE " +
           "(:keyword IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           " LOWER(c.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           " LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           " LOWER(c.company) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
           "(:status IS NULL OR c.status = :status)")
    List<Customer> filterCustomers(@Param("keyword") String keyword, @Param("status") LeadStatus status);
}
