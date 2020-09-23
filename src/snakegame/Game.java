/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author user
 */
class Game {
    final private Scanner scan = new Scanner(System.in);
    public void run(){
        while (true){
            Snake snake = new Snake();
            Apple apple = new Apple(snake);
            snake.draw();
            apple.draw();
            if (apple.isColideSnake()){
                snake.addBody(0, 0);
                apple.teleport();
            }
            if (snake.isColideItself() ||
                    snake.body[0].x > 15 || 
                    snake.body[0].x < 0 || 
                    snake.body[0].y > 15 || 
                    snake.body[0].y < 0){
                die();
            }
        }
    }
    public int listen(){
        ;
    }
    public void die(){
        ;
    }
}

class Snake {
    public int snakelength = 0;
    public Body [] body = new Body[256];
    public char direction;
    public boolean isDying;
    public boolean newbody;
    public void addBody(int x, int y){
        body[this.snakelength] = new Body(x,y);
    }
    public boolean isColideItself(){
        for (int i = 1 ;i < this.snakelength; i++){
            if ((body[i].x == body[0].x) && (body[i].y == body[0].y)){
                return true;
            }
        }
        return false;
    }
    public void draw(){
        for (int i = 0; i < this.snakelength; i++){
            body[i].draw();
        }
    }
    public void move(){
        int x = this.body[0].x;
        int y = this.body[0].y;
        switch (this.direction) {
            case 'U':
                this.body[0].y -=1;
                break;
            case 'D':
                this.body[0].y +=1;
                break;
            case 'L':
                this.body[0].x +=1;
                break;
            case 'R':
                this.body[0].x -=1;
                break;
            default:
                break;
        }
        for (int i = 1; i < this.body.length; i++) {
            int a;
            a = x;
            x = this.body[i].x;
            this.body[i].x = a;

            a = y;
            y = this.body[i].y;
            this.body[i].y = a;
        }
        if ((this.body[0].x < 0) || (this.body[0].x > 16) || (this.body[0].y < 0) || (this.body[0].y > 16)){
            	this.isDying = true;
        }
        this.isDying = this.isColideItself();
        if (this.newbody){
            this.body[this.snakelength+1] = (new Body(x,y));
            this.newbody = false;
        }
    }
}
class Body{
    public int x,y;
    Body(int x, int y){
        this.x = x;
        this.y = y;
    }

    void draw() {
        ;
    }
}
class Apple{
    final private Random rand = new Random();
    public int x,y;
    public Snake a;
    Apple(Snake a){
        this.a = a;
    }
    public void teleport(){
        while (true){
            int col = 0;
            this.x = rand.nextInt();
            this.y = rand.nextInt();
            for(int i = 0; i < this.a.snakelength; i++){
                if (( a.body[i].x == this.x) && (a.body[i].y == this.y)){
                    col++;
                }
            }
            if (col == 0){
                break;
            }
        }
    }
    public boolean isColideSnake(){
        for (int i = 1 ;i < this.a.snakelength; i++){
            if (( a.body[i].x == this.x) && (a.body[i].y == this.y)){
                return true;
            }
        }
        return false;
    }
    public void draw(){
        ;
    }
}
