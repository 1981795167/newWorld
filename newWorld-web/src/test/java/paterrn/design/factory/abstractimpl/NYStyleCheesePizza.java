package paterrn.design.factory.abstractimpl;

import paterrn.design.factory.PizzaIngredientFactory;
import paterrn.design.factory.abstrct.Pizza;

/**
 * @author Xavier.liu
 * @date 2020/6/12 16:42
 */
public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza(PizzaIngredientFactory ingredientFactory){
        name = "NYStyleCheesePizza name";
        pizzaIngredientFactory = ingredientFactory;
    }

    @Override
    protected void prepare() {
        cheese = pizzaIngredientFactory.createCheese();
        dump = pizzaIngredientFactory.createDump();
        source = pizzaIngredientFactory.createSource();
    }
}
