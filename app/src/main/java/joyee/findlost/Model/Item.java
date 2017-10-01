package joyee.findlost.Model;

import java.io.Serializable;

/**
 * Created by arunkumar on 30/09/17.
 */

public class Item implements Serializable{

    long itemId;
    String itemName;
    String ItemDetail;
    String itemFor;
    String Location;
    String itemType;
    String itemImageUri;
    long itemFoundLostDate;
    long itemCreatedDate;
    long itemModifiedDate;
    int userId;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetail() {
        return ItemDetail;
    }

    public void setItemDetail(String itemDetail) {
        ItemDetail = itemDetail;
    }


    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemImageUri() {
        return itemImageUri;
    }

    public void setItemImageUri(String itemImageUri) {
        this.itemImageUri = itemImageUri;
    }


    public long getItemCreatedDate() {
        return itemCreatedDate;
    }

    public void setItemCreatedDate(long itemCreatedDate) {
        this.itemCreatedDate = itemCreatedDate;
    }

    public long getItemModifiedDate() {
        return itemModifiedDate;
    }

    public void setItemModifiedDate(long itemModifiedDate) {
        this.itemModifiedDate = itemModifiedDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getItemFor() {
        return itemFor;
    }

    public void setItemFor(String itemFor) {
        this.itemFor = itemFor;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public long getItemFoundLostDate() {
        return itemFoundLostDate;
    }

    public void setItemFoundLostDate(long itemFoundLostDate) {
        this.itemFoundLostDate = itemFoundLostDate;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
