package com.example.elena.tennissimulation;

/**
 * Created by Elena on 9/2/2017.
 */

class ScoreUtils {
    static String getTextScore(int computerScore, int playerScore){
        if(computerScore==0 ){
            if(playerScore==1){
                return "Love Fifteen";
            }else if(playerScore==2){
                return "Love Thirty";
            }else if(playerScore==3){
                return "Love Forty";
            }else {/*player score == 4*/
                return "You won!";
            }
        }else if(computerScore == 1){
            if( playerScore==0){
                return "Fifteen Love";
            }else if( playerScore==1){
                return "Fifteen All";
            }else if ( playerScore==2){
                return "Fifteen - Thirty";
            }else if ( playerScore==3){
                return "Fifteen - Forty";
            }else{
                return "You won!";
            }
        }else if(computerScore == 2){
            if( playerScore==0){
                return "Thirty - Love";
            }else if( playerScore==1){
                return "Thirty - Fifteen";
            }else if ( playerScore==2){
                return "Thirty All";
            }else if ( playerScore==3){
                return "Thirty - Forty";
            }else{
                return "You won!";
            }
        }else if(computerScore == 3){
            if( playerScore==0){
                return "Forty - Love";
            }else if( playerScore==1){
                return "Forty - Fifteen";
            }else if ( playerScore==2){
                return "Forty - Thirty";
            }else if ( playerScore==3){
                return "Deuce";
            }else if(playerScore==4){
                return "Advantage Player";
            }else{
                return "You won!";
            }
        }else if(computerScore == 4){/*first advantage*/
            if( playerScore==3){
                return "Advantage Computer";
            }else {
                return "Computer won!";
            }
        }else {/*computer surely won*/
            return "Computer won!";
        }
    }
}
