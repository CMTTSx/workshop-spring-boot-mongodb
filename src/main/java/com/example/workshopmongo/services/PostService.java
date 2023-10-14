package com.example.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Optional<Post> findById(String id) {
        Optional<Post> user = repo.findById(id);
        if (user == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }

    public List<Post> findByTitle( String text ) {
        return repo.findByTitleContaining(text);
    }

}
