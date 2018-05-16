package com.ajlabs.demo.security;

import com.ajlabs.demo.model.Student;
import com.ajlabs.demo.repository.StudentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {

    private StudentRepository studentRepository;

    public SSUserDetailsService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Student student = studentRepository.findByUsername(username);
            if (student == null) {
                System.out.println("user not found with the provided username " + student.toString());
                return null;
            }

            System.out.println(" user from username " + student.toString());
            return new org.springframework.security.core.userdetails.User(
                    student.getUsername(), student.getPassword(), getAuthorities(student.getUsertype()));
        } catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthorities(String usertype){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        if (usertype == "student")
        {
            grantedAuthority = new SimpleGrantedAuthority("USER");
        }
        authorities.add(grantedAuthority);

        System.out.println("user authorities are " + authorities.toString());
        return authorities;
    }
}
