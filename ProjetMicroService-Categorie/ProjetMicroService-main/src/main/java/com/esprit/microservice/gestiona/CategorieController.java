package com.esprit.microservice.gestiona;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }


    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Categorie categorie = categorieService.getCategorieById(id);
        return categorie != null ? ResponseEntity.ok(categorie) : ResponseEntity.notFound().build();
    }


    @PostMapping("/add")
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie savedCategorie = categorieService.saveCategorie(categorie);
        return ResponseEntity.ok(savedCategorie);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie updatedCategorie) {
        Categorie updated = categorieService.updateCategorie(id, updatedCategorie);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable Long id) {
        boolean deleted = categorieService.deleteCategorie(id);
        return deleted ? ResponseEntity.ok("Catégorie supprimée avec succès.") : ResponseEntity.notFound().build();
    }
    @GetMapping("/pays/{originePays}")
    public ResponseEntity<List<Categorie>> getCategoriesByPays(@PathVariable String originePays) {
        List<Categorie> result = categorieService.findByOriginePays(originePays);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/export/csv")
    public void exportCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"categories.csv\"");
        List<Categorie> categories = categorieService.getAllCategories();

        PrintWriter writer = response.getWriter();
        writer.println("ID,Nom,Description,Pays,DateCreation");
        for (Categorie c : categories) {
            writer.printf("%d,%s,%s,%s,%s%n",
                    c.getId(), c.getNom(), c.getDescription(), c.getOriginePays(), c.getDateCreation());
        }
        writer.flush();
    }
    @GetMapping("/stat/total")
    public ResponseEntity<Long> getTotalCategories() {
        return ResponseEntity.ok(categorieService.countCategories());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @GetMapping("/api/catfact")
    public ResponseEntity<String> getRandomCatFact() {
        RestTemplate restTemplate = new RestTemplate();
        String fact = restTemplate.getForObject("https://catfact.ninja/fact", String.class);
        return ResponseEntity.ok(fact);
    }
    @GetMapping("/api/dogimage")
    public ResponseEntity<String> getRandomDogImage() {
        RestTemplate restTemplate = new RestTemplate();
        String img = restTemplate.getForObject("https://dog.ceo/api/breeds/image/random", String.class);
        return ResponseEntity.ok(img);
    }
    @GetMapping("/api/paysinfo/{country}")
    public ResponseEntity<String> getCountryInfo(@PathVariable String country) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restcountries.com/v3.1/name/" + country;
        String result = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(result);
    }



}
