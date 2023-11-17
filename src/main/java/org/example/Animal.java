package org.example;

import java.util.ArrayList;

public class Animal extends Entity {
   public String species;
   private ArrayList<Crop> crops = new ArrayList<>();

   private static int nextAnimalId = 1;

   public Animal(String species) {
      super(nextAnimalId, species);
      nextAnimalId++;
      this.species = species;
   }

   public Animal(int id, String species) {
      super(id, species);
      nextAnimalId = id + 1;
      this.species = species;
   }

   public String getCSV() {
      return id + "," + species +"," + crops;
   }

   public void printCrop() {
      if (crops.size() == 0) {
         System.out.println();
         System.out.println("Det är inte angivet vilken mat som detta djur äter.");
         System.out.println();
         return;
      }

      System.out.println();
      System.out.println("Följande mat äts av " + species);
      System.out.println();
      for (Crop crop: crops) {
         crop.printInfo();
         System.out.println();
      }
   }
   public void printInfo() {
      System.out.println(id + "." + " " + species);
   }

   public void addCrop(Crop crop) { crops.add(crop); }

   public String getSpecies() {
      return species;
   }

   @Override
   public String getDescription() {
      // Implementera beskrivning för Animal här
      return "ID: " + nextAnimalId() + ", Djur: " + species;
   }

   private String nextAnimalId() {
      return nextAnimalId();
   }

   public void add(Animal animal) {
   }

   public int getId() {
      return id;
   }
}


