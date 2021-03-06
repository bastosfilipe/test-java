package br.com.blz.testjava.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1468716482302282609L;

	@Id
	@SequenceGenerator(name = "inventory_seq", sequenceName = "inventory_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
	private Long id;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "warehouses", joinColumns = @JoinColumn(name = "inventory_fk"))
	private List<Warehouse> warehouses;

	public Inventory() {
		this.warehouses = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@Transient
	public int getQuantity() {
		return this.warehouses.size();
	}

}
