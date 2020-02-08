package com.example.demo;


import java.util.List;
import java.util.Optional;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsDao extends JpaRepository<Items,Integer> {



@Query(nativeQuery=true,name="findAllByInvId")
public List<Items> findAllByInvId(@Param("invId")Integer invId);
public List<Items> findByInv(Optional<Inventory> optional);
}
