import java.util.Scanner;
import java.util.Random;


//the character class (player and enemy)
class Character {
  //initialises the variables
  String ability1;
  String ability2;
  String ability3;
  String ability4;
  double health;
  double damage;

  //sets up characters stats
  public Character(String chosenClass, String[][] classes) {
    if (chosenClass.equals("WARRIOR")) {
      ability1 = classes[0][3];
      ability2 = classes[0][4];
      ability3 = classes[0][5];
      ability4 = classes[0][6];
      health = Double.parseDouble(classes[0][1]);
      damage = Double.parseDouble(classes[0][2]);
    }
      
    else if (chosenClass.equals("MAGE")) {
      ability1 = classes[1][3];
      ability2 = classes[1][4];
      ability3 = classes[1][5];
      ability4 = classes[1][6];
      health = Double.parseDouble(classes[1][1]);
      damage = Double.parseDouble(classes[1][2]);
    }
      
    else if (chosenClass.equals("ROGUE")) {
      ability1 = classes[2][3];
      ability2 = classes[2][4];
      ability3 = classes[2][5];
      ability4 = classes[2][6];
      health = Double.parseDouble(classes[2][1]);
      damage = Double.parseDouble(classes[2][2]);
    }
  }
}

public class Main {
  public static void main(String[] args) {
    String chosenClass = "";
    //class name, health, damage, 4 abilities
    String[][] classes = {{"Warrior", "100.00", "10.00", "Slash", "Shield Bash", "Consecutive Strikes", "Excalibur Thrust"}, 
                          {"Mage", "80.00", "20.00", "Fireball", "Ice Blast", "Lightning Strike", "Divine Ray"}, 
                          {"Rougue", "90.00", "15.00", "Stab", "Backstab", "Arial Strike", "Vitality Slash"}};

    //player decides their class
    System.out.println("__CLASSES__ \n---------- \n[WARRIOR] \n[MAGE] \n[ROGUE]");
    System.out.println("----------");
    Scanner input = new Scanner(System.in);
    Boolean valid = false;
    while (valid == false) {
      System.out.print("Choose your class: ");
      chosenClass = input.nextLine();
      if (chosenClass.equals("WARRIOR") || chosenClass.equals("MAGE") || chosenClass.equals("ROGUE")) {
        valid = true;
      }
    }
    Character character = new Character(chosenClass, classes);

    //outputs player stats
    System.out.println("----------");
    System.out.println("You have chosen the " + chosenClass + " class.");
    System.out.println("----------");
    System.out.println("__CHARACTER ATTRIBUTES__\n----------");
    System.out.println("Health: " +  character.health + "\nDamage: " + character.damage);
    System.out.println("Abilities: " + character.ability1 + ", " + character.ability2 + ", " + character.ability3 + ", " + character.ability4);

    //setting up enemies
    Character enemyWarrior = new Character("WARRIOR", classes);
    Character enemyMage = new Character("MAGE", classes);
    Character enemyRogue = new Character("ROGUE", classes);
    Random rand = new Random();
    int enemyNumber = rand.nextInt(3);
    System.out.println(enemyNumber);

    switch (enemyNumber) {
      case 0:
        battle(character, enemyWarrior, "WARRIOR");
        break;
        
      case 1:
        battle(character, enemyMage, "MAGE");
        break;
        
      case 2:
        battle(character, enemyRogue, "ROGUE");
        break;
    }
    
  }
  static void battle(Character player, Character enemy, String enemyClass) {
    String ability = "";
    double damage = 0.0;
    int playerCounter2 = 0;
    int playerCounter3 = 0;
    int playerCounter4 = 0;
    int enemyCounter2 = 0;
    int enemyCounter3 = 0;
    int enemyCounter4 = 0;
    Random rand = new Random();
    String winner;
    
    System.out.println("----------");
    System.out.println("You enter the arena and see a " + enemyClass+ " in the distance.");

    //begins the combat loop
    while (player.health > 0 && enemy.health > 0) {
      if (player.health < 0) {
        player.health = 0;
      }

      if (enemy.health < 0) {
        enemy.health = 0;
      }
      System.out.println("__YOUR MOVE__");
      System.out.println("----------");
      System.out.println("Your Health: " + player.health);
      System.out.println("Enemy Health: " + enemy.health + "\n");

      //player chooses their move
      System.out.println("Choose An Ability:\n");
      System.out.println("1. " + player.ability1 + "[0]");
      System.out.println("2. " + player.ability2 + "[" + playerCounter2 + "]");
      System.out.println("3. " + player.ability3 + "[" + playerCounter3 + "]");
      System.out.println("4. " + player.ability4 + "[" + playerCounter4 + "]");
      System.out.print("Chosen Ability: ");
      Scanner input = new Scanner(System.in);
      Boolean abilityChosen = false;
      
      while (abilityChosen == false) {
        ability = input.nextLine(); 
        //calculates the damage of the ability
        if (ability.equals("1")) {
          damage = player.damage;
          abilityChosen = true;
          if (playerCounter2 > 0) {
            playerCounter2--;
          }
        
          if (playerCounter3 > 0) {
            playerCounter3--;
          }

          if (playerCounter4 > 0) {
          playerCounter4--;
          }
        }
        
        else if (ability.equals("2") && playerCounter2 < 1) {
          damage = player.damage * 1.1;
          playerCounter2 = 1;
          abilityChosen = true;
          if (playerCounter3 > 0) {
            playerCounter3--;
          }

          if (playerCounter4 > 0) {
          playerCounter4--;
          }
        }
        
        else if (ability.equals("3") && playerCounter3 < 1) {
          damage = player.damage * 1.4;
          playerCounter3 = 2;
          abilityChosen = true;
          if (playerCounter2 > 0) {
            playerCounter2--;
          }

          if (playerCounter4 > 0) {
            playerCounter4--;
          }
        }

        else if (ability.equals("4") && playerCounter4 < 1) {
          damage = player.damage * 2.0;
          playerCounter4 = 3;
          abilityChosen = true;
          if (playerCounter2 > 0) {
            playerCounter2--;
          }

          if (playerCounter3 > 0) {
            playerCounter3--;
          }
        }

        else {
          System.out.print("Please choose a valid ability: ");
        }
      }

      //outputs damage done
      enemy.health -= damage;
      if (player.health < 0) {
        player.health = 0;
      }
      
      if (enemy.health < 0) {
        enemy.health = 0;
      }
      
      System.out.println("\nYou used ability " + ability + " and dealt " + damage + " damage.");
      System.out.println("----------");

      if (enemy.health > 0) {
        System.out.println("__ENEMY MOVE__");
        System.out.println("----------");
        System.out.println("Your Health: " + player.health);
        System.out.println("Enemy Health: " + enemy.health);
      }
      abilityChosen = false;

      //calculates enemy damage
      while (abilityChosen == false) {
        int abilityNumber = rand.nextInt(4);
        switch (abilityNumber) {
          case 0:
            damage = enemy.damage;
            ability = enemy.ability1;
            abilityChosen = true;
            if (enemyCounter2 > 0) {
              enemyCounter2--;
            }

            if (enemyCounter3 > 0) {
              enemyCounter3--;
            }

            if (enemyCounter4 > 0) {
              enemyCounter4--;
            }
            
            break;

          case 1:
            if (enemyCounter2 < 1) {
              damage = enemy.damage * 1.1;
              ability = enemy.ability2;
              abilityChosen = true;

              if (enemyCounter3 > 0) {
                enemyCounter3--;
              }

              if (enemyCounter4 > 0) {
                enemyCounter4--;
              }
            }

            break;

          case 2:
            if (enemyCounter3 < 1) {
              damage = enemy.damage * 1.4;
              ability = enemy.ability3;
              abilityChosen = true;

              if (enemyCounter2 > 0) {
                enemyCounter2--;
              }

              if (enemyCounter4 > 0) {
                enemyCounter4--;
              }
            }

            break;

          case 3:
            if (enemyCounter4 < 1) {
              damage = enemy.damage * 2.0;
              ability = enemy.ability4;
              abilityChosen = true;

              if (enemyCounter2 > 0) {
                enemyCounter2--;
              }

              if (enemyCounter3 > 0) {
                enemyCounter3--;
              }
            }

            break;
        }
      } 
      //outputs damage done
      player.health -= damage;
      if (enemy.health > 0) {
        System.out.println("The enemy used " + ability + " and dealt " + damage + " damage.");
        System.out.println("----------");
      }
    }
    //tells the user whether they won or lost
    System.out.println("__COMBAT ENDED__");
    System.out.println("----------");
    if (enemy.health == 0) {
      System.out.println("With your oppenent defeated, you exit the arena, a victorious champion. Return sometime to fight again.");
    }

    else {
      System.out.println("With your own defeat, you exit the arena, a broken mess. Return sometime to fight again.");
    }
  }
}
