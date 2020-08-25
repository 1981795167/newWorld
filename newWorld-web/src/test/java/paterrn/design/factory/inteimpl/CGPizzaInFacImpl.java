package paterrn.design.factory.inteimpl;

import paterrn.design.factory.PizzaIngredientFactory;
import paterrn.design.factory.ingredient.Cheese;
import paterrn.design.factory.ingredient.Dump;
import paterrn.design.factory.ingredient.Source;

/**
 * @author Xavier.liu
 * @date 2020/6/12 18:01
 */
public class CGPizzaInFacImpl implements PizzaIngredientFactory {
    @Override
    public Cheese createCheese() {
        return new Cheese();
    }

    @Override
    public Dump createDump() {
        return new Dump();
    }

    @Override
    public Source createSource() {
        return new Source();
    }
}
