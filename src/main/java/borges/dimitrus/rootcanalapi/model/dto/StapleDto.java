package borges.dimitrus.rootcanalapi.model.dto;

import borges.dimitrus.rootcanalapi.model.entity.Staple;

public class StapleDto {

    private Long id;

    private String type;

    public StapleDto() {
    }

    public StapleDto(Staple staple){
        this.id = staple.getId();
        this.type = staple.getType();
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

    public Staple toEntity(){
        return new Staple(this.id, this.type);
    }
}
