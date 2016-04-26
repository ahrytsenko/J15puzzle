/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.agrit.java.j15puzzle;

/**
 *
 * @author ahrytsenko
 */
public class J15puzzleCoreTest {
    
    public static void main(String args[]) {

        J15puzzleCore j15puzzleCore = new J15puzzleCore();

        j15puzzleCore.shuffle();
        System.out.println(j15puzzleCore.isOrdered());
        for (Integer place : j15puzzleCore.getPlaces()) {
            System.out.println(place);
        }
        
 
    }
    
}
