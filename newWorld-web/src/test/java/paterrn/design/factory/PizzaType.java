package paterrn.design.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Xavier.liu
 * @date 2020/6/12 14:55
 */
@Getter
@AllArgsConstructor
public enum PizzaType {
    CHEESE(0,"cheese"),
    VEGGIE(1,"veggie"),
    PEPPERONI(2,"pepperoni"),
    CLAM(3,"clam");

    private final int key;
    private final String type;
}
