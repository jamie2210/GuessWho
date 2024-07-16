package GuessWho
import scala.util.Random



class GameBoard(characters:Seq[Character], defaultChosenCharacter:Option[Character]) {


//  Chose random player index
  private val randomIndex:Int = Random.nextInt(characters.length)

  private var _recentUpdateMessage: String = ""

//  For testing, use default chosen character if set
  private val chosenCharacter:Character = defaultChosenCharacter match {
    case Some(character) => character
    case None => characters(randomIndex)
  }

  private val guessAbout:Guess = new Guess(chosenCharacter)

  //  Store list of players 
  private val _gameCharacters: Seq[Character] = characters

  
//  Get methods for GuessWho Object to access 
  def getRemainingCharacters:Seq[Character] = _gameCharacters
  
  def get_update_message():String = _recentUpdateMessage

//  Method to allow GuessWho object to reset update message
  def reset_update_message(): String = {
    _recentUpdateMessage = ""
  _recentUpdateMessage}

//  For Hint 1: Removes random character that is not the chosen player
  def remove_random_character():Seq[Character]={
    if (_gameCharacters.length < 2) {
      _gameCharacters
    }else{
      val notChosenPlayers:Seq[Character] = _gameCharacters.filterNot(_ == chosenCharacter)
      val random_indexInt:Int = Random.nextInt(notChosenPlayers.length)
      val playerToRemove:Character = notChosenPlayers(random_indexInt)
      _recentUpdateMessage = (s"Hint: It's not ${playerToRemove.name}")
      _gameCharacters.filterNot(_ == notChosenPlayers(random_indexInt))
    }
  }

//  Hint 2: Displays a rando letter from chosen characters name 
  def display_random_letter(): Seq[Character] = {
    val random_indexInt:Int = Random.nextInt(chosenCharacter.name.length)
    _recentUpdateMessage = (s"Hint: Name contains the letter '${chosenCharacter.name.toLowerCase.charAt(random_indexInt)}' ")
    _gameCharacters
  }

//  Filter the characters depending on question
  def filterRemaining(attribute:Int):Seq[Character]={
   attribute match {
      // Boolean params for 'has X' questions
      case 1 => if(guessAbout.guessHasHair) _gameCharacters.filter(_.hasHair) else _gameCharacters.filterNot(_.hasHair)
      case 2 => if(guessAbout.guessHasFacialHair) _gameCharacters.filter(_.hasFacialHair) else _gameCharacters.filterNot(_.hasFacialHair)
      case 3 => if(guessAbout.guessHasGlasses) _gameCharacters.filter(_.hasGlasses) else _gameCharacters.filterNot(_.hasGlasses)
      case 4 => if(guessAbout.guessHasHat) _gameCharacters.filter(_.hasHat) else _gameCharacters.filterNot(_.hasHat)
      // Gender params
      case 5 => if(guessAbout.guessGender("MALE")) _gameCharacters.filter(_.gender == Gender.MALE) else _gameCharacters.filterNot(_.gender == Gender.MALE)
      // Eye Colour params
      case 6 => if(guessAbout.guessEyeColour("BLUE")) _gameCharacters.filter(_.eyeColour == EyeColour.BLUE) else _gameCharacters.filterNot(_.eyeColour == EyeColour.BLUE)
      case 7 => if(guessAbout.guessEyeColour("GREEN")) _gameCharacters.filter(_.eyeColour == EyeColour.GREEN) else _gameCharacters.filterNot(_.eyeColour == EyeColour.GREEN)
      case 8 => if(guessAbout.guessEyeColour("BROWN")) _gameCharacters.filter(_.eyeColour == EyeColour.BROWN) else _gameCharacters.filterNot(_.eyeColour == EyeColour.BROWN)
      // Hair Colour params
      case 9 => if(guessAbout.guessHairColour("BRUNETTE")) _gameCharacters.filter(_.hairColour == HairColour.BRUNETTE) else _gameCharacters.filterNot(_.hairColour == HairColour.BRUNETTE)
      case 10 => if(guessAbout.guessHairColour("BLONDE")) _gameCharacters.filter(_.hairColour == HairColour.BLONDE) else _gameCharacters.filterNot(_.hairColour == HairColour.BLONDE)
      case 11 => if(guessAbout.guessHairColour("RED")) _gameCharacters.filter(_.hairColour == HairColour.RED) else _gameCharacters.filterNot(_.hairColour == HairColour.RED)

      // Option 8 is for hints therefore 81 = hint 1
      case 81 => remove_random_character()
      case 82 => display_random_letter()

      case _ => _gameCharacters
    }
  }
  // Guess name and return a tuple, if guessed name == chosen character name then set to true
  // Second part of tuple is unpacked and used to declare winner
  def filterRemaining(attribute:Int, guess:String):(Seq[Character], Boolean)= {
    attribute match {
      case 12 => if (guessAbout.guessName(guess)) (Seq(chosenCharacter), true) else (_gameCharacters.filterNot(_.name.toLowerCase == guess.toLowerCase()),false)
      case _ => (_gameCharacters, false)
    }
  }
}





