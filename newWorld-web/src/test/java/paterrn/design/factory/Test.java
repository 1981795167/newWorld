package paterrn.design.factory;

import paterrn.design.factory.abstractimpl.CGPizzaStore;
import paterrn.design.factory.abstractimpl.NYPizzaStore;

/**
 * @author Xavier.liu
 * @date 2020/6/12 17:12
 */
public class Test {
    public static void main(String[] args) {
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        nyPizzaStore.orderPizza("cheese");

        CGPizzaStore cgPizzaStore = new CGPizzaStore();
        cgPizzaStore.orderPizza("veggie");

    }
}
