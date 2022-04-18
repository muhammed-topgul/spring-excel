package com.muhammedtopgul.springexcel.repository;

import com.muhammedtopgul.springexcel.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 11:08
 */

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
