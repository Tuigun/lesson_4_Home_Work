package com.company;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 400,60,250,240,275};
    public static String[] heroesNames = {"Lu King", "Jax", "Scorpion", "Medic","Golem","Lucky","Berserk","Thor"};
    public static int[] heroesstrike = {20, 15, 25, 0,5,10,15,30};
    public static String bossName = "Shoe Khan";
    public static int bosshealth = 4800;
    public static int bossStrike = 50;
    public static String superStrike = "";
    public static int roundNumber = 0;


    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("--------The Game Started---------");

        while (!isGameFinished()) {
            round();
        }

    }

    public static void round() {
        roundNumber++;
        superStrike = getSuperStrikeHero();
        System.out.println("Super Strike Damage" + superStrike);
        System.out.println("-----Rount-" + roundNumber + "-----");
        System.out.println("Super Strike" + superStrike);
        bossHits();
        heroesHits();
        medicRole();
        golem();
        lucky();
        Thor();

        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bosshealth <= 0) {
            System.out.println("Heroes Won!!!" +
                    "Mortal Kombat Finished");
            return true;
        }

        boolean allHeroesDead = true;

        for (int heroHealth : heroesHealth) {
            if (heroHealth > 0) {
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead) {
            System.out.println(bossName + " Won!!!" + " Mortal Kombat Finished!!!");

        }
        return allHeroesDead;
    }

    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(9) + 2;
        for (int i = 0; i < heroesstrike.length; i++) {
            if (heroesHealth[i] > 0 && bosshealth > 0) {
                if (superStrike == heroesNames[i]) {
                    bosshealth = bosshealth - heroesstrike[i] * coeff;
                    System.out.println("Super Demage" + (heroesstrike[i] * coeff));

                } else {
                    bosshealth = bosshealth - heroesstrike[i];

                }

            }
            if (bosshealth < 0) {
                bosshealth = 0;
            }


        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bosshealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossStrike;
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }


        }

    }


    public static String getSuperStrikeHero() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesNames.length);
        return heroesNames[randomIndex];
    }


    public static void printStatistics() {
        System.out.println(bossName + " = health " + bosshealth + " strike [" + bossStrike + "]");
        for (int i = 0; i < heroesNames.length; i++) {
            System.out.println(heroesNames[i] + " = Health " + heroesHealth[i] +
                    " strike [" + heroesstrike[i] + "]");

        }
    }

    public static void medicRole(){
        Random random = new Random();
        int medicHeal = random.nextInt(20);
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 ){
                if (heroesHealth[3] != 0 && heroesHealth[i] != 0 && heroesHealth[i] != heroesHealth[3]){
                    heroesHealth[i] += medicHeal;
                    System.out.println("Medic helped to "+ heroesNames[i] +" "+ medicHeal);
                    break;

                }
            }
        }
    }

    public static void golem(){
        /*● Добавить n-го игрока, Golem,
        который имеет увеличенную жизнь но слабый удар.
         Может принимать на себя 1/5 часть урона исходящего от босса по другим игрокам.*/
        if (heroesHealth[4] > 0){
            heroesHealth[4] += (bossStrike-10);
            System.out.println(heroesHealth[4]+" 1/5 part of Boss strike "+ 10);
        }

    }
    public static void lucky(){
        Random random = new Random();
        int lucky = random.nextInt(3);
        if (lucky == 1){
            heroesHealth[5] = heroesHealth[5] + bossStrike;
            System.out.println(heroesNames[5] + "Run from bossStrike" + 50);
        }

    }
    public static void Berserk(){
        {
            bosshealth = bosshealth - 25;
            heroesHealth[6] = heroesHealth[6] + 25;

            System.out.println(heroesNames[6] + (" Block Himself and Counter strike to " + bossName + 25 ));
        }
    }
    public static void Thor(){
        Random randon = new Random();
        int Thor = randon.nextInt(4);
        if (Thor == 1){
            heroesstrike[7] = heroesstrike[7] + bosshealth;
            bossStrike = 0;
            System.out.println("Boss not Demage heroes");
        } else {bossStrike = 50;}
    }



}

