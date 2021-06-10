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
public class GunSlot {
    private Vector2 gunPosition;
    private Boolean isUsed;

    public GunSlot(Vector2 gunPosition, Boolean isUsed) {
        this.gunPosition = gunPosition;
        this.isUsed = isUsed;
    }

    public Vector2 getGunPosition() {
        return gunPosition;
    }

    public void setGunPosition(Vector2 gunPosition) {
        this.gunPosition = gunPosition;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }
    
    
}
