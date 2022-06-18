package com.billboard;

import java.time.Duration;

public class DisplayedAdvertisement {
    private String advertText;
    private Duration displayPeriod;

    public DisplayedAdvertisement(String advertText, Duration displayPeriod) {
        this.advertText = advertText;
        this.displayPeriod = displayPeriod;
    }

    public String getAdvertText(){
        return this.advertText;
    }

    public Duration getDisplayPeriod(){
        return this.displayPeriod;
    }

    public void setDisplayPeriod(Duration period){
        this.displayPeriod = period;
    }
}

