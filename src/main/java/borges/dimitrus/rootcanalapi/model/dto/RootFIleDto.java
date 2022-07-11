package borges.dimitrus.rootcanalapi.model.dto;

import borges.dimitrus.rootcanalapi.model.entity.RootFile;

import javax.validation.constraints.NotNull;

public class RootFIleDto {

    private Long id;

    @NotNull
    private String typeName;

    private String brand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RootFIleDto() {
    }

    public RootFIleDto(RootFile rootFile){
        this.id = rootFile.getId();
        this.typeName = rootFile.getTypeName();
        this.brand = rootFile.getBrand();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public RootFile toEntinty(){
        return new RootFile(this.getId(), this.getTypeName(), this.getBrand());
    }


}
