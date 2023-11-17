package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Farm {

    ArrayList<Crop> cropList = new ArrayList<>();
    ArrayList<Animal> animalList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    File cropFile = new File("crops.txt");
    File animalFile = new File("animals.txt");

    public Farm() {

        
        try {
            FileReader fr = new FileReader(cropFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] variables = line.split(",");

                int id = Integer.parseInt(variables[0]);
                String type = variables[1];
                int quantity = Integer.parseInt(variables[2]);

                Crop crop = new Crop(id, type, quantity);
                cropList.add(crop);

                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Fel vid inläsning");
        }


        try {
            FileReader fr = new FileReader(animalFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] variables = line.split(",");

                int id = Integer.parseInt(variables[0]);
                String species = variables[1];

                Animal animal = new Animal(id, species);
                animalList.add(animal);

                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Fel vid inläsning!");
        }
    }

    public void mainMenu() {
        boolean continuing = true;
        while (continuing) {
            System.out.println();
            System.out.println("MENY FÖR DJUR OCH MAT");
            System.out.println();
            System.out.println("1. Se all mat.");
            System.out.println("2. Se alla djur.");
            System.out.println("3. Lägg till mat.");
            System.out.println("4. Lägg till djur.");
            System.out.println("5. Lägg till vilken mat ett djur äter.");
            System.out.println("6. Se vilken mat som äts av ett visst djur.");
            System.out.println("7. Ta bort någon mat.");
            System.out.println("8. Ta bort ett djur.");
            System.out.println("9. Mata djur.");
            System.out.println("10. Spara.");
            System.out.println("11. Avsluta.");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    listCrop();
                    break;
                case "2":
                    listAnimal();
                    break;
                case "3":
                    addCrop();
                    break;
                case "4":
                    addAnimal();
                    break;
                case "5":
                    addCropToAnimal();
                    break;
                case "6":
                    seeAnimalEatingWhatCrop();
                    break;
                case "7":
                    removeCrop();
                    break;
                case "8":
                    removeAnimal();
                    break;
                case "9":
                    feedAnimal();
                    break;
                case "10":
                    save();
                    break;
                case "11":
                    continuing = false;
                    break;
                default:
                    break;
            }

        }

    }

    // Nr.1 i Menu
    private void listCrop() {
        for (Crop crop: cropList) {
            crop.printInfo();
        }
    }

    // Nr.2 in Menu
    private void listAnimal() {
        for(Animal animal: animalList) {
            animal.printInfo();
        }
    }

    // Nr.3 in Menu
    private void addCrop() {
        try {
            System.out.println();
            System.out.println("Ange ny mat till listan.");
            String type = scanner.nextLine();
            System.out.println();
            System.out.print("Ange mängd: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Crop c = new Crop (type, quantity);
            cropList.add(c);
        }
        catch (Exception e) {
            System.out.println("Fel! Mängd måste vara ett nummer.");
        }
    }

    // Nr.4 in Menu
    private void addAnimal() {
        try {
            System.out.println();
            System.out.println("Lägg till ett nytt djur.");
            String species = scanner.nextLine();

            Animal e = new Animal(species);
            animalList.add(e);
        }
        catch (Exception e) {
            System.out.println();
            System.out.println("Fel! Ange en siffra.");
        }
    }

    // Nr. 5 in Menu
    private void addCropToAnimal() {
        System.out.println();
        System.out.println("Välj någon mat, ange dess ID-nummer.");
        listCrop();
        try {
            String idInput = scanner.nextLine();
            int cropId = Integer.parseInt(idInput);
            Crop crop = getCropById(cropId);

            if (crop == null) {
                System.out.println();
                System.out.println("Det ID-numret finns inte.");
                return;
            }
            System.out.println();
            System.out.println("Välj ett djur, ange dess ID-nummer");
            listAnimal();
            idInput = scanner.nextLine();
            int animalId = Integer.parseInt(idInput);

            Animal animal = getAnimalById(animalId);

            if (animal == null) {
                System.out.println();
                System.out.println("Det ID-numret finns inte.");
                return;
            }

            animal.addCrop(crop);
            System.out.println();
            System.out.println(animal.getName() + " äter " + crop.getName() + ".");
        }
        catch (Exception e) {
            System.out.println();
            System.out.println("Vänligen ange ett ID-nummer.");
        }
    }

    // Nr. 6 in Menu
    private void seeAnimalEatingWhatCrop() {
        Animal animal = pickAnimal();
        if (animal == null) {
            System.out.println();
            System.out.println("Det ID-numret finns inte.");
            return;
        }

        animal.printCrop();
    }

    private Animal pickAnimal() {
        try {
            System.out.println();
            System.out.println("Välj ett djur, ange dess ID-nummer.");
            System.out.println();
            listAnimal();
            System.out.println();
            String idInput = scanner.nextLine();
            int animalId = Integer.parseInt(idInput);
            Animal animal = getAnimalById(animalId);
            return animal;
        }
        catch (Exception e) {
            System.out.println("Fel! Inte ett nummer. Försök igen.");
            return null;
        }
    }

    // Nr.7 in Menu
    private void removeCrop() {

        try {
            System.out.println();
            System.out.println("Välj ID't för den mat du vill ta bort:");

            // Visa lista på all mat som finns tillgänglig
            listCrop();

            int selectedId = Integer.parseInt(scanner.nextLine());

            Iterator<Crop> iterator = cropList.iterator();
            while (iterator.hasNext()) {
                Crop crop = iterator.next();
                if (crop.getId() == selectedId) {
                    iterator.remove();
                    System.out.println();
                    System.out.println("Mat med ID't " + selectedId + " togs bort från listan.");
                    return;
                }
            }


            System.out.println();
            System.out.println("Mat med valt ID fanns inte.");
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Felaktigt ID-format! Vänligen ange ett giltigt värde.");
        } catch (Exception e) {
            System.out.println();
            System.out.println("Ett oväntat fel inträffade!.");
        }
    }

    // Hjälpfunktion att hitta mat via ID
    private Crop findCropById (int cropId){
        for (Crop crop : cropList) {
            if (crop.getId() == cropId) {
                return crop;
            }
        }
        return null;
    }

    // Nr.8 in Menu
    private void removeAnimal() {

        try {
            System.out.println();
            System.out.println("Ange ID för det djur du önskar ta bort.");

            // Display the list of animals with their IDs
            listAnimal();

            int selectedId = Integer.parseInt(scanner.nextLine());

            Iterator<Animal> iterator = animalList.iterator();
            while (iterator.hasNext()) {
                Animal animal = iterator.next();
                if (animal.getId() == selectedId) {
                    iterator.remove();
                    System.out.println("Djur med ID " + selectedId + " har blivit raderat.");
                    return;
                }
            }

            System.out.println("Djur med valt ID finns inte.");
        }   catch(NumberFormatException e){
            System.out.println("Felaktigt ID-format. Vänligen ange ID igen!");
        } catch(Exception e){
            System.out.println("Ett oväntat fel uppstod!");
        }
    }

    // Hjälpfunktion att hitta djur via ID
    private Animal findAnimalById (int animalId){
        for (Animal animal : animalList) {
            if (animal.getId() == animalId) {
                return animal;
            }
        }
        return null;
    }

    // Nr. 9 in Menu
    private void feedAnimal() {
        try {
            // Visa listan på olika slags mat
            System.out.println();
            System.out.println("Välj vilken mat du vill mata ditt djur med.");
            listCrop();

            System.out.println();
            System.out.print("Ange matens ID: ");
            int cropId = Integer.parseInt(scanner.nextLine());

            // Visa listan på olika slags djur
            System.out.println();
            System.out.println("Välj vilket djur du vill mata.");
            listAnimal();

            System.out.println();
            System.out.print("Ange ID't för det djuret: ");
            int animalId = Integer.parseInt(scanner.nextLine());

            // Hitta vald mat och djur
            Crop selectedCrop = findCropById(cropId);
            Animal selectedAnimal = findAnimalById(animalId);

            if (selectedCrop != null && selectedAnimal != null) {
                int requiredQuantity = 1; // Assuming you want to feed 1 unit of crop to the animal

                if (selectedCrop.getQuantity() >= requiredQuantity) {
                    // Det finns tillräckligt med mat för att mata djuret
                    selectedCrop.feedAnimal(selectedAnimal);
                } else {
                    // MATEN ÄR SLUT!
                    System.out.println();
                    System.out.println("Otillräckligt med " + selectedCrop.getType() + ". Lägger till mer ...");

                    // Maten fylls på med 10 måltider automatiskt
                    selectedCrop.setQuantity(selectedCrop.getQuantity() + 5);

                    System.out.println();
                    System.out.println("Lagt till mer " + selectedCrop.getType() + ". Matat " + selectedAnimal.getSpecies());
                    selectedCrop.feedAnimal(selectedAnimal);
                }
            } else {
                System.out.println();
                System.out.println("Felaktigt ID för mat eller djur. Försök igen!");
            }
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Felaktigt ID-format! Vänligen ange ett tillåtet värde.");
        } catch (Exception e) {
            System.out.println();
            System.out.println("Ett oväntat fel uppstod!");
        }
    }

    // Nr.10 in Menu
    private void save() {
        try {
            FileWriter fw = new FileWriter(cropFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < cropList.size(); i++) {
                bw.write(cropList.get(i).getCSV());
                if (i < cropList.size() - 1) {
                    bw.newLine();
                }
            }
            bw.close();

            fw = new FileWriter(animalFile);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < animalList.size(); i++) {
                bw.write(animalList.get(i).getCSV());
                if (i < animalList.size() - 1) {
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Crop getCropById(int id) {
        for(Crop crop : cropList) {
            if(crop.getId() == id) {
                return crop;
            }
        }
        return null;
    }

    private Animal getAnimalById(int Id) {
        for(int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            if (animal.getId() == Id) {
                return animal;
            }
        }
        return null;
    }
}




