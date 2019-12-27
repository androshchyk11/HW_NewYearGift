public class ChocolateCandy implements Candy {

    private int price;
    private int weight;
    private String type;
    private String name;

    public ChocolateCandy(int price, int weight, String type,String name) {
        this.price = price;
        this.weight = weight;
        this.type = type;
        this.name = name;
    }
    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public String getName() {
        return name;
    }
}
