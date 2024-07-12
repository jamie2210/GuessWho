package GuessWho
import scala.util.Random







class Game(characters:List[Character]) {

//  Choose random player
  private val randomIndex:Int = Random.nextInt(characters.length)
  private val chosenCharacter:Character = characters(randomIndex)
  val guessAbout:Guess = new Guess(chosenCharacter)

//  keep track of remaining players
  private var _remainingPlayers:List[Character] = characters

//  Implement filtering process depending on question
  def makeGuess(question:String){???}

  def filterRemaining(attribute:Int):List[Character]={
    attribute match {
      case 1 => if(guessAbout.guessHasHair) _remainingPlayers.filter(_.hasHair) else _remainingPlayers.filterNot(_.hasHair)
      case _ => _remainingPlayers
    }
  }


}





