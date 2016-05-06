/*
** Description: 15 Puzzle
** Date: 10 MAR 2016
** Author: Andrii Grytsenko
** Programing Language: Java
*/ 

package ua.kiev.agrit.games.j15puzzle;

import java.util.*;

/**
 *
 * @author Andrii Grytsenko
 */
class J15puzzleCore {
    private final int PUZZLE_SIZE;              // Size of game fileld: PUZZLE_SIZE * PUZZLE_SIZE
    private final int EMPTY_NUMBER;             // Number that will be tritted as an Empty space
    private ArrayList<Integer> places;          // List of places with numbers of draughts
    private ArrayList<Integer> movablePlaces;   // Places from where draught can be moved to empty place
    private int freePlace;                      // ID of place that is an empty now
    private int movements;                      // Movements were made from the start of the game
    
    /**
     * Creates a puzzle game of puzzleSize * puzzleSize places
     * @param puzzleSize - int, size of puzzle game
     * @param emtpyNumber - int, number between 0 and (puzzleSize * puzzleSize - 1) which represents an empty place
     */
    public J15puzzleCore(int puzzleSize, int emptyNumber) {
        PUZZLE_SIZE = puzzleSize;
        EMPTY_NUMBER = emptyNumber;
        places = new ArrayList<>();
        for (int i = 0; i < PUZZLE_SIZE*PUZZLE_SIZE; i++) {
            places.add(i);
        }
        movablePlaces = new ArrayList<>();
        movablePlaces.clear();
        freePlace = PUZZLE_SIZE*PUZZLE_SIZE - 1;
        movements = 0;
        updateMovablePlaces();
    } // J15puzzleCore

    /**
     * Creates a classic 15 puzzle game (4*4)
     */
    public J15puzzleCore() { this(4, 15); } // J15puzzleCore
    
    /**
     * Returns number of place for specified row and column
     * @param row - int, row
     * @param col - int, column
     * @return - int, place number
     */
    private int getID(int row, int col) { return (row*(PUZZLE_SIZE) + col); } // getID

    /**
     * Returns row number for specified place
     * @param id - int, place number
     * @return - int, row
     */
    private int getRow(int id) { return (id / PUZZLE_SIZE); } // getRow

    /**
     * Returns column number for specified place
     * @param id - int, place number
     * @return - int, column
     */
    private int getCol(int id) { return (id % PUZZLE_SIZE); } // getCol

    /**
     * Fills list movablePlaces with numbers of places from where is it possible to move draught to an empty place
     */
    private void updateMovablePlaces() {
        movablePlaces.clear();
        
        if (getRow(freePlace) > 0) {
            movablePlaces.add(getID(getRow(freePlace)-1, getCol(freePlace)));
        }
        if (getRow(freePlace) < PUZZLE_SIZE-1) {
            movablePlaces.add(getID(getRow(freePlace)+1, getCol(freePlace)));
        }
        if (getCol(freePlace) > 0) {
            movablePlaces.add(getID(getRow(freePlace), getCol(freePlace)-1));
        }
        if (getCol(freePlace) < PUZZLE_SIZE-1) {
            movablePlaces.add(getID(getRow(freePlace), getCol(freePlace)+1));
        }
    } // updateMovablePlaces

    /**
     * Returns a value of movements were made from the beginning of the game
     * @return - int, movements
     */
    public int getMovements() { return movements; } // getMovements
    
    /**
     * Returns places, that is number of draughts in each place
     * @return - ArrayList<Integer>, places
     */
    public ArrayList<Integer> getPlaces() { return places; } // getPlaces
    
    /**
     * Checks if all draught are at the right places
     * Implemented only one way to check it: draughts should be arranged from 0 to PUZZLE_SIZE*PUZZLE_SIZE-1
     * If so returns true, otherwise returns false
     * @return - boolean, true if draughts are ordered
     */
    public boolean isOrdered() {
        boolean ordered = true;
        for (int i = 0; i < places.size(); i++) {
            if (ordered) {
                ordered = (places.get(i) == i);
            }
        }
        return ordered;
    } // isOrdered

    /**
     * Checks if is it possible to move a draught from selected place to a free place.
     * @param id - int, number of place from where draught is intended to move
     * @return - boolean, true if move is possible, false otherwise
     */
    public boolean isMovable(int id) {
        boolean possible = false;
        //for (placeId in movablePlaces) {
        for (Integer movablePlace : movablePlaces) {
            if (! possible) {
                possible = (movablePlace == id);
            }
        }
        return possible;
    } // isMovable

    /**
     * Moves draught from specified place to an empty place
     * After move it increases count of movements and update a list of places valid to make a next move
     * @param id - int, number of place from where drauhgt will be moved
     */
    //Description: Exchange IDs of draughts at places "ID of place" and iFreePlace (lstPlaces["ID of place"] <==> lstPlaces[iFreePlace].
    //After that change the ID of free place (iFreePlace = "ID of place") and fresh lstMovableDraughts.
    public void moveDraught(int id) {
        places.set(freePlace, places.get(id));
        places.set(id, EMPTY_NUMBER);
        freePlace = id;
        updateMovablePlaces();
        movements += 1;
    } // moveDraught

    /**
     * Shuffles draughts
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < (PUZZLE_SIZE*PUZZLE_SIZE*2); i++) {
            places.add(places.remove(random.nextInt(PUZZLE_SIZE*PUZZLE_SIZE)));
        }
    } // shuffle
    
    /**
     * Starts the new game: shuffles draugths and clears count of movements
     */
    public void startGame() {
        shuffle();
        movements = 0;
        freePlace = places.indexOf(EMPTY_NUMBER);
        updateMovablePlaces();
    } // startGame
}
