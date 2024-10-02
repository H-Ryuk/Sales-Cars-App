package com.hassan.Repo;

import com.hassan.Model.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface InvoicesRepo extends JpaRepository<Invoices, Long> {


}
