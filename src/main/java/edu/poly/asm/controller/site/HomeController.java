package edu.poly.asm.controller.site;

import edu.poly.asm.domain.Product;
import edu.poly.asm.model.ProductDto;
import edu.poly.asm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller

public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("home")
    public String listProducts(Model model) {
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "site/home";
    }

    @RequestMapping(value = "getImage/{id}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long id) {
        Optional<Product> sop = productService.findById(id);
        if (sop.isPresent()) {
            try {
                Path filename = Paths.get("uploads/images", sop.get().getImage());
                byte[] bytes = Files.readAllBytes(filename);

                ByteArrayResource bais = new ByteArrayResource(bytes);

                return ResponseEntity.ok().contentLength(bytes.length).contentType(MediaType.parseMediaType("image/png")).body(bais);
            } catch (Exception e) {

                e.printStackTrace();

            }
        }
        return ResponseEntity.notFound().build();
    }

}
