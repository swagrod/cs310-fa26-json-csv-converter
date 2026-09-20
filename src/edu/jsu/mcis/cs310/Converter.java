package edu.jsu.mcis.cs310;

import com.github.cliftonlabs.json_simple.*;
import com.opencsv.*;

import java.io.*;
import java.util.*;

public class Converter {
    
    /*
        
        Consider the following CSV data, a portion of a database of episodes of
        the classic "Star Trek" television series:
        
        "ProdNum","Title","Season","Episode","Stardate","OriginalAirdate","RemasteredAirdate"
        "6149-02","Where No Man Has Gone Before","1","01","1312.4 - 1313.8","9/22/1966","1/20/2007"
        "6149-03","The Corbomite Maneuver","1","02","1512.2 - 1514.1","11/10/1966","12/9/2006"
        
        (For brevity, only the header row plus the first two episodes are shown
        in this sample.)
    
        The corresponding JSON data would be similar to the following; tabs and
        other whitespace have been added for clarity.  Note the curly braces,
        square brackets, and double-quotes!  These indicate which values should
        be encoded as strings and which values should be encoded as integers, as
        well as the overall structure of the data:
        
        {
            "ProdNums": [
                "6149-02",
                "6149-03"
            ],
            "ColHeadings": [
                "ProdNum",
                "Title",
                "Season",
                "Episode",
                "Stardate",
                "OriginalAirdate",
                "RemasteredAirdate"
            ],
            "Data": [
                [
                    "Where No Man Has Gone Before",
                    1,
                    1,
                    "1312.4 - 1313.8",
                    "9/22/1966",
                    "1/20/2007"
                ],
                [
                    "The Corbomite Maneuver",
                    1,
                    2,
                    "1512.2 - 1514.1",
                    "11/10/1966",
                    "12/9/2006"
                ]
            ]
        }
        
        Your task for this program is to complete the two conversion methods in
        this class, "csvToJson()" and "jsonToCsv()", so that the CSV data shown
        above can be converted to JSON format, and vice-versa.  Both methods
        should return the converted data as strings, but the strings do not need
        to include the newlines and whitespace shown in the examples; again,
        this whitespace has been added only for clarity.
        
        NOTE: YOU SHOULD NOT WRITE ANY CODE WHICH MANUALLY COMPOSES THE OUTPUT
        STRINGS!!!  Leave ALL string conversion to the two data conversion
        libraries we have discussed, OpenCSV and json-simple.  See the "Data
        Exchange" lecture notes for more details, including examples.
        
    */
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
        
        String result = "{}"; // default return value; replace later!
        
        try {
        
            //This will read the data for scanner
           CSVReader Scanner = new CSVReader(new StringReader(csvString));
           
           // This read the frist row in the heading
           String[] headings = Scanner.readNext();
           
           // These will be the list to store everything
            
           List <String> prodNums = new ArrayList();
           List <String> colHeadings = new ArrayList();
           List <List<Object>> data = new ArrayList<>();
           
           // this is to add the colum to the header list
           
           for (String heading : headings){
               colHeadings.add(heading);
           }
           
           //Read each of the rest of the rows
           String[] row;
           
           while((row = Scanner.readNext()) != null){
               // Production number
               prodNums.add(row[0]);
               
               //episode data for the list
               List<Object> episode = new ArrayList();
               
               //Convert seasons and episode into intergers
               for(int i = 1; i < row.length; i++){
                    if(i == 2 || i ==3){
                        episode.add(Integer.parseInt(row[i]));
                    }
                    
                    else{
                        episode.add(row[i]);
                    }
                    
                    data.add(episode);
           }
               
               //Json object
               JsonObject json = new JsonObject();
                       
               json.put("Prodnums", prodNums);
               json.put("ColHeadings", colHeadings);
               json.put("Dara", data);
               
               //Converting the JSON
               result = Jsoner.serialize(json);
               
               Scanner.close();
           }
           
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return result.trim();
        
    }
    
    @SuppressWarnings("unchecked")
    public static String jsonToCsv(String jsonString) {
        
        String result = ""; // default return value; replace later!
        
        try {
            
            //JSON Strings in three parts
            JosnObject json = (JsonObject) Jsoner.deserialize(jsonString);
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return result.trim();
        
    }
    
}
