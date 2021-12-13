package com.cookingrecipes.cookingrecipeswebmvc.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Document(collection = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Recipe {
	// идентификатор на рецептата (ObjectID - 24 символа hexadecimal);
	// потребител споделил рецептата;
	// име на рецептата (до 80 символа);
	// кратко описание на рецептата (до 256 символа);
	// време за приготвяне (в минути);
	// използвани продукти (списък от продукти);
	// снимка на резултата от рецептата (с файл upload - задължителен атрибут);
	// подробно описание (до 2048 символа);
	// ключови думи - tags (списък от тагове);
	// дата и час на споделяне (генерира се автоматично);
	// дата и час на последна модификация (генерира се автоматично);

	@Id
	@Pattern(regexp = "[0-9a-f]{24}")
	private String id;

	@Pattern(regexp = "[0-9a-f]{24}")
	private String authorId;

	@Transient
	private String authorName;

	@NonNull
	@NotNull
	@Size(min = 2, max = 80, message = "Recipe name must be between 2 and 80 characters long.")
	private String name;

	@NonNull
	@Size(min = 2, max = 256, message = "Recipe's short description name must be between 2 and 256 characters long.")
	private String shortDescription;

	@Size(min = 2, max = 2048, message = "Recipe's long description name must be between 2 and 2048 characters long.")
	private String longDescription;

	@NonNull
	private int preparingTime;

	@NonNull
	private List<String> products;

	@NonNull
	@Length(min = 8, max = 512)
	private String imageUrl = "/img/default_recipe.png";

	private List<String> tags;
	private LocalDateTime created = LocalDateTime.now();
	private LocalDateTime modified = LocalDateTime.now();
}
