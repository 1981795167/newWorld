package paterrn.design.factory.abstrct;

/**
 * @author Xavier.liu
 * @date 2020/6/12 14:32
 */
public abstract class AbstractPizzaStore {

    public Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.back();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 工厂方法模式：
     * 抽象方法 --相当于一个创建对象工厂；
     * 通过子类来决定实例化的类是哪一个。
     *
     * 依赖倒置： 要依赖抽象，不用依赖具体类。
     * @param type
     * @return
     */
    protected abstract Pizza createPizza(String type);
}
