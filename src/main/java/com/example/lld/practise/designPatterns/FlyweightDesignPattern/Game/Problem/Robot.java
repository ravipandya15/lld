package com.example.lld.practise.designPatterns.FlyweightDesignPattern.Game.Problem;

public class Robot {
    int coordinateX;
    int coordinateY;
    String type;
    Sprites body; // small 2d bitmap (graphic element)

    Robot(int x, int y, String type, Sprites body) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.type = type;
        this.body = body;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sprites getBody() {
        return body;
    }

    public void setBody(Sprites body) {
        this.body = body;
    }
}
