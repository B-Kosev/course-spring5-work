package com.cookingrecipes.cookingrecipeswebmvc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {
    //    идентификатор на записа (ObjectID - 24 символа hexadecimal);
//    уникално име на потребителя;
//    login име (username - до 15 символа - word characters);
//    парола (поне 8 символа, поне една цифра и знак различен от буква и цифра);
//    пол;
//    потребителска роля (user или admin);
//    снимка на потребителя (с файл upload - ако липсва се замества с аватар по подразбиране в зависимост от пола);
//    кратко представяне на потребителя (до 512 символа);
//    статус на валидност на акаунта - (active, suspended или deactivated);
//    дата и час на регистрация (генерира се автоматично);
//    дата и час на последна модификация (генерира се автоматично);
    @Id
    @Pattern(regexp = "[0-9a-f]{24}")
    private String id;

    @NonNull
    @NotNull
    private String name;

    @NonNull
    @NotNull
    @Size(min = 2, max = 15, message = "Username length should be between 2 and 15 characters long.")
    private String username;

    @NonNull
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")
    private String password;

    private String gender;
    private List<String> roles = List.of("USER");

    @Size(min = 2, max = 512, message = "Description length must be between 2 and 512 characters.")
    private String description;

    private boolean active = true;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> "ROLE_"+role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive();
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
