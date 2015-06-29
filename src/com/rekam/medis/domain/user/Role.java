package com.rekam.medis.domain.user;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="role")
@NamedQueries({
    @NamedQuery(name="Role.findAll", query="SELECT r FROM Role r  ORDER BY r.authority"),
    @NamedQuery(name="Role.countAll", query="SELECT COUNT(r) FROM Role r  ORDER BY r.authority"),
    @NamedQuery(name="Role.findByAuthority", query="SELECT r FROM Role r WHERE r.authority=:authority"),
})
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * The id
	 */
	@Id
    @Basic(optional=false)
    @Column(name="id")
    @GeneratedValue
	private int id;
	
    @Basic(optional=false)
    @Column(name="authority")
    private String authority;

    @Basic(optional=false)
    @Column(name="name")
    private String name;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
