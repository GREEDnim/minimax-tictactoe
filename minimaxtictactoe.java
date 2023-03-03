import java.util.Arrays;
import java.util.Scanner;

public class minimaxtictactoe {



    public static void main(String[] args) {

        char[][] board=new char[][]{{'-','-','-'},{'-','-','-'},{'-','-','-'}};
        playgame(board);


    }


    public static void playgame(char[][] board)
    {
        int player=1;
        boolean someOneWon=false;
        Scanner in=new Scanner(System.in);
        while( movesRemaining(board))
        {
            System.out.println("board NOW : ");
           for(char[] row:board) System.out.println(Arrays.toString(row));
           if(player==1)
            {
                System.out.println("chance of player 1 ");
                System.out.println("enter row and column index to put 'x' : ");
                int r=in.nextInt();
                int c=in.nextInt();
                if(!isVaildMove(board,r,c))
                {
                    System.out.println("wrong move, try again ");
                    continue;
                }
                board[r][c]='X';

                if(isWinner(board,'X'))
                {
                    someOneWon=true;
                    System.out.println("player 1 wins");
                    break;
                }
                player=0;
            }
            else
            {
                System.out.println("chance of player AI ");
                int[] ai=getAiMove(board);
                board[ai[0]][ai[1]]='O';
                if(isWinner(board,'O'))
                {
                    someOneWon=true;
                    System.out.println("player AI wins");
                    break;
                }
                player=1;

            }

        }
        System.out.println("board NOW : ");
        for(char[] row:board) System.out.println(Arrays.toString(row));
        if(!someOneWon) System.out.println("draw");
    }

    private static boolean isVaildMove(char[][] board, int r, int c) {

        return board[r][c]=='-';
    }

    private static int[] getAiMove(char[][] board) {

        int max=Integer.MIN_VALUE;
        int[] ans = new int[2];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]=='-')
                {
                    board[i][j]='O';
                    int cur=minimax(board,false);
                    board[i][j]='-';
                    if(cur>max)
                    {
                        max=cur;
                        ans=new int[]{i,j};

                    }
                }
            }
        }

        return ans;
    }

    private static int minimax(char[][] board, boolean ai) {

        if(isWinner(board,'O')) return 1;
        if(isWinner(board,'X')) return -1;
        if(!movesRemaining(board)) return 0;
        if(ai)
        {
            int max=Integer.MIN_VALUE;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(board[i][j]=='-')
                    {

                        board[i][j]='O';
                        max=Math.max(minimax(board,false),max);
                        board[i][j]='-';
                    }
                }
            }
            return max;
        }
        else
        {
            int min=Integer.MAX_VALUE;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(board[i][j]=='-')
                    {
                        board[i][j]='X';
                        min=Math.min(minimax(board, true),min);
                        board[i][j]='-';
                    }
                }
            }
            return min;
        }
    }

    public static boolean movesRemaining(char[][] board)
    {
        for(char[] arr:board)
        {
            for (char ele:arr)
            {
                if(ele=='-') return true;
            }
        }
        return false;
    }
    public static boolean isWinner(char[][] board,char c)
    {
        boolean same;
        //checks if any row or column has same value.
        for(int i=0;i<3;i++)
        {
           same=true;
            for(int j=0;j<3;j++)
            {
                if(board[i][j]!=c)
                {
                    same=false;
                    break;
                }
            }
            if(same) return same;
            same=true;
            for(int j=0;j<3;j++) {
                if (board[j][i] != c) {
                    same = false;
                    break;
                }
            }
            if (same) return same;
        }
        //
        same=true;
        for(int i=0;i<3;i++)
        {
            if(board[i][i]!=c)
            {
                same=false;
                break;
            }
        }
        if (same) return same;

        same=true;
        for(int i=0;i<3;i++)
        {
            if(board[i][2-i]!=c)
            {
                same=false;
                break;
            }
        }
        return same;
    }
}
















