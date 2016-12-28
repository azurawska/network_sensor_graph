package com.example.alexis.graphe_donnees_reseau_capteurs;

/**
 * Created by alexis on 28/12/2016.
 */
public class Liaison {

    private int id_liaison;
    private int id_emetteur;
    private int id_receveur;

    public Liaison(int id_liaison, int id_emetteur, int id_receveur) {
        this.id_liaison = id_liaison;
        this.id_emetteur = id_emetteur;
        this.id_receveur = id_receveur;
    }

    public int getId_liaison() {
        return id_liaison;
    }

    public void setId_liaison(int id_liaison) {
        this.id_liaison = id_liaison;
    }

    public int getId_emetteur() {
        return id_emetteur;
    }

    public void setId_emetteur(int id_emetteur) {
        this.id_emetteur = id_emetteur;
    }

    public int getId_receveur() {
        return id_receveur;
    }

    public void setId_receveur(int id_receveur) {
        this.id_receveur = id_receveur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Liaison liaison = (Liaison) o;

        if (id_liaison != liaison.id_liaison) return false;
        if (id_emetteur != liaison.id_emetteur) return false;
        return id_receveur == liaison.id_receveur;

    }

    @Override
    public int hashCode() {
        int result = id_liaison;
        result = 31 * result + id_emetteur;
        result = 31 * result + id_receveur;
        return result;
    }

    @Override
    public String toString() {
        return "Liaison{" +
                "id_liaison=" + id_liaison +
                ", id_emetteur=" + id_emetteur +
                ", id_receveur=" + id_receveur +
                '}';
    }
}