package galaksiya.demo.entities.concretes;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="homes")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="property_name")
    private String propertyName;

    @Column(name="price")
    private double price;

    @Column(name="house_type")
    private String houseType;

    @Column(name="area_in_sq")
    private int areaInSq;

    @Column(name="bedrooms_no")
    private int bedroomsNo;

    @Column(name="bathrooms_no")
    private int bathroomsNo;

    @Column(name="receptions_no")
    private int receptionsNo;

    @Column(name="location")
    private String location;

    @Column(name="city_country")
    private String cityCountry;

    @Column(name="postal_code")
    private String postalCode;









    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public int getAreaInSq() {
        return areaInSq;
    }

    public void setAreaInSq(int areaInSq) {
        this.areaInSq = areaInSq;
    }

    public int getBedroomsNo() {
        return bedroomsNo;
    }

    public void setBedroomsNo(int bedroomsNo) {
        this.bedroomsNo = bedroomsNo;
    }

    public int getBathroomsNo() {
        return bathroomsNo;
    }

    public void setBathroomsNo(int bathroomsNo) {
        this.bathroomsNo = bathroomsNo;
    }

    public int getReceptionsNo() {
        return receptionsNo;
    }

    public void setReceptionsNo(int receptionsNo) {
        this.receptionsNo = receptionsNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
