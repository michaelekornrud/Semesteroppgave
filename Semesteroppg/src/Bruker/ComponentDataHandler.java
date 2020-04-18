package Bruker;

import ProductWindow.Product;
import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ComponentDataHandler {

    private String csvFile;

    public ComponentDataHandler() throws FileNotFoundException {
        String projectDirectory = System.getProperty("user.dir");
        csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";
        load();
    }


    public Map<String, List<Products>> load() throws FileNotFoundException{  //Metode for å laste inn csv-data, og "mappe" dataen.

        /*String projectDirectory = System.getProperty("user.dir");
        String csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";*/



        BufferedReader br = null;
        String currentLine = "";
        String cvsSplitBy = ";";
        boolean isFirstLine = true;
        Scanner inputStream;


        List<String[]> componentData = new ArrayList<>();
        inputStream = new Scanner(csvFile);
        while(inputStream.hasNext()){
            String line = inputStream.next();
            String [] values = line.split(cvsSplitBy);
            componentData.add(values);
        }
        inputStream.close();

        int lineNo = 1;
        for(String[] line : componentData){
            int columnNo = 1;
            for(String value : line){
                System.out.println("Line " + lineNo + "Column " + columnNo + " : " + value);
                columnNo++;
            }
            lineNo++;

        }


        return createChoiceBoxes(componentData);


    }


    public Map<String, List<Products>>  createChoiceBoxes(List<String[]> componentData) throws ArrayIndexOutOfBoundsException {  //Metode for å laste inn komponenter

        Map<String, List<Products>> mappedComponents = new HashMap<>();



        for (String[] component : componentData){
            try{
                component[0] = String.valueOf(1);
                component[3] = String.valueOf(1);

            }
            catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
            double total = (Integer.parseInt(component[4]) * Integer.parseInt(component[3]));
            //String currPrice = component[4];

            String number = String.valueOf(1);
            String name = String.valueOf(component[1]);
            String type = component[5];
            int quantity = 1;
            int price = Integer.parseInt(component[4]);
            double totalPrice = total;
            //Legg til fler om det skal være flere typer

            Products prod = new Products(number,name, type, quantity,price,totalPrice);


            //String mapId = type.toLowerCase();
            List<Products> compList = mappedComponents.get(type);

            if(compList == null){ //Kontrollerer om det er data i complist
                compList = new ArrayList<>();
            }

            compList.add(prod);

            mappedComponents.put(type, compList);

        }
        return mappedComponents;



       /* String idToLookFor = "";    //Legg dnne i add metoden. Den sjekker om id eksisterer.
        boolean containsId = false;

        for (String key : mappedComponents.keySet()){  //Metode for å sjekke om en id eksistere fra før av
            List<Product> Proditems = mappedComponents.get(key);
            containsId = Proditems.stream().anyMatch(prodItems -> prodItems.getTxtProductNumber().equals(idToLookFor));
            if(containsId){
                break;
            }
        }

        System.out.println("Contains id: " + idToLookFor +": " + containsId);*/

    }
}

