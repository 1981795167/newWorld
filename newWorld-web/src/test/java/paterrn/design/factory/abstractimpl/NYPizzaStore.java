package paterrn.design.factory.abstractimpl;

import paterrn.design.factory.abstrct.AbstractPizzaStore;
import paterrn.design.factory.abstrct.Pizza;
import paterrn.design.factory.inteimpl.NYPizzaInFacImpl;

/**
 * @author Xavier.liu
 * @date 2020/6/12 14:54
 */
public class NYPizzaStore extends AbstractPizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        switch (type){
            case "cheese":
                return new NYStyleCheesePizza(new NYPizzaInFacImpl());
            case "veggie":
                return new NYStyleVeggiePizza(new NYPizzaInFacImpl());
            default:
                return null;
        }
    }
}
