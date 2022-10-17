package Connect_4_classes;

import java.util.Scanner;

public class Main {

    // Create an empty board
    public static char[][] board = new char[6][7];

    public static Player currentPlayer;
    public static Player opponent;
    public static boolean loop = true;

    public static void main(String[] args) {

        // Initialize game
        loop = true;
        emptyBoard();

        //Create players and assign bot or not
        Player p1 = new Player('X');
        Player p2 = new Player('O');
        currentPlayer = p1;
        opponent = p2;

        makePlayer(p1, "Player 1");
        makePlayer(p2, "Player 2");

        //Game Loop
        while (loop) {
            makeMove();
            //Check if Win
            if (isaWin()) {
                renderBoard();
                System.out.println(currentPlayer.name + " is the winner!");
                playAgain();
            }
            //Check if Tie
            if (isaTie()) {
                renderBoard();
                System.out.println("It is a tie!");
                playAgain();
            }
            //Change player
            currentPlayer = currentPlayer == p1 ? p2 : p1;
            opponent = opponent == p2 ? p1 : p2;
        }

    }

    // Player Making Method
    public static void makePlayer(Player p, String pnr){
        Scanner scan = new Scanner(System.in);
        botChecker(p, pnr);
        System.out.println(pnr + ": Assign your name!");
        p.name = scan.nextLine();
        System.out.println("Welcome " + p.name + "!");
    }
    // Bot Checking Method
    public static void botChecker(Player p, String pnr) {
        Scanner in = new Scanner(System.in);
        int type;
        try {
            do {
                System.out.println(pnr + ": What kind of player are you?\n" +
                        "1. Human\n" +
                        "2. Dumb Bot\n" +
                        "3. Smart Bot");
                    type = Integer.parseInt(in.nextLine());

                    switch (type) {
                        case 1 -> {
                            p.isDumbBot = false;
                            p.isSmartBot = false;
                        }
                        case 2 -> {
                            p.isDumbBot = true;
                            p.isSmartBot = false;
                        }
                        case 3 -> {
                            p.isDumbBot = false;
                            p.isSmartBot = true;
                        }
                        default -> System.out.println("Choose a valid option!");
                    }
                } while (type < 1 || type > 3);
        }catch(Exception ignore){
            System.out.println("Choose a valid option!");
            botChecker(p, pnr);
            }

    }

    // Move making method
    public static void makeMove() {
        int col;
 //       do {
            renderBoard();
            // Choosing a valid column
            if (currentPlayer.isDumbBot) {
                do {
                    col = dumbBotMove();
                }while (board[0][col] != ' ');
            } else if (currentPlayer.isSmartBot) {
                do{
                    col = smartBotMove();
                }while (board[0][col] != ' ');
            } else {
                do {
                    col = Input.integer("\nChoose a column: ", 1, 7) - 1;
                }while (board[0][col] != ' ');
            }
            // Is column full?
//        } while (board[0][col] != ' ');
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer.symbol;
                break;
            }
        }

    }

    //  Board rendering methods
    public static void renderBoard() {
        System.out.println("\n".repeat(60));
        System.out.println(currentPlayer.name + ":s (" + currentPlayer.symbol + ") turn\n");
        System.out.println("  1   2   3   4   5   6   7");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
    }

    public static void emptyBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = ' ';
            }
        }
    }

    //  Win determining method
    public static boolean isaWin() {
        char player = currentPlayer.symbol;
        if(ScanMEthods.scanVertical(player,4)[0] != -1){
            return true;
        } else if (ScanMEthods.scanHorizontal(player, 4)[0] != -1) {
            return true;
        } else if (ScanMEthods.scanDiagonal1(player,4)[0] != -1) {
            return true;
        } else if (ScanMEthods.scanDiagonal2(player, 4)[0] != -1) {
            return true;
        } else {
            return false;
        }
/*        //has the current player won?
        char player = currentPlayer.symbol;
        // Vertical wins
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == player &&
                        board[row + 1][col] == player &&
                        board[row + 2][col] == player &&
                        board[row + 3][col] == player) {
                    return true;
                }
            }
        }
        //Horizontal wins
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == player &&
                        board[row][col + 1] == player &&
                        board[row][col + 2] == player &&
                        board[row][col + 3] == player) {
                    return true;
                }
            }
        }
        //Diagonal wins
        for (int row = 0; row < (board.length - 3); row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == player &&
                        board[row + 1][col + 1] == player &&
                        board[row + 2][col + 2] == player &&
                        board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col] == player &&
                        board[row - 1][col + 1] == player &&
                        board[row - 2][col + 2] == player &&
                        board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }
        return false;*/
    }

    //  Tie determining method
    public static boolean isaTie() {
        if (isaWin()) {
            return false;
        }
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    //play again method
    public static void playAgain() {
        Scanner in = new Scanner(System.in);
        String yn;
        do {
            System.out.println("Play Again? (y/n)");
            yn = in.nextLine();
            if (yn.equals("n")) {
                loop = false;
            } else if (yn.equals("y")) {
                do {
                    System.out.println("Keep current players? (y/n)");
                    yn = in.nextLine();
                    if (yn.equals("n")) {
                        makePlayer(currentPlayer, "Player 1");
                        makePlayer(opponent, "Player 2");
                        emptyBoard();
                    } else if (yn.equals("y")) {
                        emptyBoard();
                    }
                } while (!"yn".contains(yn));
            }
        } while (!"yn".contains(yn));
    }

    //Bot move methods
    public static int dumbBotMove(){
        try{
            Thread.sleep(2000);
        }
        catch (Exception ignore){
        }
        return (int) (Math.random() * (6));
    }

    public static int smartBotMove() {
        try {
            Thread.sleep(2000);
        } catch (Exception ignore) {
        }
        //Winning play conditions
        char player = currentPlayer.symbol;
        char opp = opponent.symbol;
        char[] players= {player, opp};
        int play = (int) (Math.random() * (7));
        for (int i = 3; i > 1; i--){
            for (char playCheck: players) {
                if (ScanMEthods.scanVertical(playCheck, i)[0] != -1){
                    if(board[0][ScanMEthods.scanDiagonal1(playCheck, i)[1]] == ' ' &&
                             board[ScanMEthods.scanVertical(playCheck, i)[0] - i][ScanMEthods.scanVertical(playCheck, i)[1]] == ' '){
                        play = ScanMEthods.scanVertical(playCheck, i)[1];
                        return play;
                    }
                } else if (ScanMEthods.scanHorizontal(playCheck, i)[0] != -1) {
                    if(board[0][ScanMEthods.scanDiagonal1(playCheck, i)[1] + i] == ' ' &&
                            board[ScanMEthods.scanHorizontal(playCheck, i)[0]][ScanMEthods.scanHorizontal(playCheck, i)[1] + i] == ' ') {
                        play = ScanMEthods.scanHorizontal(playCheck, i)[1] + i;
                        return play;
                    } else if (board[0][ScanMEthods.scanDiagonal1(playCheck, i)[1] - 1] == ' ' &&
                            ScanMEthods.scanHorizontal(playCheck, i)[1] - 1 >= 0 &&
                            board[ScanMEthods.scanHorizontal(playCheck, i)[0]][ScanMEthods.scanHorizontal(playCheck, i)[1] -1] == ' ') {
                        play = ScanMEthods.scanHorizontal(playCheck, i)[1] - 1;
                        return play;
                    }
                } else if (ScanMEthods.scanDiagonal1(playCheck, i)[0] != -1) {
                    if(board[0][ScanMEthods.scanDiagonal1(playCheck, i)[1] - i] == ' ' &&
                            board[ScanMEthods.scanDiagonal1(playCheck, i)[0] - i][ScanMEthods.scanDiagonal1(playCheck, i)[1] - i] == ' ') {
                        play = ScanMEthods.scanDiagonal1(playCheck, i)[1] - i;
                        return play;
                    }
                } else if (ScanMEthods.scanDiagonal2(playCheck, i)[0] != -1) {
                    if(board[0][ScanMEthods.scanDiagonal1(playCheck, i)[1] + i] == ' ' &&
                            board[ScanMEthods.scanDiagonal2(playCheck, i)[0] - i][ScanMEthods.scanDiagonal2(playCheck, i)[1] + i] == ' ') {
                        play = ScanMEthods.scanDiagonal2(playCheck, i)[1] + i;
                        return play;
                    }
                }
            }
        }
        return play;
    }
}
            /*for (int row = board.length - 1; row >= board.length - 3; row--) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == playCheck &&
                            board[row - 1][col] == playCheck &&
                            board[row - 2][col] == playCheck){
                        if(board[row - 3][col] == ' '){
                            play = col;
                            return play;
                        }
                    }
                }
            }
            //Horizontal 3
            for (int row = board.length - 1; row >= 0; row--) {
                for (int col = 0; col < board[0].length - 3; col++) {
                    if (board[row][col] == playCheck &&
                            board[row][col + 1] == playCheck &&
                            board[row][col + 2] == playCheck){
                        if(board[row][col + 3] == ' ') {
                            play = col + 3;
                            break;
                        } else if ((col - 1) >= 0 && board[row][col - 1] == ' ') {
                            play = col - 1;
                            return play;
                        }
                    }
                }
            }
            //Diagonal 3
            for (int row = (board.length - 1); row >= board.length - 3; row--) {
                for (int col = board[0].length - 1; col >= board[0].length - 4; col--) {
                    if (board[row][col] == playCheck &&
                            board[row - 1][col - 1] == playCheck &&
                            board[row - 2][col - 2] == playCheck) {
                        if (board[row - 3][col - 3] == ' ') {
                            play = col - 3;
                            return play;
                        }
                    }
                }
            }
            for (int row = board.length - 1; row >= 3 ; row--) {
                for (int col = 0; col < board[0].length - 3; col++) {
                    if (board[row][col] == playCheck &&
                            board[row - 1][col + 1] == playCheck &&
                            board[row - 2][col + 2] == playCheck){
                        if(board[row - 3][col + 3] == ' '){
                            play = col + 3;
                            return play;
                        }
                    }
                }
           }*/

