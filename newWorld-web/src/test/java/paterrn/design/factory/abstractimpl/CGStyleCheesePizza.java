package paterrn.design.factory.abstractimpl;

import paterrn.design.factory.PizzaIngredientFactory;
import paterrn.design.factory.abstrct.Pizza;

/**
 * @author Xavier.liu
 * @date 2020/6/12 16:44
 */
public class CGStyleCheesePizza extends Pizza {
    public CGStyleCheesePizza(PizzaIngredientFactory ingredientFactory){
        name = "CGStyleCheesePizza name";
        pizzaIngredientFactory = ingredientFactory;
    }

    @Override
    protected void prepare() {
        pizzaIngredientFactory.createCheese();
        pizzaIngredientFactory.createDump();
        pizzaIngredientFactory.createSource();
    }
}
