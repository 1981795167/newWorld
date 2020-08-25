package paterrn.design.factory.abstractimpl;

import paterrn.design.factory.PizzaIngredientFactory;
import paterrn.design.factory.abstrct.Pizza;

/**
 * @author Xavier.liu
 * @date 2020/6/12 16:44
 */
public class CGStyleVeggiePizza extends Pizza {
    public CGStyleVeggiePizza(PizzaIngredientFactory ingredientFactory){
        name = "CGStyleVeggiePizza name";
        pizzaIngredientFactory = ingredientFactory;
    }

    @Override
    protected void prepare() {
        cheese = pizzaIngredientFactory.createCheese();
        dump = pizzaIngredientFactory.createDump();
        source = pizzaIngredientFactory.createSource();
    }
}
