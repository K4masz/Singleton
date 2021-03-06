import Casino.Casino;
import Players.BlackJackPlayer;
import Players.SlotMachinePlayer;

import java.util.Scanner;

/**
 * Created by K4masz on 2017-10-22.
 */
public class Main {
    public static void main(String[] args) {

        Casino casino = Casino.getInstance(10000);
        Scanner input = new Scanner(System.in);

        System.out.print(casino.toString());

        System.out.print("\nWelcome to Casino Simulator!\nWhat's your name?\n> ");
        String name = input.nextLine();
        System.out.print("Hi, " + name + "!\nHow much money do you want deposit?\n> ");
        int money = input.nextInt();
        System.out.print("Ok, done. What would you like to play?\n1)SlotMachine\n2)BlackJack\n> ");
        int choice = input.nextInt();

        switch (choice) {
            case 1: {
                casino.addPlayer(new SlotMachinePlayer(name, money));
                break;
            }
            case 2: {
                casino.addPlayer(new BlackJackPlayer(name, money));
                break;
            }
        }


        boolean end = false;
        while (end == false) {
            System.out.println(casino.getPlayer(name).toString());
            System.out.print("Ok, how much money you want to bid?\n> ");
            casino.getPlayer(name).play(input.nextInt());
            System.out.print("Play again(1), Change game(2) or Exit?(3)\n> ");


            int menu = input.nextInt();
            if (menu == 1)
                continue;
            else if (menu == 2) {
                if (casino.getPlayer(name) instanceof BlackJackPlayer) {
                    int tempMoney = casino.getPlayer(name).getCashAmmount();
                    casino.removePlayer(casino.getPlayer(name).getName());
                    casino.addPlayer(new SlotMachinePlayer(name, tempMoney));
                } else {
                    int tempMoney = casino.getPlayer(name).getCashAmmount();
                    casino.removePlayer(casino.getPlayer(name).getName());
                    casino.addPlayer(new BlackJackPlayer(name, tempMoney));
                }
                continue;
            } else if (menu == 3) {
                System.out.println("Thank you for using Casino Simulator. Bye :)");
                break;
            }
        }
        input.close();
    }
}

