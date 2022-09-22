package Connect_4_classes;

public class Board {
    public String[][] board = new String[7][6];

    public Board() {
    }

    public void renderBoard(){
        System.out.println("\n".repeat(60));
        System.out.println(Player.currentPlayer + ":s turn\n");
        for(String[] row : this.board){
            for(String cell: row){
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------------------------");
    }

}
