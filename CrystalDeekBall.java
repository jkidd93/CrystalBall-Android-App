/*
    Used teamtreehouse.com to help create this app
 */

package com.DeekApps.crystal.deek.ball;

import java.util.Random;

public class CrystalDeekBall 
{
	String yes1 = "It definitely is going to happen.";
	String yes2 = "Surely it shall occur!";
	String yes3 = "Not even the high heavens can stop this from occuring.";
	String no1 = "You're crazy if you think that's gonna happen.";
	String no2 = "Just stop now. Please.";
	String no3 = "I will delete myself from the Play Store if that comes true.";
	String maybe1 = "Why would you even think that?";
	String maybe2 = "It might occur. Depends on your luck.";
	String maybe3 = "Maybe... If you were God.";
	
	
	String [] answerArray = { yes1, yes2, yes3, no1, no2, no3, maybe1, maybe2, maybe3};
	
	public String getAnswer()
	{
	
	// Instantiates answer
	String answer ="";
	
	//Randomly selects answer
	Random randGen = new Random(); // new random generator 
	
	int randNum = randGen.nextInt(answerArray.length);
	answer = Integer.toString(randNum);
	
	// results in an answer from the array index
	answer = answerArray[randNum];
	
	
	return answer;
	}
}
 
