package paterrn.design.factory;

import paterrn.design.factory.ingredient.Cheese;
import paterrn.design.factory.ingredient.Dump;
import paterrn.design.factory.ingredient.Source;

/**
 * @author Xavier.liu
 * @date 2020/6/12 17:51
 * 抽象工厂模式：
 *
 */
public interface PizzaIngredientFactory {
    Cheese createCheese();
    Dump createDump();
    Source createSource();
}
