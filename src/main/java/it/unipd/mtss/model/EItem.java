////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {
    
    private ProductType itemType;
    private String name;
    private double price;

    public EItem(ProductType _itemType, String _name, double _price){
        this.itemType = _itemType;
        this.name = _name;
        if(_price >= 0.0D) {
            this.price = _price;
        }else {
            throw new IllegalArgumentException("Il prezzo deve essere >= 0");
        }      
    }

    public ProductType getItemType(){
        return itemType;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    

}
