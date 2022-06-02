package com.example.apiproduto.controller;

import com.example.apiproduto.entity.ProdutoEntity;
import com.example.apiproduto.repository.ProdutoRepository;
import com.example.apiproduto.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ProdutoController {


    private ProdutoRepository produtoRepository;


    public ProdutoController(ProdutoRepository produtoRepository) {

        this.produtoRepository = produtoRepository;
    }


    @GetMapping()
    public ResponseEntity listarProdutos() {
        return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity produtoById(@PathVariable Long id) {

        return new ResponseEntity<>(produtoRepository.findById(id), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<ProdutoEntity> cadastrarProduto(@RequestBody ProdutoEntity produtoEntity) {


        return new ResponseEntity<>(produtoRepository.save(produtoEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PutMapping()
    public ResponseEntity<ProdutoEntity> alterarProduto( @RequestBody ProdutoEntity produtoEntity) {

        Optional<ProdutoEntity> produto = produtoRepository.findById(produtoEntity.getId());

        if (produto.isPresent()) {
            produto.get().setNome(produtoEntity.getNome());
            produto.get().setDescricao(produtoEntity.getDescricao());
            produto.get().setPreco(produtoEntity.getPreco());


        }


        return new ResponseEntity<>(produtoRepository.save(produtoEntity), HttpStatus.OK);
    }


}

