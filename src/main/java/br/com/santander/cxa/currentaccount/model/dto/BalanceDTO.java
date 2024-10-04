package br.com.santander.cxa.currentaccount.model.dto;

/** Data Transfer Object (DTO) responsible for represent a balance. */
public class BalanceDTO {

    /** Balance id */
    private Integer id;

    /** Balance name. */
    private String name;

    /**
     * Constructor for the class.
     *
     * @param name of the new balance.
     */
    public BalanceDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor for the class.
     */
    public BalanceDTO() {
    }

    /**
     * Method responsible for retrieve the id value.
     * 
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Method responsible for set the id value.
     * 
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Method responsible for retrieve the name value.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for set the name value.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
	@Override
	public String toString() {
		return "BalanceDTO [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceDTO other = (BalanceDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
