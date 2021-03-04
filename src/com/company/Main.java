package com.company;


import java.util.Random;

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 380};
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static String[] heroesAttackType = {"Physycal",
            "Magical", "Kinetic", "Medic"};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;


    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("The game is stared");

        while (!isGameFinished()) {
            round();
        }
    }

    public static void medicHealth() {

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                Random rando = new Random();
                int random = rando.nextInt(80);
                heroesHealth[i] = heroesHealth[i] + random;
                System.out.println("medic performance " + heroesAttackType[i] + "by " + random + " help");
                break;

            }

        }

    }

    public static void round() {
        roundNumber++;
        System.out.println("______ROUND - " + roundNumber
                + "________");
        bossDefenceType = changeBossDefence();
        System.out.println("Hero " + bossDefenceType + " Boss receive super damage");
        bossHits();
        //medicHealth();
        heroesHits();
        printStatistics();

    }

    public static boolean isGameFinished() {
//        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
//                && heroesHealth[2] <= 0){
//            System.out.println("Boss won");
//            return true;
//        } // Hard Code

        if (bossHealth <= 0) {
            System.out.println("Heroes Won");
            return true;
        }

        boolean allHeroesDead = true;

        for (int hero : heroesHealth) {
            if (hero > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won");
        }

        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            }
        }
    }

    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(8) + 2; //2,3,4,5,6,7,8,9,10
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Super damage " + heroesDamage[i]
                            * coeff + " [" + coeff + "]");
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
                if (bossHealth < 0) {
                    bossHealth = 0;
                }
            }
        }
    }

    public static String changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        return heroesAttackType[randomIndex];
    }

    public static void printStatistics() {
        System.out.println("Boss health: " + bossHealth +
                " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i] +
                    " [" + heroesDamage[i] + "]");
        }
    }
}
