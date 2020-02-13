package com.example.demo.Dao;


import java.util.List;
import java.util.Optional;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Items;

@Repository
public interface ItemsDao extends JpaRepository<Items,Long> {



@Query(value = "SELECT * FROM Items i, inventory in WHERE i.inv_id=:invId", nativeQuery = true)
public List<Items> findAllByInv(@Param("invId")Long invId);

}
