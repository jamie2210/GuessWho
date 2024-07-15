package GuessWho
import scala.util.Random



class GameBoard(characters:Seq[Character], defaultChosenCharacter:Option[Character]) {


//  Chose random player index
  private val randomIndex:Int = Random.nextInt(characters.length)

//  For testing, use default chosen character if set
  private val chosenCharacter:Character = defaultChosenCharacter match {
    case Some(character) => character
    case None => characters(randomIndex)
  }

  private val guessAbout:Guess = new Guess(chosenCharacter)

//  keep track of remaining players
  private var _remainingPlayers:Seq[Character] = characters

  def getRemainingCharacters:Seq[Character] = _remainingPlayers


  def remove_random_character():Seq[Character]={
    if (_remainingPlayers.length < 2) {
      _remainingPlayers
    }else{
      val notChosenPlayers:Seq[Character] = _remainingPlayers.filterNot(_ == chosenCharacter)
      val random_indexInt:Int = Random.nextInt(notChosenPlayers.length)
      val playerToRemove:Character = notChosenPlayers(random_indexInt)
      println(s"Removed: ${playerToRemove.name}")
      _remainingPlayers.filterNot(_ == notChosenPlayers(random_indexInt))
    }
  }

  def filterRemaining(attribute:Int):Seq[Character]={
   attribute match {
      // Boolean params
      case 1 => if(guessAbout.guessHasHair) _remainingPlayers.filter(_.hasHair) else _remainingPlayers.filterNot(_.hasHair)
      case 2 => if(guessAbout.guessHasFacialHair) _remainingPlayers.filter(_.hasFacialHair) else _remainingPlayers.filterNot(_.hasFacialHair)
      case 3 => if(guessAbout.guessHasGlasses) _remainingPlayers.filter(_.hasGlasses) else _remainingPlayers.filterNot(_.hasGlasses)
      case 4 => if(guessAbout.guessHasHat) _remainingPlayers.filter(_.hasHat) else _remainingPlayers.filterNot(_.hasHat)
      // Gender params+
      case 5 => if(guessAbout.guessGender("MALE")) _remainingPlayers.filter(_.gender == Gender.MALE) else _remainingPlayers.filterNot(_.gender == Gender.MALE)
      // Eye Colour params
      case 6 => if(guessAbout.guessEyeColour("BLUE")) _remainingPlayers.filter(_.eyeColour == EyeColour.BLUE) else _remainingPlayers.filterNot(_.eyeColour == EyeColour.BLUE)
      case 7 => if(guessAbout.guessEyeColour("GREEN")) _remainingPlayers.filter(_.eyeColour == EyeColour.GREEN) else _remainingPlayers.filterNot(_.eyeColour == EyeColour.GREEN)
      case 8 => if(guessAbout.guessEyeColour("BROWN")) _remainingPlayers.filter(_.eyeColour == EyeColour.BROWN) else _remainingPlayers.filterNot(_.eyeColour == EyeColour.BROWN)
      // Hair Colour params
      case 9 => if(guessAbout.guessHairColour("BRUNETTE")) _remainingPlayers.filter(_.hairColour == HairColour.BRUNETTE) else _remainingPlayers.filterNot(_.hairColour == HairColour.BRUNETTE)
      case 10 => if(guessAbout.guessHairColour("BLONDE")) _remainingPlayers.filter(_.hairColour == HairColour.BLONDE) else _remainingPlayers.filterNot(_.hairColour == HairColour.BLONDE)
      case 11 => if(guessAbout.guessHairColour("RED")) _remainingPlayers.filter(_.hairColour == HairColour.RED) else _remainingPlayers.filterNot(_.hairColour == HairColour.RED)

//       Option 8 is hints therefore 81 = hint 1
      case 81 => remove_random_character()


      case _ => _remainingPlayers
    }
  }
  // Guess name
  def filterRemaining(attribute:Int, guess:String):(Seq[Character], Boolean)= {
    attribute match {
      case 12 => if (guessAbout.guessName(guess)) (Seq(chosenCharacter), true) else (_remainingPlayers.filterNot(_.name.toLowerCase == guess.toLowerCase()),false)
      case _ => (_remainingPlayers, false)
    }
  }
}





