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
      _recentUpdateMessage = s"Hint: It's not ${playerToRemove.name}"
      _gameCharacters.filterNot(_ == notChosenPlayers(random_indexInt))
    }
  }

  //  Hint 2: Displays a rando letter from chosen characters name
  def display_random_letter(): Seq[Character] = {
    val random_indexInt: Int = Random.nextInt(chosenCharacter.name.length)
    _recentUpdateMessage = s"Hint: Name contains the letter '${chosenCharacter.name.toLowerCase.charAt(random_indexInt)}' "
    _gameCharacters
  }

  def filterByEyeColour(colour: EyeColour): Seq[Character] = {
    if (guessAbout.guessEyeColour(colour)) {
      _recentUpdateMessage = s"Their eyes are $colour"
      _gameCharacters.filter(_.eyeColour == colour)
    }
    else {
      _recentUpdateMessage = s"Their eyes are not $colour"
      _gameCharacters.filterNot(_.eyeColour == colour)
    }
  }

  def filterByHairColour(colour: HairColour): Seq[Character] = {
    if (guessAbout.guessHairColour(colour)) {
      _recentUpdateMessage = s"They have $colour hair"
      _gameCharacters.filter(_.hairColour == colour)
    }
    else {
      _recentUpdateMessage = s"They do not have $colour hair"
      _gameCharacters.filterNot(_.hairColour == colour)
    }
  }

  def filterByGender(gender: Gender): Seq[Character] = {
    if (guessAbout.guessGender(gender)) {
      _recentUpdateMessage = s"They are $gender"
      _gameCharacters.filter(_.gender == gender)
    } else {
      _recentUpdateMessage = s"They are not $gender"
      _gameCharacters.filterNot(_.gender == gender)}
  }

  def filterByHasHair: Seq[Character] = {
    if (guessAbout.guessHasHair) {
      _recentUpdateMessage = "They have hair!!"
      _gameCharacters.filter(_.hasHair)
    } else {
      _recentUpdateMessage = "They do not have hair!"
      _gameCharacters.filterNot(_.hasHair)
    }
  }

  def filterByHasGlasses: Seq[Character] = {
    if (guessAbout.guessHasGlasses) {
      _recentUpdateMessage = "They have glasses :)"
      _gameCharacters.filter(_.hasGlasses)
    } else {
      _recentUpdateMessage = "They do not have glasses :("
      _gameCharacters.filterNot(_.hasGlasses)
    }
  }

  def filterByHasHat: Seq[Character] = {
    if (guessAbout.guessHasHat) {
      _recentUpdateMessage = "They are wearing a hat"
      _gameCharacters.filter(_.hasHat)
    } else {
      _recentUpdateMessage = "They are not wearing a hat"
      _gameCharacters.filterNot(_.hasHat)
    }
  }

  def filterByHasFacialHair: Seq[Character] = {
    if (guessAbout.guessHasFacialHair) {
      _recentUpdateMessage = "They have facial hair"
      _gameCharacters.filter(_.hasFacialHair)
    } else {
      _recentUpdateMessage = "They do not have facial hair"
      _gameCharacters.filterNot(_.hasFacialHair)
    }
  }

  //  Filter the characters depending on question
  def filterRemaining(question: Question): Seq[Character] = {
    question match {
      case Question.RemoveCharacterHint => remove_random_character()
      case Question.LetterFromNameHint => display_random_letter()
      case Question.BlueEyesQuestion => filterByEyeColour(EyeColour.BLUE)
      case Question.GreenEyesQuestion => filterByEyeColour(EyeColour.GREEN)
      case Question.BrownEyesQuestion => filterByEyeColour(EyeColour.BROWN)
      case Question.BlondeHairQuestion => filterByHairColour(HairColour.BLONDE)
      case Question.BrunetteHairQuestion => filterByHairColour(HairColour.BRUNETTE)
      case Question.RedHairQuestion => filterByHairColour(HairColour.RED)
      case Question.MaleGenderQuestion => filterByGender(Gender.MALE)
      case Question.FemaleGenderQuestion => filterByGender(Gender.FEMALE)
      case Question.hasHairQuestion => filterByHasHair
      case Question.hasGlassesQuestion => filterByHasGlasses
      case Question.hasHatQuestion => filterByHasHat
      case Question.hasFacialHairQuestion => filterByHasFacialHair
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





