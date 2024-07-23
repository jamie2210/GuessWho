package GuessWho
import scala.util.Random



class GameBoard(characters:Seq[Character], defaultChosenCharacter:Option[Character]) {


  //  Chose random player index
  private val randomIndex: Int = Random.nextInt(characters.length)

  //  Var to keep track of Hints
  private var _recentUpdateMessage: String = ""

  //  For testing, use default chosen character if set else use randomly chosen
  private val chosenCharacter: Character = defaultChosenCharacter match {
    case Some(character) => character
    case None => characters(randomIndex)
  }

  //  Store new guess instance to guess about the chosen character
  private val guessAbout: Guess = new Guess(chosenCharacter)

  //  Store list of players 
  private val _gameCharacters: Seq[Character] = characters


  //  Get methods for GuessWho Object to access
  def getRemainingCharacters: Seq[Character] = _gameCharacters

  def get_update_message(): String = _recentUpdateMessage

  //  Method to allow GuessWho object to reset update message
  def reset_update_message(): String = {
    _recentUpdateMessage = ""
    _recentUpdateMessage
  }

  //  For Hint 1: Removes random character that is not the chosen player
  def remove_random_character(): Seq[Character] = {
    if (_gameCharacters.length < 2) {
      _gameCharacters
    } else {
      val notChosenPlayers: Seq[Character] = _gameCharacters.filterNot(_ == chosenCharacter)
      val random_indexInt: Int = Random.nextInt(notChosenPlayers.length)
      val playerToRemove: Character = notChosenPlayers(random_indexInt)
      _recentUpdateMessage = (s"Hint: It's not ${playerToRemove.name}")
      _gameCharacters.filterNot(_ == notChosenPlayers(random_indexInt))
    }
  }

  //  Hint 2: Displays a rando letter from chosen characters name
  def display_random_letter(): Seq[Character] = {
    val random_indexInt: Int = Random.nextInt(chosenCharacter.name.length)
    _recentUpdateMessage = (s"Hint: Name contains the letter '${chosenCharacter.name.toLowerCase.charAt(random_indexInt)}' ")
    _gameCharacters
  }

  //  Filter the characters depending on question
  def filterRemaining(question: Question): Seq[Character] = {
    question match {
      case Question.RemoveCharacterHint => remove_random_character()
      case Question.LetterFromNameHint => display_random_letter()
      case Question.BlueEyesQuestion => if (guessAbout.guessEyeColour("BLUE")) _gameCharacters.filter(_.eyeColour == EyeColour.BLUE) else _gameCharacters.filterNot(_.eyeColour == EyeColour.BLUE)
      case Question.GreenEyesQuestion => if (guessAbout.guessEyeColour("GREEN")) _gameCharacters.filter(_.eyeColour == EyeColour.GREEN) else _gameCharacters.filterNot(_.eyeColour == EyeColour.GREEN)
      case Question.BrownEyesQuestion => if (guessAbout.guessEyeColour("BROWN")) _gameCharacters.filter(_.eyeColour == EyeColour.BROWN) else _gameCharacters.filterNot(_.eyeColour == EyeColour.BROWN)
      case Question.BlondeHairQuestion => if (guessAbout.guessHairColour("BLONDE")) _gameCharacters.filter(_.hairColour == HairColour.BLONDE) else _gameCharacters.filterNot(_.hairColour == HairColour.BLONDE)
      case Question.BrunetteHairQuestion => if (guessAbout.guessHairColour("BRUNETTE")) _gameCharacters.filter(_.hairColour == HairColour.BRUNETTE) else _gameCharacters.filterNot(_.hairColour == HairColour.BRUNETTE)
      case Question.RedHairQuestion => if (guessAbout.guessHairColour("RED")) _gameCharacters.filter(_.hairColour == HairColour.RED) else _gameCharacters.filterNot(_.hairColour == HairColour.RED)
      case Question.MaleGenderQuestion => if (guessAbout.guessGender("MALE")) _gameCharacters.filter(_.gender == Gender.MALE) else _gameCharacters.filterNot(_.gender == Gender.MALE)
      case Question.FemaleGenderQuestion => if (guessAbout.guessGender("MALE")) _gameCharacters.filter(_.gender == Gender.FEMALE) else _gameCharacters.filterNot(_.gender == Gender.FEMALE)
      case Question.hasHairQuestion => if (guessAbout.guessHasHair) _gameCharacters.filter(_.hasHair) else _gameCharacters.filterNot(_.hasHair)
      case Question.hasGlassesQuestion => if (guessAbout.guessHasGlasses) _gameCharacters.filter(_.hasGlasses) else _gameCharacters.filterNot(_.hasGlasses)
      case Question.hasHatQuestion => if (guessAbout.guessHasHat) _gameCharacters.filter(_.hasHat) else _gameCharacters.filterNot(_.hasHat)
      case Question.hasFacialHairQuestion => if (guessAbout.guessHasFacialHair) _gameCharacters.filter(_.hasFacialHair) else _gameCharacters.filterNot(_.hasFacialHair)
      case _ => _gameCharacters
    }

  }

  // Guess name and return a tuple, if guessed name == chosen character name then set to true
  // Second part of tuple is unpacked and used to declare winner
  def filterRemaining(guess: String): (Seq[Character], Boolean) = {
    if (guessAbout.guessName(guess)) (Seq(chosenCharacter), true) else {
      val afterFilterByName = _gameCharacters.filterNot(_.name.toLowerCase == guess.toLowerCase())
      if (afterFilterByName.length == 10) {
        _recentUpdateMessage = s"That name is not recognised...?"
        (afterFilterByName, false)
      } else {
        _recentUpdateMessage = s"It's not $guess"
        (afterFilterByName, false)
      }
    }
  }
}





