package ru.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.survey.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
