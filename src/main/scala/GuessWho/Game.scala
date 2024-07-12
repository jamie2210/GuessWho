package GuessWho
import scala.util.Random







class Game(characters:Seq[Character]) {

//  Choose random player
  private val randomIndex:Int = Random.nextInt(characters.length)


  //  private val chosenCharacter:Character = characters(randomIndex)
//  FOR TESTING PURPOSES
  private val chosenCharacter:Character = characters.filter(_.name=="Jamie").head


  val guessAbout:Guess = new Guess(chosenCharacter)

//  keep track of remaining players
  private var _remainingPlayers:Seq[Character] = characters

//  Implement filtering process depending on question
  def makeGuess(question:String){???}

  def getChosenPlayer:Character = chosenCharacter

  def filterRemaining(attribute:Int):Seq[Character]={
    attribute match {
      case 1 => if(guessAbout.guessHasHair) _remainingPlayers.filter(_.hasHair) else _remainingPlayers.filterNot(_.hasHair)
      case 2 => if(guessAbout.guessHasFacialHair) _remainingPlayers.filter(_.hasFacialHair) else _remainingPlayers.filterNot(_.hasFacialHair)
      case 3 => if(guessAbout.guessHasGlasses) _remainingPlayers.filter(_.hasGlasses) else _remainingPlayers.filterNot(_.hasGlasses)
      case 4 => if(guessAbout.guessHasHat) _remainingPlayers.filter(_.hasHat) else _remainingPlayers.filterNot(_.hasHat)
      case _ => _remainingPlayers
    }
  }


}





