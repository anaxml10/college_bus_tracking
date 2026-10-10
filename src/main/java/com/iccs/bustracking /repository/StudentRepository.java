
package com.iccs.bustracking.repository;

import com.iccs.bustracking.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
