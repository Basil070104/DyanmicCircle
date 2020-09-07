import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

import javax.swing.*;

public class DynamicCircle implements Directions {

    public void start(){
        int radius = 0;
        JFrame f = new JFrame();
        //Limitation: only support till single digit decimal
        String answer = JOptionPane.showInputDialog(f,"What is the radius of your dynamic circle(Scale 1:10): ");
        try{
            radius = (int)(Float.parseFloat(answer) * 10);
            System.out.println("Valid Input!");
        }catch (NumberFormatException e){
            System.out.println("Invalid Input.");
        }

        int worldX = makeWorld(radius);
        int worldY = makeWorld(radius);

        Robot circle = new Robot(1,1,East,1000);
        World.setVisible(true);
        World.setSize(worldX,worldY);
        World.setDelay(1);

        makeCenter(circle, radius);
        circle.putBeeper();
        makeCircle(circle,radius);

    }
    public void makeCircle(Robot robot, int radius){
        int moveY;
        int xCoordinate = 0;

        while(xCoordinate < radius){
            moveY = moveY(radius,xCoordinate);
            for(int j = 0; j < moveY; j++){
                robot.move();
            }
            robot.putBeeper();
            robot.turnLeft();
            robot.turnLeft();
            for(int k = 0; k < 2 * moveY; k++){
                robot.move();
            }
            robot.putBeeper();
            robot.turnLeft();
            robot.turnLeft();
            for(int j = 0; j < moveY; j++){
                robot.move();
            }
            turnRight(robot);
            robot.move();
            xCoordinate++;
            robot.turnLeft();
        }
        makeEndOfRad(robot,radius);
        for(int i = 0; i < radius; i++){
            robot.move();
        }
        turnRight(robot);
        xCoordinate = 0;
        while(xCoordinate < radius){
            moveY = moveY(radius,xCoordinate);
            for(int j = 0; j < moveY ; j++){
                robot.move();
            }
            robot.putBeeper();
            robot.turnLeft();
            robot.turnLeft();
            for(int k = 0; k < 2 * moveY; k++){
                robot.move();
            }
            robot.putBeeper();
            robot.turnLeft();
            robot.turnLeft();
            for(int j = 0; j < moveY; j++){
                robot.move();
            }
            robot.turnLeft();
            robot.move();
            xCoordinate++;
            turnRight(robot);
        }
        makeEndOfRad(robot,radius);
        turnRight(robot);
        robot.move();
        turnRight(robot);
        for(int i = 0; i < radius; i++){
            robot.move();
        }
        turnRight(robot);
        robot.move();
    }
    public static int moveY(int radius, int x){
        int y = (int) Math.round(Math.sqrt((Math.pow(radius,2)) - (Math.pow(x,2))));
        return y;
    }

    //Assumed already facing East
    public void makeCenter(Robot bot,int r) {
        for(int i = 0; i < r; i++){
            bot.move();
        }
        bot.turnLeft();
        for(int j = 0; j < r; j++){
            bot.move();
        }

    }
    public void makeEndOfRad(Robot robot, int radius){
        int yOff = (Math.round((radius/4) + 1));
        if (yOff % 2 == 0) {
            yOff = yOff -1;
        }

        for(int i = 0; i < yOff ; i++){
            robot.move();
        }
        robot.putBeeper();
        turnBack(robot);
        for(int i = 0; i < (yOff * 2); i++){
            robot.move();
            robot.putBeeper();
        }
        turnBack(robot);
        for(int i = 0; i < yOff ; i++){
            robot.move();
        }
        robot.turnLeft();
    }
    public void turnRight(Robot bot){
        bot.turnLeft();
        bot.turnLeft();
        bot.turnLeft();
    }
    public void turnBack(Robot bot){
        bot.turnLeft();
        bot.turnLeft();
    }
    public static int makeWorld(int radius){
        int world = 2*(radius + 1);
        return world;
    }

}
