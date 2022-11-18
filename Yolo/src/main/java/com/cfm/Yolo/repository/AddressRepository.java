package com.cfm.Yolo.repository;

import com.cfm.Yolo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}