package com.example.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AuthorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

    userRepository.deleteAll();
    postRepository.deleteAll();


    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para SP", new AuthorDTO(maria));

	CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));


	post1.getComments().addAll(Arrays.asList(c1));

    postRepository.saveAll(Arrays.asList(post1));


    userRepository.saveAll(Arrays.asList(maria, alex, bob));
    postRepository.saveAll(Arrays.asList(post1));

    maria.getPosts().addAll(Arrays.asList(post1));
    userRepository.save(maria);

}
    
    
}
