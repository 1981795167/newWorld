package paterrn.design.factory.abstractimpl;

import paterrn.design.factory.abstrct.AbstractPizzaStore;
import paterrn.design.factory.abstrct.Pizza;
import paterrn.design.factory.inteimpl.CGPizzaInFacImpl;

/**
 * @author Xavier.liu
 * @date 2020/6/12 14:54
 */
public class CGPizzaStore extends AbstractPizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        switch (type){
            case "cheese":
                return new CGStyleCheesePizza(new CGPizzaInFacImpl());
            case "veggie":
                return new CGStyleVeggiePizza(new CGPizzaInFacImpl());
            default:
                return null;
        }
    }
}
