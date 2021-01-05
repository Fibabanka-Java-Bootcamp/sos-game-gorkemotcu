package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int sosChances(char [][]board, int n){
        int total=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n-2;j++)
                if(board[i][j]=='S'&& board[i][j+1]=='O' && board[i][j+2]=='S'){
                    total++;
                    }}
            for(int i=0;i<n-2;i++){
                for(int j=0;j<n;j++)
                if(board[i][j]=='S'&& board[i+1][j]=='O'&& board[i+2][j]=='S'){
                    total++;
                    }}
            for(int i=0;i<n-2;i++){
                for(int j=0;j<n-2;j++)
                if(board[i][j]=='S'&& board[i+1][j+1]=='O'&& board[i+2][j+2]=='S'){
                    total++;
                    }}
            for(int i=0;i<n-2;i++){
                for(int j=0;j<n-2;j++){
                if(board[i][j+2]=='S'&& board[i+1][j+1]=='O'&& board[i+2][j]=='S'){
                    total++;
                    }}
        } return total;
    }
    public static int addPoint(int computerScore,int score,int point){
            computerScore=computerScore+point;
            System.out.println("Bilgisayar SOS yaptı. Oyuncu: "+score+" Bilgisayar: "+computerScore);
            return computerScore;

    }
    public static int addPointPlayer(int score, int computerScore,int point){
            score+=point;
            System.out.println("Oyuncu SOS yaptı. Oyuncu: "+score+ " Bilgisayar: "+computerScore);
            return score;

    }

    public static boolean isFull(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j]=='-')
                    return false;
            }
        }
        return true;
    }

    public static void showBoard(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
            }System.out.println();
        }
    }
    public static boolean isEmpty(int x,int y, char [][] board){
        return board[x][y]=='-';
    }

    public static void putTheLetter(int n,char [][] board){
        int[] arr ={'S','O'};
        Random random = new Random();
        int randomNumber=random.nextInt(arr.length);
        char randomLetter = (char)arr[randomNumber];
        int randomx=random.nextInt(n);
        int randomy=random.nextInt(n);
        boolean valid=isEmpty(randomx,randomy,board);
        while(valid==false){
            randomx=random.nextInt(n);
            randomy=random.nextInt(n);
            valid=isEmpty(randomx,randomy,board);
        }
        board[randomx][randomy]= randomLetter;
        System.out.println("Bilgisayar hamlesini yaptı.");
    }

    public static void main(String[] args) {
        System.out.println("SOS tahtasının boyutunu seçmek için 3'ten 7'ye kadar bir tam sayı giriniz.");
        int n, score = 0, computerScore = 0;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        if (n <= 7 && n >= 3)
            System.out.println("Tahta boyutu : " + n + "x" + n);
        else {
            int exit = 0;
            while (exit == 0) {
                System.out.println("SOS tahtasının boyutunu seçmek için 3'ten 7'ye kadar bir tam sayı giriniz.");
                n = scanner.nextInt();
                if (n <= 7 && n >= 3) {
                    System.out.println("Tahta boyutu : " + n + "x" + n);
                    break;
                }
            }
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }
        showBoard(board);
        boolean game = true;
        while (game) {
            boolean replayPlayer = true;
            while (replayPlayer) {
                System.out.println("Hangi harfi koymak istiyorsunuz? (S veya O giriniz.)");
                char letter = scanner.next().charAt(0);

                while (true) {
                    if (letter == 'S' || letter == 'O')
                        break;
                    System.out.println("Hangi harfi koymak istiyorsunuz? (S veya O giriniz.)");
                    letter = scanner.next().charAt(0);
                }

                System.out.println("Harfi koymak istediğiniz koordinatı giriniz.");
                int i = scanner.nextInt();
                int j = scanner.nextInt();

                while (i > 7 || i < 1 || j > 7 || j < 1) {
                    System.out.println("Sınırların dışında koordinat girdiniz.Harfi koymak istediğiniz koordinatı giriniz.");
                    i = scanner.nextInt();
                    j = scanner.nextInt();
                }

                boolean valid = isEmpty(i - 1, j - 1, board);

                while (valid == false) {
                    System.out.println("Koordinat dolu.Harfi koymak istediğiniz koordinatı giriniz.");
                    i = scanner.nextInt();
                    j = scanner.nextInt();
                    while (i > 7 || i < 1 || j > 7 || j < 1) {
                        System.out.println("Sınırların dışında koordinat girdiniz.Harfi koymak istediğiniz koordinatı giriniz.");
                        i = scanner.nextInt();
                        j = scanner.nextInt();
                    }
                    valid = isEmpty(i - 1, j - 1, board);
                }
                int total1 = sosChances(board, n);
                board[i - 1][j - 1] = letter;
                int total2 = sosChances(board, n);
                if (total2 - total1 > 0) {
                    score = addPointPlayer(score, computerScore, total2 - total1);
                } else
                    replayPlayer = false;
                showBoard(board);
                if (isFull(board)) {
                    System.out.println("Oyun sona erdi.");
                    if (score > computerScore) {
                        System.out.println("Oyuncu kazandı.");
                    } else if (score == computerScore) {
                        System.out.println("Berabere.");
                    } else {
                        System.out.println("Bilgisayar kazandı.");
                    }
                    game = false;
                    break;
                }
            }

            boolean replayComputer = true;
            while (replayComputer) {
                int total2 = sosChances(board, n);
                putTheLetter(n, board);
                int total3 = sosChances(board, n);
                if (total3 - total2 > 0)
                    computerScore = addPoint(computerScore, score, total3 - total2);
                else
                    replayComputer = false;
                showBoard(board);
                if (isFull(board)) {
                    System.out.println("Oyun sona erdi.");
                    if (score > computerScore) {
                        System.out.println("Oyuncu kazandı.");
                    } else if (score == computerScore) {
                        System.out.println("Berabere.");
                    } else {
                        System.out.println("Bilgisayar kazandı.");
                    }
                    game = false;
                }
            }
        }
    }
}