package com.esprit.microservice.gestiona;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }


    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }


    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }


    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }


    public Categorie updateCategorie(Long id, Categorie updatedCategorie) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (existingCategorie.isPresent()) {
            Categorie categorie = existingCategorie.get();
            categorie.setNom(updatedCategorie.getNom()); // Mettre à jour le nom
            categorie.setDescription(updatedCategorie.getDescription());
            categorie.setDateCreation(updatedCategorie.getDateCreation());
            categorie.setOriginePays(updatedCategorie.getOriginePays());
            return categorieRepository.save(categorie);
        }
        return null;
    }


    public boolean deleteCategorie(Long id) {
        if (categorieRepository.existsById(id)) {
            categorieRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public long countCategories() {
        return categorieRepository.count();
    }
    public List<Categorie> findByOriginePays(String originePaysString) {
        try {
            Pays originePays = Pays.valueOf(originePaysString.toUpperCase()); // S'assurer que le format est correct
            return categorieRepository.findByOriginePays(originePays);
        } catch (IllegalArgumentException e) {
            // Gérer l'erreur si le pays ne correspond à aucun enum
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Pays: " + originePaysString);
        }
    }

}
