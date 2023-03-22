package com.fernando.biblioteca.repository;

import com.fernando.biblioteca.model.LivroModel;
import com.fernando.biblioteca.model.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepositoryOld {

    private List<LivroModel> listaLivros = new ArrayList<LivroModel>();
    private Integer ultimoLivro = 0;

    /**
     * Retorna todos os livros cadastrados.
     * @return Lista de livros
     */
    public List<LivroModel> obterTodos(){
        return listaLivros;
    }

    /**
     * Retorna um livro baseado no id
     * @param id do livro a ser pesquisado
     * @return Retorna o livro correspondente ao id
     */
    public Optional<LivroModel> oblterPorId(Integer id){
        return listaLivros.stream().filter(livro -> livro.getId() == id).findFirst();
    }

    /**
     * Adicionar um novo livro a lista
     * @param livro
     */
    public LivroModel adicionarLivro(LivroModel livro){
        if(livro.getId() == null){
            ultimoLivro++;
            livro.setId(ultimoLivro);
        }
        listaLivros.add(livro);
        return livro;
    }

    /**
     * Atualiza um livro se existente
     * @param livro a ser atualizado
     * @return livro atualizado
     */
    public LivroModel atualizarLivro(LivroModel livro){
        Optional<LivroModel> livroEncontrado = oblterPorId(livro.getId());
        if(livroEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Livro não encontrado");
        }
        remover(livro.getId());
        return adicionarLivro(livro);
    }

    /**
     * Remove um livro da lista pelo seu id
     * @param id do livro para ser removido
     */
    public void remover(Integer id){
        if(oblterPorId(id).isEmpty()){
            throw new ResourceNotFoundException("Livro não encontrado");
        }
        listaLivros.removeIf(livro -> livro.getId() == id);
    }

}
