package com.example;

/**
 *
 * @author isami
 */
public enum Town {
    
    STOCKHOLM(59.334591, 18.063240),
    GOTHENBURG(57.708870, 11.974560),
    MALMO(55.60587, 13.00073);

    private final double latitude;
    private final double longitude;
    
    private Town(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    };
    
    public double latitude(){
        return latitude;
    }
    public double longitude(){
        return longitude;
    }
}
