/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.io.IOException;

/**
 *
 * @author mflorent
 */
class ExceptionInvalidFile extends IOException {
    public ExceptionInvalidFile(String msg){
        super(msg);
    }
}
