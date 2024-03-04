package com.decodev.additional_part2;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class PatientServer {

    public static void main(String[] args) {
        HashMap<Integer, Patient> patients = initializePatients();

            try (ServerSocket serverSocket = new ServerSocket(7777)) {
                while (true) {
                    try (Socket socket = serverSocket.accept();
                         DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                         DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

                        System.out.println("Client connected");
                        Integer id;
                        while (true) {
                            
                            try{
                                id = inputStream.readInt();
                            }catch(EOFException e){
                                System.out.println("Client disconnected");
                                break;
                            }
                            if(id == 0){
                                break;
                            }
                            System.out.println("Client -> Server: " + id);
                            Patient patient = patients.get(id);
                            if (patient == null) {
                                outputStream.writeUTF("Patient not found");
                            } else {
                                int triggerCount = countTriggers(patient);
                                String riskLevel = getRiskLevel(patient, triggerCount);
                                outputStream.writeUTF(riskLevel);
                            }
                        }
                    }
                }
            } catch (BindException e) {
                System.out.println("Connection refused. Address already in use");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    private static HashMap<Integer, Patient> initializePatients() {
        HashMap<Integer, Patient> patients = new HashMap<>();
        patients.put(1, new Patient(1,"femme",57,new String[]{"Le patient déclare qu'il se sent très bien. Poids égal ou inférieur au poids recommandé."}));

        patients.put(2, new Patient(2,"homme",78,new String[]{"Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement.", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois. Il remarque également que son audition continue d'être anormale."}));

        patients.put(3, new Patient(3,"homme", 19,new String[]{"Le patient déclare qu'il fume depuis peu.","Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière. Il se plaint également de crises d’apnée respiratoire anormales. Tests de laboratoire indiquant un taux de cholestérol LDL élevé.",}));

        patients.put(4, new Patient(4,"femme",21,new String[]{"Le patient déclare qu'il lui est devenu difficile de monter les escaliers. Il se plaint également d’être essoufflé. Tests de laboratoire indiquant que les anticorps sont élevés. Réaction aux médicaments.","Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps.","Le patient déclare qu'il a commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé. Taille, Poids, Cholestérol, Vertige et Réaction.", }));
        
        return patients;
    }

    private static int countTriggers(Patient patient) {
        String[] triggers = {
                "Hémoglobine A1C",
                "Microalbumine",
                "Taille",
                "Poids",
                "Fumeur",
                // "fumeur",
                // "fume",
                "fumer",
                "Fumeuse",
                "anormale",
                "anormales",
                "cholestérol",
                "Cholestérol",
                "Vertiges",
                "Rechute",
                "Réaction",
                "réaction",
                "Anticorps",
                "anticorps",
        };
        int triggerCount = 0;
        String patientNotes = patient.getNotesAsString();
        for (String trigger : triggers) {
            if (patientNotes.contains(trigger)) {
                triggerCount++;
            }
        }
        return triggerCount;
    }


private static String getRiskLevel(Patient patient, int triggerCount) {
        if (triggerCount == 1) {
                return "The patient has " + triggerCount + " trigger -> no risk (None)";
        } else if (isBorderlineRisk(triggerCount, patient)) {
                return "The patient has " + triggerCount + " triggers -> borderline risk (Borderline)";
        } else if (isInDanger(triggerCount, patient)) {
                return "The patient has " + triggerCount + " triggers -> in danger (In Danger)";
        } else if (isEarlyOnset(triggerCount, patient)) {
                return "The patient has " + triggerCount + " triggers -> early onset (Early onset)";
        } else {
                return "The patient has " + triggerCount + " triggers -> unknown risk (Unknown risk)";
        }
}

private static boolean isBorderlineRisk(int triggerCount, Patient patient) {
        return (triggerCount >= 2 && triggerCount <= 5) && patient.getAge() >= 30;
}

private static boolean isInDanger(int triggerCount, Patient patient) {
        return (triggerCount == 3 && patient.getGender().equals("homme") && patient.getAge() < 30) ||
                        (triggerCount == 4 && patient.getGender().equals("femme") && patient.getAge() < 30) ||
                        ((triggerCount == 6 || triggerCount == 7) && patient.getAge() > 30);
}

private static boolean isEarlyOnset(int triggerCount, Patient patient) {
        return (triggerCount >= 5 && patient.getGender().equals("homme") && patient.getAge() < 30) ||
                        (triggerCount >= 7 && patient.getGender().equals("femme") && patient.getAge() < 30) ||
                        (triggerCount >= 8 && patient.getAge() > 30);

}
}
