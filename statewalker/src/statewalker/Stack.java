/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statewalker;

/**
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public interface Stack<E> {

    boolean isEmpty();

    /**
     * Get the top element.
     *
     * @return the top element
     * @throws ArrayIndexOutOfBoundsException on empty stack.
     */
    E peek();

    /**
     * Get and remove element
     *
     * @return the top element
     * @throws RuntimeException if peek in the same state of this stack.
     * @see #peek()
     */
    E pop();

    void push( E e );
    
}