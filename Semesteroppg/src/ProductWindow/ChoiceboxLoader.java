package ProductWindow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceboxLoader {
    private String[] componentNames = new String[]{"Kabinett", "Prosessor"};

    public ChoiceboxLoader(){
        load();
    }


    private void load(){  //Metode for å laste inn csv-data, og "mappe" dataen.

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
                if (isFirstLine){
                    isFirstLine = false;
                    continue;
                }

                System.out.println(currentLine);
                String[] component = currentLine.split(cvsSplitBy);
                componentData.add(component);
            }

            createChoiceBoxes(componentData);

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

    }

    private void createChoiceBoxes(List<String[]> componentData) {  //Metode for å laste inn komponenter
        Map<String, List<BaseComponent>> mappedComponents = new HashMap<>();

        for (String[] component : componentData){
            String id = component[0];
            String name = component[1];
            double price = Double.parseDouble(component[2]);
            String type = component[3];

            BaseComponent comp = new BaseComponent(id, name, price, type);


            String mapId = type.toLowerCase();
            List<BaseComponent> compList = mappedComponents.get(mapId);

            if(compList == null){
                compList = new ArrayList<>();
            }

            compList.add(comp);

            mappedComponents.put(type, compList);
        }


        String idToLookFor = "hei";  //Legg denne inn i add metoden. Den sjekker om prodID eksisterer fra før av.
        boolean containsId = false;

        for (String key : mappedComponents.keySet()){
            List<BaseComponent> comps = mappedComponents.get(key);
            containsId = comps.stream().anyMatch(baseComp -> baseComp.getId().equals(idToLookFor));
            if(containsId){
                break;
            }
        }

        System.out.println("Contains id: " + idToLookFor +": " + containsId);
    }
}
