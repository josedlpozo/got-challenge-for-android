package es.npatarino.android.gotchallenge.model;

/**
 * Created by josedelpozo on 28/4/16.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolás Patarino on 21/02/16.
 */
public class GoTHouse {


    String houseImageUrl;
    String houseName;
    String houseId;

    public String getHouseImageUrl() {
        return houseImageUrl;
    }

    public void setHouseImageUrl(String houseImageUrl) {
        this.houseImageUrl = houseImageUrl;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
}
