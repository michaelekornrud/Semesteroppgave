package Bruker;

import ProductWindow.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentDataHandler {

    private String csvFile;

    public ComponentDataHandler()
    {
        String projectDirectory = System.getProperty("user.dir");
        csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";
        load();
    }


    public Map<String, List<Products>> load() {  //Metode for å laste inn csv-data, og "mappe" dataen.

        /*String projectDirectory = System.getProperty("user.dir");
        String csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";*/

        BufferedReader br = null;
        String currentLine = "";
        String cvsSplitBy = ";";
        boolean isFirstLine = true;

        List<String[]> componentData = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((currentLine = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                System.out.println(currentLine);
                String[] component = currentLine.split(cvsSplitBy);
                componentData.add(component);
            }


        } catch (FileNotFoundException e) {  //Endre til egne exceptons
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


    public Map<String, List<Products>>  createChoiceBoxes(List<String[]> componentData) {  //Metode for å laste inn komponenter

        Map<String, List<Products>> mappedComponents = new HashMap<>();



        for (String[] component : componentData){
            String number = component[0]; //Gjøre denne om til en int og få random unik number generator tilkoblet
            String name = component[1];
            String type = component[2];
            int quantity = Integer.parseInt(component[3]);
            int price = Integer.parseInt(component[4]);
            double totalPrice = Double.parseDouble(component[5]);
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

