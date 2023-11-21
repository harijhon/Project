/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myException;

/**
 *
 * @author RazOnTheFloor
 */
public class ElectionException extends Exception {
    String error;
    public ElectionException(String error){
        this.error = error;
    }
}
