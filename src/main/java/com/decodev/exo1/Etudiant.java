package com.decodev.exo1;

import java.io.Serializable;

import java.io.Serializable;

/**
 *
 * @author Ce PC
 */
public class Etudiant implements Serializable {
    String nom;
    String specialite;
    int moy;
    public Etudiant (String nom, String specialite, int moy) {
        this.nom = nom;
        this.specialite = specialite;
        this.moy = moy;
    }
    public String getNom() {
        return nom;
    }
    public String getSpecialite() {
        return specialite;
    }
    public String toString() {
        return "Etudiant : "+nom+"   "+specialite+" : "+moy;
    }

}
