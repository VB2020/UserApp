package com.example.useroperationsapp.repository;
import com.example.useroperationsapp.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>, JpaSpecificationExecutor<ProfileEntity> {
    List<ProfileEntity> findAllByAbleToIncreaseTrue();
}
