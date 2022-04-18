package com.muhammedtopgul.springexcel.repository;

import com.muhammedtopgul.springexcel.entity.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author muhammed-topgul
 * @since 18.04.2022 14:34
 */

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {
}
