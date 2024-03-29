package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Model.Inventory;
@Repository
public interface InventoryDao extends JpaRepository<Inventory,Long> {
	//@Transactional
	//@Modifying
	//@Query(value="insert into INVENTORY(INV_DESC,INV_NAME)VALUES(:name,:desc);",nativeQuery=true)
	//public void insertInventory(@Param("name")String name,@Param("desc")String desc);
}
