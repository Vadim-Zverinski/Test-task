package com.example.demo.repository.api;

import com.example.demo.repository.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HotelRepository extends
        JpaRepository<Hotel,Long>,
        JpaSpecificationExecutor<Hotel>{
}
