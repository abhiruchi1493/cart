package com.app.cart.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Omkar Nikam
 * @since 03-Feb-2018
 * Table for Category
 */

@Entity
@Table(name="Category")
@Data
@EqualsAndHashCode(exclude = {"offers","products"})
@ToString(exclude = {"offers","products"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categoryId")
public class Category {
	
	@Id
	@Column(unique = true, nullable = false)
	@SequenceGenerator(name="category_seq", sequenceName="category_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq")
	private Integer categoryId;
	
	@Column(unique = false, nullable = false, length=50)
	private String categoryName;
	
	@Column(unique = false, nullable = true, length=50)
	private String categoryImagePath;
	
	@Column(unique = false, nullable = true)
	private Integer parentCategoryId;
	
	@OneToMany( mappedBy = "category" , cascade = CascadeType.ALL, orphanRemoval = false )
	@JsonIgnore
	private Set<Product> products;
	
	@OneToMany( mappedBy = "category" , cascade = CascadeType.ALL, orphanRemoval = false )
	@JsonIgnore
	private Set<CategoryOffer> offers;

}
