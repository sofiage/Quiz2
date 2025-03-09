package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


    public class LogisticApp {
        public List<Package> processPackage(String fileName) {

            List<Package> packageList = new ArrayList<>();
            BufferedReader reader = null;

            try {

                reader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length != 4) {
                        System.out.println("Invalid line format: " + line);
                    }
                    String location = parts[0];
                    int distance = Integer.parseInt(parts[1]);
                    int value = Integer.parseInt(parts[2]);
                    LocalDate deliveryDate = null;

                    try{
                        deliveryDate = LocalDate.parse(parts[3]);
                    } catch (DateTimeParseException e){
                        System.out.println("Invalid date format: " + parts[3]);
                    }
                    packageList.add(new Package(location, distance, value, deliveryDate));
                }


            } catch (IOException e) {
                System.out.println("File not found");
            }
            return packageList;
        }

        public Map<String, List<Package>> countPackages(List<Package> packages) {
            Map<String, List<Package>> groupedPackages = new HashMap<>();
            for (Package p : packages) {
                String key = p.location + "   " + p.deliveryDate;
                if (!groupedPackages.containsValue(key)) {
                    groupedPackages.put(key, new ArrayList<>());
                }
                groupedPackages.get(key).add(p);
            }
            return groupedPackages;
        }

        public void deliver(Map<String, List<Package>> packageToDeliver) {
            List<Thread> threadList = new ArrayList<>();
            for (String key : packageToDeliver.keySet()) {
                List<Package> packagesToDeliverList = packageToDeliver.get(key);
                Thread t = new Thread(() -> {
                    int distance = packagesToDeliverList.get(0).distance;
                    int totalValue = 0;
                    int revenue = distance * 1;
                    for (Package p : packagesToDeliverList) {
                        totalValue += p.value;
                    }
                    System.out.println("[Delivering to " + key + " | Value: " + totalValue + "| Revenue " + revenue + "]");
                });
                threadList.add(t);
                t.start();

                for (Thread thread : threadList) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }

            }

        }
    }


