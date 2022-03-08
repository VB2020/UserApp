package com.example.useroperationsapp.repository;
import com.example.useroperationsapp.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long>, JpaSpecificationExecutor<PhoneEntity> {
}
