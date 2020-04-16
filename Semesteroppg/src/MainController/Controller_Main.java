package MainController;

import Admin.Controller_Admin;
import ProductWindow.Component_DataHandler;
import ProductWindow.Controller_ProductWindow;
import ProductWindow.Product;

import java.util.List;
import java.util.Map;

public class Controller_Main {

    private Controller_Admin admin_Controller;
    private Controller_ProductWindow prodWindow_Controller;
    Component_DataHandler dh  = new Component_DataHandler();
    private Map<String, List<Product>> data;


   public Controller_Main()
   {
      data = dh.load();


   }







}
