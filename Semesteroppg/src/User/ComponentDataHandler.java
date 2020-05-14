package User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ComponentDataHandler {

    private String csvFile;
    public static String componentQuantity;

    public ComponentDataHandler(){
        String projectDirectory = System.getProperty("user.dir");
        csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";
        load();
    }


    public Map<String, List<Products>> load(){  //Metode for å laste inn csv-data, og "mappe" dataen.


        BufferedReader br = null;
        String currentLine = "";
        String cvsSplitBy = ";";

        List<String[]> componentData = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((currentLine = br.readLine()) != null) {

                String[] component = currentLine.split(cvsSplitBy);
                componentData.add(component);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (componentData.isEmpty()) {
            return null;
        }

        return createChoiceBoxes(componentData);


    }



    public Map<String, List<Products>>  createChoiceBoxes(List<String[]> componentData) throws ArrayIndexOutOfBoundsException {  //Metode for å laste inn komponenter

        Map<String, List<Products>> mappedComponents = new HashMap<>();



        for (String[] component : componentData){


            int number = 0;
            String numberToString = String.valueOf(number);
            String name = String.valueOf(component[1]);
            String type = component[5];
            int quantity = 1;
            double price = Double.parseDouble(component[4]);
            //Legg til fler om det skal være flere typer

            componentQuantity = component[2];
            int storage = Integer.parseInt(componentQuantity);




            Products prod = new Products(numberToString,name, type, quantity,price,storage);

            List<Products> compList = mappedComponents.get(type);

            if(compList == null){ //Kontrollerer om det er data i complist
                compList = new ArrayList<>();
            }

            compList.add(prod);


            mappedComponents.put(type, compList);


        }
        return mappedComponents;


    }


}

