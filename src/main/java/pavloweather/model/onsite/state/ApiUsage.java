package pavloweather.model.onsite.state;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "API_USAGE")
public class ApiUsage {

	@Id
    private Integer id;

    private Date first;

    private Date last;

    @Column(name = "VISITS")
    private Integer count;
    
    public Integer getId(){
		return id;
	}
    
    public void setId(Integer id){
		this.id = id;
	}

	public Date getFirst(){
		return first;
	}

	public void setFirst(Date first){
		this.first = first;	
	}

	public Date getLast(){
		return last;
	}

	public void setLast(Date last){
		this.last = last;	
	}

	public Integer getNumber(){
		return this.count;
	}

	public void setNumber(Integer count){
		this.count = count;
	}
}