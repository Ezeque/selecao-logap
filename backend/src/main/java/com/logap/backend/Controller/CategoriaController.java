package com.logap.backend.Controller;


import com.logap.backend.Models.Categoria;
import com.logap.backend.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/criar")
    public Categoria criaCategoria(@RequestBody Categoria categoria){
        return categoriaService.criaCategoria(categoria);
    }

    @PostMapping("/excluir")
    public void excluirCategoria(@RequestBody Categoria categoria){
        categoriaService.excluiCategoria(categoria);
    }

    @GetMapping("/todas")
    public List<Categoria> recuperaTodasCategorias(){
        return categoriaService.recuperaTodasCategorias();
    }
}
