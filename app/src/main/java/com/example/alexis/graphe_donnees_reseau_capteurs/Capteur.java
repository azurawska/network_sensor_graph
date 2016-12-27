package com.example.alexis.graphe_donnees_reseau_capteurs;

/**
 * Created by alexis on 30/11/2016.
 */
public class Capteur {

    private int id;
    private double x;
    private double y;
    private double z;
    private String rimeAdress;
    private String ipAdress;
    private int time;
    private double deviationFactor;
    private double cpu;

    public Capteur(int id, double x, double y, double z, String rimeAdress, String ipAdress, int time, double deviationFactor, double cpu) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.rimeAdress = rimeAdress;
        this.ipAdress = ipAdress;
        this.time = time;
        this.deviationFactor = deviationFactor;
        this.cpu = cpu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getRimeAdress() {
        return rimeAdress;
    }

    public void setRimeAdress(String rimeAdress) {
        this.rimeAdress = rimeAdress;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDeviationFactor() {
        return deviationFactor;
    }

    public void setDeviationFactor(double deviationFactor) {
        this.deviationFactor = deviationFactor;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Capteur capteur = (Capteur) o;

        if (id != capteur.id) return false;
        if (Double.compare(capteur.x, x) != 0) return false;
        if (Double.compare(capteur.y, y) != 0) return false;
        if (Double.compare(capteur.z, z) != 0) return false;
        if (time != capteur.time) return false;
        if (Double.compare(capteur.deviationFactor, deviationFactor) != 0) return false;
        if (Double.compare(capteur.cpu, cpu) != 0) return false;
        if (!rimeAdress.equals(capteur.rimeAdress)) return false;
        return ipAdress.equals(capteur.ipAdress);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + rimeAdress.hashCode();
        result = 31 * result + ipAdress.hashCode();
        result = 31 * result + time;
        temp = Double.doubleToLongBits(deviationFactor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cpu);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Capteur{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", rimeAdress='" + rimeAdress + '\'' +
                ", ipAdress='" + ipAdress + '\'' +
                ", time=" + time +
                ", deviationFactor=" + deviationFactor +
                ", cpu=" + cpu +
                '}';
    }
}