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



}
