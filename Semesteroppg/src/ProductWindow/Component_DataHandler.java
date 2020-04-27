package ProductWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;


public class Component_DataHandler {
    private String csvFile;

    public Component_DataHandler()
    {
        String projectDirectory = System.getProperty("user.dir");
        csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";
        load();
    }


    public void changeDataFromTableviewToCsvAndSave(Map<String, List<Product>> data) throws Exception{
        //Metode som sletter gammel data fra csv-fil og lagrer den nye dataen.

        Writer writer = null;

        try {
            File file = new File(csvFile);
            writer = new BufferedWriter(new FileWriter(file));
            for (List<Product> productList : data.values()) {
                for (Product product : productList) {
                    String text = product.toString();
                    writer.write(text);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public void removeObjectFromChoiceBoxAndCsvFile(String lineContent) throws Exception{
        //hentet fra https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it?fbclid=IwAR0SrqrG9wls0WEutLCpovSql8zX7nIOcw9ShgCWSOSZxfu5fh_yK9tm0eE
        File file = new File(csvFile);
        List<String> out = Files.lines(file.toPath())
                .filter(line -> !line.contains(lineContent))
                .collect(Collectors.toList());
        Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

    }





    public Map<String, List<Product>> load() {  //Metode for å laste inn csv-data, og "mappe" dataen.

        BufferedReader br = null;
        String currentLine = "";
        String cvsSplitBy = ";";
        boolean isFirstLine = true;

        List<String[]> componentData = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((currentLine = br.readLine()) != null) {
               /* if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }*/ //Hopper over første linje i csv-filen

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


    public Map<String, List<Product>>  createChoiceBoxes(List<String[]> componentData) throws NumberFormatException {  //Metode for å laste inn komponenter

        Map<String, List<Product>> mappedComponents = new HashMap<>();



        for (String[] component : componentData){
            String id = component[0];
            String name = component[1];
            String number = component[2];
            int numberOfProducts = Integer.parseInt(number);
            String brand = component[3];
            double price = Double.parseDouble(component[4]);
            String type = component[5];

            //Legg til fler om det skal være flere typer

            Product prod = new Product(id,name,numberOfProducts,brand,price,type);

            List<Product> compList = mappedComponents.get(type);

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

    public void write(Product product) throws IOException {  //Her blir det lagt til Product produkt, så jeg må endre csv-filen til å ha like mange attributter som produkt-metoden
        FileWriter pw = new FileWriter(csvFile, true);
        String products = product.toString();
        try {
            BOMreader.removeUTF8BOM(products);
            pw.write(products);
        }


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
