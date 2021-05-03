package nl.novi.yannickhoebers.reversedrecipe.repository;

import nl.novi.yannickhoebers.reversedrecipe.model.Cook;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CookRepository extends JpaRepository<Cook, Long> {
}