package com.cfm.Yolo.domain.repository;

import com.cfm.Yolo.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}