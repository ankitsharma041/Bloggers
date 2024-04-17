package com.ankit.blog.entities;


import java.util.Date;
import java.util.List;

import com.ankit.blog.payload.PostDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String title;
	private String content;
	private String image;
	private Date date;
	@ManyToOne
	private Category category;
	@ManyToOne
	private User user;
}
