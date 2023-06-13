package com.cfm.Yolo.intern.repository;

import com.cfm.Yolo.intern.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}