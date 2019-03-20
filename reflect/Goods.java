public class Goods {
    private int id;
    private String goodName = "default";

    public Goods() {
        super();
    }

    public Goods(int id) {
        super();
        this.id = id;
    }

    public Goods(int id, String goodName) {
        super();
        this.id = id;
        this.goodName = goodName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodName + '\'' +
                '}';
    }
}
