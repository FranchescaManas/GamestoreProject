
        
package ClientForms;

import javax.swing.Icon;
/**
 *
 * @author Franchesca
 */
public class ModelContainer {

    public int getProductid() {
        return productid;
    }

    
    public void setProductid(int productid) {
        this.productid = productid;
    }

    
    public Icon getIcon() {
        return icon;
    }

    
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getPrice() {
        return price;
    }

    
    public void setPrice(String price) {
        this.price = price;
    }

    
    public String getPublisher() {
        return publisher;
    }

    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public ModelContainer(Icon icon, String title, String publisher, String price, int prodid){
        this.icon = icon;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.productid = prodid;
        
        
    }
    public ModelContainer(){
        
    }
    
    
    private Icon icon;
    private String title;
    private String price;
    private String publisher;
    private int productid;
}
