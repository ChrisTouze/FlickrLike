package com.example.chris.flickr;

/**
 * Created by Chris on 25/11/2016.
 */

public class PhotoDto {
    private String id;
    private String owner;
    private String secret;
    private String server;
    private int farm;
    private String title;
    private int ispublic;
    private int isfriend;
    private int isfamily;

    public PhotoDto(String id, String owner, String secret, String server, int farm, String title, int ispublic, int isfriend, int isfamily) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoDto photoDto = (PhotoDto) o;

        if (farm != photoDto.farm) return false;
        if (ispublic != photoDto.ispublic) return false;
        if (isfriend != photoDto.isfriend) return false;
        if (isfamily != photoDto.isfamily) return false;
        if (id != null ? !id.equals(photoDto.id) : photoDto.id != null) return false;
        if (owner != null ? !owner.equals(photoDto.owner) : photoDto.owner != null) return false;
        if (secret != null ? !secret.equals(photoDto.secret) : photoDto.secret != null)
            return false;
        if (server != null ? !server.equals(photoDto.server) : photoDto.server != null)
            return false;
        return title != null ? title.equals(photoDto.title) : photoDto.title == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        result = 31 * result + (server != null ? server.hashCode() : 0);
        result = 31 * result + farm;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + ispublic;
        result = 31 * result + isfriend;
        result = 31 * result + isfamily;
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

   /* public String buildThumbsUrl(){
        String newUrl="https://farm"+farm+".static.flickr.com/"+server+"/"+id+"_"+secret+"_s.jpg";
        return newUrl;
    }
    public String buildPictureUrl(){
        String newUrl="https://farm"+farm+".static.flickr.com/"+server+"/"+id+"_"+secret+".jpg";
        return newUrl;
    }*/

}
