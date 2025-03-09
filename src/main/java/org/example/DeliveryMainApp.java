package org.example;

import java.util.List;
import java.util.Map;

    public class DeliveryMainApp {
        public static void main(String[] args) {
            LogisticApp logisticApp = new LogisticApp();
            List<Package> processedPackages = logisticApp.processPackage("data.txt");
            Map<String, List<Package>> groupedPackages = logisticApp.countPackages(processedPackages);
            logisticApp.deliver(groupedPackages);
            System.out.println("Deliveries completed");
        }
    }

