package com.chatgpt.studentregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chatgpt.studentregistration.model.Unit;
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
