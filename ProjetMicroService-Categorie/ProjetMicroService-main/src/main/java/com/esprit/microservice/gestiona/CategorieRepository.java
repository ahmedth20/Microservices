package com.esprit.microservice.gestiona;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    List<Categorie> findByOriginePays(String originePays);

}
