package object;

public class Items
{
    private int item_Id;
    private String description;
    
    public Items(){
    	this.item_Id = -1;
    	this.description = null;
    }
    public Items(int item_Id, String description){
    	this.item_Id = item_Id;
    	this.description = description;
    }
    public Items(String description){
    	this.description = description;
    }
	/**
	 * @return the item_Id
	 */
	public int getItem_Id() {
		return item_Id;
	}
	/**
	 * @param item_Id the item_Id to set
	 */
	public void setItem_Id(int item_Id) {
		this.item_Id = item_Id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    
    
}