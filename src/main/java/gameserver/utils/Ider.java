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
import java.util.ArrayList;
import java.util.List;

public class Ider{

    private List<Integer> dump = new ArrayList<>();
    private Integer id = 0;

    public Integer next(){

        if (dump.size() > 0){

            int i = dump.get(0);
            dump.remove(0);
            return i;

        }

        id++;
        return id - 1;

    }

    
    //return the ID back to the pool
    public void returnBackID(Integer i){
        if (id < i) return;

        dump.add(i);
    }

}
