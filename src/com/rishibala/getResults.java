package com.rishibala;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

class getResults {

    public static List<Integer> acceptableDNFStatus = new ArrayList<>(Arrays.asList(2, 5, 6, 7, 8, 9, 10, 21, 23, 24, 25, 26, 28, 30, 27, 29));

    private static final List<driver> allDrivers = getDrivers.getDrivers();

    static List<driver> rankAll() {

        System.out.println(allDrivers.size() + " drivers:" + allDrivers);
        for(int year = 1950; year <= 2024; year++) {
            int rounds = 0;

            try {
                URL obj = new URL("http://ergast.com/api/f1/" + year);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in1 = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine1;
                    StringBuilder response1 = new StringBuilder();

                    while ((inputLine1 = in1.readLine()) != null) {
                        response1.append(inputLine1);
                    }
                    in1.close();

                    String res = response1.toString();
                    int startInd = res.indexOf("total=");
                    if(year == 2024) {
                        rounds = 9;
                    } else if(res.charAt(startInd+8) != '"') {
                        rounds = Integer.parseInt(res.substring(startInd+7, startInd+9));
                    } else {
                        rounds = Integer.parseInt(res.substring(startInd+7, startInd+8));
                    }

                    System.out.println("Getting results for year " + year);
                    for (int j=1; j<=rounds; j++) {
                        try {
                            String newURL = "http://ergast.com/api/f1/" + year + "/" + j + "/results";

                            URL url = new URL(newURL);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");

                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder response = new StringBuilder();
                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                            }
                            in.close();

                            //parse XML
                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = factory.newDocumentBuilder();
                            Document doc = builder.parse(new InputSource(new StringReader(response.toString())));
                            doc.getDocumentElement().normalize();

                            NodeList raceNodes = doc.getElementsByTagName("Race");
                            String raceName = "";
                            if (raceNodes.getLength() > 0) {
                                Element raceElement = (Element) raceNodes.item(0);
                                raceName = raceElement.getElementsByTagName("RaceName").item(0).getTextContent();
                            }

                            NodeList resultNodes = doc.getElementsByTagName("Result");

                            List<driver> drivers = new ArrayList<>();
                            List<String> constructors = new ArrayList<>();
                            List<Integer> statusIds = new ArrayList<>();

                            for (int i = 0; i < resultNodes.getLength(); i++) {
                                Node resultNode = resultNodes.item(i);

                                if (resultNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element resultElement = (Element) resultNode;

//                                    String position = resultElement.getAttribute("position");
                                    Element driverElement = (Element) resultElement.getElementsByTagName("Driver").item(0);
                                    String givenName = driverElement.getElementsByTagName("GivenName").item(0).getTextContent();
                                    String familyName = driverElement.getElementsByTagName("FamilyName").item(0).getTextContent();
                                    Element constructorElement = (Element) resultElement.getElementsByTagName("Constructor").item(0);
                                    String constructor = constructorElement.getElementsByTagName("Name").item(0).getTextContent();

                                    int statusId = Integer.parseInt(resultElement.getElementsByTagName("Status").item(0).getAttributes().getNamedItem("statusId").getTextContent());

                                    if (!allDrivers.contains(new driver(givenName, familyName))) {
                                        System.out.println("Could not find " + givenName + " " + familyName);
                                        System.out.println(allDrivers);
                                        System.out.println();
                                    }

                                    drivers.add(allDrivers.get(allDrivers.indexOf(new driver(givenName, familyName))));
                                    constructors.add(constructor);
                                    statusIds.add(statusId);
                                }
                            }

                            Map<String, List<driver>> constructorMap = new HashMap<>();

                            for (int i = 0; i < drivers.size(); i++) {
                                driver driver = drivers.get(i);
                                String constructor = constructors.get(i);
                                int status = statusIds.get(i);
                                if(acceptableDNFStatus.contains(status)) {
                                    continue;
                                }

                                if (!constructorMap.containsKey(constructor)) {
                                    constructorMap.put(constructor, new ArrayList<>());
                                }
                                constructorMap.get(constructor).add(driver);
                            }

                            for (Map.Entry<String, List<driver>> entry : constructorMap.entrySet()) {
                                List<driver> drivers1 = entry.getValue();

                                elo.calculateElo(drivers1);

                                for(driver drive : drivers) {
                                    drive.increaseCountedRaces();

                                    if(drive.getCountedRaces() < 10) {
                                        continue;
                                    }

                                    if((drive.getElo() > drive.getHighestElo()) || (drive.getCountedRaces() == 10)) {
                                        drive.setHighestElo(drive.getElo());
                                        drive.setHighestEloRound(j);
                                        drive.setHighestEloYear(year);
                                        drive.setHighestEloRace(raceName);
                                    }
                                }

                            }

                        } catch(Exception e) {
                            e.printStackTrace();
                        }

                    }


                } else {
                    throw new RuntimeException("GET request failed: " + responseCode);
                }

            } catch (IOException e) {
                System.out.println(e);
            }

        }

        return allDrivers;
    }

}
