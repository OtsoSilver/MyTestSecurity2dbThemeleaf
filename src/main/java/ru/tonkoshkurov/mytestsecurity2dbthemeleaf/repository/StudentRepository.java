package ru.tonkoshkurov.mytestsecurity2dbthemeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
