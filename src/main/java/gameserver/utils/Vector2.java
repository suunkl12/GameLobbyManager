/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver.utils;

/**
 *
 * @author Khang
 */
public class Vector2 {

    public volatile float x = 0;
    public volatile float y = 0;

    public Vector2(){}

    public Vector2(float x, float y){

        this.x = x;
        this.y = y;

    }

}