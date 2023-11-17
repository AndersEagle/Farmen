package org.example;

import java.util.ArrayList;

public class Crop extends Entity {

      private String type;
      private int quantity;

      private ArrayList<Animal> animal = new ArrayList<>();

      private static int nextCropId = 1;

      // Konstruktorn
      public Crop(String type, int quantity) {
         super(nextCropId, type);
         nextCropId++;
         this.type = type;
         this.quantity = quantity;
      }

      // Denna konstruktorn används när vi skapar nya crops
      public Crop(int id, String type, int quantity) {
         super(id, type);
         nextCropId = id + 1;
         this.type = type;
         this.quantity = quantity;

      }

      public String getCSV() {
         return id + "," + type + "," + quantity; }

      public void printInfo() {
         System.out.println(id + "." + " " + type + ".  Återstår " + quantity + " matningar.");

      }

      // Start Getters and Setters

      public String getType() {
         return type; }

      public int getQuantity() {
         return quantity; }

      public void setQuantity(int quantity) {
         this.quantity = quantity; }

      // End Getters & Setters

      @Override
      public String getDescription() {

          return "Mat " + super.getDescription() + ", Sort: " + type + ", Mängd: " + quantity;
      }

      public int getId() {
         // System.out.println("ID=" + id);
         return id;
      }

      public void feedAnimal(Animal animal) {
            if (quantity > 0) {
               quantity--;
               animal.add(animal);
               System.out.println();
               System.out.println(animal.getSpecies() + " matat med " + type + ". Kvarvarande mängd: " + quantity);
            } else {
               System.out.println();
               System.out.println("Inte tillräckligt med " + type + " att mata " + animal.getSpecies());
               System.out.println();
            }
      }

}

