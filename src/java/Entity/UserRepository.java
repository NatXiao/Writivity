package src.java.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    // Méthodes personnalisées si besoin, ex : findByNom(String nom);
    Users findByUsername(String username);
}

