/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica0;

/**
 * Clase que describe al paciente, que será el objeto en nuestras implementaciones.
 * Cada paciente tiene nombre, padecimiento y con este se le asigna una prioriad.
 */

import java.util.*;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String padecimiento;
    private int prioridad;
    private static int contador=0;
    private int id;
   
    /**
     * Constructor del Paciente donde ponemos nombre, padecimiento, asignamos una prioridad y mantenemos un registro de qué número de paciente es.
     * @param nombre
     * @param padecimiento 
     */
    public Paciente(java.lang.String nombre, java.lang.String padecimiento) {
        this.nombre=nombre;
        this.padecimiento=padecimiento;
        this.prioridad=asignaPrioridad(padecimiento);
        this.id=contador;
        
        contador++;
    }
    //Geters
    public String getNombre() {
        return nombre;
    } 
    
    public String getPadecimeinto() {
        return padecimiento;
    }
    
    public int getPrioridad() {
        return prioridad;
    }
    
    //Setters: cambiamos tanto padecimiento como prioridad porque dentro del hopsital pueden cambiar ambas.
    public void setPadecimiento(String padecimiento) {
        this.padecimiento=padecimiento;
    }
    
    public void setPrioridad(int prioridad) {
        this.prioridad=prioridad;
    }
    
    /**
     * Asigna una prioridad del 1-10 de acuerdo a su padecimiento.
     * @param padecimiento
     * @return 
     */
    public int asignaPrioridad(String padecimiento) {
        int prior=0;
        switch(padecimiento) {
            case "Cardiorespiratorio": 
                prior=1;
                break;
            case "Cardiovascular": 
                prior=2;
                break;
            case "Neurologico":
                prior=3;
                break;
            case "Traumatico":
                prior=4;
                break;
            case "Abdominal": 
                prior=5;
                break;
            case "Metabolico":
                prior=6;
                break;
            case "Ortopedico":
                prior=7;
                break;
            case "Infeccioso":
                prior=8;
                break;
            case "Gastrointestinal":
                prior=9;
                break;
            case "Respiratorio":
                prior=10;
                break;
        }
        return prior;
    }
    
    /**
     * Genera una cadena con nombre, padecimiento, prioridad y id para cada paciente.  
     * @return 
     */
    @Override
    public String toString() {
        return "\nPaciente " +id + "\n Nombre:  " + nombre + "\n Padecimiento:  " + padecimiento + "\n Prioridad:  " + prioridad;
    }
    /**
     * Compara un paciente con otro.
     * @param obj
     * @return true si son iguales, false si son diferentes
     */
    @Override
    public boolean equals(Object obj) {
        boolean resp=false;
        if(obj instanceof Paciente) {
            Paciente other = (Paciente) obj;
            if (this.prioridad == other.prioridad && this.nombre.equals(other.nombre) && this.padecimiento.equals(other.padecimiento)) {
                resp=true;
            }        
        }
        return resp;
    }
    
    /**
     * Compara la prioridad de los pacientes.
     * @param otro
     * @return -1 si el primero tiene menor prioridad (un número más alto), 1 si el primero tiene mayor prioridad, número más chico
     */
    public int compareTo(Paciente otro) {
        int resp=0;
        if(this.prioridad > otro.prioridad)
            resp=-1;                                
        else
            if(this.prioridad < otro.prioridad)
                resp=1;                             
        return resp;
    }
    
    public int compareTo2(Paciente otro) {
        return Integer.compare(this.prioridad, otro.prioridad);
    }
    
}
