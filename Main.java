package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static StringBuilder printField(String[] cellsArr) {
        StringBuilder result = new StringBuilder();
        result.append("---------\n");
        int nextLine = 0;
        for (int i = 0; i < cellsArr.length; i++) {
            nextLine++;
            if (nextLine == 1) {
                result.append("| ");
            }
            result.append(cellsArr[i]).append(" ");
            if (nextLine == 3) {
                result.append("|\n");
                nextLine = 0;
            }
        }
        result.append("---------");
        return result;
    }

    public static void main(String[] args) {
        String cells = "         ";
        String[] cellsArr = cells.split("");
        System.out.println(printField(cellsArr));

        cells = cells.replaceAll("_", "0");
        cells = cells.replaceAll(" ", "0");
        cells = cells.replaceAll("X", "1");
        cells = cells.replaceAll("O", "2");
        String[] cellsArr2 = cells.split("");
        int[] inputs = new int[9];
        int count = 0;
        for (String str : cellsArr2) {
            inputs[count] = Integer.parseInt(str);
            count++;
        }

        boolean gameOn = true;

        int pl = 1;

        while (gameOn) {
            Scanner scanner2 = new Scanner(System.in);
            boolean scnOk = false;
            System.out.print("Enter the coordinates: ");
            String scan2 = scanner2.nextLine();
            int x = 0;
            int y = 0;

            while (!scnOk) {
                if (!scan2.replaceAll(" ", "").matches("\\d+")) {
                    System.out.println("You should enter numbers!");
                    System.out.print("Enter the coordinates: ");
                    scan2 = scanner2.nextLine();
                    continue;
                }
                String[] checkStr = scan2.split(" ");
                x = Integer.parseInt(checkStr[0]);
                y = Integer.parseInt(checkStr[1]);
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    System.out.print("Enter the coordinates: ");
                    scan2 = scanner2.nextLine();
                } else if (inputs[getCoordsLocation(y, x)] > 0) {
                    System.out.println("This cell is occupied! Choose another one!");
                    System.out.print("Enter the coordinates: ");
                    scan2 = scanner2.nextLine();
                } else {
                    inputs[getCoordsLocation(y, x)] = pl;
                    scnOk = true;
                }
            }

            String cells3 = Arrays.toString(inputs).replaceAll("\\[", "")
                    .replaceAll("]", "")
                    .replaceAll(",", "")
                    .replaceAll(" ", "");
            cells3 = cells3.replaceAll("0", " ");
            cells3 = cells3.replaceAll("1", "X");
            cells3 = cells3.replaceAll("2", "O");
            String[] cellsArr3 = cells3.split("");
            System.out.println(printField(cellsArr3));

            if (pl == 2) {
                pl = 1;
            } else {
                pl = 2;
            }

            // check game conditions

            if (checkWin(inputs, 1)) {
                System.out.println("X wins");
                gameOn = false;
                continue;
            } else if (checkWin(inputs, 2)) {
                System.out.println("O wins");
                gameOn = false;
                continue;
            }

            if (checkDraw(inputs)) {
                System.out.println("Draw");
                gameOn = false;
            }

        }

    }

    public static boolean checkWin(int[] inputs, int player) {
        if (inputs[0] == inputs[1] && inputs[1] == inputs[2] && inputs[2] == player) {
            return true;
        }
        if (inputs[3] == inputs[4] && inputs[4] == inputs[5] && inputs[5] == player) {
            return true;
        }
        if (inputs[6] == inputs[7] && inputs[7] == inputs[8] && inputs[8] == player) {
            return true;
        }
        if (inputs[0] == inputs[3] && inputs[3] == inputs[6] && inputs[6] == player) {
            return true;
        }
        if (inputs[1] == inputs[4] && inputs[4] == inputs[7] && inputs[7] == player) {
            return true;
        }
        if (inputs[2] == inputs[5] && inputs[5] == inputs[8] && inputs[8] == player) {
            return true;
        }
        if (inputs[0] == inputs[4] && inputs[4] == inputs[8] && inputs[8] == player) {
            return true;
        }
        if (inputs[2] == inputs[4] && inputs[4] == inputs[6] && inputs[6] == player) {
            return true;
        }
        return false;
    }

    public static boolean checkDraw(int[] inputs) {
        int checkedFields = 0;
        for (int input : inputs) {
            if (input > 0) {
                checkedFields++;
            }
        }
        return checkedFields > 8;
    }

    public static int getCoordsLocation(int x, int y) {
        x--;
        y--;
        int cordsLocation = 0;
        if (x == 1 && y == 0) {
            cordsLocation = 1;
        }
        if (x == 2 && y == 0) {
            cordsLocation = 2;
        }
        if (x == 0 && y == 1) {
            cordsLocation = 3;
        }
        if (x == 1 && y == 1) {
            cordsLocation = 4;
        }
        if (x == 2 && y == 1) {
            cordsLocation = 5;
        }
        if (x == 0 && y == 2) {
            cordsLocation = 6;
        }
        if (x == 1 && y == 2) {
            cordsLocation = 7;
        }
        if (x == 2 && y == 2) {
            cordsLocation = 8;
        }
        return cordsLocation;
    }

}
