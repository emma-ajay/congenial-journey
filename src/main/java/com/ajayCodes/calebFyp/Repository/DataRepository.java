package com.ajayCodes.calebFyp.Repository;

import com.ajayCodes.calebFyp.Entity.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DataRepository extends JpaRepository<Data,Long> {
    @Query(value = "select * from data", nativeQuery = true)
    Page<Data> getAllDataEntries(Pageable pageable);

    Data findDataByDataId (Long dataId);
}
