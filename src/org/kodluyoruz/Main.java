package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void sosChances(char [][]board, int n){
        for(int i=0;i<n-2;i++){
            for(int j=0;j<n-2;j++)
                if(board[i][j]=='S'&& board[i][j+1]=='O' && board[i][j+2]=='S'){
                    System.out.println("puan kazandınız.");}
                else if(board[i][j]=='S'&& board[i+1][j]=='O'&& board[i+2][j]=='S'){
                    System.out.println("puan kazandınız..");}
                else if(board[i][j]=='S'&& board[i+1][j+1]=='O'&& board[i+2][j+2]=='S'){
                    System.out.println("puan kazandınız...");}

        }
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
        int n, score, computerScore;
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
        for (int i = 0; i < n;i++) {
            for (int j = 0; j < n; j++) {
                board[i][j]= '-';
            }
        }
        showBoard(board);
        while(true){
            System.out.println("Hangi harfi koymak istiyorsunuz? (S veya O giriniz.)");
            char letter=scanner.next().charAt(0);

            while(letter!='S' || letter!= 'O' ){
                if (letter=='S' || letter== 'O')
                    break;
                System.out.println("Hangi harfi koymak istiyorsunuz? (S veya O giriniz.)");
                letter=scanner.next().charAt(0);
            }

            System.out.println("Harfi koymak istediğiniz koordinatı giriniz.");
            int i=scanner.nextInt();
            int j=scanner.nextInt();

            while(i>7 || i<1 || j>7 || j<1 ){
                System.out.println("Sınırların dışında koordinat girdiniz.Harfi koymak istediğiniz koordinatı giriniz.");
                i=scanner.nextInt();
                j=scanner.nextInt();
            }

            boolean valid=isEmpty(i-1,j-1,board);

            while(valid==false){
                System.out.println("Koordinat dolu.Harfi koymak istediğiniz koordinatı giriniz.");
                i=scanner.nextInt();
                j=scanner.nextInt();
                while(i>7 || i<1 || j>7 || j<1 ){
                    System.out.println("Sınırların dışında koordinat girdiniz.Harfi koymak istediğiniz koordinatı giriniz.");
                    i=scanner.nextInt();
                    j=scanner.nextInt();
                }
                valid=isEmpty(i-1,j-1,board);
            }
            board[i-1][j-1]= letter;
            putTheLetter(n,board);

            showBoard(board);
            sosChances(board,n);
        }
    }
}