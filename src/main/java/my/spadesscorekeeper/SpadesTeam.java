/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.spadesscorekeeper;

/**
 *
 * @author jarodh
 */
public class SpadesTeam {
    public int bid1;
    public int bid2;
    public SpecialBid specialBid;

    private int totalBidsTaken;
    private boolean specialBidSuccess;
    private int score;

    public enum SpecialBid {
      NONE,
      NELLO,
      BLIND_NELLO
    }

    public SpadesTeam() {
      resetValues();
    }
    
    private void resetValues() {
        this.bid1 = 0;
        this.bid2 = 0;
        this.totalBidsTaken = 0;
        this.specialBid = SpecialBid.NONE;
    }

    public void setTotalBidsTaken(int bids) {
      if (bids >= 0) {
        this.totalBidsTaken = bids;
      }
    }

    public void setSpecialBid(boolean success) {
      this.specialBidSuccess = success;
    }
    
    public int getScore() {
        return this.score;
    }

    public int calculateScore() {
      int currentScore;

      if (successfulBid()) {

        //10 points for each trick bid.
        currentScore = (this.bid1 + this.bid2) * 10;

        //One extra point for each additional trick taken.
        if (this.totalBidsTaken > this.bid1 + this.bid2) {
          currentScore += this.totalBidsTaken - (this.bid1 + this.bid2);
        }
      }
      else {
        //Negate the bids as penalty.
        currentScore = (this.bid1 + this.bid2) * -10;
      }

      switch (this.specialBid) {
        case NELLO:
          if (specialBidSuccess) {
            currentScore += 100;
          }
          else {
            currentScore += -100;
          }
          break;
        case BLIND_NELLO:
          if (specialBidSuccess) {
            currentScore += 200;
          }
          else {
            currentScore += -200;
          }
          break;
        case NONE:
        default:
          break;
      }

      this.score += currentScore;
      
      resetValues();
      
      return this.score;
    }

    private boolean successfulBid() {
      return this.totalBidsTaken >= (this.bid1 + this.bid2);
    }
}
