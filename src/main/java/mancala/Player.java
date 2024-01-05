package mancala;

public class Player {
    private String name;
    private Store store;

    public Player(){}
    public Player(String str){
        setName(str);
    }

    public String getName(){
        return this.name;
    }

    public Store getStore(){
        return this.store;
    }

    public int getStoreCount(){
        return this.store.getTotalStones();
    }

    public void setName(String str){
        this.name = str;
    }

    public void setStore(Store pStore){
        this.store = pStore;
    }

    public String toString(){
        return name;
    }

}