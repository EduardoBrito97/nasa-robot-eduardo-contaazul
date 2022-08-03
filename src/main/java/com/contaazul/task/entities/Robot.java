package com.contaazul.task.entities;

public class Robot {
    private Integer coordinateX;
    private Integer coordinateY;
    private Integer angle;
    
    public Robot(){
        this.coordinateX = 0;
        this.coordinateY = 0;
        this.angle = 90; //Pointing north
    }

    public Integer getCoordinateX(){
        return coordinateX;
    }
    public Integer getCoordinateY(){
        return coordinateY;
    }
    public Integer getAngle(){
        return angle;
    }

    public String getPositionTuple() {
        return String.format("(%d, %d, %c)", coordinateX, coordinateY, getOrientation());
    }

    public char getOrientation() {
        updateAngle();

        int angleMod = angle / 90;
        switch(angleMod) {
            case 0:
                return 'E'; // Points right, 0º
            case 1:
                return 'N'; // Points top, 90º
            case 2:
                return 'W'; // Points left, 180º
            case 3:
                return 'S';
            default:
                throw new IllegalStateException("Angle should be between 0 and 360º!");
        }
    }

    private void updateAngle() {
        angle = angle % 360;
        if (angle < 0){
            angle += 360;
        }
    }

    public void setCoordinateX(Integer coordinateX){
        this.coordinateX = coordinateX;
    }
    public void setCoordinateY(Integer coordinateY){
        this.coordinateY = coordinateY;
    }
    public void setAngle(Integer angle){
        this.angle = angle;
    }
}
