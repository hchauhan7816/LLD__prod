import entities.Coffee;
import entities.CoffeeDecorator;
import entities.decorators.MilkDecorator;
import entities.decorators.SugarDecorator;

public class App {
    public static void main(String[] args) throws Exception {
        CoffeeDecorator simpleCoffee = new Coffee(100);
        System.out.println(simpleCoffee.getCost());

        CoffeeDecorator milkDecorator = new MilkDecorator(simpleCoffee);
        System.out.println(milkDecorator.getCost());

        CoffeeDecorator sugarDecorator = new SugarDecorator(simpleCoffee);
        System.out.println(sugarDecorator.getCost());

        CoffeeDecorator milkAndSugarDecorator = new SugarDecorator(new MilkDecorator(new SugarDecorator(simpleCoffee)));
        System.out.println(milkAndSugarDecorator.getCost());
    }
}
