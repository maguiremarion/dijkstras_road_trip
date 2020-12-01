import java.util.*; 
import java.io.*; 
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class RoadTrip {
	
	public static Graph<String> UG = new Graph<>();
	public static String startingCity;
	public static String endingCity;
	public static ArrayList<String> attractionsAsCities;
	public static String finalRoute = "";

	public static ArrayList<String> route(String starting_city, String ending_city, String[] attractions){
		ArrayList<String> citiesPassedThrough = new ArrayList<String>();
		System.out.println("\nAlgorithm running. Please standby...");
	
       	Djikstras<String> djikstras = new Djikstras<>();
       	
      	for (int i=0; i < attractionsAsCities.size(); i++) {
      		if(i == 0) {
      			Set<String> temp = djikstras.findShortestPath(UG, startingCity, attractionsAsCities.get(i));
      			for (String j : temp) {
      				citiesPassedThrough.add(j);
      			}
      		} else {
      			Set<String> temp = djikstras.findShortestPath(UG, attractionsAsCities.get(i-1), attractionsAsCities.get(i));
      			for (String j : temp) {
      				citiesPassedThrough.add(j);
      			}
      		}
      	}
      	Set<String> temp = djikstras.findShortestPath(UG, attractionsAsCities.get(attractionsAsCities.size()-1), endingCity);
      	for (String j : temp) {
				citiesPassedThrough.add(j);
      	}
      	
        LinkedHashSet<String> duplicatesRemoved = new LinkedHashSet<>(citiesPassedThrough);
        citiesPassedThrough = new ArrayList<>(duplicatesRemoved);
  
		return citiesPassedThrough;
	}

	public static ArrayList<String> convertAttractionsToCities(Map<String, String> map, String[] attractions){
		ArrayList<String> attractionsAsCities = new ArrayList<String>();

		for(int i=0; i<attractions.length; i++){
			if (map.get(attractions[i]) != null) {
				attractionsAsCities.add(map.get(attractions[i]));
			} else {
				System.out.println("\nAn error has occured (Error Code 2). This error occurs usually because one or more locations you entered does not exist or you did not seperate your attractions by ','\nPlease try again. \nA good example to try is: Eugene OR -> Alcatraz -> Las Vegas Strip -> Grand Canyon -> Phoenix AZ");
				System.exit(2);
			}
		}
		return attractionsAsCities;
	}

	
	public static void main(String [] args) throws IOException {
		
		HashMap<String,String> map = new HashMap<>();

		try(BufferedReader br = Files.newBufferedReader(Path.of(args[0]), StandardCharsets.UTF_8)){
			while(br.ready()) {
				String[] temp = (br.readLine()).split(",");
            	map.put(temp[0], temp[1]);
        	}
		}
       	
		try(BufferedReader br = Files.newBufferedReader(Path.of(args[1]), StandardCharsets.UTF_8)){
			while(br.ready()) {
				String[] temp = (br.readLine()).split(",");
				UG.addEdge(temp[0], temp[1], Integer.parseInt(temp[2]));
        	}
		}

		 Scanner input = new Scanner(System.in); 

		 System.out.println("Enter the city you would like to start at (i.e. Eugene OR):");
		 startingCity = input.nextLine();

		 System.out.println("Enter the attractions you would like pass through seperated by commas (i.e. Alcatraz, Las Vegas Strip, Grand Canyon):");
		 String attractionsString = input.nextLine();

		 System.out.println("Enter the city you would like to end at (i.e. Phoenix AZ):");
		 endingCity = input.nextLine();
		 
		 input.close();
	
		 String[] preattractions = attractionsString.split(",");
		 String[] attractions = new String[preattractions.length];
		 for (int i = 0; i < preattractions.length; i++)
			 attractions[i] = preattractions[i].trim();

       	attractionsAsCities = convertAttractionsToCities(map, attractions);

       	ArrayList<String> result = route(startingCity, endingCity, attractions);
       	
       	for(int j=0; j < result.size(); j++) {
       		String temp = result.get(j);
       		if (j != result.size()-1) {
       			finalRoute += (temp + " -> ");
       		}else {
       			finalRoute += (temp);
       		}
       	}
       	
       	System.out.println("The shortest path from " + startingCity + " to " + endingCity + " while passing through all of the desired attractions is:");
       	System.out.println(finalRoute);
	}
	
}