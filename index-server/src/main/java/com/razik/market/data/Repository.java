package com.razik.market.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Repository extends JpaRepository<TreeEntity, Long> {

    @Query("select t from TreeEntity t where t.parent=:id")
    List<TreeEntity> getChildren(Long id);

    @Query(value = "select * from tree_entity where id=(coalesce(select parent from tree_entity where id=:id,:root))", nativeQuery = true)
    TreeEntity getParent(long id, long root);
}
