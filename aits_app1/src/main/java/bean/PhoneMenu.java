package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "phonemenu")
public class PhoneMenu {
    /**
     * 菜品id
     */
    @Id
    @GeneratedValue
    private String pmenuId;
    /**
     * 菜的数量
     */
    private int num;

}
