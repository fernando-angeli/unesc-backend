package com.fernando.biblioteca.service;

import com.fernando.biblioteca.model.LivroModel;
import com.fernando.biblioteca.model.exception.ResourceNotFoundException;
import com.fernando.biblioteca.repository.LivroRepository;
import com.fernando.biblioteca.shared.LivroDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    private ModelMapper mapper = new ModelMapper();

    public List<LivroDto> obterTodosLivros(){
        return repository
                .findAll()
                .stream()
                .map(livroModel -> mapper
                        .map(livroModel, LivroDto.class))
                .collect(Collectors.toList());
    }

    public Optional<LivroDto> obterLivroPorId(Integer id){
        Optional<LivroModel> livroModel = repository.findById(id);
        if(livroModel.isEmpty()){
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
        LivroDto livroDto = mapper.map(livroModel.get(), LivroDto.class);
        return Optional.ofNullable(livroDto);
    }

    public LivroDto adicionarLivros(LivroDto livroDto){
        livroDto.setId(null);
        LivroModel livro = mapper.map(livroDto, LivroModel.class);
        livro = repository.save(livro);
        livroDto.setId(livro.getId());
        return livroDto;
    }

    public LivroDto atualizarLivro(Integer id, LivroDto livroDto){
        livroDto.setId(id);
        LivroModel livroModel = mapper.map(livroDto, LivroModel.class);
        repository.save(livroModel);
        return livroDto;
    }

    public void deletarLivro(Integer id){
        Optional<LivroModel> livroModel = repository.findById(id);
        if(livroModel.isEmpty()){
            throw new ResourceNotFoundException("Id " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

}
