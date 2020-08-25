package paterrn.design.factory.abstrct;

import paterrn.design.factory.PizzaIngredientFactory;
import paterrn.design.factory.ingredient.Cheese;
import paterrn.design.factory.ingredient.Dump;
import paterrn.design.factory.ingredient.Source;

/**
 * @author Xavier.liu
 * @date 2020/6/12 14:34
 */
public abstract class Pizza {

    protected String name;

    protected PizzaIngredientFactory pizzaIngredientFactory;

    protected Dump dump;
    protected Source source;
    protected Cheese cheese;
    protected abstract void prepare();

    public void back(){
        System.out.println("back" + name + "30 minutes") ;
    }
    public void cut(){
        System.out.println("cutting " + name + "into slices");
    }
    public void box(){
        System.out.println("box==============");
    }

}
