package com.fernando.biblioteca.view.controller;

import com.fernando.biblioteca.service.LivroService;
import com.fernando.biblioteca.shared.LivroDto;
import com.fernando.biblioteca.view.model.LivroRequest;
import com.fernando.biblioteca.view.model.LivroResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    private ModelMapper mapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<LivroResponse>> obterTodosLivros(){
        List<LivroDto> livroDtos = service.obterTodosLivros();
        List<LivroResponse> response = livroDtos
                .stream()
                .map(livroDto -> mapper
                        .map(livroDto, LivroResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LivroResponse>> obterLivroPorId(@PathVariable Integer id){
        Optional<LivroDto> livroDto = service.obterLivroPorId(id);
        LivroResponse response = mapper.map(livroDto.get(), LivroResponse.class);
        return ResponseEntity.ok().body(Optional.of(response));
    }

    @PostMapping
    public ResponseEntity<LivroResponse> adicionar(@RequestBody LivroRequest livroRequest){
        LivroDto livroDto = mapper.map(livroRequest, LivroDto.class);
        livroDto = service.adicionarLivros(livroDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(mapper.map(livroDto, LivroResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizarLivro(@PathVariable Integer id, @RequestBody LivroRequest livroRequest){
        LivroDto livroDto = mapper.map(livroRequest, LivroDto.class);
        livroDto = service.atualizarLivro(id, livroDto);
        return ResponseEntity.ok().body(mapper.map(livroDto, LivroResponse.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Integer id){
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}
