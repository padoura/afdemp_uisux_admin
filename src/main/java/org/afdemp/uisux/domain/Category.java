package org.afdemp.uisux.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.NaturalId;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NaturalId
	private String type;
	
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL)
	private Set<Product> productSet;


	public Category() {}
	
	public Category(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Product> getProductSet() {
		return productSet;
	}

	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}

	@PreRemove
	private void removeAssociationsWithChildren() {
	   for (Product product : productSet) {
		   product.setCategory(null);
	   }
	}
}
