package ProductWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Component_DataHandler {
    /*private String[] componentNames = new String[]{"Kabinett", "Maincard" ,"Processor", "Screencard", "Memory","Powersupply",
    "Harddrive", "CPU-Fan", "Fans", "Casemods","Screen", "Keyboard", "Headset","Mouse"};//Her er alle komponent-typene, legg til om det blir fler.*/
    private String csvFile;

    public Component_DataHandler()
    {
        String projectDirectory = System.getProperty("user.dir");
        csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";
        load();

    }


    public Map<String, List<Product>> load() {  //Metode for å laste inn csv-data, og "mappe" dataen.

        String projectDirectory = System.getProperty("user.dir");
        String csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";

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


    public Map<String, List<Product>>  createChoiceBoxes(List<String[]> componentData) {  //Metode for å laste inn komponenter
        Map<String, List<Product>> mappedComponents = new HashMap<>();


        for (String[] component : componentData){
            String id = component[0];
            String name = component[1];
            int numberOfProducts = Integer.parseInt(component[2]);
            String brand = component[3];
            double price = Double.parseDouble(component[4]);
            String type = component[5];
            //Legg til fler om det skal være flere typer

            Product prod = new Product(id,name,numberOfProducts,brand,price,type);


            //String mapId = type.toLowerCase();
            List<Product> compList = mappedComponents.get(type);

            if(compList == null){ //Kontrollerer om det er data i complist
                compList = new ArrayList<>();
            }

            compList.add(prod);

            mappedComponents.put(type, compList);

        }
        return mappedComponents;





        /*String idToLookFor = "HK12345678";  //Legg denne inn i add metoden. Den sjekker om prodID eksisterer fra før av.
        boolean containsId = false;

        for (String key : mappedComponents.keySet()){
            List<BaseComponent> comps = mappedComponents.get(key);
            containsId = comps.stream().anyMatch(baseComp -> baseComp.getId().equals(idToLookFor));
            if(containsId){
                break;
            }
        }

        System.out.println("Contains id: " + idToLookFor +": " + containsId);*/


    }

    public void write(Product product) throws IOException {  //Her blir det lagt til PRoduct produkt, så jeg må endre csv-filen til å ha like mange attributter som produkt-metoden
        FileWriter pw = new FileWriter(csvFile, true);
        try { pw.write(product.toString());}


        catch (FileNotFoundException e) {  //Endre til egne exceptons
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                try {
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
